package com.okwei.dao.user;

import java.security.PublicKey;
import java.util.List;

import com.okwei.bean.domain.PPriceShow;
import com.okwei.bean.domain.PProductRelation;
import com.okwei.bean.domain.PProducts;
import com.okwei.bean.domain.PShopClass;
import com.okwei.bean.domain.TBussinessClass;
import com.okwei.bean.domain.TRegional;
import com.okwei.bean.domain.UAttention;
import com.okwei.bean.domain.UDemandProduct;
import com.okwei.bean.domain.UDemandRequired;
import com.okwei.bean.domain.UProductAgent;
import com.okwei.bean.domain.URequiredKv;
import com.okwei.bean.domain.USupplierIndustryCategory;
import com.okwei.bean.domain.USupplyChannel;
import com.okwei.bean.domain.USupplyDemand;
import com.okwei.bean.domain.USupplyDemandArea;
import com.okwei.bean.domain.USupplyer;
import com.okwei.bean.domain.UWeiSeller;
import com.okwei.bean.dto.user.SupplyChannelDTO;
import com.okwei.bean.dto.user.SupplyDemandDTO;
import com.okwei.bean.enums.DemandStateEnum;
import com.okwei.bean.enums.RegionLevelEnum;
import com.okwei.bean.vo.DemandProductVO;
import com.okwei.bean.vo.SupplyDemandVO;
import com.okwei.bean.vo.user.ChannelInfoVO;
import com.okwei.bean.vo.user.ChannelRegionVO;
import com.okwei.common.Limit;
import com.okwei.common.PageResult;
import com.okwei.dao.IBaseDAO;

public interface IBaseSupplyDemandDAO extends IBaseDAO {

	/**
	 * 删除招商需求代理地区
	 * @param demandID
	 */
	public void deleteDemandArea(int demandID);
	
	/**
	 * 删除招商需求 地区条件
	 * @param demandID
	 */
	public void deleteDemandRequired(int demandID);
	
	/**
	 * 删除招商需求 地区 量化条件
	 * @param demandID
	 */
	public void deleteRequiredKV(int demandID);
	
	/**
	 * 删除招商需求 商品关系列表
	 * @param demandID
	 */
	public void deleteDemandProduct(int demandID);
	
	/**
	 * 获取招商需求的代理区域列表
	 * @param demandID
	 * @return
	 */
	public List<USupplyDemandArea> getDemandAreas(int demandID);
	
	/**
	 * 获取招商需求的区域要求 区域列表
	 * @param demandID
	 * @return
	 */
	public List<USupplyDemandArea> getRequiredAddress(int requiredID);
	
	/**
	 * 获取招商需求的地区列表 限制某个省
	 * @param demandID
	 * @param proviceCode
	 * @return
	 */
	public List<USupplyDemandArea> getDemandAreas(int demandID,Integer proviceCode);
		
	/**
	 * 获取招商需求的 区域要求 列表
	 * @param demandID
	 * @return
	 */
	public List<UDemandRequired> getDemandRequireds(int demandID);
	
	/**
	 * 获取招商需求的 量化要求列表
	 * @param demandID
	 * @return
	 */
	public List<URequiredKv> getDemandKvs(int demandID);
	
	/**
	 * 获取招商需求的 区域条件的  量化要求列表
	 * @param requiredID
	 * @return
	 */
	public List<URequiredKv> getRequiredKvs(int requiredID);
		
	
	/**
	 * 获取招商需求列表
	 * @param param
	 * @param limit
	 * @return
	 */
	public PageResult<SupplyDemandVO> getDemandVos(SupplyDemandDTO dto,Limit limit);
	
	/**
	 * 获取用户展示中的招商需求列表
	 * @param weiID
	 * @return
	 */
	public List<USupplyDemand> getDemands(Long weiID);
	
	/**
	 * 招商需求批量修改状态
	 * @param demandIDs
	 */
	public int editDemandState(Integer[] demandIDs,long weiID,DemandStateEnum state);
	
	/**
	 * 获取渠道商总数 不区分落地店
	 * @param demandIDs
	 * @return
	 */
	public int getChannelCount(Integer[] demandIDs);
	
	/**
	 * 获取申请中的 代理商数量
	 * @param demandIDs
	 * @return
	 */
	public int getApplyingCount(Integer[] demandIDs);
	
	/**
	 * 删除招商需求下的渠道商
	 * @param demandIDs
	 */
	public void deleteDemandChannel(Integer[] demandIDs);
	
	/**
	 * 删除招商需求下的商品
	 * @param demandIDs
	 */
	public void deleteDemandProduct(Integer[] demandIDs);
	
	/**
	 * 获取代理商的代理列表
	 * @param weiID
	 * @return
	 */
	public List<USupplyChannel> getUserAgentList(long weiID);

	
	/**
	 * 获取招商需求的商品关系列表
	 * @param demandIDs
	 * @return
	 */
	public List<UDemandProduct> getDemandProducts(Integer[] demandIDs);
	/**
	 * 获取招商需求的商品关系列表
	 * @param demandIDs
	 * @return
	 */
	public List<UDemandProduct> getDemandProducts(Integer demandID);
	
