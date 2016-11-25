package com.okwei.appinterface.bean.vo;

public class CprxDomain {

	private Long productId ;
	private String productTitle ;
	private int saleTotal ;
	
	public CprxDomain(){
		
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

	public int getSaleTotal() {
		return saleTotal;
	}

	public void setSaleTotal(int saleTotal) {
		this.saleTotal = saleTotal;
	}
	
	
}
