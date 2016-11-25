package com.okwei.bean.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * UWalletDetails entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "U_Wallet_Details")
public class UWalletDetails  extends BaseEntity {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -4376452885870290338L;
	private Long wdetailsId;
	private Long weiId;
	private Long fromWeiId;
	private String payOrder;
	private String supplyOrder;
	private String productOrder;
	private Double amount;
	private Short type;
	private Double restAmount;
	private Date createTime;
	private Short mainType;

	// Constructors

	/** default constructor */
	public UWalletDetails() {
	}

	/** full constructor */
	public UWalletDetails(Long weiId, Long fromWeiId, String payOrder,
			String supplyOrder, String productOrder, Double amount, Short type,
			Double restAmount, Date createTime) {
		this.weiId = weiId;
		this.fromWeiId = fromWeiId;
		this.payOrder = payOrder;
		this.supplyOrder = supplyOrder;
		this.productOrder = productOrder;
		this.amount = amount;
		this.type = type;
		this.restAmount = restAmount;
		this.createTime = createTime;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "WdetailsID", unique = true, nullable = false)
	public Long getWdetailsId() {
		return this.wdetailsId;
	}

	public void setWdetailsId(Long wdetailsId) {
		this.wdetailsId = wdetailsId;
	}

	@Column(name = "WeiID")
	public Long getWeiId() {
		return this.weiId;
	}

	public void setWeiId(Long weiId) {
		this.weiId = weiId;
	}

	@Column(name = "FromWeiID")
	public Long getFromWeiId() {
		return this.fromWeiId;
	}

	public void setFromWeiId(Long fromWeiId) {
		this.fromWeiId = fromWeiId;
	}

	@Column(name = "PayOrder", length = 32)
	public String getPayOrder() {
		return this.payOrder;
	}

	public void setPayOrder(String payOrder) {
		this.payOrder = payOrder;
	}

	@Column(name = "SupplyOrder", length = 32)
	public String getSupplyOrder() {
		return this.supplyOrder;
	}

	public void setSupplyOrder(String supplyOrder) {
		this.supplyOrder = supplyOrder;
	}

	@Column(name = "ProductOrder", length = 32)
	public String getProductOrder() {
		return this.productOrder;
	}

	public void setProductOrder(String productOrder) {
		this.productOrder = productOrder;
	}

	@Column(name = "Amount", precision = 18, scale = 5)
	public Double getAmount() {
		return this.amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	@Column(name = "Type")
	public Short getType() {
		return this.type;
	}

	public void setType(Short type) {
		this.type = type;
	}

	@Column(name = "RestAmount", precision = 18, scale = 5)
	public Double getRestAmount() {
		return this.restAmount;
	}

	public void setRestAmount(Double restAmount) {
		this.restAmount = restAmount;
	}

	@Column(name = "CreateTime", length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@Column(name = "MainType")	
	public Short getMainType() {
		return mainType;
	}

	public void setMainType(Short mainType) {
		this.mainType = mainType;
	}


}