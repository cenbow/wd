package com.okwei.bean.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TUpdateTimestap entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_UpdateTimestap")
public class TUpdateTimestap implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 6114294650160206551L;
	private Integer id;
	private String name;
	private Date time;

	// Constructors

	/** default constructor */
	public TUpdateTimestap() {
	}

	/** full constructor */
	public TUpdateTimestap(String name, Date time) {
		this.name = name;
		this.time = time;
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

	@Column(name = "name", length = 32)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "time", length = 19)
	public Date getTime() {
		return this.time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

}