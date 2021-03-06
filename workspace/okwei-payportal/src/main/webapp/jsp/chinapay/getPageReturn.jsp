<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<%@ page import="java.util.*"%>
<jsp:useBean id="errors" scope="request" class="java.util.ArrayList" />
<jsp:useBean id="payResult" scope="request" class="com.chinapay.bean.PaymentBean" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<link rel="stylesheet" type="text/css" href="./css/style.css">
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>ChinaPay 银联电子支付</title>
</head>
<%
if (errors.size() > 0) {
%>
<hr>
	<font color="red">
<%
	java.util.Iterator it1 = errors.iterator();
	while (it1.hasNext()) {
		out.println("<p>");
		Object obj = it1.next();
		if (obj instanceof java.util.ArrayList) {
			java.util.Iterator it2 = ((java.util.ArrayList)obj).iterator();
			while (it2.hasNext()) {
				Object obj2 = it2.next();
				out.println(obj2 + "<br>");
			}
		}
		else {
			out.println(obj);
		}
		out.println("</p>");
	}
%>
	</font>
	<hr>
<%
}
%>

<br>
<p class="menu">
	<font size="2">
			<a href="./" class="menu">首页</a>
			&gt; <a href="./createOrder" class="menu">支付请求</a>
			&gt; 版本选择
			&gt; 支付订单数据组装
			&gt; 支付订单生成并提交
			&gt; 订单支付成功通知
	</font>
</p>
<%
	String  MerId = payResult.getMerId();
	String  OrdId = payResult.getOrdId();
	String  TransDate = payResult.getTransDate();
	String  TransType = payResult.getTransType();
	String  TransAmt = payResult.getTransAmt();
	String  CuryId = payResult.getCuryId();
	String  GateId = payResult.getGateId();
	String  Version	= payResult.getVersion();
	String  ChkValue = payResult.getChkValue();
	String  BgRetUrl = payResult.getBgRetUrl();
	String  PageRetUrl = payResult.getPageRetUrl();
	String  Priv1 = payResult.getPriv1();
	String  ClientIp = "";//payResult.getClientIP();
%>	
		<table border="1" cellpadding="2" cellspacing="0" style="border-collapse: collapse" bordercolor="#111111">
			<tr>
				<td>
					<font color=red>*</font>商户号
				</td>

				<td>
                     <%= MerId %>
                </td>
			</tr>
			<tr>
				<td>
					<font color=red>*</font>订单号
				</td>

				<td>
                     <%= OrdId %>
                </td>
			</tr>
			<tr>
				<td>
					<font color=red>*</font>商户日期
				</td>

				<td>
                     <%= TransDate %>
                </td>
			</tr>
			<tr>
				<td>
					<font color=red>*</font>交易类型
				</td>

				<td>
                     <%= TransType %>
                </td>
			</tr>
			<tr>
				<td>
					<font color=red>*</font>交易币种
				</td>

				<td>
                     <%= CuryId %>
                </td>
			</tr>
			<tr>
				<td>
					<font color=red>*</font>订单金额
				</td>

				<td>
                     <%= TransAmt %>
                </td>
			</tr>
			<tr>
				<td>
					支付网关号
				</td>

				<td>
                     <%= GateId %>
                </td>
			</tr>
			<tr>
				<td>
					<font color=red>*</font>版本号
				</td>

				<td>
                     <%= Version %>
                </td>
			</tr>
			<tr>
				<td>
					页面应答接收URL
				</td>

				<td>
                     <%= PageRetUrl %>
                </td>
			</tr>
			<tr>
				<td>
					<font color=red>*</font>后台应答接收URL
				</td>

				<td>
                     <%= BgRetUrl %>
                </td>
			</tr>
			<tr>
				<td>
					商户私有域
				</td>

				<td>
                     <%= Priv1 %>
                </td>
			</tr>
			<tr>
				<td>
					<font color=red>*</font>ChinaPay数字签名
				</td>

				<td width="800">
                     <pre><%= ChkValue %></pre>
                </td>
			</tr>

		</table>	

	<hr>

<script language=JavaScript>
	//document.payment.submit();
</script>	

<body>

</body>
</html>