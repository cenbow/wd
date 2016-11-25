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
<title>我的分销商</title>
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
		<form id="searcherForm" name="searcherForm"
			action="<%=basePath%>publishProduct/popup/getProducts"
			onsubmit="return false;">

			<!-- 弹出框 start-->
			<div id="mzh_clsspzdr" style="border-top: 1px solid #e7e7e7;">
				<div class="drsp_tcc_2 margin_20">
					<div class="rztd_cx" style="padding: 0px;">
						<div class="rztd_cx_div">商品名称：</div>
						<input type="text" class="rztd_cx_text drsp_hei_30"
							name="productName" value="${queryParam.productName}">
						<div class="rztd_cx_div">商品编码：</div>
						<input type="text" class="rztd_cx_text" id="productCode"
							name="productCode" value="${queryParam.productCode}">
						<div class="rztd_cx_div">店铺分类：</div>

						<div class="mzh_xlk" style="margin: 0px; width: 160px;"
							name="mzh_xlk">
							<select name="shopClassId" style="margin: 0px; width: 160px; height:26px;">
								<option class="mzh_xlk_text" value="">请选择</option>
								<c:forEach var="shop" items="${shopList}">
									<option value="${shop.sid}"
										<c:if test="${shop.sid == queryParam.shopClassId}">selected="selected"</c:if>>${shop.sname}</option>
								</c:forEach>
							</select>
						</div>
						<input type="submit" name="button" id="searchBtn" value="搜索"
							class="jbzl_dl_qrdz ml_10"
							style="height: 27px; line-height: 27px; width: 70px;" />
					</div>
				</div>
				<div class="conter_right_xx_cz margin_20"
					style="border: 0px; width: 694px;" id="mzh_gysgl">
					<table class="conter_right_xx_cz_table" style="width: 694px;"
						id="listDiv">
						<tbody>
							<tr class="ndfxs_1-2_color ndfxs_1-2_border"
								style="background: #eee;">
								<td class="ndfxs_1-2_xh"><b>商品编码</b></td>
								<td class="ndfxs_1-2_fxs"><b>图片</b></td>
								<td><b>商品名称</b></td>
								<td><b>店铺分类</b></td>
								<td><b>选择</b></td>
							</tr>

							<c:if test="${fn:length(page.list) < 1 }">
								<tr>
									<td colspan="5">没有数据记录</td>
									<tr>
							</c:if>
							<c:forEach var="product" items="${page.list}">
								<tr class="ndfxs_1-2_border">
					                <td class="ndfxs_1-2_xh">${product.productId}</td>
					                <td class="ndfxs_1-2_fxs"><img src="${product.defaultImg}"></td>
					                <td>${product.productTitle}</td>
					                <td>${product.sName}</td>
					                <td>
					                <a name="operation2" href="javascript:;" ahref="<%=basePath%>Product/editProductInfo?operation=2&&productId=${product.productId}"><span class="gygl_xxk_table_cz_bt_qxtj_1">选择</span></a>
					                </td>
					            </tr>
							</c:forEach>
						</tbody>
					</table>

					<!-- 分页 -->
					<!-- <div class="ndfxs_1-2_fenye mb_10" style="width: 694px;">
		            <a href="javascript:;" class="ndfxs_1-2_fenye_qd mt_7">确定</a>
		            <span class="ndfxs_1-2_fenye_span">页</span>
		            <div class="stepper "><input type="number" min="1" max="999" step="1" class="ndfxs_1-2_fenye_text mt_7 stepper-input"><span class="stepper-arrow up"></span><span class="stepper-arrow down"></span></div>
		            <span class="ndfxs_1-2_fenye_span">共<font>21</font>页，到第</span>
		            <a href="javascript:;" class="ndfxs_1-2_fenye_no"><img src="pic/jiantou_3_19.jpg"></a>
		            <span class="ndfxs_1-2_fenye_span"><img src="pic/slh_1_19.jpg"></span>
		            <a href="javascript:;" class="ndfxs_1-2_fenye_no" name="mzh_fenye">5</a>
		            <a href="javascript:;" class="ndfxs_1-2_fenye_no" name="mzh_fenye">4</a>
		            <a href="javascript:;" class="ndfxs_1-2_fenye_no" name="mzh_fenye">3</a>
		            <a href="javascript:;" class="ndfxs_1-2_fenye_no" name="mzh_fenye">2</a>
		            <a href="javascript:;" class="ndfxs_1-2_fenye_yes" name="mzh_fenye">1</a>
		            <a href="javascript:;" class="ndfxs_1-2_fenye_no mr_5"><img src="pic/jiantou_l_3_19.jpg"></a>
		        </div> -->

					<div class="pull-right">
						<pg:page pageResult="${page}" />
					</div>

				</div>
			</div>
		</form>
	</div>
</body>

<script type="text/javascript">
	$(document).ready(function(){
	   	var page = new Pagination( {
			formId: "searcherForm",
			isAjax : true,
			targetId : "navTab",
			submitId:"searchBtn",
			validateFn:checkInfo
		});
		page.init();
		
		
		$("#listDiv").on("click","[name=operation2]",function(){
			var $t = $(this);
			var url = $t.attr("ahref");
			window.parent.document.location.href=url;
		});
	});

	function checkInfo(){
		var code = $("#productCode").val();
		var re = /^[1-9]+[0-9]*]*$/;
		if (code && !re.test(code)) {
			alert("请输入整型数字编号");
			return false;
		}
		if(code.length > 11){
			alert("编号长度不能大于10");
			return false;
		}
		return true;
	}
	
</script>