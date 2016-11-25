package com.okwei.myportal.service.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.okwei.bean.domain.OBookAssist;
import com.okwei.bean.domain.OOrderAddr;
import com.okwei.bean.domain.OOrderFlow;
import com.okwei.bean.domain.OPayOrder;
import com.okwei.bean.domain.OPayOrderLog;
import com.okwei.bean.domain.OProductOrder;
import com.okwei.bean.domain.OSupplyerOrder;
import com.okwei.bean.domain.TOrderBackTotal;
import com.okwei.bean.domain.TRefundImg;
import com.okwei.bean.domain.UBrandSupplyer;
import com.okwei.bean.domain.UShopInfo;
import com.okwei.bean.domain.USupplyer;
import com.okwei.bean.domain.UTradingDetails;
import com.okwei.bean.domain.UWeiCoinLog;
import com.okwei.bean.domain.UWeiSeller;
import com.okwei.bean.domain.UYunSupplier;
import com.okwei.bean.enums.BookPayTypeEnum;
import com.okwei.bean.enums.OrderStatusEnum;
import com.okwei.bean.enums.OrderTypeEnum;
import com.okwei.bean.enums.PayTypeEnum;
import com.okwei.bean.enums.TailPayTypeEnum;
import com.okwei.bean.enums.UserAmountType;
import com.okwei.bean.vo.ReturnModel;
import com.okwei.bean.vo.ReturnStatus;
import com.okwei.bean.vo.TransVO;
import com.okwei.bean.vo.order.ParamOrderSearch;
import com.okwei.common.Limit;
import com.okwei.common.PageResult;
import com.okwei.dao.order.IBasicOrdersDao;
import com.okwei.flow.FlowMethod;
import com.okwei.myportal.bean.enums.ReFundStatusEnum;
import com.okwei.myportal.bean.util.SendSMSByMobile;
import com.okwei.myportal.bean.vo.BuyOrderListVO;
import com.okwei.myportal.bean.vo.LogisticsVO;
import com.okwei.myportal.bean.vo.OrderCountVO;
import com.okwei.myportal.bean.vo.OrderDetailsVO;
import com.okwei.myportal.bean.vo.PreOrderListVO;
import com.okwei.myportal.bean.vo.ProductOrderVO;
import com.okwei.myportal.bean.vo.RefundVO;
import com.okwei.myportal.bean.vo.ResultMsg;
import com.okwei.myportal.dao.IOrderManageDAO;
import com.okwei.myportal.dao.IProductDAO;
import com.okwei.myportal.service.IOrderManageService;
import com.okwei.service.IBaseCommonService;
import com.okwei.service.ILogisticsService;
import com.okwei.service.IRegionService;
import com.okwei.service.impl.BaseService;
import com.okwei.util.DateUtils;
import com.okwei.util.ImgDomain;
import com.okwei.util.ParseHelper;
import com.okwei.util.RedisUtil;

@Service
public class OrderManageService extends BaseService implements IOrderManageService {

	@Autowired
	private IOrderManageDAO orderManageDAO;

	@Autowired
	private IRegionService regionService;

	@Autowired
	private ILogisticsService logisticsService;

	@Autowired
	private IBasicOrdersDao basicOrdersDao;
	@Autowired
	private IBaseCommonService shopService;


	private Log logger = LogFactory.getLog(this.getClass());

