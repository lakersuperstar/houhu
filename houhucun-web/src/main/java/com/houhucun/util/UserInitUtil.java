package com.houhucun.util;

public class UserInitUtil {

	private static String password = "123456";

	public static String getInitPassword() {
		return MD5Util.getMD5Code(password);
	}

	public static String getPassword(String pwd) {
		return MD5Util.getMD5Code(pwd);
	}

}
