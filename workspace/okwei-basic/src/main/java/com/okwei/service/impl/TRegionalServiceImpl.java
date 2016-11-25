package com.okwei.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;




import com.okwei.bean.domain.TRegional;
import com.okwei.dao.impl.BaseDAO;
/**
 * @author 作者:齐李平  E-mail:qiliping@okwei.com
 * @version 1.0
 * @data 创建时间：2015年3月6日 下午1:44:50
 * 
 */
import com.okwei.service.TRegionalService;
import com.okwei.util.RedisUtil;

@Component("regionalService")
public class TRegionalServiceImpl implements TRegionalService {

	@Autowired
	private BaseDAO baseDAO;

	/*
	 * 获取所有省市列表
	 * 
	 * @see com.okwei.service.TRegionalService#getProvinceList()
	 */
	@Override
	public List<TRegional> getProvinceList() {
		String hql = " from TRegional t where t.level=2";// 查询语句
		List<TRegional> list = baseDAO.find(hql);

		return list;
	}

	/*
	 * 根据父级Code获取地区列表
	 * 
	 * @see com.okwei.service.TRegionalService#getRegionalsByParent(int)
	 */
	@Override
	public List<TRegional> getRegionalsByParent(int parentCode) {

		String hql = " from TRegional t where t.parent=?";// 查询语句
		Object[] b = new Object[1];// 参数列表
		b[0] = parentCode;
		List<TRegional> list = baseDAO.find(hql, b);

		return list;
	}

	/**
	 * 通过代码获取区域名称
	 * add by 阿甘
	 */
	@Override
	public String getNameByCode(int code) {
		//先从缓存里找
		List<TRegional> regionalList = (List<TRegional>) RedisUtil.getObject("regional");
		if(regionalList == null || regionalList.size()==0)
		{
			//缓存里没有查询数据库
			String hql = " from TRegional t";// 查询语句
			regionalList = baseDAO.find(hql);
			RedisUtil.setObject("regional", regionalList);
		}
		
		String name="";
		for(TRegional tr:regionalList)
		{
			if(tr.getCode().equals(code))
			{
				name = tr.getName();
				break;
			}
		}
		return name;
	}
    /**
     * 通过名称和上级代码获取代码
     * add by 阿甘
     * 
     */
	@Override
	public int getCodeByName(String name,int parentCode) {
		//转换数据库与百度接口来的数据统一
        switch (name)
        {
            case "新疆维吾尔自治区":
            	name = "新  疆";
                break;
            case "广西壮族自治区":
            	name = "广  西";
                break;
            case "西藏自治区":
            	name = "西  藏";
                break;
            case "宁夏回族自治区":
            	name = "宁  夏";
                break;
            case "香港特别行政区":
            	name = "香  港";
                break;
            case "澳门特别行政区":
            	name = "澳  门";
                break;
            default: break;
        }

        if (parentCode == 820000)
        	name = "澳门";
        else if (810000 == parentCode)
        	name = "香港";

      //先从缓存里找
  		List<TRegional> regionalList = (List<TRegional>) RedisUtil.getObject("regional");
  		if(regionalList == null || regionalList.size()==0)
  		{
  			//缓存里没有查询数据库
  			String hql = " from TRegional t";// 查询语句
  			regionalList = baseDAO.find(hql);
  			RedisUtil.setObject("regional", regionalList);
  		}
  		
  		int code=0;
  		for(TRegional tr:regionalList)
  		{
  			if(tr.getParent().equals(parentCode) && tr.getName().contains(name))
  			{
  				code = tr.getCode();
  				break;
  			}
  		}
  		return code;
        
	}
	
	/**
     * 通过名称和上级代码获取代码
     * add by 欧阳 ，代码从getCodeByName复制，略做修改，不动原来的getCodeByName
     * 
     */
	@Override
	public int getCodeByName2(String name,int parentCode) {
		//转换数据库与百度接口来的数据统一
		 switch (name)
	        {
	            case "新疆维吾尔自治区":
	            	name = "新疆";
	                break;
	            case "内蒙古自治区":
	            	name = "内蒙古";
	                break;
	            case "广西壮族自治区":
	            	name = "广西";
	                break;
	            case "西藏自治区":
	            	name = "西藏";
	                break;
	            case "宁夏回族自治区":
	            	name = "宁夏";
	                break;
	            case "香港特别行政区":
	            	name = "香港";
	                break;
	            case "澳门特别行政区":
	            	name = "澳门";
	                break;
	            default: break;
	        }

        if (parentCode == 820000)
        	name = "澳门";
        else if (810000 == parentCode)
        	name = "香港";

      //先从缓存里找
  		List<TRegional> regionalList = (List<TRegional>) RedisUtil.getObject("regional");
  		if(regionalList == null || regionalList.size()==0)
  		{
  			//缓存里没有查询数据库
  			String hql = " from TRegional t";// 查询语句
  			regionalList = baseDAO.find(hql);
  			RedisUtil.setObject("regional", regionalList);
  		}
  		
  		int code=0;
  		for(TRegional tr:regionalList)
  		{
  			if((( parentCode > 0 && tr.getParent().equals(parentCode)) || parentCode == 0) && tr.getName().equals(name))
  			{
  				code = tr.getCode();
  				break;
  			}
  		}
  		return code;
        
	}
	
	
    /**
     * 根据经纬度获取省市区
     * add by 阿甘
     * date 2015 3 25
     */
	@Override
	public Map<String, String> getAddressByLngAndLat(String lnglat) {
		Map<String, String> map = new HashMap<String, String>();
		String url = "http://api.map.baidu.com/geocoder/v2/?ak=1b0ee1b8dfb2df70f543d80e5d216a1a&location=" + lnglat
				+ "&output=json&pois=1";
		String json = loadJSON(url);
		JSONObject obj = JSONObject.fromObject(json);
		if (obj.get("status").toString().equals("0")) {//调用成功
			String detail=obj.getJSONObject("result").getString("formatted_address");
			String city = obj.getJSONObject("result").getJSONObject("addressComponent").getString("city");
			String province = obj.getJSONObject("result").getJSONObject("addressComponent").getString("province");
			String district=obj.getJSONObject("result").getJSONObject("addressComponent").getString("district");
			String street=obj.getJSONObject("result").getJSONObject("addressComponent").getString("street");
			map.put("detail", detail);
			map.put("province", province);
			//获取省份代码
			Integer provinceCode=getCodeByName(province,1000);
			map.put("provincecode", provinceCode.toString());
			map.put("city", city);
			//获取市代码
			Integer cityCode = getCodeByName(city,provinceCode);
			map.put("citycode", cityCode.toString());
			map.put("district", district);
			//获取区域代码
			Integer districtCode=getCodeByName(district,cityCode);
			map.put("districtcode",districtCode.toString());
			
			map.put("street", street);
//			System.out.println("省份："+province+"---城市："+city);
		} else {
//			 System.out.println("未找到相匹配的经纬度！");
		}
		return map;
	}
	
	public String loadJSON(String url) {
		StringBuilder json = new StringBuilder();
		try {
			URL oracle = new URL(url);
			URLConnection yc = oracle.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					yc.getInputStream()));
			String inputLine = null;
			while ((inputLine = in.readLine()) != null) {
				json.append(inputLine);
			}
			in.close();
		} catch (MalformedURLException e) {
		} catch (IOException e) {
		}
		return json.toString();
	}

}
