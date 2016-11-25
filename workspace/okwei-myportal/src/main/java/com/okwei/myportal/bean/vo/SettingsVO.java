package com.okwei.myportal.bean.vo;

/**
 * 安全设置基础VO
 */
public class SettingsVO
{
    /**
     * 登陆密码设置（0未设置，1已设置）
     */
    private int loginPwd;
    /**
     * 支付密码设置（0未设置，1已设置）
     */
    private int payPwd;
    /**
     * 是否绑定手机（0未绑定，1已绑定）
     */
    private int bindPhone;
    /**
     * 是否绑定邮箱（0未绑定，1已绑定）
     */
    private int bindMail;
    /**
     * 是否认证实名制（0未认证，1已认证）
     */
    private int realVerify;
    /**
     * 绑定的手机号码
     */
    private String phoneStr;
    /**
     * 绑定的邮箱
     */
    private String mailStr;
    /**
     * 认证的真实姓名
     */
    private String realName;

    public int getLoginPwd()
    {
        return loginPwd;
    }

    public void setLoginPwd(int loginPwd)
    {
        this.loginPwd = loginPwd;
    }

    public int getPayPwd()
    {
        return payPwd;
    }

    public void setPayPwd(int payPwd)
    {
        this.payPwd = payPwd;
    }

    public int getBindPhone()
    {
        return bindPhone;
    }

    public void setBindPhone(int bindPhone)
    {
        this.bindPhone = bindPhone;
    }

    public int getBindMail()
    {
        return bindMail;
    }

    public void setBindMail(int bindMail)
    {
        this.bindMail = bindMail;
    }

    public int getRealVerify()
    {
        return realVerify;
    }

    public void setRealVerify(int realVerify)
    {
        this.realVerify = realVerify;
    }

    public String getPhoneStr()
    {
        return phoneStr;
    }

    public void setPhoneStr(String phoneStr)
    {
        this.phoneStr = phoneStr;
    }

    public String getMailStr()
    {
        return mailStr;
    }

    public void setMailStr(String mailStr)
    {
        this.mailStr = mailStr;
    }

    public String getRealName()
    {
        return realName;
    }

    public void setRealName(String realName)
    {
        this.realName = realName;
    }

}
