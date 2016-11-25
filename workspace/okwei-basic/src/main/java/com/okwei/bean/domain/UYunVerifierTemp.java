package com.okwei.bean.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * UYunVerifierTemp entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "U_YunVerifierTemp")
public class UYunVerifierTemp implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2179519478700730252L;
	// Fields

	private Integer id;
	private Long weiId;
	private String phone;
	private String name;
	private Integer province;
	private Integer city;
	private Integer district;
	private String resume;
	private String photoPic;
	private String idpic;
	private Long upVerWeiId;
	private Date createTime;
	private Date verTime;
	private String applyReason;
	private Short status;
	private Short type;
	private String failReason;

	// Constructors

	/** default constructor */
	public UYunVerifierTemp() {
	}

	/** full constructor */
	public UYunVerifierTemp(Long weiId, String phone, Integer province,
			Integer city, Integer district, String resume, String photoPic,
			String idpic, Long upVerWeiId, Date createTime,
			Date verTime) {
		this.weiId = weiId;
		this.phone = phone;
		this.province = province;
		this.city = city;
		this.district = district;
		this.resume = resume;
		this.photoPic = photoPic;
		this.idpic = idpic;
		this.upVerWeiId = upVerWeiId;
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

	@Column(name = "Resume", length = 256)
	public String getResume() {
		return this.resume;
	}

	public void setResume(String resume) {
		this.resume = resume;
	}

	@Column(name = "PhotoPic", length = 128)
	public String getPhotoPic() {
		return this.photoPic;
	}

	public void setPhotoPic(String photoPic) {
		this.photoPic = photoPic;
	}

	@Column(name = "IDpic", length = 128)
	public String getIdpic() {
		return this.idpic;
	}

	public void setIdpic(String idpic) {
		this.idpic = idpic;
	}

	@Column(name = "UpVerWeiID")
	public Long getUpVerWeiId() {
		return this.upVerWeiId;
	}

	public void setUpVerWeiId(Long upVerWeiId) {
		this.upVerWeiId = upVerWeiId;
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

	@Column(name="ApplyReason",length=128)
	public String getApplyReason() {
		return applyReason;
	}

	public void setApplyReason(String applyReason) {
		this.applyReason = applyReason;
	}

	@Column(name="Status")
	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	@Column(name="Type")
	public Short getType() {
		return type;
	}

	public void setType(Short type) {
		this.type = type;
	}

	@Column(name="Name",length=64)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name="FailReason",length=128)
	public String getFailReason() {
		return failReason;
	}

	public void setFailReason(String failReason) {
		this.failReason = failReason;
	}
	
	

}