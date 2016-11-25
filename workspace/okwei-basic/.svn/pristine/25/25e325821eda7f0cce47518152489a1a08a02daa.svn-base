package com.okwei.bean.domain;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TBussinessClass entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_BussinessClass")
public class TBussinessClass extends BaseEntity {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -728813016561078740L;
	private Integer id;
	private Integer productClass;
	private String name;

	// Constructors

	/** default constructor */
	public TBussinessClass() {
	}

	/** full constructor */
	public TBussinessClass(Integer productClass, String name) {
		this.productClass = productClass;
		this.name = name;
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

	@Column(name = "ProductClass")
	public Integer getProductClass() {
		return this.productClass;
	}

	public void setProductClass(Integer productClass) {
		this.productClass = productClass;
	}

	@Column(name = "Name", length = 64)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}