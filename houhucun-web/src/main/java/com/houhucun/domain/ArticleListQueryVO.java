package com.houhucun.domain;

public class ArticleListQueryVO extends PageQuery {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3854524255103801305L;

	private String name;

	private int type;

	private String title;

	private String createAccount;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCreateAccount() {
		return createAccount;
	}

	public void setCreateAccount(String createAccount) {
		this.createAccount = createAccount;
	}

}
