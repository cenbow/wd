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
<title>我发展的代理商</title>
<script language="JavaScript" type="text/javascript" src="/statics/js/My97/WdatePicker.js"></script>
<script type="text/javascript" src="/statics/js/layer/layer.min.js"></script>
</head>
<body>
	<form id="searcherForm" action="<%=basePath%>myCustomerMgt/agentPage" onsubmit="return false;">
		<div class="conter_right_xx">
			<div style="margin-top: 0; height: auto;" class="xh-shurk">
				<ul>
					<li><span>申请者微店号：</span> <input type="text" class="btn_h24 w164" id="applyPersonWeiId"
						name="applyPersonWeiId" value="${dto.applyPersonWeiId}"></li>
					<li><span>申请状态：</span> 
						<select style="width: 127px;" class="btn_h28"  name="auditState" id="auditState">
								<option value="-1">全部</option>
								<option value="0" <c:if test="${dto.auditState==0}">selected</c:if>>待审核</option>
								<option value="1" <c:if test="${dto.auditState==1}">selected</c:if>>已通过</option>
								<option value="2" <c:if test="${dto.auditState==2}">selected</c:if>>未通过</option>
							</select>
							</li>
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
						<td>申请人店铺名称</td>
						<td>状  态</td>
						<td>申请时间</td>
						<td>平台/品牌号名称</td>
						<td>需求名称</td>
						<td>成功招募可获悬赏金额</td>
						<td>是否发放了悬赏</td>
						<td>操作</td>
					</tr>
					<c:if test="${fn:length(pageResult.list) < 1 }">
						<tr>
							<td colspan="7" style="vertical-align: middle; text-align: center; height: 200px;">暂无相关数据</td>
						</tr>
					</c:if>
					<c:forEach items="${pageResult.list}" var="supplier" varStatus="status">					
						<tr shopName="${supplier.linkname }" phone="${supplier.phone }" agentArea="${supplier.areaStr }" address="${supplier.address }" license="${supplier.imgs }" mypower="${supplier.intro }">
							<td>${supplier.shopName}</td>
							<td>
								<c:if test="${supplier.status==0}">
									<span>待审核</span>
								</c:if>
						 		<c:if test="${supplier.status==1}">
									<span>已通过</span>
								</c:if>
								<c:if test="${supplier.status==2}">
									<span>未通过</span>
								</c:if>
							</td>
							<td>${supplier.applyTime }</td>
							<td>${supplier.platformOrBrandName}</td>
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
								<c:if test="${supplier.isFollowVerifier==0}">
									<%-- <a href="javascript:;" class="mr_10" onclick="wins1('查看资料','${supplier.agentId}','520px','500px')">查看</a> --%>
									<a href="javascript:;" class="mr_10" onclick="winS3('查看资料','win_div_8','520px','480px',this)">查看</a>
								</c:if>
								<c:if test="${supplier.status==0 && supplier.isFollowVerifier==1}">
									<%-- <a href="javascript:;" class="mr_10" onclick="wins1('查看资料','${supplier.agentId}','520px','500px')">查看</a> --%>
									<a href="javascript:;" class="mr_10" onclick="wins2('跟进','${supplier.applyID}','520px','620px','${supplier.applyID}')">跟进</a>
								</c:if>
						 		<c:if test="${supplier.status==1 && supplier.isFollowVerifier==1}">
									<a href="javascript:;" class="mr_10" onclick="wins4('跟进','${supplier.agentId}','520px','620px','${supplier.agentId}')">查看</a>
								</c:if>
								<c:if test="${supplier.status==2  && supplier.isFollowVerifier==1}">
									<a href="javascript:;" class="mr_10" onclick="wins2('查看','${supplier.applyID}','520px','620px','${supplier.applyID}')">跟进</a>
								</c:if>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="pull-right">
				<pg:page pageResult="${pageResult}" />
			</div>
		</div>
	</form>
