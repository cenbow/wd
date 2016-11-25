package com.okwei.bean.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ADealsLog entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "A_DealsLog")
public class ADealsLog implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3607724067151952456L;
	// Fields

	private Integer logId;
	private Integer dealsId;
	private String operator;
	private String content;
	private Date createTime;

	// Constructors

	/** default constructor */
	public ADealsLog() {
	}

	/** full constructor */
	public ADealsLog(Integer dealsId, String operator, String content,
			Date createTime) {
		this.dealsId = dealsId;
		this.operator = operator;
		this.content = content;
		this.createTime = createTime;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "LogID", unique = true, nullable = false)
	public Integer getLogId() {
		return this.logId;
	}

	public void setLogId(Integer logId) {
		this.logId = logId;
	}

	@Column(name = "DealsID")
	public Integer getDealsId() {
		return this.dealsId;
	}

	public void setDealsId(Integer dealsId) {
		this.dealsId = dealsId;
	}

	@Column(name = "Operator", length = 64)
	public String getOperator() {
		return this.operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
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

}