package com.okwei.supplyportal.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import okwei.okwei.supplyportal.bean.enumExtend.SupplyBookOrderTypeEnum;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.okwei.bean.domain.OBookAssist;
import com.okwei.bean.domain.OOrderAddr;
import com.okwei.bean.domain.OOrderFlow;
import com.okwei.bean.domain.OPayOrder;
import com.okwei.bean.domain.OProductOrder;
import com.okwei.bean.domain.OSupplyerOrder;
import com.okwei.bean.domain.TOrderBackTotal;
import com.okwei.bean.domain.TRefundImg;
import com.okwei.bean.domain.UCustomerAddr;
import com.okwei.bean.domain.UTradingDetails;
import com.okwei.bean.domain.UWeiSeller;
import com.okwei.bean.enums.AmountStatusEnum;
import com.okwei.bean.enums.AmountTypeEnum;
import com.okwei.bean.enums.BookPayTypeEnum;
import com.okwei.bean.enums.OrderStatusEnum;
import com.okwei.bean.enums.OrderTypeEnum;
import com.okwei.bean.enums.PayTypeEnum;
import com.okwei.bean.enums.ReFundStatusEnum;
import com.okwei.bean.enums.TailPayTypeEnum;
import com.okwei.bean.vo.ReturnModel;
import com.okwei.bean.vo.ReturnStatus;
import com.okwei.common.Limit;
import com.okwei.common.PageResult;
import com.okwei.flow.FlowMethod;
import com.okwei.service.ILogisticsService;
import com.okwei.service.IRegionService;
import com.okwei.service.impl.BaseService;
import com.okwei.supplyportal.bean.vo.LogisticsVO;
import com.okwei.supplyportal.bean.vo.MsgResult;
import com.okwei.supplyportal.bean.vo.ParamOrderSearch;
import com.okwei.supplyportal.bean.vo.ProductOrderVO;
import com.okwei.supplyportal.bean.vo.ProductPriceEditParam;
import com.okwei.supplyportal.bean.vo.RefundVO;
import com.okwei.supplyportal.bean.vo.SupplyBookOrderParam;
import com.okwei.supplyportal.bean.vo.SupplyBookOrderStateCountVO;
import com.okwei.supplyportal.bean.vo.SupplyBookOrderVO;
import com.okwei.supplyportal.bean.vo.SupplyOrderCountSumVO;
import com.okwei.supplyportal.bean.vo.SupplyOrderDetailsVO;
import com.okwei.supplyportal.bean.vo.SupplyOrderStateCountVO;
import com.okwei.supplyportal.bean.vo.SupplyOrderVO;
import com.okwei.supplyportal.dao.IOrderManageDAO;
import com.okwei.supplyportal.service.IConfirmRefundService;
import com.okwei.supplyportal.service.IOrderManageService;
import com.okwei.util.AppSettingUtil;
import com.okwei.util.DateUtils;
import com.okwei.util.ImgDomain;
import com.okwei.util.ObjectUtil;
import com.okwei.util.ParseHelper;

@Service
public class OrderManageService extends BaseService implements IOrderManageService {

    @Autowired
    private IOrderManageDAO orderBase;

    @Autowired
    private ILogisticsService logisticsService;

    @Autowired
    private IConfirmRefundService confirmRefundService;

    @Autowired
    private IRegionService regionService;

    private Log logger = LogFactory.getLog(this.getClass());

    @Override
    public PageResult<SupplyOrderVO> getOrderListPageResult(ParamOrderSearch param, Long supplyWeiid, Limit limit) {
	PageResult<OSupplyerOrder> suplist = orderBase.getSupplyOrderlistResult(param, supplyWeiid, limit);
	if (suplist == null || suplist.getTotalCount() <= 0)
	    return null;
	PageResult<SupplyOrderVO> result = new PageResult<SupplyOrderVO>();
	// TODO 供应商订单列表
	String[] supplyOrderIds = new String[suplist.getList().size()];
	Long[] buyerWids = new Long[suplist.getList().size()];
	for (int i = 0; i < suplist.getList().size(); i++) {
	    supplyOrderIds[i] = suplist.getList().get(i).getSupplierOrderId();
	    buyerWids[i] = suplist.getList().get(i).getBuyerID();
	}
	// 订单商品列表
	List<OProductOrder> productlist = orderBase.getProductOrderListBySupplyOrderIds(supplyOrderIds);
	// 买家信息
	List<UWeiSeller> userSellers = orderBase.getUsersByWeiids(buyerWids);
	List<SupplyOrderVO> orderlist = new ArrayList<SupplyOrderVO>();
	for (OSupplyerOrder oo : suplist.getList()) {
	    SupplyOrderVO ss = new SupplyOrderVO();
	    ss.setOrderNo(oo.getSupplierOrderId());
	    ss.setBuyerWeiid(oo.getBuyerID());
	    ss.setOrderState(oo.getState());
	    Map<Short, OrderStatusEnum> mapState = new HashMap<Short, OrderStatusEnum>();
	    for (OrderStatusEnum tt : OrderStatusEnum.values()) {
		mapState.put(Short.parseShort(tt.toString()), tt);
	    }
	    ss.setOrderStateName(getOrderStateName(mapState.get(oo.getState())));
	    ss.setTotalPrice(ParseHelper.getDoubleDefValue(oo.getTotalPrice()) + ParseHelper.getDoubleDefValue(oo.getPostage()));
	    ss.setPostPrice(oo.getPostage());
	    if (oo.getOrderTime() != null)
		ss.setCreateTimeStr(DateUtils.format(oo.getOrderTime(), "yyyy-MM-dd hh:mm:ss"));

	    for (UWeiSeller user : userSellers) {
		if (oo.getBuyerID().equals(user.getWeiId()))
		    ss.setBuyerName(user.getWeiName());
	    }
	    ss.setProList(getSupplyOrderProducts(oo.getSupplierOrderId(), productlist));
	    orderlist.add(ss);
	}
	result.setList(orderlist);
	result.setPageIndex(limit.getPageId());
	result.setPageSize(limit.getSize());
	result.setPageCount(suplist.getPageCount());
	result.setTotalCount(suplist.getTotalCount());
	return result;
    }

