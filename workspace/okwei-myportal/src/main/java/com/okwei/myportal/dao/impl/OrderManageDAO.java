package com.okwei.myportal.dao.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.okwei.bean.domain.AActProducts;
import com.okwei.bean.domain.AActProductsShowTime;
import com.okwei.bean.domain.AActShowProducts;
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
import com.okwei.bean.enums.ActProductVerState;
import com.okwei.bean.enums.OrderStatusEnum;
import com.okwei.bean.enums.OrderTypeEnum;
import com.okwei.bean.enums.TailPayTypeEnum;
import com.okwei.bean.enums.UserAmountStatus;
import com.okwei.bean.enums.UserAmountType;
import com.okwei.bean.vo.LimitCountVO;
import com.okwei.common.Limit;
import com.okwei.common.PageResult;
import com.okwei.dao.impl.BaseDAO;
import com.okwei.myportal.bean.enums.ReFundStatusEnum;
import com.okwei.myportal.dao.IOrderManageDAO;
import com.okwei.util.ParseHelper;
import com.okwei.util.RedisUtil;

@Repository
public class OrderManageDAO extends BaseDAO implements IOrderManageDAO {

    @Override
    public PageResult<OSupplyerOrder> getOrderList(Long weiid, Limit limit, int orderType, Short orderState) {
	if (orderType == 3) {
	    String hql = "";
	    // 预订单
	    if (orderState == 0 || orderState == 1 || orderState == 2) {
		hql = "select a from OSupplyerOrder a,OBookAssist b where a.supplierOrderId=b.supplierOrderId and a.buyerID=:weiid and a.orderType=:typearr and a.buyerDel=1 ";
	    } else {
		hql = "from OSupplyerOrder a where a.buyerID=:weiid and a.orderType=:typearr and a.buyerDel=1 ";
	    }
	    Map<String, Object> params = new HashMap<String, Object>();
	    params.put("weiid", weiid);
	    params.put("typearr", Integer.parseInt(OrderTypeEnum.BookOrder.toString()));
	    switch (orderState) {
		case 0:
		    // 待付款
		    hql += " and (a.state=:state1 or (a.state=:state2 and b.tailPayType=:tailpay1) or (a.state=:state3 and b.tailPayType=:tailpay2)) ";
		    params.put("state1", Short.parseShort(OrderStatusEnum.Sured.toString()));
		    params.put("state2", Short.parseShort(OrderStatusEnum.Accepted.toString()));
		    params.put("state3", Short.parseShort(OrderStatusEnum.PayedDeposit.toString()));
		    params.put("tailpay1", Short.parseShort(TailPayTypeEnum.afterdelivery.toString()));
		    params.put("tailpay2", Short.parseShort(TailPayTypeEnum.predelivery.toString()));
		    break;
		case 1:
		    // 待发货
		    hql += " and (a.state=:state1 or (a.state=:state2 and b.tailPayType=:tailpay1)) ";
		    params.put("state1", Short.parseShort(OrderStatusEnum.Payed.toString()));
		    params.put("state2", Short.parseShort(OrderStatusEnum.PayedDeposit.toString()));
		    params.put("tailpay1", Short.parseShort(TailPayTypeEnum.afterdelivery.toString()));
		    break;
		case 2:
		    // 待收货
		    hql += " and a.state=:state1 ";
		    params.put("state1", Short.parseShort(OrderStatusEnum.Deliveried.toString()));
		    break;
	    }
	    if (orderState == 8 || orderState == 4) {
		hql += " and state=:state";
		params.put("state", orderState);
	    }
	    // 购买订单默认按下单时间排序
	    hql += " order by a.orderTime desc";
	    return super.findPageResultByMap(hql, limit, params);

	} else {
	    // 零售 批发
	    String hql = "from OSupplyerOrder where buyerID=:weiid and orderType in(:typearr) and buyerDel=1";
	    Map<String, Object> params = new HashMap<String, Object>();
	    params.put("weiid", weiid);
	    // 查询条件
	    // 订单类型 1 2批发订单
	    Integer[] typeArr = null;
	    switch (orderType) {
		case 1:
		    typeArr = new Integer[] { Integer.parseInt(OrderTypeEnum.Pt.toString()), Integer.parseInt(OrderTypeEnum.BatchOrder.toString()) };
		    break;
		case 2:
		    typeArr = new Integer[] { Integer.parseInt(OrderTypeEnum.BatchWholesale.toString()) };
		    break;
		default:
		    typeArr = new Integer[] { Integer.parseInt(OrderTypeEnum.Pt.toString()), Integer.parseInt(OrderTypeEnum.BatchOrder.toString()) };
		    break;
	    }
	    params.put("typearr", typeArr);
	    // 订单状态 -1 所有
	    if (orderState != -1) {
		hql += " and state=:state";
		params.put("state", orderState);
	    }
	    // 购买订单默认按下单时间排序
	    hql += " order by orderTime desc";
	    return super.findPageResultByMap(hql, limit, params);
	}
    }

