<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>退款发货</title>
<script type="text/javascript">
	$(function() {
		$("#danhao").keyup(function() {
			var tmptxt = $(this).val();
			$(this).val(tmptxt.replace(/[^\d|-]/g, ''));
		})
	})
	function tijiao() {
		var kuaidi = $("#kuaidi").val();
		var danhao = $("#danhao").val();
		var refundID = $("#refundID").val();
		if (danhao == null || danhao == "") {
			alert("订单号不能为空");
			return;
		}
		$.ajax({
			url : "orderajax",
			type : "post",
			dataType : "json", 
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			data : {
				"key" : "refunddelivery",
				"kuaidi" : kuaidi,
				"danhao" : danhao,
				"refundId" : refundID
			},
			error : function() {
				alert("异常！");
			},
			success : function(data) {
				if (data.msg == "1") {
					alert("提交成功");
					window.location.href = "buylist";
				} else {
					alert("提交失败");
					location.replace(document.referrer);
				}
			}
		});
	}
</script>
</head>
<body>
	<div class="weiz_iam f12 fm_song">
		当前位置： <a href="/maininfo/maininfo">微店中心</a>&gt;<a href="/order/buylist">我的购买订单 </a>&gt;<span>退货发货</span>
	</div>
	<div class="ddxq_gg">
		<c:if test="${details.refundState == 1||details.refundState == 4 }">
			<div class="ddxq_gg_ddzt" style="padding: 0px;">
				<div class="ddxq_gg_ddzt_left">
					<div class="blank2"></div>
					<dl class="ddxq_gg_ddzt_left_dl">
						<dd class="ddxq_gg_ddzt_left_dd">快递公司：</dd>
						<dt class="ddxq_gg_ddzt_left_dt">
							<select id="kuaidi" class="ddxq_gg_ddzt_left_dt_select">
								<c:if test="${!empty wuliu }">
									<c:forEach var="map" items="${wuliu }">
										<option value="${map['typeName'] }">${map['typeName'] }</option>
									</c:forEach>
								</c:if>
							</select>
						</dt>
					</dl>
					<dl class="ddxq_gg_ddzt_left_dl">
						<dd class="ddxq_gg_ddzt_left_dd">运单号码：</dd>
						<dt class="ddxq_gg_ddzt_left_dt">
							<input type="text" class="ddxq_gg_ddzt_left_dt_text" value="" id="danhao" /> <input type="hidden" id="refundID" value="${details.backOrder }" />
						</dt>
					</dl>
					<dl class="ddxq_gg_ddzt_left_dl" style="margin: none;">
						<dd class="ddxq_gg_ddzt_left_dd" style="height: 10px;"></dd>
						<dt class="ddxq_gg_ddzt_left_dt">
							<a href="javascript:void(0);" onclick="tijiao()" class="jbzl_dl_qrdz f12 ml_10 mr_5" style="width: 100px; margin: 20px 0 0 0px;">提交</a>
						</dt>
					</dl>
				</div>
				<div class="ddxq_gg_ddzt_cen">
					<img src="../statics/images/mzh_4_19_1.jpg">
				</div>
				<div class="ddxq_gg_ddzt_right">
					<div class="ddxq_gg_ddzt_xx">供应商同意您的退款，收货信息信息如下：</div>
					<div class="ddxq_gg_ddzt_xx_1 f14 ml_30 mt_20">收货人：${details.consignee }</div>
					<div class="ddxq_gg_ddzt_xx_1 f14 ml_30 mt_10">联系电话：${details.phone }</div>
					<div class="ddxq_gg_ddzt_xx_1 f14 ml_30 mt_10">收货地址：${details.retreatAddress }</div>
				</div>
			</div>
		</c:if>
		<!-- 收货地址-留言-订单信息-产品清单 -->
		<div class="ddxq_gg_sldc">
			<div class="ddxq_gg_sldc_1 margin_20">
				<!-- 收货地址 -->
				<div class="ddxq_gg_sldc_1_sh">
					<h6 class="lh_40 bor_to mt_10">需退货的产品</h6>
					<div class="conter_right_xx_cz mt bor_cls">
						<div class="conter_right_xx_cz_t" style="background: #e7e7e7;">
							<ul>
								<li class="l_8 fw_b" style="width: 260px;">商品信息</li>
								<li class="l_7 fw_b">单价</li>
								<li class="l_6 fw_b">佣金</li>
								<li class="l_13 fw_b">数量</li>
								<li class="l_6 fw_b">总金额</li>
								<li class="l_13 fw_b" style="margin-left: 150px;">退货金额</li>
							</ul>
						</div>
						<div class="blank2"></div>
						<table class="conter_right_xx_cz_table" style="border: 1px solid #e7e7e7;">
							<tbody>
								<tr class="mzh_4_17_gai">
									<td rowspan="7" style="padding: 0px; border: 0px;">
										<div class="h40_title f12 fl ft_c9" style="margin-left: 10px; width: auto;">
											店铺： <span class="ft_c3">${details.supplyerName }</span>
										</div>
										<div style="float: left; padding-top: 8px; padding-left: 10px;">
											<a target=blank href="tencent://message/?uin=${details.supplyerQQ}&Menu=yes"> <img border="0" SRC=http://wpa.qq.com/pa?p=1:88888:1 alt="点击联系"></a>
										</div>
									</td>
								</tr>
								<tr class="fl">
									<td class="conter_right_xx_cz_table_55" style="border-bottom: 0px; width: auto;">
										<!-- 退款产品 --> <c:forEach var="pro" items="${details.proList }" varStatus="status">
											<div class="conter_right_xx_cz_table_55_div" style="${status.index==0?'border: 0px;padding-top:0px;':''}">
												<div class="crxczt5d_0">
													<a href="http://www.${userinfo.weiID }.okwei.com/cpxq.html?pNo=${pro.productId }"> <img src="${pro.productImg }"></a>
												</div>
												<div class="crxczt5d_10" style="width: 220px;">
													<span style="width: 220px;">${pro.productTitle }</span> <span style="width: 220px; color: #3c3c3c;">${pro.property }</span>
												</div>
												<div class="crxczt5d_4" style="margin-left: 0px;"></div>
												<div class="crxczt5d_10" style="margin: 0px; width: 120px;">
													<font style="width: 120px;">${pro.price }</font>
												</div>
												<div class="crxczt5d_10" style="width: 255px;">
													<font style="width: 160px;">${pro.commission }</font>
												</div>
												<div class="crxczt5d_10" style="width: 125px;">${pro.count }</div>
												<div class="crxczt5d_10" style="width: 80px;">${pro.totalamount }</div>
											</div>
										</c:forEach>
									<td class="conter_right_xx_cz_table_15" rowspan="2" style="border-bottom: 0px; vertical-align: inherit; width: 7%;">
										<div class="crxczt5d_cz_1">${details.refundPrice }</div>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>

			</div>
		</div>
		<div class="ddxq_gg_ddjl margin_20">
			<div class="ddxq_gg_sldc_1_sh">
				<h6 class="mb_10">售后记录</h6>
				<ul>
					<c:forEach var="logstr" items="${details.recordList }">
						<li>${logstr }</li>
					</c:forEach>
				</ul>
			</div>
		</div>
	</div>
</body>
</html>