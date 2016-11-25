package com.okwei.service.impl.user;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javassist.expr.NewArray;
import net.sf.ehcache.search.impl.BaseResult;

import org.apache.struts2.components.Select;
import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.parsing.Problem;
import org.springframework.stereotype.Service;

import com.okwei.bean.domain.PPriceShow;
import com.okwei.bean.domain.PProductRelation;
import com.okwei.bean.domain.PProducts;
import com.okwei.bean.domain.PShopClass;
import com.okwei.bean.domain.TBussinessClass;
import com.okwei.bean.domain.TRegional;
import com.okwei.bean.domain.UAttention;
import com.okwei.bean.domain.UBrandSupplyer;
import com.okwei.bean.domain.UDemandProduct;
import com.okwei.bean.domain.UDemandRequired;
import com.okwei.bean.domain.UPlatformSupplyer;
import com.okwei.bean.domain.UProductAgent;
import com.okwei.bean.domain.URequiredKv;
import com.okwei.bean.domain.USupplierIndustryCategory;
import com.okwei.bean.domain.USupplyChannel;
import com.okwei.bean.domain.USupplyDemand;
import com.okwei.bean.domain.USupplyDemandArea;
import com.okwei.bean.domain.USupplyer;
import com.okwei.bean.domain.UUserAssist;
import com.okwei.bean.domain.UWeiSeller;
import com.okwei.bean.dto.user.SupplyDemandDTO;
import com.okwei.bean.enums.DemandStateEnum;
import com.okwei.bean.enums.RegionLevelEnum;
import com.okwei.bean.enums.SupplierIndustryType;
import com.okwei.bean.enums.SupplyChannelTypeEnum;
import com.okwei.bean.enums.UserIdentityType;
import com.okwei.bean.vo.DemandProductVO;
import com.okwei.bean.vo.LoginUser;
import com.okwei.bean.vo.RegionVO;
import com.okwei.bean.vo.ResultMsg;
import com.okwei.bean.vo.ShopClassVO;
import com.okwei.bean.vo.SupplyDemandVO;
import com.okwei.bean.vo.product.ProductInfo;
import com.okwei.bean.vo.user.ChannelInfoVO;
import com.okwei.bean.vo.user.ChannelRegionVO;
import com.okwei.bean.vo.user.ChannelSupplyVO;
import com.okwei.bean.vo.user.DemandChannelVO;
import com.okwei.bean.vo.user.DemandRequiredVO;
import com.okwei.bean.vo.user.RequiredKVVO;
import com.okwei.common.Limit;
import com.okwei.common.PageResult;
import com.okwei.dao.user.IBaseSupplyDemandDAO;
import com.okwei.service.impl.BaseService;
import com.okwei.service.impl.product.HouseService;
import com.okwei.service.product.IHouseService;
import com.okwei.service.user.IBaseSupplyDemandService;
import com.okwei.util.ImgDomain;
import com.okwei.util.RedisUtil;
import com.sdicons.json.validator.impl.predicates.Int;

@Service
public class BaseSupplyDemandService extends BaseService implements IBaseSupplyDemandService {

    @Autowired
    private IBaseSupplyDemandDAO dao;
    @Autowired
    private IHouseService houseService;

