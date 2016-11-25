package com.okwei.bean.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * PShelveComment entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "P_ShelveComment")
public class PShelveComment implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6364347827027234822L;
	// Fields

	private Long id;
	private Long productId;
	private Long weiId;
	private String comment;
	private Date createTime;
	private Short state;

	// Constructors

	/** default constructor */
	public PShelveComment() {
	}

	/** full constructor */
	public PShelveComment(Long productId, Long weiId, String comment,
			Date createTime, Short state) {
		this.productId = productId;
		this.weiId = weiId;
		this.comment = comment;
		this.createTime = createTime;
		this.state = state;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "ID", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "ProductID")
	public Long getProductId() {
		return this.productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	@Column(name = "WeiID")
	public Long getWeiId() {
		return this.weiId;
	}

	public void setWeiId(Long weiId) {
		this.weiId = weiId;
	}

	@Column(name = "Comment", length = 350)
	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Column(name = "CreateTime", length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "State")
	public Short getState() {
		return this.state;
	}

	public void setState(Short state) {
		this.state = state;
	}

}