package com.okwei.company.bean.vo;

import java.io.Serializable;


public class YunSupProductVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long shelvesId;//上架id
	private Long productId;//商品id
	private String defaultImg;//默认图片
	private String productTitle;//商品名称
	private long commentCount;//商品评论次数
	private Double defaultPrice;//默认价格
	private Double originalPrice;//原价
	
	
	public Double getDefaultPrice() {
		return defaultPrice;
	}
	public void setDefaultPrice(Double defaultPrice) {
		this.defaultPrice = defaultPrice;
	}
	public Long getShelvesId() {
		return shelvesId;
	}
	public void setShelvesId(Long shelvesId) {
		this.shelvesId = shelvesId;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public String getDefaultImg() {
		return defaultImg;
	}
	public void setDefaultImg(String defaultImg) {
		this.defaultImg = defaultImg;
	}
	public String getProductTitle() {
		return productTitle;
	}
	public void setProductTitle(String productTitle) {
		this.productTitle = productTitle;
	}
	public long getCommentCount() {
		return commentCount;
	}
	public void setCommentCount(long commentCount) {
		this.commentCount = commentCount;
	}
	public Double getOriginalPrice() {
		return originalPrice;
	}
	public void setOriginalPrice(Double originalPrice) {
		this.originalPrice = originalPrice;
	}
	
}