    @Override
    public boolean saveSupplyDemand(SupplyDemandVO demandVO) {

	USupplyDemand demand = new USupplyDemand();
	// 如果ID大于0 更新
	if (demandVO.getDemandId() != null && demandVO.getDemandId() > 0) {
	    // 获取数据库实体
	    demand = dao.get(USupplyDemand.class, demandVO.getDemandId());
	    // 判断操作权限
	    if (demand.getWeiId() == null || !demand.getWeiId().equals(demandVO.getWeiId())) {
		return false;
	    }
	    // 只能被修改为 草稿 或 待审核
	    if (demandVO.getState().equals(Short.parseShort(DemandStateEnum.Draft.toString())) || demandVO.getState().equals(Short.parseShort(DemandStateEnum.WaitAuditing.toString()))) {

		demand.setState(demandVO.getState());
	    }

	    // 删除招商需求地区条件表.
	    dao.deleteDemandRequired(demandVO.getDemandId());
	    // 删除招商需求量化要求表
	    dao.deleteRequiredKV(demandVO.getDemandId());
	    // 删除区域表
	    dao.deleteDemandArea(demandVO.getDemandId());
	    // 删除招商需求 商品列表
	    dao.deleteDemandProduct(demandVO.getDemandId());

	} else {
	    if (!demandVO.getState().equals(Short.parseShort(DemandStateEnum.WaitAuditing.toString()))) {
		demand.setState(Short.parseShort(DemandStateEnum.Draft.toString()));
	    } else {
		demand.setState(Short.parseShort(DemandStateEnum.WaitAuditing.toString()));
	    }
	}
	demand.setAgentRequired(demandVO.getAgentRequired());
	demand.setAppDetails(demandVO.getAppDetails());
	demand.setDemandId(demand.getDemandId());
	demand.setMainImage(ImgDomain.ReplitImgDoMain(demandVO.getMainImage()));
	demand.setNumberRequired(demandVO.getNumberRequired());
	demand.setOrderAmout(demandVO.getOrderAmout());
	demand.setPcDetails(demandVO.getPcDetails());
	demand.setShopReward(demandVO.getShopReward());
	demand.setSupport(demandVO.getSupport());
	demand.setTitle(demandVO.getTitle());
	demand.setWeiId(demandVO.getWeiId());
	demand.setProductCount(0);

	// 地区条件列表
	if (demandVO.getdRequiredVOs() != null && demandVO.getdRequiredVOs().size() > 0) {
	    // 代理商悬赏的最大值最小值
	    Double maxAgentReward = demandVO.getdRequiredVOs().get(0).getAgentReward();
	    Double minAgentReward = demandVO.getdRequiredVOs().get(0).getAgentReward();

	    for (DemandRequiredVO item : demandVO.getdRequiredVOs()) {
		if (item.getAgentReward() > maxAgentReward) {
		    maxAgentReward = item.getAgentReward();
		}
		if (item.getAgentReward() < minAgentReward) {
		    minAgentReward = item.getAgentReward();
		}

	    }
	    demand.setMaxAgentReward(maxAgentReward);
	    demand.setMinAgentReward(minAgentReward);
	}
	// 招商需求商品数量统计
	if (demandVO.getdProductVOs() != null && demandVO.getdProductVOs().size() > 0) {
	    demand.setProductCount(demandVO.getdProductVOs().size());
	}

	if (demand.getDemandId() != null && demand.getDemandId() > 0) {
	    // 更新
	    dao.update(demand);
	} else {
	    demand.setTopTime(new Date());
	    demand.setCreateTime(new Date());
	    demand.setAgentCount(0);
	    demand.setShopCount(0);
	    // 添加
	    int demandID = (int) dao.save(demand);
	    if (demandID < 1) {
		return false;
	    }
	    demand.setDemandId(demandID);
	}

	// 添加商品列表
	if (demandVO.getdProductVOs() != null && demandVO.getdProductVOs().size() > 0) {
	    for (DemandProductVO item : demandVO.getdProductVOs()) {
		UDemandProduct dp = new UDemandProduct();
		dp.setCreateTime(new Date());
		dp.setDemandId(demand.getDemandId());
		dp.setProductId(item.getProductID());
		dao.save(dp);
	    }
	}

	// 添加区域条件
	if (demandVO.getdRequiredVOs() != null && demandVO.getdRequiredVOs().size() > 0) {
	    for (DemandRequiredVO requiredVO : demandVO.getdRequiredVOs()) {
		// 保持区域条件
		UDemandRequired dRequired = new UDemandRequired();
		dRequired.setAgentRequired(requiredVO.getAgentRequired());
		dRequired.setAgentReward(requiredVO.getAgentReward());
		dRequired.setContract(requiredVO.getContract());
		dRequired.setContractPath(ImgDomain.ReplitImgDoMain(requiredVO.getContractPath()));
		dRequired.setCreateTime(new Date());
		dRequired.setDemandId(demand.getDemandId());
		dRequired.setSupport(requiredVO.getSupport());
		int requiredID = (int) dao.save(dRequired);

		if (requiredID > 0) {
		    // 保存区域
		    if (requiredVO.getRegionVOs() != null && requiredVO.getRegionVOs().size() > 0) {
			for (RegionVO region : requiredVO.getRegionVOs()) {
			    if (region.getCode() == null || region.getCode() < 0) {
				continue;
			    }

			    TRegional reg = dao.get(TRegional.class, region.getCode());
			    if (reg == null) {
				continue;
			    }
			    USupplyDemandArea demandArea = new USupplyDemandArea();
			    switch (reg.getLevel()) {
				case 2:
				    demandArea.setProvince(reg.getCode());
				    break;
				case 3:
				    demandArea.setProvince(reg.getParent());
				    demandArea.setCity(reg.getCode());
				    break;
				default:
				    demandArea.setProvince(region.getProvice());
				    demandArea.setCity(region.getCity());
				    demandArea.setArea(region.getArea());
				    break;
			    }

			    demandArea.setCreateTime(new Date());
			    demandArea.setDemandId(demand.getDemandId());
			    demandArea.setRequiredId(requiredID);
			    demandArea.setCode(region.getCode());
			    dao.save(demandArea);
			}
		    }
		    // 保存量化要求
		    if (requiredVO.getRequiredKVVOs() != null && requiredVO.getRequiredKVVOs().size() > 0) {
			for (RequiredKVVO kvvo : requiredVO.getRequiredKVVOs()) {
			    URequiredKv keyValue = new URequiredKv();
			    keyValue.setCreateTime(new Date());
			    keyValue.setDemandId(demand.getDemandId());
			    keyValue.setRequiredId(requiredID);
			    keyValue.setRkey(kvvo.getRkey());
			    keyValue.setRvalue(kvvo.getRvalue());
			    dao.save(keyValue);
			}
		    }
		}
	    }
	}

	return true;
    }

    public PageResult<SupplyDemandVO> getSupplyDemandVOs(Long weiID, Limit limit) {
	if (weiID == null || weiID < 1) {
	    return null;
	}
	// 查询参数设定
	SupplyDemandDTO param = new SupplyDemandDTO();
	param.setWeiId(weiID);
	param.setState(DemandStateEnum.Showing);
	// 查询招商列表
	PageResult<SupplyDemandVO> demands = dao.getDemandVos(param, limit);
	if (demands == null || demands.getList() == null || demands.getList().size() < 1) {
	    return null;
	}
	for (SupplyDemandVO item : demands.getList()) {
	    item.setMainImage(ImgDomain.GetFullImgUrl(item.getMainImage()));// 处理图片地址
	}

	return demands;
    }

    @Override
    public PageResult<SupplyDemandVO> getSupplyDemandVOs(Long weiID, Integer showAreaCount, DemandStateEnum state, Limit limit) {
	// 查询参数设定
	SupplyDemandDTO param = new SupplyDemandDTO();
	param.setWeiId(weiID);
	param.setState(state);
	// 查询招商列表
	PageResult<SupplyDemandVO> demands = dao.getDemandVos(param, limit);
	if (demands == null || demands.getList() == null || demands.getList().size() < 1) {
	    return null;
	}
	String weiName = "";
	UWeiSeller seller = dao.get(UWeiSeller.class, weiID);
	if (seller != null) {
	    weiName = seller.getWeiName();
	}
	// 数据整理
	List<Integer> demandIDs = new ArrayList<Integer>();
	for (SupplyDemandVO item : demands.getList()) {
	    demandIDs.add(item.getDemandId());
	    item.setWeiName(weiName);
	    item.setMainImage(ImgDomain.GetFullImgUrl(item.getMainImage()));// 处理图片地址
	    item.setRegionVOs(getDemandRegionVos(item.getDemandId()));
	    if (item.getRegionVOs() != null && item.getRegionVOs().size() > 0) {
		String areaName = "";
		if (showAreaCount == null || showAreaCount < 1) {
		    showAreaCount = item.getRegionVOs().size();
		}
		for (int i = 0; i < item.getRegionVOs().size() && i < showAreaCount; i++) {
		    areaName += item.getRegionVOs().get(i).getCodeName() + " ";
		}
		if (item.getRegionVOs().size() > showAreaCount) {
		    areaName += "等";
		}
		item.setAreaString(areaName);
	    }

	}

	// 商品数量统计
	List<Object[]> dpCountList = dao.getDemandProductCount((Integer[]) demandIDs.toArray(new Integer[demandIDs.size()]));
	if (dpCountList != null && dpCountList.size() > 0) {
	    for (SupplyDemandVO demand : demands.getList()) {
		for (Object[] objs : dpCountList) {
		    if (demand.getDemandId().equals(Integer.parseInt(objs[0].toString()))) {
			demand.setpCount(Integer.parseInt(objs[1].toString()));
			break;
		    }
		}
	    }
	}

	return demands;
    }

