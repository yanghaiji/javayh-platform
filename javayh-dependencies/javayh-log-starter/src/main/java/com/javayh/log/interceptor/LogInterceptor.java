package com.javayh.log.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 创建拦截器
 * </p>
 *
 * @version 1.0.0
 * @author Dylan-haiji
 * @since 2020/3/2
 */
public class LogInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler) {
		return true;
	}

}