    @Override
    public List<OProductOrder> getOrderProduct(String[] orderid) {
	String hql = "from OProductOrder where supplierOrderId in(:orderid)";
	Map<String, Object> params = new HashMap<String, Object>();
	params.put("orderid", orderid);
	return super.findByMap(hql, params);
    }

    @Override
    public List<USupplyer> getSupList(Long[] supid) {
	String hql = "from USupplyer where weiId in(:supid)";
	Map<String, Object> params = new HashMap<String, Object>();
	params.put("supid", supid);
	return super.findByMap(hql, params);
    }

    @Override
    public List<OSupplyerOrder> getOrderList(Long weiid) {
	String hql = "from OSupplyerOrder where buyerID=:weiid and orderType in(:ordertyle) and buyerDel=1";
	Map<String, Object> params = new HashMap<String, Object>();
	params.put("weiid", weiid);
	// 订单类型 零售 批发 预订
	Integer[] type = new Integer[] { Integer.parseInt(OrderTypeEnum.Pt.toString()), Integer.parseInt(OrderTypeEnum.BatchOrder.toString()), Integer.parseInt(OrderTypeEnum.BatchWholesale.toString()), Integer.parseInt(OrderTypeEnum.BookOrder.toString()) };
	params.put("ordertyle", type);
	return super.findByMap(hql, params);
    }

    @Override
    public List<OBookAssist> getBookAssistsList(String[] supOrderID) {
	String hql = "from OBookAssist where supplierOrderId in(:suporderid)";
	Map<String, Object> params = new HashMap<String, Object>();
	params.put("suporderid", supOrderID);
	return super.findByMap(hql, params);
    }

    @Override
    public OSupplyerOrder getsSupplyerOrder(Long weiid, String supOrderID) {
	String hql = "from OSupplyerOrder where buyerID=? and supplierOrderId=?";
	Object[] params = new Object[2];
	params[0] = weiid;
	params[1] = supOrderID;
	return super.getUniqueResultByHql(hql, params);
    }

    @Override
    public OOrderAddr getOrderAddr(Long addID) {
	return super.get(OOrderAddr.class, addID);
    }

    @Override
    public USupplyer getSupplyer(Long supid) {
	return super.get(USupplyer.class, supid);
    }

    @Override
    public UYunSupplier getYunSupplier(Long supid) {
	return super.get(UYunSupplier.class, supid);
    }

    @Override
    public OPayOrder getPayOrder(String payOrderID) {
	String hql = "from OPayOrder where payOrderId=?";
	Object[] params = new Object[1];
	params[0] = payOrderID;
	return super.getUniqueResultByHql(hql, params);
    }

    @Override
    public List<UWeiSeller> getWeiSellerList(Long[] sweiid) {
	String hql = "from UWeiSeller where weiId in(:weiid)";
	Map<String, Object> params = new HashMap<String, Object>();
	params.put("weiid", sweiid);
	return super.findByMap(hql, params);
    }

    @Override
    public List<OOrderFlow> getOrderFlowList(String supOrderID) {
	String hql = "from OOrderFlow where supplierOrderId=? order by operateTime asc";
	Object[] params = new Object[1];
	params[0] = supOrderID;
	return super.find(hql, params);
    }

    @Override
    public OBookAssist getOBookAssist(String supOrderID) {
	return super.get(OBookAssist.class, supOrderID);
    }

    @Override
    public List<TOrderBackTotal> getRefundList(Long[] refundID) {
	String hql = "from TOrderBackTotal where backOrder in(:refundid)";
	Map<String, Object> params = new HashMap<String, Object>();
	params.put("refundid", refundID);
	return super.findByMap(hql, params);
    }

