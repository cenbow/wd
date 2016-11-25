package com.okwei.bean.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * URepayRule entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "U_RepayRule")
public class URepayRule implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3095095497147987072L;
	// Fields

	private Integer id;
	private Long minPrice;
	private Integer percent;

	// Constructors

	/** default constructor */
	public URepayRule() {
	}

	/** full constructor */
	public URepayRule(Long minPrice, Integer percent) {
		this.minPrice = minPrice;
		this.percent = percent;
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

	@Column(name = "MinPrice", precision = 10, scale = 0)
	public Long getMinPrice() {
		return this.minPrice;
	}

	public void setMinPrice(Long minPrice) {
		this.minPrice = minPrice;
	}

	@Column(name = "Percent")
	public Integer getPercent() {
		return this.percent;
	}

	public void setPercent(Integer percent) {
		this.percent = percent;
	}

}