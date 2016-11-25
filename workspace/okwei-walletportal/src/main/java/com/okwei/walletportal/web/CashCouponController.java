package com.okwei.walletportal.web;

import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.okwei.bean.vo.LoginUser;
import com.okwei.common.JsonUtil;
import com.okwei.common.Limit;
import com.okwei.common.PageResult;
import com.okwei.dao.wallet.IBasicWalletDao;
import com.okwei.walletportal.bean.enums.BaseResultState;
import com.okwei.walletportal.bean.vo.BaseResultVO;
import com.okwei.walletportal.bean.vo.coupon.CashCouponVO;
import com.okwei.walletportal.service.ICashCouponService;
import com.okwei.walletportal.service.ITest;
import com.okwei.web.base.SSOController;
 

/**
 * @author fuhao
 * 现金券
 */
@Controller
@RequestMapping(value = "/cashCoupon")
public class CashCouponController extends SSOController {

	private final static Log logger = LogFactory.getLog(CashCouponController.class);
	@Autowired
	private IBasicWalletDao basicWalletDao;
	@Autowired
	private ICashCouponService cashCouponService;
	
	@Autowired
	private ITest itest;
	
	@RequestMapping(value = "/cashCoupon")
	public String cashCoupon(Model model ,@RequestParam(required = false, defaultValue = "1") int pageId, @RequestParam(required = false, defaultValue = "1") int dt)  { 
		LoginUser user = super.getLoginUser();
		Limit limit=Limit.buildLimit(pageId, 10); 
		PageResult<CashCouponVO>  pageResult = cashCouponService.find_CashCouponList(user.getWeiID(), limit, dt);
		Map<String, Object> get_CountCashCoupon = basicWalletDao.get_CountCashCoupon(user.getWeiID());
		for (Entry<String, Object> string : get_CountCashCoupon.entrySet()) {
			model.addAttribute(string.getKey(), string.getValue());
		} 
		model.addAttribute("pageResult", pageResult);
		model.addAttribute("dt", dt); 
		model.addAttribute("userinfo", user);
		return "cashCouponS/cashCoupon";
	}
	
	@ResponseBody
	@RequestMapping(value = "/delete")
	public String updatecashCoupon(Model model ,String cls)  { 
		LoginUser user = super.getLoginUser();
		String[] cls1 = cls.split(","); 
		BaseResultVO update_CashCoupon =null;
		if (cls1.length>0) {
			 update_CashCoupon = cashCouponService.update_CashCoupon(user.getWeiID(), cls1);
		}else{
			update_CashCoupon =new BaseResultVO();
			update_CashCoupon.setMessage("不存在删除的对象！");
			update_CashCoupon.setState(BaseResultState.Failure);
		}
		return JsonUtil.objectToJson(update_CashCoupon);
	}
	 
	
	
	@RequestMapping(value = "/youhuiquan")
	public String youhuiquan(Model model) {
		LoginUser user = super.getLoginUser();
		model.addAttribute("userinfo", user);
		return "cashCouponS/youhuiquan";
	}
}
