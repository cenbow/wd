package com.okwei.bean.vo.activity;

import java.io.Serializable;
import java.util.Date;

public class AActivityProExtend implements Serializable{

	private static final long serialVersionUID = -7630372924359878101L;
	///抢购开始时间
	private String beginTime;
	//抢购结束时间
	private String endTime;
	
	private Date beginTimeDate;
	private Date endtimeDate;
	
	
	//0未开始，1正在进行 ，2已结束
	private int state;
	
	
	
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
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public Date getBeginTimeDate() {
		return beginTimeDate;
	}
	public void setBeginTimeDate(Date beginTimeDate) {
		this.beginTimeDate = beginTimeDate;
	}
	public Date getEndtimeDate() {
		return endtimeDate;
	}
	public void setEndtimeDate(Date endtimeDate) {
		this.endtimeDate = endtimeDate;
	}
	
	
	
	
}
