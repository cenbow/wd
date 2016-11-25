package com.okwei.appinterface.bean.dto;

import java.util.List;


@SuppressWarnings("serial")
public class ChildrenAccountParam extends BaseParam{
	private int pageSize;
	private int pageIndex;
	private String password;  //密码
	private String employeeName;  //姓名
	private String supplierName; //供应商名
	private String department;  //部门
	private String phone;  //联系电话
	private String accountId; //子账号id
	private Short isOrderSend;  //是否负责发货
	private Integer status;
	private String linkName;  //联系人
	private Integer location;  //所在地区code(最后一级)
	private String address;  //代理商地址
	private String accountIdList; //子账号id集合
	private Long platformWeiId;  //平台号的微店号
	private Long verifyWeiId;  //认证员的微店号
	private Short from;//子供应商来源，0自己，1认证员推荐
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	 
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
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
	public Short getIsOrderSend() {
		return isOrderSend;
	}
	public void setIsOrderSend(Short isOrderSend) {
		this.isOrderSend = isOrderSend;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getLinkName() {
		return linkName;
	}
	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}
	public Integer getLocation() {
		return location;
	}
	public void setLocation(Integer location) {
		this.location = location;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAccountIdList() {
		return accountIdList;
	}
	public void setAccountIdList(String accountIdList) {
		this.accountIdList = accountIdList;
	}
	public Long getPlatformWeiId() {
		return platformWeiId;
	}
	public void setPlatformWeiId(Long platformWeiId) {
		this.platformWeiId = platformWeiId;
	}
	public Long getVerifyWeiId() {
		return verifyWeiId;
	}
	public void setVerifyWeiId(Long verifyWeiId) {
		this.verifyWeiId = verifyWeiId;
	}
	public Short getFrom() {
		return from;
	}
	public void setFrom(Short from) {
		this.from = from;
	}
 
	
}
