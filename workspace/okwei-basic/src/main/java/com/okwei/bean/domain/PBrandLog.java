package com.okwei.bean.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * PBrandLog entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "P_BrandLog")
public class PBrandLog implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8212534088656323483L;
	// Fields

	private Integer logId;
	private Integer brandId;
	private Long verWeiId;
	private String verName;
	private Short resultStatus;
	private String reason;
	private Date createTime;

	// Constructors

	/** default constructor */
	public PBrandLog() {
	}

	/** full constructor */
	public PBrandLog(Long verWeiId, String verName, Short resultStatus,
			String reason, Date createTime) {
		this.verWeiId = verWeiId;
		this.verName = verName;
		this.resultStatus = resultStatus;
		this.reason = reason;
		this.createTime = createTime;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "LogID", unique = true, nullable = false)
	public Integer getLogId() {
		return logId;
	}

	public void setLogId(Integer logId) {
		this.logId = logId;
	}
	
	
	
	@Column(name = "BrandID")
	public Integer getBrandId() {
		return this.brandId;
	}

	

	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}

	@Column(name = "VerWeiID")
	public Long getVerWeiId() {
		return this.verWeiId;
	}

	public void setVerWeiId(Long verWeiId) {
		this.verWeiId = verWeiId;
	}

	@Column(name = "VerName", length = 64)
	public String getVerName() {
		return this.verName;
	}

	public void setVerName(String verName) {
		this.verName = verName;
	}

	@Column(name = "ResultStatus")
	public Short getResultStatus() {
		return this.resultStatus;
	}

	public void setResultStatus(Short resultStatus) {
		this.resultStatus = resultStatus;
	}

	@Column(name = "Reason", length = 128)
	public String getReason() {
		return this.reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@Column(name = "CreateTime", length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}