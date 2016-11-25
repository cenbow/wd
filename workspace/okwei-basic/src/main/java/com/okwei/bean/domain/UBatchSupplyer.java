package com.okwei.bean.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * UBatchSupplyer entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "U_BatchSupplyer")
public class UBatchSupplyer implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4813820217584235935L;
	// Fields

	private Long weiId;
	private Integer bmid;
	private Integer province;
	private Integer city;
	private Integer district;
	private Integer street;
	private String addrDetail;
	private String shopName;
	private String shopPosition;
	private String shopPic;
	private Integer saleType;
	private Long agent;
	private Long verifier;
	private Long upVerifier;
	private Long portVerifer;
	private Long companyWeiId;
	private Long agentSupplier;
	private String jingwei;
	private String geoHash;
	private Date validBegDate;
	private Date validEndDate;
	private Short status;
	private Double bond;
	private Double serviceFee;
	private Date createTime;
	private Long createPerson;
	private Date verTime;
	private Date updateTime;
	private Date inTime;
	private Short inType;
	private Short level;
	private Integer code;
	private Integer marketId;
	private String verImage;
	private String busContent;
	private Integer zoneId;
	private String qq;
	private Short isOffLine;

	// Constructors

	/** default constructor */
	public UBatchSupplyer() {
	}

	/** full constructor */
	public UBatchSupplyer(Integer bmid, Integer province, Integer city,
			Integer district, Integer street, String addrDetail,
			String shopName, String shopPosition, String shopPic,
			Integer saleType, Long agent, Long verifier, Long upVerifier,
			Long portVerifer, Long companyWeiId, Long agentSupplier,
			String jingwei, String geoHash, Date validBegDate,
			Date validEndDate, Short status, Double bond,
			Double serviceFee, Date createTime, Long createPerson,
			Date verTime, Date updateTime, Date inTime,
			Short inType, Short level, Integer code, Integer marketId,
			String verImage, String busContent, Integer zoneId, String qq,
			Short isOffLine) {
		this.bmid = bmid;
		this.province = province;
		this.city = city;
		this.district = district;
		this.street = street;
		this.addrDetail = addrDetail;
		this.shopName = shopName;
		this.shopPosition = shopPosition;
		this.shopPic = shopPic;
		this.saleType = saleType;
		this.agent = agent;
		this.verifier = verifier;
		this.upVerifier = upVerifier;
		this.portVerifer = portVerifer;
		this.companyWeiId = companyWeiId;
		this.agentSupplier = agentSupplier;
		this.jingwei = jingwei;
		this.geoHash = geoHash;
		this.validBegDate = validBegDate;
		this.validEndDate = validEndDate;
		this.status = status;
		this.bond = bond;
		this.serviceFee = serviceFee;
		this.createTime = createTime;
		this.createPerson = createPerson;
		this.verTime = verTime;
		this.updateTime = updateTime;
		this.inTime = inTime;
		this.inType = inType;
		this.level = level;
		this.code = code;
		this.marketId = marketId;
		this.verImage = verImage;
		this.busContent = busContent;
		this.zoneId = zoneId;
		this.qq = qq;
		this.isOffLine = isOffLine;
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

	@Column(name = "BMID")
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

	@Column(name = "AddrDetail", length = 128)
	public String getAddrDetail() {
		return this.addrDetail;
	}

	public void setAddrDetail(String addrDetail) {
		this.addrDetail = addrDetail;
	}

	@Column(name = "ShopName", length = 32)
	public String getShopName() {
		return this.shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	@Column(name = "ShopPosition", length = 32)
	public String getShopPosition() {
		return this.shopPosition;
	}

	public void setShopPosition(String shopPosition) {
		this.shopPosition = shopPosition;
	}

	@Column(name = "ShopPic", length = 128)
	public String getShopPic() {
		return this.shopPic;
	}

	public void setShopPic(String shopPic) {
		this.shopPic = shopPic;
	}

	@Column(name = "SaleType")
	public Integer getSaleType() {
		return this.saleType;
	}

	public void setSaleType(Integer saleType) {
		this.saleType = saleType;
	}

	@Column(name = "Agent")
	public Long getAgent() {
		return this.agent;
	}

	public void setAgent(Long agent) {
		this.agent = agent;
	}

	@Column(name = "Verifier")
	public Long getVerifier() {
		return this.verifier;
	}

	public void setVerifier(Long verifier) {
		this.verifier = verifier;
	}

	@Column(name = "UpVerifier")
	public Long getUpVerifier() {
		return this.upVerifier;
	}

	public void setUpVerifier(Long upVerifier) {
		this.upVerifier = upVerifier;
	}

	@Column(name = "PortVerifer")
	public Long getPortVerifer() {
		return this.portVerifer;
	}

	public void setPortVerifer(Long portVerifer) {
		this.portVerifer = portVerifer;
	}

	@Column(name = "CompanyWeiID")
	public Long getCompanyWeiId() {
		return this.companyWeiId;
	}

	public void setCompanyWeiId(Long companyWeiId) {
		this.companyWeiId = companyWeiId;
	}

	@Column(name = "AgentSupplier")
	public Long getAgentSupplier() {
		return this.agentSupplier;
	}

	public void setAgentSupplier(Long agentSupplier) {
		this.agentSupplier = agentSupplier;
	}

	@Column(name = "Jingwei", length = 32)
	public String getJingwei() {
		return this.jingwei;
	}

	public void setJingwei(String jingwei) {
		this.jingwei = jingwei;
	}

	@Column(name = "GeoHash", length = 32)
	public String getGeoHash() {
		return this.geoHash;
	}

	public void setGeoHash(String geoHash) {
		this.geoHash = geoHash;
	}

	@Column(name = "ValidBegDate", length = 19)
	public Date getValidBegDate() {
		return this.validBegDate;
	}

	public void setValidBegDate(Date validBegDate) {
		this.validBegDate = validBegDate;
	}

	@Column(name = "ValidEndDate", length = 19)
	public Date getValidEndDate() {
		return this.validEndDate;
	}

	public void setValidEndDate(Date validEndDate) {
		this.validEndDate = validEndDate;
	}

	@Column(name = "Status")
	public Short getStatus() {
		return this.status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	@Column(name = "Bond", precision = 18)
	public Double getBond() {
		return this.bond;
	}

	public void setBond(Double bond) {
		this.bond = bond;
	}

	@Column(name = "ServiceFee", precision = 18)
	public Double getServiceFee() {
		return this.serviceFee;
	}

	public void setServiceFee(Double serviceFee) {
		this.serviceFee = serviceFee;
	}

	@Column(name = "CreateTime", length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "CreatePerson")
	public Long getCreatePerson() {
		return this.createPerson;
	}

	public void setCreatePerson(Long createPerson) {
		this.createPerson = createPerson;
	}

	@Column(name = "VerTime", length = 19)
	public Date getVerTime() {
		return this.verTime;
	}

	public void setVerTime(Date verTime) {
		this.verTime = verTime;
	}

	@Column(name = "UpdateTime", length = 19)
	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name = "InTime", length = 19)
	public Date getInTime() {
		return this.inTime;
	}

	public void setInTime(Date inTime) {
		this.inTime = inTime;
	}

	@Column(name = "InType")
	public Short getInType() {
		return this.inType;
	}

	public void setInType(Short inType) {
		this.inType = inType;
	}

	@Column(name = "Level")
	public Short getLevel() {
		return this.level;
	}

	public void setLevel(Short level) {
		this.level = level;
	}

	@Column(name = "Code")
	public Integer getCode() {
		return this.code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	@Column(name = "MarketID")
	public Integer getMarketId() {
		return this.marketId;
	}

	public void setMarketId(Integer marketId) {
		this.marketId = marketId;
	}

	@Column(name = "VerImage", length = 128)
	public String getVerImage() {
		return this.verImage;
	}

	public void setVerImage(String verImage) {
		this.verImage = verImage;
	}

	@Column(name = "BusContent", length = 64)
	public String getBusContent() {
		return this.busContent;
	}

	public void setBusContent(String busContent) {
		this.busContent = busContent;
	}

	@Column(name = "ZoneID")
	public Integer getZoneId() {
		return this.zoneId;
	}

	public void setZoneId(Integer zoneId) {
		this.zoneId = zoneId;
	}

	@Column(name = "QQ", length = 32)
	public String getQq() {
		return this.qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	@Column(name = "IsOffLine")
	public Short getIsOffLine() {
		return this.isOffLine;
	}

	public void setIsOffLine(Short isOffLine) {
		this.isOffLine = isOffLine;
	}

}