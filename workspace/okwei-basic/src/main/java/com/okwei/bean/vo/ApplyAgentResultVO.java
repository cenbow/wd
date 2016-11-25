package com.okwei.bean.vo;

import java.io.Serializable;

public class ApplyAgentResultVO implements Serializable
{ 
    private static final long serialVersionUID = -1999441290201606195L;
    /**
     * 返回申請ID
     */
    private Integer agentId;

    public Integer getAgentId()
    {
        return agentId;
    }

    public void setAgentId(Integer agentId)
    {
        this.agentId = agentId;
    }
    
}
