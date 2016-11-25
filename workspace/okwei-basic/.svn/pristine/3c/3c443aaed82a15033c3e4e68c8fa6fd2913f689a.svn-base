package com.okwei.bean.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * UYunSupplierTemp entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "U_YunSupplierTemp")
public class UYunSupplierTemp implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7972411477374118995L;
	// Fields

	private Integer id;
	private Long weiId;
	private String phone;
	private String chargeMan;
	private String qq;
	private String companyName;
	private Integer province;
	private Integer city;
	private Integer district;
	private String idno;
	private String idpic;
	private String buspic;
	private String busno;
	private Short bustype;
	private Long verWeiId;
	private Date createTime;
	private Date verTime;
	private String level;
	private Integer followCount;
	private String failReason;
	private Short status;

	// Constructors

	/** default constructor */
	public UYunSupplierTemp() {
	}

	/** full constructor */
	public UYunSupplierTemp(Long weiId, String phone, String chargeMan,
			String qq, String companyName, Integer province, Integer city,
			Integer district, String idno, String idpic, String buspic,
			String busno, Short bustype, Long verWeiId, Date createTime,
			Date verTime) {
		this.weiId = weiId;
		this.phone = phone;
		this.chargeMan = chargeMan;
		this.qq = qq;
		this.companyName = companyName;
		this.province = province;
		this.city = city;
		this.district = district;
		this.idno = idno;
		this.idpic = idpic;
		this.buspic = buspic;
		this.busno = busno;
		this.bustype = bustype;
		this.verWeiId = verWeiId;
		this.createTime = createTime;
		this.verTime = verTime;
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

	@Column(name = "WeiID")
	public Long getWeiId() {
		return this.weiId;
	}

	public void setWeiId(Long weiId) {
		this.weiId = weiId;
	}

	@Column(name = "Phone", length = 16)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "ChargeMan", length = 32)
	public String getChargeMan() {
		return this.chargeMan;
	}

	public void setChargeMan(String chargeMan) {
		this.chargeMan = chargeMan;
	}

	@Column(name = "QQ", length = 16)
	public String getQq() {
		return this.qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	@Column(name = "CompanyName", length = 128)
	public String getCompanyName() {
		return this.companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
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

	@Column(name = "District")
	public Integer getDistrict() {
		return this.district;
	}

	public void setDistrict(Integer district) {
		this.district = district;
	}

	@Column(name = "IDno", length = 32)
	public String getIdno() {
		return this.idno;
	}

	public void setIdno(String idno) {
		this.idno = idno;
	}

	@Column(name = "IDpic", length = 128)
	public String getIdpic() {
		return this.idpic;
	}

	public void setIdpic(String idpic) {
		this.idpic = idpic;
	}

	@Column(name = "Buspic", length = 128)
	public String getBuspic() {
		return this.buspic;
	}

	public void setBuspic(String buspic) {
		this.buspic = buspic;
	}

	@Column(name = "Busno", length = 64)
	public String getBusno() {
		return this.busno;
	}

	public void setBusno(String busno) {
		this.busno = busno;
	}

	@Column(name = "Bustype")
	public Short getBustype() {
		return this.bustype;
	}

	public void setBustype(Short bustype) {
		this.bustype = bustype;
	}

	@Column(name = "VerWeiID")
	public Long getVerWeiId() {
		return this.verWeiId;
	}

	public void setVerWeiId(Long verWeiId) {
		this.verWeiId = verWeiId;
	}

	@Column(name = "CreateTime", length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "VerTime", length = 19)
	public Date getVerTime() {
		return this.verTime;
	}

	public void setVerTime(Date verTime) {
		this.verTime = verTime;
	}
	@Column(name = "Level", length = 8)
	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	@Column(name="FollowCount",length=11)
	public Integer getFollowCount() {
		return followCount;
	}

	public void setFollowCount(Integer followCount) {
		this.followCount = followCount;
	}
	@Column(name="FailReason",length=128)
	public String getFailReason() {
		return failReason;
	}

	public void setFailReason(String failReason) {
		this.failReason = failReason;
	}
	@Column(name="Status")
	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}
	
	

}