package com.okwei.bean.vo.product;

public class PostAgeModelVO {
	/**
	 * 邮费模板id
	 */
	private Integer freightId;
	/**
	 * 邮费模板名称
	 */
	private String freightName;
	
	private Integer type;
	
	private String typeName;

	public Integer getFreightId() {
		return freightId;
	}

	public void setFreightId(Integer freightId) {
		this.freightId = freightId;
	}

	public String getFreightName() {
		return freightName;
	}

	public void setFreightName(String freightName) {
		this.freightName = freightName;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
}
