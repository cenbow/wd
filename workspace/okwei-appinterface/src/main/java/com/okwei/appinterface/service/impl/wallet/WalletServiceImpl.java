package com.okwei.appinterface.service.impl.wallet;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.okwei.appinterface.bean.vo.wallet.CashCouponVO;
import com.okwei.appinterface.bean.vo.wallet.CashCouponVOs;
import com.okwei.appinterface.bean.vo.wallet.MyTradingRebatesVO;
import com.okwei.appinterface.bean.vo.wallet.TradingRebatesDetailsVO;
import com.okwei.appinterface.bean.vo.wallet.WalletDetailVO;
import com.okwei.appinterface.bean.vo.wallet.WalletMsg;
import com.okwei.appinterface.bean.vo.wallet.WalletOutInMsg;
import com.okwei.appinterface.bean.vo.wallet.WalletOutInResultVO;
import com.okwei.appinterface.bean.vo.wallet.WalletResultVO;
import com.okwei.appinterface.bean.vo.wallet.WalletVO;
import com.okwei.appinterface.bean.vo.xuanShangOrder.RewardDetailsVO;
import com.okwei.appinterface.bean.vo.xuanShangOrder.RewardMsg;
import com.okwei.appinterface.bean.vo.xuanShangOrder.RewardResultVO;
import com.okwei.appinterface.bean.vo.xuanShangOrder.RewardVO;
import com.okwei.appinterface.bean.vo.xuanShangOrder.ServiceFeeModel;
import com.okwei.appinterface.bean.vo.xuanShangOrder.ServiceFeeMsg;
import com.okwei.appinterface.bean.vo.xuanShangOrder.ServiceFeeResultVO;
import com.okwei.appinterface.service.BaseCommonService;
import com.okwei.appinterface.service.wallet.IWalletService;
import com.okwei.appinterface.util.DateOperate;
import com.okwei.bean.domain.OPayReward;
import com.okwei.bean.domain.OProductOrder;
import com.okwei.bean.domain.OSupplyerOrder;
import com.okwei.bean.domain.UPlatformServiceOrder;
import com.okwei.bean.domain.URepayLog;
import com.okwei.bean.domain.USupplyChannel;
import com.okwei.bean.domain.UTradingDetails;
import com.okwei.bean.domain.UVerifier;
import com.okwei.bean.domain.UWallet;
import com.okwei.bean.domain.UWalletDetails;
import com.okwei.bean.domain.UWeiCoinLog;
import com.okwei.bean.enums.IsPayReward;
import com.okwei.bean.enums.OrderStatusEnum;
import com.okwei.bean.enums.UserAmountType;
import com.okwei.bean.vo.ReturnModel;
import com.okwei.bean.vo.ReturnStatus;
import com.okwei.bean.vo.order.ParamOrderSearch;
import com.okwei.common.Limit;
import com.okwei.common.PageResult;
import com.okwei.dao.order.IBasicOrdersDao;
import com.okwei.dao.wallet.IBasicWalletDao;
import com.okwei.util.AppSettingUtil;
import com.okwei.util.DateUtils;
import com.okwei.util.ImgDomain;
import com.okwei.util.ParseHelper;

/**
 * @author fuhao 
 */
@Service
public class WalletServiceImpl implements IWalletService { 
	
	@Autowired
	public IBasicWalletDao basicWalletDao; 
	
	@Autowired
	public BaseCommonService baseCommonService;
	
	@Autowired
	public IBasicOrdersDao basicOrdersDao;
	 
