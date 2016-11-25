package com.okwei.myportal.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.okwei.bean.domain.UWeiSeller;
import com.okwei.common.Limit;
import com.okwei.common.PageResult;
import com.okwei.dao.impl.BaseDAO;
import com.okwei.myportal.dao.IDistributorMgtDAO;

@Repository
public class DistributorMgtDAO extends BaseDAO implements IDistributorMgtDAO {

    @Override
    public List<UWeiSeller> getMyDistributors(Long userId, Limit limit) {
	String hql = "from UWeiSeller where sponsorWeiId = ? ";
	return super.findPage(hql, limit.getStart(), limit.getSize(), userId);
    }

    @Override
    public PageResult<UWeiSeller> getMyDistributorPage(Long userId, Limit limit) {
	String hql = "from UWeiSeller where sponsorWeiId = ? ";
	return super.findPageResult(hql, limit, userId);
    }
}
