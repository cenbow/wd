package com.okwei.company.dao.Impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.okwei.bean.domain.PShopClass;
import com.okwei.bean.domain.TBussinessClass;
import com.okwei.bean.domain.TMarketImgs;
import com.okwei.bean.domain.UBatchSupplyer;
import com.okwei.common.Limit;
import com.okwei.common.PageResult;
import com.okwei.company.bean.vo.ProductInfoVO;
import com.okwei.company.dao.IbatchMarketDAO;
import com.okwei.dao.impl.BaseDAO;

@Repository
public class BatchMarketDAO extends BaseDAO implements IbatchMarketDAO {

	@Override
	public List<TBussinessClass> getTBussinessClass(Integer bmid) {
		String hql = "select a from TBussinessClass a,TMarketBusList b WHERE b.busId=a.id AND  b.bmid=?";
		Object[] params = new Object[1];
		params[0]=bmid;
		return find(hql, params);
	}

	@Override
	public long getUBatchPortCount(Integer bmid, Short status) {
		String hql = "SELECT COUNT(1) FROM UBatchPort WHERE bmid=? AND status=?";
		Object[] params = new Object[2];
		params[0]=bmid;
		params[1]=status;
		return count(hql, params);
	}

	@Override
	public List<TMarketImgs> getMarketImgsListByBmid(Integer bmid) {
		String hql = "from TMarketImgs WHERE bmid=?";
		Object[] params = new Object[1];
		params[0]=bmid;
		return find(hql, params);
	}

	@Override
	public PageResult<UBatchSupplyer> getUBatchSupplyerPage(Limit buildLimit,Short status,Integer bmid) {
		StringBuffer hql = new StringBuffer();
		hql.append(" FROM UBatchSupplyer a where a.status=:status and a.bmid=:bmid");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("status", status);
		params.put("bmid", bmid);
		hql.append(" ");
		return super.findPageResultByMap(hql.toString(),buildLimit, params);
	}

	@Override
	public List<PShopClass> getShopClassList(long weiID) {
		if(weiID<1)
		{
			return null;
		}
		
		String hql = "from PShopClass p where p.weiid =? and state=1";
		
		return super.find(hql, weiID);
	}

	
}
