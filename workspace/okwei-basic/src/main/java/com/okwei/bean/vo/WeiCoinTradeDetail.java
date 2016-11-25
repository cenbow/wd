package com.okwei.bean.vo;

public class WeiCoinTradeDetail implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1600771624455819140L;
	private String img;// "img":"xxx.jpg",//微金币图片
    private  Double amount;//"amount":10,//微金币金额
    private  String name;//     "name":"微金币名称",
    private  String date;//     "date":"2015-9-10",//时间
    private  String detailUrl;//     "detailUrl":"微金币详情URL"
    private  String supplyOrderId;//     "supplyOrderId":"201601270001",  //微金币消费订单号，用于支出
    private	 Long pageId;//     "pageId":1000,  //分享页Id，用于收入
    private  Long shareOne;//     "shareOne":11983  //分享人微店号，用于收入
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
	public Long getPageId() {
		return pageId;
	}
	public void setPageId(Long pageId) {
		this.pageId = pageId;
	}
	public Long getShareOne() {
		return shareOne;
	}
	public void setShareOne(Long shareOne) {
		this.shareOne = shareOne;
	}
    
    

}
