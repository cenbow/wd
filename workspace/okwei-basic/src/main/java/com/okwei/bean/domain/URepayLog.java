package com.okwei.bean.domain;

import java.util.Date;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * URepayLog entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "U_RepayLog")
public class URepayLog implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 4082929464464257733L;
	private Long id;
	private Long weiId;
	private Long totalMoney;
	private Long repayMoney;
	private Date createTime;
	private Integer state;
	private Date forTime;

	// Constructors

	/** default constructor */
	public URepayLog() {
	}

	/** full constructor */
	public URepayLog(Long weiId, Long totalMoney, Long repayMoney,
			Date createTime, Integer state, Date forTime) {
		this.weiId = weiId;
		this.totalMoney = totalMoney;
		this.repayMoney = repayMoney;
		this.createTime = createTime;
		this.state = state;
		this.forTime = forTime;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "Id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "WeiId")
	public Long getWeiId() {
		return this.weiId;
	}

	public void setWeiId(Long weiId) {
		this.weiId = weiId;
	}

	@Column(name = "TotalMoney", precision = 10, scale = 0)
	public Long getTotalMoney() {
		return this.totalMoney;
	}

	public void setTotalMoney(Long totalMoney) {
		this.totalMoney = totalMoney;
	}

	@Column(name = "RepayMoney", precision = 10, scale = 0)
	public Long getRepayMoney() {
		return this.repayMoney;
	}

	public void setRepayMoney(Long repayMoney) {
		this.repayMoney = repayMoney;
	}

	@Column(name = "CreateTime", length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "State")
	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "ForTime", length = 10)
	public Date getForTime() {
		return this.forTime;
	}

	public void setForTime(Date forTime) {
		this.forTime = forTime;
	}

}