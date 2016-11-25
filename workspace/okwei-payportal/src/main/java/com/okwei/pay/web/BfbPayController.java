package com.okwei.pay.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.jdom.JDOMException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bfb.util.BfbPaySetting;
import com.bfb.util.BfbSdkComm;
import com.okwei.bean.domain.OPayOrder;
import com.okwei.bean.enums.PayTypeEnum;
import com.okwei.bean.vo.BResultMsg;
import com.okwei.pay.bean.enums.PayResultStateEnum;
import com.okwei.pay.bean.vo.BaseSSOController;
import com.okwei.bean.vo.LoginUser;
import com.okwei.pay.bean.vo.PayResult;
import com.okwei.pay.dao.IPayOrderDAO;
import com.okwei.pay.service.IPayOrderService;
import com.okwei.service.order.IBasicPayService;

@Controller
@RequestMapping(value="/bfbpay")
public class BfbPayController extends BaseSSOController {

    @Autowired
    IPayOrderService service;
    
    @Autowired 
    IBasicPayService payService;
    
    private Log logger = LogFactory.getLog(this.getClass());
    String paydomain = ResourceBundle.getBundle("domain").getString("paydomain");
    
	   @RequestMapping(value = "/payrequest",method ={RequestMethod.POST,RequestMethod.GET})
	    public String payrequest(@RequestParam(required = false,defaultValue = "") String orderNo,
	    		HttpServletRequest request,HttpServletResponse response,Model model) throws JDOMException, IOException
	    {
	    	LoginUser loginUser = super.getLoginUser();
	    	int fee =0;
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
		   
	    	request.setCharacterEncoding("gbk");
			response.setCharacterEncoding("gbk");
			response.setContentType("text/html;charset=gbk");  
			response.setHeader("content-type","text/html;charset=gbk");
			//设置支付参数字串
			
			String order_no="order_no=" +orderNo; //订单号
			String total_amount ="total_amount=" + String.valueOf(fee);//总金额，以分为单位
			String sp_no="sp_no=" +BfbPaySetting.sp_no;//百度钱包商户号ַ
		    String BFB_PAY_DIRECT_NO_LOGIN_URL=BfbPaySetting.DIRECTURL;//不登录支付 接口地址
		    String  service_code="service_code=1";//表示即时到帐支付
		    String currency="currency=1"; //支付的币种是人民币
		    String input_charset="input_charset=1";//参数值的默认编码为GBK
		    String version="version=2";//百度钱包接口版本 固定为2
		    String sign_method="sign_method=1";//签名方式默认MD5
		    String pay_type ="pay_type=2";//支付方式 1余额支付 2百度钱包 3银行直连
		    //String bank_no ="bank_no="+request.getParameter("bank_no"); 如果是银行直连 必填 
		    String goods_name ="goods_name=微店网订单";//商品名称
		    String goods_name1="goods_name="+ URLEncoder.encode("微店网订单","gbk");
		    //订单时间
		    Calendar   c   =   Calendar.getInstance(); 
		    c.add(Calendar.DAY_OF_MONTH, 2);  
		    SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");  
	        String mDateTime=formatter.format(c.getTime());  
		    String strExpire=mDateTime.substring(0, 14);//
		    String expire_time="expire_time=" +strExpire; //交易过期时间 默认2个月
		    String order_create_time1=formatter.format(System.currentTimeMillis()); 
		    String order_create_time="order_create_time=" +order_create_time1;//订单创建时间
		    
		    String return_url ="return_url="+BfbPaySetting.return_url;//异步通知地址
		    String return_url1="return_url="+URLEncoder.encode(BfbPaySetting.return_url,"gbk");

		    String page_url ="page_url="+BfbPaySetting.page_url;//同步通知地址
		    String page_url1="page_url="+URLEncoder.encode(BfbPaySetting.page_url,"gbk");
		    //String goods_category ="goods_category=1";  //商品分类号 非必填
		    //String goods_desc ="goods_desc=微店网订单";//商品的描述信息 非必填
		    //String goods_desc1= "goods_desc="+URLEncoder.encode(request.getParameter("goods_desc"),"gbk");
		    //String goods_desc1 ="goods_desc="+tempgoods_desc;
		    //String goods_url ="goods_url="+request.getParameter("goods_url");//商品在商户网站上的URL 非必填
		    //String goods_url1="goods_url="+URLEncoder.encode(request.getParameter("goods_url"),"gbk");

		    //String unit_amount ="unit_amount="+request.getParameter("unit_amount"); //商品单价，以分为单位  非必填
		    //String unit_count ="unit_count="+request.getParameter("unit_count"); //商品数量  非必填
		    //String transport_amount ="transport_amount="+request.getParameter("transport_amount");//运费   非必填

		    //String buyer_sp_username ="buyer_sp_username=微店昵称"; //买家在商户网站的用户名 非必填
	        //String buyer_sp_username1 ="buyer_sp_username="+URLEncoder.encode(tempSPUserName,"gbk");

		    //String sp_uno ="sp_uno="+request.getParameter("sp_uno"); //微店号
		    //String extra ="extra="+tempextra;//商户自定义数据
		    //String extra1="extra="+URLEncoder.encode(tempextra,"gbk");
		  
		    //ǩ����ƴ������
			String[]array={
				    service_code,
					sp_no,
					order_create_time,
					order_no,
					goods_name,
					total_amount,
					currency,
					return_url,
					page_url,
					pay_type,
					expire_time,
					input_charset,
					version,
					sign_method
					};
			//���������ƴ������
			String[]array1={
				    service_code,
					sp_no,
					order_create_time,
					order_no,
					goods_name1,
					total_amount,
					currency,
					return_url1,
					page_url1,
					pay_type,
					expire_time,
					input_charset,
					version,
					sign_method
					};
			//签名后的请求URL
			String getURL=new BfbSdkComm().create_baifubao_pay_order_url(array,array1,BFB_PAY_DIRECT_NO_LOGIN_URL);

/*			response.sendRedirect(getURL); 
		    out.flush();
		    out.close();*/
			return "redirect:"+getURL;
    }
	   
