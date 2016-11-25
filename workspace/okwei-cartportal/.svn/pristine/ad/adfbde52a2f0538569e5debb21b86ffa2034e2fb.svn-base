package com.okwei.cartportal.web;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.okwei.bean.domain.UCustomerAddr;
import com.okwei.bean.vo.LoginUser;
import com.okwei.bean.vo.ReturnModel;
import com.okwei.bean.vo.ReturnStatus;
import com.okwei.cartportal.bean.vo.ProdStylesVo;
import com.okwei.cartportal.bean.vo.StylesPriceVo;
import com.okwei.cartportal.service.IShopCartService;
import com.okwei.cartportal.service.ITest;
import com.okwei.common.AjaxUtil;
import com.okwei.common.JsonUtil;
import com.okwei.service.address.IBasicAdressService;
import com.okwei.service.shoppingcart.IBasicShoppingCartMgtService;
import com.okwei.web.base.SSOController;


@Controller
@RequestMapping(value="/shopCartAjax")
public class ShopCartAjaxController extends SSOController {

	private final static Log logger = LogFactory.getLog(ShopCartAjaxController.class);

	@Autowired
	private IShopCartService shopCartService;
	
	@Autowired
    private IBasicAdressService basicAdressService;

	@Autowired
	private IBasicShoppingCartMgtService iBasicShoppingCartMgtService;
	@Autowired
	private ITest itest;

