package com.okwei.service.impl.user;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.okwei.bean.domain.UAgenArea;
import com.okwei.bean.domain.UAgentApply;
import com.okwei.bean.domain.UAgentApplyArea;
import com.okwei.bean.domain.UAgentApplyFollowLog;
import com.okwei.bean.domain.UBatchVerifierRegion;
import com.okwei.bean.domain.UBrandSupplyer;
import com.okwei.bean.domain.UPlatformSupplyer;
import com.okwei.bean.domain.UProductAgent;
import com.okwei.bean.domain.UProductShop;
import com.okwei.bean.domain.USupplyChannel;
import com.okwei.bean.domain.USupplyDemand;
import com.okwei.bean.domain.UUserAssist;
import com.okwei.bean.domain.UVerifier;
import com.okwei.bean.domain.UWeiSeller;
import com.okwei.bean.domain.UYunVerifier;
import com.okwei.bean.dto.MyAgentOrProductShopListDTO;
import com.okwei.bean.enums.AgentStatusEnum;
import com.okwei.bean.enums.SupplyChannelTypeEnum;
import com.okwei.bean.vo.AreaShowVO;
import com.okwei.bean.vo.DemandShowVO;
import com.okwei.bean.vo.MyAgentOrProductShopListVO;
import com.okwei.bean.vo.PlatformProductShopDetailVO;
import com.okwei.bean.vo.PlatformProductShopListPCVO;
import com.okwei.bean.vo.PlatformProductShopListVO;
import com.okwei.bean.vo.ProductAgentDataVO;
import com.okwei.bean.vo.ProductShopDataVO;
import com.okwei.bean.vo.ReturnModel;
import com.okwei.bean.vo.ReturnStatus;
import com.okwei.bean.vo.user.LandShopListVO;
import com.okwei.bean.vo.user.VerifierRegion;
import com.okwei.common.Limit;
import com.okwei.common.PageResult;
import com.okwei.dao.IBaseDAO;
import com.okwei.dao.user.IBasicAgentOrProductShopDAO;
import com.okwei.service.IRegionService;
import com.okwei.service.impl.BaseService;
import com.okwei.service.product.IBasicProductService;
import com.okwei.service.user.IBasicAgentOrProductShopService;
import com.okwei.util.BitOperation;
import com.okwei.util.DateUtils;
import com.okwei.util.ImgDomain;
@Service
public class BasicAgentOrProductShopService extends BaseService implements IBasicAgentOrProductShopService {
	@Autowired
	private IBasicAgentOrProductShopDAO iBasicAgentOrProductShopDAO;
	@Autowired
	private IBaseDAO baseDAO;
	@Autowired
	private IRegionService iRegionService;
	
	@Autowired
	private IBasicProductService basicProductService;
	
	@Override
	public PageResult<MyAgentOrProductShopListVO> getMyDevelopAgent(
			Limit limit, MyAgentOrProductShopListDTO queryParam) {
		List<MyAgentOrProductShopListVO> voList = new ArrayList<MyAgentOrProductShopListVO>();
		List<Object[]> list = iBasicAgentOrProductShopDAO.getMyDevelopAgent(limit, queryParam);
		//处理voList
		processVoList(list,voList,queryParam.getVerifierWeiId());
		//总记录数
		int totalCount = (int) iBasicAgentOrProductShopDAO.getMyDevelopAgentTotalAmount(queryParam);
		// 返回page
		PageResult<MyAgentOrProductShopListVO> page = new PageResult<MyAgentOrProductShopListVO>();
		// list内容
		page.setList(voList);
		// 总共有多少页
		int totalPage = (totalCount / limit.getSize())
				+ (totalCount % limit.getSize() > 0 ? 1 : 0);
		page.setTotalCount(totalCount);
		// 1页多少条
		page.setPageSize(limit.getSize());
		// 总共有多少页
		page.setPageCount((int) totalPage);
		// 当前页
		page.setPageIndex(limit.getPageId());
		return page;
	}
	
