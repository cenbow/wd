package com.okwei.bean.vo;

import java.util.Date;

public class PlatformProductShopListVO {  //平台号的落地店列表VO
	private String shopName;  //微店名
	private String linkname;  //联系人姓名;
	private Long weiId;   //微店号
	private Short status;  //状态
	private String phone;  //联系人电话
	private String locationStr;  //地区
	private Long parentAgentWeiId;  //上级代理商微店号
	private String parentAgentName;  //上级代理商名称
	private Long merchantWeiId;  //上级供应商微店号
	private Date applyTime; //申请时间
	private Date cancelTime; //取消时间
	private String cancelReason;  //取消原因
	private Long rewardAmount;  //悬赏金额;
	private Short isPayReward;  //是否支付悬赏金额
	private Long developmentWeiId;  //发展人(认证员)的微店号
	private AreaShowVO areas;  //所在地区
	private String weiPicture;  //微店图片
	private String address;  //详细地址
	private Integer shopId;  //落地店的id
	
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
 
	public String getLocationStr() {
		return locationStr;
	}
	public void setLocationStr(String locationStr) {
		this.locationStr = locationStr;
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
	public Date getApplyTime() {
		return applyTime;
	}
	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}
	public String getCancelReason() {
		return cancelReason;
	}
	public void setCancelReason(String cancelReason) {
		this.cancelReason = cancelReason;
	}
	public Long getRewardAmount() {
		return rewardAmount;
	}
	public void setRewardAmount(Long rewardAmount) {
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
	public AreaShowVO getAreas() {
		return areas;
	}
	public void setAreas(AreaShowVO areas) {
		this.areas = areas;
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
	public Date getCancelTime() {
		return cancelTime;
	}
	public void setCancelTime(Date cancelTime) {
		this.cancelTime = cancelTime;
	}
	public Integer getShopId() {
		return shopId;
	}
	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}
	
}