    @Override
    public PageResult<SupplyDemandVO> getVerifierDemandVos(Integer provice, Integer city, Date stateTime, Date endTime, Integer showAreaCount, Limit limit) {

	SupplyDemandDTO dto = new SupplyDemandDTO();
	dto.setProvice(provice);
	dto.setCity(city);
	dto.setStartTime(stateTime);
	dto.setEndTime(endTime);

	PageResult<SupplyDemandVO> demands = dao.getDemandVos(dto, limit);
	if (demands == null || demands.getList() == null || demands.getList().size() < 1) {
	    return null;
	}
	List<Integer> demandIDs = new ArrayList<Integer>();
	for (SupplyDemandVO demand : demands.getList()) {
	    demandIDs.add(demand.getDemandId());
	    // 获取用户信息
	    UWeiSeller seller = dao.get(UWeiSeller.class, demand.getWeiId());
	    if (seller != null) {
		demand.setWeiName(seller.getWeiName());
	    }
	    // 获取代理区域
	    demand.setRegionVOs(getDemandRegionVos(demand.getDemandId()));
	    if (demand.getRegionVOs() != null && demand.getRegionVOs().size() > 0) {
		String areaName = "";
		if (showAreaCount == null || showAreaCount < 1) {
		    showAreaCount = demand.getRegionVOs().size();
		}
		for (int i = 0; i < demand.getRegionVOs().size() && i < showAreaCount; i++) {
		    areaName += demand.getRegionVOs().get(i).getCodeName() + " ";
		}
		if (demand.getRegionVOs().size() > showAreaCount) {
		    areaName += "等";
		}
		demand.setAreaString(areaName);
	    }
	    // 时间
	    java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
	    demand.setCreateTimeString(format.format(demand.getCreateTime()));
	}
	// 商品数量统计
	List<Object[]> dpCountList = dao.getDemandProductCount((Integer[]) demandIDs.toArray(new Integer[demandIDs.size()]));
	if (dpCountList != null && dpCountList.size() > 0) {
	    for (SupplyDemandVO demand : demands.getList()) {
		for (Object[] objs : dpCountList) {
		    if (demand.getDemandId().equals(Integer.parseInt(objs[0].toString()))) {
			demand.setpCount(Integer.parseInt(objs[1].toString()));
			break;
		    }
		}
	    }
	}
	return demands;
    }

