package com.okwei.bean.domain;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * PShelverCount entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "P_ShelverCount")
public class PShelverCount extends BaseEntity {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Long count;

	// Constructors

	/** default constructor */
	public PShelverCount() {
	}

	/** full constructor */
	public PShelverCount(Long count) {
		this.count = count;
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

	@Column(name = "Count")
	public Long getCount() {
		return this.count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

}