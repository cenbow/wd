package com.okwei.bean.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * PProductStyleKv entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "P_ProductStyleKV")
public class PProductStyleKv extends BaseEntity {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long kvid;
	private Long productId;
	private Long stylesId;
	private Long attributeId;
	private Long keyId;
	private Date createTime;
	private Long parentId;

	// Constructors

	/** default constructor */
	public PProductStyleKv() {
	}

	@Id
	@GeneratedValue
	@Column(name = "KVID")
	public Long getKvid() {
		return kvid;
	}

	public void setKvid(Long kvid) {
		this.kvid = kvid;
	}

	@Column(name = "ProductID")
	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	@Column(name = "StylesID")
	public Long getStylesId() {
		return this.stylesId;
	}

	public void setStylesId(Long stylesId) {
		this.stylesId = stylesId;
	}

	@Column(name = "AttributeID")
	public Long getAttributeId() {
		return this.attributeId;
	}

	public void setAttributeId(Long attributeId) {
		this.attributeId = attributeId;
	}

	@Column(name = "KeyID")
	public Long getKeyId() {
		return this.keyId;
	}

	public void setKeyId(Long keyid) {
		this.keyId = keyid;
	}

	@Column(name = "CreateTime", length = 0)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "ParentID")
	public Long getParentId() {
		return this.parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

}