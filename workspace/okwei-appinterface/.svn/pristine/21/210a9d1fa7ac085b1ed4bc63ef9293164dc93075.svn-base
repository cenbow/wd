package com.okwei.appinterface.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.okwei.appinterface.bean.vo.MsgProductInfo;
import com.okwei.appinterface.bean.vo.ProductPrice;
import com.okwei.appinterface.service.IProductDetailService;
import com.okwei.bean.vo.LoginUser;
import com.okwei.bean.vo.ReturnModel;
import com.okwei.bean.vo.ReturnStatus;
import com.okwei.common.JsonUtil;
import com.okwei.web.base.SSOController;
import com.sdicons.json.mapper.MapperException;

@Controller
@RequestMapping("/ProductDetail")
public class ProductDetailController extends SSOController {

	@Autowired
	public IProductDetailService productDetailService;

	/**
	 * 订单列表
	 * 
	 * @throws MapperException
	 */
	@ResponseBody
	@RequestMapping(value = "/GetProductDetail")
	public String GetProductDetail(String tiket, @RequestParam(required = false, defaultValue = "0") int from, Long productId, Long weiNo, Long sharePageProducer, Long shareOne, @RequestParam(required = false, defaultValue = "0") int source,
			@RequestParam(required = false, defaultValue = "0") Long sharePageId) throws MapperException {
		ReturnModel result = new ReturnModel();
		LoginUser user = getUserOrSub();
		try {
			if (user == null && from <= 0) {
				result.setStatu(ReturnStatus.LoginError);
				result.setStatusreson("您的身份已过期，请重新登录");
			} else {
				MsgProductInfo model = productDetailService.GetProductDetail(productId, user, weiNo, tiket, source, false);
				if (model != null) {
					model.setSharePageProducer(sharePageProducer);
					model.setShareOne(shareOne);
					model.setSharePageId(sharePageId);
					result.setStatu(ReturnStatus.Success);
					result.setBasemodle(model);
				} else {
					result.setStatu(ReturnStatus.DataError);
					result.setStatusreson("此产品不存在");
				}
			}
		} catch (Exception e) {
			result.setStatu(ReturnStatus.DataError);
			result.setStatusreson("系统异常，请稍后再试！");
			result.setBasemodle(e);
		}
		return JsonUtil.objectToJsonStr(result);
	}

	
	/**
	 * 订单列表
	 * 
	 * @throws MapperException
	 */
	@ResponseBody
	@RequestMapping(value = "/GetNewProductDetail")
	public String GetNewProductDetail(String tiket, @RequestParam(required = false, defaultValue = "0") int from, Long productId, Long weiNo, Long sharePageProducer, Long shareOne, @RequestParam(required = false, defaultValue = "0") int source,
			@RequestParam(required = false, defaultValue = "0") Long sharePageId) throws MapperException {
		ReturnModel result = new ReturnModel();
		LoginUser user = getUserOrSub();
		try {
			if (user == null && from <= 0) {
				result.setStatu(ReturnStatus.LoginError);
				result.setStatusreson("您的身份已过期，请重新登录");
			} else {
				MsgProductInfo model = productDetailService.GetProductDetailNew(productId, user, weiNo, tiket, source, false);
				if (model != null) {
					model.setSharePageProducer(sharePageProducer);
					model.setShareOne(shareOne);
					model.setSharePageId(sharePageId);
					result.setStatu(ReturnStatus.Success);
					result.setBasemodle(model);
				} else {
					result.setStatu(ReturnStatus.DataError);
					result.setStatusreson("此产品不存在");
				}
			}
		} catch (Exception e) {
			result.setStatu(ReturnStatus.DataError);
			result.setStatusreson("系统异常，请稍后再试！");
			result.setBasemodle(e);
		}
		return JsonUtil.objectToJsonStr(result);
	}
	/**
	 * 获取产品相关价格库存
	 * 
	 * @throws MapperException
	 */
	@ResponseBody
	@RequestMapping("/getProductPriceByStyle")
	public String GetProductDetail(String tiket, Long productId, Long styleId) throws MapperException {
		ReturnModel result = new ReturnModel();
		LoginUser user = getUserOrSub();
		if (user != null) {
			ProductPrice model = productDetailService.getProductPrice(productId, styleId);
			if (model != null) {
				result.setStatu(ReturnStatus.Success);
				result.setBasemodle(model);
			} else {
				result.setStatu(ReturnStatus.DataError);
				result.setStatusreson("不存在的产品规格");
			}
		} else {
			result.setStatu(ReturnStatus.LoginError);
			result.setStatusreson("您的身份已过期，请重新登录");
		}
		return JsonUtil.objectToJsonStr(result);
	}
}
