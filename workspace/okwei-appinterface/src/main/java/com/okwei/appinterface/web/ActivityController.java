package com.okwei.appinterface.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.okwei.bean.domain.PProducts;
import com.okwei.bean.dto.RecruitPartnerDTO;
import com.okwei.bean.vo.ReturnModel;
import com.okwei.bean.vo.ReturnStatus;
import com.okwei.common.JsonUtil;
import com.okwei.dao.product.IProductSearchDao;
import com.okwei.service.activity.IBaseRecruitPartnerService;
import com.okwei.util.ParseHelper;
import com.okwei.web.base.SSOController;

@RestController
@RequestMapping("/activity")
public class ActivityController extends SSOController {

	@Autowired
	IBaseRecruitPartnerService service;
	@Autowired
	IProductSearchDao dao;
	
	@ResponseBody
	@RequestMapping(value = "/savePartner", method = { RequestMethod.POST, RequestMethod.GET })
	public String savePartner(RecruitPartnerDTO dto){
		ReturnModel result = new ReturnModel();		
		result.setStatu(ReturnStatus.ParamError);
		if(dto.getName() ==null || "".equals(dto.getName())){
			result.setStatusreson("参数错误");
			return JsonUtil.objectToJson(result); 
		}
		if(dto.getPhone() ==null || "".equals(dto.getPhone())){
			result.setStatusreson("参数错误");
			return JsonUtil.objectToJson(result);
		}
		if(dto.getProvince() ==null || dto.getProvince()<1){
			result.setStatusreson("参数错误");
			return JsonUtil.objectToJson(result);
		}
		if(dto.getCity() ==null || dto.getCity() <1){
			result.setStatusreson("参数错误");
			return JsonUtil.objectToJson(result);
		}
		
		if(service.savePartner(dto)){
			result.setStatusreson("成功");
			result.setStatu(ReturnStatus.Success);
		}else{
			result.setStatusreson("系统异常，请稍后重试");
			result.setStatu(ReturnStatus.SystemError);
		}
		
		return JsonUtil.objectToJson(result);
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/test", method = { RequestMethod.POST, RequestMethod.GET })
	public String test(String productid){
		ReturnModel result = new ReturnModel();		
		PProducts products= dao.getPProducts(ParseHelper.toLong(productid));
		result.setBasemodle(products);
		return JsonUtil.objectToJson(result);
	}
}