	public void processVoList(List<Object[]> list,List<MyAgentOrProductShopListVO> voList,Long pverifier){
		List<Long> supplyWeiIdList = new ArrayList<Long>();
		List<Long> weiIdList = new ArrayList<Long>();
		List<Integer> demandIdList = new ArrayList<Integer>();
		List<Integer> parIdList = new ArrayList<Integer>();
		List<Long> parIdLongList = new ArrayList<Long>();
		if(list!=null&&list.size()>0){
			for(int i=0;i<list.size();i++){
				MyAgentOrProductShopListVO vo = new MyAgentOrProductShopListVO();
				Object[] obj = list.get(i);
				//状态
				vo.setStatus(obj[0]!=null?Short.valueOf(obj[0].toString()):-11);
				//申请时间
				vo.setApplyTime(DateUtils.formatDateTime((Date)(obj[1]!=null?obj[1]:new Date())));
				//悬赏金额
				vo.setRewardAmount(obj[2]!=null?(Double)obj[2]:0);
				//是否发放了悬赏
				vo.setIsPayReward(obj[3]!=null?Short.valueOf(obj[3].toString()):-11);
				if(Short.valueOf(obj[4]!=null?obj[4].toString():"-11")==1){ //1表示平台号
					vo.setPlatformOrBrandName((obj[5]!=null?String.valueOf(obj[5]):""));
					vo.setSupplierIdentity("平台号");
					vo.setMerchantWeiName((obj[5]!=null?String.valueOf(obj[5]):""));
					vo.setMerchantIdentity(1);
				}else{  //否则是品牌号
					vo.setPlatformOrBrandName((obj[6]!=null?String.valueOf(obj[6]):""));
					vo.setSupplierIdentity("品牌号");
					vo.setMerchantWeiName((obj[6]!=null?String.valueOf(obj[6]):""));
					vo.setMerchantIdentity(2);
				}
				//发展人微店号
				vo.setDevelopmentWeiId(obj[7]!=null?Long.valueOf(String.valueOf(obj[7])):-11);
				//代理商所在地区
				Integer provinceCode = obj[8]!=null?Integer.parseInt(obj[8].toString()):-11;
				Integer cityCode = obj[9]!=null?Integer.parseInt(obj[9].toString()):-11;
				Integer districtCode = obj[10]!=null?Integer.parseInt(obj[10].toString()):-11;
				//省
				String province= iRegionService.getNameByCode(provinceCode);
				//市
				String city= iRegionService.getNameByCode(cityCode);
				//区
				String district= iRegionService.getNameByCode(districtCode);
				
				String region = "";
				if(province!=null&&!province.equals("")){
					region+=province+"-";
				}
				if(city!=null&&!city.equals("")){
					region+=city+"-";
				}
				if(district!=null&&!district.equals("")){
					region+=district;
				}
				if(region.endsWith("-")){
					region=region.substring(0,region.length()-1);
				}
				vo.setLocationStr(region);
				
				//省
				AreaShowVO location = new AreaShowVO();
				location.setAreaId(provinceCode.toString());
				location.setAreaName(province);
				//市
				AreaShowVO area = new AreaShowVO();
				area.setAreaId(cityCode.toString());
				area.setAreaName(city);
				location.setArea(area);
				//区
				AreaShowVO areaDistrict = new AreaShowVO();
				areaDistrict.setAreaId(districtCode.toString());
				areaDistrict.setAreaName(district);
				area.setArea(areaDistrict);
				vo.setLocation(location);
				//详细地址
				vo.setAddress(obj[11]!=null?obj[11].toString():"");
				//微店号
				Long weiId=obj[12]!=null?Long.valueOf(String.valueOf(obj[12])):-11;
				vo.setWeiId(weiId);
				weiIdList.add(weiId);
				//联系人
				vo.setLinkname(obj[13]!=null?obj[13].toString():"");
				//电话号码
				vo.setPhone(obj[14]!=null?obj[14].toString():"");
				//供应商微店号
				Long supplyWeiId = obj[15]!=null?Long.valueOf(String.valueOf(obj[15])):-11;
				vo.setMerchantWeiId(supplyWeiId);
				supplyWeiIdList.add(supplyWeiId);
				//代理需求
				Integer demandID = obj[16]!=null?Integer.valueOf(String.valueOf(obj[16])):-11;
				demandIdList.add(demandID);
				vo.setDemandIdTemp(demandID);
				//Integer[] demandIdArr = new Integer[]{demandID};
				vo.setDemandId(demandID);
				/**
				 * 赋默认值
				 */
				DemandShowVO demands = new DemandShowVO();
				//获得招商需求id
				demands.setInvestmentDemandId(demandID);
				//获得招商需求名
				demands.setInvestmentDemandName("");
				demands.setTotalProduct(0);
				vo.setDemands(demands);
				//代理商的id
				Integer parID = obj[17]!=null?Integer.valueOf(String.valueOf(obj[17])):-11;
				parIdList.add(parID);
				vo.setAgentId(Long.parseLong(parID.toString()));
				parIdLongList.add((long)parID);
				//代理商的落地店数量
				vo.setTotalStore(obj[18]!=null?Integer.valueOf(String.valueOf(obj[18])):0);
				//理由
				vo.setRemarks((obj[19]!=null?String.valueOf(obj[19]):""));
				//审核时间
				vo.setAuditTime(obj[20]!=null?DateUtils.formatDateTime((Date)obj[20]):"");
				//申请ID
				vo.setApplyID(obj[21]!=null?Integer.valueOf(String.valueOf(obj[21])):0);
				//营业执照
				vo.setImgs((obj[22]!=null?ImgDomain.GetFullImgUrl(String.valueOf(obj[22])):""));
				//营业执照大图
				vo.setImgsBig((obj[22]!=null?ImgDomain.GetFullImgUrl(String.valueOf(obj[22]),75):""));
				//我的优势
				vo.setIntro((obj[23]!=null?String.valueOf(obj[23]):""));
				//判断是否是跟进身份
				Long followVerifier = obj[24]!=null?Long.valueOf(String.valueOf(obj[24])):0;
				Long verifier =obj[7]!=null?Long.valueOf(String.valueOf(obj[7])):0;
				if(verifier.equals(pverifier))
					vo.setIsFollowVerifier((short) 0);
				
				if(pverifier.equals(followVerifier))
					vo.setIsFollowVerifier((short) 1);
				
				voList.add(vo);
			}
		}
		List<UWeiSeller> weiSellerList = null;
		if(weiIdList!=null&&weiIdList.size()>0){
			weiSellerList = iBasicAgentOrProductShopDAO.getUWeiSellerList(weiIdList);
		}
		List<UWeiSeller> supplyWeisellList = null;
		if(supplyWeiIdList!=null&&supplyWeiIdList.size()>0){
			supplyWeisellList = iBasicAgentOrProductShopDAO.getUWeiSellerList(supplyWeiIdList);
		}
		List<USupplyDemand> supplyDemandList = null;
		List<Object[]> demandProductAmount = null;
		if(demandIdList!=null&&demandIdList.size()>0){
			supplyDemandList = iBasicAgentOrProductShopDAO.getUSupplyDemandList(demandIdList);
			demandProductAmount = iBasicAgentOrProductShopDAO.getUDemandProductAmount(demandIdList);
		}
		List<UAgenArea> agentAreaList = null;  //代理商的代理区域列表
		//List<Object[]> shopAmountList = null;  //代理商的落地店数量列表
		if(parIdList!=null&&parIdList.size()>0){
			agentAreaList = iBasicAgentOrProductShopDAO.getUAgentAreaList(parIdList);
			//shopAmountList = iBasicAgentOrProductShopDAO.getProductShopAmountByAgentId(parIdLongList);
		}
		/**
		 * 通过循环来组合数据，可以避免在循环中访问数据库
		 */
		if(voList!=null&&voList.size()>0){
			for(MyAgentOrProductShopListVO vo :voList){
				/**
				 * 获得微店图片和店铺名
				 */
				if(weiSellerList!=null&&weiSellerList.size()>0){
					for(UWeiSeller weiseller:weiSellerList){
						if(vo.getWeiId().equals(weiseller.getWeiId())){
							//微店图片
							vo.setWeiPicture(ImgDomain.GetFullImgUrl(weiseller.getImages()!=null?weiseller.getImages():""));
							//店铺名
							vo.setShopName(weiseller.getWeiName()!=null?weiseller.getWeiName():"");
							break;
						}
					}
				}
				/**
				 * 获得供应商微店名
				 */
				if(supplyWeisellList!=null&&supplyWeisellList.size()>0){
					for(UWeiSeller supplyWei:supplyWeisellList){
						if(vo.getMerchantWeiId().equals(supplyWei.getWeiId())){
							vo.setSupplierName(supplyWei.getWeiName()!=null?supplyWei.getWeiName():"");
							break;
						}
					}
				}
				/**
				 * 获得代理需求的数据
				 */
				if(supplyDemandList!=null&&supplyDemandList.size()>0){
					for(USupplyDemand dem:supplyDemandList){
						if(dem.getDemandId().equals(vo.getDemandIdTemp())){
							DemandShowVO demands = new DemandShowVO();
							//获得招商需求id
							demands.setInvestmentDemandId(dem.getDemandId());
							//获得招商需求名
							demands.setInvestmentDemandName(dem.getTitle()!=null?dem.getTitle():"");
							vo.setDemands(demands);
							vo.setDemandName(dem.getTitle()!=null?dem.getTitle():"");
							
							break;
						}
					}
				}
				if(demandProductAmount!=null&&demandProductAmount.size()>0){
					for(Object[] amount:demandProductAmount){
						//招商需求的商品数
						if(amount[1].equals(vo.getDemandIdTemp())){
							vo.getDemands().setTotalProduct(Integer.valueOf(amount[0].toString()));
							vo.setDemandProductCount(Integer.valueOf(amount[0].toString()));
						}
					}
				}
				/**
				 * 获得每一个代理商的代理区域
				 */
				List<AreaShowVO> agentAreas = new ArrayList<AreaShowVO>();
				if(agentAreaList!=null&&agentAreaList.size()>0){
					for(UAgenArea tempArea:agentAreaList){
						Integer parid = tempArea.getChannelId()!=null?tempArea.getChannelId():-11;
						if(parid.equals(Integer.parseInt(vo.getAgentId().toString()))){
							Integer provinceCode = tempArea.getProvice()!=null?tempArea.getProvice():-11;
							Integer cityCode = tempArea.getCity()!=null?tempArea.getCity():-11;
							Integer districtCode = tempArea.getArea()!=null?tempArea.getArea():-11;
							//省
							String province= iRegionService.getNameByCode(provinceCode);
							//市
							String city= iRegionService.getNameByCode(cityCode);
							//区
							String district= iRegionService.getNameByCode(districtCode);
							//省
							AreaShowVO asvo = new AreaShowVO();
							asvo.setAreaId(provinceCode.toString());
							asvo.setAreaName(province);
							//市
							AreaShowVO area = new AreaShowVO();
							area.setAreaId(cityCode.toString());
							area.setAreaName(city);
							asvo.setArea(area);
							//区
							AreaShowVO areaDistrict = new AreaShowVO();
							areaDistrict.setAreaId(districtCode.toString());
							areaDistrict.setAreaName(district);
							area.setArea(areaDistrict);
							agentAreas.add(asvo);
							String areaStr = "";
							if(province!=null&&!province.equals("")){
								areaStr+=province+"-";
							}
							if(city!=null&&!city.equals("")){
								areaStr+=city+"-";
							}
							if(district!=null&&!district.equals("")){
								areaStr+=district;
							}
							if(areaStr.endsWith("-")){
								areaStr=areaStr.substring(0,areaStr.length()-1);
							}
							vo.setAreaStr(areaStr);
						}
					}
					//每一次外层循环，都给一个代理商的代理区域集合赋值
					vo.setAgentAreas(agentAreas);
				}
				/**
				 * 获得代理商的落地店数量
				 */
//				if(shopAmountList!=null&&shopAmountList.size()>0){
//					for(Object[] amount:shopAmountList){
//						if(amount[1].equals(vo.getAgentId())){
//							vo.setTotalStore(Integer.valueOf(amount[0].toString()));
//						}
//					}
//				}
			}
		}
	}
	
