<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="java.util.ResourceBundle"%>
<%
	String productdomain = ResourceBundle.getBundle("domain").getString("productdomain");
    String mydomain = ResourceBundle.getBundle("domain").getString("mydomain");
	String sellerdomain = ResourceBundle.getBundle("domain").getString("sellerdomain");
	String okweidomain = ResourceBundle.getBundle("domain").getString("okweidomain"); 
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改订单价格</title>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/statics/js/seller/orderdetails.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/statics/js/seller/orderpublic.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/statics/js/seller/modifyprice.js"></script>
</head>
<body>
	<form action="updateConfirm" method="post">
		<div class="weiz_iam f12 fm_song">
			当前位置：<a href="/maininfo/maininfo">微店中心</a>&gt; <a
				href="/seller/buylist">订单列表</a>&gt;<span>修改价格</span>
		</div>
		<div class="update_price fl bg_white">
			<div class="updprc fl">
				<div class="price_yis fl">
					<span class="fw_b f14 ft_c3">订单原价</span> <span class="f14 ft_c3">（不包含运费）:</span>
					<span class="ft_c3 fm_var">${details.totalPrice - details.postPrice }元</span>
				</div>
				<div class="fl price_title">
					<ul>
						<li class="w297 tc">商品信息</li>
						<li class="w149 tc">单价(元)</li>
						<li class="w149 tc">数量</li>
						<li class="w359 tc">涨价或折扣<img onmouseover="tips(this)"
							name="负数代表优惠折扣"
							src="<%=request.getContextPath() %>/statics/pic/icon_wensi.png"></li>
						<li class="w189 tc">邮费（元）</li>
					</ul>
				</div>
				<div class="bgm_imgs fl">
					<div class="shopp_pls">
						<table>
							<tbody>
								<tr>
									<td>
										<div class="fl mzh_100 mzh_bo">
											<c:forEach var="pro" items="${details.proList }"
												varStatus="status">
												<input type="hidden" class="jg" name="${pro.count}" value="${pro.commission}"/>
												<div class="img_onetue1 fl">
													<div class="img_left fl">
														<a
															href="<%=productdomain %>/product?pid=${pro.productId}" target="_blank"><img
															src="${pro.productImg }"></a>
													</div>
													<div class="yes_font1 fl">
														<span><a
															href="<%=productdomain %>/product?pid=${pro.productId}" target="_blank"
															class="ft_c9">${pro.productTitle }</a></span> <font>${pro.property }</font>
													</div>
												</div>				
												<div class="fm_var ft_c3 price_ybai tc fl">${pro.price }</div>
												<div class="fm_var ft_c3 price_ybai tc fl">${pro.count }</div>
												<div class=" xiugaiprc fl">
													<ul>
														<li><input type="text" class="btn_h30 w82 zhekou"
															value="" maxlength="12" /> 折 = &nbsp;</li>
														<li><input type="text" class="btn_h30 w82 youhui"
															value="" maxlength="12" /> 元</li>
													</ul>
													<input type="hidden" value="${pro.productOrderid }" name="" />
												</div>
											</c:forEach>
										</div>
									</td>
									<td class="w189" rowspan="2">
										<div class="fl yfei_price">
											<ul>
												<li class="f12 fm_song ft_c6">快递</li>
												<li><input id="kuaidi" type="text"
													class="btn_h30 w82 kuaidi" value="${details.postPrice }"
													maxlength="9" /> 元</li>
												<li class="f12 fm_song ft_c6 ft_sihui">直接输入邮费金额</li>
											</ul>
										</div>
									</td>
								</tr>
							</tbody>
						</table>
					</div>

				</div>
				<div class="count_money fl">
					<ul>
						<li class="f14 fm_song ft_c3">收货地址：<span>广东省 深圳市 罗湖区
								国威路68号互联网产业园2栋5层（2501）</span></li>
						<li class="f14 fm_song ft_c3">买家实付： <span class="fm_var f16">
								<span id="original">${details.totalPrice - details.postPrice }</span>+
								<span id="freight">${ details.postPrice }</span> <span
								id="discount">+ 0</span> = <span id="totalprice" class="ft_red">${details.totalPrice }</span>
						</span>元
						</li>
						<li class="f12 fm_song ft_sihui">买家实付：原价+运费+涨价或折扣</li>
						<li class="f12 fm_song ft_sihui">店铺优惠**元已均摊到每个宝贝，并计算在单个宝贝的折扣中</li>
					</ul>
				</div>
				<div class="two_bottom">
					<div class="fr btom_sihuis2">
						<a href="javascript:void(0);" onclick="cancel()"
							class="dis_b bg_quxiaos bor_rad4 f16 fm_wei ft_c6">取消</a>
					</div>
					<div class="fr btom_sihuis1">
						<input type="button" onclick="submitajax('${details.orderNo}')"
							value="确认" class="btn_ply36_pre bor_rad4 shou">
					</div>
				</div>
			</div>

		</div>
	</form>
</body>
</html>