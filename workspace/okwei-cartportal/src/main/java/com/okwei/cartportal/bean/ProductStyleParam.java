package com.okwei.cartportal.bean;

import java.util.List;
import java.util.Map;

import com.okwei.bean.domain.PProductSellKey;
import com.okwei.bean.domain.PProductSellValue;

/**
 * 商品属性
 * @author yangjunjun
 *
 */
public class ProductStyleParam {
	/**属性*/
	private PProductSellKey proSellKey;
	/**属性对应的款式*/
	private List<PProductSellValue> proSellValue;
	/**商品已选择的款式*/
	private Map<Long,Long> selectStyle;
	
	public PProductSellKey getProSellKey() {
		return proSellKey;
	}
	public void setProSellKey(PProductSellKey proSellKey) {
		this.proSellKey = proSellKey;
	}
	public List<PProductSellValue> getProSellValue() {
		return proSellValue;
	}
	public void setProSellValue(List<PProductSellValue> proSellValue) {
		this.proSellValue = proSellValue;
	}
	public Map<Long, Long> getSelectStyle() {
		return selectStyle;
	}
	public void setSelectStyle(Map<Long, Long> selectStyle) {
		this.selectStyle = selectStyle;
	}
}
