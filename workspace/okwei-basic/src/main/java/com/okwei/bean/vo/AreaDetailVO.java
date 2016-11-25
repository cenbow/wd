package com.okwei.bean.vo;

import java.util.List;

public class AreaDetailVO implements java.io.Serializable
{
    private static final long serialVersionUID = 8199580030372630659L;
    /**
     * 区域Id
     */
    private Integer areaId;
    /**
     * 区域名称
     */
    private String areaName;
    /**
     * 区域下级
     */
    private AreaDetailVO area;

    public Integer getAreaId()
    {
        return areaId;
    }

    public void setAreaId(Integer areaId)
    {
        this.areaId = areaId;
    }

    public String getAreaName()
    {
        return areaName;
    }

    public void setAreaName(String areaName)
    {
        this.areaName = areaName;
    }

    public AreaDetailVO getAreas()
    {
        return area;
    }

    public void setAreas(AreaDetailVO area)
    {
        this.area = area;
    }

}
