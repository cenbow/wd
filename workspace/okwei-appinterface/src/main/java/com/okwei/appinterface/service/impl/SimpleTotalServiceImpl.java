package com.okwei.appinterface.service.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.okwei.appinterface.bean.vo.CprxDomain;
import com.okwei.appinterface.bean.vo.DlscjDomain;
import com.okwei.appinterface.bean.vo.KcyjDomain;
import com.okwei.appinterface.bean.vo.LddcjDomain;
import com.okwei.appinterface.dao.IProductDao;
import com.okwei.appinterface.service.ISimpleTotalService;
import com.okwei.bean.domain.UProductAgent;
import com.okwei.bean.domain.UProductShop;
import com.okwei.bean.domain.UShopInfo;
import com.okwei.bean.domain.UUserAssist;
import com.okwei.bean.domain.UWeiSeller;

@Component("simpleTotalService")
public class SimpleTotalServiceImpl implements ISimpleTotalService {
	
	@Autowired
	private IProductDao baseDao;

	@Override
	public Map<String, Object> queryAgentOrderRank(Long weiId, short dateType, int pageIndex, int pageSize) {
		List<DlscjDomain> resList = new ArrayList<DlscjDomain>() ;
		String dateStr = "" ; //默认累计
		if( dateType == (short)1 ){ //七天
			dateStr = " and b.BalanceTime > date_sub(now(),interval 1 week) " ;
		} else if ( dateType == (short)2 ) { //30天
			dateStr = " and b.BalanceTime > date_sub(now(),interval 1 month) " ;
		}
		String countSql = " select a.supplyId,count(1) "  
	                       +" from U_SupplyChannel a,O_SupplyerOrder b, O_ProductOrder c "
			              +" where a.channelType=1 and a.state=1 and a.weiId=b.SupplyerID and b.state = 4 "+dateStr 
			                +" and b.SupplierOrderID = c.SupplierOrderID "
			                +" and exists(select 1 from P_Products where SupplierWeiID=a.supplyId and productId=c.productId) "
	                        +" and a.supplyId="+weiId +" limit 0,1";
		String sql = " select a.weiId,count(distinct b.supplierOrderId) as orders "  
		              +" from U_SupplyChannel a,O_SupplyerOrder b,O_ProductOrder c  "
				     +" where a.channelType=1 and a.state=1 and a.weiId=b.SupplyerID and b.state = 4 "+dateStr 
				       +" and b.SupplierOrderID = c.SupplierOrderID "
		               +" and exists(select 1 from P_Products where SupplierWeiID=a.supplyId and productId=c.productId) "
		               +" and a.supplyId="+weiId
			      +" group by a.weiId  "
			      +" order by orders desc " 
			         +" limit "+((pageIndex-1)*pageSize)+","+pageSize ;
		long maxPage = 0 ;  //总页数
		List<Object[]> list = baseDao.queryBySql(sql) ;
		short sign = 0 ;
		if(list==null||list.size()==0){
//			String sqlSign = " select a.supplyId,count(1) "
//					          +" from U_ProductAgent a "
//					         +" where a.state = 1 and a.supplyId="+weiId+" group by a.supplyId limit 0,1 ";
			String sqlSign = "select SupplyID,count(1) from U_SupplyChannel where SupplyID ="+weiId+"  and channelType = 1 and state = 1 group by SupplyID " ;
			List<Object[]> signList = baseDao.queryBySql(sqlSign) ;
			if(signList==null||signList.size()<1){
				sign = 2 ; //无代理商
			}else{
				Object[] signs = signList.get(0);
				if(new Long(signs[1].toString())<1){
					sign = 2 ; 
				}else{
					sign = 1 ; //有代理商，无销量
				}
			}
		}else{
			List<Object[]> countList = baseDao.queryBySql(countSql);
		    long count = countList!=null&&countList.size()>0?new Long(countList.get(0)[1].toString()):0;
		    maxPage = (long) Math.ceil(count/pageSize) ;
		    Iterator<Object[]> it = list.iterator() ;
		    String agentIds = "-1";
		    while ( it.hasNext() ) {
		    	Object[] objs = it.next() ;
		    	DlscjDomain domain =  new DlscjDomain() ;
		        agentIds += ","+objs[0] ;
		        domain.setAgentId(objs[0]==null?null:new Long(objs[0].toString())) ;
		        domain.setOrderTotal(objs[1]==null?0:new Integer(objs[1].toString())) ;
		        resList.add(domain) ;
		    }
		    String agentSql=" select a.WeiID,a.companyName,(select name from T_Regional where code=b.city) as city from U_ProductAgent a,U_AgenArea b where a.channelID=b.channelID and a.weiId in ("+agentIds+")" ;
		    System.out.println(agentSql);
		    List<Object[]> agentList = baseDao.queryBySql(agentSql) ;
		    Iterator<Object[]> ita = agentList.iterator() ;
		    while ( ita.hasNext() ) {
		        Object[] agents = ita.next() ;
		        for ( int i = 0 ; i < resList.size() ; i++) {
		    	    DlscjDomain d = resList.get(i) ;
		    	    if( agents[0] != null && agents[0].equals(d.getAgentId())) {
		    	    	d.setAgentName(agents[1]==null?"":agents[1].toString());
		    	    	d.setAgentArea(agents[2]==null?"":agents[2].toString());
		    	    }
		        }
		    }
		}
		Map<String,Object> resMap = new HashMap<String,Object>();
		resMap.put("pageIndex", pageIndex);
		resMap.put("pageSize", pageSize);
		resMap.put("maxPage", maxPage);
		resMap.put("dataType", dateType) ;
		resMap.put("rankList", resList);
		resMap.put("sign", sign) ;
		return resMap;
	}

