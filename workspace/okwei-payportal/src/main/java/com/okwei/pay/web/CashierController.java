package com.okwei.pay.web;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.okwei.bean.domain.OPayOrder;
import com.okwei.bean.domain.OPayOrderLog;
import com.okwei.bean.domain.TVerificationId;
import com.okwei.bean.domain.UBankCard;
import com.okwei.bean.domain.UWallet;
import com.okwei.bean.domain.UWeiSeller;
import com.okwei.bean.enums.MobileBindEnum;
import com.okwei.bean.enums.OrderStatusEnum;
import com.okwei.bean.enums.OrderTypeEnum;
import com.okwei.bean.enums.PayTypeEnum;
import com.okwei.bean.vo.LoginUser;
import com.okwei.bean.vo.ReturnModel;
import com.okwei.bean.vo.ReturnStatus;
import com.okwei.bean.vo.order.OrderModelReturn;
import com.okwei.bean.vo.order.PayOrderInfoVO;
import com.okwei.common.JsonUtil;
import com.okwei.pay.bean.enums.PayResultStateEnum;
import com.okwei.pay.bean.enums.VerifyCodeTypeEnum;
import com.okwei.pay.bean.util.SendSMSUtil;
import com.okwei.pay.bean.vo.BankCardVO;
import com.okwei.pay.bean.vo.BaseSSOController;
import com.okwei.pay.bean.vo.PayResult;
import com.okwei.pay.bean.vo.ResultMsg;
import com.okwei.pay.service.IPayOrderService;
import com.okwei.pay.service.IRepaymentService;
import com.okwei.pay.service.ITest;
import com.okwei.service.order.IBasicOrdersService;
import com.okwei.service.order.IBasicPayService;
import com.okwei.util.DateUtils;
import com.okwei.util.GenerateOrderNum;
import com.okwei.util.MD5Encrypt;
import com.okwei.util.ObjectUtil;
import com.okwei.util.RedisUtil;

@Controller
@RequestMapping(value = "/pay")
public class CashierController extends BaseSSOController {

    @Autowired
    public IPayOrderService payOrderService;
    
    @Autowired
    public IBasicPayService basicPayService;
    
    @Autowired
    public IBasicOrdersService orderService;
    
    @Autowired
    public ITest itest;
    
