package com.okwei.bean.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TMarketBusList entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "T_MarketBusList")
public class TMarketBusList implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9163676149691055820L;
	// Fields

	private Long id;
	private Integer bmid;
	private Integer busId;
	private Date createTime;

	// Constructors

	/** default constructor */
	public TMarketBusList() {
	}

	/** full constructor */
	public TMarketBusList(Integer bmid, Integer busId, Date createTime) {
		this.bmid = bmid;
		this.busId = busId;
		this.createTime = createTime;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "BMID")
	public Integer getBmid() {
		return this.bmid;
	}

	public void setBmid(Integer bmid) {
		this.bmid = bmid;
	}

	@Column(name = "BusID")
	public Integer getBusId() {
		return this.busId;
	}

	public void setBusId(Integer busId) {
		this.busId = busId;
	}

	@Column(name = "CreateTime", length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}