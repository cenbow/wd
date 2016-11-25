package com.okwei.bean.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TVerificationId entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_VerificationID")
public class TVerificationId implements java.io.Serializable {

	// Fields

	private Integer verificationId;
	private String vcode;
	private Long weiNo;
	private Short type;
	private String verificationtext;
	private String userIp;
	private Date getTime;
	private Date endTime;
	private Short status;

	// Constructors

	/** default constructor */
	public TVerificationId() {
	}

	/** full constructor */
	public TVerificationId(String vcode, Long weiNo, Short type,
			String verificationtext, String userIp, Date getTime,
			Date endTime, Short status) {
		this.vcode = vcode;
		this.weiNo = weiNo;
		this.type = type;
		this.verificationtext = verificationtext;
		this.userIp = userIp;
		this.getTime = getTime;
		this.endTime = endTime;
		this.status = status;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "VerificationID", unique = true, nullable = false)
	public Integer getVerificationId() {
		return this.verificationId;
	}

	public void setVerificationId(Integer verificationId) {
		this.verificationId = verificationId;
	}

	@Column(name = "VCode", length = 16)
	public String getVcode() {
		return this.vcode;
	}

	public void setVcode(String vcode) {
		this.vcode = vcode;
	}

	@Column(name = "WeiNo")
	public Long getWeiNo() {
		return this.weiNo;
	}

	public void setWeiNo(Long weiNo) {
		this.weiNo = weiNo;
	}

	@Column(name = "Type")
	public Short getType() {
		return this.type;
	}

	public void setType(Short type) {
		this.type = type;
	}

	@Column(name = "Verificationtext", length = 64)
	public String getVerificationtext() {
		return this.verificationtext;
	}

	public void setVerificationtext(String verificationtext) {
		this.verificationtext = verificationtext;
	}

	@Column(name = "UserIP", length = 32)
	public String getUserIp() {
		return this.userIp;
	}

	public void setUserIp(String userIp) {
		this.userIp = userIp;
	}

	@Column(name = "GetTime", length = 19)
	public Date getGetTime() {
		return this.getTime;
	}

	public void setGetTime(Date getTime) {
		this.getTime = getTime;
	}

	@Column(name = "EndTime", length = 19)
	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	@Column(name = "Status")
	public Short getStatus() {
		return this.status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

}