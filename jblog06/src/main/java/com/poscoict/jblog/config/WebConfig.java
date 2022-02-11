package com.poscoict.jblog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.poscoict.jblog.security.LoginInterceptor;
import com.poscoict.jblog.security.LogoutInterceptor;

@SpringBootConfiguration
@PropertySource("classpath:com/poscoict/jblog/config/WebConfig.properties")
public class WebConfig implements WebMvcConfigurer {

	@Autowired
	private Environment env;

	@Bean
	public HandlerInterceptor loginInterceptor() {
		return new LoginInterceptor();
	}

	@Bean
	public HandlerInterceptor logoutInterceptor() {
		return new LogoutInterceptor();
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(loginInterceptor())
		.addPathPatterns("/user/auth");
		
		registry.addInterceptor(logoutInterceptor())
		.addPathPatterns("/user/logout");
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry
		.addResourceHandler(env.getProperty("fileupload.resourceMapping") )
		.addResourceLocations("file:"+env.getProperty("fileupload.uploadLocation"));
		registry
		.addResourceHandler("/assets/**")
		.addResourceLocations("classpath:assets/");
	}
}
