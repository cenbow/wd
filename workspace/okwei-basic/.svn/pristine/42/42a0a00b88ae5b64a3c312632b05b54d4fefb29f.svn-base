package com.okwei.dao.impl.shoppingcart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.okwei.bean.domain.PShevleBatchPrice;
import com.okwei.dao.impl.BaseDAO;
import com.okwei.dao.shoppingcart.IBasicPShevleBatchPriceMgtDAO;

@Repository
public class BasicPShevleBatchPriceMgtDAO extends BaseDAO implements IBasicPShevleBatchPriceMgtDAO {

	@Override
	public List<PShevleBatchPrice> getPShevleBatchPriceById(long id) {
		// TODO Auto-generated method stub
		String hql = " from PShevleBatchPrice p where p.id=?  order by p.count";
		return super.find(hql,id);

	}

	@Override
	public List<PShevleBatchPrice> getPShevleBatchPrice(List<Long> idList) {
		// TODO Auto-generated method stub
		if(idList == null || idList.size() == 0)
		{
			return new ArrayList<PShevleBatchPrice>();
		}
		String hql = "from PShevleBatchPrice where id in (:idList)";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("idList", idList);
		return super.findByMap(hql,map);
	}

}
