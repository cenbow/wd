package com.okwei.bean.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * AppPscoreKing entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "APP_PScoreKing")
public class AppPscoreKing implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6056265913779397633L;
	// Fields

	private Long kingId;
	private Long productId;
	private Long supplyWeiid;
	private Short supplyType;
	private Integer mid;
	private Integer areaId;
	private Double score;
	private Short type;
	private Date dateType;
	private Integer province;
	private Integer city;
	private Integer district;

	// Constructors

	/** default constructor */
	public AppPscoreKing() {
	}

	/** full constructor */
	public AppPscoreKing(Long productId, Long supplyWeiid, Short supplyType,
			Integer mid, Integer areaId, Double score, Date dateType,Short type,Integer province,Integer city,Integer district) {
		this.productId = productId;
		this.supplyWeiid = supplyWeiid;
		this.supplyType = supplyType;
		this.mid = mid;
		this.areaId = areaId;
		this.score = score;
		this.dateType = dateType;
		this.type=type;
		this.province=province;
		this.city=city;
		this.district=district;
		
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "KingID", unique = true, nullable = false)
	public Long getKingId() {
		return this.kingId;
	}

	public void setKingId(Long kingId) {
		this.kingId = kingId;
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

	@Column(name = "AreaId")
	public Integer getAreaId() {
		return this.areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	@Column(name = "Score", precision = 18)
	public Double getScore() {
		return this.score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	@Column(name = "DateType", length = 19)
	public Date getDateType() {
		return this.dateType;
	}

	public void setDateType(Date dateType) {
		this.dateType = dateType;
	}

	@Column(name = "Type")
	public Short getType() {
		return this.type;
	}
	public void setType(Short type) {
		this.type = type;
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
}