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
<title>推荐的子供应商列表</title>
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
.ndfxs_1-2_fxs{width:10%;}
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
$(function(){
	//初始化省市区列表
	dis = new district();
	dis.init('#selProvince', '#selCity', '#selDistrict');
})
	/** 弹窗调用函数 **/
	function wint(title, win_id, width, height,accountid) { //title 标题 win_id 弹窗ID  width 弹窗宽度 height 弹窗高度
		var pagei = $.layer({
			type : 1, //0-4的选择,0：信息框（默认），1：页面层，2：iframe层，3：加载层，4：tips层。
			btns : 2,
			btn : [ '确定', '取消' ],
			title : title,
			border : [ 0 ],
			closeBtn : [ 0 ],
			closeBtn : [ 0, true ],
			shadeClose : true,
			area : [ width, height ],
			page : {
				dom : '#' + win_id
			},
			end : function() {
				$("#AddCount").hide()
			},
			yes:function(index){
				deleteStaffChildrenAjax(accountid);
			}
		});
	}
	/** 弹窗调用函数 **/
	function win1(title, win_id, width, height) { //title 标题 win_id 弹窗ID  width 弹窗宽度 height 弹窗高度
		var pagei = $.layer({
			type : 1, //0-4的选择,0：信息框（默认），1：页面层，2：iframe层，3：加载层，4：tips层。
			btns : 1,
			btn : [ '提交' ],
			title : title,
			border : [ 0 ],
			closeBtn : [ 0 ],
			closeBtn : [ 0, true ],
			shadeClose : true,
			area : [ width, height ],
			page : {
				dom : '#' + win_id
			},
			end : function() {
				$("#AddCount").hide()
			}
		});
	}

	/** 弹窗调用函数 **/
	function win5(title, win_id, width, height) { //title 标题 win_id 弹窗ID  width 弹窗宽度 height 弹窗高度
		var pagei = $.layer({
			type : 1, //0-4的选择,0：信息框（默认），1：页面层，2：iframe层，3：加载层，4：tips层。
			btns : 1,
			btn : [ '确定' ],
			title : title,
			border : [ 0 ],
			closeBtn : [ 0 ],
			closeBtn : [ 0, true ],
			shadeClose : true,
			area : [ width, height ],
			page : {
				dom : '#' + win_id
			},
			end : function() {
				$("#AddCount").hide()
			}
		});
	}
	/** 弹窗调用函数 **/
	function win2(title, win_id, width, height, statuname,nopassreseaon,platformname,companyname,linkname,phone,address) { //title 标题 win_id 弹窗ID  width 弹窗宽度 height 弹窗高度
		$("#sstatu").empty();
	    $("#sstatu").append(statuname+nopassreseaon);
	    
	    $("#splatform").empty();
	    $("#splatform").append(platformname);
	    
	    $("#scompany").empty();
	    $("#scompany").append(companyname);
	    
	    $("#slinkman").empty();
	    $("#slinkman").append(linkname);
	    
	    $("#sphone").empty();
	    $("#sphone").append(phone);
	    
	    $("#saddress").empty();
	    $("#saddress").append(address);

		var pagei = $.layer({
			type : 1, //0-4的选择,0：信息框（默认），1：页面层，2：iframe层，3：加载层，4：tips层。
			btns : 1,
			btn : [ '关闭' ],
			title : title,
			border : [ 0 ],
			closeBtn : [ 0 ],
			closeBtn : [ 0, true ],
			shadeClose : true,
			area : [ width, height ],
			page : {
				dom : '#' + win_id
			},
			end : function() {
				$("#AddCount").hide()
			}
		});
	}
	/** 弹窗调用函数 **/
	function win3(title, win_id, width, height) { //title 标题 win_id 弹窗ID  width 弹窗宽度 height 弹窗高度
		var pagei = $.layer({
			type : 1, //0-4的选择,0：信息框（默认），1：页面层，2：iframe层，3：加载层，4：tips层。
			btns : 1,
			btn : [ '保存' ],
			title : title,
			border : [ 0 ],
			closeBtn : [ 0 ],
			closeBtn : [ 0, true ],
			shadeClose : true,
			area : [ width, height ],
			page : {
				dom : '#' + win_id
			},
			end : function() {
				$("#AddCount").hide()
			}
		});
	}
	/** 弹窗调用函数 **/
	function win15(title, win_id, width, height) { //title 标题 win_id 弹窗ID  width 弹窗宽度 height 弹窗高度
		dis.bdbycode(0,0,0);

		$.ajax({
			url : "/childrenAccount/getPlatformSupplierKV",
			type : "get",
			success : function(result) {
				var result = eval(result);
				if (result != null) {
					var option = "<option value='0'>请选择</option>";
					if (result.Statu == "Success") {
						$.each(result.BaseModle,function(n,value){
							option+="<option value='"+value.companyWeiid+"'>"+value.companyName+"</option>";
						});
						$("#platformSupplier").empty();
						$("#platformSupplier").append(option);
					} else {
						alert(result.StatusReson);
					}
				}
			}
		})
		var pagei = $.layer({
			type : 1, //0-4的选择,0：信息框（默认），1：页面层，2：iframe层，3：加载层，4：tips层。
			btns : 2,
			btn : [ '保存' ,'取消'],
			title : title,
			border : [ 0 ],
			closeBtn : [ 0 ],
			closeBtn : [ 0, true ],
			shadeClose : true,
			area : [ width, height ],
			page : {
				dom : '#' + win_id
			},
			yes : function(index) {
				$.ajax({
					url : "/childrenAccount/addRecChildrenSupplier",
					type : "post",
					data : $("#addForm").serialize(),
					success : function(result) {
						var result = eval(result);
						if (result != null) {
							if (result.Statu == "Success") {
								alert("成功!", true);
								location.href = "/childrenAccount/getRecommandSCList";
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
	function win4(title, win_id, width, height) { //title 标题 win_id 弹窗ID  width 弹窗宽度 height 弹窗高度
		var pagei = $.layer({
			type : 1, //0-4的选择,0：信息框（默认），1：页面层，2：iframe层，3：加载层，4：tips层。
			btns : 2,
			btn : [ '审核通过', '审核不通过' ],
			title : title,
			border : [ 0 ],
			closeBtn : [ 0 ],
			closeBtn : [ 0, true ],
			shadeClose : true,
			area : [ width, height ],
			page : {
				dom : '#' + win_id
			},
			end : function() {
				$("#AddCount").hide()
			}
		});
	}
	 
</script>
</head>
<body class="bg_f3">
    <c:if test="${Recommend==false }">
    <jsp:include page="/jsp/common/notpermit.jsp"/>
    </c:if>
    <c:if test="${Recommend==true }">
	<form id="searcherForm" name="searcherForm"
		action="/childrenAccount/getRecommandSCList"
		onsubmit="return false;">

		<div class="fr conter_right" style="border: 0px; background: none;">
			<div class="fl conter_right  bg_white bor_si">
				<%-- <div class="wdqb_xxk ndfxs_1-2_border">
					<div class="tab_pc f16">
					   <c:if test="${Recommend==true }">
					   <li class="now" name="name_xxk"><a href="/childrenAccount/getRecommandSCList">推荐供应商</a><i
							style="left: 43%;"></i></li>
					   </c:if>
						<c:if test="${SupplierList==true }">
						<li name="name_xxk"><a href="/childrenAccount/getSelfSCList">我的供应商</a><i
							style="left: 43%;"></i></li>
						</c:if>
						
						<li name="name_xxk"><a href="#">我关注的店铺/上游供应</a><i
							style="left: 45%;"></i></li>
					</div>
				</div> --%>
				<!-- 推荐供应商 -->
				<div class="wdgz_div">
					<div class="blank2"></div>
					<div class="wdgz_div_zjtjdgys">
						<div class="jbzl_dl_qrdz"
							style="height: 27px; margin-left: 20px; line-height: 27px; width: 80px;"
							onclick="win15('推荐新供应商','win_div_9','520px','450px')">推荐新供应商</div>
						<table class="conter_right_xx_cz_table mt_10">
							<tr class="ndfxs_1-2_color ndfxs_1-2_border">
								<td class="ndfxs_1-2_fxs" style="text-indent: 22px;">供应商名称</td>
								<td class="ndfxs_1-2_fxs">供应商账号</td>
								<td class="ndfxs_1-2_fxs">密码</td>
								<td class="ndfxs_1-2_fxs">推荐给平台商城</td>
								<td class="ndfxs_1-2_fxs">负责人</td>
								<td class="ndfxs_1-2_fxs">联系电话</td>
								<td class="ndfxs_1-2_fxs">地址</td>
								<td class="ndfxs_1-2_fxs">状态</td>
								<td class="ndfxs_1-2_fxs">操作</td>
							</tr>
							<c:if test="${fn:length(pageResult.list)<1}">
								<tr style="height: 200px;">
									<td colspan="9" style="text-align: center">暂无相关数据</td>
								<tr>
							</c:if>
							<c:forEach items="${pageResult.list}" var="res">
								<tr class="ndfxs_1-2_border">
									<td class="ndfxs_1-2_fxs" style="text-indent: 22px;">${res.supplierName }</td>
									<td class="ndfxs_1-2_fxs">${res.accountId }</td>
									<td class="ndfxs_1-2_fxs">******</td>
									<td class="ndfxs_1-2_fxs">${res.platformName }</td>
									<td class="ndfxs_1-2_fxs">${res.linkName }</td>
									<td class="ndfxs_1-2_fxs">${res.phone }</td>
									<td class="ndfxs_1-2_fxs">${res.address }</td>
									<td class="ndfxs_1-2_fxs">${res.statusName }</td>
									<td class="ndfxs_1-2_fxs"><a href="javascript:;"
										class="mr_10"
										onclick="win2('查看资料','win_div_11','520px','450px','${res.statusName }','${res.noPassReason }','${res.platformName }','${res.supplierName }','${res.linkName }','${res.phone }','${res.address }')">查看</a>
										<a href="javascript:;" onclick="wint('删除推荐的子供应商','win_div_3','520px','240px','${res.accountId }')">删除</a></td>
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

	<!-- 推荐新供应商 -->
	<form method="post" id="addForm">
	<div class="updata_tixian" style="display: none;" id="win_div_9">
		<dl class="xzgys f16 mb_20">
			<dd class="tr">推荐给平台商城：</dd>
			<dt>
				<select id="platformSupplier" style="width: 250px;" name="merchantWeiId" class="mzh_select bor_si">
			    </select> 
			</dt>
		</dl>
		<dl class="xzgys f16 mb_20">
			<dd class="tr">公司名称：</dd>
			<dt>
				<input type="text" class="xzgys_input" name="companyName" placeholder="供应商名称，必填">
			</dt>
		</dl>
		<dl class="xzgys f16 mb_20">
			<dd class="tr">负责人：</dd>
			<dt>
				<input type="text" class="xzgys_input" name="linkName" placeholder="姓名，必填">
			</dt>
		</dl>
		<dl class="xzgys f16 mb_20">
			<dd class="tr">联系电话：</dd>
			<dt>
				<input type="text" class="xzgys_input" name="phone" placeholder="手机号，必填">
			</dt>
		</dl>
		<dl class="xzgys f16 mb_20">
			<dd class="tr">地址：</dd>
			<dt>
<!-- 				<div class="mzh_xlk" style="margin: 0px; width: 100px;" -->
<!-- 					name="mzh_xlk"> -->
<!-- 					<div class="mzh_xlk_text" isclick="0" style="width: 100px;">选择省</div> -->
<!-- 					<div class="mzh_xlk_dw" style="width: 100px;"> -->
<!-- 						<span name="mzh_span_4_9">湖南省</span> -->
<!-- 					</div> -->
<!-- 				</div> -->
<!-- 				<div class="mzh_xlk" -->
<!-- 					style="margin: 0px; width: 100px; margin-left: 10px;" -->
<!-- 					name="mzh_xlk"> -->
<!-- 					<div class="mzh_xlk_text" isclick="0" style="width: 100px;">选择市</div> -->
<!-- 					<div class="mzh_xlk_dw" style="width: 100px;"> -->
<!-- 						<span name="mzh_span_4_9">岳阳市</span> -->
<!-- 					</div> -->
<!-- 				</div> -->
<!-- 				<div class="mzh_xlk" -->
<!-- 					style="margin: 0px; width: 100px; margin-left: 10px;" -->
<!-- 					name="mzh_xlk"> -->
<!-- 					<div class="mzh_xlk_text" isclick="0" style="width: 100px;">选择区</div> -->
<!-- 					<div class="mzh_xlk_dw" style="width: 100px;"> -->
<!-- 						<span name="mzh_span_4_9">岳阳楼区</span> -->
<!-- 					</div> -->
<!-- 				</div> -->
				<select id="selProvince" name="province" class="mzh_select bor_si f12" style="width:80px;">
					<option>省</option>
				</select> 
				<select id="selCity" name="city" class="mzh_select bor_si f12" style="width:80px;">
					<option>市</option>
				</select> 
				<select id="selDistrict" name="location" class="mzh_select bor_si f12" style="width:80px;">
					<option>区</option>
				</select> 
				<input type="text" class="xzgys_input mt_10" name="address" placeholder="详细地址">
			</dt>
		</dl>
	</div>
	</form>


	<!-- 查看资料 -->
	<div class="updata_tixian" style="display: none;" id="win_div_11">
		<dl class="xzgys f16 mb_20">
			<dd class="tr">审核结果：</dd>
			<dt id="sstatu">待审核/审核通过/ 审核不通过(原因:资料是假的)</dt>
		</dl>
		<dl class="xzgys f16 mb_20">
			<dd class="tr">推荐给平台商城：</dd>
			<dt id="splatform">特步官方品牌商城</dt>
		</dl>
		<dl class="xzgys f16 mb_20">
			<dd class="tr">公司名称：</dd>
			<dt id="scompany">云南青云茶庄</dt>
		</dl>
		<dl class="xzgys f16 mb_20">
			<dd class="tr">负责人：</dd>
			<dt id="slinkman">云南青云茶庄</dt>
		</dl>
		<dl class="xzgys f16 mb_20">
			<dd class="tr">联系电话：</dd>
			<dt id="sphone">135676888899</dt>
		</dl>
		<dl class="xzgys f16 mb_20">
			<dd class="tr">地址：</dd>
			<dt id="saddress">广东省厦门市思明区澜沧路11号</dt>
		</dl>
	</div>

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

		//添加员工子账号弹层
		function addChildrenStaffLayer(title, win_id) { //title 标题 win_id 弹窗ID  width 弹窗宽度 height 弹窗高度
			var pagei = $.layer({
				type : 1, //0-4的选择,0：信息框（默认），1：页面层，2：iframe层，3：加载层，4：tips层。 
				btns : 2,
				btn : [ '提交', '取消' ],
				title : title,
				border : [ 0 ],
				closeBtn : [ 0 ],
				closeBtn : [ 0, true ],
				shadeClose : false,
				area : [ '500', '400' ],
				page : {
					dom : '#' + win_id
				},
				yes : function() {
					if (addChildrenStaffAjax()) {
						layer.close(pagei);
					}
				}
			});
		}
		//添加员工子账号
		function addChildrenStaffAjax() {
			if ($.trim($("#addPwd").val()) == "") {
				alert("密码不能为空");
				return false;
			}
			//只输入数字和字母
			var reg = /^[0-9a-zA-z]{6,}$/
			if (!reg.test($.trim($("#addPwd").val()))) {
				alert("密码必须是数字和字母组合,并且长度大于6位");
				return false;
			}
			
			$.ajax({
						url : "/childrenAccount/addStaffChildrenAccount",
						type : "post",
						data : $("#addForm").serialize(),
						success : function(result) {
							var result = eval(result);
							if (result != null) {
								if (result.Statu == "Success") {
									alert("成功!", true);
									location.href = "/childrenAccount/getStaffChildrenAccountList";
								} else {
									alert(result.StatusReson);
								}
							}
						}
					})
			return true;
		}
		//编辑员工子账号
		function editStaffChildrenPrepare(childrenId, _this) {
			$.ajax({
				url : "/childrenAccount/editStaffChildrenPrepare?childrenId="
						+ childrenId,
				type : "post",
				success : function(result) {
					var result = eval(result);
					if (result != null) {
						if (result.Statu == "Success") {
							$("#editAccountId")
									.text(result.BaseModle.accountId);
							$("#editUserName").val(
									result.BaseModle.employeeName);
							$("#editDepartment").val(
									result.BaseModle.department);
							$("#editPhone").val(result.BaseModle.phone);
							showLayer("编辑", "childrenStaffDetailDIV",
									childrenId, _this);
						} else {
							alert(result.StatusReson);
						}
					}
				}
			})
		}
		function showLayer(title, win_id, childrenId, _this) {
			var pagei = $.layer({
				type : 1, //0-4的选择,0：信息框（默认），1：页面层，2：iframe层，3：加载层，4：tips层。 
				btns : 2,
				btn : [ '提交', '取消' ],
				title : title,
				border : [ 0 ],
				closeBtn : [ 0 ],
				closeBtn : [ 0, true ],
				shadeClose : false,
				area : [ '500', '400' ],
				page : {
					dom : '#' + win_id
				},
				yes : function() {
					if (editStaffChildrenAjax(childrenId, _this)) {
						layer.close(pagei);
					}
				}
			});
		}

		function editStaffChildrenAjax(childrenId, _this) {

			if ($.trim($("#editPwd").val()) != "") {
				//只输入数字和字母
				var reg = /^[0-9a-zA-z]{6,}$/
				if (!reg.test($.trim($("#editPwd").val()))) {
					alert("密码必须是数字和字母组合,并且长度大于6位");
					return false;
				}
			}

			$
					.ajax({
						url : "/childrenAccount/editStaffChildrenAccount?childrenId="
								+ childrenId,
						type : "post",
						data : $("#editForm").serialize(),
						success : function(result) {
							var result = eval(result);
							if (result != null) {
								if (result.Statu == "Success") {
									alert("成功!", true);
									location.href = '/childrenAccount/getStaffChildrenAccountList';
								} else {
									alert(result.StatusReson);
								}
							}
						}
					})
			return true;
		}
		//删除弹层
		function deletePromptLayer(title, win_id, childrenId, _this) {
			var pagei = $.layer({
				type : 1, //0-4的选择,0：信息框（默认），1：页面层，2：iframe层，3：加载层，4：tips层。 
				btns : 2,
				btn : [ '提交', '取消' ],
				title : title,
				border : [ 0 ],
				closeBtn : [ 0 ],
				closeBtn : [ 0, true ],
				shadeClose : false,
				area : [ '500', '200' ],
				page : {
					dom : '#' + win_id
				},
				yes : function() {
					deleteStaffChildrenAjax(childrenId, _this)
					layer.close(pagei);
				}
			});
		}
		function deleteStaffChildrenAjax(childrenId) {
			$.ajax({
						url : "/childrenAccount/deleteChildrenAccount?childrenId="+childrenId,
						type : "post",
						success : function(result) {
							var result = eval(result);
							if (result != null) {
								if (result.Statu == "Success") {
									alert("成功!", true);
									location.href = '/childrenAccount/getRecommandSCList';
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