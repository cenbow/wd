<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 
<%@ taglib prefix="pg" uri="http://www.okwei.com/pagination"%>

<%@ page import="java.util.ResourceBundle"%>
<%
    String paydomain = ResourceBundle.getBundle("domain").getString("paydomain");
    String walletdomain = ResourceBundle.getBundle("domain").getString("walletdomain");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>已审核的落地店列表</title>
<link rel="stylesheet" type="text/css" href="/statics/css/jumei_usercenter.min.css" />
<style>
        .gygl_xxk_table_cz_td{border-right: 1px solid #e7e7e7;vertical-align: inherit;}
        .gygl_xxk_table td { padding: 20px;}
        .tab_pc li{ list-style:none; display:block; float:left;position:relative; }
		.tab_pc li a{ display:block; height:50px; line-height:50px; margin:0 20px;}
		.tab_pc li.now a{color:red;}
		.tab_pc li.now  i{background: url("/statics/pic/xxk_3_19.jpg") center no-repeat;position: absolute;left:35%;bottom: -1px;height: 10px; display:block; width:19px;}
		.tab_pc ul{clear:both;}
		.left_xuanzs ul li.yes_bgs a{color: #fff;}
</style>
<link href="<%=request.getContextPath()%>/statics/js/layer/skin/layer.css" rel="stylesheet" type="text/css" />
<script type = "text/javascript" src = "<%=request.getContextPath()%>/statics/js/layer/layer.min.js"></script>
</head>
<body style="background: #f3f3f3;">
<c:if test="${Inload_Query==false }">
    <jsp:include page="/jsp/common/notpermit.jsp"/>
</c:if>
<c:if test="${Inload_Query==true }">
<form id="searcherForm" name="searcherForm" action="/productShop/getDownstreamStoreList/1" onsubmit="return false;">

<div class="fr conter_right">
    <div class="zhuag_suv bor_si fl bg_white">
        <div class="wdqb_xxk ndfxs_1-2_border">
            <div class="tab_pc f16">
                <li class="now" name="name_xxk">
                    <a href="#">我的落地店</a><i style="left:43%;"></i>
                    
                </li>
            </div>
        </div>
        <div class="oneci_ztai fl">
            <div class="left_font tr fl f12 ft_c9">状态：</div>
            <div class="left_xuanzs fl f12">
                <ul>
                    <li class="yes_bgs" name="mzh_4_7_yes"><a href="/productShop/getDownstreamStoreList/1">已审核（<b class="left_xuanzs_b">${auditedAmount}</b>）</a></li>
                    <li name="mzh_4_7_yes"><a href="/productShop/getDownstreamStoreList/2">已取消（<b class="left_xuanzs_b">${canceledAmount}</b>）</a></li>
                    <li><input type="submit" value="添加落地店" class="dis_b ml_20 fl btn_sel28_pre bor_cls shou" onclick="addProductShopDivLayer('添加落地店','addProductShopDiv')"></li>
                </ul>
            </div>
        </div>
    </div>

    <!-- 已审核 -->
    <div class="gygl_xxk mt_20 bor_le bor_ri" id="id_luodidian_0" style="display:block;">
        <table class="gygl_xxk_table bor_cls">
            <tr class="ndfxs_1-2_color ndfxs_1-2_border lh_40 fw_b td_no">
                <td>落地店</td>
                <td>联系电话</td>
                <td>地区</td>
                <td>上级</td>
                <td>操作</td>
            </tr>
          <c:if test="${fn:length(pageResult.list)<1}">
          		<tr style="height:200px;">
					<td colspan="5" style="text-align:center">暂无相关数据</td>
				<tr>
          </c:if>
          <c:forEach items="${pageResult.list}" var="res">
	            <tr class="ndfxs_1-2_border">
	                <td class="gygl_xxk_table_cz_td">
	                    <div class="gygl_xxk_table_cz_qx ">
	                        <img src="${res.weiPicture}"/>
	                    </div>
	                    <div class="fl tl ft_c3">
	                        <p>店铺名称：${res.shopName}</p>
	                        <p>姓名：${res.linkname}</p>
	                        <p>微店号：${res.weiId}</p>
	                        <p>招商需求名称：${res.demandName}</p>
	                    </div>
	                </td>
	                <td class="gygl_xxk_table_cz_td">
	                    <span class="tc ft_c3 f14">${res.phone}</span>
	                </td>
	                <td class="gygl_xxk_table_cz_td tc">
	                    <span class="tc ft_c3 f14">${res.locationStr}</span>
	                </td>
	                <td class="gygl_xxk_table_cz_td">
	                    <span class="tc ft_c3 f14">上级：${res.parentAgentName}</span>
	                </td>
	                <td class="gygl_xxk_table_cz_td p10">
	                    <p>
		                    <c:if test="${res.isPayReward==1}">
		                    	<span style="color:gray">已支付</span>
		                    </c:if>
		                    <c:if test="${res.isPayReward==0}">
			                    <a href="javascript:void(0);getOrderNo1(${res.shopId})" class="ft_lan">支付悬赏</a>
		                    </c:if>
	                    </p>
	                    <p><a href="#" class="ft_lan" onclick="cancelProductShopLayer('取消落地店资格原因','cancelReasonDIV',${res.shopId},this)">取消落地店</a></p>
	                </td>
	            </tr>
          </c:forEach>
        </table>
		</div>
	</div>
	
	<!-- 分页 -->
	<div class="pull-right">
		<pg:page pageResult="${pageResult}" />
	</div>
				
</form>

<!-- 添加落地店 -->
<div class="updata_tixian" style="display:none;" id="addProductShopDiv">
<form method="post" id="addProductShopForm">
    <div class="mzh_100 f14">
        <span class="fl" style="line-height: 26px;">微店号：</span>
        <input type="text" class="fl w130 xzgys_input" placeholder="请输入要添加为代理商的微店号" name="weiId"/>
    </div>
    <div class="mzh_100 f14" style="line-height: 26px;">
        <span class="fl">店铺名称：</span>
        <span class="fl" id="shopNameID"></span>
        <br>
        <span id="promptID" style="color:red"></span>
    </div>
    <div class="blank1 bor_bo"></div>
    <div class="mzh_100 f14 mt_10">
        <span class="fl" style="line-height: 26px;">选择代理招商需求：<span class="ft_c9">（多选）</span>
        <input type="checkbox" id="checkAllDemand" onclick="$('[name=demandIdArr]').prop('checked',this.checked)"/>
        <label for="checkAllDemand">全选</label></span>
        <div class="mzh_100">
        <c:if test="${fn:length(demandList)<1}">
        	<div class="fl ml_20 mt_5">该平台号暂无招商需求</div>
        </c:if>
        <c:forEach items="${demandList}" var="demand">
            <div class="fl ml_20 mt_5">
                <input type="checkbox" value="${demand.demandId}" name="demandIdArr" id="check_${demand.demandId }" onclick="singleCheck()"/>
                <label  onclick="labelClick('${demand.demandId }')">${demand.title}</label>
            </div>
        </c:forEach>
        </div>
    </div>
     <div class="blank1 bor_bo"></div>
    <div class="mzh_100 f14 mt_10">
        <span class="fl ft_c6" style="line-height: 26px;">注意：添加成功后，该落地店需到收货地址中设置默认落地店地址；</span>
    </div>
    
</form>
</div>

<!-- 取消落地原因 -->
<div class="updata_tixian" style="display:none;" id="cancelReasonDIV">
    <div class="fl">
        <textarea class="fl mzh_100 f14" style="height: 100px;width: 480px;" placeholder="请简述您取消该落地店资格的原因" id="cancelReason"></textarea>
    </div>
</div>

<script type="text/javascript">
$(function(){
	//分页
   	var page = new Pagination( {
		formId: "searcherForm",
		isAjax : true,
		targetId : "navTab",
		submitId:"searchBtn",
		validateFn:function(){
			return true;
		}
	});
	page.init();
});

//取消落地店弹层
function cancelProductShopLayer(title,win_id,shopId,_this){ //title 标题 win_id 弹窗ID  width 弹窗宽度 height 弹窗高度
	var pagei = $.layer({
    type: 1,   //0-4的选择,0：信息框（默认），1：页面层，2：iframe层，3：加载层，4：tips层。 
    btns: 2,
	btn: ['提交','取消'],
    title: title,
    border: [0],
    closeBtn: [0],
	closeBtn: [0, true],
    shadeClose: false,
    area: ['600','400'], 
    page: {dom : '#'+ win_id},
	yes: function(){
		if(cancelProductShop(shopId,_this)){
			layer.close(pagei);
		}
	}
	});
}
//取消落地店
function cancelProductShop(shopId,_this){
	if($.trim($("#cancelReason").val())==""){
		alert("取消原因不能为空");
		return false;
	}
	$.ajax({
		url:"/productShop/cancelProductShop",
		type:"post",
		data:{"shopId":shopId,"cancelReason":$("#cancelReason").val()},
		success:function(result){
	    	var result = eval(result);
		    if(result != null)
	    	{
	    		if(result.Statu == "Success")
	    		{   
	    			alert("成功!",true);
	    			location.href = "/productShop/getDownstreamStoreList/1";
	    			$("#cancelReason").val("");
	    			$(_this).parent().parent().parent().remove();
	    		}
	    		else
	    		{
	    			alert(result.StatusReson);
	    		}
	    	}
		}
	})
	return true;
}


function labelClick(demandId){
	if($("#check_"+demandId).attr("checked")){
		$("#check_"+demandId).attr("checked",false);
	}else{
		$("#check_"+demandId).attr("checked",true);
	}
	singleCheck(); 
}



function getOrderNo1(ss){
	  $.ajax({
		    url: "/productShop/getOrderNO",
		    type: "post",
		    data:{cls:ss},
		    dataType : 'json',
		    success: function (data) {
		    	var i=0;
		        if(data.state == 1){ 
		        	//跳转支付
		        	window.location.href = "<%=paydomain %>/pay/cashier?orderNo="+data.msg ;
		        }else{
		        	 alert(data.msg);
		        }
		       
		    },
		    error: function () {
		        alert("数据提交失败！请稍后重试！");
		    }
		});  
	
}
</script>
<script type = "text/javascript" src = "<%=request.getContextPath()%>/statics/js/productShop/productshop.js"></script>
</c:if>
</body>
</html>