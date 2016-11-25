package com.okwei.bean.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * USupplierFollowMsg entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "U_SupplierFollowMsg")
public class USupplierFollowMsg extends BaseEntity {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer followId;
	private Long weiId;
	private Integer recordType;
	private Long operaterId;
	private String content;
	private Date createTime;

	// Constructors

	/** default constructor */
	public USupplierFollowMsg() {
	}

	/** full constructor */
	public USupplierFollowMsg(Long weiId, Integer recordType, Long operaterId, String content, Date createTime) {
		this.weiId = weiId;
		this.recordType = recordType;
		this.operaterId = operaterId;
		this.content = content;
		this.createTime = createTime;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "FollowID", unique = true, nullable = false)
	public Integer getFollowId() {
		return this.followId;
	}

	public void setFollowId(Integer followId) {
		this.followId = followId;
	}

	@Column(name = "WeiID")
	public Long getWeiId() {
		return this.weiId;
	}

	public void setWeiId(Long weiId) {
		this.weiId = weiId;
	}

	@Column(name = "RecordType")
	public Integer getRecordType() {
		return this.recordType;
	}

	public void setRecordType(Integer recordType) {
		this.recordType = recordType;
	}

	@Column(name = "OperaterID")
	public Long getOperaterId() {
		return this.operaterId;
	}

	public void setOperaterId(Long operaterId) {
		this.operaterId = operaterId;
	}

	@Column(name = "Content", length = 256)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "CreateTime", length = 0)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}