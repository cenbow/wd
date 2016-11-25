package com.okwei.myportal.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.okwei.bean.domain.AActProductExp;
import com.okwei.bean.domain.AActProducts;
import com.okwei.bean.domain.AActShowProducts;
import com.okwei.bean.domain.AActivity;
import com.okwei.bean.domain.AActivityProducts;
import com.okwei.bean.domain.PProducts;
import com.okwei.bean.vo.activity.AActivityProExtend;
import com.okwei.bean.vo.activity.AActivityProductsResult;
import com.okwei.bean.vo.activity.AActivityResult;
import com.okwei.common.Limit;
import com.okwei.common.PageResult;
import com.okwei.dao.IBaseDAO;
import com.okwei.myportal.service.IActivityService;
import com.okwei.service.activity.IBaseActivityService;
import com.okwei.util.DateUtils;
import com.okwei.util.ImgDomain;

@Service
public class ActivityService implements IActivityService {

	@Autowired
	private IBaseActivityService activityService;
	@Autowired
	IBaseDAO baseDAO;

	public PageResult<AActivityResult> find_Activitylist(Long weiid, int pageIndex, int pageSize) {
		PageResult<AActivity> pageResult = activityService.find_ActivityList(pageIndex, pageSize);
		PageResult<AActivityResult> result = new PageResult<AActivityResult>();
		result.setPageIndex(pageIndex);
		result.setPageSize(pageSize);
		result.setTotalCount(pageResult.getTotalCount());
		result.setTotalPage(pageResult.getTotalPage());
		if (pageResult != null && pageResult.getList() != null && pageResult.getList().size() > 0) {
			List<AActivityResult> list = new ArrayList<AActivityResult>();
			for (AActivity aa : pageResult.getList()) {
				AActivityResult ac = new AActivityResult();
				ac.setActId(aa.getActId());
				ac.setApplyBeginTime(aa.getApplyBeginTime());
				ac.setApplyEndTime(aa.getApplyEndTime());
				ac.setStartTime(aa.getStartTime());
				ac.setEndTime(aa.getEndTime());
				ac.setTitle(aa.getTitle());
				ac.setCreateTime(aa.getCreateTime());
				ac.setState(aa.getState());
				ac.setDemand(aa.getDemand());
				ac.setType(aa.getType());
				if (activityService.count_AActivityProducts(aa.getActId(), weiid,null) > 0) {
					ac.setIsApplied(1);// 是否有报名参加 限时抢购
				}
				Date nowTime = new Date();
				if (nowTime.getTime() < aa.getStartTime().getTime()) {// 未开始
					ac.setStepState(0);
				} else if (nowTime.getTime() >= aa.getStartTime().getTime() && nowTime.getTime() <= aa.getEndTime().getTime()) {
					ac.setStepState(1);// 进行中
				} else {
					ac.setStepState(2);// 已结束
				}
				list.add(ac);
			}
			result.setList(list);
		}
		return result;
	}
	/**
	 * 获取供应商报名产品 通过的总数量
	 * @param actId
	 * @param sellerId
	 * @return
	 */
	public long count_actProductPass(Long actId, Long sellerId){
		String hqlString="select count(*) from AActivityProducts a where a.state=1 and a.actId=? and a.sellerId=? ";
		return baseDAO.count( hqlString,actId,sellerId);
	}

	public 	PageResult<AActivityProductsResult> find_ApplyProductListBySellerID(Long actId, Long sellerId,int pageIndex,int pageSize) {
		PageResult<AActivityProducts> pageResult=activityService.find_ApplyProductListBySellerID(actId, sellerId, pageIndex, pageSize);
		PageResult<AActivityProductsResult> result=new PageResult<AActivityProductsResult>();
		result.setPageIndex(pageIndex);
		result.setPageSize(pageSize);
		result.setTotalCount(pageResult.getTotalCount());
		result.setTotalPage(pageResult.getTotalPage());
		if(pageResult.getList()!=null&&pageResult.getList().size()>0){
			List<AActivityProductsResult> proList=new ArrayList<AActivityProductsResult>();
			for (AActivityProducts aa : pageResult.getList()) {
				AActivityProductsResult re=new AActivityProductsResult();
				re.setActId(aa.getActId());
				re.setClassId(aa.getClassId());
				re.setCommission(aa.getCommission());
				re.setCreateTime(aa.getCreateTime());
				re.setPrice(aa.getPrice());
				re.setProActId(aa.getProActId());
				re.setProductId(aa.getProductId());
				re.setProductTitle(aa.getProductTitle());
				re.setProductImg(ImgDomain.GetFullImgUrl(aa.getProductImg()) );
				re.setTotalCount(aa.getTotalCount());
				re.setState(aa.getState());
				re.setReason(aa.getReason());
				
				PProducts pro=baseDAO.get(PProducts.class, aa.getProductId());
				if(pro!=null&&pro.getDefaultPrice()!=null){
					re.setPriceBf(pro.getDefaultPrice()); 
				}else {
					re.setPriceBf(re.getPrice()); 
				}
				re.setOldPrice(pro.getOriginalPrice()==null?pro.getDefaultPrice():pro.getOriginalPrice()); 
				AActProductExp exp=baseDAO.get(AActProductExp.class, re.getProActId());
				if(exp!=null){
					re.setSelledCount(exp.getSellerCount()==null?0:exp.getSellerCount());
					re.setSelledAmount(exp.getSellerCount()==null?0:(exp.getSellerCount()*re.getPrice()));
				}
				AActShowProducts actShow=baseDAO.get(AActShowProducts.class, re.getProActId());
				if(actShow!=null){//剩余库存（临时库存，不一定成交）
					re.setStockCount(actShow.getStockCount()==null?0:actShow.getStockCount());
				}
				String hql_ex=" from AActProducts a where a.proActId=?";
				List<AActProducts> actlist=baseDAO.find(hql_ex, re.getProActId());
				Date nowtime=new Date();
						
				if(actlist!=null&&actlist.size()>0){
					List<AActivityProExtend> list=new ArrayList<AActivityProExtend>();
					for (AActProducts ee : actlist) {
						AActivityProExtend extend=new AActivityProExtend();
						extend.setBeginTimeDate(ee.getBeginTime());
						extend.setEndtimeDate(ee.getEndTime()); 
						extend.setBeginTime(DateUtils.format(ee.getBeginTime(), "yyyy/MM/dd HH:mm"));
						extend.setEndTime(DateUtils.format(ee.getEndTime(), "yyyy/MM/dd HH:mm"));
						if(ee.getBeginTime().getTime()>nowtime.getTime()){
							extend.setState(0);
						}
						else if (ee.getBeginTime().getTime()<=nowtime.getTime()&&ee.getEndTime().getTime()>nowtime.getTime()) {
							extend.setState(1);//进行中
						}else {
							extend.setState(2);//已结束
						}
						list.add(extend);
					}
					re.setProductExtends(list);
				}
				proList.add(re);
			}
			result.setList(proList);
		}
		return result;
	}
	@Override
	public Long getgetActCelebration() {
		AActivity a= baseDAO.getNotUniqueResultByHql("from AActivity where type=?", 1);
		if(a!=null){
		return a.getActId();
		}
		return null;
	}
}
