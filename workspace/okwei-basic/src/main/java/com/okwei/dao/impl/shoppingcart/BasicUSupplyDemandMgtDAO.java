package com.okwei.dao.impl.shoppingcart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.okwei.bean.domain.USupplyDemand;
import com.okwei.dao.impl.BaseDAO;
import com.okwei.dao.shoppingcart.IBasicUSupplyDemandMgtDAO;

@Repository
public class BasicUSupplyDemandMgtDAO extends BaseDAO implements IBasicUSupplyDemandMgtDAO {

	@Override
	public List<USupplyDemand> getUSupplyDemandList(List<Long> weiIdList) {
		// TODO Auto-generated method stub
		if(weiIdList == null || weiIdList.size() == 0)
		{
			return new ArrayList<USupplyDemand>();
		}
		String hql = "from USupplyDemand where weiId in (:weiIdList)";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("weiIdList", weiIdList);
		return super.findByMap(hql,map);
	}
}
