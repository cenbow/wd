package com.okwei.service.product;

import java.util.List;
import java.util.Map;

import com.okwei.bean.domain.PPriceShow;
import com.okwei.bean.vo.LoginUser;
import com.okwei.bean.vo.PageInfo;
import com.okwei.bean.vo.product.HouseClass;
import com.okwei.bean.vo.product.HouseInfo;
import com.okwei.bean.vo.product.InvestmentProducts;
import com.okwei.bean.vo.product.ProductInfo;
import com.okwei.common.Limit;
import com.okwei.common.PageResult;

public interface IHouseService {

    /**
     * 获取管的信息
     * 
     * @param size
     * @return
     */
    public List<HouseInfo> getHouseInfo(int index, int size);

    /**
     * 获取馆产品PC
     * 
     * @param pageIndex
     * @param pageSize
     * @param classId
     * @param classLevel
     * @return
     */
    public PageResult<ProductInfo> getHouseProducts(LoginUser user, Limit limit, int classId, int classLevel, int orderby);

    /**
     * 获取馆产品APP
     * 
     * @param pageIndex
     * @param pageSize
     * @param classId
     * @param classLevel
     * @return
     */
    public PageInfo getPageInfo(LoginUser user, Limit limit, int classId, int classLevel);

    /**
     * 获取招商系列商品列表
     * 
     * @param user
     * @param limit
     * @return
     */
    public InvestmentProducts getInvesProducts(LoginUser user, Limit limit, int demandId, Long productId);

    /**
     * 获取馆下级分类
     * 
     * @param parentId
     * @return
     */
    public List<HouseClass> getHouseClass(int parentId);

    /**
     * 获取落地店产品
     * 
     * @param user
     * @param params
     * @return
     */
    public PageInfo getSearchYunPurchasesProducts(LoginUser user, Map<String, String> params);

    /**
     * 获取云端产品库落地店搜索产品(PC)
     * @param user
     * @param params
     * @return
     */
    public PageResult<ProductInfo> getLdYunProducts(LoginUser user, Map<String, String> params);
    
    /**
     * 判断是否显示代理价 落地价
     * 
     * @param user
     * @param show
     * @param info
     * @return
     */
    public ProductInfo getPriceShow(LoginUser user, PPriceShow show, ProductInfo info);

    /**
     * 判断登陆用户是否平台号店铺的落地点
     * 
     * @param shopWeiId
     * @param loginWeiId
     * @return
     */
    public boolean checkUserIsStore(Long shopWeiId, Long loginWeiId) throws Exception;

    /**
     * 判断登陆用户是否平台号店铺的代理商
     * 
     * @param shopWeiId
     * @param loginWeiId
     * @return
     */
    public boolean checkUserIsAgent(Long shopWeiId, Long loginWeiId) throws Exception;

    /**
     * 获取进货管理列表
     * 
     * @param user
     * @param pageIndex
     * @param pageSize
     * @param orderBy
     * @return
     */
    public PageInfo getPurchaseList(LoginUser user, int pageIndex, int pageSize, int orderBy);

    /**
     * 判断登陆用户loginWeiId是否是店铺shopWeiId的某个系列demandId的代理商或落地店
     * 
     * @param shopWeiId
     *            店铺id
     * @param loginWeiId
     *            登陆用户id
     * @param demandId
     *            系列Id
     * @param channelType
     *            类型
     * @param state
     *            状态
     * @return
     * @throws Exception
     */
    boolean checkUserIsAgentOrStore(Long shopWeiId, Long loginWeiId, Integer demandId, Short channelType, Short state) throws Exception;

}
