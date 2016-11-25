package com.okwei.bean.vo;

import java.util.Date;

/**
 * FFlowMsg entity. @author MyEclipse Persistence Tools
 */
public class FFlowMsg implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long fid;
	private String flowId;
	private Long operater;
	private Date operateTime;
	private String actionName;
	private String actionConment;

	// Constructors

	/** default constructor */
	public FFlowMsg() {
	}

	/** full constructor */

	// Property accessors
	public Long getFid() {
		return this.fid;
	}

	public void setFid(Long fid) {
		this.fid = fid;
	}

	public String getFlowId() {
		return this.flowId;
	}

	public void setFlowId(String flowId) {
		this.flowId = flowId;
	}

	public Long getOperater() {
		return this.operater;
	}

	public void setOperater(Long operater) {
		this.operater = operater;
	}

	public Date getOperateTime() {
		return this.operateTime;
	}

	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}

	public String getActionName() {
		return this.actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public String getActionConment() {
		return this.actionConment;
	}

	public void setActionConment(String actionConment) {
		this.actionConment = actionConment;
	}

}