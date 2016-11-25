package com.okwei.service.user;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.taglibs.standard.lang.jstl.test.beans.PublicBean1;
import org.springframework.stereotype.Service;

import com.okwei.bean.domain.TRegional;
import com.okwei.bean.domain.USupplyDemand;
import com.okwei.bean.enums.DemandStateEnum;
import com.okwei.bean.enums.RegionLevelEnum;
import com.okwei.bean.enums.SupplyChannelTypeEnum;
import com.okwei.bean.vo.DemandProductVO;
import com.okwei.bean.vo.LoginUser;
import com.okwei.bean.vo.RegionVO;
import com.okwei.bean.vo.ResultMsg;
import com.okwei.bean.vo.ShopClassVO;
import com.okwei.bean.vo.SupplyDemandVO;
import com.okwei.bean.vo.user.ChannelSupplyVO;
import com.okwei.bean.vo.user.ChannelInfoVO;
import com.okwei.bean.vo.user.ChannelRegionVO;
import com.okwei.bean.vo.user.DemandChannelVO;
import com.okwei.common.Limit;
import com.okwei.common.PageResult;
import com.okwei.service.IBaseService;
import com.sdicons.json.validator.impl.predicates.Int;

public interface IBaseSupplyDemandService extends IBaseService {

    /**
     * 发布招商需求
     * 
     * @param entity
     * @return
     */
    public boolean saveSupplyDemand(SupplyDemandVO demandVO);

    /**
     * 获取供应商招商需求列表
     * 
     * @return
     */
    public PageResult<SupplyDemandVO> getSupplyDemandVOs(Long weiID, Integer showAreaCount, DemandStateEnum state, Limit limit);

    /**
     * 获取供应商招商需求列表
     * 
     * @param weiID
     * @return
     */
    public PageResult<SupplyDemandVO> getSupplyDemandVOs(Long weiID, Limit limit);

    /**
     * 获取我的招商需求按 状态分组
     * 
     * @param weiID
     * @return
     */
    public Map<DemandStateEnum, Long> getMyDemandStateCount(Long weiID);

    /**
     * 获取用户查看的招商需求列表
     * 
     * @param loginUserID
     *            登陆ID
     * @param weiID
     *            发布人
     * @param title
     *            标题 模糊查询
     * @param showPCount
     *            展示多少个商品
     * @param limit
     * @return
     */
    public PageResult<SupplyDemandVO> getUserDemandVos(LoginUser user, Long weiID, Integer industryID, Integer provice, Integer city, String title, int showPCount, int areaCount, Limit limit);

    /**
     * 认证员查看的招商需求列表
     * 
     * @param provice
     * @param city
     * @param limit
     * @return
     */
    public PageResult<SupplyDemandVO> getVerifierDemandVos(Integer provice, Integer city, Date stateTime, Date endTime, Integer showAreaCount, Limit limit);

    /**
     * 获取招商标题列表
     * 
     * @param weiID
     * @param limit
     * @return
     */
    public PageResult<SupplyDemandVO> getDemandTitleList(Long weiID, Limit limit);

    /**
     * 获取招商需求详情
     * 
     * @param demandID
     * @return
     */
    public SupplyDemandVO getSupplyDemandVO(Integer demandID);

    /**
     * 分页获取招商需求的商品列表
     * 
     * @param demandID
     * @return
     */
    public PageResult<DemandProductVO> getDemandProducts(Integer demandID, Limit limit);

    /**
     * 获取供应商没加入需求的商品
     * 
     * @param weiID
     * @param title
     *            商品标题
     * @param calssType
     *            商品类型
     * @param limit
     * @return
     */
    public PageResult<DemandProductVO> getNoDemandProducts(Long weiID, String title, Integer calssType, Limit limit);

    /**
     * 招商需求置顶
     * 
     * @return
     */
    public boolean editDemandTop(int demandID, long weiID);

    /**
     * 招商需求状态修改
     * 
     * @param demandID
     * @param weiID
     * @return
     */
    public ResultMsg editDemandState(Integer[] demandID, long weiID, DemandStateEnum state);

    /**
     * 搜索每个需求的渠道商数量
     * 
     * @param weiID
     * @param searchStr
     * @param searchType
     * @return
     */
    public List<DemandChannelVO> getSearchChannelCount(Long weiID, String searchStr, SupplyChannelTypeEnum searchType);

    /**
     * 搜索每个需求 符合条件的渠道商
     * 
     * @param weiID
     *            平台号
     * @param demandID
     *            需求ID
     * @param searchStr
     *            搜索关键字
     * @param searchType
     *            搜索类型 （代理商，落地店）
     * @param limit
     * @return
     */
    public PageResult<ChannelInfoVO> getSearchChannel(Long weiID, Integer demandID, String searchStr, SupplyChannelTypeEnum searchType, Integer code, Limit limit);

    /**
     * 获取已铺设的渠道区域
     * 
     * @param weiID
     * @param demandID
     * @param code
     * @return
     */
    public List<ChannelRegionVO> getChannelRegions(Long weiID, Integer demandID, Integer code, Integer dayNum);

    /**
     * 获取未招商的区域
     * 
     * @param weiID
     * @param demandID
     * @param limit
     * @return
     */
    public PageResult<ChannelRegionVO> getNoChannelRegions(Long weiID, Integer demandID, Limit limit);

    /**
     * 获取用户店铺分类
     * 
     * @param weiID
     * @param level
     * @param parentID
     * @return
     */
    public List<ShopClassVO> getShopClassVOs(Long weiID, Short level, Integer parentID);

    /**
     * 获取渠道供应商
     * 
     * @param weiID
     * @return
     */
    public ChannelSupplyVO getChannelSupply(Long weiID);

    /**
     * 获取省市区列表
     * 
     * @param lever
     * @param parent
     * @return
     */
    public List<TRegional> getRegionals(Short lever, Integer parent);
}