    @RequestMapping(value = "/cashier", method = { RequestMethod.POST, RequestMethod.GET })
    public String Cashier(String orderNo, Model model) throws UnsupportedEncodingException {
//    	LoginUser user=itest.getLoginUser();
		LoginUser user = super.getLoginUser();
		 
		String paydomain = ResourceBundle.getBundle("domain").getString("paydomain");
		PayResult payResult = payOrderService.verifilyOrder(orderNo, user.getWeiID(),false);
		if (payResult.getType() != 0) {
		    return "redirect:" + paydomain + "/pay/error?type=" + payResult.getType();// 只能支付自己的订单
		}
		OPayOrder payOrder = payOrderService.getPayOrderUpWallet(orderNo);// 获取订单，并修改微钱包余额为0;
		//如果订单已过期
		if(payOrder.getState() == Short.parseShort(OrderStatusEnum.Tovoided.toString())){
			String lastOrderNo = payOrderService.getLastPayOrderID(payOrder);
			if(lastOrderNo !=null && !"".equals(lastOrderNo)){
				return "redirect:"+ paydomain +"/pay/cashier?orderNo=" + lastOrderNo;
			}else{
				return "redirect:"+ paydomain +"/pay/error?type=0&msg="+URLEncoder.encode("该订单已过期,请重新选择支付方式!", "UTF-8");// 只能支付自己的订单
			}
		}
			
		List<UBankCard> bankList = payOrderService.getBankCardList(user.getWeiID());
		List<BankCardVO> bcList = new ArrayList<BankCardVO>();
		if (bankList != null && bankList.size() > 0) {		
		    for (UBankCard bc : bankList) {
		    	BankCardVO  bCard = new BankCardVO(bc);
				String num = bc.getBanckCard();
				if (!ObjectUtil.isEmpty(num) && num.length() > 3) {
				    num = num.substring(num.length() - 4, num.length());
				    bCard.setBanckCard(num);
				}
				bcList.add(bCard);
		    }
		}
		UWallet wallet = payOrderService.getWallet(user.getWeiID());
		// payOrder.setTotalPrice(500.0);// ****************************//测试
		double balance = 0;// 微钱包余额
		double surplus = 0;// 其他支付金额
		double weicoin =0; //微店币余额
		int isyz = 0;// 是否实名认证，0未认证，1已认证
		// 微钱包是否有钱，是否实名认证,金额大于0.01
		//修改fuhao用户不绑定手机也展示钱包金额
	//	if (wallet != null && wallet.getStatus() != null && wallet.getStatus() == 1 && wallet.getBalance() != null && wallet.getBalance() >= 0.01 && seller.getMobileIsBind() != null && seller.getMobileIsBind() == Short.parseShort(MobileBindEnum.Bind.toString())) {
		if (wallet != null && wallet.getStatus() != null && wallet.getStatus() == 1 && wallet.getBalance() != null && wallet.getBalance() >= 0.01) {
		    balance = wallet.getBalance();// 余额
		    isyz = 1;
		    if (balance < payOrder.getTotalPrice()) 
		    {
		    	surplus = sub(payOrder.getTotalPrice(), balance);
		    }
		}else{
			surplus = sub(payOrder.getTotalPrice(), balance);
		}
		if(wallet != null && wallet.getWeiDianCoin()!=null)
		{
			weicoin=wallet.getWeiDianCoin();
		}
	
		// if (wallet == null || wallet.getStatus() == null ||
		// wallet.getStatus() != 1 || wallet.getBalance() == null ||
		// wallet.getBalance() >= 0.01 || seller.getMobileIsBind() ==null ||
		// seller.getMobileIsBind() !=
		// Short.parseShort(MobileBindEnum.Bind.toString())) {
		// balance = wallet.getBalance();// 余额
		// isyz = 1;
		// if (balance < payOrder.getTotalPrice()) {
		// surplus = sub(payOrder.getTotalPrice(), balance);
		// }
		// }	UWallet wallet = payDao.get(UWallet.class, orderInfo.getWeiId());
	
		Date time=payOrderService.getExpirationTime(orderNo);
		PayOrderInfoVO payOrderInfo = basicPayService.getPayOrderInfo(orderNo, user.getWeiID(), false);
		model.addAttribute("payOrderInfoVO", payOrderInfo);// 支付订单
		model.addAttribute("order", payOrder);// 支付订单
		model.addAttribute("balance", balance);// 实名认证，余额
		model.addAttribute("isyz", isyz);// 实名认证，余额
		model.addAttribute("surplus", surplus);// 其他支付余额
		model.addAttribute("list", bcList);// 银行卡列表
		model.addAttribute("weicoin", weicoin);//微店币
		if(time==null)
		{
			model.addAttribute("isActivity",0);
			model.addAttribute("expirationTime", time);
		}
		else
		{
			model.addAttribute("isActivity",1);
			Date now=new Date();			
			model.addAttribute("expirationTime", time.getTime()-now.getTime());//订单过期时间
		}
		
		return "pay/cashier";
    }

    /**
     * 错误页面
     * 
     * @param type
     * @param model
     * @return
     */
    @RequestMapping(value = "/error", method = { RequestMethod.POST, RequestMethod.GET })
    public String error(@RequestParam(required = false, defaultValue = "-1") int type, String orderNo, String msg, Model model) {
	model.addAttribute("type", type);
	model.addAttribute("msg", msg);
	model.addAttribute("orderNo", orderNo);
	return "pay/error";
    }

