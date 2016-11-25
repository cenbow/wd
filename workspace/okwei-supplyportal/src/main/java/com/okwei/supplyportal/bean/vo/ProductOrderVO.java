package com.okwei.supplyportal.bean.vo;

public class ProductOrderVO extends BaseProduct
{
    /**
     * 购买数量
     */
    private int count;
    /**
     * 产品订单状态
     */
    private int productState;
    /**
     * 单商品总价
     */
    private double sumPrice;
    /**
     * 属性
     */
    private String property;
    /**
     * 产品状态名称
     */
    private String proStateStr;
    /**
     * 优惠信息
     */
    private String favorable;
    /**
     * 来源微店名称
     */
    private String sourceName;
    /**
     * 退款ID
     */
    private long refundId;
    /**
     * 退款状态
     */
    private int refundState;
    /**
     * 订单产品id
     */
    private String productOrderid;

    public String getProductOrderid() {
		return productOrderid;
	}

	public void setProductOrderid(String productOrderid) {
		this.productOrderid = productOrderid;
	}

	public long getRefundId()
    {
        return refundId;
    }

    public void setRefundId(long refundId)
    {
        this.refundId = refundId;
    }

    public int getRefundState()
    {
        return refundState;
    }

    public void setRefundState(int refundState)
    {
        this.refundState = refundState;
    }


    public String getSourceName()
    {
        return sourceName;
    }

    public void setSourceName(String sourceName)
    {
        this.sourceName = sourceName;
    }

    public String getFavorable()
    {
        return favorable;
    }

    public void setFavorable(String favorable)
    {
        this.favorable = favorable;
    }

    public String getProStateStr()
    {
        return proStateStr;
    }

    public void setProStateStr(String proStateStr)
    {
        this.proStateStr = proStateStr;
    }

    public int getCount()
    {
        return count;
    }

    public void setCount(int count)
    {
        this.count = count;
    }

    public int getProductState()
    {
        return productState;
    }

    public void setProductState(int productState)
    {
        this.productState = productState;
    }

    public double getSumPrice()
    {
        return sumPrice;
    }

    public void setSumPrice(double sumPrice)
    {
        this.sumPrice = sumPrice;
    }

    /**
     * 产品销售属性
     * 
     * @return
     */
    public String getProperty()
    {
        return property;
    }

    public void setProperty(String val)
    {
        this.property = val;
    }
}
