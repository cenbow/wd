package com.okwei.sso.bean.vo;

public class WxLoginTokenWap {
	/// <summary>
    /// token
    /// </summary>
    public String access_token ;

    /// <summary>
    /// 用户openid
    /// </summary>
    public String openid ;
    
    public WxLoginTokenWap(){
    	
    }

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}
    
    
}
