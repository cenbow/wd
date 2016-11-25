package com.okwei.service.user;

import java.util.List;

import com.okwei.bean.domain.TFansApply;
import com.okwei.bean.domain.TRegional;
import com.okwei.bean.vo.RegionVO;
import com.okwei.common.PageResult;
import com.okwei.service.IBaseService;

public interface IFansBasicService extends IBaseService{

	/**
	 * 获取粉丝 所在所有的市
	 * @return
	 */
//	List<RegionVO> find_FansCityslist();
	List<TRegional> find_FansCityslist();
	/**
	 * 获取粉丝列表
	 * @param provinceCode
	 * @param cityCode
	 * @param districtCode
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	PageResult<TFansApply> find_TFansResult(Integer provinceCode, Integer cityCode,Integer districtCode, int pageIndex,int pageSize);

	/**
	 * 获取粉丝总数
	 * @param status
	 * @return
	 */
	long count_fansAll(Integer status);
}
