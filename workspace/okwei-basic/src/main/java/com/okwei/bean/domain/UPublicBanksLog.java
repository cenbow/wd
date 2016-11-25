package com.okwei.bean.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * UPublicBanksLog entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "U_PublicBanksLog")
public class UPublicBanksLog implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -5145233874794653929L;
	private Short id;
	private Integer pid;
	private String content;
	private Date createTime;
	private Long operator;
	private Short state;

	// Constructors

	/** default constructor */
	public UPublicBanksLog() {
	}

	/** full constructor */
	public UPublicBanksLog(Integer pid, String content, Date createTime,
			Long operator, Short state) {
		this.pid = pid;
		this.content = content;
		this.createTime = createTime;
		this.operator = operator;
		this.state = state;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "ID", unique = true, nullable = false)
	public Short getId() {
		return this.id;
	}

	public void setId(Short id) {
		this.id = id;
	}

	@Column(name = "PID")
	public Integer getPid() {
		return this.pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	@Column(name = "Content", length = 50)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "CreateTime", length = 19)
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

	@Column(name = "State")
	public Short getState() {
		return this.state;
	}

	public void setState(Short state) {
		this.state = state;
	}

}