package com.okwei.walletportal.service.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.okwei.bean.domain.USupplyChannel;
import com.okwei.bean.domain.UVerifier;
import com.okwei.bean.enums.IsPayReward;
import com.okwei.common.Limit;
import com.okwei.common.PageResult;
import com.okwei.dao.order.IBasicOrdersDao;
import com.okwei.service.IBaseCommonService;
import com.okwei.service.impl.BaseService;
import com.okwei.util.DateUtils;
import com.okwei.util.ParseHelper;
import com.okwei.walletportal.bean.vo.reward.RewardVO;
import com.okwei.walletportal.service.IRewardService;

@Service
public class RewardService extends BaseService implements IRewardService {
	
	@Autowired
	public IBasicOrdersDao basicOrdersDao;
	 
	@Autowired
	public IBaseCommonService baseCommonService;
	/**
	 * 悬赏 统计已支付和未支付的个数
	 * @param weiId
	 * @return
	 */
	public Map<String, Object> get_countReward(Long weiId){
		Map<String, Object> map1 = new HashMap<String, Object>();
		
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder();
		hql.append("select count(o.id)  from USupplyChannel o where  o.supplyId=:weiId  and state=1 and  (o.payedReward=:state or o.payedReward is NULL) ");
		map.put("weiId", weiId); 
		map.put("state", ParseHelper.toShort(IsPayReward.noPayed.toString())); 
		long countByMap = basicOrdersDao.countByMap(hql.toString(), map);
		map1.put("weiZhiFu", countByMap);
		 
		 
		hql = new StringBuilder();
		hql.append("select count(o.id)  from USupplyChannel o where  o.supplyId=:weiId   and o.payedReward=:state  and state=1");
		map.put("weiId", weiId); 
		map.put("state", ParseHelper.toShort(IsPayReward.payed.toString())); 
		countByMap = basicOrdersDao.countByMap(hql.toString(), map);
		map1.put("yiZhiFu", countByMap);
		  
		return map1;
	}
	
	@Override
	public double get_monthTotalMoney(Long weiId, String yearTimeStr,
			String monthTimeStr) {
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
		hql.append("select sum(o.reward) from USupplyChannel o where  o.supplyId=:weiId and  (o.payedReward=:payedReward or o.payedReward is NULL)  and state=1 ");
		map.put("weiId", weiId); 
		map.put("payedReward", ParseHelper.toShort(IsPayReward.noPayed.toString())); 
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
 
	@Override
	public double get_TotalMoney(Long weiId,short dt) {
		String hql="select sum(o.reward) from USupplyChannel o where  o.supplyId=:weiId ";
		Map<String , Object> map=new HashMap<String, Object>();
		map.put("weiId", weiId); 
		if (dt==0) {
			hql+=" and  (o.payedReward=:payedReward or o.payedReward is NULL)  and state=1 ";
			map.put("payedReward", ParseHelper.toShort(IsPayReward.noPayed.toString())); 
		}else{
			hql+=" and o.payedReward=:payedReward   and state=1 ";
			map.put("payedReward", ParseHelper.toShort(IsPayReward.payed.toString())); 
		} 
		List<Object> findByMap = basicOrdersDao.findByMap(hql, map);
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


	@Override
	public PageResult<RewardVO> find_UPlatformServiceOrder(
			Long weiId, String yearTimeStr, String monthTimeStr,
			short isPayReward, Limit limit) { 
		PageResult<USupplyChannel> findPayRewards = basicOrdersDao.findPayRewards(weiId,yearTimeStr, monthTimeStr, isPayReward, limit);
		List<USupplyChannel> list = findPayRewards.getList();
		//获取认证员ID
		List<Long> verifierList=new ArrayList<Long>();
		for (USupplyChannel uSupplyChannel : list) {
			verifierList.add(uSupplyChannel.getVerifier());
		} 
		//去掉重复的
		verifierList=new ArrayList<Long>(new LinkedHashSet<Long>(verifierList));
		DecimalFormat d=new DecimalFormat("0.00");
		//查询认证员信息
		List<UVerifier> ul = basicOrdersDao.find_UVerifierList(verifierList);
		
		//根据weiID获取认证员图片
		PageResult<RewardVO> RewardVOList=new PageResult<RewardVO>();
		List<RewardVO> lists=new ArrayList<RewardVO>();
		for (USupplyChannel usc : list) {
			RewardVO r=new RewardVO();
			r.setChannelType(usc.getChannelType());
			r.setChannelId(usc.getChannelId());
			r.setCompanyName(baseCommonService.getShopNameByWeiId(usc.getWeiId()));
			r.setCreateTime(usc.getCreateTime());
			r.setDemandId(usc.getDemandId());
			r.setPayedReward(usc.getPayedReward());
			r.setReward(d.format(usc.getReward()==null?0:usc.getReward())); 
			r.setVerifier(usc.getVerifier());
			if (ul!=null&&ul.size()>0) {
				for (UVerifier uVerifier : ul) {
					if (usc.getVerifier()!=null&&uVerifier.getWeiId().longValue()==usc.getVerifier().longValue()) {
						r.setVerifierImg(baseCommonService.getShopImageByWeiId(usc.getVerifier()));
						r.setVerifierName(baseCommonService.getShopNameByWeiId(usc.getVerifier()));
					}
				}
			}
			lists.add(r);
		} 
		RewardVOList.setList(lists);
		RewardVOList.setPageCount(findPayRewards.getPageCount());
		RewardVOList.setPageIndex(findPayRewards.getPageIndex());
		RewardVOList.setPageSize(findPayRewards.getPageSize());
		RewardVOList.setTotalCount(findPayRewards.getTotalCount());
		RewardVOList.setTotalPage(findPayRewards.getTotalPage());
		return RewardVOList;
	}



 
	
	
	
}
