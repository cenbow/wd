package com.okwei.bean.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * AActShowProducts entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "A_ActShowProducts")
public class AActShowProducts implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3838663421233260325L;
	// Fields

	private Long proActId;
	private Long actId;
	private Long sellerId;
	private Integer classId;
	private Long productId;
	private String productTitle;
	private String productImg;
	private Double price;
	private Double commission;
	private Date createTime;
	private Integer stockCount;

	// Constructors

	/** default constructor */
	public AActShowProducts() {
	}

	/** minimal constructor */
	public AActShowProducts(Long proActId) {
		this.proActId = proActId;
	}

	/** full constructor */
	public AActShowProducts(Long proActId, Long actId, Long sellerId,
			Integer classId, Long productId, String productTitle,
			String productImg, Double price, Double commission, Date createTime) {
		this.proActId = proActId;
		this.actId = actId;
		this.sellerId = sellerId;
		this.classId = classId;
		this.productId = productId;
		this.productTitle = productTitle;
		this.productImg = productImg;
		this.price = price;
		this.commission = commission;
		this.createTime = createTime;
	}

	// Property accessors
	@Id
	@Column(name = "ProActID", unique = true, nullable = false)
	public Long getProActId() {
		return this.proActId;
	}

	public void setProActId(Long proActId) {
		this.proActId = proActId;
	}

	@Column(name = "ActID")
	public Long getActId() {
		return this.actId;
	}

	public void setActId(Long actId) {
		this.actId = actId;
	}

	@Column(name = "SellerID")
	public Long getSellerId() {
		return this.sellerId;
	}

	public void setSellerId(Long sellerId) {
		this.sellerId = sellerId;
	}

	@Column(name = "ClassID")
	public Integer getClassId() {
		return this.classId;
	}

	public void setClassId(Integer classId) {
		this.classId = classId;
	}

	@Column(name = "ProductID")
	public Long getProductId() {
		return this.productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	@Column(name = "ProductTitle", length = 256)
	public String getProductTitle() {
		return this.productTitle;
	}

	public void setProductTitle(String productTitle) {
		this.productTitle = productTitle;
	}

	@Column(name = "ProductImg", length = 128)
	public String getProductImg() {
		return this.productImg;
	}

	public void setProductImg(String productImg) {
		this.productImg = productImg;
	}

	@Column(name = "Price", precision = 18 )
	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Column(name = "Commission", precision = 18 )
	public Double getCommission() {
		return this.commission;
	}

	public void setCommission(Double commission) {
		this.commission = commission;
	}

	@Column(name = "CreateTime", length = 0)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "StockCount")
	public Integer getStockCount() {
		return this.stockCount;
	}

	public void setStockCount(Integer stockCount) {
		this.stockCount = stockCount;
	}
}