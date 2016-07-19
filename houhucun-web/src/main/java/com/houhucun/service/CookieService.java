package com.houhucun.service;

public interface CookieService {

	/**
	 * 加密
	 * @param content
	 * @return
	 */
	String encrypt(String content);

	/**
	 * 解密
	 * @param content
	 * @return
	 */
	String decrypt(String content);
}
