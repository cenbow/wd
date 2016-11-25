package com.okwei.myportal.service;

import java.util.List;
import java.util.Map;

import com.okwei.bean.domain.OProductOrder;
import com.okwei.bean.vo.ReturnModel;
import com.okwei.common.Limit;
import com.okwei.common.PageResult; 
import com.okwei.myportal.bean.vo.MsgResult;
import com.okwei.myportal.bean.vo.ParamOrderSearch;
import com.okwei.myportal.bean.vo.ProductOrderVO;
import com.okwei.myportal.bean.vo.ProductPriceEditParam;
import com.okwei.myportal.bean.vo.RefundVO;
import com.okwei.myportal.bean.vo.SellerSupplyOrderVO;
import com.okwei.myportal.bean.vo.SupplyBookOrderParam;
import com.okwei.myportal.bean.vo.SupplyBookOrderStateCountVO;
import com.okwei.myportal.bean.vo.SupplyBookOrderVO;
import com.okwei.myportal.bean.vo.SupplyOrderCountSumVO;
import com.okwei.myportal.bean.vo.SupplyOrderDetailsVO;
import com.okwei.myportal.bean.vo.SupplyOrderStateCountVO;
import com.okwei.myportal.bean.vo.SupplyOrderVO;

public interface ISellerOrderManagerService
{

    /**
     * 获取零售订单列表（供应商 零售订单）
     * @param param
     * @param limit
     * @return
     */
    public PageResult<SupplyOrderVO> getOrderListPageResult(com.okwei.bean.vo.order.ParamOrderSearch param,Long supplyWeiid,Limit limit);
    /**
     * 获取供应商预定单列表（供应商 预定单 列表）
     * @param param
     * @param supplyWeiid
     * @param limit
     * @return
     */
    public PageResult<SupplyBookOrderVO> getBookOrderListPageResult(com.okwei.bean.vo.order.ParamOrderSearch param,Long supplyWeiid, Limit limit);
    
    /**
     * @param supplyOrderid
     * @param weiid
     * @param dcomanpyNo 快递公司编号
     * @param deliveryCompany 快递公司
     * @param deliveryOrderNo 快递单号
     * @return
     */
    public MsgResult delivery1(String supplyOrderid, long weiid, String dcomanpyNo, String deliveryCompany, String deliveryOrderNo);
    	   
    /**
     * 获取定数类型数量( 供应商  订单数量统计  )
     * @param supplyerWeiid 供应商weiid
     * @return
     */
    public SupplyOrderCountSumVO getSupplyOrderCountSumVO(Long supplyerWeiid);
    
    /**
     * 获取订单状态 数量（针对 零售、批发）
     * @param supplyerWeiid
     * @param itype(1:零售、批发，2：预定)
     * @return
     */
    public SupplyOrderStateCountVO getSupplyOrderStateCountVO(Long supplyerWeiid,int itype);
    /**
     * 获取订单状态 数量（针对 预定）
     * @param supplyerWeiid
     * @return
     */
    public SupplyBookOrderStateCountVO getSupplyBookOrderStateCountVO(Long supplyerWeiid);
    
    /**
     * 获取订单详情（供应商订单详情）
     * @param supplyOrderid
     * @return
     */
    public SupplyOrderDetailsVO getOrderDetails(String supplyOrderid);
    
    public SupplyOrderDetailsVO getOrderDetails(String supplyOrderid,Long weiid);
    /**
     * 修改订单产品单价（城主/代理商操作）
     * zy(2016-7-19)
     * @param weiid
     * @param supplyOrderid
     * @param prolist
     * @return
     */
    public ReturnModel editOrderProductPrice(Long weiid, String supplyOrderid, List<OProductOrder> prolist);
    
    /**
     * 确认、 拒绝 预订单 、
     * @param param
     * @return
     */
    public MsgResult insertBookOrder(SupplyBookOrderParam param);
    /**
     * 获取物流公司
     * @return
     */
    public List<Map<String, String>> getLogistics();
    
    /**
     * 发货（供应商发货）
     * @param supplyOrderid
     * @param supplyWeiid
     * @param dcomanpyNo
     * @param deliveryCompany
     * @param deliveryOrderNo
     * @return
     */
    public MsgResult sendGoods(String supplyOrderid,long supplyWeiid,String dcomanpyNo,String deliveryCompany,String deliveryOrderNo);
    
    /**
     * 供应商发货
     * @param supplyOrderid
     * @param weiid
     * @param dcomanpyNo
     * @param deliveryCompany
     * @param deliveryOrderNo
     * @return
     */
    public MsgResult delivery(String supplyOrderid,long weiid,String dcomanpyNo,String deliveryCompany,String deliveryOrderNo);
    /**
     * 修改价格
     * @param param
     * @return
     */
    public MsgResult editProductPrice(ProductPriceEditParam param);
    /**
     * 获取退款详情
     * @param refundId
     * @param weiid
     * @return
     */
    public RefundVO getRefundVO(long refundId,long weiid);
    
    /**
     * 退款（卖家同意、不同意）
     * @param supplyweiid
     * @param refundid
     * @param isAgree
     * @param content
     * @return
     */
    public MsgResult editRefundState(long supplyweiid,long refundid, boolean isAgree,String content);
    /**
     * 供应商删除订单
     * @param supplyweiid
     * @param supplyOrderid
     * @return
     */
    public MsgResult deleteSupplyOrder(long supplyweiid,String supplyOrderid);
    /**
     * 确认收货地址（退款）
     * @param refundid
     * @param weiid
     * @param addrid
     * @return
     */
     public MsgResult saveRefundOrderAddr(long refundid,long weiid,long addrid);
     /**
      * 卖家确认收货(退款)
      * @param refundid
      * @param weiid
      * @return
      */
     public MsgResult saveRefundRecieved(long refundid,long weiid);
     /**
      * 支付尾款确认
      * @param weiid
      * @param supplyOrderid
      * @return
      */
     public MsgResult bookOrderComplete(long weiid,String supplyOrderid);
    
}
