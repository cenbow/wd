package com.okwei.dao.impl.shoppingcart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.okwei.bean.domain.USupplyer;
import com.okwei.dao.impl.BaseDAO;
import com.okwei.dao.shoppingcart.IBasicUSupplyerMgtDAO;

@Repository
public class BasicUSupplyerMgtDAO extends BaseDAO implements IBasicUSupplyerMgtDAO {

	@Override
	public USupplyer getUSupplyer(long weiId) {
		// TODO Auto-generated method stub
		return super.get(USupplyer.class,weiId);
	}

	@Override
	public List<USupplyer> getUSupplyerList(List<Long> weiIdList) {
		// TODO Auto-generated method stub
		if(weiIdList == null || weiIdList.size() == 0)
		{
			return new ArrayList<USupplyer>();
		}
		String hql = "from USupplyer where weiId in (:weiIdList)";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("weiIdList", weiIdList);
		return super.findByMap(hql,map);
	}

}