    /**
     * 获取供应商预订单列表
     * 
     * @param param
     * @param supplyWeiid
     * @param limit
     * @return
     */
    public PageResult<SupplyBookOrderVO> getBookOrderListPageResult(ParamOrderSearch param, Long supplyWeiid, Limit limit) {
	PageResult<OSupplyerOrder> suplist = orderBase.getSupplyBookOrderlistResult(param, supplyWeiid, limit);
	if (suplist == null || suplist.getTotalCount() <= 0)
	    return null;
	PageResult<SupplyBookOrderVO> result = new PageResult<SupplyBookOrderVO>();
	// TODO 供应商订单列表
	String[] supplyOrderIds = new String[suplist.getList().size()];
	Long[] buyerWids = new Long[suplist.getList().size()];
	for (int i = 0; i < suplist.getList().size(); i++) {
	    supplyOrderIds[i] = suplist.getList().get(i).getSupplierOrderId();
	    buyerWids[i] = suplist.getList().get(i).getBuyerID();
	}
	// 订单商品列表
	List<OProductOrder> productlist = orderBase.getProductOrderListBySupplyOrderIds(supplyOrderIds);
	// 买家信息
	List<UWeiSeller> userSellers = orderBase.getUsersByWeiids(buyerWids);
	// 预订单支付列表
	// List<OPayOrder> opayorderlist=null;
	// 预订单辅助信息
	List<OBookAssist> assistlist = orderBase.getOBookAssistlistBySupplyOrderIds(supplyOrderIds);

	List<SupplyBookOrderVO> orderlist = new ArrayList<SupplyBookOrderVO>();
	for (OSupplyerOrder oo : suplist.getList()) {
	    SupplyBookOrderVO ss = new SupplyBookOrderVO();
	    ss.setOrderNo(oo.getSupplierOrderId());
	    ss.setBuyerWeiid(oo.getBuyerID());
	    ss.setOrderState(oo.getState());
	    Map<Short, OrderStatusEnum> mapState = new HashMap<Short, OrderStatusEnum>();
	    for (OrderStatusEnum tt : OrderStatusEnum.values()) {
		mapState.put(Short.parseShort(tt.toString()), tt);
	    }
	    ss.setOrderStateName(getOrderStateName(mapState.get(oo.getState())));
	    ss.setTotalPrice(ParseHelper.getDoubleDefValue(oo.getTotalPrice()) + ParseHelper.getDoubleDefValue(oo.getPostage()));
	    ss.setPostPrice(oo.getPostage());
	    if (oo.getOrderTime() != null)
		ss.setCreateTimeStr(DateUtils.format(oo.getOrderTime(), "yyyy-MM-dd hh:mm:ss"));
	    if (userSellers != null && userSellers.size() > 0) {
		for (UWeiSeller user : userSellers) {
		    if (oo.getBuyerID().equals(user.getWeiId()))
			ss.setBuyerName(user.getWeiName());
		}
	    }
	    ss.setProList(getSupplyOrderProducts(oo.getSupplierOrderId(), productlist));
	    // 付款金额
	    List<String> contentList = new ArrayList<String>();
	    boolean ass = true;
	    for (OBookAssist opay : assistlist) {
		if (opay.getSupplierOrderId() != null && opay.getSupplierOrderId().equals(oo.getSupplierOrderId())) {
		    if (opay.getBookPayType() != null && Short.parseShort(BookPayTypeEnum.Full.toString()) == opay.getBookPayType()) {
			contentList.add("全额支付" + ss.getTotalPrice());
		    } else if (opay.getBookPayType() != null) {
			contentList.add("定金：" + opay.getAmount());
			// 发货后付尾款
			if (opay.getTailPayType() != null && Short.parseShort(TailPayTypeEnum.afterdelivery.toString()) == opay.getTailPayType()) {
			    contentList.add("发货后支付尾款：" + (ParseHelper.getDoubleDefValue(ss.getTotalPrice()) - ParseHelper.getDoubleDefValue(opay.getAmount())));
			} else {
			    contentList.add("发货前支付尾款：" + String.format("%.2f", (ParseHelper.getDoubleDefValue(ss.getTotalPrice()) - ParseHelper.getDoubleDefValue(opay.getAmount()))));
			}
			contentList.add("预计发货时间：" + DateUtils.dateToString(opay.getPreSendTime(), "yyyy-MM-dd"));
		    }
		    ass = false;
		    ss.setOrderState(getSupplyerBookState(ss.getOrderState(), opay.getTailPayType()));
		    ss.setOrderStateName(getSupplyerBookStateName(ss.getOrderState(), opay.getTailPayType()));
		    // hjd,6-6添加
		    if (opay.getBookPayType() != null)
			ss.setBookType(opay.getBookPayType());
		    else {
			ss.setBookType(-1);
		    }
		    if (opay.getTailPayType() != null)
			ss.setPaymentType(opay.getTailPayType());
		    else {
			ss.setPaymentType(-1);
		    }
		    ss.setOrderState(oo.getState());
		    ss.setOrderStateName(getOrderStateName(mapState.get(oo.getState())));
		}
	    }
	    if (ass) {
		// ss.setOrderState(Integer.parseInt(SupplyBookOrderTypeEnum.Waitting.toString()));
		ss.setOrderStateName("待确认");
	    }
	    ss.setPayContent(contentList);
	    orderlist.add(ss);
	}
	result.setList(orderlist);
	result.setPageIndex(limit.getPageId());
	result.setPageSize(limit.getSize());
	result.setPageCount(suplist.getPageCount());
	result.setTotalCount(suplist.getTotalCount());
	return result;
    }

    @Override
    public SupplyOrderCountSumVO getSupplyOrderCountSumVO(Long supplyerWeiid) {
	SupplyOrderCountSumVO result = new SupplyOrderCountSumVO();
	// Integer[] types = new Integer[]
	// {Integer.parseInt(OrderTypeEnum.Pt.toString()),Integer.parseInt(OrderTypeEnum.BatchOrder.toString()),Integer.parseInt(OrderTypeEnum.BatchWholesale.toString()),
	// Integer.parseInt(OrderTypeEnum.BookOrder.toString())};
	// List<OSupplyerOrder> orderList =
	// orderBase.getSupplyOrderlist(supplyerWeiid,types);
	// if(orderList != null && orderList.size() > 0)
	// {
	// int lsCount = 0,pfCount = 0,ydCount = 0;
	// for(OSupplyerOrder oo : orderList)
	// {
	// if(Short.parseShort(OrderTypeEnum.Pt.toString()) == oo.getOrderType()
	// ||
	// Short.parseShort(OrderTypeEnum.BatchOrder.toString()) ==
	// oo.getOrderType())
	// lsCount++;
	// else if(Short.parseShort(OrderTypeEnum.BatchWholesale.toString()) ==
	// oo.getOrderType())
	// {
	// pfCount++;
	// }
	// else if(Short.parseShort(OrderTypeEnum.BookOrder.toString()) ==
	// oo.getOrderType())
	// {
	// ydCount++;
	// }
	// }
	// result.setLingshowCount(lsCount);
	// result.setPifaCount(pfCount);
	// result.setYudingCount(ydCount);
	// }
	// 改版
	int linshouCount = orderBase.getSupplyOrderCount(supplyerWeiid, new Integer[] { Integer.parseInt(OrderTypeEnum.Pt.toString()), Integer.parseInt(OrderTypeEnum.BatchOrder.toString()) });
	int pifaCount = orderBase.getSupplyOrderCount(supplyerWeiid, new Integer[] { Integer.parseInt(OrderTypeEnum.BatchWholesale.toString()) });
	int yudingCount = orderBase.getSupplyOrderCount(supplyerWeiid, new Integer[] { Integer.parseInt(OrderTypeEnum.BookOrder.toString()) });
	result.setLingshowCount(linshouCount);
	result.setPifaCount(pifaCount);
	result.setYudingCount(yudingCount);
	return result;
    }

    @Override
    public SupplyOrderStateCountVO getSupplyOrderStateCountVO(Long supplyerWeiid, int itype) {
	SupplyOrderStateCountVO result = new SupplyOrderStateCountVO();
	Integer[] types = null;
	switch (itype) {
	    case 1:
		types = new Integer[] { Integer.parseInt(OrderTypeEnum.Pt.toString()), Integer.parseInt(OrderTypeEnum.BatchOrder.toString()) };
		break;
	    case 2:
		types = new Integer[] { Integer.parseInt(OrderTypeEnum.BatchWholesale.toString()) };
		break;
	    default:
		return result;
	}
	List<Object[]> orderList = orderBase.getCountSupplyOrderlist(supplyerWeiid, types);
	if (orderList != null && orderList.size() > 0) {
	    int nopayCount = 0, noFahuoCount = 0, yiFahuo = 0, completedCount = 0, refundCount = 0;
	    for (Object[] oo : orderList) {
		if (Short.parseShort(OrderStatusEnum.Nopay.toString()) == Short.parseShort(oo[1].toString()))
		    nopayCount = Integer.parseInt(String.valueOf(oo[0]));
		else if (Short.parseShort(OrderStatusEnum.Payed.toString()) == Short.parseShort(oo[1].toString())) {
		    noFahuoCount = Integer.parseInt(String.valueOf(oo[0]));
		} else if (Short.parseShort(OrderStatusEnum.Deliveried.toString()) == Short.parseShort(oo[1].toString())) {
		    yiFahuo = Integer.parseInt(String.valueOf(oo[0]));
		} else if (Short.parseShort(OrderStatusEnum.Completed.toString()) == Short.parseShort(oo[1].toString())) {
		    completedCount = Integer.parseInt(String.valueOf(oo[0]));
		} else if (Short.parseShort(OrderStatusEnum.Refunding.toString()) == Short.parseShort(oo[1].toString())) {
		    refundCount = Integer.parseInt(String.valueOf(oo[0]));
		}
	    }
	    result.setNoFaHuoCount(noFahuoCount);
	    result.setNoPayCount(nopayCount);
	    result.setNoShouHuoCount(yiFahuo);
	    result.setCompletedCount(completedCount);
	    result.setRefundCount(refundCount);
	}
	return result;
    }

