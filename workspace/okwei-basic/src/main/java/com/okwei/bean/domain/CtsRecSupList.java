package com.okwei.bean.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CtsRecSupList entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CTS_RecSupList")
public class CtsRecSupList implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 6906476279442974006L;
	private Long listId;
	private Long logId;
	private Long weiId;
	private String shopName;
	private String img;
	private Short sort;
	private Short status;
	private Date createTime;
	private String reason;
    private String busType;
    private Integer productCount;
    private String marketName;
    private String area;
    private Short supTyp;
	// Constructors

	/** default constructor */
	public CtsRecSupList() {
	}

	/** full constructor */
	public CtsRecSupList(Long logId, Long weiId, String shopName, String img,
			Short sort, Short status, Date createTime) {
		this.logId = logId;
		this.weiId = weiId;
		this.shopName = shopName;
		this.img = img;
		this.sort = sort;
		this.status = status;
		this.createTime = createTime;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "ListID", unique = true, nullable = false)
	public Long getListId() {
		return this.listId;
	}

	public void setListId(Long listId) {
		this.listId = listId;
	}

	@Column(name = "LogID")
	public Long getLogId() {
		return this.logId;
	}

	public void setLogId(Long logId) {
		this.logId = logId;
	}

	@Column(name = "WeiID")
	public Long getWeiId() {
		return this.weiId;
	}

	public void setWeiId(Long weiId) {
		this.weiId = weiId;
	}

	@Column(name = "ShopName", length = 128)
	public String getShopName() {
		return this.shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	@Column(name = "Img", length = 128)
	public String getImg() {
		return this.img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	@Column(name = "Sort")
	public Short getSort() {
		return this.sort;
	}

	public void setSort(Short sort) {
		this.sort = sort;
	}

	@Column(name = "Status")
	public Short getStatus() {
		return this.status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	@Column(name = "CreateTime", length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Column(name="Reason")
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getBusType() {
		return busType;
	}

	public void setBusType(String busType) {
		this.busType = busType;
	}

	public Integer getProductCount() {
		return productCount;
	}

	public void setProductCount(Integer productCount) {
		this.productCount = productCount;
	}

	public String getMarketName() {
		return marketName;
	}

	public void setMarketName(String marketName) {
		this.marketName = marketName;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public Short getSupTyp() {
		return supTyp;
	}

	public void setSupTyp(Short supTyp) {
		this.supTyp = supTyp;
	}
}