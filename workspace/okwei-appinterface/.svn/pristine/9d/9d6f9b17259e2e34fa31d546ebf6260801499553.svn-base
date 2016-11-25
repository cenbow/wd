package com.okwei.appinterface.web;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.okwei.appinterface.bean.vo.order.PayParamVO;
import com.okwei.appinterface.bean.vo.order.SettlementParam;
import com.okwei.appinterface.bean.vo.order.ShoppingCartOrderParam;
import com.okwei.appinterface.service.order.IPayProcess;
import com.okwei.bean.domain.OPayOrder;
import com.okwei.bean.domain.OPayOrderLog;
import com.okwei.bean.domain.OSupplyerOrder;
import com.okwei.bean.enums.FromTypeEnum;
import com.okwei.bean.enums.PayTypeEnum;
import com.okwei.bean.enums.UserIdentityType;
import com.okwei.bean.vo.BResultMsg;
import com.okwei.bean.vo.LoginUser;
import com.okwei.bean.vo.ReturnModel;
import com.okwei.bean.vo.ReturnStatus;
import com.okwei.bean.vo.order.BAddressVO;
import com.okwei.bean.vo.order.BReturnOdertInfo;
import com.okwei.bean.vo.order.BShoppingCartVO;
import com.okwei.common.JsonUtil;
import com.okwei.dao.order.IBasicOrdersDao;
import com.okwei.service.IBasicShoppingCartService;
import com.okwei.service.order.IBasicOrdersService;
import com.okwei.service.order.IBasicPayService;
import com.okwei.util.BitOperation;
import com.okwei.util.ParseHelper;
import com.okwei.util.RedisUtil;
import com.okwei.web.base.SSOController;
import com.sdicons.json.mapper.MapperException;

/**
 * 订单列表
 * 
 * @author fuhao
 *
 */
@RequestMapping("/Order")
@RestController
public class OrderController extends SSOController {

	private static final Log logger = LogFactory.getLog(OrderController.class);
	@Autowired
	public IBasicShoppingCartService baseCartService;
	@Autowired
	public IBasicPayService basePayService;
	@Autowired
	public IBasicOrdersDao orderService;
	@Autowired
	public IBasicOrdersService payOrderService;

	@Autowired
	public IPayProcess payService;
	
	/**
	 * 购物下单接口
	 * @param list
	 * @return
	 * @throws MapperException
	 */
	@RequestMapping("/submitOrder")
	public String submitOrder(String list) throws MapperException {
		ReturnModel rm = new ReturnModel();
		LoginUser user = getUser();
		if (user == null) {
			rm.setStatu(ReturnStatus.LoginError);
			rm.setStatusreson("登陆已过期，请重新登陆");
			return JsonUtil.objectToJsonStr(rm);
		}
		try {
			ShoppingCartOrderParam param=(ShoppingCartOrderParam)JsonUtil.jsonStrToObject(list, ShoppingCartOrderParam.class);
			if(param!=null){
				List<BShoppingCartVO> sellerlist=param.getSupplierOrderList();
				BAddressVO addressVO= param.getReceiveInfo();
				BReturnOdertInfo mod  = baseCartService.savePlaceOrder(sellerlist, user.getWeiID(), addressVO, FromTypeEnum.APP);
				if (mod != null && mod.getState() == 1) {//
					rm.setStatu(ReturnStatus.Success);
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("orderId", mod.getOrderno());
					map.put("price", mod.getAlltotal());
					rm.setStatusreson("下单成功！");
					rm.setBasemodle(map);
				} else {
					rm.setStatu(ReturnStatus.SystemError);
					rm.setStatusreson(mod.getMsg());
				}
			}else {
				rm.setStatu(ReturnStatus.ParamError);
				rm.setStatusreson("参数有误"); 
			}
		} catch (Exception e) {
			// TODO: handle exception
			rm.setStatu(ReturnStatus.SystemError);
			rm.setStatusreson("系统错误:"+e.getMessage()); 
		}
		
		return JsonUtil.objectToJsonStr(rm);
	}

