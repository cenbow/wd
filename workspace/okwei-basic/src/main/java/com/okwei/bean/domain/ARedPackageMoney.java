package com.okwei.bean.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ARedPackageMoney entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "A_RedPackageMoney")
public class ARedPackageMoney implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7298148562693755783L;
	// Fields

	private Integer pid;
	private Long weiId;
	private Double totalAmount;
	private Date createTime;
	private Integer status;
	private Double outAmount;
	private Date actTime;
	// Constructors

	/** default constructor */
	public ARedPackageMoney() {
	}

	/** full constructor */
	public ARedPackageMoney(Long weiId, Double totalAmount,
			Date createTime, Integer status, Double outAmount) {
		this.weiId = weiId;
		this.totalAmount = totalAmount;
		this.createTime = createTime;
		this.status = status;
		this.outAmount = outAmount;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "PID", unique = true, nullable = false)
	public Integer getPid() {
		return this.pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	@Column(name = "WeiID")
	public Long getWeiId() {
		return this.weiId;
	}

	public void setWeiId(Long weiId) {
		this.weiId = weiId;
	}

	@Column(name = "TotalAmount", precision = 18)
	public Double getTotalAmount() {
		return this.totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	@Column(name = "CreateTime", length = 0)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "Status")
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "OutAmount", precision = 18)
	public Double getOutAmount() {
		return this.outAmount;
	}

	public void setOutAmount(Double outAmount) {
		this.outAmount = outAmount;
	}
	@Column(name = "ActTime", length = 0)
	public Date getActTime() {
		return actTime;
	}
	public void setActTime(Date actTime) {
		this.actTime = actTime;
	}
}