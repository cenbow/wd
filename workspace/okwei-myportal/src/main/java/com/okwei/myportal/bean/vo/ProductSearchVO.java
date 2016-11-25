package com.okwei.myportal.bean.vo;

import java.io.Serializable;
import java.util.List;

import com.okwei.bean.domain.PBrand;
import com.okwei.bean.domain.PShopClass;
import com.okwei.bean.domain.UChildrenUser;

public class ProductSearchVO implements Serializable {

	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;

	// 销售中的数量
	private Long count_Showing;
	// 已下架的数量
	private Long count_Drop;
	// 草稿箱的数量
	private Long count_OutLine;
	// 待审核的数量
	private Long count_ToHandle;

	// 一级店铺分类列表
	private List<PShopClass> classList;
	// 二级店铺分类列表
	private List<PShopClass> subClassList;

	// 品牌列表
	private List<PBrand> brandList;

	// 子供应商列表
	private List<UChildrenUser> childrenList;

	public ProductSearchVO() {
	}

	public ProductSearchVO(Long showingCount, Long dropCount, Long outCount, List<PShopClass> classList, List<PBrand> brandList) {
		this.count_Showing = showingCount;
		this.count_Drop = dropCount;
		this.count_OutLine = outCount;
		this.classList = classList;
		this.brandList = brandList;
	}

	public Long getCount_Showing() {
		return count_Showing;
	}

	public void setCount_Showing(Long count_Showing) {
		this.count_Showing = count_Showing;
	}

	public Long getCount_Drop() {
		return count_Drop;
	}

	public void setCount_Drop(Long count_Drop) {
		this.count_Drop = count_Drop;
	}

	public Long getCount_OutLine() {
		return count_OutLine;
	}

	public void setCount_OutLine(Long count_OutLine) {
		this.count_OutLine = count_OutLine;
	}

	public Long getCount_ToHandle() {
		return count_ToHandle;
	}

	public void setCount_ToHandle(Long count_ToHandle) {
		this.count_ToHandle = count_ToHandle;
	}

	public List<PShopClass> getClassList() {
		return classList;
	}

	public void setClassList(List<PShopClass> classList) {
		this.classList = classList;
	}

	public List<PBrand> getBrandList() {
		return brandList;
	}

	public void setBrandList(List<PBrand> brandList) {
		this.brandList = brandList;
	}

	public List<UChildrenUser> getChildrenList() {
		return childrenList;
	}

	public void setChildrenList(List<UChildrenUser> childrenList) {
		this.childrenList = childrenList;
	}

	public List<PShopClass> getSubClassList() {
		return subClassList;
	}

	public void setSubClassList(List<PShopClass> subClassList) {
		this.subClassList = subClassList;
	}

}
