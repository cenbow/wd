package com.okwei.cartportal.bean;

import java.util.List;

public class KuaiDiModel
{
    private long susupplierID;
    private List<KuaiDi> kList;

    public long getSusupplierID()
    {
        return susupplierID;
    }

    public void setSusupplierID(long susupplierID)
    {
        this.susupplierID = susupplierID;
    }

    public List<KuaiDi> getkList()
    {
        return kList;
    }

    public void setkList(List<KuaiDi> kList)
    {
        this.kList = kList;
    }
}
