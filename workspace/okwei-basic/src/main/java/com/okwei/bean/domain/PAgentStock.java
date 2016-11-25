package com.okwei.bean.domain;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * AdminUser entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "P_AgentStock")
public class PAgentStock implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1641695063185064141L;
	private Integer id;
	private Long weiId;
	private Long productId;
	private Integer stockCount;
	private Integer selledCount;

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "weiId")
	public Long getWeiId() {
		return weiId;
	}

	public void setWeiId(Long weiId) {
		this.weiId = weiId;
	}

	@Column(name = "productId")
	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	@Column(name = "stockCount")
	public Integer getStockCount() {
		return stockCount;
	}

	public void setStockCount(Integer stockCount) {
		this.stockCount = stockCount;
	}

	@Column(name = "selledCount")
	public Integer getSelledCount() {
		return selledCount;
	}

	public void setSelledCount(Integer selledCount) {
		this.selledCount = selledCount;
	}

}