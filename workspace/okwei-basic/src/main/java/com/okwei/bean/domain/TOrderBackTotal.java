package com.okwei.bean.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TOrderBackTotal entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_OrderBackTotal" )
public class TOrderBackTotal implements java.io.Serializable {

	// Fields

	/** 
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	 */ 
	private static final long serialVersionUID = 1L;
	private Long backOrder;
	private String flowId;
	private String supplierOrderId;
	private Short backStatus;
	private Short oistatus;
	private String transName;
	private Date SProcessTime;
	private Short causeType;
	private Long adminId;
	private Date AProcessTime;
	private Long sellerId;
	private Long said;
	private Long buyerid;
	private String transNo;
	private String BReason;
	private Double refundAmout;
	private String SReason;
	private String AReason;
	private Date fangkunTime;
	private Short type;
	private Date createTime;

	@Column(name = "Single")
	private Short single;


	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "BackOrder", unique = true, nullable = false)
	public Long getBackOrder() {
		return this.backOrder;
	}

	public void setBackOrder(Long backOrder) {
		this.backOrder = backOrder;
	}

	@Column(name = "FlowID")
	public String getFlowId() {
		return this.flowId;
	}

	public void setFlowId(String flowId) {
		this.flowId = flowId;
	}

	@Column(name = "SupplierOrderID", length = 32)
	public String getSupplierOrderId() {
		return this.supplierOrderId;
	}

	public void setSupplierOrderId(String supplierOrderId) {
		this.supplierOrderId = supplierOrderId;
	}

	@Column(name = "BackStatus")
	public Short getBackStatus() {
		return this.backStatus;
	}

	public void setBackStatus(Short backStatus) {
		this.backStatus = backStatus;
	}

	@Column(name = "OIStatus")
	public Short getOistatus() {
		return this.oistatus;
	}

	public void setOistatus(Short oistatus) {
		this.oistatus = oistatus;
	}

	@Column(name = "TransName", length = 64)
	public String getTransName() {
		return this.transName;
	}

	public void setTransName(String transName) {
		this.transName = transName;
	}

	@Column(name = "S_ProcessTime", length = 0)
	public Date getSProcessTime() {
		return this.SProcessTime;
	}

	public void setSProcessTime(Date SProcessTime) {
		this.SProcessTime = SProcessTime;
	}

	@Column(name = "CauseType")
	public Short getCauseType() {
		return this.causeType;
	}

	public void setCauseType(Short causeType) {
		this.causeType = causeType;
	}

	@Column(name = "AdminId")
	public Long getAdminId() {
		return this.adminId;
	}

	public void setAdminId(Long adminId) {
		this.adminId = adminId;
	}

	@Column(name = "A_ProcessTime", length = 0)
	public Date getAProcessTime() {
		return this.AProcessTime;
	}

	public void setAProcessTime(Date AProcessTime) {
		this.AProcessTime = AProcessTime;
	}

	@Column(name = "SellerID")
	public Long getSellerId() {
		return this.sellerId;
	}

	public void setSellerId(Long sellerId) {
		this.sellerId = sellerId;
	}

	@Column(name = "SAID")
	public Long getSaid() {
		return this.said;
	}

	public void setSaid(Long said) {
		this.said = said;
	}

	@Column(name = "Buyerid")
	public Long getBuyerid() {
		return this.buyerid;
	}

	public void setBuyerid(Long buyerid) {
		this.buyerid = buyerid;
	}

	@Column(name = "TransNo", length = 32)
	public String getTransNo() {
		return this.transNo;
	}

	public void setTransNo(String transNo) {
		this.transNo = transNo;
	}

	@Column(name = "B_Reason", length = 128)
	public String getBReason() {
		return this.BReason;
	}

	public void setBReason(String BReason) {
		this.BReason = BReason;
	}

	@Column(name = "RefundAmout", precision = 18)
	public Double getRefundAmout() {
		return this.refundAmout;
	}

	public void setRefundAmout(Double refundAmout) {
		this.refundAmout = refundAmout;
	}

	@Column(name = "S_Reason", length = 128)
	public String getSReason() {
		return this.SReason;
	}

	public void setSReason(String SReason) {
		this.SReason = SReason;
	}

	@Column(name = "A_Reason", length = 128)
	public String getAReason() {
		return this.AReason;
	}

	public void setAReason(String AReason) {
		this.AReason = AReason;
	}

	@Column(name = "FangkunTime", length = 0)
	public Date getFangkunTime() {
		return this.fangkunTime;
	}

	public void setFangkunTime(Date fangkunTime) {
		this.fangkunTime = fangkunTime;
	}

	@Column(name = "Type")
	public Short getType() {
		return this.type;
	}

	public void setType(Short type) {
		this.type = type;
	}

	@Column(name = "CreateTime", length = 0)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Short getSingle() {
		return single;
	}

	public void setSingle(Short single) {
		this.single = single;
	}

}