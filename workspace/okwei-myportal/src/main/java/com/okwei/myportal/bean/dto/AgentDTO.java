package com.okwei.myportal.bean.dto;

public class AgentDTO {
	private Long weiid;
	private Short agenttype;
	private Integer province;
	private Integer city;
	private Integer district;
	private Integer brandid;
	private Short isbrand;
	
	
	
	
	
	
	
	public Short getIsbrand() {
		return isbrand;
	}
	public void setIsbrand(Short isbrand) {
		this.isbrand = isbrand;
	}
	public Integer getBrandid() {
		return brandid;
	}
	public void setBrandid(Integer brandid) {
		this.brandid = brandid;
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
	public Long getWeiid() {
		return weiid;
	}
	public void setWeiid(Long weiid) {
		this.weiid = weiid;
	}
	public Short getAgenttype() {
		return agenttype;
	}
	public void setAgenttype(Short agenttype) {
		this.agenttype = agenttype;
	}
	
	

}
