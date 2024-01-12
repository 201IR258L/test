package jp.co.minato.pmw.filter;

import javax.servlet.Filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;

public enum ProductManagementWebWebAppFilter {
	// @formatter:off
	// 上から下へ実行します
	AuthenticationFilter(new AuthenticationFilter(), ProductManagementWebWebAppFilter.getOrder(), "/*"),
	AuthorizationFilter(new AuthorizationFilter(), ProductManagementWebWebAppFilter.getOrder(), "/*"),;

	private static Integer counter;
	// @formatter:on
	private final Filter filter;
	private final Integer order;
	private final String[] urlPatterns;

	public FilterRegistrationBean<Filter> getBean() {
		FilterRegistrationBean<Filter> bean = new FilterRegistrationBean<Filter>(filter);
		bean.setOrder(order);
		bean.addUrlPatterns(urlPatterns);
		return bean;
	}

	private ProductManagementWebWebAppFilter(Filter filter, Integer order, String... urlPatterns) {
		this.filter = filter;
		this.order = order;
		this.urlPatterns = urlPatterns;
	}

	private static Integer getOrder() {
		if (counter == null) {
			counter = 0;
		}
		return Integer.MIN_VALUE + counter++;
	}
}
