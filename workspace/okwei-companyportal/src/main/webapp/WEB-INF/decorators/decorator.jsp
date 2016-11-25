<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><sitemesh:write property='title' /></title>
<jsp:include page="/jsp/common/scriptstyle.jsp" />
<sitemesh:write property='head' />
</head>
<body style="background:#ebebeb;">
	<!-- 头部 -->
	<header> <jsp:include page="/jsp/common/header.jsp" /> </header>
	<!-- 中间内容 -->
	<article> <sitemesh:write property='body' /> </article>
	<!-- 底部 -->
	<footer> <jsp:include page="/jsp/common/footer.jsp" /> </footer>
</body>

</html>