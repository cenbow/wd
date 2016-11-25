package com.okwei.bean.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * UAgentApplyArea entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "U_AgentApplyArea")
public class UAgentApplyArea implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4086896077576193818L;
	// Fields

	private Integer aid;
	private Integer applyId;
	private Integer code;
	private Integer provice;
	private Integer city;
	private Integer area;
	private Date createTime;

	// Constructors

	/** default constructor */
	public UAgentApplyArea() {
	}

	/** minimal constructor */
	public UAgentApplyArea(Integer aid, Integer applyId) {
		this.aid = aid;
		this.applyId = applyId;
	}

	/** full constructor */
	public UAgentApplyArea(Integer aid, Integer applyId, Integer provice,
			Integer city, Integer area, Date createTime) {
		this.aid = aid;
		this.applyId = applyId;
		this.provice = provice;
		this.city = city;
		this.area = area;
		this.createTime = createTime;
	}

	// Property accessors
	@Id
        @GeneratedValue
	@Column(name = "AID", unique = true, nullable = false)
	public Integer getAid() {
		return this.aid;
	}

	public void setAid(Integer aid) {
		this.aid = aid;
	}

	@Column(name = "ApplyID", nullable = false)
	public Integer getApplyId() {
		return this.applyId;
	}

	public void setApplyId(Integer applyId) {
		this.applyId = applyId;
	}

	@Column(name = "Provice")
	public Integer getProvice() {
		return this.provice;
	}

	public void setProvice(Integer provice) {
		this.provice = provice;
	}

	@Column(name = "City")
	public Integer getCity() {
		return this.city;
	}

	public void setCity(Integer city) {
		this.city = city;
	}

	@Column(name = "Area")
	public Integer getArea() {
		return this.area;
	}

	public void setArea(Integer area) {
		this.area = area;
	}

	@Column(name = "CreateTime", length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@Column(name = "Code")
	public Integer getCode() {
		return this.code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

}