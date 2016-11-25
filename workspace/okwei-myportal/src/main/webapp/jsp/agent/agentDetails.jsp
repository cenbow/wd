<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="java.util.ResourceBundle"%>
<%
    String paydomain = ResourceBundle.getBundle("domain").getString("paydomain"); 
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>代理商申请资料</title>
<script type="text/javascript" src="/statics/js/agent/agentDetails.js"></script>
<style>
.jbzl_dl {
	display: block;
	margin: 0px;
}
div.xubox_page{padding: 0px;}
</style>
</head>
<body>
	<input id="paydomain" type="hidden" value="<%=paydomain %>" />
	<div class="fl conter_right bg_white bor_si">
		<!-- 选项卡 -->
		<div class="gygl_xxk_t f16 ndfxs_1-2_border">
			<ul class="tab_pc f16">
				<li class="now" name="name_xxk"><a href="#">审核代理商资料</a><i style="left: 43%;"></i></li>
			</ul>
		</div>
		<!-- 基本资料 -->
		<div class="jbzl">
			<div class="fl mzh_width_100 f16 fw_b bor_bo line_42" style="text-indent: 20px;">基本资料：</div>
			<div class="blank1"></div>
			<dl class="jbzl_dl f14">
				<dd>姓名：</dd>
				<dt>${model.linkname }</dt>
			</dl>
			<dl class="jbzl_dl f14">
				<dd>联系电话：</dd>
				<dt>${model.phone }</dt>
			</dl>
			<dl class="jbzl_dl f14">
				<dd>
					<span class="f14">地址</span>：
				</dd>
				<dt>${model.address }</dt>
			</dl>
			<dl class="jbzl_dl f14">
				<dd>
					<span class="f14">代理地区</span>：
				</dd>
				<dt>${model.agentAreas[0].areaName }-${model.agentAreas[0].areas[0].areaName }</dt>
			</dl>
			<dl class="jbzl_dl f14">
				<dd>
					<span class="f14">营业执照</span>：
				</dd>
				<dt>
					<c:forEach var="img" items="${model.bigImgs }">
						<div class="jbzl_tx mt_10">
							<img src="${img }" class="showimgs" width="85" height="85">
						</div>
					</c:forEach>
				</dt>
			</dl>
			<dl class="jbzl_dl f14">
				<dd>
					<span class="f14 fw_b">代理优势描述</span>：
				</dd>
				<dt>
					<span class="fl ft_c6 mt_10" style="width: 500px; line-height: 20px;"> ${model.intro } </span>
				</dt>
			</dl>
			<dl class="jbzl_dl f14">
				<dd>
					<span class="f14 fw_b">申请代理</span>：
				</dd>
				<dt>
					<p>
						<c:forEach var="dem" items="${model.demands }">
					${dem.investmentDemandName }
				</c:forEach>
					</p>
					<c:forEach var="already" items="${model.demands }">
						<p class="ft_c9" style="margin-top: -10px;">已代理：${already.investmentDemandName }</p>
					</c:forEach>
				</dt>
			</dl>
			<dl class="jbzl_dl f14">
				<dd>
					<span class="f14 fw_b">跟进认证员</span>：
				</dd>
				<dt>
					<p>认证员:${model.developmentName } (${model.developmentWeiId }）</p>
					<p class="ft_c9" style="margin-top: -10px;">手机号:${model.developmentPhone }</p>
				</dt>
			</dl>
			<dl class="jbzl_dl f14">
				<dd>
					<span class="f14 fw_b">认证员跟进记录</span>：
				</dd>
				<dt>
					<c:forEach var="ver" items="${verlist }">
						<p>${ver.createTime }-${ver.remaks }</p>
					</c:forEach>
				</dt>
			</dl>
			<div class="fl mzh_width_100 tc ft_c9 f14 mt_20 mb_20">
				<span class="ft_red">注意:</span> 审核通过后，申请者将成为代理商！代理商将看到代理价且其所在市的所有落地店自动分配给该代理商
			</div>
			<dl class="jbzl_dl f14">
				<dd></dd>
				<dt>
					<div class="jbzl_dl_news"  onclick="nopass('审核不通过原因','win_div_1','520px','330px')">审核不通过</div>
					<div class="jbzl_dl_bc ml_48"  id="pass" onclick="pass()">审核通过</div>
				</dt>
			</dl>
			<div class="blank"></div> 
		</div>
	</div>

	<!-- 审核不通过原因 -->
	<div class="updata_tixian" style="display: none;" id="win_div_1">
		<textarea class="fl mzh_100 f14" style="height: 100px;" id="nopass" placeholder="请简述审核不通过的原因"></textarea>
	</div>

	<!-- 审核通过 -1 -->
	<div class="updata_tixian f16 tc" style="display: none;" id="win_div_2">
		<div class="fl mzh_width_100 tc mt_5">代理商审核通过！</div>
		<div class="fl mzh_width_100 tc mt_5">代理商由微店主：<span id="vname"></span> 招募而来</div>
		<div class="fl mzh_width_100 tc mt_5">
			悬赏金额：<span class="ft_red">￥<span id="payReward"></span></span>
		</div>
	</div>

	<!-- 审核通过 -2 -->
	<div class="updata_tixian f16 tc" style="display: none;" id="win_div_3">
		<div class="fl mzh_width_100 tc mt_47">该地区已有代理商，无法审核通过！</div>
	</div>

	<!-- 审核通过 -3 -->
	<div class="updata_tixian f16 tc" style="display: none;" id="win_div_4">
		<div class="fl mzh_width_100 tc mt_5">您有3笔悬赏金额未支付，请先支付</div>
		<div class="fl mzh_width_100 tc mt_5">悬赏金额。让更多的认证员为您服务。</div>
	</div>

	<!-- 错误信息 -->
	<div class="updata_tixian f16 tc" style="display: none;" id="win_div_5">
		<div class="fl mzh_width_100 tc mt_47" id="errorinfo"></div>
	</div>
<div id="tong" class="hide layer_pageContent" style="display: none; "><img src="" style="max-width:600px;max-height:600px;" /></div>
	<input type="hidden" id="applyID" value="${applyID }" />
</body>
</html>