    /**
     * 发送验证码
     * 
     * @param paypassword
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sendSMS", method = { RequestMethod.POST, RequestMethod.GET })
    public String sendSMS(String paypassword, HttpServletRequest request) {
	ResultMsg result = new ResultMsg();// 返回
	String ip = getIpAddr(request);
	if (ObjectUtil.isEmpty(paypassword)) {
	    result.setStatus(-1);
	    result.setMsg("支付密码不能为空");
	    return JsonUtil.objectToJson(result);
	}
	LoginUser user = getLoginUser();
	UWallet wallet = payOrderService.getWallet(user.getWeiID());
	if (wallet == null || wallet.getStatus() == null || wallet.getStatus() != 1) {// 0或空：未实名认证；1：已实名认证
	    result.setStatus(-2);
	    result.setMsg("用户未进行实名认证");
	    return JsonUtil.objectToJson(result);
	}
	UWeiSeller seller = payOrderService.getWeiSeller(user.getWeiID());
	if (seller == null || seller.getMobileIsBind() != Short.parseShort(MobileBindEnum.Bind.toString())) {
	    result.setMsg("用户没有绑定手机号码");
	    result.setStatus(-4);
	    return JsonUtil.objectToJson(result);
	}
	paypassword = MD5Encrypt.encrypt(paypassword);// 加密
	if (!paypassword.equals(wallet.getPayPassword())) {
	    result.setMsg("支付密码错误");
	    result.setStatus(-3);
	    return JsonUtil.objectToJson(result);
	}
	String code = String.valueOf(Math.random()).substring(2, 8);// 随机生成验证码
	RedisUtil.delete("pay_send_code_" + String.valueOf(user.getWeiID()));// 删除之前的验证码
	RedisUtil.delete("pay_send_count_" + String.valueOf(user.getWeiID()));// 删除之前的验证码
	Object object = RedisUtil.getObject("pay_send_count_" + String.valueOf(user.getWeiID()));// 查看发送次数
	int count = 1;
	if (object != null) {
	    count = Integer.parseInt(object.toString());
	    if (count >= 3) {// 三分钟三次
		result.setStatus(-5);
		result.setMsg("发送验证码太频繁");
		return JsonUtil.objectToJson(result);
	    }
	    count++;
	}
	RedisUtil.setObject("pay_send_count_" + String.valueOf(user.getWeiID()), count, 180);// 缓存验证码，3分钟
	RedisUtil.delete("pay_send_code_" + String.valueOf(user.getWeiID()));// 删除之前的验证码
	RedisUtil.setObject("pay_send_code_" + String.valueOf(user.getWeiID()), code, 300);// 缓存验证码，5分钟
	// 保存手机验证码进数据库
	TVerificationId tvId = new TVerificationId();
	tvId.setGetTime(new Date());// 时间
	tvId.setStatus(Short.parseShort("0"));// 状态
	tvId.setType(Short.parseShort(VerifyCodeTypeEnum.pay.toString()));// 类型是支付发送的验证码
	tvId.setUserIp(ip);// 来源IP
	tvId.setVcode(code);// 生成的验证码
	tvId.setVerificationtext("发送支付验证码：" + seller.getMobilePhone());// 内容
	tvId.setWeiNo(user.getWeiID());// 微店号
	payOrderService.insertTVerificationId(tvId);// 验证码入库
	String[] param = new String[] { seller.getMobilePhone(), code };// 参数一是手机号，参数二是验证码
	SendSMSUtil smsUtil = new SendSMSUtil();
	smsUtil.sendSMS(param, VerifyCodeTypeEnum.pay);// 发送验证码
	// SendSMSByMobile.sendSMS(seller.getMobilePhone(), "验证码" + code +
	// "[微店网]");
	result.setStatus(1);
	result.setMsg("发送成功");
	return JsonUtil.objectToJson(result);//
    }

    /**
     * 验证手机验证码
     * 
     * @param paypassword
     *            支付密码
     * @param code
     *            手机验证码
     * @param walletAmout
     *            需要用微钱包的余额
     * @param orderNo
     *            订单号
     * @param verify
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/verifySMS", method = { RequestMethod.POST, RequestMethod.GET })
    public String verifySMS(String paypassword, String code, String orderNo,String payType) {
//    	LoginUser user=itest.getLoginUser();
	LoginUser user = getLoginUser();
	String str = payOrderService.verifySMS(paypassword, code, orderNo, user,payType);
	return str;
    }

    /**
     * 获取访问用户的客户端IP（适用于公网与局域网）.
     */
    public static final String getIpAddr(final HttpServletRequest request) {
	if (request == null) {
	    return "";
	    // throw (new
	    // Exception("getIpAddr method HttpServletRequest Object is null"));
	}
	String ipString = request.getHeader("x-forwarded-for");
	if (StringUtils.isBlank(ipString) || "unknown".equalsIgnoreCase(ipString)) {
	    ipString = request.getHeader("Proxy-Client-IP");
	}
	if (StringUtils.isBlank(ipString) || "unknown".equalsIgnoreCase(ipString)) {
	    ipString = request.getHeader("WL-Proxy-Client-IP");
	}
	if (StringUtils.isBlank(ipString) || "unknown".equalsIgnoreCase(ipString)) {
	    ipString = request.getRemoteAddr();
	}

	// 多个路由时，取第一个非unknown的ip
	final String[] arr = ipString.split(",");
	for (final String str : arr) {
	    if (!"unknown".equalsIgnoreCase(str)) {
		ipString = str;
		break;
	    }
	}

	return ipString;
    }

