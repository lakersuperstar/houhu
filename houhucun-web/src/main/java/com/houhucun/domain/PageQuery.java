package com.houhucun.domain;

import java.io.Serializable;

public class PageQuery implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3454280337235767885L;

	/** 当前页码 */
	private int pageNo;

	/** 每页行数 */
	private int pageSize;

	/** 开始行数 */
	private int startrownum;

	/** 结束行数 */
	private int endrownum;

	/**
	 * 查询js方法名
	 */
	private String functionName;

	public int getPageNo() {
		if (pageNo == 0) {
			return 1;
		}
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getStartrownum() {
		return startrownum;
	}

	public void setStartrownum(int startrownum) {
		this.startrownum = startrownum;
	}

	public int getEndrownum() {
		return endrownum;
	}

	public void setEndrownum(int endrownum) {
		this.endrownum = endrownum;
	}

	public String getFunctionName() {
		return functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	public void setNowPage() {
		this.setStartrownum((this.getPageNo() - 1) * this.getPageSize());
		this.setEndrownum(this.getPageSize());
	}

	@Override
	public String toString() {
		return "PageQuery [pageNo=" + pageNo + ", pageSize=" + pageSize
				+ ", startrownum=" + startrownum + ", endrownum=" + endrownum
				+ "]";
	}

}
