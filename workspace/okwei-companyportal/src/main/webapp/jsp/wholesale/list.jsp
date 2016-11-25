<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="pg" uri="http://www.okwei.com/pagination"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>批发市场,全国批发市场最集中的地方,微店网(okwei.com)——免费开微店，认证原创微店官方平台官方平台</title>
<meta name="description" id="description" content="工厂货源直供微店分销平台。" />
<meta name="keywords" id="keywords" content="微店，微店网，免费开通微店，云商通，工厂，品牌工厂，工厂官方微店。" />
<style type="text/css">
    .scpf_market .cpimgs ul li{height: 300px;}
    .tyleft{width:80px;}
</style>
<script type="text/javascript">
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
		$("#divhy a").click(function() {
			$("input[name=id]").val($(this).attr("data"));
			document.forms[0].submit();
		});
		//选中
		var code = $("input[name=code]").val();
		$("#divarea a").removeClass("nows");
		$("#divarea a").each(function() {
			if (code == $(this).attr("data")) {
				$(this).attr("class", "nows");
			}
		});
		var id = $("input[name=id]").val();
		$("#divhy a").removeClass("nows");
		$("#divhy a").each(function() {
			if (id == $(this).attr("data")) {
				$(this).attr("class", "nows");
			}
		});
		 $(".imgps img").lazyload({
             placeholder: "../statics/images/206_155.png",
             effect: "fadeIn"
         });
	});

	function gotobycode(code) {
		$("input[name=code]").val(code);
		document.forms[0].submit();
	}
</script>
</head>
<body>
	<div class="w line-b fl">
		<div class="line3 mar1200 ">
			<a href="http://www.okwei.com">首页</a> > <a href="index">批发市场 </a> > <a href="javascript:gotobycode();">全国</a>
			<c:forEach var="sel" items="${listCount.selList }">
				><a href="javascript:gotobycode(${sel.id });">${sel.key }</a>
			</c:forEach>
		</div>
	</div>
	<div class="w line-b  pt20 pb20 bg-w fl">
		<div id="divarea" class="mar1200">
			<div class="fl tyleft c6">按区域查找</div>
			<div class="fl tygduos">
				<a href="javascript:gotobycode(${listCount.selCode });" data="${listCount.selCode }">全部（<span>${listCount.areaCount }</span>）
				</a>
			</div>
			<div class="fl tyright" style="width: 1000px;">
				<ul>
					<c:forEach var="area" items="${listCount.areaList }">
						<li><a href="javascript:gotobycode(${area.id });" data="${area.id }">${area.key }（<span>${area.value }</span>）
						</a></li>
					</c:forEach>
				</ul>
			</div>
		</div>
	</div>
<%-- 	<div class="w line-b  pt20 pb20 bg-w fl">
		<div id="divhy" class="mar1200">
			<div class="fl tyleft c6">按行业查找</div>
			<div class="fl tygduos">
				<a href="javascript:;" data="">全部（<span>${listCount.count }</span>）
				</a>
			</div>
			<div class="fl tyright" style="width: 1000px">
				<ul>
					<c:forEach var="trade" items="${listCount.tradeList }">
						<li><a href="javascript:;" data="${trade.id }">${trade.key }（<span>${trade.value }</span>）
						</a></li>
					</c:forEach>
				</ul>
			</div>
		</div>
	</div> --%>

	<div class="mar1200 scpf_market">
		<!-- 筛选 -->
		<!-- 商品排列  style=" visibility:visible;opacity:1"  list_li
	<div class="screen bg-w fl mt20 mb20">
      <ul>
        <li class="fl leftscr"><a href="javascript:;"  class="nows">批发市场</a>   <a href="javascript:;">商铺</a> <a href="javascript:;">商品</a>  
           
        </li>
         
        <li class="fr rigsuces"><span><b>12</b>/35</span> <a href="#">上一页</a> <a href="#">下一页</a> </li>
      </ul>
    </div>-->
		<form id="searcherForm" action="/wholesale/list">
			<input type="hidden" name="code" value="${code }" /> <input type="hidden" name="id" value="${id }" /> <input type="hidden" name="w" value="${user.tgWeiID }" />
			<div class="w fl cpimgs mt15">
					<c:if test="${fn:length(pageRes.list) < 1 }">
						<div class="null_coues fl pb30">
					      	<p class="f18 tc">没有符合条件的批发市场，请尝试其他搜索条件</p> 
					     </div>
					</c:if>
				<ul>
					<c:forEach var="model" items="${pageRes.list}" varStatus="vs">
						<li class="pro_li">
							<div class="pro_div">
								<div class="imgps">
									<a href="batchMarketInfo?m=${model.id }"><img original="${model.img }" /></a>
								</div>
								<div class="cuntitle line2 pr">
									<h3 class="f14 c6 tb">
										<a href="batchMarketInfo?m=${model.id }" class="cB">${model.name }</a>
									</h3>
								</div>
								<div class="w mt5 h70 ovhid line2">
									<div class="slh">主营：${model.bus }</div>
									<p class="c6">
										<span class="fl">商家 ${model.supCount }</span><span class="fr">服务点 ${model.rzCount }</span>
									</p>
									<div class="cb"></div>
									<p class="clearfix">${model.address }</p>
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
		</form>
		<!-- 		<div class="pages"> -->
		<!-- 			<span>共<font>1678</font>条评论 -->
		<!-- 			</span><b>1</b><a href="">2</a><a href="">3</a><a href="">4</a><a href="">5</a>...<a href="">168</a><a href="">下一页>></a> -->
		<!-- 		</div> -->
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
	</div>
</body>
</html>