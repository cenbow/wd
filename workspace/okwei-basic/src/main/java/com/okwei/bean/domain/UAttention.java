package com.okwei.bean.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * UAttention entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "U_Attention")
public class UAttention extends BaseEntity {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long attentioner;
	private Long attTo;
	private Short status;
	private Date createTime;
	private String attToPY;

	// Constructors

	/** default constructor */
	public UAttention() {
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

	@Column(name = "Attentioner")
	public Long getAttentioner() {
		return attentioner;
	}

	public void setAttentioner(Long attentioner) {
		this.attentioner = attentioner;
	}

	@Column(name = "AttTo")
	public Long getAttTo() {
		return this.attTo;
	}

	public void setAttTo(Long attTo) {
		this.attTo = attTo;
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

	@Column(name = "AttToPY")
	public String getAttToPY() {
		return attToPY;
	}

	public void setAttToPY(String attToPY) {
		this.attToPY = attToPY;
	}

}