<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="pg" uri="http://www.okwei.com/pagination"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>落地店资料</title>
<!-- 前端css库 start-->
<link rel="stylesheet" type="text/css"
	href="/statics/css/glbdy.css?_=${VERSION}" />
<link rel="stylesheet" type="text/css"
	href="/statics/css/index.css?_=${VERSION}" />
<link rel="stylesheet" type="text/css"
	href="/statics/css/jumei_usercenter.min.css?_=${VERSION}" />
<link rel="stylesheet" type="text/css"
	href="/statics/css/m_shouji.css?_=${VERSION}" />
<link rel="stylesheet" type="text/css"
	href="/statics/css/mzh_dd.css?_=${VERSION}" />
<link rel="stylesheet" type="text/css"
	href="/statics/css/mzh_dd_ddxq.css?_=${VERSION}" />
<link rel="stylesheet" type="text/css"
	href="/statics/css/xh_xq.css?_=${VERSION}" />
<link rel="stylesheet" type="text/css"
	href="/statics/css/pagination.css?_=${VERSION}" />
<!-- 前端css库 end-->

<script type="text/javascript"
	src="/statics/js/jquery-1.7.1.min.js?_=${VERSION}"></script>
<script type="text/javascript"
	src="/statics/js/common/spin.min.js?_=${VERSION}"></script>
<script type="text/javascript"
	src="/statics/js/common/extends_fn.js?_=${VERSION}"></script>
<script type="text/javascript"
	src="/statics/js/common/common.js?_=${VERSION}"></script>
<script type="text/javascript"
	src="/statics/js/common/pagination.js?_=${VERSION}"></script>

</head>

<body class="bg_f3">
	<div id="navTab">
		<form id="queryDataForm" name="queryDataForm"	onsubmit="return false;">

			<!-- 弹出框 start-->
			<div id="mzh_clsspzdr" style="border-top: 1px solid #e7e7e7;">
				 <dl class="xzgys f16 mb_20">
	        <dd class="tr">落地店名称：</dd>
	        <dt>
	            ${data.shopName}
	        </dt>
	    </dl>
	    <dl class="xzgys f16 mb_20">
	        <dd class="tr">姓名：</dd>
	        <dt>${data.name }</dt>
	    </dl>
	    <dl class="xzgys f16 mb_20">
	        <dd class="tr">联系电话：</dd>
	        <dt>${data.mobilePhone }</dt>
	    </dl>
	    <dl class="xzgys f16 mb_20">
	        <dd class="tr">地址：</dd>
	        <dt>${data.address }</dt>
	    </dl>
			</div>
		</form>
	</div>
</body>

