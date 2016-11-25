package com.okwei.bean.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * USupplyDemandLog entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "U_SupplyDemandLog")
public class USupplyDemandLog implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5423032722699040094L;
	// Fields

	private Integer id;
	private Long weiId;
	private String operatorName;
	private Short resultStatus;
	private String resultContent;
	private String reason;
	private Date createTime;
	private Long operatorWeiId;
	private Integer demandId;
	// Constructors

	/** default constructor */
	public USupplyDemandLog() {
	}

	/** full constructor */
	public USupplyDemandLog(Long weiId, String operatorName,
			Short resultStatus, String resultContent, String reason,
			Date createTime,Long operatorWeiId,Integer demandId) {
		this.weiId = weiId;
		this.operatorName = operatorName;
		this.resultStatus = resultStatus;
		this.resultContent = resultContent;
		this.reason = reason;
		this.createTime = createTime;
		this.operatorWeiId = operatorWeiId;
		this.demandId = demandId;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "Id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "WeiID")
	public Long getWeiId() {
		return this.weiId;
	}

	public void setWeiId(Long weiId) {
		this.weiId = weiId;
	}

	@Column(name = "OperatorName", length = 32)
	public String getOperatorName() {
		return this.operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	@Column(name = "ResultStatus")
	public Short getResultStatus() {
		return this.resultStatus;
	}

	public void setResultStatus(Short resultStatus) {
		this.resultStatus = resultStatus;
	}

	@Column(name = "ResultContent", length = 256)
	public String getResultContent() {
		return this.resultContent;
	}

	public void setResultContent(String resultContent) {
		this.resultContent = resultContent;
	}

	@Column(name = "Reason", length = 256)
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
	@Column(name = "OperatorWeiId", length = 11)
	public Long getOperatorWeiId() {
		return operatorWeiId;
	}

	public void setOperatorWeiId(Long operatorWeiId) {
		this.operatorWeiId = operatorWeiId;
	}
	
	@Column(name = "DemandId", length = 11)
	public Integer getDemandId() {
		return demandId;
	}

	public void setDemandId(Integer demandId) {
		this.demandId = demandId;
	}
}