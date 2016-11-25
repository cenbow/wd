<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="pg" uri="http://www.okwei.com/pagination"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>店铺分类</title>
<script type="text/javascript" src="/statics/js/lib/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="/statics/js/layer/layer.min.js"></script>
</head>
<body>
	<form action="classList" id="classForm" name="classForm">
		<!-- 订单信息-操作 -->
		<div class="conter_right_xx_cz">
			<table class="conter_right_xx_cz_table">
				<tbody>
					<tr class="ndfxs_1-2_color ndfxs_1-2_border">
						<td colspan="4" class="ygmdcp_14">
							<c:choose>
								<c:when test="${newId==1 }">
									<div id="btnSaveShopClass" class="btn_hui28_pre shou ml_20 fl">新建分类</div>
								</c:when>
								<c:otherwise>
									<div id="addclass" class="btn_hui28_pre shou ml_20 fl">新建分类</div>
								</c:otherwise>
							</c:choose>
						</td>
					</tr>
					<tr class="ndfxs_1-2_color ndfxs_1-2_border">
						<td class="ygmdcp_14_20">分类名称</td>
						<td class="ygmdcp_14">分类产品</td>
						<td class="ygmdcp_14">分类级别</td>
						<td class="ygmdcp_14">操作</td>
					</tr>
					<c:if test="${fn:length(result.list) < 1 }">
						<tr>
							<td rowspan="3">没有数据记录</td>
						</tr>
					</c:if>
					<c:forEach var="shopclass" items="${result.list }">
						<tr class="ndfxs_1-2_border">
							<td class="ygmdcp_14_20 lh_40"><a href="/myProduct/list/Showing/${shopclass.sid }?isClick=1"  target="_blank">${shopclass.sname }</a></td>
							<td class="ygmdcp_14 lh_40">${shopclass.count }</td>
							<td class="ygmdcp_14 lh_40">
							<c:choose>
								<c:when test="${shopclass.level == 2}">二级</c:when>
								<c:otherwise>一级</c:otherwise>
							</c:choose>
							</td>
							<td class="ygmdcp_14 lh_40"><a href="javascript:void(0)" class="setTop" data="${shopclass.sid }">置顶</a> <a href="javascript:void(0)" class="update" data="${shopclass.sid }">编辑</a> <a href="javascript:void(0)" class="delete" data="${shopclass.sid }" name="mzh_remove">删除</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table> 
		</div>
			<!-- 分页 -->
			<div class="pull-right">
				<pg:page pageResult="${result}" />
			</div>
		<div class="blank"></div>

		<!-- 弹出层 -->
		<div class="updata_tixian none" id="win_div_1" style="display: none;">
			<div class="f18 fl tc outermost mt_30" style="width: 422px; ">该分类下还有产品，无法删除</div>
		</div>

		<div class="updata_tixian none" id="win_div_2" style="display: none;">
			<div class="f18 fl tc outermost" style="width: 422px; ">确定删除该分类</div>
		</div>

		<div class="updata_tixian none" id="win_div_3" style="display: none;">
			<div class="update_tixian fl outermost ml_30 mt_20">
				<div class="moile_ones fl tr f16 lin34">分类名称：</div>
				<div class="moile_two fl">
					<input type="text" class="btn_h30 w222" id="className" value="" maxlength="6"/>
				</div>
			</div>
		</div> 
	</form>
	<input type="hidden" id="newId" value="${newId}"/>
	<script type="text/javascript" src="/statics/js/shopClass/classList.js"></script>
	<div class="updata_tixian" style="display: none;" id="win_div_4">
		<dl class="xzgys f16 mt_20">
			<dd class="tr">一级分类名称：</dd>
			<dt>
				<input type="text" class="xzgys_input" id="oneShopClass">
			</dt>
		</dl>
		<div class="blank1 bor_bo"></div>
		<!-- 添加二级分类 -->
		<dl class="xzgys f16 mt_20">
			<dd class="tr">
				<a href="javascript:addChildrenShopClass()" class="btn_wear42_pre dis_b fl ml_20" style="width: 100px;height: 32px;line-height: 35px;" id="btnSaveShopClass2">添加二级分类</a>
			</dd>
			<dt id="add_tjelfj" style="height: 145px;overflow-y: scroll;">
				<div class="fl mzh_width_100 mb_10">
					<span class="fl mr_10">二级分类名称：</span> 
					<input type="text" class="xzgys_input" style="width: 150px;" name="childrenShopClassName"><font class="fl ft_red fw_b shou ml_10 f22" onclick="javascript:delChildrenShopClass(this)">×</font>
				</div>
			</dt>
		</dl>
	</div>
</body>
</html>