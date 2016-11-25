package com.okwei.appinterface.service.order;

import com.okwei.appinterface.bean.vo.order.OrderServiceListVO;
import com.okwei.appinterface.bean.vo.order.UCustomerAddress;
import com.okwei.bean.domain.UCustomerAddr;
import com.okwei.bean.vo.ReturnModel;

public interface IOrderService {
	/**
	 * 修改收货地址
	 * @param modelJson     收货地址实体Json字符串
	 * @return
	 */
	public ReturnModel updateAddress(String modelJson,long weiId) ;
	/**
	 * 删除收货地址
	 * @param modelJson
	 * @param weiId
	 * @return
	 */
	public ReturnModel deleteAddress(String modelJson,long weiId);
	/**
	 * 新增收货地址
	 * @param modelJson
	 * @param weiId
	 * @return
	 */
	public ReturnModel insertAddress(String modelJson,long weiId);
	/**
	 * 修改收货地址
	 * 
	 */
	public void updateAddress(UCustomerAddress address);
	/**
	 * 根据Id获取收货地址
	 * 
	 */
	public UCustomerAddr getCustomerAddressById(int addressId, long weiId);
	
	/**
	 * 新增收货地址 ， 此方法被临时修改
	 * 
	 */
	public int insertAddress(UCustomerAddress address);
	/**
	 * 获取收货地址列表
	 * @param weiIdParam微店号参数
	 * @return ReturnModel
	 */
	public ReturnModel getCustomerAddressesList(Long weiId);
	/**
	 * 订单详情
	 * @param supplierOrderId 供应商订单号
	 * @param userType	1买家  2卖家
	 * @param weiId	登录weiId
	 * @return
	 */
	public ReturnModel getOrderDetails(String supplierOrderId,int userType,Long weiId);
	
	/**
	 * 订单列表
	 * @param order
	 * @return
	 */
	public ReturnModel findProductOrderModelList(OrderServiceListVO order);
	
	/**
	 * 代理商订单列表
	 * @param weiNo
	 * @param state
	 * @param pageindex
	 * @param pageSize
	 * @return
	 */
	public ReturnModel GetSellerProductOrderList(Long weiNo, Short state,int pageindex, int pageSize);
	
	/**
	 * 确认或取消铺货订单
	 * @param param json串
	 * @param weiId 登录人weiID
	 * @return
	 */
	public ReturnModel insertBookOrder(String param,long weiId);
} 
