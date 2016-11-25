package com.okwei.appinterface.bean.vo.share;

import java.util.List;

import com.okwei.appinterface.bean.vo.order.BasePageModle;
import com.okwei.bean.vo.share.SProductsVO;

/**
 * @author fh
 *
 */
public class ShareProductListVO extends BasePageModle { 
	/**
	 * 商品列表
	 */
	private List<ProductVO> list;
	
	private int totalCount; 
	
	
	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public List<ProductVO> getList() {
		return list;
	}

	public void setList(List<ProductVO> list) {
		this.list = list;
	}
	
	
	
}
