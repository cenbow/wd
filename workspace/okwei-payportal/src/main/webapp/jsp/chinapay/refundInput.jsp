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
<title>ChinaPay ��������֧��</title>
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
			<a href="./" class="menu">��ҳ</a>
			&gt; <a href="./refundSend" class="menu">�˿�����</a>
			&gt; �˿��������װ
	</font>
</p>
<form name="refundSend" action="./refundSend" method="POST">

		<table>
			<tr>
				<td>
					<font color=red>*</font>�̻���
				</td>

				<td>
                     <%= merId %>
                </td>
			</tr>
			<tr>
				<td>
					<font color=red>*</font>������
				</td>

				<td>
                     <input type="text" name="OrderId" maxlength="16"> &nbsp;(16λ���֣�ԭʼ���׶�����)
                </td>
			</tr>
			<tr>
				<td>
					<font color=red>*</font>�������
				</td>

				<td>
                     <input type="text" name="RefundAmount" value="000000000001" maxlength="12"> &nbsp;(12λ���֣�ԭʼ���׶������)
                </td>
			</tr>
			<tr>
				<td>
					<font color=red>*</font>��������
				</td>

				<td>
                     <input type="text" name="TransDate" maxlength="8"> &nbsp;(8λ���֣�ԭʼ������������)
                </td>
			</tr>
			<tr>
				<td>
					<font color=red>*</font>��������
				</td>

				<td>
                     <input type="text" name="TransType" value="0002" maxlength="4"> &nbsp;(4λ���֣��˿��Ϊ0002)
                </td>
			</tr>
			<tr>
				<td>
					<font color=red>*</font>�汾��
				</td>

				<td>
                     <input type="text" name="Version" value="20070129" maxlength="8"> &nbsp;(8λ���֣��˿�ӿڰ汾��)
                </td>
			</tr>
			<tr>
				<td>
					<font color=red>*</font>�˿�Ӧ�����URL
				</td>

				<td>
                     <input type="text" name="ReturnURL" value="http://131.252.83.73:8080/chinapay_java/getRefundResult" maxlength="80"> &nbsp;(������80�ֽڣ��̻�ϵͳ��̨Ӧ����ܵ�ַ)
                </td>
			</tr>
						<tr>
				<td>
					�̻�˽����
				</td>

				<td>
                     <input type="text" name="Priv1" maxlength="40"> &nbsp;(Ӣ�Ļ����֣�������40�ֽڣ�ChinaPay��ԭ�����ظ��̻�ϵͳ���ֶ����������)
                </td>
			</tr>
		</table>	

	<hr>
	<input type="hidden" name="MerID" value="<%= merId %>">
	<input type='button' name='v_action' value='�ύ����' onClick='document.refundSend.submit()'>
		
</form>

<body>

</body>
</html>