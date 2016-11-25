package com.okwei.bean.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * OPayOrderExtend entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "O_PayOrderExtend")
public class OPayOrderExtend implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 2299805901792883822L;
	private String opayOrderId;
	private Double coinAmount;

	// Constructors

	/** default constructor */
	public OPayOrderExtend() {
	}

	/** full constructor */
	public OPayOrderExtend(Double coinAmount) {
		this.coinAmount = coinAmount;
	}

	// Property accessors
	@Id
	@Column(name = "OPayOrderId", unique = true, nullable = false, length = 64)
	public String getOpayOrderId() {
		return this.opayOrderId;
	}

	public void setOpayOrderId(String opayOrderId) {
		this.opayOrderId = opayOrderId;
	}

	@Column(name = "CoinAmount", precision = 22, scale = 0)
	public Double getCoinAmount() {
		return this.coinAmount;
	}

	public void setCoinAmount(Double coinAmount) {
		this.coinAmount = coinAmount;
	}

}