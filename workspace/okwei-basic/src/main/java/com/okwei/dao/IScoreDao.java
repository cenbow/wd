package com.okwei.dao;

import java.util.List;

import com.okwei.bean.domain.AppPscoreKing;
import com.okwei.bean.domain.AppPscoreStatics;
import com.okwei.bean.enums.ScoreKingType;


public interface IScoreDao extends IBaseDAO {

	AppPscoreStatics get_AppPscoreStatics(Long productId, ScoreKingType type);

	AppPscoreKing get_AppPscoreKing(Short type, Long productId);

	List<AppPscoreKing> find_AppPscoreKings(Integer bmid, Integer areaId);
	
}
