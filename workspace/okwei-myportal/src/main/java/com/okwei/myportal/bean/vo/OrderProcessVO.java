package com.okwei.myportal.bean.vo;

import java.util.List;

public class OrderProcessVO
{
    /**
     * 当前步骤
     */
    private int steps;
    /**
     * 步骤长度
     */
    private int processLength;
    /**
     * 步骤
     */
    private List<ProcessModelVO> proList;

    public int getSteps()
    {
        return steps;
    }

    public void setSteps(int steps)
    {
        this.steps = steps;
    }

    public int getProcessLength()
    {
        return processLength;
    }

    public void setProcessLength(int processLength)
    {
        this.processLength = processLength;
    }

    public List<ProcessModelVO> getProList()
    {
        return proList;
    }

    public void setProList(List<ProcessModelVO> proList)
    {
        this.proList = proList;
    }

}
