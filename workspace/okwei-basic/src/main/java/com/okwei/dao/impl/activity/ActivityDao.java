package com.okwei.dao.impl.activity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.okwei.bean.domain.AActProductsShowTime;
import com.okwei.bean.domain.AActivityProducts;
import com.okwei.bean.domain.ARedPackageMoney;
import com.okwei.bean.domain.PProductClass;
import com.okwei.common.Limit;
import com.okwei.common.PageResult;
import com.okwei.dao.IBaseDAO;
import com.okwei.dao.activity.IActivityDao;
import com.okwei.dao.product.IBaseProductDao;

@Repository
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
public class ActivityDao  implements IActivityDao{
	@Autowired
	private IBaseDAO baseDAO;
	@Autowired
	private IBaseProductDao productDao;
	
	public PageResult<AActProductsShowTime> find_AActProductsShowTimes(Integer activiyId,Integer classId,Limit limit){
		StringBuilder sb = new StringBuilder();
		sb.append(" from AActProductsShowTime a where a.actId=:actId ");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("actId", activiyId);
		if(classId!=null){
			sb.append(" and a.classId in(:classids) ");
			map.put("classids", find_ProductClassIds(classId)); 
		}
		sb.append(" order by a.sort asc ");
		return baseDAO.findPageResult(sb.toString(),limit, map);
	}
	
	public List<AActivityProducts> find_AActivityProductsByIds(List<Long> proActIds){
		String hql=" from AActivityProducts a where a.proActId in(:proActids) ";
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("proActids", proActIds);
		return baseDAO.find(hql, map); 
	}
	
	private List<Integer> find_ProductClassIds(Integer classId){
		List<Integer> list=new ArrayList<Integer>();
		if(classId!=null){
			List<PProductClass> classes=productDao.find_PProductClass(classId);
			if(classes!=null&&classes.size()>0){
				for (PProductClass pp : classes) {
					if(pp.getStep().intValue()==3){
						list.add(pp.getClassId());
					}else {
						List<PProductClass> sublist=productDao.find_PProductClass(pp.getClassId());
						if(sublist!=null&&sublist.size()>0){
							for (PProductClass tt : sublist) {
								list.add(tt.getClassId());
							}
						}
					}
				}
			}else {
				list.add(classId);
			}
		}
		return list;
	}
	
	@Override
	public ARedPackageMoney find_todayRedPackageMoney(Date date,Integer status){
		String hql=" from ARedPackageMoney as a where a.actTime=? and a.status=? ";
		ARedPackageMoney redpacket=baseDAO.getUniqueResultByHql(hql, new Object[] {date, status });
		return redpacket;
	}
	
}
