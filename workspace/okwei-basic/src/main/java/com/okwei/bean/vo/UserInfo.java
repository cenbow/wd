package com.okwei.bean.vo;

import java.io.Serializable;

/**
 * 用户信息
 * @author yangjunjun
 *
 */
public class UserInfo implements Serializable{

	private Long weiId;//非子供应商账号登陆时的微店号
	
	private String subNo;//登陆的子供应商账号 
	
	private Integer IdentityType;//身份
	
	private Integer pubProductType;//按身份身份发布产品 1-普通微店主、代理商、落地店；2-工厂号、批发号供应商；3-平台号；4-品牌号；5-子账号供应商 6分销体系供应商
	
	private boolean isSub;//是否子供应商账号登陆
	
	private boolean isYun;//是否可上云端产品库
	/** 代理商 **/
    private Short pthdls;
    /** 落地店 **/
    private Short pthldd;
	

	public Short getPthdls() {
		return pthdls;
	}

	public void setPthdls(Short pthdls) {
		this.pthdls = pthdls;
	}

	public Short getPthldd() {
		return pthldd;
	}

	public void setPthldd(Short pthldd) {
		this.pthldd = pthldd;
	}

	public boolean isYun() {
		return isYun;
	}

	public void setYun(boolean isYun) {
		this.isYun = isYun;
	}

	public boolean isSub() {
		return isSub;
	}

	public void setSub(boolean isSub) {
		this.isSub = isSub;
	}

	public String getSubNo() {
		return subNo;
	}

	public void setSubNo(String subNo) {
		this.subNo = subNo;
	}

	public Integer getPubProductType() {
		return pubProductType;
	}

	public void setPubProductType(Integer pubProductType) {
		this.pubProductType = pubProductType;
	}

	public Long getWeiId() {
		return weiId;
	}

	public void setWeiId(Long weiId) {
		this.weiId = weiId;
	}

	public Integer getIdentityType() {
		return IdentityType;
	}

	public void setIdentityType(Integer identityType) {
		IdentityType = identityType;
	}
}
