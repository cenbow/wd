package com.okwei.bean.vo.shoppingcart;

import com.okwei.bean.domain.PProductStyleKv;

public class PProductStyleKVVO extends PProductStyleKv{
	//key名称
	private String attributeName;
	//value名称
	private String  keyIdName;
	public String getAttributeName() {
		return attributeName;
	}
	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}
	public String getKeyIdName() {
		return keyIdName;
	}
	public void setKeyIdName(String keyIdName) {
		this.keyIdName = keyIdName;
	}
}
