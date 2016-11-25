package com.okwei.appinterface.service.order;

import java.util.List;

import com.okwei.appinterface.bean.vo.order.PayOrderReturn;
import com.okwei.appinterface.bean.vo.order.PayParamVO;
import com.okwei.appinterface.bean.vo.order.SettlementParam;
import com.okwei.bean.vo.ReturnModel;
import com.okwei.bean.vo.order.BAddressVO;
import com.okwei.bean.vo.order.BShoppingCartVO;

public interface IPayProcess {

	/**
	 * 钱包支付 
	 * @param param
	 * @param buyerweiid
	 * @return
	 */
	public ReturnModel updateWalletPay(PayParamVO param,long buyerweiid);
	
	public ReturnModel updateWalletPayTest(PayParamVO param,long weiid);
	/**
	 * 获取下单参数
	 * @param paramlist
	 * @param weiid
	 * @return
	 */
	public ReturnModel getSettlementData(List<SettlementParam> paramlist,long weiid);
	/**
	 * 获取 下单参数（重新选择收货地址时用）
	 * @param resultlist
	 * @param addressVO
	 * @param weiid
	 * @return
	 */
	public ReturnModel getSettlementDataSure(List<BShoppingCartVO> resultlist,BAddressVO addressVO,long weiid);
	/**
	 * O04获取订单支付信息[11-17]（APP代金券的使用判断）
	 * @param orderId
	 * @param weiid
	 * @return
	 */
	public ReturnModel getPayOrderMsg(String orderId,long weiid);
	/**
	 * 支付成功后获取支付订单信息（wap用）
	 * @param orderno
	 * @param 买家
	 * @return
	 */
	public ReturnModel getPayParamVO(String orderno,long weiid);
	/**
	 * 悬赏、抽点订单 （获取合并支付信息）
	 * @param channelIds
	 * @param orderType
	 * @param weiid
	 * @return
	 */
	public ReturnModel getOrderPayInfo(List<String> channelIds,int orderType, long weiid)throws Exception;
	
}
