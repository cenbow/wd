package com.okwei.myportal.dao;

import java.util.List;

import com.okwei.bean.domain.TFansApply;
import com.okwei.bean.domain.UAttention;
import com.okwei.bean.domain.UAttentioned;
import com.okwei.bean.domain.UWeiSeller;
import com.okwei.common.Limit;
import com.okwei.common.PageResult;
import com.okwei.dao.IBaseDAO;
import com.okwei.myportal.bean.dto.FansDTO;
import com.okwei.myportal.bean.dto.RelationDTO;
import com.okwei.myportal.bean.vo.RelationVO;

public interface IUAttentionDAO extends IBaseDAO {

	List<UAttention> getUAttentions(long weiId);
	
	List<UAttentioned> getUAttentioneds(long weiId);

	PageResult<RelationVO> getSupplyer(RelationDTO dto, List<Long> weiIds, Limit limit);
	
	PageResult<UWeiSeller> getDistributor(RelationDTO dto, List<Long> weiIds, Limit limit);

	List<UWeiSeller> getLower(long weiId);

	PageResult<TFansApply> getFans(FansDTO dto,Limit buildLimit);

	void changestatues(Short state, Long weiId);
}
