package com.okwei.appinterface.bean.vo.xuanShangOrder;

/**
 * 悬赏
 * @author fuhao
 *
 */
public class RewardVO {
	
	public Long rewardId;//887,//悬赏id 
	public double rewardAmount;//1000,//悬赏金额
	public int  isPayReward;//0,//是否支付悬赏0-未支付1已支付
	public String payOrder;//"88899033",//支付订单号
	public Integer demandId;//177,//代理需求Id
	public Long developmentWeiId;//1983,//发展人微店号
	public String developmentWeiName;//"XXXX",//发展人微店名
	public String developmentShopImg;//"XXXX.jpg",//发展人微店图片
	public String payTime;//"2015-9-8 12:00",//悬赏支付时间
	public String auditTime;//"2015-9-8 12:00"//审核时间
	private String dateStr; //2015   年份
	private String url;//悬赏详情链接
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getDateStr() {
		return dateStr;
	}
	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}
	public Long getRewardId() {
		return rewardId;
	}
	public void setRewardId(Long rewardId) {
		this.rewardId = rewardId;
	}
	public double getRewardAmount() {
		return rewardAmount;
	}
	public void setRewardAmount(double rewardAmount) {
		this.rewardAmount = rewardAmount;
	}
	public int getIsPayReward() {
		return isPayReward;
	}
	public void setIsPayReward(int isPayReward) {
		this.isPayReward = isPayReward;
	}
 
	public String getPayOrder() {
		return payOrder;
	}
	public void setPayOrder(String payOrder) {
		this.payOrder = payOrder;
	}
	public Integer getDemandId() {
		return demandId;
	}
	public void setDemandId(Integer demandId) {
		this.demandId = demandId;
	}
	public Long getDevelopmentWeiId() {
		return developmentWeiId;
	}
	public void setDevelopmentWeiId(Long developmentWeiId) {
		this.developmentWeiId = developmentWeiId;
	}
	public String getDevelopmentWeiName() {
		return developmentWeiName;
	}
	public void setDevelopmentWeiName(String developmentWeiName) {
		this.developmentWeiName = developmentWeiName;
	}
	public String getDevelopmentShopImg() {
		return developmentShopImg;
	}
	public void setDevelopmentShopImg(String developmentShopImg) {
		this.developmentShopImg = developmentShopImg;
	}
	public String getPayTime() {
		return payTime;
	}
	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}
	public String getAuditTime() {
		return auditTime;
	}
	public void setAuditTime(String auditTime) {
		this.auditTime = auditTime;
	}
	
	 
}
