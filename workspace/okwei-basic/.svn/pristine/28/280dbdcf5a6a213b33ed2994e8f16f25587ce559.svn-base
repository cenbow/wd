package com.okwei.service;

import java.util.List;
import java.util.Map;

import com.okwei.bean.domain.TRegional;


/**
 * @author 作者:齐李平  E-mail:qiliping@okwei.com
 * @version 1.0
 * @data 创建时间：2015年3月6日 下午1:37:04
 * 
 */
public interface TRegionalService {
	/**
	 * 获取全国所有省份
	 * @return TRegional列表
	 */
	public List<TRegional> getProvinceList();
	
	/**
	 * 根据父级Code获取地区列表
	 * @return TRegional列表
	 */
	public List<TRegional> getRegionalsByParent(int parentCode);
	/**
	 * 通过代码获取区域名称
	 * add by 阿甘
	 * @param code
	 * @return
	 */
    public String getNameByCode(int code);
    /**
     * 通过名称获取代码
     * add by 阿甘
     *
     * @param name
     * @return
     */
    public int getCodeByName(String name,int parentCode);
    /**
     * 根据经纬度获取省市区
     * add by 阿甘
     * @param lnglat
     * @return
     */
    public Map<String, String> getAddressByLngAndLat(String lnglat);

	int getCodeByName2(String name, int parentCode);
   
}
