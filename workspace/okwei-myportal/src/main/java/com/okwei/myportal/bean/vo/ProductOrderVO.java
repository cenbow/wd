package com.okwei.myportal.bean.vo;

import java.io.Serializable;


public class ProductOrderVO extends BaseProduct implements Serializable
{

	private static final long serialVersionUID = 8487253711001929418L;
    /**
     * 产品订单ID
     */
    private String productOrderID;
    /**
     * 购物数量
     */
    private int count;
    /**
     * 商品状态
     */
    private int productState;
    /**
     * 属性
     */
    private String property;
    /**
     * 产品ID
     */
    private long productId;
    /**
     * 产品标题
     */
    private String productTitle;
    /**
     * 产品图片
     */
    private String productImg;
    /**
     * 产品单价
     */
    private double price;
    /**
     * 代理价
     */
    private double agentPrice;
    /**
     * 副城主价(二级代理)
     */
    private double deputyPrice;
    /**
     * 城主价（一级代理）
     */
    private double dukePrice;
    /**
     * 供货价
     */
    private double supplyPrice;
    /**
     * 零售价
     */
    private double retailPrice;
    /**
     * 售后
     */
    private String afterSales;
    /**
     * 佣金
     */
    private double commission;
    /**
     * 优惠方式
     */
    private String preferential;
    /**
     * 来源微店
     */
    private String sourceWeiName;
    /**
     * 总金额
     */
    private double totalamount;
    /**
     * 退款ID
     */
    private long refundId;
    /**
     * 退款状态
     */
    private int refundState;
    /**
     * 买家微店号
     */
    private long buyerId;
    /*****************************/
    /**
     * 单商品总价
     */
    private double sumPrice;
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
     * 订单产品id
     */
    private String productOrderid;

    public double getSumPrice()
    {
        return sumPrice;
    }

    public void setSumPrice(double sumPrice)
    {
        this.sumPrice = sumPrice;
    }

    public String getProStateStr()
    {
        return proStateStr;
    }

    public void setProStateStr(String proStateStr)
    {
        this.proStateStr = proStateStr;
    }

    public String getFavorable()
    {
        return favorable;
    }

    public void setFavorable(String favorable)
    {
        this.favorable = favorable;
    }

    public String getSourceName()
    {
        return sourceName;
    }

    public void setSourceName(String sourceName)
    {
        this.sourceName = sourceName;
    }

    public String getProductOrderid()
    {
        return productOrderid;
    }

    public void setProductOrderid(String productOrderid)
    {
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

    public double getTotalamount()
    {
        return totalamount;
    }

    public void setTotalamount(double totalamount)
    {
        this.totalamount = totalamount;
    }

    public double getCommission()
    {
        return commission;
    }

    public void setCommission(double commission)
    {
        this.commission = commission;
    }

    public String getPreferential()
    {
        return preferential;
    }

    public void setPreferential(String preferential)
    {
        this.preferential = preferential;
    }

    public String getSourceWeiName()
    {
        return sourceWeiName;
    }

    public void setSourceWeiName(String sourceWeiName)
    {
        this.sourceWeiName = sourceWeiName;
    }

    public String getAfterSales()
    {
        return afterSales;
    }

    public void setAfterSales(String afterSales)
    {
        this.afterSales = afterSales;
    }

    public String getProperty()
    {
        return property;
    }

    public long getProductId()
    {
        return productId;
    }

    public void setProductId(long productId)
    {
        this.productId = productId;
    }

    public String getProductTitle()
    {
        return productTitle;
    }

    public void setProductTitle(String productTitle)
    {
        this.productTitle = productTitle;
    }

    public String getProductImg()
    {
        return productImg;
    }

    public void setProductImg(String productImg)
    {
        this.productImg = productImg;
    }

    public double getPrice()
    {
        return price;
    }

    public void setPrice(double price)
    {
        this.price = price;
    }

    public void setProperty(String property)
    {
        this.property = property;
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

    public String getProductOrderID()
    {
        return productOrderID;
    }

    public void setProductOrderID(String productOrderID)
    {
        this.productOrderID = productOrderID;
    }

    public long getBuyerId()
    {
        return buyerId;
    }

    public void setBuyerId(long buyerId)
    {
        this.buyerId = buyerId;
    }

	public double getAgentPrice() {
		return agentPrice;
	}

	public void setAgentPrice(double agentPrice) {
		this.agentPrice = agentPrice;
	}

	public double getDeputyPrice() {
		return deputyPrice;
	}

	public void setDeputyPrice(double deputyPrice) {
		this.deputyPrice = deputyPrice;
	}

	public double getDukePrice() {
		return dukePrice;
	}

	public void setDukePrice(double dukePrice) {
		this.dukePrice = dukePrice;
	}

	public double getRetailPrice() {
		return retailPrice;
	}

	public void setRetailPrice(double retailPrice) {
		this.retailPrice = retailPrice;
	}

	public double getSupplyPrice() {
		return supplyPrice;
	}

	public void setSupplyPrice(double supplyPrice) {
		this.supplyPrice = supplyPrice;
	}


    

}
