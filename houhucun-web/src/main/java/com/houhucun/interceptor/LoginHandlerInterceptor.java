package com.houhucun.interceptor;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.houhucun.service.CookieService;
import com.houhucun.util.CookieConstant;

@Component("loginHandlerInterceptor")
public class LoginHandlerInterceptor implements HandlerInterceptor {

	@Value("#{configProperties['loginUrl']}")
	private String loginUrl;

	@Value("#{configProperties['loginController']}")
	private String loginController;

	@Resource(name = "cookieService")
	private CookieService cookieService;

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {

	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		// 1、请求到登录页面 放行
		if (request.getServletPath().endsWith(loginUrl)) {
			return true;
		}

		// 2、TODO 比如退出、首页等页面无需登录，即此处要放行 允许游客的请求
		if (request.getServletPath().endsWith(loginController)) {
			return true;
		}
		// 3、如果用户已经登录 放行
		Cookie[] cks = request.getCookies();
		if (cks != null && cks.length > 0) {
			String user = "";
			String login = "";
			for (Cookie ck : cks) {
				if (ck.getName().equalsIgnoreCase(CookieConstant.userName)) {
					user = ck.getValue();
				}
				if (ck.getName().equalsIgnoreCase(CookieConstant.login)) {
					login = ck.getValue();
				}
			}
			if (StringUtils.isNotBlank(user) && StringUtils.isNotBlank(login)) {
				if (user.equals(cookieService.decrypt(login))) {
					request.setAttribute("user", CookieConstant.userName);
					return true;
				}
			}
		}

		// 4、非法请求 即这些请求需要登录后才能访问
		// 重定向到登录页面
		if (request.getServletPath() == null) {
			response.sendRedirect(request.getRequestURL() + loginUrl);
			return true;
		}
		int endIndex = request.getRequestURL().length() - request.getRequestURI().length();
		String url = request.getRequestURL().substring(0, endIndex);
		response.sendRedirect(url + loginUrl);
		return false;
	}

}
