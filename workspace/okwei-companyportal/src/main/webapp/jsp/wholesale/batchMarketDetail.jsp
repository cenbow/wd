<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="java.util.ResourceBundle"%>
<%
	String companydomain = ResourceBundle.getBundle("domain").getString("companydomain");
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>批发市场-详情</title>
<script type="text/javascript" src="http://base2.okwei.com/js/compnay/jquery.luara.0.0.1.min.js"></script>
<script type="text/javascript" src="http://base2.okwei.com/js/compnay/jquery.event.drag-1.5.min.js"></script>
<script type="text/javascript" src="http://base2.okwei.com/js/compnay/jquery.touchSlider.js"></script>
<script>
	
</script>
<style>

</style>
</head>

<body style="background: #ebebeb;">
	<article>
	<div class="w line-b fl">
		<div class="line4 c6 mar1200">
			当前位置&nbsp;：&nbsp;微店网 > <a target="_blank" href="<%=companydomain %>/wholesale/index">批发市场</a> > ${batchMarket.name}
		</div>
	</div>
	<div class="w  fl">
		<div class="mar1200 scpf_market" style="background: #fff;padding: 20px 0;">
			<!-- 筛选 -->
			<!-- 商品排列  style=" visibility:visible;opacity:1"  list_li-->
			<c:if test="${fn:length(marketImgsList) > 0}">
			<div class="example2" style="margin: 0 20px;position: absolute;">
				<ul>
					<c:forEach items="${marketImgsList}" var="mi" varStatus="vs">
						<li><img src="${mi.img}" alt="${vs.index+1}" /></li>
					</c:forEach>
				</ul>
				<ol>
					<c:forEach items="${marketImgsList}" var="mi" varStatus="vs">
						<li>${vs.index+1}</li>
					</c:forEach>
				</ol>
			</div>
			<script>
				$(function() {
					$(".example2").luara({
						width : "250",
						height : "250",
						interval : 3000,
						selected : "seleted",
						deriction : "left"
					});
				});
			</script>
			<!-- 轮播end -->
			</c:if>
			<div class="bg-w mt20 fl w" style="width: 910px;padding-left: 290px;">
						<h3 class=" m10 tb pb15 line-b">招商说明</h3>
						<p class="m10 pb10 line-b f14 c6">
							招商对象： <span class="c3">${marketInfo.mcobject}</span>
						</p>
						<p class="m10 pb10 line-b f14 c6">
							招租 QQ： <span class="c3">${marketInfo.mcqq}</span>
						</p>
						<p class="m10 pb10 line-b f14 c6">
							联系电话：<span class=" c3">${marketInfo.mctel}</span>
						</p>
						<p class="m10 pb10 line-b f14 c6">
							地址：<span class="c3">${batchMarket.area}</span>
						</p>
						<div class="blank"></div>

						<div class="w p10 fl"></div>
					</div>
			<div class="bg-w f14 lh150 w fl ovhid">
				<h3 class="p10 tb pt15 pb15 line-b">市场简介</h3>
				<p class="p10">${batchMarket.marketDec}</p>
				<div class="w p10">

					<div class="blank10"></div>
					<div class="bg-w mt20 fl w">
						<h3 class=" m10 tb pb15 line-b">市场详情</h3>
						<p class="m10 pb10 line-b f14 c6">
							主营 ：${batchMarket.busContent}
						</p>
						<p class="m10 pb10 line-b f14 c6">
							所属行业：${batchMarket.bussinessClass} 
						</p>
						<p class="m10 pb10 line-b f14 c6">
							市场商户数量：${batchMarket.bussinesCount}
						</p>


						<div class="content f14">
							<div class="p10" style="padding-top: 0;">
								<p>${batchMarket.marketDetail}</p>
							</div>
						</div>
						<div class="blank"></div>
					</div>
					
				</div>
			</div>
		</div>
	</div>
	</article>
</body>
</html>