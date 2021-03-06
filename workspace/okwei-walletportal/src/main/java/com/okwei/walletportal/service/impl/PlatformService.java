package com.okwei.walletportal.service.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.okwei.bean.domain.OBookAssist;
import com.okwei.bean.domain.UPlatformServiceOrder;
import com.okwei.bean.enums.IsPayReward;
import com.okwei.common.Limit;
import com.okwei.common.PageResult;
import com.okwei.dao.order.IBasicOrdersDao;
import com.okwei.service.IBaseCommonService;
import com.okwei.service.impl.BaseService;
import com.okwei.util.DateUtils;
import com.okwei.util.ParseHelper;
import com.okwei.walletportal.bean.vo.platform.PlatformVO;
import com.okwei.walletportal.service.IPlatformService;
/**
 * @author fuhao
 * 平台服务费
 */
@Service 
public class PlatformService extends BaseService implements IPlatformService{
		
	@Autowired
	private IBasicOrdersDao basicOrdersDao;
 
	@Autowired
	private IBaseCommonService baseCommonService;
	
	/**
	 * 统计已支付和未支付的个数
	 * @param weiId
	 * @return
	 */
	public Map<String, Object> get_countPlatform(Long weiId){
		Map<String, Object> map1 = new HashMap<String, Object>();
		
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder();
		hql.append("select count(o.id)  from UPlatformServiceOrder o where  o.sellerWeiid=:weiId   and o.state=:state ");
		map.put("weiId", weiId); 
		map.put("state", ParseHelper.toInt(IsPayReward.noPayed.toString())); 
		 long countByMap = basicOrdersDao.countByMap(hql.toString(), map);
		map1.put("weiZhiFu", countByMap);
		
		hql = new StringBuilder();
		hql.append("select count(o.id)  from UPlatformServiceOrder o where  o.sellerWeiid=:weiId   and o.state=:state ");
		map.put("weiId", weiId); 
		map.put("state", ParseHelper.toInt(IsPayReward.payed.toString())); 
		countByMap = basicOrdersDao.countByMap(hql.toString(), map);
		map1.put("yiZhiFu", countByMap);
			 
		return map1;
	}
	/**
	 * 统计平台号 服务费 的订单总金额
	 * @param weiId
	 * @return
	 */
	public double get_TotalMoney(Long weiId,String isPayReward){
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder();
		hql.append("select sum(o.totalPrice)  from UPlatformServiceOrder o where  o.sellerWeiid=:weiId   and o.state=:state ");
		map.put("weiId", weiId); 
		if (isPayReward.equals("0")) {
			map.put("state", ParseHelper.toInt(IsPayReward.noPayed.toString())); 
		}else if (isPayReward.equals("1")) {
			map.put("state", ParseHelper.toInt(IsPayReward.payed.toString())); 
		} 
		List<Object> findByMap = basicOrdersDao.findByMap(hql.toString(), map);
		double ss=0;
		if (findByMap!=null&&findByMap.size()>0) {
			Object object = findByMap.get(0);
			if (object!=null) {
				ss=(double) object;
			}
		}
		
		return ss;
	}
	/**
	 * 统计平台号 服务费 的 总服务费
	 * @param weiId
	 * @return
	 */
	public double get_PlatformMoney(Long weiId,String isPayReward){
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder();
		hql.append("select sum(o.payAmount)  from UPlatformServiceOrder o where  o.sellerWeiid=:weiId  and o.state=:state ");
		map.put("weiId", weiId); 
		if (isPayReward.equals("0")) {
			map.put("state", ParseHelper.toInt(IsPayReward.noPayed.toString())); 
		}else if (isPayReward.equals("1")) {
			map.put("state", ParseHelper.toInt(IsPayReward.payed.toString())); 
		} 
		List<Object> findByMap = basicOrdersDao.findByMap(hql.toString(), map);
		double ss=0;
		if (findByMap!=null&&findByMap.size()>0) {
			Object object = findByMap.get(0);
			if (object!=null) {
				ss=(double) object;
			}
		}
		
		return ss;
		 
	}

