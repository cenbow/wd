package com.okwei.supplyportal.bean.vo;

import javax.persistence.Column;

public class ProductParamVO {

	private Long kvid;
	
	private Long productId;
	/**
	 * 属性名称
	 */
	private String paramName;
	/**
	 * 属性值
	 */
	private String paramValue;	
	/**
	 * 系统属性值ID
	 */
	private Integer sysAVID;
	/**
	 * 系统属性keyID
	 */
	private Integer sysAttributeID;
	
	private Integer aVID;

	private Integer attributeID;
	
	public Integer getaVID() {
		return aVID;
	}
	public void setaVID(Integer aVID) {
		this.aVID = aVID;
	}
	public Integer getAttributeID() {
		return attributeID;
	}
	public void setAttributeID(Integer attributeID) {
		this.attributeID = attributeID;
	}
	public Long getKvid() {
		return kvid;
	}
	public void setKvid(Long kvid) {
		this.kvid = kvid;
	}
	public Integer getSysAVID() {
		return sysAVID;
	}
	public void setSysAVID(Integer sysAVID) {
		this.sysAVID = sysAVID;
	}
	public Integer getSysAttributeID() {
		return sysAttributeID;
	}
	public void setSysAttributeID(Integer sysAttributeID) {
		this.sysAttributeID = sysAttributeID;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public String getParamName() {
		return paramName;
	}
	public void setParamName(String paramName) {
		this.paramName = paramName;
	}
	public String getParamValue() {
		return paramValue;
	}
	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}
	
}
