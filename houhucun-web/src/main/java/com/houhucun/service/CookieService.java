package com.houhucun.service;

import javax.servlet.http.HttpServletRequest;

public interface CookieService {

	/**
	 * 加密
	 * 
	 * @param content
	 * @return
	 */
	String encrypt(String content);

	/**
	 * 解密
	 * 
	 * @param content
	 * @return
	 */
	String decrypt(String content);

	/**
	 * 获取cookie中的user
	 */
	String getCookieUser(HttpServletRequest request);

	/**
	 * 获取用户角色
	 * 
	 * @param request
	 * @return
	 */
	String getUserRole(HttpServletRequest request);
}
