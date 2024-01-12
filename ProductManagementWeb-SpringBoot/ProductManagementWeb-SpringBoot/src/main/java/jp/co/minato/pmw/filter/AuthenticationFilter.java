package jp.co.minato.pmw.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

import jp.co.minato.pmw.constant.Constants;
import jp.co.minato.pmw.mapper.entity.UserEntity;

/**
 * 認証処理
 *
 * @author ShujiHidakaJP
 *
 */
public class AuthenticationFilter extends OncePerRequestFilter {

	private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationFilter.class);

	private static final String[] EXCLUDE_URIS = { "/public/", "/static/", "/favicon.ico" };

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		if (isFilterTarget(request) && isNotLogedin(request)) {
			String redirectTo = request.getContextPath() + "/public/loginInit";
			response.sendRedirect(redirectTo);
			LOGGER.info("Redirect to: " + redirectTo);
			LOGGER.debug("Redirect to: " + redirectTo);
			return;
		}
		chain.doFilter(request, response);
	}

	private boolean isNotLogedin(HttpServletRequest request) {
		return !isLogedin(request);
	}

	private boolean isLogedin(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session == null) {
			return false;
		}
		UserEntity loginInfo = (UserEntity) session.getAttribute(Constants.SESSION_KEY_LOGIN_USER);
		if (loginInfo == null) {
			return false;
		}
		return true;
	}

	private boolean isFilterTarget(HttpServletRequest request) {
		String contextPath = request.getContextPath();
		String requestUri = request.getRequestURI();
		String target = requestUri.replaceFirst(contextPath, "");
		for (String excludeUri : EXCLUDE_URIS) {
			if (target.startsWith(excludeUri)) {
				LOGGER.info("[Skip] AuthenticationFilter: " + requestUri);
				LOGGER.debug("[Skip] AuthenticationFilter: " + requestUri);
				return false;
			}
		}
		LOGGER.info("AuthenticationFilter: " + requestUri);
		LOGGER.debug("AuthenticationFilter: " + requestUri);
		return true;
	}
}
