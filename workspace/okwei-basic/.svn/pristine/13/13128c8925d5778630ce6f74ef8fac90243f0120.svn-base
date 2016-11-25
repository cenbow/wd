package com.okwei.bean.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * DAgentTeam entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "D_AgentTeam")
public class DAgentTeam implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7313611933594281122L;
	// Fields

	private Integer ateamId;
	private Long weiId;
	private Integer brandId;
	private Integer province;
	private Integer city;
	private Integer district;
	private Integer memberCount;
	private Date createTime;

	// Constructors

	/** default constructor */
	public DAgentTeam() {
	}

	/** minimal constructor */
	public DAgentTeam(Integer ateamId) {
		this.ateamId = ateamId;
	}

	/** full constructor */
	public DAgentTeam(Integer ateamId, Long weiId, Integer brandId,
			Integer province, Integer city, Integer district,
			Integer memberCount, Date createTime) {
		this.ateamId = ateamId;
		this.weiId = weiId;
		this.brandId = brandId;
		this.province = province;
		this.city = city;
		this.district = district;
		this.memberCount = memberCount;
		this.createTime = createTime;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ATeamID", unique = true, nullable = false)
	public Integer getAteamId() {
		return this.ateamId;
	}

	public void setAteamId(Integer ateamId) {
		this.ateamId = ateamId;
	}

	@Column(name = "WeiID")
	public Long getWeiId() {
		return this.weiId;
	}

	public void setWeiId(Long weiId) {
		this.weiId = weiId;
	}

	@Column(name = "BrandID")
	public Integer getBrandId() {
		return this.brandId;
	}

	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}

	@Column(name = "Province")
	public Integer getProvince() {
		return this.province;
	}

	public void setProvince(Integer province) {
		this.province = province;
	}

	@Column(name = "City")
	public Integer getCity() {
		return this.city;
	}

	public void setCity(Integer city) {
		this.city = city;
	}

	@Column(name = "District")
	public Integer getDistrict() {
		return this.district;
	}

	public void setDistrict(Integer district) {
		this.district = district;
	}

	@Column(name = "MemberCount")
	public Integer getMemberCount() {
		return this.memberCount;
	}

	public void setMemberCount(Integer memberCount) {
		this.memberCount = memberCount;
	}

	@Column(name = "CreateTime", length = 0)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}