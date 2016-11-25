package com.okwei.dao.shoppingcart;

import java.util.List;

import com.okwei.bean.domain.USupplyDemand;

public interface IBasicUSupplyDemandMgtDAO {
	/**
	 * 招商需求list
	 * @param weiIdList
	 * @return
	 */
	List<USupplyDemand> getUSupplyDemandList(List<Long> weiIdList); 
}
