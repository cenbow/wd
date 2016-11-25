package com.okwei.myportal.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.okwei.bean.domain.OPayOrder;
import com.okwei.bean.domain.OPayOrderLog;
import com.okwei.bean.domain.OProductOrder;
import com.okwei.bean.domain.OSupplyerOrder;
import com.okwei.bean.enums.OrderStatusEnum;
import com.okwei.myportal.bean.vo.ResultMsg;
import com.okwei.myportal.dao.IProductDAO;
import com.okwei.myportal.service.IRepaymentService;
import com.okwei.service.impl.BaseService;
import com.okwei.util.GenerateOrderNum;
import com.okwei.util.RedisUtil;

@Service("repayservice")
public class RepaymentService extends BaseService implements IRepaymentService {

    @Autowired
    private IProductDAO baseDao;

    @Override
    public ResultMsg getNewPayOrder(String orderNo) {
	ResultMsg result = new ResultMsg();
	result.setStatus(0);
	OPayOrder payOrder = baseDao.get(OPayOrder.class, orderNo);
	if (payOrder != null) {
	    if (payOrder.getState().intValue() == 1) {
		result.setMsg("该订单已支付成功");
	    } else {
		// 修改订单过期
		payOrder.setState(Short.parseShort(OrderStatusEnum.Tovoided.toString()));
		baseDao.update(payOrder);
		// 1.重新生成支付订单
		String payOrderID = GenerateOrderNum.getInstance().GenerateOrder();
		OPayOrder entity = new OPayOrder();
		entity.setPayOrderId(payOrderID);
		entity.setWeiId(payOrder.getWeiId());
		entity.setSellerWeiId(payOrder.getSellerWeiId());
		entity.setSellerUpWeiId(payOrder.getSellerUpWeiId());
		entity.setTotalPrice(payOrder.getTotalPrice());
		entity.setWalletmoney(payOrder.getWalletmoney());
		entity.setOrderFrom(payOrder.getOrderFrom());
		Date dt = new Date();
		entity.setOrderTime(dt);
		entity.setOrderDate(dt);
		entity.setState((short) 0);
		entity.setTypeState(payOrder.getTypeState());
		entity.setSupplierOrder(payOrder.getSupplierOrder());
		entity.setBigOrderId(payOrder.getBigOrderId());
		baseDao.save(entity);
		// 2.修改供应商订单
		List<OSupplyerOrder> list = baseDao.find("from OSupplyerOrder o where o.payOrderId=?", new Object[] { orderNo });
		if (list != null && list.size() > 0) {
		    List<String> supOrderID = new ArrayList<String>();
		    for (OSupplyerOrder supOrder : list) {
			RedisUtil.delete("PayToBank_" + supOrder.getSupplierOrderId());
			supOrder.setPayOrderId(payOrderID);
			baseDao.update(supOrder);
			supOrderID.add(supOrder.getSupplierOrderId());
		    }
		    // 修改产品订单
		    Map<String, Object> params = new HashMap<String, Object>();
		    params.put("suporderid", (String[]) supOrderID.toArray(new String[supOrderID.size()]));
		    List<OProductOrder> proList = baseDao.findByMap("from OProductOrder o where o.supplierOrderId in(:suporderid)", params);
		    if (proList != null && proList.size() > 0) {
			for (OProductOrder proOrder : proList) {
			    proOrder.setPayOrderId(payOrderID);
			    baseDao.update(proOrder);
			}
		    }

		    // 3.插入日志
		    OPayOrderLog log = new OPayOrderLog();
		    log.setPayOrderId(payOrderID);
		    String supOrders = "";
		    for (String supOrder : supOrderID) {
			supOrders += supOrder + ",";
		    }
		    supOrders = supOrders.substring(0, supOrders.length() - 1);
		    log.setSupplyOrderIds(supOrders);
		    log.setWeiId(payOrder.getWeiId());
		    log.setTotalAmout(payOrder.getTotalPrice());
		    log.setState((short) 0);
		    log.setCreateTime(new Date());
		    baseDao.save(log);
		}
		result.setStatus(1);
		result.setMsg(payOrderID);
	    }
	} else {
	    result.setMsg("支付订单获取失败");
	}
	return result;
    }
}