    @Override
    public SupplyBookOrderStateCountVO getSupplyBookOrderStateCountVO(Long supplyerWeiid) {
	SupplyBookOrderStateCountVO result = new SupplyBookOrderStateCountVO();
	Integer[] types = new Integer[] { Integer.parseInt(OrderTypeEnum.BookOrder.toString()) };// Integer.parseInt(OrderTypeEnum.Pt.toString()),
	// Integer.parseInt(OrderTypeEnum.BatchOrder.toString())
	List<Object[]> orderList = orderBase.getCountSupplyOrderlist(supplyerWeiid, types);
	if (orderList == null || orderList.size() <= 0)
	    return result;
	// String[] supplyorderids = new String[orderList.size()];
	// for(int i = 0; i < orderList.size(); i++)
	// {
	// supplyorderids[i] = orderList.get(i).getSupplierOrderId();
	// }
	// List<OBookAssist> assistslist =
	// orderBase.getOBookAssistlistBySupplyOrderIds(supplyorderids);
	if (orderList != null && orderList.size() > 0) {
	    int waitCount = 0, noFahuoCount = 0, completedCount = 0, nopayTailCount = 0, suredCount = 0;
	    for (Object[] oo : orderList) {
		if (Short.parseShort(OrderStatusEnum.WaitSure.toString()) == Short.parseShort(oo[1].toString()))
		    waitCount = Integer.parseInt(String.valueOf(oo[0]));
		else if (Short.parseShort(OrderStatusEnum.Sured.toString()) == Short.parseShort(oo[1].toString())) {
		    suredCount = Integer.parseInt(String.valueOf(oo[0]));
		} else if (Short.parseShort(OrderStatusEnum.PayedDeposit.toString()) == Short.parseShort(oo[1].toString())) {
		    nopayTailCount = Integer.parseInt(String.valueOf(oo[0]));
		    // 如果 发货后付尾款 noFahuoCount++

		} else if (Short.parseShort(OrderStatusEnum.Completed.toString()) == Short.parseShort(oo[1].toString())) {
		    completedCount = Integer.parseInt(String.valueOf(oo[0]));
		} else if (Short.parseShort(OrderStatusEnum.Payed.toString()) == Short.parseShort(oo[1].toString())) {
		    noFahuoCount = Integer.parseInt(String.valueOf(oo[0]));
		}
	    }
	    noFahuoCount += orderBase.getCountOBookAssistlist(supplyerWeiid, Short.parseShort(OrderStatusEnum.PayedDeposit.toString()));
	    // if(assistslist != null && assistslist.size() > 0)
	    // {
	    // for(OBookAssist bb : assistslist)
	    // {
	    // if(bb.getSupplierOrderId().equals(oo.getSupplierOrderId()))
	    // {
	    // if(Short.parseShort(TailPayTypeEnum.afterdelivery.toString()) ==
	    // bb.getTailPayType())
	    // noFahuoCount++;
	    // }
	    // }
	    // }
	    result.setCompletedCount(completedCount);
	    result.setNoFaHuoCount(noFahuoCount);
	    result.setNoPayTailCount(nopayTailCount);
	    result.setSuredCount(suredCount);
	    result.setWaitCount(waitCount);
	}
	return result;
    }