	@Override
	public Map<String, Object> queryGroundOrderRank(Long weiId, short weiType, short dateType, int pageIndex, int pageSize) {
		List<LddcjDomain> resList = new ArrayList<LddcjDomain>();
		String dateStr = "" ;  //默认累计
		if( dateType == (short)1 ){ //七天
			dateStr = " and b.BalanceTime > date_sub(now(),interval 1 week) " ;
		} else if ( dateType == (short)2 ) {  //30天内
			dateStr = " and b.BalanceTime > date_sub(now(),interval 1 month) " ;
		}
		String sql=null; 
		if ( weiType == (short)1 ){  //当前登录人身份是平台号或者批发号
			sql = " select a.weiID, "  
                        +" (select name from T_Regional where code=a.city) as city, "    
		                +" count(distinct b.supplierOrderId) as orders  "   
		           +" from U_ProductShop a, O_SupplyerOrder b,O_ProductOrder c "
		          +" where a.weiID = b.SupplyerId and b.state = 4 "+dateStr 
		            +" and b.SupplierOrderID = c.SupplierOrderID "
	                +" and exists( select 1 from P_Products where SupplierWeiID=a.supplyId and productId=c.productId ) "
				    +" and exists( select 1 from U_SupplyChannel where weiid=a.weiid and supplyId=a.supplyId and channelType=2 and state=1 ) "
	                +" and a.supplyId = "+weiId  
               +" group by a.weiID "  
               +" order by orders desc " 
		          +" limit "+((pageIndex-1)*pageSize)+","+pageSize ;
		}
		if ( weiType == (short)2 ){  //当前登录人身份代理商
			sql = " select a.weiId, " 
	                    +" (select name from T_Regional where code=a.city) as city, "   
				        +" count(distinct b.supplierOrderId) as orders  "   
		           +" from U_ProductShop a, O_SupplyerOrder b,O_ProductOrder c "
				  +" where b.SupplyerId = a.weiID and b.state = 4 "+dateStr 
				    +" and b.SupplierOrderID = c.SupplierOrderID "
	                +" and exists( select 1 from P_Products where SupplierWeiID=a.supplyId and productId=c.productId ) "
				    +" and exists( select 1 from U_SupplyChannel where weiid=a.weiid and supplyId=a.supplyId and channelType=2 and state=1 ) "
	                +" and exists( select 1 from U_ProductAgent where weiId="+weiId+" and SupplyID=a.SupplyID )   " 
		            +" and exists( select 1  " 
		                          +" from U_AgenArea d   "  
		                         +" where d.city=a.city  " 
		  					       +" and exists( select 1 from U_ProductAgent where weiId="+weiId+" and SupplyID=a.SupplyID and channelID=d.channelID) " 
	     			       	   +" )   " 
	           +" group by a.weiId  order by orders desc " 
		          +" limit "+((pageIndex-1)*pageSize)+","+pageSize ;
		}
		List<Object[]> groundList =  baseDao.queryBySql(sql) ;
		short sign = 0 ;
		if( groundList == null || groundList.size() < 1 ){
			String sqlSign = "" ;
			if( weiType == (short)1 ) { 
				sqlSign = " select a.supplyId,count(1) "
						   +" from U_SupplyChannel a "
						  +" where a.channelType=2 and a.state = 1 and a.supplyId="+weiId +" group by a.supplyId limit 0,1 " ;
			}
			if( weiType == (short)2 ) { 
				sqlSign = " select a.supplyId,count(1) "
						   +" from U_ProductShop a "
						  +" where exists(select 1 from U_SupplyChannel where weiid=a.weiid and supplyId=a.supplyId and channelType=2 and state=1 ) and exists( select 1 from U_ProductAgent where weiId="+weiId+" and SupplyID=a.SupplyID )   " 
						    +" and a.state = 1 "
				            +" and exists( select 1  " 
				                          +" from U_AgenArea d   "  
				                         +" where d.city=a.city  " 
				  					       +" and exists( select 1 from U_ProductAgent where weiId="+weiId+" and SupplyID=a.SupplyID and channelID=d.channelID) " 
			     			       	   +" )  group by a.supplyId limit 0,1" ;
			}
			List<Object[]> signList = baseDao.queryBySql(sqlSign) ;
			if(signList==null||signList.size()<1){
				sign = 2 ; //无落地店
			}else{
				Object[] signs = signList.get(0);
				if(new Long(signs[1].toString())<1){
					sign = 2 ; 
				}else{
					sign = 1 ; //有落地店，无销量
				}
			}
		}else{ 
		    Iterator<Object[]> it = groundList.iterator() ;
		    List<Long> weiList = new ArrayList<Long>() ;
		    while ( it.hasNext() ) {
		    	Object[] objs = it.next() ;
		    	weiList.add( objs[0] == null ? null : new Long(objs[0].toString())) ;
			    LddcjDomain domain = new LddcjDomain();
			    domain.setGroundId(objs[0]==null?null:new Long(objs[0].toString()));
			    domain.setGroundArea(objs[1]==null?"":objs[1].toString());
			    domain.setOrderTotal(objs[2]==null?0:new Integer(objs[2].toString()));
			   resList.add(domain);
		    }
		    Map<String,UShopInfo> shopMap = getShopInfoMap(weiList) ;
		    for ( int i = 0 ; i < resList.size() ; i++ ) {
		    	LddcjDomain ground = resList.get(i) ;
		    	UShopInfo us = shopMap.get(ground.getGroundId()!=null?ground.getGroundId().toString():"") ;
			    ground.setGroundName(us!=null?us.getShopName():"") ;
		    }
		}
		Map<String,Object> resMap = new HashMap<String,Object>();
		resMap.put("pageIndex", pageIndex);
		resMap.put("pageSize", pageSize);
		resMap.put("dateType", dateType);
		resMap.put("rankList", resList);
		resMap.put("sign", sign) ;
		return resMap;
	}

