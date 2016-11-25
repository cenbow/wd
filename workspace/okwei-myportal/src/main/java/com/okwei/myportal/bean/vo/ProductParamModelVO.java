package com.okwei.myportal.bean.vo;

import java.util.List;

public class ProductParamModelVO {
	/**
	 * 模板ID
	 */
	private Integer mid;
	/**
	 * 供应商微店号
	 */
	private Long supplierWeiId;
	/**
	 * 系统分类ID
	 */
	private Integer classId;
	/**
	 * 模板名称
	 */
	private String mname;
	/**
	 * 状态
	 */
	private Short state;
	
	/**
	 * 模板自定义Key列表
	 */
	private List<ProductParamKeyVO> keyList;
	
	public Integer getMid() {
		return mid;
	}

	public void setMid(Integer mid) {
		this.mid = mid;
	}

	public Long getSupplierWeiId() {
		return supplierWeiId;
	}

	public void setSupplierWeiId(Long supplierWeiId) {
		this.supplierWeiId = supplierWeiId;
	}

	public Integer getClassId() {
		return classId;
	}

	public void setClassId(Integer classId) {
		this.classId = classId;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public Short getState() {
		return state;
	}

	public void setState(Short state) {
		this.state = state;
	}

	public List<ProductParamKeyVO> getKeyList() {
		return keyList;
	}

	public void setKeyList(List<ProductParamKeyVO> keyList) {
		this.keyList = keyList;
	}
	
	
}
