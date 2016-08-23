package com.houhucun.test;

import com.houhucun.util.MD5Util;

public class TestMD5 {
	
	
	public static void main(String[] args) {
		
		String a = "1";
		String b = a;
		a = null;
		System.out.println(b);
		A a1 = new A();
		a1.setA("1");
		A a2 = a1;
		a1 = null;
		System.out.println(a2);
	}
}

class A {
	
	
	private String a;
	
	public String getA() {
		return a;
	}
	
	public void setA(String a) {
		this.a = a;
	}
	
	@Override
	public String toString() {
		return "A [a=" + a + "]";
	}
}