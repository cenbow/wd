package com.okwei.myportal.service;

import java.util.List;
import java.util.Map;

import com.okwei.bean.domain.PBrand;
import com.okwei.bean.domain.PParamModel;
import com.okwei.bean.domain.PProductClass;
import com.okwei.bean.domain.PShopClass;
import com.okwei.bean.enums.CatagoryStepEnum;
import com.okwei.common.Limit;
import com.okwei.common.PageResult;
import com.okwei.myportal.bean.dto.ProductPublishDTO;
import com.okwei.myportal.bean.vo.CatagoryVO;
import com.okwei.myportal.bean.vo.ProductPublishVO;
import com.okwei.service.IBaseService;

public interface IProductPublishService extends IBaseService {

	List<PProductClass> getCatagoryByStep(CatagoryStepEnum step);

	List<PProductClass> getCatagoryByParentId(Integer parentId);

	List<Map<String, String>> getCatagoryByKeyword(String keyword);

	CatagoryVO getCatagoryByClassId(Long weiId, Integer classId);

	List<PParamModel> getTemplateByClassId(Long weiId, Integer id);

	PageResult<ProductPublishVO> getPProducts(ProductPublishDTO param, Limit limit);

	List<PShopClass> getShopClassByWeiID(Long id);

	List<PBrand> getBrandByCondition(Long weiId,Integer classId, String brandName) throws Exception;

}
