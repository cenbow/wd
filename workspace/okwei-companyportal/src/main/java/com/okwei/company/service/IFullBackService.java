package com.okwei.company.service;

import java.util.List;

import com.okwei.bean.domain.PProducts;
import com.okwei.company.bean.vo.HomeInfo;

public interface IFullBackService {

	List<PProducts> findProducts(long l);
	
	 /**
     * 获取首页衣食住行用的图片
     * 
     * @return
     */
    public List<HomeInfo> getHomeProducts();
	
}
