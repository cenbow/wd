package com.okwei.bean.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * PClassProducts entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "P_ClassProducts")
public class PClassProducts extends BaseEntity {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long weiIdsort;
	private Long productId;
	private Long classId;
	private Long weiId;
	private Long supplierweiId;
	private Long shelvweiId;
	private Long sendweiId;
	private Short sort;
	private Integer sellNum;
	private Double commision;
	private String reason;
	private Short type;
	private Short state;
	private Date createTime;
	private Short isSendMyself;

	// Constructors

	/** default constructor */
	public PClassProducts() {
	}

	@Id
	@GeneratedValue
	@Column(name = "ID")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "WeiIDSort")
	public Long getWeiIdsort() {
		return weiIdsort;
	}

	public void setWeiIdsort(Long weiIdsort) {
		this.weiIdsort = weiIdsort;
	}

	@Column(name = "ProductID")
	public Long getProductId() {
		return this.productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	@Column(name = "ClassID")
	public Long getClassId() {
		return this.classId;
	}

	public void setClassId(Long classId) {
		this.classId = classId;
	}

	@Column(name = "WeiID")
	public Long getWeiId() {
		return this.weiId;
	}

	public void setWeiId(Long weiId) {
		this.weiId = weiId;
	}

	@Column(name = "Sort")
	public Short getSort() {
		return this.sort;
	}

	public void setSort(Short sort) {
		this.sort = sort;
	}

	@Column(name = "SellNum")
	public Integer getSellNum() {
		return this.sellNum;
	}

	public void setSellNum(Integer sellNum) {
		this.sellNum = sellNum;
	}

	@Column(name = "Commision", precision = 18)
	public Double getCommision() {
		return this.commision;
	}

	public void setCommision(Double commision) {
		this.commision = commision;
	}

	@Column(name = "Reason", length = 128)
	public String getReason() {
		return this.reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@Column(name = "State")
	public Short getState() {
		return this.state;
	}

	public void setState(Short state) {
		this.state = state;
	}

	@Column(name = "CreateTime", length = 0)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "IsSendMyself")
	public Short getIsSendMyself() {
		return isSendMyself;
	}

	public void setIsSendMyself(Short isSendMyself) {
		this.isSendMyself = isSendMyself;
	}

	@Column(name = "SupplierWeiID")
	public Long getSupplierweiId() {
		return supplierweiId;
	}

	public void setSupplierweiId(Long supplierweiId) {
		this.supplierweiId = supplierweiId;
	}

	@Column(name = "Type")
	public Short getType() {
		return type;
	}

	public void setType(Short type) {
		this.type = type;
	}

	@Column(name = "ShelveWeiID")
	public Long getShelvweiId() {
		return shelvweiId;
	}

	public void setShelvweiId(Long shelvweiId) {
		this.shelvweiId = shelvweiId;
	}

	@Column(name = "SendWeiID")
	public Long getSendweiId() {
		return sendweiId;
	}

	public void setSendweiId(Long sendweiId) {
		this.sendweiId = sendweiId;
	}

}