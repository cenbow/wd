<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="pg" uri="http://www.okwei.com/pagination"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>自己添加的子供应商</title>
<link rel="stylesheet" type="text/css"
	href="/statics/css/jumei_usercenter.min.css" />
<style>
.ndfxs_1-2_qq {
	width: 15%;
	text-indent: 22px;
}

.ndfxs_1-2_dp {
	width: 13%;
}

.ndfxs_1-2_ss {
	width: 8%;
}
</style>
<link
	href="<%=request.getContextPath()%>/statics/js/layer/skin/layer.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/statics/js/layer/layer.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/statics/js/district.js"></script>
<script type="text/javascript">

var dis;
var edis;
$(function(){
	//初始化省市区列表
	dis = new district();
	dis.init('#selProvince', '#selCity', '#selDistrict');
	edis = new  district();
	edis.init('#eProvince', '#eCity', '#eDistrict');
})

/** 弹窗调用函数 **/
function wine(title,win_id,width,height,accountid){ //title 标题 win_id 弹窗ID  width 弹窗宽度 height 弹窗高度

	$.ajax({
		url : "/childrenAccount/getChilrenSupplierDetail?childrenId="+accountid,
		type : "get",
		success : function(result) {
			var result = eval(result);
			if (result != null) {
				if (result.Statu == "Success") {
					 $("#haccountid").val("");
					$("#haccountid").val(accountid);
					$("#eaccountid").empty();
					$("#eaccountid").append(accountid);
					$("#epassword").val("");
					$("#epassword").val(result.BaseModle.password);
					$("#ecompanyName").val("");
					$("#ecompanyName").val(result.BaseModle.supplierName);
					$("#elinkName").val("");
					$("#elinkName").val(result.BaseModle.linkName);
					$("#ephone").val("");
					$("#ephone").val(result.BaseModle.phone);
					$("#eaddress").val("");
					$("#eaddress").val(result.BaseModle.address);
					
					edis.bdbycode(result.BaseModle.province,result.BaseModle.city,result.BaseModle.area); 
				} else {
					alert(result.StatusReson);
				}
			}
		}
	})
	
	var pagei = $.layer({
   type: 1,   //0-4的选择,0：信息框（默认），1：页面层，2：iframe层，3：加载层，4：tips层。
   btns: 2,
	btn: ['确定','取消'],
   title: title,
   border: [0],
   closeBtn: [0],
	closeBtn: [0, true],
   shadeClose: true,
   area: [width,height],
   page: {dom : '#'+ win_id},
 	end: function(){ $("#AddCount").hide()},
	yes:function(index){
		
		$.ajax({
			url : "/childrenAccount/editChildrenSupplier",
			type : "post",
			data : $("#editform").serialize(),
			success : function(result) {
				var result = eval(result);
				if (result != null) {
					if (result.Statu == "Success") {
						alert("成功!", true);
						$("#haccountid").val("");
						$("#eaccountid").empty();
						$("#epassword").val("");
						$("#ecompanyName").val("");
						$("#elinkName").val("");
						$("#ephone").val("");
						$("#eaddress").val("");
						location.href = "/childrenAccount/getSelfSCList";
					} else {
						alert(result.StatusReson);
					}
				}
			}
		})
	}
	});
}
/** 弹窗调用函数 **/
function wint(title,win_id,width,height,accountid){ //title 标题 win_id 弹窗ID  width 弹窗宽度 height 弹窗高度
	var pagei = $.layer({
   type: 1,   //0-4的选择,0：信息框（默认），1：页面层，2：iframe层，3：加载层，4：tips层。
   btns: 2,
	btn: ['确定','取消'],
   title: title,
   border: [0],
   closeBtn: [0],
	closeBtn: [0, true],
   shadeClose: true,
   area: [width,height],
   page: {dom : '#'+ win_id},
 	end: function(){ $("#AddCount").hide()},
	yes:function(index){
		deleteStaffChildrenAjax(accountid);
	}
	});
}
/** 弹窗调用函数 **/
function win1(title,win_id,width,height){ //title 标题 win_id 弹窗ID  width 弹窗宽度 height 弹窗高度
	var pagei = $.layer({
   type: 1,   //0-4的选择,0：信息框（默认），1：页面层，2：iframe层，3：加载层，4：tips层。
   btns: 1,
	btn: ['提交'],
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

/** 弹窗调用函数 **/
function win5(title,win_id,width,height){ //title 标题 win_id 弹窗ID  width 弹窗宽度 height 弹窗高度
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
/** 弹窗调用函数 **/
function win2(title,win_id,width,height){ //title 标题 win_id 弹窗ID  width 弹窗宽度 height 弹窗高度
	var pagei = $.layer({
   type: 1,   //0-4的选择,0：信息框（默认），1：页面层，2：iframe层，3：加载层，4：tips层。
   btns: 0,
	btn: ['提交'],
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
/** 弹窗调用函数 **/
function win3(title,win_id,width,height){ //title 标题 win_id 弹窗ID  width 弹窗宽度 height 弹窗高度
	var pagei = $.layer({
   type: 1,   //0-4的选择,0：信息框（默认），1：页面层，2：iframe层，3：加载层，4：tips层。
   btns: 1,
	btn: ['保存'],
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
/** 弹窗调用函数 **/
function win15(title,win_id,width,height){ //title 标题 win_id 弹窗ID  width 弹窗宽度 height 弹窗高度
	dis.bdbycode(0,0,0);
	var pagei = $.layer({
   type: 1,   //0-4的选择,0：信息框（默认），1：页面层，2：iframe层，3：加载层，4：tips层。
   btns: 2,
	btn: ['保存','取消'],
   title: title,
   border: [0],
   closeBtn: [0],
	closeBtn: [0, true],
   shadeClose: true,
   area: [width,height],
   page: {dom : '#'+ win_id},
 	yes: function(index){ 
 		$.ajax({
			url : "/childrenAccount/selfAddSupplierChildren",
			type : "post",
			data : $("#addForm").serialize(),
			success : function(result) {
				var result = eval(result);
				if (result != null) {
					if (result.Statu == "Success") {
						alert("成功!", true);
						location.href = "/childrenAccount/getSelfSCList";
					} else {
						alert(result.StatusReson);
					}
				}
			}
		})
     }
	});
}
/** 弹窗调用函数 **/
function win4(title,win_id,width,height){ //title 标题 win_id 弹窗ID  width 弹窗宽度 height 弹窗高度
	var pagei = $.layer({
   type: 1,   //0-4的选择,0：信息框（默认），1：页面层，2：iframe层，3：加载层，4：tips层。
   btns: 2,
	btn: ['审核通过','审核不通过'],
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
</head>
<body class="bg_f3">
<c:if test="${SupplierList==false }">
 <jsp:include page="/jsp/common/notpermit.jsp"/>
</c:if>

<c:if test="${SupplierList==true }">
	<form id="searcherForm" name="searcherForm"
		action="/childrenAccount/getSelfSCList"
		onsubmit="return false;">

		 <div class="fr conter_right" style="border: 0px;background: none;">
            <div class="fl conter_right  bg_white bor_si">
               <%--  <div class="wdqb_xxk ndfxs_1-2_border">
                    <div class="tab_pc f16">
                        <c:if test="${Recommend==true }">
					   <li name="name_xxk"><a href="/childrenAccount/getRecommandSCList">推荐供应商</a><i
							style="left: 43%;"></i></li>
					   </c:if>
						<c:if test="${SupplierList==true }">
						<li  class="now" name="name_xxk"><a href="/childrenAccount/getSelfSCList">我的供应商</a><i
							style="left: 43%;"></i></li>
						</c:if>
                        <li name="name_xxk">
                            <a href="#">我关注的店铺/上游供应</a><i style="left:45%;"></i>
                        </li>
                    </div>
                </div> --%>
                <!-- 我的供应商 -->
                <div class="wdgz_div">
                    <a class="wdgz_div_yes" name="name_gys" href="/childrenAccount/getSelfSCList">自己添加的供应商</a>
                    <a class="wdgz_div_no" name="name_gys" href="/childrenAccount/getRecSCList">推荐的供应商</a>
                    <div class="blank2 bor_to"></div>
                    <!-- 自己添加的供应商 -->
                    <div class="wdgz_div_zjtjdgys">
                        <div class="jbzl_dl_qrdz" style="height: 27px; margin-left:20px; line-height: 27px;width: 80px;" onclick="win15('新增供应商','win_div_1','520px','450px')">新增供应商</div>
                        <table class="conter_right_xx_cz_table mt_10">
                            <tr class="ndfxs_1-2_color ndfxs_1-2_border">
                                <td class="ndfxs_1-2_xh">供应商账号</td>
                                <td class="ndfxs_1-2_xh">密码</td>
                                <td class="ndfxs_1-2_fxs">公司名称</td>
                                <td class="ndfxs_1-2_ss">负责人</td>
                                <td class="ndfxs_1-2_qq">联系电话</td>
                                <td class="ndfxs_1-2_qq">地址</td>
                                <td class="ndfxs_1-2_ss">操作</td>
                            </tr>
                            <c:if test="${fn:length(pageResult.list)<1}">
								<tr style="height: 200px;">
									<td colspan="9" style="text-align: center">暂无相关数据</td>
								<tr>
							</c:if>
							
							<c:forEach items="${pageResult.list}" var="res">
                            <tr class="ndfxs_1-2_border">
                                <td class="ndfxs_1-2_xh">${res.accountId }</td>
                                <td class="ndfxs_1-2_xh">******</td>
                                <td class="ndfxs_1-2_fxs">${res.supplierName }</td>
                                <td class="ndfxs_1-2_ss">${res.employeeName }</td>
                                <td class="ndfxs_1-2_qq">${res.phone }</td>
                                <td class="ndfxs_1-2_qq">${res.address }</td>
                                <td class="ndfxs_1-2_ss">
                                    <a href="javascript:;" class="mr_10" onclick="wine('查看资料','win_div_2','520px','490px','${res.accountId }')">编辑</a>
                                    <a href="javascript:;" onclick="wint('删除推荐的子供应商','win_div_3','520px','240px','${res.accountId }')">删除</a>
                                </td>
                            </tr>
                            </c:forEach>
                        </table>
                    </div>
                </div>
            </div>
        </div>
		<!-- 分页 -->
		<div class="pull-right">
			<pg:page pageResult="${pageResult}" />
		</div>
	</form>


<!-- 新增供应商 -->
<form method="post" id="addForm">
<div class="updata_tixian" style="display:none;" id="win_div_1">
    <dl class="xzgys f16 mb_20">
        <dd class="tr">设置账号密码：</dd>
        <dt>
            <input type="password" name="password" class="xzgys_input" placeholder="数字和英文，至少6位">
        </dt>
    </dl>
    <dl class="xzgys f16 mb_20">
        <dd class="tr">公司名称：</dd>
        <dt>
            <input type="text" name="companyName" class="xzgys_input" placeholder="供应商名称，必填">
        </dt>
    </dl>
    <dl class="xzgys f16 mb_20">
        <dd class="tr">负责人：</dd>
        <dt>
            <input type="text" name="linkName" class="xzgys_input" placeholder="姓名，选填">
        </dt>
    </dl>
    <dl class="xzgys f16 mb_20">
        <dd class="tr">联系电话：</dd>
        <dt>
            <input type="text" name="phone" class="xzgys_input" placeholder="负责人手机号码">
        </dt>
    </dl>
    <dl class="xzgys f16 mb_20">
        <dd class="tr">地址：</dd>
        <dt class="f12">
           <select id="selProvince" name="province" class="mzh_select">
					<option>省</option>
				</select> 
				<select id="selCity" name="city" class="mzh_select">
					<option>市</option>
				</select> 
				<select id="selDistrict" name="location" class="mzh_select">
					<option>区</option>
				</select> 
            <input type="text" name="address" class="xzgys_input mt_10" placeholder="详细地址">
        </dt>
    </dl>
</div>
</form>


<!-- 查看 -->
<form action="" id="editform">
<div class="updata_tixian" style="display:none;" id="win_div_2">
    <dl class="xzgys f16 mb_20">
        <dd class="tr">供应商账号：</dd>
        <input type="hidden" id="haccountid" name="accountId">
        <dt id="eaccountid">
            1001_7
        </dt>
    </dl>
    <dl class="xzgys f16 mb_20">
        <dd class="tr">设置密码：</dd>
        <dt>
            <input type="password" name="password" id="epassword" class="xzgys_input" >
        </dt>
    </dl>
    <dl class="xzgys f16 mb_20">
        <dd class="tr">公司名称：</dd>
        <dt>
            <input type="text" name="companyName" id="ecompanyName" class="xzgys_input" >
        </dt>
    </dl>
    <dl class="xzgys f16 mb_20">
        <dd class="tr">负责人：</dd>
        <dt>
            <input type="text" name="linkName" id="elinkName" class="xzgys_input" >
        </dt>
    </dl>
    <dl class="xzgys f16 mb_20">
        <dd class="tr">联系电话：</dd>
        <dt>
            <input type="text" name="phone" id="ephone" class="xzgys_input" >
        </dt>
    </dl>
    <dl class="xzgys f16 mb_20">
        <dd class="tr">地址：</dd>
        <dt class="f12">
            <select id="eProvince" name="province" class="mzh_select">
					<option>省</option>
				</select> 
				<select id="eCity" name="city" class="mzh_select">
					<option>市</option>
				</select> 
				<select id="eDistrict" name="location" class="mzh_select">
					<option>区</option>
				</select> 
            <input type="text" name="address" id="eaddress" class="xzgys_input mt_10" >
        </dt>
    </dl>
</div>
</form>
<!-- 弹窗1 -->
	<div class="updata_tixian" style="display:none;" id="win_div_3">
	   <span class="f16"> 删除后，供应商信息将无法恢复，确认要删除账号？</span>
	</div>

	<script type="text/javascript">
		$(function() {
			//分页
			var page = new Pagination({
				formId : "searcherForm",
				isAjax : true,
				targetId : "navTab",
				submitId : "searchBtn",
				validateFn : function() {
					return true;
				}
			});
			page.init();
		});

		
		function deleteStaffChildrenAjax(childrenId) {
			$.ajax({
						url : "/childrenAccount/deleteChildrenAccount?childrenId="+childrenId,
						type : "post",
						success : function(result) {
							var result = eval(result);
							if (result != null) {
								if (result.Statu == "Success") {
									alert("成功!", true);
									location.href = '/childrenAccount/getSelfSCList';
								} else {
									alert(result.StatusReson);
								}
							}
						}
					})
		}
	</script>
	</c:if>
</body>
</html>