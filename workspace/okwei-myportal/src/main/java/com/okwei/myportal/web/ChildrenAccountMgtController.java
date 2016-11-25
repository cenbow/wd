package com.okwei.myportal.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.okwei.bean.dto.AddChildrenAccountDTO;
import com.okwei.bean.dto.user.SupplierChildrenParamDTO;
import com.okwei.bean.dto.user.VerifySupplierDTO;
import com.okwei.bean.enums.ChildrenAccountTypeEnum;
import com.okwei.bean.vo.ChildAccountListVO;
import com.okwei.bean.vo.ChildrenAccountVO;
import com.okwei.bean.vo.LoginUser;
import com.okwei.bean.vo.ReturnModel;
import com.okwei.bean.vo.ReturnStatus;
import com.okwei.common.JsonUtil;
import com.okwei.common.Limit;
import com.okwei.common.PageResult;
import com.okwei.myportal.bean.dto.VerifyChildrenCountDTO;
import com.okwei.service.user.IChildrenAccountService;
import com.okwei.util.MD5Encrypt;
import com.okwei.web.base.SSOController;
import com.sdicons.json.mapper.MapperException;

@Controller
@RequestMapping(value = "/childrenAccount")
public class ChildrenAccountMgtController extends SSOController {
	
	@Autowired
	private IChildrenAccountService iChildrenAccountService;
	
	/**
	 * 获得子员工列表
	 * @param pageId
	 * @param model
	 * @return
	 */
	@RequestMapping("/getStaffChildrenAccountList")
	public String getStaffChildrenAccountList(@RequestParam(required = false, defaultValue = "1") int pageId,Model model){
	    LoginUser user = super.getUserOrSub();
		/*LoginUser user=new LoginUser();
		user.setWeiID((long) 1036799);*/
	    PageResult<ChildAccountListVO> pageResult=new PageResult<ChildAccountListVO>();
		if(user==null)
		{
			model.addAttribute("pageResult", pageResult);
			model.addAttribute("CompanyChildren", false);
		}else{
			Boolean CompanyChildren =user.judgePower("User_CompanyChildren");
			if(CompanyChildren)
			   pageResult = iChildrenAccountService.getChildrenStaffList(user.getWeiID(),Limit.buildLimit(pageId,10));
			model.addAttribute("pageResult", pageResult);
			model.addAttribute("CompanyChildren", CompanyChildren);
		}
		model.addAttribute("userinfo",user);
		return "childrenAccount/childrenStaffList";
	}
	
	/**
	 * 获取推荐供应商子账号列表
	 * @param pageId
	 * @param model
	 * @return
	 */
	@RequestMapping("/getRecommandSCList")
	public String getSupplierChildrenAccountList(@RequestParam(required = false, defaultValue = "1") int pageId,Model model){
		LoginUser user = super.getUser();
		/*LoginUser user=new LoginUser();
		user.setWeiID((long) 1036799);*/
		PageResult<ChildrenAccountVO> pageResult = new PageResult<ChildrenAccountVO>();
		if(user==null)
		{
			model.addAttribute("pageResult", pageResult);
			model.addAttribute("Recommend", false);
			model.addAttribute("SupplierList", false);
		}else{
			Boolean recommend = user.judgePower("Supply_Recommend");
			Boolean supplierList =user.judgePower("Supply_SupplierList");
			//recommend=true;
			//supplierList=true;
			if(recommend)
			  pageResult = iChildrenAccountService.getChildrenSupplyerListByRecommand(user.getWeiID(), Limit.buildLimit(pageId,10), null);
			model.addAttribute("pageResult", pageResult);
			model.addAttribute("Recommend", recommend);
			model.addAttribute("SupplierList", supplierList);
		}
		
		model.addAttribute("userinfo",user);
		return "childrenAccount/recommandSupplierList";
	}
	
