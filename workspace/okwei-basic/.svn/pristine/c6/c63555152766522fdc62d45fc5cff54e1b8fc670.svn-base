package com.okwei.bean.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TTasteSummer entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_TasteSummer")
public class TTasteSummer implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5714860760899175726L;
	// Fields

	private Integer id;
	private Long weiId;
	private Short tasteType;

	// Constructors

	/** default constructor */
	public TTasteSummer() {
	}

	/** full constructor */
	public TTasteSummer(Long weiId, Short tasteType) {
		this.weiId = weiId;
		this.tasteType = tasteType;
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

	@Column(name = "WeiID")
	public Long getWeiId() {
		return this.weiId;
	}

	public void setWeiId(Long weiId) {
		this.weiId = weiId;
	}

	@Column(name = "TasteType")
	public Short getTasteType() {
		return this.tasteType;
	}

	public void setTasteType(Short tasteType) {
		this.tasteType = tasteType;
	}

}