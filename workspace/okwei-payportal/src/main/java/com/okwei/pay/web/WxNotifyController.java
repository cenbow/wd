package com.okwei.pay.web;

import java.io.IOException;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.filefilter.NotFileFilter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.llpay.client.utils.LLPayUtil;
import com.okwei.bean.domain.OPayOrder;
import com.okwei.bean.domain.OSupplyerOrder;
import com.okwei.bean.enums.PayTypeEnum;
import com.okwei.bean.vo.BResultMsg;
import com.okwei.bean.vo.LoginUser;
import com.okwei.bean.vo.ReturnModel;
import com.okwei.bean.vo.ReturnStatus;
import com.okwei.common.JsonUtil;
import com.okwei.pay.bean.enums.PayResultStateEnum;
import com.okwei.pay.bean.vo.PayResult;
import com.okwei.pay.service.IPayOrderService;
import com.okwei.service.impl.BaseService;
import com.okwei.service.order.IBasicPayService;
import com.okwei.util.RedisUtil;
import com.sdicons.json.mapper.MapperException;
import com.tenpay.TenpayUtil;
import com.wxpay.WxPaySetting;
import com.wxpay.WxRequestHandler;
import com.wxpay.WxResponseHandler;

@Controller
@RequestMapping(value="/wxpay")
public class WxNotifyController {
	
    @Autowired
    IPayOrderService service;
    @Autowired 
    IBasicPayService payService;
    private Log logger = LogFactory.getLog(this.getClass());
	

    @ResponseBody
	@RequestMapping(value = "/WxAppPayNotify",method ={RequestMethod.POST,RequestMethod.GET})
	 public String wxpayNotify(HttpServletRequest request,HttpServletResponse response,Model model) throws IOException
	{
		String xmlStr =LLPayUtil.readReqStr(request); 	
		SortedMap<String, String>  notifymap = TenpayUtil.xml2Map(xmlStr);
		//商户订单号
		if(notifymap ==null || notifymap.size() <1){			
			return "error";
		}
		
		String key="";
		String appid=notifymap.get("appid");
		String mch_id = notifymap.get("mch_id");
		PayTypeEnum payType =null;
		if(mch_id.equals(WxPaySetting.partner)){
			key = WxPaySetting.key;
			payType =PayTypeEnum.WxAppWDPay;
		}else if(mch_id.equals(WxPaySetting.publicpartner)){
			key = WxPaySetting.publickey;
			payType =PayTypeEnum.WxPay;
		}else{
			return "error";
		}
			
    	if(notifymap !=null && TenpayUtil.isWXsign(notifymap, key))
		{
			//商户订单号
			String out_trade_no =notifymap.get("out_trade_no");
			//财付通订单号
			String transaction_id =notifymap.get("transaction_id");
			//金额,以分为单位
			String total_fee =notifymap.get("total_fee");
			//如果有使用折扣券，discount有值，total_fee+discount=原请求的total_fee
			String discount =notifymap.get("discount"); 
			//支付结果
			String result_code =notifymap.get("result_code");  
			
			//判断签名及结果
			if ("SUCCESS".equals(result_code) ) 
			{
				return queryWxOrder(key,appid,mch_id,out_trade_no,transaction_id,payType);
				/*//创建请求对象
				WxRequestHandler queryReq = new WxRequestHandler(null, null);
				queryReq.setKey(key);  //设置密钥   	 
				queryReq.init();    
				//发起查询校验 设置查询参数
				SortedMap<String, String> packageParams = new TreeMap<String, String>();
		    	packageParams.put("appid", appid);				//公众账号ID
		    	packageParams.put("mch_id", mch_id);				//商户号
		    	packageParams.put("nonce_str",String.valueOf(TenpayUtil.buildRandom(18)) );			//随机字符串 
		    	packageParams.put("out_trade_no", out_trade_no);
		    	packageParams.put("transaction_id", transaction_id);
		    	String sign = queryReq.createSign(packageParams); //签名
		    	packageParams.put("sign", sign);
		    	queryReq.setParameters(packageParams);
		    	String xml_body = queryReq.parseToXML();       	//转换xml  
		    	String result = WxRequestHandler.httpPost(WxPaySetting.queryorder_url, xml_body);//发起查询订单
		    	SortedMap<String, String> map = TenpayUtil.xml2Map(result);
		    	if(TenpayUtil.isWXsign(map, key))
		    	{
			    	if(map !=null && map.get("return_code").equals("SUCCESS") && map.get("result_code").equals("SUCCESS")
			    			&& map.get("trade_state").equals("SUCCESS"))
			    	{
			    		//------------------------------
						//即时到账处理业务开始
						//------------------------------
				
						//处理数据库逻辑
						//注意交易单不要重复处理
						//注意判断返回金额	    		
//			    		PayResult payResult = service.OrderPaymentSuccess(out_trade_no,Double.valueOf(total_fee)/100,PayTypeEnum.WxAppWDPay);
//			    		if(payResult.getState() ==PayResultStateEnum.Success)
//			    		{
//				    		payResult = service.orderDataProcessing(out_trade_no, PayTypeEnum.WxAppWDPay);				    		
//				    		if(payResult.getState() != PayResultStateEnum.TryAgain)
//				    		{
//				    			return "success";
//				    		}	
//			    		}
			    		try {
			    			
			    			PayResult payResult = service.OrderPaymentSuccess(out_trade_no,Double.valueOf(total_fee)/100,PayTypeEnum.WxAppWDPay);
				    		if(payResult.getState()==PayResultStateEnum.Success){
				    			OPayOrder order=payService.getOPayOrder(out_trade_no, true);
			    				BResultMsg resultMsg=payService.editOrderDataProcess(order, payType);
								if(resultMsg.getState()==1){
									logger.error("微信支付" + out_trade_no + "|成功" );
									return "success";
								}else {
									logger.error("微信支付" + out_trade_no + "|"+resultMsg.getMsg() );
								}
				    		}
				    		logger.error("微信支付" + out_trade_no + "|"+payResult.getMessage() );
			    			
						} catch (Exception e) {
							// TODO: handle exception
							logger.error("微信支付" +out_trade_no + "|" +e.getMessage());
						}
			    		
			    		return "error";
						//------------------------------
						//即时到账处理业务完毕
						//------------------------------
			    		//给财付通系统发送成功信息，财付通系统收到此结果后不再进行后续通知			   
			    	}
		    	}*/
			//System.out.println("success 后台通知成功");			
			}
			//resHandler.sendToCFT("success");				
		}
		return "fail";
	}
    
