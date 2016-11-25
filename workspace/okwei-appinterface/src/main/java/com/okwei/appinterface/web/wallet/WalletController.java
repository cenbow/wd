package com.okwei.appinterface.web.wallet;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.okwei.appinterface.service.wallet.IWalletService;
import com.okwei.bean.vo.LoginUser;
import com.okwei.bean.vo.ReturnModel;
import com.okwei.bean.vo.ReturnStatus;
import com.okwei.common.JsonUtil;
import com.okwei.service.user.IBasicWalletService;
import com.okwei.web.base.SSOController;
import com.sdicons.json.mapper.MapperException;


/**
 * 订单列表
 * @author fuhao
 *
 */
@RequestMapping("/wallet")
@RestController
public class WalletController extends SSOController {

//	private static final Log logger = LogFactory.getLog(OrderListController.class);
	
	@Autowired	
	private IWalletService walletService; 
	
	@Autowired
	private IBasicWalletService baseWalletService;
	
	/** 
	 * 现金劵列表
	 * @throws MapperException 
	 */
	@RequestMapping(value = "/getCashCoupon",method ={RequestMethod.POST,RequestMethod.GET})
	public String find_CashCoupon(@RequestParam(required = false,defaultValue = "1") int status,
									@RequestParam(required = false,defaultValue = "1") int pageIndex,
									@RequestParam(required = false,defaultValue = "10") int pageSize) throws MapperException{ 
		ReturnModel rm= new ReturnModel();
		LoginUser user=getUser();
		if (user!=null) {
			rm=walletService.getCashCoupon(user.getWeiID(),status, pageIndex, pageSize);
		}else {
			rm.setStatu(ReturnStatus.LoginError);
			rm.setStatusreson("您的身份已过期，请重新登录");
		} 
		return JsonUtil.objectToJsonStr(rm);
	}
	 
	/** 
	 * 钱包收支明细列表
	 * @throws MapperException 
	 */
	@RequestMapping(value = "/find_WalletList",method ={RequestMethod.POST,RequestMethod.GET})
	public String find_WalletList(String dateStr,
			@RequestParam(required = false,defaultValue = "1") int pageIndex,
			@RequestParam(required = false,defaultValue = "10") int pageSize,String tiket) throws MapperException{ 
		ReturnModel rm= new ReturnModel();
		LoginUser user=getUser();
		if (user!=null) {
			rm=walletService.getWalletList(user.getWeiID(), dateStr, pageIndex, pageSize);
		}else {
			rm.setStatu(ReturnStatus.LoginError);
			rm.setStatusreson("您的身份已过期，请重新登录");
		} 
		return JsonUtil.objectToJsonStr(rm);
	}
	
	/** 
	 * 悬赏列表
	 * @throws MapperException 
	 */
	@RequestMapping(value = "/getMyPayRewards",method ={RequestMethod.POST,RequestMethod.GET})
	public String getMyPayRewards(@RequestParam(required = false,defaultValue = "1")Short isPayReward,
			@RequestParam(required = false,defaultValue = "1") int pageIndex,
			@RequestParam(required = false,defaultValue = "10") int pageSize,String  dateStr) throws MapperException{ 
		ReturnModel rm= new ReturnModel();
		rm.setStatu(ReturnStatus.Success);
		rm.setStatusreson("成功！"); 
		LoginUser user=getUser();
		if (user!=null) {
			rm=walletService.getMyPayRewards(user.getWeiID(),dateStr, isPayReward,  pageIndex, pageSize);
		}else {
			rm.setStatu(ReturnStatus.LoginError);
			rm.setStatusreson("您的身份已过期，请重新登录");
		} 
		return JsonUtil.objectToJsonStr(rm);
	}
	
