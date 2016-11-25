<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript"
	src="<%=path%>/statics/js/jquery-1.7.1.min.js"></script>
<title>支付订单</title>
</head>

<body style="text-align: center;">
	<form action="/shopCartMgt/preSubmit" method="post">
		<input type="hidden" id="stype" name="stype" /> <input type="hidden"
			id="scidJson" name="scidJson" />
	</form>
	<table style="text-align: center;">
		<tr>
			<td></td>
			<td>购物车类型(1/2/3)：</td>
			<td><input id="gwctype" value="1" /></td>
		</tr>
		<tr>
			<td><input type="checkbox" name="product_cb" value="1" /></td>
			<td>商品id：</td>
			<td><input id="pid1" value="305600" /></td>
		</tr>
		<tr>
			<td></td>
			<td>来源微店id：</td>
			<td><input id="shopWeiId1" value="1226" /></td>
		</tr>
		<tr>
			<td></td>
			<td>款式id：</td>
			<td><input id="styleId1" value="11218696" /></td>
		</tr>
		<tr>
			<td></td>
			<td>产品数量：</td>
			<td><input id="prodCount1" value="2" /></td>
		</tr>

		<tr>
			<td><input type="checkbox" name="product_cb" value="2" /></td>
			<td>商品id：</td>
			<td><input id="pid2" value="449359" /></td>
		</tr>
		<tr>
			<td></td>
			<td>来源微店id：</td>
			<td><input id="shopWeiId2" value="1226" /></td>
		</tr>
		<tr>
			<td></td>
			<td>款式id：</td>
			<td><input id="styleId2" value="12553344" /></td>
		</tr>
		<tr>
			<td></td>
			<td>产品数量：</td>
			<td><input id="prodCount2" value="2" /></td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td><input type="button" id="preSub" value="提交" onclick="javascript:liji()"/></td>
		</tr>
	</table>
	<script type="text/javascript">
	$(function() {
		  $("#preSub").live("click",function(){
			var scids = new Array();
			$("input[name=product_cb]:checked").each(function() {
				var scid = $(this).val();
				var cartProd = new Object;  
				cartProd.prodId = $("#pid"+scid).val();//商品id
				cartProd.styleId = $("#styleId"+scid).val();//商品款式id
				cartProd.shopWeiId = $("#shopWeiId"+scid).val();//来源微店id
				cartProd.prodCount = $("#prodCount"+scid).val();//商品数量
				scids.push(cartProd);
			});
			$("#stype").val($("#gwctype").val());
			$("#scidJson").val($.toJSON(scids));
			document.forms[0].action = "/shopCartMgt/preSubmit";
			document.forms[0].submit();
		})   
	})
</script>
</body>
</html>
