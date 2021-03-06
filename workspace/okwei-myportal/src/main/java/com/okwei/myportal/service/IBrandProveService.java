package com.okwei.myportal.service;

import java.util.List;

import com.okwei.bean.domain.PBrand;
import com.okwei.bean.domain.PClassForBrand;
import com.okwei.common.Limit;
import com.okwei.common.PageResult;
import com.okwei.myportal.bean.dto.BrandProveDTO;
import com.okwei.myportal.bean.vo.BaseResultVO;
import com.okwei.myportal.bean.vo.BrandClassParentVO;
import com.okwei.myportal.bean.vo.BrandInfoVO;
import com.okwei.myportal.bean.vo.BrandListVO;

public interface IBrandProveService {
	
	/**
	 * 获取品牌列表
	 * @param weiID
	 */
	public PageResult<BrandListVO> getBrandList(long weiID,BrandProveDTO dto,Limit limit);
	
	/**
	 * 获取品牌信息
	 * @param brandID
	 * @return
	 */
	public BrandInfoVO getBrand(int brandID,long weiID);
	
	/**
	 * 保存品牌信息
	 * @param param
	 * @return
	 */
	public BaseResultVO saveBrand(PBrand param,String parentType);
	
	/**
	 * 获取品牌分类列表 （两层系统分类）
	 * @return
	 */
	public List<BrandClassParentVO> getBrandClassList();
	
	/***
	 * 获取品牌管理的分类列表
	 * @param brandID
	 * @return
	 */
	public List<PClassForBrand> getClassForBrands(int brandID);

}