<!-- 查看资料 -->
<div class="updata_tixian" style="display:none;" id="win_div_8">
    <dl class="xzgys f16 mb_10">
        <dd class="tr">姓名：</dd>
        <dt>
            <span id="txtName"></span>
        </dt>
    </dl>
    <dl class="xzgys f16 mb_10">
        <dd class="tr">联系电话：</dd>
        <dt>
            <span id="phone"></span>
        </dt>
    </dl>
    <dl class="xzgys f16 mb_10">
        <dd class="tr">代理地区：</dd>
        <dt>
             <span id="txtArea"></span>
        </dt>
    </dl>
     <dl class="xzgys f16 mb_10">
        <dd class="tr">详细地址：</dd>
        <dt>
             <span id="txtAddress"></span>
        </dt>
    </dl>
    
    <dl class="xzgys f16 mb_10">
        <dd class="tr">营业执照：</dd>
        <dt>
            <img id="txtImg" src="../../img/xh_image0920004.jpg" width="100" height="100" />
        </dt>
    </dl>
    <dl class="xzgys f16 mb_10">
        <dd class="tr">我的优势：</dd>
        <dt>
            <span id ="txtMyPower"></span>
        </dt>
    </dl> 
</div>



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
		function winS3(title,win_id,width,height,obj){ //title 标题 win_id 弹窗ID  width 弹窗宽度 height 弹窗高度
			if(obj!=null)
			 {		
				$("#txtName").html($(obj).closest("tr").attr("shopName"));
				$("#phone").html($(obj).closest("tr").attr("phone"));
				$("#txtArea").html($(obj).closest("tr").attr("agentArea"));
				$("#txtAddress").html($(obj).closest("tr").attr("address"));
				$("#txtMyPower").html($(obj).closest("tr").attr("mypower"));
				$("#txtImg").attr("src",$(obj).closest("tr").attr("license"));
			 }
	        var pagei = $.layer({
	            type: 1,   //0-4的选择,0：信息框（默认），1：页面层，2：iframe层，3：加载层，4：tips层。
	            btns: 1,
	            btn: ['确定'],
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
		
		 function wins1(title,win_id,width,height){ //title 标题 win_id 弹窗ID  width 弹窗宽度 height 弹窗高度
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
		            /* page: {dom : '#'+ win_id}, */
		            iframe:{src: '/myCustomerMgt/showData/1_'+win_id},
		            end: function(){ $("#AddCount").hide()}
		        });
		    }
		 
		 function wins2(title,win_id,width,height,agentid){ //title 标题 win_id 弹窗ID  width 弹窗宽度 height 弹窗高度
		        var pagei = $.layer({
		            type: 2,   //0-4的选择,0：信息框（默认），1：页面层，2：iframe层，3：加载层，4：tips层。
		            btns: 2,
		            btn: ['保存','取消'],
		            title: title,
		            border: [0],
		            closeBtn: [0],
		            closeBtn: [0, true],
		            shadeClose: true,
		            area: [width,height],
		            /* page: {dom : '#'+ win_id}, */
		            iframe:{src: '/myCustomerMgt/showData/2_'+win_id},
		            yes: function(){ 
		            	if(saveFollowMsgAjax(agentid)){
		        			layer.close(pagei);
		        		}
		            	}
		        });
		    }
		 function wins4(title,win_id,width,height,agentid){ //title 标题 win_id 弹窗ID  width 弹窗宽度 height 弹窗高度
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
		            /* page: {dom : '#'+ win_id}, */
		            iframe:{src: '/myCustomerMgt/showData/2_'+win_id},
		            yes: function(){ 
		        			layer.close(pagei);
		            	}
		        });
		    }
		//保存跟进记录
		 function saveFollowMsgAjax(agentid){
			debugger;
			var txtmsg=$.trim($("iframe.xubox_iframe").contents().find("#txtNewFollow").val());
		 	if(txtmsg==""){
		 		alert("跟进记录不能为空");
		 		return false;
		 	}		 	
		 	$.ajax({
		 		url:"/myCustomerMgt/saveFollowMsg",
		 		type:"post",
		 		dataType : "json",
		 		contentType : "application/x-www-form-urlencoded; charset=utf-8",
				data : {
					"msg" : txtmsg,
					"agentid" : agentid
				},
				error : function() {
					alert("异常！");
					location.reload();
				},
				success : function(data) {
					if (data.msg == "保存成功！") {
						alert("保存成功！")
					}
					else {
						alert(data.msg);
					}
				}
		 	})
		 	return true;
		 }
		 
	</script>
</body>
</html>

