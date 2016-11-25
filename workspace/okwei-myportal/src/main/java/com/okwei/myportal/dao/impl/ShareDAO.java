package com.okwei.myportal.dao.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.okwei.bean.domain.PProductAssist;
import com.okwei.bean.domain.SMainData;
import com.okwei.bean.domain.SShareActive;
import com.okwei.bean.domain.SStatics;
import com.okwei.bean.dto.share.SMainDataDTO;
import com.okwei.bean.dto.share.SMainDataDTOs;
import com.okwei.common.Limit;
import com.okwei.common.PageResult;
import com.okwei.dao.impl.BaseDAO;
import com.okwei.myportal.dao.IShareDAO;
import com.okwei.util.ParseHelper;

@Repository("shareDAO2")
public class ShareDAO extends BaseDAO implements IShareDAO  {
 
	
	public Double getOrderCommission(long weiID,List<Long> productIds){
		Map<Integer, Integer> map=new HashMap<Integer, Integer>();
		Map<String, Object> params = new HashMap<String, Object>();
		//OrderStatusEnum  status  为4订单已完成状态
		String sql ="select sum(p.commision) from O_ProductOrder p where p.makerWeiId=:weiId and p.state=4 and p.productId in (:productId) GROUP BY p.productId,p.commision";
		params.put("weiId", weiID );
		params.put("productId", productIds);
		List<Object> findSQLByMap = super.findSQLByMap(sql, params);
		if (findSQLByMap!=null&&findSQLByMap.size()>0) {
			return ParseHelper.toDouble(findSQLByMap.get(0).toString()==null?"0.0":findSQLByMap.get(0).toString())*0.2;
		} 
		return 0.0; 
	} 
	
	public Map<Integer, Integer> getCountOrder(long weiID,List<Long> productIds){
		Map<Integer, Integer> map=new HashMap<Integer, Integer>();
		Map<String, Object> params = new HashMap<String, Object>();
		//OrderStatusEnum  status  为4订单已完成状态
		String sql ="select p.productId,count(p.productId) from O_ProductOrder p where p.makerWeiId=:weiId and p.state=4 and p.productId in (:productId) GROUP BY p.productId";
		params.put("weiId", weiID );
		params.put("productId", productIds );
		List<Object[]> findSQLByMap = super.findSQLByMap(sql, params);
		if (findSQLByMap!=null&&findSQLByMap.size()>0) {
			for (Object[] objects : findSQLByMap) {
				map.put(ParseHelper.toInt(objects[0].toString()),ParseHelper.toInt(objects[1].toString()));
			}  
			return map;
		}else{
			return null;
		}
	} 
	
