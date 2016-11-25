package com.okwei.bean.vo;

import java.util.ArrayList;
import java.util.List;

public class ShopClassVO {
	/**
	 * 店铺分类ID
	 */
	private Integer classId;
	/**
	 * 店铺分类名称
	 */
	private String className;
	/**
	 * 店铺分类名称
	 */
	private long weiid;
	/**
	 * 状态
	 */
	private Short state;
	/**
	 * 排序
	 */
	private Short sort;
	/**
	 * 类型
	 */
	private Short type;

	// 只有两级:1/2
	private Short level;

	private Integer paretId;

	List<ShopClassVO> childClass = new ArrayList<ShopClassVO>();

	// 该店铺分类下的产品数量
	private long productCount;

	public Integer getClassId() {
		return classId;
	}

	public void setClassId(Integer classId) {
		this.classId = classId;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public long getWeiid() {
		return weiid;
	}

	public void setWeiid(long weiid) {
		this.weiid = weiid;
	}

	public Short getState() {
		return state;
	}

	public void setState(Short state) {
		this.state = state;
	}

	public Short getSort() {
		return sort;
	}

	public void setSort(Short sort) {
		this.sort = sort;
	}

	public Short getType() {
		return type;
	}

	public void setType(Short type) {
		this.type = type;
	}

	public Short getLevel() {
		return level;
	}

	public void setLevel(Short level) {
		this.level = level;
	}

	public Integer getParetId() {
		return paretId;
	}

	public void setParetId(Integer paretId) {
		this.paretId = paretId;
	}

	public List<ShopClassVO> getChildClass() {
		return childClass;
	}

	public void setChildClass(List<ShopClassVO> childClass) {
		this.childClass = childClass;
	}

	public long getProductCount() {
		return productCount;
	}

	public void setProductCount(long productCount) {
		this.productCount = productCount;
	}

}
