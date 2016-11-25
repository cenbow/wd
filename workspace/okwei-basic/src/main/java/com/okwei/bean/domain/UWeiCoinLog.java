package com.okwei.bean.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * UWeiCoinLog entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "U_WeiCoinLog")
public class UWeiCoinLog implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -3627578508753112244L;
	private Long coinLogId;
	private Long weiId;
	private Integer useType;
	private Integer state;
	private Date createTime;
	private Date expireTime;
	private Double coinAmount;
	private Double ableCoinAmount;
	private String productOrderId;
	private String supplyOrderId;
	private Integer deleted;

	// Constructors

	/** default constructor */
	public UWeiCoinLog() {
	}

	/** full constructor */
	public UWeiCoinLog(Long weiId, Integer useType, Integer state, Date createTime,
			Date expireTime, Double coinAmount, Double ableCoinAmount
			) {
		this.weiId = weiId;
		this.useType = useType;
		this.state = state;
		this.createTime = createTime;
		this.expireTime = expireTime;
		this.coinAmount = coinAmount;
		this.ableCoinAmount = ableCoinAmount;
		
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "CoinLogId", unique = true, nullable = false)
	public Long getCoinLogId() {
		return this.coinLogId;
	}

	public void setCoinLogId(Long coinLogId) {
		this.coinLogId = coinLogId;
	}

	@Column(name = "WeiId")
	public Long getWeiId() {
		return this.weiId;
	}

	public void setWeiId(Long weiId) {
		this.weiId = weiId;
	}


	@Column(name = "UseType")
	public Integer getUseType() {
		return this.useType;
	}

	public void setUseType(Integer useType) {
		this.useType = useType;
	}

	@Column(name = "State")
	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	@Column(name = "CreateTime", length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "ExpireTime", length = 19)
	public Date getExpireTime() {
		return this.expireTime;
	}

	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}

	@Column(name = "CoinAmount", precision = 22, scale = 0)
	public Double getCoinAmount() {
		return this.coinAmount;
	}

	public void setCoinAmount(Double coinAmount) {
		this.coinAmount = coinAmount;
	}

	@Column(name = "AbleCoinAmount", precision = 22, scale = 0)
	public Double getAbleCoinAmount() {
		return this.ableCoinAmount;
	}

	public void setAbleCoinAmount(Double ableCoinAmount) {
		this.ableCoinAmount = ableCoinAmount;
	}

	
	@Column(name = "ProductOrderId", length = 64)
	public String getProductOrderId() {
		return this.productOrderId;
	}

	public void setProductOrderId(String productOrderId) {
		this.productOrderId = productOrderId;
	}
	
	@Column(name = "SupplyOrderId", length = 64)
	public String getSupplyOrderId() {
		return this.supplyOrderId;
	}

	public void setSupplyOrderId(String supplyOrderId) {
		this.supplyOrderId = supplyOrderId;
	}
	
	@Column(name = "Deleted")
	public Integer getDeleted() {
		return this.deleted;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}


}