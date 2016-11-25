package com.okwei.supplyportal.bean.vo;

import java.io.Serializable;

/**
 * 单点登陆实体类
 */
public class LoginUser implements Serializable
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 微店号
     */
    private long weiID;

    /**
     * 微店昵称
     */
    private String weiName;

    /**
     * 头像
     */
    private String weiImg;
    /**
     * 用户身份标识
     */
    private Short weiType;
    
    private Short yunS;
    
    private Short batchS;
    
    private Short yrz;
    
    private Short brz;
    
    private Short yHrz;
    
    private Short bHrz;

    /**
     * 认证服务点
     */
    private Short rzFwd;
    /**
     * 代理商
     */
    private Short agent;

    public Short getAgent()
    {
        return agent;
    }

    public void setAgent(Short agent)
    {
        this.agent = agent;
    }

    public Short getRzFwd()
    {
        return rzFwd;
    }

    public void setRzFwd(Short rzFwd)
    {
        this.rzFwd = rzFwd;
    }
    public Short getYunS() {
		return yunS;
	}

	public void setYunS(Short yunS) {
		this.yunS = yunS;
	}

	public Short getBatchS() {
		return batchS;
	}

	public void setBatchS(Short batchS) {
		this.batchS = batchS;
	}

	public Short getYrz() {
		return yrz;
	}

	public void setYrz(Short yrz) {
		this.yrz = yrz;
	}

	public Short getBrz() {
		return brz;
	}

	public void setBrz(Short brz) {
		this.brz = brz;
	}

	public Short getyHrz() {
		return yHrz;
	}

	public void setyHrz(Short yHrz) {
		this.yHrz = yHrz;
	}

	public Short getbHrz() {
		return bHrz;
	}

	public void setbHrz(Short bHrz) {
		this.bHrz = bHrz;
	}

	public long getWeiID()
    {
        return weiID;
    }

    public void setWeiID(long weiID)
    {
        this.weiID = weiID;
    }

    public String getWeiName()
    {
        return weiName;
    }

    public void setWeiName(String weiName)
    {
        this.weiName = weiName;
    }

    public String getWeiImg()
    {
        return weiImg;
    }

    public void setWeiImg(String weiImg)
    {
        this.weiImg = weiImg;
    }

    public Short getWeiType()
    {
        return weiType;
    }

    public void setWeiType(Short weiType)
    {
        this.weiType = weiType;
    }

}
