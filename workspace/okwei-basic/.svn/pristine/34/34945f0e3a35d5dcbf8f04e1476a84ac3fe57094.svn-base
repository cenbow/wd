package com.okwei.bean.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * APerfectProductsLog entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "A_PerfectProductsLog")
public class APerfectProductsLog implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1397606510861013126L;
	// Fields

	private Long perLogId;
	private Long perPid;
	private Long operator;
	private String content;
	private Date createTime;

	// Constructors

	/** default constructor */
	public APerfectProductsLog() {
	}

	/** minimal constructor */
	public APerfectProductsLog(Long perLogId) {
		this.perLogId = perLogId;
	}

	/** full constructor */
	public APerfectProductsLog( Long perPid, Long operator,
			String content, Date createTime) {
		this.perPid = perPid;
		this.operator = operator;
		this.content = content;
		this.createTime = createTime;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "PerLogID", unique = true, nullable = false)
	public Long getPerLogId() {
		return this.perLogId;
	}

	public void setPerLogId(Long perLogId) {
		this.perLogId = perLogId;
	}

	@Column(name = "PerPID")
	public Long getPerPid() {
		return this.perPid;
	}

	public void setPerPid(Long perPid) {
		this.perPid = perPid;
	}

	@Column(name = "Operator")
	public Long getOperator() {
		return this.operator;
	}

	public void setOperator(Long operator) {
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