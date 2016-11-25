package com.okwei.myportal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.okwei.bean.domain.PcUserAdnotice;
import com.okwei.bean.domain.WebUserAdnotice;
import com.okwei.common.Limit;
import com.okwei.common.PageResult;
import com.okwei.myportal.bean.dto.ShopFaceDTO;
import com.okwei.myportal.dao.IShopFaceMgtDAO;
import com.okwei.myportal.service.IShopFaceMgtService;
import com.okwei.service.impl.BaseService;

@Service
public class ShopFaceMgtService extends BaseService implements IShopFaceMgtService {

	@Autowired
	private IShopFaceMgtDAO shopFaceMgtDAO;

	@Override
	public PageResult<PcUserAdnotice> getShopFaceImgs(ShopFaceDTO dto, Limit limit) {
		return shopFaceMgtDAO.getShopFaceImgs(dto, limit);
	}

	@Override
	public Integer getOpenCount(Long weiId) {
		return shopFaceMgtDAO.getOpenCount(weiId);
	}

}
