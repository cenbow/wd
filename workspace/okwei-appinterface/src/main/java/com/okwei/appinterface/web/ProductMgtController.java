package com.okwei.appinterface.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;




import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.okwei.appinterface.bean.dto.BaseParam;
import com.okwei.appinterface.bean.dto.ProductParam;
import com.okwei.bean.domain.PPriceShow;
import com.okwei.bean.domain.UUserAssist;
import com.okwei.bean.dto.ProductDTO;
import com.okwei.bean.enums.ProductStatusEnum;
import com.okwei.bean.vo.LoginUser;
import com.okwei.bean.vo.ProductMgtVO;
import com.okwei.bean.vo.ReturnModel;
import com.okwei.bean.vo.ReturnStatus;
import com.okwei.common.JsonUtil;
import com.okwei.common.Limit;
import com.okwei.common.PageResult;
import com.okwei.service.product.IBasicProductMgtService;
import com.okwei.util.ImgDomain;
import com.okwei.web.base.SSOController;

@RestController
@RequestMapping("/Products")
public class ProductMgtController extends SSOController {

	@Autowired
	private IBasicProductMgtService productMgtService;

	/**
	 * 产品列表
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/SupplyProducts")
	public String productList(ProductParam param) throws Exception {
		ReturnModel result = new ReturnModel();
		ProductDTO dto = new ProductDTO();
		dto.setType(param.getType());
		dto.setStatus(ProductStatusEnum.setValue(param.getState()));
		dto.setTitle(param.getKeyword());
		dto.setShopClassId(param.getClassId());
		dto.setFromSite((short) 1);
		LoginUser user = getUserOrSub();
		if(user!=null&&user.getWeiID()!=null&&user.getWeiID()>0){
			if(user.getBrandagent()==1&&user.getIsSeller()!=1){
				if(param.getState()!=1){
					result.setBasemodle(new PageResult<ProductMgtVO>());
				}else {
					PageResult<ProductMgtVO> pageResult = productMgtService.find_myProducts(user.getWeiID(), null, null, param.getPageIndex(), param.getPageSize());
					result.setBasemodle(pageResult);
				}
				result.setStatu(ReturnStatus.Success);
				result.setStatusreson("成功");
			}else {
				if (user.getPthSupply() == 1) {// 子供应商帐号
					dto.setWeiId(user.getAccount());
					dto.setSupWeiId(user.getParentWeiId());
					dto.setPTZ(true);
				} else {// 非子供应商帐号
					dto.setWeiId(user.getWeiID().toString());
					if (user.getPth() == 1) {
						dto.setPTH(true);
					}
					if (user.getPph() == 1) {
						dto.setPPH(true);
					}
					if (user.getPthdls() == 1) {
						dto.setDLS(true);
					}
					if (user.getPthldd() == 1) {
						dto.setLDD(true);
					}
				}
				PageResult<ProductMgtVO> pageResult = productMgtService.getProducts(dto, Limit.buildLimit(param.getPageIndex(), param.getPageSize()),user);
				result.setStatu(ReturnStatus.Success);
				result.setStatusreson("成功");
				result.setBasemodle(pageResult);
			}
		}
		else if (param.getFrom() > 0 && StringUtils.isNotEmpty(param.getWeiNo())) { // 匿名访问
			PageResult<ProductMgtVO> pageResult = productMgtService.getProducts(dto, Limit.buildLimit(param.getPageIndex(), param.getPageSize()),user);
			result.setStatu(ReturnStatus.Success);
			result.setStatusreson("成功");
			result.setBasemodle(pageResult);
		} else {
			result.setStatu(ReturnStatus.LoginError);
			result.setStatusreson("您的身份已过期，请重新登录");
		}
		return JsonStr(result);
	}

	/**
	 * 产品上架、下架、删除、审核
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/productHandle")
	public String productHandle(long productId, int iType, BaseParam param, HttpServletResponse response) {
		ReturnModel result = new ReturnModel();
		LoginUser user = getUserOrSub();
		// UWeiSellerLoginLog seller = super.validTicket(param);
		if (user != null) {
			// rqStatus = getProductList(request, seller.getWeiId());
		} else {
			result.setStatu(ReturnStatus.LoginError);
			result.setStatusreson("您的身份已过期，请重新登录");
		}
		return JsonStr(result);
	}

	/**
	 * 获取库存预警
	 * 
	 * @param weiId
	 * @param param
	 * @param model
	 * @return
	 */
	@RequestMapping("/getStockWarning")
	public String stockSet(Long weiId, BaseParam param) {
		ReturnModel result = new ReturnModel();
		LoginUser user = getUserOrSub();
		if (user != null) {
			Map<String, Integer> map = new HashMap<String, Integer>();
			UUserAssist uu = productMgtService.getById(UUserAssist.class, user.getWeiID());
			if (null != uu) {
				map.put("quantity", uu.getWarningNum());
			}
			result.setBasemodle(map);
			result.setStatu(ReturnStatus.Success);
			result.setStatusreson("设置成功");
		} else {
			result.setStatu(ReturnStatus.LoginError);
			result.setStatusreson("您的身份已过期，请重新登录");
		}
		return JsonStr(result);
	}

