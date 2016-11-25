<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<%@ page import="java.util.*"%>
<jsp:useBean id="merId" scope="request" class="java.lang.String" />
<jsp:useBean id="errors" scope="request" class="java.util.ArrayList" />
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
			&gt; <a href="./refundSend" class="menu">退款请求</a>
			&gt; 退款订单数据组装
	</font>
</p>
<form name="refundSend" action="./refundSend" method="POST">

		<table>
			<tr>
				<td>
					<font color=red>*</font>商户号
				</td>

				<td>
                     <%= merId %>
                </td>
			</tr>
			<tr>
				<td>
					<font color=red>*</font>订单号
				</td>

				<td>
                     <input type="text" name="OrderId" maxlength="16"> &nbsp;(16位数字，原始交易订单号)
                </td>
			</tr>
			<tr>
				<td>
					<font color=red>*</font>订单金额
				</td>

				<td>
                     <input type="text" name="RefundAmount" value="000000000001" maxlength="12"> &nbsp;(12位数字，原始交易订单金额)
                </td>
			</tr>
			<tr>
				<td>
					<font color=red>*</font>交易日期
				</td>

				<td>
                     <input type="text" name="TransDate" maxlength="8"> &nbsp;(8位数字，原始订单交易日期)
                </td>
			</tr>
			<tr>
				<td>
					<font color=red>*</font>交易类型
				</td>

				<td>
                     <input type="text" name="TransType" value="0002" maxlength="4"> &nbsp;(4位数字，退款交易为0002)
                </td>
			</tr>
			<tr>
				<td>
					<font color=red>*</font>版本号
				</td>

				<td>
                     <input type="text" name="Version" value="20070129" maxlength="8"> &nbsp;(8位数字，退款接口版本号)
                </td>
			</tr>
			<tr>
				<td>
					<font color=red>*</font>退款应答接收URL
				</td>

				<td>
                     <input type="text" name="ReturnURL" value="http://131.252.83.73:8080/chinapay_java/getRefundResult" maxlength="80"> &nbsp;(不超过80字节，商户系统后台应答接受地址)
                </td>
			</tr>
						<tr>
				<td>
					商户私有域
				</td>

				<td>
                     <input type="text" name="Priv1" maxlength="40"> &nbsp;(英文或数字，不超过40字节，ChinaPay将原样返回给商户系统该字段填入的数据)
                </td>
			</tr>
		</table>	

	<hr>
	<input type="hidden" name="MerID" value="<%= merId %>">
	<input type='button' name='v_action' value='提交订单' onClick='document.refundSend.submit()'>
		
</form>

<body>

</body>
</html>