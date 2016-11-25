package com.okwei.bean.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * AppPscoreStatics entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "APP_PScoreStatics")
public class AppPscoreStatics implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -1904441497553240731L;
	private Long scoreId;
	private Long productId;
	private Long supplyWeiid;
	private Short supplyType;
	private Integer mid;
	private Integer areaId;
	private Short type;
	private Double score;
	private Short sort;
	
	private Integer province;
	private Integer city;
	private Integer district;
	private Integer brandId;
	
	private Integer classOneID;
	private Integer classTwoID;
	private Integer classThreeID;

	// Constructors

	/** default constructor */
	public AppPscoreStatics() {
	}

	/** full constructor */
	public AppPscoreStatics(Long productId, Long supplyWeiid, Short supplyType,
			Integer mid, Integer areaId, Short type, Double score, Short sort) {
		this.productId = productId;
		this.supplyWeiid = supplyWeiid;
		this.supplyType = supplyType;
		this.mid = mid;
		this.areaId = areaId;
		this.type = type;
		this.score = score;
		this.sort = sort;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "ScoreID", unique = true, nullable = false)
	public Long getScoreId() {
		return this.scoreId;
	}

	public void setScoreId(Long scoreId) {
		this.scoreId = scoreId;
	}

	@Column(name = "ProductId")
	public Long getProductId() {
		return this.productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	@Column(name = "SupplyWeiid")
	public Long getSupplyWeiid() {
		return this.supplyWeiid;
	}

	public void setSupplyWeiid(Long supplyWeiid) {
		this.supplyWeiid = supplyWeiid;
	}

	@Column(name = "SupplyType")
	public Short getSupplyType() {
		return this.supplyType;
	}

	public void setSupplyType(Short supplyType) {
		this.supplyType = supplyType;
	}

	@Column(name = "Mid")
	public Integer getMid() {
		return this.mid;
	}

	public void setMid(Integer mid) {
		this.mid = mid;
	}

	@Column(name = "AreaID")
	public Integer getAreaId() {
		return this.areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	@Column(name = "Type")
	public Short getType() {
		return this.type;
	}

	public void setType(Short type) {
		this.type = type;
	}

	@Column(name = "Score", precision = 18)
	public Double getScore() {
		return this.score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	@Column(name = "Sort")
	public Short getSort() {
		return this.sort;
	}

	public void setSort(Short sort) {
		this.sort = sort;
	}

	@Column(name = "Province")
	public Integer getProvince() {
		return this.province;
	}

	public void setProvince(Integer Province) {
		this.province = Province;
	}

	@Column(name = "City")
	public Integer getCity() {
		return this.city;
	}

	public void setCity(Integer City) {
		this.city = City;
	}

	@Column(name = "District")
	public Integer getDistrict() {
		return this.district;
	}

	public void setDistrict(Integer District) {
		this.district = District;
	}
	@Column(name = "ClassOneID")
	public Integer getClassOneID() {
		return classOneID;
	}

	public void setClassOneID(Integer classOneID) {
		this.classOneID = classOneID;
	}
	@Column(name = "ClassTwoID")
	public Integer getClassTwoID() {
		return classTwoID;
	}

	public void setClassTwoID(Integer classTwoID) {
		this.classTwoID = classTwoID;
	}

	@Column(name = "ClassThreeID")
	public Integer getClassThreeID() {
		return classThreeID;
	}

	public void setClassThreeID(Integer classThreeID) {
		this.classThreeID = classThreeID;
	}
	
	@Column(name = "BrandID")
	public Integer getBrandID() {
		return brandId;
	}

	public void setBrandID(Integer brandId) {
		this.brandId = brandId;
	}

}