<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="pg" uri="http://www.okwei.com/pagination"%>
<%@ page import="java.util.ResourceBundle"%>
<%
    String paydomain = ResourceBundle.getBundle("domain").getString("paydomain"); 
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>代理商管理</title>
<style>
.gygl_xxk_table_cz_td {
	border-right: 1px solid #e7e7e7;
	vertical-align: inherit;
}

.gygl_xxk_table td {
	padding: 20px;
}
. w_115{width:115px;}
. w_115 p{width:115px;height: 16px;display:block;white-space:nowrap; overflow:hidden; text-overflow:ellipsis;}
</style>

<script type="text/javascript" src="/statics/js/jquery.selectui.js"></script>
<script type="text/javascript" src="/statics/js/layer/layer.min.js"></script>
<script type="text/javascript" src="/statics/js/agent/agentIndex.js"></script>
</head>
<body>
	<input id="paydomain" type="hidden" value="<%=paydomain %>" />
	<form action="agentIndex" id="classForm" name="classForm" >
		<input name="status" type="hidden" id="statusVal" value="${status }" />
		<div class="fr conter_right">
			<div class="zhuag_suv bor_si fl bg_white">
				<div class="wdqb_xxk ndfxs_1-2_border">
					<ul class="tab_pc f16">
						<li class="now" name="name_xxk"><a href="#">我的代理商</a><i style="left: 43%;"></i></li>
						<li class="mt_10 f12"><input type="button" value="添加代理商" class="dis_b ml_20 fl btn_sel28_pre bor_cls shou" onclick="wins('添加代理商','win_div_5','520px','630px')"></li>
					</ul>
				</div>
				<div class="oneci_ztai fl">
					<div class="left_font tr fl f12 ft_c9">状态：</div>
					<div class="left_xuanzs fl f12">
						<ul>
							<li class="yes_bgs" name="mzh_4_7_yes" id="dsh" data-id="1">待审核（<b class="left_xuanzs_b">${waitAuditCount }</b>）
							</li>
							<li name="mzh_4_7_yes" id="shtg" data-id="3">审核通过（<b class="left_xuanzs_b">${throughCount }</b>）
							</li>
							<li name="mzh_4_7_yes" id="shbtg" data-id="2">审核不通过（<b class="left_xuanzs_b">${noThroughCount }</b>）
							</li>
							<li name="mzh_4_7_yes" id="yqx" data-id="4">已取消（<b class="left_xuanzs_b">${cancelCount }</b>）
							</li>
						</ul>
					</div>
				</div>
				<div class="blank1"></div>
			</div>
			<c:choose>
				<c:when test="${status == 1}">
					<!-- 待审核 -->
					<div class="gygl_xxk mt_20 bor_le bor_ri" style="border-bottom: none;" id="id_shenhe_0">
						<table class="gygl_xxk_table bor_cls" style="">
							<tbody>
								<tr class="ndfxs_1-2_color ndfxs_1-2_border lh_40 fw_b td_no">
									<td>代理商</td>
									<td>联系电话</td>
									<td>代理内容</td>
									<td>申请时间</td>
									<td>来源</td>
									<td>操作</td>
								</tr>
								<c:forEach var="vo" items="${pages.list }">
									<tr class="ndfxs_1-2_border">
										<td class="gygl_xxk_table_cz_td">
											<div class="gygl_xxk_table_cz_qx ">
												<img src="${vo.weiPicture }">
											</div>
											<div class="fl tl ft_c3">
												<p>店铺名称：${vo.shopName }</p>
												<p>姓名：${vo.linkname }</p>
												<p>微店号：${vo.weiId }</p>
											</div>
										</td>
										<td class="gygl_xxk_table_cz_td"><span class="tc ft_c3 14">${vo.phone }</span></td>
										<td class="gygl_xxk_table_cz_td tc">
											<p class="tc ft_c9">代理招商需求：${vo.demandName }</p>
											<p class="tc ft_c9">代理地区：${vo.areaStr }</p>
										</td>
										<td class="gygl_xxk_table_cz_td"><span class="tc ft_c3 f14">${vo.applyTime }</span></td>
										<td class="gygl_xxk_table_cz_td"><span class="tc ft_c3 f14"><c:if test="${vo.joinType == 0 }">系统申请</c:if> <c:if test="${vo.joinType == 1 }">手动添加</c:if></span></td>
										<td class="gygl_xxk_table_cz_td p10"><a href="agentDetails?applyID=${vo.agentId }" class="ft_lan">审核</a></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</c:when>
				<c:when test="${status == 3 }">
					<!-- 审核通过 -->
					<div class="gygl_xxk mt_20 bor_le bor_ri" id="id_shenhe_1" style="display: block; border-bottom: none;">
						<table class="gygl_xxk_table bor_cls">
							<tbody>
								<tr class="ndfxs_1-2_color ndfxs_1-2_border lh_40 fw_b td_no">
									<td>代理商</td>
									<td>联系电话</td>
									<td>申请内容</td>
									<td>审核时间</td>
									<td>来源</td>
									<td>操作</td>
								</tr>
								<c:forEach var="vo" items="${pages.list }">
									<tr class="ndfxs_1-2_border">
										<td class="gygl_xxk_table_cz_td">
											<div class="gygl_xxk_table_cz_qx ">
												<img src="${vo.weiPicture }">
											</div>
											<div class="fl tl ft_c3 w_115">
												<p>店铺名称：${vo.shopName }</p>
												<p>姓名：${vo.linkname }</p>
												<p>微店号：${vo.weiId }</p>
												<p>落地店数：${vo.totalStore }</p>
											</div>
										</td>
										<td class="gygl_xxk_table_cz_td"><span class="tc ft_c3 14">${vo.phone }</span></td>
										<td class="gygl_xxk_table_cz_td tc">
											<p class="tc ft_c9">代理招商需求：${vo.demandName }</p>
											<p class="tc ft_c9">代理地区：${vo.areaStr }</p>
										</td>
										<td class="gygl_xxk_table_cz_td" style="width: 70px;"><span class="tc ft_c3 f14">${vo.verifyTime }</span></td>
										<td class="gygl_xxk_table_cz_td" style="width: 70px;"><span class="tc ft_c3 f14"> <c:if test="${vo.joinType == 0 }">系统申请</c:if> <c:if test="${vo.joinType == 1 }">手动添加</c:if>
										</span></td>
										<td class="gygl_xxk_table_cz_td p10" style="width: 40px;"><c:if test="${vo.isPayReward == 0 }">
												<p>
													<a href="javascript:void(0)" onclick="getorderno(${vo.agentId})" class="ft_lan">支付悬赏</a>
												</p>
											</c:if> <c:if test="${vo.isPayReward == 1 }">
												<p>
													<span class="ft_c9">已支付</span>
												</p>
												<p>
													<a href="javascript:void(0)" class="ft_lan" onclick="cancelwin(${vo.agentId})">取消代理</a>
												</p>
											</c:if></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</c:when>
				<c:when test="${status == 2 }">
					<!-- 审核不通过 -->
					<div class="gygl_xxk mt_20 bor_le bor_ri" id="id_shenhe_2" style="display: block; border-bottom: none;">
						<table class="gygl_xxk_table bor_cls" style="">
							<tbody>
								<tr class="ndfxs_1-2_color ndfxs_1-2_border lh_40 fw_b td_no">
									<td>代理商</td>
									<td>联系电话</td>
									<td>申请内容</td>
									<td>审核时间</td>
									<td>审核状态</td>
									<td>来源</td>
									<td>操作</td>
								</tr>

								<c:forEach var="vo" items="${pages.list }">
									<tr class="ndfxs_1-2_border">
										<td class="gygl_xxk_table_cz_td">
											<div class="fl mt_30 mr_10">
												<input class="checkboxs" type="checkbox" value="${vo.agentId }" />
											</div>
											<div class="gygl_xxk_table_cz_qx ">
												<img src="${vo.weiPicture }">
											</div>
											<div class="fl tl ft_c3 w_115">
												<p>店铺名称：${vo.shopName }</p>
												<p>姓名：${vo.linkname }</p>
												<p>微店号：${vo.weiId }</p>
											</div>
										</td>
										<td class="gygl_xxk_table_cz_td"><span class="tc ft_c3 14">${vo.phone }</span></td>
										<td class="gygl_xxk_table_cz_td tc">
											<p class="tc ft_c9">代理招商需求：${vo.demandName }</p>
											<p class="tc ft_c9">代理地区：${vo.areaStr }</p>
										</td>
										<td class="gygl_xxk_table_cz_td"><span class="tc ft_c3 f14">${vo.verifyTime }</span></td>
										<td class="gygl_xxk_table_cz_td" style="width: 70px;">
											<p class="ft_c9">不通过</p>
											<p class="ft_c9">（${vo.remarks }）</p>
										</td>
										<td class="gygl_xxk_table_cz_td" style="width: 70px;"><span class="tc ft_c3 f14"><c:if test="${vo.joinType == 0 }">系统申请</c:if> <c:if test="${vo.joinType == 1 }">手动添加</c:if></span></td>
										<td class="gygl_xxk_table_cz_td p10" style="width: 40px;"><a href="javascript:void(0)" onclick="deletes(${vo.agentId})" class="ft_lan">删除</a></td>
									</tr>
								</c:forEach>
								<tr class="ndfxs_1-2_border td_no">
									<td colspan="8">
										<div class="fiex_sel">
											<input type="checkbox" id="selectAll" class="dis_b fl ml_10 mt13"> <label class="dis_b fl ml_5 mt13 ft_c6" for="selectAll" style="margin-top: 13px;">全选</label> 
											<a class="dis_b ml_20 fl mt_5 btn_hui28_pre shou" id="delall">删除</a>
										</div>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
				</c:when>
				<c:when test="${status == 4 }">
					<!-- 已取消 -->
					<div class="gygl_xxk mt_20 bor_le bor_ri" id="id_shenhe_3" style="display: block; border-bottom: none;">
						<table class="gygl_xxk_table bor_cls" style="">
							<tbody>
								<tr class="ndfxs_1-2_color ndfxs_1-2_border lh_40 fw_b td_no">
									<td>代理商</td>
									<td>联系电话</td>
									<td>申请内容</td>
									<td>取消时间</td>
									<td>审核状态</td>
									<td>来源</td>
									<td>操作</td>
								</tr>
								
								<c:forEach var="vo" items="${pages.list }">
								<tr class="ndfxs_1-2_border">
									<td class="gygl_xxk_table_cz_td">
										<div class="fl mt_30 mr_10">
											<input class="checkboxs" type="checkbox" value="${vo.agentId }" />
										</div>
										<div class="gygl_xxk_table_cz_qx ">
											<img src="${vo.weiPicture }">
										</div>
										<div class="fl tl ft_c3">
											<p>店铺名称：${vo.shopName }</p>
											<p>姓名：${vo.linkname }</p>
											<p>微店号：${vo.weiId }</p>
										</div>
									</td>
									<td class="gygl_xxk_table_cz_td"><span class="tc ft_c3 14">${vo.phone }</span></td>
									<td class="gygl_xxk_table_cz_td tc">
										<p class="tc ft_c9">代理招商需求：${vo.demandName }</p>
										<p class="tc ft_c9">代理地区：${vo.areaStr }</p>
									</td>
									<td class="gygl_xxk_table_cz_td"><span class="tc ft_c3 f14">${vo.verifyTime }</span></td>
									<td class="gygl_xxk_table_cz_td">
										<p class="ft_c9">已取消代理</p>
										<p class="ft_c9">（${vo.remarks }）</p>
									</td>
									<td class="gygl_xxk_table_cz_td"><span class="tc ft_c3 f14"><c:if test="${vo.joinType == 0 }">系统申请</c:if> <c:if test="${vo.joinType == 1 }">手动添加</c:if></span></td>
									<td class="gygl_xxk_table_cz_td p10"><a href="javascript:void(0)" onclick="recovery(${vo.agentId})" class="ft_lan">恢复</a> 
									<a href="javascript:void(0)" onclick="deletes(${vo.agentId})" class="ft_lan">删除</a></td>
								</tr>
								</c:forEach>
								<tr class="ndfxs_1-2_border td_no">
									<td colspan="8">
										<div class="fiex_sel">
											<input type="checkbox" id="selectAll" class="dis_b fl ml_10 mt13"> <label class="dis_b fl ml_5 mt13 ft_c6" for="selectAll" style="margin-top: 13px;">全选</label> 
											<a class="dis_b ml_20 fl mt_5 btn_hui28_pre shou" id="delall">删除</a>
										</div>
									</td>
								</tr>
							</tbody>
						</table>
						<!-- 分页 -->
					</div>
				</c:when>
			</c:choose>
			<!-- 分页 -->
			<div class="pull-right">
				<pg:page pageResult="${pages }" />
			</div>
		</div>

	</form>
	<!-- 取消代理原因 -->
	<div class="updata_tixian" style="display: none;" id="win_div_1">
		<textarea class="fl mzh_100 f14" style="height: 100px;" placeholder="请简述您取消代理的原因" id="canceltext"></textarea>
		<div class="fl f16 mt_20">
			<font class="ft_red">！</font>取消代理商后，该用户所在市的所有落地店将自动分配给本账号
		</div>
	</div>

	<!-- 恢复代理商 -->
	<div class="updata_tixian" style="display: none;" id="win_div_2">
		<div class="fl f16 mt_20">确定恢复该用户代理商资格？</div>
	</div>


	<!-- 有三笔选尚未支付则不能进行审核弹窗提示 -->
	<div class="updata_tixian" style="display: none;" id="win_div_4">
		<div class="fl f16">添加失败,您有3笔悬赏金额未支付,请先支付悬赏金额，让更多的认证员为您服务。</div>
	</div>

	<!-- 不能恢复代理商 -->
	<div class="updata_tixian" style="display: none;" id="win_div_3">
		<div class="fl f16 mt_20">该地区已有代理商，无法恢复该代理商资格！</div>
	</div>
	<div class="updata_tixian" style="display: none;" id="win_div_11">
		<div class="fl f16 mt_20">您有3笔悬赏金额未支付，请先支付悬赏金额。让更多的认证员为您服务！</div>
	</div>

	<!-- 添加代理商 -->
	<div class="updata_tixian" style="display: none;" id="win_div_5">
		<div class="mzh_100 f14">
			<span class="fl" style="line-height: 26px;">微店号：</span> <input type="text" id="addWeiid" class="fl xzgys_input" style="width: 160px;" maxlength="11" placeholder="请输入微店号" /> <span id="weiName"> 
			
			</span>
		</div>
		<div class="mzh_100 f14 mt_10">
			<span class="fl" style="line-height: 26px;"> 姓名：</span> <input  id="addName" type="text" class="fl xzgys_input" style="width: 160px;" placeholder="" />
		</div>
		<div class="mzh_100 f14 mt_10">
			<span class="fl" style="line-height: 26px;">联系电话：</span> <input id="addPhone" maxlength="11" type="text" class="fl xzgys_input" style="width: 160px;" placeholder="" />
		</div>
		<div class="mzh_100 f14 mt_10">
			<span class="fl" style="line-height: 26px;">详细地址：</span> <input id="addAddress" type="text" class="fl xzgys_input" placeholder="" />
		</div>
		<div class="blank1 bor_bo"></div>
		<div class="mzh_100 f14 mt_10">
			<span class="fl" style="line-height: 26px;">选择代理招商需求：<span class="ft_c9">（单选）</span></span>
			<div class="mzh_100" id="demands" style="overflow-y: scroll;height: 105px;"></div>
		</div>
		<div class="blank1 bor_bo"></div>
		<div class="mzh_100 f14 mt_10" style="display: none">
			<span class="fl" style="line-height: 26px;">选择代理城市：<span class="ft_c9">（单选）</span></span>
			<div class="mzh_100" id="cityName" style="overflow-y: scroll;height: 105px;"></div>
		</div>
	</div>

</body>
</html>