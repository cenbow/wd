package com.okwei.appinterface.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.okwei.bean.vo.LoginUser;
import com.okwei.bean.vo.PageInfo;
import com.okwei.bean.vo.ReturnModel;
import com.okwei.bean.vo.ReturnStatus;
import com.okwei.bean.vo.product.HouseClass;
import com.okwei.bean.vo.product.HouseInfo;
import com.okwei.bean.vo.product.InvestmentProducts;
import com.okwei.common.JsonUtil;
import com.okwei.common.Limit;
import com.okwei.service.product.IHouseService;
import com.okwei.util.RedisUtil;
import com.okwei.web.base.SSOController;
import com.sdicons.json.mapper.MapperException;

@Controller
@RequestMapping("/CloudProduct")
public class CloudProductController extends SSOController {

    @Autowired
    private IHouseService houseService;

    /**
     * 获取落地进货区推荐馆
     * 
     * @throws MapperException
     */
    @ResponseBody
    @RequestMapping(value = "/getTopic")
    public String getTopic(@RequestParam(required = false, defaultValue = "1") int pageIndex, int pageSize) throws MapperException {
	ReturnModel result = new ReturnModel();
	LoginUser user = getUserOrSub();
	if (user != null) {
	    List<HouseInfo> list = houseService.getHouseInfo(pageIndex, pageSize);
	    result.setStatu(ReturnStatus.Success);
	    result.setBasemodle(list);
	} else {
	    result.setStatu(ReturnStatus.LoginError);
	    result.setStatusreson("您的身份已过期，请重新登录");
	}
	return JsonUtil.objectToJsonStr(result);
    }

    /**
     * 获取xx专题馆商品列表
     * 
     * @throws MapperException
     */
    @ResponseBody
    @RequestMapping(value = "/getTopicProductList")
    public String getTopicProductList(@RequestParam(required = false, defaultValue = "1") int pageIndex, int pageSize, int classId, int classLevel) throws MapperException {
	ReturnModel result = new ReturnModel();
	LoginUser user = getUserOrSub();
	if (user != null) {
	    PageInfo model = houseService.getPageInfo(user, Limit.buildLimit(pageIndex, pageSize), classId, classLevel);
	    result.setStatu(ReturnStatus.Success);
	    result.setBasemodle(model);
	} else {
	    result.setStatu(ReturnStatus.LoginError);
	    result.setStatusreson("您的身份已过期，请重新登录");
	}
	return JsonUtil.objectToJsonStr(result);
    }

    /**
     * 获取招商系列商品列表
     * 
     * @throws MapperException
     */
    @ResponseBody
    @RequestMapping(value = "/getDemandProductList")
    public String getDemandProductList(@RequestParam(required = false, defaultValue = "1") int pageIndex, int pageSize, int demandId) throws MapperException {
	ReturnModel result = new ReturnModel();
	LoginUser user = getUserOrSub();
	if (user != null) {
	    InvestmentProducts model = houseService.getInvesProducts(user, Limit.buildLimit(pageIndex, pageSize), demandId, null);
	    result.setStatu(ReturnStatus.Success);
	    result.setBasemodle(model);
	} else {
	    result.setStatu(ReturnStatus.LoginError);
	    result.setStatusreson("您的身份已过期，请重新登录");
	}
	return JsonUtil.objectToJsonStr(result);
    }

    /**
     * 获取专题馆分类信息
     * 
     * @throws MapperException
     */
    @ResponseBody
    @RequestMapping(value = "/getTopicCategory")
    public String getTopicCategory(@RequestParam(required = false, defaultValue = "0") int parentId) throws MapperException {
	ReturnModel result = new ReturnModel();
	LoginUser user = getUserOrSub();
	if (user != null) {
	    List<HouseClass> list = houseService.getHouseClass(parentId);
	    result.setStatu(ReturnStatus.Success);
	    result.setBasemodle(list);
	} else {
	    result.setStatu(ReturnStatus.LoginError);
	    result.setStatusreson("您的身份已过期，请重新登录");
	}
	return JsonUtil.objectToJsonStr(result);
    }