	@Override
	public Map<String, Object> count_Statics(long weiID, SMainDataDTOs dto) {
		
		if(weiID <1){
			return null;
		}
		Map<String, Object> params = new HashMap<String, Object>();
		String hql ="from SMainData p where p.weiId=:weiId and p.status<>-1 ";
		params.put("weiId", weiID);
		if(dto !=null){
			//是否带条件查询
			if(dto.getTitle() !=null && !"".equals(dto.getTitle())){
				hql += " and p.title like :title ";
				params.put("title", "%" +dto.getTitle() + "%");
			}
			if (dto.getOnHomePage() !=null&&dto.getOnHomePage().shortValue()!=-1) {
				hql += " and p.onHomePage = :onHomePage ";
				params.put("onHomePage", dto.getOnHomePage());
			}
			if (dto.getDateTimeType()!=-1) {
			//  时间条件统计  
			hql += " and p.createTime>:begindate and p.createTime<=:enddate ";
			Calendar cals=Calendar.getInstance();
			switch (dto.getDateTimeType()) {
			 default:
				//今天
				cals.setTime(new Date());
				//小时
				cals.set(Calendar.HOUR_OF_DAY, 0);  
				//分钟
				cals.set(Calendar.MINUTE, 0);  
				//秒
				cals.set(Calendar.SECOND, 0); 
				params.put("begindate", cals.getTime());  
				cals.set(Calendar.HOUR_OF_DAY, 23);  
				cals.set(Calendar.MINUTE, 59);  
				cals.set(Calendar.SECOND, 59);  
				params.put("enddate", cals.getTime());
				break;
			case 2:
				//本周	
				Calendar cals1 = new GregorianCalendar();   
				cals1.setFirstDayOfWeek(Calendar.MONDAY);
				//将时间的时分秒 写死
				//小时
				cals1.set(Calendar.HOUR_OF_DAY, 0);  
				//分钟
				cals1.set(Calendar.MINUTE, 0);  
				//秒
				cals1.set(Calendar.SECOND, 0);  
				//获取取本周的星期一的时间
				cals1.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);  
				params.put("begindate", cals1.getTime().clone());  
				cals1.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);  
				cals1.set(Calendar.HOUR_OF_DAY, 23);  
				cals1.set(Calendar.MINUTE, 59);  
				cals1.set(Calendar.SECOND, 59);  
				params.put("enddate", cals1.getTime());
				break;
			case 3:
				//本月
				 //获取当前月第一天：
		        Calendar c = Calendar.getInstance();    
		        c.add(Calendar.MONTH, 0);
		        //小时
				c.set(Calendar.HOUR_OF_DAY, 0);  
				//分钟
				c.set(Calendar.MINUTE, 0);  
				//秒
				c.set(Calendar.SECOND, 0);
		        c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天 
		        params.put("begindate", c.getTime());  
		        //获取当前月最后一天
		        Calendar ca = Calendar.getInstance();   
		        ca.set(Calendar.HOUR_OF_DAY, 23);  
		        ca.set(Calendar.MINUTE, 59);  
		        ca.set(Calendar.SECOND, 59);
		        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH)); 
		        params.put("enddate", ca.getTime());
				break;
			case 4:
				//进三个月
				cals.setTime(new Date());
				String format = new SimpleDateFormat("MM").format(cals.getTime());
				System.out.println(format);
				cals.roll(Calendar.MONTH, -3);
				int s= Integer.parseInt(format)-3;
				if (s<=0) { 
					cals.roll(Calendar.YEAR, -1);   
				}
				params.put("begindate", cals.getTime());  
				params.put("enddate", new Date());
				break;
			case 5:
				//进六个月
				cals.setTime(new Date());
				format = new SimpleDateFormat("MM").format(cals.getTime());
				System.out.println(format);
				cals.roll(Calendar.MONTH, -6);
				 s= Integer.parseInt(format)-6;
				if (s<=0) { 
					cals.roll(Calendar.YEAR, -1);   
				}
				params.put("begindate", cals.getTime());  
				params.put("enddate", new Date());
				break;
			}  
		}
		}
		List<SMainData> findByMap = super.findByMap(hql, params);
		List<Long> shareId=new ArrayList<Long>();
		Map<String, Object> map=new HashMap<String, Object>();
		if (findByMap!=null&&findByMap.size()>0) {
			for (SMainData sMainData : findByMap) {
				shareId.add(sMainData.getShareId());
			}
		}
		if (shareId!=null&&shareId.size()>0) {
			String sql="select sum(s.pcount),sum(s.AppPV)+sum(s.WapPV)+sum(s.WebPV),sum(s.AppSV)+sum(s.WapSV)+sum(s.WebSV),sum(s.vol),sum(s.turnover) from S_Statics s where  s.weiId=:weiId and s.shareId in(:shareId) ";
			params = new HashMap<String, Object>();
			params.put("weiId", weiID); 
			params.put("shareId", shareId);
			List<Object[]> findSQLByMap = super.findSQLByMap(sql, params);
			if (findSQLByMap!=null&&findSQLByMap.size()>0) {
				 for (Object[] objects : findSQLByMap) {
					 map.put("pcount", objects[0]==null?0:objects[0]);
					 map.put("pv", objects[1]==null?0:objects[1]);
					 map.put("sv", objects[2]==null?0:objects[2]);
					 map.put("vol", objects[3]==null?0:objects[3]);
					 map.put("turnover", objects[4]==null?0:objects[4]); 
				 }   
			} 
		}else{
			 map.put("pcount", 0);
			 map.put("pv", 0);
			 map.put("sv", 0);
			 map.put("vol", 0);
			 map.put("turnover", 0); 
			 return map;
		} 
		return map;
	}

	public List<SStatics> findSStatics(List<Long> shareIds){
		String hql="from SStatics s where s.shareId in (:shareId)";
		Map<String, Object>  params=new HashMap<String, Object>();
		params.put("shareId", shareIds);
		return findByMap(hql, params); 
	}
	
	
	
	public PageResult<SMainData> findSMainDataList(long weiID, SMainDataDTO dto,List<Long> shareIds, Limit limit) {
		String hql=" from SMainData p where 1=1 ";
		Map<String, Object> params=new HashMap<String, Object>();
		if(dto !=null){
			//是否带条件查询
			if(dto.getTitle() !=null && !"".equals(dto.getTitle())){
				hql += " and p.title like :title ";
				params.put("title", "%" +dto.getTitle() + "%");
			}
			if (dto.getOnHomePage() !=null&&dto.getOnHomePage().shortValue()!=-1) {
				hql += " and p.onHomePage = :onHomePage ";
				params.put("onHomePage", dto.getOnHomePage());
			}
		}
		hql += " and p.status<>-1 and p.shareId in (:shareId) ";
		params.put("shareId", shareIds );  
		return super.findPageResultByMap(hql,limit, params);
	}
	/**
	 * 分享功能的统计列表
	 * @param weiID
	 * @param dto
	 * @param limit
	 * @return
	 */
	@Override
	public PageResult<SMainData> findSMainDataLists(long weiID, SMainDataDTOs dto, Limit limit) {
		if(weiID <1){
			return null;
		}
		Map<String, Object> params = new HashMap<String, Object>();
		String hql ="from SMainData p where p.weiId=:weiId and p.status<>-1  ";
		params.put("weiId", weiID);
		if(dto !=null){
			//是否带条件查询
			if(dto.getTitle() !=null && !"".equals(dto.getTitle())){
				hql += " and p.title like :title ";
				params.put("title", "%" +dto.getTitle() + "%");
			}
			if (dto.getOnHomePage() !=null &&dto.getOnHomePage().shortValue()!=-1) {
				hql += " and p.onHomePage = :onHomePage ";
				params.put("onHomePage", dto.getOnHomePage());
			}
			if (dto.getDateTimeType()!=-1) {
			// 时间条件统计  
			hql += " and p.createTime>:begindate and p.createTime<=:enddate ";
			Calendar cals=Calendar.getInstance();
			switch (dto.getDateTimeType()) {
			 default:
				//今天
				cals.setTime(new Date());
				//小时
				cals.set(Calendar.HOUR_OF_DAY, 0);  
				//分钟
				cals.set(Calendar.MINUTE, 0);  
				//秒
				cals.set(Calendar.SECOND, 0); 
				params.put("begindate", cals.getTime());  
				cals.set(Calendar.HOUR_OF_DAY, 23);  
				cals.set(Calendar.MINUTE, 59);  
				cals.set(Calendar.SECOND, 59);  
				params.put("enddate", cals.getTime());
				break;
			case 2:
				//本周	
				Calendar cals1 = new GregorianCalendar();   
				cals1.setFirstDayOfWeek(Calendar.MONDAY);
				//将时间的时分秒 写死
				//小时
				cals1.set(Calendar.HOUR_OF_DAY, 0);  
				//分钟
				cals1.set(Calendar.MINUTE, 0);  
				//秒
				cals1.set(Calendar.SECOND, 0);  
				//获取取本周的星期一的时间
				cals1.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);  
				params.put("begindate", cals1.getTime().clone());  
				cals1.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);  
				cals1.set(Calendar.HOUR_OF_DAY, 23);  
				cals1.set(Calendar.MINUTE, 59);  
				cals1.set(Calendar.SECOND, 59);  
				params.put("enddate", cals1.getTime());
				break;
			case 3:
				//本月
				 //获取当前月第一天：
		        Calendar c = Calendar.getInstance();    
		        c.add(Calendar.MONTH, 0);
		        //小时
				c.set(Calendar.HOUR_OF_DAY, 0);  
				//分钟
				c.set(Calendar.MINUTE, 0);  
				//秒
				c.set(Calendar.SECOND, 0);
		        c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天 
		        params.put("begindate", c.getTime());  
		        //获取当前月最后一天
		        Calendar ca = Calendar.getInstance();   
		        ca.set(Calendar.HOUR_OF_DAY, 23);  
		        ca.set(Calendar.MINUTE, 59);  
		        ca.set(Calendar.SECOND, 59);
		        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH)); 
		        params.put("enddate", ca.getTime());
				break;
			case 4:
				//进三个月
				cals.setTime(new Date());
				String format = new SimpleDateFormat("MM").format(cals.getTime());
				System.out.println(format);
				cals.roll(Calendar.MONTH, -3);
				int s= Integer.parseInt(format)-3;
				if (s<=0) { 
					cals.roll(Calendar.YEAR, -1);   
				}
				params.put("begindate", cals.getTime());  
				params.put("enddate", new Date());
				break;
			case 5:
				//进六个月
				cals.setTime(new Date());
				format = new SimpleDateFormat("MM").format(cals.getTime());
				System.out.println(format);
				cals.roll(Calendar.MONTH, -6);
				 s= Integer.parseInt(format)-6;
				if (s<=0) { 
					cals.roll(Calendar.YEAR, -1);   
				}
				params.put("begindate", cals.getTime());  
				params.put("enddate", new Date());
				break;
			}  
			
			}
		}
		return  super.findPageResultByMap(hql, limit, params);
	}

	
	public List<PProductAssist> find_PProductAssist(List<Long> productIds){
		if (productIds == null || productIds.size() <= 0)
			return null;
		Map<String, Object> map = new HashMap<String, Object>();
		String hql = "from PProductAssist p where p.productId in (:proIds)";
		map.put("proIds", productIds);
		return super.findByMap(hql, map);
	}
	
	public List<Long> findSShareActiveList(Long weiId) {
		List<Long>  list=new ArrayList<Long>();
		String hql="select s.shareId from S_ShareActive s where s.weiId=:weiId and s.status=1 order by s.createTime desc";
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("weiId", weiId); 
		List<Object> findSQLByMap = super.findSQLByMap(hql, params);
		for (Object object : findSQLByMap) {
			list.add(ParseHelper.toLong(object.toString()));
		}
		return list;
	}
	
	public PageResult<SShareActive> findSShareActiveList(Long weiID,Limit limit){
		String hql="from SShareActive s where s.weiId=:weiId and s.status=1 order by s.createTime desc";
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("weiId", weiID); 
		return  super.findPageResultByMap(hql, limit, params);
	}
	
	public List<SShareActive> findSShareActiveList(long weiId,List<Long> shareIds){ 
		String hql="from SShareActive s where s.shareId in(:shareIds) and s.status=1 and s.weiId=:weiId order by s.createTime desc";
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("shareIds", shareIds); 
		params.put("weiId", weiId); 
		return super.findByMap(hql, params);
	}
	
	

	@Override
	public List<Long> findSShareActiveList(long weiId) {
		String hql="select s.shareId from SShareActive s where s.status=1 and s.weiId=? order by s.createTime desc";
		List<Long> findSQLByMap = super.find(hql, weiId);
		return findSQLByMap;
	}

	
	
	@Override
	public List<SMainData> findSMainDataList(List<Long> shareIds) {
		String hql=" from SMainData s where s.shareId in (:shareId)";
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("shareId", shareIds);  
		return super.findByMap(hql, params);
	}
	
	@Override
	public PageResult<SMainData> findSMainDataList(long weiID, SMainDataDTO dto, Limit limit){
		if(weiID <1){
			return null;
		}
		Map<String, Object> params = new HashMap<String, Object>();
		String hql ="from SMainData p where p.weiId=:weiId and p.status<>-1 ";
		params.put("weiId", weiID);
		boolean flag=false; 
		if(dto !=null){
			//是否带条件查询
			if(dto.getTitle() !=null && !"".equals(dto.getTitle())){
				hql += " and p.title like :title ";
				params.put("title", "%" +dto.getTitle() + "%");
				flag=true;
			}
//			if(dto.getStatus() !=null && dto.getStatus().shortValue() >-1){
//				hql += " and p.status=:status ";
//				params.put("status", 1);
//			}
			if(dto.getOnHomePage() !=null&&dto.getOnHomePage().shortValue()!=-1){				
				hql += " and p.onHomePage=:onHomePage ";
				params.put("onHomePage", dto.getOnHomePage());
				flag=true;
			}	
		}
		
		if (flag) {
			//带条件时查询所有自己(分享的和转载的 ) 分享Id
			List<Long> findSShareActiveList = findSShareActiveList(weiID);
			hql += " and p.shareId in (:shareId) ";
			params.put("shareId", findSShareActiveList);
			PageResult<SMainData> findPageResultByMap = super.findPageResultByMap(hql, limit, params);
			return findPageResultByMap;
		}else{
			//没带条件时 根据自己（分享Id） 排序
			PageResult<SShareActive> findSShareActiveList = findSShareActiveList(weiID, limit);
			List<SShareActive> list = findSShareActiveList.getList();
			if (list!=null&&list.size()>0) {
				List<Long> SShareList=new ArrayList<Long>();
				for (SShareActive sShareActive : list) {
					SShareList.add(sShareActive.getShareId());
				}  
				hql += " and p.shareId in (:shareId) ";
				params.put("shareId", SShareList);
			}
			PageResult<SMainData> findPageResultByMap = new PageResult<SMainData>();
			List<SMainData> findByMap = super.findByMap(hql, params);
			findPageResultByMap.setList(findByMap);
			findPageResultByMap.setPageCount(findSShareActiveList.getPageCount());
			findPageResultByMap.setPageIndex(findSShareActiveList.getPageIndex());
			findPageResultByMap.setPageSize(findSShareActiveList.getPageSize());
			findPageResultByMap.setTotalCount(findSShareActiveList.getTotalCount());
			findPageResultByMap.setTotalPage(findSShareActiveList.getTotalPage());
			return findPageResultByMap;
		}
	}

	@Override
	public Map<Double, Integer> getCountOrders(long weiID, long shareId) {
			List<Long> productIds=new ArrayList<Long>();
			Map<String, Object> params = new HashMap<String, Object>();
			String sql1 ="select p.productId from S_Products p where p.weiId=:weiId and p.shareId=:shareId ";
			params.put("weiId", weiID );
			params.put("shareId", shareId );
			List<Long> findSProducts = super.findSQLByMap(sql1, params);
			if (findSProducts!=null&&findSProducts.size()>0) {
				productIds=findSProducts;
				Map<Double, Integer> map=new HashMap<Double, Integer>();
				params = new HashMap<String, Object>();
				//OrderStatusEnum  status  为4订单已完成状态
				String sql ="select count(p.productId),sum(p.count*p.price) from O_ProductOrder p where p.makerWeiId=:weiId and p.state=4 and p.productId in (:productId) ";
				params.put("weiId", weiID );
				params.put("productId", productIds );
				List<Object[]> findSQLByMap = super.findSQLByMap(sql, params);
				if (findSQLByMap!=null&&findSQLByMap.size()>0) {
					for (Object[] objects : findSQLByMap) {
						map.put(objects[1]==null?0:ParseHelper.toDouble(objects[1].toString()),objects[0]==null?0:ParseHelper.toInt(objects[0].toString()));
					}  
					return map;
				}else{
					return null;
				}	
			}else{
				return null;
			}
	}

	 


	
	
	
	
}
