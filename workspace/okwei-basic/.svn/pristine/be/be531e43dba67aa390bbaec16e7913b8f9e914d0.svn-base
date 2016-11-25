package com.okwei.bean.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * DCastellans entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "D_Castellans")
public class DCastellans implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7217745808722297686L;
	// Fields

	private Long agentId;
	private Long weiId;
	private Integer brandId;
	private String contact;
	private String contactPhone;
	private Integer province;
	private Integer city;
	private Integer district;
	private Integer outOrIn;
	private Integer porN;
	private Date createTime;
	private Integer status;

	// Constructors

	/** default constructor */
	public DCastellans() {
	}

	/** minimal constructor */
	public DCastellans(Long agentId) {
		this.agentId = agentId;
	}

	/** full constructor */
	public DCastellans(Long agentId, Long weiId, Integer brandId,String contact,
			String contactPhone, Integer province, Integer city,
			Integer district, Integer outOrIn, Integer porN,
			Date createTime, Integer status) {
		this.agentId = agentId;
		this.weiId = weiId;
		this.contact = contact;
		this.contactPhone = contactPhone;
		this.province = province;
		this.brandId = brandId;
		this.city = city;
		this.district = district;
		this.outOrIn = outOrIn;
		this.porN = porN;
		this.createTime = createTime;
		this.status = status;
	}

	// Property accessors
	@Id
	@Column(name = "AgentID", unique = true, nullable = false)
	public Long getAgentId() {
		return this.agentId;
	}

	public void setAgentId(Long agentId) {
		this.agentId = agentId;
	}

	@Column(name = "WeiID")
	public Long getWeiId() {
		return this.weiId;
	}

	public void setWeiId(Long weiId) {
		this.weiId = weiId;
	}
	
	@Column(name = "BrandID")
	public Integer getBrandId() {
		return this.brandId;
	}

	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}

	@Column(name = "Contact", length = 64)
	public String getContact() {
		return this.contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	@Column(name = "ContactPhone", length = 64)
	public String getContactPhone() {
		return this.contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	@Column(name = "Province")
	public Integer getProvince() {
		return this.province;
	}

	public void setProvince(Integer province) {
		this.province = province;
	}

	@Column(name = "City")
	public Integer getCity() {
		return this.city;
	}

	public void setCity(Integer city) {
		this.city = city;
	}

	@Column(name = "District")
	public Integer getDistrict() {
		return this.district;
	}

	public void setDistrict(Integer district) {
		this.district = district;
	}

	@Column(name = "OutOrIn")
	public Integer getOutOrIn() {
		return this.outOrIn;
	}

	public void setOutOrIn(Integer outOrIn) {
		this.outOrIn = outOrIn;
	}

	@Column(name = "POrN")
	public Integer getPorN() {
		return this.porN;
	}

	public void setPorN(Integer porN) {
		this.porN = porN;
	}

	@Column(name = "CreateTime", length = 0)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "Status")
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}