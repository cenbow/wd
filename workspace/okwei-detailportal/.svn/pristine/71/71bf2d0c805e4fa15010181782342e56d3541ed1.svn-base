package com.okwei.detail.web;

import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.okwei.bean.domain.PShevleBatchPrice;
import com.okwei.bean.vo.LoginUser;
import com.okwei.bean.vo.ReturnModel;
import com.okwei.bean.vo.ReturnStatus;
import com.okwei.bean.vo.product.ProductInfo;
import com.okwei.common.AjaxUtil;
import com.okwei.detail.bean.vo.HeadInfo;
import com.okwei.detail.bean.vo.ProductDetail;
import com.okwei.detail.service.ICommonService;
import com.okwei.detail.service.IProductService;
import com.okwei.service.shoppingcart.IBasicShoppingCartMgtService;
import com.okwei.util.AppSettingUtil;
import com.okwei.util.ParseHelper;
import com.okwei.util.RedisUtil;
import com.okwei.web.base.SSOController;

@Controller
@RequestMapping(value = "/product")
public class ProductController extends SSOController {

    @Autowired
    private IProductService productService;

    @Autowired
    private ICommonService commonService;

    @Autowired
    private IBasicShoppingCartMgtService cartService;

    /**
     * 产品详情
     */
    @RequestMapping(value = "", method = { RequestMethod.GET })
    public String detail(@RequestParam(required = false, defaultValue = "0") long sid, @RequestParam(required = false, defaultValue = "0") long pid, @RequestParam(required = false, defaultValue = "0") int f, @RequestParam(required = false, defaultValue = "0") long w, @RequestParam(required = false, defaultValue = "0") long shareid, Model model) {
	// 新增参数 f 1代表落地区2代理区 w 为推广人的微店号

    //获取828供应商微店号
    String supiler="supiler";
    model.addAttribute("supiler", ParseHelper.toLong(AppSettingUtil.getSingleValue(supiler)));
	// 公共信息
	LoginUser user = getUserOrSub();
	user.setCartCount(commonService.getCartCount(user.getWeiID()));
	HeadInfo headinfo = commonService.getHeadInfo();
	long userID = user.getWeiID();// 登陆的微店号
	ProductDetail product = productService.getDetail(user, sid, pid, f, w);
	if(product==null)
		return "1";
	int userLimitBuyCount=0; //用户在该活动中已购买此产品的数量
	// 查询是否关注 1关注 0未关注 2自己店铺
	if (product.getProActId()==null||product.getProActId()<=0) {
	    //product.setWholesale(1);
	    //int tag = product.getTag();
	    // 关注才能看到批发价
	    //if ((tag & 1) == 1) {
		List<PShevleBatchPrice> priceList = productService.getBatchPrices(product.getProID(), product.getSupWeiID());
		if(priceList!=null&&priceList.size()>0){
			product.setWholesale(1);
		}
		product.setPriceList(priceList);
	}
	if (userID > 0) {
		//如果活动中的产品，得到该用户在该活动中已购买此产品的数量,从缓存中获取
		if(product!=null&&product.getProActId()!=null&&product.getProActId()>0){
			String keyName="BuyLimitCount_"+userID+"_"+product.getActPid()+"_"+product.getProID();
			
			Integer limitCountVo=(Integer) RedisUtil.getObject(keyName);
			if(limitCountVo!=null)
				userLimitBuyCount=limitCountVo;
		}
		if(productService.getIsAttention(userID, product.getSupWeiID())){
			product.setIsAttention(1);
		}else{
			product.setIsAttention(2);
		}
	   /* if (userID == product.getSupWeiID()) {
	    	product.setIsAttention(2);
	    }*//* else {
			if (product.getProActId()==null||product.getProActId()<=0) {
			    product.setIsAttention(1);
			    //product.setWholesale(1);
			    //int tag = product.getTag();
			    // 关注才能看到批发价
			    //if ((tag & 1) == 1) {
				List<PShevleBatchPrice> priceList = productService.getBatchPrices(product.getProID(), product.getSupWeiID());
				if(priceList!=null&&priceList.size()>0){
					product.setWholesale(1);
				}
				product.setPriceList(priceList);
			    //}
			    // 预订 PC5.0 去掉预订，后台未赋值 前台未改动。
			    // if ((tag >> 1 & 1) == 1) {
			    // product.setSchedule(1);
			    // PPreOrder preOrder =
			    // productService.getPreOrder(product.getProID());
			    // if (preOrder != null) {
			    // product.setPrePrice(preOrder.getPreOrderPrice());
			    // product.setPreContent(preOrder.getContent());
			    // }
			    // }
			}
	    }*/
	}
	// 查询招商系列产品
	if (product.getForm() == 1 || product.getForm() == 2) {
	    // pc5.0查询用户是否可见落地价 代理价
	    ProductInfo info = productService.getPriceVisble(user, product.getDemandId(), product.getSupWeiID());
	    product.setShowAgentPrice(info.getDlPriceVisibility());
	    product.setShowLandPrice(info.getLdPriceVisibility());
	    product.setIsMyAgent(info.getCurrentUserIsAgent());
	    product.setIsMyLandShop(info.getCurrentUserIsStore());
	}
	// 查询产品的关键字
	product.setKeyword(productService.getKeyWords(product.getProID()));
	// 分享 佣金分配参数
	product.setSharePageId(shareid);
	if (w > 0) {
	    product.setShareOne(w);
	}

	model.addAttribute("user", user);
	model.addAttribute("headinfo", headinfo);
	model.addAttribute("product", product);
	model.addAttribute("userLimitBuyCount", userLimitBuyCount);
	return "product/detail";
    }

