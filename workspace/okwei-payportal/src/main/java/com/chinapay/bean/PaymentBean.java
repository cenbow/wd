/**
 * 
 */
package com.chinapay.bean;

/**
 * @author Jackie.Gao
 *
 */
public class PaymentBean {

	private String merId;
	private String ordId;
	private String transAmt;
	private String curyId;
	private String transDate;
	private String transType;
	private String version;
	private String bgRetUrl;
	private String pageRetUrl;
	private String gateId;
	private String status;
	private String priv1;
	private String userIp;
	private String chkValue;
	
	private String TransTime;
	private String CountryId;
	private String TimeZone;
	private String DSTFlag;
	private String ExtFlag;	
	private String Priv2;	
	
	public PaymentBean(){
		
	}
	
	public String toString(){
		return new StringBuffer("{MerId=").append(merId)
		.append(", OrdId=").append(ordId)
		.append(", TransAmt=").append(transAmt)
		.append(", CuryId=").append(curyId)
		.append(", TransDate=").append(transDate)
		.append(", TransType=").append(transType)
		.append(", Version=").append(version)
		.append(", BgRetUrl=").append(bgRetUrl)
		.append(", PageRetUrl=").append(pageRetUrl)
		.append(", GateId=").append(gateId)
		.append(", Status=").append(status)
		.append(", Priv1=").append(priv1)
		.append(", ClientIP=").append(userIp)
		.append(", ChkValue=").append(chkValue)
		.append(", TransTime=").append(TransTime)
		.append(", CountryId=").append(CountryId)
		.append(", TimeZone=").append(TimeZone)
		.append(", DSTFlag=").append(DSTFlag)
		.append(", ExtFlag=").append(ExtFlag)
		.append(", Priv2=").append(Priv2)
		.append("}").toString();
	}
	
	public String getMerId() {
		return merId;
	}
	public void setMerId(String merId) {
		this.merId = merId;
	}
	public String getOrdId() {
		return ordId;
	}
	public void setOrdId(String ordId) {
		this.ordId = ordId;
	}
	public String getTransAmt() {
		return transAmt;
	}
	public void setTransAmt(String transAmt) {
		this.transAmt = transAmt;
	}
	public String getCuryId() {
		return curyId;
	}
	public void setCuryId(String curyId) {
		this.curyId = curyId;
	}
	public String getTransDate() {
		return transDate;
	}
	public void setTransDate(String transDate) {
		this.transDate = transDate;
	}
	public String getTransType() {
		return transType;
	}
	public void setTransType(String transType) {
		this.transType = transType;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getBgRetUrl() {
		return bgRetUrl;
	}
	public void setBgRetUrl(String bgRetUrl) {
		this.bgRetUrl = bgRetUrl;
	}
	public String getPageRetUrl() {
		return pageRetUrl;
	}
	public void setPageRetUrl(String pageRetUrl) {
		this.pageRetUrl = pageRetUrl;
	}
	public String getGateId() {
		return gateId;
	}
	public void setGateId(String gateId) {
		this.gateId = gateId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPriv1() {
		return priv1;
	}
	public void setPriv1(String priv1) {
		this.priv1 = priv1;
	}
	public String getPriv2() {
		return Priv2;
	}
	public void setPriv2(String priv2) {
		Priv2 = priv2;
	}
	public String getUserIp() {
		return userIp;
	}
	public void setUserIp(String userIp) {
		this.userIp = userIp;
	}
	public String getChkValue() {
		return chkValue;
	}
	public void setChkValue(String chkValue) {
		this.chkValue = chkValue;
	}
	public String getTransTime() {
		return TransTime;
	}
	public void setTransTime(String transTime) {
		TransTime = transTime;
	}
	public String getCountryId() {
		return CountryId;
	}
	public void setCountryId(String countryId) {
		CountryId = countryId;
	}
	public String getTimeZone() {
		return TimeZone;
	}
	public void setTimeZone(String timeZone) {
		TimeZone = timeZone;
	}
	public String getDSTFlag() {
		return DSTFlag;
	}
	public void setDSTFlag(String dSTFlag) {
		DSTFlag = dSTFlag;
	}
	public String getExtFlag() {
		return ExtFlag;
	}
	public void setExtFlag(String extFlag) {
		ExtFlag = extFlag;
	}

}
