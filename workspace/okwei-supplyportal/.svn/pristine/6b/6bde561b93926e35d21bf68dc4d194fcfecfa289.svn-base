<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title><sitemesh:write property='title' /></title>
<sitemesh:write property='head' />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<jsp:include page="/jsp/common/scriptstyle.jsp" />
</head>
<body class="bg_f3">
	<!-- START OF HEADER PANEL -->
	<jsp:include page="/jsp/common/header.jsp" />
	<div class="blank" />

	<!-- START OF CONTENT PANEL -->
	<div class="content mar_au">
		<!--leftpanel-->
		<div class="leftpanel fl conter_fic">
			<ul>
				<jsp:include page="/jsp/common/left.jsp">
					<jsp:param name="m" value="myadd" />
				</jsp:include>
			</ul>
		</div>
		<!--leftpanel-->

		<!-- rightpanel -->
		<div class="rightpanel" id="navTab">
			<sitemesh:write property='body' />
		</div>
		<!-- rightpanel -->
	</div>
	<div class="blank" />
	<!-- 底部 -->
	<jsp:include page="/jsp/common/footer.jsp" />
</body>

</html>