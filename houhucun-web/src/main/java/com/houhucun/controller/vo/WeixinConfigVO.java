package com.houhucun.controller.vo;

import java.io.Serializable;

public class WeixinConfigVO implements Serializable {
	
	
	private static final long serialVersionUID = -5968946849927433553L;
	private String appId;
	private long timestamp;
	private String nonceStr;
	private String signature;
	private String title;
	private String desc;
	private String link;
	private String imgUrl;
	
	public String getAppId() {
		return appId;
	}
	
	public void setAppId(String appId) {
		this.appId = appId;
	}
	
	public long getTimestamp() {
		return timestamp;
	}
	
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	
	public String getNonceStr() {
		return nonceStr;
	}
	
	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}
	
	public String getSignature() {
		return signature;
	}
	
	public void setSignature(String signature) {
		this.signature = signature;
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
	
	public String getLink() {
		return link;
	}
	
	public void setLink(String link) {
		this.link = link;
	}
	
	public String getImgUrl() {
		return imgUrl;
	}
	
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
}
