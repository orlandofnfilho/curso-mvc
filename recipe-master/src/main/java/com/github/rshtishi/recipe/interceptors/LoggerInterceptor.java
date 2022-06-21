package com.github.rshtishi.recipe.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import ch.qos.logback.classic.Logger;

public class LoggerInterceptor implements HandlerInterceptor {

	private static Logger logger = (Logger) LoggerFactory.getLogger(LoggerInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		logger.info("[preHandle][" + request.getClass() + "][" + request.getMethod() + "]:" + request.getRequestURI());
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		logger.info("[postHandle]" + request.getClass() + "][" + request.getMethod() + "]:" + request.getRequestURI());
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		logger.info("[afterCompletion]" + request.getClass() + "][" + request.getMethod() + "]:" + request.getRequestURI());
	}

}