    @Override
    public List<OPayOrder> getPayOrderList(String[] supOrderID) {
	String hql = "from OPayOrder where supplierOrder in(:supOrderID)";
	Map<String, Object> params = new HashMap<String, Object>();
	params.put("supOrderID", supOrderID);
	return super.findByMap(hql, params);
    }

    @Override
    public OPayOrder getPayOrder(String supOrderID, OrderTypeEnum typeEnum) {
	String hql = "from OPayOrder where supplierOrder=? and typeState=?";
	Object[] params = new Object[2];
	params[0] = supOrderID;
	params[1] = Short.parseShort(typeEnum.toString());
	return super.getUniqueResultByHql(hql, params);
    }

    @Override
    public int updateOrderState(Long weiid, String supOrderID) {

	//如果是活动订单，则还原库存
	String hql=" from OProductOrder p where p.isActivity='1' and p.supplierOrderId = ?";
	Object[] params = new Object[1];
	params[0]=supOrderID;
	List<OProductOrder> productList=super.find(hql, params);
	if(productList!=null&&productList.size()>0){
		for (OProductOrder oproduct : productList) {
			String hqlString=" from AActProductsShowTime a where  a.productId=:productId and  a.endTime>=:endTime and a.beginTime<=:beginTime ";
			HashMap<String, Object> map  = new HashMap<String, Object>();
			//map.put("actState",Short.parseShort(ActProductVerState.Ok.toString()) );
			map.put("productId", oproduct.getProductId());
			//需要处理下单支付超时10分钟，且活动已结束的情况，所以结束时间延长10分钟
			Calendar calendar2 =Calendar.getInstance();
			calendar2.setTime(new Date());
			calendar2.add(Calendar.MINUTE, -1*10);

			map.put("endTime", calendar2.getTime());
			map.put("beginTime", new Date());
			
			List<AActProductsShowTime> list=super.findByMap(hqlString, map);
			if(list!=null&&list.size()>0){
				AActProductsShowTime  actPro=list.get(0);
				AActShowProducts actShowProduct=super.get(AActShowProducts.class, actPro.getProActId());
				actShowProduct.setStockCount(actShowProduct.getStockCount()+oproduct.getCount());
				super.update(actShowProduct);
				
				String keyName="BuyLimitCount_"+oproduct.getBuyerId()+"_"+actPro.getActPid()+"_"+actPro.getProductId();
				Integer limitCountVo=(Integer) RedisUtil.getObject(keyName);
			
				if(limitCountVo!=null){
					Integer number=limitCountVo-oproduct.getCount();
					RedisUtil.setObject(keyName, number, 21600);
				}
			}
		}
	}
	
	//修改订单状态
	String sql = "update OSupplyerOrder set state=? where buyerID=? and supplierOrderId=?";
	params = new Object[3];
	params[0] = Short.parseShort(OrderStatusEnum.Canceled.toString());
	params[1] = weiid;
	params[2] = supOrderID;
	return super.update(sql, params);
    }

    @Override
    public int deleteOrder(Long weiid, String supOrderID) {
	String sql = "update OSupplyerOrder set buyerDel=2 where buyerID=? and supplierOrderId=?";
	Object[] params = new Object[2];
	params[0] = weiid;
	params[1] = supOrderID;
	return super.update(sql, params);
    }

    @Override
    public TOrderBackTotal getOrderBack(Long weiid, Long backOrder) {
	String hql = "from TOrderBackTotal where buyerid=? and backOrder=?";
	Object[] params = new Object[2];
	params[0] = weiid;
	params[1] = backOrder;
	return super.getUniqueResultByHql(hql, params);
    }

    @Override
    public List<TRefundImg> getRefundImg(Long backOrder) {
	String hql = "from TRefundImg where backOrder=?";
	Object[] params = new Object[1];
	params[0] = backOrder;
	return super.find(hql, params);
    }

    @Override
    public List<OProductOrder> getOrderProduct(Long backOrder) {
	String hql = "from OProductOrder where backorder=?";
	Object[] params = new Object[1];
	params[0] = backOrder;
	return super.find(hql, params);
    }

    @Override
    public int confirReceipt(Long weiid, String supOrderID) {
	String sql = "update OSupplyerOrder set state=?,reciptTime=? where buyerID=? and supplierOrderId=?";
	Object[] params = new Object[4];
	params[0] = Short.parseShort(OrderStatusEnum.Accepted.toString());
	params[1] = new Date();
	params[2] = weiid;
	params[3] = supOrderID;
	return super.update(sql, params);
    }

