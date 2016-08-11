package com.houhucun.controller.vo;

import java.io.Serializable;
import java.util.List;

public class ArticleMobileListVO implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	private int code;
	private String msg;
	private boolean completed;
	private List<ArticleMobileVO> list;
	
	public int getCode() {
		return code;
	}
	
	public void setCode(int code) {
		this.code = code;
	}
	
	public String getMsg() {
		return msg;
	}
	
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public boolean isCompleted() {
		return completed;
	}
	
	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
	
	public List<ArticleMobileVO> getList() {
		return list;
	}
	
	public void setList(List<ArticleMobileVO> list) {
		this.list = list;
	}
}
