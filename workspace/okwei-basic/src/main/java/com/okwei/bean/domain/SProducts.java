package com.okwei.bean.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * SProducts entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "S_Products")
public class SProducts implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3214121980883425878L;
	// Fields

	private Long spid;
	private Long shareId;
	private Long productId;
	private Long shelveId;
	private Long weiId;
	private String description;

	// Constructors

	/** default constructor */
	public SProducts() {
	}

	/** full constructor */
	public SProducts(Long shareId, Long productId, Long shelveId,
			Long weiId) {
		this.shareId = shareId;
		this.productId = productId;
		this.shelveId = shelveId;
		this.weiId = weiId;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "SPID", unique = true, nullable = false)
	public Long getSpid() {
		return this.spid;
	}

	public void setSpid(Long spid) {
		this.spid = spid;
	}

	@Column(name = "ShareID")
	public Long getShareId() {
		return this.shareId;
	}

	public void setShareId(Long shareId) {
		this.shareId = shareId;
	}

	@Column(name = "ProductID")
	public Long getProductId() {
		return this.productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	@Column(name = "ShelveID")
	public Long getShelveId() {
		return this.shelveId;
	}

	public void setShelveId(Long shelveId) {
		this.shelveId = shelveId;
	}

	@Column(name = "WeiID")
	public Long getWeiId() {
		return this.weiId;
	}

	public void setWeiId(Long weiId) {
		this.weiId = weiId;
	}

	@Column(name = "Description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
}