	@RequestMapping(value = "/appNotifyWxPay",method ={RequestMethod.POST,RequestMethod.GET})
    public void appNotifyWxPay(String tiket,String orderNo,Integer notifyType){
		if(service.checkAppNotify(tiket, orderNo,notifyType,PayTypeEnum.WxAppWDPay)){
			queryWxOrder(WxPaySetting.key,WxPaySetting.appid,WxPaySetting.partner,orderNo,null,PayTypeEnum.WxAppWDPay);
		}			
    }
    
    
    private String queryWxOrder(String key,String appid,String mch_id,String out_trade_no,
    		String transaction_id,PayTypeEnum payType ) {
    	
		//创建请求对象
		WxRequestHandler queryReq = new WxRequestHandler(null, null);
		queryReq.setKey(key);  //设置密钥   	 
		queryReq.init();    
    	//发起查询校验 设置查询参数
		SortedMap<String, String> packageParams = new TreeMap<String, String>();
    	packageParams.put("appid", appid);				//公众账号ID
    	packageParams.put("mch_id", mch_id);				//商户号
    	packageParams.put("nonce_str",String.valueOf(TenpayUtil.buildRandom(18)) );			//随机字符串 
    	packageParams.put("out_trade_no", out_trade_no);
    	packageParams.put("transaction_id", transaction_id);
    	String sign = queryReq.createSign(packageParams); //签名
    	packageParams.put("sign", sign);
    	queryReq.setParameters(packageParams);
    	String xml_body = queryReq.parseToXML();       	//转换xml  
    	String result = WxRequestHandler.httpPost(WxPaySetting.queryorder_url, xml_body);//发起查询订单
    	SortedMap<String, String> map = TenpayUtil.xml2Map(result);
    	if(TenpayUtil.isWXsign(map, key))
    	{
	    	if(map !=null && map.get("return_code").equals("SUCCESS") && map.get("result_code").equals("SUCCESS")
	    			&& map.get("trade_state").equals("SUCCESS"))
	    	{
	    		try {
	    			String total_fee = map.get("total_fee");
	    			PayResult payResult = service.OrderPaymentSuccess(out_trade_no,Double.valueOf(total_fee)/100,PayTypeEnum.WxAppWDPay);
	    			if(payResult.getState() ==PayResultStateEnum.Success)
			    	{
		    			OPayOrder order=payService.getOPayOrder(out_trade_no, true);
	    				BResultMsg resultMsg=payService.editOrderDataProcess(order, payType);
						if(resultMsg.getState()==1){
							//logger.error("微信支付" + out_trade_no + "|成功" );
							return "success";
						}else {
							logger.error("微信支付" + out_trade_no + "|"+resultMsg.getMsg() );
						}
			    	}else {
			    		logger.error("微信支付失败：" + out_trade_no + "|"+payResult.getMessage() );
					}
				} catch (Exception e) {
					logger.error("微信支付" +out_trade_no + "|" +e.getMessage());
				}	    		   
	    	}
    	}
    	
    	return "error";
	}
}
