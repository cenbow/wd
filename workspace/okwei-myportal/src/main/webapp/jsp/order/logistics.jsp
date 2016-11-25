<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<% 
	String path=request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>物流详情</title>
<script>
	var rootPath="<%=request.getContextPath()%>";
	$(function() {
		$(".ddxq_gg_wuliu_dl dt span:last").attr("class", "ddxq_gg_wuliu_qs");
		
	    var oo="${canModify}";
	    var orderNo="${orderNo}"
	    if(oo==1){
	    	$("#s1").show();
		var baidu = rootPath+"/seller/delivery?orderNo="+orderNo;
	    //alert(baidu);
		$("#ss").attr("href", baidu);
		}else{
			$("#s1").hide();
		}

	}
	    )
</script>
</head>
<body>
	<div class="weiz_iam f12 fm_song">
		当前位置：<a href="/maininfo/maininfo">微店中心</a>&gt;<a href="/order/buylist">我的购买订单</a>&gt;<span>物流详情</span>
	</div>
	<div class="ddxq_gg">
		<!-- 物流信息 -->
		<div class="ddxq_gg_wuliu">
			<h6 class="mb_10">物流信息</h6>
			<div id="s1">
			<a id="ss" href="" class="f16 cB" style="font-weight: 100;">修改单号</a></div>
			</div>
			<div class="blank2"></div>
			<dl class="ddxq_gg_wuliu_dl">
				<dd>物流名称：</dd>
				<dt>${logisticsT.longisticsName}</dt>
			</dl>
			<dl class="ddxq_gg_wuliu_dl">
				<dd>物流单号：</dd>
				<dt>${logisticsT.logisticsNo}</dt>
			</dl>
			<dl class="ddxq_gg_wuliu_dl">
				<dd>物流跟踪：</dd>
				<dt>
					<c:forEach var="record" items="${logisticsT.tailList}">
						<span>${record}</span>
					</c:forEach>
				</dt>
			</dl>
		</div>
		<div class="blank"></div>
		<div class="blank2"></div>
	</div>
</body>
</html>