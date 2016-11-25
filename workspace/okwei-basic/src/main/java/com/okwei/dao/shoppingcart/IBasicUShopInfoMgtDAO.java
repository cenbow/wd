package com.okwei.dao.shoppingcart;

import java.util.List;

import com.okwei.bean.domain.UShopInfo;

public interface IBasicUShopInfoMgtDAO {
	/**
	 * 查找店铺名称
	 * @param weiIdList
	 * @return
	 */
	List<UShopInfo> getBasicUShopInfoList(List<Long> weiIdList);
}