	@Override
	public Map<String, Object> queryHotRank(Long weiId, short weiType, short dateType, int pageIndex, int pageSize) {
		List<CprxDomain> resList = new ArrayList<CprxDomain>();
		String dateStr = "" ;  //默认累计
		if( dateType == (short)1 ){ //七天
			dateStr = " exists ( select 1 from O_SupplyerOrder where supplyerId=a.supplyeriId and BalanceTime > date_sub(now(),interval 1 week) and state=4) and " ;
		} else if ( dateType == (short)2 ) {  //30天内
			dateStr = " exists ( select 1 from O_SupplyerOrder where supplyerId=a.supplyeriId and BalanceTime > date_sub(now(),interval 1 month) and state=4) and " ;
		}
		String sql ="" ;
		if(weiType == 1) {  //平台号品牌号
		     sql =" select a.productId, "
                          +" b.ProductTitle, "
                          +" count(1) as total "
                     +" from O_ProductOrder a,P_Products b  "
                    +" where "+ dateStr
                      +" a.ProductID = b.productId  "
                      +" and exists(select 1 from O_SupplyerOrder m where m.supplierorderid=a.supplierorderid and state=4) "
                      +" and b.state=1 "
                      +" and b.SupplierWeiID ="+weiId+" "
                 +" group by a.productId "
                 +" order by total desc " 
		            +" limit "+((pageIndex-1)*pageSize)+","+pageSize ;
		}
		if(weiType == 2){  //代理商
			sql =" select a.productId, "
                       +" b.ProductTitle, "
                       +" count(1) as total "
                  +" from O_ProductOrder a,P_Products b  "
                 +" where "+ dateStr
                   +" a.ProductID = b.productId  "
                   +" and exists(select 1 from O_SupplyerOrder m where m.supplierorderid=a.supplierorderid and state=4)  "
                   +" and b.state=1 "
                   //+" and b.SupplierWeiID in (select supplyId from U_ProductAgent where weiid="+weiId+") "
                   +" and a.SupplyeriId = "+weiId+" "
              +" group by a.productId "
              +" order by total desc " 
	            +" limit "+((pageIndex-1)*pageSize)+","+pageSize ;
		}
		if(weiType == 3){  //代理商
			sql =" select a.productId, "
                       +" b.ProductTitle, "
                       +" count(1) as total "
                  +" from O_ProductOrder a,P_Products b  "
                 +" where "+ dateStr
                   +" a.ProductID = b.productId  "
                   +" and exists(select 1 from O_SupplyerOrder m where m.supplierorderid=a.supplierorderid and state=4)  "
                   +" and b.state=1 "
                   //+" and b.SupplierWeiID in (select supplyId from U_ProductShop where weiid="+weiId+") "
                   +" and a.SupplyeriId = "+weiId+" "
              +" group by a.productId "
              +" order by total desc " 
	            +" limit "+((pageIndex-1)*pageSize)+","+pageSize ;
		}
		List<Object[]> hotList = baseDao.queryBySql(sql) ;
		short sign = 0 ;
		if( hotList==null || hotList.size()<1 ){
			String sqlSign = "" ;
			if( weiType == (short)1 ) {   //平台号和品牌号
				sqlSign = "select b.SupplierWeiID, "
	                           +" count(1) "
	                      +" from P_Products b  "
	                     +" where b.SupplierWeiID ="+weiId+" "
	                       +" and b.state=1 " ;
			}
			if( weiType == (short)2 || weiType == (short)3 ){  //代理商或落地店
				sqlSign = " select a.weiId, "
						        +" count(1) "
						   +" from P_AgentStock a "
						  +" where a.WeiID = "+weiId
						    +" and exists(select 1 from P_Products where productId=a.productId and state=1)" ;
			}
			List<Object[]> signList = baseDao.queryBySql(sqlSign) ;
			if(signList==null||signList.size()<1){
				sign = 2 ; //无商品
			}else{
				Object[] signs = signList.get(0);
				if(new Long(signs[1].toString())<1){
					sign = 2 ; 
				}else{
					sign = 1 ; //有商品，无库存预警
				}
			}
		}else{
		    Iterator<Object[]> it = hotList.iterator() ;
		    while (it.hasNext()){
			    Object[] objs = it.next() ;
			    CprxDomain domain = new CprxDomain() ;
			    domain.setProductId(objs[0]==null?null:new Long(objs[0].toString()));
			    domain.setProductTitle(objs[1]==null?"":objs[1].toString());
			    domain.setSaleTotal(objs[2]==null?0:new Integer(objs[2].toString()));
			    resList.add(domain);
		    }
		}
		int maxPage = 0 ;
		if( resList == null || resList.size() == 0 ){
			maxPage = pageIndex - 1 ;
		}
		if( resList.size() > 0 && resList.size() < pageSize ){
			maxPage = pageIndex ;
		}
		if( resList.size() == pageSize ){
			maxPage = pageIndex + 1 ;
		}
		Map<String,Object> resMap = new HashMap<String,Object>();
		resMap.put("pageIndex", pageIndex);
		resMap.put("pageSize", pageSize);
		resMap.put("maxPage", maxPage) ;
		resMap.put("dateType", dateType);
		resMap.put("rankList", resList);
		resMap.put("sign", sign);
		return resMap;
	}

