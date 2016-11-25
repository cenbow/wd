package com.okwei.supplyportal.bean.vo;

import java.util.List;

public class ProductParamKeyVO {
	
	private Integer attributeId;
	private Integer sysAttributeID;
	private Integer mid;
	private Integer classid;
	private String attributeName;
	private Short sort;
	private Short showtype;
	private Short ischooseparam;
	private String valueName;
	private Short isMust;
	
	/**
	 * 模板value列表
	 */
	private List<ProductParamValueVO> valueList;
	
	
	public Integer getAttributeId() {
		return attributeId;
	}
	public void setAttributeId(Integer attributeId) {
		this.attributeId = attributeId;
	}
	public Integer getMid() {
		return mid;
	}
	public void setMid(Integer mid) {
		this.mid = mid;
	}
	public Integer getClassid() {
		return classid;
	}
	public void setClassid(Integer classid) {
		this.classid = classid;
	}
	public String getAttributeName() {
		return attributeName;
	}
	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}
	public Short getSort() {
		return sort;
	}
	public void setSort(Short sort) {
		this.sort = sort;
	}
	public Short getShowtype() {
		return showtype;
	}
	public void setShowtype(Short showtype) {
		this.showtype = showtype;
	}
	public Short getIschooseparam() {
		return ischooseparam;
	}
	public void setIschooseparam(Short ischooseparam) {
		this.ischooseparam = ischooseparam;
	}
	public List<ProductParamValueVO> getValueList() {
		return valueList;
	}
	public void setValueList(List<ProductParamValueVO> valueList) {
		this.valueList = valueList;
	}
	public String getValueName() {
		return valueName;
	}
	public void setValueName(String valueName) {
		this.valueName = valueName;
	}
	public Integer getSysAttributeID() {
		return sysAttributeID;
	}
	public void setSysAttributeID(Integer sysAttributeID) {
		this.sysAttributeID = sysAttributeID;
	}
	public Short getIsMust() {
		return isMust;
	}
	public void setIsMust(Short isMust) {
		this.isMust = isMust;
	}
	
}
