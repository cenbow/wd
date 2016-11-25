package com.okwei.myportal.bean.vo;

import java.io.Serializable;

public class AgentPayVO implements Serializable
{ 
    private static final long serialVersionUID = -5799526105436272242L;
    /**
     * 认证员微店名
     */
    private String verName;
    /**
     * 认证员微店号
     */
    private Long verifier;
    /**
     * 代理商悬赏金额
     */
    private Double  agentReward;
    /**
     * 申请代理商ID
     */
    private Integer applyID;
    public String getVerName()
    {
        return verName;
    }
    public void setVerName(String verName)
    {
        this.verName = verName;
    }
    public Long getVerifier()
    {
        return verifier;
    }
    public void setVerifier(Long verifier)
    {
        this.verifier = verifier;
    }
    public Double getAgentReward()
    {
        return agentReward;
    }
    public void setAgentReward(Double agentReward)
    {
        this.agentReward = agentReward;
    }
    public Integer getApplyID()
    {
        return applyID;
    }
    public void setApplyID(Integer applyID)
    {
        this.applyID = applyID;
    }
    
}
