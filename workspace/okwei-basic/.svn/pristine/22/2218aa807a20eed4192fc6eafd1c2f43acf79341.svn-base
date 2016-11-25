package com.okwei.bean.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * AShopRecommend entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "A_ShopRecommend")
public class AShopRecommend implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3728972174401768244L;
	// Fields

	private Integer recId;
	private String title;
	private Long shopWeiId;
	private String shopImg;
	private Integer sort;
	private Date beginTime;
	private Date endTime;
	private Short state;
	private Integer recNum;
	private String url;

	// Constructors

	/** default constructor */
	public AShopRecommend() {
	}

	/** full constructor */
	public AShopRecommend(String title, Long shopWeiId, String shopImg,
			Integer sort, Date beginTime, Date endTime, Short state,
			Integer recNum, String url) {
		this.title = title;
		this.shopWeiId = shopWeiId;
		this.shopImg = shopImg;
		this.sort = sort;
		this.beginTime = beginTime;
		this.endTime = endTime;
		this.state = state;
		this.recNum = recNum;
		this.url = url;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "RecID", unique = true, nullable = false)
	public Integer getRecId() {
		return this.recId;
	}

	public void setRecId(Integer recId) {
		this.recId = recId;
	}

	@Column(name = "Title", length = 128)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "ShopWeiID")
	public Long getShopWeiId() {
		return this.shopWeiId;
	}

	public void setShopWeiId(Long shopWeiId) {
		this.shopWeiId = shopWeiId;
	}

	@Column(name = "ShopImg", length = 128)
	public String getShopImg() {
		return this.shopImg;
	}

	public void setShopImg(String shopImg) {
		this.shopImg = shopImg;
	}

	@Column(name = "Sort")
	public Integer getSort() {
		return this.sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
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

	@Column(name = "RecNum")
	public Integer getRecNum() {
		return this.recNum;
	}

	public void setRecNum(Integer recNum) {
		this.recNum = recNum;
	}

	@Column(name = "Url", length = 128)
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}