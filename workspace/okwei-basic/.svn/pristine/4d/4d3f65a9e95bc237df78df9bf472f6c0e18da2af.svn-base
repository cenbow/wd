package com.okwei.bean.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * URealNameLogId entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "U_RealNameLog")
public class URealNameLog extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9581493133358L;
	// Fields

	private Long verLogId;
	private Long verId;
	private Long verWeiId;
	private String verName;
	private Short resultStatus;
	private String reason;
	private Date createTime;
	private String resultContent;
	private Long realWeiId;

	// Constructors

	/** default constructor */
	public URealNameLog() {
	}

	


	// Property accessors

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "VerLogID", unique = true, nullable = false)
	public Long getVerLogId() {
		return this.verLogId;
	}

	public void setVerLogId(Long verLogId) {
		this.verLogId = verLogId;
	}

	@Column(name = "VerID")
	public Long getVerId() {
		return this.verId;
	}

	public void setVerId(Long verId) {
		this.verId = verId;
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



	@Column(name="ResultContent",length=128)
	public String getResultContent() {
		return resultContent;
	}




	public void setResultContent(String resultContent) {
		this.resultContent = resultContent;
	}



	@Column(name="RealWeiID")
	public Long getRealWeiId() {
		return realWeiId;
	}




	public void setRealWeiId(Long realWeiId) {
		this.realWeiId = realWeiId;
	}

	

}