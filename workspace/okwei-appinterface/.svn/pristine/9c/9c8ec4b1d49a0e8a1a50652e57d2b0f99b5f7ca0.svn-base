package com.okwei.appinterface.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.okwei.appinterface.bean.dto.ChildrenAccountParam;
import com.okwei.appinterface.bean.dto.StaffChildrenParamDTO;
import com.okwei.bean.dto.user.SupplierChildrenParamDTO;
import com.okwei.bean.dto.user.VerifySupplierDTO;
import com.okwei.bean.dto.AddChildrenAccountDTO;
import com.okwei.bean.vo.ChildAccountListVO;
import com.okwei.bean.vo.ChildrenAccountVO;
import com.okwei.bean.vo.LoginUser;
import com.okwei.bean.vo.ReturnModel;
import com.okwei.bean.vo.ReturnStatus;
import com.okwei.common.JsonUtil;
import com.okwei.common.Limit;
import com.okwei.common.PageResult;
import com.okwei.service.user.IChildrenAccountService;
import com.okwei.util.DES;
import com.okwei.util.MD5Encrypt;
import com.okwei.web.base.SSOController;
import com.sdicons.json.mapper.MapperException;

@RequestMapping("/ChildrenAccount")
@RestController
public class ChildrenAccountController extends SSOController {
	@Autowired
	private IChildrenAccountService iChildrenAccountService;
	
