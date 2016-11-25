package com.okwei.myportal.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.okwei.bean.domain.UProductShop;
import com.okwei.bean.domain.USupplyDemand;
import com.okwei.bean.enums.AgentStatusEnum;
import com.okwei.bean.enums.OrderFrom;
import com.okwei.bean.vo.BResultMsg;
import com.okwei.bean.vo.LoginUser;
import com.okwei.bean.vo.PlatformProductShopListPCVO;
import com.okwei.bean.vo.PlatformProductShopListVO;
import com.okwei.bean.vo.ReturnModel;
import com.okwei.bean.vo.ReturnStatus;
import com.okwei.common.JsonUtil;
import com.okwei.common.Limit;
import com.okwei.common.PageResult;
import com.okwei.dao.user.IBasicAgentOrProductShopDAO;
import com.okwei.service.order.IBasicPayService;
import com.okwei.service.user.IBasicAgentOrProductShopService;
import com.okwei.util.ParseHelper;
import com.okwei.web.base.SSOController;
import com.sdicons.json.mapper.MapperException;

@Controller
@RequestMapping(value = "/productShop")
public class ProductShopMgtController extends SSOController {
	
	@Autowired
	private IBasicAgentOrProductShopService iBasicAgentOrProductShopService;
	@Autowired
	private IBasicAgentOrProductShopDAO iBasicAgentOrProductShopDAO;
	@Autowired
	private IBasicPayService basicPayService;
	/**
	 * 平台号的落地店列表
	 * @param param
	 * @return
	 * @throws MapperException 
	 */
	@RequestMapping("/getDownstreamStoreList/{status}")
	public String getDownstreamStoreList(@RequestParam(required = false, defaultValue = "1") int pageId,Model model,@PathVariable Short status) throws MapperException {
		LoginUser user = super.getUserOrSub();
		/*LoginUser user = new LoginUser();
		user.setWeiID(1036799L);*/
		PageResult<PlatformProductShopListPCVO> pageResult=new PageResult<PlatformProductShopListPCVO>();
		if(user==null)
		{
			model.addAttribute("Inload_Query", false);
		}else{
			Boolean Inload_Query = user.judgePower("Inload_Query");
			//Inload_Query=true;
			if(Inload_Query){
				pageResult = iBasicAgentOrProductShopService.getProductShopPCList(user.getWeiID(),status, Limit.buildLimit(pageId,10),1);
				//获得已取消的落地店数目
				long canceledAmount = iBasicAgentOrProductShopDAO.getProductShopByPlatFormTotalAmount(user.getWeiID(),Short.parseShort(AgentStatusEnum.Cancel.toString()),null,null);
				//获得已审核的落地店数目
				long auditedAmount = iBasicAgentOrProductShopDAO.getProductShopByPlatFormTotalAmount(user.getWeiID(),Short.parseShort(AgentStatusEnum.Normal.toString()),null,null);
				model.addAttribute("canceledAmount", canceledAmount);
				model.addAttribute("auditedAmount", auditedAmount);
				model.addAttribute("pageResult", pageResult);
				//获得该平台号的招商需求
				List<USupplyDemand> demandList = iBasicAgentOrProductShopDAO.getUSupplyDemandList(user.getWeiID());
				model.addAttribute("demandList", demandList);
				model.addAttribute("userinfo", user);
				model.addAttribute("Inload_Query", Inload_Query);
			}else{
				model.addAttribute("Inload_Query", false);
			}
		}
		
		//已取消页面
		if(status!=null&&status==2){
			return "productshop/canceledList";
		}
		//已审核页面
		return "productshop/auditedList";
	}
	
	/**
	 * 取消落地店
	 * @param param
	 * @return
	 * @throws MapperException 
	 */
	@ResponseBody
	@RequestMapping("/cancelProductShop")
	public String cancelProductShop(Integer shopId,String cancelReason){
		ReturnModel rm= new ReturnModel();
		LoginUser user = super.getUserOrSub();
		if(user==null){
			rm.setStatu(ReturnStatus.LoginError);
			rm.setStatusreson("登陆已过期，请重新登陆");
		}else{
			Boolean Inload_Edit = user.judgePower("Inload_Edit");
			if(Inload_Edit){
			    rm = iBasicAgentOrProductShopService.updateProductShopState(shopId,cancelReason,Short.parseShort(AgentStatusEnum.Cancel.toString()));
			}else{
				rm.setStatu(ReturnStatus.ParamError);
				rm.setStatusreson("您没有权限操作");
			}
		}
		return JsonUtil.objectToJson(rm);
	}
	
