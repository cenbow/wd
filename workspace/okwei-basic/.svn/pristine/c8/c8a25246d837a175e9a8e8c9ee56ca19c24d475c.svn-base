package com.okwei.bean.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * UBankCard entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "U_BankCard")
public class UBankCard extends BaseEntity {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long weiId;
	private String name;
	private String idcard;
	private String banckName;
	private String banckMark;
	private String banckCard;
	private Short cardType;
	private Date createTime;

	@Column(name = "BanckMark")
	public String getBanckMark() {
		return banckMark;
	}

	public void setBanckMark(String banckMark) {
		this.banckMark = banckMark;
	}

	// Constructors

	/** default constructor */
	public UBankCard() {
	}

	@Id
	@GeneratedValue
	@Column(name = "ID")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "WeiID")
	public Long getWeiId() {
		return weiId;
	}

	public void setWeiId(Long weiId) {
		this.weiId = weiId;
	}

	@Column(name = "Name", length = 32)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "IDCard", length = 32)
	public String getIdcard() {
		return this.idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	@Column(name = "BanckName", length = 128)
	public String getBanckName() {
		return this.banckName;
	}

	public void setBanckName(String banckName) {
		this.banckName = banckName;
	}

	@Column(name = "BanckCard", length = 32)
	public String getBanckCard() {
		return this.banckCard;
	}

	public void setBanckCard(String banckCard) {
		this.banckCard = banckCard;
	}

	@Column(name = "CardType")
	public Short getCardType() {
		return this.cardType;
	}

	public void setCardType(Short cardType) {
		this.cardType = cardType;
	}

	@Column(name = "CreateTime", length = 0)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}