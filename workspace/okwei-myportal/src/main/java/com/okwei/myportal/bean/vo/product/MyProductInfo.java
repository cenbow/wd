package com.okwei.myportal.bean.vo.product;

import com.okwei.bean.domain.PProducts;

public class MyProductInfo extends PProducts {

	private static final long serialVersionUID = 1L;
	/**
	 * systemClass /shopClassId 分类名称
	 */
	private String className;
	private String supplierName;
	private Integer brandId;
	private String brandName;
	
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public Integer getBrandId() {
		return brandId;
	}
	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	
	
}
