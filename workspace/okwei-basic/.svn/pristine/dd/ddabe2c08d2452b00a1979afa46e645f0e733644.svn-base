package com.okwei.service.impl.product;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sun.util.logging.resources.logging;

import com.okwei.bean.domain.PPriceShow;
import com.okwei.bean.domain.PProductRelation;
import com.okwei.bean.domain.PProducts;
import com.okwei.bean.domain.THouse;
import com.okwei.bean.domain.THouseProduct;
import com.okwei.bean.domain.UDemandProduct;
import com.okwei.bean.domain.UShopInfo;
import com.okwei.bean.domain.USupplyChannel;
import com.okwei.bean.domain.USupplyDemand;
import com.okwei.bean.domain.USupplyer;
import com.okwei.bean.enums.AgentStatusEnum;
import com.okwei.bean.enums.ProductStatusEnum;
import com.okwei.bean.enums.SupplyChannelTypeEnum;
import com.okwei.bean.vo.LoginUser;
import com.okwei.bean.vo.PageInfo;
import com.okwei.bean.vo.product.HouseClass;
import com.okwei.bean.vo.product.HouseInfo;
import com.okwei.bean.vo.product.InvestmentProducts;
import com.okwei.bean.vo.product.ProductInfo;
import com.okwei.bean.vo.product.TempProduct;
import com.okwei.common.CommonMethod;
import com.okwei.common.Limit;
import com.okwei.common.PageResult;
import com.okwei.dao.IBaseDAO;
import com.okwei.service.product.IHouseService;
import com.okwei.util.ImgDomain;
import com.okwei.util.RedisUtil;
import com.okwei.util.TransferManager;

@Service
public class HouseService implements IHouseService {

    @Autowired
    private IBaseDAO baseDAO;

    @Override
    public List<HouseInfo> getHouseInfo(int index, int size) {
	List<HouseInfo> result = new ArrayList<HouseInfo>();
	String hql = "from THouse where state=1 and level=1 order by sort asc";
	List<THouse> list = baseDAO.findPage(hql, index, size);
	if (list != null && list.size() > 0) {
	    for (THouse house : list) {
		HouseInfo info = new HouseInfo();
		info.setTopicID(house.getHouseId());
		info.setTopicName(house.getName());
		info.setTopicImage(ImgDomain.GetFullImgUrl(house.getImage(), 24));
		info.setSort(house.getSort());
		info.setTopicURL("跳转页面url");
		result.add(info);
	    }
	    return result;
	}
	return null;
    }

