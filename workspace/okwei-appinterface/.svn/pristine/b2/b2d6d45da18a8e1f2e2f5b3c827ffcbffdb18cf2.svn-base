package com.okwei.appinterface.web;

import com.okwei.appinterface.bean.vo.ListModel;
import com.okwei.appinterface.bean.vo.brandAgent.AgentShop;
import com.okwei.appinterface.bean.vo.brandAgent.MyBrandShop;
import com.okwei.appinterface.bean.vo.brandAgent.BrandShopEx;
import com.okwei.appinterface.enums.UserIdentityType;
import com.okwei.appinterface.service.agent.IBrandAgentService;
import com.okwei.appinterface.util.BitOperation;
import com.okwei.bean.domain.*;
import com.okwei.bean.vo.*;
import com.okwei.bean.vo.product.ProductInfo;
import com.okwei.common.JsonUtil;
import com.okwei.common.PageResult;
import com.okwei.dao.agent.IDAgentMgtDao;
import com.okwei.dao.user.ILoginDAO;
import com.okwei.dao.user.IUUserAssistMgtDAO;
import com.okwei.service.agent.IAgentBrandService;
import com.okwei.service.user.IUserIdentityManage;
import com.okwei.util.ImgDomain;
import com.okwei.web.base.SSOController;
import com.sdicons.json.mapper.MapperException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * Created by tang on 16/7/15.
 */

@RestController
@RequestMapping("/brandAgent")
public class BrandAgentController extends SSOController {

    private static final Log LOG = LogFactory.getLog(OrderController.class);

    @Autowired
    private ILoginDAO loginDAO;

    @Autowired
    private IAgentBrandService agentBrandService;

    @Autowired
    private IDAgentMgtDao agentMgtDao;

    @Autowired
    private IBrandAgentService brandAgentService;

    @Autowired
    private IUUserAssistMgtDAO userAssistMgtDAO;


//    @RequestMapping(value = "adList")
//    public String getHomeAdList() throws MapperException {
//        ReturnModel rm = new ReturnModel();
//        LoginUser user = getUser();
//        return JsonUtil.objectToJsonStr(rm);
//    }

    @RequestMapping(value = "homeShopList")
    public String getHomeShopList() throws MapperException {
        ReturnModel rm = new ReturnModel();
        LoginUser user = getUser();
        if (user != null) {
            List<AgentVO> agent1List = agentBrandService.getAgentList(1); //联合品牌
            List<AgentVO> agent0List = agentBrandService.getAgentList(0); //独立品牌
            List<BrandShopEx> brandShopList = new ArrayList<>();
            convertTo(agent1List, brandShopList);
            convertTo(agent0List, brandShopList);
            rm.setBasemodle(new ListModel(brandShopList));
            rm.setStatu(ReturnStatus.Success);
        } else {
            rm.setStatu(ReturnStatus.LoginError);
            rm.setStatusreson("您的身份已过期，请重新登录");
        }
        return JsonUtil.objectToJsonStr(rm);
    }

