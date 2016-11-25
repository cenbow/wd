package com.okwei.bean.dto;

public class MyAgentOrProductShopListDTO {
	private Long verifierWeiId;  //认证员微店号
	private Long applyPersonWeiId;  //申请人微店号
	private Short auditState;  //审核状态
	private String beginTime;  //开始时间
	private String endTime;  //结束时间
	
	public Long getApplyPersonWeiId() {
		return applyPersonWeiId;
	}
	public void setApplyPersonWeiId(Long applyPersonWeiId) {
		this.applyPersonWeiId = applyPersonWeiId;
	}
	public Short getAuditState() {
		return auditState;
	}
	public void setAuditState(Short auditState) {
		this.auditState = auditState;
	}
	public String getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public Long getVerifierWeiId() {
		return verifierWeiId;
	}
	public void setVerifierWeiId(Long verifierWeiId) {
		this.verifierWeiId = verifierWeiId;
	}
	
	
	
}
