package com.okwei.bean.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * OPayOrder entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "O_PayOrder")
public class OPayOrder extends BaseEntity {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String payOrderId;
	private String bigOrderId;
	private Long weiId;
	private Long sellerWeiId;
	private Long sellerUpWeiId;
	private Double totalPrice;
	private Double walletmoney;
	private Short orderFrom;
	
	private Double weiDianCoin;

	@Column(name = "WalletMoney", precision = 18)
	public Double getWalletmoney() {
		if (this.walletmoney == null)
			return 0d;
		return walletmoney;
	}

	public void setWalletmoney(Double walletmoney) {
		this.walletmoney = walletmoney;
	}

	@Column(name = "PayTime", length = 0)
	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	private Date orderTime;
	private Date payTime;
	private Short state;
	private Short payType;
	private Date orderDate;
	private Short typeState;
	private String supplierOrder;
	private Double originalPrice;
	private Double otherAmout;

	
	
	
	@Column(name = "OtherAmout")
	public Double getOtherAmout() {
		return otherAmout;
	}

	public void setOtherAmout(Double otherAmout) {
		this.otherAmout = otherAmout;
	}
	
	
	@Column(name = "OriginalPrice")
	public Double getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(Double originalPrice) {
		this.originalPrice = originalPrice;
	}

	// Constructors
	@Column(name = "TypeState")
	public Short getTypeState() {
		return typeState;
	}

	public void setTypeState(Short typeState) {
		this.typeState = typeState;
	}

	@Column(name = "SupplierOrder", length = 32)
	public String getSupplierOrder() {
		return supplierOrder;
	}

	public void setSupplierOrder(String supplierOrder) {
		this.supplierOrder = supplierOrder;
	}

	/** default constructor */
	public OPayOrder() {
	}

	/** full constructor */

	// Property accessors
	@Id
	@Column(name = "PayOrderID", unique = true, nullable = false, length = 32)
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

	@Column(name = "SellerWeiID")
	public Long getSellerWeiId() {
		return this.sellerWeiId;
	}

	public void setSellerWeiId(Long sellerWeiId) {
		this.sellerWeiId = sellerWeiId;
	}

	@Column(name = "SellerUpWeiID")
	public Long getSellerUpWeiId() {
		return this.sellerUpWeiId;
	}

	public void setSellerUpWeiId(Long sellerUpWeiId) {
		this.sellerUpWeiId = sellerUpWeiId;
	}

	@Column(name = "TotalPrice", precision = 18)
	public Double getTotalPrice() {
		if (this.totalPrice == null)
			return 0d;
		return this.totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Column(name = "OrderFrom")
	public Short getOrderFrom() {
		return this.orderFrom;
	}

	public void setOrderFrom(Short orderFrom) {
		this.orderFrom = orderFrom;
	}

	@Column(name = "OrderTime", length = 0)
	public Date getOrderTime() {
		return this.orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	@Column(name = "State")
	public Short getState() {
		return this.state;
	}

	public void setState(Short state) {
		this.state = state;
	}

	@Column(name = "OrderDate", length = 0)
	public Date getOrderDate() {
		return this.orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	@Column(name = "PayType")
	public Short getPayType() {
		return payType;
	}

	public void setPayType(Short payType) {
		this.payType = payType;
	}

	@Column(name = "BigOrderID", length = 32)
	public String getBigOrderId() {
		return bigOrderId;
	}

	public void setBigOrderId(String bigOrderId) {
		this.bigOrderId = bigOrderId;
	}

	@Column(name="WeiDianCoin")
	public Double getWeiDianCoin() {
		return weiDianCoin;
	}

	public void setWeiDianCoin(Double weiDianCoin) {
		this.weiDianCoin = weiDianCoin;
	}

	
	
	
	

}