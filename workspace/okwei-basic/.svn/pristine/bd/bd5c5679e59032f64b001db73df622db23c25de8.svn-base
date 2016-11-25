package com.okwei.dao.impl.shoppingcart;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.okwei.bean.domain.PProductSellKey;
import com.okwei.dao.impl.BaseDAO;
import com.okwei.dao.shoppingcart.IBasicPProductSellKeyMgtDAO;

@Repository
public class BasicPProductSellKeyMgtDAO extends BaseDAO implements
		IBasicPProductSellKeyMgtDAO {

	@Override
	public List<PProductSellKey> getPProductSellKeyByProductId(
			List<Long> productIdList) {
		// TODO Auto-generated method stub
		if (productIdList == null || productIdList.size() == 0) {
			return new ArrayList<PProductSellKey>();
		}
		String hql = "from PProductSellKey p where productId in(:productIdList)";// 查询商品款式
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("productIdList", productIdList);
		return super.findByMap(hql, map);
	}
	
	@Override
	public List<PProductSellKey> getPProductSellKeyByProductId(long productId) {
		// TODO Auto-generated method stub
		String hql = "from PProductSellKey p where productId = ?";// 查询商品款式
		return super.find(hql, productId);
	}
}
