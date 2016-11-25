package com.okwei.myportal.service;

import com.okwei.bean.domain.PcUserAdnotice;
import com.okwei.common.Limit;
import com.okwei.common.PageResult;
import com.okwei.myportal.bean.dto.ShopFaceDTO;
import com.okwei.service.IBaseService;

public interface IShopFaceMgtService extends IBaseService {

	PageResult<PcUserAdnotice> getShopFaceImgs(ShopFaceDTO dto, Limit limit);

	Integer getOpenCount(Long weiId);
}
