package com.okwei.dao.order;

import java.util.List;

import com.okwei.bean.domain.OBookAssist;
import com.okwei.bean.domain.OOrderFlow;
import com.okwei.bean.domain.OPayOrder;
import com.okwei.bean.domain.OPayOrderLog;
import com.okwei.bean.domain.OProductOrder;
import com.okwei.bean.domain.OSupplyerOrder;
import com.okwei.bean.domain.TOrderBackTotal;
import com.okwei.bean.domain.UCustomerAddr;
import com.okwei.bean.domain.UPlatformServiceOrder;
import com.okwei.bean.domain.UPushMessage;
import com.okwei.bean.domain.URepayLog;
import com.okwei.bean.domain.USupplyChannel;
import com.okwei.bean.domain.UVerifier;
import com.okwei.bean.enums.OrderStatusEnum;
import com.okwei.bean.vo.ReturnModel;
import com.okwei.bean.vo.order.ParamOrderSearch;
import com.okwei.bean.vo.order.SupplyBookOrderParam;
import com.okwei.common.Limit;
import com.okwei.common.PageResult;
import com.okwei.dao.IBaseDAO;

/**
 * @author 付豪
 */
public interface IBasicOrdersDao extends IBaseDAO {
	/**
	 * 查询认证员信息列表
	 * @param verifierIds
	 * @return
	 */
	public List<UVerifier> find_UVerifierList(List<Long> verifierIds);
	/**
	 * 更新订单状态、价格、运费、订单产品明细价格、订单日志
	 * @param model
	 * @param totalProductPrice 订单商品总价（默认0.0）
	 * @param supplyerId
	 * @return 返回订单总价（包含邮费）
	 */
	public Double updateSupplyerOrderStateAndPrice(SupplyBookOrderParam model,Double totalProductPrice);
	/**
	 * 平台服务费列表
	 * @param weiId
	 * @param limit
	 * @return
	 */
	public PageResult<UPlatformServiceOrder> findServiceFeeList(Long weiId,String yearTimeStr, String monthTimeStr,String isPayReward, Limit limit);
	/**
	 * 悬赏列表
	 * 是否支付悬赏   IsPayReward 枚举
	 * @param param
	 * @param limit
	 * @return
	 */
	public PageResult<USupplyChannel> findPayRewards(Long weiId,String yearTimeStr, String monthTimeStr,Short status, Limit limit);
	/**
	 * 返现订单列表
	 * @param weiId
	 * @param limit
	 * @return
	 */
	public PageResult<URepayLog> findTradingRebates(Long weiId, Limit limit);
	/**
	 * 根据微店号获取收货地址List
	 * 
	 */
	public List<UCustomerAddr> getCustomerAddressByWeiID(long weiID);
	/**
	 * 返现订单列表
	 * @param param
	 * @param supperWeiid
	 * @param limit
	 * @return
	 */
	public PageResult<OSupplyerOrder> find_TradingRebatesOrder(ParamOrderSearch param, Limit limit);
	/**
	 * 获取用户身份
	 *   购买者身份，1-微店主  2-落地店 3-代理商  4-供应商
	 * @param weiId	  	微店号
	 * @param productId 	商品Id
	 * @return
	 */
	public String getIdentity(Long weiId ,Long productId);
	/**
	 * 去处订单锁
	 * @param payOrderId
	 */
	public void removeOrderlock(String payOrderId);
	
	/**
	 * 插入订单跟进记录
	 * @param supplyOrderid 供应商订单号
	 * @param weiid
	 * @param content 
	 */
	public void insertOrderFlow(String supplyOrderid, long weiid, String content);
	/**
     * 新增支付订单
     * @param supplyOrderid	供应商订单号
     * @param buyerId 	买家WeiId
     * @param totalPrice
     * @param orderFrom 订单来源
     * @param ordertype 支付类型
     * @return
     */
	public ReturnModel insertPayOrder(String supplyOrderid, long buyerId, double totalPrice, Short orderFrom, Short ordertype);
	/**
	 * 供应商 确认铺货单订单 
	 * @param supplyweiid  供应商weiID
	 * @param supplyOrderId 供应商订单号
	 * @param state		订单状态枚举
	 * @return
	 */
	public boolean UP_supplyOrder(Long supplyweiid, String supplyOrderId, OrderStatusEnum state);
	/**
     * 添加订单记录
     * @param weiid
     * @param record
     */
    public void addOrderRecord(Long weiid, String record, String supOrderID);
	
