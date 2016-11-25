package com.okwei.bean.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TLunBoMessage entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_LunBoMessage")
public class TLunBoMessage implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -975519822624341149L;
	// Fields

	private Integer id;
	private String name;
	private String imageUrl;
	private String webUrl;
	private Date createTime;
	private Integer sort;
	private Short state;

	// Constructors

	/** default constructor */
	public TLunBoMessage() {
	}

	/** full constructor */
	public TLunBoMessage(String name, String imageUrl, String webUrl,
			Date createTime, Integer sort, Short state) {
		this.name = name;
		this.imageUrl = imageUrl;
		this.webUrl = webUrl;
		this.createTime = createTime;
		this.sort = sort;
		this.state = state;
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

	@Column(name = "Name", length = 128)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "ImageUrl", length = 128)
	public String getImageUrl() {
		return this.imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	@Column(name = "WebUrl", length = 128)
	public String getWebUrl() {
		return this.webUrl;
	}

	public void setWebUrl(String webUrl) {
		this.webUrl = webUrl;
	}

	@Column(name = "CreateTime", length = 0)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "Sort")
	public Integer getSort() {
		return this.sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	@Column(name = "State")
	public Short getState() {
		return this.state;
	}

	public void setState(Short state) {
		this.state = state;
	}

}