	@Override
	public ReturnModel getServiceFeeList(Long weiId,String isPayReward, String dateStr,int pageIndex, int pageSize) {
		ReturnModel rq = new ReturnModel();
		rq.setStatu(ReturnStatus.Success);
		rq.setStatusreson("成功！");
		Limit limit=Limit.buildLimit(pageIndex, pageSize);
	//try {
		PageResult<UPlatformServiceOrder> findTradingRebates = basicOrdersDao.findServiceFeeList(weiId,"","",isPayReward, limit);
		//查询显示的数据
		List<UPlatformServiceOrder> list = findTradingRebates.getList();
		long totalPage =findTradingRebates.getPageCount();
		DecimalFormat d=new DecimalFormat("0.00");
		ServiceFeeResultVO result = new ServiceFeeResultVO();
		if (isPayReward!=null&&!"".equals(isPayReward)){
			if("1".equals(isPayReward)) {
			String hql="select count(id) from UPlatformServiceOrder where sellerWeiid =:weiId  and state=:state ";
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("weiId",weiId);
			map.put("state", ParseHelper.toInt(OrderStatusEnum.Payed.toString()));
			long orderAllCount = basicOrdersDao.countByMap(hql, map);
			result.setOrderAllCount(orderAllCount);
			if (orderAllCount!=0) {
				hql="select sum(totalPrice),sum(payAmount) from UPlatformServiceOrder where sellerWeiid =?  and state=?  ";
				List<Object[]> find = basicOrdersDao.find(hql, weiId, ParseHelper.toInt(OrderStatusEnum.Payed.toString()));
				if (find!=null&&find.size()>0) {
					if (null!=find.get(0)) {
						Object[] object = find.get(0);
						result.setOrderAllAmount(d.format(object[0]));
						result.setFee(d.format(object[1]));
					}
				}
			}
			}else{
				String hql="select count(id) from UPlatformServiceOrder where sellerWeiid =:weiId  and state =:state ";
				Map<String, Object> map=new HashMap<String, Object>();
				map.put("weiId",weiId);
				map.put("state", ParseHelper.toInt(OrderStatusEnum.Nopay.toString()));
				long orderAllCount = basicOrdersDao.countByMap(hql, map);
				result.setOrderAllCount(orderAllCount);
				if (orderAllCount!=0) {
					hql="select sum(totalPrice),sum(payAmount) from UPlatformServiceOrder where sellerWeiid =?  and state=?  ";
					List<Object[]> find = basicOrdersDao.find(hql, weiId, ParseHelper.toInt(OrderStatusEnum.Nopay.toString()));
					if (find!=null&&find.size()>0) {
						if (null!=find.get(0)) {
							Object[] object = find.get(0);
							result.setOrderAllAmount(d.format(object[0]));
							result.setFee(d.format(object[1]));
						}
					}
				}
			}
		}  
		if (list != null && list.size() > 0) {
			List<ServiceFeeMsg> outlist = new ArrayList<ServiceFeeMsg>();
			List<ServiceFeeModel> wList = new ArrayList<ServiceFeeModel>();
			for (int i = 0; i < list.size(); i++) {
				UPlatformServiceOrder ufs = list.get(i);
				// 2015-10
				String dateStr2 = DateOperate.getTimeStr(ufs.getCreateTime(), "yyyyMM");
				// 年份
				String yearStr = DateOperate.getTimeStr(ufs.getCreateTime(), "yyyy");
				String thisMonth = DateOperate.getTimeStr(new Date(), "yyyyMM");
				// 今年年份
				String thisYearStr = DateOperate.getTimeStr(new Date(), "yyyy");
				String monthName = "";
				if (thisMonth.equals(dateStr2)) {
					monthName =  DateOperate.getTimeStr(ufs.getCreateTime(), "MM") + "月";
				} else {
					if (thisYearStr.equals(yearStr)) {
						monthName = DateOperate.getTimeStr(ufs.getCreateTime(), "MM") + "月";
					} else {
						monthName = yearStr + "年" + DateOperate.getTimeStr(ufs.getCreateTime(), "MM") + "月";
					}
				}
				if (i == 0) {
					ServiceFeeMsg aa = new ServiceFeeMsg();
					aa.setDateStr(dateStr2);
					aa.setMonthName(monthName);
					aa.setYearName(thisYearStr);
					if (null!=dateStr&&!dateStr.equals(dateStr2)) {
						aa.setIsNew(1);
						dateStr = dateStr2;
					}
					outlist.add(aa);
				} else {
					if (null!=dateStr&&!dateStr.equals(dateStr2)) {
						dateStr = dateStr2;
						ServiceFeeMsg aa = new ServiceFeeMsg();
						aa.setDateStr(dateStr2);
						aa.setMonthName(monthName);
						aa.setYearName(thisYearStr);
						aa.setIsNew(1);
						outlist.add(aa);
					}
				}
					ServiceFeeModel sfm = new ServiceFeeModel();
					sfm.setFeeUrl("http://" + AppSettingUtil.getSingleValue("wapDomain") + "/v5/pph/ptfwf?tiket={tiket}&feeid="+ufs.getId()); 
					sfm.setBuyWeiId(ufs.getBuyerWeiid()); 
					sfm.setFeeId(ufs.getId());// 988,//平台服务费Id
					sfm.setBuyShopName(baseCommonService.getNickNameById(ufs.getBuyerWeiid()));// "XXX微店",//购买人微店名
					sfm.setOrderAmount(d.format(ufs.getTotalPrice()));// 8777.00,//订单总金额
					sfm.setOrderNum(ufs.getSupplyOrderId());// "323888777",//订单号
					if (null!=ufs.getCreateTime()) {
					sfm.setOrderTime(DateOperate.getTimeStr(ufs.getCreateTime(), "yyyy-MM-dd HH:mm"));// "2015-11-03  15：00",//下单时间
					}
					sfm.setFee(d.format(ufs.getPayAmount()));// 87.00,//服务费
					sfm.setIsPayReward(null!=ufs.getState()?ufs.getState():-1); //是否支付0-未支付1已支付
					sfm.setPayOrder(ufs.getPayOrderId()!=null?ufs.getPayOrderId():"0");// "88899033",//支付订单号
					sfm.setMerchantName(baseCommonService.getNickNameById(ufs.getSellerWeiid()));
					sfm.setDateStr(dateStr2);
					wList.add(sfm);
			}
				if (outlist != null && outlist.size() > 0) {
					for (ServiceFeeMsg oo : outlist) {
						List<ServiceFeeModel> suplist = new ArrayList<ServiceFeeModel>();
						for (ServiceFeeModel vo : wList) {
							if (oo.getDateStr() != null && vo.getDateStr() != null && oo.getDateStr().equals(vo.getDateStr())) {
								suplist.add(vo);
							}
						}
						oo.setList(suplist);
					}
				}
				result.setList(outlist);
			}else{
				rq.setStatusreson("暂时还没产生跟您相关的订单。");
			}
			result.setTotalPage(totalPage);
			result.setPageIndex(pageIndex);
			result.setPageSize(pageSize);
			rq.setBasemodle(result);

//		} catch (Exception e) {
//			rq.setStatu(ReturnStatus.SystemError);
//			rq.setStatusreson(e.getMessage());
//		}
		return rq; 
	}
	
	
	
