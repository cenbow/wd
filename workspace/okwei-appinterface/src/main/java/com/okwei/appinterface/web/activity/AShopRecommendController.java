package com.okwei.appinterface.web.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.okwei.appinterface.bean.vo.APPHomeVO;
import com.okwei.bean.domain.ADeals;
import com.okwei.bean.domain.AHomeApp;
import com.okwei.bean.vo.ADealsVO;
import com.okwei.bean.vo.LoginUser;
import com.okwei.bean.vo.ReturnModel;
import com.okwei.bean.vo.ReturnStatus;
import com.okwei.bean.vo.SaleAreaModel;
import com.okwei.bean.vo.activity.AHomeAppVO;
import com.okwei.bean.vo.activity.WeiShopModel;
import com.okwei.common.JsonUtil;
import com.okwei.common.Limit;
import com.okwei.common.PageResult;
import com.okwei.service.activity.IAShopRecommendMgtService;
import com.okwei.service.activity.IBaseActivityService;
import com.okwei.service.activity.IHomeAppService;
import com.okwei.service.shop.IDealsService;
import com.okwei.util.ImgDomain;
import com.okwei.web.base.SSOController;
import com.sdicons.json.mapper.MapperException;

@RestController
@RequestMapping(value = "/mall")
public class AShopRecommendController extends SSOController {

    @Autowired
    private IAShopRecommendMgtService iAshopService;
    @Autowired
    private IBaseActivityService baseActivityService;
    @Autowired
    private IDealsService dealsService;
    @Autowired
    private IHomeAppService homeAppService;

    @RequestMapping(value = "/homeShopList")
    public String recommendList(@RequestParam(required = false, defaultValue = "1") int pageIndex, @RequestParam(required = false, defaultValue = "20") int pageSize, HttpServletRequest request) throws MapperException {
	// 用户登录判断
	ReturnModel result = new ReturnModel();
	LoginUser user = getUserOrSub();
	if (user == null) {
	    result.setStatu(ReturnStatus.LoginError);
	    result.setStatusreson("您的身份已过期，请重新登录");
	    return JsonUtil.objectToJsonStr(result);
	}
	PageResult<WeiShopModel> pageResult = iAshopService.findRecommend(Limit.buildLimit(pageIndex, pageSize));
	if (pageResult != null) {
	    result.setBasemodle(pageResult);
	    result.setStatu(ReturnStatus.Success);
	    result.setStatusreson("成功");
	} else {
	    result.setStatu(ReturnStatus.DataError);
	    result.setStatusreson("传入参数为null");
	}
	return JsonUtil.objectToJsonStr(result);
    }

    @RequestMapping(value = "/homeSaleAreaList")
    public String homeSaleAreaList() throws MapperException {
	ReturnModel result = new ReturnModel();
	LoginUser user = getUserOrSub();
	if (user == null) {
	    result.setStatu(ReturnStatus.LoginError);
	    result.setStatusreson("您的身份已过期，请重新登录");
	    return JsonUtil.objectToJsonStr(result);
	}
	APPHomeVO model = new APPHomeVO();
	// 限时活动
	model.setFlashSaleArea(baseActivityService.getSaleAreaModel(user.getWeiID()));
	// 每日特卖
	ADeals deals = dealsService.getADeals();
	if (deals != null) {
	    SaleAreaModel specialSaleArea = new SaleAreaModel();
	    specialSaleArea.setAreaId(deals.getDealsId());
	    specialSaleArea.setAreaType(2);
	    specialSaleArea.setTitle("每日特卖");
	    specialSaleArea.setSubTitle("挡不住的诱惑");
	    specialSaleArea.setImageUrl(ImgDomain.GetFullImgUrl(deals.getPicturesHome()));
	    //specialSaleArea.setTagImageUrl(ImgDomain.GetFullImgUrl(deals.getDealsBigPicture()));
	    model.setSpecialSaleArea(specialSaleArea);
	}
	// 购物专区
	PageResult<AHomeAppVO> page = homeAppService.findAHomeApp(Limit.buildLimit(1, 4));
	if (page != null && page.getList() != null && page.getList().size() > 0) {
	    List<SaleAreaModel> shoppingAreaList = new ArrayList<SaleAreaModel>();
	    for (AHomeAppVO app : page.getList()) {
		SaleAreaModel temp = new SaleAreaModel();
		temp.setAreaId(app.getPosition());
		temp.setAreaType(0);
		temp.setTitle(app.getTitle());
		temp.setImageUrl(app.getHomeImage());
		shoppingAreaList.add(temp);
	    }
	    model.setShoppingAreaList(shoppingAreaList);
	}
	result.setStatu(ReturnStatus.Success);
	result.setBasemodle(model);
	return JsonUtil.objectToJsonStr(result);
    }

