<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
String remoteIP;

 if(request.getHeader("X-Forwarded-For") == null){
	 System.out.println("X-Forwarded-For is null");
	 remoteIP = request.getRemoteAddr();
 }else {
	 System.out.println("X-Forwarded-For is not null");
	 remoteIP = request.getHeader("X-Forwarded-For");
 }
%>
<p><font color="blue">
	您的访问IP：<%= remoteIP %>
</font></p>
</body>
</html>