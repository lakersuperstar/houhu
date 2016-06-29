package com.houhucun.domain;

public class ProductQueryVO extends PageQuery {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3854524255103801305L;

	private String name;

	private int type;

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

}