	@Override
	public PageResult<PlatformProductShopListPCVO> getMyDevelopProductShop(
			Limit limit, MyAgentOrProductShopListDTO queryParam) {
		
		return (PageResult<PlatformProductShopListPCVO>)commonBasicGetProductShopListTwo(queryParam.getVerifierWeiId(), (short)-1,limit, 2,queryParam.getBeginTime(),queryParam.getEndTime(),2,queryParam.getApplyPersonWeiId());
	}
	@Override
	public ProductShopDataVO getProductShopDataVO(Integer shopId) {
		ProductShopDataVO vo = new ProductShopDataVO();
		UProductShop productShop = baseDAO.get(UProductShop.class,shopId);
		if(productShop!=null){
			//详细地址
			vo.setAddress(productShop.getAddress()!=null?productShop.getAddress():"");
			//省
			vo.setProvince(iRegionService.getNameByCode(productShop.getProvince()!=null?productShop.getProvince():-11));
			//市
			vo.setCity(iRegionService.getNameByCode(productShop.getCity()!=null?productShop.getCity():-11));
			//区
			vo.setArea(iRegionService.getNameByCode(productShop.getArea()!=null?productShop.getArea():-11));
//			UWeiSeller weiSeller = baseDAO.get(UWeiSeller.class,productShop.getWeiId());
//			if (weiSeller!=null) {
//				//电话号码
//				vo.setMobilePhone(weiSeller.getMobilePhone()!=null?weiSeller.getMobilePhone():"");
//			}
			vo.setMobilePhone(productShop.getPhone());
			vo.setName(productShop.getLinkMan());
			vo.setShopName(productShop.getWeiName());
			
		}
		return vo;
	}
	@Override
	public ProductAgentDataVO getProductAgentDataVO(Integer parId,int pcOrApp) {
		ProductAgentDataVO vo = new ProductAgentDataVO();
		//pc需要单独执行的部分
		if(pcOrApp==1){
			UAgentApply productAgent = baseDAO.get(UAgentApply.class,parId);
			if (productAgent!=null) {
				//优势
				vo.setAdvantage(productAgent.getDetails()!=null?productAgent.getDetails():"");
				//营业执照图片
				vo.setLicenseImg(ImgDomain.GetFullImgUrl(productAgent.getLicenseImg()!=null?productAgent.getLicenseImg():""));
				//联系人
				vo.setLinkMan(productAgent.getLinkMan()!=null?productAgent.getLinkMan():"");
				//电话号码
				vo.setPhone(productAgent.getPhone()!=null?productAgent.getPhone():"");
				//地址
				vo.setAddress(productAgent.getAddress()!=null?productAgent.getAddress():"");
				//代理地区
				String agentArea="";
				List<UAgentApplyArea> agenAreaList = iBasicAgentOrProductShopDAO.getUAgentApplyAreaList(parId);
				if(agenAreaList!=null&&agenAreaList.size()>0){
					for(int i=0;i<agenAreaList.size();i++){
						UAgentApplyArea obj = agenAreaList.get(i);
						if(obj!=null){
							Integer code = obj.getCode()!=null?obj.getCode():-1;
							agentArea+=iRegionService.getNameByCode(code)+",";
						}
					}
					if(agentArea.endsWith(",")){
						agentArea = agentArea.substring(0,agentArea.length()-1);
					}
				}
				vo.setAgentArea(agentArea);
			} 
		}
		//跟进记录
		List<UAgentApplyFollowLog> agentApplyFollowLogList = iBasicAgentOrProductShopDAO.getUAgentApplyFollowLogList(parId);
		vo.setFollowList(agentApplyFollowLogList);
		return vo;
	}
	@Override
	public ReturnModel followAgent(Integer parId, String remark, Long weiId) {
		ReturnModel model = new ReturnModel();
		UAgentApplyFollowLog obj = new UAgentApplyFollowLog();
		obj.setCreateTime(new Date());
		obj.setRemaks(remark==null?"":remark);
		obj.setWeiId(weiId);
		obj.setApplyId(parId);
		baseDAO.save(obj);
		model.setStatu(ReturnStatus.Success);
		model.setStatusreson("成功");
		return model;
	}
	/**
	 * 我发展的落地店列表app
	 */
	@Override
	public PageResult<PlatformProductShopListVO> getProductShopList(
			Long weiIdCondition, Short state, Limit limit,int platformOrVerifier) {
		return (PageResult<PlatformProductShopListVO>)commonBasicGetProductShopList(weiIdCondition,state,limit,platformOrVerifier,null,null,1);
		
	}
	
	/**
	 * 我发展的落地店列表PC
	 */
	@Override
	public PageResult<PlatformProductShopListPCVO> getProductShopPCList(
			Long weiId, Short state, Limit limit, int platformOrVerifier) {
		return (PageResult<PlatformProductShopListPCVO>)commonBasicGetProductShopList(weiId,state,limit,platformOrVerifier,null,null,2);
	}
	/**
	 * pc app 公用方法 我发展的落地店 type 1 app type 2 pc
	 */
	