	/**
	 * 获取自己添加的子供应商
	 * @param pageId
	 * @param model
	 * @return
	 */
	@RequestMapping("/getSelfSCList")
	public String getSelfAddSupplierList(@RequestParam(required = false, defaultValue = "1") int pageId,Model model){
		LoginUser user = super.getUserOrSub();
		/*LoginUser user=new LoginUser();
		user.setWeiID((long) 1036799);*/
		PageResult<ChildrenAccountVO> pageResult = new PageResult<ChildrenAccountVO>();
		if(user==null){
			model.addAttribute("pageResult", pageResult);
			model.addAttribute("Recommend", false);
			model.addAttribute("SupplierList", false);
		}else{
			Boolean recommend = user.judgePower("Supply_Recommend");
			Boolean supplierList =user.judgePower("Supply_SupplierList");
			//recommend=true;
			//supplierList=true;
			if(supplierList)
			  pageResult = iChildrenAccountService.getChildrenSupplyerListPC(user.getWeiID(),Limit.buildLimit(pageId, 10),-1, 0);
			model.addAttribute("pageResult", pageResult);
			model.addAttribute("Recommend", recommend);
			model.addAttribute("SupplierList", supplierList);
		}
		model.addAttribute("userinfo",user);
		return "childrenAccount/selfAddSupplierList";
	}
	
	/**
	 * 获取自己添加的子供应商
	 * @param pageId
	 * @param model
	 * @return
	 */
	@RequestMapping("/getRecSCList")
	public String getRecAddSupplierList(@RequestParam(required = false, defaultValue = "1") int pageId,Model model){
		LoginUser user = super.getUserOrSub();
		/*LoginUser user=new LoginUser();
		user.setWeiID((long) 1036799);*/
		PageResult<ChildrenAccountVO> pageResult = new PageResult<ChildrenAccountVO>();
		if(user == null){
			model.addAttribute("pageResult", pageResult);
			model.addAttribute("Recommend", false);
			model.addAttribute("SupplierList", false);
		}else{
			Boolean recommend = user.judgePower("Supply_Recommend");
			Boolean supplierList =user.judgePower("Supply_SupplierList");
			//recommend=true;
			//supplierList=true;
			if(supplierList)
			   pageResult = iChildrenAccountService.getChildrenSupplyerListPC(user.getWeiID(),Limit.buildLimit(pageId, 10),-1, 1);
			model.addAttribute("pageResult", pageResult);
			model.addAttribute("Recommend", recommend);
			model.addAttribute("SupplierList", supplierList);
		}
		model.addAttribute("userinfo",user);
		return "childrenAccount/recAddSupplierList";
	}
	
	/**
	 * 添加员工子账号
	 * @param param
	 * @return
	 * @throws MapperException
	 */
	@ResponseBody
	@RequestMapping("/addStaffChildrenAccount")
	public String addStaffChildrenAccount(@ModelAttribute("queryParam")AddChildrenAccountDTO queryParam){
		ReturnModel rm= new ReturnModel();
		LoginUser user = super.getUserOrSub();
		/*LoginUser user=new LoginUser();
		user.setWeiID((long) 1036799);*/
		if(user ==null){
			rm.setStatu(ReturnStatus.ParamError);
			rm.setStatusreson("用户未登录！");
			return JsonUtil.objectToJson(rm);
		}
		Boolean CompanyChildren =user.judgePower("User_CompanyChildren");
		if(!CompanyChildren){
			rm.setStatu(ReturnStatus.ParamError);
			rm.setStatusreson("您没有权限操作！");
			return JsonUtil.objectToJson(rm);
		}
		String pwd = "";
		if(queryParam.getPassWord()!=null && !queryParam.getPassWord().equals("")){
			pwd = MD5Encrypt.encrypt(queryParam.getPassWord()).toUpperCase();
			queryParam.setPassWord(pwd);
		}
		queryParam.setType(Integer.parseInt(ChildrenAccountTypeEnum.childrenStaff.toString()));
		rm = iChildrenAccountService.addChildrenSupplyerOrStaff(queryParam,user);
		return JsonUtil.objectToJson(rm);
	}
	
