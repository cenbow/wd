<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page import="java.util.ResourceBundle"%>
<%
	String mydomain = ResourceBundle.getBundle("domain").getString(
			"mydomain");
	String walletdomain = ResourceBundle.getBundle("domain").getString(
			"walletdomain");
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>支付成功</title>
<link rel="stylesheet" href="/statics/css/glbdy.css" />
<link rel="stylesheet" href="/statics/css/index.css" />
<link rel="stylesheet" href="/statics/css/mzh_dd.css" />
<link rel="stylesheet" href="/statics/css/mzh_ibs.css" />
<script type="text/javascript" src="<%=path%>/statics/js/share.js?v=2"></script>
<style type="text/css">
a.plan {
	color: #3869cf;
}

a.plan:hover {
	color: #f10;
}
</style>
</head>
<body>
	<div class="content mar_au">
		<!--<div class="weiz_iam f12 fm_song">当前位置：<a href="#">供应管理</a>><span>IBS支付收银台</span></div>-->
		<div class="fr conter_right mzh_width_100">
			<div class="blank2"></div>
			<div class="bor_si fl bg_white mzh_width_100">
				<div class="mlf_50">
					<div class="fl mzh_width_100 pt60 mb_30" style="min-height: 200px;">
						<dl class="fl ml_270">
							<dd>
								<img src="/statics/images/d-ico1.png" width="28" class="fl mr_10">
							</dd>
							<dt class="fl">
								<h2 class="fl fb mt_5">恭喜您！付款成功！</h2>
							</dt>
							<dt class="fl">
								<a href="<%=mydomain%>/order/buylist" class="fl mzh_width_100 mt_5 plan">点此查看本笔交易详情</a>
								<h4 class="fl mr_10 line_22 mt_20 mzh_width_100 ">
									如果您还有未付款的订单，<a href="<%=mydomain%>/order/buylist" class="color_999 plan">点此查看并付款</a>；
								</h4>
								<h4 class="fl mr_10 line_22 mzh_width_100">
									返回我的钱包，<a href="<%=walletdomain%>/walletMgt/index" class="color_999 plan">进行钱包管理</a>。
								</h4>
							</dt>
							<c:if test="${vo.isHaveTicket==1}">
								<dt class="fl mt_20" style="padding:10px 40px 20px;background:#f1ffe7;">
									<div class="fl mzh_width_100 f14 mt_5">此单交易返还
										<span class="ft_red" >${vo.ticketAmount}</span>元购物券，将于订单交易完成后到账！
									</div>
									<div class="fl mzh_width_100 f14 mt_5">已累计获得
										<span class="ft_red" >${vo.ticketCount}</span>元购物券，最高可返5000.00元购物券！
									</div>
								</dt>
							</c:if>
							
						</dl>
						<c:if test="${state==1 && vo.firstShop==1}">
							<div class="fl ml_270 bor_to mt_30 pb5" style="width: 500px;">
								<h2 class="ft_red fl line_22 mt_47" style="font-weight: normal;">
									<c:if test="${state==1 && vo.firstShop==1}"> 
         							恭喜您成为落地店！ 
									</c:if>
								</h2>
								<ul>
									<li class="fl mzh_width_100 f16 mt_20">落地店权益</li>
									<li class="fl mzh_width_100 f14 mt_5">· 平台速度为您安排发货，请留意查收；</li>
									<li class="fl mzh_width_100 f14 mt_5">· 您本次获得<span class="ft_red">${vo.coinAmount}</span>元的<a href="#" class="ft_lan">现金券</a>，可用于下次进货；
									</li>
									<li class="fl mzh_width_100 f14 mt_5">· 顾客购买订单将优先自动匹配落地店，您可从中获取差价盈利。</li>

								</ul>
							</div>
						</c:if>
						
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	// 分享
	function shareTo(type, title, proID, weiNo, form) {
		var url = window.basePath + "/product?pid=" + proID + "&f=" + from
				+ "&w=" + weiNo;
		title += "【微店网】";
		if (type == "kj") {
			ShareToQzone(title, url, "");
		} else if (type == "tx") {
			ShareToTencent(title, url, "");
		} else if (type == "xl") {
			ShareToSina(title, url, "");
		}
		// 修改分享数量
		$.ajax({
			url : "/product/share",
			type : "post",
			data : {
				proID : proID
			},
			success : function(data) {
				if (data.msg == "1") {
					var count = parseInt($("#shareCount").html());
					count += 1;
					$("#shareCount").html(count);
				}
			}
		});
	}
</script>
</html>