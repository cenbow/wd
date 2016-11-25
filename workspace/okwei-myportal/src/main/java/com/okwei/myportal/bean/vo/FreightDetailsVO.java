package com.okwei.myportal.bean.vo;

import com.okwei.bean.domain.PPostAgeDetails;

public class FreightDetailsVO extends PPostAgeDetails
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /**
     * 例外区域的名称
     */
    private String areaName;

    public String getAreaName()
    {
        return areaName;
    }

    public void setAreaName(String areaName)
    {
        this.areaName = areaName;
    }
}
