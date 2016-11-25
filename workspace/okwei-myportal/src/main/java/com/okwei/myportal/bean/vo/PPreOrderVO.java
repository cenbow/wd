package com.okwei.myportal.bean.vo;

public class PPreOrderVO {
	/**
	 * 商品ID
	 */
	private Long productId;
	/**
	 * 预定价格
	 */
	private Double preOrderPrice;
	/**
	 * 预定备注
	 */
	private String content;
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public Double getPreOrderPrice() {
		return preOrderPrice;
	}
	public void setPreOrderPrice(Double preOrderPrice) {
		this.preOrderPrice = preOrderPrice;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
