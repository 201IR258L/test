package jp.co.minato.pmw.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.co.minato.pmw.constant.Constants;
import jp.co.minato.pmw.entity.UserEntity;

/**
 * 認証フィルタ
 */
public class AuthenticationFilter implements Filter {

	private final String[] excludeUris = { "/static/", "/public/" };

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest servletRequest = (HttpServletRequest) request;

		if (isFilterTarget(servletRequest) && isNotLogedin(servletRequest)) {
			((HttpServletResponse) response).sendRedirect(servletRequest.getContextPath() + "/public/jsp/login.jsp");
			return;
		}
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

	private boolean isNotLogedin(HttpServletRequest request) {
		return !isLogedin(request);
	}

	private boolean isLogedin(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session == null) {
			return false;
		}
		UserEntity userEntity = (UserEntity) session.getAttribute(Constants.SESSION_KEY_LOGIN_USER);
		if (userEntity == null) {
			return false;
		}
		return true;
	}

	private boolean isFilterTarget(HttpServletRequest request) {
		String contextPath = request.getContextPath();
		String target = request.getRequestURI().replaceFirst(contextPath, "");
//		System.out.println("-------------------------------------------------");
//		System.out.println("target=" + target);
		for (String excludeUri : excludeUris) {
			if (target.startsWith(excludeUri)) {
				return false;
			}
		}
		return true;
	}
}
