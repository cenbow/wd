package com.okwei.myportal.service;

import java.util.Map;

import com.okwei.bean.dto.share.SMainDataDTO;
import com.okwei.bean.dto.share.SMainDataDTOs;
import com.okwei.common.Limit;
import com.okwei.common.PageResult;
import com.okwei.myportal.bean.vo.CountMainDataVO;
import com.okwei.myportal.bean.vo.CountShareDetailVO;
import com.okwei.myportal.bean.vo.SMainDataVO;

public interface IShareService {
	/**
	 * 获取分享列表
	 * @param weiID
	 * @param dto
	 * @param limit
	 * @return 
	 */
	public PageResult<SMainDataVO> findSMainDataList(long weiID,SMainDataDTO dto,Limit limit);
	 
	/**
	 * 分享统计详情
	 * @param shareId 
	 * @param weiId 
	 * @param limit
	 * @return
	 */
	public CountShareDetailVO countSharedetails(long weiId,long shareId, Limit limit);
	/**
	 * 统计统计表 中的每种数据数量
	 * @param weiID
	 * @param dto
	 * @return
	 */
	public Map<String, Object> count_Statics(long weiID,SMainDataDTOs dto);
	/**
	 * 获取统计分享列表
	 * @param weiID
	 * @param dto
	 * @param limit
	 * @return
	 */
	public PageResult<CountMainDataVO> findCountMainDataList(long weiID, SMainDataDTOs dto, Limit limit);
}
