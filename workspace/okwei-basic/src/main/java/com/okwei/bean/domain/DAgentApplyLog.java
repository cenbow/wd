package com.okwei.bean.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * DAgentApplyLog entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "D_AgentApplyLog")
public class DAgentApplyLog implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Fields

	private Long logId;
	private Long agentApplyId;
	private String content;
	private Date createTime;
	private Long operator;

	// Constructors

	/** default constructor */
	public DAgentApplyLog() {
	}

	/** full constructor */
	public DAgentApplyLog(Long agentApplyId, String content,
			Date createTime, Long operator) {
		this.agentApplyId = agentApplyId;
		this.content = content;
		this.createTime = createTime;
		this.operator = operator;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "LogID", unique = true, nullable = false)
	public Long getLogId() {
		return this.logId;
	}

	public void setLogId(Long logId) {
		this.logId = logId;
	}

	@Column(name = "AgentApplyID")
	public Long getAgentApplyId() {
		return this.agentApplyId;
	}

	public void setAgentApplyId(Long agentApplyId) {
		this.agentApplyId = agentApplyId;
	}

	@Column(name = "Content", length = 128)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "CreateTime", length = 0)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "Operator")
	public Long getOperator() {
		return this.operator;
	}

	public void setOperator(Long operator) {
		this.operator = operator;
	}

}