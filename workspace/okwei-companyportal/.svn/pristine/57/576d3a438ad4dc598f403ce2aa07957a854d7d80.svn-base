package com.okwei.company.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.okwei.bean.domain.AHomeMain;
import com.okwei.bean.domain.PProductClass;
import com.okwei.bean.domain.PProducts;
import com.okwei.company.bean.vo.HomeInfo;
import com.okwei.company.bean.vo.HomeMain;
import com.okwei.company.dao.IFullBackDAO;
import com.okwei.company.service.IFullBackService;
import com.okwei.service.impl.BaseService;
import com.okwei.util.ImgDomain;
@Service
public class FullBackService extends BaseService implements IFullBackService{

	@Autowired
	private IFullBackDAO ifullBackDAO;
	
	@Override
	public List<PProducts> findProducts(long weiId) {
		List<PProducts> list=ifullBackDAO.finFullBack(weiId);
		return list;
	}

	 @Override
	    public List<HomeInfo> getHomeProducts() {
		List<PProductClass> classlist = ifullBackDAO.find("from PProductClass where step=1 order by sort asc");
		List<AHomeMain> homelist = ifullBackDAO.find("from AHomeMain where state=1 order by position asc");
		boolean flag = false;
		if (homelist != null && homelist.size() > 0)
		    flag = true;
		if (classlist != null && classlist.size() > 0) {
		    List<HomeInfo> result = new ArrayList<HomeInfo>();
		    for (PProductClass cla : classlist) {
			HomeInfo temp = new HomeInfo();
			temp.setClassId(cla.getClassId());
			temp.setClassName(cla.getClassName());
			if (flag) {
			    List<HomeMain> templist = new ArrayList<HomeMain>();
			    for (AHomeMain home : homelist) {
				if (cla.getClassId().equals(home.getClassId())) {
				    HomeMain temp1 = new HomeMain();
				    temp1.setHomeId(home.getHomeId());
				    temp1.setClassId(home.getClassId());
				    temp1.setClassName(home.getClassName());
				    temp1.setHomeImage(ImgDomain.GetFullImgUrl(home.getHomeImage()));
				    temp1.setPosition(home.getPosition());
				    templist.add(temp1);
				}
			    }
			    temp.setList(templist);
			}
			result.add(temp);
		    }
		    return result;
		}
		return null;
	    }
}
