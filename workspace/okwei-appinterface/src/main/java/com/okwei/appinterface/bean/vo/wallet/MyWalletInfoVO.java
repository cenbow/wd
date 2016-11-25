package com.okwei.appinterface.bean.vo.wallet;


public class MyWalletInfoVO extends MyWalletBalance{
	
	//是否设置支付密码
	private int isHavePwd;
	//是否实名认证（0未认证，1实名认证中 2实名认证未通过 3已经实名认证）
	private int isVerified;
	private String reason;//不通过理由
	//是否显示对公账号（ 1展示，0不展示）
	private int isPublicBank;
	//公账号状态
	private int isPublicBankStatu;
	// 姓名
	private String realName;
	// 身份证
	private String idCard;
	// 手机号
	private String phone;
	// 正面照（身份证）
	private String photoPositive;
	// 反正照
	private String photoBack;
	// 是否有保证金
	private int isHaveBond;

	
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public int getIsPublicBankStatu() {
		return isPublicBankStatu;
	}
	public void setIsPublicBankStatu(int isPublicBankStatu) {
		this.isPublicBankStatu = isPublicBankStatu;
	}
	public int getIsPublicBank() {
		return isPublicBank;
	}
	public void setIsPublicBank(int isPublicBank) {
		this.isPublicBank = isPublicBank;
	}
	public int getIsHaveBond() {
		return isHaveBond;
	}
	public void setIsHaveBond(int isHaveBond) {
		this.isHaveBond = isHaveBond;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPhotoPositive() {
		return photoPositive;
	}
	public void setPhotoPositive(String photoPositive) {
		this.photoPositive = photoPositive;
	}
	public String getPhotoBack() {
		return photoBack;
	}
	public void setPhotoBack(String photoBack) {
		this.photoBack = photoBack;
	}
	
	public int getIsHavePwd() {
		return isHavePwd;
	}
	public void setIsHavePwd(int isHavePwd) {
		this.isHavePwd = isHavePwd;
	}
	public int getIsVerified() {
		return isVerified;
	}
	public void setIsVerified(int isVerified) {
		this.isVerified = isVerified;
	}
	

}
