<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="java.util.ResourceBundle"%>
<%
	String verifydomain = ResourceBundle.getBundle("domain").getString("verifydomain");
	String joindomain = ResourceBundle.getBundle("domain").getString("joindomain");
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<head>
<title>我的钱包-保证金</title>
</head>
<script type="text/javascript">
function showDetailInfo(url,lx,intype){
	$("#altMsg").html('');
	$.ajax({
	    url: "/walletMg/checkTuizhu",
	    type: "post",
	    data: {type:lx },
	    dataType : 'json',
	    success: function (data) {
	        if(data.state =="Failure"){
	        	$("#altMsg").html(data.message);
	        	win_ones('提取保证金','win_div_one','514px','240px');
	        }else if(data.state =="Success"){
	        	//认证点
	        	if (intype==2 || intype==3) {
	        		tuizhuPortWarn(url);
				}
	        	//非认证点
	        	else {
	        		tuizhuWarn(url,lx);	
	        	}
	        }
	    },
	    error: function () {
	        alert("数据提交失败！请稍后重试！");
	    }
	});
}

function tuizhuPortWarn(ul){
	$("#altMsg3").html('');
	$.ajax({
	    url: "/walletMg/tuizhuPortWarn",
	    type: "post",
	    data: {},
	    dataType : 'json',
	    success: function (data) {
	        if(data.state =="Failure"){
	        	tuizhuWarn(ul,'2');
	        } else {
	        	$("#altMsg3").html(data.message);
	        	win_three('提取保证金','win_div_three','400px','360px',ul)
	        }
	    },
	    error: function () {
	        alert("数据提交失败！请稍后重试！");
	    }
	});	
}

function tuizhuWarn(ul,tp){
	$("#altMsg2").html('');
	$.ajax({
	    url: "/walletMg/tuizhuWarn",
	    type: "post",
	    data: {type:tp},
	    dataType : 'json',
	    success: function (data) {
	        if(data.state =="Success"){
	        	$("#altMsg2").html(data.message);
	        	win_two('提取保证金','win_div_two','400px','400px',ul)
	        }
	    },
	    error: function () {
	        alert("数据提交失败！请稍后重试！");
	    }
	});
}

function win_ones(title,win_id,width,height){ //title 标题 win_id 弹窗ID  width 弹窗宽度 height 弹窗高度
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
	  	end: function(){}  
	});
}

function win_two(title,win_id,width,height,ul){ 
	var pagei = $.layer({
	    type: 1,  
	    btns: 2,
		btn: ['确定','取消'],
	    title: title,
	    border: [0],
		closeBtn: [0, true],
	    shadeClose: true,
	    area: [width,height], 
	    page: {dom : '#'+ win_id},
	  	end: function(){},  
		yes: function(){
	  		location.href=ul;
	  	}
	});
}

function win_three(title,win_id,width,height,ul){ 
	var pagei = $.layer({
    	type: 1,   
	    btns: 2,
		btn: ['确定','去审核根进'],
	    title: title,
	    border: [0],
		closeBtn: [0, true],
	    shadeClose: true,
	    area: [width,height], 
	    page: {dom : '#'+ win_id},
	  	end: function(){},
	  	yes: function(){
	  		tuizhuWarn(ul,'2');
	  	},no: function(){
	  		location.href="<%=verifydomain%>/yunSupplierMgt/batchsupplierlist";
	  	}
	});
}



