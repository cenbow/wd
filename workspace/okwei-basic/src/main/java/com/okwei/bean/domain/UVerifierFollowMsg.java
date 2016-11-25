package com.okwei.bean.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * UVerifierFollowMsg entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "U_VerifierFollowMsg")
public class UVerifierFollowMsg extends BaseEntity {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long vid;
	private Long weiId;
	private Short recordType;
	private Long operateMan;
	private String operateContent;
	private Date createTime;

	// Constructors

	/** default constructor */
	public UVerifierFollowMsg() {
	}

	/** full constructor */
	public UVerifierFollowMsg(Long weiId, Short recordType, Long operateMan, String operateContent, Date createTime) {
		this.weiId = weiId;
		this.recordType = recordType;
		this.operateMan = operateMan;
		this.operateContent = operateContent;
		this.createTime = createTime;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "VID", unique = true, nullable = false)
	public Long getVid() {
		return this.vid;
	}

	public void setVid(Long vid) {
		this.vid = vid;
	}

	@Column(name = "WeiID")
	public Long getWeiId() {
		return this.weiId;
	}

	public void setWeiId(Long weiId) {
		this.weiId = weiId;
	}

	@Column(name = "RecordType")
	public Short getRecordType() {
		return this.recordType;
	}

	public void setRecordType(Short recordType) {
		this.recordType = recordType;
	}

	@Column(name = "OperateMan")
	public Long getOperateMan() {
		return this.operateMan;
	}

	public void setOperateMan(Long operateMan) {
		this.operateMan = operateMan;
	}

	@Column(name = "OperateContent", length = 500)
	public String getOperateContent() {
		return this.operateContent;
	}

	public void setOperateContent(String operateContent) {
		this.operateContent = operateContent;
	}

	@Column(name = "CreateTime", length = 0)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}