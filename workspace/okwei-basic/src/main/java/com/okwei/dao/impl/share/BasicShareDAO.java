package com.okwei.dao.impl.share;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.okwei.bean.domain.SProducts;
import com.okwei.bean.domain.SShareActive;
import com.okwei.bean.domain.SSingleDesc;
import com.okwei.common.Limit;
import com.okwei.common.PageResult;
import com.okwei.dao.impl.BaseDAO;
import com.okwei.dao.share.IBasicShareDAO;
import com.okwei.util.RedisUtil;

@Repository
public class BasicShareDAO extends BaseDAO implements IBasicShareDAO  {

	@Override
	public List<SSingleDesc> findSSingleDescByProducts(long spid) {
		Map<String, Object> params = new HashMap<String, Object>();
		String hql ="from SSingleDesc p where p.spid=:spid";
		params.put("spid", spid);  
		return super.findByMap(hql, params);
	}
	
	@Override
	public PageResult<SProducts> findSProductsList(long shareId, Limit limit) {
		Map<String, Object> params = new HashMap<String, Object>();
		String hql ="from SProducts p where p.shareId=:shareId and p.productId not in (select ps.productId from PProducts ps where ps.productId in (select pp.productId from  SProducts pp where pp.shareId=:shareId ) and ps.state<>1)";
		params.put("shareId", shareId); 
			hql+=" order by p.spid asc";
		return super.findPageResultByMap(hql, limit, params);
	}
	
	public PageResult<SProducts> findSProductsListRedis(long shareId, Limit limit) {
		String keyName="sp_product_"+shareId+"_index_"+limit.getStart()+"_size_"+limit.getSize();
		PageResult<SProducts> result= (PageResult<SProducts>)RedisUtil.getObject(keyName);
		if(result!=null&&result.getList()!=null&&result.getList().size()>0){
			return result;
		}else {
			result=findSProductsList( shareId,  limit);
			RedisUtil.setObject(keyName, result, 3600);
			return result;
		}
	}
	
	public List<Long> findSProductsByProductIds(long shareId){
		Map<String, Object> params = new HashMap<String, Object>();
		String sql ="select p.productId from S_Products p where p.shareId=:shareId order by p.spid asc";
		params.put("shareId", shareId);   
		return super.findSQLByMap(sql, params);
	}
	
	
	@Override
	public SShareActive getSShareActive(long shareId, long weiId){
		 String hql=" from SShareActive s where s.shareId=? and s.weiId=? ";
		 return super.getUniqueResultByHql(hql, shareId,weiId);
	}

	@Override
	public void updateShareActive(long shareId, long shareOne) {
		String sql="update S_ShareActive s set s.status=-1 where s.shareId=? and s.weiId=?";
		super.executeSql(sql, shareId,shareOne);
	}


	
}
