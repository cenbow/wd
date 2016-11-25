package com.okwei.bean.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * UAgentApplyFollowLog entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "U_AgentApplyFollowLog")
public class UAgentApplyFollowLog implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -8397643137394252288L;
	private Integer flogId;
	private Integer applyId;
	private Long weiId;
	private String remaks;
	private Date createTime;

	// Constructors

	/** default constructor */
	public UAgentApplyFollowLog() {
	}

	/** full constructor */
	public UAgentApplyFollowLog(Integer applyId, Long weiId, String remaks,
			Date createTime) {
		this.applyId = applyId;
		this.weiId = weiId;
		this.remaks = remaks;
		this.createTime = createTime;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "FlogID", unique = true, nullable = false)
	public Integer getFlogId() {
		return this.flogId;
	}

	public void setFlogId(Integer flogId) {
		this.flogId = flogId;
	}

	@Column(name = "ApplyID")
	public Integer getApplyId() {
		return this.applyId;
	}

	public void setApplyId(Integer applyId) {
		this.applyId = applyId;
	}

	@Column(name = "WeiID")
	public Long getWeiId() {
		return this.weiId;
	}

	public void setWeiId(Long weiId) {
		this.weiId = weiId;
	}

	@Column(name = "Remaks", length = 256)
	public String getRemaks() {
		return this.remaks;
	}

	public void setRemaks(String remaks) {
		this.remaks = remaks;
	}

	@Column(name = "CreateTime", length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}