	@Override
	public ReturnModel getServiceFeeDetail(Long feeId) {
		ReturnModel rq = new ReturnModel();
		rq.setStatu(ReturnStatus.Success);
		rq.setStatusreson("成功！");
		ServiceFeeModel sfm=new ServiceFeeModel();
		UPlatformServiceOrder ufs = basicOrdersDao.get(UPlatformServiceOrder.class, feeId);
		DecimalFormat d=new DecimalFormat("0.00");
		if (ufs!=null) {
			
			sfm.setBuyWeiId(ufs.getBuyerWeiid());  
			sfm.setFeeId(feeId);// 988,//平台服务费Id
			sfm.setBuyShopName(baseCommonService.getNickNameById(ufs.getBuyerWeiid()));// "XXX微店",//购买人微店名
			sfm.setOrderAmount(d.format(ufs.getTotalPrice()));// 8777.00,//订单总金额
			sfm.setOrderNum(ufs.getSupplyOrderId());// "323888777",//订单号
			sfm.setOrderTime(DateOperate.getTimeStr(ufs.getCreateTime(), "yyyy-MM-dd HH:mm"));// "2015-11-03  15：00",//下单时间
			sfm.setFee(d.format(ufs.getPayAmount()));// 87.00,//服务费
			sfm.setIsPayReward(ufs.getState()!=null?ufs.getState():0); //是否支付0-未支付1已支付
			if (null!=ufs.getState()&&ufs.getState().intValue()==1) {
				if (null!=ufs.getPayTime()){
					sfm.setPayTime(DateOperate.getTimeStr(ufs.getPayTime(), "yyyy-MM-dd HH:mm"));
				}
				sfm.setPayOrder(ufs.getPayOrderId()!=null?ufs.getPayOrderId():"0");// "88899033",//支付订单号
			} 
			sfm.setMerchantName(ufs.getSellerWeiid()!=null?baseCommonService.getNickNameById(ufs.getSellerWeiid()):"");
			rq.setBasemodle(sfm);
		}else{
			rq.setStatu(ReturnStatus.ParamError);
			rq.setStatusreson("该详情不存在！");
		}
		return rq;
	}
	