    @RequestMapping(value = "myBrandList")
    public String getMyBrandList() throws MapperException {
        ReturnModel rm = new ReturnModel();
        LoginUser user = getUser();
        if (user != null) {
            List<DBrands> brandsList;
            if (BitOperation.isIdentity(user.getIdentity(), UserIdentityType.AgentBrandSupplier)) { //品牌供应商
                brandsList = new ArrayList<>();
                DBrands brand = brandAgentService.getBrands(user.getWeiID());
                if (brand != null)
                    brandsList.add(brand);
            } else {
                brandsList = brandAgentService.findAgentBrands(user.getWeiID());
            }
            List<MyBrandShop> brandShops = new ArrayList<>();
            if (brandsList != null) {
                Map<Long, Set<MyBrandShop>> weiSellerMap = new HashMap<>();
                Map<Integer, Set<MyBrandShop>> brandMap = new HashMap<>();
                for (DBrands brands : brandsList) {
                    MyBrandShop brandShop = new MyBrandShop();
                    brandShop.setUserId(brands.getWeiId());
                    brandShop.setBrandId(brands.getBrandId());
                    brandShop.setBrandName(brands.getBrandName());
                    brandShop.setBrandLogo(brands.getLogo());
                    brandShop.setRelationshipType(brandAgentService.getAgentRelationshipType(user.getWeiID(), brands.getBrandId()));
                    brandShop.setRelationshipName(brandAgentService.getRelationshipName(brandShop.getRelationshipType()));
                    brandShops.add(brandShop);
                    if (!weiSellerMap.containsKey(brandShop.getUserId()))
                        weiSellerMap.put(brandShop.getUserId(), new HashSet<>());
                    weiSellerMap.get(brandShop.getUserId()).add(brandShop);
                    if (!brandMap.containsKey(brands.getBrandId()))
                        brandMap.put(brands.getBrandId(), new HashSet<>());
                    brandMap.get(brands.getBrandId()).add(brandShop);
                }
                if (weiSellerMap.size() > 0) {
                    Map<String, Object> hqlParams = new HashMap<String, Object>();
                    hqlParams.put("weiids", weiSellerMap.keySet());
                    List<UUserAssist> userAssistList = loginDAO.findByMap("from UUserAssist where weiId in (:weiids)", hqlParams);
                    for (UUserAssist userAssist : userAssistList) {
                        Set<MyBrandShop> brandShopSet = weiSellerMap.get(userAssist.getWeiId());
                        for (MyBrandShop brandShop : brandShopSet) {
                            brandShop.setIdentityType(userAssist.getIdentity());
                        }
                    }
                    List<UWeiSeller> weiSellerList = loginDAO.findByMap("from UWeiSeller where weiId in (:weiids)", hqlParams);
                    for (UWeiSeller weiSeller : weiSellerList) {
                        Set<MyBrandShop> brandShopSet = weiSellerMap.get(weiSeller.getWeiId());
                        for (MyBrandShop brandShop : brandShopSet) {
                            brandShop.setUserName(weiSeller.getWeiName());
                            brandShop.setShopName(weiSeller.getWeiName());
                            brandShop.setShopImg(ImgDomain.GetFullImgUrl(weiSeller.getImages(), 24));
                        }
                    }
                    List<UShopInfo> shopInfoList = loginDAO.findByMap("from UShopInfo where weiId in (:weiids)", hqlParams);
                    for (UShopInfo shopInfo : shopInfoList) {
                        Set<MyBrandShop> brandShopSet = weiSellerMap.get(shopInfo.getWeiId());
                        for (MyBrandShop brandShop : brandShopSet) {
//                    brandShop.setUserName(shopInfo.getWeiName());
                            brandShop.setShopName(shopInfo.getShopName());
                            brandShop.setShopImg(ImgDomain.GetFullImgUrl(shopInfo.getShopImg(), 24));
                        }
                    }
                }
                if (brandMap.size() > 0) {
                    Map<String, Object> hqlParams = new HashMap<String, Object>();
                    hqlParams.put("brandids", brandMap.keySet());
                    List<DBrandsInfo> brandsInfoList = loginDAO.findByMap("from DBrandsInfo where brandId in (:brandids)", hqlParams);
                    for (DBrandsInfo brandsInfo : brandsInfoList) {
                        Set<MyBrandShop> brandShopSet = brandMap.get(brandsInfo.getBrandId());
                        for (MyBrandShop brandShop : brandShopSet) {
                            brandShop.setAddress(brandAgentService.getAddress(brandsInfo.getProvince(), brandsInfo.getCity(), brandsInfo.getDistrict()));
                        }
                    }
                }
            }
            rm.setBasemodle(new ListModel(brandShops));
            rm.setStatu(ReturnStatus.Success);
        } else {
            rm.setStatu(ReturnStatus.LoginError);
            rm.setStatusreson("您的身份已过期，请重新登录");
        }
        return JsonUtil.objectToJsonStr(rm);
    }

