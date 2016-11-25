package com.okwei.bean.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * OCancelOrderApply entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "O_CancelOrderApply")
public class OCancelOrderApply extends BaseEntity {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String supplierOrderId;
	private Long applyWeiId;
	private Long supplierId;
	private Short state;
	private Date applyTime;

	// Constructors

	/** default constructor */
	public OCancelOrderApply() {
	}

	/** full constructor */
	public OCancelOrderApply(String supplierOrderId, Long applyWeiId, Long supplierId, Short state, Date applyTime) {
		this.supplierOrderId = supplierOrderId;
		this.applyWeiId = applyWeiId;
		this.supplierId = supplierId;
		this.state = state;
		this.applyTime = applyTime;
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

	@Column(name = "SupplierOrderID", length = 20)
	public String getSupplierOrderId() {
		return this.supplierOrderId;
	}

	public void setSupplierOrderId(String supplierOrderId) {
		this.supplierOrderId = supplierOrderId;
	}

	@Column(name = "ApplyWeiID")
	public Long getApplyWeiId() {
		return this.applyWeiId;
	}

	public void setApplyWeiId(Long applyWeiId) {
		this.applyWeiId = applyWeiId;
	}

	@Column(name = "SupplierID")
	public Long getSupplierId() {
		return this.supplierId;
	}

	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
	}

	@Column(name = "State")
	public Short getState() {
		return this.state;
	}

	public void setState(Short state) {
		this.state = state;
	}

	@Column(name = "ApplyTime", length = 0)
	public Date getApplyTime() {
		return this.applyTime;
	}

	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}

}