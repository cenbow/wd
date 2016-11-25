package com.okwei.bean.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * DAdInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "D_AdInfo")
public class DAdInfo implements java.io.Serializable {

	// Fields

	private Integer adId;
	private String title;
	private String wapUrl;
	private String imageUrl;
	private Date publishTime;
	private Integer positionType;

	// Constructors

	/** default constructor */
	public DAdInfo() {
	}

	/** full constructor */
	public DAdInfo(String title, String wapUrl, String imageUrl,
			Date publishTime, Integer positionType) {
		this.title = title;
		this.wapUrl = wapUrl;
		this.imageUrl = imageUrl;
		this.publishTime = publishTime;
		this.positionType = positionType;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "AdID", unique = true, nullable = false)
	public Integer getAdId() {
		return this.adId;
	}

	public void setAdId(Integer adId) {
		this.adId = adId;
	}

	@Column(name = "Title", length = 128)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "WapUrl", length = 128)
	public String getWapUrl() {
		return this.wapUrl;
	}

	public void setWapUrl(String wapUrl) {
		this.wapUrl = wapUrl;
	}

	@Column(name = "ImageUrl", length = 128)
	public String getImageUrl() {
		return this.imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	@Column(name = "PublishTime", length = 0)
	public Date getPublishTime() {
		return this.publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}

	@Column(name = "PositionType")
	public Integer getPositionType() {
		return this.positionType;
	}

	public void setPositionType(Integer positionType) {
		this.positionType = positionType;
	}

}