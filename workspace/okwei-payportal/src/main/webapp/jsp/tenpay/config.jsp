<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ page import="com.tenpay.TenPaySetting" %>
<% 

//�տ
String spname =TenPaySetting.spname;// "΢����";  

//�̻���
String partner =TenPaySetting.partner; // "1218322301";

//��Կ
String key =TenPaySetting.key;// "9f7529bb659f6043a3f11b5600f1d1c2";

//������ɺ���ת��URL
String return_url =TenPaySetting.return_url;// "http://pay.okwei.net/tenpay_api_b2c/payReturnUrl.jsp";

//���ղƸ�֪ͨͨ��URL
String notify_url =TenPaySetting.notify_url;// "http://pay.okwei.net/tenpay_api_b2c/payNotifyUrl.jsp";

%>
