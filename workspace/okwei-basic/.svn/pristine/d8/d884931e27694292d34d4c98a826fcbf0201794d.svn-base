package com.okwei.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.okwei.bean.domain.AppPscoreKing;
import com.okwei.bean.domain.AppPscoreStatics;
import com.okwei.bean.enums.ScoreKingType;
import com.okwei.dao.IScoreDao;
 
@Repository
public class ScoreDao extends BaseDAO  implements IScoreDao  {
	@Override
	public AppPscoreStatics get_AppPscoreStatics(Long productId, ScoreKingType type) {
		List<AppPscoreStatics> list = find_AppPscoreStatics(productId);
		if (list != null && list.size() > 0) {
			for (AppPscoreStatics aa : list) {
				if (aa.getType() != null && aa.getType() == Short.parseShort(type.toString()))
					return aa;
			}
		}
		return null;
	}
	
	public List<AppPscoreStatics> find_AppPscoreStatics(Long productId) {
		if (productId == null || productId <= 0)
			return null;
		String hqlString = " from AppPscoreStatics a where a.productId=? ";
		Object[] bb = new Object[1];
		bb[0] = productId;
		return find(hqlString, bb);
	}
	@Override
	public AppPscoreKing get_AppPscoreKing(Short type,Long productId){
		String hqlString = " from AppPscoreKing a where a.type=? and a.productId=? order by a.score desc";
		Object[] bb = new Object[2];
		bb[0] = type;
		bb[1]=productId;
		return getUniqueResultByHql(hqlString, bb);
	}
	@Override
	public List<AppPscoreKing> find_AppPscoreKings(Integer bmid, Integer areaId) {
		if (bmid != null && bmid > 0) {
			String hqlString = " from AppPscoreKing a where a.mid=? ";
			Object[] bb = new Object[1];
			bb[0] = bmid;
			return find(hqlString, bb);
		} else if (areaId != null && areaId > 0) {
			String hqlString = " from AppPscoreKing a where a.areaId=? ";
			Object[] bb = new Object[1];
			bb[0] = areaId;
			return find(hqlString, bb);
		}
		return null;
	}
}
