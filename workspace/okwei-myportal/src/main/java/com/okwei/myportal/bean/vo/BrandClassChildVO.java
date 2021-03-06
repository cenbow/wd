package com.okwei.myportal.bean.vo;

public class BrandClassChildVO {
	/**
	 * 品牌ID
	 */
	private int brandID;
	/**
	 * 分类ID
	 */
	private int classID;
	/**
	 * 分类名称
	 */
	private String className;
	/**
	 * 父级ID
	 */
	private int parentID;
	/**
	 * 父级名称
	 */
	private String parentName;
	/**
	 * 是否选中
	 */
	private boolean checked;
	
	
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public int getBrandID() {
		return brandID;
	}
	public void setBrandID(int brandID) {
		this.brandID = brandID;
	}
	public int getClassID() {
		return classID;
	}
	public void setClassID(int classID) {
		this.classID = classID;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public int getParentID() {
		return parentID;
	}
	public void setParentID(int parentID) {
		this.parentID = parentID;
	}
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	
	
	
}
