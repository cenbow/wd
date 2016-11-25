package com.okwei.bean.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * UAdvisorFellowMsg entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "U_AdvisorFellowMsg")
public class UAdvisorFellowMsg extends BaseEntity {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer aid;
	private Long weiId;
	private Short type;
	private Long operateMan;
	private String operateContent;
	private Date createTime;

	// Constructors

	/** default constructor */
	public UAdvisorFellowMsg() {
	}

	/** full constructor */
	public UAdvisorFellowMsg(Long weiId, Short type, Long operateMan, String operateContent, Date createTime) {
		this.weiId = weiId;
		this.type = type;
		this.operateMan = operateMan;
		this.operateContent = operateContent;
		this.createTime = createTime;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "AID", unique = true, nullable = false)
	public Integer getAid() {
		return this.aid;
	}

	public void setAid(Integer aid) {
		this.aid = aid;
	}

	@Column(name = "WeiID")
	public Long getWeiId() {
		return this.weiId;
	}

	public void setWeiId(Long weiId) {
		this.weiId = weiId;
	}

	@Column(name = "Type")
	public Short getType() {
		return this.type;
	}

	public void setType(Short type) {
		this.type = type;
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