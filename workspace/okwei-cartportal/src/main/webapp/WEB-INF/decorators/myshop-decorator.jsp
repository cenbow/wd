<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title><sitemesh:write property='title'/></title>
	<sitemesh:write property='head' />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<jsp:include page="/jsp/common/scriptstyle.jsp" />
</head>

<body class="bg_f3">
	<jsp:include page="/jsp/common/header.jsp"/>
	<sitemesh:write property='body' />
	<!-- 底部 -->
	<jsp:include page="/jsp/common/footer.jsp" />
</body>

</html>