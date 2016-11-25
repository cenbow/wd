<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<%@ page import="java.util.*"%>
<jsp:useBean id="version" scope="request" class="java.lang.String" />
<jsp:useBean id="merId" scope="request" class="java.lang.String" />
<jsp:useBean id="OrdId" scope="request" class="java.lang.String" />
<jsp:useBean id="TransAmt" scope="request" class="java.lang.String" />
<jsp:useBean id="TransDate" scope="request" class="java.lang.String" />
<jsp:useBean id="TransTime" scope="request" class="java.lang.String" />
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
			&gt; <a href="./createOrder" class="menu">支付请求</a>
			&gt; 版本选择
			&gt; 支付订单数据组装
	</font>
</p>
<form name="createOrder" action="./createOrder" method="POST">
<%
	System.out.println("Version=" + version);
	if (version.equals("20141120")){
%>	
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
                     <input type="text" name="OrdId" value="<%= OrdId %>" maxlength="16"> &nbsp;(16位数字，订单号的第5～9位必须和商户号的第11～15位相同。不填由系统自动产生)
                </td>
			</tr>
			<tr>
				<td>
					<font color=red>*</font>订单金额
				</td>

				<td>
                     <input type="text" name="TransAmt" value="<%= TransAmt %>" maxlength="12"> &nbsp;(12位数字，不填默认金额为1分)
                </td>
			</tr>
			<tr>
				<td>
					<font color=red>*</font>交易日期
				</td>

				<td>
                     <input type="text" name="TransDate" value="<%= TransDate %>" maxlength="8"> &nbsp;(8位数字，不填系统默认为当前日期)
                </td>
			</tr>
			<tr>
				<td>
					<font color=red>*</font>交易类型
				</td>

				<td>
                     <input type="text" name="TransType" value="0001" maxlength="4"> &nbsp;(4位数字，支付交易为0001)
                </td>
			</tr>
			<tr>
				<td>
					<font color=red>*</font>交易币种
				</td>

				<td>
                     <input type="text" name="CuryId" value="156" maxlength="3"> &nbsp;(3位，默认为156 人民币)
                </td>
			</tr>
			<tr>
				<td>
					<font color=red>*</font>版本号
				</td>

				<td>
                     <input type="text" name="Version" value="<%= version %>" maxlength="8"> &nbsp;(8位数字，支付接口版本号)
                </td>
			</tr>
			<tr>
				<td>
					支付网关号
				</td>

				<td>
                     <input type="text" name="GateId" maxlength="4"> &nbsp;(4位数字，可以为空)
                </td>
			</tr>
			<tr>
				<td>
					页面应答接收URL
				</td>

				<td>
                     <input type="text" name="PageRetUrl" value="http://localhost:8080/chinapay_java/getPageReturn" maxlength="80"> &nbsp;(不超过80字节，商户系统前台应答接受地址)
                </td>
			</tr>
						<tr>
				<td>
					<font color=red>*</font>后台应答接收URL
				</td>

				<td>
                     <input type="text" name="BgRetUrl" value="http://131.252.83.73:8080/chinapay_java/getBgReturn" maxlength="80"> &nbsp;(不超过80字节，商户系统后台应答接受地址)
                </td>
			</tr>
						<tr>
				<td>
					商户私有域
				</td>

				<td>
                     <input type="text" name="Priv1" value="memo" maxlength="60"> &nbsp;(英文或数字，不超过60字节，ChinaPay将原样返回给商户系统该字段填入的数据)
                </td>
			</tr>
		</table>	

<%
	}else if (version.equals("20140522")){
%>
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
                     <input type="text" name="OrdId" value="<%= OrdId %>" maxlength="16"> &nbsp;(16位数字，不填由系统自动产生)
                </td>
			</tr>
			<tr>
				<td>
					<font color=red>*</font>订单金额
				</td>

				<td>
                     <input type="text" name="TransAmt" value="<%= TransAmt %>" maxlength="12"> &nbsp;(12位数字，不填默认金额为1分)
                </td>
			</tr>
			<tr>
				<td>
					<font color=red>*</font>交易日期
				</td>

				<td>
                     <input type="text" name="TransDate" value="<%= TransDate %>" maxlength="8"> &nbsp;(8位数字，不填系统默认为当前日期)
                </td>
			</tr>
			<tr>
				<td>
					<font color=red>*</font>交易类型
				</td>

				<td>
                     <input type="text" name="TransType" value="0001" maxlength="4"> &nbsp;(4位数字，支付交易为0001)
                </td>
			</tr>
			<tr>
				<td>
					<font color=red>*</font>交易币种
				</td>

				<td>
                     <input type="text" name="CuryId" value="156" maxlength="3"> &nbsp;(3位，默认为156 人民币)
                </td>
			</tr>
			<tr>
				<td>
					<font color=red>*</font>版本号
				</td>

				<td>
                     <input type="text" name="Version" value="<%= version %>" maxlength="8"> &nbsp;(8位数字，支付接口版本号)
                </td>
			</tr>
			<tr>
				<td>
					支付网关号
				</td>

				<td>
                     <input type="text" name="GateId" maxlength="4"> &nbsp;(4位数字，可以为空)
                </td>
			</tr>
			<tr>
				<td>
					页面应答接收URL
				</td>

				<td>
                     <input type="text" name="PageRetUrl" value="http://localhost:8080/chinapay_java/getPageReturn" maxlength="80"> &nbsp;(不超过80字节，商户系统前台应答接受地址)
                </td>
			</tr>
						<tr>
				<td>
					<font color=red>*</font>后台应答接收URL
				</td>

				<td>
                     <input type="text" name="BgRetUrl" value="http://131.252.83.73:8080/chinapay_java/getBgReturn" maxlength="80"> &nbsp;(不超过80字节，商户系统后台应答接受地址)
                </td>
			</tr>
						<tr>
				<td>
					商户私有域
				</td>

				<td>
                     <input type="text" name="Priv1" maxlength="60"> &nbsp;(英文或数字，不超过60字节，ChinaPay将原样返回给商户系统该字段填入的数据)
                </td>
			</tr>
		</table>
<%
	}
%>
	<hr>
	<input type="hidden" name="MerId" value="<%= merId %>">
	<input type='button' name='v_action' value='提交订单' onClick='document.createOrder.submit()'>
		
</form>

<body>

</body>
</html>