	@Override
	public PageResult<BuyOrderListVO> getOrderList(Long weiid, Limit limit, ParamOrderSearch param) {

		PageResult<OSupplyerOrder> page = basicOrdersDao.find_BuyerOSupplyerOrder(param, weiid, limit);
		if (page == null) {
			return new PageResult<BuyOrderListVO>();
		}
		List<OSupplyerOrder> list = page.getList();
		// TODO 预定订单列表
//		String[] supplyOrderIds1 = new String[list.size()];
		if (list != null && list.size() > 0) {
			// 获取订单号集
			String[] orderid = new String[list.size()];
			// 获取供应商集
			List<Long> supidsList=new ArrayList<Long>();
//			Long[] supid = new Long[list.size()];
			for (int i = 0; i < list.size(); i++) {
				supidsList.add( list.get(i).getSupplyerId());
				orderid[i] = list.get(i).getSupplierOrderId();
//				supid[i] = list.get(i).getSupplyerId();
//				if (list.get(i).getOrderType().intValue() == ParseHelper.toInt(OrderTypeEnum.Puhuo.toString()) || list.get(i).getOrderType().intValue() == ParseHelper.toInt(OrderTypeEnum.BookOrder.toString())) {
//					supplyOrderIds1[i] = list.get(i).getSupplierOrderId();
//				}
			}
			List<OProductOrder> prolist = orderManageDAO.getOrderProduct(orderid);

			// 获取所有的退款ID
			List<Long> refundID = new ArrayList<Long>();
			if (prolist != null && prolist.size() > 0) {
				for (OProductOrder pro : prolist) {
					Long refID = pro.getBackOrder();
					if (refID != null && refID.longValue() > 0) {
						refundID.add(refID);
					}
				}
			}
			List<TOrderBackTotal> refundList = null;
			if (refundID.size() > 0) {
				refundList = orderManageDAO.getRefundList((Long[]) refundID.toArray(new Long[refundID.size()]));
			}

			// 铺货单辅助信息
			List<OBookAssist> assistlist = orderManageDAO.getBookAssistsList(orderid);
//			List<USupplyer> suplist = orderManageDAO.getSupList(supid);
			List<UShopInfo> suplist=shopService.find_shopinfolist(supidsList);
			// 返回的结果集
			List<BuyOrderListVO> voList = new ArrayList<BuyOrderListVO>();
			DecimalFormat d = new DecimalFormat("0.00");
			for (OSupplyerOrder order : list) {
				BuyOrderListVO vo = new BuyOrderListVO();
				vo.setOrderNo(order.getSupplierOrderId());
				vo.setOrderType(order.getOrderType());
				if (suplist != null && suplist.size() > 0) {
					for (UShopInfo sup : suplist) {
						if (order.getSupplyerId().equals(sup.getWeiId())) {
							vo.setSupplyerName(sup.getShopName());
							break;
						}
					}
				}
				// 显示付款金额状态
				List<String> amountState = new ArrayList<String>();
				short state = order.getState();
				if (order.getOrderType().intValue() == ParseHelper.toShort(OrderTypeEnum.BookOrder.toString()) || order.getOrderType().intValue() == ParseHelper.toShort(OrderTypeEnum.Puhuo.toString())) {

					/*
					 * for (OBookAssist bAssist : assistlist) { if
					 * (order.getSupplierOrderId
					 * ().equals(bAssist.getSupplierOrderId())) { // 是否全额付款 if
					 * (bAssist
					 * .getBookPayType().equals(Short.parseShort(BookPayTypeEnum
					 * .Full.toString()))) { amountState.add("全额支付"); } else {
					 * amountState.add("定金：¥" +d.format(bAssist.getAmount()));
					 * Double finalprice = order.getTotalPrice() +
					 * order.getPostage() - bAssist.getAmount(); if
					 * (bAssist.getTailPayType
					 * ()==null||bAssist.getTailPayType().
					 * equals(Short.parseShort
					 * (TailPayTypeEnum.predelivery.toString()))) {
					 * amountState.add("发货前支付尾款：¥" + d.format(finalprice)); }
					 * else { amountState.add("发货后支付尾款：¥" +
					 * d.format(finalprice)); } } amountState.add("发货时间：" +
					 * DateUtils.format(bAssist.getPreSendTime(), null));
					 * 
					 * } }
					 */
					// 查找支付订单
					List<OPayOrder> poList = orderManageDAO.getPayOrderList(orderid);
					for (OBookAssist bAssist : assistlist) {
						if (order.getSupplierOrderId().equals(bAssist.getSupplierOrderId())) {
							// 是否全额付款
							if (bAssist.getBookPayType().equals(Short.parseShort(BookPayTypeEnum.Full.toString()))) {
								vo.setFullPrice(true);
								amountState.add("全额支付");
							} else {
								vo.setFullPrice(false);
								amountState.add("定金：¥" + d.format(bAssist.getAmount()));
								Double finalprice = order.getTotalPrice() + order.getPostage() - bAssist.getAmount();
								if (null != bAssist.getTailPayType() && bAssist.getTailPayType().equals(Short.parseShort(TailPayTypeEnum.predelivery.toString()))) {
									amountState.add("发货前支付尾款：¥" + d.format(finalprice));
								} else {
									amountState.add("发货后支付尾款：¥" + d.format(finalprice));
								}
								vo.setDepositprice(bAssist.getAmount());
								// TODO 尾款通过订单总价-首款，以后看是否要查PayOrder
								vo.setFinalprice(finalprice);
							}
							amountState.add("发货时间：" + DateUtils.format(bAssist.getPreSendTime(), null));
							vo.setTailPayType(bAssist.getTailPayType() == null ? -1 : bAssist.getTailPayType());
							vo.setDeliveryTimeStr(bAssist.getPreSendTime() == null ? "" : DateUtils.formatDateTime(bAssist.getPreSendTime()));
							// 查询支付订单号
							OrderTypeEnum typeEnum = null;
							OrderTypeEnum puHuoTypeEnum = null;
							if (state == Short.parseShort(OrderStatusEnum.Sured.toString())) {
								// 已确定
								if (bAssist.getBookPayType().equals(Short.parseShort(BookPayTypeEnum.Full.toString()))) {
									// 全额支付
									typeEnum = OrderTypeEnum.BookFullOrder;
									puHuoTypeEnum = OrderTypeEnum.PuhuoFull;
								} else {
									// 部分支付
									typeEnum = OrderTypeEnum.BookHeadOrder;
									puHuoTypeEnum = OrderTypeEnum.PuhuoHeader;
								}

							} else if (state == Short.parseShort(OrderStatusEnum.PayedDeposit.toString())) {
								// 已支付定金
								if (bAssist.getTailPayType().equals(Short.parseShort(TailPayTypeEnum.predelivery.toString()))) {
									typeEnum = OrderTypeEnum.BookTailOrder;
									puHuoTypeEnum = OrderTypeEnum.PuhuoTail;
								}
							} else if (state == Short.parseShort(OrderStatusEnum.Accepted.toString())) {
								// 已收货
								if (bAssist.getTailPayType().equals(Short.parseShort(TailPayTypeEnum.afterdelivery.toString()))) {
									typeEnum = OrderTypeEnum.BookTailOrder;
									puHuoTypeEnum = OrderTypeEnum.PuhuoTail;
								}
							}
							if (typeEnum != null) {

								if (poList != null && poList.size() > 0) {
									for (OPayOrder payOrder : poList) {
										if (order.getSupplierOrderId().equals(payOrder.getSupplierOrder()) && payOrder.getTypeState().equals(Short.parseShort(typeEnum.toString()))) {
											vo.setPayOrderNo(payOrder.getPayOrderId());
											break;
										}
										if (order.getSupplierOrderId().equals(payOrder.getSupplierOrder()) && payOrder.getTypeState().equals(Short.parseShort(puHuoTypeEnum.toString()))) {
											vo.setPayOrderNo(payOrder.getPayOrderId());
											break;
										}
									}
								}
							}
							break;
						}
					}
				}
				vo.setAmountState(amountState);

				vo.setPayContent(amountState);
				vo.setSupplyId(order.getSupplyerId());
				vo.setCreateTimeStr(order.getOrderTime() == null ? "" : DateUtils.formatDateTime(order.getOrderTime()));
				vo.setOrderState(order.getState());
				Double totalPrice = ParseHelper.getDoubleDefValue(order.getTotalPrice()) + ParseHelper.getDoubleDefValue(order.getPostage());
				vo.setTotalPrice(ParseHelper.toDouble(d.format(totalPrice)));
				vo.setPostPrice(order.getPostage());
				// 获取订单的产品列表
				List<ProductOrderVO> tempList = new ArrayList<ProductOrderVO>();
				if (prolist != null && prolist.size() > 0) {
					for (OProductOrder pro : prolist) {
						if (order.getSupplierOrderId().equals(pro.getSupplierOrderId())) {
							ProductOrderVO voPro = new ProductOrderVO();
							voPro.setCount(pro.getCount());
							voPro.setProductState(pro.getState());
							voPro.setProperty(returnProperty(pro.getStyleDes()));
							voPro.setProductId(pro.getProductId());
							voPro.setProductTitle(pro.getProdcutTitle());
							// 处理图片
							voPro.setProductImg(ImgDomain.GetFullImgUrl(pro.getProductImg(), 24));
							voPro.setPrice(pro.getPrice());
							voPro.setBuyerId(pro.getBuyerId());
							// 退款状态
							voPro.setRefundState(-1);// -1代表没有申请记录
							if (pro.getBackOrder() != null && pro.getBackOrder().longValue() > 0) {
								if (refundList != null && refundList.size() > 0) {
									for (TOrderBackTotal ob : refundList) {
										if (pro.getBackOrder().equals(ob.getBackOrder())) {
											voPro.setRefundId(ob.getBackOrder());
											voPro.setRefundState(ob.getBackStatus());
											// 将没有完成的退款订单ID
											if (!(ob.getBackStatus().equals(Short.parseShort(ReFundStatusEnum.GysQueRen.toString())) && ob.getBackStatus().equals(Short.parseShort(ReFundStatusEnum.ShenQingClose.toString())))) {
												vo.setRefundId(ob.getBackOrder());
											}
											break;
										}
									}
								}
							}
							tempList.add(voPro);
						}
					}
				}
				vo.setProList(tempList);
				voList.add(vo);
			}
			return new PageResult<BuyOrderListVO>(page.getTotalCount(), limit, voList);
		}
		return new PageResult<BuyOrderListVO>();
	}

	/**
	 * 返回订单产品已处理好的属性字符串
	 * 
	 * @param property
	 * @return
	 */
	public String returnProperty(String property) {
		String result = "";
		if (property != null && property != "") {
			/*
			 * String[] strs = property.split("\\|"); if (strs.length > 1) {
			 * String[] str1 = strs[0].split(":"); String[] str2 =
			 * strs[1].split(":"); if (str1.length > 1) { result = str1[1]; }
			 * else { result = str1[0]; } result += " "; if (str2.length > 1) {
			 * result += str2[1]; } else { result += str2[0]; } } else { result
			 * = property.replaceAll("/", " "); } result = "属性：" + result;
			 */
			result = property.replaceAll("\\|", "<br />").replaceAll("-1:-1", "默认：默认");
		}
		return result;
	}