    @RequestMapping(value = "myAgentList")
    public String getMyAgentList(@RequestParam(required = false, defaultValue = "1") int pageIndex,
                                 @RequestParam(required = false, defaultValue = "20") int pageSize,
                                 @RequestParam(required = false, defaultValue = "0") int scope,
                                 @RequestParam() long brandWid) throws MapperException {
        ReturnModel rm = new ReturnModel();
        LoginUser user = getUser();
        if (user != null) {
            DBrands brands = brandAgentService.getBrands(brandWid);
            if (brands != null) {
                PageResult<DAgentInfo> agentInfoList;
                if (user.getWeiID() == brandWid)
                    agentInfoList = brandAgentService.findDAgentInfos(brands.getBrandId(), scope, pageIndex, pageSize);
                else
                    agentInfoList = brandAgentService.findDAgentInfos(user.getWeiID(), brands.getBrandId(), scope, pageIndex, pageSize);
                Map<Long, Set<AgentShop>> weiSellerMap = new HashMap<>();
                Map<Long, Set<AgentShop>> parentWeiSellerMap = new HashMap<>();
                List<AgentShop> agentShopList = new ArrayList<>();
                for (DAgentInfo agentInfo: agentInfoList.getList()) {
                    AgentShop agentShop = new AgentShop();
                    agentShop.setUserId(agentInfo.getWeiId());
                    agentShop.setShopName(agentInfo.getName());
                    agentShop.setPhone(agentInfo.getContactPhone());
                    agentShop.setAgentType(agentInfo.getType() == null ? 0 : agentInfo.getType());
                    agentShop.setAgentBrandWid(brandWid);
                    agentShop.setParentWid(agentInfo.getSuperWeiid());
                    agentShop.setAddress(brandAgentService.getAddress(agentInfo.getProvince(), agentInfo.getCity(), agentInfo.getDistrict()));
                    DCastellans castellans = agentMgtDao.get_DCastellan(brands.getBrandId(), agentInfo.getWeiId());
                    if (castellans != null) {
                        agentShop.setIdentityName(castellans.getPorN() == 1 ? "城主" : "副城主");
                    } else {
                        DAgentTeam agentTeam = agentMgtDao.getDAgentTeam(brands.getBrandId(), agentInfo.getWeiId());
                        if (agentTeam != null) {
                            agentShop.setIdentityName("队长");
                        } else {
                            agentShop.setIdentityName("代理");
                        }
                    }
                    agentShopList.add(agentShop);
                    if (!weiSellerMap.containsKey(agentShop.getUserId()))
                        weiSellerMap.put(agentShop.getUserId(), new HashSet<>());
                    weiSellerMap.get(agentShop.getUserId()).add(agentShop);
                    if (agentInfo.getSuperWeiid() != null) {
                        if (!parentWeiSellerMap.containsKey(agentInfo.getSuperWeiid()))
                            parentWeiSellerMap.put(agentInfo.getSuperWeiid(), new HashSet<>());
                        parentWeiSellerMap.get(agentInfo.getSuperWeiid()).add(agentShop);
                    }
                }
                if (weiSellerMap.size() > 0) {
                    Map<String, Object> hqlParams = new HashMap<String, Object>();
                    hqlParams.put("weiids", weiSellerMap.keySet());
                    List<UUserAssist> userAssistList = loginDAO.findByMap("from UUserAssist where weiId in (:weiids)", hqlParams);
                    for (UUserAssist userAssist : userAssistList) {
                        Set<AgentShop> agentShopSet = weiSellerMap.get(userAssist.getWeiId());
                        for (AgentShop agentShop : agentShopSet) {
                            agentShop.setIdentityType(userAssist.getIdentity());
                        }
                    }
                    List<UWeiSeller> weiSellerList = loginDAO.findByMap("from UWeiSeller where weiId in (:weiids)", hqlParams);
                    for (UWeiSeller weiSeller: weiSellerList) {
                        Set<AgentShop> agentShopSet = weiSellerMap.get(weiSeller.getWeiId());
                        for (AgentShop agentShop : agentShopSet) {
                            agentShop.setUserName(weiSeller.getWeiName());
                            agentShop.setShopName(weiSeller.getWeiName());
                            agentShop.setShopImg(ImgDomain.GetFullImgUrl(weiSeller.getImages(), 24));
                        }
                    }
                    List<UShopInfo> shopInfoList = loginDAO.findByMap("from UShopInfo where weiId in (:weiids)", hqlParams);
                    for (UShopInfo shopInfo: shopInfoList) {
                        Set<AgentShop> agentShopSet = weiSellerMap.get(shopInfo.getWeiId());
                        for (AgentShop agentShop : agentShopSet) {
    //                        agentShop.setUserName(shopInfo.getWeiName());
                            agentShop.setShopName(shopInfo.getShopName());
                            agentShop.setShopImg(ImgDomain.GetFullImgUrl(shopInfo.getShopImg(), 24));
                        }
                    }
                }
                if (parentWeiSellerMap.size() > 0) {
                    Map<String, Object> hqlParams = new HashMap<String, Object>();
                    hqlParams.put("weiids", parentWeiSellerMap.keySet());
                    List<UWeiSeller> weiSellerList = loginDAO.findByMap("from UWeiSeller where weiId in (:weiids)", hqlParams);
                    for (UWeiSeller weiSeller : weiSellerList) {
                        Set<AgentShop> agentShopSet = parentWeiSellerMap.get(weiSeller.getWeiId());
                        for (AgentShop agentShop : agentShopSet) {
                            agentShop.setParentName(weiSeller.getWeiName());
                        }
                    }
                }
                PageInfo model = new PageInfo();
                model.setPageIndex(pageIndex);
                model.setPageSize(pageSize);
                model.setTotalPage(agentInfoList.getTotalPage());
                model.setTotalCount(agentInfoList.getTotalCount());
                model.setList(agentShopList);
                rm.setBasemodle(model);
                rm.setStatu(ReturnStatus.Success);
            } else {
                rm.setStatu(ReturnStatus.ParamError);
                rm.setStatusreson("品牌商不存在");
            }
        } else {
            rm.setStatu(ReturnStatus.LoginError);
            rm.setStatusreson("您的身份已过期，请重新登录");
        }
        return JsonUtil.objectToJsonStr(rm);
    }

