package com.okwei.bean.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TWeiFans entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_WeiFans")
public class TWeiFans implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5143624902289760608L;
	// Fields

	private Long id;
	private Long weiId;
	private Long fanWeiId;
	private Short flag;

	// Constructors

	/** default constructor */
	public TWeiFans() {
	}

	
	@Id
	@GeneratedValue
	@Column(name = "ID", nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "WeiID", nullable = false)
	public Long getWeiId() {
		return this.weiId;
	}

	public void setWeiId(Long weiId) {
		this.weiId = weiId;
	}

	@Column(name = "FanWeiID")
	public Long getFanWeiId() {
		return this.fanWeiId;
	}

	public void setFanWeiId(Long fanWeiId) {
		this.fanWeiId = fanWeiId;
	}

	@Column(name = "Flag")
	public Short getFlag() {
		return this.flag;
	}

	public void setFlag(Short flag) {
		this.flag = flag;
	}

}