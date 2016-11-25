package com.okwei.appinterface.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.okwei.appinterface.bean.vo.AppResult;
import com.okwei.appinterface.bean.vo.AreaTree;
import com.okwei.appinterface.bean.vo.DemandProduct;
import com.okwei.appinterface.bean.vo.InvestmentDemand;
import com.okwei.appinterface.bean.vo.RegionChannel;
import com.okwei.appinterface.bean.vo.RequiredInfo;
import com.okwei.appinterface.bean.vo.AppResult.Status;
import com.okwei.appinterface.bean.vo.RequiredKV;
import com.okwei.bean.domain.USupplyer;
import com.okwei.bean.domain.User;
import com.okwei.bean.enums.DemandStateEnum;
import com.okwei.bean.enums.SupplyChannelTypeEnum;
import com.okwei.bean.vo.DemandProductVO;
import com.okwei.bean.vo.LoginUser;
import com.okwei.bean.vo.RegionVO;
import com.okwei.bean.vo.ResultMsg;
import com.okwei.bean.vo.ReturnModel;
import com.okwei.bean.vo.ReturnStatus;
import com.okwei.bean.vo.SupplyDemandVO;
import com.okwei.bean.vo.user.ChannelInfoVO;
import com.okwei.bean.vo.user.ChannelRegionVO;
import com.okwei.bean.vo.user.DemandChannelVO;
import com.okwei.bean.vo.user.DemandRequiredVO;
import com.okwei.bean.vo.user.RequiredKVVO;
import com.okwei.common.JsonUtil;
import com.okwei.common.Limit;
import com.okwei.common.PageResult;
import com.okwei.service.user.IBaseSupplyDemandService;
import com.okwei.web.base.SSOController;


@RestController
@RequestMapping("/demand")
public class SupplyDemandController extends SSOController {

	@Autowired
	IBaseSupplyDemandService service;
	