	/**
	 * 添加推荐的子供应商
	 * @param queryParam
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/addRecChildrenSupplier")
	public String addRecSupplierChildrenAccount(@ModelAttribute("queryParam")SupplierChildrenParamDTO queryParam){
		ReturnModel rm= new ReturnModel();
		LoginUser user = super.getUserOrSub();
		/*LoginUser user=new LoginUser();
		user.setWeiID((long) 1036799);*/
		if(user ==null){
			rm.setStatu(ReturnStatus.ParamError);
			rm.setStatusreson("用户未登录！");
			return JsonUtil.objectToJson(rm);
		}
		Boolean recommend = user.judgePower("Supply_Recommend");
		if(!recommend){
			rm.setStatu(ReturnStatus.ParamError);
			rm.setStatusreson("您没有权限操作！");
			return JsonUtil.objectToJson(rm);
		}
		
		if(queryParam.getMerchantWeiId()==0)
		{
			rm.setStatu(ReturnStatus.ParamError);
			rm.setStatusreson("请选择公司！");
			return JsonUtil.objectToJson(rm);
		}
		AddChildrenAccountDTO dto = new AddChildrenAccountDTO();
		dto.setVerifyWeiId(user.getWeiID());
		dto.setPlatformWeiId(queryParam.getMerchantWeiId());
		dto.setUserName(queryParam.getLinkName());
		dto.setMobilePhone(queryParam.getPhone());
		dto.setCompanyName(queryParam.getCompanyName());
		dto.setLocation(queryParam.getLocation());
		dto.setAddress(queryParam.getAddress());
		dto.setType(2);
		dto.setIsOrderSend(queryParam.getIsOrderSend());
		rm = iChildrenAccountService.addChildrenSupplyerByVerifier(dto);
		return JsonUtil.objectToJson(rm);
	}
	
	/**
	 * 自己添加子供应商
	 * @param queryParam
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/selfAddSupplierChildren")
	public String selfAddSupplierChildren(@ModelAttribute("queryParam")SupplierChildrenParamDTO queryParam){
		ReturnModel rm= new ReturnModel();
		LoginUser user = super.getUserOrSub();
		/*LoginUser user=new LoginUser();
		user.setWeiID((long) 1036799);*/
		if(user ==null){
			rm.setStatu(ReturnStatus.ParamError);
			rm.setStatusreson("用户未登录！");
			return JsonUtil.objectToJson(rm);
		}
		
		Boolean supplierList =user.judgePower("Supply_SupplierList");
		if(!supplierList){
			rm.setStatu(ReturnStatus.ParamError);
			rm.setStatusreson("您没有权限操作！");
			return JsonUtil.objectToJson(rm);
		}
		
		AddChildrenAccountDTO dto = new AddChildrenAccountDTO();
		String pwd = "";
		if(queryParam.getPassword()!=null && !queryParam.getPassword().equals("")){
			pwd = MD5Encrypt.encrypt(queryParam.getPassword()).toUpperCase();
		}
		dto.setPassWord(pwd);
		dto.setUserName(queryParam.getLinkName());
		dto.setMobilePhone(queryParam.getPhone());
		dto.setCompanyName(queryParam.getCompanyName());
		dto.setLocation(queryParam.getLocation());
		dto.setAddress(queryParam.getAddress());
		dto.setIsOrderSend(queryParam.getIsOrderSend());
		dto.setType(2);
		rm = iChildrenAccountService.addChildrenSupplyerOrStaff(dto, user);
		return JsonUtil.objectToJson(rm);
	}
	
	/**
	 * 获取所有平台号的键值对
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getPlatformSupplierKV")
	public String getPlatformSupplierKV(){
		ReturnModel rm= new ReturnModel();
		LoginUser user = super.getUserOrSub();
		/*LoginUser user=new LoginUser();
		user.setWeiID((long) 1036799);*/
		if(user ==null){
			rm.setStatu(ReturnStatus.ParamError);
			rm.setStatusreson("用户未登录！");
			return JsonUtil.objectToJson(rm);
		}
		
		Boolean recommend = user.judgePower("Supply_Recommend");
		if(!recommend){
			rm.setStatu(ReturnStatus.ParamError);
			rm.setStatusreson("您没有权限操作！");
			return JsonUtil.objectToJson(rm);
		}
		rm = iChildrenAccountService.getPlatformSupplierOption();
		return JsonUtil.objectToJson(rm);
	}
	/**
	 * 编辑子供应商的准备
	 * @param childrenId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/editStaffChildrenPrepare")
	public String editStaffChildrenPrepare(String childrenId){
		ReturnModel rm= new ReturnModel();
		LoginUser user = super.getUserOrSub();
		/*LoginUser user=new LoginUser();
		user.setWeiID((long) 1036799);*/
		if(user ==null){
			rm.setStatu(ReturnStatus.ParamError);
			rm.setStatusreson("用户未登录！");
			return JsonUtil.objectToJson(rm);
		}
		
		Boolean CompanyChildren =user.judgePower("User_CompanyChildren");
		if(!CompanyChildren){
			rm.setStatu(ReturnStatus.ParamError);
			rm.setStatusreson("您没有权限操作！");
			return JsonUtil.objectToJson(rm);
		}
		rm = iChildrenAccountService.getChildrenSupplyerOrStaff(childrenId,1,user);
		return JsonUtil.objectToJson(rm);
	}
	/**
	 * 编辑员工子账号
	 * @param param
	 * @return
	 * @throws MapperException
	 */
	@ResponseBody
	@RequestMapping("/editStaffChildrenAccount")
	public String editStaffChildrenAccount(@ModelAttribute("queryParam")AddChildrenAccountDTO queryParam){
		ReturnModel rm= new ReturnModel();
		LoginUser user = super.getUserOrSub();
		/*LoginUser user=new LoginUser();
		user.setWeiID((long) 1036799);*/
		if(user ==null){
			rm.setStatu(ReturnStatus.ParamError);
			rm.setStatusreson("用户未登录！");
			return JsonUtil.objectToJson(rm);
		}
		Boolean CompanyChildren =user.judgePower("User_CompanyChildren");
		if(!CompanyChildren){
			rm.setStatu(ReturnStatus.ParamError);
			rm.setStatusreson("您没有权限操作！");
			return JsonUtil.objectToJson(rm);
		}
		queryParam.setType(1);
		String pwd = "";
		if(queryParam.getPassWord()!=null && !queryParam.getPassWord().equals("")){
			pwd = MD5Encrypt.encrypt(queryParam.getPassWord()).toUpperCase();
			queryParam.setPassWord(pwd);
		}
		rm = iChildrenAccountService.editChildrenSupplyerOrStaff(queryParam,user);
		return JsonUtil.objectToJson(rm);
	}
	/**
	 * 删除子账号
	 * @param param
	 * @return
	 * @throws MapperException
	 */
	@ResponseBody
	@RequestMapping("/deleteChildrenAccount")
	public String deleteChildrenAccount(String childrenId){
		ReturnModel rm= new ReturnModel();
		LoginUser user = super.getUserOrSub();
		/*LoginUser user=new LoginUser();
		user.setWeiID((long) 1036799);*/
		if(user ==null){
			rm.setStatu(ReturnStatus.ParamError);
			rm.setStatusreson("用户未登录！");
			return JsonUtil.objectToJson(rm);
		}
		Boolean CompanyChildren =user.judgePower("User_CompanyChildren");
		if(!CompanyChildren){
			rm.setStatu(ReturnStatus.ParamError);
			rm.setStatusreson("您没有权限操作！");
			return JsonUtil.objectToJson(rm);
		}
		rm = iChildrenAccountService.deleteChildrenSupplyerOrStaff(childrenId,null, user);
		return JsonUtil.objectToJson(rm);
	}
	
	/**
	 * 获取供应商详情
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getChilrenSupplierDetail")
	public String getChilrenSupplierDetail(String childrenId){
		ReturnModel rm= new ReturnModel();
		LoginUser user = super.getUserOrSub();
		if(user ==null){
			rm.setStatu(ReturnStatus.ParamError);
			rm.setStatusreson("用户未登录！");
			return JsonUtil.objectToJson(rm);
		}
		
		Boolean supplierList =user.judgePower("Supply_SupplierList");
		if(!supplierList){
			rm.setStatu(ReturnStatus.ParamError);
			rm.setStatusreson("您没有权限操作！");
			return JsonUtil.objectToJson(rm);
		}
		
		rm = iChildrenAccountService.getChildrenSupplyerOrStaff(childrenId,2,user);
		return JsonUtil.objectToJson(rm); 
	}
	
	/**
	 * 编辑子供应商
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/editChildrenSupplier")
	public String editChildrenSupplier(@ModelAttribute("queryParam")SupplierChildrenParamDTO queryParam){
		ReturnModel rm= new ReturnModel();
		LoginUser user = super.getUserOrSub();
		/*LoginUser user=new LoginUser();
		user.setWeiID((long) 1036799);*/
		if(user ==null){
			rm.setStatu(ReturnStatus.ParamError);
			rm.setStatusreson("用户未登录！");
			return JsonUtil.objectToJson(rm);
		}
		
		Boolean supplierList =user.judgePower("Supply_SupplierList");
		if(!supplierList){
			rm.setStatu(ReturnStatus.ParamError);
			rm.setStatusreson("您没有权限操作！");
			return JsonUtil.objectToJson(rm);
		}
		
		AddChildrenAccountDTO dto = new AddChildrenAccountDTO();
		String pwd = "";
		if(queryParam.getPassword()!=null && !queryParam.getPassword().equals("")){
			pwd = MD5Encrypt.encrypt(queryParam.getPassword()).toUpperCase();
		}
		dto.setChildrenId(queryParam.getAccountId());
		dto.setPassWord(pwd);
		dto.setUserName(queryParam.getLinkName());
		dto.setMobilePhone(queryParam.getPhone());
		dto.setCompanyName(queryParam.getCompanyName());
		dto.setLocation(queryParam.getLocation());
		dto.setAddress(queryParam.getAddress());
		dto.setIsOrderSend(queryParam.getIsOrderSend());
		dto.setType(2);
		rm = iChildrenAccountService.editChildrenSupplyerOrStaff(dto, user);
		return JsonUtil.objectToJson(rm); 
	}
	
	/**
	 * 审核子供应商
	 * @param queryParam
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/verifyChildrenSupplier")
	public String verifyChildrenSupplier(@ModelAttribute("queryParam")VerifyChildrenCountDTO queryParam){
		ReturnModel rm= new ReturnModel();
		LoginUser user = super.getUserOrSub();
		/*LoginUser user=new LoginUser();
		user.setWeiID((long) 1036799);*/
		if(user ==null){
			rm.setStatu(ReturnStatus.ParamError);
			rm.setStatusreson("用户未登录！");
			return JsonUtil.objectToJson(rm);
		}
		
		Boolean supplierList =user.judgePower("Supply_SupplierList");
		if(!supplierList){
			rm.setStatu(ReturnStatus.ParamError);
			rm.setStatusreson("您没有权限操作！");
			return JsonUtil.objectToJson(rm);
		}
		
		VerifySupplierDTO param = new VerifySupplierDTO();
		param.setAccountId(queryParam.getAccountId());
		param.setIsPass(queryParam.getStatus());
		param.setNotPassReson(queryParam.getStatusReason());
		param.setPassword(queryParam.getPassword());
		String pwd = "";
		if(param.getPassword()!=null && !param.getPassword().equals("")){
			pwd = MD5Encrypt.encrypt(param.getPassword()).toUpperCase();
		}
		param.setPassword(pwd);
		rm = iChildrenAccountService.verifyChildrenSupply(param,user);
		return JsonUtil.objectToJson(rm);
	}
	
}
