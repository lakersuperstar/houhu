package com.houhucun.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.houhucun.service.CookieService;
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

}