    /**
     * 分享
     * 
     * @param rzweiid
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/share", method = { RequestMethod.GET, RequestMethod.POST })
    public String share(long proID) {
	try {
	    productService.saveShareCount(proID);
	} catch (Exception e) {
	}
	return AjaxUtil.ajaxSuccess("1");

    }

    /**
     * 获取评论列表
     * 
     * @param rzweiid
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/comment", method = { RequestMethod.GET, RequestMethod.POST })
    public String comment(long proID, int index, int size) {
	return AjaxUtil.ajaxSuccess(productService.getCommentList(proID, index, size));
    }

    /**
     * 获取邮费
     * 
     * @param rzweiid
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/postage", method = { RequestMethod.GET, RequestMethod.POST })
    public String getPostage(long proID, int province, int city, int district, int count) {
	String result = "";
	double d = productService.getPostage(proID, province, city, district, count);
	if (d == 0) {
	    result = "0";
	} else if (d == -1) {
	    result = "-1";
	} else {
	    result = String.valueOf(d);
	}
	return AjaxUtil.ajaxSuccess(result);
    }

    /**
     * 关注
     * 
     * @param rzweiid
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/attention", method = { RequestMethod.GET, RequestMethod.POST })
    public String attention(int type, long supID) {
	LoginUser user = getUserOrSub();
	if (user.getWeiID() <= 0) {
	    return AjaxUtil.ajaxSuccess("-1");// 登陆超时
	}
	try {
	    productService.attentionSup(user.getWeiID(), type, supID);
	} catch (Exception e) {
	    return AjaxUtil.ajaxSuccess("0");
	}
	return AjaxUtil.ajaxSuccess("1");
    }

    /**
     * 加入购物车
     * 
     * @param rzweiid
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addcart", method = { RequestMethod.GET, RequestMethod.POST })
    public String addcart(String data) {
	LoginUser user =  getUserOrSub();
//	user.setWeiID(1036799l);
	if (user.getWeiID() <= 0) {
	    return AjaxUtil.ajaxSuccess("-1");// 登陆超时
	}
	ReturnModel result = cartService.addShoppingCartByStyleList(data, user);
	if (result.getStatu().equals(ReturnStatus.Success)) {
	    // 查询购物车的数量
	    long count = productService.getCartCount(user.getWeiID());
	    return AjaxUtil.ajaxSuccess(String.valueOf(count));
	} else {
	    return AjaxUtil.ajaxSuccess("0");
	}
    }

    /**
     * 老页面跳转到新的详情页
     * 
     * @param rzweiid
     * @return
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping(value = "/jump", method = { RequestMethod.GET, RequestMethod.POST })
    public void jump(ServletResponse response, @RequestParam(required = false, defaultValue = "0") long w, @RequestParam(required = false, defaultValue = "0") long pNo) throws IOException {
	String detaildomain = ResourceBundle.getBundle("domain").getString("detaildomain");
	HttpServletResponse httpResponse = (HttpServletResponse) response;
	long id = productService.getJumpID(w, pNo);
	if (id > 0) {
	    httpResponse.sendRedirect(detaildomain + "/product/" + String.valueOf(id));
	}
    }
}
