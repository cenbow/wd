package com.okwei.bean.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * UChildrenStaff entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "U_ChildrenStaff")
public class UChildrenStaff implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5102767716680389691L;
	// Fields

	private String childrenId;
	private String department;

	// Constructors

	/** default constructor */
	public UChildrenStaff() {
	}

	/** minimal constructor */
	public UChildrenStaff(String childrenId) {
		this.childrenId = childrenId;
	}

	/** full constructor */
	public UChildrenStaff(String childrenId, String department) {
		this.childrenId = childrenId;
		this.department = department;
	}

	// Property accessors
	@Id
	@Column(name = "ChildrenID", unique = true, nullable = false, length = 64)
	public String getChildrenId() {
		return this.childrenId;
	}

	public void setChildrenId(String childrenId) {
		this.childrenId = childrenId;
	}

	@Column(name = "Department", length = 64)
	public String getDepartment() {
		return this.department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

}