    @RequestMapping(value = "/success", method = { RequestMethod.POST, RequestMethod.GET })
    public String success(String orderNo, Model model) {
    ReturnModel rm=	orderService.getPayBackVO(orderNo);
    if(rm!=null&&rm.getStatu().equals(ReturnStatus.Success)){
    	OrderModelReturn mo=(OrderModelReturn)rm.getBasemodle();
    	model.addAttribute("vo", mo);
    	model.addAttribute("state",1);
    }else {
    	model.addAttribute("state",0);
	}
	model.addAttribute("orderNo", orderNo);
	return "pay/successNew";
    }

//    @RequestMapping(value = "/successNew", method = { RequestMethod.POST, RequestMethod.GET })
//    public String successNew(String orderNo, Model model) {
//		ReturnModel rm = orderService.getPayBackVO(orderNo);
//		if (rm != null && rm.getStatu().equals(ReturnStatus.Success)) {
//			OrderModelReturn mo = (OrderModelReturn) rm.getBasemodle();
//			model.addAttribute("vo", mo);
//			model.addAttribute("state", 1);
//		} else {
//			model.addAttribute("state", 0);
//		}
//		model.addAttribute("orderNo", orderNo);
//		return "pay/successNew";
//    }

    
    
    @RequestMapping(value = "/recharge", method = { RequestMethod.POST, RequestMethod.GET })
    public String recharge(Model model) {
	LoginUser user = getLoginUser();
	List<UBankCard> bankList = payOrderService.getBankCardList(user.getWeiID());
	List<BankCardVO> bcList = new ArrayList<BankCardVO>();
	if (bankList != null && bankList.size() > 0) {		
	    for (UBankCard bc : bankList) {
	    	BankCardVO  bCard = new BankCardVO(bc);
			String num = bc.getBanckCard();
			if (!ObjectUtil.isEmpty(num) && num.length() > 3) {
			    num = num.substring(num.length() - 4, num.length());
			    bCard.setBanckCard(num);
			}
			bcList.add(bCard);
	    }
	}
	model.addAttribute("list", bcList);// 银行卡列表
	return "pay/recharge";
    }

