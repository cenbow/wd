package com.okwei.bean.vo.activity;

import java.io.Serializable;
import java.util.Date;

public class ARedPacMoneyAgLogVO implements Serializable{
	private static final long serialVersionUID = -7630372924359878101L;
	private Long weiId;
	private String weiName;
	private Double amount;
	private String createTime;
	public Long getWeiId() {
		return weiId;
	}
	public String getWeiName() {
		return weiName;
	}
	public Double getAmount() {
		return amount;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setWeiId(Long weiId) {
		this.weiId = weiId;
	}
	public void setWeiName(String weiName) {
		this.weiName = weiName;
	}
	
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	
	
	
}
