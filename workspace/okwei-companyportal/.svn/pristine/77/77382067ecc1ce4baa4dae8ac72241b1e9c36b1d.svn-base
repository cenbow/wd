package com.okwei.company.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.okwei.bean.domain.PShopClass;
import com.okwei.bean.domain.TMarketImgs;
import com.okwei.bean.domain.TMarketInfo;
import com.okwei.bean.domain.UBatchSupplyer;
import com.okwei.bean.enums.agent.BaseResultStateEnum;
import com.okwei.bean.vo.LoginUser;
import com.okwei.bean.vo.agent.BaseResultVO;
import com.okwei.bean.vo.agent.HeadInfo;
import com.okwei.common.JsonUtil;
import com.okwei.common.Limit;
import com.okwei.common.PageResult;
import com.okwei.company.bean.vo.BatchMarket;
import com.okwei.company.bean.vo.BatchMarketVO;
import com.okwei.company.bean.vo.ProductInfoVO;
import com.okwei.company.bean.vo.WholesaleCount;
import com.okwei.company.bean.vo.WholesaleList;
import com.okwei.company.service.IWholesaleService;
import com.okwei.service.IAgentCommonService;
import com.okwei.util.ImgDomain;
import com.okwei.web.base.SSOController;

@Controller
@RequestMapping(value = "/wholesale")
public class WholesaleController extends SSOController {

	private final static Log logger = LogFactory.getLog(WholesaleController.class);

	@Autowired
	private IAgentCommonService commonService;

	@Autowired
	private IWholesaleService wholesaleService;

	@RequestMapping(value = "/index", method = { RequestMethod.GET })
	public String index(Model model) {
		// 公共信息
		LoginUser user = getUserOrSub();
		user.setCartCount(commonService.getCartCount(user.getWeiID()));
		HeadInfo headinfo = commonService.getHeadInfo();
		model.addAttribute("user", user);
		model.addAttribute("headinfo", headinfo);

		// 获取批发市场推荐数据
		WholesaleCount wholesale = wholesaleService.getWholesaleCount();
		model.addAttribute("wholesale", wholesale);
		List<WholesaleList> list = wholesaleService.getWholesaleListss();
		// List<WholesaleList> list = wholesaleService.getWholesaleLists();
		model.addAttribute("list", list);
		model.addAttribute("mtype", "0");
		return "wholesale/index";
	}

	@RequestMapping(value = "/list", method = { RequestMethod.GET })
	public String list(String code, String id, @RequestParam(required = false, defaultValue = "1") int pageId, Model model) {
		// 公共信息
		LoginUser user = getUserOrSub();
		user.setCartCount(commonService.getCartCount(user.getWeiID()));
		HeadInfo headinfo = commonService.getHeadInfo();
		model.addAttribute("user", user);
		model.addAttribute("headinfo", headinfo);
		// 获取数量数据
		WholesaleCount listCount = wholesaleService.getWholesaleCount(code);
		model.addAttribute("listCount", listCount);
		// 获取列表数据
		Limit.buildLimit(pageId, 10);
		PageResult<BatchMarket> pageRes = wholesaleService.getWholesaleList(Limit.buildLimit(pageId, 60), code, id);
		model.addAttribute("pageRes", pageRes);
		model.addAttribute("code", code);
		model.addAttribute("id", id);
		model.addAttribute("mtype", "0");
		return "wholesale/list";
	}

