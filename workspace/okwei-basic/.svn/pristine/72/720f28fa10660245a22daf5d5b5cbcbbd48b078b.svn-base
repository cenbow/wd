package com.okwei.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.okwei.bean.domain.PProductClass;
import com.okwei.bean.domain.TRegional;
import com.okwei.cache.RedisConstant;
import com.okwei.service.ISysInitService;
import com.okwei.util.RedisUtil;

@Service("sysInitService")
public class SysInitService extends BaseService implements ISysInitService {

	@Override
	public boolean addCacheSysInit() {
		try {
			// 产品分类
			List<PProductClass> catagoryList = this.getAll(PProductClass.class);
			RedisUtil.setObject(RedisConstant.BASEDATA_CATAGORY, catagoryList);

			// 地区
			List<TRegional> regionList = this.getAll(TRegional.class);
			RedisUtil.setObject(RedisConstant.BASEDATA_REGION, regionList);

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
