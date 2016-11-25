package com.okwei.bean.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TCountStat entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_CountStat")
public class TCountStat implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3396861793646014096L;
	// Fields

	private Integer id;
	private String name;
	private Long countNum;

	// Constructors

	/** default constructor */
	public TCountStat() {
	}

	/** full constructor */
	public TCountStat(String name, Long countNum) {
		this.name = name;
		this.countNum = countNum;
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

	@Column(name = "Name", length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "CountNum")
	public Long getCountNum() {
		return this.countNum;
	}

	public void setCountNum(Long countNum) {
		this.countNum = countNum;
	}

}