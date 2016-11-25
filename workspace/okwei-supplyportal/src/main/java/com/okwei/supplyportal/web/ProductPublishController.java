package com.okwei.supplyportal.web;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.okwei.bean.domain.PParamModel;
import com.okwei.bean.domain.PProductClass;
import com.okwei.bean.domain.PProducts;
import com.okwei.bean.domain.PShopClass;
import com.okwei.bean.enums.CatagoryStepEnum;
import com.okwei.common.JsonUtil;
import com.okwei.common.Limit;
import com.okwei.common.PageResult;
import com.okwei.supplyportal.bean.dto.ProductDTO;
import com.okwei.supplyportal.bean.vo.BaseSSOController;
import com.okwei.supplyportal.bean.vo.CatagoryVO;
import com.okwei.supplyportal.bean.vo.LoginUser;
import com.okwei.supplyportal.bean.vo.ProductVO;
import com.okwei.supplyportal.service.IProductPublishService;
import com.okwei.web.base.BaseController;

@Controller
@RequestMapping(value = "/publishProduct")
public class ProductPublishController extends BaseSSOController {

	private final static Log logger = LogFactory.getLog(ProductPublishController.class);

	@Autowired
	private IProductPublishService productPublishService;

	@RequestMapping(value = "/index")
	public String index(Model model) {
		logger.info("ProductPublishController index method starting .......");
		LoginUser user = super.getLoginUser();
		CatagoryVO vo = new CatagoryVO();
		List<PProductClass> catagoryList = productPublishService.getCatagoryByStep(CatagoryStepEnum.LEVEL1);
		vo.setLevel1(catagoryList);
		model.addAttribute("catagoryVO", vo);
		model.addAttribute("userinfo", user);
		return "productmgt/category";
	}

	@RequestMapping(value = "/show")
	public String show(String keyword, Integer catagoryId, Long productId, Model model) {
		logger.info("ProductPublishController index method starting .......");
		String view = "productmgt/category";
		LoginUser user = super.getLoginUser();
		if (StringUtils.isNotEmpty(keyword)) {
			// 搜索页面返回
			List<Map<String, String>> list = productPublishService.getCatagoryByKeyword(keyword);
			model.addAttribute("paths", list);
			model.addAttribute("keyword", keyword);
			return view;
		} else if (null != catagoryId) {
			// 从搜索结果页面返回
			CatagoryVO vo = productPublishService.getCatagoryByClassId(user.getWeiID(), catagoryId);
			model.addAttribute("catagoryVO", vo);
		} else if (null != productId) {
			// 从发布产品页面返回
			PProducts product = productPublishService.getById(PProducts.class, productId);
			Integer classId = product.getClassId();
			CatagoryVO vo = productPublishService.getCatagoryByClassId(user.getWeiID(), classId);
			vo.setTemplateId(product.getMid());
			model.addAttribute("catagoryVO", vo);
			model.addAttribute("catagoryId", classId);
			model.addAttribute("productId", productId);
		}
		return view;
	}

	@ResponseBody
	@RequestMapping(value = "/next/{id}", method = RequestMethod.GET)
	public String next(@PathVariable int id, @RequestParam(required = false) Model model) {
		logger.info("ProductPublishController next method starting .......");
		List<PProductClass> catagoryList = productPublishService.getCatagoryByParentId(id);
		return JsonUtil.objectToJson(catagoryList);
	}

	@ResponseBody
	@RequestMapping(value = "/getTemplate/{id}", method = RequestMethod.GET)
	public String getTemplate(@PathVariable int id, @RequestParam(required = false) Model model) {
		logger.info("ProductPublishController getTemplate method starting .......");
		LoginUser user = super.getLoginUser();
		List<PParamModel> templates = productPublishService.getTemplateByClassId(user.getWeiID(), id);
		return JsonUtil.objectToJson(templates);
	}

	@RequestMapping(value = "/popup/getProducts")
	public String getProducts(@ModelAttribute("queryParam") ProductDTO param, @RequestParam(required = false, defaultValue = "1") int pageId, Model model) {
		LoginUser user = super.getLoginUser();
		param.setSupplier(user.getWeiID());
		List<PShopClass> shopClass = productPublishService.getShopClassByWeiID(user.getWeiID());
		PageResult<ProductVO> page = productPublishService.getPProducts(param, Limit.buildLimit(pageId, 3));
		model.addAttribute("shopList", shopClass);
		model.addAttribute("page", page);
		return "productmgt/popup";
	}

}
