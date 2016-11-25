package com.okwei.myportal.dao;

import java.util.List;

import com.okwei.bean.domain.UWeiSeller;
import com.okwei.common.Limit;
import com.okwei.common.PageResult;

public interface IDistributorMgtDAO {

	List<UWeiSeller> getMyDistributors(Long userId, Limit limit);

	PageResult<UWeiSeller> getMyDistributorPage(Long userId, Limit limit);

}
