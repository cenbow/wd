package com.okwei.myportal.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.okwei.bean.domain.PBrand;
import com.okwei.bean.domain.PParamModel;
import com.okwei.bean.domain.PProductClass;
import com.okwei.bean.domain.PProducts;
import com.okwei.bean.domain.PShopClass;
import com.okwei.bean.enums.BrandStatus;
import com.okwei.bean.enums.CatagoryStepEnum;
import com.okwei.common.Limit;
import com.okwei.common.PageResult;
import com.okwei.dao.impl.BaseDAO;
import com.okwei.myportal.bean.dto.ProductPublishDTO;
import com.okwei.myportal.dao.IProductPublishDAO;
import com.okwei.util.ObjectUtil;

@Repository
public class ProductPublishDAO extends BaseDAO implements IProductPublishDAO {

	@Override
	public List<PProductClass> getCatagoryByStep(CatagoryStepEnum step) {
		String hql = "from PProductClass where step = ?";
		return super.find(hql, (short) step.getStep());
	}

	@Override
	public List<PProductClass> getCatagoryByParentId(Integer parentId) {
		String hql = "from PProductClass where parentId = ? ";
		return super.find(hql, parentId);
	}

	@Override
	public List<PProductClass> getCatagoryByKeyword(String keyword) {
		String hql = "from PProductClass where step = ? and className like ? ";
		return super.find(hql, Short.parseShort(String.valueOf(CatagoryStepEnum.LEVEL3.getStep())), "%" + keyword + "%");
	}

	@Override
	public List<PProductClass> getSiblingsById(Integer id) {
		String hql = "from PProductClass where parentId = (select parentId from PProductClass where classId = ? ) ";
		return super.find(hql, id);
	}

	@Override
	public List<PParamModel> getTemplateByClassId(Long weiId, Integer id) {
		String hql = "from PParamModel where state = 1 and supplierWeiId = ?  and classId = ? ";
		return super.find(hql, weiId, id);
	}

	@Override
	public PageResult<PProducts> getPProducts(ProductPublishDTO param, Limit limit) {
		StringBuilder sb = new StringBuilder("from PProducts where state in (1,3,4)");
		List<Object> params = new ArrayList<Object>();
		if (null != param) {
			if (ObjectUtil.isNotEmpty(param.getProductName())) {
				sb.append(" and productTitle like ?");
				params.add("%" + param.getProductName() + "%");
			}
			if (ObjectUtil.isNotEmpty(param.getProductCode())) {
				sb.append(" and productId = ?");
				params.add(param.getProductCode());
			}
			if (ObjectUtil.isNotEmpty(param.getSupplier())) {
				sb.append(" and supplierWeiId = ?");
				params.add(param.getSupplier());
			}
			if (ObjectUtil.isNotEmpty(param.getClassID())) {
				sb.append(" and classId = ?");
				params.add(param.getClassID());
			}
			if (ObjectUtil.isNotEmpty(param.getShopClassId())) {
				sb.append(" and sid = ?");
				params.add(param.getShopClassId());
			}
		}
		sb.append(" order by UpdateTime desc");
		return super.findPageResult(sb.toString(), limit, params.toArray());
	}

	@Override
	public List<PShopClass> getShopClassByWeiID(Long id) {
		String hql = "from PShopClass where state=1 and weiid = ? ";
		return super.find(hql, id);
	}
	
	@Override
	public List<PBrand> getBrandByCondition(Long weiId, Integer classId, String brandName) {
		if (weiId == null || weiId.longValue() <= 0)
			return null;
		StringBuffer sb = new StringBuffer();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("weiId", weiId);
		sb.append(" from PBrand b where b.companyNo=:weiId ");
		if (classId != null) {
			sb.append(" and b.brandId in "
						+ " (select q.brandId from PClassForBrand q "
						+ " where q.weiId=:weiId  and  q.typeId=:typeId) ");
			map.put("typeId", classId);
		}
		if (brandName != null && StringUtils.isNotEmpty(brandName.trim())) {
			sb.append(" and b.brandName like :brandName");
			map.put("brandName", "%"+brandName.trim()+"%");
		}
		sb.append(" and b.status=:status order by b.sort desc");
		map.put("status", Integer.valueOf(BrandStatus.pass.toString()));
		return findByMap(sb.toString(), map);
	}
}
