package com.okwei.bean.vo.activity;

import java.io.Serializable;
import java.util.Date;

public class ARedPacMoneyDetailVO implements Serializable{
	private static final long serialVersionUID = -7630372924359878101L;
	private Integer redDetailId;
	private Integer pid;
	private Double amount;
	private Integer redTypeId;
	private Integer redTypeName;
	private Integer count;
	private Date createTime;
	private Integer status;
	private Integer restCount;
	public Integer getRedDetailId() {
		return redDetailId;
	}
	public Integer getPid() {
		return pid;
	}
	public Double getAmount() {
		return amount;
	}
	public Integer getRedTypeId() {
		return redTypeId;
	}
	public Integer getRedTypeName() {
		return redTypeName;
	}
	public Integer getCount() {
		return count;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public Integer getStatus() {
		return status;
	}
	public Integer getRestCount() {
		return restCount;
	}
	public void setRedDetailId(Integer redDetailId) {
		this.redDetailId = redDetailId;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public void setRedTypeId(Integer redTypeId) {
		this.redTypeId = redTypeId;
	}
	public void setRedTypeName(Integer redTypeName) {
		this.redTypeName = redTypeName;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public void setRestCount(Integer restCount) {
		this.restCount = restCount;
	}
	
	
	
	
}
