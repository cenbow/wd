package com.okwei.bean.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * OPayReward entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "O_PayReward")
public class OPayReward implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -2071972475367195900L;
	private Integer channelId;
	private String payOrderId;
	private String supplyOrderId;
	private Date payTime;

	// Constructors

	/** default constructor */
	public OPayReward() {
	}

	/** full constructor */
	public OPayReward(String payOrderId, String supplyOrderId) {
		this.payOrderId = payOrderId;
		this.supplyOrderId = supplyOrderId;
	}

	// Property accessors
	@Id
	@Column(name = "ChannelId", unique = true, nullable = false)
	public Integer getChannelId() {
		return this.channelId;
	}

	public void setChannelId(Integer channelId) {
		this.channelId = channelId;
	}

	@Column(name = "PayOrderId", length = 64)
	public String getPayOrderId() {
		return this.payOrderId;
	}

	public void setPayOrderId(String payOrderId) {
		this.payOrderId = payOrderId;
	}

	@Column(name = "SupplyOrderId", length = 64)
	public String getSupplyOrderId() {
		return this.supplyOrderId;
	}

	public void setSupplyOrderId(String supplyOrderId) {
		this.supplyOrderId = supplyOrderId;
	}
	
	@Column(name = "PayTime", length = 0)
	public Date getPayTime() {
		return this.payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

}