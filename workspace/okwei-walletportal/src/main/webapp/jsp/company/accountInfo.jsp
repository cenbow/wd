<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="pg" uri="http://www.okwei.com/pagination"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<head>
<title>我的钱包-银行卡</title>
<script type="text/javascript">

$(function(){
	
	//删除银行卡
	$("#bankdiv").on("click","div.yhk_yhk_2_close",function(){
		if(confirm("确定要删除该银行卡？")){
			var t = $(this);
			var id = t.attr("value");
			$.get("/bankCardMgt/remove/"+id,function(data){
	   			if(!data.error){
	   				t.closest(".yhk_yhk").remove();
	   	  			alert("删除成功！");
	   			}     
   			},"json");
		}
	});
	
	$("#addBtn").click(function(){
		$.get("/bankCardMgt/checkWallet",function(data){
   			if(!data.error){
   		        var url = $("#addBtn").attr("ahref");
   		     	var to = $("#" + $("#addBtn").attr("to"));
   		     	to.load(url+".ajax", function(data){});
   			}else{
   				var layerIndex = $.layer({
   					title:"实名认证",
   					shade: [0.2, '#000'],
   				    area: ['auto','auto'],
   				    border: [0],
   				    dialog: {
   				        msg: "微钱包已更新升级,实名认证后方可进行安全购物及提现!",
   				        btns: 2,                    
   				        type: 4,
   				        btn: ["马上认证","暂不认证"],
   				        yes: function(){
   				        	location.href=$("#txtSetUrl").val()+"/userInfo/settings?set=name";
   				        }	
   				    }
   				});	
   			}    
		},"json");
	});
	
	$("#mzh_pfh").click(function(){
		if($(this).attr("class") == "gygl_xxk_no"){
	        var html ='当前位置：<a href="/walletMgt/index">钱包管理</a>><span>银行卡</span>';
			$("#breadcrumb").empty().append(html);
			$("#leftMenu li.now").removeClass("now");
			$("#leftMenu li").eq(1).addClass("now")
			$("#navTab").load("/bankCardMgt/bankCard.ajax", function(data){});
		}
    })
    
    $("#modifyBtn").click(function(){
    	var url = $("#modifyBtn").attr("ahref");
     	var to = $("#" + $("#modifyBtn").attr("to"));
     	to.load(url+".ajax", function(data){});
    	
    })
    
    
});
	
</script>
</head>
<body class="bg_f3">
	<div style="border: 0px; background: none;" class="fr conter_right">
		<div id="bankdiv" class="fl conter_right mt_20  bg_white bor_si">
			<div class="gygl_xxk_t f16 ndfxs_1-2_border">
                <div class="gygl_xxk_no" id="mzh_pfh">个人账户<span style="width: 60px;"></span></div>
                <c:if test="${ userinfo.yunS ==1 || userinfo.batchS ==1 }">
                	<div class="gygl_xxk_yes" id="mzh_rzfwd">对公账户<span style="width:60px;"></span></div>
                </c:if>
            </div>
            <c:choose>
            	<c:when test="${accountInfo!=null}">
            		<c:choose>
            			<c:when test="${accountInfo.state==0 }"><div class="mzh_kk_gg">对公账户将在1-3个工作日内进行审核，在此期间暂不可用，请耐心等待</div></c:when>
            			<c:when test="${accountInfo.state==2 }"><div class="mzh_kk_gg"><b>审核不通过</b><span class="ml_30">理由：${accountInfo.reason }</span></div></c:when>
            		</c:choose>
	                <div class="fl mzh_width_100">
	                    <dl class="mzh_add_dgzh">
	                        <dd>开户名：</dd>
	                        <dt>${accountInfo.name}</dt>
	                    </dl>
	                    <dl class="mzh_add_dgzh">
	                        <dd>对公银行账户：</dd>
	                        <dt>${accountInfo.banckNo }</dt>
	                    </dl>
	                    <dl class="mzh_add_dgzh">
	                        <dd>开户银行：</dd>
	                        <dt>${accountInfo.banckName }</dt>
	                    </dl>
	                    <dl class="mzh_add_dgzh">
	                        <dd>开户银行所在地：</dd>
	                        <dt>${banckArea}</dt>
	                    </dl>
	                    <dl class="mzh_add_dgzh">
	                        <dd>开户银行支行名称：</dd>
	                        <dt>${accountInfo.branchName }</dt>
	                    </dl>
	                    <c:if test="${accountInfo.state==2 || accountInfo.state==1}">
		                    <dl class="mzh_add_dgzh">
		                        <dd></dd>
		                        <dt><a class="jbzl_dl_qrdz mr_20" style="padding: 0 10px;" id="modifyBtn" href="javascript:;" ahref="<%=basePath%>companyAccount/addAcount" to="navTab">修改对公账户</a></dt>
		                    </dl>
	                    </c:if>
	                </div>
	                <div class="blank"></div>
	                <div class="blank"></div>
            	</c:when>
            	<c:otherwise>
					<div class="yhk_yhk_0">
						<a id="addBtn" href="javascript:;" ahref="<%=basePath%>companyAccount/addAcount" to="navTab"><img src="/statics/pic/addyhk_3_25.png"></a>
					</div>
            	</c:otherwise>
            </c:choose>
			<div class="blank"></div>
		</div>
	</div>
</body>
</html>
