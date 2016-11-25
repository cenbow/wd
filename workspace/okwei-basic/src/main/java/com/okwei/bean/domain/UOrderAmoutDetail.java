package com.okwei.bean.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * UOrderAmoutDetail entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "U_OrderAmoutDetail")
public class UOrderAmoutDetail extends BaseEntity {

	// Fields

	private Integer oadid;
	private Long weiId;
	private Long detailId;
	private String orderNo;
	private Double amout;
	private Double amoutWater;
	private Short type;
	private Date createTime;

	// Constructors

	/** default constructor */
	public UOrderAmoutDetail() {
	}

	@Id
	@GeneratedValue
	@Column(name = "OADID")
	public Integer getOadid() {
		return oadid;
	}

	public void setOadid(Integer oadid) {
		this.oadid = oadid;
	}

	@Column(name = "WeiID")
	public Long getWeiId() {
		return weiId;
	}

	public void setWeiId(Long weiId) {
		this.weiId = weiId;
	}

	@Column(name = "DetailID")
	public Long getDetailId() {
		return this.detailId;
	}

	public void setDetailId(Long detailId) {
		this.detailId = detailId;
	}

	@Column(name = "OrderNo", length = 32)
	public String getOrderNo() {
		return this.orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	@Column(name = "Amout", precision = 18)
	public Double getAmout() {
		if (this.amout == null)
			return 0d;
		return this.amout;
	}

	public void setAmout(Double amout) {
		this.amout = amout;
	}

	@Column(name = "AmoutWater", precision = 18)
	public Double getAmoutWater() {
		if (this.amoutWater == null)
			return 0d;
		return this.amoutWater;
	}

	public void setAmoutWater(Double amoutWater) {
		this.amoutWater = amoutWater;
	}

	@Column(name = "Type")
	public Short getType() {
		return this.type;
	}

	public void setType(Short type) {
		this.type = type;
	}

	@Column(name = "CreateTime", length = 0)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}