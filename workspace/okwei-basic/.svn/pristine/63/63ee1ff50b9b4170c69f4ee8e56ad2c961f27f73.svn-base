package com.okwei.bean.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * SMainData entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "S_MainData")
public class SMainData implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7161422573537409914L;
	// Fields

	private Long shareId;
	private Long weiId;
	private String title;
	private String describle;
	private Integer pcount;
	private Short onHomePage;
	//这是status  的 枚举是ShareStatus 
	private Short status;
	private Date createTime;
	private Date updateTime;
	private Date topTime;
	private Short terminateType;
	//userType 的枚举 MainDataUserType 
	private Integer userType;
	//标示cts是否删除记录（这里不影响别处的展示）
	private Short ctsFlag;
	private Short ctsState;
	private String ctsUser;
	private Short shareType;
	private Date pcTopTime;
	
	@Column(name = "ShareType")
	public Short getShareType() {
		return shareType;
	}

	public void setShareType(Short shareType) {
		this.shareType = shareType;
	}

	@Column(name = "CtsState")
	public Short getCtsState() {
		return ctsState;
	}

	public void setCtsState(Short ctsState) {
		this.ctsState = ctsState;
	}

	@Column(name = "CtsFlag")
	public Short getCtsFlag() {
		return ctsFlag;
	}

	public void setCtsFlag(Short ctsFlag) {
		this.ctsFlag = ctsFlag;
	}
	
	@Column(name = "UserType")
	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	
	@Column(name = "TerminateType")
	public Short getTerminateType() {
		return terminateType;
	}

	public void setTerminateType(Short terminateType) {
		this.terminateType = terminateType;
	}



	// Constructors

	/** default constructor */
	public SMainData() {
	}

	/** full constructor */
	public SMainData(Long weiId, String title, String describle,
			Integer pcount, Short onHomePage, Short status,
			Date createTime, Date updateTime) {
		this.weiId = weiId;
		this.title = title;
		this.describle = describle;
		this.pcount = pcount;
		this.onHomePage = onHomePage;
		this.status = status;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ShareID", unique = true, nullable = false)
	public Long getShareId() {
		return this.shareId;
	}

	public void setShareId(Long shareId) {
		this.shareId = shareId;
	}

	@Column(name = "WeiID")
	public Long getWeiId() {
		return this.weiId;
	}

	public void setWeiId(Long weiId) {
		this.weiId = weiId;
	}

	@Column(name = "Title", length = 128)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "Describle", length = 512)
	public String getDescrible() {
		return this.describle;
	}

	public void setDescrible(String describle) {
		this.describle = describle;
	}

	@Column(name = "Pcount")
	public Integer getPcount() {
		return this.pcount;
	}

	public void setPcount(Integer pcount) {
		this.pcount = pcount;
	}

	@Column(name = "OnHomePage")
	public Short getOnHomePage() {
		return this.onHomePage;
	}

	public void setOnHomePage(Short onHomePage) {
		this.onHomePage = onHomePage;
	}

	@Column(name = "Status")
	public Short getStatus() {
		return this.status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}
	@Column(name = "TopTime", length = 0)
	public Date getTopTime() {
		return this.topTime;
	}

	public void setTopTime(Date topTime) {
		this.topTime = topTime;
	} 
	
	@Column(name = "CreateTime", length = 0)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "UpdateTime", length = 0)
	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	@Column(name = "CtsUser", length = 32)
	public String getCtsUser() {
		return this.ctsUser;
	}

	public void setCtsUser(String ctsUser) {
		this.ctsUser = ctsUser;
	}
	
	@Column(name = "PcTopTime")
	public Date getPcTopTime() {
		return this.pcTopTime;
	}

	public void setPcTopTime(Date pcTopTime) {
		this.pcTopTime = pcTopTime;
	} 
}