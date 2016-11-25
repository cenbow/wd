package com.okwei.bean.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * UYunSupplier entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "U_YunSupplier")
public class UYunSupplier implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1755736816976266890L;
	// Fields

	private Long weiId;
	private Long sourceWeiId;
	private Long rzWeiId;
	private Long sourceReciever;
	private Long rzReciever;
	private Short status;
	private String serviceQq;
	private Integer saleType;
	private Short isRetain;
	private Short tstatus;
	private Short idtype;
	private Short serviceType;
	private Double serviceFee;
	private Double bond;
	private Date createTime;
	private Date updateTime;
	private Date payTime;
	private Short fzfxsNum;
	private Short fzOrderNum;
	private String invoice;
	private Integer province;
	private Integer city;
	private Integer district;
	private Integer street;
	private Long createMan;
	private Date endTime;
	private String level;
	private String procInstId;
	private String productType;
	private String sampleThing;
	private Date startTime;
	private Long verifyMan;
	private Long adviser;
	private Long upAdviser;
	private String failReason;
	private Integer followCount;
	private Short bustype;
	private Short isOffLine;
	private Short haveProduct;
	private String busContent;

	// Constructors

	/** default constructor */
	public UYunSupplier() {
	}

	/** full constructor */
	public UYunSupplier(Long sourceWeiId, Long rzWeiId, Long sourceReciever,
			Long rzReciever, Short status, String serviceQq, Integer saleType,
			Short isRetain, Short tstatus, Short idtype, Short serviceType,
			Double serviceFee, Double bond, Date createTime,
			Date updateTime, Date payTime, Short fzfxsNum,
			Short fzOrderNum, String invoice, Integer province, Integer city,
			Integer district, Integer street, Long createMan,
			Date endTime, String level, String procInstId,
			String productType, String sampleThing, Date startTime,
			Long verifyMan, Long adviser, Long upAdviser, String failReason,
			Integer followCount, Short bustype, Short isOffLine,
			Short haveProduct, String busContent) {
		this.sourceWeiId = sourceWeiId;
		this.rzWeiId = rzWeiId;
		this.sourceReciever = sourceReciever;
		this.rzReciever = rzReciever;
		this.status = status;
		this.serviceQq = serviceQq;
		this.saleType = saleType;
		this.isRetain = isRetain;
		this.tstatus = tstatus;
		this.idtype = idtype;
		this.serviceType = serviceType;
		this.serviceFee = serviceFee;
		this.bond = bond;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.payTime = payTime;
		this.fzfxsNum = fzfxsNum;
		this.fzOrderNum = fzOrderNum;
		this.invoice = invoice;
		this.province = province;
		this.city = city;
		this.district = district;
		this.street = street;
		this.createMan = createMan;
		this.endTime = endTime;
		this.level = level;
		this.procInstId = procInstId;
		this.productType = productType;
		this.sampleThing = sampleThing;
		this.startTime = startTime;
		this.verifyMan = verifyMan;
		this.adviser = adviser;
		this.upAdviser = upAdviser;
		this.failReason = failReason;
		this.followCount = followCount;
		this.bustype = bustype;
		this.isOffLine = isOffLine;
		this.haveProduct = haveProduct;
		this.busContent = busContent;
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

	@Column(name = "SourceWeiID")
	public Long getSourceWeiId() {
		return this.sourceWeiId;
	}

	public void setSourceWeiId(Long sourceWeiId) {
		this.sourceWeiId = sourceWeiId;
	}

	@Column(name = "RzWeiID")
	public Long getRzWeiId() {
		return this.rzWeiId;
	}

	public void setRzWeiId(Long rzWeiId) {
		this.rzWeiId = rzWeiId;
	}

	@Column(name = "Source_Reciever")
	public Long getSourceReciever() {
		return this.sourceReciever;
	}

	public void setSourceReciever(Long sourceReciever) {
		this.sourceReciever = sourceReciever;
	}

	@Column(name = "Rz_Reciever")
	public Long getRzReciever() {
		return this.rzReciever;
	}

	public void setRzReciever(Long rzReciever) {
		this.rzReciever = rzReciever;
	}

	@Column(name = "Status")
	public Short getStatus() {
		return this.status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	@Column(name = "ServiceQQ", length = 128)
	public String getServiceQq() {
		return this.serviceQq;
	}

	public void setServiceQq(String serviceQq) {
		this.serviceQq = serviceQq;
	}

	@Column(name = "SaleType")
	public Integer getSaleType() {
		return this.saleType;
	}

	public void setSaleType(Integer saleType) {
		this.saleType = saleType;
	}

	@Column(name = "IsRetain")
	public Short getIsRetain() {
		return this.isRetain;
	}

	public void setIsRetain(Short isRetain) {
		this.isRetain = isRetain;
	}

	@Column(name = "TStatus")
	public Short getTstatus() {
		return this.tstatus;
	}

	public void setTstatus(Short tstatus) {
		this.tstatus = tstatus;
	}

	@Column(name = "IDType")
	public Short getIdtype() {
		return this.idtype;
	}

	public void setIdtype(Short idtype) {
		this.idtype = idtype;
	}

	@Column(name = "ServiceType")
	public Short getServiceType() {
		return this.serviceType;
	}

	public void setServiceType(Short serviceType) {
		this.serviceType = serviceType;
	}

	@Column(name = "ServiceFee", precision = 18)
	public Double getServiceFee() {
		return this.serviceFee;
	}

	public void setServiceFee(Double serviceFee) {
		this.serviceFee = serviceFee;
	}

	@Column(name = "Bond", precision = 18)
	public Double getBond() {
		return this.bond;
	}

	public void setBond(Double bond) {
		this.bond = bond;
	}

	@Column(name = "CreateTime", length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "UpdateTime", length = 19)
	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name = "PayTime", length = 19)
	public Date getPayTime() {
		return this.payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	@Column(name = "fzfxsNum")
	public Short getFzfxsNum() {
		return this.fzfxsNum;
	}

	public void setFzfxsNum(Short fzfxsNum) {
		this.fzfxsNum = fzfxsNum;
	}

	@Column(name = "fzOrderNum")
	public Short getFzOrderNum() {
		return this.fzOrderNum;
	}

	public void setFzOrderNum(Short fzOrderNum) {
		this.fzOrderNum = fzOrderNum;
	}

	@Column(name = "Invoice", length = 100)
	public String getInvoice() {
		return this.invoice;
	}

	public void setInvoice(String invoice) {
		this.invoice = invoice;
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

	@Column(name = "CreateMan")
	public Long getCreateMan() {
		return this.createMan;
	}

	public void setCreateMan(Long createMan) {
		this.createMan = createMan;
	}

	@Column(name = "EndTime", length = 19)
	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	@Column(name = "Level", length = 8)
	public String getLevel() {
		return this.level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	@Column(name = "ProcInstID", length = 50)
	public String getProcInstId() {
		return this.procInstId;
	}

	public void setProcInstId(String procInstId) {
		this.procInstId = procInstId;
	}

	@Column(name = "ProductType", length = 64)
	public String getProductType() {
		return this.productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	@Column(name = "SampleThing", length = 128)
	public String getSampleThing() {
		return this.sampleThing;
	}

	public void setSampleThing(String sampleThing) {
		this.sampleThing = sampleThing;
	}

	@Column(name = "StartTime", length = 19)
	public Date getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	@Column(name = "VerifyMan")
	public Long getVerifyMan() {
		return this.verifyMan;
	}

	public void setVerifyMan(Long verifyMan) {
		this.verifyMan = verifyMan;
	}

	@Column(name = "Adviser")
	public Long getAdviser() {
		return this.adviser;
	}

	public void setAdviser(Long adviser) {
		this.adviser = adviser;
	}

	@Column(name = "UpAdviser")
	public Long getUpAdviser() {
		return this.upAdviser;
	}

	public void setUpAdviser(Long upAdviser) {
		this.upAdviser = upAdviser;
	}

	@Column(name = "FailReason", length = 128)
	public String getFailReason() {
		return this.failReason;
	}

	public void setFailReason(String failReason) {
		this.failReason = failReason;
	}

	@Column(name = "FollowCount")
	public Integer getFollowCount() {
		return this.followCount;
	}

	public void setFollowCount(Integer followCount) {
		this.followCount = followCount;
	}

	@Column(name = "Bustype")
	public Short getBustype() {
		return this.bustype;
	}

	public void setBustype(Short bustype) {
		this.bustype = bustype;
	}

	@Column(name = "IsOffLine")
	public Short getIsOffLine() {
		return this.isOffLine;
	}

	public void setIsOffLine(Short isOffLine) {
		this.isOffLine = isOffLine;
	}

	@Column(name = "HaveProduct")
	public Short getHaveProduct() {
		return this.haveProduct;
	}

	public void setHaveProduct(Short haveProduct) {
		this.haveProduct = haveProduct;
	}

	@Column(name = "BusContent", length = 128)
	public String getBusContent() {
		return this.busContent;
	}

	public void setBusContent(String busContent) {
		this.busContent = busContent;
	}

}