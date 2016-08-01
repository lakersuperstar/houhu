package com.houhucun.controller;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.houhucun.controller.vo.LoginResult;
import com.houhucun.controller.vo.LoginVO;
import com.houhucun.domain.UserInfo;
import com.houhucun.service.CookieService;
import com.houhucun.service.UserInfoService;
import com.houhucun.util.CookieConstant;
import com.houhucun.util.MD5Util;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	
	@Resource(name = "userInfoService")
	private UserInfoService userInfoService;
	@Resource(name = "cookieService")
	private CookieService cookieService;
	
	@RequestMapping("/submit")
	@ResponseBody
	public Object login(HttpServletResponse response, HttpServletRequest request, LoginVO loginvo) {
		try {
			UserInfo userInfo = new UserInfo();
			userInfo.setUserAccount(loginvo.getAccount());
			userInfo.setPassword(MD5Util.getMD5Code(loginvo.getPassword()));
			UserInfo userInfoNew = userInfoService.checkUser(userInfo);
			if (userInfoNew == null) {
				LoginResult loginresult = new LoginResult();
				loginresult.setFlag(0);
				loginresult.setMsg("不存在此用户!");
				return loginresult;
			}
			boolean flag = userInfo.getPassword().equals(userInfoNew.getPassword());
			LoginResult loginresult = new LoginResult();
			if (flag) {
				Cookie cookie = new Cookie(CookieConstant.userName, loginvo.getAccount());
				cookie.setMaxAge(3600 * 24 * 7);
				cookie.setPath("/");
				Cookie cookie2 = new Cookie(CookieConstant.login, cookieService.encrypt(loginvo.getAccount() + userInfoNew.getRole()));
				cookie2.setMaxAge(3600 * 24 * 7);
				cookie2.setPath("/");
				response.addCookie(cookie);
				response.addCookie(cookie2);
				loginresult.setFlag(1);
				loginresult.setSuccess("/");
			} else {
				loginresult.setFlag(0);
				loginresult.setMsg("用户账号名或密码错误!");
			}
			return loginresult;
		} catch (Exception e) {
			LoginResult loginresult = new LoginResult();
			loginresult.setFlag(0);
			loginresult.setMsg("系统繁忙!");
			return loginresult;
		}
	}
}
