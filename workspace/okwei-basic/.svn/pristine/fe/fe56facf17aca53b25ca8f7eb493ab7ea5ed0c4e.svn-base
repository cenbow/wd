package com.okwei.dao.impl.shoppingcart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.okwei.bean.domain.PProducts;
import com.okwei.dao.impl.BaseDAO;
import com.okwei.dao.shoppingcart.IBasicPProductsMgtDAO;

@Repository
public class BasicPProductsMgtDAO extends BaseDAO implements IBasicPProductsMgtDAO {

	@Override
	public PProducts getPProducts(Long productId) {
		// TODO Auto-generated method stub
		return super.get(PProducts.class, productId);
	}
	
	@Override
	public List<PProducts> getPProductsList(List<Long> productList) {
		// TODO Auto-generated method stub
		if(productList == null || productList.size() == 0)
		{
			return new ArrayList<PProducts>();
		}
		String hql = "from PProducts p where p.productId in(:productList)";// 查询商品
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("productList", productList);
		return super.findByMap(hql,map);
	}
}
