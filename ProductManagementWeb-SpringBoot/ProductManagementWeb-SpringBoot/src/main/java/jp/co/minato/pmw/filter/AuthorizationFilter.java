package jp.co.minato.pmw.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

/**
 * 認可処理
 * @author ShujiHidakaJP
 *
 */
public class AuthorizationFilter extends OncePerRequestFilter {


	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		//System.out.println("AuthorizationFilter@start");

		chain.doFilter(request, response);
		//System.out.println("AuthorizationFilter@end");
	}
}
