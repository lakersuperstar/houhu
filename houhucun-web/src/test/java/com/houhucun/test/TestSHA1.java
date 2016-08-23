package com.houhucun.test;

import java.io.IOException;

import com.houhucun.util.WeixinUtil;

public class TestSHA1 {
	
	
	public static void main(String[] args) throws IOException {
		long s = 1471930703;
		long a = System.currentTimeMillis() / 1000;
		System.out.println(a);
		System.out.println(s);
		String aa = WeixinUtil.getSignature("kgt8ON7yVITDhtdwci0qeUIx8L1eB3q2rcnbvMk5kAdt4NPKtXah1PbjnOOF44ZZxCDUvu1aGZ0l_ss94APOmQ", s + "", "asdfasdf", "http://houhucun.com/show/article/99");
		System.out.println(aa);
	}
}
