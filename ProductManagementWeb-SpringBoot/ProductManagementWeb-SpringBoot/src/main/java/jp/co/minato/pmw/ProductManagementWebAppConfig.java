package jp.co.minato.pmw;

import java.util.Collections;

import javax.servlet.SessionTrackingMode;

import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@Import({ ProductManagementWebHttpSessionListener.class })
public class ProductManagementWebAppConfig implements WebMvcConfigurer {

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// @formatter:off
		registry
			.addResourceHandler("/static/**")
			.addResourceLocations("classpath:/static/");
		// @formatter:on
	}

	@Bean
	// public ServletContextInitializer
	// servletContextInitializer(@Value("${secure.cookie}") boolean secure) {
	public ServletContextInitializer servletContextInitializer() {
		return servletContext -> {
			servletContext.getSessionCookieConfig().setHttpOnly(true);
			//servletContext.getSessionCookieConfig().setSecure(true);
			servletContext.getSessionCookieConfig().setSecure(false);
			servletContext.setSessionTrackingModes(Collections.singleton(SessionTrackingMode.COOKIE));
		};
	}
}
