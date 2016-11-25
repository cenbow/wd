package com.okwei.dao.impl.shoppingcart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.okwei.bean.domain.UShopInfo;
import com.okwei.dao.impl.BaseDAO;
import com.okwei.dao.shoppingcart.IBasicUShopInfoMgtDAO;

@Repository
public class BasicUShopInfoMgtDAO extends BaseDAO implements IBasicUShopInfoMgtDAO {
	
	@Override
	public List<UShopInfo> getBasicUShopInfoList(List<Long> weiIdList) {
		// TODO Auto-generated method stub
		if(weiIdList == null || weiIdList.size() == 0)
		{
			return new ArrayList<UShopInfo>();
		}
		String hql = "from UShopInfo where weiId in (:weiIdList)";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("weiIdList", weiIdList);
		return super.findByMap(hql,map);
	}
}
