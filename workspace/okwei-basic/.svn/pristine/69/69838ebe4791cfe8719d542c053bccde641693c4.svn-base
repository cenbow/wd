package com.okwei.bean.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * PcRecommendSupplier entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "PC_RecommendSupplier")
public class PcRecommendSupplier implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -1887252634562823491L;
	private Long weiId;
	private Long supId;
	private String supName;
	private String supImg;
	private String busType;
	private Integer productCount;
	private String marketName;
	private String area;
	private Short supTyp;
	private Integer sort;
	private Date createTime;
	private Long creater;
	private Short status;
	private Date updateTime;
	private Long updater;

	// Constructors

	/** default constructor */
	public PcRecommendSupplier() {
	}

	/** full constructor */
	public PcRecommendSupplier(String supName, String supImg, String busType,
			Integer productCount, String marketName, String area, Short supTyp,
			Integer sort, Date createTime, Long creater, Short status,
			Date updateTime, Long updater) {
		this.supName = supName;
		this.supImg = supImg;
		this.busType = busType;
		this.productCount = productCount;
		this.marketName = marketName;
		this.area = area;
		this.supTyp = supTyp;
		this.sort = sort;
		this.createTime = createTime;
		this.creater = creater;
		this.status = status;
		this.updateTime = updateTime;
		this.updater = updater;
	}

	// Property accessors
	@Id
	@Column(name = "SupID", unique = true, nullable = false)
	public Long getSupId() {
		return this.supId;
	}

	public void setSupId(Long supId) {
		this.supId = supId;
	}

	@Column(name = "SupName", length = 128)
	public String getSupName() {
		return this.supName;
	}

	public void setSupName(String supName) {
		this.supName = supName;
	}

	@Column(name = "SupImg", length = 128)
	public String getSupImg() {
		return this.supImg;
	}

	public void setSupImg(String supImg) {
		this.supImg = supImg;
	}

	@Column(name = "BusType", length = 128)
	public String getBusType() {
		return this.busType;
	}

	public void setBusType(String busType) {
		this.busType = busType;
	}

	@Column(name = "ProductCount")
	public Integer getProductCount() {
		return this.productCount;
	}

	public void setProductCount(Integer productCount) {
		this.productCount = productCount;
	}

	@Column(name = "MarketName", length = 128)
	public String getMarketName() {
		return this.marketName;
	}

	public void setMarketName(String marketName) {
		this.marketName = marketName;
	}

	@Column(name = "Area", length = 64)
	public String getArea() {
		return this.area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	@Column(name = "SupTyp")
	public Short getSupTyp() {
		return this.supTyp;
	}

	public void setSupTyp(Short supTyp) {
		this.supTyp = supTyp;
	}

	@Column(name = "Sort")
	public Integer getSort() {
		return this.sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	@Column(name = "CreateTime", length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "Creater")
	public Long getCreater() {
		return this.creater;
	}

	public void setCreater(Long creater) {
		this.creater = creater;
	}

	@Column(name = "Status")
	public Short getStatus() {
		return this.status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	@Column(name = "UpdateTime", length = 19)
	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name = "Updater")
	public Long getUpdater() {
		return this.updater;
	}

	public void setUpdater(Long updater) {
		this.updater = updater;
	}

	@Column(name="WeiID")
	public Long getWeiId() {
		return weiId;
	}

	public void setWeiId(Long weiId) {
		this.weiId = weiId;
	}

}