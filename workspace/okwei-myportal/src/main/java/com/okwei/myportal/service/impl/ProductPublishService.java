package com.okwei.myportal.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.okwei.bean.domain.PBrand;
import com.okwei.bean.domain.PParamModel;
import com.okwei.bean.domain.PProductClass;
import com.okwei.bean.domain.PProducts;
import com.okwei.bean.domain.PShopClass;
import com.okwei.bean.enums.BrandStatus;
import com.okwei.bean.enums.CatagoryStepEnum;
import com.okwei.common.Limit;
import com.okwei.common.PageResult;
import com.okwei.myportal.bean.dto.ProductPublishDTO;
import com.okwei.myportal.bean.vo.CatagoryVO;
import com.okwei.myportal.bean.vo.ProductPublishVO;
import com.okwei.myportal.dao.IProductPublishDAO;
import com.okwei.myportal.service.IProductPublishService;
import com.okwei.service.impl.BaseService;
import com.okwei.util.ImgDomain;

@Service
public class ProductPublishService extends BaseService implements IProductPublishService {

	@Autowired
	private IProductPublishDAO productPublishDAO;

	@Override
	public List<PProductClass> getCatagoryByStep(CatagoryStepEnum step) {
		return productPublishDAO.getCatagoryByStep(step);
	}

	@Override
	public List<PProductClass> getCatagoryByParentId(Integer parentId) {
		return productPublishDAO.getCatagoryByParentId(parentId);
	}

	@Override
	public List<Map<String, String>> getCatagoryByKeyword(String keyword) {
		List<Map<String, String>> result = null;
		List<PProductClass> list = productPublishDAO.getCatagoryByKeyword(keyword);
		if (null != list && list.size() > 0) {
			result = new ArrayList<Map<String, String>>();
			for (PProductClass pp : list) {
				Map<String, String> map = new HashMap<String, String>();
				PProductClass parent = productPublishDAO.load(PProductClass.class, pp.getParentId());
				PProductClass ancestry = productPublishDAO.load(PProductClass.class, parent.getParentId());
				StringBuilder sb = new StringBuilder(ancestry.getClassName() + ">>" + parent.getClassName() + ">>" + pp.getClassName());
				map.put("catagoryId", pp.getClassId().toString());
				map.put("path", sb.toString());
				result.add(map);
			}
		}
		return result;
	}

	@Override
	public CatagoryVO getCatagoryByClassId(Long weiId, Integer classId) {
		CatagoryVO vo = new CatagoryVO();
		PProductClass pp = productPublishDAO.load(PProductClass.class, classId);
		vo.setLevel3Id(classId);
		List<PParamModel> templates = productPublishDAO.getTemplateByClassId(weiId, classId);
		vo.setTemplates(templates);
		try {
			if (pp == null || pp.getParentId() == null) {
				return vo;
			}
		} catch (Exception e) {
			List<PProductClass> catagoryList = this.getCatagoryByStep(CatagoryStepEnum.LEVEL1);
			vo.setLevel1(catagoryList);
			return vo;
		}

		vo.setLevel2Id(pp.getParentId());
		List<PProductClass> level3 = productPublishDAO.getCatagoryByParentId(pp.getParentId());
		vo.setLevel3(level3);
		PProductClass pp2 = productPublishDAO.load(PProductClass.class, pp.getParentId());
		if (pp2 == null || pp2.getParentId() == null) {
			return vo;
		}
		vo.setLevel1Id(pp2.getParentId());
		List<PProductClass> level2 = productPublishDAO.getCatagoryByParentId(pp2.getParentId());
		vo.setLevel2(level2);
		PProductClass pp3 = productPublishDAO.load(PProductClass.class, pp2.getParentId());
		if (pp3 == null || pp3.getParentId() == null) {
			return vo;
		}
		List<PProductClass> level1 = productPublishDAO.getCatagoryByParentId(pp3.getParentId());
		vo.setLevel1(level1);
		return vo;
	}

	@Override
	public List<PParamModel> getTemplateByClassId(Long weiId, Integer id) {
		return productPublishDAO.getTemplateByClassId(weiId, id);
	}

	@Override
	public PageResult<ProductPublishVO> getPProducts(ProductPublishDTO param, Limit limit) {
		// TODO Auto-generated method stub
		List<ProductPublishVO> list = new ArrayList<ProductPublishVO>();
		PageResult<PProducts> pageResult = productPublishDAO.getPProducts(param, limit);
		if (null != pageResult) {
			for (PProducts pp : pageResult.getList()) {
				ProductPublishVO vo = new ProductPublishVO();
				vo.setProductId(pp.getProductId());
				vo.setMid(pp.getMid());
				vo.setSid(pp.getSid());
				vo.setClassId(pp.getClassId());
				vo.setDefaultImg(ImgDomain.GetFullImgUrl(pp.getDefaultImg()));
				vo.setProductTitle(pp.getProductTitle());
				vo.setProductMinTitle(pp.getProductMinTitle());
				PShopClass ps = super.getById(PShopClass.class, pp.getSid());
				if (null != ps) {
					vo.setsName(ps.getSname());
				}
				list.add(vo);
			}
			return new PageResult<ProductPublishVO>(pageResult.getTotalCount(), limit, list);
		}
		return new PageResult<ProductPublishVO>();
	}

	@Override
	public List<PShopClass> getShopClassByWeiID(Long id) {
		return productPublishDAO.getShopClassByWeiID(id);
	}

	@Override
	public List<PBrand> getBrandByCondition(Long weiId, Integer classId, String brandName) throws Exception{
		return productPublishDAO.getBrandByCondition(weiId, classId, brandName);
	}

}
