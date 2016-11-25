package com.okwei.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.okwei.bean.domain.TRegional;
import com.okwei.dao.ICommonDAO;
@Repository
public class CommonDAO extends BaseDAO implements ICommonDAO {

	@Override
	public List<TRegional> getRegional(Short type) {
		// TODO 自动生成的方法存根
		String hql = " from TRegional t where t.level=?";// 查询语句
		Object[] b = new Object[1];
		b[0] = type;
		return super.find(hql, b);
	}

	@Override
	public List<TRegional> getRegional() {
		// TODO 自动生成的方法存根
		String hql = " from TRegional t";// 查询语句
		return super.find(hql);
	}

}
