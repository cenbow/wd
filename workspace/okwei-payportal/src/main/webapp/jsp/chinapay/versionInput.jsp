<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<%@ page import="java.util.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<link rel="stylesheet" type="text/css" href="./css/style.css">
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>ChinaPay 银联电子支付</title>
</head>

<script language="javascript">
</script>
<br>
<p class="menu">
	<font size="2">
			<a href="./" class="menu">首页</a>
			&gt; <a href="./createOrder" class="menu">支付请求</a>
			&gt; 版本选择
	</font>
</p>
<form name="createOrder" action="./createOrder" method="GET">
	
		<table>
			<tr>
    			<td>接口版本选择：</td>
    			<td><select size="1" name="version">
    			<option value="20141120" selected>4.0版本(20141120)</option>
    			<option value="20140522">20140522防钓鱼版本(20140522)</option>
    			</select></td>
 			</tr>
		</table>		
	<hr>
	<input type='button' name='v_action' value='确定' onClick='document.createOrder.submit()'>
		
</form>

</html>