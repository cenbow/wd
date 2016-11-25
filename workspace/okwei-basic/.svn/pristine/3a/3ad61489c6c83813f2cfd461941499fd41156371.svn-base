package com.okwei.bean.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TRegional entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_Regional")
public class TRegional extends BaseEntity implements Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer code;
	private String name;
	private Integer parent;
	private Short level;

	// Constructors

	/** default constructor */
	public TRegional() {
	}

	/** full constructor */
	public TRegional(String name, Integer parent, Short level) {
		this.name = name;
		this.parent = parent;
		this.level = level;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Code", unique = true, nullable = false)
	public Integer getCode() {
		return this.code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	@Column(name = "Name", length = 64)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "Parent")
	public Integer getParent() {
		return this.parent;
	}

	public void setParent(Integer parent) {
		this.parent = parent;
	}

	@Column(name = "Level")
	public Short getLevel() {
		return this.level;
	}

	public void setLevel(Short level) {
		this.level = level;
	}

}