package com.houhucun.controller.vo;

import java.io.Serializable;

public class WeixinTicket implements Serializable {
	
	
	private static final long serialVersionUID = 3389948596993720353L;
	private int errcode;
	private String errmsg;
	private String ticket;
	private int expires_in;
	
	public int getErrcode() {
		return errcode;
	}
	
	public void setErrcode(int errcode) {
		this.errcode = errcode;
	}
	
	public String getErrmsg() {
		return errmsg;
	}
	
	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}
	
	public String getTicket() {
		return ticket;
	}
	
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	
	public int getExpires_in() {
		return expires_in;
	}
	
	public void setExpires_in(int expires_in) {
		this.expires_in = expires_in;
	}
}
