package com.okwei.bean.vo.user;

import java.io.Serializable;
import java.util.Date;



public class FriendRequestVo implements Serializable  {
	private static final long serialVersionUID = 1L;
	
	private Long weiNo; //请求方微店号
	private String shopName;//请求方店铺名
	private String shopPicture;//请求方头像
	private Long requestCode;//请求代码
	private String requestTime;//请求时间
	private String requestExtra;//请求附加信息，可为空
	private String replyExtra;//回复附加信息，可为空
	private Short state;//状态，0为未回复，1为已同意，2为已拒绝
	
	public Long getWeiNo() {
		return weiNo;
	}
	public String getShopName() {
		return shopName;
	}
	public String getShopPicture() {
		return shopPicture;
	}
	public Long getRequestCode() {
		return requestCode;
	}
	public String getRequestTime() {
		return requestTime;
	}
	public String getRequestExtra() {
		return requestExtra;
	}
	public String getReplyExtra() {
		return replyExtra;
	}
	public Short getState() {
		return state;
	}
	public void setWeiNo(Long weiNo) {
		this.weiNo = weiNo;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public void setShopPicture(String shopPicture) {
		this.shopPicture = shopPicture;
	}
	public void setRequestCode(Long requestCode) {
		this.requestCode = requestCode;
	}
	public void setRequestTime(String requestTime) {
		this.requestTime = requestTime;
	}
	public void setRequestExtra(String requestExtra) {
		this.requestExtra = requestExtra;
	}
	public void setReplyExtra(String replyExtra) {
		this.replyExtra = replyExtra;
	}
	public void setState(Short state) {
		this.state = state;
	}
}