	/**
	 * 产品库存预警设置
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/setStockWarning")
	public String setStockWarning(Integer quantity, BaseParam param) {
		ReturnModel result = new ReturnModel();
		LoginUser user = getUserOrSub();
		if (user != null) {
			UUserAssist uu = productMgtService.getById(UUserAssist.class, user.getWeiID());
			if (null != uu) {
				uu.setWarningNum(quantity);
				productMgtService.update(uu);
			}
			result.setStatu(ReturnStatus.Success);
			result.setStatusreson("设置成功");
		} else {
			result.setStatu(ReturnStatus.LoginError);
			result.setStatusreson("您的身份已过期，请重新登录");
		}
		return JsonStr(result);
	}

	/**
	 * 获取价格可视范围
	 * 
	 * @param weiId
	 * @param param
	 * @param model
	 * @return
	 */
	@RequestMapping("/getPriceVisible")
	public String getPriceVisible(Long weiId, BaseParam param, Model model) {
		ReturnModel result = new ReturnModel();
		LoginUser user = getUserOrSub();
		if (user != null) {
			PPriceShow original = productMgtService.getById(PPriceShow.class, user.getWeiID());
			result.setBasemodle(original);
			result.setStatu(ReturnStatus.Success);
			result.setStatusreson("设置成功");
		} else {
			result.setStatu(ReturnStatus.LoginError);
			result.setStatusreson("您的身份已过期，请重新登录");
		}
		return JsonStr(result);
	}

	/**
	 * 价格可视范围设置
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/priceVisible")
	public String priceVisible(@RequestParam(value = "visible") String visible, Model model) {
		ReturnModel result = new ReturnModel();
		LoginUser user = getUserOrSub();
		PPriceShow ps = (PPriceShow) JsonUtil.jsonToBean(visible, PPriceShow.class);
		if (user != null) {
			ps.setWeiId(user.getWeiID());
			PPriceShow original = productMgtService.getById(PPriceShow.class, user.getWeiID());
			if (null != original) {
				original.setAgentsVisible(ps.getAgentsVisible());
				original.setDlFullyOpen(ps.getDlFullyOpen());
				original.setLdAgentsVisible(ps.getLdAgentsVisible());
				original.setOtherAgentsVisible(ps.getOtherAgentsVisible());
				original.setOtherShopVisible(ps.getOtherShopVisible());
				original.setShopVisible(ps.getShopVisible());
				productMgtService.update(original);
			} else {
				productMgtService.add(ps);
			}
			result.setStatu(ReturnStatus.Success);
			result.setStatusreson("设置成功");
		} else {
			result.setStatu(ReturnStatus.LoginError);
			result.setStatusreson("您的身份已过期，请重新登录");
		}
		return JsonStr(result);
	}
	
	@RequestMapping("/pictureList")
	public String getPictureImg(Long productId,Long w, Long h) {
		ReturnModel result = new ReturnModel();
		LoginUser user = getUserOrSub();
		if (user != null) {
			List<String> imglist=productMgtService.getPictureImg(productId);
			if(imglist!=null){
				List<String> list = new ArrayList<String>();
				for(String imgUrl:imglist){
					list.add(ImgDomain.GetFullImgUrl(imgUrl,24));
				};
				result.setBasemodle(list);
				result.setStatu(ReturnStatus.Success);
				result.setStatusreson("成功");
			}else{
				result.setBasemodle("");
				result.setStatu(ReturnStatus.Success);
				result.setStatusreson("成功");
			}
			
		} else {
			result.setStatu(ReturnStatus.LoginError);
			result.setStatusreson("您的身份已过期，请重新登录");
		}
		return JsonStr(result);
	}

}