	/**
	 * 获取员工子账号列表
	 * @param param
	 * @return
	 * @throws MapperException
	 */
	@RequestMapping("/getStaffChildrenAccountList")
	public String getStaffChildrenAccountList(ChildrenAccountParam param) throws MapperException {
		ReturnModel rm= new ReturnModel();
		LoginUser user =super.getUserOrSub();
		if(user==null){
			rm.setStatu(ReturnStatus.LoginError);
			rm.setStatusreson("登陆已过期，请重新登陆");
			return JsonUtil.objectToJsonStr(rm);
		}
		PageResult<ChildAccountListVO> pageResult = iChildrenAccountService.getChildrenStaffList(user.getWeiID(),Limit.buildLimit(param.getPageIndex(), param.getPageSize()));
		rm.setBasemodle(pageResult);
		rm.setStatu(ReturnStatus.Success);
		rm.setStatusreson("成功!");
		return JsonUtil.objectToJsonStr(rm);
	}
	/**
	 * 添加员工子账号
	 * @param param
	 * @return
	 * @throws MapperException
	 */
	@RequestMapping(value="/addStaffChildrenAccount",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public String addStaffChildrenAccount(String account) throws MapperException {
		ReturnModel rm= new ReturnModel();
		LoginUser user =super.getUserOrSub();
		if(user==null){
			rm.setStatu(ReturnStatus.LoginError);
			rm.setStatusreson("登陆已过期，请重新登陆");
			return JsonUtil.objectToJsonStr(rm);
		}
		SupplierChildrenParamDTO param=new SupplierChildrenParamDTO();
		try
		{
			param = (SupplierChildrenParamDTO) JsonUtil.jsonToBean(account, SupplierChildrenParamDTO.class);
			if(param == null)
			{
				rm.setStatu(ReturnStatus.ParamError);
				rm.setStatusreson("传入参数为null！");
				return JsonUtil.objectToJsonStr(rm);
			}
		}
		catch(Exception e)
		{
			rm.setStatu(ReturnStatus.ParamError);
			rm.setStatusreson("参数转换实体出错！");
			return JsonUtil.objectToJsonStr(rm);
		}
		
		AddChildrenAccountDTO dto = new AddChildrenAccountDTO();
		String pwd = "";
		if(param.getPassword()!=null && !param.getPassword().equals("")){
			pwd = MD5Encrypt.encrypt(DES.decrypt(param.getPassword(),"")).toUpperCase();
		}
		dto.setPassWord(pwd);
		dto.setUserName(param.getLinkName());
		dto.setMobilePhone(param.getPhone());
		dto.setDepartment(param.getDepartment());
	    
		dto.setType(1);
		rm = iChildrenAccountService.addChildrenSupplyerOrStaff(dto, user);
		return JsonUtil.objectToJsonStr(rm);
	}
	/**
	 * 修改员工子账号
	 * @param param
	 * @return
	 * @throws MapperException
	 */
	@RequestMapping("/editStaffChildrenAccount")
	public String editStaffChildrenAccount(String account) throws MapperException {
		ReturnModel rm= new ReturnModel();
		LoginUser user = super.getUserOrSub();
		if(user==null){
			rm.setStatu(ReturnStatus.LoginError);
			rm.setStatusreson("登陆已过期，请重新登陆");
			return JsonUtil.objectToJsonStr(rm);
		}
		
		SupplierChildrenParamDTO param=new SupplierChildrenParamDTO();
		try
		{
			param = (SupplierChildrenParamDTO) JsonUtil.jsonToBean(account, SupplierChildrenParamDTO.class);
			if(param == null)
			{
				rm.setStatu(ReturnStatus.ParamError);
				rm.setStatusreson("传入参数为null！");
				return JsonUtil.objectToJsonStr(rm);
			}
		}
		catch(Exception e)
		{
			rm.setStatu(ReturnStatus.ParamError);
			rm.setStatusreson("参数转换实体出错！");
			return JsonUtil.objectToJsonStr(rm);
		}
		
		AddChildrenAccountDTO dto = new AddChildrenAccountDTO();
		String pwd = "";
		if(param.getPassword()!=null && !param.getPassword().equals("")){
			pwd = MD5Encrypt.encrypt(DES.decrypt(param.getPassword(),"")).toUpperCase();
		}
		dto.setPassWord(pwd);
		dto.setUserName(param.getLinkName());
		dto.setMobilePhone(param.getPhone());
		dto.setDepartment(param.getDepartment());
		dto.setChildrenId(param.getAccountId());
		dto.setType(1);
		rm = iChildrenAccountService.editChildrenSupplyerOrStaff(dto, user);
		return JsonUtil.objectToJsonStr(rm);
	}
	
	/**
	 * 删除子账号
	 * @param param
	 * @return
	 * @throws MapperException
	 */
	@RequestMapping("/deleteChildrenAccount")
	public String deleteChildrenAccount(ChildrenAccountParam param) throws MapperException {
		ReturnModel rm= new ReturnModel();
		LoginUser user = super.getUserOrSub();
		if(user==null){
			rm.setStatu(ReturnStatus.LoginError);
			rm.setStatusreson("登陆已过期，请重新登陆");
			return JsonUtil.objectToJsonStr(rm);
		}
		rm = iChildrenAccountService.deleteChildrenSupplyerOrStaff(param.getAccountId(),null, user);
		return JsonUtil.objectToJsonStr(rm);
	}
	
	/**
	 * 审核子供应商
	 * @param param
	 * @return
	 * @throws MapperException
	 */
	@RequestMapping("/verifyChildrenAccount")
	public String verifyChildrenAccount(String model) throws MapperException {
		ReturnModel rm= new ReturnModel();
		LoginUser user = super.getUserOrSub();
		if(user==null){
			rm.setStatu(ReturnStatus.LoginError);
			rm.setStatusreson("登陆已过期，请重新登陆");
			return JsonUtil.objectToJsonStr(rm);
		}
		
		VerifySupplierDTO param = new VerifySupplierDTO();
		try
		{
			param = (VerifySupplierDTO) JsonUtil.jsonToBean(model, VerifySupplierDTO.class);
			if(param == null)
			{
				rm.setStatu(ReturnStatus.ParamError);
				rm.setStatusreson("传入参数为null！");
				return JsonUtil.objectToJsonStr(rm);
			}
		}
		catch(Exception e)
		{
			rm.setStatu(ReturnStatus.ParamError);
			rm.setStatusreson("参数转换实体出错！");
			return JsonUtil.objectToJsonStr(rm);
		}
		
		String pwd = "";
		if(param.getPassword()!=null && !param.getPassword().equals("")){
			pwd = MD5Encrypt.encrypt(DES.decrypt(param.getPassword(),"")).toUpperCase();
		}
		param.setPassword(pwd);
		rm = iChildrenAccountService.verifyChildrenSupply(param,user);
		return JsonUtil.objectToJsonStr(rm);
	}
	/**
	 * 批量删除子账号
	 * @param param
	 * @return
	 * @throws MapperException
	 */
	@RequestMapping("/batchDeleteChildrenAccount")
	public String batchDeleteChildrenAccount(ChildrenAccountParam param) throws MapperException {
		ReturnModel rm= new ReturnModel();
		LoginUser user = super.getUserOrSub();
		if(user==null){
			rm.setStatu(ReturnStatus.LoginError);
			rm.setStatusreson("登陆已过期，请重新登陆");
			return JsonUtil.objectToJsonStr(rm);
		}
		String[] params ;
		try
		{
			params = (String[]) JsonUtil.jsonToBean(param.getAccountIdList(), String[].class);
			if(params == null)
			{
				rm.setStatu(ReturnStatus.ParamError);
				rm.setStatusreson("传入参数为null！");
				return JsonUtil.objectToJsonStr(rm);
			}
		}
		catch(Exception e)
		{
			rm.setStatu(ReturnStatus.ParamError);
			rm.setStatusreson("参数转换实体出错！");
			return JsonUtil.objectToJsonStr(rm);
		}
		rm = iChildrenAccountService.batchDeleteChildrenUser(params,user);
		return JsonUtil.objectToJsonStr(rm);
	}
	
	/**
	 * 批量审核子供应商
	 * @param param
	 * @return
	 * @throws MapperException
	 */
	@RequestMapping("/batchVerifyChildrenAccount")
	public String batchVerifyChildrenAccount(ChildrenAccountParam param) throws MapperException {
		ReturnModel rm= new ReturnModel();
		LoginUser user = super.getUserOrSub();
		if(user==null){
			rm.setStatu(ReturnStatus.LoginError);
			rm.setStatusreson("登陆已过期，请重新登陆");
			return JsonUtil.objectToJsonStr(rm);
		}
		String[] params ;
		try
		{
			params = (String[]) JsonUtil.jsonToBean(param.getAccountIdList(), String[].class);
			if(params == null)
			{
				rm.setStatu(ReturnStatus.ParamError);
				rm.setStatusreson("传入参数为null！");
				return JsonUtil.objectToJsonStr(rm);
			}
		}
		catch(Exception e)
		{
			rm.setStatu(ReturnStatus.ParamError);
			rm.setStatusreson("参数转换实体出错！");
			return JsonUtil.objectToJsonStr(rm);
		}
		rm = iChildrenAccountService.batchVerifyChildrenSupply(params, user, param.getStatus());
		return JsonUtil.objectToJsonStr(rm);
	}
	/**
	 * 获取员工子账号详情
	 * @param param
	 * @return
	 * @throws MapperException
	 */
	@RequestMapping("/getChildrenStaffDetail")
	public String getChildrenStaffDetail(ChildrenAccountParam param) throws MapperException {
		ReturnModel rm= new ReturnModel();
		LoginUser user = super.getUserOrSub();
		if(user==null){
			rm.setStatu(ReturnStatus.LoginError);
			rm.setStatusreson("登陆已过期，请重新登陆");
			return JsonUtil.objectToJsonStr(rm);
		}
		rm = iChildrenAccountService.getChildrenSupplyerOrStaff(param.getAccountId(),1,user);

		return JsonUtil.objectToJsonStr(rm);
	}
	
	/**
	 * 获取供应商子账号详情
	 * @param param
	 * @return
	 * @throws MapperException
	 */
	@RequestMapping("/getChildrenSupplyerDetail")
	public String getChildrenSupplyerDetail(ChildrenAccountParam param) throws MapperException {
		ReturnModel rm= new ReturnModel();
		LoginUser user = super.getUserOrSub();
		if(user==null){
			rm.setStatu(ReturnStatus.LoginError);
			rm.setStatusreson("登陆已过期，请重新登陆");
			return JsonUtil.objectToJsonStr(rm);
		}
		rm = iChildrenAccountService.getChildrenSupplyerOrStaff(param.getAccountId(),2,user);
		
		return JsonUtil.objectToJsonStr(rm);
	}
	
	
	/**
	 * 获取平台号子供应商列表
	 * @param param
	 * @return
	 * @throws MapperException
	 */
	@RequestMapping("/getChildrenSupplyerList")
	public String getChildrenSupplyerList(ChildrenAccountParam param) throws MapperException {
		ReturnModel rm= new ReturnModel();
		LoginUser user = super.getUserOrSub();
		if(user==null){
			rm.setStatu(ReturnStatus.LoginError);
			rm.setStatusreson("登陆已过期，请重新登陆");
			return JsonUtil.objectToJsonStr(rm);
		}
		PageResult<ChildAccountListVO> pageResult = iChildrenAccountService.getChildrenSupplyerList(user.getWeiID(),Limit.buildLimit(param.getPageIndex(), param.getPageSize()),param.getStatus(),param.getFrom());
		rm.setBasemodle(pageResult);
		rm.setStatu(ReturnStatus.Success);
		rm.setStatusreson("成功!");
		return JsonUtil.objectToJsonStr(rm);
	}
	
	/**
	 * 获取推荐的供应商列表
	 * @param param
	 * @return
	 * @throws MapperException
	 */
	@RequestMapping("/getChildrenSupplyerByRecommand")
	public String getChildSupplyerByRecommand(ChildrenAccountParam param)throws MapperException {
		ReturnModel rm= new ReturnModel();
		LoginUser user = super.getUserOrSub();
		if(user==null){
			rm.setStatu(ReturnStatus.LoginError);
			rm.setStatusreson("登陆已过期，请重新登陆");
			return JsonUtil.objectToJsonStr(rm);
		}
		PageResult<ChildAccountListVO> pageResult = iChildrenAccountService.getChildrenSByRecommandAPP(user.getWeiID(),Limit.buildLimit(param.getPageIndex(), param.getPageSize()),param.getStatus());
		rm.setBasemodle(pageResult);
		rm.setStatu(ReturnStatus.Success);
		rm.setStatusreson("成功!");
		return JsonUtil.objectToJsonStr(rm);
	}
	
	/**
	 * 添加平台号子供应商
	 * @param param
	 * @return
	 * @throws MapperException
	 */
	@RequestMapping("/addPlatformChildrenSupplyer")
	public String addPlatformChildrenSupplyer(String supplier) throws MapperException {
		ReturnModel rm= new ReturnModel();
		LoginUser user = super.getUserOrSub();
		if(user==null){
			rm.setStatu(ReturnStatus.LoginError);
			rm.setStatusreson("登陆已过期，请重新登陆");
			return JsonUtil.objectToJsonStr(rm);
		}
		
		SupplierChildrenParamDTO param = new SupplierChildrenParamDTO();
		
		try
		{
			param = (SupplierChildrenParamDTO) JsonUtil.jsonToBean(supplier, SupplierChildrenParamDTO.class);
			if(param == null)
			{
				rm.setStatu(ReturnStatus.ParamError);
				rm.setStatusreson("传入参数为null！");
				return JsonUtil.objectToJsonStr(rm);
			}
		}
		catch(Exception e)
		{
			rm.setStatu(ReturnStatus.ParamError);
			rm.setStatusreson("参数转换实体出错！");
			return JsonUtil.objectToJsonStr(rm);
		}
		AddChildrenAccountDTO dto = new AddChildrenAccountDTO();
		String pwd = "";
		if(param.getPassword()!=null && !param.getPassword().equals("")){
			pwd = MD5Encrypt.encrypt(DES.decrypt(param.getPassword(),"")).toUpperCase();
		}
		dto.setPassWord(pwd);
		dto.setUserName(param.getLinkName());
		dto.setMobilePhone(param.getPhone());
		dto.setCompanyName(param.getCompanyName());
		dto.setLocation(param.getLocation());
		dto.setAddress(param.getAddress());
		dto.setIsOrderSend(param.getIsOrderSend());
		dto.setType(2);
		rm = iChildrenAccountService.addChildrenSupplyerOrStaff(dto, user);
		return JsonUtil.objectToJsonStr(rm);
	}
	
	/**
	 * 认证员推荐添加平台号子供应商
	 * @param param
	 * @return
	 * @throws MapperException
	 */
	@RequestMapping("/addChildrenSupplyerByVerifier")
	public String addChildrenSupplyerByVerifier(String supplier) throws MapperException {
		ReturnModel rm= new ReturnModel();
		LoginUser user = super.getUserOrSub();
		if(user==null){
			rm.setStatu(ReturnStatus.LoginError);
			rm.setStatusreson("登陆已过期，请重新登陆");
			return JsonUtil.objectToJsonStr(rm);
		}
        SupplierChildrenParamDTO param = new SupplierChildrenParamDTO();
		
		try
		{
			param = (SupplierChildrenParamDTO) JsonUtil.jsonToBean(supplier, SupplierChildrenParamDTO.class);
			if(param == null)
			{
				rm.setStatu(ReturnStatus.ParamError);
				rm.setStatusreson("传入参数为null！");
				return JsonUtil.objectToJsonStr(rm);
			}
		}
		catch(Exception e)
		{
			rm.setStatu(ReturnStatus.ParamError);
			rm.setStatusreson("参数转换实体出错！");
			return JsonUtil.objectToJsonStr(rm);
		}
		
		AddChildrenAccountDTO dto = new AddChildrenAccountDTO();
		dto.setVerifyWeiId(user.getWeiID());
		dto.setPlatformWeiId(param.getMerchantWeiId());
		dto.setUserName(param.getLinkName());
		dto.setMobilePhone(param.getPhone());
		dto.setCompanyName(param.getCompanyName());
		dto.setLocation(param.getLocation());
		dto.setAddress(param.getAddress());
		dto.setType(2);
		dto.setIsOrderSend(param.getIsOrderSend());
		rm = iChildrenAccountService.addChildrenSupplyerByVerifier(dto);
		return JsonUtil.objectToJsonStr(rm);
	}
	
	/**
	 * 修改平台号子供应商
	 * @param param
	 * @return
	 * @throws MapperException
	 */
	@RequestMapping("/editPlatformChildrenSupplyer")
	public String editPlatformChildrenSupplyer(String supplier) throws MapperException {
		ReturnModel rm= new ReturnModel();
		LoginUser user = super.getUserOrSub();
		if(user==null){
			rm.setStatu(ReturnStatus.LoginError);
			rm.setStatusreson("登陆已过期，请重新登陆");
			return JsonUtil.objectToJsonStr(rm);
		}
		
        SupplierChildrenParamDTO param = new SupplierChildrenParamDTO();
		
		try
		{
			param = (SupplierChildrenParamDTO) JsonUtil.jsonToBean(supplier, SupplierChildrenParamDTO.class);
			if(param == null)
			{
				rm.setStatu(ReturnStatus.ParamError);
				rm.setStatusreson("传入参数为null！");
				return JsonUtil.objectToJsonStr(rm);
			}
		}
		catch(Exception e)
		{
			rm.setStatu(ReturnStatus.ParamError);
			rm.setStatusreson("参数转换实体出错！");
			return JsonUtil.objectToJsonStr(rm);
		}
		AddChildrenAccountDTO dto = new AddChildrenAccountDTO();
		String pwd = "";
		if(param.getPassword()!=null && !param.getPassword().equals("")){
			pwd = MD5Encrypt.encrypt(DES.decrypt(param.getPassword(),"")).toUpperCase();
		}
		dto.setChildrenId(param.getAccountId());
		dto.setPassWord(pwd);
		dto.setUserName(param.getLinkName());
		dto.setMobilePhone(param.getPhone());
		dto.setCompanyName(param.getCompanyName());
		dto.setLocation(param.getLocation());
		dto.setAddress(param.getAddress());
		dto.setIsOrderSend(param.getIsOrderSend());
		dto.setType(2);
		rm = iChildrenAccountService.editChildrenSupplyerOrStaff(dto, user);
		return JsonUtil.objectToJsonStr(rm);
	}
	
	/**
	 * 获取所有平台号的键值对
	 * @return
	 * @throws MapperException
	 */
	@RequestMapping("/getPlatformSupplierKV")
	public String getPlatformSupplierKV() throws MapperException{
		ReturnModel rm= new ReturnModel();
		LoginUser user = super.getUserOrSub();
		if(user==null){
			rm.setStatu(ReturnStatus.LoginError);
			rm.setStatusreson("登陆已过期，请重新登陆");
			return JsonUtil.objectToJsonStr(rm);
		}
		
		rm = iChildrenAccountService.getPlatformSupplierOption();
		return JsonUtil.objectToJsonStr(rm);
	}
	
}
