package com.okwei.appinterface.web.products;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.okwei.bean.vo.LoginUser;
import com.okwei.bean.vo.ReturnModel;
import com.okwei.bean.vo.ReturnStatus;
import com.okwei.bean.vo.product.ShelveProductParam;
import com.okwei.common.JsonUtil;
import com.okwei.service.product.IBasicProductMgtService;
import com.okwei.web.base.SSOController;

/**
 * 产品操作
 * @author yangjunjun
 *
 */
@RequestMapping("/productOperation")
@RestController
public class ProductOperationController extends SSOController {
	
	private final Logger LOG = Logger.getLogger(this.getClass());
	
	@Autowired
	private IBasicProductMgtService basicProductMgtService;
	
	
	
	
	/**
	 * 商品上架、下架、删除
	 * @param shelveProduct
	 * @return
	 */
	@RequestMapping(value = "/onSellProduct", method=RequestMethod.POST)
	public String raiseProduct(ShelveProductParam shelveProduct) {
		ReturnModel rs = new ReturnModel();
		try {
			LoginUser loginUser = getUserOrSub();
			if (loginUser != null) {
				if ("onGetSell".equals(shelveProduct.getFun())) {
					rs = basicProductMgtService.onGetShelvesProduct(shelveProduct,loginUser);
				} else if ("onSell".equals(shelveProduct.getFun())) {
					rs = basicProductMgtService.onShelvesProduct(shelveProduct,loginUser);
				} else if ("offSell".equals(shelveProduct.getFun())) {
					rs = basicProductMgtService.offShelvesProduct(shelveProduct,loginUser);
				} else if ("delete".equals(shelveProduct.getFun())) {
					rs = basicProductMgtService.deleteShelvesProduct(shelveProduct,loginUser);
				} else {
					rs.setStatu(ReturnStatus.ParamError);
					rs.setStatusreson("参数有误");
				}
			} else {
				rs.setStatu(ReturnStatus.LoginError);
				rs.setStatusreson("您的身份已过期，请重新登录");
			}
		} catch (Exception e) {
			LOG.error("操作商品"+shelveProduct.getIds()+"上架、下架、删除异常："+e.getMessage());
			rs.setStatu(ReturnStatus.DataError);
			if (e.getMessage() != null)
				rs.setStatusreson(e.getMessage());
			else
				rs.setStatusreson("数据有误!");
		}
		return JsonStr(rs);
	}
	
	

}
