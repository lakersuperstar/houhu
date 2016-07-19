package com.houhucun.controller.vo;

import java.io.Serializable;

public class LoginResult implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3164672949088298275L;

	private String success;
	
	private int flag;

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}
	
}
