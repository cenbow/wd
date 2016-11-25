package com.okwei.bean.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TReturnAmount entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_ReturnAmount")
public class TReturnAmount implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -2766418891046881024L;
	private Long id;
	private String backOrder;
	private Long weiId;
	private String orderNo;
	private String tkorderNo;
	private Short payType;
	private Short transactionType;
	private Short refundType;
	private Double refundAmount;
	private Double counterFee;
	private Short state;
	private Date createTime;
	private Long operator;
	private Date playingTime;
	private String remark;

	// Constructors

	/** default constructor */
	public TReturnAmount() {
	}

	/** full constructor */
	public TReturnAmount(String backOrder, Long weiId, String orderNo,
			String tkorderNo, Short payType, Short transactionType,
			Short refundType, Double refundAmount, Double counterFee,
			Short state, Date createTime, Long operator,
			Date playingTime, String remark) {
		this.backOrder = backOrder;
		this.weiId = weiId;
		this.orderNo = orderNo;
		this.tkorderNo = tkorderNo;
		this.payType = payType;
		this.transactionType = transactionType;
		this.refundType = refundType;
		this.refundAmount = refundAmount;
		this.counterFee = counterFee;
		this.state = state;
		this.createTime = createTime;
		this.operator = operator;
		this.playingTime = playingTime;
		this.remark = remark;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "BackOrder", length = 32)
	public String getBackOrder() {
		return this.backOrder;
	}

	public void setBackOrder(String backOrder) {
		this.backOrder = backOrder;
	}

	@Column(name = "WeiID")
	public Long getWeiId() {
		return this.weiId;
	}

	public void setWeiId(Long weiId) {
		this.weiId = weiId;
	}

	@Column(name = "OrderNo", length = 32)
	public String getOrderNo() {
		return this.orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	@Column(name = "TKOrderNo", length = 32)
	public String getTkorderNo() {
		return this.tkorderNo;
	}

	public void setTkorderNo(String tkorderNo) {
		this.tkorderNo = tkorderNo;
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

	@Column(name = "RefundAmount", precision = 18)
	public Double getRefundAmount() {
		return this.refundAmount;
	}

	public void setRefundAmount(Double refundAmount) {
		this.refundAmount = refundAmount;
	}

	@Column(name = "CounterFee", precision = 18)
	public Double getCounterFee() {
		return this.counterFee;
	}

	public void setCounterFee(Double counterFee) {
		this.counterFee = counterFee;
	}

	@Column(name = "State")
	public Short getState() {
		return this.state;
	}

	public void setState(Short state) {
		this.state = state;
	}

	@Column(name = "CreateTime", length = 0)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "Operator")
	public Long getOperator() {
		return this.operator;
	}

	public void setOperator(Long operator) {
		this.operator = operator;
	}

	@Column(name = "PlayingTime", length = 0)
	public Date getPlayingTime() {
		return this.playingTime;
	}

	public void setPlayingTime(Date playingTime) {
		this.playingTime = playingTime;
	}

	@Column(name = "Remark", length = 128)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}