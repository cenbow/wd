package com.okwei.bean.domain;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * AHomeApp entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "A_HomeApp")
public class AHomeApp implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer homeId;
	private Integer position;
	private String title;
	private String homeImage;
	private String bannerImage;

	// Constructors

	/** default constructor */
	public AHomeApp() {
	}

	/** minimal constructor */
	public AHomeApp(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public AHomeApp(Integer id, Integer homeId, Integer position, String title,
			String homeImage, String bannerImage) {
		this.id = id;
		this.homeId = homeId;
		this.position = position;
		this.title = title;
		this.homeImage = homeImage;
		this.bannerImage = bannerImage;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "HomeID")
	public Integer getHomeId() {
		return this.homeId;
	}

	public void setHomeId(Integer homeId) {
		this.homeId = homeId;
	}

	@Column(name = "position")
	public Integer getPosition() {
		return this.position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	@Column(name = "Title", length = 16)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "HomeImage", length = 256)
	public String getHomeImage() {
		return this.homeImage;
	}

	public void setHomeImage(String homeImage) {
		this.homeImage = homeImage;
	}

	@Column(name = "BannerImage", length = 256)
	public String getBannerImage() {
		return this.bannerImage;
	}

	public void setBannerImage(String bannerImage) {
		this.bannerImage = bannerImage;
	}

}