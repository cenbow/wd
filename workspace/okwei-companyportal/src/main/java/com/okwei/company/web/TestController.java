package com.okwei.company.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.okwei.bean.vo.LoginUser;
import com.okwei.bean.vo.agent.HeadInfo;
import com.okwei.service.IAgentCommonService;
import com.okwei.web.base.SSOController;

@Controller
@RequestMapping(value = "/test")
public class TestController extends SSOController {

    @Autowired
    private IAgentCommonService commonService;

    @RequestMapping(value = "/test", method = { RequestMethod.GET })
    public String index(String w, Model model) {
	LoginUser user = getUserOrSub();
	user.setTgWeiID(w);
	user.setCartCount(commonService.getCartCount(user.getWeiID()));
	HeadInfo headinfo = commonService.getHeadInfo();
	model.addAttribute("user", user);
	model.addAttribute("headinfo", headinfo);
		return "test/test";
    }
}
