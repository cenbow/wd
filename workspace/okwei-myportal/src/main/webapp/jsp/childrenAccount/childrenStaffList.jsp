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
<title>子账号列表</title>
<link rel="stylesheet" type="text/css" href="/statics/css/jumei_usercenter.min.css" />
 <style>
    .ndfxs_1-2_qq{width: 15%;text-indent: 22px;}
    .ndfxs_1-2_dp{width: 13%;}
    .ndfxs_1-2_ss{width: 8%;}
</style>
<link href="<%=request.getContextPath()%>/statics/js/layer/skin/layer.css" rel="stylesheet" type="text/css" />
<script type = "text/javascript" src = "<%=request.getContextPath()%>/statics/js/layer/layer.min.js"></script>
</head>
<body class="bg_f3">
<c:if test="${CompanyChildren==false }">
<jsp:include page="/jsp/common/notpermit.jsp"/>
</c:if>
<c:if test="${CompanyChildren==true }">
<style>
.xzgys{float:left;width:100%;}
.xzgys dd{float:left;width:130px;line-height:26px;}
.xzgys dt{float:left;width:340px;line-height:26px;}
.xzgys_input{float:left;width:250px;border-radius: 3px;text-indent: 7px;border:1px solid #ccc;height:26px;line-height:26px;font-size:12px;}
</style>

<form id="searcherForm" name="searcherForm" action="/childrenAccount/getStaffChildrenAccountList" onsubmit="return false;">
<div class="fr conter_right" style="border: 0px;background: none;">
<div class="fl conter_right mt_20 bg_white bor_si">
<div class="wdgz_div" id="id_xxk_0">
    <div class="jbzl_dl_qrdz " style="height: 27px; margin: 20px 0 0 20px; line-height: 27px;width: 100px;" onclick="addChildrenStaffLayer('新增员工账号','addChildrenStaffDIV')">新增员工账号</div>
    <div class="wdgz_div_zjtjdgys" id="id_gys_0">
        <table class="conter_right_xx_cz_table mt_10">
            <tr class="ndfxs_1-2_color ndfxs_1-2_border">
                <td class="ndfxs_1-2_qq">员工账号</td>
                <td>设置密码</td>
                <td>负责人</td>
                <td>部门</td>
                <td>联系电话</td>
                <td>操作</td>
            </tr>
          <c:if test="${fn:length(pageResult.list)<1}">
          		<tr style="height:200px;">
					<td colspan="5" style="text-align:center">暂无相关数据</td>
				<tr>
          </c:if>
          <c:forEach items="${pageResult.list}" var="res">
	            <tr class="ndfxs_1-2_border">
	                <td class="ndfxs_1-2_qq">${res.accountId}</td>
	                <td>******</td>
	                <td>${res.accountName}</td>
	                <td>${res.department}</td>
	                <td>${res.mobilePhone}</td>
	                <td>
	                    <a href="javascript:;" class="mr_10" onclick="editStaffChildrenPrepare('${res.accountId}',this)">查看</a>
	                    <a href="javascript:;" onclick="deletePromptLayer('删除账号','deleteDIV','${res.accountId}',this)">删除</a>
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

<!-- 新增员工账号 -->
<div class="updata_tixian" style="display:none;" id="addChildrenStaffDIV">
<form method="post" id="addForm">
    <dl class="xzgys f16 mb_20">
        <dd class="tr">设置密码：</dd>
        <dt>
            <input type="password" class="xzgys_input" placeholder="数字和英文，至少6位" name="passWord" id="addPwd" maxlength="50">
        </dt>
    </dl>
    <dl class="xzgys f16 mb_20">
        <dd class="tr">部门：</dd>
        <dt>
            <input type="text" class="xzgys_input" placeholder="员工所在部门，选填" name="department" id="addptt">
        </dt>
    </dl>
    <dl class="xzgys f16 mb_20">
        <dd class="tr">负责人：</dd>
        <dt>
            <input type="text" class="xzgys_input" placeholder="姓名，选填" name="userName" id="addun">
        </dt>
    </dl>
    <dl class="xzgys f16 mb_20">
        <dd class="tr">联系电话：</dd>
        <dt>
            <input type="text" class="xzgys_input" placeholder="负责人手机号码" name="mobilePhone" id="addmp">
        </dt>
    </dl>
</form>
</div>

<!-- 查看 -->
<div class="updata_tixian" style="display:none;" id="childrenStaffDetailDIV">
<form method="post" id="editForm">
    <dl class="xzgys f16 mb_20">
        <dd class="tr">员工账号：</dd>
        <dt id="editAccountId">
        </dt>
    </dl>
    <dl class="xzgys f16 mb_20">
        <dd class="tr">设置密码：</dd>
        <dt>
            <input type="password" class="xzgys_input" placeholder="密码" id="editPwd" maxlength="50" name="passWord">
        </dt>
    </dl>
    <dl class="xzgys f16 mb_20">
        <dd class="tr">部门：</dd>
        <dt>
            <input type="text" class="xzgys_input" placeholder="部门" id="editDepartment" name="department">
        </dt>
    </dl>
    <dl class="xzgys f16 mb_20">
        <dd class="tr">负责人：</dd>
        <dt>
            <input type="text" class="xzgys_input" placeholder="负责人" id="editUserName" name="userName">
        </dt>
    </dl>
    <dl class="xzgys f16 mb_20">
        <dd class="tr">联系电话：</dd>
        <dt>
            <input type="text" class="xzgys_input" placeholder="电话号码" id="editPhone" name="mobilePhone">
        </dt>
    </dl>
</form>
</div>

<!-- 删除 -->
<div class="updata_tixian" style="display:none;" id="deleteDIV">
    <span class="f16"> 删除账号后，该员工将不能通过账号登录系统，确认是否要删除账号？</span>
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

//添加员工子账号弹层
function addChildrenStaffLayer(title,win_id){ //title 标题 win_id 弹窗ID  width 弹窗宽度 height 弹窗高度
	$("#addPwd").val("");
	$("#addptt").val("");
	$("#addun").val("");
	$("#addmp").val("");
	var pagei = $.layer({
    type: 1,   //0-4的选择,0：信息框（默认），1：页面层，2：iframe层，3：加载层，4：tips层。 
    btns: 2,
	btn: ['提交','取消'],
    title: title,
    border: [0],
    closeBtn: [0],
	closeBtn: [0, true],
    shadeClose: false,
    area: ['500','400'], 
    page: {dom : '#'+ win_id},
	yes: function(){
		if(addChildrenStaffAjax()){
			layer.close(pagei);
		}
	}
	});
}
//添加员工子账号
function addChildrenStaffAjax(){

	if($.trim($("#addPwd").val())!=""){
		//只输入数字和字母
		var reg = /^[0-9a-zA-z]{6,}$/
		if(!reg.test($.trim($("#addPwd").val()))){
			alert("密码必须是数字和字母组合,并且长度大于6位");
			return false;
		}
	}
	
	$.ajax({
		url:"/childrenAccount/addStaffChildrenAccount",
		type:"post",
		data:$("#addForm").serialize(),
		success:function(result){
			var result = eval(result);
		    if(result != null)
	    	{
	    		if(result.Statu == "Success")
	    		{   
	    			alert("成功!",true);
	    			location.href="/childrenAccount/getStaffChildrenAccountList";
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
//编辑员工子账号
function editStaffChildrenPrepare(childrenId,_this){
	$.ajax({
		url:"/childrenAccount/editStaffChildrenPrepare?childrenId="+childrenId,
		type:"post",
		success:function(result){
			var result = eval(result);
		    if(result != null)
	    	{
	    		if(result.Statu == "Success")
	    		{   
	    			 $("#editAccountId").text(result.BaseModle.accountId);
	    			 $("#editUserName").val(result.BaseModle.employeeName);
	    			 $("#editDepartment").val(result.BaseModle.department);
	    			 $("#editPhone").val(result.BaseModle.phone);
	    			 showLayer("编辑","childrenStaffDetailDIV",childrenId,_this);
	    		}
	    		else
	    		{
	    			alert(result.StatusReson);
	    		}
	    	}
		}
	})
}
function showLayer(title,win_id,childrenId,_this){
	var pagei = $.layer({
	    type: 1,   //0-4的选择,0：信息框（默认），1：页面层，2：iframe层，3：加载层，4：tips层。 
	    btns: 2,
		btn: ['提交','取消'],
	    title: title,
	    border: [0],
	    closeBtn: [0],
		closeBtn: [0, true],
	    shadeClose: false,
	    area: ['500','400'], 
	    page: {dom : '#'+ win_id},
		yes: function(){
			if(editStaffChildrenAjax(childrenId,_this)){
				layer.close(pagei);
			}
		}
		});
}

function editStaffChildrenAjax(childrenId,_this){
	if($.trim($("#editPwd").val())!=""){
		//只输入数字和字母
		var reg = /^[0-9a-zA-z]{6,}$/
		if(!reg.test($.trim($("#editPwd").val()))){
			alert("密码必须是数字和字母组合,并且长度大于6位");
			return false;
		}
	}
	
	$.ajax({
		url:"/childrenAccount/editStaffChildrenAccount?validatePwd="+$("#editPwd").attr('data')+"&childrenId="+childrenId,
		type:"post",
		data:$("#editForm").serialize(),
		success:function(result){
			var result = eval(result);
		    if(result != null)
	    	{
	    		if(result.Statu == "Success")
	    		{   
	    			alert("成功!",true);
	    			location.href='/childrenAccount/getStaffChildrenAccountList';
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
//删除弹层
function deletePromptLayer(title,win_id,childrenId,_this){
	var pagei = $.layer({
	    type: 1,   //0-4的选择,0：信息框（默认），1：页面层，2：iframe层，3：加载层，4：tips层。 
	    btns: 2,
		btn: ['提交','取消'],
	    title: title,
	    border: [0],
	    closeBtn: [0],
		closeBtn: [0, true],
	    shadeClose: false,
	    area: ['500','200'], 
	    page: {dom : '#'+ win_id},
		yes: function(){
			deleteStaffChildrenAjax(childrenId,_this)
			layer.close(pagei);
		}
		});
}
function deleteStaffChildrenAjax(childrenId,_this){
	$.ajax({
		url:"/childrenAccount/deleteChildrenAccount?childrenId="+childrenId,
		type:"post",
		success:function(result){
			var result = eval(result);
		    if(result != null)
	    	{
	    		if(result.Statu == "Success")
	    		{
	    			alert("成功!",true);
	    			location.href="/childrenAccount/getStaffChildrenAccountList";
	    		}
	    		else
	    		{
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