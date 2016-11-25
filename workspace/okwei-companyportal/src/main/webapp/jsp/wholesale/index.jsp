<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>批发市场,全国批发市场最集中的地方,微店网(okwei.com)——免费开微店，认证原创微店官方平台</title>
<meta name="description" id="description" content="全国批发市场最集中的地方，批发市场商户免费开微店，全国批发市场商户都在这里开通了微店。" />
<meta name="keywords" id="keywords" content="微店，微店网，免费开通微店，批发，批发市场，批发市场微店。" />
<script>
	$(function() {
		$('.leftscr a').each(function() {
			$(this).click(function() {
				$('.leftscr a').attr('class', '');
				$(this).attr('class', 'nows')
			})
		})
		$(".cpimgs ul li:nth-child(5n)").css("margin-right", "0px")
		$(".money").hover(function() {
			$(this).siblings(".ladder-price").css({
				"visibility" : "visible",
				"opacity" : "1"
			})
		}, function() {
			$(this).siblings(".ladder-price").css({
				"visibility" : "hidden",
				"opacity" : "0"
			})
		})
		$('.imgps a').height($('.imgps a').width() * 0.75);
		
		 $(".imgps img").lazyload({
             placeholder: "../statics/images/206_155.png",
             effect: "fadeIn"
         });
	})
</script>
<style>
.scpf_market .cpimgs ul li {
	height: 290px;
}
</style>
</head>
<body>
	<div class="w line-b fl">
		<div class="line3 mar1200 ">
			<a href="http://www.okwei.com">首页 </a> > <a href="javascript:;">批发市场 </a>
		</div>
	</div>
	<div class="w line-b  pt20 pb20 bg-w fl">
		<div class="mar1200">
			<div class="fl lefies1" style="width:100%;border:none;">
				<div class="widbot_yes">按区域查找批发市场</div>
				<div class="ue_iyes mt10" style="width:100%;">
					<ul>
						<li><a href="list"> 全国（<span>${wholesale.count }</span>）
						</a></li>
						<c:forEach var="area" items="${wholesale.areaList }">
							<li><a href="list?code=${area.id }">${area.key }（<span>${area.value }</span>）
							</a></li>
						</c:forEach>
					</ul>
				</div>
			</div>
			<%-- <div class="fl lefies2">
				<div class="widbot_no">按行业查找批发市场</div>
				<div class="ue_iyes mt10">
					<ul>
						<c:forEach var="trade" items="${wholesale.tradeList }">
							<li><a href="list?id=${trade.id }"> ${trade.key }（<span>${trade.value }</span>）
							</a></li>
						</c:forEach>
					</ul>
				</div>
			</div> --%>
		</div>
	</div>
	<div class="mar1200 scpf_market">
		<!-- 筛选 -->
		<!-- 商品排列  style=" visibility:visible;opacity:1"  list_li-->
		<c:forEach var="model" items="${list }" varStatus="vs">
			<div class="lcef fl w mt20">
				<span class="block fl sopc1">${vs.index+1 }F</span><span class="f16 cr_c4 ml5 fl block sopc2">${model.bigClass }</span>
				<!--<span class="fr sopc3 f12">已有<font></font>家批发市场入驻 </span> -->
			</div>
			<div class="w fl cpimgs mt15">
				<ul>
					<li class="pro_liibs">
						<div class="<c:choose>
								<c:when test="${fn:length(model.leftList) == 2 }">ers_pailies</c:when>
								<c:when test="${fn:length(model.leftList) == 4 }">sis_pailies</c:when>
								<c:when test="${fn:length(model.leftList) == 6 }">yi_pailies</c:when>
								<c:otherwise>san_pailies</c:otherwise>
							</c:choose>">
							<ul>
								<c:forEach var="left" items="${model.leftList }">
									<%-- <li><a href="${left.linkUrl }"><img src="${left.img }" /></a></li> --%>
									<li><img src="${left.img }" /></li>
								</c:forEach>
							</ul>
						</div>
					</li>
					<c:forEach var="main" items="${model.mainListvo }">
						<li class="pro_li">
							<div class="pro_div">
								<div class="imgps">
									<a href="batchMarketInfo?m=${main.bmid }"><img original="${main.mimg }" /></a>
								</div>
								<div class="cuntitle line2 pr">
									<h3 class="f14 c6 tb">
										<a href="batchMarketInfo?m=${main.bmid }" class="cB">${main.marketName }</a>
									</h3>
								</div>
								<div class="w mt5 h70 ovhid line2">
									<div class="slh">主营：${main.mainBus }</div>
									<div class="slh">${main.address}</div>
								</div>
							</div>
						</li>
					</c:forEach>
				</ul>
			</div>
		</c:forEach>
	</div>
</body>
</html>