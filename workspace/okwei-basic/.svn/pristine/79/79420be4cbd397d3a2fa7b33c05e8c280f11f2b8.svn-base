package com.okwei.service.order;

import java.util.List;

import com.okwei.bean.domain.OPayOrder;
import com.okwei.bean.enums.PayTypeEnum;
import com.okwei.bean.vo.BResultMsg;
import com.okwei.bean.vo.order.PayOrderInfoVO;

public interface IBasicPayService {

	
	/**
	 * 支付成功处理
	 * @param payOrderId
	 * @param paytype
	 * @return
	 */
	public BResultMsg editOrderDataProcess(OPayOrder payOrder, PayTypeEnum paytype);
	/**
	 * 获取支付订单
	 * @param payOrderId
	 * @param needLastOne
	 * @return
	 */
	public OPayOrder getOPayOrder(String payOrderId, boolean needLastOne);
	/**
	 * 获取用户对于某个产品的身份标示 （ 落地店、代理商身份值）
	 * @param weiid 
	 * @param productId
	 * @return
	 */
	public int isShop(long weiid,long productId);
	/**
	 * 悬赏、平台服务费 合并支付（获取支付单号信息）
	 * @param weiid
	 * @param orderFrom枚举
	 * @param orderType(1悬赏订单，2服务费订单)
	 * @param  List<String>ids(如果悬赏订单：传channelId,如果服务费订单 传UPlatformServiceOrder表主键ID)
	 * @return 如果成功 msg:返回的订单编号
	 */
	public BResultMsg update_OrderPayInfo(long weiid,int orderFrom,int orderType, List<String>ids) throws Exception;
	/**
	 * 获取
	 * @param orderNo
	 * @return
	 */
	public double UP_getCoinPayAmount(String orderNo);
	/**
	 * 清除代金券
	 * @param orderNo
	 */
	public void UP_CoinPayAmountDel(String orderNo);
	
	/**
	 * 获取支付信息
	 * @param orderId 支付单号
	 * @param weiid 买家
	 * @param lastOne 是否获取最新的支付记录（true:获取最新支付单，false获取当前订单信息）
	 * @return
	 */
	public PayOrderInfoVO getPayOrderInfo(String orderId,long weiid,boolean lastOne);
	
	
}
