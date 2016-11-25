package com.okwei.bean.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * AppPscoreLogTemp entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "APP_PScoreLogTemp")
public class AppPscoreLogTemp implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4597681690795266955L;
	// Fields

	private Long logId;
	private Long productId;
	private Long weiId;
	private Short scoreType;
	private Integer mid;
	private Integer areaId;
	private Long supplyWeiid;
	private Short supplyType;
	private Double score;
	private Date createTime;

	// Constructors

	/** default constructor */
	public AppPscoreLogTemp() {
	}

	/** full constructor */
	public AppPscoreLogTemp(Long productId, Short scoreType, Integer mid,
			Integer areaId, Long supplyWeiid, Short supplyType, Double score,
			Date createTime, Long weiId) {
		this.productId = productId;
		this.scoreType = scoreType;
		this.mid = mid;
		this.areaId = areaId;
		this.supplyWeiid = supplyWeiid;
		this.supplyType = supplyType;
		this.score = score;
		this.createTime = createTime;
		this.weiId=weiId;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "LogID", unique = true, nullable = false)
	public Long getLogId() {
		return this.logId;
	}

	public void setLogId(Long logId) {
		this.logId = logId;
	}

	@Column(name = "ProductId")
	public Long getProductId() {
		return this.productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	@Column(name = "ScoreType")
	public Short getScoreType() {
		return this.scoreType;
	}

	public void setScoreType(Short scoreType) {
		this.scoreType = scoreType;
	}

	@Column(name = "Mid")
	public Integer getMid() {
		return this.mid;
	}

	public void setMid(Integer mid) {
		this.mid = mid;
	}

	@Column(name = "AreaID")
	public Integer getAreaId() {
		return this.areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}
	@Column(name = "WeiID")
	public Long getWeiID() {
		return this.weiId;
	}

	public void setWeiID(Long weiId) {
		this.weiId = weiId;
	}

	@Column(name = "SupplyWeiid")
	public Long getSupplyWeiid() {
		return this.supplyWeiid;
	}

	public void setSupplyWeiid(Long supplyWeiid) {
		this.supplyWeiid = supplyWeiid;
	}

	@Column(name = "SupplyType")
	public Short getSupplyType() {
		return this.supplyType;
	}

	public void setSupplyType(Short supplyType) {
		this.supplyType = supplyType;
	}

	@Column(name = "Score", precision = 18)
	public Double getScore() {
		return this.score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	@Column(name = "CreateTime", length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}