package com.okwei.detail.service;

import java.util.List;

import com.okwei.bean.domain.PPreOrder;
import com.okwei.bean.domain.PShevleBatchPrice;
import com.okwei.bean.domain.TShoppingCar;
import com.okwei.bean.domain.USupplyDemand;
import com.okwei.bean.vo.LoginUser;
import com.okwei.bean.vo.product.ProductInfo;
import com.okwei.detail.bean.vo.Product;
import com.okwei.detail.bean.vo.ProductDetail;

public interface IProductService {
    /**
     * 获取产品信息
     * 
     * @param pNo
     * @return
     */
    public ProductDetail getDetail(LoginUser user, long sid, long pid, int f, long w);

    /**
     * 分享添加分享数量
     * 
     * @param proID
     */
    public void saveShareCount(long proID);

    /**
     * 获取评论列表
     * 
     * @param proID
     * @param index
     * @param size
     * @return
     */
    public String getCommentList(long proID, int index, int size);

    /**
     * 获取邮费
     * 
     * @param proID
     * @param province
     * @param city
     * @param district
     * @param count
     * @return
     */
    public double getPostage(long proID, int province, int city, int district, int count);

    /**
     * 关注店铺(取消关注)
     * 
     * @param userID
     * @param type
     * @param supID
     */
    public void attentionSup(long userID, int type, long supID);

    /**
     * 添加购物车
     * 
     * @param cart
     * @return
     */
    public String addCart(List<TShoppingCar> cart, long weiid);

    /**
     * 获取购物车数量
     * 
     * @param weiid
     * @return
     */
    public long getCartCount(long weiid);

    public boolean getIsAttention(long userID, long supID);

    /**
     * 查找批发价
     * 
     * @param proID
     * @return
     */
    public List<PShevleBatchPrice> getBatchPrices(long proid, long supid);

    public List<PShevleBatchPrice> getBatchPrices(long id);

    /**
     * 查找预订价
     * 
     * @param proID
     * @return
     */
    public PPreOrder getPreOrder(long proID);

    /**
     * 查询产品关键字
     * 
     * @param proid
     * @return
     */
    public List<String> getKeyWords(long proid);

    /**
     * 根据上架人的微店号，产品ID获取上架ID
     * 
     * @param w
     * @param pNo
     * @return
     */
    public long getJumpID(long w, long pNo);

    /**
     * 判断用户是否能够看到平台号的代理价，落地价
     * 
     * @param weiNo
     * @param supWeiID
     * @return
     */
    public ProductInfo getPriceVisble(LoginUser user, Integer demandId, long supWeiID);

    /**
     * 根据产品获取招商需求
     * 
     * @param supWeiID
     * @return
     */
    public USupplyDemand getSupplyDemand(long proID);

    /**
     * 获取系列产品
     * 
     * @param demandID
     * @return
     */
    public List<Product> getDemandProduct(Integer demandID, Long proid);
}
