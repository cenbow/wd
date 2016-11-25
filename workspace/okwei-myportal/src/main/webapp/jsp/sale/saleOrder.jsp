<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="pg" uri="http://www.okwei.com/pagination"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的分销订单</title>
<script language="JavaScript" type="text/javascript" src="<%=request.getContextPath()%>/statics/js/My97/WdatePicker.js"></script>
</head>
<body>
	<form action="saleOrder" name="saleForm" id="saleForm">
		<!-- 订单信息 -->
		<div class="conter_right_xx">
			<div class="xh-shurk" style="margin-top: 0;">
				<ul>
					<li><span>商品标题：</span> <input type="text" class="btn_h24 w164" name="title" value="${title }" /></li>
					<li><span>供应商：</span> <input type="text" class="btn_h24 w164" name="name" value="${name }" /></li>
					<li><span>订单完成时间：</span> <input type="text" class="btn_h24" name="start" value="${start }" id="beginTimeStr" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" /> 
					<label>—</label> <input type="text" name="end" class="btn_h24" value="${end }" id="endTimeStr" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" /></li>
					<li><input type="submit" value="查询" class="btn_submit_two" style="width: 80px;"></li>
				</ul>
			</div>
		</div>
		<div class="conter_right_xx_cz mt_20" style="border-bottom: 0px;">
			<div class="conter_right_xx_cz_t">
				<ul>
					<li class="l_1 fw_b">商品</li>
					<li class="l_2 fw_b" style="margin-left: 300px;">购买价格</li>
					<li class="l_3 fw_b">购买数量</li>
					<li class="l_4 fw_b" style="margin-left: 36px;">供应商</li>
					<li class="l_6 fw_b" style="margin-left: 162px;">订单完成时间</li>
					<li class="l_7 fw_b">获得佣金</li>
				</ul>
			</div>
		</div>
		<c:if test="${fn:length(saleList.list) < 1 }">
			<div class="conter_right_xx_cz_ddh" style="border-bottom: 0px; margin-bottom: 0px; border: 1px solid #e7e7e7; border-top: none; text-align: center; height: 200px; line-height: 200px;">没有相关数据</div>
		</c:if>
		<c:forEach var="sale" items="${saleList.list }">
			<div class="conter_right_xx_cz mt_10">
				<div class="conter_right_xx_cz_ddh">
					<div class="conter_right_xx_cz_ddh_1">
						订单号：<span>${sale.orderNo }</span>
					</div>
				</div> 
				<table class="conter_right_xx_cz_table">
					<tbody>
						<tr>
							<td class="conter_right_xx_cz_table_55" style="border-bottom: none;"><div class="conter_right_xx_cz_table_55_div" style="border: 0px; padding-top: 0px;">
									<div class="crxczt5d_0">
										<a href="#"><img src="${sale.proImg }"></a>
									</div>
									<div class="crxczt5d_1">
										<span><a href="#${sale.proId }" class="ft_c3">${sale.proTitle }</a></span> <font>${fn:replace(sale.property, "|", "<br />")}</font>
									</div>
									<div class="crxczt5d_2">
										<span> </span> <font>${sale.price }</font>
									</div>
									<div class="crxczt5d_3">${sale.count }</div>
									<div class="crxczt5d_4" style="text-align: left; width: 170px;">${sale.supplierName }</div>
								</div></td>
							<td class="conter_right_xx_cz_table_15" style="border-bottom: none;" rowspan="2"><div class="crxczt5d_sfk">
									<span><fmt:formatDate value="${sale.orderTime }" type="both" dateStyle="default" timeStyle="default" /></span>
								</div></td>
							<td class="conter_right_xx_cz_table_15" rowspan="2" style="border-bottom: none">
								<c:choose>
									<c:when test="${sale.orderType == 8 || sale.orderType == 9 }"> 
											<div class="crxczt5d_cz tb "><fmt:formatNumber type="number"  maxFractionDigits="2" value="${sale.commission * 0.97 * 0.7 }"  ></fmt:formatNumber></div>
									</c:when>
									<c:otherwise>
											<div class="crxczt5d_cz tb "><fmt:formatNumber type="number"  maxFractionDigits="2" value="${sale.commission * 0.7 }"  ></fmt:formatNumber></div>
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</c:forEach>

		<!-- 分页 -->
		<div class="pull-right">
			<pg:page pageResult="${saleList}" />
		</div>
	</form>
	<script type="text/javascript">
		$(function() {
			$("#endTimeStr").blur(function() {
				if ($("#beginTimeStr").val() == "" || $("#endTimeStr").val() == "") {
					return false;
				}
				var begin = new Date($("#beginTimeStr").val());
				var end = new Date($("#endTimeStr").val());
				if (begin > end) {
					$("#endTimeStr").val("");
				}
			})
			$("#beginTimeStr").blur(function() {
				if ($("#beginTimeStr").val() == "" || $("#endTimeStr").val() == "") {
					return false;
				}
				var begin = new Date($("#beginTimeStr").val());
				var end = new Date($("#endTimeStr").val());
				if (begin > end) {
					$("#beginTimeStr").val("");
				}
			})

			/**
			 * 分页
			 */
			var page = new Pagination({
				formId : "saleForm",
				isAjax : false,
				targetId : "navTab",
				submitId : "searchBtn",
				validateFn : false
			});
			page.init(); 
		});
		// 获取URL参数
		function GetQueryString(name) {
			var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
			var r = window.location.search.substr(1).match(reg);
			if (r != null)
				return unescape(r[2]);
			return null;
		}
	</script>
</body>
</html>