	@Override
	public Map<String, Object> queryStockAlert(Long weiId, short weiType, int pageIndex, int pageSize) {
		
		List<KcyjDomain> resList = new ArrayList<KcyjDomain>();
		String hql = " from UUserAssist where weiId =:weiId " ;
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("weiId", weiId);
		List<UUserAssist> userList = baseDao.findByMap(hql, params);
		UUserAssist ua = userList!=null&&userList.size()>0?userList.get(0):null;
		int warning = ua==null || ua.getWarningNum() == null ? 50 : ua.getWarningNum() ;
		String sql = "" ;
		if ( weiType == (short)1 ) {  //登录账号是平台号或者品牌号
			sql = "select b.productId, "
                       +" b.ProductTitle, "
                       +" b.Count as total "
                  +" from P_Products b  "
                 +" where b.SupplierWeiID ="+weiId+" "
                   +" and b.state = 1 "
                   +" and b.Count < "+warning
              +" group by b.productId  "
              +" order by total asc " 
		         +" limit "+((pageIndex-1)*pageSize)+","+pageSize ;
		}
		if( weiType == (short)2 || weiType == (short)3 ){ //代理商或落地店
			sql = " select b.productId, "
                        +" b.ProductTitle, "
                        +" a.stockCount as total "
                   +" from P_AgentStock a,P_Products b "
                  +" where a.productId=b.ProductID "
		            +" and a.WeiID = "+weiId
                    +" and b.state = 1 "
                    +" and a.stockCount < "+warning 
               +" group by b.productId "
               +" order by total asc " 
                  +" limit "+((pageIndex-1)*pageSize)+","+pageSize ;
		}
		List<Object[]> alertList = baseDao.queryBySql(sql) ;
		short sign = 0 ;
		if ( alertList == null || alertList.size() < 1 ) { 
			String sqlSign = "" ;
			if( weiType == (short)1 ) {
				sqlSign = "select b.SupplierWeiID, "
	                           +" count(1) "
	                      +" from P_Products b  "
	                     +" where b.SupplierWeiID ="+weiId+" "
	                       +" and b.state = 1 " ;
			}
			if( weiType == (short)2 || weiType == (short)3 ){
				sqlSign = " select a.weiId, "
						        +" count(1) "
						   +" from P_AgentStock a "
						  +" where a.WeiID = "+weiId
		                    +" and exists(select 1 from P_Products where productId=a.productId and state = 1) ";
			}
			List<Object[]> signList = baseDao.queryBySql(sqlSign) ;
			if(signList==null||signList.size()<1){
				sign = 2 ; //无商品
			}else{
				Object[] signs = signList.get(0);
				if(new Long(signs[1].toString())<1){
					sign = 2 ; 
				}else{
					sign = 1 ; //有商品，无库存预警
				}
			}
		}else{
		    Iterator<Object[]> it = alertList.iterator() ;
		    while (it.hasNext()){
		    	Object[] objs = it.next() ;
		    	Map<String,Object> map = new HashMap<String, Object>();
		    	KcyjDomain domain = new KcyjDomain() ;
		    	domain.setProductId(objs[0]==null?null:new Long(objs[0].toString()));
		    	domain.setProductTitle(objs[1]==null?"":objs[1].toString());
		    	domain.setStock(objs[2]==null?0:new Integer(objs[2].toString()));
			    resList.add(domain);
		    }
		}
		int maxPage = 0 ;
		if( resList == null || resList.size() == 0 ){
			maxPage = pageIndex - 1 ;
		}
		if( resList.size() > 0 && resList.size() < pageSize ){
			maxPage = pageIndex ;
		}
		if( resList.size() == pageSize ){
			maxPage = pageIndex + 1 ;
		}
		Map<String,Object> resMap = new HashMap<String,Object>();
	    resMap.put("pageIndex", pageIndex);
	    resMap.put("pageSize", pageSize);
	    resMap.put("maxPage", maxPage) ;
	    resMap.put("rankList", resList);
	    resMap.put("sign", sign);
	    return resMap;
		
	}
	
