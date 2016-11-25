package com.okwei.pay.bean.vo;

import java.io.Serializable;
import java.util.Date;

import com.okwei.bean.domain.UBankCard;

public class BankCardVO implements Serializable {

	private Long id;
	private Long weiId;
	private String name;
	private String idcard;
	private String banckName;
	
	public BankCardVO(UBankCard bc) {
		
		super();
		this.id = bc.getId();
		this.weiId = bc.getWeiId();
		this.name = bc.getName();
		this.idcard = bc.getIdcard();
		this.banckName = bc.getBanckName();
		this.banckMark = bc.getBanckMark();
		this.banckCard = bc.getBanckCard();
		this.cardType = bc.getCardType();
		this.createTime = bc.getCreateTime();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getWeiId() {
		return weiId;
	}
	public void setWeiId(Long weiId) {
		this.weiId = weiId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public String getBanckName() {
		return banckName;
	}
	public void setBanckName(String banckName) {
		this.banckName = banckName;
	}
	public String getBanckMark() {
		return banckMark;
	}
	public void setBanckMark(String banckMark) {
		this.banckMark = banckMark;
	}
	public String getBanckCard() {
		return banckCard;
	}
	public void setBanckCard(String banckCard) {
		this.banckCard = banckCard;
	}
	public Short getCardType() {
		return cardType;
	}
	public void setCardType(Short cardType) {
		this.cardType = cardType;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	private String banckMark;
	private String banckCard;
	private Short cardType;
	private Date createTime;

}