	@Override
	public ReturnModel getRewardDetail(Long weiId, Integer channelId) {
		ReturnModel rq = new ReturnModel();
		rq.setStatu(ReturnStatus.Success);
		rq.setStatusreson("成功！");
		RewardDetailsVO rd=new RewardDetailsVO();
		USupplyChannel usc = basicOrdersDao.get(USupplyChannel.class, channelId);
		if (usc!=null&&usc.getSupplyId().longValue()==weiId.longValue()) {
		rd.setRewardId(channelId);
		rd.setAuditTime(DateOperate.getTimeStr(usc.getCreateTime(), "yyyy-MM-dd HH:mm"));
		if (null!=usc.getVerifier()&&usc.getVerifier().longValue()>0) {
			UVerifier uu = basicOrdersDao.get(UVerifier.class,usc.getVerifier());
			if (uu!=null) {
				rd.setDevelopmentWeiId(usc.getVerifier());
				rd.setDevelopmentWeiName(uu.getName());
			}
		}
		rd.setRewardAmount(usc.getReward());
		rd.setDemandId(usc.getDemandId());
		rd.setDownstreamWeiId(usc.getWeiId());
		rd.setDownstreamShopName(baseCommonService.getNickNameById(usc.getWeiId()));
		rd.setIsPayReward(usc.getPayedReward()==null?0:usc.getPayedReward());
		OPayReward oPayReward = basicOrdersDao.get(OPayReward.class, channelId);
		if (oPayReward!=null&&null!=oPayReward.getPayTime()) {
			rd.setPayTime(DateOperate.getTimeStr(oPayReward.getPayTime(), "yyyy-MM-dd HH:mm"));
			//支付订单号
			rd.setPayOrder(oPayReward.getPayOrderId()==null?"0":oPayReward.getPayOrderId());
		}
		}else{
			rq.setStatu(ReturnStatus.ParamError);
			rq.setStatusreson("不存在该悬赏记录！");
		}
		rq.setBasemodle(rd);
		return rq;
	}

	
	@Override
	public ReturnModel getMyPayRewards(Long weiId,String dateStr, Short isPayReward,
			int pageIndex, int pageSize) {
		ReturnModel rq = new ReturnModel();
		rq.setStatu(ReturnStatus.Success);
		rq.setStatusreson("成功！");
		Limit limit=Limit.buildLimit(pageIndex, pageSize);
		PageResult<USupplyChannel> findPayRewards = basicOrdersDao.findPayRewards(weiId,"","",isPayReward, limit);
		//查询显示的数据
		List<USupplyChannel> list = findPayRewards.getList();
		List<OPayReward> findByMap =null;
		List<Integer> app=new ArrayList<Integer>();
		if (list != null && list.size() > 0) {
			for (USupplyChannel uSupplyChannel : list) {
				app.add(uSupplyChannel.getChannelId());
			}
		}
		if (app!=null&&app.size()>0) {
			String hql=" from OPayReward where channelId in (:channelId)";
			Map<String , Object > map = new HashMap<String, Object>();
			map.put("channelId", app);
			findByMap = basicOrdersDao.findByMap(hql, map);
		}
		
		long totalPage =findPayRewards.getPageCount();
		RewardResultVO result = new RewardResultVO();
		result.setTotalCount(findPayRewards.getTotalCount());
		if (list != null && list.size() > 0) {
			List<RewardMsg> outlist = new ArrayList<RewardMsg>();
			List<RewardVO> wList = new ArrayList<RewardVO>();
			for (int i = 0; i < list.size(); i++) {
				USupplyChannel ww = list.get(i);
				// 2015-10
				String dateStr2 = DateOperate.getTimeStr(ww.getCreateTime(), "yyyyMM");
				// 年份
				String yearStr = DateOperate.getTimeStr(ww.getCreateTime(), "yyyy");
				String thisMonth = DateOperate.getTimeStr(new Date(), "yyyyMM");
				// 今年年份
				String thisYearStr = DateOperate.getTimeStr(new Date(), "yyyy");
				String monthName = "";
				if (thisMonth.equals(dateStr2)) {
					monthName = "本月";
				} else {
					if (thisYearStr.equals(yearStr)) {
						monthName = DateOperate.getTimeStr(ww.getCreateTime(), "MM") + "月";
					} else {
						monthName = yearStr + "年" + DateOperate.getTimeStr(ww.getCreateTime(), "MM") + "月";
					}
				}
				if (i == 0) {
					RewardMsg aa = new RewardMsg();
					aa.setDateStr(dateStr2);
					aa.setMonthName(monthName);
					aa.setYearName(thisYearStr);
					if (null!=dateStr&&!dateStr.equals(dateStr2)) {
						aa.setIsNew(1);
						dateStr = dateStr2;
					}
					outlist.add(aa);
				} else {
					if (null!=dateStr&&!dateStr.equals(dateStr2)) {
						dateStr = dateStr2;
						RewardMsg aa = new RewardMsg();
						aa.setDateStr(dateStr2);
						aa.setMonthName(monthName);
						aa.setYearName(thisYearStr);
						aa.setIsNew(1);
						outlist.add(aa);
					}
				}
				RewardVO vo = new RewardVO();
				vo.setRewardId(ParseHelper.toLong(ww.getChannelId().toString()));
				vo.setUrl("http://" + AppSettingUtil.getSingleValue("wapDomain") + "/v5/pph/xsxq?rewardId="+ww.getChannelId()+"&tiket={tiket}"); 
				vo.setRewardAmount(null!=ww.getReward()?ww.getReward():0);
				vo.setAuditTime(DateOperate.getTimeStr(ww.getCreateTime(), "yyyy-MM-dd HH:mm"));
				vo.setIsPayReward(null==ww.getPayedReward()?0:ww.getPayedReward());
				if (null!=ww.getPayedReward()&&ww.getPayedReward().shortValue()==ParseHelper.toShort(IsPayReward.payed.toString())){
					if (findByMap!=null&&findByMap.size()>0) {
						for (OPayReward opr : findByMap) {
							if (opr.getChannelId().intValue()==ww.getChannelId().intValue()) {
								// 支付单号
								vo.setPayOrder(opr.getPayOrderId());
								if (null!=opr.getPayTime()) {
									//支付时间  
									vo.setPayTime(DateOperate.getTimeStr(opr.getPayTime(), "yyyy-MM-dd HH:mm"));
								}
							}
						}
					}
				}
				vo.setDemandId(ww.getDemandId());
				vo.setDevelopmentWeiId(ww.getWeiId());
				vo.setDevelopmentWeiName(baseCommonService.getNickNameById(ww.getWeiId()));
				vo.setDevelopmentShopImg(baseCommonService.getImageById(ww.getWeiId()));
				vo.setDateStr(dateStr2);
				wList.add(vo);
			}
			if (outlist != null && outlist.size() > 0) {
				for (RewardMsg oo : outlist) {
					 List<RewardVO>  suplist = new ArrayList<RewardVO>();
					for (RewardVO vo : wList) {
						if (oo.getDateStr() != null && vo.getDateStr() != null && oo.getDateStr().equals(vo.getDateStr())) {
							suplist.add(vo);
						}
					}
					oo.setList(suplist);
				}
			}
			result.setList(outlist);
			}else{
				rq.setStatusreson("暂时还没产生跟您相关的订单。");
			}
		
			DecimalFormat d=new DecimalFormat("0.00");
			if (isPayReward.shortValue()==0) {
				String hql="select count(weiId) from USupplyChannel where supplyId = :weiId and  (payedReward=0 or payedReward is NULL)  and state=1 ";
				Map<String, Object> map=new HashMap<String, Object>();
				map.put("weiId",weiId);
				long noPayCount = basicOrdersDao.countByMap(hql, map);
				hql="select sum(reward) from USupplyChannel where supplyId=?  and (payedReward=0 or payedReward is NULL) and state=1";
				Object[] objs=new Object[1];
				objs[0]=weiId;
				List<Object> find = basicOrdersDao.find(hql, objs);
				if (find!=null&&find.size()>0) {
					if (null!=find.get(0)) {
						result.setNoPayAmount(d.format(find.get(0)));
					}
				}
				result.setNoPayCount(noPayCount);
				
			}else{
				String hql="select count(weiId) from USupplyChannel where supplyId = :weiId and payedReward = 1 and state=1";
				Map<String, Object> map=new HashMap<String, Object>();
				map.put("weiId",weiId);
				long noPayCount = basicOrdersDao.countByMap(hql, map);
				hql="select sum(reward) from USupplyChannel where supplyId=? and payedReward = 1 and state=1";
				Object[] objs=new Object[1];
				objs[0]=weiId;
				List<Object> find = basicOrdersDao.find(hql, objs);
				if (find!=null&&find.size()>0) {
					if (null!=find.get(0)) {
						result.setNoPayAmount(d.format(find.get(0)));
					}
				}
				result.setNoPayCount(noPayCount);
				
			}
			result.setTotalPage(totalPage);
			result.setPageIndex(pageIndex);
			result.setPageSize(pageSize);
			rq.setBasemodle(result);
		return rq;
	}
	
	
	/**
	 * 返现列表
	 * @param weiId 采购商weiId
	 * @param dateStr
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public ReturnModel findTradingRebates(Long weiId, String dateStr, int pageIndex, int pageSize) {
		ReturnModel rq = new ReturnModel();
		rq.setStatu(ReturnStatus.Success);
		rq.setStatusreson("成功！");
		Limit limit=Limit.buildLimit(pageIndex, pageIndex);
	//try {
		PageResult<URepayLog> findTradingRebates = basicOrdersDao.findTradingRebates(weiId, limit);
		//查询显示的数据
		List<URepayLog> list = findTradingRebates.getList();
		long totalPage =findTradingRebates.getPageCount();
		WalletResultVO result = new WalletResultVO();
		if (list != null && list.size() > 0) {
			List<WalletMsg> outlist = new ArrayList<WalletMsg>();
			List<WalletVO> wList = new ArrayList<WalletVO>();
			for (int i = 0; i < list.size(); i++) {
				URepayLog ww = list.get(i);
				// 2015-10
				String dateStr2 = DateOperate.getTimeStr(ww.getCreateTime(), "yyyyMM");
				// 年份
				String yearStr = DateOperate.getTimeStr(ww.getCreateTime(), "yyyy");
				String thisMonth = DateOperate.getTimeStr(new Date(), "yyyyMM");
				// 今年年份
				String thisYearStr = DateOperate.getTimeStr(new Date(), "yyyy");
				String monthName = "";
				if (thisMonth.equals(dateStr2)) {
					monthName =  DateOperate.getTimeStr(ww.getCreateTime(), "MM") + "月";
				} else {
					if (thisYearStr.equals(yearStr)) {
						monthName = DateOperate.getTimeStr(ww.getCreateTime(), "MM") + "月";
					} else {
						monthName = yearStr + "年" + DateOperate.getTimeStr(ww.getCreateTime(), "MM") + "月";
					}
				}
				if (i == 0) {
					WalletMsg aa = new WalletMsg();
					aa.setDateStr(dateStr2);
					aa.setMonthName(monthName);
					aa.setYearName(thisYearStr);
					if (null!=dateStr&&!dateStr.equals(dateStr2)) {
						aa.setIsNew(1);
						dateStr = dateStr2;
					}
					outlist.add(aa);
				} else {
					if (null!=dateStr&&!dateStr.equals(dateStr2)) {
						dateStr = dateStr2;
						WalletMsg aa = new WalletMsg();
						aa.setDateStr(dateStr2);
						aa.setMonthName(monthName);
						aa.setYearName(thisYearStr);
						aa.setIsNew(1);
						outlist.add(aa);
					}
				}
				WalletVO vo = new WalletVO();
				//TODO 图片
//				vo.setImage(image);
				vo.setRebateId(ww.getId().toString());
				vo.setDateStr(dateStr2);
				vo.setMonthName(monthName);
				vo.setMonth(DateOperate.getTimeStr(ww.getCreateTime(), "MM"));
				vo.setMonthAmount(ww.getTotalMoney());
				vo.setAmount(ww.getRepayMoney());
				wList.add(vo);
			}
				if (outlist != null && outlist.size() > 0) {
					for (WalletMsg oo : outlist) {
						List<WalletVO> suplist = new ArrayList<WalletVO>();
						for (WalletVO vo : wList) {
							if (oo.getDateStr() != null && vo.getDateStr() != null && oo.getDateStr().equals(vo.getDateStr())) {
								suplist.add(vo);
							}
						}
						oo.setList(suplist);
					}
				}
				result.setYearlist(outlist);
			}else{
				rq.setStatusreson("暂时还没产生跟您相关的订单。");
			}
			result.setTotalPage(totalPage);
			result.setPageIndex(pageIndex);
			result.setPageSize(pageSize);
			rq.setBasemodle(result);

//		} catch (Exception e) {
//			rq.setStatu(ReturnStatus.SystemError);
//			rq.setStatusreson(e.getMessage());
//		}
		return rq;

	}
	
	@Override
	public ReturnModel getCashCoupon(Long weiId, int isPayReward,
			int pageIndex, int pageSize) {
		ReturnModel rq = new ReturnModel();
		rq.setStatu(ReturnStatus.Success);
		rq.setStatusreson("成功！"); 
		Limit limit=Limit.buildLimit(pageIndex, pageSize);
		CashCouponVOs pageResult1=new CashCouponVOs(); 
		UWallet w = basicWalletDao.get(UWallet.class, weiId);
		if (null!=w) { 
			pageResult1.setCountAmount(w.getTotalCoin());
		}else{
			pageResult1.setCountAmount(0.00);
		}
		pageResult1.setHelpUrl("http://" + AppSettingUtil.getSingleValue("wapDomain") + "/v5/app/voucherraiders?tiket={tiket}");
		List<CashCouponVO> list1=new ArrayList<CashCouponVO>();
		//数据源
		PageResult<UWeiCoinLog> pageResult = basicWalletDao.find_CashCoupon(weiId, limit, isPayReward);
		List<UWeiCoinLog> list = pageResult.getList();
		if (list!=null&&list.size()>0) {
			//先获取 商品订单号 
			String[] supplyOrderids=new String[list.size()];
			int i=0;
			for (UWeiCoinLog uWeiCoinLog : list) {
				supplyOrderids[i]=uWeiCoinLog.getSupplyOrderId();
				i++;
			}
			//查询出商品的名称和图片 获取商品订单集合
			List<OProductOrder> find_ProductOrderBySupOrderIds = basicOrdersDao.find_ProductOrderBySupOrderIds(supplyOrderids);
			//拼接展示的数据
			for (UWeiCoinLog uWeiCoinLog : list) {
				CashCouponVO ccv=new CashCouponVO();
				ccv.setCashCouponId(uWeiCoinLog.getCoinLogId());
				ccv.setAmount(Math.abs(uWeiCoinLog.getCoinAmount()));
				ccv.setDate(DateUtils.format(uWeiCoinLog.getCreateTime(), "yyyy-MM-dd"));
				if (uWeiCoinLog.getProductOrderId()!=null) { 
					for (OProductOrder oProductOrder : find_ProductOrderBySupOrderIds) {
						if (uWeiCoinLog.getSupplyOrderId().equals(oProductOrder.getSupplierOrderId())) {
							//给商品 图片  描述 赋值
							ccv.setImg(ImgDomain.GetFullImgUrl(oProductOrder.getProductImg(), 24));
							ccv.setName(oProductOrder.getProdcutTitle());
						}
					}	
				}
				ccv.setSupplyOrderId(uWeiCoinLog.getSupplyOrderId());
				list1.add(ccv);
			} 
			pageResult1.setList(list1);
			pageResult1.setPageIndex(pageResult.getPageIndex());
			pageResult1.setPageSize(pageResult.getPageSize());
			pageResult1.setTotalPage(pageResult.getTotalPage());
			rq.setBasemodle(pageResult1);
		}else{
			rq.setBasemodle(pageResult1);
			rq.setStatusreson("赶快去购物获得更多现金券吧！");
		} 
		return rq;
	}

	 
	
	/**
	 * 返现详情
	 * @param detailId
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public ReturnModel getMyTradingRebatesDetails(Long detailId,Long weiId,int pageIndex,int pageSize){
		ReturnModel rq = new ReturnModel();
		rq.setStatu(ReturnStatus.Success);
		rq.setStatusreson("成功！");
		URepayLog ww = basicWalletDao.get(URepayLog.class, detailId);
		if (ww!=null) {
			TradingRebatesDetailsVO vo=new TradingRebatesDetailsVO();
			//TODO 返现图片
//			vo.setImage(image);
			vo.setRebateId(ww.getId().toString());
			String yearName = DateOperate.getTimeStr(ww.getCreateTime(), "yyyy年-MM月");
			vo.setYearName(yearName);
			vo.setYear(DateOperate.getTimeStr(ww.getCreateTime(),"yyyy"));
			String monthName =  DateOperate.getTimeStr(ww.getCreateTime(), "MM") + "月";
			vo.setMonthName(monthName);
			vo.setMonth(DateOperate.getTimeStr(ww.getCreateTime(), "MM"));
			vo.setMonthAmount(ww.getTotalMoney());
			vo.setAmount(ww.getRepayMoney());
			Limit limit=Limit.buildLimit(pageIndex, pageSize);
			String fromDate = DateOperate.getTimeStr(ww.getCreateTime(), "yyyy-MM-01");
			Calendar cals=Calendar.getInstance();
			cals.setTime(ww.getCreateTime());
			cals.roll(Calendar.MONTH, +1);
			String toDate = new SimpleDateFormat("yyyy-MM-01").format(cals.getTime());
			ParamOrderSearch param=new ParamOrderSearch();
			param.setBuyerid(weiId);
			param.setBeginTimeStr(fromDate);
			param.setBeginTimeStr(toDate);
			PageResult<OSupplyerOrder> myWalletDetailPage = basicOrdersDao.find_TradingRebatesOrder(param,limit);
			List<MyTradingRebatesVO> orderList=new ArrayList<MyTradingRebatesVO>();
			for (OSupplyerOrder ud : myWalletDetailPage.getList()) {
				MyTradingRebatesVO mv=new MyTradingRebatesVO();
				mv.setOrderAmount(ud.getTotalPrice()!=null?ud.getTotalPrice().toString():"0");
				mv.setOrderNum(ud.getSupplierOrderId());
				mv.setOrderTime(DateOperate.getTimeStr(ud.getPayTime(), "yyyy-MM-dd HH:mm:ss"));
				orderList.add(mv);
			}
			vo.setTotalPage(myWalletDetailPage.getPageCount());
			vo.setPageIndex(pageIndex);
			vo.setPageIndex(pageSize);
			rq.setBasemodle(vo);
		}else{
			rq.setStatu(ReturnStatus.ParamError);
			rq.setStatusreson("返现记录不存在！");
		}
		return rq;
	}
	
	
	/**
	 * 获取用户收支列表
	 */
	public ReturnModel getWalletList(long weiid, String dateStr, int pageIndex, int pageSize) {
		ReturnModel rq = new ReturnModel();
		try {
			Limit limit=Limit.buildLimit(pageIndex, pageSize);
			PageResult<UWalletDetails> list1 = basicWalletDao.getMyWalletDetailPage(weiid, limit, 0, null, null);
			List<UWalletDetails> list =list1.getList();
			long totalPage =list1.getPageCount();
			WalletOutInResultVO result = new WalletOutInResultVO();
			if (list != null && list.size() > 0) {
				List<WalletOutInMsg> outlist = new ArrayList<WalletOutInMsg>();
				List<WalletDetailVO> wList = new ArrayList<WalletDetailVO>();
				for (int i = 0; i < list.size(); i++) {
					UWalletDetails ww = list.get(i);
					// 2015-10
					String dateStr2 = DateOperate.getTimeStr(ww.getCreateTime(), "yyyyMM");
					// 年份
					String yearStr = DateOperate.getTimeStr(ww.getCreateTime(), "yyyy");
					String thisMonth = DateOperate.getTimeStr(new Date(), "yyyyMM");
					// 今年年份
					String thisYearStr = DateOperate.getTimeStr(new Date(), "yyyy");
					String monthName = "";
					if (thisMonth.equals(dateStr2)) {
						monthName = "本月";
					} else {
						if (thisYearStr.equals(yearStr)) {
							monthName = DateOperate.getTimeStr(ww.getCreateTime(), "MM") + "月";
						} else {
							monthName = yearStr + "年" + DateOperate.getTimeStr(ww.getCreateTime(), "MM") + "月";
						}
					}
					if (i == 0) {
						WalletOutInMsg aa = new WalletOutInMsg();
						aa.setDateStr(dateStr2);
						aa.setMonthName(monthName);
						if (!dateStr.equals(dateStr2)) {
							aa.setIsNew(1);
							dateStr = dateStr2;
						}
						outlist.add(aa);
					} else {
						if (!dateStr.equals(dateStr2)) {
							dateStr = dateStr2;
							WalletOutInMsg aa = new WalletOutInMsg();
							aa.setDateStr(dateStr2);
							aa.setMonthName(monthName);
							aa.setIsNew(1);
							outlist.add(aa);
						}
					}
					WalletDetailVO vo = new WalletDetailVO();
					vo.setDetailId(String.valueOf(ww.getWdetailsId()));
					vo.setOrderNo(String.valueOf(ww.getWdetailsId()));
					vo.setDateStr(dateStr2);
					vo.setDateName(DateOperate.getTimeStr(ww.getCreateTime(), "MM-dd"));
					vo.setRestAmount(ParseHelper.getDoubleDefValue(ww.getRestAmount()));
					// 转换保留两位小数
					DecimalFormat d = new DecimalFormat("0.00");
					vo.setRestAmountName("余额：" + d.format(ParseHelper.getDoubleDefValue(ww.getRestAmount())));
					if (ww.getMainType() != null && ww.getMainType().shortValue() == 1) {
						vo.setColorValue("#00be00");
						vo.setAmount("+" + d.format(ParseHelper.getDoubleDefValue(ww.getAmount())));
					} else {
						vo.setColorValue("#000000");
						vo.setAmount("" + d.format(ParseHelper.getDoubleDefValue(ww.getAmount())));
					}
					vo.setImg(getBaseImgByUserAmountType(ww.getType()));
					wList.add(vo);
				}
				if (outlist != null && outlist.size() > 0) {
					for (WalletOutInMsg oo : outlist) {
						List<WalletDetailVO> suplist = new ArrayList<WalletDetailVO>();
						for (WalletDetailVO vo : wList) {
							if (oo.getDateStr() != null && vo.getDateStr() != null && oo.getDateStr().equals(vo.getDateStr())) {
								suplist.add(vo);
							}
						}
						oo.setList(suplist);
					}
				}
				result.setList(outlist);
			} else {
				result.setList(null);
				rq.setStatusreson("您还没有获得佣金！请加强推广！");
			}
			rq.setStatu(ReturnStatus.Success);
			result.setTotalPage(totalPage);
			result.setPageIndex(pageIndex);
			result.setPageSize(pageSize);
			rq.setBasemodle(result);

		} catch (Exception e) {
			rq.setStatu(ReturnStatus.SystemError);
			rq.setStatusreson(e.getMessage());
			rq.setBasemodle(e);
		}
		return rq;
	}
	
