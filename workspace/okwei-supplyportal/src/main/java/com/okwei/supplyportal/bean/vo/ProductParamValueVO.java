package com.okwei.supplyportal.bean.vo;

import java.util.Date;

public class ProductParamValueVO {
	/**
	 * 值ID
	 */
	private Integer avid;
	/**
	 * keyID
	 */
	private Integer attributeId;
	/**
	 * 值
	 */
	private String value;
	/**
	 * 是否默认
	 */
	private Short isDefault;
	/**
	 * 状态
	 */
	private Short state;
	/**
	 * 参数模板值ID
	 */
	private Integer sysAVID;
	public Integer getAvid() {
		return avid;
	}
	public void setAvid(Integer avid) {
		this.avid = avid;
	}
	public Integer getAttributeId() {
		return attributeId;
	}
	public void setAttributeId(Integer attributeId) {
		this.attributeId = attributeId;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Short getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(Short isDefault) {
		this.isDefault = isDefault;
	}

	public Short getState() {
		return state;
	}
	public void setState(Short state) {
		this.state = state;
	}
	public Integer getSysAVID() {
		return sysAVID;
	}
	public void setSysAVID(Integer sysAVID) {
		this.sysAVID = sysAVID;
	}
	
	
}
