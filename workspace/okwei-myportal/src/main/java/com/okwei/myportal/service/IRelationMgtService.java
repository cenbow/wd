package com.okwei.myportal.service;

import com.okwei.bean.domain.TFansApply;
import com.okwei.bean.vo.ReturnModel;
import com.okwei.common.Limit;
import com.okwei.common.PageResult;
import com.okwei.myportal.bean.dto.FansDTO;
import com.okwei.myportal.bean.dto.RelationDTO;
import com.okwei.myportal.bean.vo.RelationVO;
import com.okwei.service.IBaseService;

public interface IRelationMgtService extends IBaseService {

	PageResult<RelationVO> getUpStream(RelationDTO dto, Limit limit);

	PageResult<RelationVO> getDownStream(RelationDTO dto, Limit limit);

	/**
	 * @author wly
	 * 获取铁杆粉丝
	 * @param buildLimit
	 * @return
	 */
	PageResult<TFansApply> getFans(FansDTO dto,Limit buildLimit);

	/**
	 * 改变审核状态 wly
	 * @param state
	 * @return
	 */
	ReturnModel changeStatus(Short state,Long weiId);

}
