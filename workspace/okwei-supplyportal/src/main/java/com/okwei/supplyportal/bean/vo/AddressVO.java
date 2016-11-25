package com.okwei.supplyportal.bean.vo;

public class AddressVO
{
    /**
     * 收货地址ID
     */
    private Integer caddrId;

    /**
     * 收货人
     */
    private String receiverName;

    /**
     * 省
     */
    private Integer province;

    /**
     * 市
     */
    private Integer city;

    /**
     * 区
     */
    private Integer district;

    /**
     * 详细地址
     */
    private String detailAddr;

    /**
     * 地址文字
     */
    private String address;
    /**
     * 手机号
     */
    private String mobilePhone;

    /**
     * QQ
     */
    private String qq;

    /**
     * 是否默认
     */
    private Short isDefault;

    public Integer getCaddrId()
    {
        return caddrId;
    }

    public void setCaddrId(Integer caddrId)
    {
        this.caddrId = caddrId;
    }

    public String getReceiverName()
    {
        return receiverName;
    }

    public void setReceiverName(String receiverName)
    {
        this.receiverName = receiverName;
    }

    public Integer getProvince()
    {
        return province;
    }

    public void setProvince(Integer province)
    {
        this.province = province;
    }

    public Integer getCity()
    {
        return city;
    }

    public void setCity(Integer city)
    {
        this.city = city;
    }

    public Integer getDistrict()
    {
        return district;
    }

    public void setDistrict(Integer district)
    {
        this.district = district;
    }

    public String getDetailAddr()
    {
        return detailAddr;
    }

    public void setDetailAddr(String detailAddr)
    {
        this.detailAddr = detailAddr;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getMobilePhone()
    {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone)
    {
        this.mobilePhone = mobilePhone;
    }

    public String getQq()
    {
        return qq;
    }

    public void setQq(String qq)
    {
        this.qq = qq;
    }

    public Short getIsDefault()
    {
        return isDefault;
    }

    public void setIsDefault(Short isDefault)
    {
        this.isDefault = isDefault;
    }

}
