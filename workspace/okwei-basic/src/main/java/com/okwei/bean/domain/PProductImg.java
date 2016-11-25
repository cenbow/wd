package com.okwei.bean.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * PProductImg entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "P_ProductImg")
public class PProductImg extends BaseEntity {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long productImgId;
	private Long productId;
	private Long supplierWeiId;
	private String imgPath;
	private Date createTime;

	// Constructors

	/** default constructor */
	public PProductImg() {
	}

	@Id
	@GeneratedValue
	@Column(name = "ProductImgID")
	public Long getProductImgId() {
		return productImgId;
	}

	public void setProductImgId(Long productImgId) {
		this.productImgId = productImgId;
	}

	@Column(name = "ProductID")
	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	@Column(name = "SupplierWeiID")
	public Long getSupplierWeiId() {
		return this.supplierWeiId;
	}

	public void setSupplierWeiId(Long supplierWeiId) {
		this.supplierWeiId = supplierWeiId;
	}

	@Column(name = "ImgPath", length = 512)
	public String getImgPath() {
		return this.imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	@Column(name = "CreateTime", length = 0)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}