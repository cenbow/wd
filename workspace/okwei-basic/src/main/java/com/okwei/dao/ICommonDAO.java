package com.okwei.dao;

import java.util.List;

import com.okwei.bean.domain.TRegional;

public interface ICommonDAO extends IBaseDAO {
	/**
	 * 根据行政级别获取区域列表
	 * @param type
	 * @return
	 */
	List<TRegional> getRegional(Short type);
	/**
	 * 获取全部行政区域列表
	 * @return
	 */
	List<TRegional> getRegional();
}