	private Object commonBasicGetProductShopList(Long weiIdCondition, Short state, Limit limit,int platformOrVerifier,String startTime,String endTime,int type)
	{
		return commonBasicGetProductShopListTwo(weiIdCondition,state,limit,platformOrVerifier,startTime,endTime,type,null);
	}
	
	private Object commonBasicGetProductShopListTwo(Long weiIdCondition, Short state, Limit limit,int platformOrVerifier,String startTime,String endTime,int type,Long searchWeiId)
	{
		List<PlatformProductShopListVO> voList = new ArrayList<PlatformProductShopListVO>();
		List<PlatformProductShopListPCVO> voPCList = new ArrayList<PlatformProductShopListPCVO>();	
		int countWei=0;//主要是用来兼用PC端传微店号过来，筛选用。
		/**
		 * 如果platformOrVerifier==1，则为平台号的落地店列表，否则为认证员的落地店列表
		 */
		List<Object[]> list = null;
		if(platformOrVerifier==1){
			list = iBasicAgentOrProductShopDAO.getProductShopByPlatForm(weiIdCondition, state, limit,startTime,endTime);
		}else{
			list = iBasicAgentOrProductShopDAO.getMyDevelopProductShopApp(limit, weiIdCondition, state,startTime,endTime);
		}
		List<Long> weiIdList = new ArrayList<Long>();
		if(list!=null&&list.size()>0){
			for(int i=0;i<list.size();i++){				
				PlatformProductShopListVO vo = new PlatformProductShopListPCVO();
				Object[] obj = list.get(i);
				//微店号				
				Long weiId = obj[0]!=null?Long.valueOf(String.valueOf(obj[0])):-11;
				if(searchWeiId!=null && searchWeiId != weiId)
				{
					countWei++;//后面总数减1
					continue;
				}
				weiIdList.add(weiId);
				vo.setWeiId(weiId);
				Integer provinceCode = obj[1]!=null?Integer.parseInt(obj[1].toString()):-11;
				Integer cityCode = obj[2]!=null?Integer.parseInt(obj[2].toString()):-11;
				Integer districtCode = obj[3]!=null?Integer.parseInt(obj[3].toString()):-11;
				//省
				String province= iRegionService.getNameByCode(provinceCode);
				//市
				String city= iRegionService.getNameByCode(cityCode);
				//区
				String district= iRegionService.getNameByCode(districtCode);
				//这个地区设置是给pc的页面显示用的
				String region = "";
				if(province!=null&&!province.equals("")){
					region+=province+"-";
				}
				if(city!=null&&!city.equals("")){
					region+=city+"-";
				}
				if(district!=null&&!district.equals("")){
					region+=district;
				}
				if(region.endsWith("-")){
					region=region.substring(0,region.length()-1);
				}
				vo.setLocationStr(region);
				//下面的地区设置是给接口调用的
				//省
				AreaShowVO areas = new AreaShowVO();
				areas.setAreaId(provinceCode.toString());
				areas.setAreaName(province);
				//市
				AreaShowVO area = new AreaShowVO();
				area.setAreaId(cityCode.toString());
				area.setAreaName(city);
				areas.setArea(area);
				//区
				AreaShowVO areaDistrict = new AreaShowVO();
				areaDistrict.setAreaId(districtCode.toString());
				areaDistrict.setAreaName(district);
				area.setArea(areaDistrict);
				vo.setAreas(areas);
				//取消原因
				vo.setCancelReason(obj[4]!=null?String.valueOf(obj[4]):"");
				//取消时间
				vo.setCancelTime(obj[15]!=null?(Date)obj[15]:null);
				//申请时间
				vo.setApplyTime(obj[5]!=null?(Date)obj[5]:null);
				//状态
				vo.setStatus(obj[6]!=null?Short.valueOf(String.valueOf(obj[6])):-11);
				//悬赏金额
				vo.setRewardAmount(obj[7]!=null?Long.valueOf(String.valueOf(obj[7])):0);
				//是否支付悬赏
				vo.setIsPayReward(obj[8]!=null?Short.valueOf(String.valueOf(obj[8])):0);
				//发展人微店号
				vo.setDevelopmentWeiId(obj[9]!=null?Long.valueOf(String.valueOf(obj[9])):-11);
				//上级代理商公司名
				String agentCompanyName=obj[10]!=null?String.valueOf(obj[10]):"";
				//上级代理商微店号
				Long parentAgentWeiId=obj[11]!=null?Long.valueOf(String.valueOf(obj[11])):-11;
				vo.setParentAgentWeiId(parentAgentWeiId);
				//平台号公司名
				String supplyName= obj[12]!=null?String.valueOf(obj[12]):"";
				//详细地址
				vo.setAddress(obj[13]!=null?String.valueOf(obj[13]):"");
				//上级供应商微店号
				vo.setMerchantWeiId(obj[14]!=null?Long.valueOf(String.valueOf(obj[14])):-11);
				//如果代理商公司名不为空,则该落地店的上级为代理商公司名，否则为平台号公司名
				if(!agentCompanyName.equals("")){
					vo.setParentAgentName(agentCompanyName);
				}else{
					vo.setParentAgentName(supplyName);
				}
				//落地店的id
				vo.setShopId(obj[16]!=null?Integer.parseInt(obj[16].toString()):0);
				//联系人
				vo.setLinkname(obj[17]!=null?String.valueOf(obj[17]):"");
				if(type==1)
				{
					voList.add(vo);
				}
				else
				{
					PlatformProductShopListPCVO pcvo=(PlatformProductShopListPCVO)vo;
					pcvo.setSupplierName(supplyName);
					pcvo.setDemandName(obj[18]!=null?String.valueOf(obj[18]):"");
					voPCList.add(pcvo);
				}
			}
		}else{
		    	
		}
		
		List<UWeiSeller> weiSellerList = null;
		if(weiIdList!=null&&weiIdList.size()>0){
			weiSellerList = iBasicAgentOrProductShopDAO.getUWeiSellerList(weiIdList);
		}
		/**
		 * 通过循环来组合数据，可以避免在循环中访问数据库
		 */
		if(voList!=null&&voList.size()>0){
			for(PlatformProductShopListVO vo :voList){
				/**
				 * 获得用户信息表中的数据
				 */
				if(weiSellerList!=null&&weiSellerList.size()>0){
					for(UWeiSeller weiseller:weiSellerList){
						if(vo.getWeiId().equals(weiseller.getWeiId())){
							//微店图片
							vo.setWeiPicture(ImgDomain.GetFullImgUrl(weiseller.getImages()!=null?weiseller.getImages():""));
							//店铺名
							vo.setShopName(weiseller.getWeiName()!=null?weiseller.getWeiName():"");
							//联系人姓名
						    //vo.setLinkname(weiseller.getRealName()!=null?weiseller.getRealName():"");
							//手机号
							vo.setPhone(weiseller.getMobilePhone()!=null?weiseller.getMobilePhone():"");
							break;
						}
					}
				}
			}
		}
		/**
		 * 通过循环来组合数据，可以避免在循环中访问数据库
		 */
		if(voPCList!=null&&voPCList.size()>0){
			for(PlatformProductShopListPCVO vo :voPCList){
				/**
				 * 获得用户信息表中的数据
				 */
				if(weiSellerList!=null&&weiSellerList.size()>0){
					for(UWeiSeller weiseller:weiSellerList){
						if(vo.getWeiId().equals(weiseller.getWeiId())){
							//微店图片
							vo.setWeiPicture(ImgDomain.GetFullImgUrl(weiseller.getImages()!=null?weiseller.getImages():""));
							//店铺名
							vo.setShopName(weiseller.getWeiName()!=null?weiseller.getWeiName():"");
							//联系人姓名
						    //vo.setLinkname(weiseller.getRealName()!=null?weiseller.getRealName():"");
							//手机号
							vo.setPhone(weiseller.getMobilePhone()!=null?weiseller.getMobilePhone():"");
							break;
						}
					}
				}
			}
		}
		int totalCount = 0;
		if(platformOrVerifier==1){
			totalCount = (int) iBasicAgentOrProductShopDAO.getProductShopByPlatFormTotalAmount(weiIdCondition, state,startTime,endTime);
		}else{
			totalCount = (int) iBasicAgentOrProductShopDAO.getProductShopByVerifierTotalAmount(weiIdCondition, state,startTime,endTime)-countWei;
		}
		// 返回page
		if(type==1)
		{
			PageResult<PlatformProductShopListVO> page = new PageResult<PlatformProductShopListVO>();
			// list内容
			page.setList(voList);
			// 总共有多少页
			int totalPage = (totalCount / limit.getSize())
					+ (totalCount % limit.getSize() > 0 ? 1 : 0);
			page.setTotalCount(totalCount);
			// 1页多少条
			page.setPageSize(limit.getSize());
			// 总共有多少页
			page.setPageCount((int) totalPage);
			// 当前页
			page.setPageIndex(limit.getPageId());
			return page;
		}
		else
		{
			PageResult<PlatformProductShopListPCVO> page = new PageResult<PlatformProductShopListPCVO>();
			// list内容
			page.setList(voPCList);
			// 总共有多少页
			int totalPage = (totalCount / limit.getSize())
					+ (totalCount % limit.getSize() > 0 ? 1 : 0);
			page.setTotalCount(totalCount);
			// 1页多少条
			page.setPageSize(limit.getSize());
			// 总共有多少页
			page.setPageCount((int) totalPage);
			// 当前页
			page.setPageIndex(limit.getPageId());
			return page;
		}
	}
	
	
	@Override
	public PageResult<PlatformProductShopListVO> getProductShopListByDemandID(
			Long weiIdCondition, Short state, Limit limit,int demandId,int areaId) {
		List<PlatformProductShopListVO> voList = new ArrayList<PlatformProductShopListVO>();
		
		List<Object[]> list = iBasicAgentOrProductShopDAO.getMyDevelopProductShopApp(limit, weiIdCondition, state,demandId,areaId);
		
		List<Long> weiIdList = new ArrayList<Long>();
		if(list!=null&&list.size()>0){
			for(int i=0;i<list.size();i++){
				PlatformProductShopListVO vo = new PlatformProductShopListVO();
				Object[] obj = list.get(i);
				//微店号
				Long weiId = obj[0]!=null?Long.valueOf(String.valueOf(obj[0])):-11;
				weiIdList.add(weiId);
				vo.setWeiId(weiId);
				Integer provinceCode = obj[1]!=null?Integer.parseInt(obj[1].toString()):-11;
				Integer cityCode = obj[2]!=null?Integer.parseInt(obj[2].toString()):-11;
				Integer districtCode = obj[3]!=null?Integer.parseInt(obj[3].toString()):-11;
				//省
				String province= iRegionService.getNameByCode(provinceCode);
				//市
				String city= iRegionService.getNameByCode(cityCode);
				//区
				String district= iRegionService.getNameByCode(districtCode);
				//这个地区设置是给pc的页面显示用的
				String region = "";
				if(province!=null&&!province.equals("")){
					region+=province+"-";
				}
				if(city!=null&&!city.equals("")){
					region+=city+"-";
				}
				if(district!=null&&!district.equals("")){
					region+=district;
				}
				if(region.endsWith("-")){
					region=region.substring(0,region.length()-1);
				}
				vo.setLocationStr(region);
				//下面的地区设置是给接口调用的
				//省
				AreaShowVO areas = new AreaShowVO();
				areas.setAreaId(provinceCode.toString());
				areas.setAreaName(province);
				//市
				AreaShowVO area = new AreaShowVO();
				area.setAreaId(cityCode.toString());
				area.setAreaName(city);
				areas.setArea(area);
				//区
				AreaShowVO areaDistrict = new AreaShowVO();
				areaDistrict.setAreaId(districtCode.toString());
				areaDistrict.setAreaName(district);
				area.setArea(areaDistrict);
				vo.setAreas(areas);
				//取消原因
				vo.setCancelReason(obj[4]!=null?String.valueOf(obj[4]):"");
				//取消时间
				vo.setCancelTime(obj[15]!=null?DateUtils.parseDateTime(obj[15].toString()):null);
				//申请时间
				vo.setApplyTime(obj[5]!=null?DateUtils.parseDateTime(obj[5].toString()):null);
				//状态
				vo.setStatus(obj[6]!=null?Short.valueOf(String.valueOf(obj[6])):-11);
				//悬赏金额
				vo.setRewardAmount(obj[7]!=null?Long.valueOf(String.valueOf(obj[7])):-11);
				//是否支付悬赏
				vo.setIsPayReward(obj[8]!=null?Short.valueOf(String.valueOf(obj[8])):-11);
				//发展人微店号
				vo.setDevelopmentWeiId(obj[9]!=null?Long.valueOf(String.valueOf(obj[9])):-11);
				//上级代理商公司名
				String agentCompanyName=obj[10]!=null?String.valueOf(obj[10]):"";
				//上级代理商微店号
				Long parentAgentWeiId=obj[11]!=null?Long.valueOf(String.valueOf(obj[11])):-11;
				vo.setParentAgentWeiId(parentAgentWeiId);
				//平台号公司名
				String supplyName= obj[12]!=null?String.valueOf(obj[12]):"";
				//详细地址
				vo.setAddress(obj[13]!=null?String.valueOf(obj[13]):"");
				//上级供应商微店号
				vo.setMerchantWeiId(obj[14]!=null?Long.valueOf(String.valueOf(obj[14])):-11);
				//如果代理商公司名不为空,则该落地店的上级为代理商公司名，否则为平台号公司名
				if(!agentCompanyName.equals("")){
					vo.setParentAgentName(agentCompanyName);
				}else{
					vo.setParentAgentName(supplyName);
				}
				//落地店的id
				vo.setShopId(obj[16]!=null?Integer.parseInt(obj[16].toString()):-11);
				voList.add(vo);
			}
		}
		List<UWeiSeller> weiSellerList = null;
		if(weiIdList!=null&&weiIdList.size()>0){
			weiSellerList = iBasicAgentOrProductShopDAO.getUWeiSellerList(weiIdList);
		}
		/**
		 * 通过循环来组合数据，可以避免在循环中访问数据库
		 */
		if(voList!=null&&voList.size()>0){
			for(PlatformProductShopListVO vo :voList){
				/**
				 * 获得用户信息表中的数据
				 */
				if(weiSellerList!=null&&weiSellerList.size()>0){
					for(UWeiSeller weiseller:weiSellerList){
						if(vo.getWeiId().equals(weiseller.getWeiId())){
							//微店图片
							vo.setWeiPicture(ImgDomain.GetFullImgUrl(weiseller.getImages()!=null?weiseller.getImages():""));
							//店铺名
							vo.setShopName(weiseller.getWeiName()!=null?weiseller.getWeiName():"");
							//联系人姓名
							vo.setLinkname(weiseller.getRealName()!=null?weiseller.getRealName():"");
							//手机号
							vo.setPhone(weiseller.getMobilePhone()!=null?weiseller.getMobilePhone():"");
							break;
						}
					}
				}
			}
		}
		int totalCount = (int) iBasicAgentOrProductShopDAO.getProductShopByVerifierTotalAmount(weiIdCondition, state,demandId,areaId);
		
		// 返回page
		PageResult<PlatformProductShopListVO> page = new PageResult<PlatformProductShopListVO>();
		// list内容
		page.setList(voList);
		// 总共有多少页
		int totalPage = (totalCount / limit.getSize())
				+ (totalCount % limit.getSize() > 0 ? 1 : 0);
		page.setTotalCount(totalCount);
		// 1页多少条
		page.setPageSize(limit.getSize());
		// 总共有多少页
		page.setPageCount((int) totalPage);
		// 当前页
		page.setPageIndex(limit.getPageId());
		return page;
	}
	
