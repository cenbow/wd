package com.okwei.bean.vo.user;

import java.io.Serializable;
import java.util.Date;

/**
 * 申请结果
 */
public class ApplyResultVO implements Serializable
{ 
    private static final long serialVersionUID = -3787240437661711985L;
    /**
     * 代理商申請Id
     */
    private Integer agentId;
    /**
     * 代理商微店号
     */
    private Long agentWeiId;
    /**
     * 微店名
     */
    private String shopName;
    /**
     * 上级供应商微店号
     */
    private Long merchantWeiId;
    /**
     * 发展人微店号
     */
    private Long developmentWeiId;
    /**
     * 申请时间
     */
    private Date applyTime;
    /**
     * 1已审核，2已取消，0待审核,3已拒绝
     */
    private Short status;
    /**
     * 该地区是否有代理商，1是0否
     */
    private Short currentAreaHasAgent;
    /**
     * 该地区是否在招商，1是0否
     */
    private Short currentAreaHasRecruitment;
    /**
     * 审核拒绝理由
     */
    private String statusReson;
    /**
     * 跟进认证员
     */
    private Long followVerifier;

    public Long getFollowVerifier()
    {
        return followVerifier;
    }

    public void setFollowVerifier(Long followVerifier)
    {
        this.followVerifier = followVerifier;
    }

    public Integer getAgentId()
    {
        return agentId;
    }

    public void setAgentId(Integer agentId)
    {
        this.agentId = agentId;
    }

    public Long getAgentWeiId()
    {
        return agentWeiId;
    }

    public void setAgentWeiId(Long agentWeiId)
    {
        this.agentWeiId = agentWeiId;
    }

    public String getShopName()
    {
        return shopName;
    }

    public void setShopName(String shopName)
    {
        this.shopName = shopName;
    }

    public Long getMerchantWeiId()
    {
        return merchantWeiId;
    }

    public void setMerchantWeiId(Long merchantWeiId)
    {
        this.merchantWeiId = merchantWeiId;
    }

    public Long getDevelopmentWeiId()
    {
        return developmentWeiId;
    }

    public void setDevelopmentWeiId(Long developmentWeiId)
    {
        this.developmentWeiId = developmentWeiId;
    }

    public Date getApplyTime()
    {
        return applyTime;
    }

    public void setApplyTime(Date applyTime)
    {
        this.applyTime = applyTime;
    }

    public Short getStatus()
    {
        return status;
    }

    public void setStatus(Short status)
    {
        this.status = status;
    }

    public Short getCurrentAreaHasAgent()
    {
        return currentAreaHasAgent;
    }

    public void setCurrentAreaHasAgent(Short currentAreaHasAgent)
    {
        this.currentAreaHasAgent = currentAreaHasAgent;
    }

    public Short getCurrentAreaHasRecruitment()
    {
        return currentAreaHasRecruitment;
    }

    public void setCurrentAreaHasRecruitment(Short currentAreaHasRecruitment)
    {
        this.currentAreaHasRecruitment = currentAreaHasRecruitment;
    }

    public String getStatusReson()
    {
        return statusReson;
    }

    public void setStatusReson(String statusReson)
    {
        this.statusReson = statusReson;
    }
    
}
