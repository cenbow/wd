package com.okwei.dao.impl.shoppingcart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.okwei.bean.domain.UBatchSupplyer;
import com.okwei.dao.impl.BaseDAO;
import com.okwei.dao.shoppingcart.IBasicUBatchSupplyerMgtDAO;

@Repository
public class BasicUBatchSupplyerMgtDAO extends BaseDAO implements IBasicUBatchSupplyerMgtDAO {

	@Override
	public List<UBatchSupplyer> getBasicUBatchSupplyerList(
			List<Long> weiIdList, short status) {
		// TODO Auto-generated method stub
		if(weiIdList == null || weiIdList.size() == 0)
		{
			return new ArrayList<UBatchSupplyer>();
		}
		String hql = "from UBatchSupplyer where weiId in (:weiIdList) and status=:status ";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("weiIdList", weiIdList);
		map.put("status", status);
		return super.findByMap(hql,map);
	}
}
