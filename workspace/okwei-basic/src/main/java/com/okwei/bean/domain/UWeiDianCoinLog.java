package com.okwei.bean.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * UWeiDianCoinLog entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "U_WeiDianCoinLog")
public class UWeiDianCoinLog implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -292484863635998902L;
	// Fields

	private Long logId;
	private Long weiId;
	private Date createTime;
	private Short type;
	private String remark;
	private Double consumeAmount;
	private Double balanceAmount;
	private String payOrderId;
	private Long shareId;

	// Constructors

	/** default constructor */
	public UWeiDianCoinLog() {
	}

	/** full constructor */
	public UWeiDianCoinLog(Long weiId, Date createTime, Short type,
			String remark) {
		this.weiId = weiId;
		this.createTime = createTime;
		this.type = type;
		this.remark = remark;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "LogID", unique = true, nullable = false)
	public Long getLogId() {
		return this.logId;
	}

	public void setLogId(Long logId) {
		this.logId = logId;
	}

	@Column(name = "WeiID")
	public Long getWeiId() {
		return this.weiId;
	}

	public void setWeiId(Long weiId) {
		this.weiId = weiId;
	}

	@Column(name = "CreateTime", length = 0)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "Type")
	public Short getType() {
		return this.type;
	}

	public void setType(Short type) {
		this.type = type;
	}

	@Column(name = "Remark", length = 128)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "ConsumeAmount")
	public Double getConsumeAmount() {
		return consumeAmount;
	}

	public void setConsumeAmount(Double consumeAmount) {
		this.consumeAmount = consumeAmount;
	}

	@Column(name = "BalanceAmount")
	public Double getBalanceAmount() {
		return balanceAmount;
	}

	public void setBalanceAmount(Double balanceAmount) {
		this.balanceAmount = balanceAmount;
	}

	@Column(name = "PayOrderID", length = 64)
	public String getPayOrderId() {
		return payOrderId;
	}

	public void setPayOrderId(String payOrderId) {
		this.payOrderId = payOrderId;
	}

	@Column(name = "ShareID")
	public Long getShareId() {
		return shareId;
	}

	public void setShareId(Long shareId) {
		this.shareId = shareId;
	}
	
	
}