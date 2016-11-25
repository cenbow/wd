package com.okwei.bean.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.okwei.bean.enums.ProductStatusEnum;

public class ProductDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Short fromSite;//标记访问来自pc/app: 1:app; 2:pc;

	private Long supWeiId; // 当前帐号对应的平台号id
	private String subWeiId; // 子供应商id,用于传递页面的参数
	private boolean isPTH; // 是否平台号
	private boolean isPPH;// 是否品牌号
	private boolean isPTZ;// 是否平台子供应商

	private boolean isDLS;// 是否代理商
	private boolean isLDD;// 是否落地店

	private String weiId;// 当前用户id
	private ProductStatusEnum status;
	
	private Integer shopClassId;//二级店铺分类id
	private Integer supShopClassId;//一级店铺分类id
	private List<Integer> shopClassIds = new ArrayList<Integer>();// 店铺分类分成二级后，需要支持多个二级分类的过滤
	private String title;
	/**
	 * 类型: -1表示页面选择全部;0表示分销；1表示自营;4表示代理;5表示落地分销 (对应于ShelveType枚举值);
	 */
	private Short type;
	// 标志页面是否点击"查询"按钮操作
	private Short isClick;

	public String getWeiId() {
		return weiId;
	}

	public void setWeiId(String weiId) {
		this.weiId = weiId;
	}

	public boolean isPTH() {
		return isPTH;
	}

	public void setPTH(boolean isPTH) {
		this.isPTH = isPTH;
	}

	public boolean isPPH() {
		return isPPH;
	}

	public void setPPH(boolean isPPH) {
		this.isPPH = isPPH;
	}

	public boolean isPTZ() {
		return isPTZ;
	}

	public void setPTZ(boolean isPTZ) {
		this.isPTZ = isPTZ;
	}

	public ProductStatusEnum getStatus() {
		return status;
	}

	public void setStatus(ProductStatusEnum status) {
		this.status = status;
	}

	public Short getType() {
		return type;
	}

	public void setType(Short type) {
		this.type = type;
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

	public Long getSupWeiId() {
		return supWeiId;
	}

	public void setSupWeiId(Long supWeiId) {
		this.supWeiId = supWeiId;
	}

	public boolean isDLS() {
		return isDLS;
	}

	public void setDLS(boolean isDLS) {
		this.isDLS = isDLS;
	}

	public boolean isLDD() {
		return isLDD;
	}

	public void setLDD(boolean isLDD) {
		this.isLDD = isLDD;
	}

	public Short getIsClick() {
		return isClick;
	}

	public void setIsClick(Short isClick) {
		this.isClick = isClick;
	}

	public String getSubWeiId() {
		return subWeiId;
	}

	public void setSubWeiId(String subWeiId) {
		this.subWeiId = subWeiId;
	}

	public List<Integer> getShopClassIds() {
		return shopClassIds;
	}

	public void setShopClassIds(List<Integer> shopClassIds) {
		this.shopClassIds = shopClassIds;
	}

	public Integer getSupShopClassId() {
		return supShopClassId;
	}

	public void setSupShopClassId(Integer supShopClassId) {
		this.supShopClassId = supShopClassId;
	}

	public Short getFromSite() {
		return fromSite;
	}

	public void setFromSite(Short fromSite) {
		this.fromSite = fromSite;
	}

}
