package com.okwei.dao.shoppingcart;

import java.util.List;

import com.okwei.bean.domain.UProductShop;

public interface IBasicUProductShopMgtDAO {
	/**
	 * 查询落地店
	 * @param weiId
	 * @return
	 */
	UProductShop getUProductShop(long weiId);
	/**
	 * 落地店list
	 * @param weiIdList
	 * @return
	 */
	List<UProductShop> getUProductShop(List<Long> weiIdList);
}