	/**
	 * 修改购物车商品数量
	 * @param prodCount
	 * @param scId
	 * @return
	 */
	@ResponseBody
    @RequestMapping(value = "/updateCartPordCount",method = RequestMethod.POST)
    public String updateCartPordCount(int prodCount,long scId,long sellerWeiId){
		ReturnModel returnModel = new ReturnModel();
		try {
			if (scId > 0 && prodCount > 0) {
				LoginUser user = getLoginUser();
//				LoginUser user = itest.getLoginUser();
//				shopCartService.updateCartPordCount(prodCount,scId);
				returnModel = iBasicShoppingCartMgtService.updateTShoppingCar(user.getWeiID(), scId, sellerWeiId, prodCount);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			returnModel.setStatu(ReturnStatus.SystemError);
			returnModel.setStatusreson(e.getMessage());
		}
		return JsonUtil.objectToJson(returnModel);
    }
	/**
	 * 删除购物车
	 * @param scids
	 * @return
	 */
	@ResponseBody
    @RequestMapping(value = "/delCartPord",method = RequestMethod.POST)
    public String delCartPord(String scids){
//		String msg = "-1";
		ReturnModel returnModel = new ReturnModel();
		try {
			if (StringUtils.isNotEmpty(scids)) {
				LoginUser user = getLoginUser();
//				shopCartService.delCartPord(scids,user.getWeiID());
//				return AjaxUtil.ajaxSuccess("0");
				returnModel = iBasicShoppingCartMgtService.delTShoppingCar(user.getWeiID(),Long.parseLong(scids));
//				returnModel.setStatu(ReturnStatus.Success);
//				returnModel.setStatusreson("成功!");
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
//			return AjaxUtil.ajaxError(e, msg);
		}
//		return AjaxUtil.ajaxFail(msg);
		return JsonUtil.objectToJson(returnModel);
    }
	/**
	 * 获得选择的商品数量及价格
	 * @param scids
	 * @return
	 */
	@ResponseBody
    @RequestMapping(value = "/getJiesuanInfo",method = RequestMethod.POST)
    public String getJiesuanInfo(String scids,Short stype){
		ReturnModel returnModel = new ReturnModel();
		try {
			if (StringUtils.isNotEmpty(scids)) {
//				LoginUser user=itest.getLoginUser();
				LoginUser user = getLoginUser();
				returnModel = iBasicShoppingCartMgtService.getBalance(scids, user.getWeiID());
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			returnModel.setStatu(ReturnStatus.SystemError);
			returnModel.setStatusreson("系统错误!");
		}
		return JsonUtil.objectToJson(returnModel);
    }
	
	/**
     * 设为默认
     */
    @ResponseBody
    @RequestMapping(value = "/setDefaultAddr", method = { RequestMethod.POST, RequestMethod.GET })
    public String setDefaultAddr(int caddrId) {
	LoginUser user = super.getLoginUser();
	long weiid = user.getWeiID();
	if (caddrId <= 0) {
	    return AjaxUtil.ajaxSuccess("地址ID获取失败");
	}
	String result = "";
	try {
	    if (basicAdressService.setDefault(weiid, caddrId) > 0) {
		result = "1";
	    } else {
		result = "设为地址操作失败";
	    }
	} catch (Exception e) {
	    result = "设为地址操作失败";
	}
	return AjaxUtil.ajaxSuccess(result);
    }
    
    /**
     * 保存收货地址
     */
    @ResponseBody
    @RequestMapping(value = "/saveAddress",method =
    {RequestMethod.POST,RequestMethod.GET})
    public String submitApply(UCustomerAddr entity)
    {
        LoginUser user = super.getLoginUser();
        long weiid = user.getWeiID();
        // 验证
        String check = checkAddrEntity(entity);
        if(check != "ok")
        {
            return AjaxUtil.ajaxSuccess(check);
        }
        entity.setWeiId(weiid);
        String result = "";
        try
        {
            
            if(basicAdressService.saveOrUpdateAdd(entity) > 0)
            {
                result = "1";
            }
            else
            {
                result = "保存收货地址失败";
            }
        }
        catch(Exception e)
        {
            result = "保存收货地址失败";
        }
        return AjaxUtil.ajaxSuccess(result);
    }
    
    /**
     * 删除地址
     */
    @ResponseBody
    @RequestMapping(value = "/deleteAddr", method = { RequestMethod.POST, RequestMethod.GET })
	public String deleteAddr(int caddrId) {
		LoginUser user = super.getLoginUser();
		long weiid = user.getWeiID();
		if (caddrId <= 0) {
			return AjaxUtil.ajaxSuccess("地址ID获取失败");
		}
		String result = "";
		try {
			if (basicAdressService.deleteAddress(weiid, caddrId) > 0) {
				result = "1";
			} else {
				result = "删除失败";
			}
		} catch (Exception e) {
			result = "删除失败";
		}
		return AjaxUtil.ajaxSuccess(result);
	}
    
    private String checkAddrEntity(UCustomerAddr entity)
    {
        if(StringUtils.isEmpty(entity.getMobilePhone()))
        {
            return "手机不能为空";
        }
        else
        {
            if(!Pattern.matches("^[1][3,4,5,7,8][0-9]{9}$",entity.getMobilePhone()))
            {
                return "手机格式错误";
            }
        }
        if(StringUtils.isEmpty(entity.getReceiverName()))
        {
            return "收货人不能为空";
        }
        if(entity.getProvince() == null || entity.getProvince().intValue() == 0)
        {
            return "请选择省";
        }
        if(entity.getCity() == null || entity.getCity().intValue() == 0)
        {
            return "请选择市";
        }
        if(entity.getDistrict() == null || entity.getDistrict().intValue() == 0)
        {
            return "请选择区";
        }
        if(StringUtils.isEmpty(entity.getDetailAddr()))
        {
            return "详细地址不能为空";
        }
        return "ok";
    }
    /**
     * 根据选择的属性获得款式价格、款式id
     * @param styleJson
     * @return
     */
	@ResponseBody
	@RequestMapping(value = "/getProdStylePrice", method = {RequestMethod.POST, RequestMethod.GET })
	public String getProdStylePrice(String styleJson) {
		StylesPriceVo sp = new StylesPriceVo();
		try {
			if (StringUtils.isNotEmpty(styleJson)){
				sp = shopCartService.getProdStylePrice(styleJson);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return JsonUtil.objectToJson(sp);
	}
	/**
	 * 修改商品款式
	 * @param styleId
	 * @param scId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/modifyProdStyle", method = {RequestMethod.POST, RequestMethod.GET })
	public String modifyProdStyle(long styleId,long scId,short source) {
		StylesPriceVo sp = new StylesPriceVo();
		LoginUser user = getLoginUser();
		try {
			if (styleId > 0 && scId > 0){
				sp = shopCartService.modifyProdStyleId(scId,styleId,user.getWeiID(),source);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return JsonUtil.objectToJson(sp);
	}
	
	@ResponseBody
	@RequestMapping(value = "/checkProdStyles", method = {RequestMethod.POST, RequestMethod.GET })
	public String checkProdStyles(long productId,long attrId,long keyId) {
		List<ProdStylesVo> psList = new ArrayList<ProdStylesVo>();
		try {
			if (productId > 0 && attrId > 0 && keyId > 0){
				psList = shopCartService.getAvailableProdStyles(productId,attrId,keyId);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return JsonUtil.objectToJson(psList);
	}
	
}
