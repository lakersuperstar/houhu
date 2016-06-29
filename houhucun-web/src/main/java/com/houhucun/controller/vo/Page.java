package com.houhucun.controller.vo;

public class Page {

	private int pageNo;

	private int nextPage;

	private int totalPage;

	private int size = 5;

	private int totalRecord;

	public String sizeToString(String page_method) {
		if (page_method == null || page_method == "") {
			page_method = "queryBut";
		}

		StringBuffer pageHtml = new StringBuffer(
				"<div class=\"page ml14px\"><ul><li class=\"lastPage\"><a href=\"javascript:"
						+ page_method + "(1)\">首页</a></li>");
		if (this.pageNo == 1) {
			pageHtml.append("<li class=\"lastPage\"></li>");
		} else {
			pageHtml.append("<li class=\"lastPage\"><a href=\"javascript:"
					+ page_method + "(" + (this.pageNo - 1)
					+ ")\">上一页</a></li>");
		}

		pageHtml.append("<li>");
		pageHtml.append("<a  class=\"curr\" href=\"javascript:" + page_method
				+ "(" + this.getPageNo() + ")\">" + this.getPageNo() + "</a>");
		pageHtml.append("</li>");
		if (this.pageNo == this.getTotalPage()) {
			pageHtml.append("<li class=\"lastPage\"></li>");

		} else {
			pageHtml.append("<li class=\"nextPage\"><a href=\"javascript:"
					+ page_method + "(" + (this.pageNo + 1)
					+ ")\">下一页</a></li>");
		}
		pageHtml.append("<li class=\"nextPage\"><a href=\"javascript:"
				+ page_method + "(" + this.getTotalPage() + ")\">末页</a>&nbsp;共"
				+ this.getTotalPage() + "页</li>");
		pageHtml.append("</ul></div>");
		return pageHtml.toString();
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getNextPage() {
		return nextPage;
	}

	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}

	public int getTotalPage() {
		if (this.totalRecord % size != 0) {
			if (this.totalRecord > size) {
				return this.totalRecord / size + 1;
			} else {
				return 1;
			}

		} else {
			return this.totalRecord / size;
		}
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

}
