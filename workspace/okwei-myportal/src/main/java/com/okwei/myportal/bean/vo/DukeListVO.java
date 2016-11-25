package com.okwei.myportal.bean.vo;

import java.io.Serializable;
import java.util.Date;

public class DukeListVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8487253711001929418L;
	private Long agentid;
	private Long weiid;
	private Integer brandid;
	private String shopname;
	private Integer province;
	private Integer city;
	private Integer district;
	private String showarea;
	private String mobile;
	private String qq;
	private Integer type;
	private Date applytime;
	private String showapplytime;
	private Double bond;
	
	
	public String getShowarea() {
		return showarea;
	}
	public void setShowarea(String showarea) {
		this.showarea = showarea;
	}
	public Long getAgentid() {
		return agentid;
	}
	public void setAgentid(Long agentid) {
		this.agentid = agentid;
	}
	public Integer getBrandid() {
		return brandid;
	}
	public void setBrandid(Integer brandid) {
		this.brandid = brandid;
	}
	public Long getWeiid() {
		return weiid;
	}
	public void setWeiid(Long weiid) {
		this.weiid = weiid;
	}
	public String getShopname() {
		return shopname;
	}
	public void setShopname(String shopname) {
		this.shopname = shopname;
	}
	public Integer getProvince() {
		return province;
	}
	public void setProvince(Integer province) {
		this.province = province;
	}
	public Integer getCity() {
		return city;
	}
	public void setCity(Integer city) {
		this.city = city;
	}
	public Integer getDistrict() {
		return district;
	}
	public void setDistrict(Integer district) {
		this.district = district;
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
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Date getApplytime() {
		return applytime;
	}
	public void setApplytime(Date applytime) {
		this.applytime = applytime;
	}
	public String getShowapplytime() {
		return showapplytime;
	}
	public void setShowapplytime(String showapplytime) {
		this.showapplytime = showapplytime;
	}
	public Double getBond() {
		return bond;
	}
	public void setBond(Double bond) {
		this.bond = bond;
	}
	
	

}
