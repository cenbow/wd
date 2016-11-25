package com.okwei.bean.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * USupplierBill entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "U_SupplierBill")
public class USupplierBill implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5190713002879903429L;
	// Fields

	private Long supWeiId;
	private Short type;
	private String companyName;
	private String billOrder;
	private String linkMan;
	private String phone;
	private String addrDetail;
	private Date createTime;
	private Short state;
	private Date doTime;
	private Date sendTime;
	private Double billCount;
	private String billNo;
	private String billRemark;
	private String sendRemark;
	private Short sendType;
	private String deliveryOrder;
	// Constructors

	/** default constructor */
	public USupplierBill() {
	}

	/** full constructor */
	public USupplierBill(Long supWeiId,Short type, String companyName, String billOrder,
			String linkMan, String phone, String addrDetail,
			Date createTime, Short state, Date doTime,
			Date sendTime, Double billCount, String billNo) {
		this.supWeiId=supWeiId;
		this.type = type;
		this.companyName = companyName;
		this.billOrder = billOrder;
		this.linkMan = linkMan;
		this.phone = phone;
		this.addrDetail = addrDetail;
		this.createTime = createTime;
		this.state = state;
		this.doTime = doTime;
		this.sendTime = sendTime;
		this.billCount = billCount;
		this.billNo = billNo;
	}

	// Property accessors
	@Id
	@Column(name = "SupWeiID", unique = true, nullable = false)
	public Long getSupWeiId() {
		return this.supWeiId;
	}

	public void setSupWeiId(Long supWeiId) {
		this.supWeiId = supWeiId;
	}

	@Column(name = "Type")
	public Short getType() {
		return this.type;
	}

	public void setType(Short type) {
		this.type = type;
	}

	@Column(name = "CompanyName", length = 128)
	public String getCompanyName() {
		return this.companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	@Column(name = "BillOrder", length = 64)
	public String getBillOrder() {
		return this.billOrder;
	}

	public void setBillOrder(String billOrder) {
		this.billOrder = billOrder;
	}

	@Column(name = "LinkMan", length = 32)
	public String getLinkMan() {
		return this.linkMan;
	}

	public void setLinkMan(String linkMan) {
		this.linkMan = linkMan;
	}

	@Column(name = "Phone", length = 32)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "AddrDetail", length = 128)
	public String getAddrDetail() {
		return this.addrDetail;
	}

	public void setAddrDetail(String addrDetail) {
		this.addrDetail = addrDetail;
	}

	@Column(name = "CreateTime", length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@Column(name = "State")
	public Short getState() {
		return this.state;
	}

	public void setState(Short state) {
		this.state = state;
	}

	@Column(name = "DoTime", length = 19)
	public Date getDoTime() {
		return this.doTime;
	}

	public void setDoTime(Date doTime) {
		this.doTime = doTime;
	}

	@Column(name = "SendTime", length = 19)
	public Date getSendTime() {
		return this.sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	@Column(name = "BillCount", precision = 18)
	public Double getBillCount() {
		return this.billCount;
	}

	public void setBillCount(Double billCount) {
		this.billCount = billCount;
	}

	@Column(name = "BillNo", length = 64)
	public String getBillNo() {
		return this.billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	@Column(name = "BillRemark", length = 512)
	public String getBillRemark() {
		return billRemark;
	}

	public void setBillRemark(String billRemark) {
		this.billRemark = billRemark;
	}

	@Column(name = "SendRemark", length = 512)
	public String getSendRemark() {
		return sendRemark;
	}

	public void setSendRemark(String sendRemark) {
		this.sendRemark = sendRemark;
	}

	@Column(name = "SendType")
	public Short getSendType() {
		return sendType;
	}

	public void setSendType(Short sendType) {
		this.sendType = sendType;
	}

	@Column(name = "DeliveryOrder", length = 32)
	public String getDeliveryOrder() {
		return deliveryOrder;
	}

	public void setDeliveryOrder(String deliveryOrder) {
		this.deliveryOrder = deliveryOrder;
	}
	
	
	
}