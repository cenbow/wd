package com.okwei.myportal.service;

import java.util.List;

import com.okwei.myportal.bean.vo.BaseResultVO;

import net.sf.ehcache.search.impl.BaseResult;

public interface ICustomerQQService {
	
	/**
	 * 获取客服QQ 列表
	 * @param weiID
	 * @return
	 */
	public String[] getCustomerQQs(long weiID);
	
	/**
	 * 保存客服QQ
	 * @param weiID
	 * @param QQs
	 * @return
	 */
	public BaseResultVO saveCustomerQQ(long weiID,String QQs);
}
