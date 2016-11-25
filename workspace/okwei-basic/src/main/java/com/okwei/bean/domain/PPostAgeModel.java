package com.okwei.bean.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * PPostAgeModel entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "P_PostAgeModel")
public class PPostAgeModel extends BaseEntity {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer freightId;
	private String postAgeName;
	private Long supplierWeiId;
	private Short billingType;
	private Short deliverytime;
	private Double free;
	private String deliveryAddress;
	private Short status;
	private Date createTime;
	private Date upDateTime;
	private String remark;
	private Integer province;
	private Integer city;
	private Integer district;

	// Constructors

	/** default constructor */
	public PPostAgeModel() {
	}

	/** full constructor */
	public PPostAgeModel(Long supplierWeiId, Short billingType, Short deliverytime, Double free, String deliveryAddress, Short status, Date createTime,
			Date upDateTime, String remark) {
		this.supplierWeiId = supplierWeiId;
		this.billingType = billingType;
		this.deliverytime = deliverytime;
		this.free = free;
		this.deliveryAddress = deliveryAddress;
		this.status = status;
		this.createTime = createTime;
		this.upDateTime = upDateTime;
		this.remark = remark;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "FreightID", unique = true, nullable = false)
	public Integer getFreightId() {
		return this.freightId;
	}

	public void setFreightId(Integer freightId) {
		this.freightId = freightId;
	}

	@Column(name = "SupplierWeiID")
	public Long getSupplierWeiId() {
		return this.supplierWeiId;
	}

	public void setSupplierWeiId(Long supplierWeiId) {
		this.supplierWeiId = supplierWeiId;
	}

	@Column(name = "BillingType")
	public Short getBillingType() {
		return this.billingType;
	}

	public void setBillingType(Short billingType) {
		this.billingType = billingType;
	}

	@Column(name = "Deliverytime")
	public Short getDeliverytime() {
		return this.deliverytime;
	}

	public void setDeliverytime(Short deliverytime) {
		this.deliverytime = deliverytime;
	}

	@Column(name = "Free", precision = 18)
	public Double getFree() {
		if (this.free == null)
			return 0d;
		return this.free;
	}

	public void setFree(Double free) {
		this.free = free;
	}

	@Column(name = "DeliveryAddress", length = 512)
	public String getDeliveryAddress() {
		return this.deliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	@Column(name = "Status")
	public Short getStatus() {
		return this.status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	@Column(name = "CreateTime", length = 0)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "UpDateTime", length = 0)
	public Date getUpDateTime() {
		return this.upDateTime;
	}

	public void setUpDateTime(Date upDateTime) {
		this.upDateTime = upDateTime;
	}

	@Column(name = "Remark", length = 512)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "PostAgeName", length = 512)
	public String getPostAgeName() {
		return postAgeName;
	}

	public void setPostAgeName(String postAgeName) {
		this.postAgeName = postAgeName;
	}

	@Column(name = "Province")
	public Integer getProvince() {
		return province;
	}

	public void setProvince(Integer province) {
		this.province = province;
	}

	@Column(name = "City")
	public Integer getCity() {
		return city;
	}

	public void setCity(Integer city) {
		this.city = city;
	}

	@Column(name = "District")
	public Integer getDistrict() {
		return district;
	}

	public void setDistrict(Integer district) {
		this.district = district;
	}

	
}