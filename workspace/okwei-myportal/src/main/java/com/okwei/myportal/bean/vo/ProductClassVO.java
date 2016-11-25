package com.okwei.myportal.bean.vo;

public class ProductClassVO {
	
	/**
	 * 商品分类ID
	 */
	private int classID;
	
	/**
	 * 分类名称
	 */
	private String clssName;
	
	private int oneClassID;
	
	public int getClassID() {
		return classID;
	}

	public void setClassID(int classID) {
		this.classID = classID;
	}

	public String getClssName() {
		return clssName;
	}

	public void setClssName(String clssName) {
		this.clssName = clssName;
	}

	public int getOneClassID() {
		return oneClassID;
	}

	public void setOneClassID(int oneClassID) {
		this.oneClassID = oneClassID;
	}

	public String getOneClassName() {
		return oneClassName;
	}

	public void setOneClassName(String oneClassName) {
		this.oneClassName = oneClassName;
	}

	public int getTwoClassID() {
		return twoClassID;
	}

	public void setTwoClassID(int twoClassID) {
		this.twoClassID = twoClassID;
	}

	public String getTwoClassName() {
		return twoClassName;
	}

	public void setTwoClassName(String twoClassName) {
		this.twoClassName = twoClassName;
	}

	private String oneClassName;
	
	private int twoClassID;
	
	private String twoClassName;
}
