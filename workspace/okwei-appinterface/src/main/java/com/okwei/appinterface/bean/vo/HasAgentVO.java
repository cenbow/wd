package com.okwei.appinterface.bean.vo;

import java.io.Serializable;

/**
 * 是否有代理商
 */
public class HasAgentVO implements Serializable
{
    private static final long serialVersionUID = 1497337035667022028L;
    /**
     * 是否已有代理（1有0没有）
     */
    private int hasAgent;

    public int getHasAgent()
    {
        return hasAgent;
    }

    public void setHasAgent(int hasAgent)
    {
        this.hasAgent = hasAgent;
    }

}
