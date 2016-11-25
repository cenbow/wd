<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="pg" uri="http://www.okwei.com/pagination"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>我的分销商</title>
<style type="text/css">
.publish {
	float: left;
	width: 252px;
	line-height: 40px;
	background: #75be00 none repeat scroll 0 0;
	color: #fff;
	border-radius: 3px;
	text-align: center;
}
</style>
</head>

<body class="bg_f3">
	<form id="searcherForm" name="searcherForm"
		action="<%=basePath%>publishProduct/show" onsubmit="return false;">

		<c:if test="${productId!=null}">
			<input id="productId" type="hidden" name="productId"
				value="${productId}" />
		</c:if>

		<div style="border: 0px; background: none;" class="fl conter_right">
			<!-- 搜索 -->
			<div class="fl conter_right gysgl_ss bg_white bor_si">
				<span class="gysgl_ss_span">类目搜索：</span> 
				<input id="keyword" name="keyword" type="text" class="gysgl_ss_text mr_5" value="${keyword}"> 
				<a class="gysgl_ss_a" href="javascript:;" id="searchBtn">快速搜索类目</a>
				<!-- <button class="gysgl_ss_a" id="searchBtn" type="submit">快速搜索类目</button> -->
			</div>

			<!-- 搜索结果 -->
			<div class="fl conter_right mt_10 gysgl_xzfl bor_si bg_white"
				id="searchResultDiv" style="display: none;">
				<div class="fl pipei_title fw_b t_ind20 ft_c3">
					匹配到<span class="ft_blue"> <c:choose>
							<c:when test="${!empty paths}">
          				${paths.size()}
          			</c:when>
							<c:otherwise>
          				0
          			</c:otherwise>
						</c:choose>
					</span>个类目
				</div>
				<div class="fl sel_jieguo">
					<a class="gbs_del" id="closeDiv" href="javascript:;"
						ahref="<%=basePath%>publishProduct/show" to="navTab">× 关闭，返回类目</a>
					<ul>
						<c:choose>
							<c:when test="${!empty paths}">
								<c:forEach var="item" items="${paths}" varStatus="status">
									<li name="path" value='${item["catagoryId"]}'
										<c:if test="${status.index==0}">class="nows_sel"</c:if>>
										${status.index+1}. <span>${item["path"]}</span>
									</li>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<span style="margin-left: 10px;">没有相应的类别</span>
							</c:otherwise>
						</c:choose>
					</ul>
				</div>
			</div>

			<!-- 选择分类 -->
			<div class="fl conter_right mt_10 gysgl_xzfl bor_si bg_white"
				id="selectDiv">
				<div class="gysgl_xzfl_1">
					<b class="gysgl_ss_span f14">选择分类</b> <a id="importBtn"
						class="ml_10 gysgl_ss_a" href="javascript:;">从类似商品中导入</a>
				</div>
				<div class="gysgl_xzfl_2" id="chainSelete">
					<!-- 衣食住行用完 -->
					<div class="gysgl_fl">
						<div style="border-left: 0px;" class="mzh_lmbt_one_ck" id="first">
							<c:forEach var="cat" items="${catagoryVO.level1}">
								<div name="mzh_lmbt_one_ck_no" to="second"
									value="${cat.classId}" namedesc="${cat.className}"
									<c:choose>
		                			<c:when test="${!empty catagoryVO.level1Id && cat.classId==catagoryVO.level1Id}">
			                			class="mzh_lmbt_one_ck_yes"
		                			</c:when>
		                			<c:otherwise>
		                				class="mzh_lmbt_one_ck_no"
		                			</c:otherwise>
		                		</c:choose>>
									<span>${cat.className}</span> <img class="gysgl_fl_img"
										src="/statics/pic/jiantou_3_25.png" />
								</div>
							</c:forEach>
						</div>
					</div>
					<div class="gysgl_fl">
						<div class="mzh_lmbt_one_ck" id="second"
							<c:if test="${empty catagoryVO.level2}">style="display: none;"</c:if>>
							<c:forEach var="cat" items="${catagoryVO.level2}">
								<div name="mzh_lmbt_one_ck_notwo" to="third"
									value="${cat.classId}" namedesc="${cat.className}"
									<c:choose>
		                			<c:when test="${!empty catagoryVO.level2Id && cat.classId==catagoryVO.level2Id}">
			                			class="mzh_lmbt_one_ck_yes"
		                			</c:when>
		                			<c:otherwise>
		                				class="mzh_lmbt_one_ck_no"
		                			</c:otherwise>
		                		</c:choose>>
									<span>${cat.className}</span> <img class="gysgl_fl_img"
										src="/statics/pic/jiantou_3_25.png" />
								</div>
							</c:forEach>
						</div>
					</div>
					<div class="gysgl_fl">
						<div class="mzh_lmbt_one_ck" id="third"
							<c:if test="${empty catagoryVO.level3}">style="display: none;"</c:if>>
							<c:forEach var="cat" items="${catagoryVO.level3}">
								<div name="mzh_lmbt_one_ck_nothr" to="fourth"
									value="${cat.classId}" namedesc="${cat.className}"
									<c:choose>
		                			<c:when test="${!empty catagoryVO.level3Id && cat.classId==catagoryVO.level3Id}">
			                			class="mzh_lmbt_one_ck_yes"
		                			</c:when>
		                			<c:otherwise>
		                				class="mzh_lmbt_one_ck_no"
		                			</c:otherwise>
		                		</c:choose>>
									<span>${cat.className}</span> <img class="gysgl_fl_img"
										src="/statics/pic/jiantou_3_25.png" />
								</div>
							</c:forEach>

						</div>
					</div>

					<!-- 模板 -->
					<div class="gysgl_mb">
						<b class="gysgl_mb_mb">--选择你的模板--</b>
						<div style="height: 266px; border: 0px;" class="mzh_lmbt_one_ck"
							id="fourth">
							<c:forEach var="template" items="${catagoryVO.templates}">
								<div name="mzh_lmbt_one_ck_nofor" value="${template.mid}"
									namedesc="${template.mname}"
									<c:choose>
		                			<c:when test="${!empty catagoryVO.templateId && template.mid==catagoryVO.templateId}">
			                			class="mzh_lmbt_one_ck_yes"
		                			</c:when>
		                			<c:otherwise>
		                				class="mzh_lmbt_one_ck_no"
		                			</c:otherwise>
		                		</c:choose>>
									<span>${template.mname}</span> <b
										name="mzh_lmbt_one_ck_no_img1" class="mzh_lmbt_one_ck_no_img1"></b>
									<b name="mzh_lmbt_one_ck_no_img2"
										class="mzh_lmbt_one_ck_no_img2"></b>
								</div>
							</c:forEach>
						</div>
					</div>
				</div>
			</div>

			<!-- 显示选择path -->
			<div class="gysgl_xzds mt_10"
				<c:if test="${!empty paths}">style="display: none;"</c:if>>
				<img class="gysgl_xzdsdw" src="/statics/pic/tijt_3_25.png"> <b
					style="margin-left: 20px;" class="gysgl_xzds_wz">您当前选择的是：</b>
				<div id="path">
					<!-- <span class="gysgl_xzds_wz">衣</span>
					<span class="gysgl_xzds_wz">></span>
					<span class="gysgl_xzds_wz">女装</span>
					<span class="gysgl_xzds_wz">></span>
					<span class="gysgl_xzds_wz">针织衫</span>
					<span class="gysgl_xzds_wz">></span> -->


				</div>
			</div>
			<div class="auto_1024">
				<button id="submitBtn"
					<c:choose>
          			<c:when test="${!empty catagoryVO.level3Id || !empty paths}">
            			class="publish f14 mt_20"
           			</c:when>
           			<c:otherwise>
           				class="jbzl_dl_news f14 mt_20" disabled="true"
           			</c:otherwise>
           		</c:choose>>发布商品</button>
			</div>
		</div>
	</form>

	<script type="text/javascript">
		$(document).ready(function(){
			var page = new Pagination( {
				formId: "searcherForm",
				isAjax : true,
				targetId : "navTab",
				submitId:"searchBtn",
				validateFn:checkInfo,
				ajaxCallback:callback
			});
			page.init();
			
			/* 输入框回车搜索 */
			$("#keyword").keyup(function(event){
				if(event.keyCode == 13){
					var data = $("#searcherForm").serializeJson();
				 	var loader = new Loading().start();
				 	$("#navTab").load("<%=basePath%>publishProduct/show.ajax",data, function() {
				 		callback && callback();
	                    loader && loader.stop();
                	});
				}
			});
			
			$("#chainSelete").on("click","[name=mzh_lmbt_one_ck_no]",function(){
				$("[name=mzh_lmbt_one_ck_no]").attr("class","mzh_lmbt_one_ck_no");
				$(this).attr("class","mzh_lmbt_one_ck_yes");
 			});
			 
			$("#chainSelete").on("click","[name=mzh_lmbt_one_ck_notwo]",function(){
				$("[name=mzh_lmbt_one_ck_notwo]").attr("class","mzh_lmbt_one_ck_no");
				$(this).attr("class","mzh_lmbt_one_ck_yes");
			});
			 
			$("#chainSelete").on("click","[name=mzh_lmbt_one_ck_nothr]",function(){
				$("[name=mzh_lmbt_one_ck_nothr]").attr("class","mzh_lmbt_one_ck_no");
				$(this).attr("class","mzh_lmbt_one_ck_yes");
	 		});
			 
			$("#chainSelete").on("click","[name=mzh_lmbt_one_ck_nofor]",function(){
				$("[name=mzh_lmbt_one_ck_nofor]").attr("class","mzh_lmbt_one_ck_no");
				$(this).attr("class","mzh_lmbt_one_ck_yes");
			});
			
			/* 选择分类联动 */
			$("#chainSelete").on("click",".mzh_lmbt_one_ck_no",function(){
				var t = $(this);
				var id = t.attr("value");
				var to = t.attr("to");
				var targetName,toNext;
				url = window.basePath+"publishProduct/next/" + id;
				if(to=='second'){
					targetName = "mzh_lmbt_one_ck_notwo";
					toNext = "third";
					$("#third").hide();
					$("#fourth").hide();
					$("#submitBtn").attr("disabled",true).removeClass("publish").addClass("jbzl_dl_news");
					$("#path").html("").html('<span class="gysgl_xzds_wz">'+$("#first>div.mzh_lmbt_one_ck_yes").attr("namedesc")+'</span> <span class="gysgl_xzds_wz">&gt;</span>');
					
				}else if(to=='third'){
					targetName = "mzh_lmbt_one_ck_nothr";
					toNext = "fourth";
					$("#fourth").hide();
					$("#submitBtn").attr("disabled",true).removeClass("publish").addClass("jbzl_dl_news");
					$("#path").html("").html('<span class="gysgl_xzds_wz">'+$("#first>div.mzh_lmbt_one_ck_yes").attr("namedesc")+'</span> <span class="gysgl_xzds_wz">&gt;</span>'+
							'<span class="gysgl_xzds_wz">'+$("#second>div.mzh_lmbt_one_ck_yes").attr("namedesc")+'</span> <span class="gysgl_xzds_wz">&gt;</span>');
				}else if(to=='fourth'){
					targetName = "mzh_lmbt_one_ck_nofor";
					url = window.basePath+"publishProduct/getTemplate/" + id;
					$("#submitBtn").attr("disabled",false).removeClass("jbzl_dl_news").addClass("publish");
					$("#path").html("").html('<span class="gysgl_xzds_wz">'+$("#first div.mzh_lmbt_one_ck_yes").attr("namedesc")+'</span> <span class="gysgl_xzds_wz">&gt;</span>'+
							'<span class="gysgl_xzds_wz">'+$("#second div.mzh_lmbt_one_ck_yes").attr("namedesc")+'</span> <span class="gysgl_xzds_wz">&gt;</span>'+
							'<span class="gysgl_xzds_wz">'+$("#third div.mzh_lmbt_one_ck_yes").attr("namedesc")+'</span> <span class="gysgl_xzds_wz">&gt;</span>');
				}
				var toDom = $("#" + to);
				toDom.show();
				$.get(url,function(data){
					toDom.empty()
					for(var i = 0; i < data.length; i++){ 
						var value,name,jiantou;
						if(to=='fourth'){
							value = data[i].mid;
							name = data[i].mname;
							jiantou = '';
						}else{
							value = data[i].classId;
							name = data[i].className;
							jiantou = '<img src="/statics/pic/jiantou_3_25.png" class="gysgl_fl_img">';
						}
						var html ="";
						html += '<div '+'namedesc='+name+' class="mzh_lmbt_one_ck_no" to="'+toNext+'" value='+ value +' name='+targetName+'>';
						html += '<span>'+name+'</span>';
						html += jiantou;
						html += '</div>';
						toDom.append(html);
					}
				});
			});
			
			if( $("#productId").val()){
				$("#path").html("").html('<span class="gysgl_xzds_wz">'+$("#first div.mzh_lmbt_one_ck_yes").attr("namedesc")+'</span> <span class="gysgl_xzds_wz">&gt;</span>'+
						'<span class="gysgl_xzds_wz">'+$("#second div.mzh_lmbt_one_ck_yes").attr("namedesc")+'</span> <span class="gysgl_xzds_wz">&gt;</span>'+
						'<span class="gysgl_xzds_wz">'+$("#third div.mzh_lmbt_one_ck_yes").attr("namedesc")+'</span> <span class="gysgl_xzds_wz">&gt;</span>');
			}
			
			/* 选择分类弹出框 */
			$("#importBtn").click(function(){
				wins('导入商品','mzh_clsspzdr','734px','530px');
			});
			/* $("input[type='number']").stepper(); */
			
			/* 发布产品 */
			$("#submitBtn").click(function(){
				var classId1 = $("[name=mzh_lmbt_one_ck_nothr].mzh_lmbt_one_ck_yes").attr("value");
				var classId2 = $("[name=path]").attr("value");
				var classId=classId1===undefined ? classId2 : classId1;
				var tempId = $("[name=mzh_lmbt_one_ck_nofor].mzh_lmbt_one_ck_yes").attr("value");
				var templateId= tempId===undefined ? "" : tempId;
				var productId = $("#productId").val()===undefined ? "" : $("#productId").val();
				var operation = 1;
				if(productId>0){
					operation = 3;
				}
				window.location = "/Product/editProductInfo?operation="+operation+"&&classID="+classId+"&&tempID="+templateId+"&&productId="+productId;
			});
		});
		
		
		function checkInfo(){
			var term = $("[name=keyword]").val();
			if (!term){
				alert("请输入关键词");
			    return false;
			}
			return true;
		}
		
		function callback(){
			$("#searchResultDiv").show();
			$("#selectDiv").hide();
			$('.sel_jieguo ul li').each(function(){
				$(this).click(function(){
					$('.sel_jieguo ul li').attr('class','');
					$(this).attr('class','nows_sel');
				})
			});
			$("#closeDiv").click(function(){
				$("#submitBtn").attr("disabled",false);
				var t = $(this);
		        var to = $("#" + t.attr("to"));
		        var url = t.attr("ahref");
		        var a = $("li.nows_sel:eq(0)").attr('catagoryid');
		        if($("li.nows_sel:eq(0)").length ==0){
		        	to.load("<%=basePath%>publishProduct/index.ajax");
		        }else{
		        	to.load(url+".ajax",{'catagoryId':$("li.nows_sel:eq(0)").attr('value')},function(data) {
			        	$("#path").html("").html('<span class="gysgl_xzds_wz">'+$("#first div.mzh_lmbt_one_ck_yes").attr("namedesc")+'</span> <span class="gysgl_xzds_wz">&gt;</span>'+
								'<span class="gysgl_xzds_wz">'+$("#second div.mzh_lmbt_one_ck_yes").attr("namedesc")+'</span> <span class="gysgl_xzds_wz">&gt;</span>'+
								'<span class="gysgl_xzds_wz">'+$("#third div.mzh_lmbt_one_ck_yes").attr("namedesc")+'</span> <span class="gysgl_xzds_wz">&gt;</span>');
			        });
		        }
			});
		};
		
		/* 弹窗调用函数 */
		function wins(title,win_id,width,height){ //title 标题 win_id 弹窗ID  width 弹窗宽度 height 弹窗高度
			var pagei = $.layer({
		   		type: 2,   //0-4的选择,0：信息框（默认），1：页面层，2：iframe层，3：加载层，4：tips层。 
				btn: ['确定','取消'],
			   	title: title,
			   	border: [0],
			   	closeBtn: [0],
				closeBtn: [0, true],
			   	shadeClose: true,
			   	area: [width,height],
			   	iframe: { src: '/publishProduct/popup/getProducts' }
			});
		};
		
	</script>

</body>