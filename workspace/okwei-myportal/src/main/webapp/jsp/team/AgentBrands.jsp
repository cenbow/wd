<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="pg" uri="http://www.okwei.com/pagination"%>
<%@ page import="java.util.ResourceBundle"%>
<%@page import="com.okwei.common.JsonUtil"%>
<%
	String mydomain = ResourceBundle.getBundle("domain").getString(
			"mydomain");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>代理管理</title>
<style>
.conter_right tr {
	border-bottom: 1px solid #ddd;
}

.conter_right tr td {
	border-right: 1px solid #ddd;
}

.mzh_czhxr {
	float: left;
	width: 100%;
}

.mzh_czhxr li {
	float: left;
	padding: 5px 15px;
	color: #333;
	border: 1px solid #ddd;
	background: #fff;
	border-bottom: none;
	border-right: none;
	cursor: pointer;
}

.mzh_czhxr li.yes {
	float: left;
	padding: 5px 15px;
	color: #3366cc;
	border: 1px solid #ddd;
	background: #fff;
	border-bottom: none;
	border-right: none;
	cursor: pointer;
	font-weight: bold;
}

.mzh_czhxr li:last-child {
	float: left;
	padding: 5px 15px;
	border: 1px solid #ddd;
	background: #fff;
	border-bottom: none;
}
</style>
<script type="text/javascript" src="/statics/js/common/JsRender.js"></script>
<script>
	$(function() {
		/* Tab切换代码 */
		$(document).ready(function() {
			//On Click Event
			$(".tab2 li").click(function() {
				$(".tab2 li").removeClass("active");
				$(this).addClass("active");
				var brandid = $(this).val();
				$("#brandid").val(brandid);
				$("#btnSubmit").click();
			});
		});
	})
</script>
</head>
<body>
	<form id="searcherForm" name="searcherForm" action="/teamMgt/agentList" onsubmit="return true;">
		<div class="fr conter_right">
			<div class="mzh_100 mt_20">
				<div id="brandlistDiv" class="mzh_czhxr">
					<ul>
						<c:forEach items="${list }" var="mo" varStatus="status">
							<li class="<c:if test="${status.index==0 }">yes</c:if>" data-brandid=${mo.brandId } data-list=${JsonUtil.objectToJsonStr(mo) }>${mo.brandName }</li>
						</c:forEach>
					</ul>
				</div>
				<div id="agentlist-div"></div>
				<script type="text/x-jsrender" id="agentlist-render">
			<table class="conter_right fl bg_white bor_si f14 line_42 tc">
					<tr class="bg_f3">
						<th>店铺名</th>
						<th>联系电话</th>
						<th>QQ</th>
						<th>代理类型</th>
						<th>绑定关系</th>
						<th>申请时间</th>
						<th>缴费金额</th>
					</tr>
				{{for agentList }}
					<tr>
						<td><a href="#">{{:shopName}}</a></td>
						<td>{{:contactPhone}}</td>
						<td>{{:qq}}</td>
						<td>一级</td>
						<td>{{:relation}}</td>
						<td>{{:dateStr}}</td>
						<td>{{:costs}}</td>
					</tr>
				{{/for}}
			</table>
 		</script>

			</div>
		</div>
	</form>
	<input type="hidden" id="brandid" name="brandid" value="${dto.brandid }">


	<script type="text/javascript">
		$(function() {
			var list = $("#listjsonVal").val();
			//alert(list);
			var mo = $("#brandlistDiv ul li:first-child").attr("data-list");
			$("#agentlist-div").html("");
			mo = eval("(" + mo + ")")
			var htm = $("#agentlist-render").render(mo);
			$("#agentlist-div").append(htm);

			$("#brandlistDiv ul li").live("click", function() {
				$("#brandlistDiv ul li").removeClass("yes");
				$(this).addClass("yes");
				$("#agentlist-div").html("");
				var model = $(this).attr("data-list");
				model = eval("(" + model + ")");
				var htmNew = $("#agentlist-render").render(model);
				$("#agentlist-div").append(htmNew);
			})
		})
	</script>
</body>

</html>