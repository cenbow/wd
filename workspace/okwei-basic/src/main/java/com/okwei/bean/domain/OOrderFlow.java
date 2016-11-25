package com.okwei.bean.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * OOrderFlow entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "O_OrderFlow")
public class OOrderFlow extends BaseEntity {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long flowId;
	private String supplierOrderId;
	private Short ordertype;
	private String operateContent;
	private Long weiId;
	private Date operateTime;

	// Constructors

	/** default constructor */
	public OOrderFlow() {
	}

	/** full constructor */
	public OOrderFlow(String supplierOrderId, String operateContent, Long weiId, Date operateTime) {
		this.supplierOrderId = supplierOrderId;
		this.operateContent = operateContent;
		this.weiId = weiId;
		this.operateTime = operateTime;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "FlowID", unique = true, nullable = false)
	public Long getFlowId() {
		return this.flowId;
	}

	public void setFlowId(Long flowId) {
		this.flowId = flowId;
	}

	@Column(name = "SupplierOrderID", length = 20)
	public String getSupplierOrderId() {
		return this.supplierOrderId;
	}

	public void setSupplierOrderId(String supplierOrderId) {
		this.supplierOrderId = supplierOrderId;
	}

	@Column(name = "OperateContent", length = 65535)
	public String getOperateContent() {
		return this.operateContent;
	}

	public void setOperateContent(String operateContent) {
		this.operateContent = operateContent;
	}

	@Column(name = "WeiID")
	public Long getWeiId() {
		return this.weiId;
	}

	public void setWeiId(Long weiId) {
		this.weiId = weiId;
	}

	@Column(name = "OperateTime", length = 0)
	public Date getOperateTime() {
		return this.operateTime;
	}

	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}

	@Column(name = "OrderType")
	public Short getOrdertype() {
		return ordertype;
	}

	public void setOrdertype(Short ordertype) {
		this.ordertype = ordertype;
	}

}