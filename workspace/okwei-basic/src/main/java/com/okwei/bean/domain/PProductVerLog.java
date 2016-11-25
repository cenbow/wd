package com.okwei.bean.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * PProductVerLog entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "P_ProductVerLog")
public class PProductVerLog implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1874736829746975211L;
	// Fields

	private Long logId;
	private Long productId;
	private Long verId;
	private String verName;
	private Short verStatu;
	private String verReason;
	private Date verTime;

	// Constructors

	/** default constructor */
	public PProductVerLog() {
	}

	/** full constructor */
	public PProductVerLog(Long productId, Long verId, String verName,
			Short verStatu, String verReason, Date verTime) {
		this.productId = productId;
		this.verId = verId;
		this.verName = verName;
		this.verStatu = verStatu;
		this.verReason = verReason;
		this.verTime = verTime;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "LogID", unique = true, nullable = false)
	public Long getLogId() {
		return this.logId;
	}

	public void setLogId(Long logId) {
		this.logId = logId;
	}

	@Column(name = "ProductID")
	public Long getProductId() {
		return this.productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	@Column(name = "VerID")
	public Long getVerId() {
		return this.verId;
	}

	public void setVerId(Long verId) {
		this.verId = verId;
	}

	@Column(name = "VerName", length = 32)
	public String getVerName() {
		return this.verName;
	}

	public void setVerName(String verName) {
		this.verName = verName;
	}

	@Column(name = "VerStatu")
	public Short getVerStatu() {
		return this.verStatu;
	}

	public void setVerStatu(Short verStatu) {
		this.verStatu = verStatu;
	}

	@Column(name = "VerReason", length = 64)
	public String getVerReason() {
		return this.verReason;
	}

	public void setVerReason(String verReason) {
		this.verReason = verReason;
	}

	@Column(name = "VerTime", length = 19)
	public Date getVerTime() {
		return this.verTime;
	}

	public void setVerTime(Date verTime) {
		this.verTime = verTime;
	}

}