package com.okwei.bean.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * UPlatformSupplyerLog entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "U_PlatformSupplyerLog")
public class UPlatformSupplyerLog implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1958251115558089981L;
	// Fields

	private Integer id;
	private Long weiId;
	private String operatorName;
	private Short resultStatus;
	private String resultContent;
	private Date createTime;
	private Short type;

	// Constructors

	/** default constructor */
	public UPlatformSupplyerLog() {
	}

	/** full constructor */
	public UPlatformSupplyerLog(Long weiId, String operatorName,
			Short resultStatus, String resultContent, Date createTime,
			Short type) {
		this.weiId = weiId;
		this.operatorName = operatorName;
		this.resultStatus = resultStatus;
		this.resultContent = resultContent;
		this.createTime = createTime;
		this.type = type;
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

	@Column(name = "CreateTime", length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "Type")
	public Short getType() {
		return this.type;
	}

	public void setType(Short type) {
		this.type = type;
	}

}