    @Override
    public PageResult<SupplyDemandVO> getUserDemandVos(LoginUser user, Long weiID, Integer industryID, Integer provice, Integer city, String title, int showPCount, int areaCount, Limit limit) {
	String keyName = "DemandProductsList" + String.valueOf(industryID) + "_" + String.valueOf(provice) + "_" + String.valueOf(city) + "_" + title + String.valueOf(showPCount) + "_" + String.valueOf(areaCount) + "_" + String.valueOf(limit.getPageId()) + "_" + String.valueOf(limit.getSize());
	PageResult<SupplyDemandVO> result = null;
	try {
	    result = (PageResult<SupplyDemandVO>) RedisUtil.getObject(keyName);
	} catch (Exception e) {

	}
	Long[] weiIDLongs = null;
	PageResult<SupplyDemandVO> demands = null;
	if (result == null) {

	    // 查询参数设定
	    SupplyDemandDTO param = new SupplyDemandDTO();
	    param.setTitle(title);
	    param.setState(DemandStateEnum.Showing);
	    param.setProvice(provice);
	    param.setCity(city);
	    param.setIndustryID(industryID);
	    param.setWeiId(weiID);
	    param.setShowPCount(showPCount);

	    // 查询招商列表
	    demands = dao.getDemandVos(param, limit);
	    if (demands == null || demands.getList() == null || demands.getList().size() < 1) {
		return null;
	    }

	    List<Integer> demandIDs = new ArrayList<Integer>(); // 所有代理需求的ID集合
	    List<Long> weiIDs = new ArrayList<Long>(); // 平台号ID集合
	    // 数据整理
	    for (SupplyDemandVO item : demands.getList()) {
		demandIDs.add(item.getDemandId());
		weiIDs.add(item.getWeiId());
		item.setMainImage(ImgDomain.GetFullImgUrl(item.getMainImage()));// 处理图片地址
		item.setRegionVOs(getDemandRegionVos(item.getDemandId()));// 获取地区列表

		// 招商需求商品 总数 及展示前多少件商品
		PageResult<DemandProductVO> dpResult = getDemandProducts(item.getDemandId(), Limit.buildLimit(1, showPCount));
		if (dpResult != null && dpResult.getList() != null && dpResult.getList().size() > 0) {
		    item.setpCount(dpResult.getTotalCount());
		    item.setdProductVOs(dpResult.getList());
		}

		// 地区字符串？
		if (item.getRegionVOs() != null && item.getRegionVOs().size() > 0) {
		    String areaName = "";
		    for (int i = 0; i < item.getRegionVOs().size() && i < areaCount; i++) {
			areaName += item.getRegionVOs().get(i).getCodeName() + " ";
		    }
		    if (item.getRegionVOs().size() > areaCount) {
			areaName += "...";
		    }
		    item.setAreaString(areaName);
		}
		// 中文 时间 今天 昨天 月-日
		Calendar now = Calendar.getInstance();
		now.setTime(new Date());

		Calendar create = Calendar.getInstance();
		create.setTime(item.getCreateTime());
		if (now.get(Calendar.YEAR) == create.get(Calendar.YEAR) && now.get(Calendar.MONDAY) == create.get(Calendar.MONDAY) && now.get(Calendar.DAY_OF_MONTH) == create.get(Calendar.DAY_OF_MONTH)) {
		    item.setCreateTimeString("今天");
		} else if (now.get(Calendar.YEAR) == create.get(Calendar.YEAR) && now.get(Calendar.MONDAY) == create.get(Calendar.MONDAY) && (now.get(Calendar.DAY_OF_MONTH) - create.get(Calendar.DAY_OF_MONTH)) == -1) {
		    item.setCreateTimeString("昨天");
		} else {
		    item.setCreateTimeString((create.get(Calendar.MONDAY) + 1) + "月" + create.get(Calendar.DAY_OF_MONTH) + "日");
		}
	    }
	    weiIDLongs = (Long[]) weiIDs.toArray(new Long[weiIDs.size()]);
	    // 平台号信息 批量获取
	    List<UWeiSeller> weiSellers = dao.getWeiSellers(weiIDLongs);
	    if (weiSellers != null && weiSellers.size() > 0) {
		for (SupplyDemandVO item : demands.getList()) {
		    for (UWeiSeller seller : weiSellers) {
			if (item.getWeiId().equals(seller.getWeiId())) {
			    item.setWeiName(seller.getWeiName());
			    break;
			}
		    }
		}
	    }
	    // 平台号 客服QQ
	    List<USupplyer> supplyList = dao.getSupplyers(weiIDLongs);
	    if (supplyList != null && supplyList.size() > 0) {
		for (SupplyDemandVO item : demands.getList()) {
		    for (USupplyer supplyer : supplyList) {
			if (item.getWeiId().equals(supplyer.getWeiId())) {
			    item.setCompanyName(supplyer.getCompanyName());
			    if (supplyer.getServiceQQ() != null && !"".equals(supplyer.getServiceQQ())) {
				String[] serverQQs = supplyer.getServiceQQ().split("\\|");
				List<String> qqList = new ArrayList<String>();
				for (String string : serverQQs) {
				    qqList.add(string);
				}
				item.setQqList(qqList);
			    }
			    break;
			}
		    }
		}
	    }
	    result = demands;
	    RedisUtil.setObject(keyName, result, 600);
	}

	// 如果用户已经登陆
	if (user != null && user.getWeiID() != null && user.getWeiID() > 0) {
	    if (weiIDLongs != null && demands != null && demands.getList() != null) {
		List<PPriceShow> priceShows = dao.getPriceShows(weiIDLongs);// 代理商价格可是范围设定
		List<USupplyChannel> agentList = dao.getUserAgentList(user.getWeiID());// 用户的代理列表
		// 是否已经代理该需求

		if (agentList != null && agentList.size() > 0) {
		    for (SupplyDemandVO item : demands.getList()) {
			ProductInfo pinfo = new ProductInfo();// 标识 是否是该供应商的 代理商
							      // 落地店
			for (USupplyChannel agent : agentList) {
			    // 如果是他的代理商
			    if (item.getDemandId().equals(agent.getDemandId()) && agent.getChannelType().equals(Short.parseShort(SupplyChannelTypeEnum.Agent.toString()))) {
				pinfo.setCurrentUserIsAgent(1);
				item.setAgented(true);
			    }
			    // 如果是他的落地店
			    if (item.getDemandId().equals(agent.getDemandId()) && agent.getChannelType().equals(Short.parseShort(SupplyChannelTypeEnum.ground.toString()))) {
				pinfo.setCurrentUserIsStore(1);
			    }
			}
			// 该用户是否有权限查看
			if (priceShows == null) {
			    continue;
			}
			//
			for (PPriceShow show : priceShows) {
			    if (item.getWeiId().equals(show.getWeiId())) {
				pinfo = houseService.getPriceShow(user, show, pinfo);
				// 权限赋予
				if (pinfo != null && item.getdProductVOs() != null && item.getdProductVOs().size() > 0) {
				    for (DemandProductVO product : item.getdProductVOs()) {
					product.setIsShowAgent(pinfo.getCurrentUserIsAgent());
					product.setIsShowLoad(pinfo.getCurrentUserIsStore());
				    }
				}
				break;
			    }
			}
		    }
		}
		// 是否已经关注
		List<UAttention> attenList = dao.getAttentions(user.getWeiID(), weiIDLongs);
		if (attenList != null && attenList.size() > 0) {
		    for (SupplyDemandVO item : demands.getList()) {
			for (UAttention atten : attenList) {
			    if (item.getWeiId().equals(atten.getAttTo())) {
				item.setAttened(true);
				break;
			    }
			}
		    }
		}
	    }
	    // end
	}

	// 代理需求 与 商品的 关系
	/*
	 * List<UDemandProduct> dpList =
	 * dao.getDemandProducts((Integer[])demandIDs.toArray(new
	 * Integer[demandIDs.size()])); if(dpList !=null && dpList.size()>0){
	 * 
	 * for (SupplyDemandVO item : demands.getList()) { int pCount
	 * =0;//计算商品数量 List<Long> productIDs = new ArrayList<Long>(); //该需求下
	 * 所有商品ID 集合 for (UDemandProduct dp : dpList) {
	 * if(item.getDemandId().equals(dp.getDemandId())){ pCount ++;//数量自增
	 * productIDs.add(dp.getProductId()); } } item.setpCount(pCount);
	 * //获取该需求下的商品列表 List<PProducts> products =
	 * dao.getProducts((Long[])productIDs.toArray(new
	 * Long[productIDs.size()]), showPCount); if(products !=null &&
	 * products.size() >0){ List<DemandProductVO> productVOs = new
	 * ArrayList<DemandProductVO>(); for (PProducts product : products) {
	 * DemandProductVO productVO = new DemandProductVO();
	 * productVO.setDemandID(item.getDemandId());
	 * productVO.setProdcutTitle(product.getProductTitle());
	 * productVO.setProductID(product.getProductId());
	 * productVO.setProductImg
	 * (ImgDomain.GetFullImgUrl(product.getDefaultImg()));
	 * productVO.setProductMinTitle(product.getProductMinTitle());
	 * productVOs.add(productVO); } item.setdProductVOs(productVOs); } } }
	 */

	return result;
    }

    public PageResult<SupplyDemandVO> getDemandTitleList(Long weiID, Limit limit) {
	// 查询参数设定
	SupplyDemandDTO param = new SupplyDemandDTO();
	param.setWeiId(weiID);
	param.setState(DemandStateEnum.Showing);

	// 查询招商列表
	return dao.getDemandVos(param, limit);
    }

