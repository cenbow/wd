package com.okwei.service.impl.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.okwei.bean.domain.TRegional;
import com.okwei.bean.domain.UBrandSupplyer;
import com.okwei.bean.domain.UChildrenCreate;
import com.okwei.bean.domain.UChildrenStaff;
import com.okwei.bean.domain.UChildrenSupplyer;
import com.okwei.bean.domain.UChildrenUser;
import com.okwei.bean.domain.UPlatformSupplyer;
import com.okwei.bean.domain.USupplyChannel;
import com.okwei.bean.domain.UVerifier;
import com.okwei.bean.dto.AddChildrenAccountDTO;
import com.okwei.bean.dto.user.VerifySupplierDTO;
import com.okwei.bean.enums.ChildrenSupplyerStatusEnum;
import com.okwei.bean.vo.AreaShowVO;
import com.okwei.bean.vo.ChildAccountListVO;
import com.okwei.bean.vo.ChildrenAccountVO;
import com.okwei.bean.vo.LoginUser;
import com.okwei.bean.vo.ReturnModel;
import com.okwei.bean.vo.ReturnStatus;
import com.okwei.bean.vo.user.ChildrenAccountIdVO;
import com.okwei.bean.vo.user.PlatformSupplierKVVO;
import com.okwei.common.Limit;
import com.okwei.common.PageResult;
import com.okwei.dao.IBaseDAO;
import com.okwei.dao.user.IBasicAgentOrProductShopDAO;
import com.okwei.dao.user.IChildrenAccountDAO;
import com.okwei.service.IRegionService;
import com.okwei.service.impl.BaseService;
import com.okwei.service.user.IChildrenAccountService;
import com.okwei.util.ObjectUtil;
@Service
public class ChildrenAccountService extends BaseService implements IChildrenAccountService {
	@Autowired
	private IChildrenAccountDAO iChildrenAccountDAO;
	@Autowired
	private IBaseDAO baseDAO;
	@Autowired
	private IRegionService iRegionService;
	@Autowired
	private IBasicAgentOrProductShopDAO iBasicAgentOrProductShopDAO;
	
