<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>	
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<head>
<title>添加银行卡</title>
<style type="text/css">
	.error{color:#F00;}
	.jbzl_dl{display:block;}
</style>
<script type="text/javascript">
$(function(){
	jQuery.validator.addMethod("info", function() {  
		$.post('/bankCardMgt/getCardInfo',{'cardId':$("#cardId").val()},function(data){
			$("#banckName").val(data.bank_name);
			$("#cardType").val(data.card_type);
			var typeName,msg;
			if(data.card_type==2){
				typeName = "储蓄卡";
				msg = data.bank_name+" "+typeName;
			}else if(data.card_type==3){
				typeName = "信用卡";
				msg = data.bank_name+" "+typeName;
			}else{
				msg = data.ret_msg;
			}
			$("#cardTip").hide();
			$("#cardTypeDiv").html(msg).show();
		});
		return true;
	}, "格式错误"); 
	
	var rules = { // 定义验证规则,其中属性名为表单的name属性
		banckCard : {
			required : {
				depends : function() {
					$(this).val($.trim($(this).val()));
					return true;
				}
			},
			creditcard : true,
			digits : true,
			remote : "/bankCardMgt/checkCard",
			info : true
		},
		password : {
			required : true,
			remote : "/bankCardMgt/checkPwd"
		}
	};

	var messages = { // 自定义验证消息
		banckCard : {
			creditcard : "请输入合法的银行卡号",
			required : "请输入银行卡号!",
			digits : "请输入数字!",
			remote : "该银行卡号已经存在",
			info : ""
		},
		password : {
			required : "请输入微店网支付密码!",
			remote : "支付密码错误"
		}
	};
	
	var validator = $("#mainForm").validate({
		debug : false, // 调试模式取消submit的默认提交功能
		errorClass : "error",// 默认为错误的样式类为：error
		focusInvalid : false,
		onkeyup : false,
		submitHandler : function(form) {
			var _from = $(form); 
			$("#submitBtn").attr("disabled","disabled");
			$.post(_from.attr("action"), _from.serializeJson(),
				function(data) {
					if (!data.error) {
						alert("保存成功！",true);
						$("#navTab").load("/bankCardMgt/bankCard.ajax");
					}else{
						alert(data.msg);
					}
					$("#submitBtn").removeAttr("disabled");
				}, "json");
		},
		rules : rules,
		messages : messages
	});
	
	$("#mzh_rzfwd").click(function(){
    	if($(this).attr("class") == "gygl_xxk_no"){
	    	var html ='当前位置：<a href="/walletMgt/index">钱包管理</a>><span>银行卡</span>';
			$("#breadcrumb").empty().append(html);
			$("#leftMenu li.now").removeClass("now");
			$("#leftMenu li").eq(1).addClass("now")
			$("#navTab").load("/companyAccount/info.ajax", function(data){});
		}
    })
	
	
});
	
</script>
</head>
<body class="bg_f3">
	<div class="fl conter_right bg_white bor_si">
        <div class="gygl_xxk_t f16 ndfxs_1-2_border">
            <div class="gygl_xxk_yes" id="mzh_pfh">个人账户<span style="width: 60px;"></span></div>
            <c:if test="${ userinfo.yunS ==1 || userinfo.batchS ==1 }">
            	<div class="gygl_xxk_no" id="mzh_rzfwd">对公账户<span style="width:60px;"></span></div>
            </c:if>
        </div>
        <!-- 选项卡 -->
        <div class="gygl_xxk_t f16 ndfxs_1-2_border">
            <div style="color: #000;" name="mzh_xxk" class="gygl_xxk_yes">添加银行卡<span></span></div>
        </div>
          
		<div class="jbzl">
	 		<form id="mainForm" action="<%=basePath%>bankCardMgt/add" method="post">
	 			<input type="hidden" name="banckName" id="banckName"/>
	 			<input type="hidden" name="banckMark" id="banckMark"/>
	 			<input type="hidden" name="cardType" id="cardType"/>
	 		
				<dl class="jbzl_dl f14">
		    		<dd>持卡人：</dd>
		    		<dt><input type="text" name="name" value="${userName}" readonly="readonly" class="fl btn_nosur42 w250"></dt>
		   			<dt style="margin-left:180px; height:20px; clear:both;"><span class="wjmm_3_23 lh_40 ft_c9">为保证您的账户安全，只能添加认证用户本人的银行卡</span></dt>
				</dl>
				<dl class="jbzl_dl f14">
				    <dd>银行账号：</dd>
				    <dt><input type="text" class="fl btn_h42 w250" name="banckCard" id="cardId"></dt>
				    <dt style="margin-left:180px; height:20px;clear:both;">
				   	<span class="wjmm_3_23 lh_40 ft_c3" style="display: none;" id="cardTypeDiv">建设银行 储蓄卡</span>
			        <span class="wjmm_3_23 lh_40 ft_c9" id="cardTip">输入卡号后自动识别银行和卡种</span>
				    </dt>
				</dl>
				<dl class="jbzl_dl f14">
				    <dd>支付密码：</dd>
				    <dt><input type="password" class="fl btn_h42 w250" name="password"></dt>
				</dl>
				<dl class="jbzl_dl f14">
		    		<dd></dd>
		    		<dt><button type="submit" id="submitBtn" class="jbzl_dl_qrdz">确定</button><button id="quxiao" type="button" onclick="javascript:window.location.reload();" class="jbzl_dl_qx  ml_30" style="width:90px;">取消</button></dt>
		    	</dl>
	    	</form>
		</div>
	</div>
</body>
</html>
