package com.okwei.bean.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * UCollectionStore entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "U_CollectionStore")
public class UCollectionStore extends BaseEntity {

	// Fields

	private Long storeId;
	private Long weiId;
	private Long sellerweiId;
	private Long thingId;
	private Short thingType;
	private Short state;
	private Date createTime;

	// Constructors

	/** default constructor */
	public UCollectionStore() {
	}

	@Id
	@GeneratedValue
	@Column(name = "StoreID")
	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	@Column(name = "WeiID")
	public Long getWeiId() {
		return weiId;
	}

	public void setWeiId(Long weiId) {
		this.weiId = weiId;
	}

	@Column(name = "ThingID")
	public Long getThingId() {
		return this.thingId;
	}

	public void setThingId(Long thingId) {
		this.thingId = thingId;
	}

	@Column(name = "ThingType")
	public Short getThingType() {
		return this.thingType;
	}

	public void setThingType(Short thingType) {
		this.thingType = thingType;
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

	@Column(name = "SellerWeiID")
	public Long getSellerweiId() {
		return sellerweiId;
	}

	public void setSellerweiId(Long sellerweiId) {
		this.sellerweiId = sellerweiId;
	}

}