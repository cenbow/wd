package com.okwei.bean.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * THouseCarousel entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_HouseCarousel")
public class THouseCarousel implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1376060703575398584L;
	// Fields

	private Integer id;
	private Integer houseId;
	private String title;
	private String image;
	private Integer sort;
	private String url;
	private Short state;
	private Date createTime;

	// Constructors

	/** default constructor */
	public THouseCarousel() {
	}

	/** full constructor */
	public THouseCarousel(Integer houseId, String title, String image,
			Integer sort, String url, Short state, Date createTime) {
		this.houseId = houseId;
		this.title = title;
		this.image = image;
		this.sort = sort;
		this.url = url;
		this.state = state;
		this.createTime = createTime;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "ID", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "HouseID")
	public Integer getHouseId() {
		return this.houseId;
	}

	public void setHouseId(Integer houseId) {
		this.houseId = houseId;
	}

	@Column(name = "Title", length = 128)
	public String getTitle() {
		return this.title;
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

	@Column(name = "Sort")
	public Integer getSort() {
		return this.sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	@Column(name = "Url", length = 128)
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "state")
	public Short getState() {
		return this.state;
	}

	public void setState(Short state) {
		this.state = state;
	}

	@Column(name = "CreateTime", length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}