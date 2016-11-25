package com.okwei.company.dao;

import java.util.List;

import com.okwei.bean.domain.PProducts;
import com.okwei.dao.IBaseDAO;

public interface IFullBackDAO extends IBaseDAO{

	List<PProducts> finFullBack(long weiId);

}
