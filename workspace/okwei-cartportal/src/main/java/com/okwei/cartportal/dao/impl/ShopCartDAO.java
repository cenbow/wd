package com.okwei.cartportal.dao.impl;

import org.springframework.stereotype.Repository;

import com.okwei.bean.domain.PClassProducts;
import com.okwei.bean.domain.UBatchSupplyer;
import com.okwei.bean.domain.UPushMessage;
import com.okwei.bean.domain.UYunSupplier;
import com.okwei.cartportal.dao.IShopCartDAO;
import com.okwei.dao.impl.BaseDAO;

@Repository
public class ShopCartDAO extends BaseDAO implements IShopCartDAO {

	@Override
	public boolean isSaleProduct(Long weiNo) {
		if(weiNo == null)
			return false;
		UYunSupplier ysup = get(UYunSupplier.class, weiNo);
		if (ysup != null) {
			if(ysup.getStatus()!=null && ysup.getStatus()==4)
				return true;
		}
		UBatchSupplyer bsup = get(UBatchSupplyer.class, weiNo);
		if (bsup != null) {
			if(bsup.getStatus()!=null && bsup.getStatus()==4)
				return true;
		}
		
		return false;
	}

	@Override
	public boolean insertSendPushMsg(UPushMessage msg) {
		//回写数据库
		save(msg);
		return true;
	}

	@Override
	public PClassProducts getClassProducts(Long supplierWeiId, Long prodId) {
		String hql = "from PClassProducts p where p.weiId=? and p.productId=? and p.state=1";
		return super.getUniqueResultByHql(hql, new Object[] { supplierWeiId, prodId });
	}



}