    /**
     * 
     * @param price
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/rechargeajax", method = { RequestMethod.POST, RequestMethod.GET })
    public String rechargeajax(@RequestParam(required = false, defaultValue = "0") double price) {
	LoginUser user = getLoginUser();
	ResultMsg result = new ResultMsg();// 返回
	if (price < 1) {
	    result.setStatus(-1);
	    result.setMsg("输入的金额不能小于1元");
	} else {
	    OPayOrder order = new OPayOrder();
	    order.setOrderFrom((short) 1);// 来源pc
	    order.setOrderTime(new Date());// 时间
	    order.setState(Short.parseShort(OrderStatusEnum.Nopay.toString()));// 未支付状态
	    order.setTotalPrice(price);// 充值的金额
	    order.setTypeState(Short.parseShort(OrderTypeEnum.ChongZhi.toString()));// 充值订单
	    order.setWalletmoney(0D);// 微钱包为0
	    order.setWeiId(user.getWeiID());// 用户微店号
	    order.setPayType(Short.parseShort(PayTypeEnum.LLPay.toString()));// 连连的快捷支付
	    order.setPayOrderId(GenerateOrderNum.getInstance().GenerateOrder());// 生成订单号
	    order.setOrderDate(new Date());
	    if (payOrderService.insertPayOrder(order)) {
	    	OPayOrderLog orderLog = new OPayOrderLog();
	    	orderLog.setCreateTime(new Date());
	    	orderLog.setPayOrderId(order.getPayOrderId());
	    	orderLog.setState((short) 0);
	    	orderLog.setTotalAmout(order.getTotalPrice());
	    	orderLog.setWeiId(order.getWeiId());
	    	payOrderService.add(orderLog);
	    	
		result.setStatus(1);
		result.setMsg(order.getPayOrderId());
	    } else {
		result.setStatus(1);
		result.setMsg("添加充值订单失败");
	    }
	}
	return JsonUtil.objectToJson(result);
    }

    @RequestMapping(value = "/test", method = { RequestMethod.POST, RequestMethod.GET })
    public String test(){
        return "pay/test";
    } 
  
    @RequestMapping(value = "/testSubmit", method = { RequestMethod.POST, RequestMethod.GET })
    public String testSubmit(String orderNo,Double price,Model model){ 
        ResultMsg result = new ResultMsg();// 返回
        if(orderNo==null || price ==null){
            result.setMsg("参数错误");
            result.setStatus(-1);
            model.addAttribute("jsons",JsonUtil.objectToJson(result));
            return "pay/testSubmit";
        }
        
		PayResult pr = payOrderService.OrderPaymentSuccess(orderNo,price,PayTypeEnum.TestPay);
		if(pr.getState() ==PayResultStateEnum.Success)
		{
			pr = payOrderService.orderDataProcessing(orderNo, PayTypeEnum.TestPay);
		}
        if(pr!=null)
        {
            model.addAttribute("jsons",JsonUtil.objectToJson(pr));
            return "pay/testSubmit"; 
        }
        result.setMsg("参数错误");
        result.setStatus(-2);
        model.addAttribute("jsons",JsonUtil.objectToJson(result));
        return "pay/testSubmit"; 
    }


    /**
     * 验证是否支付完成
     * 
     * @param paypassword
     * @param code
     * @param orderNo
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/yzPayState", method = { RequestMethod.POST, RequestMethod.GET })
    public String yzPayState(String orderNo) {
	ResultMsg result = new ResultMsg();
	OPayOrder order = payOrderService.getPayOrder(orderNo);
	if (order != null && order.getState().intValue() == 1) {
	    result.setStatus(1);// 支付成功
	} else {
	    result.setStatus(0);// 支付失败
	}
	return JsonUtil.objectToJson(result);
    }

    /**
     * 验证是否有支付锁
     * 
     * @param orderNo
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/yzResubmit", method = { RequestMethod.POST, RequestMethod.GET })
    public String yzResubmit(String orderNo) {
	ResultMsg result = payOrderService.getIszfs(orderNo);
	return JsonUtil.objectToJson(result);
    }

    @Autowired
    private IRepaymentService repaymentService;

    /**
     * 重新生成支付订单
     * 
     * @param orderNo
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/Repayment", method = { RequestMethod.POST, RequestMethod.GET })
    public String Repayment(String orderNo) {
	ResultMsg result = repaymentService.getNewPayOrder(orderNo);
	return JsonUtil.objectToJson(result);
    }

    /**
     * 查询订单状态
     * 
     * @param orderNo
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getPayState", method = { RequestMethod.POST, RequestMethod.GET })
    public String getPayState(String orderNo,boolean coinPay) {
	ResultMsg result = new ResultMsg();
	result.setStatus(0);
	OPayOrder order = payOrderService.getPayOrder(orderNo);
	UWallet wallet = payOrderService.getById(UWallet.class, order.getWeiId());
	if (coinPay && wallet != null) {
		payOrderService.UP_getCoinPayAmount(order);
	}else {
		payOrderService.UP_CoinPayAmountDel(order);
	}
	if (order != null && order.getState().shortValue() == Short.parseShort(OrderStatusEnum.Tovoided.toString())) {
	    result.setStatus(1);
	}
	return JsonUtil.objectToJson(result);
    }

    public static double sub(double v1, double v2) {
	BigDecimal b1 = new BigDecimal(Double.toString(v1));
	BigDecimal b2 = new BigDecimal(Double.toString(v2));
	return b1.subtract(b2).doubleValue();
    }
    
    @ResponseBody
    @RequestMapping(value = "/checkOrder", method = { RequestMethod.POST, RequestMethod.GET })
    public String checkOrder(String orderNo) {
	ResultMsg result = new ResultMsg();
	result.setStatus(0);
	OPayOrder order = payOrderService.getPayOrder(orderNo);	
	if (order != null && order.getState().shortValue() == Short.parseShort(OrderStatusEnum.Payed.toString())) {
	    result.setStatus(1);
	    result.setMsg(orderNo);
	}
	else
	{
		result.setStatus(-1);
		result.setMsg("支付失败，请稍后重试");
	}
	return JsonUtil.objectToJson(result);
    }
    
    
}
