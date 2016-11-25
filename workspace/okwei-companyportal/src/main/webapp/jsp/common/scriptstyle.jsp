<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.ResourceBundle"%>

<%
    String path = request.getContextPath();
			String basePath = request.getScheme() + "://"
					+ request.getServerName() + ":" + request.getServerPort()
					+ path + "/";

			Date curDate = new Date();
			pageContext.setAttribute("VERSION", curDate.getTime(),
					PageContext.APPLICATION_SCOPE);
			pageContext.setAttribute("JSESSIONID", session.getId(),
					PageContext.SESSION_SCOPE);
%>
<script>
	window.basePath = "<%=basePath%>";
</script>
<title>首页</title>
<!-- 前端css库 start-->
<link rel="stylesheet" type="text/css" href="http://base1.okwei.com/css/web/pc-base.css" />
<link rel="stylesheet" type="text/css" href="http://base2.okwei.com/css/web/home-page.css" />
<link rel="stylesheet" type="text/css" href="http://base3.okwei.com/css/web/header.css" />
<link rel="stylesheet" type="text/css" href="http://base1.okwei.com/css/web/footer.css" />
<link rel="shortcut icon" href="http://base.okimgs.com/images/favicon.ico">

<link rel="stylesheet" type="text/css" href="http://base1.okwei.com/css/company/pagination.css" />
<!-- 前端css库 end-->

<!-- 前端js库 start-->
<script type="text/javascript" src="http://base2.okwei.com/js/company/jquery-1.7.1.min.js"></script>
<link rel="stylesheet" type="text/css" href="http://base2.okwei.com/js/company/lib/jquery-ui-1.11.4.custom/jquery-ui.css" />

<script type="text/javascript" src="http://base1.okwei.com/js/company/common/spin.min.js"></script>
<script type="text/javascript" src="http://base2.okwei.com/js/company/common/extends_fn.js"></script>
<script type="text/javascript" src="http://base3.okwei.com/js/company/common/common.js"></script>
<script type="text/javascript" src="http://base2.okwei.com/js/company/common/pagination.js"></script>
<script type="text/javascript" src="http://base1.okwei.com/js/company/header.js"></script>
<script type="text/javascript" src="http://base2.okwei.com/js/company/jquery.lazyload.js"></script>
<script type="text/javascript" src="http://base3.okwei.com/js/company/layer/layer.min.js"></script>
<!-- 前端js库 end-->