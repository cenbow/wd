package com.okwei.myportal.dao;

import java.io.Serializable;
import java.util.List;

import com.okwei.bean.domain.OBookAssist;
import com.okwei.bean.domain.OOrderFlow;
import com.okwei.bean.domain.OPayOrder;
import com.okwei.bean.domain.OProductOrder;
import com.okwei.bean.domain.OSupplyerOrder;
import com.okwei.bean.domain.TOrderBackTotal;
import com.okwei.bean.domain.TRefundImg;
import com.okwei.bean.domain.UWeiSeller;
import com.okwei.bean.enums.OrderStatusEnum;
import com.okwei.common.Limit;
import com.okwei.common.PageResult;
import com.okwei.dao.IBaseDAO;
import com.okwei.myportal.bean.vo.MsgResult;
import com.okwei.myportal.bean.vo.ParamOrderSearch;

public interface ISellerOrderManagerDAO extends IBaseDAO
{

    <T> T getEntity(Class<T> obj,Serializable id);

    /**
     * 获取供应商产品列表， 带分页、查询条件
     * 
     * @param param
     *            查询条件
     * @param supperWeiid
     *            供应商微店号
     * @param limit
     * @return
     */
    PageResult<OSupplyerOrder> getSupplyOrderlistResult(ParamOrderSearch param,Long supperWeiid,Limit limit);

    /**
     * 获取供应商 预定单 列表
     * 
     * @param param
     * @param supperWeiid
     * @param limit
     * @return
     */
    PageResult<OSupplyerOrder> getSupplyBookOrderlistResult(ParamOrderSearch param,Long supperWeiid,Limit limit);

    /**
     * 获取预定单辅助列表
     * 
     * @param supplyOrderIds
     * @return
     */
    public List<OBookAssist> getOBookAssistlistBySupplyOrderIds(String[] supplyOrderIds);

    /**
     * 通过
     * 
     * @param supplyOrderIds
     * @return
     */

    public int getCountOBookAssistlist(Long weiid,Short typestates);

    /**
     * 根据供应商订单号 获取 产品订单列表
     * 
     * @param supplyOrderIds
     * @return
     */
    public List<OProductOrder> getProductOrderListBySupplyOrderIds(String[] supplyOrderIds);

    /**
     * 根据供应商订单 获取支付订单列表（预订单 支付列表）
     * 
     * @param supplyOrderIds
     * @return
     */
    public List<OPayOrder> getOPayOrderlistBySupplyOrderIds(String[] supplyOrderIds);

    /**
     * 根据用户Weiids获取用户列表
     * 
     * @param weiids
     * @return
     */
    public List<UWeiSeller> getUsersByWeiids(Long[] weiids);

    /**
     * 获取订单列表
     * 
     * @param weiid
     * @param states
     * @return
     */
    public List<OSupplyerOrder> getSupplyOrderlist(Long weiid,Integer[] typestates);

    /**
     * 获取订单列表
     * 
     * @param weiid
     * @param states
     * @return
     */
    public List<Object[]> getCountSupplyOrderlist(Long weiid,Integer[] typestates);

    /**
     * 获取订单分类总数
     * 
     * @param weiid
     * @param typeStates
     * @return
     */
    public int getSupplyOrderCount(Long weiid,Integer[] typeStates);

    /**
     * 获取订单记录
     * 
     * @param supplyOrderId
     * @return
     */
    public List<OOrderFlow> getOrderFlows(String supplyOrderId);

    /**
     * 供应商 确认订单
     * 
     * @param supplyweiid
     * @param orderno
     * @param state
     * @return
     */
    public boolean UP_supplyOrder(Long supplyweiid,String orderno,OrderStatusEnum state);

    /**
     * 插入订单
     * 
     * @param supplyOrderid
     * @param buyerId
     * @param totalPrice
     * @param orderFrom
     * @param ordertype
     * @return
     */
    public MsgResult insertPayOrder(String supplyOrderid,long buyerId,double totalPrice,Short orderFrom,Short ordertype);

    /**
     * 插入订单跟进记录
     * 
     * @param supplyOrderid
     * @param weiid
     * @param content
     */
    public void insertOrderFlow(String supplyOrderid,long weiid,String content);

    /**
     * 获取预订单支付列表
     * 
     * @param supplyOrderid
     * @return
     */
    public List<OPayOrder> getOPayOrdersBySupplyOrderid(String supplyOrderid);

    /**
     * 获取退款订单列表
     * 
     * @param refundIds
     * @return
     */
    public List<TOrderBackTotal> getRefundOrders(Object[] refundIds);

    /**
     * 根据供应商订单获取退款订单
     * 
     * @param supplyOrderid
     * @return
     */
    public List<TOrderBackTotal> getTOrderBackTotals(String supplyOrderid);

    /**
     * 获取退款图片
     * 
     * @param refundid
     * @return
     */
    public List<TRefundImg> getRefundImgs(long refundid);

    /**
     * 根据退款id获取退款商品
     * 
     * @param refundid
     * @return
     */
    public List<OProductOrder> getProductsByRefundId(long refundid);

    /**
     * 跟新钱包
     * 
     * @param weino
     */
    public void updateWallet(long weino);
}
