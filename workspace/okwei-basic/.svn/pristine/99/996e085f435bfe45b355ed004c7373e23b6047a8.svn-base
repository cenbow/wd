package com.okwei.bean.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * UChildrenCreate entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "U_ChildrenCreate")
public class UChildrenCreate implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6423643836219990546L;
	// Fields

	private Long parentId;
	private Integer createId;

	// Constructors

	/** default constructor */
	public UChildrenCreate() {
	}

	/** minimal constructor */
	public UChildrenCreate(Long parentId) {
		this.parentId = parentId;
	}

	/** full constructor */
	public UChildrenCreate(Long parentId, Integer createId) {
		this.parentId = parentId;
		this.createId = createId;
	}

	// Property accessors
	@Id
	@Column(name = "ParentID", unique = true, nullable = false)
	public Long getParentId() {
		return this.parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	@Column(name = "CreateID")
	public Integer getCreateId() {
		return this.createId;
	}

	public void setCreateId(Integer createId) {
		this.createId = createId;
	}

}