	@Override
	public OrderCountVO getOrderCount(Long weiid, int orderType) {
		int retailCount = 0, wholesaleCount = 0, reserveCount = 0, waitPayCount = 0, waitShipmentsCount = 0, waitReceivCount = 0, refundCount = 0, completeCount = 0, confirmCount = 0;
		List<OSupplyerOrder> list = orderManageDAO.getOrderList(weiid);
		if (list != null && list.size() > 0) {
			// 用来查询已支付定金的订单号
			List<String> supOrderID = new ArrayList<String>();
			List<OSupplyerOrder> newList = new ArrayList<OSupplyerOrder>();
			for (OSupplyerOrder order : list) {
				int type = order.getOrderType();
				short state = order.getState();
				if (type == Integer.parseInt(OrderTypeEnum.Pt.toString()) || type == Integer.parseInt(OrderTypeEnum.BatchOrder.toString())) {
					retailCount++;
					// 零售订单
					if (orderType == 1) {
						if (state == Short.parseShort(OrderStatusEnum.Nopay.toString())) {
							waitPayCount++;
						} else if (state == Short.parseShort(OrderStatusEnum.Payed.toString())) {
							waitShipmentsCount++;
						} else if (state == Short.parseShort(OrderStatusEnum.Deliveried.toString())) {
							waitReceivCount++;
						} else if (state == Short.parseShort(OrderStatusEnum.Refunding.toString())) {
							refundCount++;
						} else if (state == Short.parseShort(OrderStatusEnum.Completed.toString())) {
							completeCount++;
						}
					}
				} else if (type == Integer.parseInt(OrderTypeEnum.BatchWholesale.toString())) {
					wholesaleCount++;
					// 批发单
					if (orderType == 2) {
						if (state == Short.parseShort(OrderStatusEnum.Nopay.toString())) {
							waitPayCount++;
						} else if (state == Short.parseShort(OrderStatusEnum.Payed.toString())) {
							waitShipmentsCount++;
						} else if (state == Short.parseShort(OrderStatusEnum.Deliveried.toString())) {
							waitReceivCount++;
						} else if (state == Short.parseShort(OrderStatusEnum.Refunding.toString())) {
							refundCount++;
						} else if (state == Short.parseShort(OrderStatusEnum.Completed.toString())) {
							completeCount++;
						}
					}
				} else if (type == Integer.parseInt(OrderTypeEnum.BookOrder.toString())) {
					reserveCount++;
					// 预订单
					if (orderType == 3) {
						if (state == Short.parseShort(OrderStatusEnum.WaitSure.toString())) {
							confirmCount++;
						} else if (state == Short.parseShort(OrderStatusEnum.Sured.toString())) {
							waitPayCount++;
						} else if (state == Short.parseShort(OrderStatusEnum.Payed.toString())) {
							waitShipmentsCount++;
						} else if (state == Short.parseShort(OrderStatusEnum.Deliveried.toString())) {
							waitReceivCount++;
						} else if (state == Short.parseShort(OrderStatusEnum.Accepted.toString())) {
							supOrderID.add(order.getSupplierOrderId());
							newList.add(order);
						} else if (state == Short.parseShort(OrderStatusEnum.PayedDeposit.toString())) {
							// 如果是已支付定金状态，需要确认
							supOrderID.add(order.getSupplierOrderId());
							newList.add(order);
						} else if (state == Short.parseShort(OrderStatusEnum.Completed.toString())) {
							completeCount++;
						}
					}
				}
			}
			// 查询预订单辅助表
			if (supOrderID.size() > 0) {
				List<OBookAssist> baList = orderManageDAO.getBookAssistsList((String[]) supOrderID.toArray(new String[supOrderID.size()]));
				if (baList != null && baList.size() > 0) {
					for (OBookAssist oBookAssist : baList) {
						short type = oBookAssist.getTailPayType();
						for (OSupplyerOrder newOrder : newList) {
							if (oBookAssist.getSupplierOrderId().equals(newOrder.getSupplierOrderId())) {
								if (newOrder.getState().equals(Short.parseShort(OrderStatusEnum.Accepted.toString()))) {
									if (type == Short.parseShort(TailPayTypeEnum.afterdelivery.toString())) {
										waitPayCount++;
									}
								} else if (newOrder.getState().equals(Short.parseShort(OrderStatusEnum.PayedDeposit.toString()))) {
									if (type == Short.parseShort(TailPayTypeEnum.predelivery.toString())) {
										// 发货前付尾款
										waitPayCount++;
									} else if (type == Short.parseShort(TailPayTypeEnum.afterdelivery.toString())) {
										// 发货后付尾款
										waitShipmentsCount++;
									}
								}
								break;
							}
						}
					}
				}
			}
		}
		OrderCountVO voCount = new OrderCountVO();
		voCount.setRetailCount(retailCount);
		voCount.setWholesaleCount(wholesaleCount);
		voCount.setReserveCount(reserveCount);
		voCount.setWaitPayCount(waitPayCount);
		voCount.setWaitShipmentsCount(waitShipmentsCount);
		voCount.setWaitReceivCount(waitReceivCount);
		voCount.setRefundCount(refundCount);
		voCount.setCompleteCount(completeCount);
		voCount.setConfirmCount(confirmCount);
		return voCount;
	}

	@Override
	public PageResult<PreOrderListVO> getPreOrderList(Long weiid, Limit limit, ParamOrderSearch param) {
		// 3为预订单/鋪貨
		PageResult<OSupplyerOrder> page = basicOrdersDao.find_BuyerOSupplyerOrder(param, weiid, limit);
		if (page == null) {
			return new PageResult<PreOrderListVO>();
		}
		List<OSupplyerOrder> list = page.getList();
		if (list != null && list.size() > 0) {
			// 获取订单号集
			String[] orderid = new String[list.size()];
			// 获取供应商集
			Long[] supid = new Long[list.size()];
			for (int i = 0; i < list.size(); i++) {
				OSupplyerOrder temp = list.get(i);
				orderid[i] = temp.getSupplierOrderId();
				supid[i] = temp.getSupplyerId();
			}
			DecimalFormat d = new DecimalFormat("0.00");
			List<OProductOrder> prolist = orderManageDAO.getOrderProduct(orderid);
			List<USupplyer> suplist = orderManageDAO.getSupList(supid);
			// 订单辅助表list
			List<OBookAssist> baList = orderManageDAO.getBookAssistsList(orderid);
			// 返回的结果集
			List<PreOrderListVO> voList = new ArrayList<PreOrderListVO>();
			// 查找支付订单
			List<OPayOrder> poList = orderManageDAO.getPayOrderList(orderid);
			for (OSupplyerOrder order : list) {
				PreOrderListVO vo = new PreOrderListVO();
				String supOrderID = order.getSupplierOrderId();
				vo.setOrderNo(supOrderID);
				if (suplist != null && suplist.size() > 0) {
					for (USupplyer sup : suplist) {
						if (order.getSupplyerId().equals(sup.getWeiId())) {
							vo.setSupplyerName(sup.getCompanyName());
							break;
						}
					}
				}
				vo.setOrderType(order.getOrderType());
				vo.setSupplyId(order.getSupplyerId());
				vo.setCreateTimeStr(order.getOrderTime() == null ? "" : DateUtils.formatDateTime(order.getOrderTime()));
				short state = order.getState();
				vo.setOrderState(state);
				Double totalPrice = ParseHelper.getDoubleDefValue(order.getTotalPrice()) + ParseHelper.getDoubleDefValue(order.getPostage());
				vo.setTotalPrice(ParseHelper.toDouble(d.format(totalPrice)));
				vo.setPostPrice(order.getPostage());
				// 显示付款金额状态
				List<String> amountState = new ArrayList<String>();
				// 获取预订单辅助表信息
				if (baList != null && baList.size() > 0) {

					for (OBookAssist bAssist : baList) {
						if (supOrderID.equals(bAssist.getSupplierOrderId())) {
							// 是否全额付款
							if (bAssist.getBookPayType().equals(Short.parseShort(BookPayTypeEnum.Full.toString()))) {
								vo.setFullPrice(true);
								amountState.add("全额支付");
							} else {
								vo.setFullPrice(false);
								amountState.add("定金：¥" + d.format(bAssist.getAmount()));
								Double finalprice = order.getTotalPrice() + order.getPostage() - bAssist.getAmount();
								if (null != bAssist.getTailPayType() && bAssist.getTailPayType().equals(Short.parseShort(TailPayTypeEnum.predelivery.toString()))) {
									amountState.add("发货前支付尾款：¥" + d.format(finalprice));
								} else {
									amountState.add("发货后支付尾款：¥" + d.format(finalprice));
								}
								vo.setDepositprice(bAssist.getAmount());
								// TODO 尾款通过订单总价-首款，以后看是否要查PayOrder
								vo.setFinalprice(finalprice);
							}
							amountState.add("发货时间：" + DateUtils.format(bAssist.getPreSendTime(), null));
							vo.setTailPayType(bAssist.getTailPayType() == null ? -1 : bAssist.getTailPayType());
							vo.setDeliveryTimeStr(bAssist.getPreSendTime() == null ? "" : DateUtils.formatDateTime(bAssist.getPreSendTime()));
							// 查询支付订单号
							OrderTypeEnum typeEnum = null;
							OrderTypeEnum puHuoTypeEnum = null;
							if (state == Short.parseShort(OrderStatusEnum.Sured.toString())) {
								// 已确定
								if (bAssist.getBookPayType().equals(Short.parseShort(BookPayTypeEnum.Full.toString()))) {
									// 全额支付
									typeEnum = OrderTypeEnum.BookFullOrder;
									puHuoTypeEnum = OrderTypeEnum.PuhuoFull;
								} else {
									// 部分支付
									typeEnum = OrderTypeEnum.BookHeadOrder;
									puHuoTypeEnum = OrderTypeEnum.PuhuoHeader;
								}

							} else if (state == Short.parseShort(OrderStatusEnum.PayedDeposit.toString())) {
								// 已支付定金
								if (bAssist.getTailPayType().equals(Short.parseShort(TailPayTypeEnum.predelivery.toString()))) {
									typeEnum = OrderTypeEnum.BookTailOrder;
									puHuoTypeEnum = OrderTypeEnum.PuhuoTail;
								}
							} else if (state == Short.parseShort(OrderStatusEnum.Accepted.toString())) {
								// 已收货
								if (bAssist.getTailPayType().equals(Short.parseShort(TailPayTypeEnum.afterdelivery.toString()))) {
									typeEnum = OrderTypeEnum.BookTailOrder;
									puHuoTypeEnum = OrderTypeEnum.PuhuoTail;
								}
							}
							if (typeEnum != null) {
								if (poList != null && poList.size() > 0) {
									for (OPayOrder payOrder : poList) {
										if (order.getSupplierOrderId().equals(payOrder.getSupplierOrder()) && payOrder.getTypeState().equals(Short.parseShort(typeEnum.toString()))) {
											vo.setPayOrderNo(payOrder.getPayOrderId());
											break;
										}
										if (order.getSupplierOrderId().equals(payOrder.getSupplierOrder()) && payOrder.getTypeState().equals(Short.parseShort(puHuoTypeEnum.toString()))) {
											vo.setPayOrderNo(payOrder.getPayOrderId());
											break;
										}
									}
								}
							}
							break;
						}
					}
				}
				vo.setAmountState(amountState);
				// 获取订单的产品列表
				List<ProductOrderVO> tempList = new ArrayList<ProductOrderVO>();
				if (prolist != null && prolist.size() > 0) {
					for (OProductOrder pro : prolist) {
						if (supOrderID.equals(pro.getSupplierOrderId())) {
							ProductOrderVO voPro = new ProductOrderVO();
							voPro.setBuyerId(pro.getBuyerId());
							voPro.setPrice(pro.getPrice());
							voPro.setCount(pro.getCount());
							voPro.setProductState(pro.getState());
							voPro.setProperty(returnProperty(pro.getStyleDes()));
							voPro.setProductId(pro.getProductId());
							voPro.setProductTitle(pro.getProdcutTitle());
							voPro.setTotalamount(pro.getPrice() * pro.getCount());
							// 处理图片
							voPro.setProductImg(ImgDomain.GetFullImgUrl(pro.getProductImg(), 24));
							tempList.add(voPro);
						}
					}
				}
				vo.setProList(tempList);
				voList.add(vo);
			}
			return new PageResult<PreOrderListVO>(page.getTotalCount(), limit, voList);
		}
		return new PageResult<PreOrderListVO>();
	}

