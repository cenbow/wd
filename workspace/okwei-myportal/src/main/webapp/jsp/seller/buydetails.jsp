<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.ResourceBundle"%>
<%
    String mydomain = ResourceBundle.getBundle("domain").getString("mydomain");
	String sellerdomain = ResourceBundle.getBundle("domain").getString("sellerdomain");
	String okweidomain = ResourceBundle.getBundle("domain").getString("okweidomain");
	String jsdomain = ResourceBundle.getBundle("domain").getString("jsdomain");
	String cssdomain = ResourceBundle.getBundle("domain").getString("cssdomain");
	String imgdomain = ResourceBundle.getBundle("domain").getString("imgdomain");
	String detaildomain = ResourceBundle.getBundle("domain").getString("detaildomain");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>订单详情</title>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/statics/js/seller/orderdetails.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/statics/js/seller/orderpublic.js"></script>
</head>
<body>
	<div class="weiz_iam f12 fm_song">
		当前位置：<a href="<%=mydomain %>/maininfo/maininfo">微店中心</a>&gt; <a href="buylist">已销售的产品</a>&gt;
		<a href="#"> <c:choose>
				<c:when test="${details.orderShowType==1}">
						零售订单
					</c:when>
				<c:when test="${details.orderShowType==2}">
						批发订单
					</c:when>
				<c:when test="${details.orderShowType==3}">
						铺货订单
					</c:when>
			</c:choose> <span class="fw_100">${details.orderNo}</span>
		</a>&gt;<span>订单详情</span>
	</div>
	<div class="ddxq_gg">
		<!-- 流程 -->
		<div class="ddxq_gg_flow">
			<c:choose>
				<c:when
					test="${details.orderShowType==1 ||details.orderShowType==2}">
					<!-- 零售订单/批发订单 -->
					<div
						class="	<c:choose><c:when test="${details.orderState == 0 }">ddxq_gg_flow_red_5_1_yes</c:when>
				<c:when test="${details.orderState == 1 }">ddxq_gg_flow_red_5_2_yes</c:when>
				<c:when test="${details.orderState == 2 }">ddxq_gg_flow_red_5_3_yes</c:when>
				<c:when test="${details.orderState == 3 }">ddxq_gg_flow_red_5_4_yes</c:when>
				<c:when test="${details.orderState == 4 }">ddxq_gg_flow_red_5_5_yes</c:when>
				<c:otherwise>ddxq_gg_flow_red_5_1_no</c:otherwise>
				</c:choose>">
						<div class="ddxq_gg_flow_red_5_1">
							<b>1</b> <span>下单成功</span> <font>${details.orderTimeStr }</font>
						</div>
						<div class="ddxq_gg_flow_red_5_2">
							<b>2</b> <span>买家付款</span> <font>${details.paymentTimeStr }</font>
						</div>
						<div class="ddxq_gg_flow_red_5_3">
							<b>3</b> <span>供应商发货</span> <font>${details.deliveryTimeStr }</font>
						</div>
						<div class="ddxq_gg_flow_red_5_4">
							<b>4</b> <span>买家收货</span> <font>${details.goodsTimeStr }</font>
						</div>
						<div class="ddxq_gg_flow_red_5_5">
							<b>5</b> <span>微店网付款给供应商</span> <font>${details.dealTime }</font>
						</div>
					</div>
				</c:when>
				<c:when test="${details.orderShowType==3}">
					<!-- 预售订单 -->
					<div
						class="	<c:choose><c:when test="${details.orderState == 8 }">ddxq_gg_flow_red_5_1_yes</c:when>
				<c:when test="${details.orderState == 10 }">ddxq_gg_flow_red_5_2_yes</c:when>
				<c:when test="${details.orderState == 12||details.orderState == 1 }">ddxq_gg_flow_red_5_3_yes</c:when>
				<c:when test="${details.orderState == 3 ||details.orderState == 2 }">ddxq_gg_flow_red_5_4_yes</c:when>
				<c:when test="${details.orderState == 4 }">ddxq_gg_flow_red_5_5_yes</c:when>
				<c:otherwise>ddxq_gg_flow_red_5_1_no</c:otherwise>
				</c:choose>">
						<div class="ddxq_gg_flow_red_5_1">
							<b>1</b> <span>提交铺货</span> <font>${details.orderTimeStr }</font>
						</div>
						<div class="ddxq_gg_flow_red_5_2">
							<b>2</b> <span>确认铺货</span> <font>${details.makeOrderTimeStr }</font>
						</div>
						<div class="ddxq_gg_flow_red_5_3">
							<b>3</b> <span>支付款项</span> <font>${details.paymentTimeStr }</font>
						</div>
						<div class="ddxq_gg_flow_red_5_4">
							<b>4</b> <span>确认收货</span> <font>${details.goodsTimeStr }</font>
						</div>
						<div class="ddxq_gg_flow_red_5_5">
							<b>5</b> <span>订单完成</span> <font>${details.dealTime }</font>
						</div>
					</div>
				</c:when>
			</c:choose>

		</div>

		<div class="ddxq_gg_ddzt">
			<c:if test="${details.orderShowType==1||details.orderShowType==2 }">
				<!-- 零售批发 -->
				<c:choose>
					<c:when test="${details.orderState==0}">
						<!-- 未付款 -->
						<div class="ddxq_gg_ddzt_xx">
							当前订单状态：<b>下单成功</b>，未支付；
						</div>
						<div class="ddxq_gg_ddzt_xx_1 mt_10 ml_30">
							1.您可以去修改该订单的价格 <a href="modifyprice?orderNo=${details.orderNo }">
								<span class="ddxq_gg_ddzt_xx_fk">修改价格</span>
							</a>
						</div>
						<!-- 
							<div class="ddxq_gg_ddzt_xx_1 ml_30">
								2.您可以联系消费者，进行在线聊天，方便完成此交易。
								<a href="javascript:void(0);" class="wdqb_cz_tx" style="float: none; display: inline-block; color: #666;">在线聊天</a>
							</div>
							 -->
					</c:when>
					<c:when test="${details.orderState==1}">
						<!-- 已付款 -->
						<div class="ddxq_gg_ddzt_xx">
							当前订单状态：<b>消费者已支付</b>，请及时发货；
						</div>
						<div class="ddxq_gg_ddzt_xx_1 mt_10 ml_30">
							1.点击进行<a href="delivery?orderNo=${details.orderNo }"><span
								class="ddxq_gg_ddzt_xx_fk">发货</span></a>
						</div>
					</c:when>
					<c:when test="${details.orderState==2}">
						<!-- 已发货 -->
						<div class="ddxq_gg_ddzt_xx">
							当前订单状态：<b>已发货</b>，等待买家收货；
						</div>
						<div class="ddxq_gg_ddzt_xx_1 mt_10 ml_30">
							1.点击进行 <a
								href="<%=mydomain %>/order/logistics?orderNo=${details.orderNo }">
								<span class="wdqb_cz_tx"
								style="display: inline-block; float: none; margin-left: 10px;">查看物流</span>
							</a>
						</div>
					</c:when>
					<c:when test="${details.orderState==3}">
						<!-- 已收货 -->
						<div class="ddxq_gg_ddzt_xx">
							当前订单状态：<b>采购商已收货</b>，等待微店网放款；
						</div>
						<div class="ddxq_gg_ddzt_xx_1 mt_10 ml_30">
							1.点击进行 <a
								href="<%=mydomain %>/order/logistics?orderNo=${details.orderNo }">
								<span class="wdqb_cz_tx"
								style="display: inline-block; float: none; margin-left: 10px;">查看物流</span>
							</a>
						</div>
						<div class="ddxq_gg_ddzt_xx_1  ml_30">2.等待微店网打款，预计将在7-10个工作日内放款。</div>
					</c:when>
					<c:when test="${details.orderState==4}">
						<!-- 已完成 -->
						<div class="ddxq_gg_ddzt_xx">
							当前订单状态：<b>交易完成</b>，等待微店网放款；
						</div>
						<div class="ddxq_gg_ddzt_xx_1 mt_10 ml_30">您的订单交易完成，微店网已成功放款到您微店钱包账户，请注意查收！</div>
						<!-- 
							<div class="ddxq_gg_ddzt_xx_1  ml_30">
								如果您有什么疑问，请联系微店网客服 
								<a href="javascript:void(0);" class="wdqb_cz_tx" style="display: inline-block; float: none; margin-left: 10px; color: #323232;">联系客服</a>
							</div>
							 -->
					</c:when>
					<c:when test="${details.orderState==13}">
						<!-- 已过期 -->
						<div class="ddxq_gg_ddzt_xx">
							当前订单状态：<b>该订单已过期</b>；
						</div>
					</c:when>
					<c:when test="${details.orderState==7}">
						<!-- 已取消 -->
						<div class="ddxq_gg_ddzt_xx">
							当前订单状态：<b>该订单已取消</b>；
						</div>
						<div class="ddxq_gg_ddzt_xx_1 mt_10 ml_30">1.采购商主动取消该订单</div>
					</c:when>
					<c:when test="${details.orderState == 5 }">
						<div class="ddxq_gg_ddzt_xx">
							当前订单状态：<b>退款中</b>；
						</div>
					</c:when>
					<c:when test="${details.orderState == 6 }">
						<div class="ddxq_gg_ddzt_xx">
							当前订单状态：<b>退款完成</b>；
						</div>
					</c:when>
				</c:choose>
			</c:if>
			<c:if test="${details.orderShowType==3 }">
				<!-- 铺货单 -->
				<c:choose>
					<c:when test="${details.orderState==8}">
						<!-- 等待确定 -->
						<div class="ddxq_gg_ddzt_xx">
							当前订单状态：<b>下单成功</b>，等待供应商确认；请及时处理铺货订单
						</div>
						<div class="ddxq_gg_ddzt_xx_1 mt_20 ml_30">
							1.点击进行<a href="reservedispose?orderNo=${details.orderNo }"
								class="ddxq_gg_ddzt_xx_fk mr_5" style="color: #fff;">处理订单</a>
						</div>
						<div class="ddxq_gg_ddzt_xx_1 mt_10 ml_30">
							1.点击进行 <a href="javascript:void(0);"
								onclick="refusedwin(${details.orderNo})" class="wdqb_cz_tx"
								style="display: inline-block; float: none; margin-left: 5px; width: 94px; color: #323232;">拒绝订单</a>
						</div>
					</c:when>
					<c:when test="${details.orderState==13}">
						<!-- 已过期 -->
						<div class="ddxq_gg_ddzt_xx">
							当前订单状态：<b>该订单已过期</b>；
						</div>
					</c:when>
					<c:when test="${details.orderState==7}">
						<!-- 已取消 -->
						<div class="ddxq_gg_ddzt_xx">
							当前订单状态：<b>该订单已取消</b>；
						</div>
						<div class="ddxq_gg_ddzt_xx_1 mt_10 ml_30">1.采购商主动取消该订单</div>
					</c:when>
					<c:when test="${details.orderState==11}">
						<!-- 已拒绝 -->
						<div class="ddxq_gg_ddzt_xx">
							当前订单状态：<b>已拒绝；</b>
						</div>
						<div class="ddxq_gg_ddzt_xx_1 mt_20 ml_30">您已拒绝该订单</div>
					</c:when>
					<c:when test="${details.orderState==10}">
						<!-- 已确定 -->
						<c:if test="${details.payPriceType==1  }">
							<!-- 全款支付 -->
							<div class="ddxq_gg_ddzt_xx">
								当前订单状态：<b>已确认订单</b>，等待采购商付款
							</div>
							<div class="ddxq_gg_ddzt_xx_1 mt_20 ml_30">1.付款方式：全额支付</div>
							<div class="ddxq_gg_ddzt_xx_1 ml_30">2.预计发货时间：${details.deliveryTimeStr }
							</div>
							<div class="ddxq_gg_ddzt_xx_1 ml_30">3.留言：${details.supplierMsg }</div>
						</c:if>
						<c:if test="${details.payPriceType ==2 }">
							<!-- 支付定金支付 -->
							<div class="ddxq_gg_ddzt_xx">
								当前订单状态：<b>已确认订单</b>，等待采购商付款
							</div>
							<div class="ddxq_gg_ddzt_xx_1 mt_20 ml_30">
								1.付款方式：预付定金<font class="ziti_Verdana">￥${details.deposit }</font>
							</div>
							<div class="ddxq_gg_ddzt_xx_1 ml_30">
								<c:if test="${details.tailPayType ==0 }">
									<!-- 发货前支付尾款 -->
									2.尾款付款方式：发货前支付尾款 <font class="ziti_Verdana">￥${details.finalprice }</font>
								</c:if>
								<c:if test="${details.tailPayType ==1 }">
									<!-- 发货后支付尾款 -->
									2.尾款付款方式：发货后支付尾款 <font class="ziti_Verdana">￥${details.finalprice }</font>
								</c:if>
							</div>
							<div class="ddxq_gg_ddzt_xx_1 ml_30">3.发货时间：${details.deliveryTimeStr }</div>
							<div class="ddxq_gg_ddzt_xx_1 ml_30">4.留言：${details.supplierMsg }</div>
						</c:if>
						<c:if test="${details.payPriceType ==3 }">
							<!-- 支付定金支付(百分比) -->
							<div class="ddxq_gg_ddzt_xx">
								当前订单状态：<b>已确认订单</b>，等待采购商付款
							</div>
							<div class="ddxq_gg_ddzt_xx_1 mt_20 ml_30">
								1.付款方式：预付${details.percentage }%定金 <font class="ziti_Verdana">￥${details.deposit }</font>
							</div>
							<div class="ddxq_gg_ddzt_xx_1 ml_30">
								<c:if test="${details.tailPayType ==0 }">
									<!-- 发货前支付尾款 -->
									2.尾款付款方式：发货前支付尾款 <font class="ziti_Verdana">￥${details.finalprice }</font>
								</c:if>
								<c:if test="${details.tailPayType ==1 }">
									<!-- 发货后支付尾款 -->
									2.尾款付款方式：发货后支付尾款 <font class="ziti_Verdana">￥${details.finalprice }</font>
								</c:if>
							</div>
							<div class="ddxq_gg_ddzt_xx_1 ml_30">3.发货时间：${details.deliveryTimeStr }
							</div>
							<div class="ddxq_gg_ddzt_xx_1 ml_30">4.留言：${details.supplierMsg }</div>
						</c:if>
					</c:when>
					<c:when test="${details.orderState==12}">
						<!-- 已支付定金  -->
						<c:if test="${details.tailPayType==0 }">
							<!-- 发货前 -->
							<div class="ddxq_gg_ddzt_xx">
								当前订单状态：<b>采购商已支付定金</b>；
							</div>
							<div class="ddxq_gg_ddzt_xx_1 mt_20 ml_30">
								1.付款方式：支付铺货金额${details.percentage }%/支付铺货金额<font
									class="ziti_Verdana">￥${details.deposit }</font>
							</div>
							<div class="ddxq_gg_ddzt_xx_1 ml_30">
								2.尾款付款方式：采购商发货前支付尾款 <font class="ziti_Verdana">￥${details.finalprice }</font>
							</div>
							<div class="ddxq_gg_ddzt_xx_1 ml_30">3.请在${details.deliveryTimeStr }
								前备货</div>
						</c:if>
						<c:if test="${details.tailPayType==1 }">
							<!-- 发货后 -->
							<div class="ddxq_gg_ddzt_xx">
								当前订单状态：<b>采购商已支付定金</b>，等待供应商发货；
							</div>
							<div class="ddxq_gg_ddzt_xx_1 mt_20 ml_30">
								1.付款方式：支付铺货金额${details.percentage }%/支付铺货金额<font
									class="ziti_Verdana">${details.deposit }</font>
							</div>
							<div class="ddxq_gg_ddzt_xx_1 ml_30">
								2.尾款付款方式：采购商发货前支付尾款 <font class="ziti_Verdana">￥${details.finalprice }</font>
							</div>
							<div class="ddxq_gg_ddzt_xx_1 ml_30">3.请在${details.deliveryTimeStr }前备货</div>
							<div class="ddxq_gg_ddzt_xx_1 ml_30">
								4.点击这里<a href="delivery?orderNo=${details.orderNo }"
									class="ddxq_gg_ddzt_xx_fk mr_5" style="color: #fff;">去发货</a>
							</div>
						</c:if>
					</c:when>
					<c:when test="${details.orderState==1}">
						<!-- 已支付 -->
						<div class="ddxq_gg_ddzt_xx">
							当前订单状态：<b>采购商已支付全款</b>，请预先备货，并及时发货；
						</div>
						<div class="ddxq_gg_ddzt_xx_1 mt_20 ml_30">
							1.点击进行<a href="delivery?orderNo=${details.orderNo }"
								class="ddxq_gg_ddzt_xx_fk mr_5" style="color: #fff;">发货</a>
						</div>
						<div class="ddxq_gg_ddzt_xx_1 ml_30">3.请在${details.deliveryTimeStr }前备货</div>
					</c:when>
					<c:when test="${details.orderState==2}">
						<!-- 已发货 -->
						<div class="ddxq_gg_ddzt_xx">
							当前订单状态：<b>已发货</b>，等待采购商收货；
						</div>
						<div class="ddxq_gg_ddzt_xx_1 mt_20 ml_30">
							<!-- 
									支付类型，1全额支付，2预付定金，3预付定金百分比
									铺货付款方式（0支付尾款发货，1发货后支付尾款）
								 -->
							<c:choose>
								<c:when test="${details.payPriceType==1}">
									1.采购商已全款支付 <font>￥${details.fullprice }</font>
								</c:when>
								<c:when test="${details.tailPayType==0}">
									1.采购商已支付定金+尾款 <font>￥${details.deposit + details.finalprice }</font>
								</c:when>
								<c:when test="${details.tailPayType==1}">
									1.采购商已支付定金 <font>￥${details.deposit }</font>
								</c:when>
							</c:choose>
						</div>
						<a
							href="<%=mydomain %>/order/logistics?orderNo=${details.orderNo }"
							class="wdqb_cz_tx"
							style="display: inline-block; float: none; margin-left: 5px; width: 94px; color: #323232;">查看物流</a>
					</c:when>
					<c:when test="${details.orderState==3}">
						<!-- 已收货 -->
						<c:if
							test="${details.tailPayType == 0 || details.payPriceType==1 }">
							<!-- 发货前 -->
							<div class="ddxq_gg_ddzt_xx">
								当前订单状态：<b>采购商已收货</b>，等待微店网放款；
							</div>
							<div class="ddxq_gg_ddzt_xx_1 ml_30">2.等待微店网打款，预计将在7-10个工作日内打款。</div>
						</c:if>
						<c:if test="${details.tailPayType == 1  }">
							<!-- 发货后 -->
							<div class="ddxq_gg_ddzt_xx">
								当前订单状态：<b>采购商已收货</b>，等待采购商支付尾款；
							</div>
							<div class="ddxq_gg_ddzt_xx_1 mt_20 ml_30">1.请等待采购商支付尾款</div>
							<div class="ddxq_gg_ddzt_xx_1 ml_30">
								2.线下打款请点击<a href="javascript:void(0);"
									onclick="paymentwin('${details.orderNo}')"
									class="ddxq_gg_ddzt_xx_fk mr_5" style="color: #fff;">已支付尾款</a>
							</div>
						</c:if>
					</c:when>
					<c:when test="${details.orderState==4}">
						<!-- 已完成 -->
						<div class="ddxq_gg_ddzt_xx">
							当前订单状态：<b class="ddxq_gg_ddzt_xx_col">交易完成</b>
						</div>
						<div class="ddxq_gg_ddzt_xx_1 mt_20 ml_30">您的订单交易完成，微店网已成功放款到你的微店钱包账户，请注意查收！</div>
						<div class="ddxq_gg_ddzt_xx_1 ml_30">
							如果您有什么疑问，请联系微店网客服 <a
								href="<%=mydomain %>/order/logistics?orderNo=${details.orderNo }"
								class="wdqb_cz_tx"
								style="display: inline-block; float: none; margin-left: 5px; width: 94px; color: #323232;">查看物流</a>
						</div>
					</c:when>
					<c:when test="${details.orderState == 5 }">
						<div class="ddxq_gg_ddzt_xx">
							当前订单状态：<b>退款中</b>；
						</div>
					</c:when>
					<c:when test="${details.orderState == 6 }">
						<div class="ddxq_gg_ddzt_xx">
							当前订单状态：<b>退款完成</b>；
						</div>
					</c:when>
				</c:choose>
			</c:if>
		</div>


		<!-- 收货地址-留言-订单信息-产品清单 -->
		<div class="ddxq_gg_sldc">
			<div class="ddxq_gg_sldc_1 margin_20">
				<!-- 收货地址 -->
				<div class="ddxq_gg_sldc_1_sh">
					<h6 class="mb_10">收货地址</h6>
					<div class="ddxq_gg_sldc_1_sh_1">
						<span class="ddxq_gg_sldc_1_sh_1_span">收货人：<font>${details.reciverName}</font></span>
						<span class="ddxq_gg_sldc_1_sh_1_span" style="margin-left:50px;"> 联系电话 ：<font>${details.reciverPhoneNumber}</font></span>
					</div>
					<div class="ddxq_gg_sldc_1_sh_1">
						<span class="ddxq_gg_sldc_1_sh_1_span">收货地址：<font>${details.reciverAddress}</font></span>
					</div>
					<h6 class="mb_10 mt_20">留言</h6>
					<span class="ddxq_gg_sldc_1_sh_1_span">${details.clientMsg }</span>
					<h6 class="mb_10 mt_20">订单信息</h6>
					<div class="ddxq_gg_sldc_1_sh_1">
						<span class="ddxq_gg_sldc_1_sh_1_span">订单号：<font>${details.orderNo }</font></span><span
							class="ddxq_gg_sldc_1_sh_1_span ml_10" style="margin-left:50px;"> 支付方式：<font>${details.payWay }</font></span>
						<span class="ddxq_gg_sldc_1_sh_1_span" style="margin-left:50px;"> 参与促销：<font>${details.preferential }</font></span>
					</div>
					<h6 class="lh_40 bor_to mt_10">产品清单</h6>

					<div class="conter_right_xx_cz mt bor_cls">
						<div class="conter_right_xx_cz_t mt_20"
							style="background: #e7e7e7;">
							<ul>
								<li class="l_8 fw_b">商品信息</li>
								<li class="l_13 fw_b">单价（元）</li>
								<li class="l_12 fw_b">数量</li>


								<li class="l_3 fw_b">优惠方式（元）</li>
								<li class="l_3 fw_b" style="width: 120px;">成交微店</li>
								<li class="l_3 fw_b" style="width: 80px;">付款金额</li>
								<li class="l_3 fw_b">售后</li>
								<li class="l_7 fw_b" style="margin-left: 100px;">运费（元）</li>
								<li class="l_7 fw_b" style="margin-left: 70px;">状态</li>
							</ul>
						</div>
						<table class="conter_right_xx_cz_table"
							style="border: 1px solid #e7e7e7;">
							<tbody>
								<tr class="fl">
									<td class="conter_right_xx_cz_table_55"
										style="border-bottom: 0px; width: auto;"><c:forEach
											var="pro" items="${details.proList }" varStatus="varStr">
											<div class="conter_right_xx_cz_table_55_div"
												style=" ${varStr.index == 0?'border: 0px;padding-top:0px;':'' }">
												<div class="crxczt5d_0">
													<%-- <a
														href="http://www.${userinfo.weiID }.<%=okweidomain %>/cpxq.html?pNo=${pro.productId }">
													 --%>	<a href="<%=detaildomain%>/product?sid=&pid=${pro.productId }&f=&w="> 
														<img src="${pro.productImg }" /></a>
												</div>
												<div class="crxczt5d_10">
													<span style="width: 140px;">${pro.productTitle }</span> <span
														style="width: 140px; color: #3c3c3c;">${pro.property }</span>
												</div>
												<div class="crxczt5d_4" style="margin-left: 0px;"></div>
												<div class="crxczt5d_2">
													<font>${pro.price }</font>
												</div>
												<div class="crxczt5d_2" style="width: 80px;">${pro.count }</div>
												<div class="crxczt5d_10"
													style="width: 70px; min-height: 30px;">
													<font style="width: 100px;">${pro.favorable }</font>
												</div>
												<div class="crxczt5d_10"
													style="margin: 0px; min-height: 30px; width: 120px; margin-left: 40px; text-align: center;">
													<font class="mzh_4_17_jl" style="margin: 0px;">${pro.sourceName }</font>
												</div>
												<div class="crxczt5d_10"
													style="width: 70px; min-height: 30px;">
													<font style="width: 100px;">${pro.sumPrice }</font>
												</div>
												<div class="crxczt5d_10"
													style="width: 100px; min-height: 30px;">
													<c:if test="${details.infoType==1}">
													<font><a href="refund?refundId=${pro.refundId }">
															<c:choose>
																<c:when test="${pro.refundState ==0 }">申请中</c:when>
																<c:when test="${pro.refundState ==1 }">供应商同意退款</c:when>
																<c:when test="${pro.refundState ==2 }">供应商不同意退款</c:when>
																<c:when test="${pro.refundState ==3 }">微店网介入</c:when>
																<c:when test="${pro.refundState ==4 }">微店网介入，支持退款</c:when>
																<c:when test="${pro.refundState ==5 }">买家已发货</c:when>
																<c:when test="${pro.refundState ==6 }">退款完成</c:when>
															</c:choose>
													</a></font>
													</c:if>
												</div>
											</div>
										</c:forEach></td>
									<td class="conter_right_xx_cz_table_15" rowspan="2"
										style="border-bottom: 0px; vertical-align: inherit; width: 7%;">
										<div class="crxczt5d_cz_1">${details.postPrice }</div>
										<div class="crxczt5d_cz_2">（快递）</div>
									</td>
									<td class="conter_right_xx_cz_table_15" rowspan="2"
										style="border-bottom: 0px; vertical-align: inherit; width: 7%;">
										<div class="crxczt5d_cz_1">
											<c:choose>
												<c:when test="${details.orderState==0}">
													未付款
												</c:when>
												<c:when test="${details.orderState==1}">
													已付款
												</c:when>
												<c:when test="${details.orderState==2}">
													已发货
												</c:when>
												<c:when test="${details.orderState==3}">
													已收货
												</c:when>
												<c:when test="${details.orderState==4}">
													已完成
												</c:when>
												<c:when test="${details.orderState==5}">
													退款中
												</c:when>
												<c:when test="${details.orderState==6}">
													已退款
												</c:when>
												<c:when test="${details.orderState==7}">
													已取消
												</c:when>
												<c:when test="${details.orderState==8}">
													等待确认
												</c:when>
												<c:when test="${details.orderState==9}">
													申请取消
												</c:when>
												<c:when test="${details.orderState==10}">
													已确定
												</c:when>
												<c:when test="${details.orderState==11}">
													已拒绝
												</c:when>
												<c:when test="${details.orderState==12}">
													已支付定金
												</c:when>
												<c:when test="${details.orderState==13}">
													已过期
												</c:when>
												<c:when test="${details.orderState==14}">
													已删除
												</c:when>
											</c:choose>
										</div>
									</td>
								</tr>
								<tr class="fl">
									<td rowspan="7"
										style="border: 0px; border-top: 1px solid #e7e7e7; padding: 0px;">
										<div class="fl count_price f12 tr"
											style="background: #fff; margin-left: 0px; width: 1140px; padding-right: 14px;">
											实付款：<span class="f24 color_red">￥${details.totalPrice }</span>
										</div>
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
				<h6 class="mb_10">订单记录</h6>
				<ul>
					<c:forEach var="log" items="${details.orderFlows }">
						<li><fmt:formatDate value="${log.operateTime}" type="both"
								dateStyle="default" /><span> ${log.operateContent }</span></li>
					</c:forEach>
				</ul>
			</div>
		</div>
	</div>


	<!-- 删除订单提示 -->
	<div id="delete_ts" class="mzh_tcc">
		<div class="mzh_tcc_1">
			是否确认删除该订单？<br />删除该订单将不再显示
		</div>
	</div>
	<!-- 拒绝订单提示 -->
	<div id="refused_ts" class="mzh_tcc">
		<div class="mzh_tcc_1">
			是否确认拒绝该订单？<br />
		</div>
	</div>
	<!-- 支付尾款确认 -->
	<div id="payment_ts" class="mzh_tcc">
		<div class="mzh_tcc_1">
			是否确认支付尾款？<br />
		</div>
	</div>
	<!-- 同意退款 -->
	<div id="agreed_ts" class="mzh_tcc">
		<div class="mzh_tcc_1">
			是否同意退款？<br />
		</div>
	</div>
	<!-- 不同意退款 -->
	<div id="notagreed_ts" class="mzh_tcc">
		<div class="mzh_tcc_1">
			是否不同意退款？<br />
		</div>
	</div>
	<!-- 确认收货(退货收货)提示 -->
	<div id="confirmcargo_ts" class="mzh_tcc">
		<div class="mzh_tcc_1">
			是否确认收货？<br />
		</div>
	</div>

</body>
</html>