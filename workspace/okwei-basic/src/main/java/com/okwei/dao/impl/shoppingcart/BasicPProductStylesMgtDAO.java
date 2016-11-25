package com.okwei.dao.impl.shoppingcart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.okwei.bean.domain.PProductStyles;
import com.okwei.dao.impl.BaseDAO;
import com.okwei.dao.shoppingcart.IBasicPProductStylesMgtDAO;

@Repository
public class BasicPProductStylesMgtDAO extends BaseDAO implements IBasicPProductStylesMgtDAO {

	@Override
	public PProductStyles getPProductStyles(long stylesId) {
		// TODO Auto-generated method stub
		String hql = "from PProductStyles p where p.stylesId=?";// 查款式是否存在
		return super.getUniqueResultByHql(hql, stylesId);// 获取款式
	}

	@Override
	public PProductStyles getPProductStyles(long stylesId, long productId) {
		// TODO Auto-generated method stub
		String hql = "from PProductStyles where stylesId = ? and productId = ?";
		return super.getUniqueResultByHql(hql, stylesId,productId);
	}

	@Override
	public List<PProductStyles> getPProductStyles(List<Long> styleList) {
		// TODO Auto-generated method stub
		if(styleList == null || styleList.size() == 0)
		{
			return new ArrayList<PProductStyles>();
		}
		String hql = "from PProductStyles p where p.stylesId in(:styleList)";// 查询商品款式
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("styleList", styleList);
		return super.findByMap(hql,map);
	}
}
