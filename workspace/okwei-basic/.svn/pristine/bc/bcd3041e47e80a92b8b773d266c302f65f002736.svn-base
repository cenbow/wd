package com.okwei.bean.domain;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * DBrandImgs entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "D_BrandImgs")
public class DBrandImgs implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2949260244514326478L;
	// Fields

	private Long brandImgId;
	private Integer brandId;
	private String img;
	private Integer type;

	// Constructors

	/** default constructor */
	public DBrandImgs() {
	}

	/** minimal constructor */
	public DBrandImgs(Long brandImgId) {
		this.brandImgId = brandImgId;
	}

	/** full constructor */
	public DBrandImgs(Long brandImgId, Integer brandId, String img, Integer type) {
		this.brandImgId = brandImgId;
		this.brandId = brandId;
		this.img = img;
		this.type = type;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "BrandImgID", unique = true, nullable = false)
	public Long getBrandImgId() {
		return this.brandImgId;
	}

	public void setBrandImgId(Long brandImgId) {
		this.brandImgId = brandImgId;
	}

	@Column(name = "BrandID")
	public Integer getBrandId() {
		return this.brandId;
	}

	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}

	@Column(name = "Img", length = 128)
	public String getImg() {
		return this.img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	@Column(name = "Type")
	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

}