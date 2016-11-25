package com.okwei.detail.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.okwei.bean.domain.PProductClass;
import com.okwei.bean.domain.TCountStat;
import com.okwei.detail.bean.vo.HeadInfo;
import com.okwei.detail.bean.vo.LeftMenu;
import com.okwei.detail.dao.IProductDao;
import com.okwei.detail.service.ICommonService;
import com.okwei.detail.util.LocalRedisUtil;
import com.okwei.service.impl.BaseService;

@Service
public class CommonService extends BaseService implements ICommonService {

    @Autowired
    private IProductDao baseDao;

    @Override
    public long getCartCount(long weiid) {
	if (weiid > 0) {
	    String hql = "select count(1) from TShoppingCar where weiId=? and status=1";
	    return baseDao.count(hql, new Object[] { weiid });
	}
	return 0;
    }

    @Override
    public HeadInfo getHeadInfo() {
	HeadInfo info = null;
	try {
	    info = (HeadInfo) LocalRedisUtil.getObject("NewHeadInfo");
	} catch (Exception e) {
	}
	if (info == null) {
	    info = new HeadInfo();
	    // 获取用户数量
	    info.setUserCount(getUserCount());
	    // 获取左边菜单
	    info.setLeftMenu(getLeftMenu());
	    LocalRedisUtil.setObject("NewHeadInfo", info);
	}
	return info;
    }

    public List<LeftMenu> getLeftMenu() {
	List<LeftMenu> result = new ArrayList<LeftMenu>();
	String hql = "from PProductClass where step in(1,2,3) order by sort";
	List<PProductClass> list = baseDao.find(hql);
	// 一级分类
	for (PProductClass one : list) {
	    if (one.getStep().shortValue() == 1) {
		LeftMenu tempOne = new LeftMenu();
		tempOne.setId(one.getClassId());
		tempOne.setName(one.getClassName());
		List<LeftMenu> oneList = new ArrayList<LeftMenu>();
		// 二级分类
		for (PProductClass two : list) {
		    if (two.getParentId().equals(one.getClassId())) {
			LeftMenu tempTwo = new LeftMenu();
			tempTwo.setId(two.getClassId());
			tempTwo.setName(two.getClassName());
			List<LeftMenu> twoList = new ArrayList<LeftMenu>();
			// 三级分类
			for (PProductClass three : list) {
			    if (three.getParentId().equals(two.getClassId())) {
				LeftMenu tempThree = new LeftMenu();
				tempThree.setId(three.getClassId());
				tempThree.setName(three.getClassName());
				twoList.add(tempThree);
			    }
			}
			tempTwo.setList(twoList);
			oneList.add(tempTwo);
		    }
		}
		tempOne.setList(oneList);
		result.add(tempOne);
	    }
	}
	return result;
    }

    /**
     * 获取用户的数量
     * 
     * @return
     */
    private long getUserCount() {
	String hql = "from TCountStat where name='UserCount'";
	TCountStat entity = baseDao.getUniqueResultByHql(hql);
	if (entity != null && entity.getCountNum().longValue() > 0)
	    return entity.getCountNum() / 10000;
	return 0;
    }

}
