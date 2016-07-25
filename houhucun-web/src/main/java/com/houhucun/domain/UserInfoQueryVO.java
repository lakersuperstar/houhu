package com.houhucun.domain;

public class UserInfoQueryVO extends PageQuery {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5441883295194383343L;

	/**
	 * 用户名称
	 */
	private String userName;

	/**
	 * 用户手机号
	 */
	private String mobile;

	/**
	 * 用户联系地址
	 */
	private String address;

	/**
	 * 用户登陆账号
	 */
	private String userAccount;

	
	private String createAccount;

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public String getCreateAccount() {
		return createAccount;
	}

	public void setCreateAccount(String createAccount) {
		this.createAccount = createAccount;
	}

	
}
