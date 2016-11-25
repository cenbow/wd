package com.okwei.myportal.bean.enums;

public enum LoginType
{
    /**
     * qq登陆
     */
    qq(1),
    /**
     * 微信
     */
    weixin(2),
    /**
     * 新浪
     */
    sina(3),
    /**
     * 百度
     */
    baidu(4),
    /**
     * 微店号登陆
     */
    weiid(5),
    /**
     * 手机号
     */
    mobilephone(6);

    private final int Type;

    private LoginType(int step)
    {

        this.Type = step;
    }

    public String toString()
    {
        return String.valueOf(this.Type);
    }
}