	@Override
	public Map<String, Object> querySupplyList(Long weiId, short weiType, int pageIndex, int pageSize) {
		List<Map<String,Object>> resList = new ArrayList<Map<String,Object>>() ;
		int maxPage = 0 ;
		short sign = 0 ;
		String suffer = "" ;
		String sqlSign = "" ;
		if ( weiType == (short)2 ) {  //代理商 
			suffer = " U_ProductAgent " ;
			sqlSign = " select weiid,count(distinct(supplyId)) from U_ProductAgent where weiid="+weiId +" group by weiid ";
		
		}else if ( weiType == (short)3 ) { //落地店
			suffer = " U_ProductShop " ;
			sqlSign = " select weiId,count(distinct(supplyId)) from U_ProductShop where weiid="+weiId +" group by weiid ";
		}
		List<Object[]> signList = baseDao.queryBySql(sqlSign) ;
		if(signList==null||signList.size()<1){
			sign = 2 ; //无供货商
		}else{
			maxPage = new Integer((signList.get(0))[1].toString());
		}
		
		UProductShop us = null ;
		UProductAgent ua = null ;
		String sql = "select m.weiId,m.supplyName,sum(m.total) as total "
	                 +" from (select a.weiId, "
	                              //+" ifnull((select supplyName from U_PlatformSupplyer where weiid=a.weiId),(select supplyName from U_BrandSupplyer  where weiid=a.weiId)) as supplyName, "
	                              +" (select shopName from U_ShopInfo where weiid=a.weiid) as supplyName, "
	                              +" (select count(1) from U_DemandProduct where demandId=a.demandId) as total "
	                         +" from U_SupplyDemand a  "
	                       +"  where exists( select 1 from "+suffer+" where demandId=a.demandId and weiid="+weiId+") "
	                      +" ) m "
	             +" group by m.weiId,m.supplyName "  
		            +" limit "+((pageIndex-1)*pageSize)+","+pageSize ;
		List<Object[]> list = baseDao.queryBySql(sql);
		Iterator<Object[]> it = list.iterator() ;
		while ( it.hasNext() ) {
			Object[] objs = it.next() ;
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("supplyId", objs[0]) ;
			map.put("supplyName", objs[1]) ;
			map.put("goodsTotal", objs[2]) ;
			resList.add(map);
		}
		Map<String,Object> resMap = new HashMap<String,Object>();
		resMap.put("wdghsList", resList);
        resMap.put("pageIndex", pageIndex);
        resMap.put("maxPage", maxPage);
        resMap.put("sign", sign);
        resMap.put("pageSize", pageSize);
		return resMap;
	}
	
