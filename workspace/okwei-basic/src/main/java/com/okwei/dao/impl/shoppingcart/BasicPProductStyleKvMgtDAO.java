package com.okwei.dao.impl.shoppingcart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.okwei.bean.domain.PProductStyleKv;
import com.okwei.dao.impl.BaseDAO;
import com.okwei.dao.shoppingcart.IBasicPProductStyleKvMgtDAO;

@Repository
public class BasicPProductStyleKvMgtDAO extends BaseDAO implements
		IBasicPProductStyleKvMgtDAO {

	@Override
	public List<PProductStyleKv> getPProductStyleKvByProductIdAndStyleId(
			List<Long> styleIdList, List<Long> productIdList) {
		// TODO Auto-generated method stub
		if (styleIdList == null || styleIdList.size() == 0
				|| productIdList == null || productIdList.size() == 0) {
			return new ArrayList<PProductStyleKv>();
		}
		String hql = "from PProductStyleKv p where p.stylesId in(:styleIdList) and productId in(:productIdList)";// 查询商品款式
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("styleIdList", styleIdList);
		map.put("productIdList", productIdList);
		return super.findByMap(hql, map);
	}

	@Override
	public List<PProductStyleKv> getPProductStyleKvByProductIdAndStyleId(
			long styleId, long productId) {
		// TODO Auto-generated method stub
		String hql = "from PProductStyleKv p where p.stylesId = ? and productId = ?";// 查询商品款式
		return super.find(hql, styleId,productId);
	}
}
