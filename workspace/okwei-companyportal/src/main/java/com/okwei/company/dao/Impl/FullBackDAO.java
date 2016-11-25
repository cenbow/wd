package com.okwei.company.dao.Impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.okwei.bean.domain.PProducts;
import com.okwei.company.dao.IFullBackDAO;
import com.okwei.dao.impl.BaseDAO;

@Repository
public class FullBackDAO extends BaseDAO implements IFullBackDAO{

	@Override
	public List<PProducts> finFullBack(long weiId) {
		return super.find("from PProducts where supplierWeiId=? and state=1", weiId);
	}

}
