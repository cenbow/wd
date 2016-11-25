 package com.okwei.appinterface.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.okwei.appinterface.bean.dto.ProductShopParam;
import com.okwei.appinterface.bean.dto.VerifierProductAgentListParam;
import com.okwei.bean.vo.LoginUser;
import com.okwei.bean.vo.PlatformProductShopListVO;
import com.okwei.bean.vo.ReturnModel;
import com.okwei.bean.vo.ReturnStatus;
import com.okwei.bean.vo.user.LandShopListVO;
import com.okwei.common.JsonUtil;
import com.okwei.common.Limit;
import com.okwei.common.PageResult;
import com.okwei.service.user.IBasicAgentOrProductShopService;
import com.okwei.web.base.SSOController;
import com.sdicons.json.mapper.MapperException;

@RequestMapping("/SupplierModule")
@RestController
public class PlatformOrBrandSupplierController extends SSOController{
	@Autowired
	private IBasicAgentOrProductShopService iBasicAgentOrProductShopService;
	
	/**
	 * 平台号的落地店列表
	 * @param param
	 * @return
	 * @throws MapperException 
	 */
	@RequestMapping("/getDownstreamStoreList")
	public String getDownstreamStoreList(VerifierProductAgentListParam param) throws MapperException {
		ReturnModel rm= new ReturnModel();
		LoginUser user = super.getUserOrSub();
		if(user==null){
			rm.setStatu(ReturnStatus.LoginError);
			rm.setStatusreson("登陆已过期，请重新登陆");
			return JsonUtil.objectToJsonStr(rm);
		}
		PageResult<PlatformProductShopListVO> pageResult = iBasicAgentOrProductShopService.getProductShopList(user.getWeiID(),(short)param.getStatus(), Limit.buildLimit(param.getPageIndex(), param.getPageSize()),1);
		rm.setBasemodle(pageResult);
		rm.setStatu(ReturnStatus.Success);
		rm.setStatusreson("成功!");
		return JsonUtil.objectToJsonStr(rm);
	}
	
	/**
	 * 代理商的落地店列表
	 * @param param
	 * @return
	 * @throws MapperException 
	 */
	@RequestMapping("/getDownstreamStoreListBydemandId")
	public String getDownstreamStoreListByDemandID(VerifierProductAgentListParam param) throws MapperException {
		ReturnModel rm= new ReturnModel();
		LoginUser user = super.getUserOrSub();
		if(user==null){
			rm.setStatu(ReturnStatus.LoginError);
			rm.setStatusreson("登陆已过期，请重新登陆");
			return JsonUtil.objectToJsonStr(rm);
		}
		PageResult<PlatformProductShopListVO> pageResult = iBasicAgentOrProductShopService.getProductShopListByDemandID(user.getWeiID(),(short)param.getStatus(), Limit.buildLimit(param.getPageIndex(), param.getPageSize()),param.getDemandId(),param.getCityCode());
		rm.setBasemodle(pageResult);
		rm.setStatu(ReturnStatus.Success);
		rm.setStatusreson("成功!");
		return JsonUtil.objectToJsonStr(rm);
	}
	
	/**
	 * 获取自己的落地点列表
	 * @param param
	 * @return
	 * @throws MapperException
	 */
	@RequestMapping("/getMyLandShopList")
	public String getMyLandShopList(VerifierProductAgentListParam param)throws MapperException{
		ReturnModel rm= new ReturnModel();
		LoginUser user = super.getUserOrSub();
		if(user==null){
			rm.setStatu(ReturnStatus.LoginError);
			rm.setStatusreson("登陆已过期，请重新登陆");
			return JsonUtil.objectToJsonStr(rm);
		}
		PageResult<LandShopListVO> pageResult = iBasicAgentOrProductShopService.getMyLandShopList(user.getWeiID(), Limit.buildLimit(param.getPageIndex(), param.getPageSize()));
		rm.setBasemodle(pageResult);
		rm.setStatu(ReturnStatus.Success);
		rm.setStatusreson("成功!");
		return JsonUtil.objectToJsonStr(rm);
	}
	
	/**
	 * 平台号的落地店详情
	 * @param param
	 * @return
	 * @throws MapperException 
	 */
	@RequestMapping("/getDownstreamStoreDetail")
	public String getDownstreamStoreDetail(ProductShopParam param) throws MapperException {
		ReturnModel rm= new ReturnModel();
		LoginUser user = super.getUserOrSub();
		if(user==null){
			rm.setStatu(ReturnStatus.LoginError);
			rm.setStatusreson("登陆已过期，请重新登陆");
			return JsonUtil.objectToJsonStr(rm);
		}
		rm = iBasicAgentOrProductShopService.getProductShopDetail(param.getShopId());
		return JsonUtil.objectToJsonStr(rm);
	}
	
	/**
	 * 取消落地店
	 * @param param
	 * @return
	 * @throws MapperException 
	 */
	@RequestMapping("/cancelProductShop")
	public String cancelProductShop(ProductShopParam param) throws MapperException {
		ReturnModel rm= new ReturnModel();
		LoginUser user = super.getUserOrSub();
		if(user==null){
			rm.setStatu(ReturnStatus.LoginError);
			rm.setStatusreson("登陆已过期，请重新登陆");
			return JsonUtil.objectToJsonStr(rm);
		}
		rm = iBasicAgentOrProductShopService.updateProductShopState(param.getShopId(),param.getStatusReson(),param.getStatus());
		return JsonUtil.objectToJsonStr(rm);
	}
	
	
}
