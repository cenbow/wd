package com.okwei.bean.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * UVerifier entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "U_Verifier")
public class UVerifier extends BaseEntity {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long weiId;
	private String name;
	private String idcard;
	private Integer province;
	private Integer city;
	private Integer district;
	private Integer street;
	private String addressDetail;
	private String facePic;
	private String idcardPic;
	private Short type;
	private Date createTime;

	@Column(name = "Phone")
	private String phone;

	// Constructors

	/** default constructor */
	public UVerifier() {
	}

	/** full constructor */
	public UVerifier(String name, String idcard, Integer province, Integer city, Integer district, Integer street, String addressDetail, String facePic,
			String idcardPic, Short type, Short state, Date createTime) {
		this.name = name;
		this.idcard = idcard;
		this.province = province;
		this.city = city;
		this.district = district;
		this.street = street;
		this.addressDetail = addressDetail;
		this.facePic = facePic;
		this.idcardPic = idcardPic;
		this.type = type;
		this.createTime = createTime;
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

	@Column(name = "Name", length = 32)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "IDCard", length = 32)
	public String getIdcard() {
		return this.idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
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

	@Column(name = "Street")
	public Integer getStreet() {
		return this.street;
	}

	public void setStreet(Integer street) {
		this.street = street;
	}

	@Column(name = "AddressDetail", length = 128)
	public String getAddressDetail() {
		return this.addressDetail;
	}

	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}

	@Column(name = "FacePic", length = 128)
	public String getFacePic() {
		return this.facePic;
	}

	public void setFacePic(String facePic) {
		this.facePic = facePic;
	}

	@Column(name = "IDCardPic", length = 128)
	public String getIdcardPic() {
		return this.idcardPic;
	}

	public void setIdcardPic(String idcardPic) {
		this.idcardPic = idcardPic;
	}

	@Column(name = "Type")
	public Short getType() {
		return this.type;
	}

	public void setType(Short type) {
		this.type = type;
	}

	@Column(name = "CreateTime", length = 0)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}