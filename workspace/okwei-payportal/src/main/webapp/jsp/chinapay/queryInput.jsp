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
			&gt; <a href="./querySend" class="menu">���ʲ�ѯ����</a>
			&gt; ���ʲ�ѯ����������װ
	</font>
</p>
<form name="querySend" action="./querySend" method="POST">

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
                     <input type="text" name="OrdId" maxlength="16"> &nbsp;(16λ���֣�ԭʼ���׶�����)
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
                     <input type="text" name="TransType" value="0001" maxlength="4"> &nbsp;(4λ���֣�Ŀǰ��֧��֧���ɹ����׵Ĳ�ѯ��֧������Ϊ0001)
                </td>
			</tr>
			<tr>
				<td>
					<font color=red>*</font>�汾��
				</td>

				<td>
                     <select size="1" name="Version">
    				<option value="20060831" selected>20060831</option>
    				<option value="20140521" >20140521</option>
    				</select>&nbsp;(8λ���֣���ѯ�ӿڰ汾��)
                </td>
			</tr>
			<tr>
				<td>
					�̻�˽����
				</td>

				<td>
                     <input type="text" name="Resv" maxlength="40"> &nbsp;(Ӣ�Ļ����֣�������40�ֽڣ�ChinaPay��ԭ�����ظ��̻�ϵͳ���ֶ����������)
                </td>
			</tr>
		</table>	

	<hr>
	<input type="hidden" name="MerID" value="<%= merId %>">
	<input type='button' name='v_action' value='�ύ����' onClick='document.querySend.submit()'>
		
</form>

<p><font color="blue">
ע�� 1.Ŀǰ��ѯ���ܽ�֧�ֶ�֧���ɹ������Ĳ�ѯ���������͵Ĳ�ѯ�ݲ�֧�֡�<br>&nbsp;&nbsp;&nbsp;&nbsp;
	2.ʹ�ò�ѯ����ǰ����Ҫ��ǰ���̻����ڲ�ѯ�ķ�����IP����ChinaPay�������ú�ſ�ʹ�á�<br>&nbsp;&nbsp;&nbsp;&nbsp;
	3.��ѯ����ÿ��IP��Ĭ�ϲ�ѯ���Ϊ30s��
</font></p>
<body>

</body>
</html>