package com.okwei.pay.bean.vo;

import com.okwei.pay.bean.enums.VerifyCodeTypeEnum;

/**
 * 短信发送VO
 * 
 * @author Administrator
 *
 */
public class SendSMSVO {

    /**
     * 发送的参数
     */
    private String[] param;
    /**
     * 微店号
     */
    private long weiid;
    /**
     * 类型枚举
     */
    private VerifyCodeTypeEnum verify;

    public String[] getParam() {
	return param;
    }

    public void setParam(String[] param) {
	this.param = param;
    }

    public long getWeiid() {
	return weiid;
    }

    public void setWeiid(long weiid) {
	this.weiid = weiid;
    }

    public VerifyCodeTypeEnum getVerify() {
	return verify;
    }

    public void setVerify(VerifyCodeTypeEnum verify) {
	this.verify = verify;
    }

}
