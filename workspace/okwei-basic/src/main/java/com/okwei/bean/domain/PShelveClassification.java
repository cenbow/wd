package com.okwei.bean.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * PShelveClassification entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "P_ShelveClassification")
public class PShelveClassification extends BaseEntity {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long classId;
	private Long weiIdsort;
	private Long weiId;
	private String name;
	private Short sort;
	private Short state;
	private Date createTime;

	// Constructors

	/** default constructor */

	/** full constructor */

	@Column(name = "WeiID")
	public Long getWeiId() {
		return this.weiId;
	}

	public void setWeiId(Long weiId) {
		this.weiId = weiId;
	}

	@Column(name = "Name", length = 64)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "Sort")
	public Short getSort() {
		return this.sort;
	}

	public void setSort(Short sort) {
		this.sort = sort;
	}

	@Column(name = "State")
	public Short getState() {
		return this.state;
	}

	public void setState(Short state) {
		this.state = state;
	}

	@Column(name = "CreateTime", length = 0)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Id
	@GeneratedValue
	@Column(name = "ClassID")
	public Long getClassId() {
		return classId;
	}

	public void setClassId(Long classId) {
		this.classId = classId;
	}

	@Column(name = "WeiIDSort")
	public Long getWeiIdsort() {
		return weiIdsort;
	}

	public void setWeiIdsort(Long weiIdsort) {
		this.weiIdsort = weiIdsort;
	}

}