package com.okwei.bean.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * USupplyChannel entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "U_SupplyChannel")
public class USupplyChannel implements java.io.Serializable
{

    // Fields

    /**
	 * 
	 */

    private static final long serialVersionUID = -8349386181034781197L;
    private Integer channelId;
    private Integer demandId;
    private Long weiId;
    private Long supplyId;
    private Long verifier;
    private Long followVerifier;
    private Short channelType;
    private Long upWeiId; 
	private Short state;
    private Double reward;
    private Short payedReward;
    private String cancelRemark;
    private Date createTime;
    private Integer applyID;
    private Date applyTime;
    private Date cancelTime;
    private Short joinType;
    // Constructors

    /** default constructor */
    public USupplyChannel()
    {
    }

    /** full constructor */
    public USupplyChannel(Integer demandId,Long weiId,Long supplyId,Long verifier,Long followVerifier,Short channelType,Long upWeiId,Short state,Double reward,Short payedReward,
            String cancelRemark,Date createTime,Integer applyID,Date applyTime)
    {
        this.demandId = demandId;
        this.weiId = weiId;
        this.supplyId = supplyId;
        this.verifier = verifier;
        this.followVerifier = followVerifier;
        this.channelType = channelType;
        this.upWeiId = upWeiId;
        this.state = state;
        this.reward = reward;
        this.payedReward = payedReward;
        this.cancelRemark = cancelRemark;
        this.createTime = createTime;
        this.applyID = applyID;
        this.applyTime = applyTime;
    }

    // Property accessors
    @Id
    @GeneratedValue
    @Column(name = "ChannelID",unique = true,nullable = false)
    public Integer getChannelId()
    {
        return this.channelId;
    }

    public void setChannelId(Integer channelId)
    {
        this.channelId = channelId;
    }

    @Column(name = "DemandID")
    public Integer getDemandId()
    {
        return this.demandId;
    }

    public void setDemandId(Integer demandId)
    {
        this.demandId = demandId;
    }

    @Column(name = "WeiID")
    public Long getWeiId()
    {
        return this.weiId;
    }

    public void setWeiId(Long weiId)
    {
        this.weiId = weiId;
    }

    @Column(name = "SupplyID")
    public Long getSupplyId()
    {
        return this.supplyId;
    }

    public void setSupplyId(Long supplyId)
    {
        this.supplyId = supplyId;
    }

    @Column(name = "Verifier")
    public Long getVerifier()
    {
        return this.verifier;
    }

    public void setVerifier(Long verifier)
    {
        this.verifier = verifier;
    }

    @Column(name = "FollowVerifier")
    public Long getFollowVerifier()
    {
        return this.followVerifier;
    }

    public void setFollowVerifier(Long followVerifier)
    {
        this.followVerifier = followVerifier;
    }

    @Column(name = "ChannelType")
    public Short getChannelType()
    {
        return this.channelType;
    }

    public void setChannelType(Short channelType)
    {
        this.channelType = channelType;
    }

    @Column(name = "UpWeiID")
    public Long getUpWeiId()
    {
        return this.upWeiId;
    }

    public void setUpWeiId(Long upWeiId)
    {
        this.upWeiId = upWeiId;
    }

    @Column(name = "State")
    public Short getState()
    {
        return this.state;
    }

    public void setState(Short state)
    {
        this.state = state;
    }

    @Column(name = "Reward",precision = 10,scale = 0)
    public Double getReward()
    {
        return this.reward;
    }

    public void setReward(Double reward) {
		this.reward = reward;
	}

    @Column(name = "PayedReward")
    public Short getPayedReward()
    {
        return this.payedReward;
    }

    public void setPayedReward(Short payedReward)
    {
        this.payedReward = payedReward;
    }

    @Column(name = "CancelRemark",length = 512)
    public String getCancelRemark()
    {
        return this.cancelRemark;
    }

    public void setCancelRemark(String cancelRemark)
    {
        this.cancelRemark = cancelRemark;
    }

    @Column(name = "CreateTime",length = 19)
    public Date getCreateTime()
    {
        return this.createTime;
    }

    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }

    @Column(name = "ApplyID")
    public Integer getApplyID()
    {
        return applyID;
    }

    public void setApplyID(Integer applyID)
    {
        this.applyID = applyID;
    }

    @Column(name = "ApplyTime",length = 19)
    public Date getApplyTime()
    {
        return applyTime;
    }

    public void setApplyTime(Date applyTime)
    {
        this.applyTime = applyTime;
    }

    @Column(name = "CancelTime",length = 19)
	public Date getCancelTime() {
		return cancelTime;
	}

	public void setCancelTime(Date cancelTime) {
		this.cancelTime = cancelTime;
	}

        @Column(name = "JoinType")
    public Short getJoinType()
    {
        return joinType;
    }

    public void setJoinType(Short joinType)
    {
        this.joinType = joinType;
    }
	
}