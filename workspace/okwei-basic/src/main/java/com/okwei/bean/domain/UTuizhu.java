package com.okwei.bean.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * UTuizhu entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "U_Tuizhu")
public class UTuizhu implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 6082612278451227290L;
	private Integer tid;
	private Long weiId;
	private Short type;
	private Double amount;
	private Short state;
	private String bankNo;
	private String bankName;
	private String bankAdress;
	private Short bankType;
	private String name;
	private Long operator;
	private Double actAmount;
	private String detailPath;
	private Date createTime;
	private Date processTime;
	private String note;
	private String imageFront;
	private String imageBack;
	private String reason;
	private Date payTime;

	// Constructors

	/** default constructor */
	public UTuizhu() {
	}

	/** full constructor */
	public UTuizhu(Long weiId, Short type, Double amount, Short state,
			String bankNo, String bankName, String bankAdress, Short bankType,
			String name, Long operator, Double actAmount, String detailPath,
			Date createTime, Date processTime, String note,
			String imageFront, String imageBack, String reason,
			Date payTime) {
		this.weiId = weiId;
		this.type = type;
		this.amount = amount;
		this.state = state;
		this.bankNo = bankNo;
		this.bankName = bankName;
		this.bankAdress = bankAdress;
		this.bankType = bankType;
		this.name = name;
		this.operator = operator;
		this.actAmount = actAmount;
		this.detailPath = detailPath;
		this.createTime = createTime;
		this.processTime = processTime;
		this.note = note;
		this.imageFront = imageFront;
		this.imageBack = imageBack;
		this.reason = reason;
		this.payTime = payTime;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "TID", unique = true, nullable = false)
	public Integer getTid() {
		return this.tid;
	}

	public void setTid(Integer tid) {
		this.tid = tid;
	}

	@Column(name = "WeiID")
	public Long getWeiId() {
		return this.weiId;
	}

	public void setWeiId(Long weiId) {
		this.weiId = weiId;
	}

	@Column(name = "Type")
	public Short getType() {
		return this.type;
	}

	public void setType(Short type) {
		this.type = type;
	}

	@Column(name = "Amount", precision = 22, scale = 0)
	public Double getAmount() {
		return this.amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	@Column(name = "State")
	public Short getState() {
		return this.state;
	}

	public void setState(Short state) {
		this.state = state;
	}

	@Column(name = "BankNo", length = 50)
	public String getBankNo() {
		return this.bankNo;
	}

	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
	}

	@Column(name = "BankName", length = 50)
	public String getBankName() {
		return this.bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	@Column(name = "BankAdress", length = 100)
	public String getBankAdress() {
		return this.bankAdress;
	}

	public void setBankAdress(String bankAdress) {
		this.bankAdress = bankAdress;
	}

	@Column(name = "BankType")
	public Short getBankType() {
		return this.bankType;
	}

	public void setBankType(Short bankType) {
		this.bankType = bankType;
	}

	@Column(name = "Name", length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "Operator")
	public Long getOperator() {
		return this.operator;
	}

	public void setOperator(Long operator) {
		this.operator = operator;
	}

	@Column(name = "ActAmount", precision = 22, scale = 0)
	public Double getActAmount() {
		return this.actAmount;
	}

	public void setActAmount(Double actAmount) {
		this.actAmount = actAmount;
	}

	@Column(name = "DetailPath", length = 100)
	public String getDetailPath() {
		return this.detailPath;
	}

	public void setDetailPath(String detailPath) {
		this.detailPath = detailPath;
	}

	@Column(name = "CreateTime", length = 0)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "ProcessTime", length = 0)
	public Date getProcessTime() {
		return this.processTime;
	}

	public void setProcessTime(Date processTime) {
		this.processTime = processTime;
	}

	@Column(name = "Note", length = 65535)
	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Column(name = "ImageFront", length = 500)
	public String getImageFront() {
		return this.imageFront;
	}

	public void setImageFront(String imageFront) {
		this.imageFront = imageFront;
	}

	@Column(name = "ImageBack", length = 500)
	public String getImageBack() {
		return this.imageBack;
	}

	public void setImageBack(String imageBack) {
		this.imageBack = imageBack;
	}

	@Column(name = "Reason", length = 200)
	public String getReason() {
		return this.reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@Column(name = "PayTime", length = 0)
	public Date getPayTime() {
		return this.payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

}