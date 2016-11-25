<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="pg" uri="http://www.okwei.com/pagination"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我申请的代理商记录</title>
<script type="text/javascript">
$(function(){ 
	var page = new Pagination({
		formId : "classForm",
		isAjax : false,
		targetId : "navTab",
		submitId : "searchBtn",
		validateFn : false
	});
	page.init();
})
</script>
</head>
<body>
<form action="myApply" id="classForm" name="classForm">
	<div class="fr conter_right"> 
		<!-- 销售中 -->
		<div class="gygl_xxk bor_le bor_ri" id="id_cpjl_0">
			<table class="conter_right_xx_cz_table ">
				<tbody>
					<tr class="ndfxs_1-2_color ndfxs_1-2_border">
						<td class="ndfxs_1-2_xh">平台/品牌商城名称</td>
						<td class="ndfxs_1-2_fxs">代理地区</td>
						<td class="ndfxs_1-2_ss">代理产品数量</td>
						<td class="ndfxs_1-2_qq">申请状态</td>
						<td class="ndfxs_1-2_fxs">申请时间</td>
						<td class="ndfxs_1-2_qq">审核时间</td>
						<td class="ndfxs_1-2_ss">操作</td>
					</tr>
					<c:if test="${fn:length(list.list) < 1 }">
					<tr class="ndfxs_1-2_border">
						<td colspan="7" style="text-align: center;line-height: 200px;">您还没有提交过代理申请，<a href="http://www.okwei.com/list/agent" target="_blank">去申请成为代理</a></td> 
					</tr>
					</c:if>
					<c:forEach var="model" items="${list.list }">
					<tr class="ndfxs_1-2_border">
						<td class="ndfxs_1-2_xh">${model.merchantWeiName }</td>
						<td class="ndfxs_1-2_fxs">${model.areaStr }</td>
						<td class="ndfxs_1-2_ss">${model.demandProductCount }</td>
						<td class="ndfxs_1-2_qq"><span class="ft_lan">
						<c:choose>
							<c:when test="${model.status ==0 }">待审核</c:when>
							<c:when test="${model.status ==1 }">已通过</c:when>
							<c:otherwise>未通过</c:otherwise>
						</c:choose> 
						</span></td>
						<td class="ndfxs_1-2_fxs">${model.applyTime }</td>
						<td class="ndfxs_1-2_qq">${model.verifyTime }</td>
						<td class="ndfxs_1-2_ss"><a href="applyDetails?applyID=${model.agentId}" class="mr_10">查看</a></td>
					</tr> 
					</c:forEach>
				</tbody>
			</table>
		</div>
		
			<!-- 分页 -->
			<div class="pull-right">
				<pg:page pageResult="${list }" />
			</div>
	</div>
	</form>
</body>
</html>