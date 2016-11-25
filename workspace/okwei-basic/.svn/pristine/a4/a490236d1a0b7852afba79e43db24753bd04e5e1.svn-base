package com.okwei.bean.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * OBatchAssistatOrder entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "O_BatchAssistatOrder")
public class OBatchAssistatOrder extends BaseEntity {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String supplierOrderId;
	private Short payType;
	private Short status;

	// Constructors

	/** default constructor */
	public OBatchAssistatOrder() {
	}

	/** full constructor */
	public OBatchAssistatOrder(Short payType, Short status) {
		this.payType = payType;
		this.status = status;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "SupplierOrderID", unique = true, nullable = false, length = 20)
	public String getSupplierOrderId() {
		return this.supplierOrderId;
	}

	public void setSupplierOrderId(String supplierOrderId) {
		this.supplierOrderId = supplierOrderId;
	}

	@Column(name = "PayType")
	public Short getPayType() {
		return this.payType;
	}

	public void setPayType(Short payType) {
		this.payType = payType;
	}

	@Column(name = "Status")
	public Short getStatus() {
		return this.status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

}