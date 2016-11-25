<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.ResourceBundle"%>
<%
	String mydomain = ResourceBundle.getBundle("domain").getString("mydomain");
	String okweidomain = ResourceBundle.getBundle("domain").getString("okweidomain");
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>铺货单</title>
</head>    
<script type="text/javascript">
	
</script>
<body class="bg_f3">
<div class="tab_qie">
<!-- 提交成功 -->
 <c:choose>
 	<c:when test="${rmolde.statu == '1'}">
 	<div class="fl quan_xuz">
    <div class="shopp_count fl">
       <div class="shop_cgs">
       	<div class="submit_cgs fl"><img src="<%=path%>/statics/images/d-ico1.png" /><span class="ml_10">您的铺货单已经提交成功，请等待供应商回复</span></div>
        <div class="submit_cgsto fl mt_20">
        	<a href="<%=mydomain %>/order/reservelist?dt=3&ds=-1" class="btn_yes42_pre dis_b fl mr_20" style="margin-left:53px;">查看订单详情</a>
        	<a href="http://www.<%=okweidomain %>/list/list" class="btn_yes42_pre dis_b fl">返回购物</a>
        </div> 
       </div>
    </div>
  </div>
 	</c:when>
 	<c:otherwise>
 	<!-- 失败 -->
	  <div class="fl quan_xuz">
	    <div class="shopp_count fl">
	       <div class="shop_cgs">
	       	<div class="submit_cgs fl"><img src="<%=path%>/statics/images/c-ico1.png" /><span class="ml_10">您的铺货订单提交失败(错误代码：${rmolde.statusReson})。</span></div>
	        <div class="submit_cgsto fl mt_20">
	        	<a href="http://www.<%=okweidomain %>/list/list" class="btn_yes42_pre dis_b fl" style="margin-left:53px;">返回购物</a>
	        </div> 
	       </div>
	    </div>
	  </div>
 	</c:otherwise>
 </c:choose>
  
</div>
</body>
</html>
