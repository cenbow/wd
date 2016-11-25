package com.okwei.bean.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * OOrderAddr entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "O_OrderAddr")
public class OOrderAddr extends BaseEntity {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long orderAddrId;
	private Integer caddrId;
	private Long weiId;
	private String receiverName;
	private Integer province;
	private Integer city;
	private Integer district;
	private Integer street;
	private String detailAddr;
	private String mobilePhone;
	private String qq;
	private Date createTime;
	private String idCard;

	// Constructors

	/** default constructor */
	public OOrderAddr() {
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "OrderAddrID", unique = true, nullable = false)
	public Long getOrderAddrId() {
		return orderAddrId;
	}

	public void setOrderAddrId(Long orderAddrId) {
		this.orderAddrId = orderAddrId;
	}

	@Column(name = "CaddrID")
	public Integer getCaddrId() {
		return this.caddrId;
	}

	public void setCaddrId(Integer caddrId) {
		this.caddrId = caddrId;
	}

	@Column(name = "WeiID")
	public Long getWeiId() {
		return this.weiId;
	}

	public void setWeiId(Long weiId) {
		this.weiId = weiId;
	}

	@Column(name = "ReceiverName", length = 32)
	public String getReceiverName() {
		return this.receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
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
		return street;
	}

	public void setStreet(Integer street) {
		this.street = street;
	}

	@Column(name = "DetailAddr", length = 128)
	public String getDetailAddr() {
		return this.detailAddr;
	}

	public void setDetailAddr(String detailAddr) {
		this.detailAddr = detailAddr;
	}

	@Column(name = "MobilePhone", length = 32)
	public String getMobilePhone() {
		return this.mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	@Column(name = "QQ", length = 32)
	public String getQq() {
		return this.qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	@Column(name = "CreateTime", length = 0)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@Column(name = "IdCard", length = 64)
	public String getIdCard() {
		return this.idCard;
	}

	public void setIdCard(String idcard) {
		this.idCard = idcard;
	}

}