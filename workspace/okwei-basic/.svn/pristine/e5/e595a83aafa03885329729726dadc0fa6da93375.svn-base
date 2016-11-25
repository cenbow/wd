package com.okwei.bean.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * SSingleDesc entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "S_SingleDesc")
public class SSingleDesc implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 3121555370280610173L;
	private Long sid;
	private Long spid;
	private String imageUrl;
	private String description;
	private String remark;

	// Constructors

	/** default constructor */
	public SSingleDesc() {
	}

	/** full constructor */
	public SSingleDesc(Long spid, String imageUrl, String description,
			String remark) {
		this.spid = spid;
		this.imageUrl = imageUrl;
		this.description = description;
		this.remark = remark;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "SID", unique = true, nullable = false)
	public Long getSid() {
		return this.sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

	@Column(name = "SPID")
	public Long getSpid() {
		return this.spid;
	}

	public void setSpid(Long spid) {
		this.spid = spid;
	}

	@Column(name = "ImageUrl", length = 128)
	public String getImageUrl() {
		return this.imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	@Column(name = "Description", length = 1024)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "Remark", length = 64)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}