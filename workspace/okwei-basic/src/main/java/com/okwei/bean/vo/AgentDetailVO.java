package com.okwei.bean.vo;

import java.util.Date;
import java.util.List;

/**
 * 代理商详情
 */
public class AgentDetailVO implements java.io.Serializable
{
    private static final long serialVersionUID = 1L;

    /**
     * 代理商ID
     */
    private Integer agentId;
    /**
     * 微店ID
     */
    private Long weiId;
    /**
     * 微店名
     */
    private String shopName;
    /**
     * 微店图片
     */
    private String weiPicture;
    /**
     * 联系人姓名
     */
    private String linkname;
    /**
     * 联系电话
     */
    private String phone;
    /**
     * 上传的图片
     */
    private String[] imgs;
    /**
     * 大图
     */
    private String[] bigImgs;
    /**
     * 优势
     */
    private String intro;
    /**
     * 上级供应商微店号
     */
    private Long merchantWeiId;
    /**
     * 上级供应商微店名
     */
    private String merchantShopName;
    /**
     * 状态1-正常2已取消
     */
    private Short status;
    /**
     * 悬赏金额
     */
    private Double rewardAmount;
    /**
     * 是否支付悬赏0-未支付1已支付
     */
    private Short isPayReward;
    /**
     * 发展人微店号
     */
    private Long developmentWeiId;
    /**
     * 发展人名
     */
    private String developmentName;
    /**
     * 发展人手机
     */
    private String developmentPhone;
    /**
     * 申请失去
     */
    private Date applyTime;
    /**
     * 所在地区
     */
    private AreaDetailVO location;
    /**
     * 落地店地址
     */
    private String address;
    /**
     * 代理地区
     */
    private List<AreaVO> agentAreas;
    /**
     * 落地店数量
     */
    private Integer storeCount;
    /**
     * 代理需求
     */
    private List<DemandsVO> demands;
    /**
     * 已经代理的需求
     */
    private List<DemandsVO> hasDemands;
    /**
     * 拒绝原因
     */
    private String reason;
    /**
     * 跟进认证员
     */
    private Long followVerifier;

     public String[] getBigImgs()
    {
        return bigImgs;
    }

    public void setBigImgs(String[] bigImgs)
    {
        this.bigImgs = bigImgs;
    }

    public Long getFollowVerifier()
    {
        return followVerifier;
    }

    public void setFollowVerifier(Long followVerifier)
    {
        this.followVerifier = followVerifier;
    }

    public String getReason()
    {
        return reason;
    }

    public void setReason(String reason)
    {
        this.reason = reason;
    }

    public String getDevelopmentPhone()
    {
        return developmentPhone;
    }

    public void setDevelopmentPhone(String developmentPhone)
    {
        this.developmentPhone = developmentPhone;
    }

    public String getDevelopmentName()
    {
        return developmentName;
    }

    public void setDevelopmentName(String developmentName)
    {
        this.developmentName = developmentName;
    }

    public List<DemandsVO> getHasDemands()
    {
        return hasDemands;
    }

    public void setHasDemands(List<DemandsVO> hasDemands)
    {
        this.hasDemands = hasDemands;
    }

    public Integer getAgentId()
    {
        return agentId;
    }

    public void setAgentId(Integer agentId)
    {
        this.agentId = agentId;
    }

    public Long getWeiId()
    {
        return weiId;
    }

    public void setWeiId(Long weiId)
    {
        this.weiId = weiId;
    }

    public String getShopName()
    {
        return shopName;
    }

    public void setShopName(String shopName)
    {
        this.shopName = shopName;
    }

    public String getWeiPicture()
    {
        return weiPicture;
    }

    public void setWeiPicture(String weiPicture)
    {
        this.weiPicture = weiPicture;
    }

    public String getLinkname()
    {
        return linkname;
    }

    public void setLinkname(String linkname)
    {
        this.linkname = linkname;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String[] getImgs()
    {
        return imgs;
    }

    public void setImgs(String[] imgs)
    {
        this.imgs = imgs;
    }

    public String getIntro()
    {
        return intro;
    }

    public void setIntro(String intro)
    {
        this.intro = intro;
    }

    public Long getMerchantWeiId()
    {
        return merchantWeiId;
    }

    public void setMerchantWeiId(Long merchantWeiId)
    {
        this.merchantWeiId = merchantWeiId;
    }

    public String getMerchantShopName()
    {
        return merchantShopName;
    }

    public void setMerchantShopName(String merchantShopName)
    {
        this.merchantShopName = merchantShopName;
    }

    public Short getStatus()
    {
        return status;
    }

    public void setStatus(Short status)
    {
        this.status = status;
    }

    public Double getRewardAmount()
    {
        return rewardAmount;
    }

    public void setRewardAmount(Double rewardAmount)
    {
        this.rewardAmount = rewardAmount;
    }

    public Short getIsPayReward()
    {
        return isPayReward;
    }

    public void setIsPayReward(Short isPayReward)
    {
        this.isPayReward = isPayReward;
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

    public AreaDetailVO getLocation()
    {
        return location;
    }

    public void setLocation(AreaDetailVO location)
    {
        this.location = location;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public List<AreaVO> getAgentAreas()
    {
        return agentAreas;
    }

    public void setAgentAreas(List<AreaVO> agentAreas)
    {
        this.agentAreas = agentAreas;
    }

    public Integer getStoreCount()
    {
        return storeCount;
    }

    public void setStoreCount(Integer storeCount)
    {
        this.storeCount = storeCount;
    }

    public List<DemandsVO> getDemands()
    {
        return demands;
    }

    public void setDemands(List<DemandsVO> demands)
    {
        this.demands = demands;
    }
}
