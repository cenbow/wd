package com.okwei.bean.vo.order;

public class TicketInfoVO {
	private String img;//购物券图片
	private Double amount=0.0;//购物券金额
	private String name;//购物券名称
	private String date;//时间
	private String detailUrl;//购物券详情URL，可为空
	private String supplyOrderId;//购物券关联订单号
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getDetailUrl() {
		return detailUrl;
	}
	public void setDetailUrl(String detailUrl) {
		this.detailUrl = detailUrl;
	}
	public String getSupplyOrderId() {
		return supplyOrderId;
	}
	public void setSupplyOrderId(String supplyOrderId) {
		this.supplyOrderId = supplyOrderId;
	}
	
	
}
