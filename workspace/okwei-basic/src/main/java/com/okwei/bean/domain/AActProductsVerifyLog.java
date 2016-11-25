package com.okwei.bean.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * AActProductsVerifyLog entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "A_ActProductsVerifyLog")
public class AActProductsVerifyLog implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -1159569494306703688L;
	private Integer id;
	private Long proActId;
	private Long verifier;
	private String content;
	private Date createTime;

	// Constructors

	/** default constructor */
	public AActProductsVerifyLog() {
	}

	/** minimal constructor */
	public AActProductsVerifyLog(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public AActProductsVerifyLog(Integer id, Long proActId, Long verifier,
			String content, Date createTime) {
		this.id = id;
		this.proActId = proActId;
		this.verifier = verifier;
		this.content = content;
		this.createTime = createTime;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "ProActID")
	public Long getProActId() {
		return this.proActId;
	}

	public void setProActId(Long proActId) {
		this.proActId = proActId;
	}

	@Column(name = "Verifier")
	public Long getVerifier() {
		return this.verifier;
	}

	public void setVerifier(Long verifier) {
		this.verifier = verifier;
	}

	@Column(name = "Content", length = 128)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "CreateTime", length = 0)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}