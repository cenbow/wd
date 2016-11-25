package com.okwei.walletportal.web;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
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

import com.okwei.bean.enums.OrderFrom;
import com.okwei.bean.vo.BResultMsg;
import com.okwei.bean.vo.LoginUser;
import com.okwei.common.JsonUtil;
import com.okwei.common.Limit;
import com.okwei.common.PageResult;
import com.okwei.service.order.IBasicPayService;
import com.okwei.util.ParseHelper;
import com.okwei.walletportal.bean.vo.BaseSSOController;
import com.okwei.walletportal.bean.vo.reward.RewardVO;
import com.okwei.walletportal.service.IRewardService;
import com.okwei.walletportal.service.ITest;

 

/**
 * @author fuhao
 * 悬赏
 */
@Controller
@RequestMapping(value = "/reward")
public class RewardController extends BaseSSOController {
	 
	private final static Log logger = LogFactory.getLog(RewardController.class);
	 
	@Autowired
	private IRewardService rewardService;

	@Autowired
	private IBasicPayService basicPayService;
	@Autowired
	private ITest itest;
	
	
	/**
	 * 获取支付单号
	 * @param model
	 * @param cls
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getOrderNO")
	public String getOrderNO(Model model ,String cls)  { 
		LoginUser user = super.getLoginUser();
		String[] cls1 = cls.split(","); 
		BResultMsg update_OrderPayInfo =new BResultMsg();
		if (cls1.length>0) {
			List<String> ids=new ArrayList<String>();
			for (int i = 0; i < cls1.length; i++) {
				ids.add(cls1[i]);
			}  
				try {
					update_OrderPayInfo = basicPayService.update_OrderPayInfo(user.getWeiID(), ParseHelper.toInt(OrderFrom.PC.toString()), 1, ids);
				} catch (Exception e) { 
					update_OrderPayInfo.setState(-1);
					update_OrderPayInfo.setMsg("沒有找到相应的支付信息");
				} 
		} 
		return JsonUtil.objectToJson(update_OrderPayInfo);
	}
	  
	
	@RequestMapping(value = "/rewardLists")
	public String cashCoupon(Model model ,
			@RequestParam(required = false, defaultValue = "") String monthTimeStr,
			@RequestParam(required = false, defaultValue = "") String yearTimeStr,
			@RequestParam(required = false, defaultValue = "1") int pageId,
			@RequestParam(required = false, defaultValue = "0") short dt)  { 
		LoginUser user = super.getLoginUser();
		Limit limit = Limit.buildLimit(pageId, 10) ; 
		PageResult<RewardVO> pageResult = rewardService.find_UPlatformServiceOrder(user.getWeiID(),yearTimeStr,monthTimeStr, dt, limit);
		DecimalFormat d=new DecimalFormat("0.00");
		double get_TotalMoney = rewardService.get_TotalMoney(user.getWeiID(),dt);
		
		Map<String, Object> get_countReward = rewardService.get_countReward(user.getWeiID());
		for (Entry<String, Object> map : get_countReward.entrySet()) {
			model.addAttribute(map.getKey(), map.getValue());
		}
		//未支付悬赏金额
		model.addAttribute("TotalMoney", d.format(get_TotalMoney));
		if (monthTimeStr!=null&&!"".equals(monthTimeStr)) {
			double get_monthTotalMoney = rewardService.get_monthTotalMoney(user.getWeiID(), yearTimeStr, monthTimeStr);
			//该月未支付悬赏金额
			model.addAttribute("monthTotalMoney", d.format(get_monthTotalMoney));
		}
		model.addAttribute("userinfo", user);
		model.addAttribute("pageResult", pageResult);
		model.addAttribute("monthTimeStr", monthTimeStr);
		model.addAttribute("yearTimeStr", yearTimeStr);	
		model.addAttribute("dt", dt); 
		return "reward/reward";
	}
	
 
}
