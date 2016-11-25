package com.okwei.bean.vo;

public class LoginResult
{
    /**
     * 返回信息
     */
    private String message;
    /**
     * 返回的域名
     */
    private String okweidomain;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 登陆的微店号
     */
    private Long weiid;

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public String getOkweidomain()
    {
        return okweidomain;
    }

    public void setOkweidomain(String okweidomain)
    {
        this.okweidomain = okweidomain;
    }

    public Integer getStatus()
    {
        return status;
    }

    public void setStatus(Integer status)
    {
        this.status = status;
    }

    public Long getWeiid()
    {
        return weiid;
    }

    public void setWeiid(Long weiid)
    {
        this.weiid = weiid;
    }

}
