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
			&gt; <a href="./createOrder" class="menu">֧������</a>
			&gt; �汾ѡ��
			&gt; ֧������������װ
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
                     <input type="text" name="OrdId" value="<%= OrdId %>" maxlength="16"> &nbsp;(16λ���֣������ŵĵ�5��9λ������̻��ŵĵ�11��15λ��ͬ��������ϵͳ�Զ�����)
                </td>
			</tr>
			<tr>
				<td>
					<font color=red>*</font>�������
				</td>

				<td>
                     <input type="text" name="TransAmt" value="<%= TransAmt %>" maxlength="12"> &nbsp;(12λ���֣�����Ĭ�Ͻ��Ϊ1��)
                </td>
			</tr>
			<tr>
				<td>
					<font color=red>*</font>��������
				</td>

				<td>
                     <input type="text" name="TransDate" value="<%= TransDate %>" maxlength="8"> &nbsp;(8λ���֣�����ϵͳĬ��Ϊ��ǰ����)
                </td>
			</tr>
			<tr>
				<td>
					<font color=red>*</font>��������
				</td>

				<td>
                     <input type="text" name="TransType" value="0001" maxlength="4"> &nbsp;(4λ���֣�֧������Ϊ0001)
                </td>
			</tr>
			<tr>
				<td>
					<font color=red>*</font>���ױ���
				</td>

				<td>
                     <input type="text" name="CuryId" value="156" maxlength="3"> &nbsp;(3λ��Ĭ��Ϊ156 �����)
                </td>
			</tr>
			<tr>
				<td>
					<font color=red>*</font>�汾��
				</td>

				<td>
                     <input type="text" name="Version" value="<%= version %>" maxlength="8"> &nbsp;(8λ���֣�֧���ӿڰ汾��)
                </td>
			</tr>
			<tr>
				<td>
					֧�����غ�
				</td>

				<td>
                     <input type="text" name="GateId" maxlength="4"> &nbsp;(4λ���֣�����Ϊ��)
                </td>
			</tr>
			<tr>
				<td>
					ҳ��Ӧ�����URL
				</td>

				<td>
                     <input type="text" name="PageRetUrl" value="http://localhost:8080/chinapay_java/getPageReturn" maxlength="80"> &nbsp;(������80�ֽڣ��̻�ϵͳǰ̨Ӧ����ܵ�ַ)
                </td>
			</tr>
						<tr>
				<td>
					<font color=red>*</font>��̨Ӧ�����URL
				</td>

				<td>
                     <input type="text" name="BgRetUrl" value="http://131.252.83.73:8080/chinapay_java/getBgReturn" maxlength="80"> &nbsp;(������80�ֽڣ��̻�ϵͳ��̨Ӧ����ܵ�ַ)
                </td>
			</tr>
						<tr>
				<td>
					�̻�˽����
				</td>

				<td>
                     <input type="text" name="Priv1" value="memo" maxlength="60"> &nbsp;(Ӣ�Ļ����֣�������60�ֽڣ�ChinaPay��ԭ�����ظ��̻�ϵͳ���ֶ����������)
                </td>
			</tr>
		</table>	

<%
	}else if (version.equals("20140522")){
%>
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
                     <input type="text" name="OrdId" value="<%= OrdId %>" maxlength="16"> &nbsp;(16λ���֣�������ϵͳ�Զ�����)
                </td>
			</tr>
			<tr>
				<td>
					<font color=red>*</font>�������
				</td>

				<td>
                     <input type="text" name="TransAmt" value="<%= TransAmt %>" maxlength="12"> &nbsp;(12λ���֣�����Ĭ�Ͻ��Ϊ1��)
                </td>
			</tr>
			<tr>
				<td>
					<font color=red>*</font>��������
				</td>

				<td>
                     <input type="text" name="TransDate" value="<%= TransDate %>" maxlength="8"> &nbsp;(8λ���֣�����ϵͳĬ��Ϊ��ǰ����)
                </td>
			</tr>
			<tr>
				<td>
					<font color=red>*</font>��������
				</td>

				<td>
                     <input type="text" name="TransType" value="0001" maxlength="4"> &nbsp;(4λ���֣�֧������Ϊ0001)
                </td>
			</tr>
			<tr>
				<td>
					<font color=red>*</font>���ױ���
				</td>

				<td>
                     <input type="text" name="CuryId" value="156" maxlength="3"> &nbsp;(3λ��Ĭ��Ϊ156 �����)
                </td>
			</tr>
			<tr>
				<td>
					<font color=red>*</font>�汾��
				</td>

				<td>
                     <input type="text" name="Version" value="<%= version %>" maxlength="8"> &nbsp;(8λ���֣�֧���ӿڰ汾��)
                </td>
			</tr>
			<tr>
				<td>
					֧�����غ�
				</td>

				<td>
                     <input type="text" name="GateId" maxlength="4"> &nbsp;(4λ���֣�����Ϊ��)
                </td>
			</tr>
			<tr>
				<td>
					ҳ��Ӧ�����URL
				</td>

				<td>
                     <input type="text" name="PageRetUrl" value="http://localhost:8080/chinapay_java/getPageReturn" maxlength="80"> &nbsp;(������80�ֽڣ��̻�ϵͳǰ̨Ӧ����ܵ�ַ)
                </td>
			</tr>
						<tr>
				<td>
					<font color=red>*</font>��̨Ӧ�����URL
				</td>

				<td>
                     <input type="text" name="BgRetUrl" value="http://131.252.83.73:8080/chinapay_java/getBgReturn" maxlength="80"> &nbsp;(������80�ֽڣ��̻�ϵͳ��̨Ӧ����ܵ�ַ)
                </td>
			</tr>
						<tr>
				<td>
					�̻�˽����
				</td>

				<td>
                     <input type="text" name="Priv1" maxlength="60"> &nbsp;(Ӣ�Ļ����֣�������60�ֽڣ�ChinaPay��ԭ�����ظ��̻�ϵͳ���ֶ����������)
                </td>
			</tr>
		</table>
<%
	}
%>
	<hr>
	<input type="hidden" name="MerId" value="<%= merId %>">
	<input type='button' name='v_action' value='�ύ����' onClick='document.createOrder.submit()'>
		
</form>

<body>

</body>
</html>