	/**
	 * 兑换下单接口
	 * @param list //为了兼容以前的版本，所以传很多无用的参数过来，这边需要一个address和styleid
	 * @return
	 * @throws MapperException
	 */
	@RequestMapping("/submitExchangeOrder")
	public String submitExchangeOrder(String list) throws MapperException {
		ReturnModel rm = new ReturnModel();
		LoginUser user = getUser();
		if (user == null) {
			rm.setStatu(ReturnStatus.LoginError);
			rm.setStatusreson("登陆已过期，请重新登陆");
			return JsonUtil.objectToJsonStr(rm);
		}
		try {
			ShoppingCartOrderParam param=(ShoppingCartOrderParam)JsonUtil.jsonStrToObject(list, ShoppingCartOrderParam.class);
			if(param!=null){
				List<BShoppingCartVO> sellerlist=param.getSupplierOrderList();
				BAddressVO addressVO= param.getReceiveInfo();
				BReturnOdertInfo mod  = baseCartService.saveExchangeOrder(sellerlist, user.getWeiID(), addressVO, FromTypeEnum.APP);
				if (mod != null && mod.getState() == 1) {//
					rm.setStatu(ReturnStatus.Success);
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("orderId", mod.getOrderno());
					map.put("price", mod.getAlltotal());
					rm.setStatusreson("下单成功！");
					rm.setBasemodle(map);
				} else {
					rm.setStatu(ReturnStatus.SystemError);
					rm.setStatusreson(mod.getMsg());
				}
			}else {
				rm.setStatu(ReturnStatus.ParamError);
				rm.setStatusreson("参数有误"); 
			}
		} catch (Exception e) {
			// TODO: handle exception
			rm.setStatu(ReturnStatus.SystemError);
			rm.setStatusreson("系统错误:"+e.getMessage()); 
		}
		
		return JsonUtil.objectToJsonStr(rm);
	}
	
	@RequestMapping("/getSettlementData")
	public String getSettlementData(String list) throws MapperException {
		ReturnModel rm = new ReturnModel();
		LoginUser user = getUser();
		if (user == null) {
			rm.setStatu(ReturnStatus.LoginError);
			rm.setStatusreson("登陆已过期，请重新登陆");
			return JsonUtil.objectToJsonStr(rm);
		}
		List<SettlementParam> sellerList= (List<SettlementParam>) JsonUtil.json2Objectlist(list, SettlementParam.class);
		if(sellerList!=null&&sellerList.size()>0){
			rm=payService.getSettlementData(sellerList, user.getWeiID());
		}
		return JsonUtil.objectToJsonStr(rm);
	}
	
	@RequestMapping("/getSettlementDataSure")
	public String getSettlementDataSure(String data) throws MapperException {
		ReturnModel rm = new ReturnModel();
		LoginUser user = getUser();
		if (user == null) {
			rm.setStatu(ReturnStatus.LoginError);
			rm.setStatusreson("登陆已过期，请重新登陆");
			return JsonUtil.objectToJsonStr(rm);
		}
		ShoppingCartOrderParam param=(ShoppingCartOrderParam)JsonUtil.jsonStrToObject(data, ShoppingCartOrderParam.class);
		if(param!=null){
			List<BShoppingCartVO> sellerlist=param.getSupplierOrderList();
			BAddressVO addressVO= param.getReceiveInfo();
			rm = payService.getSettlementDataSure(sellerlist, addressVO, user.getWeiID());
		}else {
			rm.setStatu(ReturnStatus.ParamError);
			rm.setStatusreson("参数有误"); 
		}
		
		return JsonUtil.objectToJsonStr(rm);
	}
	
	
	
