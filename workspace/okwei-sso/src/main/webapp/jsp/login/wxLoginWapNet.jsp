<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<body> 
<%-- 从此处跳往本地局域网无效  --%>
<form id="form1" method="post" action="${actionUrl}" >
    <input type="hidden" id="headImg" name="headImg" value="${headimg}"/>
    <input type="hidden" id="nickName" name="nickName" value="${nickname}"/>
    <input type="hidden" id="openId" name="openId" value="${openid}"/>
    <input type="hidden" id="state" name="state" value="${state}"/>  
	<input type="hidden" id="code" name="code" value="${code}"/>
	<input type="hidden" id="accesstoken" name="accesstoken" value="${accesstoken}"/>
    <input type="hidden" id="originalState" name="originalState" value="${originalState}"/>
    <input type="hidden" id="originalOpenid" name="originalOpenid" value="${originalOpenid}"/>
	<%-- 当前页面url，供后续页面在需要时提取使用 --%> 
	
	<input type="hidden" id="url" name="url" value="<%=request.getRequestURI()%>"/> 
</form>
   
<script type="text/javascript">
var openId = "${openid}" ;
if(openId!=null&&openId!=''&&openId!=undefined){
    form1.submit();
}
</script>
</body>
</html>