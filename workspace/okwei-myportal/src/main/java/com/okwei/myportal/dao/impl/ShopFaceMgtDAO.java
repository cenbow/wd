package com.okwei.myportal.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.okwei.bean.domain.PcUserAdnotice;
import com.okwei.common.Limit;
import com.okwei.common.PageResult;
import com.okwei.dao.impl.BaseDAO;
import com.okwei.myportal.bean.dto.ShopFaceDTO;
import com.okwei.myportal.dao.IShopFaceMgtDAO;

@Repository
public class ShopFaceMgtDAO extends BaseDAO implements IShopFaceMgtDAO {

	@Override
	public PageResult<PcUserAdnotice> getShopFaceImgs(ShopFaceDTO dto, Limit limit) {
		Assert.notNull(dto);
		Assert.notNull(limit);
		StringBuilder sb = new StringBuilder("from PcUserAdnotice where 1=1");
		Map<String, Object> params = new HashMap<String, Object>();

		if (null != dto.getWeiId() && dto.getWeiId() > 0) {
			sb.append(" and weiId = :weiId");
			params.put("weiId", dto.getWeiId());
		}
		if (StringUtils.isNotEmpty(dto.getTitle())) {
			sb.append(" and title like :title");
			params.put("title", "%" + dto.getTitle() + "%");
		}
		if (null != dto.getStatus() && !(dto.getStatus().equals(ShopFaceDTO.Status.ALL))) {
			Short status = null;
			switch (dto.getStatus()) {
			case NEW:
				status = 1;
				break;
			case OPEN:
				status = 2;
				break;
			case CLOSE:
				status = 3;
				break;
			default:
				status = 1;
				break;
			}
			sb.append(" and status = :status");
			params.put("status", status);
		}

		sb.append(" order by createTime desc");
		return super.findPageResultByMap(sb.toString(), limit, params);
	}

	@Override
	public Integer getOpenCount(Long weiId) {
		String hql = "select count(*) from PC_UserADNotice where weiId = ? and Status = 2";
		return (int) super.countBySql(hql, weiId);
	}

}