	/**
	 * 支付（钱包支付）
	 * @param orderno
	 * @param pwd
	 * @param vcode
	 * @param sign
	 * @param checkCode
	 * @param UseCashCoupon
	 * @return
	 * @throws MapperException
	 */
	@RequestMapping("/walletPay")
	public String walletPay(String orderno,String pwd,String vcode,String sign,String checkCode,String UseCashCoupon) throws MapperException {
		ReturnModel rm = new ReturnModel();
		LoginUser user = getUser();
		if (user == null) {
			rm.setStatu(ReturnStatus.LoginError);
			rm.setStatusreson("登陆已过期，请重新登陆");
			return JsonUtil.objectToJsonStr(rm);
		}
		
		PayParamVO param=new PayParamVO();
		param.setOrderNo(orderno);
		param.setPwd(pwd);
		param.setvCode(vcode);
		param.setSign(sign);
		param.setCheckCode(checkCode);
		param.setIsFullPay(0);
		int cashUse=ParseHelper.toInt(UseCashCoupon);
		if(cashUse>0){
			double coinAmount= basePayService.UP_getCoinPayAmount(orderno);
			if(coinAmount>0){
				param.setIsCoin(1);
				param.setCoinAmount(coinAmount); 
			}
		}else {
			basePayService.UP_CoinPayAmountDel(orderno); 
		}

		try {
			rm.setStatu(ReturnStatus.LoginError);
			rm.setStatusreson("请升级您的app版本。");
			return JsonUtil.objectToJsonStr(rm);
//			rm = payService.updateWalletPay(param, user.getWeiID());
		} catch (Exception e) {
			// TODO: handle exception
			rm.setStatu(ReturnStatus.SystemError);
			rm.setStatusreson(e.getMessage());
			rm.setBasemodle(e); 
		}
		return JsonUtil.objectToJsonStr(rm);
	}
	
	
	@RequestMapping("/walletPayNew")
	public String walletPayNew(String orderno,String pwd,String vcode,String sign,String checkCode,String UseCashCoupon,String confirm,@RequestParam(required = false, defaultValue = "") String useOkweiCoin,@RequestParam(required = false, defaultValue = "")String payOrder) throws MapperException {
		ReturnModel rm = new ReturnModel();
		LoginUser user = getUser();
		if (user == null) {
			rm.setStatu(ReturnStatus.LoginError);
			rm.setStatusreson("登陆已过期，请重新登陆");
			return JsonUtil.objectToJsonStr(rm);
		}
		PayParamVO param=new PayParamVO();
		param.setOrderNo(orderno);
		param.setPwd(pwd);
		param.setvCode(vcode);
		param.setSign(sign);
		param.setCheckCode(checkCode);
		param.setIsFullPay(0);
		if(useOkweiCoin!=null && !"".equals(useOkweiCoin))
			param.setUseOkweiCoin(Integer.parseInt(useOkweiCoin));
		else
			param.setUseOkweiCoin(0);
		param.setPayOrder(payOrder);
		int cashUse=ParseHelper.toInt(UseCashCoupon);
		if(cashUse>0){
			if(ParseHelper.toDouble(useOkweiCoin)>0){
				rm.setStatu(ReturnStatus.SystemError);
				rm.setStatusreson("现金券与微金币不能同时使用！");
				return JsonUtil.objectToJsonStr(rm);
			}
			double coinAmount= basePayService.UP_getCoinPayAmount(orderno);
			if(coinAmount>0){
				param.setIsCoin(1);
				param.setCoinAmount(coinAmount); 
			}
		}else {
			basePayService.UP_CoinPayAmountDel(orderno); 
		}
		
		try {
			rm = payService.updateWalletPay(param, user.getWeiID());
		} catch (Exception e) {
			// TODO: handle exception
			rm.setStatu(ReturnStatus.SystemError);
			rm.setStatusreson(e.getMessage());
			rm.setBasemodle(e); 
		}
		return JsonUtil.objectToJsonStr(rm);
	}
	
	
	@RequestMapping("/getPayOrderInfo")
	public String getPayOrderInfo(String orderId) throws MapperException {
		ReturnModel rm = new ReturnModel();
		LoginUser user = getUser();
		if (user == null) {
			rm.setStatu(ReturnStatus.LoginError);
			rm.setStatusreson("登陆已过期，请重新登陆");
			return JsonUtil.objectToJsonStr(rm);
		}
		rm=payService.getPayOrderMsg(orderId, user.getWeiID());
		return JsonUtil.objectToJsonStr(rm);
	}
	
	@RequestMapping("/getOrderCombine")
	public String getOrderCombine(String ids,String type) throws MapperException {
		ReturnModel rm = new ReturnModel();
		LoginUser user = getUser();
		if (user == null) {
			rm.setStatu(ReturnStatus.LoginError);
			rm.setStatusreson("登陆已过期，请重新登陆");
			return JsonUtil.objectToJsonStr(rm);
		}
		List<String> list=(List<String>)JsonUtil.jsonToList(ids);
		try {
			rm=payService.getOrderPayInfo(list, ParseHelper.toInt(type), user.getWeiID());
		} catch (Exception e) {
			// TODO: handle exception
			rm.setStatu(ReturnStatus.ParamError);
			rm.setStatusreson(e.getMessage()); 
			rm.setBasemodle(e); 
		}
		return JsonUtil.objectToJsonStr(rm);
	}
	