    @Override
    public PageResult<ProductInfo> getHouseProducts(LoginUser user, Limit limit, int classId, int classLevel, int orderby) {
	// 馆的产品分类
	// 现在馆暂时定三级,第一级为馆，后两级为分类。
	List<Integer> houseIDs = new ArrayList<Integer>();
	if (classLevel == 1) {
	    String sql = "SELECT HouseID FROM T_House WHERE State=1 AND ParentID IN(SELECT HouseID FROM T_House WHERE ParentID=? AND Level=2 AND State=1)";
	    List<Object> list = baseDAO.queryBySql(sql, classId);
	    if (list != null && list.size() > 0) {
		for (Object obj : list) {
		    houseIDs.add((Integer) obj);
		}
	    }
	} else if (classLevel == 2) {
	    List<THouse> list = baseDAO.find("from THouse where state=1 and parentId=? and level=3", classId);
	    if (list != null && list.size() > 0) {
		for (THouse house : list) {
		    houseIDs.add(house.getHouseId());
		}
	    }
	} else if (classLevel == 3) {
	    houseIDs.add(classId);
	} else {
	    return null;
	}
	if (houseIDs.size() <= 0)
	    return null;
	Map<String, Object> params = new HashMap<String, Object>();
	params.put("houseid", (Integer[]) houseIDs.toArray(new Integer[houseIDs.size()]));
	List<THouseProduct> houselist = baseDAO.findByMap("from THouseProduct where houseId in(:houseid) and state=1", params);
	if (houselist != null && houselist.size() > 0) {
	    List<Long> proids = new ArrayList<Long>();
	    for (THouseProduct product : houselist) {
		proids.add(product.getProductId());
	    }
	    // 查询正式产品表
	    String hql = "from PProducts where productId in(:proids) and state=:state ";
	    switch (orderby) {
		case 1:
		    hql += " order by updateTime desc";
		    break;
		case 2:
		    hql += " order by updateTime asc";
		    break;
		case 3:
		    hql += " order by defaultPrice asc";
		    break;
		case 4:
		    hql += " order by defaultPrice desc";
		    break;
	    }
	    Map<String, Object> paramPro = new HashMap<String, Object>();
	    paramPro.put("proids", (Long[]) proids.toArray(new Long[proids.size()]));
	    paramPro.put("state", Short.parseShort(ProductStatusEnum.Showing.toString()));
	    PageResult<PProducts> page = baseDAO.findPageResultByMap(hql, limit, paramPro);
	    if (page != null) {
		List<PProducts> proList = page.getList();
		if (proList != null && proList.size() > 0) {
		    // 查询店铺名称
		    List<Long> supids = new ArrayList<Long>();
		    if (proList != null && proList.size() > 0) {
			for (PProducts pro : proList) {
			    supids.add(pro.getSupplierWeiId());
			}
		    }
		    Map<String, Object> paramSup = new HashMap<String, Object>();
		    paramSup.put("weiids", (Long[]) supids.toArray(new Long[supids.size()]));

		    // 查询落地价 代理价显示
		    List<PPriceShow> visibleList = baseDAO.findByMap("from PPriceShow where weiId in(:weiids)", paramSup);
		    // 查询供应商的公司名称
		    List<USupplyer> supList = baseDAO.findByMap("from USupplyer where weiId in(:weiids)", paramSup);
		    boolean supflag = false;
		    if (supList != null && supList.size() > 0)
			supflag = true;

		    // 查询产品辅助表
		    Map<String, Object> paramRel = new HashMap<String, Object>();
		    paramRel.put("proids", (Long[]) proids.toArray(new Long[proids.size()]));
		    List<PProductRelation> relList = baseDAO.findByMap("from PProductRelation where productId in(:proids)", paramRel);
		    boolean relflag = false;
		    if (relList != null && relList.size() > 0)
			relflag = true;
		    // 查询登陆人是否是该供应商的代理商 落地店
		    List<UDemandProduct> demandList = null;
		    List<USupplyChannel> chanList = null;
		    List<USupplyDemand> demList = null;
		    if (user.getWeiID() != null && user.getWeiID().longValue() > 0) {
			demandList = baseDAO.findByMap("from UDemandProduct where productId in(:proids)", paramRel);
			if (demandList != null && demandList.size() > 0) {
			    Integer[] demandids = new Integer[demandList.size()];
			    for (int i = 0; i < demandList.size(); i++) {
				demandids[i] = demandList.get(i).getDemandId();
			    }
			    Map<String, Object> paramChan = new HashMap<String, Object>();
			    paramChan.put("weiid", user.getWeiID());
			    paramChan.put("demandids", demandids);
			    paramChan.put("state", Short.parseShort(AgentStatusEnum.Normal.toString()));
			    chanList = baseDAO.findByMap("from USupplyChannel where weiId=:weiid and demandId in(:demandids) and state=:state", paramChan);
			    Map<String, Object> paramDem = new HashMap<String, Object>();
			    paramDem.put("demandids", demandids);
			    demList = baseDAO.findByMap("from USupplyDemand where demandId in(:demandids)", paramDem);
			}
		    }
		    boolean demandflag = false;
		    if (demandList != null && demandList.size() > 0)
			demandflag = true;
		    boolean chanflag = false;
		    if (chanList != null && chanList.size() > 0)
			chanflag = true;
		    boolean demflag = false;
		    if (demList != null && demList.size() > 0)
			demflag = true;

		    List<ProductInfo> result = new ArrayList<ProductInfo>();
		    for (PProducts pro : proList) {
			ProductInfo temp = new ProductInfo();
			temp.setProductId(pro.getProductId());
			temp.setProductName(pro.getProductTitle());
			temp.setProductPicture(ImgDomain.GetFullImgUrl(pro.getDefaultImg(), 24));
			temp.setRetailPrice(pro.getDefaultPrice());
			temp.setSupWeiID(pro.getSupplierWeiId());
			//分享 增加原价 
			temp.setDisplayPrice(CommonMethod.getInstance().getDisplayPrice(pro.getDefaultPrice(), pro.getOriginalPrice(), pro.getPercent()));
			if (supflag) {
			    for (USupplyer supplyer : supList) {
				if (pro.getSupplierWeiId().equals(supplyer.getWeiId())) {
				    temp.setCompanyName(supplyer.getCompanyName());
				    break;
				}
			    }
			}
			// 最低代理价 落地价
			if (relflag) {
			    for (PProductRelation rel : relList) {
				if (pro.getProductId().equals(rel.getProductId())) {
				    temp.setStorePrice(rel.getMinFloorPrice());
				    temp.setAgentPrice(rel.getMinProxyPrice());
				    break;
				}
			    }
			}
			if (demandflag) {
			    for (UDemandProduct demand : demandList) {
				if (demand.getProductId().equals(pro.getProductId())) {
				    temp.setDemandId(demand.getDemandId());
				    if (chanflag) {
					for (USupplyChannel chan : chanList) {
					    if (demand.getDemandId().equals(chan.getDemandId())) {
						if (chan.getChannelType().shortValue() == Short.parseShort(SupplyChannelTypeEnum.Agent.toString())) {
						    temp.setCurrentUserIsAgent(1);
						}
						if (chan.getChannelType().shortValue() == Short.parseShort(SupplyChannelTypeEnum.ground.toString())) {
						    temp.setCurrentUserIsStore(1);
						}
						break;
					    }
					}
				    }
				    if (demflag) {
					for (USupplyDemand dem : demList) {
					    if (demand.getDemandId().equals(dem.getDemandId())) {
						temp.setOrderAmount(dem.getOrderAmout());
						break;
					    }
					}
				    }
				    break;
				}
			    }
			}
			PPriceShow visible = null;
			for (PPriceShow vis : visibleList) {
			    if (vis.getWeiId().equals(pro.getSupplierWeiId())) {
				visible = vis;
				break;
			    }
			}
			temp = getPriceShow(user, visible, temp);
			result.add(temp);
		    }
		    return new PageResult<ProductInfo>(page.getTotalCount(), limit, result);
		}
	    }
	}
	return null;
    }

