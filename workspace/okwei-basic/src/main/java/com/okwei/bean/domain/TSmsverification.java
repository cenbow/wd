package com.okwei.bean.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * TSmsverification entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_SMSVerification")
public class TSmsverification implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -8328682845940006127L;
	private Integer id;
	private String content;
	private String phone;
	private Long weiId;
	private Short type;
	private Date createTime;
	private Short status;

	// Constructors

	/** default constructor */
	public TSmsverification() {
	}

	/** full constructor */
	public TSmsverification(String content, String phone, Long weiId,
			Short type, Date createTime, Short status) {
		this.content = content;
		this.phone = phone;
		this.weiId = weiId;
		this.type = type;
		this.createTime = createTime;
		this.status = status;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "ID", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "Content", length = 512)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "Phone", length = 32)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "WeiID")
	public Long getWeiId() {
		return this.weiId;
	}

	public void setWeiId(Long weiId) {
		this.weiId = weiId;
	}

	@Column(name = "Type")
	public Short getType() {
		return this.type;
	}

	public void setType(Short type) {
		this.type = type;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "CreateTime", length = 10)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "Status")
	public Short getStatus() {
		return this.status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

}