	@Override
	public PageResult<ChildAccountListVO> getChildrenSupplyerList(Long weiId,Limit limit,Integer status,Short from) {
		List<ChildAccountListVO> voList = new ArrayList<ChildAccountListVO>();
		List<Object[]> list = iChildrenAccountDAO.getChildrenSupplyerList(weiId, limit,status,from);
		if(list!=null&&list.size()>0){
			for(int i=0;i<list.size();i++){
				//ChildrenAccountVO vo = new ChildrenAccountVO();
				ChildAccountListVO vo = new ChildAccountListVO();
				Object[] obj = list.get(i);
				//子账号
				String childId = obj[0]!=null?String.valueOf(obj[0]):"";
				vo.setAccountId(childId);
				//公司名称
				vo.setAccountName(obj[1]!=null?String.valueOf(obj[1]):"");
				//状态
				vo.setStatus(obj[2]!=null?Short.valueOf(obj[2].toString()):null);
				//联系电话
				vo.setMobilePhone(obj[3]!=null?String.valueOf(obj[3]):"");
				//推荐人员
				vo.setVerifyWeiId(obj[4]!=null?String.valueOf(obj[4]):"");
				//子账号类型
				vo.setAccountType(1);
				voList.add(vo);
			}
		}
		
		int totalCount = (int) iChildrenAccountDAO.getChildrenSupplyerTotalAmount(weiId,status,Integer.parseInt(from.toString()));
		// 返回page
		PageResult<ChildAccountListVO> page = new PageResult<ChildAccountListVO>();
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
	
	/**
	 * 获取推荐的供应商列表
	 */
	@Override
	public PageResult<ChildAccountListVO> getChildrenSByRecommandAPP(
			Long weiId, Limit limit, Integer status) {
		List<ChildAccountListVO> voList = new ArrayList<ChildAccountListVO>();
		List<Object[]> list = iChildrenAccountDAO.getChildrenSupplyerListByRecommand(weiId, limit,status);
		if(list!=null&&list.size()>0){
			for(int i=0;i<list.size();i++){
				//ChildrenAccountVO vo = new ChildrenAccountVO();
				ChildAccountListVO vo = new ChildAccountListVO();
				Object[] obj = list.get(i);
				//子账号
				String childId = obj[0]!=null?String.valueOf(obj[0]):"";
				vo.setAccountId(childId);
				//公司名称
				vo.setAccountName(obj[3]!=null?String.valueOf(obj[3]):"");
				//状态
				vo.setStatus(obj[4]!=null?Short.valueOf(obj[4].toString()):null);
				//联系电话
				vo.setMobilePhone(obj[2]!=null?String.valueOf(obj[2]):"");
				//推荐人员
				vo.setVerifyWeiId(weiId.toString());
				//子账号类型
				vo.setAccountType(1);
				voList.add(vo);
			}
		}
		
		int totalCount = (int) iChildrenAccountDAO.getChildrenSupplyerTotalAmountByRecommand(weiId,status);
		// 返回page
		PageResult<ChildAccountListVO> page = new PageResult<ChildAccountListVO>();
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
	public PageResult<ChildrenAccountVO> getChildrenSupplyerListPC(Long weiId,Limit limit,Integer status,Integer from) {
		List<ChildrenAccountVO> voList = new ArrayList<ChildrenAccountVO>();
		List<String> childIdList = new ArrayList<String>();
		List<Long> weiIdList = new ArrayList<Long>();
		List<Object[]> list = iChildrenAccountDAO.getChildrenSupplyerListPC(weiId, limit,status,from);
		if(list!=null&&list.size()>0){
			for(int i=0;i<list.size();i++){
				ChildrenAccountVO vo = new ChildrenAccountVO();
				Object[] obj = list.get(i);
				//子账号
				String childId = obj[0]!=null?String.valueOf(obj[0]):"";
				vo.setAccountId(childId);
				childIdList.add(childId);
				//密码
				//vo.setPassword(obj[1]!=null?String.valueOf(obj[1]):"");
				//负责人
				vo.setEmployeeName(obj[2]!=null?String.valueOf(obj[2]):"");
				
				vo.setPhone(obj[3]!=null?String.valueOf(obj[3]):"");
				//公司名
				vo.setSupplierName(obj[4]!=null?String.valueOf(obj[4]):"");
				//是否负责发货
				vo.setIsOrderSend(obj[5]!=null?Short.valueOf(obj[5].toString()):null);
				//状态
				vo.setStatus(obj[6]!=null?Short.valueOf(obj[6].toString()):null);
				//状态名称
				vo.setStatusName(StateChange(vo.getStatus()));
				//详细地址
				vo.setAddress(obj[7]!=null?String.valueOf(obj[7]):"");
				//省市区编码
				/*Integer provinceCode = obj[8]!=null?Integer.parseInt(obj[8].toString()):-11;
				Integer cityCode = obj[9]!=null?Integer.parseInt(obj[9].toString()):-11;
				Integer districtCode = obj[10]!=null?Integer.parseInt(obj[10].toString()):-11;
				//省市区名称
				String province= iRegionService.getNameByCode(provinceCode);
				String city= iRegionService.getNameByCode(cityCode);
				String district= iRegionService.getNameByCode(districtCode);
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
				if(provinceCode==110000 || provinceCode==120000 || provinceCode==310000 || provinceCode==500000)
					vo.setLocation(area);
				else
				    vo.setLocation(location);*/
				//认证员的微店号
				Long verifierWeiId = obj[11]!=null?Long.valueOf(String.valueOf(obj[11])):null;
				vo.setVerifyWeiId(verifierWeiId);
				weiIdList.add(verifierWeiId);
				voList.add(vo);
			}
		}
		//认证员列表
		/*List<UVerifier> verifierList = null;
		if(weiIdList!=null&&weiIdList.size()>0){
			verifierList = iChildrenAccountDAO.getVerifierInfo(weiIdList);
		}
		//发布的商品数列表
		List<Object[]> publishProductCountList = null;
		//待审核的商品数列表
		List<Object[]> pendingProductCountList = null;
		if(childIdList!=null&&childIdList.size()>0){
			publishProductCountList = iChildrenAccountDAO.getPublishProductCountByChildSupplyer(childIdList);
			pendingProductCountList = iChildrenAccountDAO.getPendingProductCountByChildSupplyer(childIdList);
		}
		if(voList!=null&&voList.size()>0){
			for(ChildrenAccountVO vo :voList){
				if(verifierList!=null&&verifierList.size()>0){
					for(UVerifier ver:verifierList){
						Long verWeiId = vo.getVerifyWeiId()!=null?vo.getVerifyWeiId():-11;
						if(verWeiId.equals(ver.getWeiId())){
							//认证员的名字
							vo.setVerifyName(ver.getName()!=null?ver.getName():"");
							break;
						}
					}
				}
				if(publishProductCountList!=null && publishProductCountList.size()>0){
					for(Object[] pub:publishProductCountList){
						if(vo.getAccountId().equals(pub[1])){
							//发布的商品数
							vo.setTotalProduct(Integer.valueOf(pub[0].toString()));
						}
					}
				}
				if(pendingProductCountList!=null && pendingProductCountList.size()>0){
					for(Object[] pen:pendingProductCountList){
						if(vo.getAccountId().equals(pen[1])){
							//待审核的商品数
							vo.setWaitAuditProduct(Integer.valueOf(pen[0].toString()));
						}
					}
				}
			}
		}*/
		
		int totalCount = (int) iChildrenAccountDAO.getChildrenSupplyerTotalAmount(weiId,status,from);
		// 返回page
		PageResult<ChildrenAccountVO> page = new PageResult<ChildrenAccountVO>();
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
	public PageResult<ChildrenAccountVO> getChildrenSupplyerListByRecommand(
			Long weiId, Limit limit, Integer status) {
		List<ChildrenAccountVO> voList = new ArrayList<ChildrenAccountVO>();
		List<Long> parentList = new ArrayList<Long>();
		List<Object[]> list = iChildrenAccountDAO.getChildrenSupplyerListByRecommand(weiId, limit,status);
		if(list!=null&&list.size()>0){
			for(int i=0;i<list.size();i++){
				ChildrenAccountVO vo = new ChildrenAccountVO();
				Object[] obj = list.get(i);
				//子账号
				String childId = obj[0]!=null?String.valueOf(obj[0]):"";
				vo.setAccountId(childId);
				//联系人
				vo.setLinkName(obj[1]!=null?String.valueOf(obj[1]):"");
				
				vo.setPhone(obj[2]!=null?String.valueOf(obj[2]):"");
				//公司名
				vo.setSupplierName(obj[3]!=null?String.valueOf(obj[3]):"");
				//状态
				vo.setStatus(obj[4]!=null?Short.valueOf(obj[4].toString()):null);
				//状态名称
				vo.setStatusName(StateChange(obj[4]!=null?Short.valueOf(obj[4].toString()):null));
				//详细地址
				vo.setAddress(obj[5]!=null?String.valueOf(obj[5]):"");
				
				if(vo.getStatus()==4)
					vo.setNoPassReason("("+(obj[10]!=null?String.valueOf(obj[10]):"")+")");
				//省市区编码
				Integer provinceCode = obj[6]!=null?Integer.parseInt(obj[6].toString()):-11;
				Integer cityCode = obj[7]!=null?Integer.parseInt(obj[7].toString()):-11;
				Integer districtCode = obj[8]!=null?Integer.parseInt(obj[8].toString()):-11;
				
				Long parentid=obj[9]!=null?Long.valueOf(obj[9].toString()):0;
				parentList.add(parentid);
				vo.setPlatformId(parentid);
				//省市区名称
				String province= iRegionService.getNameByCode(provinceCode);
				String city= iRegionService.getNameByCode(cityCode);
				String district= iRegionService.getNameByCode(districtCode);
				
				
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
				if(provinceCode==110000 || provinceCode==120000 || provinceCode==310000 || provinceCode==500000)
					vo.setLocation(area);
				else
				    vo.setLocation(location);

				voList.add(vo);
			}
		}
        
		List<UPlatformSupplyer> supplyerList = new ArrayList<UPlatformSupplyer>();
		if(parentList!=null && parentList.size()>0)
			supplyerList= iChildrenAccountDAO.getPlatformSuppler(parentList);
		
		
		if(voList!=null&&voList.size()>0){
			for(ChildrenAccountVO vo :voList){
				if(supplyerList!=null && supplyerList.size()>0){
					for(UPlatformSupplyer supplier:supplyerList){
						vo.setPlatformName("不是平台号供应商");
						if(vo.getPlatformId().equals(supplier.getWeiId()))
						{
						    vo.setPlatformName(supplier.getSupplyName());
						    break;
						}
					}
				}
			}
		}
		
		int totalCount = (int) iChildrenAccountDAO.getChildrenSupplyerTotalAmountByRecommand(weiId,status);
		// 返回page
		PageResult<ChildrenAccountVO> page = new PageResult<ChildrenAccountVO>();
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
	
	public String StateChange(Short state){
		String name="";
		if(state!=null){
			switch(state)
			{
			case 2:name ="待审核";break;
			case 3:name ="已通过";break;
			case 4:name ="未通过";break;
			default:name ="未知状态";break;
			}
		}else{
			name ="未知状态";
		}
		return name;
	}
	
	@Override
	public PageResult<ChildAccountListVO> getChildrenStaffList(Long weiId,Limit limit) {
		List<ChildAccountListVO> voList = new ArrayList<ChildAccountListVO>();
		List<Object[]> list = iChildrenAccountDAO.getChildrenStaffList(weiId, limit);
		if(list!=null&&list.size()>0){
			for(int i=0;i<list.size();i++){
				ChildAccountListVO vo = new ChildAccountListVO();
				Object[] obj = list.get(i);
				//子账号
				vo.setAccountId(obj[0]!=null?String.valueOf(obj[0]):"");
				//名称
				vo.setAccountName(obj[1]!=null?String.valueOf(obj[1]):"");
				//部门
				vo.setDepartment(obj[2]!=null?String.valueOf(obj[2]):"");
				//状态
				vo.setStatus(obj[3]!=null?Short.valueOf(String.valueOf(obj[3])):null);
	            //联系电话
				vo.setMobilePhone(obj[4]!=null?String.valueOf(obj[4]):"");
				//子账号类型
				vo.setAccountType(0);
				voList.add(vo);
			}
		}
		int totalCount = (int) iChildrenAccountDAO.getChildrenStaffTotalAmount(weiId);
		// 返回page
		PageResult<ChildAccountListVO> page = new PageResult<ChildAccountListVO>();
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
	public ReturnModel addChildrenSupplyerOrStaff(AddChildrenAccountDTO param,
			LoginUser user) {
		ReturnModel model = new ReturnModel();
		if(param!=null){
//			if(ObjectUtil.isEmpty(param.getUserName())){
//				model.setStatu(ReturnStatus.ParamError);
//				model.setStatusreson("姓名不能为空");
//				return model;
//			}
			if(ObjectUtil.isEmpty(param.getPassWord())){
				model.setStatu(ReturnStatus.ParamError);
				model.setStatusreson("密码不能为空");
				return model;
			}
			//子账号id
			String childrenID="";
			//子账号用户
			UChildrenUser obj = new UChildrenUser();
			//获取子账号生成表对象
			UChildrenCreate childrenCreate = iChildrenAccountDAO.getUChildrenCreate(user.getWeiID());
			//如果已有子账号的情况
			if(childrenCreate!=null){
				//把子账号的id进行逻辑自增
				int temp=childrenCreate.getCreateId()+1;
				childrenCreate.setCreateId(temp);  //更新数据库中的对应父微店号的createID
				//子账号id
				String childId=childrenCreate.getParentId()+"_";
				//根据数值位数，进行补零
				if(temp<10){
					childId+="000"+temp;
				}
				if(10<=temp&&temp<100){
					childId+="00"+temp;
				}
				if(100<=temp&&temp<1000){
					childId+="0"+temp;
				}
				obj.setChildrenId(childId);
				childrenID=childId;
			}else{  
				//如果是第一次添加子账号的情况
				UChildrenCreate childrenCreateTemp = new UChildrenCreate();
				childrenCreateTemp.setParentId(user.getWeiID());
				childrenCreateTemp.setCreateId(1);
				iChildrenAccountDAO.addChildrenCreate(childrenCreateTemp);
				childrenID=user.getWeiID()+"_0001";
				obj.setChildrenId(childrenID);
			}
			//父微店号
			obj.setParentId(user.getWeiID());
			//创建时间
			obj.setCreateTime(new Date());
			//创建人
			obj.setCreateUser(user.getWeiID()!=null?user.getWeiID().toString():"");
			//手机号
			obj.setMobilePhone(param.getMobilePhone());
			//负责人
			obj.setUserName(param.getUserName());
			//密码
			obj.setPassWord(param.getPassWord());
			//状态
			obj.setState(Integer.parseInt(ChildrenSupplyerStatusEnum.pass.toString()));
			//区分是添加员工子账号还是添加供应商子账号
			obj.setType(param.getType());
			//自己添加的就是审核通过的
			obj.setState(3);
			iChildrenAccountDAO.addChildrenUser(obj);
			if(param.getType()==1){  //type=1表示员工子账号
				UChildrenStaff staff = new UChildrenStaff();
				staff.setChildrenId(childrenID);
				staff.setDepartment(param.getDepartment());
				iChildrenAccountDAO.addChildrenStaffInfo(staff);
			}else{  //供应商子账号
				UChildrenSupplyer supplyer = new UChildrenSupplyer();
				supplyer.setChildrenId(childrenID);
				supplyer.setCompanyName(param.getCompanyName());
				supplyer.setIsOrderSend(param.getIsOrderSend());
				supplyer.setAddress(param.getAddress());
				//区编码
				Integer district = param.getLocation();
				supplyer.setArea(district);
				//市编码
				Integer cityCode = null;
				//省编码
				Integer provinceCode = null;
				//处理省市区
				Map<String, Object> map = processReginal(district,provinceCode,cityCode);
				supplyer.setProvince(map.get("province")!=null?(Integer)map.get("province"):null);
				supplyer.setCity(map.get("city")!=null?(Integer)map.get("city"):null);
				iChildrenAccountDAO.addChildrenSupplyerInfo(supplyer);
			}
			
			ChildrenAccountIdVO acountid = new ChildrenAccountIdVO();
			acountid.setAccountId(childrenID);
			model.setBasemodle(acountid);
			model.setStatu(ReturnStatus.Success);
			model.setStatusreson("成功");
			return model;
		}else{
			model.setStatu(ReturnStatus.ParamError);
			model.setStatusreson("传入的数据为空");
			return model;
		}
	}
	public Map<String,Object> processReginal(Integer district,Integer provinceCode,Integer cityCode){
		Map<String,Object> map = new HashMap<String, Object>();
		//通过区编码获得它的市编码
		List<TRegional> regionalByDistrict = iChildrenAccountDAO.getTRegionalByCode(district);
		if(regionalByDistrict!=null && regionalByDistrict.size()>0){
			TRegional regional = regionalByDistrict.get(0);
			cityCode = regional.getParent()!=null?regional.getParent():null;
			//通过市编码获得它的上级省编码
			if(cityCode!=null){
				//直辖市除外
				switch (cityCode) {
				case 110000: // 北京市
					provinceCode = cityCode;
					break;
				case 120000: // 天津
					provinceCode = cityCode;
					break;
				case 310000: // 上海
					provinceCode = cityCode;
					break;
				case 500000: // 重庆
					provinceCode = cityCode;
					break;
				default:
					List<TRegional> regionalByCity = iChildrenAccountDAO.getTRegionalByCode(cityCode);
					TRegional reginalCity = regionalByCity.get(0);
					provinceCode = reginalCity.getParent()!=null?reginalCity.getParent():null;
					break;
				}
			}
		}
		map.put("province",provinceCode);
		map.put("city",cityCode);
		return map;
	}
	@Override
	public ReturnModel editChildrenSupplyerOrStaff(AddChildrenAccountDTO param,
			LoginUser user) {
		ReturnModel model = new ReturnModel();
//		if(ObjectUtil.isEmpty(param.getUserName())){
//			model.setStatu(ReturnStatus.ParamError);
//			model.setStatusreson("姓名不能为空");
//			return model;
//		}
//		if(ObjectUtil.isEmpty(param.getPassWord())){
//			model.setStatu(ReturnStatus.ParamError);
//			model.setStatusreson("密码不能为空");
//			return model;
//		}
		if(ObjectUtil.isEmpty(param.getChildrenId())){
			model.setStatu(ReturnStatus.ParamError);
			model.setStatusreson("子账号id不能为空");
			return model;
		}
		
		UChildrenUser childrenUser = iChildrenAccountDAO.getUChildrenUser(param.getChildrenId());
		
		
		Boolean haveOwner = false;
		if(childrenUser==null)
		{
			model.setStatu(ReturnStatus.ParamError);
			model.setStatusreson("子账号不存在");
			return model;
		}
		else if(user.getWeiID().equals(childrenUser.getParentId())){
			haveOwner=true;
		}
		
		//认证员只有在还没有审核的时候可以修改
		if(param.getType()!=1 && childrenUser.getState()==2)
		{
			UChildrenSupplyer childrenSupplyer = baseDAO.get(UChildrenSupplyer.class,param.getChildrenId());
			if(childrenSupplyer == null){
				model.setStatu(ReturnStatus.ParamError);
				model.setStatusreson("子账号数据有问题！");
				return model;
			}else if(user.getWeiID().equals(childrenSupplyer.getVerifier())){
				haveOwner=true;
			}
		}
		
		if(!haveOwner){
			model.setStatu(ReturnStatus.ParamError);
			model.setStatusreson("该子账号信息你无权修改！");
			return model;
		}
		
		if(param.getPassWord()!=null && !param.getPassWord().equals(""))
		   childrenUser.setPassWord(param.getPassWord());
		childrenUser.setUserName(param.getUserName());
		childrenUser.setMobilePhone(param.getMobilePhone());
		childrenUser.setUpdateTime(new Date());
		childrenUser.setUpdateUser(user.getWeiName());
		
		//修改子账号用户的基本信息
		//iChildrenAccountDAO.editUChildrenUser(param);
		if(param.getType()==1){  //type=1表示员工子账号
			iChildrenAccountDAO.editUChildrenStaff(param.getChildrenId(),param.getDepartment());
		}else{//供应商子账号
			UChildrenSupplyer childrenSupplyer = baseDAO.get(UChildrenSupplyer.class,param.getChildrenId());
			childrenSupplyer.setAddress(param.getAddress());
			childrenSupplyer.setCompanyName(param.getCompanyName());
			childrenSupplyer.setArea(param.getLocation());
			//市编码
			Integer cityCode = null;
			//省编码
			Integer provinceCode = null;
			//处理省市区
			Map<String, Object> map = processReginal(param.getLocation(),provinceCode,cityCode);
			childrenSupplyer.setProvince(map.get("province")!=null?(Integer)map.get("province"):null);
			childrenSupplyer.setCity(map.get("city")!=null?(Integer)map.get("city"):null);
		}
		model.setStatu(ReturnStatus.Success);
		model.setStatusreson("成功");
		return model;
	}

	@Override
	public ReturnModel getChildrenSupplyerOrStaff(String childrenId,
			Integer type,LoginUser user) {
		ReturnModel rm= new ReturnModel();
		ChildrenAccountVO vo = new ChildrenAccountVO();
		vo.setAccountId(childrenId);
		UChildrenUser childrenUser = iChildrenAccountDAO.getUChildrenUser(childrenId);
		UChildrenSupplyer childrenSupplyer = iChildrenAccountDAO.getUChildrenSupplyer(childrenId);
		if(childrenUser!=null){
			Boolean havepower=false;
			if(childrenUser.getParentId().equals(user.getWeiID())){
				havepower=true;
			}
			
			if(childrenSupplyer!=null && childrenSupplyer.getVerifier()!=null && childrenSupplyer.getVerifier().equals(user.getWeiID()))
			{
				havepower=true;
			}
			
			if(!havepower)
			{
				rm.setStatu(ReturnStatus.ParamError);
				rm.setStatusreson("你没权限查看此供应商详情！");
				return rm;
			}
			vo.setPhone(childrenUser.getMobilePhone()!=null?childrenUser.getMobilePhone():"");
			vo.setEmployeeName(childrenUser.getUserName()!=null?childrenUser.getUserName():"");
			vo.setStatus(childrenUser.getState()!=null?Short.parseShort(childrenUser.getState().toString()):null);
			vo.setStatusName(StateChange(vo.getStatus()));
			vo.setLinkName(childrenUser.getUserName()!=null?childrenUser.getUserName():"");
			vo.setNoPassReason(childrenUser.getNoPassReason());
			
			vo.setPlatformId(childrenUser.getParentId());
			UPlatformSupplyer psup = iChildrenAccountDAO.getPSupplyerById(childrenUser.getParentId());
			if(psup!=null){
				vo.setPlatformName(psup.getSupplyName());
			}else{
				UBrandSupplyer bsup = iChildrenAccountDAO.getBSupplyerById(childrenUser.getParentId());
				if(bsup!=null)
					vo.setPlatformName(bsup.getSupplyName());
			}
				
		}else{
			rm.setStatu(ReturnStatus.ParamError);
			rm.setStatusreson("该子供应商不存在！");
			return rm;
		}
		if(type==1){//type=1表示员工子账号
			
			UChildrenStaff childrenStaff = iChildrenAccountDAO.getUChildrenStaff(childrenId);
			if(childrenStaff!=null){
				vo.setDepartment(childrenStaff.getDepartment()!=null?childrenStaff.getDepartment():"");
			}
		}else{
			//供应商子账号
			
			List<String> childIdList = new ArrayList<String>();
			childIdList.add(childrenId);
			//发布的商品数列表
			List<Object[]> publishProductCountList = null;
			//待审核的商品数列表
			List<Object[]> pendingProductCountList = null;
			if(childIdList!=null&&childIdList.size()>0){
				publishProductCountList = iChildrenAccountDAO.getPublishProductCountByChildSupplyer(childIdList);
				pendingProductCountList = iChildrenAccountDAO.getPendingProductCountByChildSupplyer(childIdList);
			}
			if(publishProductCountList!=null && publishProductCountList.size()>0){
				for(Object[] pub:publishProductCountList){
					if(vo.getAccountId().equals(pub[1])){
						//发布的商品数
						vo.setTotalProduct(Integer.valueOf(pub[0].toString()));
					}
				}
			}
			if(pendingProductCountList!=null && pendingProductCountList.size()>0){
				for(Object[] pen:pendingProductCountList){
					if(vo.getAccountId().equals(pen[1])){
						//待审核的商品数
						vo.setWaitAuditProduct(Integer.valueOf(pen[0].toString()));
					}
				}
			}
			if(childrenSupplyer!=null){
				vo.setSupplierName(childrenSupplyer.getCompanyName()!=null?childrenSupplyer.getCompanyName():"");
				//省市区编码
				Integer provinceCode = childrenSupplyer.getProvince()!=null?childrenSupplyer.getProvince():-11;
				Integer cityCode = childrenSupplyer.getCity()!=null?childrenSupplyer.getCity():-11;
				Integer districtCode = childrenSupplyer.getArea()!=null?childrenSupplyer.getArea():-11;
				
				//省市区名称
				String province= iRegionService.getNameByCode(provinceCode);
				String city= iRegionService.getNameByCode(cityCode);
				String district= iRegionService.getNameByCode(districtCode);
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
				
				//特殊处理省市区，配合pc前端输出
				if(provinceCode == 110000)
				{
					cityCode=110100;
					vo.setLocation(area);
				}
				if(provinceCode == 120000)
				{
					cityCode=120100;
					vo.setLocation(area);
				}
				if(provinceCode == 310000)
				{
					cityCode=310100;
					vo.setLocation(area);
				}
				if(provinceCode == 500000)
				{
					cityCode=500100;
					vo.setLocation(area);
				}
				
				vo.setProvince(provinceCode);
				vo.setCity(cityCode);
				vo.setArea(districtCode);
				//认证员信息
				vo.setVerifyWeiId(childrenSupplyer.getVerifier()!=null?childrenSupplyer.getVerifier():null);
				UVerifier verifier = baseDAO.get(UVerifier.class,childrenSupplyer.getVerifier()!=null?childrenSupplyer.getVerifier():-11);
				if(verifier!=null){
					vo.setVerifyName(verifier.getName()!=null?verifier.getName():"");
				}
				vo.setAddress(childrenSupplyer.getAddress()!=null?childrenSupplyer.getAddress():"");
			}
		}
		
		rm.setBasemodle(vo);
		rm.setStatu(ReturnStatus.Success);
		rm.setStatusreson("成功");
		return rm;
	}

	@Override
	public ReturnModel deleteChildrenSupplyerOrStaff(String childrenId,Integer type,
			LoginUser user) {
		ReturnModel model = new ReturnModel();
		if(childrenId==null||childrenId.equals("")){
			model.setStatu(ReturnStatus.ParamError);
			model.setStatusreson("子账号id为空");
			return model;
		}
		
		UChildrenUser children = iChildrenAccountDAO.getUChildrenUser(childrenId);

		Boolean haveOwner = false;
		if(children==null)
		{
			model.setStatu(ReturnStatus.ParamError);
			model.setStatusreson("子账号不存在");
			return model;
		}
		else if(children.getParentId()!=null && user.getWeiID().equals(children.getParentId())){
			haveOwner=true;
		}
		
		//认证员只有在还没有审核的时候可以修改
		if(children.getState()==2)
		{
			UChildrenSupplyer childrenSupplyer = baseDAO.get(UChildrenSupplyer.class,childrenId);
			if(childrenSupplyer == null){
				;
			}else if(childrenSupplyer.getVerifier()!=null && user.getWeiID().equals(childrenSupplyer.getVerifier())){
				haveOwner=true;
			}
		}
		
		if(!haveOwner){
			model.setStatu(ReturnStatus.ParamError);
			model.setStatusreson("你没有权限删除该子用户！");
			return model;
		}
		
		//删除子账号用户的基本信息
		iChildrenAccountDAO.deleteUChildrenUser(childrenId);

		model.setStatu(ReturnStatus.Success);
		model.setStatusreson("成功");
		return model;
	}

	@Override
	public ReturnModel editPassword(String childrenId, String newPassword,
			String oldPassword) {
		ReturnModel model = new ReturnModel();
		UChildrenUser childrenUser = iChildrenAccountDAO.getUChildrenUser(childrenId);
		if(childrenUser!=null){
			String oldPasswordTemp = childrenUser.getPassWord()!=null?childrenUser.getPassWord():"";
			//如果旧登录密码输入错误
			if(!oldPasswordTemp.equals(oldPassword)){
				model.setStatu(ReturnStatus.DataError);
				model.setStatusreson("旧登录密码输入错误");
				return model;
			}else{
				//设置新密码
				childrenUser.setPassWord(newPassword);
			}
		}else{
			model.setStatu(ReturnStatus.DataError);
			model.setStatusreson("该用户不存在");
			return model;
		}
		model.setStatu(ReturnStatus.Success);
		model.setStatusreson("成功");
		return model;
	}

	@Override
	public ReturnModel batchDeleteChildrenUser(String[] idList,LoginUser user) {
		ReturnModel model = new ReturnModel();
		if(idList==null || idList.length<1){
			model.setStatu(ReturnStatus.ParamError);
			model.setStatusreson("子账号id为空");
			return model;
		}
		List<UChildrenUser> childrenList = iChildrenAccountDAO.getChildrenById(idList);
		if(childrenList==null || childrenList.size()<1)
		{
			model.setStatu(ReturnStatus.DataError);
			model.setStatusreson("子账号不存在！");
			return model;
		}
		List<String> tempList = new ArrayList<String>();
		List<String> statuList = new ArrayList<String>();
		//登录者是该子账号的上级
		for(UChildrenUser children:childrenList){
			if(children.getParentId()!=null && children.getParentId().equals(user.getWeiID()))
				tempList.add(children.getChildrenId());
			
			if(children.getState()!=null && children.getState().equals(Integer.parseInt(ChildrenSupplyerStatusEnum.waitAudit.toString())))
				statuList.add(children.getChildrenId());
		}
		
		//登录者是该子账号的推荐者，并且该子账号在审核状态
		List<UChildrenSupplyer> supList = iChildrenAccountDAO.getChildrenSupplyer(idList);
		if(supList!=null && supList.size()>0){
			for(UChildrenSupplyer sup:supList){
				if(sup.getVerifier()!=null && sup.getVerifier().equals(user.getWeiID())
						&& statuList.contains(sup.getChildrenId())
						&& !tempList.contains(sup.getChildrenId()))
					tempList.add(sup.getChildrenId());
			}
		}
		if(tempList.size()>0)
		   iChildrenAccountDAO.batchDeleteUChildrenUser(tempList);
		else
		{
			model.setStatu(ReturnStatus.DataError);
			model.setStatusreson("你没有权限删除子账号！");
			return model;
		}
		model.setStatu(ReturnStatus.Success);
		model.setStatusreson("成功");
		return model;
	}

	@Override
	public ReturnModel addChildrenSupplyerByVerifier(AddChildrenAccountDTO param) {
		ReturnModel model = new ReturnModel();
		if(param!=null){
			//判断传入的verifierWeiID有没有权限推荐子供应商
			if(param.getVerifyWeiId()!=null){
				//认证员有权限
				UVerifier verifier = baseDAO.get(UVerifier.class, param.getVerifyWeiId());
				Boolean havePower=false;
				if(verifier!=null && verifier.getType()!=null && verifier.getType()>0){
					havePower=true;
				}
				//判断是否是此平台号供应商下面的代理商
				List<USupplyChannel> channel = iChildrenAccountDAO.getAgentSupplierByID(param.getVerifyWeiId());
				if(channel!=null && channel.size()>0){
					for(USupplyChannel ch:channel)
					{
						if(ch.getSupplyId()!=null && ch.getSupplyId().equals(param.getPlatformWeiId())){
							havePower=true;
							break;
						}
					}
				}
				
				//否则就没有权限
				if(!havePower)
				{
					model.setStatu(ReturnStatus.ParamError);
					model.setStatusreson("你没有权限推荐子供应商");
					return model;
				}
			}else{
				model.setStatu(ReturnStatus.ParamError);
				model.setStatusreson("请传入推荐的认证员");
				return model;
			}
			
			//子账号id
			String childrenID="";
			//子账号用户
			UChildrenUser obj = new UChildrenUser();
			//获取子账号生成表对象
			UChildrenCreate childrenCreate = iChildrenAccountDAO.getUChildrenCreate(param.getPlatformWeiId());
			//如果已有子账号的情况
			if(childrenCreate!=null){
				//把子账号的id进行逻辑自增
				int temp=childrenCreate.getCreateId()+1;
				childrenCreate.setCreateId(temp);  //更新数据库中的对应父微店号的createID
				//子账号id
				String childId=childrenCreate.getParentId()+"_";
				//根据数值位数，进行补零
				if(temp<10){
					childId+="000"+temp;
				}
				if(10<=temp&&temp<100){
					childId+="00"+temp;
				}
				if(100<=temp&&temp<1000){
					childId+="0"+temp;
				}
				obj.setChildrenId(childId);
				childrenID=childId;
			}else{  
				//如果是第一次添加子账号的情况
				UChildrenCreate childrenCreateTemp = new UChildrenCreate();
				childrenCreateTemp.setParentId(param.getPlatformWeiId());
				childrenCreateTemp.setCreateId(1);
				iChildrenAccountDAO.addChildrenCreate(childrenCreateTemp);
				childrenID=param.getPlatformWeiId()+"_0001";
				obj.setChildrenId(childrenID);
			}
			//父微店号
			obj.setParentId(param.getPlatformWeiId());
			//创建时间
			obj.setCreateTime(new Date());
			//创建人
			obj.setCreateUser(param.getVerifyWeiId()!=null?param.getVerifyWeiId().toString():"");
			//手机号
			obj.setMobilePhone(param.getMobilePhone());
			//负责人
			obj.setUserName(param.getUserName());
			//由认证员推荐的子供应商处于待审核状态
			obj.setState(Integer.parseInt(ChildrenSupplyerStatusEnum.waitAudit.toString()));
			//区分是添加员工子账号还是添加供应商子账号
			obj.setType(param.getType());
			//认证员推荐的需要审核，so 是待审核状态 见枚举ChildrenSupplyerStatusEnum
			obj.setState(2);
			
			iChildrenAccountDAO.addChildrenUser(obj);
			UChildrenSupplyer supplyer = new UChildrenSupplyer();
			//认证员
			supplyer.setVerifier(param.getVerifyWeiId());
			supplyer.setChildrenId(childrenID);
			supplyer.setCompanyName(param.getCompanyName());
			supplyer.setIsOrderSend(param.getIsOrderSend());
			supplyer.setAddress(param.getAddress());
			//区编码
			Integer district = param.getLocation();
			supplyer.setArea(district);
			//市编码
			Integer cityCode = null;
			//省编码
			Integer provinceCode = null;
			//处理省市区
			Map<String, Object> map = processReginal(district,provinceCode,cityCode);
			supplyer.setProvince(map.get("province")!=null?(Integer)map.get("province"):null);
			supplyer.setCity(map.get("city")!=null?(Integer)map.get("city"):null);
			iChildrenAccountDAO.addChildrenSupplyerInfo(supplyer);
			
			ChildrenAccountIdVO acountid = new ChildrenAccountIdVO();
			acountid.setAccountId(childrenID);
			model.setBasemodle(acountid);
			model.setStatu(ReturnStatus.Success);
			model.setStatusreson("成功");
			return model;
		}else{
			model.setStatu(ReturnStatus.ParamError);
			model.setStatusreson("传入的数据为空");
			return model;
		}
	}

	@Override
	public ReturnModel verifyChildrenSupply(VerifySupplierDTO param,LoginUser user) {
		// TODO 自动生成的方法存根
		ReturnModel model = new ReturnModel();
		if(param.getAccountId()==null||param.getAccountId().equals("")){
			model.setStatu(ReturnStatus.ParamError);
			model.setStatusreson("子账号id为空");
			return model;
		}
		
		UChildrenUser children = iChildrenAccountDAO.getUChildrenUser(param.getAccountId());
		if(children == null)
		{
			model.setStatu(ReturnStatus.DataError);
			model.setStatusreson("该子用户不存在！");
			return model;
		}
		
		if(children.getType()!=2)
		{
			model.setStatu(ReturnStatus.DataError);
			model.setStatusreson("该子用户不是子供应商，不需要审核！");
			return model;
		}
		
		if(children.getState()!=2)
		{
			model.setStatu(ReturnStatus.DataError);
			model.setStatusreson("该子用户不在待审核状态！");
			return model;
		}
		
		if(children.getParentId()!=null && !children.getParentId().equals(user.getWeiID()))
		{
			model.setStatu(ReturnStatus.DataError);
			model.setStatusreson("你没有权限审核该子用户！");
			return model;
		}
		
		if(param.getIsPass()!=3 &&param.getIsPass()!=4)
		{
			model.setStatu(ReturnStatus.DataError);
			model.setStatusreson("传入的审核状态不正确！");
			return model;
		}
		
		
		//删除子账号用户的基本信息
		iChildrenAccountDAO.verifyUChildrenUser(param);

		model.setStatu(ReturnStatus.Success);
		model.setStatusreson("成功");
		return model;

	}

	@Override
	public ReturnModel batchVerifyChildrenSupply(String[] idList, LoginUser user,Integer statu) {
		// TODO 自动生成的方法存根
		ReturnModel model = new ReturnModel();
		if(idList==null || idList.length<1){
			model.setStatu(ReturnStatus.ParamError);
			model.setStatusreson("子账号id为空");
			return model;
		}
		List<UChildrenUser> childrenList = iChildrenAccountDAO.getChildrenById(idList);
		if(childrenList==null || childrenList.size()<1)
		{
			model.setStatu(ReturnStatus.DataError);
			model.setStatusreson("子账号不存在！");
			return model;
		}
		List<String> tempList = new ArrayList<String>();
		for(UChildrenUser children:childrenList){
			if(children.getParentId().equals(user.getWeiID()) 
				&& children.getType()==2
				&& children.getState()==2)
				tempList.add(children.getChildrenId());
		}
		
		if(tempList.size()>0)
		   iChildrenAccountDAO.batchVerifyChildrenUser(tempList, statu);
		else
		{
			model.setStatu(ReturnStatus.DataError);
			model.setStatusreson("你没有权限审核子账号！");
			return model;
		}
		model.setStatu(ReturnStatus.Success);
		model.setStatusreson("成功");
		return model;

	}

	@Override
	public ReturnModel getPlatformSupplierOption() {
		// TODO 自动生成的方法存根
		ReturnModel model=new ReturnModel();
		List<PlatformSupplierKVVO> voList = new ArrayList<PlatformSupplierKVVO>();
		List<UPlatformSupplyer> supplyerList = iChildrenAccountDAO.getPlatformSuppler();
		if(supplyerList!=null && supplyerList.size()>0){
			for(UPlatformSupplyer supplyer:supplyerList){
				PlatformSupplierKVVO vo=new PlatformSupplierKVVO();
				vo.setCompanyWeiid(supplyer.getWeiId());
				vo.setCompanyName(supplyer.getSupplyName());
				
				voList.add(vo);
			}
		}
		
		model.setBasemodle(voList);
		model.setStatu(ReturnStatus.Success);
		model.setStatusreson("成功！");
        return model;
	}

	
}