	@Override
	public ReturnModel updateProductShopState(Integer shopId, String cancelReason,
			Short state) {
		ReturnModel model = new ReturnModel();
		if(state!=null && state==Short.parseShort(AgentStatusEnum.Cancel.toString())){
			if(cancelReason==null||cancelReason.equals("")){
				model.setStatu(ReturnStatus.ParamError);
				model.setStatusreson("取消原因不能为空");
				return model;
			}
		}
		
		USupplyChannel channel = iBasicAgentOrProductShopDAO.get(USupplyChannel.class, shopId);
		//取消落地店身份就需要判断是否需要修改身份
		if(state==2){
			//判断是否还有落地店的身份
			
			if(channel!=null){
			   List<USupplyChannel> channelList = iBasicAgentOrProductShopDAO.getSupplyChannelList(channel.getWeiId());
			   if(channelList!=null && channelList.size()==1 && channelList.get(0).getChannelId().equals(shopId)){
				   UUserAssist assist = iBasicAgentOrProductShopDAO.get(UUserAssist.class, channel.getWeiId());
				   assist.setIdentity(BitOperation.setIntegerSomeBit(assist.getIdentity(), 12, false));
				   iBasicAgentOrProductShopDAO.update(assist);   
			   }
			}
			
			try
			{
				basicProductService.shelveProductToAgeStore(channel.getDemandId(), channel.getSupplyId(), channel.getWeiId(), channel.getChannelType(), (short) 0);
			}
			catch(Exception ex)
			{
				
			}
		}
		//恢复落地店身份就需要判断是否需要修改身份
		if(state == 1)
		{
			if(channel!=null){
				List<USupplyChannel> channelList = iBasicAgentOrProductShopDAO.getSupplyChannelList(channel.getWeiId());
				if(channelList==null || channelList.size()==0){
					UUserAssist assist = iBasicAgentOrProductShopDAO.get(UUserAssist.class, channel.getWeiId());
					   assist.setIdentity(BitOperation.setIntegerSomeBit(assist.getIdentity(), 12, true));
					   iBasicAgentOrProductShopDAO.update(assist); 
				}
			}
			
			try
			{
				basicProductService.shelveProductToAgeStore(channel.getDemandId(), channel.getSupplyId(), channel.getWeiId(), channel.getChannelType(), (short) 1);
			}
			catch(Exception ex)
			{
				
			}
		}
		
		//取消(有取消原因)、恢复、删除落地店
		int flag = iBasicAgentOrProductShopDAO.cancelOrRecoverProductShop(state, shopId, cancelReason);
		
		
		if(flag>0){
			model.setStatu(ReturnStatus.Success);
			model.setStatusreson("成功");
		}else{
			model.setStatu(ReturnStatus.SystemError);
			model.setStatusreson("数据没有更新");
		}
		return model;
	}
	@Override
	public MyAgentOrProductShopListVO getMyDevelopDetail(Integer parid) {
		List<MyAgentOrProductShopListVO> voList = new ArrayList<MyAgentOrProductShopListVO>();
		List<Object[]> list = iBasicAgentOrProductShopDAO.getMyDevelopAgentDetail(parid);
		//处理voList
		processVoList(list,voList,(long) 0);
		MyAgentOrProductShopListVO vo = new MyAgentOrProductShopListVO();
		if(voList!=null&&voList.size()>0){
			vo = voList.get(0);
		}
		return vo;
	}

