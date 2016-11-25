package com.okwei.bean.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * UPublicBanks entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "U_PublicBanks")
public class UPublicBanks implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4428436067019108775L;
	// Fields

	private Integer pid;
	private Long weiId;
	private String name;
	private String idcard;
	private String banckName;
	private String banckNo;
	private Integer province;
	private Integer city;
	private String branchName;
	private String license;
	private Date createTime;
	private Short state;
	private String reason;
	private Date processTime;
	private Short supplierType;
	private Short isBatchSupplier;
	private Short isYunSupplier;

	// Constructors

	/** default constructor */
	public UPublicBanks() {
	}

	/** full constructor */
	public UPublicBanks(Long weiId, String name, String idcard,
			String banckName, String banckNo, Integer province, Integer city,
			String branchName, String license, Date createTime,
			Short state, String reason, Date processTime,
			Short supplierType, Short isBatchSupplier, Short isYunSupplier) {
		this.weiId = weiId;
		this.name = name;
		this.idcard = idcard;
		this.banckName = banckName;
		this.banckNo = banckNo;
		this.province = province;
		this.city = city;
		this.branchName = branchName;
		this.license = license;
		this.createTime = createTime;
		this.state = state;
		this.reason = reason;
		this.processTime = processTime;
		this.supplierType = supplierType;
		this.isBatchSupplier = isBatchSupplier;
		this.isYunSupplier = isYunSupplier;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "PID", unique = true, nullable = false)
	public Integer getPid() {
		return this.pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	@Column(name = "WeiID")
	public Long getWeiId() {
		return this.weiId;
	}

	public void setWeiId(Long weiId) {
		this.weiId = weiId;
	}

	@Column(name = "Name", length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "IDCard", length = 50)
	public String getIdcard() {
		return this.idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	@Column(name = "BanckName", length = 50)
	public String getBanckName() {
		return this.banckName;
	}

	public void setBanckName(String banckName) {
		this.banckName = banckName;
	}

	@Column(name = "BanckNo", length = 50)
	public String getBanckNo() {
		return this.banckNo;
	}

	public void setBanckNo(String banckNo) {
		this.banckNo = banckNo;
	}

	@Column(name = "Province")
	public Integer getProvince() {
		return this.province;
	}

	public void setProvince(Integer province) {
		this.province = province;
	}

	@Column(name = "City")
	public Integer getCity() {
		return this.city;
	}

	public void setCity(Integer city) {
		this.city = city;
	}

	@Column(name = "BranchName", length = 50)
	public String getBranchName() {
		return this.branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	@Column(name = "License", length = 218)
	public String getLicense() {
		return this.license;
	}

	public void setLicense(String license) {
		this.license = license;
	}

	@Column(name = "CreateTime", length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "State")
	public Short getState() {
		return this.state;
	}

	public void setState(Short state) {
		this.state = state;
	}

	@Column(name = "Reason", length = 50)
	public String getReason() {
		return this.reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@Column(name = "ProcessTime", length = 19)
	public Date getProcessTime() {
		return this.processTime;
	}

	public void setProcessTime(Date processTime) {
		this.processTime = processTime;
	}

	@Column(name = "SupplierType")
	public Short getSupplierType() {
		return this.supplierType;
	}

	public void setSupplierType(Short supplierType) {
		this.supplierType = supplierType;
	}

	@Column(name = "IsBatchSupplier")
	public Short getIsBatchSupplier() {
		return this.isBatchSupplier;
	}

	public void setIsBatchSupplier(Short isBatchSupplier) {
		this.isBatchSupplier = isBatchSupplier;
	}

	@Column(name = "IsYunSupplier")
	public Short getIsYunSupplier() {
		return this.isYunSupplier;
	}

	public void setIsYunSupplier(Short isYunSupplier) {
		this.isYunSupplier = isYunSupplier;
	}

}