    @Override
    public PageInfo getPageInfo(LoginUser user, Limit limit, int classId, int classLevel) {
	PageResult<ProductInfo> page = getHouseProducts(user, limit, classId, classLevel, 0);
	if (page != null) {
	    PageInfo result = new PageInfo();
	    result.setPageIndex(limit.getPageId());
	    result.setPageSize(limit.getSize());
	    result.setTotalCount(page.getTotalCount());
	    result.setTotalPage(page.getPageCount());
	    result.setList(page.getList());
	    return result;
	}
	return null;
    }

    @Override
    public InvestmentProducts getInvesProducts(LoginUser user, Limit limit, int demandId, Long productId) {
	if (productId != null) {
	    UDemandProduct up = baseDAO.getUniqueResultByHql("from UDemandProduct where productId=?", new Object[] { productId });
	    if (up != null) {
		demandId = up.getDemandId();
	    }
	}
	// 获取招商条件
	USupplyDemand demand = baseDAO.get(USupplyDemand.class, demandId);
	if (demand != null) {
	    if (user == null) {
		user = new LoginUser();
		user.setWeiID(0l);
	    }
	    InvestmentProducts result = new InvestmentProducts();
	    result.setMerchantWeiId(demand.getWeiId());
	    result.setMerchantName(getSupName(demand.getWeiId()));
	    result.setInvestmentDemandId(demandId);
	    result.setInvestmentDemandName(demand.getTitle());
	    result.setStoreBuyAmount(demand.getOrderAmout());

	    ProductInfo info = new ProductInfo();
	    // 判断是不是该招商需求的代理商 落地店
	    if (user != null && user.getWeiID().longValue() > 0) {
		List<USupplyChannel> chanList = baseDAO.find("from USupplyChannel where demandId=? and weiId=? and state=?", new Object[] { demandId, user.getWeiID(), Short.parseShort(AgentStatusEnum.Normal.toString()) });
		if (chanList != null && chanList.size() > 0) {
		    for (USupplyChannel chan : chanList) {
			if (chan.getChannelType().shortValue() == Short.parseShort(SupplyChannelTypeEnum.Agent.toString())) {
			    info.setCurrentUserIsAgent(1);
			}
			if (chan.getChannelType().shortValue() == Short.parseShort(SupplyChannelTypeEnum.ground.toString())) {
			    info.setCurrentUserIsStore(1);
			}
		    }
		}
	    }
	    // 判断该用户是否显示 落地价 代理价
	    PPriceShow visible = baseDAO.get(PPriceShow.class, demand.getWeiId());
	    info = getPriceShow(user, visible, info);

	    // 获取招商系列产品
	    List<UDemandProduct> demList = baseDAO.find("from UDemandProduct where demandId=?", demandId);
	    if (demList != null && demList.size() > 0) {
		Long[] allproids = new Long[demList.size()];
		for (int i = 0; i < demList.size(); i++) {
		    allproids[i] = demList.get(i).getProductId();
		}
		// 查询产品
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("proids", allproids);
		params.put("state", Short.parseShort(ProductStatusEnum.Showing.toString()));
		PageResult<PProducts> page = baseDAO.findPageResultByMap("from PProducts where productId in(:proids) and state=:state", limit, params);
		if (page != null) {
		    List<PProducts> proList = page.getList();
		    if (proList != null && proList.size() > 0) {
			// 分页相关信息
			result.setPageIndex(limit.getPageId());
			result.setPageSize(limit.getSize());
			result.setTotalCount(page.getTotalCount());
			result.setTotalPage(page.getPageCount());

			List<Long> proids = new ArrayList<Long>();
			for (PProducts pro : proList) {
			    proids.add(pro.getProductId());
			}

			// 查询产品辅助表
			Map<String, Object> paramRel = new HashMap<String, Object>();
			paramRel.put("proids", (Long[]) proids.toArray(new Long[proids.size()]));
			List<PProductRelation> relList = baseDAO.findByMap("from PProductRelation where productId in(:proids)", paramRel);

			List<ProductInfo> resultList = new ArrayList<ProductInfo>();
			for (PProducts pro : proList) {
			    ProductInfo temp = new ProductInfo();
			    temp.setProductId(pro.getProductId());
			    temp.setProductName(pro.getProductTitle());
			    temp.setProductPicture(ImgDomain.GetFullImgUrl(pro.getDefaultImg(), 24));
			    temp.setRetailPrice(pro.getDefaultPrice());
			    PProductRelation relation = null;
			    if (relList != null && relList.size() > 0) {
				for (PProductRelation rel : relList) {
				    if (rel.getProductId().equals(pro.getProductId())) {
					relation = rel;
					break;
				    }
				}
			    }
			    if (relation != null) {
				temp.setStorePrice(relation.getMinFloorPrice());
				temp.setAgentPrice(relation.getMinProxyPrice());
			    }
			    temp.setDlPriceVisibility(info.getDlPriceVisibility());
			    temp.setLdPriceVisibility(info.getLdPriceVisibility());
			    temp.setCurrentUserIsAgent(info.getCurrentUserIsAgent());
			    temp.setCurrentUserIsStore(info.getCurrentUserIsStore());
			    temp.setDemandId(demandId);
			    temp.setSupWeiID(pro.getSupplierWeiId());
			    //分享 增加原价 add by 阿甘  2016.1.26
			    temp.setDisplayPrice(CommonMethod.getInstance().getDisplayPrice(pro.getDefaultPrice(), pro.getOriginalPrice(), pro.getPercent()));
			    resultList.add(temp);
			}
			result.setList(resultList);
		    }
		}
	    }
	    return result;
	}
	return null;
    }