	/**
	 * 获取用户的招商需求列表 
	 * @param tiket
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getMyDemandList", method = { RequestMethod.POST, RequestMethod.GET })
	public String getMyDemandList(String tiket,Integer pageIndex,Integer pageSize){
		ReturnModel result = new ReturnModel();
		
		if(pageIndex ==null || pageIndex <1){
			pageIndex =1;
		}
		if(pageSize ==null || pageSize <1){
			pageSize =3;
		}

		LoginUser user = super.getUserOrSub();
		if(user==null){
			result.setStatu(ReturnStatus.LoginError);
			result.setStatusreson("登陆已过期，请重新登陆");
			return JsonStr(result);
		}
		
		PageResult<SupplyDemandVO> demandVOs = service.getSupplyDemandVOs(user.getWeiID(),Limit.buildLimit(pageIndex, pageSize));
		if(demandVOs !=null && demandVOs.getList() !=null && demandVOs.getList().size() >0){
			PageResult<InvestmentDemand> idPageResult = new PageResult<InvestmentDemand>();
			idPageResult.setPageCount(demandVOs.getPageCount());
			idPageResult.setPageIndex(demandVOs.getPageIndex());
			idPageResult.setPageSize(demandVOs.getPageSize());
			idPageResult.setTotalCount(demandVOs.getTotalCount());
			
			List<InvestmentDemand> idemandList = new ArrayList<InvestmentDemand>();
			for (SupplyDemandVO sd : demandVOs.getList()) {
				InvestmentDemand iDemand = new InvestmentDemand();
							
				iDemand.setMerchantWeiId(sd.getWeiId());
				iDemand.setMerchantName(user.getWeiName());
				iDemand.setDemandId(sd.getDemandId());
				iDemand.setDemandName(sd.getTitle());
				iDemand.setTotalProduct(sd.getpCount());
				iDemand.setImg(sd.getMainImage());
				iDemand.setAreaStr(sd.getAreaString());
				iDemand.setStoreCount(sd.getShopCount());
				iDemand.setAgentCount(sd.getAgentCount());
				iDemand.setApplyTime(sd.getCreateTime());				
				iDemand.setStatus(sd.getState());
				iDemand.setAuditTime(sd.getAuditTime());
				iDemand.setMaxAgentReward(sd.getMaxAgentReward());
				iDemand.setMinAgentReward(sd.getMinAgentReward());
				iDemand.setRemarks(sd.getNoPassReason());

				idemandList.add(iDemand);
			}
			idPageResult.setList(idemandList);
			
			result.setBasemodle(idPageResult);

		}
		
		result.setStatusreson("成功");
		result.setStatu(ReturnStatus.Success);
		return JsonStr(result).replace("Status", "status");
	}
	
	/**
	 * 获取供应商自己的招商需求列表  CH01
	 * @param status
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getSupplyDemandSelf", method = { RequestMethod.POST, RequestMethod.GET })
	public String getSupplyDemandSelf(String tiket,Integer status,Integer pageIndex,Integer pageSize) {
		
		ReturnModel result = new ReturnModel();
		
		if(pageIndex ==null || pageIndex <1){
			pageIndex =1;
		}
		if(pageSize ==null || pageSize <1){
			pageSize =3;
		}

		LoginUser user = super.getUserOrSub();
		if(user==null){
			result.setStatu(ReturnStatus.LoginError);
			result.setStatusreson("登陆已过期，请重新登陆");
			return JsonStr(result);
		}
			
		DemandStateEnum demandState = null;
		if(status !=null && status >-1){
			try {
				demandState = DemandStateEnum.values()[status];
			} catch (Exception e) {
				result.setStatu(ReturnStatus.ParamError);
				result.setStatusreson("参数错误");
				return JsonStr(result);
			}			
		}
		//-1代表所有，0-草稿箱，1-审核中，2-招商中，3审核不通过，4停止招商
/*		switch (status) {
		case  0:demandState = DemandStateEnum.Draft;break;
		case  1:demandState = DemandStateEnum.WaitAuditing;break;
		case  2:demandState = DemandStateEnum.Showing;break;
		case  3:demandState = DemandStateEnum.NoPass;break;
		case  4:demandState = DemandStateEnum.OffShelf;break;
			default :
				demandState = null;
			break;
		}	*/	

		PageResult<SupplyDemandVO> demandVOs = service.getSupplyDemandVOs(user.getWeiID(),3, demandState, Limit.buildLimit(pageIndex, pageSize));
		if(demandVOs !=null && demandVOs.getList() !=null && demandVOs.getList().size() >0){
			PageResult<InvestmentDemand> idPageResult = new PageResult<InvestmentDemand>();
			idPageResult.setPageCount(demandVOs.getPageCount());
			idPageResult.setPageIndex(demandVOs.getPageIndex());
			idPageResult.setPageSize(demandVOs.getPageSize());
			idPageResult.setTotalCount(demandVOs.getTotalCount());
			
			List<InvestmentDemand> idemandList = new ArrayList<InvestmentDemand>();
			for (SupplyDemandVO sd : demandVOs.getList()) {
				InvestmentDemand iDemand = new InvestmentDemand();
							
				iDemand.setMerchantWeiId(sd.getWeiId());
				iDemand.setMerchantName(user.getWeiName());
				iDemand.setDemandId(sd.getDemandId());
				iDemand.setDemandName(sd.getTitle());
				iDemand.setTotalProduct(sd.getpCount());
				iDemand.setImg(sd.getMainImage());
				iDemand.setAreaStr(sd.getAreaString());
				iDemand.setStoreCount(sd.getShopCount());
				iDemand.setAgentCount(sd.getAgentCount());
				iDemand.setApplyTime(sd.getCreateTime());				
				iDemand.setStatus(sd.getState());
				iDemand.setAuditTime(sd.getAuditTime());
				iDemand.setMaxAgentReward(sd.getMaxAgentReward());
				iDemand.setMinAgentReward(sd.getMinAgentReward());
				iDemand.setRemarks(sd.getNoPassReason());

				idemandList.add(iDemand);
			}
			idPageResult.setList(idemandList);
			
			result.setBasemodle(idPageResult);

		}
		
