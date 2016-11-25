package com.okwei.appinterface.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.okwei.appinterface.bean.vo.share.DianZanCountVO;
import com.okwei.appinterface.dao.IShareDAO;
import com.okwei.bean.domain.PProducts;
import com.okwei.bean.domain.SMainData;
import com.okwei.bean.domain.SProducts;
import com.okwei.bean.domain.SShareActive;
import com.okwei.bean.domain.SStatics;
import com.okwei.bean.domain.SZambiaActive;
import com.okwei.bean.domain.UCollectionStore;
import com.okwei.bean.enums.CollectionType;
import com.okwei.bean.enums.ShareOnHomePage;
import com.okwei.common.Limit;
import com.okwei.common.PageResult;
import com.okwei.dao.impl.BaseDAO;
import com.okwei.util.ParseHelper;

@Repository("ShareDAO1")
public class ShareDAO  extends BaseDAO implements IShareDAO {


	@Override
	public PageResult<UCollectionStore> findUCollectionStoreList(long weiId,Limit limit) {
		String hql=" from UCollectionStore s where weiId=:weiId and thingType=:thingType  order by s.createTime desc";
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("weiId", weiId); 
		params.put("thingType", ParseHelper.toShort(CollectionType.Share.toString())); 
		return super.findPageResultByMap(hql, limit, params);
	}
	
	
	
	@Override
	public PageResult<SMainData> findSMainDataList(Limit limit) {
		String hql=" from SMainData s where s.onHomePage=:onHomePage and s.status<>-1 order by s.topTime desc,s.updateTime desc";
		Map<String, Object> params=new HashMap<String, Object>();
		// 是否上首页 ShareOnHomePage 枚举   热门列表只展示首页数据
		params.put("onHomePage", ParseHelper.toShort(ShareOnHomePage.Yes.toString())); 
		return super.findPageResultByMap(hql, limit, params);
	}


	@Override
	public List<DianZanCountVO> getDianZanCount(List<Long> shareIds) {
		if (shareIds!=null&&shareIds.size()>0) {
		 List<DianZanCountVO> list=new ArrayList<DianZanCountVO>();
		String sql="select count(1),s.shareId from S_ZambiaActive s where s.shareId in (:shareId) group by s.shareId ";
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("shareId", shareIds); 
		List<Object[]> findSQLByMap = super.findSQLByMap(sql, params);
		if (findSQLByMap!=null&&findSQLByMap.size()>0) {
			for (Object[] objects : findSQLByMap) {
				DianZanCountVO dzcv=new DianZanCountVO();
				dzcv.setCount(objects[0]==null?0:ParseHelper.toInt(objects[0].toString()));
				dzcv.setShareId(objects[1]==null?0:ParseHelper.toInt(objects[1].toString()));
				list.add(dzcv);
			}
		}else{
			return null;
		}
		return list;
		}else{
			return null;
		}
	}
 
	
	@Override
	public PageResult<SShareActive> findSShareActiveList(Long weiId, Limit limit) {
		String hql=" from SShareActive s where s.weiId=:weiId and s.status<>-1 order by s.createTime desc";
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("weiId", weiId); 
		return super.findPageResultByMap(hql, limit, params);
	}

	
	@Override
	public List<SStatics> findSStaticsList(List<Long> shareIds) {
		if (shareIds!=null&&shareIds.size()>0) {
		String hql=" from SStatics s where s.shareId in (:shareId)";
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("shareId", shareIds);  
		return super.findByMap(hql, params);
		}else{
			return null;
		}
	}

	
	@Override
	public List<SMainData> findSMainDataList(List<Long> shareIds) {
		if (shareIds!=null&&shareIds.size()>0) {
		String hql=" from SMainData s where s.shareId in (:shareId)";
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("shareId", shareIds);  
		return super.findByMap(hql, params);
		}else{
			return null;
		}
	}

	@Override
	public List<SProducts> findSProductsList(Long shareIds) {
		String hql=" from SProducts s where s.shareId =? order by s.spid asc";
		return super.find(hql, shareIds);
	}

	@Override
	public List<PProducts> findPProductsList(List<Long> productIds) {
		if (productIds!=null&&productIds.size()>0) {
			String hql=" from PProducts s where s.productId in (:productId)";
			Map<String, Object> params=new HashMap<String, Object>();
			params.put("productId", productIds);  
			return super.findByMap(hql, params);
		}else{
			return null;
		}
	}


	@Override
	public List<SZambiaActive> findSZambiaActiveList(List<Long> shareIds, long weiId) {
		if (shareIds!=null&&shareIds.size()>0) {
		String hql=" from SZambiaActive s where s.shareId in (:shareIds) and s.weiId=:weiId";
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("shareIds", shareIds);  
		params.put("weiId", weiId);  
		return super.findByMap(hql, params);
		}else{
			return null;
		}
	}


	@Override
	public UCollectionStore getUCollectionStore(Long thingId,Long weiId, Short thingType) {
		String hql=" from UCollectionStore s where s.thingId = :shareIds and s.weiId=:weiId and s.thingType=:thingType";
		Map<String, Object> params=new HashMap<String, Object>();
		params.put("shareIds", thingId);  
		params.put("weiId", weiId);   
		params.put("thingType", thingType);  
		List<UCollectionStore> findByMap = super.findByMap(hql, params);
		if (findByMap!=null&&findByMap.size()>0) {
			return findByMap.get(0);
		}else{
			return null;
		}
	}



	@Override
	public List<SMainData> findSMainData(Long pageId) {
		String hql="from SMainData where shareId=?";
		return super.find(hql, pageId);
	}


	
}
