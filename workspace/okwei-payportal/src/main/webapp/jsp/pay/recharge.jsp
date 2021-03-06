<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page import="java.util.ResourceBundle"%>
<%
    String mydomain = ResourceBundle.getBundle("domain").getString("mydomain");
    String walletdomain = ResourceBundle.getBundle("domain").getString("walletdomain");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>微钱包充值</title>
<link rel="stylesheet" href="/statics/css/glbdy.css" />
<link rel="stylesheet" href="/statics/css/index.css" />
<link rel="stylesheet" href="/statics/css/mzh_dd.css" />
<link rel="stylesheet" href="/statics/css/mzh_ibs.css" />
<script type="text/javascript" src="/statics/js/pay/recharge.js"></script>
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
			<div class="bor_si fl bg_white mzh_width_100">
				<div class="fl mzh_width_100 mt_20">
					<span class="fl ml_30 f14">充值：</span> <input class="fl" type="text" value="100" id="price" /> <span class="fl ml_10 f14">元</span>
					<h6 class="fl ml_20" style="color: #999;">(即时到账)</h6>
					<a href="http://www.okwei.com/help/us#Cjwti" class="fl ml_20 plan">常见问题？</a>
				</div>
				<div class="fl mt_10 mb_30 ml_20" style="color: #999;">微店钱包目前仅支持快捷支付充值，信用卡除外，不允许从事无真实交易背景的虚假交易，套现和洗钱等禁止的交易行为，否则充值款项将不能体现。</div>
			</div>
			<div class="blank2"></div>
			<div class="bor_si fl bg_white mzh_width_100">
				<div class="Binding f14 fl" id="kuaijie">

					<ul class="bg_F0F0F0">
						<li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label for="mzh_kjzf">快捷支付</label></li>
					</ul>
					<dl class="jbzl_dl f14 bor_si" id="bor_si">
						<ul>

							<c:forEach var="back" items="${list }">
								<li><input class="fl mt_14 mr_5" type="radio" name="mzh_zf_zf" id="backid${back.id }" value="${back.id }"> <label class="fl" for="backid${back.id }">************ <c:set var="date" value="${back.banckCard}" /> <c:out value="${fn:substring(date,12,16)}" /></label> <span class="fl ml_20">储蓄卡</span>
									<div class="fl dis_n" style="display: none;">
										<img src="/statics/images/u60.png" class="fl mt_5 ml_10">
										<div class="fl ml_20">
											支付：<span class="color_red f14">${surplus }</span>元
										</div>
									</div></li>
							</c:forEach>

							<c:if test="${empty list  }">
								<li>您还没有绑定银行卡！ <a class="plan" href="<%=walletdomain%>/bankCardMgt/bankCard">马上绑定</a></li>
							</c:if>
						</ul>
					</dl>
				</div>
				<div class="fl mzh_width_100">
					<input name="button" type="submit" class="mzh_xiayibu ml_40 mb_30" value="下一步" onclick="next()" />
				</div>



			</div>
		</div>
		<div class="fl tc mzh_width_100 mt_20">深圳市云商微店网络技术有限公司版权所有 经营许可证：粤ICP备10203293号-3 - 增值电信业务经营许可证：粤B2-20130803</div>
	</div>
	<div class="updata_tixian layer_pageContent" style="display: none;" id="win_div_3">
		<div class="updata_tixian">
			<div class="fl mzh_width_100">
				<ul class="p10">
					<li class="f14">支付完成前，请不要关闭该窗口。</li>
					<li class="f14 mt_10">支付完成后，请根据您的情况点击下面的按钮：</li>
					<li class="fl mt_47"><input id="butzfwc" name="button" type="submit" class="mzh_xiayibu " style="padding: 0px 20px;" value="支付完成">
						<div class="dis_b ml_20 fl  btn_hui28_pre shou">支付遇到问题，重新支付</div></li>
				</ul>
			</div>
		</div>
	</div>
		<form id="llpay" action="/llpay/payrequest" target="_blank">
		<input type="hidden" name="orderNo" value="">
		<input type="hidden" name="cardID" value="">
	</form>
</body>
</html>