	   @ResponseBody
	   @RequestMapping(value = "/BFBPayNotify",method ={RequestMethod.POST,RequestMethod.GET})
	    public String BFBPayNotify(HttpServletRequest request,HttpServletResponse response) throws IOException
	    {		   
		   PayResult payResult = payedNotify(request,response);
           if (payResult.getState() == PayResultStateEnum.TryAgain)
           {
               return "error";
           }
           else
           {
               /**
                * 3、支付通知结果的回执
                * 作用：	收到通知，并验证通过，向百付宝发起回执。百付宝GET请求商户的return_url页面，商户这边的响应
                * 中必须包含以下部分，百付宝只有接收到特定的响应信息后，才能确认商户已经收到通知，并验证通过。这样
                * 百付宝才不会再向商户发送支付结果通知
                */
        	   StringBuffer sb = new StringBuffer();
    			sb.append("<HTML><head>");
    			sb.append("<meta name=\"VIP_BFB_PAYMENT\" content=\"BAIFUBAO\">");
    			sb.append("</head>");
    			sb.append("<body>");
    			sb.append("Success");
    			sb.append("</body></html>");
    			return sb.toString();
               /**
               *   重要：接收到百度钱包的后台通知后，商户须返回特定的HTML页面。该页面应该满足以下要求：
               *	重要：HTML头部须包括<meta name="VIP_BFB_PAYMENT" content="BAIFUBAO">
               *	重要： 商户可以通过百付宝订单查询接口再次查询订单状态，二次校验
               *	重要： 该查询接口存在一定的延迟，商户可以不用二次校验，信任后台的支付结果通知便行
               * */
           }  
	    }

	   @RequestMapping(value = "/BFBPayPageReturn",method ={RequestMethod.POST,RequestMethod.GET})
	   public String BFBPageReturn(HttpServletRequest request,HttpServletResponse response,Model model) throws IOException
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
	   
