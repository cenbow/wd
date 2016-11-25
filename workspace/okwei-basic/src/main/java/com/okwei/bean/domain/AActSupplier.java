package com.okwei.bean.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * AActSupplier entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "A_ActSupplier")
public class AActSupplier implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 7851494572416610811L;
	private Integer asid;
	private Long actId;
	private Long supplyWeiid;
	private Double amount;
	private Date createTime;

	// Constructors

	/** default constructor */
	public AActSupplier() {
	}

	/** full constructor */
	public AActSupplier(Long actId, Long supplyWeiid, Double amount,
			Date createTime) {
		this.actId = actId;
		this.supplyWeiid = supplyWeiid;
		this.amount = amount;
		this.createTime = createTime;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ASid", unique = true, nullable = false)
	public Integer getAsid() {
		return this.asid;
	}

	public void setAsid(Integer asid) {
		this.asid = asid;
	}

	@Column(name = "ActID")
	public Long getActId() {
		return this.actId;
	}

	public void setActId(Long actId) {
		this.actId = actId;
	}

	@Column(name = "SupplyWeiid")
	public Long getSupplyWeiid() {
		return this.supplyWeiid;
	}

	public void setSupplyWeiid(Long supplyWeiid) {
		this.supplyWeiid = supplyWeiid;
	}

	@Column(name = "Amount", precision = 18)
	public Double getAmount() {
		return this.amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	@Column(name = "CreateTime", length = 0)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}