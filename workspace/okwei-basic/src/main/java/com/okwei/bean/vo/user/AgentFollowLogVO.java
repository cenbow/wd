package com.okwei.bean.vo.user;

import java.io.Serializable;
import java.util.Date;

/**
 * 代理商跟进
 */
public class AgentFollowLogVO implements Serializable
{
    private static final long serialVersionUID = 7036020192373421077L;
    /**
     * 跟进时间
     */
    private Date followTime;
    /**
     * 跟进人微店号
     */
    private Long followWeiId;
    /**
     * 跟进内容
     */
    private String followContent;

    public Date getFollowTime()
    {
        return followTime;
    }

    public void setFollowTime(Date followTime)
    {
        this.followTime = followTime;
    }

    public Long getFollowWeiId()
    {
        return followWeiId;
    }

    public void setFollowWeiId(Long followWeiId)
    {
        this.followWeiId = followWeiId;
    }

    public String getFollowContent()
    {
        return followContent;
    }

    public void setFollowContent(String followContent)
    {
        this.followContent = followContent;
    }

}
