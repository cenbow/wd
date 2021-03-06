package com.okwei.supplyportal.bean.dto;

import java.io.Serializable;

public class ProductDTO implements Serializable {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;

	private String productName;

	private Long productCode;

	private Integer classID;

	private Long supplier;

	private Integer shopClassId;

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Long getProductCode() {
		return productCode;
	}

	public void setProductCode(Long productCode) {
		this.productCode = productCode;
	}

	public Integer getClassID() {
		return classID;
	}

	public void setClassID(Integer classID) {
		this.classID = classID;
	}

	public Long getSupplier() {
		return supplier;
	}

	public void setSupplier(Long supplier) {
		this.supplier = supplier;
	}

	public Integer getShopClassId() {
		return shopClassId;
	}

	public void setShopClassId(Integer shopClassId) {
		this.shopClassId = shopClassId;
	}

}
