package com.okwei.appinterface.bean.dto;


@SuppressWarnings("serial")
public class VerifierProductAgentListParam extends BaseParam{
	
	private int status;
	private int pageSize;
	private int pageIndex;
	private String keyword;
	private Integer parId;  //代理商的id
	private Integer demandId;//
	private Integer cityCode;//
	 
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
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
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public Integer getParId() {
		return parId;
	}
	public void setParId(Integer parId) {
		this.parId = parId;
	}
	public Integer getDemandId() {
		return demandId;
	}
	public void setDemandId(Integer demandId) {
		this.demandId = demandId;
	}
	public Integer getCityCode() {
		return cityCode;
	}
	public void setCityCode(Integer cityCode) {
		this.cityCode = cityCode;
	}

	
	
//	public enum Status {
//		// 已通过
//		Pass(1),
//		// 已取消
//		Cancel(2),
//		//待审核
//		pending(0),
//		// 未通过
//		Audit(3);
//
//		private final int step;
//
//		private Status(int step) {
//			this.step = step;
//		}
//
//		@Override
//		public String toString() {
//			return String.valueOf(this.step);
//		}
//	}
}