    @Override
    public SupplyDemandVO getSupplyDemandVO(Integer demandID) {
	if (demandID == null || demandID < 1) {
	    return null;
	}

	USupplyDemand demand = dao.get(USupplyDemand.class, demandID);
	if (demand == null) {
	    return null;
	}

	SupplyDemandVO demandVO = new SupplyDemandVO();
	demandVO.setTitle(demand.getTitle());
	demandVO.setAgentRequired(demand.getAgentRequired());
	demandVO.setAppDetails(demand.getAppDetails());
	demandVO.setWeiId(demand.getWeiId());
	demandVO.setShopReward(demand.getShopReward());
	demandVO.setCreateTime(demand.getCreateTime());
	demandVO.setDemandId(demand.getDemandId());
	demandVO.setMainImage(ImgDomain.GetFullImgUrl(demand.getMainImage(), 75));
	demandVO.setOrderAmout(demand.getOrderAmout());
	demandVO.setPcDetails(demand.getPcDetails());
	demandVO.setShopReward(demand.getShopReward());
	demandVO.setState(demand.getState());
	demandVO.setAgentCount(demand.getAgentCount());
	demandVO.setShopCount(demand.getShopCount());
	demandVO.setdRequiredVOs(getDemandRequiredVOs(demandID));// 区域条件列表
								 // 量化要求，地区列表

	// 组装地区名称
	if (demandVO.getdRequiredVOs() != null && demandVO.getdRequiredVOs().size() > 0) {
	    String areaString = "";
	    int areaCount = 0;
	    int nameCount = 0;
	    for (DemandRequiredVO required : demandVO.getdRequiredVOs()) {
		areaCount += required.getRegionVOs().size();
		if (required.getRegionVOs() != null && required.getRegionVOs().size() > 0) {
		    String codeNames = "";
		    for (ChannelRegionVO region : required.getRegionVOs()) {
			codeNames += region.getCodeName() + " ";
			if (nameCount < 5) {
			    areaString += region.getCodeName() + " ";
			}
			nameCount++;
		    }
		    required.setCodeName(codeNames);
		}
	    }
	    if (nameCount > 5) {
		areaString += "等" + nameCount + "省市";
	    }
	    demandVO.setAreaString(areaString);
	}
	// 商品列表
	PageResult<DemandProductVO> pageResult = getDemandProducts(demandID, Limit.buildLimit(1, 5));
	if (pageResult != null && pageResult.getList() != null) {
	    demandVO.setdProductVOs(pageResult.getList());
	    demandVO.setpCount(pageResult.getTotalCount());
	}

	// 平台号名称
	UWeiSeller seller = dao.get(UWeiSeller.class, demand.getWeiId());
	if (seller != null) {
	    demandVO.setWeiName(seller.getWeiName());
	    demandVO.setWeiImg(ImgDomain.GetFullImgUrl(seller.getImages()));
	    java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
	    demandVO.setCreateTimeString(format.format(seller.getRegisterTime()));
	}
	// 行业
	UUserAssist assist = dao.get(UUserAssist.class, demand.getWeiId());
	if (assist != null) {
	    if ((assist.getIdentity() & Integer.parseInt(UserIdentityType.BrandSupplier.toString())) > 0) {
		/*
		 * UBrandSupplyer supplyer = dao.get(UBrandSupplyer.class,
		 * demand.getWeiId()); if (supplyer != null) {
		 * demandVO.setSaleType(supplyer.getSaleType()); }
		 */
		demandVO.setBusCategoryStr(getCategoryString(demand.getWeiId(), Short.parseShort(SupplierIndustryType.brand.toString())));// 行业
	    } else if ((assist.getIdentity() & Integer.parseInt(UserIdentityType.PlatformSupplier.toString())) > 0) {
		/*
		 * UPlatformSupplyer supplyer = dao.get(UPlatformSupplyer.class,
		 * demand.getWeiId()); if (supplyer != null) {
		 * demandVO.setSaleType(supplyer.getSaleType()); }
		 */
		demandVO.setBusCategoryStr(getCategoryString(demand.getWeiId(), Short.parseShort(SupplierIndustryType.platform.toString())));// 行业
	    }
	}
	// 主营
	USupplyer supplyer = dao.get(USupplyer.class, demand.getWeiId());
	if (supplyer != null) {
	    demandVO.setSaleType(supplyer.getBusContent());
	}

	return demandVO;
    }

    @Override
    public PageResult<DemandProductVO> getDemandProducts(Integer demandID, Limit limit) {
	if (demandID == null || demandID < 1) {
	    return null;
	}
	PageResult<DemandProductVO> dpResult = dao.getDemandProducts(demandID, limit);
	if (dpResult != null && dpResult.getList() != null && dpResult.getList().size() > 0) {
	    // 数据整理
	    List<Long> productIDs = new ArrayList<Long>();
	    List<Integer> sids = new ArrayList<Integer>();
	    for (DemandProductVO productVO : dpResult.getList()) {
		productIDs.add(productVO.getProductID());
		productVO.setProductImg(ImgDomain.GetFullImgUrl(productVO.getProductImg(), 24));
		if (!sids.contains(productVO.getSid())) {
		    sids.add(productVO.getSid());
		}
	    }
	    List<PProductRelation> pRelations = dao.getProductRelations((Long[]) productIDs.toArray(new Long[productIDs.size()]));
	    if (pRelations != null && pRelations.size() > 0) {
		for (DemandProductVO productVO : dpResult.getList()) {
		    for (PProductRelation relations : pRelations) {
			if (productVO.getProductID().equals(relations.getProductId())) {
			    productVO.setAgentPrice(relations.getMinProxyPrice());
			    productVO.setShopPrice(relations.getMinFloorPrice());
			    break;
			}
		    }
		}
	    }

	    List<PShopClass> shopClasses = dao.getShopClasses((Integer[]) sids.toArray(new Integer[sids.size()]));
	    if (shopClasses != null && shopClasses.size() > 0) {
		for (DemandProductVO product : dpResult.getList()) {
		    for (PShopClass shopClass : shopClasses) {
			if (product.getSid().equals(shopClass.getSid())) {
			    product.setShopClassName(shopClass.getSname());
			    break;
			}
		    }
		}
	    }
	}

	return dpResult;
    }

    @Override
    public boolean editDemandTop(int demandID, long weiID) {
	if (demandID < 1 || weiID < 1) {
	    return false;
	}
	USupplyDemand demand = dao.get(USupplyDemand.class, demandID);
	if (demand == null || demand.getWeiId() != weiID) {
	    return false;
	}
	demand.setTopTime(new Date());
	dao.update(demand);

	return true;
    }