	/**
	 * 恢复、删除单个落地店
	 * @param param
	 * @return
	 * @throws MapperException 
	 */
	@ResponseBody
	@RequestMapping("/editProductShopState")
	public String editProductShopState(Integer shopId,Short status){
		ReturnModel rm= new ReturnModel();
		LoginUser user = super.getUserOrSub();
		if(user==null){
			rm.setStatu(ReturnStatus.LoginError);
			rm.setStatusreson("登陆已过期，请重新登陆");
		}else{
			Boolean Inload_Delete = user.judgePower("Inload_Delete");
			if(Inload_Delete){
			    rm = iBasicAgentOrProductShopService.updateProductShopState(shopId,null, status);
			}else{
				rm.setStatu(ReturnStatus.ParamError);
				rm.setStatusreson("您没有权限操作");
			}
		}
		
		return JsonUtil.objectToJson(rm);
	}
	
	/**
	 * 批量恢复、删除落地店
	 * @param param
	 * @return
	 * @throws MapperException 
	 */
	@ResponseBody
	@RequestMapping("/editProductShopStateBatch")
	public String editProductShopStateBatch(Integer[] shopIdArr,Short status){
		ReturnModel rm= new ReturnModel();
		LoginUser user = super.getUserOrSub();
		if(user==null){
			rm.setStatu(ReturnStatus.LoginError);
			rm.setStatusreson("登陆已过期，请重新登陆");
		}else{
			Boolean Inload_Delete = user.judgePower("Inload_Delete");
			if(Inload_Delete){
		        rm = iBasicAgentOrProductShopService.batchUpdateProductShopState(shopIdArr,status);
		        iBasicAgentOrProductShopService.operateUserIdentity(shopIdArr, status);
			}else{
				rm.setStatu(ReturnStatus.ParamError);
				rm.setStatusreson("您没有权限操作");
			}
		}
		return JsonUtil.objectToJson(rm);
	}
	
	/**
	 * 添加落地店
	 * @param demandIdArr
	 * @param weiId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/addProductShop")
	public String addProductShop(Integer[] demandIdArr,Long weiId){
		ReturnModel rm= new ReturnModel();
		LoginUser user = super.getUserOrSub();
		if(user==null){
			rm.setStatu(ReturnStatus.LoginError);
			rm.setStatusreson("登陆已过期，请重新登陆");
		}else{
			Boolean Inload_Add = user.judgePower("Inload_Add");
			if(Inload_Add){
		        rm = iBasicAgentOrProductShopService.addProductShop(demandIdArr, weiId,user.getWeiID());
			}else{
				rm.setStatu(ReturnStatus.ParamError);
				rm.setStatusreson("您没有权限操作");
			}
		}
		return JsonUtil.objectToJson(rm);
	}
	/**
	 * 获得微店号的店铺名
	 * @param weiId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getShopName")
	public String getShopName(Long weiId){
		ReturnModel rm= new ReturnModel();
		LoginUser user = super.getUserOrSub();
		/*LoginUser user = new LoginUser();
		user.setWeiID(1036799L);*/
		if(user==null){
			rm.setStatu(ReturnStatus.LoginError);
			rm.setStatusreson("登陆已过期，请重新登陆");
		}else{
			rm = iBasicAgentOrProductShopService.getShopNameInfo(weiId,user.getWeiID());
		}
		
		return JsonUtil.objectToJson(rm);
	}
	
	
	/**
	 * 获取支付单号
	 * @param model
	 * @param cls
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getOrderNO")
	public String getOrderNO(Model model ,String cls)  { 
		LoginUser user = super.getLoginUser();
		String[] cls1 = cls.split(","); 
		BResultMsg update_OrderPayInfo =new BResultMsg();
		if (cls1.length>0) {
			List<String> ids=new ArrayList<String>();
			for (int i = 0; i < cls1.length; i++) {
				ids.add(cls1[i]);
			}  
				try {
					update_OrderPayInfo = basicPayService.update_OrderPayInfo(user.getWeiID(), ParseHelper.toInt(OrderFrom.PC.toString()), 1, ids);
				} catch (Exception e) { 
					update_OrderPayInfo.setState(-1);
					update_OrderPayInfo.setMsg("沒有找到相应的支付信息");
				} 
		} 
		return JsonUtil.objectToJson(update_OrderPayInfo);
	}
}
