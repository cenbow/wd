package com.okwei.bean.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TClassBrand entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_ClassBrand")
public class TClassBrand implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3080873413988794479L;
	// Fields

	private Integer id;
	private Integer classId;
	private Integer brandId;
	private String brandName;

	// Constructors

	/** default constructor */
	public TClassBrand() {
	}

	/** full constructor */
	public TClassBrand(Integer classId, Integer brandId, String brandName) {
		this.classId = classId;
		this.brandId = brandId;
		this.brandName = brandName;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "ClassID")
	public Integer getClassId() {
		return this.classId;
	}

	public void setClassId(Integer classId) {
		this.classId = classId;
	}

	@Column(name = "BrandID")
	public Integer getBrandId() {
		return this.brandId;
	}

	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}

	@Column(name = "BrandName", length = 64)
	public String getBrandName() {
		return this.brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

}