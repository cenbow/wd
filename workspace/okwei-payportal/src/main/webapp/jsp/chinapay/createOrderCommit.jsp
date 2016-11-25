<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<%@ page import="java.util.*"%>
<jsp:useBean id="paybean" scope="request" class="com.chinapay.bean.PaymentBean" />
<jsp:useBean id="pay_url" scope="request" class="java.lang.String" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<link rel="stylesheet" type="text/css" href="./css/style.css">
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>ChinaPay 银联电子支付</title>
<script type="text/javascript">
  window.onload=function(){
 	document.getElementById("payment").submit();
 } 
 </script>
</head>

<body>
<form name="payment" id="payment" action="${pay_url}" method="POST">

<input type=hidden name="MerId" value="${paybean.merId}">
<input type=hidden name="OrdId" value="${paybean.ordId}">
<input type=hidden name="TransAmt" value="${paybean.transAmt}">
<input type=hidden name="CuryId" value="${paybean.curyId}">
<input type=hidden name="TransDate" value="${paybean.transDate}">
<input type=hidden name="TransType" value="${paybean.transType}">
<input type=hidden name="Version" value="${paybean.version}">
<input type=hidden name="BgRetUrl" value="${paybean.bgRetUrl}">
<input type=hidden name="PageRetUrl" value="${paybean.pageRetUrl}">
<input type=hidden name="Priv1" value="${paybean.priv1}">
<input type=hidden name="ChkValue" value="${paybean.chkValue}">
<input type=hidden name="GateId" value="${paybean.gateId}">
<input type=hidden name="ClientIp" value="${paybean.userIp}">
</form>
如果您的浏览器没有弹出支付页面，请点击按钮<input type='button' name='v_action' value='提交订单' onClick='document.getElementById("payment").submit();'>再次提交。
	
</body>
</html>