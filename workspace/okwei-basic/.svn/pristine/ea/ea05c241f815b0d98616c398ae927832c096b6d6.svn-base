package com.okwei.bean.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * ADeals entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "A_Deals")
public class ADeals implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2557556121091130030L;
	// Fields

	private Integer dealsId;
	private Date dealsDate;
	private String dealsTitle;
	private String picturesHome;
	private String dealsBigPicture;
	private Integer count;
	private Short state;
	private Date createTime;

	// Constructors

	/** default constructor */
	public ADeals() {
	}

	/** full constructor */
	public ADeals(Date dealsDate, String dealsTitle, String picturesHome,
			String dealsBigPicture, Integer count, Short state,
			Date createTime) {
		this.dealsDate = dealsDate;
		this.dealsTitle = dealsTitle;
		this.picturesHome = picturesHome;
		this.dealsBigPicture = dealsBigPicture;
		this.count = count;
		this.state = state;
		this.createTime = createTime;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "DealsID", unique = true, nullable = false)
	public Integer getDealsId() {
		return this.dealsId;
	}

	public void setDealsId(Integer dealsId) {
		this.dealsId = dealsId;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DealsDate", length = 0)
	public Date getDealsDate() {
		return this.dealsDate;
	}

	public void setDealsDate(Date dealsDate) {
		this.dealsDate = dealsDate;
	}

	@Column(name = "DealsTitle", length = 64)
	public String getDealsTitle() {
		return this.dealsTitle;
	}

	public void setDealsTitle(String dealsTitle) {
		this.dealsTitle = dealsTitle;
	}

	@Column(name = "PicturesHome", length = 256)
	public String getPicturesHome() {
		return this.picturesHome;
	}

	public void setPicturesHome(String picturesHome) {
		this.picturesHome = picturesHome;
	}

	@Column(name = "DealsBigPicture", length = 256)
	public String getDealsBigPicture() {
		return this.dealsBigPicture;
	}

	public void setDealsBigPicture(String dealsBigPicture) {
		this.dealsBigPicture = dealsBigPicture;
	}

	@Column(name = "Count")
	public Integer getCount() {
		return this.count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	@Column(name = "State")
	public Short getState() {
		return this.state;
	}

	public void setState(Short state) {
		this.state = state;
	}

	@Column(name = "CreateTime", length = 0)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}