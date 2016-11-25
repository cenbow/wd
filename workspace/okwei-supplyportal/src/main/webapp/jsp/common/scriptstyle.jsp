<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.ResourceBundle"%>

<% 
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";

	Date curDate = new Date();
	pageContext.setAttribute("VERSION", curDate.getTime(), PageContext.APPLICATION_SCOPE);
	pageContext.setAttribute("JSESSIONID", session.getId(), PageContext.SESSION_SCOPE);
%>
<script>
	window.basePath = "<%=basePath %>";
</script>
<title>个人后台首页</title>
<!-- 前端css库 start-->
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath() %>/statics/css/glbdy.css?_=${VERSION}" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath() %>/statics/css/index.css?_=${VERSION}" />
<%-- <link rel="stylesheet" type="text/css" href="<%=cssdomain %>/statics/css/jumei_usercenter.min.css?_=${VERSION}" />
<link rel="stylesheet" type="text/css" href="<%=cssdomain %>/statics/css/m_shouji.css?_=${VERSION}" /> --%>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath() %>/statics/css/m_shouji.css?_=${VERSION}" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath() %>/statics/css/mzh_dd.css?_=${VERSION}" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath() %>/statics/css/mzh_dd_ddxq.css?_=${VERSION}" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/statics/css/xh_xq.css?_=${VERSION}" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath() %>/statics/css/pagination.css?_=${VERSION}" />
<!-- 前端css库 end-->

<script type="text/javascript"
	src="<%=request.getContextPath() %>/statics/js/jquery-1.7.1.min.js?_=${VERSION}"></script>
<%-- <script type="text/javascript" src="/statics/js/lib/jquery-1.11.2.js?_=${VERSION}"></script>
<script type="text/javascript" src="/statics/js/lib/jquery-ui-1.11.4.custom/jquery-ui.js?_=${VERSION}"></script> --%>
<link rel="stylesheet" type="text/css"
	href="/statics/js/lib/jquery-ui-1.11.4.custom/jquery-ui.css?_=${VERSION}" />

<script type="text/javascript"
	src="<%=request.getContextPath() %>/statics/js/common/spin.min.js?_=${VERSION}"></script>
<script type="text/javascript"
	src="<%=request.getContextPath() %>/statics/js/common/extends_fn.js?_=${VERSION}"></script>
<script type="text/javascript"
	src="<%=request.getContextPath() %>/statics/js/common/common.js?_=${VERSION}"></script>
<script type="text/javascript"
	src="<%=request.getContextPath() %>/statics/js/common/pagination.js?_=${VERSION}"></script>

<!-- 前端js库 start-->
<%-- <script type="text/javascript" src="/statics/js/header.js?_=${VERSION}"></script> --%>
<%-- <script type="text/javascript" src="/statics/js/jquery.fs.stepper.js?_=${VERSION}"></script> --%>
<%-- <script type="text/javascript" src="/statics/js/jquery.selectui.js?_=${VERSION}"></script> --%>
<%-- <script type="text/javascript" src="/statics/js/mzh_dd.js?_=${VERSION}"></script> --%>
<script type="text/javascript"
	src="<%=request.getContextPath() %>/statics/js/layer/layer.min.js?_=${VERSION}"></script>

<!-- 前端js库 end-->