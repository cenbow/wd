<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="pg" uri="http://www.okwei.com/pagination"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>我的下游分销</title>
<style>
.left_xuanzs li.yes_bgs a{color:#fff;}
</style>
<script  type="text/javascript" src="<%=request.getContextPath()%>/statics/js/My97/WdatePicker.js"></script>
</head>

<body class="bg_f3">
	<form id="searcherForm" onsubmit="return false;">
		<div class="w fl bor_si" style="background:#fff">
		<div class="gygl_xxk_t f16 ndfxs_1-2_border">
			<div name="mzh_xxk" style="color: #333;" class="gygl_xxk_yes">
				我的下游分销商<span style="width: 64px;"></span>
			</div>
		</div>
		<div class="oneci_ztai fl">
			<div style="margin-left: 64px;" class="left_xuanzs fl f12">
				<ul>
					<c:if test="${null!=dto.onlyWeiSeller && dto.onlyWeiSeller==1}">
					<li name="mzh_4_7_yes" class="yes_bgs"><a href="<%=basePath%>relationMgt/downstream/distributor">直接分销商</a></li>
					</c:if>
					<c:if test="${dto.onlyWeiSeller==null || dto.onlyWeiSeller==0}">
					<li name="mzh_4_7_yes"
					<c:if test='${dto.type=="all"}'>class="yes_bgs"</c:if>
					><a href="<%=basePath%>relationMgt/downstream/all">全部</a></li>
					
					<li name="mzh_4_7_yes" 
					<c:if test='${dto.type=="distributor"}'>class="yes_bgs"</c:if>
					><a href="<%=basePath%>relationMgt/downstream/distributor">直接分销商</a></li>
					
					<li name="mzh_4_7_yes" 
					<c:if test='${dto.type=="attention"}'>class="yes_bgs"</c:if>
					><a href="<%=basePath%>relationMgt/downstream/attention">普通关注</a></li>
					</c:if>
				</ul>
			</div>
		</div>
		<div class="rztd_cx">
			<div class="rztd_cx_div">微店号：</div>
			<input type="text" class="btn_h24 dis_b fl mr_18" name="weiId" id="weiId" value="${dto.weiId}"/>
			<%-- <div class="rztd_cx_div">店铺：</div> 
			<input type="text" maxlength="12" class="btn_h24 dis_b fl mr_18" name="weiName" value="${dto.weiName}"/> --%>
			<div class="rztd_cx_div">加盟日期：</div> 
			<input type="text" maxlength="11" class="btn_h24 dis_b fl mr_18" name="fromTime" value="<fmt:formatDate value="${dto.fromTime}" pattern="yyyy-MM-dd"/>" readonly="readonly" onclick="WdatePicker()"/>
			<div class="fl ft_c9 ml_5 mr_5">&mdash;</div> 
			<input type="text" maxlength="11" class="btn_h24 dis_b fl mr_18" name="toTime" value="<fmt:formatDate value="${dto.toTime}" pattern="yyyy-MM-dd"/>" readonly="readonly" onclick="WdatePicker()"/>
			<input style="height: 27px; margin-left: 20px; line-height: 27px; width: 70px;" class="jbzl_dl_qrdz" type="submit" value="查询" id="searchBtn"/>
		</div>

		<!-- 订单信息-操作 -->
		<div style="border: 0px;" class="conter_right_xx_cz mt_20">
			<table class="conter_right_xx_cz_table">
				<tbody>
					<tr class="ndfxs_1-2_color">
						<td style="text-indent: 12px;" width="10%"><b>序号</b></td>
						<td width="20%"><b>微店号</b></td>
						<td width="20%"><b>分销商</b></td>
						<td width="20%"><b>QQ</b></td>
						<td width="20%"><b>加盟日期</b></td>
					</tr>
					<c:if test="${fn:length(pageResult.list) < 1 }">
						<tr>
							<td colspan="5" style="vertical-align: middle; text-align: center; height: 200px;">暂无相关数据</td>
						<tr>
					</c:if>
					<c:forEach items="${pageResult.list}" var="supplier" varStatus="status">
						<tr class="ndfxs_1-2_border">
							<td><div style="text-indent: 12px;">${status.index+1}</div></td>
							<td>${supplier.weiId}</td>
							<td><div class="wying">
								<img src="${supplier.images}"><span class="c3" title="${supplier.weiName}">${supplier.weiName}</span>
							</div></td>
							<td>
								<c:if test='${supplier.qq!=null && supplier.qq!=""}'>
								<a target="_blank" href="http://wpa.qq.com/msgrd?v=3&uin=${supplier.qq}&site=qq&menu=yes"><img border="0" src="http://wpa.qq.com/pa?p=2:${supplier.qq}:51" alt="点击这里给我发消息" title="点击这里给我发消息"/></a>
								</c:if>
							</td>
							<td class="f12"><fmt:formatDate value="${supplier.registerTime}" type="both"/></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		</div>
		<div class="pull-right">
			<pg:page pageResult="${pageResult}" />
		</div>
	</form>
	<div style="display: none" id="settext">downstreampage</div>

	<script>
		$(function() {
			// 全部，直接分销商，普通关注 tab切换时，改变url
			$("#searcherForm").attr("action",window.location.pathname);
			var page = new Pagination({
				formId : "searcherForm",
				isAjax : true,
				targetId : "navTab",
				submitId : "searchBtn",
				validateFn : checkInfo
			});
			page.init();
		})

		function checkInfo() {
			var code = $("#weiId").val();
			var re = /^[1-9]+[0-9]*]*$/;
			if (code && !re.test(code)) {
				alert("请输入数字格式的微店号");
				return false;
			}
			return true;
		}
	</script>
	
	
</body>