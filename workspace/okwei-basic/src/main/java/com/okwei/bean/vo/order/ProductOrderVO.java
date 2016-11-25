package com.okwei.bean.vo.order;

public class ProductOrderVO extends BaseProduct
{
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
    /**
     * 产品标题
     */
    /**
     * 产品图片
     */
    /**
     * 产品单价
     */
    /**
     * 售后
     */
    private String afterSales;
    /**
     * 佣金
     */
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
     * 退款类型
     */
    private int refundType;
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
    
	//分享页制作人微店号
	private Long sharePageProducer;  
	//分享人微店号
	private Long shareOne; 
	//分享页Id
	private Long sharePageId;
   

	public Long getSharePageId() {
		return sharePageId;
	}

	public void setSharePageId(Long sharePageId) {
		this.sharePageId = sharePageId;
	}

	public Long getSharePageProducer() {
		return sharePageProducer;
	}

	public void setSharePageProducer(Long sharePageProducer) {
		this.sharePageProducer = sharePageProducer;
	}

	public Long getShareOne() {
		return shareOne;
	}

	public void setShareOne(Long shareOne) {
		this.shareOne = shareOne;
	}

	public int getRefundType() {
		return refundType;
	}

	public void setRefundType(int refundType) {
		this.refundType = refundType;
	}

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

}
