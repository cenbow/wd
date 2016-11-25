<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>    
<%@ taglib prefix="pg" uri="http://www.okwei.com/pagination"%>
<%@ page import="java.util.ResourceBundle"%>
<%
	String shopdomain = ResourceBundle.getBundle("domain").getString("shopdomain");
	String okweidomain = ResourceBundle.getBundle("domain").getString("okweidomain");
	String companydomain = ResourceBundle.getBundle("domain").getString("companydomain");
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="http://base3.okwei.com/js/company/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="http://base3.okwei.com/js/company/common/extends_fn.js"></script>
<script type="text/javascript" src="http://base3.okwei.com/js/company/common/pagination.js"></script>
<script type="text/javascript" src="http://base3.okwei.com/js/company/jquery.lazyload.js"></script>
<link rel="stylesheet" type="text/css" href="http://base1.okwei.com/css/web/pc-base.css" />
<link rel="stylesheet" type="text/css" href="http://base2.okwei.com/css/web/home-page.css" />
<link rel="stylesheet" type="text/css" href="http://base3.okwei.com/css/company/pagination.css" />
<title>批发市场商铺列表</title>
<script>
$(function(){
	//分页加载
	var page = new Pagination( {
		formId: "searchForm",
		isAjax : false,
		targetId : "navTab",
		submitId:"searchBtn"
	});
	page.init();
	//图片延迟加载
	$("img[name='shopImg']").lazyload({
        placeholder: "<%=path%>/statics/images/206_206.png",
        event : "sporty"
    });
	$(window).bind("load", function() {    
		var timeout = setTimeout(function() {$("img[name='shopImg']").trigger("sporty")}, 1000);    
	}); 
	//样式
	$(".cpimgs ul li:nth-child(5n)").css("margin-right","0px");
	//分页控制
	$('#shangpu_li',window.parent.document).find('b').eq(0).text($('.current').html());
	$('#shangpu_li',window.parent.document).find('b').eq(1).text($('.pageinfo em').html());
	
	$('.imgps a').height($('.imgps a').width()*0.75);
});
function goPage1(h){
	$('.pagination a').each(function(){
		if ($(this).html()==h) {
			$(this).trigger('click');
		}
	});
}
</script>
<style>
.scpf_market .cpimgs ul li{ height:260px;}
</style>
</head>

<body style="background:#ebebeb;">
<div class="mar1200 scpf_market"> 
    <!-- 筛选 --> 
    <!-- 商品排列  style=" visibility:visible;opacity:1"  list_li-->
	<form id="searchForm" name="searchForm" action="<%=basePath %>wholesale/batchMarketShopList" >
	<input type="hidden" name="bmid" value="${bmid}"/>
    <div class="w fl cpimgs">
  	  <c:if test="${fn:length(pageResult.list)<1}">
		<!-- 批发市场详情 商铺空 -->
	     <div class="null_coues fl pb30">
	      	<p class="f18 tc">该市场暂无商铺入驻，逛逛其他批发市场</p>
	        <div class="w350 fl botlse">
	        	<a target="_blank" href="<%=companydomain %>/wholesale/list">去看看</a>
	        </div>
	     </div>
	  </c:if>
      <ul>
      <c:forEach items="${pageResult.list}" var="bm">
        <li class="pro_li"> 
        	<div class="pro_div">
           		<div class="imgps"><a href="http://www.${bm.weiId}.<%=okweidomain %>" target="_blank"><img name="shopImg" original="${bm.shopPic}" /></a></div>
            	<div class="cuntitle line2">
                	<h3 class="f14 c6 tb"><a href="#" class="cB">${bm.shopName}</a></h3>
                </div>
                <div class="w mt10 h60 ovhid">
                	<div class="fl caseone h20">主营：</div>
                	<div class="fl casetwo">${bm.busContent}</div>
                </div>
           </div>
        </li>
      </c:forEach>
      </ul>
    </div>
    <!-- 分页 -->
	<div class="pull-right">
		<pg:page pageResult="${pageResult}" />
	</div>
	</form>
  </div>
</body>
</html>