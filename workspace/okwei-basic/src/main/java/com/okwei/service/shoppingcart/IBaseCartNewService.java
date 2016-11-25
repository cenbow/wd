package com.okwei.service.shoppingcart;

import com.okwei.bean.vo.ReturnModel;
import com.okwei.bean.vo.order.BAddressVO;

public interface IBaseCartNewService {

	/**
	 * 获取购物车列表
	 * zy(2016-7-13 )
	 * @param weiId
	 * @return
	 */
	public ReturnModel find_ShoppingCartList(long weiId);
	
	public ReturnModel exchangeProduct(Long weiid, String cartJson,BAddressVO address);
	
	public ReturnModel getShopCarDTO(long weiId,String json);
}
