<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>退款详情</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/statics/js/layer/layer.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/statics/js/order/refundetail.js"></script>

</head>
<body>
	<div class="weiz_iam f12 fm_song">
		当前位置：<a href="/maininfo/maininfo">微店中心</a>&gt;<a href="/order/buylist">我的购买订单 </a>&gt;
		<c:choose>
			<c:when test="${details.refundWay == 1 }">
				<span>退款详情</span>
			</c:when>
			<c:when test="${details.refundWay == 2 }">
				<span>退货详情</span>
			</c:when>
		</c:choose>
	</div>
	<div class="ddxq_gg">
		<!-- 流程图 -->
		<div class="ddxq_gg_flow">
			<c:choose>
				<c:when test="${details.refundWay==1}">
					<div class="<c:choose>
						<c:when test="${details.refundState==0}">ddxq_gg_flow_red_5_3_yes</c:when>
						<c:when test="${details.refundState==6}">ddxq_gg_flow_red_5_5_yes</c:when>
						<c:otherwise>ddxq_gg_flow_red_5_3_yes</c:otherwise>
					</c:choose>">
						<div class="ddxq_gg_flow_red_5_1">
							<b>1</b> <span>申请退款</span>
						</div>
						<div class="ddxq_gg_flow_red_5_3">
							<b>2</b> <span>等待供应商确认</span>
						</div>
						<div class="ddxq_gg_flow_red_5_5">
							<b>3</b> <span>退款完成</span>
						</div>
					</div>
				</c:when>
				<c:when test="${details.refundWay==2}">

					<div class="<c:choose>
						<c:when test="${details.refundState==0||details.refundState==2||details.refundState==3 ||details.refundState==4}">ddxq_gg_flow_red_5_2_yes</c:when>
						<c:when test="${details.refundState==1}">ddxq_gg_flow_red_5_3_yes</c:when>
						<c:when test="${details.refundState==5}">ddxq_gg_flow_red_5_4_yes</c:when>
						<c:when test="${details.refundState==6}">ddxq_gg_flow_red_5_5_yes</c:when>
						<c:otherwise>ddxq_gg_flow_red_5_1_yes</c:otherwise>
					</c:choose>">
						<div class="ddxq_gg_flow_red_5_1">
							<b>1</b> <span>申请退货退款</span>
						</div>
						<div class="ddxq_gg_flow_red_5_2">
							<b>2</b> <span>等待供应商回应</span>
						</div>
						<div class="ddxq_gg_flow_red_5_3">
							<b>3</b> <span>发货给供应商</span>
						</div>
						<div class="ddxq_gg_flow_red_5_4">
							<b>4</b> <span>等待供应商收货</span>
						</div>
						<div class="ddxq_gg_flow_red_5_5">
							<b>5</b> <span>退款完成</span>
						</div>
					</div>
				</c:when>
			</c:choose>
		</div>
		<!-- 下单成功 -->
		<div class="ddxq_gg_ddzt">
			<c:choose>
				<c:when test="${details.refundState == 0 }">
					<!-- 申请中（等待供应商确认） -->
					<div class="ddxq_gg_ddzt_xx">
						当前状态： <b>等待供应商确认</b>； <a href="javascript:void(0);" onclick="cancelrefundwin('${details.backOrder}')" class="wdqb_cz_tx" style="float: none; display: inline-block; margin-left: 20px;">取消退款申请</a>
					</div>
					<div class="ddxq_gg_ddzt_xx_1 mt_10 ml_30">您的退款申请已提交，请等待供应商确认，将在48小时内回应；超时则默认同意您的请求！</div>
					<div class="ddxq_gg_ddzt_xx_1 ml_30">
						退款方式：
						<c:choose>
							<c:when test="${details.refundWay==1 }">退款</c:when>
							<c:when test="${details.refundWay==2 }">退货</c:when>
						</c:choose>
					</div>
					<div class="ddxq_gg_ddzt_xx_1 ml_30">
						退款金额：<span class="color_red">￥${details.refundPrice }</span>
					</div>
					<div class="ddxq_gg_ddzt_xx_1 f14 ml_30 mt_10">
						<b>退款理由：</b>${details.reason }</div>
					<div class="ddxq_gg_ddzt_xx_1 f14 ml_30 mt_10">
						<b>产品图片：</b>
						<div class="clear"></div>
						<div class="blank2"></div>
						<c:forEach var="imgstr" items="${details.proImages }">
							<img src="${imgstr }" class="ddxq_gg_tkly_dt_img">
						</c:forEach>
					</div>
				</c:when>
				<c:when test="${details.refundState == 1 }">
					<!-- 供应商同意退款 -->
					<c:choose>
						<c:when test="${details.refundWay==1 }">
							<!-- 同意退款 -->
							<div class="ddxq_gg_ddzt_xx">
								当前状态： <b>供应商同意退款；交易结束</b>
							</div>
							<div class="ddxq_gg_ddzt_xx_1 mt_10 ml_30">供应商已同意您的退款，微店网预计在15个工作日内将退款金额原路退回，届时请留意查看。</div>
							<div class="ddxq_gg_ddzt_xx_1 mt_10 ml_30">如有疑问，请联系微店网客服，感谢您对微店网的支持</div>
						</c:when>
						<c:when test="${details.refundWay==2 }">
							<!-- 同意退货 -->
							<div class="ddxq_gg_ddzt_xx">
								当前状态： <b>供应商同意退货</b>； <a href="refunddelivery?refundId=${details.backOrder }" class="wdqb_cz_tx" style="float: none; display: inline-block; margin-left: 20px;">发货</a>
							</div>
							<div class="ddxq_gg_ddzt_xx_1 mt_10 ml_30">供应商同意您的退款，请按照如下地址退货：</div>
							<div class="ddxq_gg_ddzt_xx_1 ml_30">收货人：${details.consignee }</div>
							<div class="ddxq_gg_ddzt_xx_1 ml_30">联系电话：${details.phone }</div>
							<div class="ddxq_gg_ddzt_xx_1 ml_30">收货地址：${details.retreatAddress }</div>
						</c:when>
					</c:choose>
				</c:when>
				<c:when test="${details.refundState == 2 }">
					<!-- 供应商不同意退款 -->
					<div class="ddxq_gg_ddzt_xx">
						当前状态：
						<c:choose>
							<c:when test="${details.refundWay ==1 }">
								<b>供应商不同意退款</b>；</c:when>
							<c:when test="${details.refundWay ==2 }">
								<b>供应商不同意退货</b>；</c:when>
						</c:choose>
						如果您对处理结果不满意，可在48小时内， <a href="javascript:void(0);" onclick="meddlewin(${details.backOrder})">申请微店网介入</a>
					</div>
					<c:if test="${details.refundWay == 2 }">
						<div class="ddxq_gg_ddzt_xx_1 mt_10 ml_30">退款金额: ￥${details.refundPrice }</div>
					</c:if>
					<div class="ddxq_gg_ddzt_xx_1 mt_10 ml_30">供应商不同意理由：${details.reasonNo }</div>
					<div class="ddxq_gg_ddzt_xx_1 ml_30">退款理由：${details.reason }</div>
					<div class="ddxq_gg_ddzt_xx_1 f14 ml_30 ">
						<b>产品图片：</b>
						<div class="clear"></div>
						<div class="blank2"></div>
						<c:forEach var="imgStr" items="${details.proImages }">
							<img src="${imgStr }" class="ddxq_gg_tkly_dt_img">
						</c:forEach>
					</div>
				</c:when>
				<c:when test="${details.refundState == 3 }">
					<!-- 微店网介入 -->
					<div class="ddxq_gg_ddzt_xx">
						当前状态：<b>微店网介入中</b>； <a href="javascript:void(0);" onclick="cancelrefundwin(${details.backOrder})">取消申请</a>
					</div>
					<div class="ddxq_gg_ddzt_xx_1 mt_10 ml_30">供应商不同意理由：${details.reasonNo }</div>
					<div class="ddxq_gg_ddzt_xx_1 ml_30">退款理由：${details.reason }</div>
					<div class="ddxq_gg_ddzt_xx_1 f14 ml_30 ">
						<b>产品图片：</b>
						<div class="clear"></div>
						<div class="blank2"></div>
						<c:forEach var="imgStr" items="${details.proImages }">
							<img src="${imgStr }" class="ddxq_gg_tkly_dt_img">
						</c:forEach>
					</div>
				</c:when>
				<c:when test="${details.refundState == 4 }">
					<!-- 微店网介入，支持退款-->
					<div class="ddxq_gg_ddzt_xx">
						当前状态： <b>微店网同意退货</b>； <a href="refunddelivery?refundId=${details.backOrder }" class="wdqb_cz_tx" style="float: none; display: inline-block; margin-left: 20px;">发货</a>
					</div>
					<div class="ddxq_gg_ddzt_xx_1 ml_30 mt_10">微店网处理：${details.reasonOkwei }</div>
					<div class="ddxq_gg_ddzt_xx_1 mt_10 ml_30">供应商同意您的退款，请按照如下地址退货：</div>
					<div class="ddxq_gg_ddzt_xx_1 ml_30">收货人：${details.consignee }</div>
					<div class="ddxq_gg_ddzt_xx_1 ml_30">联系电话：${details.phone }</div>
					<div class="ddxq_gg_ddzt_xx_1 ml_30">收货地址：${details.retreatAddress }</div>
				</c:when>
				<c:when test="${details.refundState == 5 }">
					<!-- 买家已发货 -->
					<div class="ddxq_gg_ddzt_xx">
						当前状态：<b>等待供应商确认收货</b>
					</div>
					<div class="ddxq_gg_ddzt_xx_1 ml_30 mt_10">
						<b>物流信息</b>
					</div>
					<dl class="ddxq_gg_wuliu_dl ml_30 mt_7">
						<dd>快递公司：</dd>
						<dt>${details.logistics.longisticsName }</dt>
					</dl>
					<dl class="ddxq_gg_wuliu_dl ml_30">
						<dd>运单号码：</dd>
						<dt>${details.logistics.logisticsNo }</dt>
					</dl>
					<dl class="ddxq_gg_wuliu_dl ml_30">
						<dd>物流跟踪：</dd>
						<dt>
							<c:forEach var="logStr" items="${details.logistics.tailList }">
								<span>${logStr }</span>
							</c:forEach>
						</dt>
					</dl>
				</c:when>
				<c:when test="${details.refundState == 6 }">
					<!-- 退款完成 -->
					<div class="ddxq_gg_ddzt_xx">
						当前状态：<b>供应商已确认收货；</b>交易结束
					</div>
					<div class="ddxq_gg_ddzt_xx_1 ml_30 mt_5">供应商已同意您的退款，微店网预计在15个工作日内将退款金额原路退回，届时请留意查看。</div>
					<div class="ddxq_gg_ddzt_xx_1 ml_30 ">如有疑问，请联系微店网客服，感谢您对微店网的支持</div>
				</c:when>
				<c:when test="${details.refundState == 7 }">
					<!-- 申请关闭 -->
				</c:when>
			</c:choose>
		</div>

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
													<a href="http://www.${pro.buyerId }.okwei.com/cpxq.html?pNo=${pro.productId }"> <img src="${pro.productImg }"></a>
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
	<!-- 弹窗 -->
	<!-- 取消退款 -->
	<div id="cancelrefund" class="mzh_tcc">
		<div class="mzh_tcc_1">是否确认取消退款退货申请？</div>
	</div>
	<!-- 申请微店网介入 -->
	<div id="meddle" class="mzh_tcc">
		<div class="mzh_tcc_1">是否确认申请微店网介入？</div>
	</div>
</body>
</html>