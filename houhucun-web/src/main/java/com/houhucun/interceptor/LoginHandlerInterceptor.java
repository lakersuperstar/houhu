package com.houhucun.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component("loginHandlerInterceptor")
public class LoginHandlerInterceptor implements HandlerInterceptor {

	@Value("#{configProperties['loginUrl']}")
	private String loginUrl;

	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {

	}

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object arg2) throws Exception {
		// 1、请求到登录页面 放行
		if (request.getServletPath().endsWith(loginUrl)) {
			return true;
		}

		// 2、TODO 比如退出、首页等页面无需登录，即此处要放行 允许游客的请求

		// 3、如果用户已经登录 放行
		if (request.getSession().getAttribute("username") != null) {
			// 更好的实现方式的使用cookie
			return true;
		}

		// 4、非法请求 即这些请求需要登录后才能访问
		// 重定向到登录页面
		if (request.getServletPath() == null) {
			response.sendRedirect(request.getRequestURL() + loginUrl);
			return true;
		}
		int endIndex = request.getRequestURL().length()
				- request.getRequestURI().length();
		String url = request.getRequestURL().substring(0, endIndex);
		response.sendRedirect(url + loginUrl);
		return false;
	}

}
