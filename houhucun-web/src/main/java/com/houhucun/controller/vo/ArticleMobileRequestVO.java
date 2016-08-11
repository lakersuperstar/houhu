package com.houhucun.controller.vo;

import java.io.Serializable;

public class ArticleMobileRequestVO implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	private int start;
	private int size;
	private String search;
	private String order;
	
	public int getStart() {
		return start;
	}
	
	public void setStart(int start) {
		this.start = start;
	}
	
	public int getSize() {
		return size;
	}
	
	public void setSize(int size) {
		this.size = size;
	}
	
	public String getSearch() {
		return search;
	}
	
	public void setSearch(String search) {
		this.search = search;
	}
	
	public String getOrder() {
		return order;
	}
	
	public void setOrder(String order) {
		this.order = order;
	}
}