	/**
     * 获取订单实体
     * @param supOrderID  供应商订单号
     * @param userType 1买家 2卖家
     * @return
     */
    public OSupplyerOrder getSupplyerOrder(String supOrderID,int userType);
	
    /**
     * 根据订单号修改金额状态为已收货
     * @param orderNo
     */
    public void updateFrozenOrderAmout(String supplierOrderID);
	/**
     * 买家确认收货
     * @param weiid
     * @param supOrderID 供应商订单号
     * @return
     */
    public int updateConfirReceipt(Long weiid, String supOrderID);
	
	 /**
	  * 获取支付订单信息
	 * @param supplyOrderIds 供应商订单号 
	 * @return
	 */
	public List<OPayOrder> getOPayOrderlistBySupplyOrderIds(String[] supplyOrderIds);
    /**
     * 获取退款订单列表 
     * @param refundIds 退款订单号  type:数组
     * @return
     */
    public List<TOrderBackTotal> getRefundOrders(Object[] refundIds);
	/**
     * 获取订单记录 
     *
     * */
	public List<OOrderFlow> getOrderFlows(String supplyOrderId);
	 /**
     * 根据供应商订单号 获取 产品订单列表
     * @param supplyOrderIds
     * @param weiId 
     * @param userType 1 买家  2 卖家
     * @return
     */
    public List<OProductOrder> getProductOrderListBySupplyOrderIds(String[] supplyOrderIds,Long weiId,int userType);

    /**
     * 获取订单产品列表
     * @param supplyOrderids 供应商订单
     * @return
     */
    public List<OProductOrder> find_ProductOrderBySupOrderIds(String[] supplyOrderids);
    
    /**
     * 根据供应商订单获取 订单产品列表（仅限购物订单）
     * @param supplyOrderId
     * @return
     */
    public List<OProductOrder> find_ProductOrderBySupplyOrderId(String supplyOrderId);
	/**
	 * 根据 payOrderId获取 供应商订单
	 * @param payId
	 * @return
	 */
    public List<OSupplyerOrder> find_SupplyerOrderByOrderID(String payId);
    /**
     * 根据支付记录拿到最新一条支付快照
     * @param payorderId
     * @return
     */
    public OPayOrderLog get_OPayOrderLog(String payorderId);
    /**
     * 获取当前支付快照
     * @param payorderId
     * @return
     */
    public OPayOrderLog getOPayOrderLog(String payorderId);
    /**
     * 获取最新支付记录
     * @param payorderId
     * @return
     */
    public OPayOrder get_OPayOrderLastOne(String payorderId) ;
    /**
	 * 获取供应商 预定单 列表
	 * @param param
	 * @param supperWeiid
	 * @param limit
	 * @return
	 */
	public PageResult<OSupplyerOrder> getSupplyBookOrderlistResult(ParamOrderSearch param, Long supperWeiid, Limit limit);
	
	/**
	 * 买家订单列表
	 * @param param
	 * @param supperWeiid
	 * @param limit
	 * @return
	 */
	public PageResult<OSupplyerOrder> find_BuyerOSupplyerOrder(ParamOrderSearch param, Long supperWeiid, Limit limit) ;
	
	/**
	 * 卖家订单列表
	 * @param param
	 * @param supperWeiid
	 * @param limit
	 * @return
	 */
	public PageResult<OSupplyerOrder> find_SellerOSupplyerOrder(ParamOrderSearch param, Long supperWeiid, Limit limit);
	/**
	 * 代理商销售订单（分销区订单管理用）
	 * zy 
	 * PS:代理商、城主、副城主 可见
	 * @param shopWeiid
	 * @param status （未支付状态）
	 * @param limit
	 * @return
	 */
	public PageResult<OSupplyerOrder> find_OSupplyerOrderByAgent(Long shopWeiid,ParamOrderSearch param, Limit limit);

	/**
	 * 获取预定单辅助列表
	 * @param supplyOrderIds
	 * @return
	 */
	public List<OBookAssist> getOBookAssistlistBySupplyOrderIds(String[] supplyOrderIds);
	
	/**
     * 修改退款订单的收货地址
     * @param weiid
     * @param backOrder
     * @param transNo
     * @param transName
     * @return
     */
    public int updateTrans(long weiid, long backOrder, String transNo, String transName);

    /**
     * 消息推送
     * @param msg
     * @return
     */
    public boolean insertSendPushMsg(UPushMessage msg);
    /**
     * 获取用户手机号
     * @param weiid
     * @return
     */
    public String getUserMobile(Long weiid);

}
