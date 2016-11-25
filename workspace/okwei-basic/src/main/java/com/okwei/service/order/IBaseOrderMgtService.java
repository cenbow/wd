package com.okwei.service.order;

import com.okwei.bean.vo.ReturnModel;

public interface IBaseOrderMgtService {

	/**
	 * 修改单价
	 * @param WeiID
	 * @param param
	 * @return
	 */
	public ReturnModel updatePostUnitPrice(long WeiID, String param);
	/**
	 * 修改总价
	 * @param WeiID
	 * @param param
	 * @return
	 */
	public ReturnModel updateModifyTotalPrice(long WeiID, String param);
	/**
	 * 修改产品单价（代理区订单）
	 * zy(2016-8-8)
	 * @param WeiID 
	 * @param param
	 * @return
	 */
	public ReturnModel updateOrderProductPrice(long WeiID, String param);
}
