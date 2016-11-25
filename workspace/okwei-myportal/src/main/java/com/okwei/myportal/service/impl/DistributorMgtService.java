package com.okwei.myportal.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.okwei.bean.domain.UWeiSeller;
import com.okwei.bean.enums.ProductStatusEnum;
import com.okwei.common.Limit;
import com.okwei.common.PageResult;
import com.okwei.myportal.bean.vo.DistributorVO;
import com.okwei.myportal.dao.IDistributorMgtDAO;
import com.okwei.myportal.service.IDistributorMgtService;
import com.okwei.service.impl.BaseService;

@Service
public class DistributorMgtService extends BaseService implements IDistributorMgtService {

	@Autowired
	private IDistributorMgtDAO distributorMgtDAO;

	@Override
	public PageResult<DistributorVO> getMyDistributors(Long userId, Limit limit) {
		PageResult<UWeiSeller> page = distributorMgtDAO.getMyDistributorPage(userId, limit);
		if (null == page) {
			return new PageResult<DistributorVO>();
		}
		List<DistributorVO> voList = new ArrayList<DistributorVO>();
		for (UWeiSeller seller : page.getList()) {
			DistributorVO vo = new DistributorVO();
			vo.setId(seller.getWeiId());
			vo.setName(seller.getWeiName());
			vo.setQq(seller.getQq());
			vo.setDate(seller.getRegisterTime());
			vo.setRegion("广东深圳");
			vo.setCommission_m(100);
			vo.setCommission_t(200);
			vo.setCount(110);
			voList.add(vo);
		}
		ProductStatusEnum enum1=null;
		return new PageResult<DistributorVO>(page.getTotalCount(), limit, voList);
		
	}

}
