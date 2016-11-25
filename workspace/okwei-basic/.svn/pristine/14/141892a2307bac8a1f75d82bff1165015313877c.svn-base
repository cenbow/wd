package com.okwei.bean.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TRecommendVerSup entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_RecommendVerSup" )
public class TRecommendVerSup extends BaseEntity {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long weiId;
	private String image;
	private Short type;
	private Short state;
	private Date createTime;

	// Constructors

	/** default constructor */
	public TRecommendVerSup() {
	}

	/** full constructor */
	public TRecommendVerSup(Short type, Short state, Date createTime) {
		this.type = type;
		this.state = state;
		this.createTime = createTime;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "WeiID", unique = true, nullable = false)
	public Long getWeiId() {
		return this.weiId;
	}

	public void setWeiId(Long weiId) {
		this.weiId = weiId;
	}

	@Column(name = "Type")
	public Short getType() {
		return this.type;
	}

	public void setType(Short type) {
		this.type = type;
	}

	@Column(name = "State")
	public Short getState() {
		return this.state;
	}

	public void setState(Short state) {
		this.state = state;
	}

	@Column(name = "CreateTime", length = 0)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "Image", length = 128)
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

}