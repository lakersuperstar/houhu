package com.houhucun.controller.vo;

import java.io.Serializable;

public class ImageCodeVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1077054596040237745L;

	/**
	 * 标题
	 */
	private String title;

	/**
	 * 描述
	 */
	private String desc;

	/**
	 * 是打印还是保存
	 */
	private String printtype;

	/**
	 * 模板类型
	 */
	private int inlineRadioOptions;

	/**
	 * 二维码地址
	 */
	private String ec;

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

	public String getPrinttype() {
		return printtype;
	}

	public void setPrinttype(String printtype) {
		this.printtype = printtype;
	}

	public int getInlineRadioOptions() {
		return inlineRadioOptions;
	}

	public void setInlineRadioOptions(int inlineRadioOptions) {
		this.inlineRadioOptions = inlineRadioOptions;
	}

	public String getEc() {
		return ec;
	}

	public void setEc(String ec) {
		this.ec = ec;
	}

}
