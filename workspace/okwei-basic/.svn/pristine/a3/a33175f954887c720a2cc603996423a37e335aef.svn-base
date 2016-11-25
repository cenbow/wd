package com.okwei.bean.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TFansNums entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_FansNums")
public class TFansNums implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4485170112667841481L;
	// Fields

	private Long id;
	private Long weiId;
	private Integer nums;

	// Constructors

	/** default constructor */
	public TFansNums() {
	}

	/** full constructor */
	public TFansNums(Long weiId, Integer nums) {
		this.weiId = weiId;
		this.nums = nums;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "WeiID")
	public Long getWeiId() {
		return this.weiId;
	}

	public void setWeiId(Long weiId) {
		this.weiId = weiId;
	}

	@Column(name = "Nums")
	public Integer getNums() {
		return this.nums;
	}

	public void setNums(Integer nums) {
		this.nums = nums;
	}

}