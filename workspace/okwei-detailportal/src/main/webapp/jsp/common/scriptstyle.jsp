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
<title>首页</title>
<!-- 前端css库 start-->
<link rel="stylesheet" type="text/css" href="http://base1.okwei.com/css/web/pc-base.css" />
<link rel="stylesheet" type="text/css" href="http://base3.okwei.com/css/web/header.css" />
<link rel="stylesheet" type="text/css" href="http://base1.okwei.com/css/web/footer.css" />
<link rel="shortcut icon" href="http://base.okimgs.com/images/favicon.ico">
<!-- 前端css库 end-->

<!-- 前端js库 start-->
<script type="text/javascript" src="http://base2.okwei.com/js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="http://base1.okwei.com/js/layer/layer.min.js"></script>
<script type="text/javascript" src="http://base3.okwei.com/js/detail/header.js"></script>
<script type="text/javascript">
function alert(msg,bool){
	if(bool){
		layer.msg(msg, 1, 1);//绿色的钩钩
	}else{
		layer.msg(msg, 1, 8);//不高兴的脸
	}	
}
</script>
<!-- 前端js库 end-->