package com.okwei.bean.vo;

public class RegionVO {
	
	private Integer provice;
	
	private Integer city;
	
	private Integer area;

	private Integer code;

	private String codeName;
	

	
	
	public void setCode(Integer code) {
		this.code = code;
	}

	public Integer getProvice() {
		return provice;
	}

	public void setProvice(Integer provice) {
		this.provice = provice;
	}

	public Integer getCity() {
		return city;
	}

	public void setCity(Integer city) {
		this.city = city;
	}

	public Integer getArea() {
		return area;
	}

	public void setArea(Integer area) {
		this.area = area;
	}

	public Integer getCode() {
		return code;
	}


	public String getCodeName() {
		return codeName;
	}

	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}
	
}