    /**
     * 获取供应商名称
     * 
     * @param weiid
     * @return
     */
    private String getSupName(Long weiid) {
	UShopInfo shopInfo = baseDAO.get(UShopInfo.class, weiid);
	if (shopInfo != null)
	    return shopInfo.getShopName();
	USupplyer supplyer = baseDAO.get(USupplyer.class, weiid);
	if (supplyer != null)
	    return supplyer.getCompanyName();
	return null;
    }

    @Override
    public List<HouseClass> getHouseClass(int parentId) {
	List<THouse> list = baseDAO.find("from THouse where parentId=? and state=1", parentId);
	if (list != null && list.size() > 0) {
	    List<HouseClass> result = new ArrayList<HouseClass>();
	    for (THouse house : list) {
		HouseClass temp = new HouseClass();
		temp.setCategoryId(house.getHouseId());
		temp.setCategoryName(house.getName());
		temp.setCategoryPicture(ImgDomain.GetFullImgUrl(house.getImage()));
		temp.setSort(house.getSort());
		result.add(temp);
	    }
	    return result;
	}
	return null;
    }

    @Override
    public PageInfo getSearchYunPurchasesProducts(LoginUser user, Map<String, String> params) {
	PageResult<ProductInfo> page = getLdYunProducts(user, params);
	if (page != null) {
	    PageInfo result = new PageInfo();
	    result.setPageIndex(page.getPageIndex());
	    result.setPageSize(page.getPageSize());
	    result.setTotalCount(page.getTotalCount());
	    result.setTotalPage(page.getPageCount());
	    result.setList(page.getList());
	    return result;
	}
	return null;
    }

