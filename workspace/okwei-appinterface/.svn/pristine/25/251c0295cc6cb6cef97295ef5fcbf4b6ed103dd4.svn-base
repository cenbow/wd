package com.okwei.appinterface.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.okwei.appinterface.dao.IProductDao;
import com.okwei.bean.domain.PProducts;
import com.okwei.dao.impl.BaseDAO;

@Repository
public class ProductDao extends BaseDAO  implements IProductDao  {
	
	@Override
	public List<PProducts> find_ProductByProductIDS(List<Long> productIdS) {
		if (productIdS == null || productIdS.size() <= 0)
			return null;
		Map<String, Object> map = new HashMap<String, Object>();
		String hql = " from PProducts p where p.productId in (:proIds)";
		map.put("proIds", productIdS);
		return super.findByMap(hql, map);
	}
}