		@RequestMapping(value = "/appNotifyWxPay",method ={RequestMethod.POST,RequestMethod.GET})
	    public void appNotifyBFBPay(String tiket,String orderNo,Integer notifyType) throws IOException{
			if(service.checkAppNotify(tiket, orderNo,notifyType,PayTypeEnum.BFBPay)){
				BfbQueryOrder(orderNo);
			}			
	    }
	   
	   
	   private PayResult payedNotify(HttpServletRequest request,HttpServletResponse response) throws IOException
	   {
		   PayResult payResult = new PayResult();
		   payResult.setState(PayResultStateEnum.Failure);
		   
		   String getStrPre=request.getQueryString();
		   if(getStrPre ==null ||"".equals(getStrPre))
		   {
			   return payResult;
		   }
		   
		   String getStr=java.net.URLDecoder.decode(getStrPre, "gbk");
		    String  []resultStrTemp=getStr.split("&");
		    String  []resultStr=new String[resultStrTemp.length-1];
		    //ȡǩ����
		    String signtemp=resultStrTemp[resultStrTemp.length-1];
		    String sign=signtemp.substring(signtemp.indexOf("=")+1,signtemp.length());

		    for(int i=0;i<resultStrTemp.length-1;i++){ 
		    	resultStr[i]=resultStrTemp[i];
		    	System.out.println(resultStr[i]);
		    }

			String  Localsign=new BfbSdkComm().make_sign(resultStr);
			StringBuffer sb = new StringBuffer();
			//验证结果
			if(request.getParameter("pay_result").equals("1"))//
			{
				String orderNo =request.getParameter("order_no");
				double payAmout =Double.valueOf(request.getParameter("total_amount"))/100;
				//验证签名
				if(sign.trim().equalsIgnoreCase(Localsign.trim()))//
				{   
					return BfbQueryOrder(orderNo);
/*					payResult = service.OrderPaymentSuccess(orderNo,payAmout,PayTypeEnum.BFBPay);
					if(payResult.getState() ==PayResultStateEnum.Success)
					{						
						//支付5.0版本-----------------------------------
						try {
							OPayOrder order=payService.getOPayOrder(orderNo, false);
							BResultMsg resultMsg=payService.editOrderDataProcess(order, PayTypeEnum.BFBPay);
							if(resultMsg.getState()==1){
								payResult.setState(PayResultStateEnum.Success);
								return payResult;
							}else {
								payResult.setState(PayResultStateEnum.Failure);
							}
							payResult.setMessage(resultMsg.getMsg());
							payResult.setOrderNo(orderNo); 
							logger.error("百付宝" + payResult.getOrderNo() + "|" + payResult.getMessage());
						} catch (Exception e) {
							logger.error("百付宝" + payResult.getOrderNo() + "|" + e.getMessage());
						}
					}*/
				}
				else if(!sign.trim().equalsIgnoreCase(Localsign.trim()))
				{
					payResult.setMessage("验签失败");
				}   
			}else{
				payResult.setMessage("支付失败");
			}
			logger.error("百付宝" + payResult.getOrderNo() + "|"+payResult.getMessage()); 
			return payResult;			   
	   }
	   
	   
	   
