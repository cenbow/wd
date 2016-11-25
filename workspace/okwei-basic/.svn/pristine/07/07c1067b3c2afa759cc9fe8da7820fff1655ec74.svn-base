package com.okwei.bean.domain;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Market entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "market")
public class TMarket extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6613828557664937290L;
	// Fields

	private Integer id;
	private Integer districtint;
	private Integer cityint;
	private Integer provinceint;
	private String geohash;
	private String xy;
	private String address;
	private String marketname;
	private String district;
	private String city;
	private String province;

	// Constructors

	/** default constructor */
	public TMarket() {
	}

	/** full constructor */
	public TMarket(Integer districtint, Integer cityint, Integer provinceint, String geohash, String xy, String address, String marketname, String district,
			String city, String province) {
		this.districtint = districtint;
		this.cityint = cityint;
		this.provinceint = provinceint;
		this.geohash = geohash;
		this.xy = xy;
		this.address = address;
		this.marketname = marketname;
		this.district = district;
		this.city = city;
		this.province = province;
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

	@Column(name = "districtint")
	public Integer getDistrictint() {
		return this.districtint;
	}

	public void setDistrictint(Integer districtint) {
		this.districtint = districtint;
	}

	@Column(name = "cityint")
	public Integer getCityint() {
		return this.cityint;
	}

	public void setCityint(Integer cityint) {
		this.cityint = cityint;
	}

	@Column(name = "provinceint")
	public Integer getProvinceint() {
		return this.provinceint;
	}

	public void setProvinceint(Integer provinceint) {
		this.provinceint = provinceint;
	}

	@Column(name = "geohash")
	public String getGeohash() {
		return this.geohash;
	}

	public void setGeohash(String geohash) {
		this.geohash = geohash;
	}

	@Column(name = "xy")
	public String getXy() {
		return this.xy;
	}

	public void setXy(String xy) {
		this.xy = xy;
	}

	@Column(name = "address")
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "marketname")
	public String getMarketname() {
		return this.marketname;
	}

	public void setMarketname(String marketname) {
		this.marketname = marketname;
	}

	@Column(name = "district")
	public String getDistrict() {
		return this.district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	@Column(name = "city")
	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name = "province")
	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

}