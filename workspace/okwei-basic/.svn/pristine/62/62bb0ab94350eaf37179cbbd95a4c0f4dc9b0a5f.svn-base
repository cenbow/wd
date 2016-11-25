package com.okwei.bean.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ARedPacMoneyTypes entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "A_RedPacMoneyTypes")
public class ARedPacMoneyTypes implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -1906091988877755308L;
	private Integer rtypeId;
	private Double amount;
	private Integer status;

	// Constructors

	/** default constructor */
	public ARedPacMoneyTypes() {
	}

	/** full constructor */
	public ARedPacMoneyTypes(Double amount, Integer status) {
		this.amount = amount;
		this.status = status;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "RTypeId", unique = true, nullable = false)
	public Integer getRtypeId() {
		return this.rtypeId;
	}

	public void setRtypeId(Integer rtypeId) {
		this.rtypeId = rtypeId;
	}

	@Column(name = "Amount", precision = 18)
	public Double getAmount() {
		return this.amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	@Column(name = "Status")
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}