package com.okwei.bean.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TMarketImgs entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_MarketImgs")
public class TMarketImgs implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -6116063656508591684L;
	private Long id;
	private Integer bmid;
	private String img;
	private Date createTime;

	// Constructors

	/** default constructor */
	public TMarketImgs() {
	}

	/** full constructor */
	public TMarketImgs(Integer bmid, String img, Date createTime) {
		this.bmid = bmid;
		this.img = img;
		this.createTime = createTime;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "BMID")
	public Integer getBmid() {
		return this.bmid;
	}

	public void setBmid(Integer bmid) {
		this.bmid = bmid;
	}

	@Column(name = "Img", length = 128)
	public String getImg() {
		return this.img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	@Column(name = "CreateTime", length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}