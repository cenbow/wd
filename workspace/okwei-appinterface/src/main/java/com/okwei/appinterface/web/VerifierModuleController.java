package com.okwei.appinterface.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.okwei.appinterface.bean.dto.VerifierProductAgentFollowParam;
import com.okwei.appinterface.bean.dto.VerifierProductAgentListParam;
import com.okwei.appinterface.bean.vo.ProductAgentFollowRecordVO;
import com.okwei.bean.domain.UAgentApplyFollowLog;
import com.okwei.bean.dto.MyAgentOrProductShopListDTO;
import com.okwei.bean.enums.VerifierRegionEnum;
import com.okwei.bean.vo.LoginUser;
import com.okwei.bean.vo.MyAgentOrProductShopListVO;
import com.okwei.bean.vo.PlatformProductShopListVO;
import com.okwei.bean.vo.ProductAgentDataVO;
import com.okwei.bean.vo.ReturnModel;
import com.okwei.bean.vo.ReturnStatus;
import com.okwei.common.JsonUtil;
import com.okwei.common.Limit;
import com.okwei.common.PageResult;
import com.okwei.service.user.IBasicAgentOrProductShopService;
import com.okwei.util.DateUtils;
import com.okwei.web.base.SSOController;
import com.sdicons.json.mapper.MapperException;

@RequestMapping("/VerifierModule")
@RestController
public class VerifierModuleController extends SSOController {
	@Autowired
	private IBasicAgentOrProductShopService iBasicAgentOrProductShopService;
	
	/**
	 * 认证员的代理商列表
	 * @param param
	 * @return
	 * @throws MapperException 
	 */
	@RequestMapping("/getMyVerifyAgentList")
	public String getMyVerifyAgentList(VerifierProductAgentListParam param) throws MapperException {
		ReturnModel rm= new ReturnModel();
		LoginUser user = super.getUserOrSub();
		if(user==null){
			rm.setStatu(ReturnStatus.LoginError);
			rm.setStatusreson("登陆已过期，请重新登陆");
			return JsonUtil.objectToJsonStr(rm);
		}
		MyAgentOrProductShopListDTO dto = new MyAgentOrProductShopListDTO();
		//模拟查询条件
		dto.setAuditState((short)param.getStatus());
		dto.setVerifierWeiId(user.getWeiID());
		PageResult<MyAgentOrProductShopListVO> pageResult = iBasicAgentOrProductShopService.getMyDevelopAgent(Limit.buildLimit(param.getPageIndex(), param.getPageSize()),dto);
		rm.setBasemodle(pageResult);
		rm.setStatu(ReturnStatus.Success);
		rm.setStatusreson("成功!");
		return JsonUtil.objectToJsonStr(rm);
	}
	
	/**
	 * 认证员的落地店列表
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
		PageResult<PlatformProductShopListVO> pageResult = iBasicAgentOrProductShopService.getProductShopList(user.getWeiID(),(short)param.getStatus(), Limit.buildLimit(param.getPageIndex(), param.getPageSize()), 2);
		rm.setBasemodle(pageResult);
		rm.setStatu(ReturnStatus.Success);
		rm.setStatusreson("成功!");
		return JsonUtil.objectToJsonStr(rm);
	}
	
	/**
	 * 认证员的代理商详情
	 * @param param
	 * @return
	 * @throws MapperException 
	 */
	@RequestMapping("/getDownstreamAgentDetail")
	public String getDownstreamAgentDetail(VerifierProductAgentListParam param) throws MapperException {
		ReturnModel rm= new ReturnModel();
		MyAgentOrProductShopListVO vo = iBasicAgentOrProductShopService.getMyDevelopDetail(1);
		rm.setBasemodle(vo);
		rm.setStatu(ReturnStatus.Success);
		rm.setStatusreson("成功!");
		return JsonUtil.objectToJsonStr(rm);
	}
	
	/**
	 * 添加跟进记录
	 * @param param
	 * @return
	 * @throws MapperException 
	 */
	@RequestMapping("/addProductAgentFollow")
	public String addProductAgentFollow(String follow) throws MapperException {
		ReturnModel rm= new ReturnModel();
		LoginUser user = super.getUserOrSub();
		if(user==null){
			rm.setStatu(ReturnStatus.LoginError);
			rm.setStatusreson("登陆已过期，请重新登陆");
			return JsonUtil.objectToJsonStr(rm);
		}
		
		VerifierProductAgentFollowParam param=new VerifierProductAgentFollowParam();
		try
		{
			param = (VerifierProductAgentFollowParam) JsonUtil.jsonToBean(follow, VerifierProductAgentFollowParam.class);
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
		
		rm = iBasicAgentOrProductShopService.followAgent(param.getAgentId(),param.getContent(),user.getWeiID());
		return JsonUtil.objectToJsonStr(rm);
	}
	
	/**
	 * 代理商的跟进记录
	 * @param param
	 * @return
	 * @throws MapperException 
	 */
	@RequestMapping("/getProductAgentFollowRecord")
	public String getProductAgentFollowRecord(VerifierProductAgentFollowParam param) throws MapperException {
		ReturnModel rm= new ReturnModel();
		LoginUser user = super.getUserOrSub();
		if(user==null){
			rm.setStatu(ReturnStatus.LoginError);
			rm.setStatusreson("登陆已过期，请重新登陆");
			return JsonUtil.objectToJsonStr(rm);
		}
		List<ProductAgentFollowRecordVO> list = new ArrayList<ProductAgentFollowRecordVO>();
		ProductAgentDataVO vo = iBasicAgentOrProductShopService.getProductAgentDataVO(param.getAgentId(),2);
		if(vo!=null){
			List<UAgentApplyFollowLog> followList = vo.getFollowList();
			if(followList!=null&&followList.size()>0){
				for(UAgentApplyFollowLog obj:followList){
					ProductAgentFollowRecordVO record = new ProductAgentFollowRecordVO();
					record.setContent(obj.getRemaks()!=null?obj.getRemaks():"");
					record.setFollowDate(DateUtils.formatDateTime(obj.getCreateTime()!=null?(Date)obj.getCreateTime():new Date()));
					record.setFollowWeiId(obj.getWeiId()!=null?obj.getWeiId():-11);
					list.add(record);
				}
			}
		}
		rm.setBasemodle(list);
		rm.setStatu(ReturnStatus.Success);
		rm.setStatusreson("成功!");
		return JsonUtil.objectToJsonStr(rm);
	}
	/**
	 * 获取认证员负责的区域
	 * @return
	 * @throws MapperException
	 */
	@RequestMapping("/getVerifierRegion")
	public String getVerifierRegion(Long weiId)throws MapperException{
		ReturnModel rm= new ReturnModel();
		LoginUser user = super.getUserOrSub();
		if(user==null){
			rm.setStatu(ReturnStatus.LoginError);
			rm.setStatusreson("登陆已过期，请重新登陆");
			return JsonUtil.objectToJsonStr(rm);
		}
		rm =iBasicAgentOrProductShopService.getVerifierRegion(weiId, Short.valueOf(VerifierRegionEnum.Platform.toString())); 
		return JsonUtil.objectToJsonStr(rm);
	}
}
