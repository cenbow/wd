package com.okwei.bean.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * APerfectProducts entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "A_PerfectProducts")
public class APerfectProducts implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -2592646813665907355L;
	private Long perPid;
	private Long productId;
	private String title;
	private Long sellerId;
	private Integer sort;
	private Date createTime;
	private Date beginTime;
	private Date endTime;
	private Short state;
	private String url;
	private String productImg;
	private Integer recNum;

	// Constructors

	/** default constructor */
	public APerfectProducts() {
	}

	/** minimal constructor */
	public APerfectProducts(Long perPid) {
		this.perPid = perPid;
	}

	/** full constructor */
	public APerfectProducts( Long productId, String title,
			Long sellerId, Integer sort, Date createTime,
			Date beginTime, Date endTime, Short state, String url,
			String productImg,Integer recNum) {
		this.productId = productId;
		this.title = title;
		this.sellerId = sellerId;
		this.sort = sort;
		this.createTime = createTime;
		this.beginTime = beginTime;
		this.endTime = endTime;
		this.state = state;
		this.url = url;
		this.productImg = productImg;
		this.recNum=recNum;
	}

	// Property accessors

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "PerPID", unique = true, nullable = false)
	public Long getPerPid() {
		return this.perPid;
	}

	public void setPerPid(Long perPid) {
		this.perPid = perPid;
	}

	@Column(name = "ProductID")
	public Long getProductId() {
		return this.productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	@Column(name = "Title", length = 128)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "SellerID")
	public Long getSellerId() {
		return this.sellerId;
	}

	public void setSellerId(Long sellerId) {
		this.sellerId = sellerId;
	}

	@Column(name = "Sort")
	public Integer getSort() {
		return this.sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	@Column(name = "CreateTime", length = 0)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "BeginTime", length = 0)
	public Date getBeginTime() {
		return this.beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	@Column(name = "EndTime", length = 0)
	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	@Column(name = "State")
	public Short getState() {
		return this.state;
	}

	public void setState(Short state) {
		this.state = state;
	}

	@Column(name = "Url", length = 128)
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "ProductImg", length = 128)
	public String getProductImg() {
		return this.productImg;
	}

	public void setProductImg(String productImg) {
		this.productImg = productImg;
	}

	@Column(name = "RecNum")
	public Integer getRecNum() {
		return recNum;
	}

	public void setRecNum(Integer recNum) {
		this.recNum = recNum;
	}
	
	

}