package com.okwei.bean.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * UFeedbackInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "U_FeedbackInfo")
public class UFeedbackInfo extends BaseEntity {

	// Fields

	private Long id;
	private Long weiId;
	private String content;
	private String contactWay;
	private Short terminateType;
	private Date createTime;

	// Constructors

	/** default constructor */
	public UFeedbackInfo() {
	}

	@Id
	@GeneratedValue
	@Column(name = "ID")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "WeiID")
	public Long getWeiId() {
		return weiId;
	}

	public void setWeiId(Long weiId) {
		this.weiId = weiId;
	}

	@Column(name = "Content", length = 512)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "ContactWay", length = 32)
	public String getContactWay() {
		return this.contactWay;
	}

	public void setContactWay(String contactWay) {
		this.contactWay = contactWay;
	}

	@Column(name = "TerminateType")
	public Short getTerminateType() {
		return this.terminateType;
	}

	public void setTerminateType(Short terminateType) {
		this.terminateType = terminateType;
	}

	@Column(name = "CreateTime", length = 0)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}