		result.setStatusreson("成功");
		result.setStatu(ReturnStatus.Success);
		return JsonStr(result).replace("Status", "status");

	}
	
	/**
	 * 获取某个供应商的招商需求列表  DP04
	 * @param weiId
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getSupplyDemand", method = { RequestMethod.POST, RequestMethod.GET })
	public String getSupplyDemand(String tiket, Long weiId,Integer pageIndex,Integer pageSize) {
		
		ReturnModel result = new ReturnModel();
		LoginUser user = super.getUserOrSub();
		if(user==null){
			result.setStatu(ReturnStatus.LoginError);
			result.setStatusreson("登陆已过期，请重新登陆");
			return JsonStr(result);
		}
		if(weiId == null || weiId <1){
			result.setStatu(ReturnStatus.ParamError);
			result.setStatusreson("参数错误！");
			return JsonStr(result);
		}
		if(pageIndex ==null || pageIndex <1){
			pageIndex =1;
		}
		if(pageSize ==null || pageSize <1){
			pageSize =3;
		}
		
		
		PageResult<SupplyDemandVO> demandVOs = service.getSupplyDemandVOs(weiId,3, DemandStateEnum.Showing, Limit.buildLimit(pageIndex, pageSize));
		if(demandVOs !=null && demandVOs.getList() !=null && demandVOs.getList().size() >0){
			PageResult<InvestmentDemand> idPageResult = new PageResult<InvestmentDemand>();
			idPageResult.setPageCount(demandVOs.getPageCount());
			idPageResult.setPageIndex(demandVOs.getPageIndex());
			idPageResult.setPageSize(demandVOs.getPageSize());
			idPageResult.setTotalCount(demandVOs.getTotalCount());
			
			List<InvestmentDemand> idemandList = new ArrayList<InvestmentDemand>();
			for (SupplyDemandVO sd : demandVOs.getList()) {
				InvestmentDemand iDemand = new InvestmentDemand();
				iDemand.setAgentCount(sd.getAgentCount());
				iDemand.setApplyTime(sd.getCreateTime());
				iDemand.setDemandId(sd.getDemandId());
				iDemand.setDemandName(sd.getTitle());
				iDemand.setImg(sd.getMainImage());
				iDemand.setStatus(sd.getState());
				iDemand.setStoreCount(sd.getShopCount());
				iDemand.setTotalProduct(sd.getpCount());
				iDemand.setAuditTime(sd.getAuditTime());
				iDemand.setAreaStr(sd.getAreaString());
				iDemand.setMerchantName(sd.getWeiName());
				iDemand.setMerchantWeiId(sd.getWeiId());
				iDemand.setMaxAgentReward(sd.getMaxAgentReward());
				iDemand.setMinAgentReward(sd.getMinAgentReward());
				
				idemandList.add(iDemand);
			}
			idPageResult.setList(idemandList);
			
			result.setBasemodle(idPageResult);					

			result.setStatusreson("参数错误！");			
		}
		result.setStatu(ReturnStatus.Success);
		result.setStatusreson("成功");
		return JsonStr(result);
	}
	
	/***
	 * 获取招商列表  Y03
	 * @param tiket
	 * @param industryID
	 * @param provice
	 * @param city
	 * @param productSize 商品数量
	 * @param keyword  关键字 标题
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getUserDemand", method = { RequestMethod.POST, RequestMethod.GET })
	public String getUserDemand(String tiket,Integer industryID,Integer provice,Integer city, Integer productSize, String keyword ,Integer pageIndex,Integer pageSize){
		
		ReturnModel result = new ReturnModel();
		if(productSize == null || productSize <1){
			productSize =3;
		}		
		if(pageIndex ==null || pageIndex <1){
			pageIndex =1;
		}
		if(pageSize ==null || pageSize <1){
			pageSize =3;
		}
		LoginUser user = super.getUserOrSub();
		if(user==null){
			result.setStatu(ReturnStatus.LoginError);
			result.setStatusreson("登陆已过期，请重新登陆");
			return JsonStr(result);
		}
		
		PageResult<SupplyDemandVO> demandVOs = service.getUserDemandVos(user,null, industryID,provice,city,
				keyword, productSize,4, Limit.buildLimit(pageIndex, pageSize));
		if(demandVOs !=null && demandVOs.getList() !=null && demandVOs.getList().size() >0){
			PageResult<InvestmentDemand> idPageResult = new PageResult<InvestmentDemand>();
			idPageResult.setPageCount(demandVOs.getPageCount());
			idPageResult.setPageIndex(demandVOs.getPageIndex());
			idPageResult.setPageSize(demandVOs.getPageSize());
			idPageResult.setTotalCount(demandVOs.getTotalCount());
			
			List<InvestmentDemand> idemandList = new ArrayList<InvestmentDemand>();
			for (SupplyDemandVO sd : demandVOs.getList()) {
				InvestmentDemand iDemand = new InvestmentDemand();
				iDemand.setAgentCount(sd.getAgentCount());
				iDemand.setApplyTime(sd.getCreateTime());
				iDemand.setDemandId(sd.getDemandId());
				iDemand.setDemandName(sd.getTitle());
				iDemand.setImg(sd.getMainImage());
				iDemand.setStatus(sd.getState());
				iDemand.setStoreCount(sd.getShopCount());
				iDemand.setTotalProduct(sd.getpCount());
				iDemand.setAuditTime(sd.getAuditTime());
				iDemand.setAreaStr(sd.getAreaString());
				iDemand.setMerchantName(sd.getWeiName());
				iDemand.setMerchantWeiId(sd.getWeiId());
				
				List<DemandProduct> dproductList = new ArrayList<DemandProduct>();
				if(sd.getdProductVOs() !=null && sd.getdProductVOs().size()>0){
					for (DemandProductVO dp : sd.getdProductVOs()) {
						DemandProduct product = new DemandProduct();
						product.setProductId(dp.getProductID());
						product.setProductName(dp.getProdcutTitle());
						product.setProductPicture(dp.getProductImg());
						
						dproductList.add(product);
					}					
				}
				iDemand.setProducts(dproductList);
				idemandList.add(iDemand);
			}
			idPageResult.setList(idemandList);
			
			result.setBasemodle(idPageResult);	
		}
		
		result.setStatusreson("成功");
		result.setStatu(ReturnStatus.Success);
		return JsonStr(result);		
	}
	
	
	/** 
	 * 获取招商详情   DP05
	 * @param demandID
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getDemandInfo", method = { RequestMethod.POST, RequestMethod.GET })
	public String getDemandInfo(String tiket,Integer demandId){
		
		ReturnModel result = new ReturnModel();
		result.setStatu(ReturnStatus.ParamError);
		result.setStatusreson("参数错误！");
		LoginUser user = super.getUserOrSub();
		if(user==null){
			result.setStatu(ReturnStatus.LoginError);
			result.setStatusreson("登陆已过期，请重新登陆");
			return JsonStr(result);
		}
		if(demandId == null || demandId <1){
			return JsonUtil.objectToJson(result);
		}
		
		SupplyDemandVO demandVO = service.getSupplyDemandVO(demandId);
		if(demandVO ==null){
			return JsonUtil.objectToJson(result);
		}
		
		InvestmentDemand demand = new InvestmentDemand();
		demand.setMerchantWeiId(demandVO.getWeiId());
		demand.setMerchantName(demandVO.getWeiName());
		demand.setDemandId(demandVO.getDemandId());
		demand.setDemandName(demandVO.getTitle());
		demand.setTotalProduct(demandVO.getpCount());
		demand.setImg(demandVO.getMainImage());
		demand.setAgentCount(demandVO.getAgentCount());
		demand.setStoreCount(demandVO.getShopCount());
		demand.setDetails(demandVO.getAppDetails());
		demand.setAreaStr(demandVO.getAreaString());
		demand.setZhuying(demandVO.getSaleType());
		//主营 
		demand.setIndustry(demandVO.getBusCategoryStr());
		//行业
		if(demandVO.getdRequiredVOs() !=null && demandVO.getdRequiredVOs().size() >0){
			List<RequiredInfo> areaList = new ArrayList<RequiredInfo>();
			for (DemandRequiredVO requiredVO : demandVO.getdRequiredVOs() ) {
				RequiredInfo info = new RequiredInfo();
				//招商区域说明
				String areaIntro = "";
				if(requiredVO.getRegionVOs() !=null && requiredVO.getRegionVOs().size() >0 ){
					for (RegionVO region : requiredVO.getRegionVOs()) {
						areaIntro += region.getCodeName() + " "; 
					}
				}
				info.setAreaIntro(areaIntro);
				info.setCondition(requiredVO.getAgentRequired());
				info.setFile(requiredVO.getContract());
				info.setMinOrderTrans(requiredVO.getShopReward());
				info.setZhengce(requiredVO.getSupport());
				//量化要求				
				if(requiredVO.getRequiredKVVOs() !=null && requiredVO.getRequiredKVVOs().size() >0){
					List<RequiredKV> requestList = new ArrayList<RequiredKV>();
					for (RequiredKVVO kvvo : requiredVO.getRequiredKVVOs()) {
						RequiredKV kv = new RequiredKV();
						kv.setKey(kvvo.getRkey());
						kv.setValue(kvvo.getRvalue());
						requestList.add(kv);
					}
					info.setRequestList(requestList);
				} 
				
				//地区
				info.setAreas(RegionVOsToAreaTrees(requiredVO.getRegionVOs()));				
				areaList.add(info);
			}
			demand.setAreaList(areaList);			
		}
		result.setStatu(ReturnStatus.Success);
		result.setStatusreson("成功");
		result.setBasemodle(demand);
				
		return JsonStr(result);
		
	}
	
	/** 
	 * 获取招商标题列表   CH21
	 * @param demandID
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getDemandTitletList", method = { RequestMethod.POST, RequestMethod.GET })
	public String getDemandTitletList(String tiket, Integer pageIndex,Integer pageSize){
		ReturnModel result = new ReturnModel();
		
		if(pageIndex ==null || pageIndex <1){
			pageIndex =1;
		}
		if(pageSize ==null || pageSize <1){
			pageSize =3;
		}
		LoginUser user = super.getUserOrSub();
		if(user==null){
			result.setStatu(ReturnStatus.LoginError);
			result.setStatusreson("登陆已过期，请重新登陆");
			return JsonStr(result);
		}
		
		PageResult<SupplyDemandVO> demandVOs = service.getDemandTitleList(user.getWeiID(),Limit.buildLimit(pageIndex, pageSize));
		if(demandVOs ==null || demandVOs.getList() ==null && demandVOs.getList().size() <1){
			return null;
		}
		
		PageResult<DemandChannelVO> pageResult = new PageResult<DemandChannelVO>();
		List<DemandChannelVO> titleVOs = new ArrayList<DemandChannelVO>();
		for (SupplyDemandVO demandVO : demandVOs.getList()) {
			DemandChannelVO titleVO = new DemandChannelVO();
			titleVO.setDemandID(demandVO.getDemandId());
			titleVO.setTitle(demandVO.getTitle());
			titleVOs.add(titleVO);
		}
		pageResult.setList(titleVOs);
		pageResult.setPageCount(demandVOs.getPageCount());
		pageResult.setPageIndex(demandVOs.getPageIndex());
		pageResult.setPageSize(demandVOs.getPageSize());
		pageResult.setTotalCount(demandVOs.getTotalCount());
		
		result.setStatu(ReturnStatus.Success);
		result.setStatusreson("成功");
		result.setBasemodle(pageResult);
		
		return JsonStr(result);
	}
	
	/**
	 * 搜索供应商标题 统计数量 CH26
	 * @param tiket
	 * @param keyword
	 * @param keyType
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getSearcDemandTitlet", method = { RequestMethod.POST, RequestMethod.GET })
	public String getSearcDemandTitlet(String tiket,String keyword,Short keyType){
		ReturnModel result = new ReturnModel();
		LoginUser user = super.getUserOrSub();
		if(user==null){
			result.setStatu(ReturnStatus.LoginError);
			result.setStatusreson("登陆已过期，请重新登陆");
			return JsonStr(result);
		}
		SupplyChannelTypeEnum channelType;
		if(keyType ==null || keyType !=Short.parseShort(SupplyChannelTypeEnum.ground.toString())){
			channelType = SupplyChannelTypeEnum.Agent;
		}else{
			channelType = SupplyChannelTypeEnum.ground;
		}
		
		List<DemandChannelVO> channelVOs = service.getSearchChannelCount(user.getWeiID(), keyword, channelType);
		
		result.setBasemodle(channelVOs);
		result.setStatusreson("成功");
		result.setStatu(ReturnStatus.Success);
		
		return JsonStr(result);			
	}
	
	/**
	 * 搜索渠道商信息 CH27
	 * @param tiket
	 * @param demandID
	 * @param keyword
	 * @param keyType
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getSearcDemandChannel", method = { RequestMethod.POST, RequestMethod.GET })
	public String getSearcDemandChannel(String tiket,Integer demandID, String keyword,Short keyType
			,Integer code,Integer pageIndex,Integer pageSize){
		ReturnModel result = new ReturnModel();
		if(demandID ==null || demandID <1){
			result.setStatu(ReturnStatus.ParamError);
			return JsonStr(result);
		}
		if(pageIndex ==null || pageIndex <1){
			pageIndex =1;
		}
		if(pageSize ==null || pageSize <1){
			pageSize =3;
		}
		LoginUser user = super.getUserOrSub();
		if(user==null){
			result.setStatu(ReturnStatus.LoginError);
			result.setStatusreson("登陆已过期，请重新登陆");
			return JsonStr(result);
		}
		SupplyChannelTypeEnum channelType;
		if(keyType ==null || keyType !=Short.parseShort(SupplyChannelTypeEnum.ground.toString())){
			channelType = SupplyChannelTypeEnum.Agent;
		}else{
			channelType = SupplyChannelTypeEnum.ground;
		}
		
		PageResult<ChannelInfoVO> channelInfoVOs = service.getSearchChannel(user.getWeiID(), demandID, keyword,
				channelType, code, Limit.buildLimit(pageIndex, pageSize));
				
		result.setBasemodle(channelInfoVOs);
		result.setStatusreson("成功");
		result.setStatu(ReturnStatus.Success);
		
		return JsonStr(result);
	}
	
	/**
	 * 获取招商需求的地区渠道分布统计 CH28
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getDemandRegionChannel", method = { RequestMethod.POST, RequestMethod.GET })
	public String getDemandRegionChannel(String tiket,Integer demandID,Integer code){
		ReturnModel result = new ReturnModel();
		if(demandID ==null || demandID <1){
			result.setStatu(ReturnStatus.ParamError);
			return JsonStr(result);
		}		
		
		LoginUser user = super.getUserOrSub();
		if(user==null){
			result.setStatu(ReturnStatus.LoginError);
			result.setStatusreson("登陆已过期，请重新登陆");
			return JsonStr(result);
		}
		List<ChannelRegionVO> regVos = service.getChannelRegions(user.getWeiID(), demandID, code,null);
		if(regVos !=null && regVos.size() >0){
			RegionChannel regChannel = new RegionChannel();
			
			int agentTotalCount =0;
			int shopTotalCount=0;
			List<RegionChannel> regList = new ArrayList<RegionChannel>();
			for (ChannelRegionVO regionVO : regVos) {
				RegionChannel item = new RegionChannel();
				item.setAgentCount(regionVO.getAgentCount());
				item.setCode(regionVO.getCode());
				item.setCodeName(regionVO.getCodeName());
				item.setShopCount(regionVO.getShopCount());
				regList.add(item);
				
				agentTotalCount += regionVO.getAgentCount();
				shopTotalCount += regionVO.getShopCount();
			}
			regChannel.setAgentCount(agentTotalCount);
			regChannel.setShopCount(shopTotalCount);
			regChannel.setRegList(regList);
				
			result.setBasemodle(regChannel);
		}
		
		result.setStatusreson("成功");
		result.setStatu(ReturnStatus.Success);
		return JsonStr(result);
	}
	
	
	/***
	 * 修改需求状态 CH01-1
	 * @param tiket
	 * @param demandId
	 * @param status
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/editDemandState", method = { RequestMethod.POST, RequestMethod.GET })
	public String editDemandState(String tiket,Integer demandId,Short status){
		ReturnModel result = new ReturnModel();
		if(demandId ==null || demandId <1){
			result.setStatu(ReturnStatus.ParamError);
			return JsonStr(result);
		}	
		if(status != Short.parseShort(DemandStateEnum.Showing.toString()) &&
				status != Short.parseShort(DemandStateEnum.OffShelf.toString()) &&
				status != Short.parseShort(DemandStateEnum.delete.toString())){
			result.setStatu(ReturnStatus.ParamError);
			return JsonStr(result);
		}
		
		LoginUser user = super.getUserOrSub();
		if(user==null){
			result.setStatu(ReturnStatus.LoginError);
			result.setStatusreson("登陆已过期，请重新登陆");
			return JsonStr(result);
		}
		Integer[] demandIDs = new Integer[]{demandId};
		DemandStateEnum state =DemandStateEnum.values()[status];
		ResultMsg resultMsg = service.editDemandState(demandIDs, user.getWeiID(), state);
		if(resultMsg.getStatus() ==1){
			result.setStatu(ReturnStatus.Success);
			result.setStatusreson("成功");
			return JsonStr(result);
		}
		else{
			result.setStatu(ReturnStatus.SystemError);
			result.setStatusreson(resultMsg.getMsg());
			return JsonStr(result);
		}
	}
	
	
	
	private List<AreaTree> RegionVOsToAreaTrees(List<ChannelRegionVO> regionVOs){
		if(regionVOs ==null || regionVOs.size() <1){
			return null;
		}
		List<AreaTree> proviceTrees = new ArrayList<AreaTree>();
		//总共有多少省
		List<Integer> provices = new ArrayList<Integer>();
		for (RegionVO regprovice : regionVOs) {
			//省去重
			if(provices.contains(regprovice.getProvice()))
			{
				break;
			}	
			provices.add(regprovice.getProvice());
			AreaTree provicetree = new AreaTree();
			provicetree.setAreaId(regprovice.getProvice());
			//全部的市
			List<AreaTree> cityTrees =new ArrayList<AreaTree>();
			List<Integer> citys = new ArrayList<Integer>();
			for (RegionVO regcity : regionVOs){
				//市去重
				if(citys.contains(regcity.getCity()) || !regcity.getProvice().equals(regprovice.getProvice())){
					break;
				}
				citys.add(regcity.getCity());
				AreaTree cityTree = new AreaTree();
				cityTree.setAreaId(regcity.getCity());
				
				cityTrees.add(cityTree);
			} 
			provicetree.setAreas(cityTrees);			
			proviceTrees.add(provicetree);
		}
				
		return proviceTrees;		
	}
	
}
