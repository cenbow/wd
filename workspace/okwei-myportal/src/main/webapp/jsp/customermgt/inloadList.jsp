<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="pg" uri="http://www.okwei.com/pagination"%>

<%@ page import="java.util.ResourceBundle"%>
<%
	String paydomain = ResourceBundle.getBundle("domain").getString("paydomain");
	String okweidomain = ResourceBundle.getBundle("domain").getString("okweidomain"); 
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>我发展的落地店</title>
<script language="JavaScript" type="text/javascript" src="/statics/js/My97/WdatePicker.js"></script>
</head>
<body>
	<form id="searcherForm" action="<%=basePath%>myCustomerMgt/inloadPage" onsubmit="return false;">
		<div class="conter_right_xx">
			<div style="margin-top: 0; height: auto;" class="xh-shurk">
				<ul>
					<li><span>申请者微店号：</span> <input type="text" class="btn_h24 w164" id="applyPersonWeiId"
						name="applyPersonWeiId" value="${dto.applyPersonWeiId}"></li>
					<li><span>申请时间：</span> <input type="text" class="btn_h24" name="beginTime" value="${dto.beginTime}" id="beginTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" /> 
					<label>—</label> <input type="text" name="endTime" class="btn_h24" value="${dto.endTime }" id="endTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" /></li>
					<li><input type="submit" style="width: 80px;" class="btn_submit_two" value="查询" id="searchBtn"></li>
				</ul>
			</div>
		</div>

		<div class="outermost bg_white mt13 fl tabnes">
			<table>
				<tbody>
					<tr>
						<td>申请人店铺</td>
						<td>加入时间</td>
						<td>供应商名</td>
						<td>代理需求名称</td>
						<td>成功招募可获悬赏金额</td>
						<td>是否发放了悬赏</td>
						<td>操作</td>
					</tr>
					
					<c:if test="${fn:length(pageResult.list) < 1 }">
						<tr>
							<td colspan="7" style="vertical-align: middle; text-align: center; height: 200px;">暂无相关数据</td>
						<tr>
					</c:if>
					<c:forEach items="${pageResult.list}" var="supplier" varStatus="status">
					<div name="divSupplierData">
						<tr  shopname="${supplier.shopName}" linkname="${supplier.linkname }" phone="${supplier.phone }" address="${supplier.locationStr}${supplier.address}">							
							<td >${supplier.shopName}</td>							
							<td>${supplier.applyTime }</td>
							<td>${supplier.supplierName}</td>
							<td>${supplier.demandName}</td>
							<td>${supplier.rewardAmount }</td>
							<td>
								<c:if test="${supplier.isPayReward==0}">
									<span>未发放</span>
								</c:if>
								<c:if test="${supplier.isPayReward==1}">
									<span>已发放</span>
								</c:if>
							</td>
							<td>
								<%-- <a href="javascript:;" class="mr_10" onclick="showWin('查看资料','${supplier.shopId}','520px','340px')">查看资料</a> --%>
								<a href="javascript:;" class="mr_10" onclick="winS1('查看资料','win_div_8','520px','340px',this)">查看资料</a>
							</td>
						</tr>
						</div>
					</c:forEach>
				</tbody>
			</table>
			<div class="pull-right">
				<pg:page pageResult="${pageResult}" />
			</div>
		</div>
		</form>
	
	<script>
		$(function(){			
			var page = new Pagination( {
				formId: "searcherForm",
				isAjax : true,
				targetId : "navTab",
				submitId:"searchBtn",
				validateFn:checkInfo
			});
			page.init();
		})
		
			
		
		$("#endTime").blur(function() {
				if ($("#beginTime").val() == "" || $("#endTime").val() == "") {
					return false;
				}
				var begin = new Date($("#beginTime").val());
				var end = new Date($("#endTime").val());
				if (begin > end) {
					$("#endTime").val("");
				}
			})
			$("#beginTime").blur(function() {
				if ($("#beginTime").val() == "" || $("#endTime").val() == "") {
					return false;
				}
				var begin = new Date($("#beginTime").val());
				var end = new Date($("#endTime").val());
				if (begin > end) {
					$("#beginTime").val("");
				}
			})
		function checkInfo(){
			var code = $("#applyPersonWeiId").val();
			var re = /^[1-9]+[0-9]*]*$/;
			if (code && !re.test(code)) {
				alert("请输入数字格式的微店号");
				return false;
			}
			return true;
		}
		 function showWin(title,win_id,width,height){ //title 标题 win_id 弹窗ID  width 弹窗宽度 height 弹窗高度
		        var pagei = $.layer({
		            type: 2,   //0-4的选择,0：信息框（默认），1：页面层，2：iframe层，3：加载层，4：tips层。
		            btns: 1,
		            btn: ['关闭'],
		            title: title,
		            border: [0],
		            closeBtn: [0],
		            closeBtn: [0, true],
		            shadeClose: true,
		            area: [width,height],
		            //page: {dom : '#'+ win_id},
		            iframe: { src: '/myCustomerMgt/queryData/'+win_id},
		            end: function(){ $("#AddCount").hide()}		            
		        });
		 
		    }
		 function winS1(title,win_id,width,height,obj){   //title 标题 win_id 弹窗ID  width 弹窗宽度 height 弹窗高度
			 	if(obj!=null)
				 {		
					$("#txtInloadName").html($(obj).closest("tr").attr("shopname"));
					$("#txtName").html($(obj).closest("tr").attr("linkname"));
					$("#txtPhone").html($(obj).closest("tr").attr("phone"));
					$("#txtAddress").html($(obj).closest("tr").attr("address"));
				 }
		        var pagei = $.layer({
		            type: 1,   //0-4的选择,0：信息框（默认），1：页面层，2：iframe层，3：加载层，4：tips层。
		            btns: 1,
		            btn: ['关闭'],
		            title: title,
		            border: [0],
		            closeBtn: [0],
		            closeBtn: [0, true],
		            shadeClose: true,
		            area: [width,height],
		            page: {dom : '#'+ win_id},
		            end: function(){ $("#AddCount").hide()}
		        });
		    }
	</script>
	<!-- 查看资料 -->
<div class="updata_tixian" style="display:none;" id="win_div_8">
    <dl class="xzgys f16 mb_20">
        <dd class="tr">落地店名称：</dd>
        <dt>
        <span id="txtInloadName"></span>           
        </dt>
    </dl>
    <dl class="xzgys f16 mb_20">
        <dd class="tr">姓名：</dd>
        <dt><span id="txtName"></span></dt>
    </dl>
    <dl class="xzgys f16 mb_20">
        <dd class="tr">联系电话：</dd>
        <dt><span id="txtPhone"></span></dt>
    </dl>
    <dl class="xzgys f16 mb_20">
        <dd class="tr">地址：</dd>
        <dt><span id="txtAddress"></span></dt>
    </dl>
    
</div>
</body>
</html>

