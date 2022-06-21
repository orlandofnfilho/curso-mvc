package com.github.rshtishi.recipe.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import com.github.rshtishi.recipe.interceptors.LoggerInterceptor;

@Configuration
public class InterceptorConfiguration implements WebMvcConfigurer {
	
	@Bean
	public LoggerInterceptor loggerInterceptor() {
		return new LoggerInterceptor();
	}
	
	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
		localeChangeInterceptor.setParamName("language");
		return localeChangeInterceptor;
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(loggerInterceptor());
		registry.addInterceptor(localeChangeInterceptor());
	}
	
}
