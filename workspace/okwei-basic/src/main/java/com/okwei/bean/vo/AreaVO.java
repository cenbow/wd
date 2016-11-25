package com.okwei.bean.vo;

import java.util.List;

/**
 * 区域树形结构VO
 */
public class AreaVO implements java.io.Serializable
{
    private static final long serialVersionUID = -8645226327437034111L;
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
    private List<AreaVO> areas;

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

    public List<AreaVO> getAreas()
    {
        return areas;
    }

    public void setAreas(List<AreaVO> areas)
    {
        this.areas = areas;
    }

}