    @Override
    public SupplyOrderDetailsVO getOrderDetails(String supplyOrderid) {
	SupplyOrderDetailsVO result = new SupplyOrderDetailsVO();
	try {
	    OSupplyerOrder order = orderBase.getEntity(OSupplyerOrder.class, supplyOrderid);
	    if (order != null) {
		String[] supplyidStrings = new String[] { order.getSupplierOrderId() };
		List<OProductOrder> prolist = orderBase.getProductOrderListBySupplyOrderIds(supplyidStrings);
		if (prolist != null && prolist.size() > 0) {
		    result.setProList(getSupplyOrderProducts(order.getSupplierOrderId(), prolist));
		}
		OOrderAddr addrInfo = orderBase.getEntity(OOrderAddr.class, order.getAddrId());
		result.setOrderNo(order.getSupplierOrderId());
		result.setOrderTimeStr(DateUtils.format(order.getOrderTime(), "yyyy-MM-dd HH:mm:ss"));
		result.setClientMsg(order.getMessage());
		result.setOrderState(order.getState());
		result.setBuyerWeiid(order.getBuyerID());
		result.setClientMsg(order.getMessage());
		result.setSupplyerWeiid(order.getSupplyerId());
		result.setTotalPrice(ParseHelper.getDoubleDefValue(order.getTotalPrice()) + ParseHelper.getDoubleDefValue(order.getPostage()));
		result.setFullprice(ParseHelper.getDoubleDefValue(order.getTotalPrice()) + ParseHelper.getDoubleDefValue(order.getPostage()));
		result.setPostPrice(order.getPostage());
		OPayOrder payOrder = null;
		if (Short.parseShort(OrderTypeEnum.BookOrder.toString()) == order.getOrderType()) {
		    List<OPayOrder> payList = orderBase.getOPayOrderlistBySupplyOrderIds(new String[] { order.getSupplierOrderId() });
		    if (payList != null && payList.size() > 0) {
			for (OPayOrder pay : payList) {
			    if (Short.parseShort(OrderTypeEnum.BookHeadOrder.toString()) == pay.getTypeState()) {
				payOrder = pay;
				if (pay.getOrderTime() != null) {
				    result.setMakeOrderTimeStr(pay.getOrderTime().toString());
				}
				result.setPayWay(getpayWayStr(pay.getPayType()));
			    }
			}
		    }
		} else {
		    payOrder = orderBase.getEntity(OPayOrder.class, order.getPayOrderId());
		    if (payOrder != null && payOrder.getPayTime() != null)
			result.setPaymentTimeStr(payOrder.getPayTime().toString());

		    result.setPayWay(getpayWayStr(payOrder.getPayType()));
		}
		if (payOrder != null) {
		    PayTypeEnum payTypeEnum = null;
		    for (PayTypeEnum tt : PayTypeEnum.values()) {
			if (tt.toString().equals(payOrder.getPayType()))
			    payTypeEnum = tt;
		    }
		    if (payTypeEnum != null)
			result.setPayWay(getPayTypeName(payTypeEnum));
		}
		if (addrInfo != null) {
		    result.setReciverName(addrInfo.getReceiverName());
		    result.setReciverPhoneNumber(addrInfo.getMobilePhone());
		    // hjd6-15
		    String address = "";
		    int province = addrInfo.getProvince() == null ? 0 : addrInfo.getProvince();
		    if (province > 0) {
			address += regionService.getNameByCode(province);
		    }
		    int city = addrInfo.getCity() == null ? 0 : addrInfo.getCity();
		    if (city > 0) {
			address += regionService.getNameByCode(city);
		    }
		    int street = addrInfo.getDistrict() == null ? 0 : addrInfo.getDistrict();
		    if (street > 0) {
			address += regionService.getNameByCode(street);
		    }
		    address += addrInfo.getDetailAddr();
		    result.setReciverAddress(address);
		}
		OrderTypeEnum typeEnum = null;
		for (OrderTypeEnum tt : OrderTypeEnum.values()) {
		    if (Integer.parseInt(tt.toString()) == order.getOrderType())
			typeEnum = tt;
		}
		result.setOrderShowType(1);
		if (typeEnum == null)
		    typeEnum = OrderTypeEnum.Pt;
		switch (typeEnum) {
		    case Pt:
		    case BatchOrder:
			result.setOrderShowType(1);
			result.setPayPriceType(1);
			break;
		    case BatchWholesale:
			result.setOrderShowType(2);
			result.setPayPriceType(1);
			break;
		    case BookOrder:// 预订单
			result.setOrderShowType(3);
			OBookAssist modelAssist = orderBase.getEntity(OBookAssist.class, order.getSupplierOrderId());
			if (modelAssist != null) {
			    result.setDeliveryTimeStr(modelAssist.getPreSendTime() == null ? "" : DateUtils.dateToString(modelAssist.getPreSendTime(), "yyyy-MM-dd").toString());
			    if (Short.parseShort(BookPayTypeEnum.Full.toString()) == modelAssist.getBookPayType()) {
				result.setPayPriceType(1);
				result.setFullprice(modelAssist.getAmount());
				result.setPayment(order.getTotalPrice());
			    } else if (Short.parseShort(BookPayTypeEnum.percent.toString()) == modelAssist.getBookPayType()) {
				result.setPayPriceType(3);
				result.setPercentage(modelAssist.getPersent());
				result.setDeposit(modelAssist.getAmount());
				result.setFinalprice(ParseHelper.getDoubleDefValue(order.getTotalPrice() + ParseHelper.getDoubleDefValue(order.getPostage()) - ParseHelper.getDoubleDefValue(modelAssist.getAmount())));
			    } else {
				result.setPayPriceType(2);
				result.setDeposit(modelAssist.getAmount());
				result.setFinalprice(ParseHelper.getDoubleDefValue(order.getTotalPrice() + ParseHelper.getDoubleDefValue(order.getPostage()) - ParseHelper.getDoubleDefValue(modelAssist.getAmount())));
			    }
			    result.setSupplierMsg(modelAssist.getRemark());
			    result.setTailPayType(modelAssist.getTailPayType());
			}
			break;
		    default:
			result.setOrderShowType(1);
			break;
		}
		List<OOrderFlow> flows = orderBase.getOrderFlows(order.getSupplierOrderId());
		result.setOrderFlows(flows);

		// 查询物流信息
		String wlNo = order.getDeliveryOrder();
		String companyNo = order.getdComanpyNo();
		String companyName = order.getDeliveryCompany();
		List<String> wuliu = new ArrayList<String>();
		if (wlNo != null && wlNo != "") {
		    String wlString = "";
		    if (companyNo != null && companyNo != "") {
			wlString = logisticsService.getInfoByNo(wlNo, companyNo);
		    } else if (companyName != null && companyName != "") {
			wlString = logisticsService.getInfoByName(wlNo, companyName);
		    }
		    wuliu = getLogisticsDetails(wlString);
		}
		LogisticsVO logistics = new LogisticsVO();
		logistics.setLogisticsNo(wlNo);
		logistics.setLongisticsName(companyName);
		logistics.setTailList(wuliu);
		result.setLogistics(logistics);
	    }
	} catch (Exception e) {
	    // TODO: handle exception
	}
	return result;
    }

    /**
     * 产品列表清单 根据 供应商订单id 产品列表
     * 
     * @param supplyOrderId
     * @param prolist
     * @return
     */
    private List<ProductOrderVO> getSupplyOrderProducts(String supplyOrderId, List<OProductOrder> prolist) {
	try {
	    if (ObjectUtil.isNotEmpty(prolist) && !ObjectUtil.isEmpty(supplyOrderId)) {
		List<Long> backorderids = new ArrayList<Long>();
		for (OProductOrder oo : prolist) {
		    if (oo.getBackOrder() != null && oo.getBackOrder().longValue() > 0)
			backorderids.add(oo.getBackOrder());
		}
		List<TOrderBackTotal> reOrders = null;
		if (backorderids != null && backorderids.size() > 0) {
		    reOrders = orderBase.getRefundOrders(backorderids.toArray());
		}
		List<ProductOrderVO> supProductList = new ArrayList<ProductOrderVO>();
		for (OProductOrder pp : prolist) {
		    if (supplyOrderId.equals(pp.getSupplierOrderId())) {
			ProductOrderVO pro = new ProductOrderVO();
			pro.setProductId(pp.getProductId());
			pro.setProductTitle(pp.getProdcutTitle());
			pro.setProductState(pp.getState());
			pro.setCount(pp.getCount());
			pro.setPrice(pp.getPrice());
			pro.setCommission(pp.getCommision());
			pro.setSumPrice(pp.getCount() * pp.getPrice());
			pro.setProductOrderid(pp.getProductOrderId());
			pro.setProductImg(ImgDomain.GetFullImgUrl(pp.getProductImg(), 24));
			if (pp.getStyleDes() != null) {
			    pro.setProperty(pp.getStyleDes().replace("|", " "));
			}
			pro.setFavorable("无");
			long sellerweiid = pp.getSellerWeiid();
			UWeiSeller user = orderBase.getEntity(UWeiSeller.class, sellerweiid);
			pro.setSourceName(user.getWeiName());
			pro.setRefundState(-1);
			if (pp.getBackOrder() != null && pp.getBackOrder().longValue() > 0) {
			    if (reOrders != null) {
				for (TOrderBackTotal tt : reOrders) {
				    if (tt.getBackOrder().longValue() == pp.getBackOrder().longValue()) {
					pro.setRefundState(tt.getBackStatus());
					pro.setRefundId(pp.getBackOrder());
				    }
				}
			    }
			}
			supProductList.add(pro);
		    }
		}
		return supProductList;
	    }
	} catch (Exception e) {
	    // TODO: handle exception
	}
	return null;
    }

