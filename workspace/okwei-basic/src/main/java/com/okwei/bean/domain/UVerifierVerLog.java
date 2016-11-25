package com.okwei.bean.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * UVerifierVerLog entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "U_VerifierVerLog")
public class UVerifierVerLog implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -4861778616414755139L;
	private Integer verLogId;
	private Long verWeiId;
	private Long operaterId;
	private String operateName;
	private String operateContent;
	private Date createTime;
    private Short verStatus;
	// Constructors

	/** default constructor */
	public UVerifierVerLog() {
	}

	/** full constructor */
	public UVerifierVerLog(Long verWeiId, Long operaterId, String operateName,
			String operateContent, Date createTime) {
		this.verWeiId = verWeiId;
		this.operaterId = operaterId;
		this.operateName = operateName;
		this.operateContent = operateContent;
		this.createTime = createTime;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "VerLogID", unique = true, nullable = false)
	public Integer getVerLogId() {
		return this.verLogId;
	}

	public void setVerLogId(Integer verLogId) {
		this.verLogId = verLogId;
	}

	@Column(name = "VerWeiID")
	public Long getVerWeiId() {
		return this.verWeiId;
	}

	public void setVerWeiId(Long verWeiId) {
		this.verWeiId = verWeiId;
	}

	@Column(name = "OperaterID")
	public Long getOperaterId() {
		return this.operaterId;
	}

	public void setOperaterId(Long operaterId) {
		this.operaterId = operaterId;
	}

	@Column(name = "OperateName", length = 32)
	public String getOperateName() {
		return this.operateName;
	}

	public void setOperateName(String operateName) {
		this.operateName = operateName;
	}

	@Column(name = "OperateContent", length = 128)
	public String getOperateContent() {
		return this.operateContent;
	}

	public void setOperateContent(String operateContent) {
		this.operateContent = operateContent;
	}

	@Column(name = "CreateTime", length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Short getVerStatus() {
		return verStatus;
	}

	public void setVerStatus(Short verStatus) {
		this.verStatus = verStatus;
	}

}