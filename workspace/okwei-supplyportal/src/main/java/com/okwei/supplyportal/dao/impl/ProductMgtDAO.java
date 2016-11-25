package com.okwei.supplyportal.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.okwei.bean.domain.PProducts;
import com.okwei.dao.impl.BaseDAO;
import com.okwei.supplyportal.dao.IProductMgtDAO;

@Repository
public class ProductMgtDAO extends BaseDAO implements IProductMgtDAO {

	@Override
	public List<PProducts> getProducts() {
		// TODO Auto-generated method stub
		return null;
	}

}
