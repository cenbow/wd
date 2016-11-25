<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="pg" uri="http://www.okwei.com/pagination"%>
<%@ page import="java.util.ResourceBundle"%>
<%
	String shopdomain = ResourceBundle.getBundle("domain").getString("shopdomain");
	String domain = ResourceBundle.getBundle("domain").getString("okweidomain");
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>工厂,工厂货源直供微店分销平台,微店网(okwei.com)——免费开微店，认证原创微店官方平台</title>
<meta content="工厂货源直供微店分销平台。" name="description">
<meta content="微店网，免费开通微店，工厂，品牌工厂，工厂官方微店。" name="keywords">
<link href="http://base.okimgs.com/images/favicon.ico"
	rel="shortcut icon">
<script>
	$(function() {
		$('.leftscr a').each(function() {
			$(this).click(function() {
				$('.leftscr a').attr('class', '');
				$(this).attr('class', 'nows')
			})
		})
		$(".cpimgs ul li:nth-child(5n)").css("margin-right", "0px");
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
			placeholder : "../statics/images/206_155.png",
			effect : "fadeIn"
		});
	})
</script>
<style>
.scpf_market .cpimgs ul li {
	height: 288px;
}
</style>
</head>
<body>

	<div class="w line-b fl">
		<div class="line3 c6 mar1200">${mainvo.currentType }</div>
	</div>
	<div class="w line-b  pt10 pb10 bg-w fl">
		<div class="mar1200">
			<div class="fl tyright  f14 ">
				<script type="text/javascript">
					$(function() {
						$('.img_left li a').each(function() {
							$(this).click(function() {
								$('.img_left li a').attr('class', 'fic_no');
								$(this).attr('class', 'fic_yes');
							})
						})
					})
				</script>

				<ul class="img_left">
					<c:if test="${mainvo.classone==0 }">
						<li><a class="fic_yes" href="/yun/list?c=0&type=0"><i></i>全部</a></li>
					</c:if>
					<c:if test="${mainvo.classone!=0 }">
						<li><a class="fic_no" href="/yun/list?c=0&type=0"><i></i>全部</a></li>
					</c:if>
					<c:forEach var="kv" items="${mainvo.alltype }" varStatus="status">
						<c:if test="${kv.typeid==mainvo.classone }">
							<li><a class="fic_yes"
								href="/yun/list?c=${kv.typeid }&type=1"><span
									class="${kv.cssclass }"></span><i></i>${kv.typename }</a></li>
						</c:if>
						<c:if test="${kv.typeid!=mainvo.classone }">
							<li><a class="fic_no"
								href="/yun/list?c=${kv.typeid }&type=1"><span
									class="${kv.cssclass }"></span><i></i>${kv.typename }</a></li>
						</c:if>
					</c:forEach>
				</ul>
			</div>
		</div>
	</div>
	<c:if test="${mainvo.hpNavagation!=null }">
		<div class="w line-b  pt10 pb10 bg-w fl">
			<div class="mar1200">
				<div class="fl tyleft c6">${mainvo.hpNavagation.head }:</div>
				<div class="fl tygduos">
					<c:if test="${mainvo.classtwo==0 }">
						<a class="bgyue"
							href="/yun/list?c=${mainvo.hpNavagation.allcode }&type=1">${mainvo.hpNavagation.all }</a>
					</c:if>
					<c:if test="${mainvo.classtwo!=0 }">
						<a href="/yun/list?c=${mainvo.hpNavagation.allcode }&type=1">${mainvo.hpNavagation.all }</a>
					</c:if>
				</div>
				<div class="fl tyright">
					<ul>
						<c:forEach var="classkv" items="${mainvo.hpNavagation.typelist }"
							varStatus="status">
							<c:if test="${mainvo.classtwo==classkv.typeid }">
								<li><a class="bgyue"
									href="/yun/list?c=${classkv.typeid }&type=2">${classkv.typename }</a></li>
							</c:if>
							<c:if test="${mainvo.classtwo!=classkv.typeid }">
								<li><a href="/yun/list?c=${classkv.typeid }&type=2">${classkv.typename }</a></li>
							</c:if>
						</c:forEach>
					</ul>
				</div>
			</div>
		</div>
	</c:if> 
	<c:if test="${fn:length(mainvo.supmsgList.supmsgList)<1}">
		<div class="mar1200 scpf_market ">
			<!-- 工厂 空 -->
			<div class="null_coues fl pb30">
				<p class="f18 tc">没有符合条件的工厂，请尝试其他搜索条件</p>
			</div>
		</div>
	</c:if> 
	<c:if test="${fn:length(mainvo.supmsgList.supmsgList)>0}">
	<form id="searcherForm" action="/yun/list" >
	<input type="hidden" name="type" value="${mainvo.type}" /> 
	<input type="hidden" name="c" value="${mainvo.classid}" />
		<div class="mar1200 scpf_market ">
			<div class="w fl cpimgs mt20">
				<ul>
					<c:forEach var="supplier" items="${mainvo.supmsgList.supmsgList }"
						varStatus="status">
						<li class="pro_li">
							<div class="pro_div">
								<div class="imgps ">
									<a href="http://${supplier.weiid }.<%=domain %>" style="height: 154.5px;">
						            <img original="${supplier.supimg }">
						            </a> 
									<%-- <a href="http://${supplier.weiid }.<%=domain %>/weiShop/index"
										class="pr" href="#" style="height: 154.5px;"> <img
										original="${supplier.supimg }"> <span
										style="top: 65px; left: 0; padding: 0 25px; z-index: 5; height: 33px; overflow: hidden;"
										class="pa cW f14 tb block">${supplier.supname }</span>
									</a> --%>
								</div>
								<div class="cuntitle line2 pr">
									<h3 class="f14 c6 tb">
										<a class="cB cuntitle_a"
											href="http://${supplier.weiid }.<%=domain %>"
											title="${supplier.supname }">${supplier.supname }</a>
									</h3>
									<div class="pa img_n">
										<span title="" class="pic_img1"></span><span class="pic_img2"></span><span
											class="pic_img3"></span>

									</div>
								</div>
								<div class="w mt5 h70 ovhid line2">
									<div class="slh" title="${supplier.mainbus }">主营：${supplier.mainbus }</div>
									<p class="c6">
										<span class="fl">产品数量 ${supplier.productcount }</span><span
											class="fr">上架次数 ${supplier.shelvecount }</span>
									</p>
									<div class="cb"></div>
									<p class="c6">${supplier.area }</p>
								</div>
							</div>
						</li>
					</c:forEach>
				</ul>
			</div>

			<!-- 分页 -->
			<div class="pull-right">
				<pg:page pageResult="${pageRes}" />
			</div>
		</div>
	</form> 
	</c:if> 
	<script type="text/javascript">
$(function() {
	var page = new Pagination({
		formId : "searcherForm",
		isAjax : false,
		targetId : "navTab",
		submitId : "searchBtn",
		validateFn : false
	});
	page.init();
});
	</script>
</body>

</html>