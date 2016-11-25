package com.okwei.dao.impl.shoppingcart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.okwei.bean.domain.UBrandSupplyer;
import com.okwei.bean.vo.shoppingcart.ShoppingCar;
import com.okwei.dao.impl.BaseDAO;
import com.okwei.dao.shoppingcart.IBasicUBrandSupplyerMgtDAO;

@Repository
public class BasicUBrandSupplyerMgtDAO extends BaseDAO implements IBasicUBrandSupplyerMgtDAO {

	@Override
	public UBrandSupplyer getUBrandSupplyer(long weiId,short state) {
		// TODO Auto-generated method stub
		String hql = "from UBrandSupplyer where weiId = ? and state = ?";
		return super.getNotUniqueResultByHql(hql, weiId,state);
	}

	@Override
	public List<UBrandSupplyer> getUBrandSupplyerList(List<Long> weiIdList) {
		// TODO Auto-generated method stub
		if(weiIdList == null || weiIdList.size() == 0)
		{
			return new ArrayList<UBrandSupplyer>();
		}
		String hql = "from UBrandSupplyer where weiId in(:weiIdList)";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("weiIdList", weiIdList);
		return super.findByMap(hql,map);
	}


}
