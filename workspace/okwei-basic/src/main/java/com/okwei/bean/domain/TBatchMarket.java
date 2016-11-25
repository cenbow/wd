package com.okwei.bean.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TBatchMarket entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_BatchMarket")
public class TBatchMarket implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer bmid;
	private Integer province;
	private Integer city;
	private Integer district;
	private Integer street;
	private String xy;
	private String geoHash;
	private String name;
	private String address;
	private String image;
	private String busContent;
	private String marketDes;
	private Long creater;
	private Short status;
	private Date createTime;
	private Integer maxVerPort;
	private Short isAllIn;
	private Short isFree;
	private Long marketWeiId;
	private Integer shopCount;
	private String marketDec;
	private String marketDetail;
	private Long marketVerWeiId;
	private String marketDetailPre;
	private Double bond;

	// Constructors


	/** default constructor */
	public TBatchMarket() {
	}

	/** full constructor */
	public TBatchMarket(Integer province, Integer city, Integer district,
			Integer street, String xy, String geoHash, String name,
			String address, String image, String busContent, String marketDes,
			Long creater, Short status, Date createTime,
			Integer maxVerPort, Short isAllIn, Short isFree, Long marketWeiId,
			Integer shopCount, String marketDec, String marketDetail,
			Long marketVerWeiId) {
		this.province = province;
		this.city = city;
		this.district = district;
		this.street = street;
		this.xy = xy;
		this.geoHash = geoHash;
		this.name = name;
		this.address = address;
		this.image = image;
		this.busContent = busContent;
		this.marketDes = marketDes;
		this.creater = creater;
		this.status = status;
		this.createTime = createTime;
		this.maxVerPort = maxVerPort;
		this.isAllIn = isAllIn;
		this.isFree = isFree;
		this.marketWeiId = marketWeiId;
		this.shopCount = shopCount;
		this.marketDec = marketDec;
		this.marketDetail = marketDetail;
		this.marketVerWeiId = marketVerWeiId;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "BMID", unique = true, nullable = false)
	public Integer getBmid() {
		return this.bmid;
	}

	public void setBmid(Integer bmid) {
		this.bmid = bmid;
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

	@Column(name = "XY", length = 32)
	public String getXy() {
		return this.xy;
	}

	public void setXy(String xy) {
		this.xy = xy;
	}

	@Column(name = "GeoHash", length = 32)
	public String getGeoHash() {
		return this.geoHash;
	}

	public void setGeoHash(String geoHash) {
		this.geoHash = geoHash;
	}

	@Column(name = "Name", length = 64)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "Address", length = 200)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "Image", length = 200)
	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Column(name = "BusContent", length = 128)
	public String getBusContent() {
		return this.busContent;
	}

	public void setBusContent(String busContent) {
		this.busContent = busContent;
	}

	@Column(name = "MarketDes", length = 512)
	public String getMarketDes() {
		return this.marketDes;
	}

	public void setMarketDes(String marketDes) {
		this.marketDes = marketDes;
	}

	@Column(name = "Creater")
	public Long getCreater() {
		return this.creater;
	}

	public void setCreater(Long creater) {
		this.creater = creater;
	}

	@Column(name = "Status")
	public Short getStatus() {
		return this.status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	@Column(name = "CreateTime", length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "MaxVerPort")
	public Integer getMaxVerPort() {
		return this.maxVerPort;
	}

	public void setMaxVerPort(Integer maxVerPort) {
		this.maxVerPort = maxVerPort;
	}

	@Column(name = "IsAllIn")
	public Short getIsAllIn() {
		return this.isAllIn;
	}

	public void setIsAllIn(Short isAllIn) {
		this.isAllIn = isAllIn;
	}

	@Column(name = "IsFree")
	public Short getIsFree() {
		return this.isFree;
	}

	public void setIsFree(Short isFree) {
		this.isFree = isFree;
	}

	@Column(name = "MarketWeiID")
	public Long getMarketWeiId() {
		return this.marketWeiId;
	}

	public void setMarketWeiId(Long marketWeiId) {
		this.marketWeiId = marketWeiId;
	}

	@Column(name = "ShopCount")
	public Integer getShopCount() {
		return this.shopCount;
	}

	public void setShopCount(Integer shopCount) {
		this.shopCount = shopCount;
	}

	@Column(name = "MarketDec", length = 256)
	public String getMarketDec() {
		return this.marketDec;
	}

	public void setMarketDec(String marketDec) {
		this.marketDec = marketDec;
	}

	@Column(name = "MarketDetail", length = 1024)
	public String getMarketDetail() {
		return this.marketDetail;
	}

	public void setMarketDetail(String marketDetail) {
		this.marketDetail = marketDetail;
	}

	@Column(name = "MarketVerWeiID")
	public Long getMarketVerWeiId() {
		return this.marketVerWeiId;
	}

	public void setMarketVerWeiId(Long marketVerWeiId) {
		this.marketVerWeiId = marketVerWeiId;
	}
	@Column(name = "MarketDetailPre", length = 1024)
	public String getMarketDetailPre() {
		return marketDetailPre;
	}

	public void setMarketDetailPre(String marketDetailPre) {
		this.marketDetailPre = marketDetailPre;
	}
    @Column(name="Bond",precision = 18)
	public Double getBond() {
		return bond;
	}

	public void setBond(Double bond) {
		this.bond = bond;
	}
}