package com.okwei.appinterface.web.agent;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.okwei.appinterface.bean.vo.agent.ApplyBrandAgentInfoVO;
import com.okwei.appinterface.service.agent.IBrandAgentService;
import com.okwei.bean.vo.LoginUser;
import com.okwei.bean.vo.ReturnModel;
import com.okwei.bean.vo.ReturnStatus;
import com.okwei.util.AppSettingUtil;
import com.okwei.web.base.SSOController;

@Controller
@RequestMapping(value="/brandAgent")
public class BrandAgentMgtController extends SSOController{
	
	@Autowired
	private IBrandAgentService iBaseActivityService;
	
	@Autowired
    HttpServletRequest request;
	
	@ResponseBody
	@RequestMapping(value="/saveApplyBrandAgentInfo")
	public String saveApplyBrandAgentInfo(Integer brandId, String name,
			String contactPhone, String qq, String weiXin,Long referencer,Integer level,String advantage) throws Exception{
		ReturnModel result = new ReturnModel();
		  // 用户登录判断
        LoginUser user = getUserOrSub();
        if(user == null)
        {
        	result.setStatu(ReturnStatus.LoginError);
		    result.setStatusreson("您的身份已过期，请重新登录");
		    return JsonStr(result);
        }else if(user!=null && (user.getYunS()==1||user.getBatchS()==1||user.getPth()==1||user.getPph()==1||user.getBrandsupplyer()==1)){//
        	result.setStatu(ReturnStatus.SystemError);
        	result.setStatusreson("供应商无法申请代理！");
		}else if (user!=null&&(user.getbHrz()==1||user.getyHrz()==1||user.getYrz()==1)) {
			result.setStatu(ReturnStatus.SystemError);
			result.setStatusreson("认证员无法申请代理！");
		}else {
			result=iBaseActivityService.saveApplyAgent(brandId,name,contactPhone,qq,weiXin,0d,user.getWeiID(),referencer,level,advantage);
			Map<String, Object> map=new HashMap();
			map.put("url", "http://"+AppSettingUtil.getSingleValue("wapDomain")+"/v5/agent/agentonpay.mvc?brandId="+brandId+"&tiket="+request.getParameter("tiket")); 
			result.setBasemodle(map);
		}
		return JsonStr(result);
	}
	@ResponseBody
	@RequestMapping(value="getApplyBrandAgentInfo",method=RequestMethod.POST)
	public String getApplyBrandAgentInfo(Integer brandId,HttpServletRequest request) throws Exception{
		// 用户登录判断
		ReturnModel result = new ReturnModel();
		 // 用户登录判断
        LoginUser user = getUserOrSub();
        if(user == null)
        {
        	result.setStatu(ReturnStatus.LoginError);
		    result.setStatusreson("您的身份已过期，请重新登录");
		    return JsonStr(result);
        }
		ApplyBrandAgentInfoVO baseModle= iBaseActivityService.getApplyAgent(brandId);
		if(baseModle!=null){
			result.setBasemodle(baseModle);
			result.setStatu(ReturnStatus.Success);
			result.setStatusreson("成功");
			return JsonStr(result);
		}else{
			result.setStatu(ReturnStatus.DataError);
			result.setStatusreson("失败");
			return JsonStr(result);
		}
		
	}
}
