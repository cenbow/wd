package com.okwei.supplyportal.bean.vo;

import java.util.List;

public class ProductStylesVO {
	
	/**
	 * 款式ID
	 */
	private Long stylesId;
	/**
	 * 商品ID
	 */
	private Long productId;
	/**
	 * 价格
	 */
	private Double price;
	/**
	 * 佣金
	 */
	private Double conmision;
	/**
	 * 数量
	 */
	private Integer count;
	/**
	 * 款式图片
	 */
	private String defaultImg;
	/**
	 * 商家编码
	 */
	private String bussinessNo;
	
	/**
	 * 款式属性键值对列表
	 */
	private List<ProductStyleKVVO> styleKVList;
	
	public Long getStylesId() {
		return stylesId;
	}
	public void setStylesId(Long stylesId) {
		this.stylesId = stylesId;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getConmision() {
		return conmision;
	}
	public void setConmision(Double conmision) {
		this.conmision = conmision;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public String getDefaultImg() {
		return defaultImg;
	}
	public void setDefaultImg(String defaultImg) {
		this.defaultImg = defaultImg;
	}
	public String getBussinessNo() {
		return bussinessNo;
	}
	public void setBussinessNo(String bussinessNo) {
		this.bussinessNo = bussinessNo;
	}
	
	public List<ProductStyleKVVO> getStyleKVList() {
		return styleKVList;
	}
	public void setStyleKVList(List<ProductStyleKVVO> styleKVList) {
		this.styleKVList = styleKVList;
	}
}
