package com.okwei.bean.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TLevelWeiId entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_LevelWeiID")
public class TLevelWeiId implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5430414887261435648L;
	// Fields

	private Long weiId;
	private Long parentId;
	private Integer level;
	private Date createTime;

	// Constructors

	/** default constructor */
	public TLevelWeiId() {
	}

	/** minimal constructor */
	public TLevelWeiId(Long weiId) {
		this.weiId = weiId;
	}

	/** full constructor */
	public TLevelWeiId(Long weiId, Long parentId, Integer level,
			Date createTime) {
		this.weiId = weiId;
		this.parentId = parentId;
		this.level = level;
		this.createTime = createTime;
	}

	// Property accessors
	@Id
	@Column(name = "WeiID", unique = true, nullable = false)
	public Long getWeiId() {
		return this.weiId;
	}

	public void setWeiId(Long weiId) {
		this.weiId = weiId;
	}

	@Column(name = "ParentID")
	public Long getParentId() {
		return this.parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	@Column(name = "Level")
	public Integer getLevel() {
		return this.level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	@Column(name = "CreateTime", length = 0)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}