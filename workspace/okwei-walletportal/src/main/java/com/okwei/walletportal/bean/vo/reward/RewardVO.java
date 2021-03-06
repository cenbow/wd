package com.okwei.walletportal.bean.vo.reward;

import java.util.Date;

public class RewardVO {

	/**
	 * 悬赏Id
	 */
	private Integer demandId;
	
	/**
	 * Id
	 */
	private Integer channelId;
	/**
	 * 认证员Id
	 */
	private Long verifier;
	/**
	 * 认证员名称
	 */
	private String verifierName;
	/**
	 * 认证员图片
	 */
	private String verifierImg;
	/**
	 * 标示身份 1代理商 2落地店 SupplyChannelTypeEnum
	 */
	private Short channelType;

	/**
	 * 公司名称
	 */
	private String companyName;

	/**
	 * 悬赏金额
	 */
	private String reward;
	/**
	 * 是否支付0未支付 1 已支付
	 */
	private Short payedReward;

	/**
	 * 审核通过时间
	 */
	private Date createTime;

	
	public Integer getChannelId() {
		return channelId;
	}

	public void setChannelId(Integer channelId) {
		this.channelId = channelId;
	}

	public Integer getDemandId() {
		return demandId;
	}

	public void setDemandId(Integer demandId) {
		this.demandId = demandId;
	}

	public Long getVerifier() {
		return verifier;
	}

	public void setVerifier(Long verifier) {
		this.verifier = verifier;
	}

	public String getVerifierName() {
		return verifierName;
	}

	public void setVerifierName(String verifierName) {
		this.verifierName = verifierName;
	}

	public String getVerifierImg() {
		return verifierImg;
	}

	public void setVerifierImg(String verifierImg) {
		this.verifierImg = verifierImg;
	}

	public Short getChannelType() {
		return channelType;
	}

	public void setChannelType(Short channelType) {
		this.channelType = channelType;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	} 
	
	public String getReward() {
		return reward;
	}

	public void setReward(String reward) {
		this.reward = reward;
	}

	public Short getPayedReward() {
		return payedReward;
	}

	public void setPayedReward(Short payedReward) {
		this.payedReward = payedReward;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	

}