    @Override
    public ProductInfo getPriceShow(LoginUser user, PPriceShow show, ProductInfo info) {
	if (show == null)
	    return info;
	if (user == null) {
	    user = new LoginUser();
	    user.setWeiID(0l);
	}
	if (user.getWeiID().equals(show.getWeiId())) {
	    info.setDlPriceVisibility(1);
	    info.setLdPriceVisibility(1);
	} else {
	    if (show != null) {
		// 判断代理价是否显示
		if (show.getDlFullyOpen().intValue() == 1) {
		    info.setDlPriceVisibility(1);
		} else {
		    // 其他厂家的代理商可见
		    if (show.getOtherAgentsVisible().intValue() == 1) {
			if (info.getCurrentUserIsAgent() != 1 && user.getPthdls() != null && user.getPthdls().intValue() == 1) {
			    info.setDlPriceVisibility(1);
			}
		    }
		    // 我的代理商可见
		    if (info.getDlPriceVisibility() != 1 && show.getAgentsVisible().intValue() == 1) {
			if (info.getCurrentUserIsAgent() == 1) {
			    info.setDlPriceVisibility(1);
			}
		    }
		}
		// 判断落地价是否显示
		if (show.getLdFullyOpen().intValue() == 1) {
		    info.setLdPriceVisibility(1);
		} else {
		    // 我的落地店可见
		    if (show.getShopVisible().intValue() == 1) {
			if (info.getCurrentUserIsStore() == 1) {
			    info.setLdPriceVisibility(1);
			}
		    }
		    // 我的代理商可见
		    if (info.getLdPriceVisibility() != 1 && show.getLdAgentsVisible().intValue() == 1) {
			if (user.getPthdls() != null && user.getPthdls().intValue() == 1) {
			    info.setLdPriceVisibility(1);
			}
		    }
		    // 其他厂家的落地店可见
		    if (info.getLdPriceVisibility() != 1 && show.getOtherShopVisible().intValue() == 1) {
			if (info.getCurrentUserIsStore() != 1 && user.getPthldd() != null && user.getPthldd().intValue() == 1) {
			    info.setLdPriceVisibility(1);
			}
		    }
		}
	    }
	}
	return info;
    }

    @Override
    public boolean checkUserIsStore(Long shopWeiId, Long loginWeiId) throws Exception {
	boolean result = false;
	String hql = "select count(*) from UProductShop where supplyId=? and weiId=?";
	long count = baseDAO.count(hql, shopWeiId, loginWeiId);
	if (count > 0) {
	    result = true;
	}
	return result;
    }

    @Override
    public boolean checkUserIsAgent(Long shopWeiId, Long loginWeiId) throws Exception {
	boolean result = false;
	String hql = "select count(*) from UProductAgent where supplyId=? and weiId=?";
	long count = baseDAO.count(hql, shopWeiId, loginWeiId);
	if (count > 0) {
	    result = true;
	}
	return result;
    }

    @Override
    public boolean checkUserIsAgentOrStore(Long shopWeiId, Long loginWeiId, Integer demandId, Short channelType, Short state) throws Exception {
	boolean result = false;
	String hql = "select count(*) from USupplyChannel where supplyId=? and weiId=? and demandId=? and channelType=? and state=? ";
	long count = baseDAO.count(hql, shopWeiId, loginWeiId, demandId, channelType, state);
	if (count > 0) {
	    result = true;
	}
	return result;
    }

