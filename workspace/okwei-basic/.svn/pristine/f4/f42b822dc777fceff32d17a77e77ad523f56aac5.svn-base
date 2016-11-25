package com.okwei.bean.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * USupplierFllowRecord entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "U_SupplierFllowRecord")
public class USupplierFllowRecord implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -5990453776911905433L;
	private Long id;
	private Long supplierWeiId;
	private Long followWeiId;
	private String linkContent;
	private String followContent;
	private Date createTime;

	// Constructors

	/** default constructor */
	public USupplierFllowRecord() {
	}

	/** full constructor */
	public USupplierFllowRecord(Long followWeiId, String followContent,
			Date createTime) {
		this.followWeiId = followWeiId;
		this.followContent = followContent;
		this.createTime = createTime;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "FollowWeiID")
	public Long getFollowWeiId() {
		return this.followWeiId;
	}

	

	public void setFollowWeiId(Long followWeiId) {
		this.followWeiId = followWeiId;
	}

	@Column(name = "LinkContent", length = 16)
	public String getLinkContent() {
		return linkContent;
	}

	public void setLinkContent(String linkContent) {
		this.linkContent = linkContent;
	}

	@Column(name = "FollowContent", length = 256)
	public String getFollowContent() {
		return this.followContent;
	}

	public void setFollowContent(String followContent) {
		this.followContent = followContent;
	}

	@Column(name = "CreateTime", length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
    @Column(name="SupplierWeiID")
	public Long getSupplierWeiId() {
		return supplierWeiId;
	}

	public void setSupplierWeiId(Long supplierWeiId) {
		this.supplierWeiId = supplierWeiId;
	}

}