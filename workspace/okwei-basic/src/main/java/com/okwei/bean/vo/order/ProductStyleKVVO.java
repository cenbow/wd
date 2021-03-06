package com.okwei.bean.vo.order;

public class ProductStyleKVVO {
	private Long kvid;
	/**
	 * 商品ID
	 */
	private Long productId;
	/**
	 * 款式ID
	 */
	private Long stylesId;
	/**
	 * 款式属性ID
	 */
	private Long attributeId;
	/**
	 * 款式属性名称
	 */
	private String attrbuteName;
	/**
	 * 款式属性值ID
	 */
	private Long keyId;
	/**
	 * 属性值名称
	 */
	private String valueName;
	
	/**
	 * 排序
	 */
	private Short sort;
	
	public Long getKvid() {
		return kvid;
	}
	public void setKvid(Long kvid) {
		this.kvid = kvid;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public Long getStylesId() {
		return stylesId;
	}
	public void setStylesId(Long stylesId) {
		this.stylesId = stylesId;
	}
	public Long getAttributeId() {
		return attributeId;
	}
	public void setAttributeId(Long attributeId) {
		this.attributeId = attributeId;
	}
	public Long getKeyId() {
		return keyId;
	}
	public void setKeyId(Long keyId) {
		this.keyId = keyId;
	}
	public String getAttrbuteName() {
		return attrbuteName;
	}
	public void setAttrbuteName(String attrbuteName) {
		this.attrbuteName = attrbuteName;
	}
	public String getValueName() {
		return valueName;
	}
	public void setValueName(String valueName) {
		this.valueName = valueName;
	}
	public Short getSort() {
		return sort;
	}
	public void setSort(Short sort) {
		this.sort = sort;
	}
	
}
