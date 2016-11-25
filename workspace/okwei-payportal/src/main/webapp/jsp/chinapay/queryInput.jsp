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
			&gt; <a href="./querySend" class="menu">单笔查询请求</a>
			&gt; 单笔查询订单数据组装
	</font>
</p>
<form name="querySend" action="./querySend" method="POST">

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
                     <input type="text" name="OrdId" maxlength="16"> &nbsp;(16位数字，原始交易订单号)
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
                     <input type="text" name="TransType" value="0001" maxlength="4"> &nbsp;(4位数字，目前仅支持支付成功交易的查询。支付交易为0001)
                </td>
			</tr>
			<tr>
				<td>
					<font color=red>*</font>版本号
				</td>

				<td>
                     <select size="1" name="Version">
    				<option value="20060831" selected>20060831</option>
    				<option value="20140521" >20140521</option>
    				</select>&nbsp;(8位数字，查询接口版本号)
                </td>
			</tr>
			<tr>
				<td>
					商户私有域
				</td>

				<td>
                     <input type="text" name="Resv" maxlength="40"> &nbsp;(英文或数字，不超过40字节，ChinaPay将原样返回给商户系统该字段填入的数据)
                </td>
			</tr>
		</table>	

	<hr>
	<input type="hidden" name="MerID" value="<%= merId %>">
	<input type='button' name='v_action' value='提交订单' onClick='document.querySend.submit()'>
		
</form>

<p><font color="blue">
注： 1.目前查询功能仅支持对支付成功订单的查询，其他类型的查询暂不支持。<br>&nbsp;&nbsp;&nbsp;&nbsp;
	2.使用查询功能前，需要提前将商户用于查询的服务器IP，在ChinaPay进行配置后才可使用。<br>&nbsp;&nbsp;&nbsp;&nbsp;
	3.查询功能每个IP的默认查询间隔为30s。
</font></p>
<body>

</body>
</html>