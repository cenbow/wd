package com.okwei.service;

import java.util.List;
import java.util.Map;

import com.okwei.bean.domain.TRegional;
import com.okwei.bean.domain.UBatchVerifierRegion;

public interface IRegionService extends IBaseService {

	/**
	 * 获取全国所有省份
	 * 
	 * @return TRegional列表
	 */
	List<TRegional> getProvinceList();

	/**
	 * 根据父级Code获取地区列表
	 * 
	 * @return TRegional列表
	 */
	List<TRegional> getRegionalsByParent(int parentCode);

	/**
	 * 通过代码获取区域名称 add by 阿甘
	 * 
	 * @param code
	 * @return
	 */
	String getNameByCode(Integer code);

	/**
	 * 通过名称获取代码 add by 阿甘
	 *
	 * @param name
	 * @return
	 */
	int getCodeByName(String name, int parentCode);

	/**
	 * 根据经纬度获取省市区 add by 阿甘
	 * 
	 * @param lnglat
	 * @return
	 */
	Map<String, String> getAddressByLngAndLat(String lnglat);

}
