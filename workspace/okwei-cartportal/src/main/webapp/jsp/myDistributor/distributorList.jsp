<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="pg" uri="http://www.okwei.com/pagination"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>我的分销商</title>
</head>

<body class="bg_f3">
	<form id="searcherForm" name="searcherForm" action="<%=basePath%>distributor/list" onsubmit="return false;">
		<div class="fr conter_right">
			<!-- 条件框  -->
			<div class="conter_right_xx">
				<span class="ndfxs_1-2 f16">你的分销商：</span><span
					class="ndfxs_1-2_col f24">257</span>
			</div>

			<!-- 列表 -->
			<div class="conter_right_xx_cz mt_20">
				<table class="conter_right_xx_cz_table">
					<tbody>
						<tr class="ndfxs_1-2_color ndfxs_1-2_border">
							<td class="ndfxs_1-2_xh">序号</td>
							<td class="ndfxs_1-2_fxs">分销商</td>
							<td class="ndfxs_1-2_qq">QQ</td>
							<td class="ndfxs_1-2_rq">加盟日期</td>
							<td class="ndfxs_1-2_ze">他的成交佣金总额</td>
							<td class="ndfxs_1-2_yj">他为你带来的佣金</td>
							<td class="ndfxs_1-2_ss">所在省市</td>
						</tr>
						<c:if test="${fn:length(pageResult.list) < 1 }">
							<tr>
								<td colspan="7">没有数据记录</td>
							<tr>
						</c:if>
						<c:forEach var="distributor" items="${pageResult.list}">
							<tr>
								<td class="ndfxs_1-2_xh">1</td>
								<td class="ndfxs_1-2_fxs">
									<img src="/statics/pic/collect_ico.jpg">
									<a class="ndfxs_1-2_fxs_dm" href="javascript:;">${distributor.weiName}</a>
								</td>
								<td class="ndfxs_1-2_qq"><a href="javascript:;">
									<img src="/statics/pic/qqjt_3_19.png">${distributor.qq}</a>
								</td>
								<td class="ndfxs_1-2_rq">
									<span class="ndfxs_1-2_rq_span">${distributor.registerTime}</span>
								</td>
								<td class="ndfxs_1-2_ze">0元</td>
								<td class="ndfxs_1-2_yj">0元</td>
								<td class="ndfxs_1-2_ss">浙江温州</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<!-- 分页 -->
				<div class="pull-right">
					<pg:page pageResult="${pageResult}" />
				</div>
			</div>
		</div>
	</form>
	
	<script type="text/javascript">
		
	</script>
	
</body>