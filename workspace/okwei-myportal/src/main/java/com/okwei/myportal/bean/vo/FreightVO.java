package com.okwei.myportal.bean.vo;

import java.util.List;

import com.okwei.bean.domain.PPostAgeModel;

public class FreightVO extends PPostAgeModel
{
    private static final long serialVersionUID = 1L;
 /**
     * 邮费详情
     */
    private List<FreightDetailsVO> detailsList;

    public List<FreightDetailsVO> getDetailsList()
    {
        return detailsList;
    }

    public void setDetailsList(List<FreightDetailsVO> detailsList)
    {
        this.detailsList = detailsList;
    }

}
