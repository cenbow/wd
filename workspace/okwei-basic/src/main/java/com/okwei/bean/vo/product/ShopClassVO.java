package com.okwei.bean.vo.product;

import java.util.List;

public class ShopClassVO {

	private Integer classId;
	private String className;
	private Integer productCount;
	private List<ShopClassVO> childClass;
	
	
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
	public List<ShopClassVO> getChildClass() {
		return childClass;
	}
	public void setChildClass(List<ShopClassVO> childClass) {
		this.childClass = childClass;
	}
	public Integer getProductCount() {
		return productCount;
	}
	public void setProductCount(Integer productCount) {
		this.productCount = productCount;
	}
}
