package com.okwei.bean.dto.share;

public class SMainDataDTO {
	/**
	 * 标题
	 */
	private String title="";
	/**
	 * 是否上首页
	 */
	private Short onHomePage=-1;
	/**
	 * 状态
	 */
	private Short status=-1;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Short getOnHomePage() {
		return onHomePage;
	}
	public void setOnHomePage(Short onHomePage) {
		this.onHomePage = onHomePage;
	}
	public Short getStatus() {
		return status;
	}
	public void setStatus(Short status) {
		this.status = status;
	}
	
	
	
}
