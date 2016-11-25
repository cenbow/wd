package com.okwei.myportal.dao;

import java.util.List;

import com.okwei.bean.domain.PBrand;
import com.okwei.bean.domain.PParamModel;
import com.okwei.bean.domain.PProductClass;
import com.okwei.bean.domain.PProducts;
import com.okwei.bean.domain.PShopClass;
import com.okwei.bean.enums.CatagoryStepEnum;
import com.okwei.common.Limit;
import com.okwei.common.PageResult;
import com.okwei.dao.IBaseDAO;
import com.okwei.myportal.bean.dto.ProductPublishDTO;

public interface IProductPublishDAO extends IBaseDAO {

	List<PProductClass> getCatagoryByStep(CatagoryStepEnum step);

	List<PProductClass> getCatagoryByParentId(Integer parentId);

	List<PProductClass> getCatagoryByKeyword(String keyword);

	List<PProductClass> getSiblingsById(Integer id);

	List<PParamModel> getTemplateByClassId(Long weiId, Integer id);

	PageResult<PProducts> getPProducts(ProductPublishDTO param, Limit limit);

	List<PShopClass> getShopClassByWeiID(Long id);

	List<PBrand> getBrandByCondition(Long weiId, Integer classId,
			String brandName);

}
