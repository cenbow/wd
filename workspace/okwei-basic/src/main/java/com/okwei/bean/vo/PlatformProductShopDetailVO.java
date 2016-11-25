package com.okwei.bean.vo;


public class PlatformProductShopDetailVO {
	private String shopName;  //微店名
	private String linkname;  //联系人姓名;
	private Long weiId;   //微店号
	private Short status;  //状态
	private String phone;  //联系人电话
	private Long parentAgentWeiId;  //上级代理商微店号
	private String parentAgentName;  //上级代理商名称
	private Long merchantWeiId;  //上级供应商微店号
	private String merchantShopName;  //上级供应商微店名
	private String cancelReason;  //取消原因
	private Double rewardAmount;  //悬赏金额;
	private Short isPayReward;  //是否支付悬赏金额
	private Long developmentWeiId;  //发展人(认证员)的微店号
	private AreaShowVO location;  //所在地区
	private String weiPicture;  //微店图片
	private String address;  //详细地址
	private Integer shopId;//落地店Id
	private String developmentName;//认证员名
	private String developmentPhone;//认证员手机号
	private String applyTime;//申请时间
	private Integer demandId;//招商需求Id
	private String demandName;//招商需求名称
	
	public Integer getShopId() {
		return shopId;
	}
	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}
	public String getDevelopmentName() {
		return developmentName;
	}
	public void setDevelopmentName(String developmentName) {
		this.developmentName = developmentName;
	}
	public String getDevelopmentPhone() {
		return developmentPhone;
	}
	public void setDevelopmentPhone(String developmentPhone) {
		this.developmentPhone = developmentPhone;
	}
	public String getApplyTime() {
		return applyTime;
	}
	public void setApplyTime(String applyTime) {
		this.applyTime = applyTime;
	}
	public Integer getDemandId() {
		return demandId;
	}
	public void setDemandId(Integer demandId) {
		this.demandId = demandId;
	}
	public String getDemandName() {
		return demandName;
	}
	public void setDemandName(String demandName) {
		this.demandName = demandName;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getLinkname() {
		return linkname;
	}
	public void setLinkname(String linkname) {
		this.linkname = linkname;
	}
	public Long getWeiId() {
		return weiId;
	}
	public void setWeiId(Long weiId) {
		this.weiId = weiId;
	}
	public Short getStatus() {
		return status;
	}
	public void setStatus(Short status) {
		this.status = status;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
 
	public Long getParentAgentWeiId() {
		return parentAgentWeiId;
	}
	public void setParentAgentWeiId(Long parentAgentWeiId) {
		this.parentAgentWeiId = parentAgentWeiId;
	}
	public String getParentAgentName() {
		return parentAgentName;
	}
	public void setParentAgentName(String parentAgentName) {
		this.parentAgentName = parentAgentName;
	}
	public Long getMerchantWeiId() {
		return merchantWeiId;
	}
	public void setMerchantWeiId(Long merchantWeiId) {
		this.merchantWeiId = merchantWeiId;
	}
	public String getCancelReason() {
		return cancelReason;
	}
	public void setCancelReason(String cancelReason) {
		this.cancelReason = cancelReason;
	}
	 
	public Double getRewardAmount() {
		return rewardAmount;
	}
	public void setRewardAmount(Double rewardAmount) {
		this.rewardAmount = rewardAmount;
	}
	public Short getIsPayReward() {
		return isPayReward;
	}
	public void setIsPayReward(Short isPayReward) {
		this.isPayReward = isPayReward;
	}
	public Long getDevelopmentWeiId() {
		return developmentWeiId;
	}
	public void setDevelopmentWeiId(Long developmentWeiId) {
		this.developmentWeiId = developmentWeiId;
	}
	 
	public String getWeiPicture() {
		return weiPicture;
	}
	public void setWeiPicture(String weiPicture) {
		this.weiPicture = weiPicture;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMerchantShopName() {
		return merchantShopName;
	}
	public void setMerchantShopName(String merchantShopName) {
		this.merchantShopName = merchantShopName;
	}
	public AreaShowVO getLocation() {
		return location;
	}
	public void setLocation(AreaShowVO location) {
		this.location = location;
	}
	
}