    @Override
    public void addOrderRecord(Long weiid, String record, String supOrderID) {
	OOrderFlow model = new OOrderFlow();
	model.setSupplierOrderId(supOrderID);
	model.setOperateContent(record);
	model.setWeiId(weiid);
	model.setOperateContent(record);
	model.setOperateTime(new Date());
	super.save(model);
    }

    @Override
    public Long addTOrderBackTotal(TOrderBackTotal model) {
	super.save(model);
	return model.getBackOrder();
    }

    @Override
    public int updateSupOrderState(String supOrderID, Short state) {
	String hql = "update OSupplyerOrder set state=? where supplierOrderId=?";
	Object[] params = new Object[2];
	params[0] = state;
	params[1] = supOrderID;
	return super.update(hql, params);
    }

    @Override
    public int updateBackOrder(String[] proOrderID, Long backOrder) {
	String hql = "update OProductOrder set backOrder=:backorder where productOrderId in(:proorderid)";
	Map<String, Object> params = new HashMap<String, Object>();
	params.put("backorder", backOrder);
	params.put("proorderid", proOrderID);
	return super.executeHqlByMap(hql, params);
    }

    @Override
    public void addTkImages(TRefundImg model) {
	super.save(model);
    }

    @Override
    public int updateTrans(long weiid, long backOrder, String transNo, String transName) {
	String hql = "update TOrderBackTotal set transNo=?,transName=?,backStatus=? where backOrder=? and buyerid=?";
	Object[] params = new Object[5];
	params[0] = transNo;
	params[1] = transName;
	params[2] = Short.parseShort(ReFundStatusEnum.BuyerFaHuo.toString());
	params[3] = backOrder;
	params[4] = weiid;
	return super.executeHql(hql, params);
    }

    @Override
    public void updateBackOrder(TOrderBackTotal model) {
	if (model.getBackOrder() == null || model.getBackOrder().longValue() <= 0) {
	    return;
	}
	super.update(model);
    }

    @Override
    public OSupplyerOrder getsSupplyerOrder(String supOrderID) {
	String hql = "from OSupplyerOrder where supplierOrderId=?";
	Object[] params = new Object[1];
	params[0] = supOrderID;
	return super.getUniqueResultByHql(hql, params);
    }

    @Override
    public String savePayOrder(OPayOrder entity) {
	super.save(entity);
	return entity.getPayOrderId();
    }

    @Override
    public TOrderBackTotal getTOrderBackTotal(Long orderbackid) {
	return super.get(TOrderBackTotal.class, orderbackid);
    }

    @Override
    public double getRefundedAmout(String supOrderID) {
	double amout = 0d;
	String hql = "select sum(t.refundAmout) from TOrderBackTotal t  where t.supplierOrderId=? and t.backStatus=?";
	List<Object[]> list = super.find(hql, new Object[] { supOrderID, Short.parseShort(ReFundStatusEnum.GysQueRen.toString()) });
	if (list != null && list.size() > 0) {
	    Object b = list.get(0);
	    if (b != null) {
		amout = (double) b;
	    }
	}
	return amout;
    }

    @Override
    public void setFrozenOrderAmout(String supplyOrder) {
	if (supplyOrder == null || supplyOrder.length() == 0) {
	    return;
	}
	String hql = "Update UTradingDetails p set p.state =:state where p.supplyOrder =:supplyOrder and p.type in (:type) and p.state!=:state1 ";
	// 设置查询参数
	Map<String, Object> params = new HashMap<String, Object>();
	params.put("state", Short.parseShort(UserAmountStatus.shouHuoDongjie.toString()));
	params.put("supplyOrder", supplyOrder);
	Short[] type = new Short[] { Short.parseShort(UserAmountType.orderYj.toString()), Short.parseShort(UserAmountType.rzYj.toString()), Short.parseShort(UserAmountType.supplyPrice.toString()), Short.parseShort(UserAmountType.refund.toString()) };
	params.put("type", type);
	// 排除已退款的佣金
	params.put("state1", Short.parseShort(UserAmountStatus.yiTuiKuan.toString()));
	super.executeHqlByMap(hql, params);
    }
}
