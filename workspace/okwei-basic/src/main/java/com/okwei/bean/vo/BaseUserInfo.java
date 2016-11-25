package com.okwei.bean.vo;

import java.io.Serializable;

/**
 * 基础用户信息
 * @author Administrator
 *
 */
public class BaseUserInfo implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3547298293084630209L;
	private String UserName;
	private Long UserID;
	private String Signature;
	private String QRCode;//二维码地址
	private String Photo;//用户头像
	private Integer IdentityType;
	private String CompanyName;
	private Short HasPassWord;
//	/**
//	 * 店铺地址
//	 */
//	private String shopUrl;
	public Integer getIdentityType() {
		return IdentityType;
	}
	public void setIdentityType(Integer identityType) {
		IdentityType = identityType;
	}
	public String getCompanyName() {
		return CompanyName;
	}
	public void setCompanyName(String companyName) {
		CompanyName = companyName;
	}
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public Long getUserID() {
		return UserID;
	}
	public void setUserID(Long userID) {
		UserID = userID;
	}
	public String getSignature() {
		return Signature;
	}
	public void setSignature(String signature) {
		Signature = signature;
	}
	public String getQRCode() {
		return QRCode;
	}
	public void setQRCode(String qRCode) {
		QRCode = qRCode;
	}
	public String getPhoto() {
		return Photo;
	}
	public void setPhoto(String photo) {
		Photo = photo;
	}
	public Short getHasPassWord() {
		return HasPassWord;
	}
	public void setHasPassWord(Short hasPassWord) {
		HasPassWord = hasPassWord;
	}
}