	/** 
	 * 悬赏详情
	 * @throws MapperException 
	 */
	@RequestMapping(value = "/getRewardDetail",method ={RequestMethod.POST,RequestMethod.GET})
	public String getRewardDetail(@RequestParam(required = false,defaultValue = "-1")Integer rewardId) throws MapperException{ 
		ReturnModel rm= new ReturnModel();
		rm.setStatu(ReturnStatus.Success);
		rm.setStatusreson("成功！");
		LoginUser user=getUser();
		if (user!=null) {
			rm=walletService.getRewardDetail(user.getWeiID(), rewardId);
		}else {
			rm.setStatu(ReturnStatus.LoginError);
			rm.setStatusreson("您的身份已过期，请重新登录");
		} 
		return JsonUtil.objectToJsonStr(rm);
	}
	
	
	/** 
	 * 平台服务费列表
	 * @throws MapperException 
	 */
	@RequestMapping(value = "/getMyServiceFeeList",method ={RequestMethod.POST,RequestMethod.GET})
	public String getMyServiceFeeList(@RequestParam(required = false,defaultValue = "-1")String isPayReward,
			@RequestParam(required = false,defaultValue = "null")String dateStr,
			@RequestParam(required = false,defaultValue = "1") int pageIndex,
			@RequestParam(required = false,defaultValue = "10") int pageSize) throws MapperException{ 
		ReturnModel rm= new ReturnModel();
		rm.setStatu(ReturnStatus.Success);
		rm.setStatusreson("成功！");
		LoginUser user=getUser();
		if (user!=null) {
			rm=walletService.getServiceFeeList(user.getWeiID(),isPayReward,dateStr,pageIndex,pageSize); 
		}else {
			rm.setStatu(ReturnStatus.LoginError);
			rm.setStatusreson("您的身份已过期，请重新登录");
		} 
		return JsonUtil.objectToJsonStr(rm);
	}
	
	
	/** 
	 * 平台服务费详情
	 * @throws MapperException 
	 */
	@RequestMapping(value = "/getServiceFeeDetail",method ={RequestMethod.POST,RequestMethod.GET})
	public String getServiceFeeDetail(@RequestParam(required = false,defaultValue = "-1")Long feeId) throws MapperException{ 
		ReturnModel rm= new ReturnModel();
		rm.setStatu(ReturnStatus.Success);
		rm.setStatusreson("成功！");
		if (feeId!=null&&feeId<1) {
			rm.setStatu(ReturnStatus.ParamError);
			rm.setStatusreson("参数错误！");
			return JsonUtil.objectToJsonStr(rm);
		}
		LoginUser user=getUser();
		if (user!=null) {
			rm=walletService.getServiceFeeDetail(feeId); 
		}else {
			rm.setStatu(ReturnStatus.LoginError);
			rm.setStatusreson("您的身份已过期，请重新登录");
		} 
		return JsonUtil.objectToJsonStr(rm);
	}
	
	 
	
	
	/** 
	 * 返现列表
	 * @throws MapperException 
	 */
	@RequestMapping(value = "/getMyTradingRebates",method ={RequestMethod.POST,RequestMethod.GET})
	public String find_WalletList1(String dateStr,
			@RequestParam(required = false,defaultValue = "1") int pageIndex,
			@RequestParam(required = false,defaultValue = "10") int pageSize) throws MapperException{ 
		ReturnModel rm= new ReturnModel();
		LoginUser user=getUser();
		if (user!=null) {
			rm=walletService.findTradingRebates(user.getWeiID(), dateStr,  pageIndex, pageSize);
		}else {
			rm.setStatu(ReturnStatus.LoginError);
			rm.setStatusreson("您的身份已过期，请重新登录");
		} 
		return JsonUtil.objectToJsonStr(rm);
	}
	/** 
	 * 返现详情
	 * @throws MapperException 
	 */
	@RequestMapping(value = "/getMyTradingRebatesDetails",method ={RequestMethod.POST,RequestMethod.GET})
	public String find_WalletList2(Long rebateId,
			@RequestParam(required = false,defaultValue = "1") int pageIndex,
			@RequestParam(required = false,defaultValue = "10") int pageSize,String tiket) throws MapperException{ 
		ReturnModel rm= new ReturnModel();
		LoginUser user=getUser();
		if (user!=null) {
			rm=walletService.getMyTradingRebatesDetails(rebateId,user.getWeiID(), pageIndex, pageSize);
		}else {
			rm.setStatu(ReturnStatus.LoginError);
			rm.setStatusreson("您的身份已过期，请重新登录");
		} 
		return JsonUtil.objectToJsonStr(rm);
	}
	
	/** 
	 * 获取我的微金币列表
	 * @throws MapperException 
	 */
	@RequestMapping(value = "/getOkweiCoin",method ={RequestMethod.POST,RequestMethod.GET})
	public String getOkweiCoin(@RequestParam(required = false,defaultValue = "1") int status,
			@RequestParam(required = false,defaultValue = "1") int pageIndex,
			@RequestParam(required = false,defaultValue = "10") int pageSize,String tiket) throws MapperException{ 
		ReturnModel rm= new ReturnModel();
		LoginUser user=getUser();
		if (user!=null) {
			rm=baseWalletService.getMyWeiCoinList(user.getWeiID(), pageIndex, pageSize, status);
		}else {
			rm.setStatu(ReturnStatus.LoginError);
			rm.setStatusreson("您的身份已过期，请重新登录");
		} 
		return JsonUtil.objectToJsonStr(rm);
	}
	
	
	/** 
	 * 获取我的购物券列表
	 * @throws MapperException 
	 */
	@RequestMapping(value = "/getExchangeCoupon",method ={RequestMethod.POST,RequestMethod.GET})
	public String getExchangeCoupon(@RequestParam(required = false,defaultValue = "1") int status,
			@RequestParam(required = false,defaultValue = "1") int pageIndex,
			@RequestParam(required = false,defaultValue = "10") int pageSize,String tiket) throws MapperException{ 
		ReturnModel rm= new ReturnModel();
		LoginUser user=getUser();
		if (user!=null) {
			rm=baseWalletService.getMyExchangeCoupon(user.getWeiID(), pageIndex, pageSize, status);
		}else {
			rm.setStatu(ReturnStatus.LoginError);
			rm.setStatusreson("您的身份已过期，请重新登录");
		} 
		return JsonUtil.objectToJsonStr(rm);
	}
}
