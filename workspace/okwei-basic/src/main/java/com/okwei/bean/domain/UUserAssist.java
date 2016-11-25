package com.okwei.bean.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * UUserAssist entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "U_UserAssist")
public class UUserAssist extends BaseEntity {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long weiId;
	private Long weiIdSort;
	private Integer sqflcount;
	private Integer sqppcount;
	private String signature;
	private String classdb;
	private String botFloor;
	private Integer distriCount;
	private Integer ontimeCount;
	private String webInfo;
	private Short showTips;
	private Short isAllowWallet;
	private Short hasInvoice;
	private Integer identity;

	// 预警库存
	private Integer warningNum;

	// Constructors
	@Column(name = "sqflcount")
	public Integer getSqflcount() {
		return sqflcount;
	}

	public void setSqflcount(Integer sqflcount) {
		this.sqflcount = sqflcount;
	}

	@Column(name = "sqppcount")
	public Integer getSqppcount() {
		return sqppcount;
	}

	public void setSqppcount(Integer sqppcount) {
		this.sqppcount = sqppcount;
	}

	@Column(name = "signature", length = 128)
	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	@Column(name = "classdb", length = 128)
	public String getClassdb() {
		return classdb;
	}

	public void setClassdb(String classdb) {
		this.classdb = classdb;
	}

	@Column(name = "BotFloor", length = 32)
	public String getBotFloor() {
		return botFloor;
	}

	public void setBotFloor(String botFloor) {
		this.botFloor = botFloor;
	}

	@Column(name = "distriCount")
	public Integer getDistriCount() {
		return distriCount;
	}

	public void setDistriCount(Integer distriCount) {
		this.distriCount = distriCount;
	}

	@Column(name = "ontimeCount")
	public Integer getOntimeCount() {
		return ontimeCount;
	}

	public void setOntimeCount(Integer ontimeCount) {
		this.ontimeCount = ontimeCount;
	}

	@Column(name = "webInfo", length = 128)
	public String getWebInfo() {
		return webInfo;
	}

	public void setWebInfo(String webInfo) {
		this.webInfo = webInfo;
	}

	@Column(name = "ShowTips")
	public Short getShowTips() {
		return showTips;
	}

	public void setShowTips(Short showTips) {
		this.showTips = showTips;
	}

	@Column(name = "IsAllowWallet")
	public Short getIsAllowWallet() {
		return isAllowWallet;
	}

	public void setIsAllowWallet(Short isAllowWallet) {
		this.isAllowWallet = isAllowWallet;
	}

	/** default constructor */
	public UUserAssist() {
	}

	// Property accessors
	@Id
	@Column(name = "WeiID", unique = true, nullable = false)
	public Long getWeiId() {
		return this.weiId;
	}

	public void setWeiId(Long weiId) {
		this.weiId = weiId;
	}

	public Long getWeiIdSort() {
		return weiIdSort;
	}

	public void setWeiIdSort(Long weiIdSort) {
		this.weiIdSort = weiIdSort;
	}

	@Column(name = "HasInvoice")
	public Short getHasInvoice() {
		return hasInvoice;
	}

	public void setHasInvoice(Short hasInvoice) {
		this.hasInvoice = hasInvoice;
	}

	@Column(name = "Identity")
	public Integer getIdentity() {
		return identity;
	}

	public void setIdentity(Integer identity) {
		this.identity = identity;
	}

	@Column(name = "WarningNum")
	public Integer getWarningNum() {
		return this.warningNum;
	}

	public void setWarningNum(Integer warningNum) {
		this.warningNum = warningNum;
	}

}