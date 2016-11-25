package com.okwei.myportal.bean.dto;

import java.io.Serializable;

import com.okwei.bean.enums.ProductStatusEnum;

public class ProductDTO implements Serializable {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;

	private Long weiId;

	private ProductStatusEnum status;

	private Integer shopClassId;

	private String title;

	/**
	 * 类型: 0表示分销；1表示自营(对应于ShelveType枚举值); -1表示页面选择全部
	 */
	private Short type;

	// 标志页面是否点击"查询"按钮操作
	private Short isClick;

	public Long getWeiId() {
		return weiId;
	}

	public void setWeiId(Long weiId) {
		this.weiId = weiId;
	}

	public ProductStatusEnum getStatus() {
		return status;
	}

	public void setStatus(ProductStatusEnum status) {
		this.status = status;
	}

	public Integer getShopClassId() {
		return shopClassId;
	}

	public void setShopClassId(Integer shopClassId) {
		this.shopClassId = shopClassId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Short getType() {
		return type;
	}

	public void setType(Short type) {
		this.type = type;
	}

	public Short getIsClick() {
		return isClick;
	}

	public void setIsClick(Short isClick) {
		this.isClick = isClick;
	}

}
