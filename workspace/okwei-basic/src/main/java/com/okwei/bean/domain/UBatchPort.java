package com.okwei.bean.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * UBatchPort entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "U_BatchPort")
public class UBatchPort implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2644756757962712577L;
	// Fields

	private Long weiId;
	private Integer bmid;
	private Long UBWeiId;
	private Long UBReceiver;
	private Short type;
	private Long supperVerifier;
	private Long supperReceiver;
	private Date createTime;
	private Date verTime;
	private Short inType;
	private Date inTime;
	private Short status;
	private Double bond;
	private Long companyWeiId;

	// Constructors

	/** default constructor */
	public UBatchPort() {
	}

	/** full constructor */
	public UBatchPort(Integer bmid, Long UBWeiId, Long UBReceiver, Short type,
			Long supperVerifier, Long supperReceiver, Date createTime,
			Date verTime, Short inType, Date inTime, Short status,
			Double bond, Long companyWeiId) {
		this.bmid = bmid;
		this.UBWeiId = UBWeiId;
		this.UBReceiver = UBReceiver;
		this.type = type;
		this.supperVerifier = supperVerifier;
		this.supperReceiver = supperReceiver;
		this.createTime = createTime;
		this.verTime = verTime;
		this.inType = inType;
		this.inTime = inTime;
		this.status = status;
		this.bond = bond;
		this.companyWeiId = companyWeiId;
	}

	// Property accessors
	@Id
	@Column(name = "WeiID", unique = true, nullable = false)
	public Long getWeiId() {
		return this.weiId;
	}

	public void setWeiId(Long weiId) {
		this.weiId = weiId;
	}

	@Column(name = "BMID")
	public Integer getBmid() {
		return this.bmid;
	}

	public void setBmid(Integer bmid) {
		this.bmid = bmid;
	}

	@Column(name = "U_B_WeiID")
	public Long getUBWeiId() {
		return this.UBWeiId;
	}

	public void setUBWeiId(Long UBWeiId) {
		this.UBWeiId = UBWeiId;
	}

	@Column(name = "U_B_Receiver")
	public Long getUBReceiver() {
		return this.UBReceiver;
	}

	public void setUBReceiver(Long UBReceiver) {
		this.UBReceiver = UBReceiver;
	}

	@Column(name = "Type")
	public Short getType() {
		return this.type;
	}

	public void setType(Short type) {
		this.type = type;
	}

	@Column(name = "SupperVerifier")
	public Long getSupperVerifier() {
		return this.supperVerifier;
	}

	public void setSupperVerifier(Long supperVerifier) {
		this.supperVerifier = supperVerifier;
	}

	@Column(name = "Supper_Receiver")
	public Long getSupperReceiver() {
		return this.supperReceiver;
	}

	public void setSupperReceiver(Long supperReceiver) {
		this.supperReceiver = supperReceiver;
	}

	@Column(name = "CreateTime", length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "VerTime", length = 19)
	public Date getVerTime() {
		return this.verTime;
	}

	public void setVerTime(Date verTime) {
		this.verTime = verTime;
	}

	@Column(name = "InType")
	public Short getInType() {
		return this.inType;
	}

	public void setInType(Short inType) {
		this.inType = inType;
	}

	@Column(name = "InTime", length = 19)
	public Date getInTime() {
		return this.inTime;
	}

	public void setInTime(Date inTime) {
		this.inTime = inTime;
	}

	@Column(name = "Status")
	public Short getStatus() {
		return this.status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	@Column(name = "Bond", precision = 18)
	public Double getBond() {
		return this.bond;
	}

	public void setBond(Double bond) {
		this.bond = bond;
	}

	@Column(name = "CompanyWeiID")
	public Long getCompanyWeiId() {
		return this.companyWeiId;
	}

	public void setCompanyWeiId(Long companyWeiId) {
		this.companyWeiId = companyWeiId;
	}

}