    @Override
    public PageInfo getPurchaseList(LoginUser user, int pageIndex, int pageSize, int orderBy) {
	List<USupplyChannel> chanList = baseDAO.find("from USupplyChannel where weiId=? and state=?", new Object[] { user.getWeiID(), Short.parseShort(AgentStatusEnum.Normal.toString()) });
	if (chanList != null && chanList.size() > 0) {
	    Integer[] demandids = new Integer[chanList.size()];
	    for (int i = 0; i < chanList.size(); i++) {
		demandids[i] = chanList.get(i).getDemandId();
	    }
	    // 查询招商需求的产品
	    Map<String, Object> params = new HashMap<String, Object>();
	    params.put("demandids", demandids);
	    List<UDemandProduct> proList = baseDAO.findByMap("from UDemandProduct where demandId in(:demandids)", params);
	    if (proList != null && proList.size() > 0) {
		Long[] proids = new Long[proList.size()];
		for (int i = 0; i < proList.size(); i++) {
		    proids[i] = proList.get(i).getProductId();
		}
		Map<String, Object> pa = new HashMap<String, Object>();
		pa.put("proids", proids);
		pa.put("weiid", user.getWeiID());
		String sql = "SELECT a.*,IFNULL(b.stockCount,0) stockCount,IFNULL(b.selledCount,0) selledCount FROM (SELECT ProductID,ProductTitle,DefaultImg,DefaultPrice,SupplierWeiID FROM P_Products WHERE ProductID in(:proids) and State=1 ) a LEFT JOIN  (SELECT * FROM P_AgentStock WHERE weiId=:weiid) b ON a.ProductID=b.productId ORDER BY ";
		switch (orderBy) {
		    case 0:
			sql += " b.selledCount DESC";
			break;
		    case 1:
			sql += " b.selledCount ASC";
			break;
		    case 2:
			sql += " b.stockCount ASC";
			break;
		    case 3:
			sql += " b.stockCount DESC";
			break;
		    default:
			sql += " b.selledCount DESC";
			break;
		}
		PageResult<TempProduct> page = baseDAO.queryPageResultByMap(sql, TempProduct.class, Limit.buildLimit(pageIndex, pageSize), pa);
		if (page != null) {
		    PageInfo result = new PageInfo();
		    result.setPageIndex(pageIndex);
		    result.setPageSize(pageSize);
		    result.setTotalCount(page.getTotalCount());
		    result.setTotalPage(page.getPageCount());

		    List<TempProduct> list = page.getList();
		    if (list != null && list.size() > 0) {
			// 查询店铺名称
			List<Long> supids = new ArrayList<Long>();
			List<Long> pids = new ArrayList<Long>();
			for (TempProduct temp : list) {
			    supids.add(temp.getSupplierWeiID().longValue());
			    pids.add(temp.getProductID().longValue());
			}

			Map<String, Object> paramSup = new HashMap<String, Object>();
			paramSup.put("weiids", (Long[]) supids.toArray(new Long[supids.size()]));
			// 查询落地价 代理价显示
			List<PPriceShow> visibleList = baseDAO.findByMap("from PPriceShow where weiId in(:weiids)", paramSup);
			// 查询产品辅助表
			Map<String, Object> paramRel = new HashMap<String, Object>();
			paramRel.put("pids", (Long[]) pids.toArray(new Long[pids.size()]));
			List<PProductRelation> relList = baseDAO.findByMap("from PProductRelation where productId in(:pids)", paramRel);
			boolean relflag = false;
			if (relList != null && relList.size() > 0)
			    relflag = true;
			boolean demandflag = false;
			if (proList != null && proList.size() > 0)
			    demandflag = true;
			boolean chanflag = false;
			if (chanList != null && chanList.size() > 0)
			    chanflag = true;

			List<ProductInfo> tempList = new ArrayList<ProductInfo>();
			for (TempProduct pro : list) {
			    ProductInfo temp = new ProductInfo();
			    temp.setProductId(pro.getProductID().longValue());
			    temp.setProductName(pro.getProductTitle());
			    temp.setProductPicture(ImgDomain.GetFullImgUrl(pro.getDefaultImg(), 24));
			    temp.setRetailPrice(pro.getDefaultPrice().doubleValue());
			    temp.setStockCount(pro.getStockCount().intValue());
			    temp.setSaleCount(pro.getSelledCount().intValue());
			    // 最低代理价 落地价
			    if (relflag) {
				for (PProductRelation rel : relList) {
				    if (pro.getProductID().longValue() == rel.getProductId().longValue()) {
					temp.setStorePrice(rel.getMinFloorPrice());
					temp.setAgentPrice(rel.getMinProxyPrice());
					break;
				    }
				}
			    }
			    // 代理商 落地店进货去 默认是代理商 落地店
			    if (demandflag) {
				for (UDemandProduct demand : proList) {
				    if (demand.getProductId().longValue() == pro.getProductID().longValue()) {
					temp.setDemandId(demand.getDemandId());
					if (chanflag) {
					    for (USupplyChannel chan : chanList) {
						if (demand.getDemandId().equals(chan.getDemandId())) {
						    if (chan.getChannelType().shortValue() == Short.parseShort(SupplyChannelTypeEnum.Agent.toString())) {
							temp.setCurrentUserIsAgent(1);
						    }
						    if (chan.getChannelType().shortValue() == Short.parseShort(SupplyChannelTypeEnum.ground.toString())) {
							temp.setCurrentUserIsStore(1);
						    }
						    break;
						}
					    }
					}
					break;
				    }
				}
			    }
			    PPriceShow visible = null;
			    for (PPriceShow vis : visibleList) {
				if (vis.getWeiId().longValue() == pro.getSupplierWeiID().longValue()) {
				    visible = vis;
				    break;
				}
			    }
			    // 判断是不是显示代理价落地价
			    temp = getPriceShow(user, visible, temp);
			    tempList.add(temp);
			}
			result.setList(tempList);
			return result;
		    }
		}
	    }
	}
	return null;
    }