    /**
     * 获取落地店的列表或搜索
     * 
     * @return
     * @throws MapperException
     */
    @ResponseBody
    @RequestMapping(value = "/getSearchYunPurchasesProducts")
    public String getSearchYunPurchasesProducts(@RequestParam(required = false, defaultValue = "") String keyWord, @RequestParam(required = false, defaultValue = "1") int pageIndex, int pageSize, @RequestParam(required = false, defaultValue = "0") int bigType, @RequestParam(required = false, defaultValue = "0") int smallType, @RequestParam(required = false, defaultValue = "0") int thirdType, @RequestParam(required = false, defaultValue = "0") int brandId, @RequestParam(required = false, defaultValue = "0") int source, @RequestParam(required = false, defaultValue = "0") int sortType, @RequestParam(required = false, defaultValue = "0") double minPrice, @RequestParam(required = false, defaultValue = "0") double maxPrice) throws MapperException {
	ReturnModel result = new ReturnModel();
	LoginUser user = getUserOrSub();
	// 首选获取缓存
	String keyName = keyWord + "_" + pageIndex + "-" + pageSize + "-" + bigType + "-" + smallType + "-" + thirdType + "-" + brandId + "_" + source + "-" + sortType + "-" + minPrice + "-" + maxPrice;
	Object obj = RedisUtil.getObject(keyName);
	if (obj != null) {
	    result.setStatu(ReturnStatus.Success);
	    result.setBasemodle(obj);
	    return JsonUtil.objectToJsonStr(result);
	}

	Map<String, String> params = new HashMap<String, String>();
	params.put("pageIndex", String.valueOf(pageIndex));
	params.put("pageSize", String.valueOf(pageSize));
	if (keyWord != "") {
	    try {
		keyWord = java.net.URLEncoder.encode(keyWord, "utf-8");
		params.put("content", keyWord);
	    } catch (Exception e) {
	    }
	}
	if (bigType > 0) {
	    params.put("bType", String.valueOf(bigType));
	}
	if (smallType > 0) {
	    params.put("mType", String.valueOf(smallType));
	}
	if (thirdType > 0) {
	    params.put("sType", String.valueOf(thirdType));
	}
	if (brandId > 0) {
	    params.put("brandId", String.valueOf(brandId));
	}
	if (source > 0) {
	    params.put("source", String.valueOf(source));
	}
	String orderby = "";// 排序字段
	switch (sortType) {
	    case 2:
		orderby = "aprice";
		break;
	    case 1:
		orderby = "dprice";
		break;
	    case 4:
		orderby = "acount";
		break;
	    case 3:
		orderby = "dcount";
		break;
	    case 5:
		orderby = "acreatetime";
		break;
	    case 6:
		orderby = "dcreatetime";
		break;
	    default:
		break;
	}
	if (orderby != "") {
	    params.put("orderBy", orderby);
	}
	if (minPrice > 0) {
	    params.put("sprice", String.valueOf(minPrice));
	}
	if (maxPrice > 0) {
	    params.put("eprice", String.valueOf(maxPrice));
	}
	// 落地店产品列表
	params.put("pType", "1");
	PageInfo model = houseService.getSearchYunPurchasesProducts(user, params);
	if (model != null)
	    RedisUtil.setObject(keyName, model);
	result.setStatu(ReturnStatus.Success);
	result.setBasemodle(model);
	return JsonUtil.objectToJsonStr(result);
    }

    /**
     * 获取首页进货列表
     * 
     * @throws MapperException
     */
    @ResponseBody
    @RequestMapping(value = "/getPurchaseList")
    public String getPurchaseList(@RequestParam(required = false, defaultValue = "1") int pageIndex, @RequestParam(required = false, defaultValue = "10") int pageSize, @RequestParam(required = false, defaultValue = "0") int orderBy) throws MapperException {
	ReturnModel result = new ReturnModel();
	LoginUser user = getUserOrSub();
	if (user != null) {
	    PageInfo model = houseService.getPurchaseList(user, pageIndex, pageSize, orderBy);
	    result.setStatu(ReturnStatus.Success);
	    result.setBasemodle(model);
	} else {
	    result.setStatu(ReturnStatus.LoginError);
	    result.setStatusreson("您的身份已过期，请重新登录");
	}
	return JsonUtil.objectToJsonStr(result);
    }
}
