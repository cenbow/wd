package com.okwei.appinterface.bean.vo;

public class KcyjDomain {
	
	private Long productId ;
	private String productTitle ;
	private int stock ;
	
	public KcyjDomain(){
		
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductTitle() {
		return productTitle;
	}

	public void setProductTitle(String productTitle) {
		this.productTitle = productTitle;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

}
