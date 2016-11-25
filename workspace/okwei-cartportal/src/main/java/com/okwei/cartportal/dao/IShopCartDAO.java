package com.okwei.cartportal.dao;

import com.okwei.bean.domain.PClassProducts;
import com.okwei.bean.domain.UPushMessage;
import com.okwei.dao.IBaseDAO;


public interface IShopCartDAO extends IBaseDAO{

	/*
	 * 是否能发布能销售的产品
	 */
	public boolean isSaleProduct(Long weiNo);
	
	public boolean insertSendPushMsg(UPushMessage msg);

	public PClassProducts getClassProducts(Long supplierWeiId, Long prodId);
}
