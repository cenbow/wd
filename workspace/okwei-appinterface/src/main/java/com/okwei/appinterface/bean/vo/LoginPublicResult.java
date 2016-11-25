package com.okwei.appinterface.bean.vo;

/**
 * 第三方登陆储存的数据
 * 
 * @author Administrator
 *
 */
public class LoginPublicResult implements java.io.Serializable
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String headImg;
    private Short loginType;
    private String nickName;
    private String openID;

    public String getHeadImg()
    {
        return headImg;
    }

    public void setHeadImg(String headImg)
    {
        this.headImg = headImg;
    }

    public Short getLoginType()
    {
        return loginType;
    }

    public void setLoginType(Short loginType)
    {
        this.loginType = loginType;
    }

    public String getNickName()
    {
        return nickName;
    }

    public void setNickName(String nickName)
    {
        this.nickName = nickName;
    }

    public String getOpenID()
    {
        return openID;
    }

    public void setOpenID(String openID)
    {
        this.openID = openID;
    }
}
