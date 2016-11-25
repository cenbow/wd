package com.okwei.myportal.bean.vo;

import java.util.List;

import com.okwei.bean.domain.PBrand;

public class BrandInfoVO {
	/**
	 * 品牌ID
	 */
	private int brandID;
	/**
	 * 微店号
	 */
	private long companyNo;
	/**
	 * 品牌名称
	 */
	private String brandName;
	/**
	 * 品牌logo
	 */
	private String brandLogo;
	/**
	 * 授权书
	 */
	private String authorization;
	/**
	 * 分类信息
	 */
	private List<BrandClassParentVO> cfbVO; 
	/**
	 * 品牌故事
	 */
	private String brandStory;
	/**
	 * 联系人
	 */
	private String linkMan;
	/**
	 * 职务
	 */
	private String job;
	/**
	 * 状态
	 */
	private Integer status;
	/**
	 * 不通过理由
	 */
	private String reason;
	/**
	 * 联系方式
	 */
	private String phone;
	
	
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getBrandID() {
		return brandID;
	}
	public void setBrandID(int brandID) {
		this.brandID = brandID;
	}
	public long getCompanyNo() {
		return companyNo;
	}
	public void setCompanyNo(long companyNo) {
		this.companyNo = companyNo;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public String getBrandLogo() {
		return brandLogo;
	}
	public void setBrandLogo(String brandLogo) {
		this.brandLogo = brandLogo;
	}
	public String getAuthorization() {
		return authorization;
	}
	public void setAuthorization(String authorization) {
		this.authorization = authorization;
	}
	public List<BrandClassParentVO> getCfbVO() {
		return cfbVO;
	}
	public void setCfbVO(List<BrandClassParentVO> cfbVO) {
		this.cfbVO = cfbVO;
	}
	public String getBrandStory() {
		return brandStory;
	}
	public void setBrandStory(String brandStory) {
		this.brandStory = brandStory;
	}
	public String getLinkMan() {
		return linkMan;
	}
	public void setLinkMan(String linkMan) {
		this.linkMan = linkMan;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	
}
