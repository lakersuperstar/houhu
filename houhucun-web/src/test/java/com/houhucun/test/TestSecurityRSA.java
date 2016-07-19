package com.houhucun.test;

import java.io.UnsupportedEncodingException;

import com.houhucun.util.KeyGenerate;
import com.houhucun.util.SecretKey;
import com.houhucun.util.SecurityRSAUtils;

public class TestSecurityRSA {

	public static void main(String[] args) throws Exception {

		// for (int i = 0; i < 10; i++) {
		SecretKey key = KeyGenerate.getSecretKey("houhucun-后胡村", 512);

		String pub = key.getPubSecretKey();
		String pri = key.getPriSecretKey();

		System.out.println(pub);
		System.out.println(pri);
		String content = SecurityRSAUtils.encrypt("aaaaa", pub);
		System.out.println(content);

		String de = SecurityRSAUtils.decrypt(content, pri);
		System.out.println(de);
		// }

	}

}
