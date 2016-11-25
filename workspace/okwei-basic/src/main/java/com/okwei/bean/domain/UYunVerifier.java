package com.okwei.bean.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * UYunVerifier entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "U_YunVerifier")
public class UYunVerifier implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 5944972984719519935L;
	private Long weiId;
	private Long supperVerifier;
	private Long supperAdvisor;
	private Long agentSupplier;
	private Short type;
	private Date createTime;
	private Short status;
	private String applyReason;
	private Double bond;
	private Date inTime;
	private String qq;
	private String resume;
	private Date verTime;
	private Short jxresult;
	private Short zsresult;
	private Short isActive;
	private Integer supAllotCount;
	private Date updateTime;
	private Integer verAllotCount;
	private Short inOrOut;
	private Double payAmount;

	// Constructors

	/** default constructor */
	public UYunVerifier() {
	}

	/** full constructor */
	public UYunVerifier(Long supperVerifier, Long supperAdvisor,
			Long agentSupplier, Short type, Date createTime, Short status,
			String applyReason, Double bond, Date inTime, String qq,
			String resume, Date verTime, Short jxresult, Short zsresult,
			Short isActive, Integer supAllotCount, Date updateTime,
			Integer verAllotCount, Short inOrOut) {
		this.supperVerifier = supperVerifier;
		this.supperAdvisor = supperAdvisor;
		this.agentSupplier = agentSupplier;
		this.type = type;
		this.createTime = createTime;
		this.status = status;
		this.applyReason = applyReason;
		this.bond = bond;
		this.inTime = inTime;
		this.qq = qq;
		this.resume = resume;
		this.verTime = verTime;
		this.jxresult = jxresult;
		this.zsresult = zsresult;
		this.isActive = isActive;
		this.supAllotCount = supAllotCount;
		this.updateTime = updateTime;
		this.verAllotCount = verAllotCount;
		this.inOrOut = inOrOut;
	}

	// Property accessors
	@Id
	@Column(name = "WeiID", unique = true, nullable = false)
	public Long getWeiId() {
		return this.weiId;
	}

	public void setWeiId(Long weiId) {
		this.weiId = weiId;
	}

	@Column(name = "SupperVerifier")
	public Long getSupperVerifier() {
		return this.supperVerifier;
	}

	public void setSupperVerifier(Long supperVerifier) {
		this.supperVerifier = supperVerifier;
	}

	@Column(name = "SupperAdvisor")
	public Long getSupperAdvisor() {
		return this.supperAdvisor;
	}

	public void setSupperAdvisor(Long supperAdvisor) {
		this.supperAdvisor = supperAdvisor;
	}

	@Column(name = "AgentSupplier")
	public Long getAgentSupplier() {
		return this.agentSupplier;
	}

	public void setAgentSupplier(Long agentSupplier) {
		this.agentSupplier = agentSupplier;
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

	@Column(name = "Status")
	public Short getStatus() {
		return this.status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	@Column(name = "ApplyReason", length = 128)
	public String getApplyReason() {
		return this.applyReason;
	}

	public void setApplyReason(String applyReason) {
		this.applyReason = applyReason;
	}

	@Column(name = "Bond", precision = 22, scale = 0)
	public Double getBond() {
		return this.bond;
	}

	public void setBond(Double bond) {
		this.bond = bond;
	}

	@Column(name = "InTime", length = 19)
	public Date getInTime() {
		return this.inTime;
	}

	public void setInTime(Date inTime) {
		this.inTime = inTime;
	}

	@Column(name = "QQ", length = 16)
	public String getQq() {
		return this.qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	@Column(name = "Resume", length = 128)
	public String getResume() {
		return this.resume;
	}

	public void setResume(String resume) {
		this.resume = resume;
	}

	@Column(name = "VerTime", length = 19)
	public Date getVerTime() {
		return this.verTime;
	}

	public void setVerTime(Date verTime) {
		this.verTime = verTime;
	}

	@Column(name = "JXResult")
	public Short getJxresult() {
		return this.jxresult;
	}

	public void setJxresult(Short jxresult) {
		this.jxresult = jxresult;
	}

	@Column(name = "ZSResult")
	public Short getZsresult() {
		return this.zsresult;
	}

	public void setZsresult(Short zsresult) {
		this.zsresult = zsresult;
	}

	@Column(name = "IsActive")
	public Short getIsActive() {
		return this.isActive;
	}

	public void setIsActive(Short isActive) {
		this.isActive = isActive;
	}

	@Column(name = "SupAllotCount")
	public Integer getSupAllotCount() {
		return this.supAllotCount;
	}

	public void setSupAllotCount(Integer supAllotCount) {
		this.supAllotCount = supAllotCount;
	}

	@Column(name = "updateTime", length = 19)
	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name = "VerAllotCount")
	public Integer getVerAllotCount() {
		return this.verAllotCount;
	}

	public void setVerAllotCount(Integer verAllotCount) {
		this.verAllotCount = verAllotCount;
	}

	@Column(name = "InOrOut")
	public Short getInOrOut() {
		return this.inOrOut;
	}

	public void setInOrOut(Short inOrOut) {
		this.inOrOut = inOrOut;
	}
	@Column(name = "PayAmount", precision = 18, scale = 2)
	public Double getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(Double payAmount) {
		this.payAmount = payAmount;
	}

}