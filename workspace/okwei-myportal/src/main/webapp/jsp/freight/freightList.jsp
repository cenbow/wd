<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="pg" uri="http://www.okwei.com/pagination"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>运费模板</title>
</head>
<body>
	<style>
		.ft_lvse{ color:#5c8c18;}
	</style>
	<form action="freightList" id="classForm" name="classForm">
		<div class="outermost">
			<a href="addorupd"><div class="btn_hui28_pre shou fl w80" style="text-indent: 0;">添加</div></a>
		</div>
		<div class="clear"></div>
		<c:if test="${pageId == 1 }">
			<div class="outermost bg_white ft_lvse mt13 ietabes">
				<table>
					<tbody>
						<tr>
							<td colspan="7">
								<div class="outermost">
									<span class="fl f14 tb ">全国10元</span>
								</div>
							</td>
						</tr>
						<tr>
							<td>运送方式</td>
							<td width="180">运送到</td>
							<td>首件</td>
							<td>运费（元）</td>
							<td>续件</td>
							<td>运费（元）</td>
							<td>模板类型</td>
						</tr>
						<tr>
							<td>快递</td>
							<td>全国</td>
							<td>1</td>
							<td>10</td>
							<td>1</td>
							<td>10</td>
							<td>系统</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="outermost bg_white ft_lvse mt13 ietabes">
				<table>
					<tbody>
						<tr>
							<td colspan="7">
								<div class="outermost">
									<span class="fl f14 tb">全国包邮</span>
								</div>
							</td>
						</tr>
						<tr>
							<td>运送方式</td>
							<td width="180">运送到</td>
							<td>首件</td>
							<td>运费（元）</td>
							<td>续件</td>
							<td>运费（元）</td>
							<td>模板类型</td>
						</tr>
						<tr>
							<td>快递</td>
							<td>全国</td>
							<td>1</td>
							<td>0</td>
							<td>1</td>
							<td>0</td>
							<td>系统</td>
						</tr>
					</tbody>
				</table>
			</div>
		</c:if>
		<c:forEach var="fvo" items="${fvopr.list }">
			<div class="outermost bg_white mt13 ietabes">
				<table>
					<tbody>
						<tr>
							<td colspan="7" class=" bg_f3">
								<div class="outermost">
									<span class="fl f14 tb">${fvo.postAgeName }</span> <span class="fr"> <a href="addorupd?fid=${fvo.freightId }">修改</a>&nbsp;&nbsp; <a href="javascript:void(0)" class="delfd" data="${fvo.freightId }">删除</a>
									</span>
								</div>
							</td>
						</tr>
						<tr>
							<td>运送方式</td>
							<td width="180">运送到</td>
							<td>首件</td>
							<td>运费（元）</td>
							<td>续件</td>
							<td>运费（元）</td>
							<td>模板类型</td>
						</tr>
						<c:forEach var="fd" items="${fvo.detailsList }">
							<tr>
								<td><c:choose>
										<c:when test="${fd.courierType == 4 }">物流</c:when>
										<c:otherwise>快递</c:otherwise>
									</c:choose></td>
								<td>${fd.areaName }</td>
								<td>${fd.firstCount }</td>
								<td>${fd.firstpiece }</td>
								<td>${fd.moreCount }</td>
								<td>${fd.morepiece }</td>
								<td>自定义</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</c:forEach>

		<!-- 分页 -->
		<div class="pull-right">
			<pg:page pageResult="${fvopr}" />
		</div>
		<div class="blank"></div>

		<div class="updata_tixian none" id="win_div_2" style="display: none;">
			<div class="f18 fl tc outermost" style="width: 422px;">确认删除该运费模版？</div>
		</div>
	</form>
	<script type="text/javascript">
		/**
		 * 公用弹窗
		 */
		function wins(title, win_id, width, height, fun, param) { // title 标题 win_id 弹窗ID width 弹窗宽度 height 弹窗高度
			var pagei = $.layer({
				type : 1, // 0-4的选择,0：信息框（默认），1：页面层，2：iframe层，3：加载层，4：tips层。
				btns : 2,
				btn : [
						'确定', '取消'
				],
				title : title,
				border : [
					0
				],
				closeBtn : [
					0
				],
				closeBtn : [
						0, true
				],
				shadeClose : true,
				area : [
						width, height
				],
				yes : function() {
					if (param == null) {
						fun();
					}
					else {
						fun(param);
					}
				},
				page : {
					dom : '#' + win_id
				},
				end : function() {
					$("#AddCount").hide()
				}
			});
		}
		$(function() {
			var page = new Pagination({
				formId : "classForm",
				isAjax : false,
				targetId : "navTab",
				submitId : "searchBtn",
				validateFn : false
			});
			page.init();
			$(".delfd").click(function() {
				var data = $(this).attr("data");
				wins('删除运费模板', 'win_div_2', '514px', '240px', delfd, data);// 显示弹窗
			})
		});
		var delfd = function(fid) {
			$.ajax({
				url : "delFreight",
				dataType : "json",
				type : "post",
				data : {
					"fid" : fid
				},
				success : function(data) {
					if (data.state != "1") {
						alert(data.msg);
					}
					setTimeout(function() {
						location.reload();
					}, 1000);
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert("系统异常");
					setTimeout(function() {
						location.reload();
					}, 1000);
				}
			})
		}
	</script>
</body>
</html>