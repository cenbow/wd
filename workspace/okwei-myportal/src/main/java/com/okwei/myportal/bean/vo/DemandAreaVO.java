package com.okwei.myportal.bean.vo;

import java.io.Serializable;

/**
 * 招商需求区域
 */
public class DemandAreaVO implements Serializable
{ 
    private static final long serialVersionUID = -9096141877275242583L;
    /**
     * 招商城市区域代码
     */
    private Integer code;
    /**
     * 是否可选（0，不可选【已经招商，下架区域】，1可选）
     */
    private Integer isEnable;
    /**
     * 区域名称
     */
    private String areaName;
    /**
     * 地理区域Id
     */
    private Integer areaID;

    public Integer getAreaID()
    {
        return areaID;
    }

    public void setAreaID(Integer areaID)
    {
        this.areaID = areaID;
    }

    public Integer getCode()
    {
        return code;
    }

    public void setCode(Integer code)
    {
        this.code = code;
    }

    public Integer getIsEnable()
    {
        return isEnable;
    }

    public void setIsEnable(Integer isEnable)
    {
        this.isEnable = isEnable;
    }

    public String getAreaName()
    {
        return areaName;
    }

    public void setAreaName(String areaName)
    {
        this.areaName = areaName;
    }

}
