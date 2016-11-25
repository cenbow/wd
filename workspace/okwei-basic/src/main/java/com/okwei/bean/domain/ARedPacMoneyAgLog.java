package com.okwei.bean.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ARedPacMoneyAgLog entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "A_RedPacMoneyAgLog")
public class ARedPacMoneyAgLog implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3345288588304144330L;
	// Fields

	private Long takeId;
	private Long weiId;
	private Integer redDetailId;
	private Double amount;
	private Date createTime;

	// Constructors

	/** default constructor */
	public ARedPacMoneyAgLog() {
	}

	/** minimal constructor */
	public ARedPacMoneyAgLog(Long weiId) {
		this.weiId = weiId;
	}

	/** full constructor */
	public ARedPacMoneyAgLog(Long weiId, Integer redDetailId, Double amount) {
		this.weiId = weiId;
		this.redDetailId = redDetailId;
		this.amount = amount;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "TakeID", unique = true, nullable = false)
	public Long getTakeId() {
		return this.takeId;
	}

	public void setTakeId(Long takeId) {
		this.takeId = takeId;
	}

	@Column(name = "WeiId", nullable = false)
	public Long getWeiId() {
		return this.weiId;
	}

	public void setWeiId(Long weiId) {
		this.weiId = weiId;
	}

	@Column(name = "RedDetailId")
	public Integer getRedDetailId() {
		return this.redDetailId;
	}

	public void setRedDetailId(Integer redDetailId) {
		this.redDetailId = redDetailId;
	}

	@Column(name = "Amount", precision = 18)
	public Double getAmount() {
		return this.amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	@Column(name = "CreateTime", length = 0)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}