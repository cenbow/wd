<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="pg" uri="http://www.okwei.com/pagination"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>店铺首页设置</title>

<link rel="stylesheet" type="text/css" href="/statics/css/glbdy.css" />
<link rel="stylesheet" type="text/css" href="/statics/css/mzh_dd.css" />
<link rel="stylesheet" type="text/css"href="/statics/js/layer/skin/layer.css" />
<link rel="stylesheet" type="text/css" href="/statics/js/lib/uploadify/uploadify.css" />
<script type="text/javascript" src="/statics/js/lib/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="/statics/js/common/spin.min.js?_=${VERSION}"></script>
<script type="text/javascript" src="/statics/js/common/extends_fn.js?_=${VERSION}"></script>
<script type="text/javascript" src="/statics/js/common/common.js?_=${VERSION}"></script>
<script type="text/javascript" charset="utf-8" src="/statics/js/lib/uploadify/jquery.uploadify-3.1.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		
		$("#sort").bind("keyup", function() {
			$(this).val($(this).val().replace(/\D|^0/g, ''));
		}).bind("paste", function() { //CTR+V事件处理     
			$(this).val($(this).val().replace(/\D|^0/g, ''));
		}).css("ime-mode", "disabled");
		
		$("#confirm").click(function(){
			// 判断输入框不能为空
			if($("#tagId").val()==''){
				alert("标签不能为空!");
				$("#tagId").focus();
				return false;
			}
			
			if($("#tagId").val().length>50){
				alert("标签长度不能超过50个字符!");
				$("#tagId").focus();
				return false;
			}
			
			if($("#shopImageInput").val()==''){
				alert("图片不能为空!");
				return false;
			}
			if($("#sort").val()==''){
				alert("排序不能为空!");
				$("#sort").focus();
				return false;
			}
			
			var url = $("#urlId").val();
			if(url){
				if(url.toLowerCase().indexOf("http://")!=0){
					alert("链接的格式不对，请以http://开始");
					$("#urlId").focus();
					return false;
				}
				if(url.toLowerCase().indexOf(".okwei.com")<0){
					alert("链接只能在.okwei.com域名跳转");
					$("#urlId").focus();
					return false;
				}
			}
			
	 		var formdata = $("#popupForm").serializeJson()
            $.post("/myShopMgt/doAdd",formdata,function(data){
            	if(!data.error){
					parent.location.reload();
				}else{
					alert("开启状态的数量不能大于5个");
				}
            });
		});
		
		$("#cancel").click(function(){
			var index = parent.layer.getFrameIndex(window.name);
			parent.layer.close(index);
		});
		
		// 上传图片
		$('#uploadImg').uploadify({
			//'buttonImage' : '/statics/images/sctp_3_28.jpg',
			//'buttonClass' : 'browser',
			'buttonText':'',
			'dataType' : 'html',
			'removeCompleted' : false,
			'swf' : '/statics/js/uploadify/uploadify.swf',
			'debug' : false,
			'width' : '180',
			'height' : '90',
			'auto' : true,
			'multi' : false,
			'fileTypeExts' : '*.jpg;*.gif;*.png;*.jpeg',
			'fileTypeDesc' : '图片类型(*.jpg;*.jpeg;*.gif;*.png)',
			'formData' : {
				'cate' : "1",
				'type' : "3"
			},
			'uploader' : 'http://fdfsservice.okwei.com/handle/UploadImg.ashx',
			'fileSizeLimit' : '1024',
			'progressData' : 'speed',
			'removeCompleted' : true,
			'removeTimeout' : 0,
			'requestErrors' : true,
			'onFallback' : function() {
				alert("您的浏览器没有安装Flash");
			},
			'onUploadStart' : function(file) {
				$(".uploadify-queue").css("height", "50px");
			},
			'onUploadSuccess' : function(file, data, response) {
				var json = JSON.parse(data);
				if (json.Status != 1) {
					alert(json.StatusReason);
				} else {
					$("#shopImg").val(json.ImgUrl);
					$.ajax({
						url : "/userInfo/fullImgUrl",
						type : "get",
						async : false,
						contentType : "application/x-www-form-urlencoded; charset=utf-8",
						data : 'img='+json.ImgUrl,
						error : function(XMLHttpRequest, textStatus, errorThrown) {
							alert("服务器出现异常");
						},
						success : function(data) {
							$("#shopImage").attr('src',data.msg);
							$("#shopImageInput").val(data.msg);
						}
					});
				}
			}
		});
		
		
	});