	   @SuppressWarnings("unused")
	   private PayResult BfbQueryOrder(String orderNo) throws IOException
	   {
	    	PayResult payResult = new PayResult();
	    	payResult.setState(PayResultStateEnum.TryAgain);   
		   
			String  service_code="service_code=11";//表示即时到帐支付
			String sp_no="sp_no=" +BfbPaySetting.sp_no;//百度钱包商户号
			String output_charset="output_charset=1";//百度钱包接口字符编码 gbk
			String output_type="output_type=1";//百度钱包接口返回格式：XML
			String version="version=2";//<!-- 百度钱包接口版本-->
			String sign_method="sign_method=1";//<!-- 签名校验算法md5-->
			String SP_KEY=BfbPaySetting.SP_KEY;//商户KEYַ
			String BFB_QUERY_ORDER_URL=BfbPaySetting.QUERYORDERURL;//<!--  百度钱包订单号查询支付结果接口URL    -->
			String order_no ="order_no="+orderNo;
			//参数集合
			String[]array={
					order_no,
					output_type,
					output_charset,
					service_code,
					sign_method,
					sp_no,
					version};
			//请求地址+参数
			String getURL=new BfbSdkComm().create_baifubao_pay_order_url(array,array,BFB_QUERY_ORDER_URL);
			//构建请求对象
			HttpClient httpClient =  new  HttpClient();    
			httpClient.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "GBK");
			httpClient.getHttpConnectionManager().getParams().setConnectionTimeout( 5000 );   
			GetMethod getMethod =  new  GetMethod(getURL);    
			getMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT,  5000 );   
			getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new  DefaultHttpMethodRetryHandler());         
			String responseStr = "" ;    
			 try  
			 {    
			     int  statusCode = httpClient.executeMethod(getMethod); 
			     if  (statusCode != HttpStatus.SC_OK) 
			     {    
			        return payResult;
			     }    
			     //返回的xml 字串
			     String gbkxml = getMethod.getResponseBodyAsString();			 
			     String result= gbkxml;
			    Document doc = null;
			    doc = DocumentHelper.parseText(result); 
			    //解析xml
			    Element root = doc.getRootElement(); 
			    List listTemp = root.elements();  
			    String []resultArry=new String[listTemp.size()];
			    StringBuffer strBur=new StringBuffer();
			    int i=0;
			    for(Iterator its =  listTemp.iterator();its.hasNext();){   
			        Element chileEle = (Element)its.next();  		        	
		        	if(chileEle.getName() !="goods_name"){
		        		resultArry[i]=chileEle.getName()+"="+chileEle.getStringValue();
		        	}
		        	else{
		        		resultArry[i]="goods_name=微店网订单";
		        	}
			        strBur.append(resultArry[i]+"<br/>");
			        i++;
			    }   
			
			    String signTemp=resultArry[resultArry.length-1];
			    String resultSign=signTemp.substring(signTemp.indexOf("=")+1,signTemp.length());
			    String[] SignArry=new String [resultArry.length-1];
			    for(int j=0;j<resultArry.length-1;j++)
			    {
			    	SignArry[j]=resultArry[j];
			    }
			    //查询是否是成功的
			   if(root.element("query_status").getText().equals("0"))
			   {   
				   //根据返回的结果前面
				   String RspLocalSign=new BfbSdkComm().make_sign(SignArry);
				   //验签
				   if(RspLocalSign.equalsIgnoreCase(resultSign) &&root.element("pay_result").getText().equals("1"))
				   {
						double payAmout =Double.valueOf(root.element("total_amount").getText())/100;
					   //接收返回数据成功，验签通过，系百度钱包返回数据
					   payResult = service.OrderPaymentSuccess(orderNo,payAmout,PayTypeEnum.BFBPay);
						if(payResult.getState() ==PayResultStateEnum.Success)
						{						
							//支付5.0版本-----------------------------------
							try {
								OPayOrder order=payService.getOPayOrder(orderNo, false);
								BResultMsg resultMsg=payService.editOrderDataProcess(order, PayTypeEnum.BFBPay);
								if(resultMsg.getState()==1){
									payResult.setState(PayResultStateEnum.Success);
									return payResult;
								}else {
									payResult.setState(PayResultStateEnum.Failure);
								}
								payResult.setMessage(resultMsg.getMsg());
								payResult.setOrderNo(orderNo); 
								logger.error("百付宝" + payResult.getOrderNo() + "|" + payResult.getMessage());
							} catch (Exception e) {
								logger.error("百付宝" + payResult.getOrderNo() + "|" + e.getMessage());
							}
						}
		    	   }
			   }
			 
			      }catch (HttpException e)
			 		{    
			
			      }
			 	catch (DocumentException e) 
			      {
			
				  } 
			      finally  
			      {    
			    	  //重置
			    	  getMethod.releaseConnection();    
			      }   
			   
			   return payResult;		   
	   }
	   
	  
}
