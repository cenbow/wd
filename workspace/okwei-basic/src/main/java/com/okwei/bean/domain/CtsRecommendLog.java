package com.okwei.bean.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CtsRecommendLog entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CTS_RecommendLog")
public class CtsRecommendLog implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3375283340208492304L;
	// Fields

	private Long logId;
	private Long opWeiId;
	private String opName;
	private String content;
	private Short ctype;
	private Date createTime;

	// Constructors

	/** default constructor */
	public CtsRecommendLog() {
	}

	/** full constructor */
	public CtsRecommendLog(Long opWeiId, String opName, String content,
			Short ctype, Date createTime) {
		this.opWeiId = opWeiId;
		this.opName = opName;
		this.content = content;
		this.ctype = ctype;
		this.createTime = createTime;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "LogID", unique = true, nullable = false)
	public Long getLogId() {
		return this.logId;
	}

	public void setLogId(Long logId) {
		this.logId = logId;
	}

	@Column(name = "OpWeiID")
	public Long getOpWeiId() {
		return this.opWeiId;
	}

	public void setOpWeiId(Long opWeiId) {
		this.opWeiId = opWeiId;
	}

	@Column(name = "OpName", length = 16)
	public String getOpName() {
		return this.opName;
	}

	public void setOpName(String opName) {
		this.opName = opName;
	}

	@Column(name = "Content", length = 512)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "CType")
	public Short getCtype() {
		return this.ctype;
	}

	public void setCtype(Short ctype) {
		this.ctype = ctype;
	}

	@Column(name = "CreateTime", length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}