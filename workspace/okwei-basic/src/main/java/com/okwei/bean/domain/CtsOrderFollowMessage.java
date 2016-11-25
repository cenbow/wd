package com.okwei.bean.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CtsOrderFollowMessage entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CTS_OrderFollowMessage")
public class CtsOrderFollowMessage implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -2894324584744705016L;
	private Long id;
	private String supplierOrder;
	private Long operatorWeiId;
	private Date createTime;
	private String content;
	private String operatorName;

	// Constructors

	/** default constructor */
	public CtsOrderFollowMessage() {
	}

	/** full constructor */
	public CtsOrderFollowMessage(String supplierOrder, Long operatorWeiId,
			Date createTime, String content, String operatorName) {
		this.supplierOrder = supplierOrder;
		this.operatorWeiId = operatorWeiId;
		this.createTime = createTime;
		this.content = content;
		this.operatorName = operatorName;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "SupplierOrder", length = 32)
	public String getSupplierOrder() {
		return this.supplierOrder;
	}

	public void setSupplierOrder(String supplierOrder) {
		this.supplierOrder = supplierOrder;
	}

	@Column(name = "OperatorWeiID")
	public Long getOperatorWeiId() {
		return this.operatorWeiId;
	}

	public void setOperatorWeiId(Long operatorWeiId) {
		this.operatorWeiId = operatorWeiId;
	}

	@Column(name = "CreateTime", length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "Content", length = 256)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "OperatorName", length = 32)
	public String getOperatorName() {
		return this.operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

}