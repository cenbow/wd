package com.okwei.bean.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * UTradingDetails entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "U_Trading_Details")
public class UTradingDetails extends BaseEntity {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long detailId;
	private Long weiId;
	private String orderNo;
	private String productOrder;
	private String supplyOrder;
	private Double amount;
	private Short type;
	private Short state;
	private Short ltwoType;
	private Double surplusAmout;
	private Date createTime;
	private Long wdetailsId;
	
	
	
	@Column(name = "WdetailsID")
	public Long getWdetailsId() {
		return wdetailsId;
	}

	public void setWdetailsId(Long wdetailsId) {
		this.wdetailsId = wdetailsId;
	}

	@Column(name = "InTime")
	private Date inTime;
	

	private Long fromWeiId;

	// Constructors

	/** default constructor */
	public UTradingDetails() {
	}

	@Id
	@GeneratedValue
	@Column(name = "DetailID")
	public Long getDetailId() {
		return detailId;
	}

	public void setDetailId(Long detailId) {
		this.detailId = detailId;
	}

	@Column(name = "WeiID")
	public Long getWeiId() {
		return weiId;
	}
	public void setWeiId(Long weiId) {
		this.weiId = weiId;
	}
	@Column(name="FromWeiID")
	public Long getFromWeiID() {
		return fromWeiId;
	}
	public void setFromWeiID(Long fromWeiID) {
		this.fromWeiId = fromWeiID;
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
		if (this.amount == null)
			return 0d;
		return this.amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	@Column(name = "Type")
	public Short getType() {
		return this.type;
	}

	public void setType(Short tyep) {
		this.type = tyep;
	}

	@Column(name = "State")
	public Short getState() {
		return this.state;
	}

	public void setState(Short state) {
		this.state = state;
	}

	@Column(name = "LtwoType")
	public Short getLtwoType() {
		return this.ltwoType;
	}

	public void setLtwoType(Short ltwoType) {
		this.ltwoType = ltwoType;
	}

	@Column(name = "SurplusAmout", precision = 18)
	public Double getSurplusAmout() {
		if (this.surplusAmout == null)
			return 0d;
		return this.surplusAmout;
	}

	public void setSurplusAmout(Double surplusAmout) {
		this.surplusAmout = surplusAmout;
	}

	@Column(name = "CreateTime", length = 0)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getSupplyOrder() {
		return supplyOrder;
	}

	public void setSupplyOrder(String supplyOrder) {
		this.supplyOrder = supplyOrder;
	}

	public String getProductOrder() {
		return productOrder;
	}

	public void setProductOrder(String productOrder) {
		this.productOrder = productOrder;
	}

	public Date getInTime() {
		return inTime;
	}

	public void setInTime(Date inTime) {
		this.inTime = inTime;
	}
}