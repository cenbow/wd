package com.okwei.appinterface.web.agent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.okwei.bean.vo.LoginUser;
import com.okwei.bean.vo.PictureAdModel;
import com.okwei.bean.vo.ReturnModel;
import com.okwei.bean.vo.ReturnStatus;
import com.okwei.common.JsonUtil;
import com.okwei.service.ISantoMgtService;
import com.okwei.web.base.SSOController;
import com.sdicons.json.mapper.MapperException;
@RestController
@RequestMapping("/brandAgent")
public class BrandAdsHomeController extends SSOController{
	@Autowired
	private ISantoMgtService iSantoMgtService;
	@RequestMapping("/adList")
	public String brandadList(HttpServletRequest request,@RequestParam(required = false, defaultValue = "1") int pageIndex, @RequestParam(required = false, defaultValue = "20") int pageSize)throws MapperException{
		// 用户登录判断
		ReturnModel result = new ReturnModel();
		LoginUser user = getUserOrSub();
		if (user == null) {
		    result.setStatu(ReturnStatus.LoginError);
		    result.setStatusreson("您的身份已过期，请重新登录");
		    return JsonUtil.objectToJsonStr(result);
		}
		List<PictureAdModel> list = iSantoMgtService.findListAdModels();
		if(list!=null&&list.size()>0){
			Map<String,Object> pMap = new HashMap<String, Object>();
			pMap.put("list", list);
			result.setBasemodle(pMap);
		}
		result.setStatu(ReturnStatus.Success);
	    result.setStatusreson("成功");
		return JsonUtil.objectToJsonStr(result);
	}
}
