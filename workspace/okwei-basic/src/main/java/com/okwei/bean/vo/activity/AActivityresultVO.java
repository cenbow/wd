package com.okwei.bean.vo.activity;

import java.io.Serializable;
import java.util.Date;

import com.okwei.bean.domain.AActivity;

public class AActivityresultVO implements Serializable{
	
	
	/**
	 * 活动是否开始
	 */
	private int stepState;
	private Long actId;
	private String title;
	private String demand;
	private Date applyEndTime;
	private Date applyBeginTime;
	private Date startTime;
	private Date endTime;
	private Short state;
	private Date createTime;
	private Long creator;
	private Date updateTime;
	private Integer sellerCount;
	
	public Long getActId() {
		return actId;
	}

	public void setActId(Long actId) {
		this.actId = actId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDemand() {
		return demand;
	}

	public void setDemand(String demand) {
		this.demand = demand;
	}

	public Date getApplyEndTime() {
		return applyEndTime;
	}

	public void setApplyEndTime(Date applyEndTime) {
		this.applyEndTime = applyEndTime;
	}

	public Date getApplyBeginTime() {
		return applyBeginTime;
	}

	public void setApplyBeginTime(Date applyBeginTime) {
		this.applyBeginTime = applyBeginTime;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Short getState() {
		return state;
	}

	public void setState(Short state) {
		this.state = state;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getCreator() {
		return creator;
	}

	public void setCreator(Long creator) {
		this.creator = creator;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getSellerCount() {
		return sellerCount;
	}

	public void setSellerCount(Integer sellerCount) {
		this.sellerCount = sellerCount;
	}

	public int getStepState() {
		return stepState;
	}

	public void setStepState(int stepState) {
		this.stepState = stepState;
	}
	
}
