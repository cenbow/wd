package com.okwei.bean.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * BFilterWords entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "B_FilterWords")
public class BFilterWords implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -3575661325268192687L;
	private Integer id;
	private String filterWords;
	private Short flag;

	// Constructors

	/** default constructor */
	public BFilterWords() {
	}

	/** full constructor */
	public BFilterWords(String filterWords, Short flag) {
		this.filterWords = filterWords;
		this.flag = flag;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "ID", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "FilterWords")
	public String getFilterWords() {
		return this.filterWords;
	}

	public void setFilterWords(String filterWords) {
		this.filterWords = filterWords;
	}

	@Column(name = "Flag")
	public Short getFlag() {
		return this.flag;
	}

	public void setFlag(Short flag) {
		this.flag = flag;
	}

}