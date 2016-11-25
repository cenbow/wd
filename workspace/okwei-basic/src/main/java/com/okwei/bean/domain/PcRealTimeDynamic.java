package com.okwei.bean.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * PcRealTimeDynamic entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "PC_RealTimeDynamic")
public class PcRealTimeDynamic implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long dyId;
	private String dyContent;
	private Date dyTime;

	// Constructors

	/** default constructor */
	public PcRealTimeDynamic() {
	}

	/** full constructor */
	public PcRealTimeDynamic(String dyContent, Date dyTime) {
		this.dyContent = dyContent;
		this.dyTime = dyTime;
	}

	// Property accessors
	@Id	
	@GeneratedValue
	@Column(name = "DyID", unique = true, nullable = false)
	public Long getDyId() {
		return this.dyId;
	}

	public void setDyId(Long dyId) {
		this.dyId = dyId;
	}

	@Column(name = "DyContent", length = 64)
	public String getDyContent() {
		return this.dyContent;
	}

	public void setDyContent(String dyContent) {
		this.dyContent = dyContent;
	}

	@Column(name = "DyTime", length = 19)
	public Date getDyTime() {
		return this.dyTime;
	}

	public void setDyTime(Date dyTime) {
		this.dyTime = dyTime;
	}

}