	/**
	 * 通过支付订单号查询支付方式
	 * 
	 * @param payOrderID
	 * @return
	 */
	private String getPayWay(String payOrderID) {
		String result = "";
		// 查询支付订单
		OPayOrder payOrder = orderManageDAO.getPayOrder(payOrderID);
		if (payOrder != null) {
			// 支付方式
			result = getPayWay(payOrder.getPayType());
		}
		return result;
	}

	/**
	 * 根据支付类型返回支付类型名称
	 * 
	 * @param payType
	 * @return
	 */
	private String getPayWay(Short payType) {
		if (payType == null) {
			return null;
		}
		String result = null;
		if (payType.equals(Short.parseShort(PayTypeEnum.OtherPay.toString()))) {
			result = "其他支付";
		} else if (payType.equals(Short.parseShort(PayTypeEnum.DinPay.toString()))) {
			result = "智付支付";
		} else if (payType.equals(Short.parseShort(PayTypeEnum.TenPay.toString()))) {
			result = "财付通";
		} else if (payType.equals(Short.parseShort(PayTypeEnum.ChinaPay.toString()))) {
			result = "银联";
		} else if (payType.equals(Short.parseShort(PayTypeEnum.WxPay.toString()))) {
			result = "微信支付";
		} else if (payType.equals(Short.parseShort(PayTypeEnum.SinaPay.toString()))) {
			result = "新浪支付";
		} else if (payType.equals(Short.parseShort(PayTypeEnum.LLPay.toString()))) {
			result = "连连支付";
		} else if (payType.equals(Short.parseShort(PayTypeEnum.WeiWallet.toString()))) {
			result = "微店钱包";
		}
		return result;
	}

