package com.houhucun.service.impl;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.houhucun.service.CookieService;
import com.houhucun.util.CookieConstant;
import com.houhucun.util.SecurityRSAUtils;

@Service("cookieService")
public class CookieServiceImpl implements CookieService {

	@Value("#{configProperties['pri']}")
	private String pri;

	@Value("#{configProperties['pub']}")
	private String pub;

	@Override
	public String encrypt(String content) {
		return SecurityRSAUtils.encrypt(content, pub);
	}

	@Override
	public String decrypt(String content) {
		return SecurityRSAUtils.decrypt(content, pri);
	}

	@Override
	public String getCookieUser(HttpServletRequest request) {
		Cookie[] cks = request.getCookies();
		if (cks != null && cks.length > 0) {
			for (Cookie ck : cks) {
				if (ck.getName().equalsIgnoreCase(CookieConstant.userName)) {
					return ck.getValue();
				}
			}
		}
		return null;
	}

	@Override
	public String getUserRole(HttpServletRequest request) {
		Cookie[] cks = request.getCookies();
		if (cks != null && cks.length > 0) {
			for (Cookie ck : cks) {
				if (ck.getName().equalsIgnoreCase(CookieConstant.login)) {
					String loginUser = this.decrypt(ck.getValue());
					return loginUser.substring(loginUser.length() - 1,
							loginUser.length());
				}
			}
		}
		return null;

	}
}
