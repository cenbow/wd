package com.okwei.bean.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * URealNameVerify entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "U_RealNameVerify")
public class URealNameVerify extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6737685864542756249L;
	// Fields

	private Long verId;
	private Long weiId;
	private String realName;
	private String idcard;
	private String positiveImage;
	private String backImage;
	private Short status;
	private Date createTime;
	private String failContent;

	// Constructors

	/** default constructor */
	public URealNameVerify() {
	}

	/** minimal constructor */
	public URealNameVerify(Long weiId) {
		this.weiId = weiId;
	}

	/** full constructor */
	public URealNameVerify(Long weiId, String realName, String idcard,
			String positiveImage, String backImage, Short status,
			Date createTime) {
		this.weiId = weiId;
		this.realName = realName;
		this.idcard = idcard;
		this.positiveImage = positiveImage;
		this.backImage = backImage;
		this.status = status;
		this.createTime = createTime;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "VerID", unique = true, nullable = false)
	public Long getVerId() {
		return this.verId;
	}

	public void setVerId(Long verId) {
		this.verId = verId;
	}

	@Column(name = "WeiID", nullable = false)
	public Long getWeiId() {
		return this.weiId;
	}

	public void setWeiId(Long weiId) {
		this.weiId = weiId;
	}

	@Column(name = "RealName", length = 64)
	public String getRealName() {
		return this.realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	@Column(name = "IDCard", length = 32)
	public String getIdcard() {
		return this.idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	@Column(name = "PositiveImage", length = 128)
	public String getPositiveImage() {
		return this.positiveImage;
	}

	public void setPositiveImage(String positiveImage) {
		this.positiveImage = positiveImage;
	}

	@Column(name = "BackImage", length = 128)
	public String getBackImage() {
		return this.backImage;
	}

	public void setBackImage(String backImage) {
		this.backImage = backImage;
	}

	@Column(name = "Status")
	public Short getStatus() {
		return this.status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	@Column(name = "CreateTime", length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name="FailContent",length=128)
	public String getFailContent() {
		return failContent;
	}
	public void setFailContent(String failContent) {
		this.failContent = failContent;
	}

}