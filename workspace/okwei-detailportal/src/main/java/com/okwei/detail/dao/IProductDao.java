package com.okwei.detail.dao;

import java.util.List;

import com.okwei.bean.domain.PClassProducts;
import com.okwei.bean.domain.PPostAgeDetails;
import com.okwei.bean.domain.PPostAgeModel;
import com.okwei.bean.domain.PPreOrder;
import com.okwei.bean.domain.PProductAssist;
import com.okwei.bean.domain.PProductComment;
import com.okwei.bean.domain.PProductImg;
import com.okwei.bean.domain.PProductParamKv;
import com.okwei.bean.domain.PProducts;
import com.okwei.bean.domain.PShevleBatchPrice;
import com.okwei.bean.domain.TShoppingCar;
import com.okwei.bean.domain.UAttention;
import com.okwei.bean.domain.UAttentioned;
import com.okwei.bean.domain.UWallet;
import com.okwei.bean.domain.UWeiSeller;
import com.okwei.dao.IBaseDAO;

public interface IProductDao extends IBaseDAO {
    /**
     * 获取产品信息
     * 
     * @param proID
     * @return
     */
    public PProducts getProducts(long proID);

    /**
     * 获取产品图片
     * 
     * @param proID
     * @return
     */
    public List<PProductImg> getProImgList(long proID);

    /**
     * 获取产品参数
     * 
     * @param proID
     * @return
     */
    public List<PProductParamKv> getParamList(long proID);

    /**
     * 获取该供应商的其他的产品
     * 
     * @param supWeiID
     * @return
     */
    public List<PProducts> getProList(long supWeiID, long proID);

    /**
     * 获取该供应商的其他的产品
     * 
     * @param supWeiID
     * @return
     */
    public List<PProducts> getProList(Long[] proids);

    /**
     * 获取产品相关数量
     * 
     * @param proID
     * @return
     */
    public PProductAssist getProductAssist(long proID);

    /**
     * 添加相关数量
     * 
     * @param entity
     */
    public void saveAssist(PProductAssist entity);

    /**
     * 修改相关数量
     * 
     * @param entity
     */
    public void updateAssist(PProductAssist entity);

    /**
     * 获取评论列表
     * 
     * @param proID
     * @param index
     * @param size
     * @return
     */
    public List<PProductComment> getComments(long proID, int index, int size);

    /**
     * 获取用户信息
     * 
     * @param weiids
     * @return
     */
    public List<UWeiSeller> getSellers(Long[] weiids);

    /**
     * 获取邮费模板
     * 
     * @param freightId
     * @return
     */
    public PPostAgeModel getPostAgeModel(int freightId);

    /**
     * 获取邮费模板详情
     * 
     * @param freightId
     * @return
     */
    public List<PPostAgeDetails> getAgeDetails(int freightId);

    /**
     * 获取供应商的产品数量
     * 
     * @param supWeiID
     * @return
     */
    public long getSupProCount(long supWeiID);

    /**
     * 判断是否被关注
     * 
     * @param userID
     *            登陆微店号
     * @param supID
     *            供应商微店号
     * @return
     */
    public boolean getIsAttention(long userID, long supID);

    public boolean getIsAttentioned(long userID, long supID);

    /**
     * 删除关注
     * 
     * @param userID
     * @param supID
     */
    public void deleteAttention(long userID, long supID);

    /**
     * 添加关注
     * 
     * @param userID
     * @param supID
     */
    public void addAttention(UAttention entity);

    public void addAttention(UAttentioned entity);

    /**
     * 获取钱包信息
     * 
     * @param weiid
     * @return
     */
    public UWallet getWallet(long weiid);

    /**
     * 查找批发价
     * 
     * @param id
     * @return
     */
    public List<PShevleBatchPrice> getBatchPrices(long id);

    /**
     * 查找预订价
     * 
     * @param proID
     * @return
     */
    public PPreOrder getPreOrder(long proID);

    /**
     * 查询上架表
     * 
     * @param weiid
     * @param proID
     * @return
     */
    public PClassProducts getClassProducts(long weiid, long proID);

    /**
     * 查询购物车
     * 
     * @param proID
     * @param weiid
     * @return
     */
    public TShoppingCar getShopCart(long proID, long weiid, long styleid, long supid, short type, long shopWeiID);

}
