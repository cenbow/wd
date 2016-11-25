package com.okwei.bean.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * PcHotShop entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "PC_HotShop")
public class PcHotShop implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long hotId;
	private Long supWeiId;
	private String shopName;
	private String shopPosition;
	private Integer productCount;
	private Short ptype;
	private Integer sort;
	private Date createTime;
	private Long creater;
	private Short status;
	private Date updateTime;
	private Long updater;

	// Constructors

	/** default constructor */
	public PcHotShop() {
	}

	/** full constructor */
	public PcHotShop(Long supWeiId, String shopName, String shopPosition, Integer productCount, Short ptype, Integer sort, Date createTime, Long creater,
			Short status, Date updateTime, Long updater) {
		this.supWeiId = supWeiId;
		this.shopName = shopName;
		this.shopPosition = shopPosition;
		this.productCount = productCount;
		this.ptype = ptype;
		this.sort = sort;
		this.createTime = createTime;
		this.creater = creater;
		this.status = status;
		this.updateTime = updateTime;
		this.updater = updater;
	}

	// Property accessors
	@Id
	@Column(name = "HotID", unique = true, nullable = false)
	public Long getHotId() {
		return this.hotId;
	}

	public void setHotId(Long hotId) {
		this.hotId = hotId;
	}

	@Column(name = "SupWeiID")
	public Long getSupWeiId() {
		return this.supWeiId;
	}

	public void setSupWeiId(Long supWeiId) {
		this.supWeiId = supWeiId;
	}

	@Column(name = "ShopName", length = 128)
	public String getShopName() {
		return this.shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	@Column(name = "ShopPosition", length = 128)
	public String getShopPosition() {
		return this.shopPosition;
	}

	public void setShopPosition(String shopPosition) {
		this.shopPosition = shopPosition;
	}

	@Column(name = "ProductCount")
	public Integer getProductCount() {
		return this.productCount;
	}

	public void setProductCount(Integer productCount) {
		this.productCount = productCount;
	}

	@Column(name = "Ptype")
	public Short getPtype() {
		return this.ptype;
	}

	public void setPtype(Short ptype) {
		this.ptype = ptype;
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

}