package com.okwei.detail.web;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.okwei.bean.vo.LoginUser;
import com.okwei.util.ObjectUtil;
import com.okwei.util.RedisUtil;
import com.okwei.web.base.SSOController;

@Controller
@RequestMapping(value = "/commons")
public class CommonsController extends SSOController {

    @ResponseBody
    @RequestMapping(value = "/outLogin", method = { RequestMethod.GET })
    public String outLogin(HttpServletRequest request, Model model) {
	Cookie[] cookies = request.getCookies();
	String cookie_tiket = "";
	if (cookies != null) {
	    for (Cookie cookie : cookies) {
		if ("tiket".equals(cookie.getName())) {
		    cookie_tiket = cookie.getValue();
		    break;
		}
	    }
	}
	LoginUser user = super.getUserOrSub();
	// 判断cookie不为空
	if (!ObjectUtil.isEmpty(cookie_tiket)) {
	    RedisUtil.delete(cookie_tiket + "_java");
	    RedisUtil.delete(cookie_tiket + "_IBS");
            RedisUtil.delete(cookie_tiket + "_SUB");
	    RedisUtil.delete(cookie_tiket);
	}
	return String.valueOf(user.getWeiID());
    }

    @ResponseBody
    @RequestMapping(value = "/isLogin", method = { RequestMethod.GET })
    public String isLogin() {
	LoginUser user = getUserOrSub();
	if (user.getWeiID() > 0)
	    return "1";
	return "0";
    }
}
