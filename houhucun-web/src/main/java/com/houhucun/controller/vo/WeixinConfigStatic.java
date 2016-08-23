package com.houhucun.controller.vo;

public class WeixinConfigStatic {
	
	
	private String token;
	private String ticket;
	private long expires;
	
	public WeixinConfigStatic() {
	}
	
	public WeixinConfigStatic(long expires) {
		super();
		this.expires = expires;
	}
	
	public String getToken() {
		return token;
	}
	
	public void setToken(String token) {
		this.token = token;
	}
	
	public String getTicket() {
		return ticket;
	}
	
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	
	public long getExpires() {
		return expires;
	}
	
	public void setExpires(long expires) {
		this.expires = expires;
	}
	
	@Override
	public String toString() {
		return "WeixinConfigStatic [token=" + token + ", ticket=" + ticket + ", expires=" + expires + "]";
	}
}
