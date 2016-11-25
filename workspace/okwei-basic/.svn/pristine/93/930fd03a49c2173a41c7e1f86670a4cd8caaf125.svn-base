package com.okwei.dao.impl.shoppingcart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.okwei.bean.domain.UDemandProduct;
import com.okwei.dao.impl.BaseDAO;
import com.okwei.dao.shoppingcart.IBasicUDemandProductMgtDAO;

@Repository
public class BasicUDemandProductMgtDAO extends BaseDAO implements IBasicUDemandProductMgtDAO {

	@Override
	public UDemandProduct getUDemandProduct(int demandId, long productId) {
		// TODO Auto-generated method stub
		String hql = "from UDemandProduct where demandId = ? and productId = ?";
		return super.getNotUniqueResultByHql(hql, demandId,productId);
	}
	@Override
	public List<UDemandProduct> getUDemandProductListByProductId(
			List<Long> productList) {
		// TODO Auto-generated method stub
		if(productList == null || productList.size() == 0)
		{
			return new ArrayList<UDemandProduct>();
		}
		String hql = "from UDemandProduct where productId in (:productList)";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("productList", productList);
		return super.findByMap(hql,map);
	}
	@Override
	public List<UDemandProduct> getUDemandProductListByProductIdAndDemandId(
			List<Long> productList,List<Integer> demandIdList) {
		// TODO Auto-generated method stub
		if(productList == null || demandIdList == null || productList.size() < 1 || demandIdList.size() < 1)
		{
			return new ArrayList<UDemandProduct>();
		}
		String hql = "from UDemandProduct where productId in (:productList) and demandId in(:demandIdList)";
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("productList", productList);
		map.put("demandIdList", demandIdList);
		return super.findByMap(hql,map);
	}
	@Override
	public UDemandProduct getUDemandProductByProductId(long productId) {
		// TODO Auto-generated method stub
		String hql = "from UDemandProduct where productId = ?";
		return super.getNotUniqueResultByHql(hql,productId);
	}
}
