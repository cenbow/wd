package com.okwei.myportal.web;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.okwei.bean.vo.LoginUser;
import com.okwei.bean.vo.ReturnModel;
import com.okwei.bean.vo.ReturnStatus;
import com.okwei.common.JsonUtil;
import com.okwei.service.IBasicCommonService;
import com.okwei.util.ObjectUtil;
import com.okwei.util.RedisUtil;
import com.okwei.web.base.SSOController;
import com.sdicons.json.mapper.MapperException;

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

    @Autowired
    private IBasicCommonService basicCommonService;

    /**
     * 活动提醒
     * 
     * @return
     * @throws MapperException
     */
    @ResponseBody
    @RequestMapping(value = "/shareReminders")
    public String shareReminders(@RequestParam(required = false, defaultValue = "0") Short type) throws MapperException {
	LoginUser user = getLoginUser();
	ReturnModel rm = new ReturnModel();
	rm.setStatu(ReturnStatus.DataError);
	if (user == null) {
	    rm.setStatu(ReturnStatus.LoginError);
	    rm.setStatusreson("登陆超时");
	}
	// 判断只有供应商才提醒
	// if
	// test="${userinfo.yunS ==1 || userinfo.batchS ==1 || userinfo.pth ==1 || userinfo.pph ==1 || userinfo.pthdls ==1 || userinfo.pthldd ==1}">
	if (user.getYunS().intValue() == 1 || user.getBatchS().intValue() == 1 || user.getPth().intValue() == 1 && user.getPph().intValue() == 1) {
	    rm = basicCommonService.getShareRimand(user.getWeiID(), type);
	}
	return JsonUtil.objectToJsonStr(rm);
    }
}
