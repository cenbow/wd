package com.okwei.appinterface.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.okwei.bean.vo.CompanyInfo;
import com.okwei.bean.vo.LoginUser;
import com.okwei.bean.vo.ReturnModel;
import com.okwei.bean.vo.ReturnStatus;
import com.okwei.bean.vo.ShopClassVO;
import com.okwei.bean.vo.ShopInfoMsg;
import com.okwei.service.agent.IDAgentService;
import com.okwei.service.shop.IBasicShopMgtService;
import com.okwei.web.base.SSOController;

@RestController
public class ShopMgtController extends SSOController {

	@Autowired
	private IBasicShopMgtService basicShopMgtService;

	
	/**
	 * 获取店铺分类
	 * 
	 * @param parentId
	 * @param shopWeiId
	 * @return
	 */
	@RequestMapping("/getShopClassList")
	public String getShopClassList(Integer parentId, Long shopWeiId) {
		ReturnModel result = new ReturnModel();
		LoginUser user = getUserOrSub();
		if (user != null) {
			List<ShopClassVO> list = basicShopMgtService.getShopClass(parentId, shopWeiId);
			result.setBasemodle(list);
			result.setStatu(ReturnStatus.Success);
			result.setStatusreson("成功");
		} else {
			result.setStatu(ReturnStatus.LoginError);
			result.setStatusreson("您的身份已过期，请重新登录");
		}
		return JsonStr(result);
	}

	/**
	 * 获取公司信息
	 * 
	 * @param weiId
	 * @return
	 */
	@RequestMapping("/getShopIntro")
	public String getShopIntro(Long weiId) {
		ReturnModel result = new ReturnModel();
		if (weiId != null) {
			CompanyInfo info = basicShopMgtService.getShopInfo(weiId);
			result.setBasemodle(info);
			result.setStatu(ReturnStatus.Success);
			result.setStatusreson("成功");
		} else {
			result.setStatu(ReturnStatus.LoginError);
			result.setStatusreson("请传入正确的微店号id");
		}
		return JsonStr(result);
	}
	
	
	@RequestMapping("/GetShopMsg")
	public String getShopMsg(HttpServletRequest request,Long weiNo,@RequestParam(required = false,defaultValue="0" ) int from){
		ReturnModel result = new ReturnModel();
		String tiket=request.getParameter("tiket");
		LoginUser user = getUserOrSub();
		if (user != null&&user.getWeiID()>0) {
			
			ShopInfoMsg rs = basicShopMgtService.UP_getShopInfo(weiNo, user.getWeiID(),tiket);
			result.setBasemodle(rs);
			result.setStatu(ReturnStatus.Success);
			result.setStatusreson("成功");
		}else{
			if(from==1){
				ShopInfoMsg rs = basicShopMgtService.UP_getShopInfo(weiNo, (long) 0 ,"");
				result.setBasemodle(rs);
				result.setStatu(ReturnStatus.Success);
				result.setStatusreson("成功");
			}else{
				result.setStatu(ReturnStatus.LoginError);
				result.setStatusreson("您的身份已过期，请重新登录");
			}
		}
		return JsonStr(result);
	}
}
