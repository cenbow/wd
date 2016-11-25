package com.okwei.dao.impl.user;

import org.springframework.stereotype.Repository;

import com.okwei.dao.impl.BaseDAO;
import com.okwei.dao.user.IBasicPlatformSupplyerDAO;

@Repository
public class BasicPlatformSupplyerDAO extends BaseDAO implements IBasicPlatformSupplyerDAO{

	@Override
	public void deletePlatformSupplyerImg(Long weiId) {
		String hql="delete from UPlatformSupplyerImg where weiId=? ";
		super.executeHql(hql, weiId);
	}

}
