package com.okwei.supplyportal.bean.vo;

import java.util.List;

public class ProductSellKeyVO {
	/**
	 * 销售属性KeyID
	 */
	private Long attributeId;
	/**
	 * 商品ID
	 */
	private Long productId;
	/**
	 * 销售属性KeyName
	 */
	private String attributeName;
	/**
	 * 排序
	 */
	private Short sort;
	
	/**
	 * 款式Value 列表
	 */
	private List<ProductSellValueVO> sellValueList;
	
	public Long getAttributeId() {
		return attributeId;
	}
	public void setAttributeId(Long attributeId) {
		this.attributeId = attributeId;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
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
	public List<ProductSellValueVO> getSellValueList() {
		return sellValueList;
	}
	public void setSellValueList(List<ProductSellValueVO> sellValueList) {
		this.sellValueList = sellValueList;
	}
	
}
