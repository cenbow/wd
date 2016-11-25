package com.okwei.bean.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * UYunOfflineInLog entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "U_YunOfflineInLog")
public class UYunOfflineInLog implements java.io.Serializable {

	// Fields

	private Long weiId;
	private Short isDisCommision;
	private Date payTime;
	private Short bankType;
	private String bankCard;
	private String orderNo;
	private Double amount;
	private Integer serviceYears;
	private Double bon;
	private Double serviceFee;
	private String payNo;
	private String remark;
	private String receptBill;
	private Date createTime;
	private Long operater;

	// Constructors

	/** default constructor */
	public UYunOfflineInLog() {
	}

	/** full constructor */
	public UYunOfflineInLog(Short isDisCommision, Date payTime,
			Short bankType, String bankCard, String orderNo, Double amount,
			Integer serviceYears, Double bon, Double serviceFee, String payNo,
			String remark, String receptBill, Date createTime,
			Long operater) {
		this.isDisCommision = isDisCommision;
		this.payTime = payTime;
		this.bankType = bankType;
		this.bankCard = bankCard;
		this.orderNo = orderNo;
		this.amount = amount;
		this.serviceYears = serviceYears;
		this.bon = bon;
		this.serviceFee = serviceFee;
		this.payNo = payNo;
		this.remark = remark;
		this.receptBill = receptBill;
		this.createTime = createTime;
		this.operater = operater;
	}

	// Property accessors
	@Id
	@Column(name = "WeiID", unique = true, nullable = false)
	public Long getWeiId() {
		return this.weiId;
	}

	public void setWeiId(Long weiId) {
		this.weiId = weiId;
	}

	@Column(name = "IsDisCommision")
	public Short getIsDisCommision() {
		return this.isDisCommision;
	}

	public void setIsDisCommision(Short isDisCommision) {
		this.isDisCommision = isDisCommision;
	}

	@Column(name = "PayTime", length = 19)
	public Date getPayTime() {
		return this.payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	@Column(name = "BankType")
	public Short getBankType() {
		return this.bankType;
	}

	public void setBankType(Short bankType) {
		this.bankType = bankType;
	}

	@Column(name = "BankCard", length = 64)
	public String getBankCard() {
		return this.bankCard;
	}

	public void setBankCard(String bankCard) {
		this.bankCard = bankCard;
	}

	@Column(name = "OrderNo", length = 32)
	public String getOrderNo() {
		return this.orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	@Column(name = "Amount", precision = 18)
	public Double getAmount() {
		return this.amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	@Column(name = "ServiceYears")
	public Integer getServiceYears() {
		return this.serviceYears;
	}

	public void setServiceYears(Integer serviceYears) {
		this.serviceYears = serviceYears;
	}

	@Column(name = "Bon", precision = 18)
	public Double getBon() {
		return this.bon;
	}

	public void setBon(Double bon) {
		this.bon = bon;
	}

	@Column(name = "ServiceFee", precision = 18)
	public Double getServiceFee() {
		return this.serviceFee;
	}

	public void setServiceFee(Double serviceFee) {
		this.serviceFee = serviceFee;
	}

	@Column(name = "PayNo", length = 32)
	public String getPayNo() {
		return this.payNo;
	}

	public void setPayNo(String payNo) {
		this.payNo = payNo;
	}

	@Column(name = "Remark", length = 128)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "ReceptBill", length = 128)
	public String getReceptBill() {
		return this.receptBill;
	}

	public void setReceptBill(String receptBill) {
		this.receptBill = receptBill;
	}

	@Column(name = "CreateTime", length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "Operater")
	public Long getOperater() {
		return this.operater;
	}

	public void setOperater(Long operater) {
		this.operater = operater;
	}

}