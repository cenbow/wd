package com.okwei.bean.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * PProductAssist entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "P_ProductAssist")
public class PProductAssist extends BaseEntity {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long productId;
	private Integer monthCount;
	private Integer shelvesCount;
	private Integer classId;
	private Long supplierId;
	private Integer shareCount;
	private Integer totalCount;
	private Integer evaluateCount;
	private Integer readCount;

	@Column(name = "ClassID")
	public Integer getClassId() {
		return classId;
	}

	public void setClassId(Integer classId) {
		this.classId = classId;
	}

	@Column(name = "SupplierID")
	public Long getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
	}



	// Constructors

	/** default constructor */
	public PProductAssist() {
	}

	/** full constructor */
	public PProductAssist(Integer monthCount, Integer shelvesCount, Integer evaluateCount) {
		this.monthCount = monthCount;
		this.shelvesCount = shelvesCount;
		this.evaluateCount = evaluateCount;
	}

	// Property accessors
	@Id
	@Column(name = "ProductID", unique = true, nullable = false)
	public Long getProductId() {
		return this.productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	@Column(name = "MonthCount")
	public Integer getMonthCount() {
		return this.monthCount;
	}

	public void setMonthCount(Integer monthCount) {
		this.monthCount = monthCount;
	}

	@Column(name = "ShelvesCount")
	public Integer getShelvesCount() {
		return this.shelvesCount;
	}

	public void setShelvesCount(Integer shelvesCount) {
		this.shelvesCount = shelvesCount;
	}

	@Column(name = "EvaluateCount")
	public Integer getEvaluateCount() {
		return this.evaluateCount;
	}

	public void setEvaluateCount(Integer evaluateCount) {
		this.evaluateCount = evaluateCount;
	}

	@Column(name = "ShareCount")
	public Integer getShareCount() {
		return shareCount;
	}

	public void setShareCount(Integer shareCount) {
		this.shareCount = shareCount;
	}

	@Column(name = "TotalCount")
	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	@Column(name = "ReadCount")
	public Integer getReadCount() {
		return readCount;
	}

	public void setReadCount(Integer readCount) {
		this.readCount = readCount;
	}

	
}