    @RequestMapping(value = "/specialSaleProductList")
    public String specialSaleProductList(@RequestParam(required = false, defaultValue = "1") int pageIndex, @RequestParam(required = false, defaultValue = "10") int pageSize) throws MapperException {
	ReturnModel result = new ReturnModel();
	LoginUser user = getUserOrSub();
	if (user == null) {
	    result.setStatu(ReturnStatus.LoginError);
	    result.setStatusreson("您的身份已过期，请重新登录");
	    return JsonUtil.objectToJsonStr(result);
	}
	ADeals deals = dealsService.getADeals();
	if (deals != null) {
	    result.setBasemodle(dealsService.getDealsProducts(deals.getDealsId(), Limit.buildLimit(pageIndex, pageSize)));
	}
	result.setStatu(ReturnStatus.Success);
	return JsonUtil.objectToJsonStr(result);
    }

    @RequestMapping(value = "/areaMainPrdouctList")
    public String areaMainPrdouctList(@RequestParam(required = false, defaultValue = "0") int areaId) throws MapperException {
	ReturnModel result = new ReturnModel();
	LoginUser user = getUserOrSub();
	if (user == null) {
	    result.setStatu(ReturnStatus.LoginError);
	    result.setStatusreson("您的身份已过期，请重新登录");
	    return JsonUtil.objectToJsonStr(result);
	}
	AHomeApp app = homeAppService.findAHomeApp(areaId);
	if (app != null) {
	    Map<String, Object> map = new HashMap<String, Object>();
	    map.put("list", dealsService.getHotMainProducts(app.getHomeId()));
	    result.setBasemodle(map);
	}
	result.setStatu(ReturnStatus.Success);
	return JsonUtil.objectToJsonStr(result);
    }

    @RequestMapping(value = "/areaProductList")
    public String areaProductList(@RequestParam(required = false, defaultValue = "0") int areaId, @RequestParam(required = false, defaultValue = "1") int pageIndex, @RequestParam(required = false, defaultValue = "10") int pageSize) throws MapperException {
	ReturnModel result = new ReturnModel();
	LoginUser user = getUserOrSub();
	if (user == null) {
	    result.setStatu(ReturnStatus.LoginError);
	    result.setStatusreson("您的身份已过期，请重新登录");
	    return JsonUtil.objectToJsonStr(result);
	}
	AHomeApp app = homeAppService.findAHomeApp(areaId);
	if (app != null) {
	    result.setBasemodle(dealsService.getAHomeMainProducts(app.getHomeId(), Limit.buildLimit(pageIndex, pageSize)));
	}
	result.setStatu(ReturnStatus.Success);
	return JsonUtil.objectToJsonStr(result);
    }

    /**
     * 
     * @param areaId表示AHomeApp主键ID
     * @param areaType表示AHomeApp的位置
     * @return
     * @throws MapperException
     */
    @RequestMapping(value = "/saleAreaInfo")
    public String saleAreaList(@RequestParam(required = false, defaultValue = "0") int areaId, @RequestParam(required = false, defaultValue = "0") int areaType) throws MapperException {
	// 用户登录判断
	ReturnModel result = new ReturnModel();
	LoginUser user = getUserOrSub();
	if (user == null) {
	    result.setStatu(ReturnStatus.LoginError);
	    result.setStatusreson("您的身份已过期，请重新登录");
	    return JsonUtil.objectToJsonStr(result);
	}
	SaleAreaModel temp = new SaleAreaModel();
	if (areaType == 0) {
	    AHomeApp app = homeAppService.findAHomeApp(areaId);
	    if (app != null) {
		temp.setAreaId(areaId);
		temp.setAreaType(areaType);
		temp.setImageUrl(ImgDomain.GetFullImgUrl(ImgDomain.GetFullImgUrl(app.getBannerImage(),75)));
		temp.setTitle(app.getTitle());
	    }
	} else if (areaType == 2) {
	    ADealsVO deals = dealsService.getADeals(areaId);
	    if (deals != null) {
		temp.setAreaId(areaId);
		temp.setAreaType(areaType);
		temp.setImageUrl(deals.getDealsBigPicture());
		temp.setTitle(deals.getDealsTitle());
	    }
	} else {
	    result.setStatu(ReturnStatus.DataError);
	    result.setStatusreson("数据错误");
	    return JsonUtil.objectToJsonStr(result);
	}
	result.setStatu(ReturnStatus.Success);
	result.setBasemodle(temp);
	return JsonUtil.objectToJsonStr(result);
    }
}
