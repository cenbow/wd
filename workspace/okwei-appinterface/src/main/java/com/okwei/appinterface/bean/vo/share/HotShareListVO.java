package com.okwei.appinterface.bean.vo.share;

import java.util.List;

import com.okwei.appinterface.bean.vo.order.BasePageModle;

/**
 * @author fh
 * 热门分享
 */
public class HotShareListVO extends BasePageModle{ 
	
	/**
	 * 返回的分享列表实体
	 */
	private List<HotShareVO> list;
	private  int totalCount;
	
	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public List<HotShareVO> getList() {
		return list;
	}

	public void setList(List<HotShareVO> list) {
		this.list = list;
	}

	 
	
}
