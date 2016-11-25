package com.okwei.service.share;

import java.util.List;
import java.util.Map;

import com.okwei.bean.domain.PProductClass;
import com.okwei.bean.domain.PShopClass;
import com.okwei.bean.domain.SProducts;
import com.okwei.bean.domain.SSingleDesc;
import com.okwei.bean.enums.MainDataUserType;
import com.okwei.bean.enums.ShareTypeEnum;
import com.okwei.bean.vo.ReturnModel;
import com.okwei.bean.vo.product.ProductInfo;
import com.okwei.bean.vo.share.MainShare;
import com.okwei.bean.vo.share.ShareProduct;
import com.okwei.common.Limit;
import com.okwei.common.PageResult;
import com.okwei.service.IBaseService;

public interface IShareAddService extends IBaseService {

    /**
     * 获取上架产品的数量
     * 
     * @param weiid
     *            微店号
     * @param title
     *            标题
     * @param type
     *            类型 1 为自营 其他为分销
     * @param ids
     *            上架ID
     * @return
     */
    public Long getShareProCount(Long weiid, String title, short type, Long[] ids, int classid);

    /**
     * 获取上架产品列表PC
     * 
     * @param weiid
     *            微店号
     * @param title
     *            标题
     * @param type
     *            类型
     * @param ids
     *            上架ID
     * @return
     */
    public List<ShareProduct> getShareProList(Long weiid, String title, short type, Long[] ids, Limit limit, int classid);

    /**
     * 获取上架产品列表APP
     * 
     * @param weiid
     * @param ids
     * @param limit
     * @param classid
     * @return
     */
    public PageResult<ShareProduct> getShareProListForApp(Long weiid, String title, short type, Long[] ids, Limit limit, int classid);

    /**
     * 添加
     * 
     * @param weiid
     * @param shareId
     * @param title
     * @param des
     * @param list
     * @return
     */
    public ReturnModel addShare(Long weiid, Long shareId, String title, String des, List<SProducts> list, MainDataUserType type, String ctsUser, int shareType, List<SSingleDesc> singlist);

    /**
     * 重写添加
     * 
     * @param weiid
     * @param shareId
     * @param title
     * @param des
     * @param list
     * @return
     */
    public ReturnModel addShare(Long weiid, Long shareId, String title, String des, ShareTypeEnum sharyType, List<SSingleDesc> singList, List<SProducts> list, MainDataUserType type, String ctsUser);

    /**
     * 获取店铺一级分类
     * 
     * @return
     */
    public List<PShopClass> getShopClasses(Long weiid);

    /**
     * 获取分享的实体
     * 
     * @param weiid
     * @param shareid
     * @return
     */
    public MainShare getMainShare(Long weiid, Long shareid);

    /**
     * 判断是不是供应商
     * 
     * @param idtype
     * @return
     */
    public boolean getIsSupper(int idtype);

    /**
     * 获取所有的系统分类
     * 
     * @return
     */
    public List<PProductClass> getProductClasses();

    /**
     * 根据条件获取分类
     * 
     * @param classid1
     * @param classid2
     * @param step
     * @return
     */
    public List<PProductClass> getProductClasses(int classid1, int classid2, int step);

    /**
     * 获取分享的产品数量cts
     * 
     * @param kv
     * @return
     */
    public int getShareCountCts(Map<String, String> kv);

    /**
     * 获取分享的产品cts
     * 
     * @param kv
     * @return
     */
    public List<ProductInfo> getShareProListCts(Map<String, String> kv);
    
    /**
     * 获取热门分享
     * 
     * @param limit 分页
     * @param count 显示的产品数量
     * @return
     */
    public PageResult<MainShare> getMainShares(Limit limit, int count);
}
