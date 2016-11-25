package com.okwei.bean.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TRefundImg entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_Refund_Img" )
public class TRefundImg extends BaseEntity {

	// Fields

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	private Long id;

	@Column(name = "BackOrder")
	private Long backOrder;

	@Column(name = "RefundImg")
	private String refundImg;

	@Column(name = "Rid")
	private Long rid;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getBackOrder() {
		return backOrder;
	}

	public void setBackOrder(Long backOrder) {
		this.backOrder = backOrder;
	}

	public String getRefundImg() {
		return refundImg;
	}

	public void setRefundImg(String refundImg) {
		this.refundImg = refundImg;
	}

	public Long getRid() {
		return rid;
	}

	public void setRid(Long rid) {
		this.rid = rid;
	}
}