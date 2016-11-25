package com.okwei.service.impl.product;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.okwei.bean.domain.PBrand;
import com.okwei.bean.vo.product.BrandVO;
import com.okwei.common.Limit;
import com.okwei.common.PageResult;
import com.okwei.dao.product.IBaseProductDao;
import com.okwei.service.impl.BaseService;
import com.okwei.service.product.IBrandManagerService;
import com.okwei.util.ImgDomain;
@Service
public class BrandManagerService extends BaseService implements IBrandManagerService{

	@Autowired
	private IBaseProductDao productDao;
	
	@Override
	public List<PBrand> getBrandList(Long weiId, Integer classId)
			throws Exception {
		return productDao.findBrandList(weiId, classId);
	}

	@Override
	public PageResult<BrandVO> getBrandPageResult(Long weiID, Integer classId,
			Integer pageIndex, Integer pageSize) throws Exception {
		Limit limit = Limit.buildLimit(pageIndex, pageSize);
		PageResult<PBrand> pageResult = productDao.findBrandPageResult(weiID, classId,limit);
		if (pageResult == null) {
			return new PageResult<BrandVO>();
		}
		List<BrandVO> brandList = new ArrayList<BrandVO>();
		for (PBrand bd : pageResult.getList()) {
			BrandVO vo = new BrandVO();
			vo.setBrandId(bd.getBrandId());
			vo.setBrandName(bd.getBrandName());
			vo.setBrandImg(ImgDomain.ReplitImgDoMain(bd.getBrandLogo()));
			brandList.add(vo);
		}
		return new PageResult<BrandVO>(pageResult.getTotalCount(),limit,brandList);
	}

}
