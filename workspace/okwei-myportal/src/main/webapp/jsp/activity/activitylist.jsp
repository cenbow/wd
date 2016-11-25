<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="pg" uri="http://www.okwei.com/pagination"%>

<%@page import="com.okwei.util.DateUtils"%>
<%@ page import="java.util.ResourceBundle"%>
<%
	String paydomain = ResourceBundle.getBundle("domain").getString(
			"paydomain");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>限时抢购</title>
<link rel="stylesheet" href="../statics/css/glbdy.css" />
<link rel="stylesheet" href="../statics/css/mzh_dd.css" />
<link rel="stylesheet" href="../statics/css/jumei_usercenter.min.css" />
<script src="/statics/js/jquery-1.7.1.min.js"></script>
<script src="/statics/js/mzh_dd.js"></script>
<script src="/statics/js/layer/layer.min.js"></script>
<style>
    .conter_right tr{border-bottom: 1px solid #ddd;}
    .conter_right tr td{border-right: 1px solid #ddd;}
</style>
<script type="text/javascript">
    /** 弹窗调用函数 **/
    function win1(title,win_id,width,height) { //title 标题 win_id 弹窗ID  width 弹窗宽度 height 弹窗高度
    var pagei = $.layer({
            type: 1,   //0-4的选择,0：信息框（默认），1：页面层，2：iframe层，3：加载层，4：tips层。
            btns: 2,
            btn: ['去添加报名产品','取消'],
            title: title,
            border: [0],
            closeBtn: [0],
            closeBtn: [0, true],
            shadeClose: true,
            area: [width,height],
            page: {dom : '#'+ win_id},
            no : function(index) {
    			$("#"+win_id).hide();
    		},
            yes: function(){ 
            	if($("input[type='checkbox']").is(':checked')){
            		$.ajax({ 
            		    url: "/act/celebration",
            		    type: "post",
            		    dataType : 'json',
            		    success: function (data) { 
            				//alert("########");
            		        if(data.Statu == "Success"){ 
            		        	//alert(data.BaseModle);
            					window.location.href = window.basePath + "act/actProductPage?actid="+data.BaseModle;
            				    };
            		        },
            		    error: function () {
            		        //alert("数据提交失败！请稍后重试！");
            		    }
            		}); 
            		
            	}
            },
            end: function(){ $("#AddCount").hide()}
        });
    }
     
    $(function(){
    	$("a[name='sty']").parent().parent().attr("style","background: #fe4483;color: #fff;");
    });
   
</script>

</head>
<body style="background: #f3f3f3;">
	<div class="outermost">
		<form id="searcherForm" name="searcherForm" action="actlist">
			<div class="fr conter_right">
			
				<div class="zhuag_suv bor_si fl bg_white">
        			<div class="xh-shurk f14" style="height: auto; margin-left: 20px"> * 温馨提示：请广大供应商阅读<a href="#" onclick="win1('活动协议','win_div_1','1000','600')">828  微店网周年店庆活动协议</a>  *</div>
        			<div class="blank1"></div>
      			</div>
      
				<table class="conter_right fl bg_white bor_si f14 line_42 tc">
					<tr class="bg_f3"  style="border-bottom:1px solid #e7e7e7;">
						<th width="15%" class="tl" style="padding-left;">活动名称</th>
						<th width="15%" class="tl" style="padding-left;">活动要求</th>
						<th width="10%">报名截止日期</th>
						<th width="10%">活动展示日期</th>
						<th width="8%">活动状态</th>
						<th width="8%">操作</th>
					</tr>
					<c:forEach var="actModel" items="${pageRes.list}" varStatus="index">
						<tr  style="border-bottom:1px solid #e7e7e7;">
							<td class="tl" style="line-height: 26px;padding-left: 15px;">${actModel.title }</td>
							<td class="tl" style="line-height: 26px;padding-left: 15px;">${actModel.demand }</td>
							<td>${DateUtils.format(actModel.applyEndTime,"yyyy/MM/dd" )}</td>
							<td><p>${DateUtils.format(actModel.startTime ,"yyyy/MM/dd" )}-</p>
							<p>${DateUtils.format(actModel.endTime ,"yyyy/MM/dd" ) }</p></td>
							<td><c:choose>
								<c:when test="${actModel.stepState==0 }">
									未开始
								</c:when>
									<c:when test="${actModel.stepState==1 }">
									进行中
								</c:when>
									<c:when test="${actModel.stepState==2 }">
									已停止
								</c:when>
								</c:choose></td>
							<td><c:choose>
									<c:when test="${actModel.isApplied==1 && actModel.stepState==0 }">
										<c:choose>
										<c:when test="${actModel.type==1 }">
										<a name="sty" href="#" class="ft_lan" onclick="win1('活动协议','win_div_1','1000','600')">报名</a>
										</c:when>
										<c:otherwise>
										<a href="actProductPage?actid=${actModel.actId }" class="ft_lan">报名</a>
										</c:otherwise>
										</c:choose>
										<a href="editlist?actid=${actModel.actId }" class="ft_lan">编辑</a>
										<a href="actdetail?actid=${actModel.actId }" name="detail" class="ft_lan">详情</a>
									</c:when>
									<c:when test="${actModel.isApplied==0 && actModel.stepState==0 }">
										<c:choose>
										<c:when test="${actModel.type==1 }">
										<a name="sty" href="#" class="ft_lan" onclick="win1('活动协议','win_div_1','1000','600')">报名</a>
										</c:when>
										<c:otherwise>
										<a href="actProductPage?actid=${actModel.actId }" class="ft_lan">报名</a>
										</c:otherwise>
										</c:choose>
										<a href="actdetail?actid=${actModel.actId }" name="detail" class="ft_lan">详情</a>
									</c:when>
									<c:otherwise>
										<a href="actdetail?actid=${actModel.actId }" name="detail" class="ft_lan">详情</a>
									</c:otherwise>
								</c:choose></td>
						</tr>
					</c:forEach>
				</table>
			</div>
			<!-- 分页 -->
			<div class="pull-right">
				<pg:page pageResult="${pageRes}" />
			</div>
		</form>

	</div>
	
	<!-- 保存成功 -->
<div class="fl mzh_100 f14 none" id="win_div_1">
    <div class="mzh_100 fl">
        <h2 class="ft_c3 mzh_100 ml_10 line_30 tc">828  微店网周年店庆活动协议</h2>
    </div>
    <div class="mzh_100 f14 mt_20" style="height: 350px;;overflow-y: scroll;overflow-x: hidden;">
        <p class="fw_b">本协议由同意并承诺遵守本协议规定使用微店网服务的法律实体（下称“商户”或“乙方”）、微店网及其关联公司深圳市云商微店网络技术有限公司（下称“甲方”），本协议具有合同效力。</p>
        <p class="fw_b">一、协议内容及生效：</p>
        <p>（一）本协议内容包括协议正文及所有微店网已经发布的或将来可能发布的各类规则。所有规则为协议不可分割的一部分，与协议正文具有同等法律效力。</p>
        <p>（二）商户签署或在线接受本协议则本协议立即生效，经过微店网审核通过，本协议即在商户和微店网之间产生法律效力。</p>
        <p class="mt_20 fw_b">二、商户的声明与保证</p>
        <p>（一）其承诺遵守本协议，并保证发布于微店网的所有信息真实、准确，符合相关法律法规及微店网规则。</p>
        <p>（二）其对其发布于微店网的交易信息中所涉商品有合法的销售权。</p>
        <p class="mt_20 fw_b">（三）其交易活动中采取全额返还规则，其中商户最低赞助20%，微店网最高补贴80%。微店网即将开放城主联盟系统，商户赞助比例高的将优先进入系统，享受全国2830各县分销的权利。</p>
        <p>（四）其活动商品价格<b>不能高于其同类同款商品在第三方电商</b>平台售价（如天猫、京东、苏宁等），在活动结束后60天内不能对本次活动价格进行调整，不得虚高骗取补贴，一经发现取消其活动参与资格。</p>
        <p>（五）其活动商品必须如实申报库存，如库存不足所产生的订单不能发货的，所造成的损失由商户负全责，并针对<b>未发货的订单追加20%罚款，在其货款中扣除。</b></p>
        <p>（六）其活动商品<b>48小时未发货</b>并受到消费者投诉的，<b>除补发货品外，针对未发货的订单追加20%罚款，在其货款中扣除。</b>经与买家协商同意延迟发货，此条款不成立。</p>
        <p>（七）其活动商品在买家签收货物7天内，如买家主观原因不愿完成本次交易，商户承诺同意按照本协议之约定向买家提供<b>7天无理由退换货服务。</b></p>
        <p>（八）其活动商品在买家收到货物后，<b>经权威机构检测该商非正品，消费者有权起诉商户，微店网免责</b></p>
        <p class="mt_20 fw_b">三、微店网的权利和义务：</p>
        <p>（一）微店网履行活动组织发起方，有对本次活动的解释权。</p>
        <p>（二）微店网有义务对本次活动进行宣传与推广。</p>
        <p>（三）微店网有权利针对违反《微店网服务协议》进行索赔和责罚。</p>
        <p class="mt_20 fw_b">四、协议的终止：</p>
        <p>（一）自然终止：本协议终止条件发生或实现，则本协议将同时终止。</p>
        <p>（二）微店网单方解除权：如商户违反微店网的任何规则或本协议中的任何承诺或保证，微店网都有权立刻终止本协议，且按有关规则对商户进行处罚。 </p>
        <p class="mt_20 fw_b">五、反不正当谋利</p>
        <p>商家不得通过不正当手段（包括但不限于向微店网工作人员及其关联人士提供财物、消费、款待及商业机会，或<b>刷单和恶意骗补等行为）谋取利益，情节严重的移交司法机关处理。</b></p>
        <p class="mt_20 fw_b">六、争议解决及其他：</p>
        <p>（一）本协议之解释与适用，以及与本协议有关的争议，均应依照中华人民共和国法律予以处理，并以深圳市当地人民法院为第一审管辖法院。</p>
        <p>（二）如本协议的任何条款被视作无效或无法执行，则上述条款可被分离，其余部份则仍具有法律效力。</p>
        <p>（三）微店网于商户过失或违约时放弃本协议规定的权利的，不得视为其对商户的其他或以后同类之过失或违约行为弃权。</p>
        <div class="blank"></div>
    </div>
    <div class="mzh_100 tc mt_10">
        <input type="checkbox" id="mzh_hdxy">
        <label for="mzh_hdxy">我已阅读并同意828微店网周年店庆活动协议</label>
    </div>
</div>

	<script language="javascript" type="text/javascript">
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
