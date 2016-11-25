package com.okwei.bean.dto.user;

import java.util.Date;

import com.okwei.bean.enums.DemandStateEnum;

public class SupplyDemandDTO {

	private Long weiId;
	private DemandStateEnum state;
	private String title;
	private Integer provice;
	private Integer city;
	private Date startTime;
	private Date endTime;
	private Integer industryID;
	private Integer showPCount;
	
	public Long getWeiId() {
		return weiId;
	}
	public void setWeiId(Long weiId) {
		this.weiId = weiId;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getProvice() {
		return provice;
	}
	public void setProvice(Integer provice) {
		this.provice = provice;
	}
	public Integer getCity() {
		return city;
	}
	public void setCity(Integer city) {
		this.city = city;
	}

	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public DemandStateEnum getState() {
		return state;
	}
	public void setState(DemandStateEnum state) {
		this.state = state;
	}
	public Integer getIndustryID() {
		return industryID;
	}
	public void setIndustryID(Integer industryID) {
		this.industryID = industryID;
	}
	public Integer getShowPCount() {
		return showPCount;
	}
	public void setShowPCount(Integer showPCount) {
		this.showPCount = showPCount;
	}

	
	
	
}
