package com.okwei.pay.web;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.okwei.bean.domain.OPayOrder;
import com.okwei.bean.enums.PayTypeEnum;
import com.okwei.bean.vo.BResultMsg;
import com.okwei.pay.bean.enums.PayResultStateEnum;
import com.okwei.pay.bean.vo.BaseSSOController;
import com.okwei.bean.vo.LoginUser;
import com.okwei.pay.bean.vo.PayResult;
import com.okwei.pay.service.IPayOrderService;
import com.okwei.service.order.IBasicPayService;
import com.tenpay.ClientResponseHandler;
import com.tenpay.RequestHandler;
import com.tenpay.ResponseHandler;
import com.tenpay.TenPaySetting;
import com.tenpay.TenpayHttpClient;
import com.tenpay.TenpayUtil;


@Controller
@RequestMapping(value="/tenpay")
public class TenPayController extends BaseSSOController {
	 
    @Autowired
    IPayOrderService service;

    @Autowired 
    IBasicPayService payService;
    
    private Log logger = LogFactory.getLog(this.getClass());
    String paydomain = ResourceBundle.getBundle("domain").getString("paydomain");
	//request
	/**
     * 调起财付通支付
     * @throws UnsupportedEncodingException 
     */
    @SuppressWarnings("unused")
    @RequestMapping(value = "/payrequest",method ={RequestMethod.POST,RequestMethod.GET})
	private String gotoTenPay(HttpServletRequest request,HttpServletResponse response,
			@RequestParam(required = false,defaultValue = "") String orderNo,Model model) 
    		throws UnsupportedEncodingException{
  	
    	int fee = 0;
    	LoginUser loginUser = super.getLoginUser();
    	PayResult payResult = service.verifilyOrder(orderNo, loginUser.getWeiID(),true);
    	if(payResult.getState() == PayResultStateEnum.Success)
    	{
    		double totalAmout = Double.valueOf(payResult.getMessage());
    		fee = (int) (totalAmout * 100);
    	}
    	else
    	{	//跳转到错误页 并提示消息
    		 return "redirect:"+paydomain+"/pay/error?type=" + payResult.getType()+"&msg="+new String(payResult.getMessage().getBytes(),"ISO8859-1");
		}
    	
    	
    	//设置请求编码
    	request.setCharacterEncoding("UTF-8");
    	//创建支付请求对象
    	RequestHandler reqHandler = new RequestHandler(request, response);
    	reqHandler.init();
    	//设置密钥
    	reqHandler.setKey(TenPaySetting.key);
    	//设置支付网关
    	reqHandler.setGateUrl("https://gw.tenpay.com/gateway/pay.htm");

    	//-----------------------------
    	//设置支付参数
    	//-----------------------------
    	reqHandler.setParameter("partner", TenPaySetting.partner);		        //商户号
    	reqHandler.setParameter("out_trade_no", orderNo);		//商家订单号
    	reqHandler.setParameter("total_fee", String.valueOf(fee));			        //商品金额,以分为单位
    	reqHandler.setParameter("return_url", TenPaySetting.return_url);		    //交易完成后跳转的URL
    	reqHandler.setParameter("notify_url", TenPaySetting.notify_url);		    //接收财付通通知的URL
    	reqHandler.setParameter("body", TenPaySetting.spname);	                    //商品描述
    	reqHandler.setParameter("bank_type", "DEFAULT");		    //银行类型(中介担保时此参数无效)
    	reqHandler.setParameter("spbill_create_ip",TenpayUtil.getIp(request));   //用户的公网ip，不是商户服务器IP
    	reqHandler.setParameter("fee_type", "1");                    //币种，1人民币
    	reqHandler.setParameter("subject", TenPaySetting.spname);              //商品名称(中介交易时必填)

    	//系统可选参数
    	reqHandler.setParameter("sign_type", "MD5");                //签名类型,默认：MD5
    	reqHandler.setParameter("service_version", "1.0");			//版本号，默认为1.0
    	reqHandler.setParameter("input_charset", "UTF-8");            //字符编码
    	reqHandler.setParameter("sign_key_index", "1");             //密钥序号


    	//业务可选参数
    	reqHandler.setParameter("attach", "");                      //附加数据，原样返回
    	reqHandler.setParameter("product_fee", "");                 //商品费用，必须保证transport_fee + product_fee=total_fee
    	reqHandler.setParameter("transport_fee", "0");               //物流费用，必须保证transport_fee + product_fee=total_fee
    	reqHandler.setParameter("time_start", TenpayUtil.getCurrTime());            //订单生成时间，格式为yyyymmddhhmmss
    	reqHandler.setParameter("time_expire", "");                 //订单失效时间，格式为yyyymmddhhmmss
    	reqHandler.setParameter("buyer_id", "");                    //买方财付通账号
    	reqHandler.setParameter("goods_tag", "");                   //商品标记
    	reqHandler.setParameter("trade_mode", "1");                 //交易模式，1即时到账(默认)，2中介担保，3后台选择（买家进支付中心列表选择）
    	reqHandler.setParameter("transport_desc", "");              //物流说明
    	reqHandler.setParameter("trans_type", "1");                  //交易类型，1实物交易，2虚拟交易
    	reqHandler.setParameter("agentid", "");                     //平台ID
    	reqHandler.setParameter("agent_type", "");                  //代理模式，0无代理(默认)，1表示卡易售模式，2表示网店模式
    	reqHandler.setParameter("seller_id", "");                   //卖家商户号，为空则等同于partner

    	//请求的url
/*    	String requestUrl = reqHandler.getRequestFormSubmit();  	   	
    	model.addAttribute("requestUrl",requestUrl);
		return "gotobank";*/
    	String requestUrl = reqHandler.getRequestURL();
    	return "redirect:"+requestUrl;
    }
	
