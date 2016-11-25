package com.okwei.supplyportal.service;

import java.util.List;
import java.util.Map;

import com.okwei.bean.domain.PParamModel;
import com.okwei.bean.domain.PProductClass;
import com.okwei.bean.domain.PShopClass;
import com.okwei.bean.enums.CatagoryStepEnum;
import com.okwei.common.Limit;
import com.okwei.common.PageResult;
import com.okwei.service.IBaseService;
import com.okwei.supplyportal.bean.dto.ProductDTO;
import com.okwei.supplyportal.bean.vo.CatagoryVO;
import com.okwei.supplyportal.bean.vo.ProductVO;

public interface IProductPublishService extends IBaseService {

	List<PProductClass> getCatagoryByStep(CatagoryStepEnum step);

	List<PProductClass> getCatagoryByParentId(Integer parentId);

	List<Map<String, String>> getCatagoryByKeyword(String keyword);

	CatagoryVO getCatagoryByClassId(Long weiId, Integer classId);

	List<PParamModel> getTemplateByClassId(Long weiId, Integer id);

	PageResult<ProductVO> getPProducts(ProductDTO param, Limit limit);

	List<PShopClass> getShopClassByWeiID(Long id);

}
