package com.okwei.bean.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * SStatics entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "S_Statics")
public class SStatics implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -6408247754229192920L;
	private Long shareId;
	private Long weiId;
	private Integer pcount;
	private Integer webPv;
	private Integer webSv;
	private Integer wapSv;
	private Integer wapPv;
	private Integer appSv;
	private Integer appPv;
	private Integer vol;
	private Double turnover;
	private Double commission;
	private Integer zanCount;

	// Constructors

	/** default constructor */
	public SStatics() {
	}

	/** minimal constructor */
	public SStatics(Long shareId) {
		this.shareId = shareId;
	}

	/** full constructor */
	public SStatics(Long shareId, Long weiId, Integer pcount, Integer webPv,
			Integer webSv, Integer wapSv, Integer wapPv, Integer appSv,
			Integer appPv, Integer vol, Double turnover, Double commission) {
		this.shareId = shareId;
		this.weiId = weiId;
		this.pcount = pcount;
		this.webPv = webPv;
		this.webSv = webSv;
		this.wapSv = wapSv;
		this.wapPv = wapPv;
		this.appSv = appSv;
		this.appPv = appPv;
		this.vol = vol;
		this.turnover = turnover;
		this.commission = commission;
	}

	// Property accessors
	@Id
	@Column(name = "ShareID", unique = true, nullable = false)
	public Long getShareId() {
		return this.shareId;
	}

	public void setShareId(Long shareId) {
		this.shareId = shareId;
	}

	@Column(name = "WeiID")
	public Long getWeiId() {
		return this.weiId;
	}

	public void setWeiId(Long weiId) {
		this.weiId = weiId;
	}

	@Column(name = "Pcount")
	public Integer getPcount() {
		return this.pcount;
	}

	public void setPcount(Integer pcount) {
		this.pcount = pcount;
	}

	@Column(name = "WebPV")
	public Integer getWebPv() {
		return this.webPv;
	}

	public void setWebPv(Integer webPv) {
		this.webPv = webPv;
	}

	@Column(name = "WebSV")
	public Integer getWebSv() {
		return this.webSv;
	}

	public void setWebSv(Integer webSv) {
		this.webSv = webSv;
	}

	@Column(name = "WapSV")
	public Integer getWapSv() {
		return this.wapSv;
	}

	public void setWapSv(Integer wapSv) {
		this.wapSv = wapSv;
	}

	@Column(name = "WapPV")
	public Integer getWapPv() {
		return this.wapPv;
	}

	public void setWapPv(Integer wapPv) {
		this.wapPv = wapPv;
	}

	@Column(name = "AppSV")
	public Integer getAppSv() {
		return this.appSv;
	}

	public void setAppSv(Integer appSv) {
		this.appSv = appSv;
	}

	@Column(name = "AppPV")
	public Integer getAppPv() {
		return this.appPv;
	}

	public void setAppPv(Integer appPv) {
		this.appPv = appPv;
	}

	@Column(name = "VOL")
	public Integer getVol() {
		return this.vol;
	}

	public void setVol(Integer vol) {
		this.vol = vol;
	}

	@Column(name = "Turnover", precision = 18)
	public Double getTurnover() {
		return this.turnover;
	}

	public void setTurnover(Double turnover) {
		this.turnover = turnover;
	}

	@Column(name = "Commission", precision = 18)
	public Double getCommission() {
		return this.commission;
	}

	public void setCommission(Double commission) {
		this.commission = commission;
	}
	
	@Column(name = "ZanCount")
	public Integer getZanCount() {
		return this.zanCount;
	}

	public void setZanCount(Integer zanCount) {
		this.zanCount = zanCount;
	}
	

}