package com.okwei.appinterface.bean.dto;

@SuppressWarnings("serial")
public class ProductParam extends BaseParam {

	private short state;// 1 销售中的产品，4 仓库中的产品，3 草稿箱，6待审核商品仅针对平台号子供应商
	private Short type;// 产品类型:0分销，1自营，4代理，5落地分销 ;对应ShelveType枚举 6：自营+分销
	private int pageSize;
	private int pageIndex;
	private String weiNo;
	private int from;
	private String keyword;
	private Integer classId;

	public short getState() {
		return state;
	}

	public void setState(short state) {
		this.state = state;
	}

	public Short getType() {
		return type;
	}

	public void setType(Short type) {
		this.type = type;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public String getWeiNo() {
		return weiNo;
	}

	public void setWeiNo(String weiNo) {
		this.weiNo = weiNo;
	}

	public int getFrom() {
		return from;
	}

	public void setFrom(int from) {
		this.from = from;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public Integer getClassId() {
		return classId;
	}

	public void setClassId(Integer classId) {
		this.classId = classId;
	}

}
