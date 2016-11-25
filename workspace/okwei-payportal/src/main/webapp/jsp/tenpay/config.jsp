<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ page import="com.tenpay.TenPaySetting" %>
<% 

//收款方
String spname =TenPaySetting.spname;// "微店网";  

//商户号
String partner =TenPaySetting.partner; // "1218322301";

//密钥
String key =TenPaySetting.key;// "9f7529bb659f6043a3f11b5600f1d1c2";

//交易完成后跳转的URL
String return_url =TenPaySetting.return_url;// "http://pay.okwei.net/tenpay_api_b2c/payReturnUrl.jsp";

//接收财付通通知的URL
String notify_url =TenPaySetting.notify_url;// "http://pay.okwei.net/tenpay_api_b2c/payNotifyUrl.jsp";

%>