	@RequestMapping("/unlockOrder")
	public String unlockOrder(String orderId) throws MapperException{
		OPayOrder payOrder=orderService.get_OPayOrderLastOne(orderId);
		if(payOrder!=null){
			List<OSupplyerOrder> list=orderService.find_SupplyerOrderByOrderID(payOrder.getPayOrderId());
			if(list!=null&&list.size()>0){
				String baseKey="PayToBank_";
				for (OSupplyerOrder ss : list) {
					String keyString = RedisUtil.getString(baseKey + ss.getSupplierOrderId());
					if(!"".equals(keyString)&&ss.getSupplierOrderId().equals(keyString))
					{
						RedisUtil.delete(baseKey+ss.getSupplierOrderId()); 
					}
				}
			}
		}
		ReturnModel rm = new ReturnModel();
		rm.setStatu(ReturnStatus.Success);
		rm.setStatusreson("解锁成功");
		return JsonUtil.objectToJsonStr(rm);
	}
	
	
	//----------------------测试板块-------------------------------
	
	@RequestMapping("/walletPayTest")
	public String walletPayTest(String orderno,String pwd,String UseCashCoupon) throws MapperException {
		ReturnModel rm = new ReturnModel();
		LoginUser user = getUser();
		if (user == null) {
			rm.setStatu(ReturnStatus.LoginError);
			rm.setStatusreson("登陆已过期，请重新登陆");
			return JsonUtil.objectToJsonStr(rm);
		}
		PayParamVO param=new PayParamVO();
		param.setOrderNo(orderno);
		param.setPwd(pwd);
		param.setIsFullPay(0); 
		
		int cashUse=ParseHelper.toInt(UseCashCoupon);
		if(cashUse>0){
			double coinAmount= basePayService.UP_getCoinPayAmount(orderno);
			if(coinAmount>0){
				param.setIsCoin(1);
				param.setCoinAmount(coinAmount); 
			}
		}else {
			basePayService.UP_CoinPayAmountDel(orderno); 
		}
		try {
			rm = payService.updateWalletPayTest(param, user.getWeiID());
		} catch (Exception e) {
			// TODO: handle exception
			rm.setStatu(ReturnStatus.SystemError);
			rm.setBasemodle(e); 
		}
		return JsonUtil.objectToJsonStr(rm);
	}
	
	
	@RequestMapping("/PayProcessTest")
	public String PayProcessTest(String orderno,String payType) throws MapperException {
		ReturnModel rm = new ReturnModel();
//		LoginUser user = getUser();
//		if (user == null) {
//			rm.setStatu(ReturnStatus.LoginError);
//			rm.setStatusreson("登陆已过期，请重新登陆");
//			return JsonUtil.objectToJsonStr(rm);
//		}
		
		OPayOrderLog log=orderService.getOPayOrderLog(orderno);
		if(log==null||log.getState()==null||log.getState()==0){
			rm.setStatu(ReturnStatus.SystemError);
			rm.setStatusreson("该条支付记录未付款成功");
			return JsonUtil.objectToJsonStr(rm);
		} 
		OPayOrder order=basePayService.getOPayOrder(orderno,true);
		PayTypeEnum typeEnum=null;
	
		for (PayTypeEnum tt : PayTypeEnum.values()) {
			if (Short.parseShort(tt.toString()) == ParseHelper.toShort(payType))
				typeEnum = tt;
		}
		try {
			BResultMsg resultMsg= basePayService.editOrderDataProcess(order, typeEnum);
			if(resultMsg.getState()==1){
				rm.setStatu(ReturnStatus.ParamError);
				rm.setStatusreson(resultMsg.getMsg());
			}else {
				rm.setStatu(ReturnStatus.SystemError);
				rm.setStatusreson(resultMsg.getMsg());
			}
		} catch (Exception e) {
			// TODO: handle exception
			rm.setStatu(ReturnStatus.SystemError);
			rm.setBasemodle(e);
		}
		
		return JsonUtil.objectToJsonStr(rm);
	}
	
	
	
	
	@RequestMapping("/getTest")
	public String getTest(String weiid,String productid,String orderno) throws MapperException {
		ReturnModel rm = new ReturnModel();
		 rm=	payOrderService.getPayBackVO(orderno);
//		int identity=basePayService.isShop(ParseHelper.toLong(weiid), ParseHelper.toLong(productid));
//		if(BitOperation.verIdentity(identity, UserIdentityType.Ground)){
//			rm.setStatusreson("1");
//		} 
//		if(BitOperation.verIdentity(identity, UserIdentityType.Agent)){
//			rm.setStatusreson("2");
//		}
//		rm.setBasemodle(identity);
		return JsonUtil.objectToJsonStr(rm);
	}
}
