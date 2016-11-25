<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title><sitemesh:write property='title'/></title>
<sitemesh:write property='head' />
</head>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<jsp:include page="/jsp/common/scriptstyle.jsp" />
</head>

<body class="bg_f3">
	<jsp:include page="/jsp/common/header.jsp"></jsp:include>
	<div class="content mar_au">
		<div class="fl conter_fic">
			<jsp:include page="/jsp/common/left.jsp">
				<jsp:param name="m" value="myadd" />
			</jsp:include>
		</div>
		<sitemesh:write property='body' />
	</div>
	<div class="blank"></div>

	<!-- 底部 -->
	<jsp:include page="/jsp/common/footer.jsp" />
</body>

</html>