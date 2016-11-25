package com.okwei.bean.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TRefundHandle entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_Refund_Handle")
public class TRefundHandle extends BaseEntity {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long rid;
	private String orderNo;
	private String orderListNo;
	private Short causeType;
	private Long buyerid;
	private Double refundAmout;
	private String BReason;
	private String transName;
	private String transNo;
	private Short oistatus;
	private Short status;
	private Long sellerId;
	private String SReason;
	private Date SProcessTime;
	private Integer said;
	private Long adminId;
	private String AReason;
	private Date AProcessTime;
	private Short type;
	private Date fangkunTime;
	private Date createTime;

	// Constructors

	/** default constructor */
	public TRefundHandle() {
	}

	/** full constructor */
	public TRefundHandle(String orderNo, String orderListNo, Short causeType, Long buyerid, Double refundAmout, String BReason, String transName,
			String transNo, Short oistatus, Short status, Long sellerId, String SReason, Date SProcessTime, Integer said, Long adminId, String AReason,
			Date AProcessTime, Short type, Date fangkunTime, Date createTime) {
		this.orderNo = orderNo;
		this.orderListNo = orderListNo;
		this.causeType = causeType;
		this.buyerid = buyerid;
		this.refundAmout = refundAmout;
		this.BReason = BReason;
		this.transName = transName;
		this.transNo = transNo;
		this.oistatus = oistatus;
		this.status = status;
		this.sellerId = sellerId;
		this.SReason = SReason;
		this.SProcessTime = SProcessTime;
		this.said = said;
		this.adminId = adminId;
		this.AReason = AReason;
		this.AProcessTime = AProcessTime;
		this.type = type;
		this.fangkunTime = fangkunTime;
		this.createTime = createTime;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "Rid", unique = true, nullable = false)
	public Long getRid() {
		return this.rid;
	}

	public void setRid(Long rid) {
		this.rid = rid;
	}

	@Column(name = "OrderNo", length = 32)
	public String getOrderNo() {
		return this.orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	@Column(name = "OrderListNo", length = 32)
	public String getOrderListNo() {
		return this.orderListNo;
	}

	public void setOrderListNo(String orderListNo) {
		this.orderListNo = orderListNo;
	}

	@Column(name = "CauseType")
	public Short getCauseType() {
		return this.causeType;
	}

	public void setCauseType(Short causeType) {
		this.causeType = causeType;
	}

	@Column(name = "Buyerid")
	public Long getBuyerid() {
		return this.buyerid;
	}

	public void setBuyerid(Long buyerid) {
		this.buyerid = buyerid;
	}

	@Column(name = "RefundAmout", precision = 18)
	public Double getRefundAmout() {
		return this.refundAmout;
	}

	public void setRefundAmout(Double refundAmout) {
		this.refundAmout = refundAmout;
	}

	@Column(name = "B_Reason", length = 128)
	public String getBReason() {
		return this.BReason;
	}

	public void setBReason(String BReason) {
		this.BReason = BReason;
	}

	@Column(name = "TransName", length = 64)
	public String getTransName() {
		return this.transName;
	}

	public void setTransName(String transName) {
		this.transName = transName;
	}

	@Column(name = "TransNo", length = 32)
	public String getTransNo() {
		return this.transNo;
	}

	public void setTransNo(String transNo) {
		this.transNo = transNo;
	}

	@Column(name = "OIStatus")
	public Short getOistatus() {
		return this.oistatus;
	}

	public void setOistatus(Short oistatus) {
		this.oistatus = oistatus;
	}

	@Column(name = "Status")
	public Short getStatus() {
		return this.status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	@Column(name = "SellerID")
	public Long getSellerId() {
		return this.sellerId;
	}

	public void setSellerId(Long sellerId) {
		this.sellerId = sellerId;
	}

	@Column(name = "S_Reason", length = 128)
	public String getSReason() {
		return this.SReason;
	}

	public void setSReason(String SReason) {
		this.SReason = SReason;
	}

	@Column(name = "S_ProcessTime", length = 0)
	public Date getSProcessTime() {
		return this.SProcessTime;
	}

	public void setSProcessTime(Date SProcessTime) {
		this.SProcessTime = SProcessTime;
	}

	@Column(name = "SAID")
	public Integer getSaid() {
		return this.said;
	}

	public void setSaid(Integer said) {
		this.said = said;
	}

	@Column(name = "AdminId")
	public Long getAdminId() {
		return this.adminId;
	}

	public void setAdminId(Long adminId) {
		this.adminId = adminId;
	}

	@Column(name = "A_Reason", length = 128)
	public String getAReason() {
		return this.AReason;
	}

	public void setAReason(String AReason) {
		this.AReason = AReason;
	}

	@Column(name = "A_ProcessTime", length = 0)
	public Date getAProcessTime() {
		return this.AProcessTime;
	}

	public void setAProcessTime(Date AProcessTime) {
		this.AProcessTime = AProcessTime;
	}

	@Column(name = "Type")
	public Short getType() {
		return this.type;
	}

	public void setType(Short type) {
		this.type = type;
	}

	@Column(name = "FangkunTime", length = 0)
	public Date getFangkunTime() {
		return this.fangkunTime;
	}

	public void setFangkunTime(Date fangkunTime) {
		this.fangkunTime = fangkunTime;
	}

	@Column(name = "CreateTime", length = 0)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}