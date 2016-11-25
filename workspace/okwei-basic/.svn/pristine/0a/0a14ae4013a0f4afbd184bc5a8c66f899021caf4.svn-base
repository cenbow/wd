package com.okwei.dao.impl.shoppingcart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.okwei.bean.domain.UWeiSeller;
import com.okwei.dao.impl.BaseDAO;
import com.okwei.dao.shoppingcart.IBasicUWeiSellerMgtDAO;

@Repository
public class BasicUWeiSellerMgtDAO extends BaseDAO implements IBasicUWeiSellerMgtDAO {

	@Override
	public List<UWeiSeller> getUWeiSellerList(List<Long> weiIdList) {
		// TODO Auto-generated method stub
		if(weiIdList == null || weiIdList.size() == 0)
		{
			return new ArrayList<UWeiSeller>();
		}
		String hql = "from UWeiSeller where weiId in (:weiIdList)";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("weiIdList", weiIdList);
		return super.findByMap(hql,map);
	}
} 
