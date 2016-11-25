package com.okwei.bean.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * OPayOrderRefund entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "O_PayOrderRefund")
public class OPayOrderRefund implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1488518530492185563L;
	private String payOrderId;
	private Long weiId;
	private Double payAmout;
	private Short payType;
	private Short state;
	private Date refundTime;
	private Date createTime;
	private String remarks;

	// Constructors

	/** default constructor */
	public OPayOrderRefund() {
	}

	/** full constructor */
	public OPayOrderRefund(Long weiId, Double payAmout, Short payType,
			Short state, Date refundTime, Date createTime) {
		this.weiId = weiId;
		this.payAmout = payAmout;
		this.payType = payType;
		this.state = state;
		this.refundTime = refundTime;
		this.createTime = createTime;
	}

	// Property accessors
	@Id
	@Column(name = "PayOrderID", unique = true, nullable = false, length = 20)
	public String getPayOrderId() {
		return this.payOrderId;
	}

	public void setPayOrderId(String payOrderId) {
		this.payOrderId = payOrderId;
	}

	@Column(name = "WeiID")
	public Long getWeiId() {
		return this.weiId;
	}

	public void setWeiId(Long weiId) {
		this.weiId = weiId;
	}

	@Column(name = "PayAmout", precision = 18)
	public Double getPayAmout() {
		return this.payAmout;
	}

	public void setPayAmout(Double payAmout) {
		this.payAmout = payAmout;
	}

	@Column(name = "PayType")
	public Short getPayType() {
		return this.payType;
	}

	public void setPayType(Short payType) {
		this.payType = payType;
	}

	@Column(name = "State")
	public Short getState() {
		return this.state;
	}

	public void setState(Short state) {
		this.state = state;
	}

	@Column(name = "RefundTime", length = 19)
	public Date getRefundTime() {
		return this.refundTime;
	}

	public void setRefundTime(Date refundTime) {
		this.refundTime = refundTime;
	}

	@Column(name = "CreateTime", length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name="Remarks",length=256)
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}