package com.okwei.bean.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CompanyInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long weiId;
	private String shopName;
	private String shopImg;
	private String industry;
	private String areaStr;
	private String intro;
	private String pinpaiTese;
	private String companyName;
	private String address;
	private String contact;
	private String tel;
	private String mobile;
	private String qq;

	// 权益保障
	private List<Map<String, String>> baozhang = new ArrayList<Map<String, String>>();
	// 资质列表
	private List<Map<String, String>> zizhi = new ArrayList<Map<String, String>>();

	public Long getWeiId() {
		return weiId;
	}

	public void setWeiId(Long weiId) {
		this.weiId = weiId;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getShopImg() {
		return shopImg;
	}

	public void setShopImg(String shopImg) {
		this.shopImg = shopImg;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getAreaStr() {
		return areaStr;
	}

	public void setAreaStr(String areaStr) {
		this.areaStr = areaStr;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getPinpaiTese() {
		return pinpaiTese;
	}

	public void setPinpaiTese(String pinpaiTese) {
		this.pinpaiTese = pinpaiTese;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public List<Map<String, String>> getBaozhang() {
		return baozhang;
	}

	public void setBaozhang(List<Map<String, String>> baozhang) {
		this.baozhang = baozhang;
	}

	public List<Map<String, String>> getZizhi() {
		return zizhi;
	}

	public void setZizhi(List<Map<String, String>> zizhi) {
		this.zizhi = zizhi;
	}

}
