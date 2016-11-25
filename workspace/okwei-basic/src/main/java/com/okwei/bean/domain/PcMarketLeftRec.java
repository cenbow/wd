package com.okwei.bean.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * PcMarketLeftRec entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "PC_MarketLeftRec")
public class PcMarketLeftRec implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Fields

	private Long recId;
	private Integer recType;
	private String img;
	private String linkUrl;
	private Short sort;
	private Short status;

	// Constructors

	/** default constructor */
	public PcMarketLeftRec() {
	}

	/** full constructor */
	public PcMarketLeftRec(Integer recType, String img, String linkUrl,
			Short sort, Short status) {
		this.recType = recType;
		this.img = img;
		this.linkUrl = linkUrl;
		this.sort = sort;
		this.status = status;
	}

	// Property accessors
	@Id	
	@Column(name = "RecID", unique = true, nullable = false)
	public Long getRecId() {
		return this.recId;
	}

	public void setRecId(Long recId) {
		this.recId = recId;
	}

	@Column(name = "RecType")
	public Integer getRecType() {
		return this.recType;
	}

	public void setRecType(Integer recType) {
		this.recType = recType;
	}

	@Column(name = "Img", length = 128)
	public String getImg() {
		return this.img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	@Column(name = "LinkUrl", length = 128)
	public String getLinkUrl() {
		return this.linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	@Column(name = "Sort")
	public Short getSort() {
		return this.sort;
	}

	public void setSort(Short sort) {
		this.sort = sort;
	}

	@Column(name = "Status")
	public Short getStatus() {
		return this.status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

}