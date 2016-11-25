package com.okwei.dao.impl.shoppingcart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.okwei.bean.domain.PProductSellValue;
import com.okwei.dao.impl.BaseDAO;
import com.okwei.dao.shoppingcart.IBasicPProductSellValueMgtDAO;

@Repository
public class BasicPProductSellValueMgtDAO extends BaseDAO implements IBasicPProductSellValueMgtDAO {

	@Override
	public List<PProductSellValue> getPProductSellValueByProductIdAndAttributeId(
			List<Long> productIdList, List<Long> attributeIdList) {
		// TODO Auto-generated method stub
		if(productIdList == null || productIdList.size() == 0 || attributeIdList == null || attributeIdList.size() == 0)
		{
			return new ArrayList<PProductSellValue>();
		}
		String hql = "from PProductSellValue p where p.productId in(:productIdList) and attributeId in(:attributeIdList)";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("productIdList", productIdList);
		map.put("attributeIdList", attributeIdList);
		return super.findByMap(hql,map);
	}
	@Override
	public List<PProductSellValue> getPProductSellValueByProductIdAndAttributeId(
			long productId, List<Long> attributeIdList) {
		// TODO Auto-generated method stub
		if(attributeIdList == null || attributeIdList.size() < 1)
		{
			return new ArrayList<PProductSellValue>();
		}
		String hql = "from PProductSellValue p where p.productId =:productId and attributeId in(:attributeIdList)";
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("productId",productId);
		map.put("attributeIdList",attributeIdList);
		return super.findByMap(hql,map);
	}
}
