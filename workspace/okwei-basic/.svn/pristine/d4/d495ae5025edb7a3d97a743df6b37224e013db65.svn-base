package com.okwei.bean.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * UPlatformSupplyerImg entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "U_PlatformSupplyerImg")
public class UPlatformSupplyerImg implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -4829748495523897865L;
	private Integer imgId;
	private Long weiId;
	private String title;
	private String image;
	private Date createTime;

	// Constructors

	/** default constructor */
	public UPlatformSupplyerImg() {
	}

	/** minimal constructor */
	public UPlatformSupplyerImg(Long weiId) {
		this.weiId = weiId;
	}

	/** full constructor */
	public UPlatformSupplyerImg(Long weiId, String image, Date createTime) {
		this.weiId = weiId;
		this.image = image;
		this.createTime = createTime;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ImgID", unique = true, nullable = false)
	public Integer getImgId() {
		return this.imgId;
	}

	public void setImgId(Integer imgId) {
		this.imgId = imgId;
	}

	@Column(name = "WeiID", nullable = false)
	public Long getWeiId() {
		return this.weiId;
	}

	public void setWeiId(Long weiId) {
		this.weiId = weiId;
	}

	@Column(name = "Title")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "Image", length = 128)
	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Column(name = "CreateTime", length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}