package com.okwei.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.okwei.bean.domain.TRegional;
import com.okwei.bean.domain.UBatchVerifierRegion;
import com.okwei.cache.RedisConstant;
import com.okwei.service.IRegionService;
import com.okwei.util.RedisUtil;

@Service
public class RegionService extends BaseService implements IRegionService {

	@Override
	public List<TRegional> getProvinceList() {
		List<TRegional> regions = new ArrayList<TRegional>();
		List<TRegional> regionList = (List<TRegional>) this.getRegion();
		for (TRegional region : regionList) {
			if (region.getLevel() == 2) {
				regions.add(region);
			}
		}
		return regions;
	}

	@Override
	public List<TRegional> getRegionalsByParent(int parentCode) {
		List<TRegional> regions = new ArrayList<TRegional>();
		List<TRegional> regionList = (List<TRegional>) this.getRegion();
		for (TRegional region : regionList) {
			if (region.getParent() == parentCode) {
				regions.add(region);
			}
		}
		return regions;
	}

	@Override
	public String getNameByCode(Integer code) {
		if(code == null)
			return "";
		String name = "";
		List<TRegional> regionList = (List<TRegional>) this.getRegion();
		for (TRegional region : regionList) {
			if (region.getCode().intValue() == code.intValue()) {
				name = region.getName();
				break;
			}
		}
		return name;
	}

	@Override
	public int getCodeByName(String name, int parentCode) {
		// 转换数据库与百度接口来的数据统一
		switch (name) {
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
		default:
			break;
		}

		if (parentCode == 820000)
			name = "澳门";
		else if (810000 == parentCode)
			name = "香港";
		List<TRegional> regionList = (List<TRegional>) this.getRegion();
		int code = 0;
		for (TRegional tr : regionList) {
			if (tr.getParent().equals(parentCode) && tr.getName().contains(name)) {
				code = tr.getCode();
				break;
			}
		}
		return code;
	}

	@Override
	public Map<String, String> getAddressByLngAndLat(String lnglat) {
		Map<String, String> map = new HashMap<String, String>();
		String url = "http://api.map.baidu.com/geocoder/v2/?ak=1b0ee1b8dfb2df70f543d80e5d216a1a&location=" + lnglat + "&output=json&pois=1";
		String json = loadJSON(url);
		JSONObject obj = JSONObject.fromObject(json);
		if (obj.get("status").toString().equals("0")) {// 调用成功
			String detail = obj.getJSONObject("result").getString("formatted_address");
			String city = obj.getJSONObject("result").getJSONObject("addressComponent").getString("city");
			String province = obj.getJSONObject("result").getJSONObject("addressComponent").getString("province");
			String district = obj.getJSONObject("result").getJSONObject("addressComponent").getString("district");
			String street = obj.getJSONObject("result").getJSONObject("addressComponent").getString("street");
			map.put("detail", detail);
			map.put("province", province);
			// 获取省份代码
			Integer provinceCode = getCodeByName(province, 1000);
			map.put("provincecode", provinceCode.toString());
			map.put("city", city);
			// 获取市代码
			Integer cityCode = getCodeByName(city, provinceCode);
			map.put("citycode", cityCode.toString());
			map.put("district", district);
			// 获取区域代码
			Integer districtCode = getCodeByName(district, cityCode);
			map.put("districtcode", districtCode.toString());

			map.put("street", street);
			// System.out.println("省份："+province+"---城市："+city);
		} else {
			// System.out.println("未找到相匹配的经纬度！");
		}
		return map;
	}

	private Object getRegion() {
		List<TRegional> regionList = null;
		try {
			regionList = (List<TRegional>) RedisUtil.getObject(RedisConstant.BASEDATA_REGION);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (regionList == null || regionList.size() < 1) {
			List<TRegional> versionList = super.getAll(TRegional.class);
			RedisUtil.setObject(RedisConstant.BASEDATA_REGION, versionList);
		}
		return regionList;
	}

	private String loadJSON(String url) {
		StringBuilder json = new StringBuilder();
		try {
			URL oracle = new URL(url);
			URLConnection yc = oracle.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
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