    @Override
    public MsgResult insertBookOrder(SupplyBookOrderParam param) {
	MsgResult result = new MsgResult();
	if (param == null || param.getWeiid() <= 0 || ObjectUtil.isEmpty(param.getSupplyOrderid())) {
	    result.setState(-1);
	    result.setMsg("参数有误");
	    return result;
	}
	// TODO 预定单未完成
	if (param.isSured())// 确认 预订单
	{
	    // 清楚之前的预定单
	    List<OPayOrder> payList = orderBase.getOPayOrdersBySupplyOrderid(param.getSupplyOrderid());
	    if (payList != null && payList.size() > 0) {
		for (OPayOrder oo : payList) {
		    super.delete(oo);
		}
	    }
	    OBookAssist payAssist = orderBase.getEntity(OBookAssist.class, param.getSupplyOrderid());
	    if (payAssist != null)
		super.delete(payAssist);
	    OSupplyerOrder supplyerOrder = orderBase.getEntity(OSupplyerOrder.class, param.getSupplyOrderid());
	    if (supplyerOrder == null || supplyerOrder.getSupplyerId().longValue() != param.getWeiid()) {
		result.setState(-1);
		result.setMsg("非法操作");
		return result;
	    }
	    // 需要支付的总价
	    double paytotalprice = 0;
	    // 1 .修改订单价格方式
	    switch (param.getEditPriceType()) {
		case 1:// 修改产品单价
		    if (param.getProducts() != null && param.getProducts().size() > 0) {
			double totalprice = 0;
			// String[] supplyOrderIds = new String[] {
			// param.getSupplyOrderid() };
			// 供应商订单包含的产品列表
			List<OProductOrder> proList = orderBase.getProductOrderListBySupplyOrderIds(new String[] { param.getSupplyOrderid() });
			if (proList != null && proList.size() > 0) {
			    for (OProductOrder pl : proList) {
				for (OProductOrder pp : param.getProducts()) {
				    if (!ObjectUtil.isEmpty(pp.getProductOrderId()) && pp.getPrice() != null) {
					if (pl.getProductOrderId().equals(pp.getProductOrderId())) {
					    pl.setPrice(pp.getPrice());
					    super.update(pl);
					}
				    }
				}
				paytotalprice += (pl.getPrice().doubleValue() + pl.getCommision().doubleValue()) * pl.getCount().intValue();
				totalprice += pl.getPrice().doubleValue() * pl.getCount().intValue();
			    }
			    paytotalprice += ParseHelper.getDoubleDefValue(param.getEditPostPrice());
			    supplyerOrder.setPostage(param.getEditPostPrice());
			    supplyerOrder.setTotalPrice(totalprice);

			}
		    }
		    break;
		case 2:// 修改订单总价
		    if (param.getEditTotalPrice() > 0.1) {
			supplyerOrder.setTotalPrice(param.getEditTotalPrice());
			supplyerOrder.setPostage(0d);
			paytotalprice = param.getEditTotalPrice();
		    } else {
			result.setState(-1);
			result.setMsg("总价不能小于0.1元");
		    }
		    break;
		default:// 不修改价格，不操作
		    paytotalprice = supplyerOrder.getTotalPrice().doubleValue() + supplyerOrder.getPostage().doubleValue();
		    break;
	    }
	    OBookAssist ass = new OBookAssist();
	    ass.setSupplierOrderId(param.getSupplyOrderid());
	    ass.setPreSendTime(param.getPreDeliverTime()); // 预计发货时间
	    // 2. 首款 付款方式
	    switch (param.getPayType()) {
		case 1:// 百分比
		    ass.setBookPayType(Short.parseShort(BookPayTypeEnum.percent.toString()));
		    ass.setPersent(Short.parseShort(String.valueOf(param.getFirstPercent())));
		    ass.setAmount(paytotalprice * param.getFirstPercent() * 0.01);
		    break;
		case 2:// 指定金额
		    ass.setBookPayType(Short.parseShort(BookPayTypeEnum.specifyamount.toString()));
		    ass.setAmount(param.getFirstPrice());
		    break;
		default:// 全款
		    ass.setBookPayType(Short.parseShort(BookPayTypeEnum.Full.toString()));
		    ass.setAmount(paytotalprice);
		    break;
	    }
	    if (param.getPayType() > 0) {
		// 3.尾款付款方式
		TailPayTypeEnum tailPayTypeEnum = param.getTailPayType();
		ass.setTailPayType(Short.parseShort(tailPayTypeEnum.toString()));
		// 4、生成 预订单 首款、尾款
		orderBase.insertPayOrder(supplyerOrder.getSupplierOrderId(), supplyerOrder.getBuyerID().longValue(), ass.getAmount(), supplyerOrder.getOrderFrom(), Short.parseShort(OrderTypeEnum.BookHeadOrder.toString()));
		orderBase.insertPayOrder(supplyerOrder.getSupplierOrderId(), supplyerOrder.getBuyerID().longValue(), paytotalprice - ass.getAmount(), supplyerOrder.getOrderFrom(), Short.parseShort(OrderTypeEnum.BookTailOrder.toString()));
	    } else {
		ass.setTailPayType((short) 0);
		// 生成全款支付记录
		orderBase.insertPayOrder(param.getSupplyOrderid(), supplyerOrder.getBuyerID().longValue(), paytotalprice, supplyerOrder.getOrderFrom(), Short.parseShort(OrderTypeEnum.BookFullOrder.toString()));
	    }
	    ass.setState((short) 1);
	    ass.setRemark(param.getMessage());
	    super.add(ass);
	    supplyerOrder.setState(Short.parseShort(OrderStatusEnum.Sured.toString()));
	    super.update(supplyerOrder);
	    orderBase.insertOrderFlow(supplyerOrder.getSupplierOrderId(), supplyerOrder.getSupplyerId(), "供应商确认订单");
	    result.setState(1);
	    result.setMsg("确定成功！");

	} else
	// 拒绝
	{
	    if (orderBase.UP_supplyOrder(param.getWeiid(), param.getSupplyOrderid(), OrderStatusEnum.Rejected)) {
		result.setState(1);
		result.setMsg("成功拒绝此预订单！");
	    } else {
		result.setState(-1);
		result.setMsg("拒绝失败！");
	    }
	}
	return result;
    }

    /**
     * 获取物流公司列表
     */
    public List<Map<String, String>> getLogistics() {
	List<Map<String, String>> list = AppSettingUtil.getMaplist("transports");
	return list;
    }

    public MsgResult sendGoods(String supplyOrderid, long supplyWeiid, String dcomanpyNo, String deliveryCompany, String deliveryOrderNo) {
	MsgResult result = new MsgResult();
	if (ObjectUtil.isEmpty(supplyOrderid) || ObjectUtil.isEmpty(deliveryCompany) || ObjectUtil.isEmpty(deliveryOrderNo)) {
	    result.setState(-1);
	    result.setMsg("参数不全");
	    return result;
	}
	OSupplyerOrder order = orderBase.getEntity(OSupplyerOrder.class, supplyOrderid);
	if (order == null) {
	    result.setState(-1);
	    result.setMsg("订单不存在");
	    return result;
	} else if (supplyWeiid != order.getSupplyerId()) {
	    result.setState(-1);
	    result.setMsg("您没有操作权限");
	    return result;
	}
	boolean isOkLogistics = false;
	List<Map<String, String>> logistics = AppSettingUtil.getMaplist("transports");
	if (logistics != null && logistics.size() > 0) {
	    for (Map<String, String> map : logistics) {
		if (map.get("typeName").equals(deliveryCompany)) {
		    dcomanpyNo = map.get("typeNo");
		    isOkLogistics = true;
		}
	    }
	}
	if (!isOkLogistics) {
	    result.setState(-1);
	    result.setMsg("不支持的物流公司");
	    return result;
	}
	OrderTypeEnum typeEnum = null;
	for (OrderTypeEnum tt : OrderTypeEnum.values()) {
	    if (Integer.parseInt(tt.toString()) == order.getOrderType())
		typeEnum = tt;
	}
	if (typeEnum == null) {
	    result.setState(-1);
	    result.setMsg("系统错误303");
	    return result;
	}
	switch (typeEnum) {
	    case Pt:
	    case BatchOrder:
	    case BatchWholesale:
		if (Short.parseShort(OrderStatusEnum.Payed.toString()) == order.getState() || Short.parseShort(OrderStatusEnum.Deliveried.toString()) == order.getState()) {
		    order.setdComanpyNo(dcomanpyNo);
		    order.setDeliveryCompany(deliveryCompany);
		    order.setDeliveryOrder(deliveryOrderNo);
		}
		// else
		// if(Short.parseShort(OrderStatusEnum.Refunding.toString()) ==
		// order.getState())
		// {
		//
		// }
		else {
		    result.setState(-1);
		    result.setMsg("当前状态不是已支付/已发货状态，不能发货！");
		    return result;
		}
		break;
	    case BookOrder:
		OBookAssist assist = orderBase.getEntity(OBookAssist.class, supplyOrderid);
		if (assist == null) {
		    result.setState(-1);
		    result.setMsg("订单类型错误!");
		    return result;
		}
		if ((Short.parseShort(BookPayTypeEnum.Full.toString()) == assist.getBookPayType() && Short.parseShort(OrderStatusEnum.Payed.toString()) == order.getState()) || (Short.parseShort(TailPayTypeEnum.afterdelivery.toString()) == assist.getTailPayType() && Short.parseShort(OrderStatusEnum.PayedDeposit.toString()) == order.getState()) || (Short.parseShort(TailPayTypeEnum.predelivery.toString()) == assist.getTailPayType() && Short.parseShort(OrderStatusEnum.Payed.toString()) == order.getState())) {
		    order.setdComanpyNo(dcomanpyNo);
		    order.setDeliveryCompany(deliveryCompany);
		    order.setDeliveryOrder(deliveryOrderNo);
		}
		break;
	    default:
		result.setState(-1);
		result.setMsg("系统错误304");
		return result;
	}
	OOrderFlow OrderFlow = new OOrderFlow();
	OrderFlow.setSupplierOrderId(order.getSupplierOrderId());
	OrderFlow.setOperateContent("供应商发货成功");
	Date operateTime = new Date();
	OrderFlow.setOperateTime(operateTime);
	OrderFlow.setWeiId(order.getSupplyerId());
	super.add(OrderFlow); // 保存实体

	order.setState(Short.parseShort(OrderStatusEnum.Deliveried.toString()));
	order.setSendTime(new Date());
	super.update(order);
	result.setState(1);
	result.setMsg("发货成功");
	return result;
    }

