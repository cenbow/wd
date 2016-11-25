package com.okwei.bean.dto.share;

public class SMainDataDTOs {
	/**
	 * 标题
	 */
	private String title="";
	/**
	 * 查询时间类型
	 */
	private int dateTimeType=3;
	/**
	 * 是否上首页
	 */
	private Short onHomePage=-1;
	
	 
	public Short getOnHomePage() {
		return onHomePage;
	}
	public void setOnHomePage(Short onHomePage) {
		this.onHomePage = onHomePage;
	}
	public int getDateTimeType() {
		return dateTimeType;
	}
	public void setDateTimeType(int dateTimeType) {
		this.dateTimeType = dateTimeType;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
 
	
	
}