	@Override
	public ReturnModel getProductShopDetail(Integer shopId) {
		ReturnModel model = new ReturnModel();
		if(shopId==null){
			model.setStatu(ReturnStatus.ParamError);
			model.setStatusreson("落地店id为空");
			return model;
		}
		PlatformProductShopDetailVO vo = new PlatformProductShopDetailVO();
		UProductShop productShop = baseDAO.get(UProductShop.class,shopId);
		if(productShop==null){
			model.setStatu(ReturnStatus.ParamError);
			model.setStatusreson("数据为空");
			return model;
		}
		USupplyChannel supplyChannel = baseDAO.get(USupplyChannel.class,shopId);
		if(supplyChannel==null){
			model.setStatu(ReturnStatus.ParamError);
			model.setStatusreson("数据为空");
			return model;
		}
		if(productShop!=null){
			Long weiId = productShop.getWeiId()!=null?productShop.getWeiId():-11;
			vo.setWeiId(weiId);
			vo.setShopId(shopId);
		    vo.setApplyTime(DateUtils.formatDateTime(productShop.getCreateTime()==null?new Date():productShop.getCreateTime()));
			UWeiSeller weiSeller = baseDAO.get(UWeiSeller.class,weiId);
			if(weiSeller!=null){
				//微店名
				vo.setShopName(weiSeller.getWeiName()!=null?weiSeller.getWeiName():"");
				//微店图片
				vo.setWeiPicture(ImgDomain.GetFullImgUrl(weiSeller.getImages()!=null?weiSeller.getImages():""));
				//联系人姓名
				//vo.setLinkname(weiSeller.getRealName()!=null?weiSeller.getRealName():"");
				//手机号
				vo.setPhone(weiSeller.getMobilePhone()!=null?weiSeller.getMobilePhone():"");
			}
			
			UProductAgent productAgent = baseDAO.get(UProductAgent.class,supplyChannel.getUpWeiId()!=null?supplyChannel.getUpWeiId().intValue():-11);
			if(productAgent!=null){
				//上级代理商微店号
				vo.setParentAgentWeiId(productAgent.getWeiId()!=null?productAgent.getWeiId():-11);
				//上级代理商名称
				vo.setParentAgentName(productAgent.getCompanyName()!=null?productAgent.getCompanyName():"");
				//联系人姓名
				vo.setLinkname(productAgent.getLinkMan()==null?"":productAgent.getLinkMan());
			}
			Long supplyerWeiId = productShop.getSupplyId()!=null?productShop.getSupplyId():-11;
			UWeiSeller supplyerWeiSeller = baseDAO.get(UWeiSeller.class,supplyerWeiId);
			//上级供应商微店号
			vo.setMerchantWeiId(supplyerWeiId);
			if(supplyerWeiSeller!=null){
				//上级供应商微店名
				vo.setMerchantShopName(supplyerWeiSeller.getWeiName()!=null?supplyerWeiSeller.getWeiName():"");
			}
			//状态
			vo.setStatus(supplyChannel.getState()!=null?supplyChannel.getState():null);
			//悬赏金额
			vo.setRewardAmount(supplyChannel.getReward()!=null?supplyChannel.getReward():null);
			//是否悬赏
			vo.setIsPayReward(supplyChannel.getPayedReward()!=null?supplyChannel.getPayedReward():0);
			//发展人微店号
			vo.setDevelopmentWeiId(supplyChannel.getVerifier()!=null?supplyChannel.getVerifier():null);
			
			if(vo.getDevelopmentWeiId()!=null){
				UVerifier verifier = baseDAO.get(UVerifier.class, vo.getDevelopmentWeiId());
				if(verifier!=null)
				{
					vo.setDevelopmentName(verifier.getName());
					vo.setDevelopmentPhone(verifier.getPhone());
				}
			}
			
			vo.setDemandId(supplyChannel.getDemandId());
			USupplyDemand demand=baseDAO.get(USupplyDemand.class, supplyChannel.getDemandId());
			if(demand!=null)
			{
				vo.setDemandName(demand.getTitle());
			}
			//所在地区
			Integer provinceCode = productShop.getProvince()!=null?Integer.parseInt(productShop.getProvince().toString()):-11;
			Integer cityCode = productShop.getCity()!=null?Integer.parseInt(productShop.getCity().toString()):-11;
			Integer districtCode = productShop.getArea()!=null?Integer.parseInt(productShop.getArea().toString()):-11;
			//省
			String province= iRegionService.getNameByCode(provinceCode);
			//市
			String city= iRegionService.getNameByCode(cityCode);
			//区
			String district= iRegionService.getNameByCode(districtCode);
			AreaShowVO areas = new AreaShowVO();
			areas.setAreaId(provinceCode.toString());
			areas.setAreaName(province);
			//市
			AreaShowVO area = new AreaShowVO();
			area.setAreaId(cityCode.toString());
			area.setAreaName(city);
			areas.setArea(area);
			//区
			AreaShowVO areaDistrict = new AreaShowVO();
			areaDistrict.setAreaId(districtCode.toString());
			areaDistrict.setAreaName(district);
			area.setArea(areaDistrict);
			vo.setLocation(areas);
			//详细地址
			vo.setAddress(productShop.getAddress()!=null?productShop.getAddress():"");
		}
		model.setBasemodle(vo);
		model.setStatu(ReturnStatus.Success);
		model.setStatusreson("成功");
		return model;
	}