	@ResponseBody
    @RequestMapping(value = "/TenPayNotify",method ={RequestMethod.POST,RequestMethod.GET})
    public String TenPayNotify(HttpServletRequest request,HttpServletResponse response) throws Exception
    {
		PayResult payResult = payedNotify(request,response);
	   if(payResult.getState() != PayResultStateEnum.TryAgain)
	   {
		   return	"success";
	   }
	   else
	   {
		   return	"fail";
	   }	
    }
	
	/**
	 * 财付通支付成功同步跳转
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
    @RequestMapping(value = "/TenPayPageReturn",method ={RequestMethod.POST,RequestMethod.GET})
    public String TenPayPageReturn(HttpServletRequest request,HttpServletResponse response) throws Exception
    {
	   PayResult payResult = payedNotify(request,response);
	   if(payResult.getState() == PayResultStateEnum.Success)
	   {
		   //跳转成功页面
		   return "redirect:"+paydomain+"/pay/success?orderNo="+payResult.getOrderNo();
	   }
	   else
	   {
		   //跳转错误页面
		   return "redirect:"+paydomain+"/pay/error?type=" + payResult.getType()+"&msg="+new String(payResult.getMessage().getBytes(),"ISO8859-1");
	   }
    }
	
	
	/**
	 * 财付通成功处理
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	 private PayResult payedNotify(HttpServletRequest request,HttpServletResponse response) throws Exception 
	 {
    	PayResult payResult = new PayResult();
    	payResult.setState(PayResultStateEnum.Failure); 
    	
		ResponseHandler resHandler = new ResponseHandler(request, response);
    	resHandler.setKey(TenPaySetting.key);
    	//判断签名
    	if(resHandler.isTenpaySign()) {
    		//通知id
    		String notify_id = resHandler.getParameter("notify_id");   		
    		//创建请求对象
    		RequestHandler queryReq = new RequestHandler(null, null);
    		//通信对象
    		TenpayHttpClient httpClient = new TenpayHttpClient();
    		//应答对象
    		ClientResponseHandler queryRes = new ClientResponseHandler();
    		
    		//通过通知ID查询，确保通知来至财付通
    		queryReq.init();
    		queryReq.setKey(TenPaySetting.key);
    		queryReq.setGateUrl("https://gw.tenpay.com/gateway/simpleverifynotifyid.xml");
    		queryReq.setParameter("partner", TenPaySetting.partner);
    		queryReq.setParameter("notify_id", notify_id);
    		
    		//通信对象
    		httpClient.setTimeOut(5);
    		//设置请求内容
    		httpClient.setReqContent(queryReq.getRequestURL());
    		//后台调用
    		if(httpClient.call()) {
    			//设置结果参数
    			queryRes.setContent(httpClient.getResContent());
    			queryRes.setKey(TenPaySetting.key);
    				    				
    			//获取id验证返回状态码，0表示此通知id是财付通发起
    			String retcode = queryRes.getParameter("retcode");   			
    			//商户订单号
    			String out_trade_no = resHandler.getParameter("out_trade_no");
    			//财付通订单号
    			String transaction_id = resHandler.getParameter("transaction_id");
    			//金额,以分为单位
    			String total_fee = resHandler.getParameter("total_fee");
    			//如果有使用折扣券，discount有值，total_fee+discount=原请求的total_fee
    			String discount = resHandler.getParameter("discount");
    			//支付结果
    			String trade_state = resHandler.getParameter("trade_state");
    			//交易模式，1即时到账，2中介担保
    			String trade_mode = resHandler.getParameter("trade_mode");
    			//判断签名及结果
    			if(queryRes.isTenpaySign()&& "0".equals(retcode)){ 
    				if("1".equals(trade_mode)){       //即时到账 
    					if( "0".equals(trade_state)){
    				        //------------------------------
    						//即时到账处理业务开始
    						//------------------------------
    						
    						//处理数据库逻辑
    						//注意交易单不要重复处理
    						//注意判断返回金额
    						payResult = service.OrderPaymentSuccess(out_trade_no,Double.valueOf(total_fee)/100,PayTypeEnum.TenPay);
    						if(payResult.getState() ==PayResultStateEnum.Success)
    						{
//    							payResult = service.orderDataProcessing(out_trade_no, PayTypeEnum.TenPay);
    							OPayOrder order=payService.getOPayOrder(out_trade_no, false);
    							BResultMsg resultMsg=payService.editOrderDataProcess(order, PayTypeEnum.TenPay);
    							if(resultMsg.getState()==1){
    								payResult.setState(PayResultStateEnum.Success);
    							}else {
    								payResult.setState(PayResultStateEnum.Failure);
    							}
    							payResult.setMessage(resultMsg.getMsg());
    							payResult.setOrderNo(out_trade_no); 
    						}
    						
    						logger.error("财付通支付" + payResult.getOrderNo() + "|" + payResult.getMessage());
    						  						
    						//------------------------------
    						//即时到账处理业务完毕
    						//------------------------------
    						
    						//System.out.println("即时到账支付成功");
    						//给财付通系统发送成功信息，财付通系统收到此结果后不再进行后续通知
    						//resHandler.sendToCFT("success");
    					}
    				}
    			}
    		}
    	}
		
    	return	payResult;	 
	 }
	
}
