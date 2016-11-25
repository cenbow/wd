package com.okwei.myportal.bean.vo;

import java.io.Serializable;
import java.util.Date;

public class MyAgentBrandVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8487253711001929418L;

	private Long weiid;
	private Integer brandid;
	private String brandname;
	private Integer province;
	private Integer city;
	private Integer district;
	private String showarea;
	private String agenttype;
	private Integer type;
	private Date applytime;
	private String showapplytime;
	private Double bond;
	private Integer level;
	private Integer status;
	private String payOrderId;
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Long getWeiid() {
		return weiid;
	}
	public void setWeiid(Long weiid) {
		this.weiid = weiid;
	}
	public Integer getBrandid() {
		return brandid;
	}
	public void setBrandid(Integer brandid) {
		this.brandid = brandid;
	}
	public String getBrandname() {
		return brandname;
	}
	public void setBrandname(String brandname) {
		this.brandname = brandname;
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
	public String getShowarea() {
		return showarea;
	}
	public void setShowarea(String showarea) {
		this.showarea = showarea;
	}
	public String getAgenttype() {
		return agenttype;
	}
	public void setAgenttype(String agenttype) {
		this.agenttype = agenttype;
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
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public String getPayOrderId() {
		return payOrderId;
	}
	public void setPayOrderId(String payOrderId) {
		this.payOrderId = payOrderId;
	}
	
	
	
	
	

}
