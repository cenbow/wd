package com.okwei.bean.vo;


public class ChildrenAccountVO {
 
	
	private String accountId;  //子账号
	private String password;  //密码
	private String employeeName;  //姓名
	private String supplierName;  //供应商名(也就是公司名)
	private String department;  //部门
	private String phone;  //联系电话
	private Short isOrderSend;  //是否负责发货
	private int totalProduct;  //发布商品数
	private int waitAuditProduct; //待审核的商品数
	private Long verifyWeiId;  //认证员微店号
	private String verifyName;  //认证员名字
	private String address;  //详细地址
	private Short status;//状态
	private String statusName;//状态名称
	private Integer province;
	private Integer city;
	private Integer area;
	private AreaShowVO location;  //所在地区
	private String linkName;  //联系人
	private String platformName;
	private Long platformId;
	private String noPassReason;
	
	public String getPlatformName() {
		return platformName;
	}
	public void setPlatformName(String platformName) {
		this.platformName = platformName;
	}
	public Long getPlatformId() {
		return platformId;
	}
	public void setPlatformId(Long platformId) {
		this.platformId = platformId;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Short getIsOrderSend() {
		return isOrderSend;
	}
	public void setIsOrderSend(Short isOrderSend) {
		this.isOrderSend = isOrderSend;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public int getTotalProduct() {
		return totalProduct;
	}
	public void setTotalProduct(int totalProduct) {
		this.totalProduct = totalProduct;
	}
	public int getWaitAuditProduct() {
		return waitAuditProduct;
	}
	public void setWaitAuditProduct(int waitAuditProduct) {
		this.waitAuditProduct = waitAuditProduct;
	}
	public Long getVerifyWeiId() {
		return verifyWeiId;
	}
	public void setVerifyWeiId(Long verifyWeiId) {
		this.verifyWeiId = verifyWeiId;
	}
	public String getVerifyName() {
		return verifyName;
	}
	public void setVerifyName(String verifyName) {
		this.verifyName = verifyName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Short getStatus() {
		return status;
	}
	public void setStatus(Short status) {
		this.status = status;
	}
	public AreaShowVO getLocation() {
		return location;
	}
	public void setLocation(AreaShowVO location) {
		this.location = location;
	}
	public String getLinkName() {
		return linkName;
	}
	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	public String getNoPassReason() {
		return noPassReason;
	}
	public void setNoPassReason(String noPassReason) {
		this.noPassReason = noPassReason;
	}
	public Integer getProvince() {
		return province;
	}
	public void setProvince(Integer province) {
		this.province = province;
	}
	public Integer getCity() {
		return city;
	}
	public void setCity(Integer city) {
		this.city = city;
	}
	public Integer getArea() {
		return area;
	}
	public void setArea(Integer area) {
		this.area = area;
	}
}
