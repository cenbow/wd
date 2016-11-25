package com.okwei.myportal.bean.vo;

public class AccountVO
{
    /**
     * 是否绑定QQ
     */
    private boolean bindQQ;
    /**
     * 是否绑定微信
     */
    private boolean bindWX;

    public boolean isBindQQ()
    {
        return bindQQ;
    }

    public void setBindQQ(boolean bindQQ)
    {
        this.bindQQ = bindQQ;
    }

    public boolean isBindWX()
    {
        return bindWX;
    }

    public void setBindWX(boolean bindWX)
    {
        this.bindWX = bindWX;
    }

}