    private void convertTo(List<AgentVO> source, List<BrandShopEx> target) {
        if (source == null || target == null)
            return;
        Map<Long, Set<BrandShopEx>> weiSellerMap = new HashMap<>();
        for (AgentVO agentVO: source) {
            BrandShopEx brandShop = new BrandShopEx(agentVO.getUser().getWeiid(), null, null,
                    agentVO.getUser().getImgurl(), 0,
                    agentVO.getBrandid(), agentVO.getUser().getBrandname(), agentVO.getUser().getImgurl(),
                    agentVO.getUser().getProductcount(), agentVO.getUser().getAgentcount());
            if (agentVO.getProduct() != null) {
                List<ProductInfo> productList = new ArrayList<>();
                for (AgentProductVO agentProductVO: agentVO.getProduct()) {
                    ProductInfo product = new ProductInfo();
                    product.setProductId(agentProductVO.getProductid());
                    product.setProductName(agentProductVO.getTitle());
                    product.setProductPicture(agentProductVO.getImgurl());
                    product.setRetailPrice(agentProductVO.getPrice());
                    productList.add(product);
                }
                brandShop.setProductList(productList);
            }
            target.add(brandShop);
            if (!weiSellerMap.containsKey(brandShop.getUserId()))
                weiSellerMap.put(brandShop.getUserId(), new HashSet<>());
            weiSellerMap.get(brandShop.getUserId()).add(brandShop);
        }
        if (weiSellerMap.size() > 0) {
            Map<String, Object> hqlParams = new HashMap<String, Object>();
            hqlParams.put("weiids", weiSellerMap.keySet());
            List<UUserAssist> userAssistList = loginDAO.findByMap("from UUserAssist where weiId in (:weiids)", hqlParams);
            for (UUserAssist userAssist : userAssistList) {
                Set<BrandShopEx> brandShopSet = weiSellerMap.get(userAssist.getWeiId());
                for (BrandShopEx brandShop : brandShopSet) {
                    brandShop.setIdentityType(userAssist.getIdentity());
                }
            }
        }
    }

}