    public MsgResult editProductPrice(ProductPriceEditParam param) {
	MsgResult result = new MsgResult();
	if (param == null || ObjectUtil.isEmpty(param.getSupplyOrderId()) || param.getSupplyWeiid() <= 0) {
	    result.setState(-1);
	    result.setMsg("参数有误");
	    return result;
	}
	OSupplyerOrder order = orderBase.getEntity(OSupplyerOrder.class, param.getSupplyOrderId());
	if (order == null || order.getSupplyerId() != param.getSupplyWeiid()) {
	    result.setState(-1);
	    result.setMsg("您不能操作其他卖家的订单");
	    return result;
	}
	if (param.getTotalPrice() <= 0) {
	    result.setState(-1);
	    result.setMsg("订单总价至少大于0");
	    return result;
	}
	order.setPostage(param.getPostPrice());
	double totalprice = param.getTotalPrice() - ParseHelper.getDoubleDefValue(param.getPostPrice());
	if (totalprice < 0.1) {
	    result.setState(-1);
	    result.setMsg("修改价格不成功");
	    return result;
	}
	OPayOrder payOrder = orderBase.getEntity(OPayOrder.class, param.getSupplyOrderId());
	if (payOrder != null) {
	    payOrder.setTotalPrice(param.getTotalPrice());
	    super.update(payOrder);
	}
	order.setTotalPrice(totalprice);
	order.setIsChagePrice((short)2);
	super.update(order);
	result.setState(1);
	result.setMsg("修改价格成功");
	return result;
    }

    public RefundVO getRefundVO(long refundId, long weiid) {
	RefundVO result = new RefundVO();
	TOrderBackTotal refuBackTotal = orderBase.getEntity(TOrderBackTotal.class, refundId);
	if (refuBackTotal != null) {
	    if (!ObjectUtil.isEmpty(refuBackTotal.getTransNo()) && !ObjectUtil.isEmpty(refuBackTotal.getTransName())) {
		String logistics = logisticsService.getInfoByName(refuBackTotal.getTransNo(), refuBackTotal.getTransName());
		List<String> logs = getLogisticsDetails(logistics);
		LogisticsVO logvo = new LogisticsVO();
		logvo.setLogisticsNo(refuBackTotal.getTransNo());
		logvo.setLongisticsName(refuBackTotal.getTransName());
		logvo.setTailList(logs);
		result.setLogistics(logvo);
	    }
	    result.setBackOrder(refundId);
	    result.setSupOrderID(refuBackTotal.getSupplierOrderId());
	    result.setRefundTime(refuBackTotal.getCreateTime().toString());
	    result.setRefundType(Integer.valueOf(refuBackTotal.getBackStatus()));
	    if (refuBackTotal.getFlowId() != null)
		result.setProcessId(ParseHelper.toLong(refuBackTotal.getFlowId()));
	    result.setRefundPrice(refuBackTotal.getRefundAmout());
	    result.setReason(refuBackTotal.getBReason());
	    result.setReasonNo(refuBackTotal.getSReason());
	    result.setReasonOkwei(refuBackTotal.getAReason());
	    result.setRefundWay(refuBackTotal.getType());
	    List<TRefundImg> imgs = orderBase.getRefundImgs(refundId);
	    if (imgs != null && imgs.size() > 0) {
		List<String> imgStrings = new ArrayList<String>();
		for (TRefundImg ss : imgs) {
		    imgStrings.add(ImgDomain.GetFullImgUrl(ss.getRefundImg()));
		}
		result.setProImages(imgStrings);
	    }
	    List<OProductOrder> proList = orderBase.getProductsByRefundId(refundId);
	    result.setProList(getSupplyOrderProducts(refuBackTotal.getSupplierOrderId(), proList));
	    result.setOrderType(refuBackTotal.getOistatus());
	    List<OOrderFlow> flows = orderBase.getOrderFlows(refuBackTotal.getSupplierOrderId());
	    if (flows != null && flows.size() > 0) {
		List<String> flowStrings = new ArrayList<String>();
		for (OOrderFlow oo : flows) {
		    flowStrings.add(oo.getOperateContent());
		}
		result.setOrderrecord(flowStrings);
	    }
	    // if(refuBackTotal.getSaid()!=null&&refuBackTotal.getSaid().longValue()>0)
	    // {
	    // UCustomerAddr addr=orderBase.getEntity(UCustomerAddr.class,
	    // refuBackTotal.getSaid());
	    // }
	}
	return result;
    }

    public MsgResult editRefundState(long supplyweiid, long refundid, boolean isAgree, String content) {
	MsgResult result = new MsgResult();
	TOrderBackTotal refund = orderBase.getEntity(TOrderBackTotal.class, refundid);
	if (refund != null) {
	    // logger.error("流程请求包体：flowid："+
	    // refund.getFlowId()+"供应商id："+supplyweiid);
	    ReturnModel reModel = FlowMethod.QueryTask(refund.getFlowId().toString(), supplyweiid);
	    if (reModel != null) {

		String task = reModel.getBasemodle().toString();
		// logger.error("流程返回的包："+task);
		JSONObject jo = JSONObject.fromObject(task);
		if (isAgree) {
		    if (refund.getType() == 1) {
			// 退款
			try {
			    confirmRefundService.refundPenny(supplyweiid, refund);
			    if ("".equals(content))
				content = "卖家同意退款";

			    if (FlowMethod.ActionFlow(jo.getString("taskId"), jo.getLong("taskUser"), "agree", content)) {
				result.setState(1);
			    } else {
				result.setState(-1);
				result.setMsg("操作不成功303");
				return result;
			    }

			} catch (Exception e) {
			    result.setState(-1);
			    result.setMsg(e.getMessage());
			}

		    } else {
			if ("".equals(content))
			    content = "卖家同意退款";
			if (FlowMethod.ActionFlow(jo.getString("taskId"), jo.getLong("taskUser"), "agree", content)) {
			    result.setState(1);
			} else {
			    result.setState(-1);
			    result.setMsg("操作不成功304");
			    return result;
			}
		    }

		} else {
		    if ("".equals(content))
			content = "卖家不同意退款";
		    if (FlowMethod.ActionFlow(jo.getString("taskId"), jo.getLong("taskUser"), "disagree", content)) {
			result.setState(1);
		    } else {
			result.setState(-1);
			result.setMsg("操作不成功305");
			return result;
		    }
		}
	    }
	}
	return result;
    }

