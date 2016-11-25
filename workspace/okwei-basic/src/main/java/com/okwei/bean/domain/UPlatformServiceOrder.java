package com.okwei.bean.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * UPlatformServiceOrder entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "U_PlatformServiceOrder")
public class UPlatformServiceOrder implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -8177110390337001212L;
	private Long id;
	private String supplyOrderId;
	private Integer state;
	private Long sellerWeiid;
	private Long buyerWeiid;
	private Date createTime;
	/**
	 * 总金额
	 */
	private Double totalPrice;
	/**
	 * 平台服务费
	 */
	private Double payAmount;
	private String payOrderId;
	private Short orderType;
	private Date payTime;

	// Constructors

	/** default constructor */
	public UPlatformServiceOrder() {
	}

	/** full constructor */
	public UPlatformServiceOrder(String supplyOrderId, Integer state,
			Long sellerWeiid, Long buyerWeiid, Date createTime,
			Double totalPrice, Double payAmount, String payOrderId,
			Short orderType, Date payTime) {
		this.supplyOrderId = supplyOrderId;
		this.state = state;
		this.sellerWeiid = sellerWeiid;
		this.buyerWeiid = buyerWeiid;
		this.createTime = createTime;
		this.totalPrice = totalPrice;
		this.payAmount = payAmount;
		this.payOrderId = payOrderId;
		this.orderType = orderType;
		this.payTime = payTime;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "ID", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "SupplyOrderId", length = 64)
	public String getSupplyOrderId() {
		return this.supplyOrderId;
	}

	public void setSupplyOrderId(String supplyOrderId) {
		this.supplyOrderId = supplyOrderId;
	}

	@Column(name = "State")
	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	@Column(name = "SellerWeiid")
	public Long getSellerWeiid() {
		return this.sellerWeiid;
	}

	public void setSellerWeiid(Long sellerWeiid) {
		this.sellerWeiid = sellerWeiid;
	}

	@Column(name = "BuyerWeiid")
	public Long getBuyerWeiid() {
		return this.buyerWeiid;
	}

	public void setBuyerWeiid(Long buyerWeiid) {
		this.buyerWeiid = buyerWeiid;
	}

	@Column(name = "CreateTime", length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "TotalPrice", precision = 10)
	public Double getTotalPrice() {
		return this.totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Column(name = "PayAmount", precision = 10)
	public Double getPayAmount() {
		return this.payAmount;
	}

	public void setPayAmount(Double payAmount) {
		this.payAmount = payAmount;
	}

	@Column(name = "PayOrderId", length = 64)
	public String getPayOrderId() {
		return this.payOrderId;
	}

	public void setPayOrderId(String payOrderId) {
		this.payOrderId = payOrderId;
	}

	@Column(name = "OrderType")
	public Short getOrderType() {
		return this.orderType;
	}

	public void setOrderType(Short orderType) {
		this.orderType = orderType;
	}

	@Column(name = "PayTime", length = 19)
	public Date getPayTime() {
		return this.payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

}