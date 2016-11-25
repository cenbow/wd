package com.okwei.bean.domain;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TMarketInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_MarketInfo")
public class TMarketInfo implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5063159647440864198L;
	private Integer bmid;
	private String mcexplain;
	private String mcobject;
	private String mcqq;
	private String mctel;
	private String mcphone;

	// Constructors


	// Property accessors

	@Id
	@Column(name = "BMID")
	public Integer getBmid() {
		return this.bmid;
	}

	public void setBmid(Integer bmid) {
		this.bmid = bmid;
	}

	@Column(name = "MCExplain", length = 256)
	public String getMcexplain() {
		return this.mcexplain;
	}

	public void setMcexplain(String mcexplain) {
		this.mcexplain = mcexplain;
	}

	@Column(name = "MCObject", length = 128)
	public String getMcobject() {
		return this.mcobject;
	}

	public void setMcobject(String mcobject) {
		this.mcobject = mcobject;
	}

	@Column(name = "MCQQ", length = 64)
	public String getMcqq() {
		return this.mcqq;
	}

	public void setMcqq(String mcqq) {
		this.mcqq = mcqq;
	}

	@Column(name = "MCTel", length = 32)
	public String getMctel() {
		return this.mctel;
	}

	public void setMctel(String mctel) {
		this.mctel = mctel;
	}

	@Column(name = "MCPhone", length = 32)
	public String getMcphone() {
		return this.mcphone;
	}

	public void setMcphone(String mcphone) {
		this.mcphone = mcphone;
	}

	
	

}