    @Override
    public ResultMsg editDemandState(Integer[] demandIDs, long weiID, DemandStateEnum state) {
	ResultMsg result = new ResultMsg();
	result.setStatus(2);
	result.setMsg("参数异常");
	if (demandIDs == null || demandIDs.length < 1 || weiID < 1) {
	    return result;
	}

	List<USupplyDemand> demandList = dao.getDemands(demandIDs);
	if (demandList == null || demandList.size() < 1) {
	    return result;
	}
	// 只能是该用户的 才允许修改
	List<Integer> myDemandIDList = new ArrayList<Integer>();
	for (USupplyDemand item : demandList) {
	    if (item.getWeiId() == weiID) {
		myDemandIDList.add(item.getDemandId());
	    }
	}
	if (myDemandIDList.size() < 1) {
	    return result;
	}
	Integer[] myDemandIDs = (Integer[]) myDemandIDList.toArray(new Integer[myDemandIDList.size()]);
	// 如果是删除需求 校验 是否有渠道商 包括申请中的
	if (state == DemandStateEnum.delete) {
	    int channelCount = dao.getChannelCount(myDemandIDs);
	    int applyCount = dao.getApplyingCount(myDemandIDs);
	    if (channelCount > 0 || applyCount > 0) {
		result.setMsg("招商需求拥有渠道商或申请中的代理商，不能删除！");
		return result;
	    }
	}

	int editCount = dao.editDemandState(myDemandIDs, weiID, state);
	if (editCount > 0) {
	    result.setStatus(1);
	    result.setMsg("Success");
	    if (state == DemandStateEnum.delete) {
		dao.deleteDemandChannel(myDemandIDs);
		dao.deleteDemandProduct(myDemandIDs);
	    }
	}

	return result;
    }

    @Override
    public List<DemandChannelVO> getSearchChannelCount(Long weiID, String searchStr, SupplyChannelTypeEnum searchType) {
	if (weiID == null || weiID < 1) {
	    return null;
	}

	List<Object[]> objList = new ArrayList<Object[]>();
	if (searchType == SupplyChannelTypeEnum.Agent) {
	    objList = dao.getAgentsCountByDemand(weiID, searchStr);
	} else if (searchType == SupplyChannelTypeEnum.ground) {
	    objList = dao.getShopCountByDemand(weiID, searchStr);
	}

	if (objList == null || objList.size() < 1) {
	    return null;
	}

	List<Integer> demandIDs = new ArrayList<Integer>();
	List<DemandChannelVO> result = new ArrayList<DemandChannelVO>();
	for (Object[] objs : objList) {

	    DemandChannelVO dcVO = new DemandChannelVO();
	    dcVO.setDemandID((Integer) objs[0]);
	    dcVO.setChannelCount(Integer.parseInt(objs[1].toString()));
	    result.add(dcVO);

	    demandIDs.add((Integer) objs[0]);
	}
	List<USupplyDemand> demands = dao.getDemands((Integer[]) demandIDs.toArray(new Integer[demandIDs.size()]));
	if (demands != null && demands.size() > 0) {
	    for (DemandChannelVO dcvo : result) {
		for (USupplyDemand demand : demands) {
		    if (demand.getDemandId().equals(dcvo.getDemandID())) {
			dcvo.setTitle(demand.getTitle()); // TODO： 是否需要截取？
			break;
		    }
		}
	    }
	}

	return result;
    }

    @Override
    public PageResult<ChannelInfoVO> getSearchChannel(Long weiID, Integer demandID, String searchStr, SupplyChannelTypeEnum searchType, Integer code, Limit limit) {
	if (weiID == null || weiID < 1) {
	    return null;
	}
	RegionLevelEnum reg;
	if (code != null && code > 0) {
	    reg = RegionLevelEnum.City;
	} else {
	    reg = RegionLevelEnum.Province;
	}

	PageResult<ChannelInfoVO> result = new PageResult<ChannelInfoVO>();
	if (searchType == SupplyChannelTypeEnum.Agent) {
	    result = dao.getSearchAgent(weiID, demandID, searchStr, code, reg, limit);
	} else if (searchType == SupplyChannelTypeEnum.ground) {
	    result = dao.getSearchShop(weiID, demandID, searchStr, code, reg, limit);
	}

	if (result == null || result.getList() == null || result.getList().size() < 1) {
	    return null;
	}

	List<Long> weiIDs = new ArrayList<Long>();
	for (ChannelInfoVO infoVO : result.getList()) {
	    if (!weiIDs.contains(infoVO.getWeiId())) {
		weiIDs.add(infoVO.getWeiId());
	    }

	    if (infoVO.getChannelType() == Short.parseShort(SupplyChannelTypeEnum.Agent.toString())) {
		infoVO.setChannelName("代理商");
	    } else if (infoVO.getChannelType() == Short.parseShort(SupplyChannelTypeEnum.ground.toString())) {
		infoVO.setChannelName("落地店");
	    }
	}

	List<UWeiSeller> weiSellers = dao.getWeiSellers((Long[]) weiIDs.toArray(new Long[weiIDs.size()]));
	if (weiSellers != null && weiSellers.size() > 0) {
	    for (ChannelInfoVO infoVO : result.getList()) {
		for (UWeiSeller seller : weiSellers) {
		    if (infoVO.getWeiId().equals(seller.getWeiId())) {
			infoVO.setImage(ImgDomain.GetFullImgUrl(seller.getImages()));
		    }
		}
	    }
	}

	return result;
    }

