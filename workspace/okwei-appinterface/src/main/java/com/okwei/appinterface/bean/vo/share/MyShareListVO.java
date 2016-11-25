package com.okwei.appinterface.bean.vo.share;

import java.util.List;

import com.okwei.appinterface.bean.vo.order.BasePageModle;

/**
 * @author fh
 *	我的分享列表
 */
public class MyShareListVO extends BasePageModle{ 
	
	/**
	 * 返回的分享列表实体
	 */
	private List<MyShareVO> list;
	private  int totalCount;
	
	 
	public List<MyShareVO> getList() {
		return list;
	}

	public int getTotalCount() {
		return totalCount;
	}
	/**
	 * 总条数
	 * @return
	 */
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public void setList(List<MyShareVO> list) {
		this.list = list;
	}
	
	
}
