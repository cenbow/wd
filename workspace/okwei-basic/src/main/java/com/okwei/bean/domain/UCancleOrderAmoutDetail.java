package com.okwei.bean.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * UCancleOrderAmoutDetail entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "U_CancleOrderAmoutDetail")
public class UCancleOrderAmoutDetail implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1059765657724236994L;
	// Fields

	private Integer id;
	private Long weiId;
	private Long rid;
	private String tkOrderNo;
	private String orderNo;
	private Short payType;
	private Short transactionType;
	private Short refundType;
	private Double amount;
	private Double counterFee;
	private String txname;
	private String bankNum;
	private String bankName;
	private Date createTime;
	private Short state;
	private Short type;
	private Date playTime;
	private String playName;
	private String remark;
	private Double amoutWater;
	private Long detailId;
	private Short bankType;
	private String failReason;

	// Constructors

	/** default constructor */
	public UCancleOrderAmoutDetail() {
	}

	

	

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "ID", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "WeiID", nullable = false)
	public Long getWeiId() {
		return this.weiId;
	}

	public void setWeiId(Long weiId) {
		this.weiId = weiId;
	}

	@Column(name = "Rid")
	public Long getRid() {
		return this.rid;
	}

	public void setRid(Long rid) {
		this.rid = rid;
	}

	@Column(name = "TkOrderNo", length = 32)
	public String getTkOrderNo() {
		return this.tkOrderNo;
	}

	public void setTkOrderNo(String tkOrderNo) {
		this.tkOrderNo = tkOrderNo;
	}

	@Column(name = "OrderNo", length = 32)
	public String getOrderNo() {
		return this.orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	@Column(name = "PayType")
	public Short getPayType() {
		return this.payType;
	}

	public void setPayType(Short payType) {
		this.payType = payType;
	}

	@Column(name = "TransactionType")
	public Short getTransactionType() {
		return this.transactionType;
	}

	public void setTransactionType(Short transactionType) {
		this.transactionType = transactionType;
	}

	@Column(name = "RefundType")
	public Short getRefundType() {
		return this.refundType;
	}

	public void setRefundType(Short refundType) {
		this.refundType = refundType;
	}

	@Column(name = "Amount", precision = 18)
	public Double getAmount() {
		return this.amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	@Column(name = "CounterFee", precision = 18)
	public Double getCounterFee() {
		return this.counterFee;
	}

	public void setCounterFee(Double counterFee) {
		this.counterFee = counterFee;
	}

	@Column(name = "TXName", length = 32)
	public String getTxname() {
		return this.txname;
	}

	public void setTxname(String txname) {
		this.txname = txname;
	}

	@Column(name = "BankNum", length = 64)
	public String getBankNum() {
		return this.bankNum;
	}

	public void setBankNum(String bankNum) {
		this.bankNum = bankNum;
	}

	@Column(name = "BankName", length = 128)
	public String getBankName() {
		return this.bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	@Column(name = "CreateTime", length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "State")
	public Short getState() {
		return this.state;
	}

	public void setState(Short state) {
		this.state = state;
	}

	@Column(name = "Type")
	public Short getType() {
		return this.type;
	}

	public void setType(Short type) {
		this.type = type;
	}

	@Column(name = "PlayTime", length = 19)
	public Date getPlayTime() {
		return this.playTime;
	}

	public void setPlayTime(Date playTime) {
		this.playTime = playTime;
	}

	@Column(name = "PlayName", length = 32)
	public String getPlayName() {
		return this.playName;
	}

	public void setPlayName(String playName) {
		this.playName = playName;
	}

	@Column(name = "Remark", length = 65535)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "AmoutWater", precision = 22, scale = 0)
	public Double getAmoutWater() {
		return this.amoutWater;
	}

	public void setAmoutWater(Double amoutWater) {
		this.amoutWater = amoutWater;
	}

	@Column(name = "DetailID")
	public Long getDetailId() {
		return this.detailId;
	}

	public void setDetailId(Long detailId) {
		this.detailId = detailId;
	}

	@Column(name = "BankType")
	public Short getBankType() {
		return this.bankType;
	}

	public void setBankType(Short bankType) {
		this.bankType = bankType;
	}

	@Column(name = "FailReason", length = 256)
	public String getFailReason() {
		return this.failReason;
	}

	public void setFailReason(String failReason) {
		this.failReason = failReason;
	}

}