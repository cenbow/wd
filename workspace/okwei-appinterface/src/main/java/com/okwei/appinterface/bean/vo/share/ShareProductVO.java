package com.okwei.appinterface.bean.vo.share;

public class ShareProductVO {
	private Long productId;  //产品Id
	private String productTitle;  //产品标题，新增时可为空
	private String productImg;//xxxxx/xxx.png",  //产品图片URL，完整地址，新增时可为空
	private Double price;  //产品现价，新增时可为空
	private Double displayPrice; //产品原价，可为空
	private Long providerWeiId;  //产品供应商微店号
	private Long prodcutSeller; //产品上架人微店号，可为空
	
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
	public String getProductImg() {
		return productImg;
	}
	public void setProductImg(String productImg) {
		this.productImg = productImg;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getDisplayPrice() {
		return displayPrice;
	}
	public void setDisplayPrice(Double displayPrice) {
		this.displayPrice = displayPrice;
	}
	public Long getProviderWeiId() {
		return providerWeiId;
	}
	public void setProviderWeiId(Long providerWeiId) {
		this.providerWeiId = providerWeiId;
	}
	public Long getProdcutSeller() {
		return prodcutSeller;
	}
	public void setProdcutSeller(Long prodcutSeller) {
		this.prodcutSeller = prodcutSeller;
	}
	
	
}