	private Map<String,UShopInfo> getShopInfoMap(List<Long> weiList){
		Map<String,UShopInfo> targetMap=new HashMap<String,UShopInfo>();
		if(weiList==null||weiList.size()<1)return targetMap;
	    Map<String,Object> map=new HashMap<String,Object>();

		map.put("weiIds", weiList);
		String hqlOne="from UShopInfo where weiid in (:weiIds) ";
		List<UShopInfo> shopInfoList=baseDao.findByMap(hqlOne, map);
		Map<String,UShopInfo> shopMap=new HashMap<String,UShopInfo>();
		for(int i=0;i<shopInfoList.size();i++){
			shopMap.put(shopInfoList.get(i).getWeiId()+"", shopInfoList.get(i));
		}
		//从U_WeiSeller中获取本次查询关联人的头像及名称
		String hqlTwo="from UWeiSeller where weiid in (:weiIds) ";
		List<UWeiSeller> weiSellerList=baseDao.findByMap(hqlTwo, map);
		Map<String,UWeiSeller> weiMap=new HashMap<String,UWeiSeller>();
		for(int i=0;i<weiSellerList.size();i++){
			weiMap.put(weiSellerList.get(i).getWeiId()+"", weiSellerList.get(i));
		}
		for(int i=0;i<weiList.size();i++){
			String weino=weiList.get(i)+"";
			//根据评论人weino查询可能存在UShopInfo对象或UWeiSeller对象
			UShopInfo us=shopMap.get(weino);
			UWeiSeller uw=weiMap.get(weino);
			if(us!=null&&!"".equals(us)){ //以UShopInfo对象中的信息为优先
				if(us.getShopImg()==null||"".equals(us.getShopImg())){
					us.setShopImg(uw==null?null:uw.getImages());
				}
				if(us.getShopName()==null||"".equals(us.getShopName())){
					us.setShopName(uw==null?"":uw.getWeiName());
				}
			}else{
				us=new UShopInfo();
				if(uw!=null&&!"".equals(uw)){
					us.setShopImg(uw.getImages());
					us.setShopName(uw.getWeiName());
				}
			}
			targetMap.put(weino, us);
		}
		return targetMap;
	} 
	

}
