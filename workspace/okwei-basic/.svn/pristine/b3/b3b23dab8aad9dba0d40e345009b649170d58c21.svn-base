package com.okwei.bean.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * URequiredKv entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "U_RequiredKV")
public class URequiredKv implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4612703782934453829L;
	// Fields

	private Integer kvID;
	private Integer requiredId;
	private Integer demandId;
	private String rkey;
	private String rvalue;
	private Date createTime;

	// Constructors

	/** default constructor */
	public URequiredKv() {
	}

	/** full constructor */
	public URequiredKv(Integer demandId, String rkey, String rvalue,
			Date createTime) {
		this.demandId = demandId;
		this.rkey = rkey;
		this.rvalue = rvalue;
		this.createTime = createTime;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "KVID", unique = true, nullable = false)
	public Integer getKVID() {
		return this.kvID;
	}

	public void setKVID(Integer kvID) {
		this.kvID = kvID;
	}
		
	@Column(name = "RequiredID")
	public Integer getRequiredId() {
		return this.requiredId;
	}

	public void setRequiredId(Integer requiredId) {
		this.requiredId = requiredId;
	}

	@Column(name = "DemandID")
	public Integer getDemandId() {
		return this.demandId;
	}

	public void setDemandId(Integer demandId) {
		this.demandId = demandId;
	}

	@Column(name = "RKey", length = 64)
	public String getRkey() {
		return this.rkey;
	}

	public void setRkey(String rkey) {
		this.rkey = rkey;
	}

	@Column(name = "RValue", length = 256)
	public String getRvalue() {
		return this.rvalue;
	}

	public void setRvalue(String rvalue) {
		this.rvalue = rvalue;
	}

	@Column(name = "CreateTime", length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}