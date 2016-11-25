package com.okwei.dao.activity;

import java.util.Date;
import java.util.List;

import com.okwei.bean.domain.AActProductsShowTime;
import com.okwei.bean.domain.AActivityProducts;
import com.okwei.bean.domain.ARedPackageMoney;
import com.okwei.common.Limit;
import com.okwei.common.PageResult;

public interface IActivityDao {

	/**
	 * 获取活动产品展示列表
	 * @param activiyId
	 * @param classId 分类ID（）
	 * @param limit
	 * @return
	 */
	public PageResult<AActProductsShowTime> find_AActProductsShowTimes(Integer activiyId,Integer classId,Limit limit);
	/**
	 * 获取活动产品信息（活动价/数量）
	 * @param proActIds （AActivityProducts主键ID集合）
	 * @return
	 */
	public List<AActivityProducts> find_AActivityProductsByIds(List<Long> proActIds);
	/**
	 * 得到某一天发放的红包信息
	 * @author zlp
	 * @param date
	 * @param status
	 * @return
	 */
	ARedPackageMoney find_todayRedPackageMoney(Date date, Integer status);
}
