package com.okwei.bean.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "O_SupplyerOrderCompletedLog")
public class OSupplyerOrderCompletedLog implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	private Long id;
	private String supplierOrderId;
	private Short orderState;
	private Short state;
	private Date createTime;
	
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public Long getBrandId() {
		return this.id;
	}

	public void setBrandId(Long id) {
		this.id = id;
	}
	
	@Column(name = "SupplierOrderId", length = 32)
	public String getSupplierOrderId() {
		return this.supplierOrderId;
	}

	public void setSupplierOrderId(String supplyOrderid) {
		this.supplierOrderId = supplyOrderid;
	}
	@Column(name = "OrderState")
	public Short getOrderState() {
		return this.orderState;
	}

	public void setOrderState(Short orderState) {
		this.orderState = orderState;
	}
	
	@Column(name = "State")
	public Short getState() {
		return this.state;
	}

	public void setState(Short state) {
		this.state = state;
	}
	
	@Column(name = "CreateTime")
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