    public MsgResult deleteSupplyOrder(long supplyweiid, String supplyOrderid) {
	MsgResult result = new MsgResult();
	try {
	    OSupplyerOrder order = orderBase.getEntity(OSupplyerOrder.class, supplyOrderid);
	    if (order != null) {
		if (order.getSupplyerId() != null && supplyweiid == order.getSupplyerId().longValue()) {
		    order.setSellerDel(Short.parseShort("2"));
		    super.update(order);
		    result.setState(1);
		    result.setMsg("删除成功");
		    return result;
		} else {
		    result.setMsg("没有此操作权限");
		    result.setState(-1);
		    return result;
		}
	    }
	} catch (Exception e) {
	    // TODO: handle exception
	    result.setMsg(e.getMessage());
	    result.setState(-2);
	}
	return result;
    }

    public MsgResult saveRefundOrderAddr(long refundid, long weiid, long addrid) {
	MsgResult result = new MsgResult();
	try {
	    if (refundid <= 0 || weiid <= 0 || addrid <= 0) {
		result.setState(-1);
		result.setMsg("参数有误");
		return result;
	    }
	    TOrderBackTotal refundBackTotal = orderBase.getEntity(TOrderBackTotal.class, refundid);
	    if (refundBackTotal != null) {
		if (refundBackTotal.getSellerId() != null && weiid == refundBackTotal.getSellerId()) {
		    UCustomerAddr addrs = orderBase.getEntity(UCustomerAddr.class, (int) addrid);
		    if (addrs == null) {
			result.setState(-1);
			result.setMsg("参数有误");
			return result;
		    }
		    // 添加地址
		    OOrderAddr oadd = new OOrderAddr();
		    oadd.setWeiId(weiid);
		    oadd.setCity(addrs.getCity());
		    oadd.setCreateTime(new Date());
		    oadd.setDetailAddr(addrs.getDetailAddr());
		    oadd.setDistrict(addrs.getDistrict());
		    oadd.setMobilePhone(addrs.getMobilePhone());
		    oadd.setProvince(addrs.getProvince());
		    oadd.setQq(addrs.getQq());
		    oadd.setReceiverName(addrs.getReceiverName());
		    oadd.setStreet(addrs.getStreet());
		    super.add(oadd);
		    refundBackTotal.setSaid(oadd.getOrderAddrId());
		    refundBackTotal.setBackStatus((short) 1);
		    super.update(refundBackTotal);
		    result.setState(1);
		    result.setMsg("成功");
		} else {
		    result.setState(-1);
		    result.setMsg("非法操作");
		}
	    } else {
		result.setState(-1);
		result.setMsg("退款订单不存在");
	    }
	} catch (Exception e) {
	    // TODO: handle exception
	    result.setState(-1);
	    result.setMsg(e.getMessage());
	}
	return result;
    }

    public MsgResult saveRefundRecieved(long refundid, long weiid) {
	MsgResult result = new MsgResult();
	if (refundid <= 0 || weiid <= 0) {
	    result.setState(-1);
	    result.setMsg("参数有误");
	    return result;
	}
	try {
	    TOrderBackTotal refundBackTotal = orderBase.getEntity(TOrderBackTotal.class, refundid);
	    if (refundBackTotal != null) {
		if (refundBackTotal.getSellerId() != null && weiid == refundBackTotal.getSellerId()) {
		    if (ReFundStatusEnum.BuyerFaHuo.toString().equals(refundBackTotal.getBackStatus().toString())) {
			try {
			    confirmRefundService.refundPenny(weiid, refundBackTotal);
			    result.setState(1);
			    result.setMsg("成功");
			} catch (Exception e) {
			    result.setState(-1);
			    result.setMsg(e.getMessage());
			}

		    } else {
			result.setState(-1);
			result.setMsg("非法操作");
		    }
		}
	    } else {
		result.setState(-1);
		result.setMsg("退款订单不存在");
	    }

	    if (refundBackTotal != null) {
		if (refundBackTotal.getSellerId() != null && weiid == refundBackTotal.getSellerId()) {
		    refundBackTotal.setBackStatus((short) 6);
		    super.update(refundBackTotal);
		    result.setState(1);
		    result.setMsg("成功");
		} else {
		    result.setState(-1);
		    result.setMsg("非法操作");
		}
	    } else {
		result.setState(-1);
		result.setMsg("退款订单不存在");
	    }
	} catch (Exception e) {
	    // TODO: handle exception
	    result.setMsg(e.getMessage());
	    result.setState(-2);
	}
	return result;
    }

    public MsgResult bookOrderComplete(long weiid, String supplyOrderid) {
	MsgResult result = new MsgResult();
	if (ObjectUtil.isEmpty(supplyOrderid) || weiid <= 0) {
	    result.setState(-1);
	    result.setMsg("参数有误");
	    return result;
	}
	try {
	    List<OPayOrder> order_list = orderBase.getOPayOrderlistBySupplyOrderIds(new String[] { supplyOrderid });
	    OSupplyerOrder supplyerOrder = orderBase.getEntity(OSupplyerOrder.class, supplyOrderid);
	    if (order_list != null && order_list.size() > 0 && supplyerOrder != null) {
		if (weiid != supplyerOrder.getSupplyerId()) {
		    result.setState(-1);
		    result.setMsg("订单不匹配");
		    return result;
		}
		OPayOrder headOrder = null, tailOrder = null;
		for (OPayOrder oo : order_list) {
		    if (Short.parseShort(OrderTypeEnum.BookHeadOrder.toString()) == oo.getTypeState()) {
			headOrder = oo;
		    } else if (Short.parseShort(OrderTypeEnum.BookTailOrder.toString()) == oo.getTypeState()) {
			tailOrder = oo;
		    } else {
			result.setState(-1);
			result.setMsg("不符合要求的订单类型");
			return result;
		    }
		}
		double totalprice = supplyerOrder.getTotalPrice() + ParseHelper.getDoubleDefValue(supplyerOrder.getPostage());
		if (headOrder != null && tailOrder != null && Short.parseShort(OrderStatusEnum.Payed.toString()) == headOrder.getState()) {
		    double amount = totalprice * 0.97;
		    UTradingDetails suppDetails = new UTradingDetails();
		    suppDetails.setAmount(amount);
		    suppDetails.setCreateTime(new Date());
		    suppDetails.setLtwoType((short) 0);
		    suppDetails.setOrderNo(tailOrder.getPayOrderId());
		    suppDetails.setSupplyOrder(supplyOrderid);
		    suppDetails.setState(Short.parseShort(AmountStatusEnum.weiFangKuan.toString()));
		    suppDetails.setType(Short.parseShort(AmountTypeEnum.supplyPrice.toString()));
		    suppDetails.setWeiId(weiid);
		    suppDetails.setSurplusAmout(amount);
		    super.add(suppDetails);
		    // 跟新钱包
		    orderBase.updateWallet(weiid);
		    if (Short.parseShort(OrderStatusEnum.Accepted.toString()) == supplyerOrder.getState()) {// 已收货
			supplyerOrder.setState(Short.parseShort(OrderStatusEnum.Completed.toString()));
			super.update(supplyerOrder);
		    } else {
			supplyerOrder.setState(Short.parseShort(OrderStatusEnum.Payed.toString()));
			super.update(supplyerOrder);
		    }
		    result.setState(1);
		    result.setMsg("操作成功");

		} else {
		    result.setState(-1);
		    result.setMsg("不符合要求的订单类型303");
		    return result;
		}
	    }
	} catch (Exception e) {
	    // handle exception
	    result.setMsg(e.getMessage());
	    result.setState(-2);
	}
	return result;
    }

