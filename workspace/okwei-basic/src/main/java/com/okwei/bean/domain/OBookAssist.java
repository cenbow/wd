package com.okwei.bean.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * OBookAssist entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "O_BookAssist")
public class OBookAssist extends BaseEntity {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String supplierOrderId;
	private Short bookPayType;
	private Short persent;
	private Double amount;
	private Short tailPayType;
	private Date preSendTime;
	private Short state;
	private String remark;
	private String hPayOrder;
	private String TPayOrder;

	// Constructors

	/** default constructor */
	public OBookAssist() {
	}

	/** full constructor */

	// Property accessors
	@Id
	@Column(name = "SupplierOrderID", unique = true, nullable = false, length = 20)
	public String getSupplierOrderId() {
		return this.supplierOrderId;
	}

	public OBookAssist(String supplierOrderId, Short bookPayType, Short persent, Double amount, Short tailPayType, Date preSendTime) {
		super();
		this.supplierOrderId = supplierOrderId;
		this.bookPayType = bookPayType;
		this.persent = persent;
		this.amount = amount;
		this.tailPayType = tailPayType;
		this.preSendTime = preSendTime;
	}

	public void setSupplierOrderId(String supplierOrderId) {
		this.supplierOrderId = supplierOrderId;
	}

	@Column(name = "BookPayType")
	public Short getBookPayType() {
		return this.bookPayType;
	}

	public void setBookPayType(Short bookPayType) {
		this.bookPayType = bookPayType;
	}

	@Column(name = "Amount", precision = 18)
	public Double getAmount() {
		return this.amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	@Column(name = "TailPayType")
	public Short getTailPayType() {
		return this.tailPayType;
	}

	public void setTailPayType(Short tailPayType) {
		this.tailPayType = tailPayType;
	}

	@Column(name = "PreSendTime", length = 0)
	public Date getPreSendTime() {
		return this.preSendTime;
	}

	public void setPreSendTime(Date preSendTime) {
		this.preSendTime = preSendTime;
	}

	@Column(name = "Persent")
	public Short getPersent() {
		return persent;
	}

	public void setPersent(Short persent) {
		this.persent = persent;
	}

	@Column(name = "State")
	public Short getState() {
		return state;
	}

	public void setState(Short state) {
		this.state = state;
	}

	@Column(name = "Remark")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String gethPayOrder() {
		return hPayOrder;
	}

	public void sethPayOrder(String hPayOrder) {
		this.hPayOrder = hPayOrder;
	}

	public String getTPayOrder() {
		return TPayOrder;
	}

	public void setTPayOrder(String tPayOrder) {
		TPayOrder = tPayOrder;
	}

}