	/**
	 * 统计平台号服务费 该月的服务费
	 * @param weiId
	 * @param yearTimeStr
	 * @param monthTimeStr
	 * @param isPayReward
	 * @return
	 */
	@Override
	public double get_monthTotalMoney(Long weiId, String yearTimeStr,
			String monthTimeStr,String isPayReward) {
		String dateStr="";
		if (!"".equals(yearTimeStr)) {
			dateStr=yearTimeStr;
			if (!"".equals(monthTimeStr)) {
				dateStr+="-"+monthTimeStr+"-01";
			}else{
				dateStr+="-01-01";
			}
		} 
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder();
		hql.append("select sum(o.payAmount)  from UPlatformServiceOrder o where  o.sellerWeiid=:weiId  and o.state=:state ");
		map.put("weiId", weiId); 
		if (isPayReward.equals("0")) {
			map.put("state", ParseHelper.toInt(IsPayReward.noPayed.toString())); 
		}else if (isPayReward.equals("1")) {
			map.put("state", ParseHelper.toInt(IsPayReward.payed.toString())); 
		} 
		if (!"".equals(dateStr)) {
			Date date = DateUtils.parseDate(dateStr);
			Calendar cals = Calendar.getInstance();
			cals.setTime(date);
			if(!"".equals(monthTimeStr)) {
				cals.roll(Calendar.MONTH, +1); 
				if ("12".equals(monthTimeStr)) {
					cals.roll(Calendar.YEAR, +1); 
				}
			}else {
				cals.roll(Calendar.YEAR, +1); 
			}
			hql.append(" and o.createTime>:beginTime ");
			map.put("beginTime", date); 
		 	hql.append(" and o.createTime<=:endtime ");
			map.put("endtime", cals.getTime());
		} 
		List<Object> findByMap = basicOrdersDao.findByMap(hql.toString(), map);
		if (findByMap!=null&&findByMap.size()>0) {
			Object object = findByMap.get(0);
			if (object!=null) {
				return (double) object;
			}else{
				return 0;
			}
		}else{
			return 0;
		} 
	} 
	
	/**
	 *	平台服务费列表
	 * @param weiId
	 * @param yearTimeStr   年
	 * @param monthTimeStr	月
	 * @param isPayReward	 1已支付   0 未支付
	 * @param limit
	 * @return
	 */
	public PageResult<PlatformVO>  findServiceFeeList(Long weiId,String yearTimeStr, String monthTimeStr,String isPayReward, Limit limit){
		PageResult<UPlatformServiceOrder> findServiceFeeList = basicOrdersDao.findServiceFeeList(weiId, yearTimeStr, monthTimeStr, isPayReward, limit);
		PageResult<PlatformVO> platformList=new PageResult<PlatformVO>();
		List<UPlatformServiceOrder> list = findServiceFeeList.getList();
		List<PlatformVO> list1=new ArrayList<PlatformVO>();
		DecimalFormat d=new DecimalFormat("0.00");
		
		for (UPlatformServiceOrder ufs : list) {
			PlatformVO sfm = new PlatformVO();
			sfm.setBuyWeiId(ufs.getBuyerWeiid()); 
			sfm.setFeeId(ufs.getId());//平台服务费Id
			sfm.setBuyShopName(baseCommonService.getShopNameByWeiId(ufs.getBuyerWeiid()));//购买人微店名
			sfm.setOrderAmount(ufs.getTotalPrice());//订单总金额
			sfm.setOrderNum(ufs.getSupplyOrderId());//订单号
			if (null!=ufs.getCreateTime()) {
			sfm.setOrderTime(DateUtils.format(ufs.getCreateTime(), "yyyy-MM-dd HH:mm"));//下单时间
			}
			sfm.setFee(d.format(ufs.getPayAmount()));//服务费
			if (null!=ufs.getPayTime()) {
				sfm.setIsPayReward(1); //是否支付0-未支付1已支付
			}else{
				sfm.setIsPayReward(0);
			}
			sfm.setPayOrder(ufs.getPayOrderId()!=null?ufs.getPayOrderId():"0");//支付订单号
			sfm.setMerchantName(baseCommonService.getShopNameByWeiId(ufs.getSellerWeiid()));
			list1.add(sfm);
		}
		platformList.setList(list1);
		platformList.setPageCount(findServiceFeeList.getPageCount());
		platformList.setPageIndex(findServiceFeeList.getPageIndex());
		platformList.setPageSize(findServiceFeeList.getPageSize());
		platformList.setTotalCount(findServiceFeeList.getTotalCount());
		platformList.setTotalPage(findServiceFeeList.getTotalPage());
		return platformList;
	}
	
	
}
