package com.okwei.myportal.bean.vo;

import java.util.List;
import java.util.Map;

import com.sdicons.json.validator.impl.predicates.Int;

public class BrandClassParentVO {
	/**
	 * 父级分类名称
	 */
	private String parentCName;
	/**
	 * 父级分类ID
	 */
	private int parentCID;
	/**
	 * 子级分类列表
	 */
	private List<BrandClassChildVO> classChildVOs;
	public String getParentCName() {
		return parentCName;
	}
	public void setParentCName(String parentCName) {
		this.parentCName = parentCName;
	}
	public int getParentCID() {
		return parentCID;
	}
	public void setParentCID(int parentCID) {
		this.parentCID = parentCID;
	}
	public List<BrandClassChildVO> getClassChildVOs() {
		return classChildVOs;
	}
	public void setClassChildVOs(List<BrandClassChildVO> classChildVOs) {
		this.classChildVOs = classChildVOs;
	}
	
	
}

