package com.okwei.bean.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TTasteApply entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_TasteApply")
public class TTasteApply implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8298177105907019002L;
	// Fields

	private Integer id;
	private String name;
	private String phone;
	private String email;
	private Short isMyself;
	private String relationship;
	private Double highNum;
	private Double lowNum;
	private Short isHighXueYa;
	private Double xueTangNum;
	private Short isHighXueTang;
	private Double xueZhiNum;
	private Short isHighXueZhi;
	private Short isZhongFeng;
	private String imageOne;
	private String imageTwo;
	private String imageThree;
	private Integer province;
	private Integer city;
	private Integer district;
	private String detailAddress;
	private Date createTime;

	// Constructors

	/** default constructor */
	public TTasteApply() {
	}

	/** full constructor */
	public TTasteApply(String name, String phone, String email, Short isMyself,
			Double highNum, Double lowNum, Short isHighXueYa,
			Double xueTangNum, Short isHighXueTang, Double xueZhiNum,
			Short isHighXueZhi, Short isZhongFeng, String imageOne,
			String imageTwo, String imageThree, Integer province, Integer city,
			Integer district, String detailAddress, Date createTime) {
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.isMyself = isMyself;
		this.highNum = highNum;
		this.lowNum = lowNum;
		this.isHighXueYa = isHighXueYa;
		this.xueTangNum = xueTangNum;
		this.isHighXueTang = isHighXueTang;
		this.xueZhiNum = xueZhiNum;
		this.isHighXueZhi = isHighXueZhi;
		this.isZhongFeng = isZhongFeng;
		this.imageOne = imageOne;
		this.imageTwo = imageTwo;
		this.imageThree = imageThree;
		this.province = province;
		this.city = city;
		this.district = district;
		this.detailAddress = detailAddress;
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

	@Column(name = "Name", length = 64)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "Phone", length = 24)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "Email", length = 32)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "IsMyself")
	public Short getIsMyself() {
		return this.isMyself;
	}

	public void setIsMyself(Short isMyself) {
		this.isMyself = isMyself;
	}

	@Column(name = "HighNum", precision = 10)
	public Double getHighNum() {
		return this.highNum;
	}

	public void setHighNum(Double highNum) {
		this.highNum = highNum;
	}

	@Column(name = "LowNum", precision = 10)
	public Double getLowNum() {
		return this.lowNum;
	}

	public void setLowNum(Double lowNum) {
		this.lowNum = lowNum;
	}

	@Column(name = "IsHighXueYa")
	public Short getIsHighXueYa() {
		return this.isHighXueYa;
	}

	public void setIsHighXueYa(Short isHighXueYa) {
		this.isHighXueYa = isHighXueYa;
	}

	@Column(name = "XueTangNum", precision = 10)
	public Double getXueTangNum() {
		return this.xueTangNum;
	}

	public void setXueTangNum(Double xueTangNum) {
		this.xueTangNum = xueTangNum;
	}

	@Column(name = "IsHighXueTang")
	public Short getIsHighXueTang() {
		return this.isHighXueTang;
	}

	public void setIsHighXueTang(Short isHighXueTang) {
		this.isHighXueTang = isHighXueTang;
	}

	@Column(name = "XueZhiNum", precision = 10)
	public Double getXueZhiNum() {
		return this.xueZhiNum;
	}

	public void setXueZhiNum(Double xueZhiNum) {
		this.xueZhiNum = xueZhiNum;
	}

	@Column(name = "IsHighXueZhi")
	public Short getIsHighXueZhi() {
		return this.isHighXueZhi;
	}

	public void setIsHighXueZhi(Short isHighXueZhi) {
		this.isHighXueZhi = isHighXueZhi;
	}

	@Column(name = "IsZhongFeng")
	public Short getIsZhongFeng() {
		return this.isZhongFeng;
	}

	public void setIsZhongFeng(Short isZhongFeng) {
		this.isZhongFeng = isZhongFeng;
	}

	@Column(name = "ImageOne", length = 128)
	public String getImageOne() {
		return this.imageOne;
	}

	public void setImageOne(String imageOne) {
		this.imageOne = imageOne;
	}

	@Column(name = "ImageTwo", length = 128)
	public String getImageTwo() {
		return this.imageTwo;
	}

	public void setImageTwo(String imageTwo) {
		this.imageTwo = imageTwo;
	}

	@Column(name = "ImageThree", length = 128)
	public String getImageThree() {
		return this.imageThree;
	}

	public void setImageThree(String imageThree) {
		this.imageThree = imageThree;
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

	@Column(name = "DetailAddress", length = 128)
	public String getDetailAddress() {
		return this.detailAddress;
	}

	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}

	@Column(name = "CreateTime", length = 0)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "Relationship", length = 32)
	public String getRelationship() {
		return this.relationship;
	}

	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}
}