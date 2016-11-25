package com.okwei.bean.vo.user;

import java.io.Serializable;

/**
 * 渠道支付数量
 */
public class ChannelCountRewardVO implements Serializable
{ 
    private static final long serialVersionUID = -2070893220890226496L;
    /**
     * 未支付代理商悬赏笔数
     */
    private Integer noPayAgentReward;
    /**
     * 已支付代理商悬赏笔数
     */
    private Integer payAgentReward;
    /**
     * 未支付落地店悬赏笔数
     */
    private Integer noPayStoreReward;
    /**
     * 已支付落地店悬赏笔数
     */
    private Integer payStoreReward;

    public Integer getNoPayAgentReward()
    {
        return noPayAgentReward;
    }

    public void setNoPayAgentReward(Integer noPayAgentReward)
    {
        this.noPayAgentReward = noPayAgentReward;
    }

    public Integer getPayAgentReward()
    {
        return payAgentReward;
    }

    public void setPayAgentReward(Integer payAgentReward)
    {
        this.payAgentReward = payAgentReward;
    }

    public Integer getNoPayStoreReward()
    {
        return noPayStoreReward;
    }

    public void setNoPayStoreReward(Integer noPayStoreReward)
    {
        this.noPayStoreReward = noPayStoreReward;
    }

    public Integer getPayStoreReward()
    {
        return payStoreReward;
    }

    public void setPayStoreReward(Integer payStoreReward)
    {
        this.payStoreReward = payStoreReward;
    }

}