	@Override
	public OrderDetailsVO getOrderDetails(Long weiid, String supOrderID) {
		OrderDetailsVO result = new OrderDetailsVO();
		OSupplyerOrder supplyerOrder = basicOrdersDao.get(OSupplyerOrder.class, supOrderID); //orderManageDAO.getsSupplyerOrder(weiid, supOrderID);
		if(supplyerOrder==null||(supplyerOrder.getBuyerID().longValue()!=weiid&&supplyerOrder.getSellerWeiId().longValue()!=weiid)){
			return result;
		}
		if (supplyerOrder != null) {
			String hql = " from UWeiCoinLog where supplyOrderId=?  and useType=2 ";
			List<UWeiCoinLog> find = basicOrdersDao.find(hql, supplyerOrder.getSupplierOrderId());
			if (find != null && find.size() > 0) {
				UWeiCoinLog uWeiCoinLog = find.get(0);
				// 使用现金劵金额
				result.setCashCoupon(Math.abs(uWeiCoinLog.getCoinAmount()));
				// 是否使用了现金券 用来true 没用false
				result.setCashCouponflag(true);
			} else {
				result.setCashCoupon(0.0);
				result.setCashCouponflag(false);
			}
			result.setSellerId(supplyerOrder.getSellerWeiId()); 
			result.setBuyerId(supplyerOrder.getBuyerID());
			result.setTotalPrice(supplyerOrder.getTotalPrice());
			result.setOrderNum(supOrderID);
			int orderType = supplyerOrder.getOrderType();
			Short state = supplyerOrder.getState();
			result.setOrderState(state);
			// 下单 支付 发货 收货 结算 时间
			result.setPlaceOrderTimeStr(supplyerOrder.getOrderTime() == null ? "" : DateUtils.formatDateTime(supplyerOrder.getOrderTime()));
			result.setPaymentTimeStr(supplyerOrder.getPayTime() == null ? "" : DateUtils.formatDateTime(supplyerOrder.getPayTime()));
			result.setDeliveryTimeStr(supplyerOrder.getSendTime() == null ? "" : DateUtils.formatDateTime(supplyerOrder.getSendTime()));
			result.setGoodsTimeStr(supplyerOrder.getReciptTime() == null ? "" : DateUtils.formatDateTime(supplyerOrder.getReciptTime()));
			result.setDealTime(supplyerOrder.getBalanceTime() == null ? "" : DateUtils.formatDateTime(supplyerOrder.getBalanceTime()));
			if (orderType == Integer.parseInt(OrderTypeEnum.RetailPTH.toString()) || orderType == Integer.parseInt(OrderTypeEnum.Pt.toString()) || orderType == Integer.parseInt(OrderTypeEnum.BatchOrder.toString())) {
				// 1表示零售订单
				result.setOrderType(1);
				result.setPayWay(getPayWay(supplyerOrder.getPayOrderId()));
				result.setPayOrderNo(supOrderID);
			} else if (orderType == Integer.parseInt(OrderTypeEnum.Jinhuo.toString()) || orderType == Integer.parseInt(OrderTypeEnum.BatchWholesale.toString())) {
				// 2表示批发订单
				result.setOrderType(2);
				result.setPayWay(getPayWay(supplyerOrder.getPayOrderId()));
				result.setPayOrderNo(supOrderID);
			} else if (orderType == Integer.parseInt(OrderTypeEnum.Puhuo.toString()) || orderType == Integer.parseInt(OrderTypeEnum.BookOrder.toString())) {
				// 3表示预订单
				result.setOrderType(3);
				// 查询预订单支付方式（跟零售订单不一样）
				List<OPayOrder> poList = orderManageDAO.getPayOrderList(new String[] { supOrderID });
				if (poList != null && poList.size() > 0) {
					for (OPayOrder oPayOrder : poList) {
						short typeState = oPayOrder.getTypeState();
						if (typeState == Short.parseShort(OrderTypeEnum.BookHeadOrder.toString()) || typeState == Short.parseShort(OrderTypeEnum.BookFullOrder.toString()) || typeState == Short.parseShort(OrderTypeEnum.PuhuoHeader.toString())
								|| typeState == Short.parseShort(OrderTypeEnum.PuhuoFull.toString())

						) {
							result.setPayWay(getPayWay(oPayOrder.getPayType()));
							break;
						}
					}
				}
				// 查询预订辅助表信息
				OBookAssist bAssist = orderManageDAO.getOBookAssist(supOrderID);
				if (bAssist != null) {
					result.setSupplierMessage(bAssist.getRemark());
					// 是否全额付款
					if (bAssist.getBookPayType().equals(Short.parseShort(BookPayTypeEnum.Full.toString()))) {
						result.setIsfullPrice(true);
					} else {
						result.setIsfullPrice(false);
						result.setBookPayType(bAssist.getBookPayType());
						result.setDepositprice(bAssist.getAmount() == null ? 0.0 : bAssist.getAmount());
						result.setPercentage(bAssist.getPersent() == null ? 0 : bAssist.getPersent());
						// TODO 尾款通过订单总价-首款，以后看是否要查PayOrder
						result.setFinalprice(supplyerOrder.getTotalPrice() + supplyerOrder.getPostage() - bAssist.getAmount());
						result.setTailPayType(bAssist.getTailPayType());
					}
					result.setDeliveryTimeStr(DateUtils.format(bAssist.getPreSendTime(), null));
					// 如果是预订单，需要
					OrderTypeEnum typeEnum = null;
					OrderTypeEnum typeEnum1 = null;
					if (state.equals(Short.parseShort(OrderStatusEnum.Sured.toString()))) {
						// 已确定
						if (bAssist.getBookPayType().equals(Short.parseShort(BookPayTypeEnum.Full.toString()))) {
							// 全额支付
							typeEnum = OrderTypeEnum.BookFullOrder;
							typeEnum1 = OrderTypeEnum.PuhuoFull;
						} else {
							// 部分支付
							typeEnum = OrderTypeEnum.BookHeadOrder;
							typeEnum1 = OrderTypeEnum.PuhuoHeader;
						}

					} else if (state.equals(Short.parseShort(OrderStatusEnum.PayedDeposit.toString()))) {
						// 已支付定金
						if (bAssist.getTailPayType().equals(Short.parseShort(TailPayTypeEnum.predelivery.toString()))) {
							typeEnum = OrderTypeEnum.BookTailOrder;
							typeEnum1 = OrderTypeEnum.PuhuoTail;
						}
					} else if (state.equals(Short.parseShort(OrderStatusEnum.Accepted.toString()))) {
						// 已收货
						if (bAssist.getTailPayType().equals(Short.parseShort(TailPayTypeEnum.afterdelivery.toString()))) {
							typeEnum = OrderTypeEnum.BookTailOrder;
							typeEnum1 = OrderTypeEnum.PuhuoTail;
						}
					}
					if (typeEnum != null) {
						if (poList != null && poList.size() > 0) {
							for (OPayOrder oPayOrder : poList) {
								if (oPayOrder.getTypeState().equals(Short.parseShort(typeEnum.toString())) || oPayOrder.getTypeState().equals(Short.parseShort(typeEnum1.toString()))) {
									result.setPayOrderNo(oPayOrder.getPayOrderId());
									break;
								}
							}
						}
					}
				}
			}
			double totalAmount = supplyerOrder.getTotalPrice() + supplyerOrder.getPostage();
			result.setPayment(totalAmount);
			// 获取收货信息
			OOrderAddr orderAddr = null;
			Long addID = supplyerOrder.getAddrId();
			if (addID != null && addID.longValue() > 0) {
				orderAddr = orderManageDAO.getOrderAddr(supplyerOrder.getAddrId());
			}
			if (orderAddr != null) {
				result.setReceivingName(orderAddr.getReceiverName());
				result.setReceivingPhone(orderAddr.getMobilePhone());
				// TODO 获取详细地址 基类缓存还没有配置好
				String address = "";
				int province = orderAddr.getProvince() == null ? 0 : orderAddr.getProvince();
				if (province > 0) {
					address += regionService.getNameByCode(province);
				}
				int city = orderAddr.getCity() == null ? 0 : orderAddr.getCity();
				if (city > 0) {
					address += regionService.getNameByCode(city);
				}
				int street = orderAddr.getDistrict() == null ? 0 : orderAddr.getDistrict();
				if (street > 0) {
					address += regionService.getNameByCode(street);
				}
				address += orderAddr.getDetailAddr();
				result.setReceivingAddress(address);
			}
			result.setMessage(supplyerOrder.getMessage());
			// 如果订单未付款获取订单过期时间
			if (supplyerOrder.getState().equals(Short.parseShort(OrderStatusEnum.Nopay.toString())) || supplyerOrder.getState().equals(Short.parseShort(OrderStatusEnum.Tovoided.toString()))) {
				// TODO 三天过期时间，这里看3是否读配置文件
				// 如果订单是活动订单，则10分钟内过期
				if (supplyerOrder.getIsActivity() == 1) {
					result.setExpirationTime(DateUtils.addMinute(supplyerOrder.getOrderTime(), 10));
				} else {
					result.setExpirationTime(DateUtils.addDate(supplyerOrder.getOrderTime(), 3));
				}
			}

			// 获取供应商的信息
			Long supid = supplyerOrder.getSupplyerId();
			result.setSupplierId(supid);
			result.setSupplierName(shopService.getShopNameByWeiId(supid));
			result.setSupplierPhone(shopService.getContactPhone(supid)); 
			result.setSupplierQQ(shopService.getQQ(supid));
//			USupplyer supplyer = orderManageDAO.getSupplyer(supid);  
//			if (supplyer != null) {
//				if (supplyer.getServiceQQ() != null && supplyer.getServiceQQ().length() > 0) {
//					String[] qqStr = supplyer.getServiceQQ().split("\\|");
//					result.setSupplierQQ(qqStr[0]);
//				}
//			}
			result.setFreight(supplyerOrder.getPostage());

			double amount = 0d;
			// 查询产品列表
			List<OProductOrder> prolist = orderManageDAO.getOrderProduct(new String[] { supOrderID });
			List<ProductOrderVO> proListVO = new ArrayList<ProductOrderVO>();
			if (prolist != null && prolist.size() > 0) {
				Long[] sweiid = new Long[prolist.size()];
				List<Long> refundID = new ArrayList<Long>();
				for (int i = 0; i < prolist.size(); i++) {
					OProductOrder temp = prolist.get(i);
					sweiid[i] = temp.getSellerWeiid();
					Long refid = temp.getBackOrder();
					if (refid != null && refid.longValue() > 0) {
						refundID.add(refid);
					}
				}
				// 查询退款列表
				List<TOrderBackTotal> refList = null;
				if (refundID.size() > 0) {
					refList = orderManageDAO.getRefundList((Long[]) refundID.toArray(new Long[refundID.size()]));
				}
				// 获取成交微店名称
				List<UWeiSeller> wsList = orderManageDAO.getWeiSellerList(sweiid);
				for (OProductOrder pro : prolist) {
					ProductOrderVO voPro = new ProductOrderVO();
					voPro.setProductId(pro.getProductId());
					voPro.setProductOrderID(pro.getProductOrderId());
					voPro.setCount(pro.getCount());
					voPro.setProductState(pro.getState());
					voPro.setProperty(returnProperty(pro.getStyleDes()));
					voPro.setProductId(pro.getProductId());
					voPro.setProductTitle(pro.getProdcutTitle());
					voPro.setPreferential("无优惠");
					// 处理图片域名
					voPro.setProductImg(ImgDomain.GetFullImgUrl(pro.getProductImg(), 24));
					voPro.setPrice(pro.getPrice());
					// 查询退款ID 状态
					voPro.setRefundState(-1);
					voPro.setBuyerId(pro.getBuyerId());
					if (refList != null && refList.size() > 0) {
						for (TOrderBackTotal ref : refList) {
							if (ref.getBackOrder().equals(pro.getBackOrder())) {
								if (ref.getBackStatus().equals(Short.parseShort(ReFundStatusEnum.GysQueRen.toString()))) {
									amount += ref.getRefundAmout();
								}
								voPro.setRefundId(ref.getBackOrder());
								voPro.setRefundState(ref.getBackStatus());
								// 将没有完成的退款订单ID
								if (!(ref.getBackStatus().equals(Short.parseShort(ReFundStatusEnum.GysQueRen.toString())) && ref.getBackStatus().equals(Short.parseShort(ReFundStatusEnum.ShenQingClose.toString())))) {
									result.setRefundId(ref.getBackOrder());
								}
								break;
							}
						}
					}
					voPro.setCommission(pro.getCommision());
					voPro.setTotalamount(pro.getPrice() * pro.getCount());
					// 来源微店名称
					for (UWeiSeller wSeller : wsList) {
						if (pro.getSellerWeiid().equals(wSeller.getWeiId())) {
							voPro.setSourceWeiName(wSeller.getWeiName());
							break;
						}
					}
					proListVO.add(voPro);
				}
			}
			result.setMaxPrice(totalAmount - amount);
			result.setProList(proListVO);

			List<String> recordList = new ArrayList<String>();
			// 查询订单记录
			List<OOrderFlow> ofList = orderManageDAO.getOrderFlowList(supOrderID);
			if (ofList != null && ofList.size() > 0) {
				for (OOrderFlow of : ofList) {
					recordList.add((of.getOperateTime() == null ? "" : DateUtils.formatDateTime(of.getOperateTime())) + "  " + of.getOperateContent());
				}
			}
			result.setRecordList(recordList);
			// 查询物流信息
			String wlNo = supplyerOrder.getDeliveryOrder();
			String companyNo = supplyerOrder.getdComanpyNo();
			String companyName = supplyerOrder.getDeliveryCompany();
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

			/**
			 * 使用多少微金币
			 */
			OPayOrder po = orderManageDAO.getPayOrder(supplyerOrder.getPayOrderId());
			result.setWeiCoin(po.getWeiDianCoin() == null ? 0.0 : po.getWeiDianCoin());

		}
		return result;
	}