</script>
<div class="fl conter_right mt_20  bg_white bor_si">
	<div class="wdqb_xxk ndfxs_1-2_border">
		<div class="tab_pc f16">
			<li class="now"><a href="javascript:;">保证金</a><i></i></li>
		</div>
	</div>

	<table class="mt_20 wdqb_table">
		<tbody>
			<tr class="wdqb_tr">
				<td><b class="ml_20">交易时间</b></td>
				<td><b>类型</b></td>
				<td><b>保证金</b></td>
				<td><b>状态</b></td>
				<td><b>服务期</b></td>
				<td><b>终止日期</b></td>
				<td><b>操作</b></td>
			</tr>
			<!-- 批发号去缴费 -->
			<c:if test="${null!=bsPassDeposit}">
				<tr>
					<td><div class="wdqb_div ml_20">
							<p><fmt:formatDate value="${bsPassDeposit.inTime}" type="both"/></p>
						</div></td>
					<td>
					<c:if test="${bsPassDeposit.inType==1}">批发号保证金</c:if>
					<c:if test="${bsPassDeposit.inType==2}">批发号认证点保证金</c:if>
					<c:if test="${bsPassDeposit.inType==3}">批发号整体进驻保证金</c:if>
					</td>
					<td>
						<c:choose>
							<c:when test="${not empty bsPassDeposit.bond}">${bsPassDeposit.bond}元</c:when>
							<c:otherwise>1000元</c:otherwise>
						</c:choose>
					</td>
					<td>未缴纳保证金</td>
					<td></td>
					<td></td>
					<td>
						<c:if test="${bsPassDeposit.inType==1}"><a class="ft_lan" href="<%=joindomain %>/batchSupplier/apply?bt=1" target="_blank">去缴纳</a></c:if>
						<c:if test="${bsPassDeposit.inType==2 || bsPassDeposit.inType==3}"><a class="ft_lan" href="<%=joindomain %>/batchSupplier/apply?bt=2" target="_blank">去缴纳</a></c:if>
					</td>
				</tr>
			</c:if>
			<!-- 工厂号去缴费 -->
			<c:if test="${null!=ysPassDeposit}">
				<tr>
					<td><div class="wdqb_div ml_20">
							<p><fmt:formatDate value="${ysPassDeposit.payTime}" type="both"/></p>
						</div></td>
					<td>工厂号保证金</td>
					<td>
						<c:choose>
							<c:when test="${not empty ysPassDeposit.bond}">${ysPassDeposit.bond}元</c:when>
							<c:otherwise>5000元</c:otherwise>
						</c:choose>
					</td>
					<td>未缴纳保证金</td>
					<td></td>
					<td></td>
					<td>
						<a class="ft_lan" href="<%=joindomain %>/supplier/apply" target="_blank">去缴纳</a>
					</td>
				</tr>
			</c:if>
			<!-- 批发号已退驻 -->
			<c:if test="${null!=bsTuizhuDeposit}">
				<tr>
					<td><div class="wdqb_div ml_20">
							<p><fmt:formatDate value="${bsTuizhuDeposit.inTime}" type="both"/></p>
						</div></td>
					<td>批发号保证金</td>
					<td>
						<c:choose>
							<c:when test="${not empty bsTuizhuDeposit.bond}">${bsTuizhuDeposit.bond}元</c:when>
							<c:otherwise>1000元</c:otherwise>
						</c:choose>
					</td>
					<td><c:if test="${bsTuizhuStatus==3 }">已提取保证金</c:if><c:if test="${bsTuizhuStatus!=3 }">退驻申请已审核</c:if></td>
					<td>至申退保证金前</td>
					<td>
						<div class="wdqb_div ml_20">
							<p><fmt:formatDate value="${bsProcessTime}" type="both"/></p>
						</div>
					</td>
					<td>
						<a class="ft_lan" href="<%=basePath %>walletMgt/applybond?type=2">查看详情</a>
					</td>
				</tr>
			</c:if>
			<!-- 工厂号已退驻 -->
			<c:if test="${null!=ysTuizhuDeposit}">
				<tr>
					<td><div class="wdqb_div ml_20">
							<p><fmt:formatDate value="${ysTuizhuDeposit.payTime}" type="both"/></p>
						</div></td>
					<td>工厂号保证金</td>
					<td>
						<c:choose>
							<c:when test="${not empty ysTuizhuDeposit.bond}">${ysTuizhuDeposit.bond}元</c:when>
							<c:otherwise>1000元</c:otherwise>
						</c:choose>
					</td>
					<td>
						<c:if test="${ysTuizhuStatus==3 }">已提取保证金</c:if><c:if test="${ysTuizhuStatus!=3 }">已申请提取保证金，等待系统处理</c:if>
					</td>
					<td><fmt:formatDate value="${endTime}" type="date"/></td>
					<td>
						<div class="wdqb_div ml_20">
							<p><fmt:formatDate value="${ysProcessTime}" type="both"/></p>
						</div>
					</td>
					<td>
						<a class="ft_lan" href="<%=basePath %>walletMgt/applybond?type=1">查看详情</a>
					</td>
				</tr>
			</c:if>
			<!-- 批发号 -->
			<c:if test="${null!=pshDeposit}">
				<tr>
					<td><div class="wdqb_div ml_20">
							<p><fmt:formatDate value="${pshDeposit.inTime}" type="both"/></p>
						</div></td>
					<td>批发号保证金</td>
					<td>
						<c:choose>
							<c:when test="${not empty pshDeposit.bond}">${pshDeposit.bond}元</c:when>
							<c:otherwise>1000元</c:otherwise>
						</c:choose>
					</td>
					<td>
						<c:choose>
							<c:when test="${bsTuizhuStatus==0 || bsTuizhuStatus==1 || bsTuizhuStatus==2}">
								已申请提取保证金，等待系统处理
							</c:when>
							<c:otherwise>
								您已缴纳保证金，产品正常销售中
							</c:otherwise>
						</c:choose>
					</td>
					<td>至申退保证金前</td>
					<td>
					</td>
					<td>
						<c:choose>
							<c:when test="${bsTuizhuStatus==0 || bsTuizhuStatus==1 || bsTuizhuStatus==2}">
								<a class="ft_lan" href="<%=basePath %>walletMgt/applybond?type=2">查看详情</a>
							</c:when>
							<c:otherwise>
								<a class="ft_lan" href="javascript:showDetailInfo('<%=basePath %>walletMgt/applybond?type=2',2,'${pshDeposit.inType}')">提取保证金</a>
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
			</c:if>
			<!-- 工厂号 -->
			<c:if test="${null!=gcDeposit}">
				<tr>
					<td>
						<div class="wdqb_div ml_20">
							<p><fmt:formatDate value="${gcDeposit.payTime}" type="both"/></p>
						</div>
					</td>
					<td>工厂号保证金</td>
					<td>
						<c:choose>
							<c:when test="${not empty gcDeposit.bond}">${gcDeposit.bond}元</c:when>
							<c:otherwise>1000元</c:otherwise>
						</c:choose>
					</td>
					<td>
						<c:choose>
							<c:when test="${ysTuizhuStatus==0 || ysTuizhuStatus==1 || ysTuizhuStatus==2}">
								已申请提取保证金，等待系统处理
							</c:when>
							<c:otherwise>
								您已缴纳保证金，产品正常销售中
							</c:otherwise>
						</c:choose>
					</td>
					<td><fmt:formatDate value="${endTime}" type="date"/></td>
					<td>
					</td>
					<td>
						<c:choose>
							<c:when test="${ysTuizhuStatus==0 || ysTuizhuStatus==1 || ysTuizhuStatus==2}">
								<a class="ft_lan" href="<%=basePath %>walletMgt/applybond?type=1">查看详情</a>
							</c:when>
							<c:otherwise>
								<a class="ft_lan" href="javascript:showDetailInfo('<%=basePath %>walletMgt/applybond?type=1',1,'')">提取保证金</a>
							</c:otherwise>
						</c:choose>
						
					</td>
				</tr>
			</c:if>
			<!-- 正式认证员 -->
			<c:if test="${null!=rzyDeposit}">
				<tr>
					<td><div class="wdqb_div ml_20">
							<p><fmt:formatDate value="${rzyDeposit.createTime}" type="both"/></p>
						</div></td>
					<td>正式认证员保证金</td>
					<td>${rzyBond}元</td>
					<td>您已缴纳保证金，已获得审核权限</td>
					<td>至申退保证金前</td>
					<td></td>
					<td></td>
				</tr>
			</c:if>
		</tbody>
	</table>
</div>
<!-- 弹窗 -->
<!-- 提取保证金1  入驻三个月，不能提取 -->
<div class="updata_tixian" style="display:none;" id="win_div_one">
  <div class="updata_tixian">
    <p class="tc f16 mt_30" id="altMsg"></p>  
  </div>
</div>

<!-- 提取保证金2 供应商提示-->
<div class="updata_tixian" style="display:none;" id="win_div_two">
  <div class="updata_tixian" style="text-indent:80px;" id="altMsg2"></div>
</div>

<!-- 提取保证金3 认证服务点提示 -->
<div class="updata_tixian" style="display:none;" id="win_div_three">
  <div class="updata_tixian" style="width:346px; padding-left:20px;" id="altMsg3"></div>
</div>

