package com.okwei.bean.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * PShopClass entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "P_ShopClass")
public class PShopClass extends BaseEntity {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer sid;
	private Long weiid;
	private String sname;
	private Short state;
	private Date createTime;
	private Short sort;
	private Short type;
	private Short level;
	private Integer paretId;

	// Constructors
	@Column(name = "Type")
	public Short getType() {
		return type;
	}

	public void setType(Short type) {
		this.type = type;
	}

	/** default constructor */
	public PShopClass() {
	}

	/** full constructor */
	public PShopClass(String sname, Short state, Date createTime) {
		this.sname = sname;
		this.state = state;
		this.createTime = createTime;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "SID", unique = true, nullable = false)
	public Integer getSid() {
		return this.sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	@Column(name = "WeiID", length = 20)
	public Long getWeiid() {
		return weiid;
	}

	public void setWeiid(Long weiid) {
		this.weiid = weiid;
	}

	@Column(name = "SName", length = 32)
	public String getSname() {
		return this.sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
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

	@Column(name = "Sort", length = 6)
	public Short getSort() {
		return sort;
	}

	public void setSort(Short sort) {
		this.sort = sort;
	}
	@Column(name = "Level",columnDefinition="SMALLINT default 1", length = 6)
	public Short getLevel() {
		return level;
	}

	public void setLevel(Short level) {
		this.level = level;
	}
	@Column(name = "ParetID")
	public Integer getParetId() {
		return paretId;
	}

	public void setParetId(Integer paretId) {
		this.paretId = paretId;
	}

}