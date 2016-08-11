package com.houhucun.domain;

import java.io.Serializable;

public class ArticleListQueryForMobile implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	private int start;
	private int size;
	private String title;
	private String sortParam;
	
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
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getSortParam() {
		return sortParam;
	}
	
	public void setSortParam(String sortParam) {
		this.sortParam = sortParam;
	}
}
