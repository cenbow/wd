<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ResourceBundle"%>
<% 
String mydomain = ResourceBundle.getBundle("domain").getString("mydomain");
String walletdomain = ResourceBundle.getBundle("domain").getString("walletdomain");
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
<style type="text/css">
a.plan{color: #3869cf;}
a.plan:hover{color: #f10;}
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
								<h2 class="fl ">恭喜您！付款成功！</h2>
							</dt>
							<dt class="fl">
								<a href="<%=mydomain %>/order/buylist" class="fl mzh_width_100 mt_5 plan">点此查看本笔交易详情</a>
								<h4 class="fl mr_10 line_22 mt_20 mzh_width_100">
									如果您还有未付款的订单，<a href="<%=mydomain %>/order/buylist" class="color_999 plan">点此查看并付款</a>；
								</h4>
								<h4 class="fl mr_10 line_22">
									返回我的钱包，<a href="<%=walletdomain %>/walletMgt/index" class="color_999 plan">进行钱包管理</a>。
								</h4>
							</dt>
						</dl>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>