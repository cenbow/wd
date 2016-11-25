package com.okwei.cartportal.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.okwei.bean.domain.PProductBatchPrice;

/**
 * 前端显示购物车实体
 * @author yangjunjun
 *
 */
public class ShoppingModel implements Serializable
{
    private static final long serialVersionUID = 1L;
    private Long sCID;// 主键
    private Long weiID;// 买家微店号
    private Long supplierID;// 供应商微店号
    private Long sellerWeiId;// 成交微店
    private Long shopWeiId;//卖家微店
    private String shopWeiName;//卖家微店名称
	private Long proNum;// 商品编号
    private Integer count;// 商品数量
    private Double price;// 商品单价*
    private Double price2;// 商品零售价
    private Double nowprice;// 现价*
    private Double batchprice;//默认批发价
    private String image;// 商品默认图片
    private String proTitle;// 商品标题
    private String property;// 商品已选属性
    private Integer classId;// 商品分类ID
    private Short status;// 购物车商品状态（1启用，0启用）
    private Short buyType;// 购物车类型（1-零售、2-预订单、3-批发单）
    private Date createTime;// 创建时间
    private Long styleId;// 款式ID
    private String remark1;// 备注
    private String remark2;// 备注
    private List<PProductBatchPrice> ppbplist;// 阶梯价
    private List<ProductStyleParam> styles;//商品所有属性
    private Double totalPrice;//商品总价
	private Long shelvesId;//供应商上架ID
    
    public Long getShelvesId() {
		return shelvesId;
	}

	public void setShelvesId(Long shelvesId) {
		this.shelvesId = shelvesId;
	}

	public Long getSellerWeiId()
    {
        return sellerWeiId;
    }

    public void setSellerWeiId(Long sellerWeiId)
    {
        this.sellerWeiId = sellerWeiId;
    }

    public Double getNowprice()
    {
        return nowprice;
    }

    public void setNowprice(Double nowprice)
    {
        this.nowprice = nowprice;
    }

    public Double getPrice2()
    {
        return price2;
    }

    public void setPrice2(Double price2)
    {
        this.price2 = price2;
    }

    public List<PProductBatchPrice> getPpbplist()
    {
        return ppbplist;
    }

    public void setPpbplist(List<PProductBatchPrice> ppbplist)
    {
        this.ppbplist = ppbplist;
    }

    public Long getStyleId()
    {
        return styleId;
    }

    public void setStyleId(Long styleId)
    {
        this.styleId = styleId;
    }

    public Long getsCID()
    {
        return sCID;
    }

    public void setsCID(Long sCID)
    {
        this.sCID = sCID;
    }

    public Long getWeiID()
    {
        return weiID;
    }

    public void setWeiID(Long weiID)
    {
        this.weiID = weiID;
    }

    public Long getSupplierID()
    {
        return supplierID;
    }

    public void setSupplierID(Long supplierID)
    {
        this.supplierID = supplierID;
    }

    public Long getProNum()
    {
        return proNum;
    }

    public void setProNum(Long proNum)
    {
        this.proNum = proNum;
    }

    public Integer getCount()
    {
        return count;
    }

    public void setCount(Integer count)
    {
        this.count = count;
    }

    public Double getPrice()
    {
        return price;
    }

    public void setPrice(Double price)
    {
        this.price = price;
    }

    public String getImage()
    {
        return image;
    }

    public void setImage(String image)
    {
        this.image = image;
    }

    public String getProTitle()
    {
        return proTitle;
    }

    public void setProTitle(String proTitle)
    {
        this.proTitle = proTitle;
    }

    public Integer getClassId()
    {
        return classId;
    }

    public void setClassId(Integer classId)
    {
        this.classId = classId;
    }

    public Short getStatus()
    {
        return status;
    }

    public void setStatus(Short status)
    {
        this.status = status;
    }

    public Short getBuyType()
    {
        return buyType;
    }

    public void setBuyType(Short buyType)
    {
        this.buyType = buyType;
    }

    public Date getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }

    public String getRemark1()
    {
        return remark1;
    }

    public void setRemark1(String remark1)
    {
        this.remark1 = remark1;
    }

    public String getRemark2()
    {
        return remark2;
    }

    public void setRemark2(String remark2)
    {
        this.remark2 = remark2;
    }

    public static long getSerialversionuid()
    {
        return serialVersionUID;
    }

	public List<ProductStyleParam> getStyles() {
		return styles;
	}

	public void setStyles(List<ProductStyleParam> styles) {
		this.styles = styles;
	}

	public String getShopWeiName() {
		return shopWeiName;
	}

	public void setShopWeiName(String shopWeiName) {
		this.shopWeiName = shopWeiName;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public Long getShopWeiId() {
		return shopWeiId;
	}

	public void setShopWeiId(Long shopWeiId) {
		this.shopWeiId = shopWeiId;
	}

	public Double getBatchprice() {
		return batchprice;
	}

	public void setBatchprice(Double batchprice) {
		this.batchprice = batchprice;
	}
}
