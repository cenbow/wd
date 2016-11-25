package com.okwei.bean.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * UAttentioned entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "U_Attentioned")
public class UAttentioned extends BaseEntity {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long attTo;
	private Long attentioner;
	private Short status;
	private Date createTime;
	private String attentionerPY;

	// Constructors

	/** default constructor */
	public UAttentioned() {
	}

	@Id
	@GeneratedValue
	@Column(name = "ID")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "AttTo")
	public Long getAttTo() {
		return attTo;
	}

	public void setAttTo(Long attTo) {
		this.attTo = attTo;
	}

	@Column(name = "Attentioner")
	public Long getAttentioner() {
		return this.attentioner;
	}

	public void setAttentioner(Long attentioner) {
		this.attentioner = attentioner;
	}

	@Column(name = "Status")
	public Short getStatus() {
		return this.status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	@Column(name = "CreateTime", length = 0)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "AttentionerPY")
	public String getAttentionerPY() {
		return attentionerPY;
	}

	public void setAttentionerPY(String attentionerPY) {
		this.attentionerPY = attentionerPY;
	}

}