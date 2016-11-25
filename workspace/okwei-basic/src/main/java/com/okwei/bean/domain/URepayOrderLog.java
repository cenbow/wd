package com.okwei.bean.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * URepayOrderLog entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "U_RepayOrderLog")
public class URepayOrderLog implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8310505650701624660L;
	// Fields

	private String supplyOrderId;
	private Long weiId;
	private Double totalPrice;
	private Date createTime;
	private Integer type;

	// Constructors

	/** default constructor */
	public URepayOrderLog() {
	}

	/** full constructor */
	public URepayOrderLog(Long weiId, Double totalPrice, Date createTime,
			Integer type) {
		this.weiId = weiId;
		this.totalPrice = totalPrice;
		this.createTime = createTime;
		this.type = type;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "SupplyOrderId", unique = true, nullable = false, length = 64)
	public String getSupplyOrderId() {
		return this.supplyOrderId;
	}

	public void setSupplyOrderId(String supplyOrderId) {
		this.supplyOrderId = supplyOrderId;
	}

	@Column(name = "WeiId")
	public Long getWeiId() {
		return this.weiId;
	}

	public void setWeiId(Long weiId) {
		this.weiId = weiId;
	}

	@Column(name = "TotalPrice", precision = 18)
	public Double getTotalPrice() {
		return this.totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Column(name = "CreateTime", length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "Type")
	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

}