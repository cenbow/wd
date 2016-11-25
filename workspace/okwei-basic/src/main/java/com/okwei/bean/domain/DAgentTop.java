package com.okwei.bean.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * DAgentTop entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "D_AgentTop")
public class DAgentTop implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6025850538367341706L;
	// Fields

	private Integer id;
	private Long productId;
	private Long pbid;
	private Integer sort;

	// Constructors

	/** default constructor */
	public DAgentTop() {
	}

	/** full constructor */
	public DAgentTop(Long productId, Long pbid, Integer sort) {
		this.productId = productId;
		this.pbid = pbid;
		this.sort = sort;
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

	@Column(name = "ProductID")
	public Long getProductId() {
		return this.productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	@Column(name = "PBID")
	public Long getPbid() {
		return this.pbid;
	}

	public void setPbid(Long pbid) {
		this.pbid = pbid;
	}

	@Column(name = "Sort")
	public Integer getSort() {
		return this.sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

}