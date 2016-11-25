package com.okwei.myportal.service;

import com.okwei.common.Limit;
import com.okwei.common.PageResult;
import com.okwei.myportal.bean.vo.DistributorVO;
import com.okwei.service.IBaseService;

public interface IDistributorMgtService extends IBaseService {

	PageResult<DistributorVO> getMyDistributors(Long userId, Limit limit);

}
