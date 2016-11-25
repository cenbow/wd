package com.okwei.bean.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * UYunSupplierVerifier entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "U_YunSupplier_Verifier")
public class UYunSupplierVerifier extends BaseEntity {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long yvid;
	private Long supplierWeiId;
	private Long verifierWeiId;
	private String followStatus;
	private Long supperCensor;
	private Date censorTime;
	private String censorResult;
	private Date createTime;
	private Date updateTime;
	private Long updateMan;

	// Constructors

	/** default constructor */
	public UYunSupplierVerifier() {
	}

	/** full constructor */
	public UYunSupplierVerifier(Long supplierWeiId, Long verifierWeiId, String followStatus, Long supperCensor, Date censorTime, String censorResult,
			Date createTime, Date updateTime, Long updateMan) {
		this.supplierWeiId = supplierWeiId;
		this.verifierWeiId = verifierWeiId;
		this.followStatus = followStatus;
		this.supperCensor = supperCensor;
		this.censorTime = censorTime;
		this.censorResult = censorResult;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.updateMan = updateMan;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "YVID", unique = true, nullable = false)
	public Long getYvid() {
		return this.yvid;
	}

	public void setYvid(Long yvid) {
		this.yvid = yvid;
	}

	@Column(name = "SupplierWeiID")
	public Long getSupplierWeiId() {
		return this.supplierWeiId;
	}

	public void setSupplierWeiId(Long supplierWeiId) {
		this.supplierWeiId = supplierWeiId;
	}

	@Column(name = "VerifierWeiID")
	public Long getVerifierWeiId() {
		return this.verifierWeiId;
	}

	public void setVerifierWeiId(Long verifierWeiId) {
		this.verifierWeiId = verifierWeiId;
	}

	@Column(name = "FollowStatus", length = 32)
	public String getFollowStatus() {
		return this.followStatus;
	}

	public void setFollowStatus(String followStatus) {
		this.followStatus = followStatus;
	}

	@Column(name = "SupperCensor")
	public Long getSupperCensor() {
		return this.supperCensor;
	}

	public void setSupperCensor(Long supperCensor) {
		this.supperCensor = supperCensor;
	}

	@Column(name = "CensorTime", length = 0)
	public Date getCensorTime() {
		return this.censorTime;
	}

	public void setCensorTime(Date censorTime) {
		this.censorTime = censorTime;
	}

	@Column(name = "CensorResult", length = 32)
	public String getCensorResult() {
		return this.censorResult;
	}

	public void setCensorResult(String censorResult) {
		this.censorResult = censorResult;
	}

	@Column(name = "CreateTime", length = 0)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "UpdateTime", length = 0)
	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name = "UpdateMan")
	public Long getUpdateMan() {
		return this.updateMan;
	}

	public void setUpdateMan(Long updateMan) {
		this.updateMan = updateMan;
	}

}