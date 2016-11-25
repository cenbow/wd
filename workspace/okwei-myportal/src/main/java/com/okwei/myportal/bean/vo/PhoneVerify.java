package com.okwei.myportal.bean.vo;

import java.io.Serializable;

public class PhoneVerify implements Serializable
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /**
     * 验证码
     */
    private String code;
    /**
     * 手机号码
     */
    private String phone;
    /**
     * 验证次数
     */
    private Integer count;

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public Integer getCount()
    {
        return count;
    }

    public void setCount(Integer count)
    {
        this.count = count;
    }
}