</script>
<style type="text/css">
.uploadify-queue{ display:none;}
#uploadImg{ z-index:999; position:absolute; left:0; top:0;}
</style>
</head>

<body class="bg_f3">
	<form id="popupForm" name="popupForm"
		action="<%=basePath%>publishProduct/popup/getProducts"
		onsubmit="return false;">
		<input type="hidden" name="flag" value="${flag}"/>
		<input type="hidden" name="uadId" value="${pcRecommend.uadId}"/>
		<!-- 添加资讯 -->
		<div class="add_zunis" id="add_zixun">
			<ul>
				<li><span class="lanz_ones">标签：</span> <span class="lanz_twos"><input
						type="text" class="btn_h30 w222" name="title" value="${pcRecommend.title}" id="tagId"/></span>
				</li>
				<li><span class="lanz_ones">图片：</span> <span class="lanz_twos">
						<div class="imsic fl" style="position:relative;">
							<c:if test="${pcRecommend.imgLog!=null}">
								<img src="${pcRecommend.imgLog}" width="180" height="90" id="shopImage"/>
							</c:if>
							<c:if test="${pcRecommend.imgLog==null}">
								<img width="180" height="90" id="shopImage"/>
							</c:if>
							<input type="file" id="uploadImg" name="uploadImg" class="jbzl_tx_dw" style="background-position: center;opacity: 0;"/>
							<input type="hidden" id="shopImageInput" name="imgLog" value="${pcRecommend.imgLog}" id="imgId"/>
						</div>
						<div class="fl ml_10">
							<!-- <a href="#">上传图片</a> -->
							1200*300
						</div>
						<%-- <div class="jbzl_tx">
                            <img id="shopImage" src="${fullImgUrl}" width="85" height="85" />
                            <div class="jbzl_tx_dw" style="height:18px;line-height:18px; "><input type="file" id="uploadImg" name="uploadImg" class="jbzl_tx_dw" style="background-position: center;opacity: 0;"/></div>
                        </div> --%>
						
				</span></li>
				<li><span class="lanz_ones">排序：</span> <span class="lanz_twos"><input
						type="text" class="btn_h30 w222" name="sort"
						<c:if test="${flag==2}">
						value="${pcRecommend.sort}"
						</c:if>
						<c:if test="${flag==1}">
						value="1"
						</c:if>
						id="sort"/></span></li>
				<li><span class="lanz_ones">链接：</span> <span class="lanz_twos"><input
						type="text" class="btn_h30 w222" name="url"
						value="${pcRecommend.url}" id="urlId"/></span></li>
				<li><span class="lanz_ones">状态：</span> <span class="lanz_twos">
						<select class="btn_h30 w222" name="status">
							<option value="1" <c:if test="${pcRecommend.status==1}">selected</c:if>>新建</option>
							<option value="2" <c:if test="${pcRecommend.status==2 || flag==1}">selected</c:if>>开启</option>
							<option value="3" <c:if test="${pcRecommend.status==3}">selected</c:if>>关闭</option>
					</select>
				</span></li>
			</ul>
			<div class="clear"></div>
			<div class="font_bgsic fl">
				<font class="ft_red">排序说明：</font>如果出现重复排序时以最新保存时间的记录优先
			</div>
		</div>
		<span class="xubox_botton">
			<a class="xubox_yes xubox_botton2" href="javascript:;" id="confirm">确定</a>
			<a class="xubox_no xubox_botton3" href="javascript:;" id="cancel">取消</a>
		</span>
	</form>
</body>
</html>
