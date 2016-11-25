package com.okwei.bean.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * UModifyPhoneLog entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "U_ModifyPhoneLog")
public class UModifyPhoneLog implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -8825533011019659716L;
	private Integer id;
	private String phone;
	private Long weiId;
	private Short type;
	private Date createTime;
	private String ip;
	private Short froms;
	private Short statu;

	// Constructors

	/** default constructor */
	public UModifyPhoneLog() {
	}

	/** minimal constructor */
	public UModifyPhoneLog(Long weiId) {
		this.weiId = weiId;
	}

	/** full constructor */
	public UModifyPhoneLog(String phone, Long weiId, Short type,
			Date createTime, String ip, Short froms) {
		this.phone = phone;
		this.weiId = weiId;
		this.type = type;
		this.createTime = createTime;
		this.ip = ip;
		this.froms = froms;
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

	@Column(name = "Phone", length = 30)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "WeiID", nullable = false)
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

	@Column(name = "CreateTime", length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "IP", length = 20)
	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@Column(name = "Froms")
	public Short getFrom() {
		return this.froms;
	}

	public void setFrom(Short froms) {
		this.froms = froms;
	}

	@Column(name="Statu")
	public Short getStatu() {
		return statu;
	}

	public void setStatu(Short statu) {
		this.statu = statu;
	}

	
}