	@Override
	public String cancelOrder(Long weiid, String supOrderID) {
		try {
			if (orderManageDAO.updateOrderState(weiid, supOrderID) > 0) {

				// 添加订单记录
				try {
					orderManageDAO.addOrderRecord(weiid, "买家取消了订单", supOrderID);
				} catch (Exception e) {
				}
				return "1";
			}
			return "0";
		} catch (Exception e) {
			return "-1";
		}

	}

	@Override
	public String deleteOrder(Long weiid, String supOrderID) {
		// 先查询该订单验证是否能够删除
		OSupplyerOrder entity = orderManageDAO.getsSupplyerOrder(weiid, supOrderID);
		if (entity != null) {
			if (entity.getState().equals(Short.parseShort(OrderStatusEnum.Canceled.toString())) || entity.getState().equals(Short.parseShort(OrderStatusEnum.Tovoided.toString())) || entity.getState().equals(Short.parseShort(OrderStatusEnum.Rejected.toString()))) {
				orderManageDAO.deleteOrder(weiid, supOrderID);
				// 添加订单记录
				try {
					orderManageDAO.addOrderRecord(weiid, "买家删除了订单", supOrderID);
				} catch (Exception e) {
				}
				return "1";
			} else {
				return "该订单不能被删除";
			}
		} else {
			return "获取订单异常";
		}

	}

