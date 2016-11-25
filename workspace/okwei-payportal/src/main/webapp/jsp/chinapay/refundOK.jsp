<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<%@ page import="java.util.*"%>
<jsp:useBean id="result" scope="request" class="java.lang.String" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<link rel="stylesheet" type="text/css" href="./css/style.css">
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>ChinaPay 银联电子支付</title>
</head>
<p class="menu">
	<font size="2">
			<a href="./" class="menu">首页</a>
			&gt; <a href="./refundSend" class="menu">退款请求</a>
			&gt; 退款提交完成
	</font>
</p>
<body>
<%= result %>
</body>
</html>