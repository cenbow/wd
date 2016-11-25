package com.okwei.bean.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * PProductSup entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "P_ProductSup")
public class PProductSup implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8682028731013529108L;
	// Fields

	private Long productId;
	private String productTitle;
	private String productMinTitle;
	private Integer sid;
	private Integer classId;
	private String pcdes;
	private String appdes;
	private Short state;
	private Date createTime;
	private Date updateTime;
	private Long weiID;
	private String childrenID;
	private Integer stock;
	private String defaultImg;
	private Integer brandID;
	private Double defaultPrice;
	private Double advicePrice;
	private String reason;
	// Constructors


	/** default constructor */
	public PProductSup() {
	}

	/** minimal constructor */
	public PProductSup(Long productId, Date createTime,
			Date updateTime) {
		this.productId = productId;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}

	/** full constructor */
	public PProductSup(Long productId, String productTitle, Integer sid,
			Integer classId, String pcdes, String appdes, Short state, Date createTime,
			Date updateTime,Integer brandID,Double defaultPrice,
			Double advicePrice,Long weiID,String childrenID,String reason) {
		this.productId = productId;
		this.productTitle = productTitle;
		this.sid = sid;
		this.classId = classId;
		this.pcdes = pcdes;
		this.appdes = appdes;
		this.state = state;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.weiID=weiID;
		this.childrenID=childrenID;
		this.brandID= brandID;
		this.defaultPrice = defaultPrice;
		this.advicePrice = advicePrice;
		this.reason=reason;
	}
	
	
	@Column(name = "BrandID")
	public Integer getBrandID() {
		return brandID;
	}
	
	public void setBrandID(Integer brandID) {
		this.brandID = brandID;
	}

	// Property accessors
	@Id
	@Column(name = "ProductID", unique = true, nullable = false)
	public Long getProductId() {
		return this.productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	@Column(name = "ProductTitle", length = 128)
	public String getProductTitle() {
		return this.productTitle;
	}

	public void setProductTitle(String productTitle) {
		this.productTitle = productTitle;
	}

	@Column(name = "SID")
	public Integer getSid() {
		return this.sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	@Column(name = "ClassID")
	public Integer getClassId() {
		return this.classId;
	}

	public void setClassId(Integer classId) {
		this.classId = classId;
	}

	@Column(name = "PCDES")
	public String getPcdes() {
		return this.pcdes;
	}

	public void setPcdes(String pcdes) {
		this.pcdes = pcdes;
	}

	@Column(name = "APPDES")
	public String getAppdes() {
		return this.appdes;
	}

	public void setAppdes(String appdes) {
		this.appdes = appdes;
	}

	

	@Column(name = "State")
	public Short getState() {
		return this.state;
	}

	public void setState(Short state) {
		this.state = state;
	}
	
	
	@Column(name = "CreateTime", nullable = false, length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "UpdateTime", nullable = false, length = 19)
	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	@Column(name = "WeiID")
	public Long getWeiID() {
	    return weiID;
	}
	
	public void setWeiID(Long weiID) {
	    this.weiID = weiID;
	}
	@Column(name = "ChildrenID")
	public String getChildrenID() {
	    return childrenID;
	}

	public void setChildrenID(String childrenID) {
	    this.childrenID = childrenID;
	}

	@Column(name = "ProductMinTitle")
	public String getProductMinTitle() {
	    return productMinTitle;
	}

	public void setProductMinTitle(String productMinTitle) {
	    this.productMinTitle = productMinTitle;
	}
	@Column(name = "Stock")
	public Integer getStock() {
	    return stock;
	}

	public void setStock(Integer stock) {
	    this.stock = stock;
	}
	
	@Column(name = "DefaultImg", length = 256)
	public String getDefaultImg() {
		return this.defaultImg;
	}

	public void setDefaultImg(String defaultImg) {
		this.defaultImg = defaultImg;
	}
	
	@Column(name = "DefaultPrice", precision = 18)
	public Double getDefaultPrice() {
		return this.defaultPrice;
	}

	public void setDefaultPrice(Double defaultPrice) {
		this.defaultPrice = defaultPrice;
	}

	@Column(name = "AdvicePrice", precision = 18)
	public Double getAdvicePrice() {
		return this.advicePrice;
	}

	public void setAdvicePrice(Double advicePrice) {
		this.advicePrice = advicePrice;
	}
	@Column(name = "Reason", length = 128)
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	

}