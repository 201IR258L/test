package jp.co.minato.pmw;

import javax.servlet.Filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jp.co.minato.pmw.filter.ProductManagementWebWebAppFilter;

@Configuration
public class ProductManagementWebAppFilterConfig {

	@Bean
	public FilterRegistrationBean<Filter> authenticationFilter() {
		return ProductManagementWebWebAppFilter.AuthenticationFilter.getBean();
	}

	@Bean
	public FilterRegistrationBean<Filter> authorizationFilter() {
		return ProductManagementWebWebAppFilter.AuthorizationFilter.getBean();
	}
}
