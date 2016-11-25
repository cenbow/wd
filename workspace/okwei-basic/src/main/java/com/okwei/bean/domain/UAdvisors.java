package com.okwei.bean.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * UAdvisors entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "U_Advisors")
public class UAdvisors implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3704887231296701065L;
	// Fields

	private Long weiId;
	private String photo;
	private String realName;
	private String mobilePhone;
	private String qq;
	private Long groupManager;
	private Short type;
	private Date createTime;
	private Long createMan;
	private Long updateMan;
	private Date updateTime;
	private Short status;
	private String region;

	// Constructors

	/** default constructor */
	public UAdvisors() {
	}

	/** minimal constructor */
	public UAdvisors(Long weiId) {
		this.weiId = weiId;
	}

	/** full constructor */
	public UAdvisors(Long weiId, String photo, String realName,
			String mobilePhone, String qq, Long groupManager, Short type,
			Date createTime, Long createMan, Long updateMan,
			Date updateTime, Short status, String region) {
		this.weiId = weiId;
		this.photo = photo;
		this.realName = realName;
		this.mobilePhone = mobilePhone;
		this.qq = qq;
		this.groupManager = groupManager;
		this.type = type;
		this.createTime = createTime;
		this.createMan = createMan;
		this.updateMan = updateMan;
		this.updateTime = updateTime;
		this.status = status;
		this.region = region;
	}

	// Property accessors
	@Id
	@Column(name = "WeiID", unique = true, nullable = false)
	public Long getWeiId() {
		return this.weiId;
	}

	public void setWeiId(Long weiId) {
		this.weiId = weiId;
	}

	@Column(name = "Photo", length = 128)
	public String getPhoto() {
		return this.photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	@Column(name = "RealName", length = 32)
	public String getRealName() {
		return this.realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	@Column(name = "MobilePhone", length = 16)
	public String getMobilePhone() {
		return this.mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	@Column(name = "QQ", length = 16)
	public String getQq() {
		return this.qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	@Column(name = "GroupManager")
	public Long getGroupManager() {
		return this.groupManager;
	}

	public void setGroupManager(Long groupManager) {
		this.groupManager = groupManager;
	}

	@Column(name = "Type")
	public Short getType() {
		return this.type;
	}

	public void setType(Short type) {
		this.type = type;
	}

	@Column(name = "CreateTime", length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "CreateMan")
	public Long getCreateMan() {
		return this.createMan;
	}

	public void setCreateMan(Long createMan) {
		this.createMan = createMan;
	}

	@Column(name = "UpdateMan")
	public Long getUpdateMan() {
		return this.updateMan;
	}

	public void setUpdateMan(Long updateMan) {
		this.updateMan = updateMan;
	}

	@Column(name = "UpdateTime", length = 19)
	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name = "Status")
	public Short getStatus() {
		return this.status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	@Column(name = "Region", length = 32)
	public String getRegion() {
		return this.region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

}