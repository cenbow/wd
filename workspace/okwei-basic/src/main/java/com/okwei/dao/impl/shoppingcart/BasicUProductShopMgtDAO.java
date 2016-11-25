package com.okwei.dao.impl.shoppingcart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.okwei.bean.domain.UProductShop;
import com.okwei.dao.impl.BaseDAO;
import com.okwei.dao.shoppingcart.IBasicUProductShopMgtDAO;
@Repository
public class BasicUProductShopMgtDAO extends BaseDAO implements IBasicUProductShopMgtDAO {

	@Override
	public UProductShop getUProductShop(long weiId) {
		// TODO Auto-generated method stub
		String hql = "from UProductShop where weiId = ?";
		return super.getUniqueResultByHql(hql, weiId);
	}
	/**
	 * UProductShopList
	 */
	@Override
	public List<UProductShop> getUProductShop(List<Long> weiIdList) {
		// TODO Auto-generated method stub
		if(weiIdList == null || weiIdList.size() == 0)
		{
			return new ArrayList<UProductShop>();
		}
		String hql = "from UProductShop where weiId in (:weiIdList)";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("weiIdList", weiIdList);
		return super.findByMap(hql,map);
	}
}