	@Override
	public LogisticsVO getLogistics(String supOrderID) {
		LogisticsVO result = new LogisticsVO();
		TransVO trans = logisticsService.getLogisticsInfo(supOrderID);
		if (trans != null) {
			result.setLogisticsNo(trans.getTransNo());
			result.setLongisticsName(trans.getTransCompany());
			result.setTailList(getLogisticsDetails(trans.getWuliu()));

			result.setSellerWeiid(trans.getSellerWeiid());
			result.setBuyerWeiid(trans.getBuyerWeiid());
			result.setOrderState(trans.getOrderState());
		}
		return result;
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
				int state = ParseHelper.toInt(String.valueOf(json.get("status")));
				if (state == 0)
					return strList;
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

	@Override
	public RefundVO getRefundInfo(Long weiid, Long backOrder) {
		RefundVO result = new RefundVO();
		TOrderBackTotal model = orderManageDAO.getOrderBack(weiid, backOrder);
		if (model != null) {
			result.setBackOrder(backOrder);
			result.setSupOrderID(model.getSupplierOrderId());
			result.setRefundWay(model.getType());
			result.setRefundPrice(model.getRefundAmout());
			result.setReason(model.getBReason());
			result.setReasonNo(model.getSReason());
			result.setReasonOkwei(model.getAReason());
			result.setRefundState(model.getBackStatus());
			// result.setRefundTime( model.getCreateTime());
			// result.setProcessId(model.getFlowId());
			// 查询退款图片列表
			List<TRefundImg> imgList = orderManageDAO.getRefundImg(backOrder);
			List<String> proImages = new ArrayList<String>();
			if (imgList != null && imgList.size() > 0) {
				for (TRefundImg rImg : imgList) {
					proImages.add(ImgDomain.GetFullImgUrl(rImg.getRefundImg()));
				}
			}
			result.setProImages(proImages);
			// 查询卖家收货地址
			if (model.getSaid() != null && model.getSaid().intValue() > 0) {
				OOrderAddr addr = orderManageDAO.getOrderAddr(model.getSaid());
				if (addr != null) {
					result.setConsignee(addr.getReceiverName());
					result.setPhone(addr.getMobilePhone());
					String address = "";
					int province = addr.getProvince() == null ? 0 : addr.getProvince();
					if (province > 0) {
						address += regionService.getNameByCode(province);
					}
					int city = addr.getCity() == null ? 0 : addr.getCity();
					if (city > 0) {
						address += regionService.getNameByCode(city);
					}
					int street = addr.getDistrict() == null ? 0 : addr.getDistrict();
					if (street > 0) {
						address += regionService.getNameByCode(street);
					}
					address += addr.getDetailAddr();
					result.setRetreatAddress(address);
				}
			}
			// 获取供应商的信息
			Long supid = model.getSellerId();
			USupplyer Supplyer = orderManageDAO.getSupplyer(supid);
			if (Supplyer != null) {
				result.setSupplyerName(Supplyer.getCompanyName());
			}
			UYunSupplier yunSupplier = orderManageDAO.getYunSupplier(supid);
			if (yunSupplier != null) {
				result.setSupplyerQQ(yunSupplier.getServiceQq());
			}
			// 获取物流信息
			LogisticsVO logisticsVO = new LogisticsVO();
			if (model.getBackStatus().equals(Short.parseShort(ReFundStatusEnum.BuyerFaHuo.toString()))) {
				if (model.getTransNo() != null && model.getTransName() != null) {
					logisticsVO.setLogisticsNo(model.getTransNo());
					logisticsVO.setLongisticsName(model.getTransName());
					logisticsVO.setTailList(getLogisticsDetails(logisticsService.getInfoByName(model.getTransNo(), model.getTransName())));
				}
			}
			result.setLogistics(logisticsVO);
			// 获取退款产品列表
			List<ProductOrderVO> proListVO = new ArrayList<ProductOrderVO>();
			List<OProductOrder> proList = orderManageDAO.getOrderProduct(backOrder);
			if (proList != null && proList.size() > 0) {
				for (OProductOrder pro : proList) {
					ProductOrderVO temp = new ProductOrderVO();
					temp.setBuyerId(pro.getBuyerId());
					temp.setProductImg(ImgDomain.GetFullImgUrl(pro.getProductImg(), 24));
					temp.setProductId(pro.getProductId());
					temp.setProductTitle(pro.getProdcutTitle());
					temp.setProperty(returnProperty(pro.getStyleDes()));
					temp.setPrice(pro.getPrice());
					temp.setCommission(pro.getCommision());
					temp.setCount(pro.getCount());
					temp.setTotalamount(pro.getPrice() * pro.getCount());
					proListVO.add(temp);
				}
			}
			result.setProList(proListVO);
			List<String> recordList = new ArrayList<String>();
			// 查询退款记录
			if (model.getFlowId() != null && model.getFlowId() != "") {
				ReturnModel returnModel = FlowMethod.QueryHistory(model.getFlowId(), weiid, 1, 100);
				if (returnModel != null && returnModel.getBasemodle() != null) {

					String msgList = returnModel.getBasemodle().toString();
					// List<FFlowMsg> msgList = (List<FFlowMsg>)
					// returnModel.getBasemodle();
					JSONArray ja = JSONArray.fromObject(msgList);
					if (ja != null && ja.size() > 0) {
						for (int i = 0; i < ja.size(); i++) {
							JSONObject jo = (JSONObject) ja.get(i);
							recordList.add((jo.getString("operateTime") == null ? "" : jo.getString("operateTime") + "  " + jo.getString("actionConment")));
						}
					}
				}
			}
			result.setRecordList(recordList);

			// //使用的微金币
			// OSupplyerOrder
			// so=orderManageDAO.getsSupplyerOrder(model.getSupplierOrderId());
			// OPayOrder po=orderManageDAO.getPayOrder(so.getPayOrderId());
			// result.setWeiCoin(po.getWeiDianCoin());

		}
		return result;
	}

	@Override
	public String confirmationReceipt(Long weiid, String supOrderID) {
		OSupplyerOrder entity = orderManageDAO.getsSupplyerOrder(weiid, supOrderID);
		if (entity != null) {
			if (entity.getState().equals(Short.parseShort(OrderStatusEnum.Deliveried.toString()))) {
				// 修改订单状态
				orderManageDAO.confirReceipt(weiid, supOrderID);
				// 将微钱包里的钱修改为收货冻结
				orderManageDAO.setFrozenOrderAmout(supOrderID);
				// 添加订单记录
				try {
					orderManageDAO.addOrderRecord(weiid, "买家确认收货", supOrderID);
				} catch (Exception e) {
				}
				return "1";

			} else if (entity.getState().equals(Short.parseShort(OrderStatusEnum.Accepted.toString()))) {
				return "该订单已确认收货";
			} else {
				return "操作失败";
			}
		} else {
			return "获取订单失败";
		}
	}

	@Override
	public BuyOrderListVO applyRefund(Long weiid, String supOrderID, String proOrderID, short tkType, String tkReason, String tkImage, double tkMoney) {
		BuyOrderListVO result = new BuyOrderListVO();
		DecimalFormat d = new DecimalFormat("0.00");
		OSupplyerOrder supOrder = orderManageDAO.getsSupplyerOrder(weiid, supOrderID);
		if (supOrder == null) {
			result.setOrderState(0);
			result.setSupplyerName("获取订单失败");
			return result;
		}
		if(supOrder.getOrderType() == Short.parseShort(OrderTypeEnum.Convert.toString()))
		{
			result.setOrderState(0);
			result.setSupplyerName("兑换区的商品不能提交申请！");
			return result;
		}
		// 判断订单只有在 已付款 已发货 已收货才能退款
		short state = supOrder.getState();
		if (state != Short.parseShort(OrderStatusEnum.Payed.toString()) && state != Short.parseShort(OrderStatusEnum.Deliveried.toString()) && state != Short.parseShort(OrderStatusEnum.Accepted.toString())) {
			result.setOrderState(0);
			result.setSupplyerName("获取订单失败，或者您已提交退款申请");
			return result;
		}

		List<OProductOrder> proList = orderManageDAO.getOrderProduct(new String[] { supOrderID });
		if (proList == null || proList.size() <= 0) {
			result.setOrderState(0);
			result.setSupplyerName("获取订单产品失败");
			return result;
		}
		String[] proOrderIDs = proOrderID.split(",");
		// 计算验证退款金额
		double totalPrice = supOrder.getTotalPrice() + supOrder.getPostage();
		double yrefundAmount = orderManageDAO.getRefundedAmout(supOrderID);
		// 使用的现金劵金额
		double cashCoupon = 0d;
		String hql = "select sum(t.coinAmount) from UWeiCoinLog t  where t.supplyOrderId=? and t.weiId=? and t.useType=2 ";
		List<Object[]> list1 = basicOrdersDao.find(hql, supOrderID, weiid);
		if (list1 != null && list1.size() > 0) {
			Object b = list1.get(0);
			if (b != null) {
				cashCoupon = Math.abs((double) b);
			}
		}
		// 实付金额
		double money = totalPrice - cashCoupon;
		// 实退金额
		double rbackamount = (money / totalPrice) * tkMoney;
		// 实退代金券
		double rbackcash = (tkMoney / totalPrice) * cashCoupon;

		double commission = 0d;
		for (OProductOrder pro : proList) {
			boolean flag = false;
			for (String str : proOrderIDs) {
				if (pro.getProductOrderId().equals(str)) {
					flag = true;
					break;
				}
			}
			if (flag) {
				continue;
			}
			if (pro.getBackOrder() == null || pro.getBackOrder().longValue() < 0) {
				commission += pro.getCommision();
			} else {
				TOrderBackTotal entity = orderManageDAO.getTOrderBackTotal(pro.getBackOrder());
				if (entity != null) {
					if (entity.getBackStatus().equals(Short.parseShort(ReFundStatusEnum.ShenQingClose.toString()))) {
						commission += pro.getCommision();
					}
				} else {
					commission += pro.getCommision();
				}
			}
		}
		if (tkMoney > (totalPrice - yrefundAmount - commission)) {
			result.setOrderState(0);
			result.setSupplyerName("最多可以退款" + d.format((totalPrice - yrefundAmount - commission)) + "元");
			return result;
		}
		
		//处理微金币情况
		OPayOrder po= orderManageDAO.getPayOrder(supOrder.getPayOrderId());
		if(po==null)
		{
			result.setOrderState(0);
			result.setSupplyerName("获取支付订单失败");
			return result;
		}
		if(po.getWeiDianCoin()!=null && po.getWeiDianCoin()>0) //使用微金币
		{
			if (tkMoney > (totalPrice - yrefundAmount - commission -po.getWeiDianCoin())) {
				result.setOrderState(0);
				result.setSupplyerName("最多可以退款" + d.format((totalPrice - yrefundAmount - commission-po.getWeiDianCoin())) + "元");
				return result;
			}
		}
		//如果是活动全返订单，要扣除未到账数量
//		UTradingDetails td=basicOrdersDao.getUniqueResultByHql(" from UTradingDetails u where u.weiId=? and u.supplyOrder=? and u.type=? and u.state=0", weiid,supOrderID,Short.parseShort(UserAmountType.ticket.toString()));
//		if(td!=null && td.getAmount()>0) //扣除返券
//		{
//			basicOrdersDao.executeHql(" update UWallet u set u.unAbleTicket=u.unAbleTicket-?  where u.weiId=? ", td.getAmount(),weiid);
//		}
		TOrderBackTotal model = new TOrderBackTotal();
		// 判断是否整单退款

		if (proOrderIDs.length == proList.size()) {
			model.setSingle((short) 1);
		} else {
			List<Long> refundID = new ArrayList<Long>();
			boolean isZd = true;
			for (OProductOrder proOrder : proList) {
				boolean iscz = false;
				for (String str : proOrderIDs) {
					if (proOrder.getProductOrderId().equals(str)) {
						iscz = true;
						break;
					}
				}
				if (iscz) {
					continue;
				}
				if (proOrder.getBackOrder() == null || proOrder.getBackOrder().longValue() < 0) {
					// 不是整单
					isZd = false;
					break;
				} else {
					refundID.add(proOrder.getBackOrder());
				}
			}
			if (isZd) {
				List<TOrderBackTotal> refList = orderManageDAO.getRefundList((Long[]) refundID.toArray(new Long[refundID.size()]));
				isZd = true;
				for (TOrderBackTotal back : refList) {
					if (back.getBackStatus().equals(Short.parseShort(ReFundStatusEnum.ShenQingClose.toString()))) {
						isZd = false;
						break;
					}
				}
				if (isZd) {
					model.setSingle((short) 1);
				} else {
					model.setSingle((short) 2);
				}

			} else {
				model.setSingle((short) 2);
			}
		}

		model.setSupplierOrderId(supOrderID);
		model.setBackStatus(Short.parseShort(ReFundStatusEnum.ShenQingZhong.toString()));
		model.setOistatus(supOrder.getState());
		model.setSellerId(supOrder.getSupplyerId());
		model.setBuyerid(supOrder.getBuyerID());
		model.setBReason(tkReason);
		model.setRefundAmout(tkMoney - rbackcash);
		model.setType(tkType);
		model.setCreateTime(new Date());
		// 添加申请退款记录
		Long backOrder = orderManageDAO.addTOrderBackTotal(model);
		if (backOrder != null && backOrder.longValue() > 0) {
			// 修改订单状态为退款中
			orderManageDAO.updateSupOrderState(supOrderID, Short.parseShort(OrderStatusEnum.Refunding.toString()));
			// 绑定退款ID到产品订单表
			orderManageDAO.updateBackOrder(proOrderIDs, backOrder);
			// 插入退款图片
			if (tkImage != null && tkImage != "") {
				String[] images = tkImage.split(",");
				for (String str : images) {
					TRefundImg temp = new TRefundImg();
					temp.setBackOrder(backOrder);
					temp.setRefundImg(str);
					orderManageDAO.addTkImages(temp);
				}
			}

			// 启动退款流程
			String flowname = "";
			if (tkType == 1) {
				if (supOrder.getState().equals(Short.parseShort(OrderStatusEnum.Payed.toString()))) {
					// 退款未发货
					flowname = "YHTKWFH";
				} else {
					// 退款已发货
					flowname = "YHTKFH";
				}
			} else {
				// 退货
				flowname = "YHTH";
			}
			result.setOrderState(1);
			result.setCreateTimeStr(flowname);
			result.setSupplyId(backOrder);
		}
		// 发短信给供应商
		String content = "消费者发起了一笔退款退货申请，订单号" + supOrderID + "，请及时处理，以免影响消费者体验。如不及时处理，微店网将会做一些降权等处罚！ [微店网]";
		if(supOrder.getOrderType().longValue() == Integer.parseInt(OrderTypeEnum.RetailAgent.toString()))
		{
			UBrandSupplyer us=basicOrdersDao.get(UBrandSupplyer.class, supOrder.getSupplyerId());
			if(us!=null && us.getMobilePhone()!=null)
			{
				SendSMSByMobile.sendSMS(us.getMobilePhone(), content);
			}
		}
		else
		{
			USupplyer usu = getById(USupplyer.class, supOrder.getSupplyerId());
			String lr = SendSMSByMobile.sendSMS(usu.getMobilePhone(), content);
		}
		return result;
	}

	@Override
	public String buyersDelivery(long weiid, long backOrder, String transNo, String transName) {
		// 修改退款表的收货地址
		if (orderManageDAO.updateTrans(weiid, backOrder, transNo, transName) > 0) {
			return "1";
		}
		return "提交信息失败";
	}

	@Override
	public String cancelRefund(long weiid, long backOrder) {
		TOrderBackTotal model = orderManageDAO.getOrderBack(weiid, backOrder);
		if (model != null) {
			// 将退款状态改为取消
			model.setBackStatus(Short.parseShort(ReFundStatusEnum.ShenQingClose.toString()));
			orderManageDAO.updateBackOrder(model);
			// 将订单状态改为原先的状态
			orderManageDAO.updateSupOrderState(model.getSupplierOrderId(), model.getOistatus());
			if (model.getFlowId() != null && model.getFlowId() != "") {
				// TODO 关闭流程
				FlowMethod.CancelFlow(model.getFlowId(), weiid);
			}
			return "1";
		}
		return "取消退款申请失败";
	}

	@Override
	public String OkweiIntervention(long backOrder, long weiid) {
		TOrderBackTotal entity = orderManageDAO.getOrderBack(weiid, backOrder);
		if (entity != null && entity.getFlowId() != null && entity.getFlowId() != "") {
			ReturnModel model = FlowMethod.QueryTask(entity.getFlowId().toString(), weiid);
			if (model != null && model.getStatu().equals(ReturnStatus.Success)) {
				String ftask = model.getBasemodle().toString();
				if (ftask != null) {
					JSONObject jo = JSONObject.fromObject(ftask);
					if (FlowMethod.ActionFlow(jo.getString("taskId"), weiid, "disagree", "买家申请微店网介入")) {
						return "1";
					}
				}
			}
		}
		return "申请微店网介入失败";
	}

	@Autowired
	private IProductDAO productDAO;

	@Override
	public ResultMsg returnPayOrderID(String orderNo) {
		ResultMsg result = new ResultMsg();

		// 查询最新的payorder
		OPayOrder payOrder = null;
		List<OPayOrderLog> loglist = productDAO.find("from OPayOrderLog where supplyOrderIds=? order by createTime desc", new Object[] { orderNo });
		if (loglist != null && loglist.size() > 0) {
			payOrder = productDAO.get(OPayOrder.class, loglist.get(0).getPayOrderId());
		}
		if (payOrder != null) {
			// 检查有没有支付锁
			String keyString = RedisUtil.getString("PayToBank_" + orderNo);
			if (keyString != null && keyString != "") {
				result.setStatus(0);
			} else {
				result.setStatus(1);
			}
			result.setMsg(payOrder.getPayOrderId());
		} else {
			// 查询供应商订单
			OSupplyerOrder supOrder = orderManageDAO.getsSupplyerOrder(orderNo);
			if (supOrder != null) {
				supOrder.setPayOrderId(supOrder.getSupplierOrderId());
				super.update(supOrder);
				// 解除支付锁
				RedisUtil.delete("PayToBank_" + orderNo);
				OPayOrder payInfo = new OPayOrder();
				payInfo.setPayOrderId(supOrder.getSupplierOrderId());
				payInfo.setWeiId(supOrder.getBuyerID());
				payInfo.setTotalPrice(supOrder.getTotalPrice() + ParseHelper.getDoubleDefValue(supOrder.getPostage()));
				payInfo.setOrderFrom(supOrder.getOrderFrom());
				payInfo.setOrderTime(supOrder.getOrderTime());
				payInfo.setOrderDate(supOrder.getOrderDate());
				payInfo.setState((short) 0);
				payInfo.setTypeState(Short.parseShort(supOrder.getOrderType().toString()));
				productDAO.save(payInfo);
				// 修改产品支付订单号
				List<OProductOrder> proList = productDAO.find("from OProductOrder o where o.supplierOrderId = ?", new Object[] { orderNo });
				if (proList != null && proList.size() > 0) {
					for (OProductOrder proOrder : proList) {
						proOrder.setPayOrderId(orderNo);
						productDAO.update(proOrder);
					}
				}
				// 添加log
				OPayOrderLog log = new OPayOrderLog();
				log.setPayOrderId(orderNo);
				log.setSupplyOrderIds(orderNo);
				log.setWeiId(supOrder.getBuyerID());
				log.setTotalAmout(payInfo.getTotalPrice());
				log.setState((short) 0);
				log.setCreateTime(new Date());
				productDAO.save(log);
				result.setStatus(1);
				result.setMsg(orderNo);
			}
		}
		return result;
	}

	@Override
	public long saveOrderBackTotal(TOrderBackTotal o) {
		return orderManageDAO.addTOrderBackTotal(o);
	}

	@Override
	public void updateOrderBackTotal(TOrderBackTotal model) {
		// TODO Auto-generated method stub
		orderManageDAO.updateBackOrder(model);
	}
}
