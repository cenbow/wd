package com.okwei.bean.vo;

import java.io.Serializable;
import java.util.List;
/**
 * 子账号登录实体
 * @author Administrator
 *
 */
public class LoginUserSub extends LoginUser implements Serializable
{ 
    private static final long serialVersionUID = -5720225078838833919L;
    /** 权限 **/
    private List<String> power;
    /**上級微店號**/
    private long parentWeiId;
    /**子账号类型**/
    private Integer accountType;
    /**子帐号的账号**/
    private String account;
    /**帐号名称/子供应商名称**/
    private String accountName; 
    
    public long getParentWeiId()
    {
        return parentWeiId;
    }
    public void setParentWeiId(long parentWeiId)
    {
        this.parentWeiId = parentWeiId;
    }
    public List<String> getPower()
    {
        return power;
    }
    public void setPower(List<String> power)
    {
        this.power = power;
    }
    public Integer getAccountType()
    {
        return accountType;
    }
    public void setAccountType(Integer accountType)
    {
        this.accountType = accountType;
    }
    public String getAccount()
    {
        return account;
    }
    public void setAccount(String account)
    {
        this.account = account;
    }
    public String getAccountName()
    {
        return accountName;
    }
    public void setAccountName(String accountName)
    {
        this.accountName = accountName;
    } 
}
