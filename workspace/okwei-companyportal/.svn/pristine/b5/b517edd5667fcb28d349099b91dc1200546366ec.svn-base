package com.okwei.company.web;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.okwei.bean.domain.PProducts;
import com.okwei.bean.vo.LoginUser;
import com.okwei.bean.vo.agent.HeadInfo;
import com.okwei.company.bean.vo.HomeInfo;
import com.okwei.company.service.IFullBackService;
import com.okwei.service.IAgentCommonService;
import com.okwei.util.AppSettingUtil;
import com.okwei.util.ParseHelper;
import com.okwei.web.base.SSOController;

@Controller
@RequestMapping
public class GouFullback extends SSOController{
	

    @Autowired
    private IAgentCommonService commonService;
	
    @Autowired
    private IFullBackService fullbackService;
	
	@RequestMapping("/exchange")
	public String exchange(Model model){
		LoginUser user = getUserOrSub();
		user.setCartCount(commonService.getCartCount(user.getWeiID()));
		model.addAttribute("user", user);
		HeadInfo headinfo = commonService.getHeadInfo();
		model.addAttribute("headinfo", headinfo);
		String supiler = "supiler";
		List<PProducts> list= fullbackService.findProducts(ParseHelper.toLong(AppSettingUtil.getSingleValue( supiler)));
		model.addAttribute("list", list);
		
		// 衣食住行用玩
		List<HomeInfo> home = fullbackService.getHomeProducts();
		model.addAttribute("home", home);
		return "fullback/exchange";
	}
}
