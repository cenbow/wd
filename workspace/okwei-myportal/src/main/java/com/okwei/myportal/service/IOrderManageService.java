package com.okwei.myportal.service;

import java.io.Serializable;

import com.okwei.bean.domain.TOrderBackTotal;
import com.okwei.bean.vo.order.ParamOrderSearch;
import com.okwei.common.Limit;
import com.okwei.common.PageResult;
import com.okwei.myportal.bean.vo.BuyOrderListVO;
import com.okwei.myportal.bean.vo.LogisticsVO;
import com.okwei.myportal.bean.vo.OrderCountVO;
import com.okwei.myportal.bean.vo.OrderDetailsVO;
import com.okwei.myportal.bean.vo.PreOrderListVO;
import com.okwei.myportal.bean.vo.RefundVO;
import com.okwei.myportal.bean.vo.ResultMsg;
import com.okwei.service.IBaseService;

/**
 * 买家购买订单，详情数据获取方法
 * 
 * @author Administrator
 *
 */
public interface IOrderManageService extends IBaseService {

    /**
     * 获取购买订单列表
     * 
     * @param weiid
     *            买家微店号   
     * @param limit
     *            分页
     * @param orderType
     *            订单类型
     * @param orderState
     *            订单状态
     * @return
     */
    public PageResult<BuyOrderListVO> getOrderList(Long weiid, Limit limit,ParamOrderSearch param);

    /**
     * 获取各种购买订单的数量
     * 
     * @param weiid
     *            微店号
     * @return
     */
    public OrderCountVO getOrderCount(Long weiid, int orderType);

    /**
     * 获取购买预订单列表
     * 
     * @param weiid
     *            买家微店号
     * @param limit
     *            分页
     * @param orderState
     *            订单状态
     * @return
     */
    public PageResult<PreOrderListVO> getPreOrderList(Long weiid, Limit limit, ParamOrderSearch param);

    /**
     * 获取订单详情
     * 
     * @param weiid
     *            买家微店号
     * @param supOrderID
     *            供应商订单号
     * @return
     */
    public OrderDetailsVO getOrderDetails(Long weiid, String supOrderID);

    /**
     * 买家取消订单（预订单）
     * 
     * @param weiid
     *            买家微店号
     * @param supOrderID
     *            订单号
     * @param state
     *            需要修改的订单枚举
     * @return
     */
    public String cancelOrder(Long weiid, String supOrderID);

    /**
     * 买家确认收货
     * 
     * @param weiid
     *            买家微店号
     * @param supOrderID
     *            供应商订单号
     * @return
     */
    public String confirmationReceipt(Long weiid, String supOrderID);

    /**
     * 买家删除订单
     * 
     * @param weiid
     * @param supOrderID
     * @return
     */
    public String deleteOrder(Long weiid, String supOrderID);

    /**
     * 获取物流信息by供应商订单号
     * 
     * @param supOrderID
     * @return
     */
    public LogisticsVO getLogistics(String supOrderID);

    /**
     * 获取退款订单详情
     * 
     * @param weiid
     *            买家微店号
     * @param backOrder
     *            退款订单ID
     * @return
     */
    public RefundVO getRefundInfo(Long weiid, Long backOrder);

    /**
     * 申请退款
     * 
     * @param weiid
     *            供应商订单号
     * @param supOrderID
     *            供应商订单号
     * @param proOrderID
     *            申请退款的产品订单号
     * @param tkType
     *            退款类型
     * @param tkReason
     *            退款原因
     * @param tkImage
     *            退款图片
     * @param tkMoney
     *            退款金额
     * @return
     */
    public BuyOrderListVO applyRefund(Long weiid, String supOrderID, String proOrderID, short tkType, String tkReason, String tkImage, double tkMoney);

    /**
     * 买家发货给供应商
     * 
     * @param weiid
     *            微店号
     * @param backOrder
     *            退款订单ID
     * @param transNo
     *            物流编号
     * @param transName
     *            物流公司名称
     * @return
     */
    public String buyersDelivery(long weiid, long backOrder, String transNo, String transName);

    /**
     * 取消退款申请
     * 
     * @param weiid
     *            买家微店号
     * @param backOrder
     *            退款ID
     * @return
     */
    public String cancelRefund(long weiid, long backOrder);

    /**
     * 申请微店网介入
     * @param flowid
     * @param weiid
     * @return
     */
    public String OkweiIntervention(long backOrder, long weiid);
    
    /**
     * 返回支付订单号，没有支付订单生成支付订单
     * @param orderNo
     * @return
     */
    public ResultMsg returnPayOrderID(String orderNo);

	long saveOrderBackTotal(TOrderBackTotal o);
	
	public void updateOrderBackTotal(TOrderBackTotal model);
	
}