    @Override
    public List<ChannelRegionVO> getChannelRegions(Long weiID, Integer demandID, Integer code, Integer dayNum) {
	if (weiID == null || weiID < 1) {
	    return null;
	}

	RegionLevelEnum reg;
	if (code != null && code > 0) {
	    reg = RegionLevelEnum.City;
	} else {
	    reg = RegionLevelEnum.Province;
	}

	List<ChannelRegionVO> result = new ArrayList<ChannelRegionVO>();// 返回对象
	List<Integer> codeIDs = new ArrayList<Integer>();// 去重标识
	List<Object[]> agentObjs = dao.getAgentsCountByRegion(weiID, demandID, code, reg, dayNum);
	if (agentObjs != null && agentObjs.size() > 0) {
	    for (Object[] objs : agentObjs) {
		if (objs[0] == null) {
		    break;
		}

		ChannelRegionVO cr = new ChannelRegionVO();
		cr.setCode((Integer) objs[0]);
		cr.setAgentCount(Integer.parseInt(objs[1].toString()));
		cr.setShopCount(0);
		result.add(cr);

		codeIDs.add((Integer) objs[0]);
	    }
	}
	List<Object[]> shopObjs = dao.getShopCountByRegion(weiID, demandID, code, reg, dayNum);
	if (shopObjs != null && shopObjs.size() > 0) {
	    for (Object[] objs : shopObjs) {
		if (objs[0] == null) {
		    break;
		}

		// 此区域是否已创建 不存在 添加
		if (!codeIDs.contains((Integer) objs[0])) {
		    ChannelRegionVO cr = new ChannelRegionVO();
		    cr.setCode((Integer) objs[0]);
		    cr.setShopCount(Integer.parseInt(objs[1].toString()));
		    cr.setAgentCount(0);
		    result.add(cr);

		    codeIDs.add((Integer) objs[0]);
		    continue;
		}
		// 此区域存在的话 找到该区域 添加落地店数量
		for (ChannelRegionVO regionVO : result) {
		    if (regionVO.getCode().equals((Integer) objs[0])) {
			regionVO.setShopCount(Integer.parseInt(objs[1].toString()));
			break;
		    }
		}

	    }
	}
	// 获取招商需求的当前招商区域
	List<USupplyDemandArea> demandAreas = dao.getDemandAreas(demandID, code);
	if (demandAreas != null && demandAreas.size() > 0) {
	    for (USupplyDemandArea area : demandAreas) {
		if (reg == RegionLevelEnum.City && !codeIDs.contains(area.getArea()) && area.getArea() != null) {
		    ChannelRegionVO cr = new ChannelRegionVO();
		    cr.setCode(area.getArea());
		    cr.setAgentCount(0);
		    cr.setShopCount(0);
		    result.add(cr);

		    codeIDs.add(area.getArea());
		} else if (reg == RegionLevelEnum.Province && !codeIDs.contains(area.getProvince()) && area.getProvince() != null) {
		    ChannelRegionVO cr = new ChannelRegionVO();
		    cr.setCode(area.getProvince());
		    cr.setAgentCount(0);
		    cr.setShopCount(0);
		    result.add(cr);

		    codeIDs.add(area.getProvince());
		}
	    }
	}

	// 获取区域名称
	List<TRegional> regionals = dao.getRegionals((Integer[]) codeIDs.toArray(new Integer[codeIDs.size()]));
	if (regionals == null || regionals.size() < 1) {
	    return result;
	}
	// 组装区域名称
	for (ChannelRegionVO regionVO : result) {
	    for (TRegional tRegional : regionals) {
		if (regionVO.getCode().equals(tRegional.getCode())) {
		    regionVO.setCodeName(tRegional.getName());
		    break;
		}
	    }
	}

	return result;
    }

    @Override
    public PageResult<ChannelRegionVO> getNoChannelRegions(Long weiID, Integer demandID, Limit limit) {
	if (weiID == null || weiID < 1) {
	    return null;
	}

	return dao.getNoChannelRegions(weiID, demandID, limit);
    }

    // ////////////////////////////////////私有区域///////////////////////////////////////////////////

    /**
     * 获取 招商需求 区域条件的区域集合 并转换VO 中文名称
     * 
     * @param requiredId
     * @return
     */
    private List<ChannelRegionVO> getRequiredRegionVos(int requiredId) {
	if (requiredId < 1) {
	    return null;
	}

	return getRegionName(dao.getRequiredAddress(requiredId));
    }

    /**
     * 获取招商需求的 区域集合 并转换VO 中文区域名称
     * 
     * @param demandID
     * @return
     */
    private List<ChannelRegionVO> getDemandRegionVos(int demandID) {
	if (demandID < 1) {
	    return null;
	}

	return getRegionName(dao.getDemandAreas(demandID));
    }

    private List<ChannelRegionVO> getRegionName(List<USupplyDemandArea> areaList) {

	List<ChannelRegionVO> result = new ArrayList<ChannelRegionVO>();
	// 转换VO 提取codeIDs
	if (areaList != null && areaList.size() > 0) {
	    List<Integer> codes = new ArrayList<Integer>();// code 集合
	    for (USupplyDemandArea area : areaList) {
		codes.add(area.getCode());

		ChannelRegionVO regVo = new ChannelRegionVO();
		regVo.setArea(area.getArea());
		regVo.setCity(area.getCity());
		regVo.setProvice(area.getProvince());
		regVo.setCode(area.getCode());
		regVo.setEquiredID(area.getRequiredId());
		regVo.setDemandID(area.getDemandId());
		result.add(regVo);
	    }
	    // 组装中文名称
	    List<TRegional> regList = dao.getRegionals((Integer[]) codes.toArray(new Integer[codes.size()]));
	    if (regList != null && regList.size() > 0) {
		for (RegionVO regVo : result) {
		    for (TRegional reg : regList) {
			if (regVo.getCode().equals(reg.getCode())) {
			    regVo.setCodeName(reg.getName());
			    break;
			}
		    }
		}
	    }
	}

	return result;
    }

    /**
     * 获取招商地区条件列表
     * 
     * @param demandID
     * @return
     */
    private List<DemandRequiredVO> getDemandRequiredVOs(Integer demandID) {
	// 组装区域要求 量化要求 代理区域
	List<UDemandRequired> requireds = dao.getDemandRequireds(demandID);
	if (requireds != null && requireds.size() > 0) {
	    List<URequiredKv> requiredKvs = dao.getDemandKvs(demandID); // 量化要求

	    // 转换VO
	    List<DemandRequiredVO> requiredVOs = new ArrayList<DemandRequiredVO>();
	    for (UDemandRequired uDemandRequired : requireds) {

		DemandRequiredVO requiredVO = new DemandRequiredVO();
		requiredVO.setAgentRequired(uDemandRequired.getAgentRequired());
		requiredVO.setAgentReward(uDemandRequired.getAgentReward());
		requiredVO.setContractPath(ImgDomain.GetFullImgUrl(uDemandRequired.getContractPath()));
		requiredVO.setContract(uDemandRequired.getContract());
		requiredVO.setCreateTime(uDemandRequired.getCreateTime());
		requiredVO.setDemandId(uDemandRequired.getDemandId());
		// requiredVO.setNumRequired(uDemandRequired.getNumRequired());
		// requiredVO.setOrderAmout(orderAmout);
		requiredVO.setRequiredId(uDemandRequired.getRequiredId());
		requiredVO.setSupport(uDemandRequired.getSupport());
		// 代理区域
		requiredVO.setRegionVOs(getRequiredRegionVos(uDemandRequired.getRequiredId()));

		// 量化要求
		if (requiredKvs != null && requiredKvs.size() > 0) {
		    List<RequiredKVVO> kvvos = new ArrayList<RequiredKVVO>();
		    for (URequiredKv kv : requiredKvs) {
			if (kv.getRequiredId().equals(uDemandRequired.getRequiredId())) {
			    RequiredKVVO kvvo = new RequiredKVVO();
			    kvvo.setCreateTime(kv.getCreateTime());
			    kvvo.setDemandId(kv.getDemandId());
			    kvvo.setRequiredId(kv.getRequiredId());
			    kvvo.setRkey(kv.getRkey());
			    kvvo.setRvalue(kv.getRvalue());
			    kvvos.add(kvvo);
			}
		    }
		    // 量化要求组装到 需求地区条件
		    requiredVO.setRequiredKVVOs(kvvos);
		}
		// 地区条件集合
		requiredVOs.add(requiredVO);
	    }
	    return requiredVOs;
	}

	return null;
    }