	@Override
	public ReturnModel batchUpdateProductShopState(Integer[] shopId, Short state) {
		ReturnModel model = new ReturnModel();
		if(shopId==null||shopId.length<1){
			model.setStatu(ReturnStatus.ParamError);
			model.setStatusreson("参数错误");
			return model;
		}
		iBasicAgentOrProductShopDAO.batchUpdateProductShopState(shopId, state);
		model.setStatu(ReturnStatus.Success);
		model.setStatusreson("成功");
		return model;
	}

	@Override
	public ReturnModel addProductShop(Integer[] demandIdArr, Long weiId,Long supplyWeiId) {
		ReturnModel model = new ReturnModel();
		UWeiSeller weiSeller = baseDAO.get(UWeiSeller.class,weiId);
		if(weiSeller==null){
			model.setStatu(ReturnStatus.ParamError);
			model.setStatusreson("该微店号在用户表中不存在");
			return model;
		}
		if(supplyWeiId==null){
			model.setStatu(ReturnStatus.ParamError);
			model.setStatusreson("平台号微店号为空");
			return model;
		}
		if(weiId==null){
			model.setStatu(ReturnStatus.ParamError);
			model.setStatusreson("微店号必须填写");
			return model;
		}
		if(demandIdArr==null||demandIdArr.length<1){
			model.setStatu(ReturnStatus.ParamError);
			model.setStatusreson("招商需求必须选择");
			return model;
		}
		//添加落地店的身份
		
		UUserAssist assist = iBasicAgentOrProductShopDAO.get(UUserAssist.class, weiId);
		assist.setIdentity(BitOperation.setIntegerSomeBit(assist.getIdentity(), 13, true));
		iBasicAgentOrProductShopDAO.update(assist); 
		
		
		
		for(int i=0;i<demandIdArr.length;i++){
			//供应商渠道表
			USupplyChannel sc = new USupplyChannel();
			Integer channelID=0;
			List<USupplyChannel> scList = iBasicAgentOrProductShopDAO.getUBrandSupplyerByParam(weiId, demandIdArr[i]);
			if(scList!=null && scList.size()>0)
			{
				sc=scList.get(0);
				channelID = sc.getChannelId();
				sc.setDemandId(demandIdArr[i]);
				sc.setSupplyId(supplyWeiId);
				sc.setWeiId(weiId);
				sc.setPayedReward((short) 1);
				sc.setChannelType(Short.parseShort(SupplyChannelTypeEnum.ground.toString()));
				sc.setState(Short.parseShort(AgentStatusEnum.Normal.toString()));
				sc.setCreateTime(new Date());
				baseDAO.update(sc);
			}else{
				sc = new USupplyChannel();
				sc.setDemandId(demandIdArr[i]);
				sc.setSupplyId(supplyWeiId);
				sc.setWeiId(weiId);
				sc.setPayedReward((short) 1);
				sc.setChannelType(Short.parseShort(SupplyChannelTypeEnum.ground.toString()));
				sc.setState(Short.parseShort(AgentStatusEnum.Normal.toString()));
				sc.setCreateTime(new Date());
				channelID = (Integer) baseDAO.save(sc);
			}
			
			//落地店表
			UProductShop ps = baseDAO.get(UProductShop.class, channelID);
			if(ps==null)
			{
				ps=new UProductShop();
				ps.setWeiId(weiId);
				ps.setDemandId(demandIdArr[i]);
				ps.setChannelId((Integer)channelID);
				ps.setWeiId(weiId);
				ps.setSupplyId(supplyWeiId);
				ps.setLinkMan(weiSeller.getRealName()!=null?weiSeller.getRealName():"");
				ps.setPhone(weiSeller.getMobilePhone()!=null?weiSeller.getMobilePhone():"");
				ps.setWeiName(weiSeller.getWeiName()!=null?weiSeller.getWeiName():"");
				ps.setCreateTime(new Date());
				iBasicAgentOrProductShopDAO.insertProductShop(ps);
			}else{
				ps.setWeiId(weiId);
				ps.setDemandId(demandIdArr[i]);
				ps.setChannelId((Integer)channelID);
				ps.setWeiId(weiId);
				ps.setSupplyId(supplyWeiId);
				ps.setLinkMan(weiSeller.getRealName()!=null?weiSeller.getRealName():"");
				ps.setPhone(weiSeller.getMobilePhone()!=null?weiSeller.getMobilePhone():"");
				ps.setWeiName(weiSeller.getWeiName()!=null?weiSeller.getWeiName():"");
				ps.setCreateTime(new Date());
				baseDAO.save(ps);
			}
			
		}
		
		model.setStatu(ReturnStatus.Success);
		model.setStatusreson("成功");
		return model;
	}
	
