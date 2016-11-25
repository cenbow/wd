package com.okwei.myportal.dao;

import java.util.List;

import com.okwei.bean.domain.OBookAssist;
import com.okwei.bean.domain.OOrderAddr;
import com.okwei.bean.domain.OOrderFlow;
import com.okwei.bean.domain.OPayOrder;
import com.okwei.bean.domain.OProductOrder;
import com.okwei.bean.domain.OSupplyerOrder;
import com.okwei.bean.domain.TOrderBackTotal;
import com.okwei.bean.domain.TRefundImg;
import com.okwei.bean.domain.USupplyer;
import com.okwei.bean.domain.UWeiSeller;
import com.okwei.bean.domain.UYunSupplier;
import com.okwei.bean.enums.OrderTypeEnum;
import com.okwei.common.Limit;
import com.okwei.common.PageResult;

public interface IOrderManageDAO {
    /**
     * 查询购买订单list
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
    public PageResult<OSupplyerOrder> getOrderList(Long weiid, Limit limit, int orderType, Short orderState);

    /**
     * 根据订单订单ID查询订单产品
     * 
     * @param orderid
     *            订单号
     * @return
     */
    public List<OProductOrder> getOrderProduct(String[] orderid);

    /**
     * 根据退款ID查询退款产品
     * 
     * @param backOrder
     * @return
     */
    public List<OProductOrder> getOrderProduct(Long backOrder);

    /**
     * 根据微店号获取供应商信息列表
     * 
     * @param supid
     *            供应商微店号
     * @return
     */
    public List<USupplyer> getSupList(Long[] supid);

    /**
     * 获取供应商基本信息实体
     * 
     * @param supid
     *            供应商微店号
     * @return
     */
    public USupplyer getSupplyer(Long supid);

    public TOrderBackTotal getTOrderBackTotal(Long orderbackid);

    /**
     * 获取供应商的基本信息
     * 
     * @param supid
     * @return
     */
    public UYunSupplier getYunSupplier(Long supid);

    /**
     * 获取买家的订单列表
     * 
     * @param weiid
     * @return
     */
    public List<OSupplyerOrder> getOrderList(Long weiid);

    /**
     * 获取预订单辅助表
     * 
     * @param supOrderID
     *            供应商订单号
     * @return
     */
    public List<OBookAssist> getBookAssistsList(String[] supOrderID);

    /**
     * 获取预订单实体
     * 
     * @param supOrderID
     * @return
     */
    public OBookAssist getOBookAssist(String supOrderID);

    /**
     * 获取订单实体
     * 
     * @param weiid
     *            微店号
     * @param supOrderID
     *            供应商订单号
     * @return
     */
    public OSupplyerOrder getsSupplyerOrder(Long weiid, String supOrderID);

    /**
     * 获取供应商订单
     * 
     * @param supOrderID
     * @return
     */
    public OSupplyerOrder getsSupplyerOrder(String supOrderID);

    /**
     * 获取收货地址实体
     * 
     * @param addID
     *            收货地址ID
     * @return
     */
    public OOrderAddr getOrderAddr(Long addID);

    /**
     * 获取支付订单
     * 
     * @param payOrderID
     * @return
     */
    public OPayOrder getPayOrder(String payOrderID);

    /**
     * 保存支付订单
     * 
     * @param entity
     * @return
     */
    public String savePayOrder(OPayOrder entity);

    /**
     * 获取支付订单（预订单使用）
     * 
     * @param supOrderID
     * @param typeEnum
     * @return
     */
    public OPayOrder getPayOrder(String supOrderID, OrderTypeEnum typeEnum);

    /**
     * 获取支付订单列表
     * 
     * @param supOrderID
     * @return
     */
    public List<OPayOrder> getPayOrderList(String[] supOrderID);

    /**
     * 获取微店信息列表
     * 
     * @param sweiid
     * @return
     */
    public List<UWeiSeller> getWeiSellerList(Long[] sweiid);

    /**
     * 根据订单号获取流水
     * 
     * @param supOrderID
     * @return
     */
    public List<OOrderFlow> getOrderFlowList(String supOrderID);

    /**
     * 获取退款订单
     * 
     * @param refundID
     * @return
     */
    public List<TOrderBackTotal> getRefundList(Long[] refundID);

    /**
     * 买家取消订单
     * 
     * @param weiid
     * @param supOrderID
     * @param state
     * @return
     */
    public int updateOrderState(Long weiid, String supOrderID);

    /**
     * 买家删除订单
     * 
     * @param weiid
     * @param supOrderID
     * @return
     */
    public int deleteOrder(Long weiid, String supOrderID);

    /**
     * 获取退款详情
     * 
     * @param weiid
     * @param backOrder
     * @return
     */
    public TOrderBackTotal getOrderBack(Long weiid, Long backOrder);

    /**
     * 获取退款图片
     * 
     * @param backOrder
     * @return
     */
    public List<TRefundImg> getRefundImg(Long backOrder);

    /**
     * 买家确认收货
     * 
     * @param weiid
     * @param supOrderID
     * @return
     */
    public int confirReceipt(Long weiid, String supOrderID);

    /**
     * 添加订单记录
     * 
     * @param weiid
     * @param record
     */
    public void addOrderRecord(Long weiid, String record, String supOrderID);

    /**
     * 添加退款申请返回退款订单ID
     * 
     * @param model
     * @return
     */
    public Long addTOrderBackTotal(TOrderBackTotal model);

    /**
     * 修改订单状态
     * 
     * @param supOrderID
     * @param state
     */
    public int updateSupOrderState(String supOrderID, Short state);

    /**
     * 绑定产品订单表的退款ID
     * 
     * @param proOrderID
     * @param backOrder
     */
    public int updateBackOrder(String[] proOrderID, Long backOrder);

    /**
     * 添加退款图片
     * 
     * @param backOrder
     * @param images
     * @return
     */
    public void addTkImages(TRefundImg model);

    /**
     * 修改退款订单的收货地址
     * 
     * @param weiid
     * @param backOrder
     * @param transNo
     * @param transName
     * @return
     */
    public int updateTrans(long weiid, long backOrder, String transNo, String transName);

    /**
     * 修改退款订单
     * 
     * @param model
     */
    public void updateBackOrder(TOrderBackTotal model);

    /**
     * 获取已经退款的金额
     * 
     * @param supOrderID
     * @return
     */
    public double getRefundedAmout(String supOrderID);

    /**
     * 根据订单号修改金额状态为已收货
     * 
     * @param orderNo
     */
    public void setFrozenOrderAmout(String supplierOrderID);
}
