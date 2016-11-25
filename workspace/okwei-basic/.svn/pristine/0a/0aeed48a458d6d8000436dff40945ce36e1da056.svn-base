package com.okwei.bean.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * OPayOrderLog entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "O_PayOrderLog")
public class OPayOrderLog implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -5955794374529892298L;
	private String payOrderId;
	private String supplyOrderIds;
	private Long weiId;
	private Double totalAmout;
	private Double payAmout;
	private Short state;
	private Date payTime;
	private Date createTime;
	private String remarks;

	// Constructors

	/** default constructor */
	public OPayOrderLog() {
	}

	/** full constructor */
	public OPayOrderLog(String supplyOrderIds, Long weiId, Double totalAmout,
			Double payAmout, Short state, Date payTime,
			Date createTime) {
		this.supplyOrderIds = supplyOrderIds;
		this.weiId = weiId;
		this.totalAmout = totalAmout;
		this.payAmout = payAmout;
		this.state = state;
		this.payTime = payTime;
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

	@Column(name = "SupplyOrderIDs", length = 265)
	public String getSupplyOrderIds() {
		return this.supplyOrderIds;
	}

	public void setSupplyOrderIds(String supplyOrderIds) {
		this.supplyOrderIds = supplyOrderIds;
	}

	@Column(name = "WeiID")
	public Long getWeiId() {
		return this.weiId;
	}

	public void setWeiId(Long weiId) {
		this.weiId = weiId;
	}

	@Column(name = "TotalAmout", precision = 18, scale =2)
	public Double getTotalAmout() {
		return this.totalAmout;
	}

	public void setTotalAmout(Double totalAmout) {
		this.totalAmout = totalAmout;
	}

	@Column(name = "PayAmout", precision = 18, scale = 2)
	public Double getPayAmout() {
		return this.payAmout;
	}

	public void setPayAmout(Double payAmout) {
		this.payAmout = payAmout;
	}

	@Column(name = "State")
	public Short getState() {
		return this.state;
	}

	public void setState(Short state) {
		this.state = state;
	}

	@Column(name = "PayTime", length = 19)
	public Date getPayTime() {
		return this.payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
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