    /**
     * 获取供应商的 行业列表 字符串
     * 
     * @param weiID
     * @param categoryType
     * @return
     */
    private String getCategoryString(Long weiID, Short categoryType) {
	// 行业
	List<USupplierIndustryCategory> categoryList = dao.getCategorys(weiID, categoryType);
	if (categoryList != null && categoryList.size() > 0) {
	    List<Integer> categoryIDs = new ArrayList<Integer>();
	    for (USupplierIndustryCategory item : categoryList) {
		categoryIDs.add(item.getCategoryId());
	    }
	    // 查询行业表
	    List<TBussinessClass> classes = dao.getBussinessClassList((Integer[]) categoryIDs.toArray(new Integer[categoryIDs.size()]));
	    if (classes != null && classes.size() > 0) {
		String busCategoryStr = "";
		for (TBussinessClass item : classes) {
		    busCategoryStr += item.getName() + " ";
		}
		return busCategoryStr;
	    }
	}

	return null;
    }

    @Override
    public PageResult<DemandProductVO> getNoDemandProducts(Long weiID, String title, Integer calssType, Limit limit) {
	if (weiID == null || weiID < 1) {
	    return null;
	}
	List<Integer> types = new ArrayList<Integer>();
	if (calssType != null && calssType > 0) {
	    types.add(calssType);
	    List<PShopClass> classList = dao.getShopClasses(weiID, null, calssType);
	    if (classList != null && classList.size() > 0) {
		for (PShopClass item : classList) {
		    types.add(item.getSid());
		}
	    }
	}

	PageResult<DemandProductVO> result = dao.getNoDemandProducts(weiID, title, (Integer[]) types.toArray(new Integer[types.size()]), limit);
	// 数据整理
	List<Long> productIDs = new ArrayList<Long>();
	List<Integer> sids = new ArrayList<Integer>();
	if (result != null && result.getList() != null && result.getList().size() > 0) {
	    for (DemandProductVO product : result.getList()) {
		product.setProductImg(ImgDomain.GetFullImgUrl(product.getProductImg()));
		productIDs.add(product.getProductID());
		if (!sids.contains(product.getSid())) {
		    sids.add(product.getSid());
		}
	    }
	}
	// 获取商品的 落地价 代理价
	List<PProductRelation> relationList = dao.getProductRelations((Long[]) productIDs.toArray(new Long[productIDs.size()]));
	if (relationList != null && relationList.size() > 0) {
	    for (DemandProductVO product : result.getList()) {
		for (PProductRelation relation : relationList) {
		    if (product.getProductID().equals(relation.getProductId())) {
			product.setAgentPrice(relation.getProxyPrice());
			product.setShopPrice(relation.getFloorPrice());
			break;
		    }
		}
	    }
	}

	List<PShopClass> shopClasses = dao.getShopClasses((Integer[]) sids.toArray(new Integer[sids.size()]));
	if (shopClasses != null && shopClasses.size() > 0) {
	    for (DemandProductVO product : result.getList()) {
		for (PShopClass shopClass : shopClasses) {
		    if (product.getSid().equals(shopClass.getSid())) {
			product.setShopClassName(shopClass.getSname());
			break;
		    }
		}
	    }
	}

	return result;
    }

    @Override
    public List<ShopClassVO> getShopClassVOs(Long weiID, Short level, Integer parentID) {

	List<PShopClass> classList = dao.getShopClasses(weiID, level, parentID);
	if (classList == null || classList.size() < 1) {
	    return null;
	}

	List<ShopClassVO> result = new ArrayList<ShopClassVO>();
	for (PShopClass sClass : classList) {
	    ShopClassVO classVO = new ShopClassVO();
	    classVO.setClassId(sClass.getSid());
	    classVO.setClassName(sClass.getSname());
	    classVO.setLevel(sClass.getLevel());
	    classVO.setParetId(sClass.getParetId());
	    classVO.setWeiid(sClass.getWeiid());

	    result.add(classVO);
	}

	return result;
    }

    @Override
    public Map<DemandStateEnum, Long> getMyDemandStateCount(Long weiID) {
	if (weiID == null || weiID < 1) {
	    return null;
	}
	Map<DemandStateEnum, Long> result = new HashMap<DemandStateEnum, Long>();
	List<Object[]> objs = dao.getMyDemandStateCount(weiID);
	for (DemandStateEnum state : DemandStateEnum.values()) {
	    result.put(state, (long) 0);
	    if (objs != null && objs.size() > 0) {
		for (Object[] obj : objs) {
		    if (Short.parseShort(state.toString()) == Short.parseShort(obj[0].toString())) {
			result.put(state, Long.parseLong(obj[1].toString()));
			break;
		    }
		}
	    }
	}

	return result;
    }

    @Override
    public ChannelSupplyVO getChannelSupply(Long weiID) {

	if (weiID == null || weiID < 1) {
	    return null;
	}
	// 1.判断身份
	// 2.分别取数据

	return null;
    }

    @Override
    public List<TRegional> getRegionals(Short lever, Integer parent) {
	if (lever == null && parent == null) {
	    return null;
	}

	return dao.getRegionals(lever, parent);
    }

}
