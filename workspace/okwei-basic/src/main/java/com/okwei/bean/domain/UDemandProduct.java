package com.okwei.bean.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * UDemandProduct entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "U_DemandProduct")
public class UDemandProduct implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3886374479022682337L;
	// Fields

	private Integer dpid;
	private Integer demandId;
	private Long productId;
	private Date createTime;

	// Constructors

	/** default constructor */
	public UDemandProduct() {
	}

	/** minimal constructor */
	public UDemandProduct(Integer demandId) {
		this.demandId = demandId;
	}

	/** full constructor */
	public UDemandProduct(Integer demandId, Long productId, Date createTime) {
		this.demandId = demandId;
		this.productId = productId;
		this.createTime = createTime;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "DPID", unique = true, nullable = false)
	public Integer getDpid() {
		return this.dpid;
	}

	public void setDpid(Integer dpid) {
		this.dpid = dpid;
	}

	@Column(name = "DemandID", nullable = false)
	public Integer getDemandId() {
		return this.demandId;
	}

	public void setDemandId(Integer demandId) {
		this.demandId = demandId;
	}

	@Column(name = "ProductID")
	public Long getProductId() {
		return this.productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	@Column(name = "CreateTime", length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}