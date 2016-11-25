package com.okwei.bean.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ADealsProducts entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "A_DealsProducts")
public class ADealsProducts implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5387009569475075907L;
	// Fields

	private Integer id;
	private Integer dealsId;
	private Long productId;
	private String picture;
	private String description;
	private Integer sort;
	private Integer salesCount;
	private Double salesMoney;

	// Constructors

	/** default constructor */
	public ADealsProducts() {
	}

	/** full constructor */
	public ADealsProducts(Integer dealsId, Long productId, String picture,
			String description, Integer sort, Integer salesCount,
			Double salesMoney) {
		this.dealsId = dealsId;
		this.productId = productId;
		this.picture = picture;
		this.description = description;
		this.sort = sort;
		this.salesCount = salesCount;
		this.salesMoney = salesMoney;
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

	@Column(name = "DealsID")
	public Integer getDealsId() {
		return this.dealsId;
	}

	public void setDealsId(Integer dealsId) {
		this.dealsId = dealsId;
	}

	@Column(name = "ProductID")
	public Long getProductId() {
		return this.productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	@Column(name = "Picture", length = 256)
	public String getPicture() {
		return this.picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	@Column(name = "Description", length = 128)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "Sort")
	public Integer getSort() {
		return this.sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	@Column(name = "SalesCount")
	public Integer getSalesCount() {
		return this.salesCount;
	}

	public void setSalesCount(Integer salesCount) {
		this.salesCount = salesCount;
	}

	@Column(name = "SalesMoney", precision = 18)
	public Double getSalesMoney() {
		return this.salesMoney;
	}

	public void setSalesMoney(Double salesMoney) {
		this.salesMoney = salesMoney;
	}

}