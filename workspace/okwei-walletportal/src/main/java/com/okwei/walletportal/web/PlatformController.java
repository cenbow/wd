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
import com.okwei.walletportal.bean.vo.platform.PlatformVO;
import com.okwei.walletportal.service.IPlatformService;
import com.okwei.walletportal.service.IRewardService;
import com.okwei.walletportal.service.ITest;
import com.okwei.web.base.SSOController;

/**
 * @author fuhao
 * 平台服务费
 */
@Controller
@RequestMapping(value = "/platform")
public class PlatformController extends SSOController {
	 
	private final static Log logger = LogFactory.getLog(PlatformController.class);
	 
	@Autowired
	private IRewardService rewardService;
	
	@Autowired
	private IPlatformService platformService;
	 
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
				update_OrderPayInfo = basicPayService.update_OrderPayInfo(user.getWeiID(), ParseHelper.toInt(OrderFrom.PC.toString()), 2, ids);
			} catch (Exception e) {
				update_OrderPayInfo.setState(-1);
				update_OrderPayInfo.setMsg("沒有找到相应的支付信息");
			}
		} 
		return JsonUtil.objectToJson(update_OrderPayInfo);
	}
	
	@RequestMapping(value = "/platformList")
	public String platformList(Model model ,
			@RequestParam(required = false, defaultValue = "") String monthTimeStr,
			@RequestParam(required = false, defaultValue = "") String yearTimeStr,
			@RequestParam(required = false, defaultValue = "1") int pageId,
			@RequestParam(required = false, defaultValue = "0") String dt)  { 
		LoginUser user = super.getLoginUser();
		Limit limit = Limit.buildLimit(pageId, 10); 
		PageResult<PlatformVO> pageResult = platformService.findServiceFeeList(user.getWeiID(),yearTimeStr,monthTimeStr, dt, limit);
		double get_monthTotalMoney = 0.0;
		DecimalFormat d=new DecimalFormat("0.00");
		if (monthTimeStr!=null&&!"".equals(monthTimeStr)) {
			get_monthTotalMoney = platformService.get_monthTotalMoney(user.getWeiID(), yearTimeStr, monthTimeStr, dt);
			model.addAttribute("monthTotalMoney",d.format(get_monthTotalMoney));
		} 
		Map<String, Object> get_countPlatform = platformService.get_countPlatform(user.getWeiID());
		for (Entry<String, Object> map : get_countPlatform.entrySet()) {
			model.addAttribute(map.getKey(), map.getValue());
		}
		double get_PlatformMoney = platformService.get_PlatformMoney(user.getWeiID(), dt);
		model.addAttribute("PlatformMoney", d.format(get_PlatformMoney));
		double get_TotalMoney = platformService.get_TotalMoney(user.getWeiID(), dt);
		model.addAttribute("TotalMoney", d.format(get_TotalMoney));
		model.addAttribute("userinfo", user);
		model.addAttribute("pageResult", pageResult);
		model.addAttribute("monthTimeStr", monthTimeStr);
		model.addAttribute("yearTimeStr", yearTimeStr);	
		model.addAttribute("dt", dt); 
		return "platform/platform";
	}
	
 
}
