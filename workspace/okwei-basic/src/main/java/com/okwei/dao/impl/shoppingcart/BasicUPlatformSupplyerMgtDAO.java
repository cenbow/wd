package com.okwei.dao.impl.shoppingcart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.okwei.bean.domain.UPlatformSupplyer;
import com.okwei.dao.impl.BaseDAO;
import com.okwei.dao.shoppingcart.IBasicUPlatformSupplyerMgtDAO;

@Repository
public class BasicUPlatformSupplyerMgtDAO extends BaseDAO implements IBasicUPlatformSupplyerMgtDAO {

	@Override
	public List<UPlatformSupplyer> getUPlatformSupplyerList(List<Long> weiIdList) {
		// TODO Auto-generated method stub
		if(weiIdList == null || weiIdList.size() == 0)
		{
			return new ArrayList<UPlatformSupplyer>();
		}
		String hql = "from UPlatformSupplyer where weiId in (:weiIdList)";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("weiIdList", weiIdList);
		return super.findByMap(hql,map);
	}
	@Override
	public UPlatformSupplyer getUPlatformSupplyer(long weiId,short state) {
		// TODO Auto-generated method stub
		String hql = "from UPlatformSupplyer where weiId = ? and state = ?";
		return super.getNotUniqueResultByHql(hql, weiId,state);
	}
}