	/**
	 *	返现列表
	 * @author 付豪
	 * @param weiId
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public List<UTradingDetails> findWalletDetail1(Long weiId, int pageIndex, int pageSize) {
		String hql = "from UTradingDetails u where u.weiId=:weiId and u.type in (:type) order by u.createTime desc";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("type", new Short[] {  ParseHelper.toShort(UserAmountType.repay.toString())});
		map.put("weiId", weiId);
		return basicWalletDao.findPageByMap(hql, pageIndex, pageSize, map);
	}
	
	public String getBaseImgByUserAmountType(Short amountType) {
		if (amountType != null) {
			switch (amountType) {
			case 1:
				return ImgDomain.GetFullImgUrl_base("/images/app-yongjin-150709.png");// "http://base3.okimgs.com/images/app-baozhengjin-150709.png";
			case 2:
				return ImgDomain.GetFullImgUrl_base("/images/app-yongjin-150709.png");// "http://base3.okimgs.com/images/app-yongjin-150709.png";
			case 3:
				return ImgDomain.GetFullImgUrl_base("/images/app-huokuan-150709.png");// "http://base3.okimgs.com/images/app-huokuan-150709.png";
			case 4:
				return ImgDomain.GetFullImgUrl_base("/images/app-tuikuan-150709.png");// "http://base3.okimgs.com/images/app-tuikuan-150709.png";
			case 5:
				return ImgDomain.GetFullImgUrl_base("/images/app-yongjin-150709.png");// "http://base3.okimgs.com/images/app-yongjin-150709.png";
			case 6:
				return ImgDomain.GetFullImgUrl_base("/images/app-chongzhi-150709.png");// "http://base3.okimgs.com/images/app-chongzhi-150709.png";
			case 7:
				return ImgDomain.GetFullImgUrl_base("/images/app-tixian-150709.png");// "http://base3.okimgs.com/images/app-tixian-150709.png";
			case 8:
				return ImgDomain.GetFullImgUrl_base("/images/app-gouwu-150709.png");// "http://base3.okimgs.com/images/app-gouwu-150709.png";
			case 9:
				return ImgDomain.GetFullImgUrl_base("/images/app-tuikuan-150709.png");// "http://base3.okimgs.com/images/app-tuikuan-150709.png";
			case 10:
				return ImgDomain.GetFullImgUrl_base("/images/app-koushui-150709.png");// "http://base3.okimgs.com/images/app-koushui-150709.png";
			case 11:
				//返现
				return ImgDomain.GetFullImgUrl_base("/images/app-fanxian-150709.png");// "http://base3.okimgs.com/images/app-koushui-150709.png";
			case 12:
				//悬赏
				return ImgDomain.GetFullImgUrl_base("/images/app-xuanshang-150709.png");// "http://base3.okimgs.com/images/app-koushui-150709.png";
			case 13:
				//平台号抽点
				return ImgDomain.GetFullImgUrl_base("/images/app-pintaihao-150709.png");// "http://base3.okimgs.com/images/app-koushui-150709.png";
			default:
				break;
			}
		}
		return "";
	}



	



	

}
