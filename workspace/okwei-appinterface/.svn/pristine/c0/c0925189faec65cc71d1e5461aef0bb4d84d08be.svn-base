package com.okwei.appinterface.service;

import com.okwei.appinterface.bean.vo.BaseImageMsg;
import com.okwei.bean.vo.LoginUser;
import com.okwei.bean.vo.ReturnModel;

public interface BaseCommonService {
	/*
	 * 获取昵称
	 */
	public String getNickNameById(Long weiNo);
	
	/*
	 * 获取头像
	 */
	public String getImageById(Long weiNo);
	/**
	 * 获取店铺主营
	 * @param weiNo
	 * @return
	 */
	public String getBusContentById(Long weiNo);
	
	/*
	 * 是否能发布能销售的产品
	 */
	public boolean isSaleProduct(Long weiNo);
	
	/*
	 * 是否没有是没有交钱的供应商
	 */
	public boolean isNoPayShop(Long weiNo);
	 
	
	/**
	 * 图片路径返回
	 * @param imgurl
	 * @return
	 */
	public BaseImageMsg getImageMsg(String imgurl);
	
	/**
	 * 获取分享链接
	 * @param type
	 * @param loginWeiid
	 * @param param
	 * @return
	 */
	public ReturnModel getShareUrl(int type,LoginUser loginWeiid, String param);
}
