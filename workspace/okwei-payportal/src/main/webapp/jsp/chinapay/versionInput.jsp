<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<%@ page import="java.util.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<link rel="stylesheet" type="text/css" href="./css/style.css">
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>ChinaPay ��������֧��</title>
</head>

<script language="javascript">
</script>
<br>
<p class="menu">
	<font size="2">
			<a href="./" class="menu">��ҳ</a>
			&gt; <a href="./createOrder" class="menu">֧������</a>
			&gt; �汾ѡ��
	</font>
</p>
<form name="createOrder" action="./createOrder" method="GET">
	
		<table>
			<tr>
    			<td>�ӿڰ汾ѡ��</td>
    			<td><select size="1" name="version">
    			<option value="20141120" selected>4.0�汾(20141120)</option>
    			<option value="20140522">20140522������汾(20140522)</option>
    			</select></td>
 			</tr>
		</table>		
	<hr>
	<input type='button' name='v_action' value='ȷ��' onClick='document.createOrder.submit()'>
		
</form>

</html>