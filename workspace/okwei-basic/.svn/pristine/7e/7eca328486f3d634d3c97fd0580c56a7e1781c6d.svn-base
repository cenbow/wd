package com.okwei.service.order;

import com.okwei.bean.vo.ReturnModel;
import com.okwei.bean.vo.order.SupplyBookOrderParam;
import com.okwei.bean.vo.order.SupplyOrderDetailsVO;

public interface IBasicOrdersService {
	
	
	/**
	 * 供应商发货
	 * @param supplyOrderid	供应商订单号
	 * @param supplyWeiid 供应商weiId
	 * @param dcomanpyNo	快递公司（名称）拼音
	 * @param deliveryCompany	快递公司名称
	 * @param deliveryOrderNo	快递单号
	 * @return
	 */
	public ReturnModel sendGoods(String supplyOrderid, long supplyWeiid, String dcomanpyNo, String deliveryCompany, String deliveryOrderNo);
	/**
     * 买家发货给供应商 
     * @param weiid   微店号
     * @param backOrder   退款订单ID
     * @param transNo   物流单号
     * @param transName 物流公司名称
     * @return
     */
    public ReturnModel buyersDelivery(long weiid, long backOrder, String transNo, String transName);
	/**
     * 确认或取消铺货单（param.isSured()） （PC 公共  APP）
     * @param param
     * @return
     */
    public ReturnModel insertBookOrder(SupplyBookOrderParam param) ;
    
	/**
	 * 查询订单详情（PC 公共  APP）注：app铺货单详情 独立出来了
	 * @param supplyOrderid 供应商订单号
	 * @param userType	1买家  2卖家
	 * @return
	 */
	public SupplyOrderDetailsVO getOrderDetails(String supplyOrderid,int userType);

	/**
	 * 买家确认收货（PC 公共  APP）
	 * @param weiid
	 * @param supOrderID 供应商订单号
	 * @return
	 */
	public ReturnModel updateConfirmationReceipt(Long weiid, String supOrderID);

	/**
	 * 卖家确认收货（PC 公共  APP）
	 * @param weiid
	 * @param backOrder 退款订单号
	 * @return
	 */
	public ReturnModel updateConfirmcargo(Long weiid, String backOrder);
 

	/**
	 * 获取订单返回信息
	 * @param orderno
	 * @param weiid
	 * @return
	 */
	public ReturnModel getPayBackVO(String orderno);
}