    public MsgResult delivery(String supplyOrderid, long weiid, String dcomanpyNo, String deliveryCompany, String deliveryOrderNo) {
	MsgResult result = new MsgResult();
	OSupplyerOrder order = orderBase.getEntity(OSupplyerOrder.class, supplyOrderid);
	if (order != null && Short.parseShort(OrderStatusEnum.Refunding.toString()) == order.getState()) {
	    List<TOrderBackTotal> list = orderBase.getTOrderBackTotals(order.getSupplierOrderId());
	    if (list != null && list.size() > 0) {
		for (TOrderBackTotal too : list) {
		    // logger.error("流程请求包体：flowid："+
		    // too.getFlowId().toString()+"供应商id："+order.getSupplyerId());
		    ReturnModel reModel = FlowMethod.QueryTask(too.getFlowId().toString(), order.getSupplyerId());
		    if (reModel != null && reModel.getStatu().equals(ReturnStatus.Success)) {
			String task = reModel.getBasemodle().toString();

			JSONObject jo = JSONObject.fromObject(task);
			if (FlowMethod.ActionFlow(jo.getString("taskId"), jo.getLong("taskUser"), "disagree", "卖家已经发货")) {
			    List<OProductOrder> opolist = orderBase.getProductsByRefundId(too.getBackOrder());
			    for (OProductOrder opo : opolist) {
				opo.setState(Short.parseShort(OrderStatusEnum.Deliveried.toString()));
				super.update(opo);
			    }
			    OSupplyerOrder oso = orderBase.getEntity(OSupplyerOrder.class, order.getSupplierOrderId());
			    oso.setState(too.getOistatus());// Short.parseShort(OrderStatusEnum.Deliveried.toString())
			    super.update(oso);
			} else {
			    result.setState(-1);
			    result.setMsg("退款流程操作失败！");
			    return result;
			}
		    }
		}
	    }
	}
	return sendGoods(supplyOrderid, weiid, dcomanpyNo, deliveryCompany, deliveryOrderNo);

    }

    /**
     * 获取订单状态名称
     * 
     * @param state
     * @return
     */
    public String getOrderStateName(OrderStatusEnum state) {
	String name = "";
	if (state == null)
	    return name;
	switch (state) {
	    case Nopay:
		name = "未支付";
		break;
	    case Payed:
		name = "已付款";
		break;
	    case Deliveried:
		name = "已发货";
		break;
	    case Accepted:
		name = "已收货";
		break;
	    case Completed:
		name = "交易完成";
		break;
	    case Canceled:
		name = "已取消";
		break;
	    case Tovoided:
		name = "已过期";
		break;
	    case Refunding:
		name = "退款中";
		break;
	    case Refunded:
		name = "已退款";
		break;
	    case WaitSure:
		name = "等待确认";
		break;
	    case ApplyCancel:
		name = "申请取消";
		break;
	    case Sured:
		name = "已确认";
		break;
	    case Rejected:
		name = "已拒绝";
		break;
	    case PayedDeposit:
		name = "已支付定金";
		break;
	    default:
		break;
	}
	return name;
    }

    public String getPayTypeName(PayTypeEnum paytype) {
	if (paytype == null)
	    return "";
	switch (paytype) {
	    case DinPay:
		return "智付支付";
	    case TenPay:
		return "财付通";
	    case ChinaPay:
		return "银联";
	    case WxPay:
		return "微信支付";
	    case SinaPay:
		return "新浪支付";
	    case LLPay:
		return "连连支付";
	    case WeiWallet:
		return "钱包支付";
	    default:
		return "其他支付";
	}
    }

    /**
     * 处理物流信息
     * 
     * @param wuliu
     * @return
     */
    private List<String> getLogisticsDetails(String wuliu) {
	List<String> strList = new ArrayList<String>();
	if (wuliu != null && wuliu != "") {
	    JSONObject json = JSONObject.fromObject(wuliu);
	    if (json != null) {
		JSONArray array = json.getJSONArray("data");
		if (array != null && array.size() > 0) {
		    for (int i = 0; i < array.size(); i++) {
			JSONObject obj = array.getJSONObject(i);
			strList.add(obj.getString("time") + "  " + obj.getString("context"));
		    }
		}
	    }
	}
	return strList;
    }

    /**
     * 预定单状态
     * 
     * @param orderState
     * @param tailPayType
     * @return
     */
    public int getSupplyerBookState(int orderState, Short tailPayType) {
	OrderStatusEnum state = null;
	for (OrderStatusEnum ee : OrderStatusEnum.values()) {
	    if (orderState == Integer.parseInt(ee.toString()))
		state = ee;
	}
	if (state == null)
	    return orderState;
	switch (state) {
	    case WaitSure:
		return Short.parseShort(SupplyBookOrderTypeEnum.Waitting.toString());
	    case Sured:
		return Short.parseShort(SupplyBookOrderTypeEnum.Sured.toString());
	    case Completed:
		return Short.parseShort(SupplyBookOrderTypeEnum.Complete.toString());
	    case PayedDeposit:
		if (tailPayType != null && tailPayType == 1)
		    return Short.parseShort(SupplyBookOrderTypeEnum.DaiFahuo.toString());
		return Short.parseShort(SupplyBookOrderTypeEnum.NopayTail.toString());
	    case Payed:
		return Short.parseShort(SupplyBookOrderTypeEnum.DaiFahuo.toString());
	    default:
		return orderState;
	}
    }

    /**
     * 预定单状态
     * 
     * @param orderState
     * @param tailPayType
     * @return
     */
    public String getSupplyerBookStateName(int orderState, Short tailPayType) {
	OrderStatusEnum state = null;
	for (OrderStatusEnum ee : OrderStatusEnum.values()) {
	    if (orderState == Integer.parseInt(ee.toString()))
		state = ee;
	}
	if (state == null)
	    return "";
	switch (state) {
	    case WaitSure:
		return "待确认";// Short.parseShort(SupplyBookOrderTypeEnum.Waitting.toString());
	    case Sured:
		return "已确认";// Short.parseShort(SupplyBookOrderTypeEnum.Sured.toString());
	    case Completed:
		return "已完成";// Short.parseShort(SupplyBookOrderTypeEnum.Complete.toString());
	    case PayedDeposit:
		if (tailPayType != null && tailPayType == 1)
		    return "待发货";// Short.parseShort(SupplyBookOrderTypeEnum.DaiFahuo.toString());
		return "待付尾款";// Short.parseShort(SupplyBookOrderTypeEnum.NopayTail.toString());
	    case Payed:
		return "待发货";// Short.parseShort(SupplyBookOrderTypeEnum.DaiFahuo.toString());
	    case Tovoided:
		return "已过期";
	    default:
		return "";// orderState;
	}
    }

    public String getpayWayStr(Short ps) {
	if (ps == null) {
	    return "其他";
	}
	PayTypeEnum payType = null;
	for (PayTypeEnum pt : PayTypeEnum.values()) {
	    if (pt.toString().equals(ps.toString())) {
		payType = pt;
	    }
	}
	if (payType == null) {
	    return "其他";
	}
	switch (payType) {
	    case OtherPay:
		return "其他";
	    case DinPay:
		return "智付支付";
	    case TenPay:
		return "财付通";
	    case ChinaPay:
		return "银联";
	    case WxPay:
		return "微信支付";
	    case SinaPay:
		return "新浪支付";
	    case LLPay:
		return "连连支付";
	    case WeiWallet:
		return "微店钱包";
	    default:
		return "其他";
	}
    }
}
