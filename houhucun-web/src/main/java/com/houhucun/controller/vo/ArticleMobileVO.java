package com.houhucun.controller.vo;

import java.io.Serializable;

public class ArticleMobileVO implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	private String link;
	private String headImg;
	private String title;
	private String desc;
	private long count;
	
	public String getLink() {
		return link;
	}
	
	public void setLink(String link) {
		this.link = link;
	}
	
	public String getHeadImg() {
		return headImg;
	}
	
	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDesc() {
		return desc;
	}
	
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public long getCount() {
		return count;
	}
	
	public void setCount(long count) {
		this.count = count;
	}
}
