package com.okwei.bean.vo;

public class ChildAccountListVO {
   private String accountId;//子帐号
   private String accountName;//员工姓名或供应商公司名称  
   private String department; //所在部门
   private String mobilePhone;//联系电话
   private Short status;//状态：2待审核3已通过4未通过
   private Integer accountType;//账号类型，0员工子帐号；1子供应商
   private String verifyWeiId;

	public String getVerifyWeiId() {
	return verifyWeiId;
}
public void setVerifyWeiId(String verifyWeiId) {
	this.verifyWeiId = verifyWeiId;
}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public Short getStatus() {
		return status;
	}
	public void setStatus(Short status) {
		this.status = status;
	}
	public String getMobilePhone() {
		return mobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	public Integer getAccountType() {
		return accountType;
	}
	public void setAccountType(Integer accountType) {
		this.accountType = accountType;
	}
   
}
