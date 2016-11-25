package com.okwei.service.product;

import java.util.List;

import com.okwei.bean.domain.PBrand;
import com.okwei.bean.vo.product.BrandVO;
import com.okwei.common.PageResult;
import com.okwei.service.IBaseService;

public interface IBrandManagerService extends IBaseService{

	List<PBrand> getBrandList(Long weiId, Integer classId) throws Exception;

	PageResult<BrandVO> getBrandPageResult(Long weiID, Integer classId,
			Integer pageIndex, Integer pageSize)throws Exception;

}
