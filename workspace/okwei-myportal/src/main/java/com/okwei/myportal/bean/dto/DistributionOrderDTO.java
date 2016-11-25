package com.okwei.myportal.bean.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class DistributionOrderDTO {

	/**
	 * 商品名称
	 */
	private String productTitle;
	/**
	 * 供应商名称
	 */
	private String companyName;
	/**
	 * 开始时间
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date beginDate;
	/**
	 * 结束时间
	 */
        @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date endDate;

	public String getProductTitle() {
		return productTitle;
	}

	public void setProductTitle(String productTitle) {
		this.productTitle = productTitle;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	
}
