package com.okwei.appinterface.service;

import com.okwei.appinterface.bean.vo.MsgProductInfo;
import com.okwei.appinterface.bean.vo.ProductPrice;
import com.okwei.bean.vo.LoginUser;

public interface IProductDetailService {
    /**
     * 获取产品详情信息
     * 
     * @param productid
     * @param weiNo
     * @param shopweino
     * @param tiket
     * @return
     */
    public MsgProductInfo GetProductDetail(Long productid, LoginUser user, Long shopWeiNo, String tiket, int source, boolean isShowDetail);

    public MsgProductInfo GetProductDetailNew(Long productid, LoginUser user, Long shopWeiNo, String tiket, int source, boolean isShowDetail) ;
    /**
     * 获取产品款式价格
     * 
     * @param productid
     * @param styleId
     * @return
     */
    public ProductPrice getProductPrice(Long productid, Long styleId);

}
