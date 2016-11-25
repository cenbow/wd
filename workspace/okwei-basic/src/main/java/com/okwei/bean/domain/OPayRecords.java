package com.okwei.bean.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * OPayRecords entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "O_PayRecords")
public class OPayRecords extends BaseEntity {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String payOrderId;
	/**
	 * 微信支付时：存prepayId，银行卡支付时：存卡号
	 */
	private String rowOne;
	/**
	 * 身份证号
	 */
	private String rowTwo;
	/**
	 * 姓名
	 */
	private String rowThree;
	/**
	 * 1:微信支付，2银行卡支付
	 */
	private Short bankType;
	private Date createTime;

	// Constructors

	/** default constructor */
	public OPayRecords() {
	}

	/** full constructor */
	public OPayRecords(String rowOne, String rowTwo, String rowThree, Short bankType, Date createTime) {
		this.rowOne = rowOne;
		this.rowTwo = rowTwo;
		this.rowThree = rowThree;
		this.bankType = bankType;
		this.createTime = createTime;
	}

	// Property accessors
	@Id
	@Column(name = "PayOrderID", unique = true, nullable = false, length = 32)
	public String getPayOrderId() {
		return this.payOrderId;
	}

	public void setPayOrderId(String payOrderId) {
		this.payOrderId = payOrderId;
	}

	@Column(name = "RowOne", length = 64)
	public String getRowOne() {
		return this.rowOne;
	}

	public void setRowOne(String rowOne) {
		this.rowOne = rowOne;
	}

	@Column(name = "RowTwo", length = 64)
	public String getRowTwo() {
		return this.rowTwo;
	}

	public void setRowTwo(String rowTwo) {
		this.rowTwo = rowTwo;
	}

	@Column(name = "RowThree", length = 64)
	public String getRowThree() {
		return this.rowThree;
	}

	public void setRowThree(String rowThree) {
		this.rowThree = rowThree;
	}

	@Column(name = "BankType")
	public Short getBankType() {
		return this.bankType;
	}

	public void setBankType(Short bankType) {
		this.bankType = bankType;
	}

	@Column(name = "CreateTime", length = 0)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}