	@RequestMapping(value = "/batchMarketInfo", method = { RequestMethod.GET })
	public String info(Integer m, Model model) {
		LoginUser user = getUserOrSub();
		HeadInfo headinfo = commonService.getHeadInfo();
		model.addAttribute("user", user);
		model.addAttribute("headinfo", headinfo);
		BatchMarketVO batchMarket = null;
		try {
			user.setCartCount(commonService.getCartCount(user.getWeiID()));
			WholesaleCount wholesale = wholesaleService.getWholesaleCount();
			batchMarket = wholesaleService.getBatchMarketVOById(m, 1);
			model.addAttribute("user", user);
			model.addAttribute("batchMarket", batchMarket);
			model.addAttribute("wholesale", wholesale);
			model.addAttribute("mtype", "0");
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return "wholesale/batchMarketInfo";
	}

	@RequestMapping(value = "/batchMarketDetail", method = { RequestMethod.GET })
	public String detail(Integer m, Model model) {
		if (m != null && m > 0) {
			BatchMarketVO batchMarket = null;
			LoginUser user = getUserOrSub();
			try {
				user.setCartCount(commonService.getCartCount(user.getWeiID()));
				HeadInfo headinfo = commonService.getHeadInfo();
				WholesaleCount wholesale = wholesaleService.getWholesaleCount();
				batchMarket = wholesaleService.getBatchMarketVOById(m, 0);
				if (batchMarket != null) {
					List<TMarketImgs> marketImgsList = wholesaleService.getMarketImgsListByBmid(batchMarket.getBmid());
					if (marketImgsList != null && marketImgsList.size() > 0) {
						for (TMarketImgs img : marketImgsList) {
							img.setImg(ImgDomain.GetFullImgUrl(StringUtils.isEmpty(img.getImg()) ? "" : img.getImg()));
						}
					}
					TMarketInfo marketInfo = wholesaleService.getById(TMarketInfo.class, m);
					model.addAttribute("batchMarket", batchMarket);
					model.addAttribute("marketInfo", marketInfo);
					model.addAttribute("marketImgsList", marketImgsList);
					model.addAttribute("user", user);
					model.addAttribute("headinfo", headinfo);
					model.addAttribute("wholesale", wholesale);
				}
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}
		return "wholesale/batchMarketDetail";
	}

	@RequestMapping(value = "/batchMarketShopList", method = { RequestMethod.POST, RequestMethod.GET })
	public String batchMarketShopList(@RequestParam(required = false, defaultValue = "1") int pageId, Model model, Integer bmid) {
		PageResult<UBatchSupplyer> pageRes = null;
		LoginUser user = getUserOrSub();
		if (bmid != null && bmid > 0) {
			try {
				pageRes = wholesaleService.getUBatchSupplyerList(Limit.buildLimit(pageId, 60), bmid);
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}
		model.addAttribute("pageResult", pageRes);// 列表
		model.addAttribute("bmid", bmid);// 批发市场id
		model.addAttribute("user", user);
		return "wholesale/shopList";
	}

	@RequestMapping(value = "/batchMarketProductList", method = { RequestMethod.POST, RequestMethod.GET })
	public String batchMarketProductList(@RequestParam(required = false, defaultValue = "1") int pageId, @RequestParam(required = false, defaultValue = "") String w, Model model, Integer bmid) {
		LoginUser user = getUserOrSub();
		if (!"".equals(w)) {
			user.setTgWeiID(w);
		}
		PageResult<ProductInfoVO> pageRes = null;
		if (bmid != null && bmid > 0) {
			try {
				pageRes = wholesaleService.getUBatchSupplyerProductList(Limit.buildLimit(pageId, 60), bmid, user.getWeiID());
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}
		model.addAttribute("pageResult", pageRes);// 列表
		model.addAttribute("bmid", bmid);// 批发市场id
		model.addAttribute("user", user);
		return "wholesale/productList";
	}

	/**
	 * 商品上架
	 * 
	 * @param type
	 * @param productID
	 *            商品id
	 * @param strProductIDs
	 *            商品阶梯价 json
	 * @param shopClassID
	 *            商品分类
	 */
	@ResponseBody
	@RequestMapping(value = "/submitShelves", method = { RequestMethod.POST, RequestMethod.GET })
	public String submitShelves(@RequestParam(required = false, defaultValue = "1") int type, @RequestParam(required = false, defaultValue = "0") int productID, @RequestParam(required = false, defaultValue = "") String strProductIDs,
			@RequestParam(required = false, defaultValue = "0") int shopClassID) {
		BaseResultVO resultVO = new BaseResultVO();
		resultVO.setState(BaseResultStateEnum.Failure);
		resultVO.setMessage("参数异常！");
		LoginUser user = getUserOrSub();
		if (user == null || user.getWeiID() < 1) {
			resultVO.setMessage("登录已过期,请重新登录！");
			return JsonUtil.objectToJson(resultVO);
		}
		boolean update = false;
		try {
			// 普通微店主单件商品上架
			if (type == 1) {
				if (productID < 1 || shopClassID < 1) {
					return JsonUtil.objectToJson(resultVO);
				}
				update = wholesaleService.updateShevles(productID, shopClassID, user.getWeiID());
			}
			// 供应商单件商品上架
			else {
				if (productID < 1 || shopClassID < 1) {
					return JsonUtil.objectToJson(resultVO);
				}
				update = wholesaleService.updateShevlesWithBatchPrice(productID, shopClassID, user.getWeiID(), strProductIDs);
			}

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		if (!update) {
			resultVO.setMessage("上架失败,请稍后重试！");
			return JsonUtil.objectToJson(resultVO);
		}

		resultVO.setState(BaseResultStateEnum.Success);
		resultVO.setMessage("上架成功！");
		return JsonUtil.objectToJson(resultVO);
	}

	@ResponseBody
	@RequestMapping(value = "/getShopClassList", method = { RequestMethod.POST, RequestMethod.GET })
	public String getShopClassList(String key, HttpServletRequest request) {
		LoginUser user = getUserOrSub();
		// 用户商品分类列表
		List<PShopClass> scList = new ArrayList<PShopClass>();
		if (user != null && user.getWeiID() > 0) {
			scList = wholesaleService.getShopClassList(user.getWeiID());
		}
		return JsonUtil.objectToJson(scList);
	}
}
