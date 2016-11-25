package com.okwei.bean.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * PParamModel entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "P_ParamModel")
public class PParamModel extends BaseEntity {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer mid;
	private Long supplierWeiId;
	private Integer classId;
	private String mname;
	private Short state;
	private Date createTime;

	// Constructors

	/** default constructor */
	public PParamModel() {
	}

	/** full constructor */
	public PParamModel(Long supplierWeiId, Integer classId, String mname, Short state, Date createTime) {
		this.supplierWeiId = supplierWeiId;
		this.classId = classId;
		this.mname = mname;
		this.state = state;
		this.createTime = createTime;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "MID", unique = true, nullable = false)
	public Integer getMid() {
		return this.mid;
	}

	public void setMid(Integer mid) {
		this.mid = mid;
	}

	@Column(name = "SupplierWeiID")
	public Long getSupplierWeiId() {
		return this.supplierWeiId;
	}

	public void setSupplierWeiId(Long supplierWeiId) {
		this.supplierWeiId = supplierWeiId;
	}

	@Column(name = "ClassID")
	public Integer getClassId() {
		return this.classId;
	}

	public void setClassId(Integer classId) {
		this.classId = classId;
	}

	@Column(name = "MName", length = 32)
	public String getMname() {
		return this.mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
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

}