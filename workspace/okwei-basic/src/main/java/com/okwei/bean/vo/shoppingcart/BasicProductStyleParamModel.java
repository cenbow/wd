package com.okwei.bean.vo.shoppingcart;

import java.util.List;

public class BasicProductStyleParamModel {
	//款式列表
	private List<BasicProductStyleParam> basicProductStyleParamList;
	//选中的款式
	private String jsonPProductStyleKVVOList;


	public String getJsonPProductStyleKVVOList() {
		return jsonPProductStyleKVVOList;
	}

	public void setJsonPProductStyleKVVOList(String jsonPProductStyleKVVOList) {
		this.jsonPProductStyleKVVOList = jsonPProductStyleKVVOList;
	}

	public List<BasicProductStyleParam> getBasicProductStyleParamList() {
		return basicProductStyleParamList;
	}

	public void setBasicProductStyleParamList(
			List<BasicProductStyleParam> basicProductStyleParamList) {
		this.basicProductStyleParamList = basicProductStyleParamList;
	}
}
