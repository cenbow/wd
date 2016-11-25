package com.okwei.bean.domain;
 
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * UAgentApply entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "U_AgentApply")
public class UAgentApply implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -4449812064881086251L;
	private Integer applyId;
	private Integer demandId;
	private Long weiId;
	private Long supplyId;
	private Long verifier;
	private Long followVerifier;
	private String companyName;
	private String linkMan;
	private String phone;
	private String details;
	private String licenseImg;
	private String registNum;
	private Short state;
	private Date createTime;
	private Integer provice;
	private Integer city;
	private Integer area;
	private String address;
	private String remarks;
	private Double reward;
	private Short joinType;
	private Date auditTime;

	// Constructors

	/** default constructor */
	public UAgentApply() {
	}

	/** full constructor */
	public UAgentApply(Integer demandId, Long weiId, Long supplyId,
			Long verifier, Long followVerifier, String companyName,
			String linkMan, String phone, String details, String licenseImg,
			String registNum, Short state, Date createTime,
			Integer provice, Integer city, Integer area, String address,
			String remarks, Double reward, Short joinType, Date auditTime) {
		this.demandId = demandId;
		this.weiId = weiId;
		this.supplyId = supplyId;
		this.verifier = verifier;
		this.followVerifier = followVerifier;
		this.companyName = companyName;
		this.linkMan = linkMan;
		this.phone = phone;
		this.details = details;
		this.licenseImg = licenseImg;
		this.registNum = registNum;
		this.state = state;
		this.createTime = createTime;
		this.provice = provice;
		this.city = city;
		this.area = area;
		this.address = address;
		this.remarks = remarks;
		this.reward = reward;
		this.joinType = joinType;
		this.auditTime = auditTime;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "ApplyID", unique = true, nullable = false)
	public Integer getApplyId() {
		return this.applyId;
	}

	public void setApplyId(Integer applyId) {
		this.applyId = applyId;
	}

	@Column(name = "DemandID")
	public Integer getDemandId() {
		return this.demandId;
	}

	public void setDemandId(Integer demandId) {
		this.demandId = demandId;
	}

	@Column(name = "WeiID")
	public Long getWeiId() {
		return this.weiId;
	}

	public void setWeiId(Long weiId) {
		this.weiId = weiId;
	}

	@Column(name = "SupplyID")
	public Long getSupplyId() {
		return this.supplyId;
	}

	public void setSupplyId(Long supplyId) {
		this.supplyId = supplyId;
	}

	@Column(name = "Verifier")
	public Long getVerifier() {
		return this.verifier;
	}

	public void setVerifier(Long verifier) {
		this.verifier = verifier;
	}

	@Column(name = "FollowVerifier")
	public Long getFollowVerifier() {
		return this.followVerifier;
	}

	public void setFollowVerifier(Long followVerifier) {
		this.followVerifier = followVerifier;
	}

	@Column(name = "CompanyName", length = 128)
	public String getCompanyName() {
		return this.companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	@Column(name = "LinkMan", length = 32)
	public String getLinkMan() {
		return this.linkMan;
	}

	public void setLinkMan(String linkMan) {
		this.linkMan = linkMan;
	}

	@Column(name = "Phone", length = 16)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "Details", length = 1024)
	public String getDetails() {
		return this.details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	@Column(name = "LicenseImg", length = 128)
	public String getLicenseImg() {
		return this.licenseImg;
	}

	public void setLicenseImg(String licenseImg) {
		this.licenseImg = licenseImg;
	}

	@Column(name = "RegistNum", length = 64)
	public String getRegistNum() {
		return this.registNum;
	}

	public void setRegistNum(String registNum) {
		this.registNum = registNum;
	}

	@Column(name = "State")
	public Short getState() {
		return this.state;
	}

	public void setState(Short state) {
		this.state = state;
	}

	@Column(name = "CreateTime", length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

        @Column(name = "AuditTime", length = 19)
	public Date getAuditTime()
        {
            return auditTime;
        }
    
        public void setAuditTime(Date auditTime)
        {
            this.auditTime = auditTime;
        }

    @Column(name = "Provice")
	public Integer getProvice() {
		return this.provice;
	}

	public void setProvice(Integer provice) {
		this.provice = provice;
	}

	@Column(name = "City")
	public Integer getCity() {
		return this.city;
	}

	public void setCity(Integer city) {
		this.city = city;
	}

	@Column(name = "Area")
	public Integer getArea() {
		return this.area;
	}

	public void setArea(Integer area) {
		this.area = area;
	}

	@Column(name = "Address", length = 512)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "Remarks")
	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Column(name = "Reward", precision = 22, scale = 0)
	public Double getReward() {
		return this.reward;
	}

	public void setReward(Double reward) {
		this.reward = reward;
	}

        @Column(name = "JoinType")
        public Short getJoinType()
        {
            return joinType;
        }
    
        public void setJoinType(Short joinType)
        {
            this.joinType = joinType;
        }

}