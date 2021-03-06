package com.okwei.bean.vo.user;

import java.io.Serializable;

import com.okwei.bean.vo.BaseImageMsg;
import com.okwei.bean.vo.ChildAccountInfo;

/**
 * APP登陆返回实体
 */
public class LoginReturnAPP implements Serializable
{
    private static final long serialVersionUID = -5963046741500994539L;
    /** 是否设置密码(0-未设置，1已设置) **/
    private Short hasPassWord;
    /** 身份标识二进制 **/
    private Integer identityType;
    /** 是否绑定微信 **/
    private Short isBindWx;
    /** 暂时没有用 **/
    private short isEnableAuth;
    /** 是否绑定手机(0未绑定1已绑定) **/
    private Short mobileIsBind;
    /** 手机号码 **/
    private String phone;
    /** 主营 **/
    private String shopBusContent;
    /** 图片 **/
    private BaseImageMsg shopImg;
    /** 店铺名 **/
    private String shopName;
    /** 登录标示 **/
    private String tiket;
    /** 暂时没有用，赋值登录WEIID **/
    private Long userId;
    /** 微店号 **/
    private Long weiId;
    /** 是否子帐号0否1是 **/
    private Short isChildAccount;
    /** 子帐号信息 **/
    private ChildAccountInfo childAccount;    
    
    /**
     * 是否可以发布商品
     */
    private Short publishDenyFlag;
    
    private String homePageUrl;
    
    
    
    
    
    
    public String getHomePageUrl() {
		return homePageUrl;
	}
	public void setHomePageUrl(String homePageUrl) {
		this.homePageUrl = homePageUrl;
	}
	public Short getPublishDenyFlag() {
		return publishDenyFlag;
	}
	public void setPublishDenyFlag(Short publishDenyFlag) {
		this.publishDenyFlag = publishDenyFlag;
	}
	public Short getHasPassWord()
    {
        return hasPassWord;
    }
    public void setHasPassWord(Short hasPassWord)
    {
        this.hasPassWord = hasPassWord;
    }
    public Integer getIdentityType()
    {
        return identityType;
    }
    public void setIdentityType(Integer identityType)
    {
        this.identityType = identityType;
    }
    public Short getIsBindWx()
    {
        return isBindWx;
    }

    public void setIsBindWx(Short isBindWx)
    {
        this.isBindWx = isBindWx;
    }
    public short getIsEnableAuth()
    {
        return isEnableAuth;
    }
    public void setIsEnableAuth(short isEnableAuth)
    {
        this.isEnableAuth = isEnableAuth;
    }
    public Short getMobileIsBind()
    {
        return mobileIsBind;
    }
    public void setMobileIsBind(Short mobileIsBind)
    {
        this.mobileIsBind = mobileIsBind;
    }
    public String getPhone()
    {
        return phone;
    }
    public void setPhone(String phone)
    {
        this.phone = phone;
    }
    public String getShopBusContent()
    {
        return shopBusContent;
    }
    public void setShopBusContent(String shopBusContent)
    {
        this.shopBusContent = shopBusContent;
    }
    public BaseImageMsg getShopImg()
    {
        return shopImg;
    }
    public void setShopImg(BaseImageMsg shopImg)
    {
        this.shopImg = shopImg;
    }
    public String getShopName()
    {
        return shopName;
    }
    public void setShopName(String shopName)
    {
        this.shopName = shopName;
    }
    public String getTiket()
    {
        return tiket;
    }
    public void setTiket(String tiket)
    {
        this.tiket = tiket;
    }
    public Long getUserId()
    {
        return userId;
    }
    public void setUserId(Long userId)
    {
        this.userId = userId;
    }
    public Long getWeiId()
    {
        return weiId;
    }
    public void setWeiId(Long weiId)
    {
        this.weiId = weiId;
    }
    public Short getIsChildAccount()
    {
        return isChildAccount;
    }
    public void setIsChildAccount(Short isChildAccount)
    {
        this.isChildAccount = isChildAccount;
    }
    public ChildAccountInfo getChildAccount()
    {
        return childAccount;
    }
    public void setChildAccount(ChildAccountInfo childAccount)
    {
        this.childAccount = childAccount;
    }
    
}
