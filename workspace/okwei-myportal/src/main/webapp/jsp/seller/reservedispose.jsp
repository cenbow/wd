<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.ResourceBundle"%>
<%
    String mydomain = ResourceBundle.getBundle("domain").getString("mydomain");
	String sellerdomain = ResourceBundle.getBundle("domain").getString("sellerdomain");
	String okweidomain = ResourceBundle.getBundle("domain").getString("okweidomain"); 
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>铺货单处理</title>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/statics/js/seller/orderdispose.js"></script>
<script language="JavaScript" type="text/javascript"
	src="<%=request.getContextPath()%>/statics/js/My97/WdatePicker.js"></script>

</head>

<body>
	<form action="reservedConfirm?v=1" method="post" accept-charset="utf-8"
		onsubmit="document.charset='utf-8';">
		<input type="hidden" id="supplyOrderid" name="supplyOrderid"
			value="${details.orderNo }" />
		<div class="weiz_iam f12 fm_song">
			当前位置： <a href="/maininfo/maininfo">微店中心</a>&gt;  <a
				href="/seller/reservelist">铺货列表</a>&gt; <a
				href="/seller/delivery?orderNo=${details.orderNo }">
				<c:choose>
					<c:when test="${details.orderShowType==1}">
						零售订单
					</c:when>
					<c:when test="${details.orderShowType==2}">
						批发订单
					</c:when>
					<c:when test="${details.orderShowType==3}">
						铺货订单
					</c:when>
				</c:choose> <span class="fw_100">${details.orderNo }</span>
			</a>&gt; <span>确认订单</span>
		</div>
		<div class="ddxq_gg">

			<!-- 收货地址-留言-订单信息-产品清单 -->
			<div class="ddxq_gg_sldc">
				<div class="ddxq_gg_sldc_1 margin_20">
					<!-- 收货地址 -->
					<div class="ddxq_gg_sldc_1_sh">
						<h6 class="mb_10">收货地址</h6>
						<div class="ddxq_gg_sldc_1_sh_1">
							<span class="ddxq_gg_sldc_1_sh_1_span">收货人：<font>${details.reciverName }</font></span>
							<span class="ddxq_gg_sldc_1_sh_1_span" style="margin-left:50px;"> 联系电话 ：<font>${details.reciverPhoneNumber }</font></span>
						</div>
						<div class="ddxq_gg_sldc_1_sh_1">
							<span class="ddxq_gg_sldc_1_sh_1_span">收货地址：<font>${details.reciverAddress }</font></span>
						</div>
						<h6 class="mb_10 mt_20">留言</h6>
						<span class="ddxq_gg_sldc_1_sh_1_span">${details.clientMsg }</span>
						<div class="blank1"></div>
						<!-- 第一种 -->
						<div class="mzh_radio_xxk">
							<div class="blank1"></div>
							<div class="mzh_radio_xxk_1">
								<input type="radio" name="editPriceType" id="mzh_bxgcpjg"
									value="0" checked="checked"> <label for="mzh_bxgcpjg">不修改产品价格</label>
							</div>
							<div class="mzh_radio_xxk_1">
								<input type="radio" name="editPriceType" id="mzh_xgcpjg"
									value="1"> <label for="mzh_xgcpjg">修改产品单价</label>
							</div>
							<div class="mzh_radio_xxk_1">
								<input type="radio" name="editPriceType" id="mzh_xgcpzg"
									value="2"> <label for="mzh_xgcpzg">修改产品总价</label>
							</div>
						</div>
						<div class="conter_right_xx_cz mt bor_cls">
							<div class="conter_right_xx_cz_t mt_20"
								style="background: #e7e7e7;">
								<ul>
									<li class="l_8 fw_b">商品信息</li>
									<li class="l_13 fw_b">单价（元）</li>
									<li class="l_12 fw_b">数量</li>
									<li class="l_3 fw_b">优惠方式（元）</li>
									<li class="l_12 fw_b">成交微店</li>
									<li class="l_7 fw_b">付款金额</li>
									<li class="l_7 fw_b" style="margin-left: 180px;">运费（元）</li>
									<li class="l_7 fw_b" style="margin-left: 70px;">状态</li>
								</ul>
							</div>
							<table class="conter_right_xx_cz_table"
								style="border: 1px solid #e7e7e7;">
								<tbody>
									<tr class="fl">
										<td class="conter_right_xx_cz_table_55"
											style="border-bottom: 0px; width: auto;"><c:forEach
												var="pro" items="${details.proList }" varStatus="status">
												<div class="conter_right_xx_cz_table_55_div"
													style="${status.index==0?'border: 0px; padding-top: 0px;':''}">
													<div class="crxczt5d_0">
														<a
															href="http://www.${userinfo.weiID }.<%=okweidomain %>/cpxq.html?pNo=${pro.productId}"><img
															src="${pro.productImg }"></a>
													</div>
													<div class="crxczt5d_10">
														<span style="width: 140px;">${pro.productTitle }</span> <span
															style="width: 140px; color: #3c3c3c;">颜色：白色 尺码：均码</span>
													</div>
													<div class="crxczt5d_4" style="margin-left: 0px;"></div>
													<div class="crxczt5d_2">
														<font class="yuanprice" style="display: none">原价${pro.price }</font>
														<font class="upprice" data-original="${pro.price }">${pro.price }</font>
														<input type="text" name="products[${status.index }].price"
															value="${pro.price }" maxlength="12"
															class="mzh_4_20_text ml_20 fl upprice_input"
															style="display: none" value="" /> <input type="hidden"
															name="products[${status.index }].productOrderId"
															value="${pro.productOrderid }" />
													</div>
													<div class="crxczt5d_2 procount" style="width: 80px;">${pro.count }</div>
													<div class="crxczt5d_10"
														style="width: 100px; min-height: 30px;">
														<font style="width: 100px;">${pro.favorable }</font>
													</div>
													<div class="crxczt5d_10"
														style="margin: 0px; min-height: 30px;">
														<font class="mzh_4_17_jl" style="margin: 0px;">${pro.sourceName }</font>
													</div>
													<div class="crxczt5d_10" style="width: 100px;">
														<font style="width: 100px;" class="allprice">${pro.sumPrice }</font>
													</div>
												</div>
											</c:forEach></td>
										<td class="conter_right_xx_cz_table_15" rowspan="2"
											style="border-bottom: 0px; vertical-align: inherit; width: 7%;">
											<div class="crxczt5d_cz_1">
												<font id="editPostPrice"
													data-original="${details.postPrice }">${details.postPrice }</font>
												<input type="text" value="${details.postPrice }"
													maxlength="12" name="editPostPrice"
													class="mzh_4_20_text ml_20 fl" style="display: none;">
											</div>
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
												实付款：<span class="f24 ft_red">￥</span> <span
													class="f24 ft_red" id="editTotalPrice"
													data-original="${details.totalPrice }">${details.totalPrice }</span>
												<input type="text" maxlength="12"
													class="f24 ft_red mzh_4_20_text" name="editTotalPrice"
													value="${details.totalPrice }"
													style="width: 200px; display: none;">
											</div>
										</td>
									</tr>
								</tbody>
							</table>
						</div>

						<div class="clear"></div>
						<div class="blank1"></div>

						<div class="mzh_fkfs">
							<div class="blank1"></div>
							<div class="mzh_fkfs_fs">
								<b>付款方式</b>
								<div class="mzh_fkfs_fs_fkfs">
									<div class="mzh_fkfs_fs_fkfs_qe">
										<input type="radio" id="mzh_quane" name="payType" value="0"
											checked="checked" /> <label for="mzh_quane">全额支付</label>
									</div>
									<div class="mzh_fkfs_fs_fkfs_qe">
										<input type="radio" id="mzh_baifenbi" name="payType" value="1" />
										<label for="mzh_baifenbi">预付定金百分比</label> <input type="text"
											class="mzh_4_20_text fl ml_5 mr_5" name="firstPercent"
											value="" /> <label>%</label>
									</div>
									<div class="mzh_fkfs_fs_fkfs_qe">
										<input type="radio" id="mzh_zhiding" name="payType" value="2" />
										<label for="mzh_zhiding">自定义定金</label> <input type="text"
											class="mzh_4_20_text fl ml_5 mr_5" name="firstPrice" value="" />
										<label>元</label>
									</div>
								</div>
							</div>
							<div class="blank2"></div>
							<div class="mzh_fkfs_fs" id="weikuan" style="display: none;">
								<b>尾款支付方式</b>
								<div class="mzh_fkfs_fs_fkfs">
									<div class="mzh_fkfs_fs_fkfs_qe">
										<input type="radio" id="mzh_qian" name="tailPayType" value="0"
											checked="checked" /><label for="mzh_qian">发货前支付尾款</label>
									</div>
									<div class="mzh_fkfs_fs_fkfs_qe">
										<input type="radio" id="mzh_hou" name="tailPayType" value="1"><label
											for="mzh_hou">发货后支付尾款</label>
									</div>
								</div>
							</div>
							<div class="blank2"></div>
							<div class="mzh_fkfs_fs">
								<b>预计发货时间</b>
								<div class="mzh_fkfs_fs_fkfs">
									<div class="mzh_fkfs_fs_fkfs_qe">
										<label for="mzh_qian">日期：</label> <input type="text"
											name="preDeliverTime" id="preDeliverTime"
											class="mzh_4_20_text fl mr_5" style="width: 170px;"
											onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" /> <label
											class="col_aaa"></label>
									</div>
								</div>
							</div>
							<div class="blank1"></div>
							<div class="mzh_fkfs_fs">
								<b>给采购商一个贴心的问候</b>
								<div class="mzh_fkfs_fs_fkfs">
									<textarea name="message" class="jbzl_dl_textarea"
										style="height: 84px;"></textarea>
								</div>
							</div>
						</div>
					</div>

				</div>
			</div>

			<div class="blank1"></div>
			<input class="mzh_4_20_qrdd" type="submit" value="确认订单" /> 
			<a href="javascript:void(0);" class="mzh_4_20_jjdd"  onclick="refused(${details.orderNo })">拒绝订单</a>
			
			<div class="blank1"></div>
		</div>
	</form>
</body>
</html>