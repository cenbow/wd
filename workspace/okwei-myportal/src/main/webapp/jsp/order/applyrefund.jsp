<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>申请退款</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/statics/js/uploadify/uploadify.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/statics/js/uploadify/jquery.uploadify-3.1.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/statics/js/order/applyrefund.js"></script>
<style type="text/css">
#uploadImg-button {
	background-position: center;
}
</style>
</head>
<body>

	<div class="weiz_iam f12 fm_song">
		当前位置：<a href="/maininfo/maininfo">微店中心</a>&gt;<a href="/order/buylist">订单列表</a>&gt;
		<c:choose>
			<c:when test="${details.orderType==1}">
						零售订单
					</c:when>
			<c:when test="${details.orderType==2}">
						预售订单
					</c:when>
			<c:when test="${details.orderType==3}">
						批发订单
					</c:when>
		</c:choose>
		<span class="fw_100">${details.orderNum}</span> &gt;<span>申请退款</span>
	</div>
	<div class="ddxq_gg">
		<!-- 流程 -->
		<div id="lctk" class="ddxq_gg_flow" style="display: none;">
			<div class="ddxq_gg_flow_yes po_left_-50">
				<b>1</b> <span>申请退款</span>
			</div>
			<div class="ddxq_gg_flow_no po_left_380">
				<b>2</b> <span>等待供应商确认</span>
			</div>
			<div class="ddxq_gg_flow_no po_left_760">
				<b>3</b> <span>微店网退款给消费者</span>
			</div>
		</div>
		<div id="lcth" class="ddxq_gg_flow" style="display: none;">
			<div class="ddxq_gg_flow_yes po_left_-50">
				<b>1</b> <span>申请退货</span>
			</div>
			<div class="ddxq_gg_flow_no po_left_190">
				<b>2</b> <span>等待供应商确认</span>
			</div>
			<div class="ddxq_gg_flow_no po_left_380">
				<b>3</b> <span>发货给供应商</span>
			</div>
			<div class="ddxq_gg_flow_no po_left_570">
				<b>4</b> <span>供应商确认收货</span>
			</div>
			<div class="ddxq_gg_flow_no po_left_760">
				<b>5</b> <span>微店网退款给消费者</span>
			</div>
		</div>
		<div class="margin_20">
			<div class="conter_right_xx_cz mt bor_cls">
				<div class="conter_right_xx_cz_t" style="background: #e7e7e7;">
					<ul>
						<li class="l_8 fw_b">商品信息</li>
						<li class="l_13 fw_b">单价（元）</li>
						<li class="l_3 fw_b">佣金（元）</li>
						<li class="l_3 fw_b">数量</li>
						<li class="l_3 fw_b">优惠方式（元）</li>
						<li class="l_3 fw_b">总金额（元）</li>
						<li class="l_3 fw_b">来源</li>
						<li class="l_12 fw_b">售后</li>
						<li class="l_7 fw_b" style="margin-left: 115px;">运费（元）</li>
						<li class="l_7 fw_b" style="margin-left: 70px;">状态</li>
					</ul>
				</div>
				<div class="blank2"></div>
				<table class="conter_right_xx_cz_table" style="border: 1px solid #e7e7e7;">
					<tbody>
						<tr class="mzh_4_17_gai">
							<td rowspan="7" style="padding: 0px; border: 0px; float: left; width: 100%;">
								<!-- 没有发货的时候只能整单退款 --> <c:choose>
									<c:when test="${details.orderState==1}">
										<input type="checkbox" id="checkAll" class="mzh_pjsd_text" disabled="disabled" checked="checked">
									</c:when>
									<c:otherwise>
										<input type="checkbox" id="checkAll" class="mzh_pjsd_text">
									</c:otherwise>
								</c:choose> <label for="checkAll" class="mzh_pjsd_label">全选</label>
								<div class="h40_title f12 fl ft_c9" style="margin-left: 10px; width: auto;">
									店铺：<span class="ft_c3">${details.supplierName }</span>
								</div> <c:if test="${details.supplierQQ!=null && details.supplierQQ!='' }">
									<a target=blank href=tencent://message/?uin=${details.supplierQQ}&Menu=yes><img border="0" SRC=http://wpa.qq.com/pa?p=1:88888:1 alt="点击联系" class="mzh_images"></a>
								</c:if> <span class="ddxq_gg_sldc_1_sh_1_span" style="float: right; line-height: 40px; margin-right: 20px; color: #3c3c3c;">订单号：<font id="supOrderID">${details.orderNum }</font></span>
							</td>
						</tr>
						<tr class="fl">
							<td class="conter_right_xx_cz_table_55" style="border-bottom: 0px; width: auto;"><c:forEach var="product" items="${details.proList }">
									<div class="conter_right_xx_cz_table_55_div" style="border: 0px; padding-top: 0px;">
										<!-- 没有发货的时候只能整单退款 -->
										<c:choose>
											<c:when test="${details.orderState==1}">
												<input value="${product.productOrderID }" name="subBox" type="checkbox" class="mzh_pjsd_text" disabled="disabled" checked="checked">
											</c:when>
											<c:otherwise>
												<c:choose>
													<c:when test="${product.refundState==-1||product.refundState==7}">
														<input value="${product.productOrderID }" name="subBox" type="checkbox" class="mzh_pjsd_text">
													</c:when>
													<c:otherwise>
														<span style="float: left; width: 23px; height: 10px;"></span>
													</c:otherwise>
												</c:choose>
											</c:otherwise>
										</c:choose>
										<div class="crxczt5d_0" style="margin-left: 10px;">
											<a href="http://www.${userinfo.weiID }.okwei.com/cpxq.html?pNo=${product.productId }"><img src="${product.productImg }"></a>
										</div>
										<div class="crxczt5d_10">
											<span style="width: 140px;">${product.productTitle }</span> <span style="width: 140px; color: #3c3c3c;">${product.property }</span>
										</div>
										<div class="crxczt5d_4" style="margin-left: 0px;"></div>
										<div class="crxczt5d_11" style="width: 90px;">
											<font style="width: 80px;">${product.price }</font>
										</div>
										<div class="crxczt5d_2" style="width: 80px;">
											<font name="commission">${product.commission }</font>
										</div>
										<div class="crxczt5d_2" style="width: 60px;">${product.count }</div>
										<div class="crxczt5d_10" style="width: 100px;">
											<font style="width: 100px;">${product.preferential }</font>
										</div>
										<div class="crxczt5d_10" style="width: 80px;">
											<font style="width: 80px;" name="totalamount">${product.totalamount }</font>
										</div>
										<div class="crxczt5d_10" style="width: 80px; margin: 0px;">
											<font class="mzh_4_17_jl" style="margin: 0px; width: 80px; overflow: hidden; white-space: nowrap; text-overflow: ellipsis" title="${product.sourceWeiName }">${product.sourceWeiName }</font>
										</div>
										<div class="crxczt5d_10" style="margin: 0px; width: 60px;">
											<font style="width: 60px; color: red;"> <c:choose>
													<c:when test="${product.refundState==0}">
													申请中
												</c:when>
													<c:when test="${product.refundState==1}">
													供应商同意退款
												</c:when>
													<c:when test="${product.refundState==2}">
													供应商不同意退款
												</c:when>
													<c:when test="${product.refundState==3}">
													微店网介入
												</c:when>
													<c:when test="${product.refundState==4}">
													微店网介入,支持退款
												</c:when>
													<c:when test="${product.refundState==5}">
													买家已发货
												</c:when>
													<c:when test="${product.refundState==6}">
													退款完成
												</c:when>
												</c:choose>
											</font>
										</div>
									</div>
								</c:forEach></td>
							<td class="conter_right_xx_cz_table_15" rowspan="2" style="border-bottom: 0px; vertical-align: inherit; width: 7%;">
								<div class="crxczt5d_cz_1" id="freight">${details.freight }</div> <!-- <div class="crxczt5d_cz_2">（快递）</div> -->
							</td>
							<td class="conter_right_xx_cz_table_15" rowspan="2" style="border-bottom: 0px; vertical-align: inherit; width: 7%;">
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
							<td rowspan="7" style="border: 0px; border-top: 1px solid #e7e7e7; padding: 0px;">
								<c:if test="${details.weiCoin>0 }">
								<div class="fl count_price f12 tr" style="background: #fff; margin-left: 0px; width: 1140px; padding-right: 14px;">
									使用微店网微金币 -<span class="f24 ft_red">￥${details.weiCoin }</span>
								</div>
								</c:if>
								<div class="fl count_price f12 tr" style="background: #fff; margin-left: 0px; width: 1140px; padding-right: 14px;">
									实付款：<span class="f24 ft_red">￥<label id="payment">${details.payment-details.weiCoin }</label></span>
								</div>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>

		<!-- 下单成功 -->
		<div class="ddxq_gg_ddzt">
			<dl class="ddxq_gg_tkly mt_20" style="border-bottom: 1px solid #f1f1f1; padding-bottom: 20px;">
				<dd>请选择退款方式：</dd>
				<dt>
					<c:choose>
						<c:when test="${details.orderState==1}">
							<select disabled="disabled" id="selRefundMode">
								<option selected="selected" value="1">仅退款</option>
								<option value="2">退款退货</option>
							</select>
						</c:when>
						<c:otherwise>
							<select id="selRefundMode">
								<option value="1">仅退款</option>
								<option value="2" selected="selected">退款退货</option>
							</select>
						</c:otherwise>
					</c:choose>

				</dt>
			</dl>
			<dl class="ddxq_gg_tkly mt_20">
				<dd>退款理由：</dd>
				<dt>
					<select onchange="seltkReason(this)">
						<option></option>
						<option>退运费</option>
						<option>收到商品有破损</option>
						<option>收到商品与描述不符</option>
						<option>未收到货</option>
					</select>
					<div class="clear"></div>
					<div class="blank1"></div>
					<textarea id="tkReason"></textarea>
				</dt>
			</dl>
			<dl class="ddxq_gg_tkly mt_20">
				<dd>
					上传图片<span class="f12" style="color: #aaa;">（非必填）</span>
				</dd>
				<dt>
					<div class="fl" id="divImage"></div>
					<div class="fl">
						<input type="file" id="uploadImg" name="uploadImg" style="background-position: center;" />
						<!-- <img src="../statics/images/sctp_3_28.jpg" class="ddxq_gg_tkly_dt_img_1" style="cursor: pointer;"> -->
					</div>
				</dt>

			</dl>
			<dl class="ddxq_gg_tkly mt_20">
				<dd style="line-height: 42px;">退款金额：</dd>
				<dt>
					<input type="text" class="ddxq_gg_text" id="tkprice"> <span class="ddxq_gg_tkly_text">最多可退 <font>￥<label id="maxPrice">0.0</label></font></span> <input type="hidden" id="maxAmount" value="${details.maxPrice-details.weiCoin }">
					
				</dt>
			</dl>
			<dl class="ddxq_gg_tkly mt_20">
				<dd></dd>
				<dt>
					<c:if test="${details.cashCouponflag}">
						提示：购买时，您使用了微店代金券，卖家同意退款后，系统会原路退还${details.totalPrice}元的现金至您的支付账户，${details.cashCoupon}元代金券将不会退回您的账户
					</c:if>
					<c:if test="${details.weiCoin>0 }">
						提示：卖家同意退款后，系统会原路退还${details.maxPrice-details.weiCoin }元的现金至您的支付账户，已使用的${details.weiCoin}微金币已失效，不再返还						
					</c:if>
				</dt>
			</dl>
			<dl class="ddxq_gg_tkly mt_20">
				<dd></dd>
				<dt>
					<span id="submitRefund" class="jbzl_dl_qrdz mr_20" ><a href="javascript:void(0);">确认</a></span>
					<!-- <span class="jbzl_dl_qrdz" style="width: 65px; background: #ccc;">取消</span> -->
				</dt>
			</dl>
		</div>


		<!-- 收货地址-留言-订单信息-产品清单 -->
		<div class="ddxq_gg_sldc">
			<div class="ddxq_gg_sldc_1 margin_20">

				<!-- 收货地址 -->
				<div class="ddxq_gg_sldc_1_sh">
					<h6 class="mb_10">收货地址</h6>
					<div class="ddxq_gg_sldc_1_sh_1">
						<span class="ddxq_gg_sldc_1_sh_1_span">收货人：<font>${details.receivingName}</font></span> <span class="ddxq_gg_sldc_1_sh_1_span"> 联系电话 ：<font>${details.receivingPhone}</font></span>
					</div>
					<div class="ddxq_gg_sldc_1_sh_1">
						<span class="ddxq_gg_sldc_1_sh_1_span">收货地址：<font>${details.receivingAddress}</font></span>
					</div>
					<h6 class="mb_10 mt_20">留言</h6>
					<span class="ddxq_gg_sldc_1_sh_1_span">${details.message }</span>
					<h6 class="mb_10 mt_20">订单信息</h6>
					<div class="ddxq_gg_sldc_1_sh_1">
						<span class="ddxq_gg_sldc_1_sh_1_span">订单号：<font>${details.orderNum }</font></span> <span class="ddxq_gg_sldc_1_sh_1_span"> 支付方式：<font>${details.payWay }</font></span> <span class="ddxq_gg_sldc_1_sh_1_span"> 参与促销：<font>无促销</font></span>
					</div>

				</div>

			</div>
		</div>

	</div>
</body>
</html>