	@Override
	public ReturnModel getShopNameInfo(Long weiId, Long supplyWeiId) {
		ReturnModel model = new ReturnModel();
		UWeiSeller weiSeller = baseDAO.get(UWeiSeller.class,weiId);
		if(weiSeller==null){
			model.setStatu(ReturnStatus.ParamError);
			model.setStatusreson("该微店号在用户表中不存在");
			return model;
		}
		String shopName = weiSeller.getWeiName()!=null?weiSeller.getWeiName():"";
		model.setStatu(ReturnStatus.Success);
		model.setBasemodle(shopName);
		return model;
	}

	@Override
	public ReturnModel getVerifierRegion(Long weiId, Short type) {
		// TODO 自动生成的方法存根
		ReturnModel model = new ReturnModel();
		List<VerifierRegion> regionVoList = new ArrayList<VerifierRegion>();
		List<UBatchVerifierRegion> regionList = iBasicAgentOrProductShopDAO.getVerifierRegion(weiId, type);
		if(regionList!=null && regionList.size()>0)
		{
			for(UBatchVerifierRegion verfierRegion:regionList)
			{
				VerifierRegion vr = new VerifierRegion();
				vr.setArea(verfierRegion.getCode().toString());
				regionVoList.add(vr);
			}
		}
		model.setStatu(ReturnStatus.Success);
		model.setStatusreson("成功！");
		model.setBasemodle(regionVoList);
		return model;
	}

	@Override
	public PageResult<LandShopListVO> getMyLandShopList(Long weiId,Limit limit) {
		// TODO 自动生成的方法存根
		List<LandShopListVO> voList = new ArrayList<LandShopListVO>();
		List<Long> companyWeiIDList = new ArrayList<Long>();
		List<Integer> demandIdList = new ArrayList<Integer>();
		
		List<UProductShop> landList = iBasicAgentOrProductShopDAO.getLandShopListMyself(weiId,limit);
		if(landList!=null && landList.size()>0){
			for(UProductShop land:landList){
				LandShopListVO vo=new LandShopListVO();
				vo.setFirstOrderTime(DateUtils.formatDateTime(land.getCreateTime()==null?new Date():land.getCreateTime()));
				vo.setInvestmentDemandId(land.getDemandId());
				vo.setMerchantWeiId(land.getSupplyId());
				
				voList.add(vo);
				if(land.getSupplyId()!=null)
				   companyWeiIDList.add(land.getSupplyId());
				if(land.getDemandId()!=null)
				   demandIdList.add(land.getDemandId());
			}
		}
		if(voList.size()>0){
			List<UPlatformSupplyer> supplierList = iBasicAgentOrProductShopDAO.getPlatformSupplyerListByIds(companyWeiIDList);
			List<USupplyDemand> demandList = iBasicAgentOrProductShopDAO.getSupplyDemandListByIds(demandIdList);
			//12-2hjd添加
			List<UBrandSupplyer> brandList = iBasicAgentOrProductShopDAO.getBrandSupplyersList(companyWeiIDList);
			for(LandShopListVO vo:voList){
				if(supplierList!=null && supplierList.size()>0){
					for(UPlatformSupplyer supplier:supplierList){
						if(vo.getMerchantWeiId().equals(supplier.getWeiId()))
						{
							vo.setMerchantName(supplier.getSupplyName()==null?"":supplier.getSupplyName());
						}
					}
				}
				//12-2hjd添加
				if(brandList!=null && brandList.size()>0){
                                        for(UBrandSupplyer brand:brandList){
                                                if(vo.getMerchantWeiId().equals(brand.getWeiId()))
                                                {
                                                        vo.setMerchantName(brand.getSupplyName()==null?"":brand.getSupplyName());
                                                }
                                        }
                                }
				if(demandList!=null && demandList.size()>0){
					for(USupplyDemand demand:demandList){
						if(vo.getInvestmentDemandId().equals(demand.getDemandId())){
							vo.setInvestmentDemandName(demand.getTitle()==null?"":demand.getTitle());
							vo.setMinOrderTrans(demand.getOrderAmout()==null?0.0:demand.getOrderAmout());
						}
					}
				}
			}
		}
		
		//总记录数
		int totalCount = (int) iBasicAgentOrProductShopDAO.getMyLandShopCount(weiId);
		// 返回page
		PageResult<LandShopListVO> page = new PageResult<LandShopListVO>();
		// list内容
		page.setList(voList);
		// 总共有多少页
		int totalPage = (totalCount / limit.getSize())
				+ (totalCount % limit.getSize() > 0 ? 1 : 0);
		page.setTotalCount(totalCount);
		// 1页多少条
		page.setPageSize(limit.getSize());
		// 总共有多少页
		page.setPageCount((int) totalPage);
		// 当前页
		page.setPageIndex(limit.getPageId());
		return page;
	}

	@Override
	public void operateUserIdentity(Integer[] shopId, Short state) {
		// TODO 自动生成的方法存根
		for(Integer sid:shopId){
			USupplyChannel channel = iBasicAgentOrProductShopDAO.get(USupplyChannel.class, sid);
			if(channel!=null){
				List<USupplyChannel> channelList = iBasicAgentOrProductShopDAO.getSupplyChannelList(channel.getWeiId());
				UUserAssist assist = iBasicAgentOrProductShopDAO.get(UUserAssist.class, channel.getWeiId());
				if(channelList==null || channelList.size()==0){
					assist.setIdentity(BitOperation.setIntegerSomeBit(assist.getIdentity(), 12, false)); 
				}else{
					assist.setIdentity(BitOperation.setIntegerSomeBit(assist.getIdentity(), 12, true)); 
				}
				iBasicAgentOrProductShopDAO.update(assist);
			}
		}	
	}


}