    @Override
    public PageResult<ProductInfo> getLdYunProducts(LoginUser user, Map<String, String> params) {
	String keyName = "LdYunProducts" + params.toString();
	PageResult<ProductInfo> result = null;
	try {
	    result = (PageResult<ProductInfo>) RedisUtil.getObject(keyName);
	} catch (Exception e) {

	}
	boolean demandflag = false;
	boolean chanflag = false;
	List<UDemandProduct> demandList = null;
	List<USupplyChannel> chanList = null;
	List<USupplyDemand> demList = null;
	boolean demflag = false;
	List<PPriceShow> visibleList = null;
	if (result == null) {
	    String content = TransferManager.SearchProduct(params);
	    if (content != null && content != "") {
		JSONObject jo = new JSONObject().fromObject(content);
		int pageIndex = jo.getInt("pageIndex");
		int pageSize = jo.getInt("pageSize");
		int totalPage = jo.getInt("totalPage");
		int totalCount = jo.getInt("totalCount");
		JSONArray ja = new JSONArray().fromObject(jo.getString("list"));
		if (ja != null && ja.size() > 0) {
		    Long[] proids = new Long[ja.size()];
		    Long[] supids = new Long[ja.size()];
		    for (int i = 0; i < ja.size(); i++) {
			JSONObject jsonObj = ja.getJSONObject(i);
			proids[i] = jsonObj.getLong("productId"); // 产品id
			supids[i] = jsonObj.getLong("supplierWeiId");// 供应商id
		    }
		    // 查询产品
		    Map<String, Object> paMap = new HashMap<String, Object>();
		    paMap.put("proids", proids);
		    List<PProducts> proList = baseDAO.findByMap("from PProducts where productId in (:proids) and state=1", paMap);

		    // 查询代理价落地价
		    List<PProductRelation> relList = baseDAO.findByMap("from PProductRelation where productId in (:proids)", paMap);
		    boolean relflag = false;
		    if (relList != null && relList.size() > 0)
			relflag = true;
		    // 查询登陆人是否是该供应商的代理商 落地店

		    if (user.getWeiID() != null && user.getWeiID().longValue() > 0) {
			demandList = baseDAO.findByMap("from UDemandProduct where productId in(:proids)", paMap);
			if (demandList != null && demandList.size() > 0) {
			    Integer[] demandids = new Integer[demandList.size()];
			    for (int i = 0; i < demandList.size(); i++) {
				demandids[i] = demandList.get(i).getDemandId();
			    }
			    Map<String, Object> paramChan = new HashMap<String, Object>();
			    paramChan.put("weiid", user.getWeiID());
			    paramChan.put("demandids", demandids);
			    paramChan.put("state", Short.parseShort(AgentStatusEnum.Normal.toString()));
			    chanList = baseDAO.findByMap("from USupplyChannel where weiId=:weiid and demandId in(:demandids) and state=:state", paramChan);
			    Map<String, Object> paramDem = new HashMap<String, Object>();
			    paramDem.put("demandids", demandids);
			    demList = baseDAO.findByMap("from USupplyDemand where demandId in(:demandids)", paramDem);
			}
		    }
		    if (demandList != null && demandList.size() > 0)
			demandflag = true;
		    if (chanList != null && chanList.size() > 0)
			chanflag = true;
		    if (demList != null && demList.size() > 0)
			demflag = true;
		    // 查询落地价 代理价显示
		    Map<String, Object> paramVis = new HashMap<String, Object>();
		    paramVis.put("supids", supids);
		    visibleList = baseDAO.findByMap("from PPriceShow where weiId in(:supids)", paramVis);
		    // 查询供应商的公司名称
		    List<USupplyer> supList = baseDAO.findByMap("from USupplyer where weiId in(:supids)", paramVis);
		    boolean supflag = false;
		    if (supList != null && supList.size() > 0)
			supflag = true;
		    // 价格显示
		    List<ProductInfo> tempList = new ArrayList<ProductInfo>();
		    for (PProducts pro : proList) {
			ProductInfo temp = new ProductInfo();
			temp.setProductId(pro.getProductId());
			temp.setProductName(pro.getProductTitle());
			temp.setProductPicture(ImgDomain.GetFullImgUrl(pro.getDefaultImg(), 24));
			temp.setRetailPrice(pro.getDefaultPrice());
			temp.setSupWeiID(pro.getSupplierWeiId());
			//分享需求增加原价字段 add by 阿甘 2016.1.26
			temp.setDisplayPrice(CommonMethod.getInstance().getDisplayPrice(pro.getDefaultPrice(), pro.getOriginalPrice(), pro.getPercent()));

			if (supflag) {
			    for (USupplyer supplyer : supList) {
				if (pro.getSupplierWeiId().equals(supplyer.getWeiId())) {
				    temp.setCompanyName(supplyer.getCompanyName());
				    break;
				}
			    }
			}
			// 最低代理价 落地价
			if (relflag) {
			    for (PProductRelation rel : relList) {
				if (pro.getProductId().equals(rel.getProductId())) {
				    temp.setStorePrice(rel.getMinFloorPrice());
				    temp.setAgentPrice(rel.getMinProxyPrice());
				    break;
				}
			    }
			}

			tempList.add(temp);
		    }
		    result = new PageResult<ProductInfo>(totalCount, Limit.buildLimit(pageIndex, pageSize), tempList);
		    RedisUtil.setObject(keyName, result, 600);
		}
	    }
	}
	if (result != null && result.getList() != null && result.getList().size() > 0) {
	    for (ProductInfo temp : result.getList()) {
		if (demandflag) {
		    for (UDemandProduct demand : demandList) {
			if (demand.getProductId().equals(temp.getProductId())) {
			    temp.setDemandId(demand.getDemandId());
			    if (chanflag) {
				for (USupplyChannel chan : chanList) {
				    if (demand.getDemandId().equals(chan.getDemandId())) {
					if (chan.getChannelType().shortValue() == Short.parseShort(SupplyChannelTypeEnum.Agent.toString())) {
					    temp.setCurrentUserIsAgent(1);
					}
					if (chan.getChannelType().shortValue() == Short.parseShort(SupplyChannelTypeEnum.ground.toString())) {
					    temp.setCurrentUserIsStore(1);
					}
					break;
				    }
				}
			    }
			    if (demflag) {
				for (USupplyDemand dem : demList) {
				    if (demand.getDemandId().equals(dem.getDemandId())) {
					temp.setOrderAmount(dem.getOrderAmout());
					break;
				    }
				}
			    }
			    break;
			}
		    }
		}
		PPriceShow visible = null;
		if (visibleList != null && visibleList.size() > 0) {
		    for (PPriceShow vis : visibleList) {
			if (vis.getWeiId().equals(temp.getSupWeiID())) {
			    visible = vis;
			    break;
			}
		    }
		}
		// 判断是不是显示代理价落地价
		temp = getPriceShow(user, visible, temp);
	    }
	}
	return result;
    }
}