	/**
	 * 获取招商需关联的商品数量
	 * @param demandIDs
	 * @return
	 */
	public List<Object[]> getDemandProductCount(Integer[] demandIDs);
	
	/**
	 * 根据商品ID数组  获取商品列表
	 * @param productIDs
	 * @param topCount
	 * @return
	 */
	public List<PProducts> getProducts(Long[] productIDs,int topCount);
	
	/**
	 * 获取招商需求的区域列表  by 招商集合IDs
	 * @param demandIDs
	 * @return
	 */
	public List<USupplyDemandArea> getDemandAreas(Integer[] demandIDs);
	
	/**
	 * 根据编码集合 获取地区列表
	 * @param codes
	 * @return
	 */
	public List<TRegional> getRegionals(Integer[] codes);

	/**
	 * 获取供应商行业类型
	 * @param weiID
	 * @return
	 */
	public List<USupplierIndustryCategory> getCategorys(Long weiID,Short categoryType);
	
	/**
	 *获取行业类型表
	 * @param classIDs
	 * @return
	 */
	public List<TBussinessClass> getBussinessClassList(Integer[] classIDs);
		
	/**
	 * 模糊查询平台号的代理商数量 根据 需求ID 分组
	 */
	public List<Object[]> getAgentsCountByDemand(Long weiID,String searchStr);
	
	/**
	 * 模糊查询平台号的落地店数量 根据 需求ID 分组
	 */
	public List<Object[]> getShopCountByDemand(Long weiID,String searchStr);
	
	/**
	 * 查询平台号的代理商数量 根据区域分组
	 * @param weiID
	 * @param demandID
	 * @return
	 */
	public List<Object[]> getAgentsCountByRegion(Long weiID,Integer demandID,Integer code,RegionLevelEnum reg,Integer dayNum);
	
	/**
	 * 查询平台号的落地店数量 根据区域分组
	 * @param weiID
	 * @param demandID
	 * @return
	 */
	public List<Object[]> getShopCountByRegion(Long weiID,Integer demandID,Integer code,RegionLevelEnum reg,Integer dayNum);
	
	/**
	 * 查询招商需求列表
	 * @param demandIDs
	 * @return
	 */
	public List<USupplyDemand> getDemands(Integer[] demandIDs);
	
	
	/**
	 * 获取用户集合
	 * @param weiIDs 
	 * @return
	 */
	public List<UWeiSeller> getWeiSellers(Long[] weiIDs);
	
	/**
	 * 获取供应商
	 * @param weiIDs
	 * @return
	 */
	public List<USupplyer> getSupplyers(Long[] weiIDs);
	/**
	 * 搜索符合条件的代理商
	 * @param weiID
	 * @param searchStr
	 * @return
	 */
	public PageResult<ChannelInfoVO> getSearchAgent(Long weiID,Integer demandID,String searchStr,Integer code,RegionLevelEnum reg,Limit limit);
	
	/**
	 * 搜索符合条件的落地店
	 * @param weiID
	 * @param demandID
	 * @param searchStr
	 * @return
	 */
	public PageResult<ChannelInfoVO> getSearchShop(Long weiID,Integer demandID,String searchStr,Integer code,RegionLevelEnum reg,Limit limit);

	/**
	 * 获取没有渠道商的区域
	 * @param weiID
	 * @param demandID
	 * @param limit
	 * @return
	 */
	public PageResult<ChannelRegionVO> getNoChannelRegions(Long weiID,Integer demandID,Limit limit);
	
	/**
	 * 获取招商需求的商品列表
	 * @param demandID
	 * @param limit
	 * @return
	 */
	public PageResult<DemandProductVO> getDemandProducts(Integer demandID,Limit limit);
	
	/**
	 * 获取商品辅助列表
	 * @param productIDs
	 * @return
	 */
	public List<PProductRelation> getProductRelations(Long[] productIDs);
	
	/**
	 * 获取店铺分类列表
	 * @param sids
	 * @return
	 */
	public List<PShopClass> getShopClasses(Integer[] sids);
	
	/**
	 * 获取没加入招商需求的商品列表
	 * @param weiID
	 * @param title
	 * @param calssType
	 * @param limit
	 * @return
	 */
	public PageResult<DemandProductVO> getNoDemandProducts(Long weiID,String title, Integer[] calssTypes, Limit limit);
	
	/**
	 * 获取用户店铺分类列表
	 * @param weiID
	 * @param parentID
	 * @return
	 */
	public List<PShopClass> getShopClasses(Long weiID,Short level, Integer parentID);
	
	/**
	 * 获取用户招商需求每个状态的数量
	 * @param weiID
	 * @return
	 */
	public List<Object[]> getMyDemandStateCount(Long weiID);
	
	/**
	 * 获取省市区列表
	 * @param lever
	 * @param code
	 * @return
	 */
	public List<TRegional> getRegionals(Short lever,Integer parent);
	
	/**
	 * 获取用户 关注某些用户
	 * @param weiID
	 * @param attentions
	 * @return
	 */
	public List<UAttention> getAttentions(Long weiID,Long[] attentions);
	
	/**
	 * 获取价格可视范围
	 * @param weiIDs
	 * @return
	 */
	public List<PPriceShow> getPriceShows(Long[] weiIDs);
	
	
}
