<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="java.util.ResourceBundle"%>
<%
    String okweidomain = ResourceBundle.getBundle("domain").getString(
					"okweidomain");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加分享专题</title>
<link rel="stylesheet" type="text/css" href="/statics/css/js.pagination.css" />
<link rel="stylesheet" type="text/css" href="/statics/js/uploadify/uploadify.css" />
<script type="text/javascript" src="/statics/js/jquery-josn.js"></script>
<script type="text/javascript" src="/statics/js/common/jquery.pagination.js"></script>
<script type="text/javascript" src="/statics/js/uploadify/jquery.uploadify-3.1.min.js"></script>
<script type="text/javascript">
	$(function() {
		$("[name=mzh_lx][value=" + shareType + "]").attr("checked", "checked");
		if (shareid > 0) {
			$("[name=mzh_lx]").attr("disabled", true);
		}
		$('[name=mzh_lx]').click(function() {
			shareType = $(this).val();
			$("#prodiv").html("");
			$("#imgdiv").html("");
			if (shareType == 2) {
				$("#divtuwen").show();
			} else {
				$("#divtuwen").hide();
			}
		});
	});

	/** 弹窗调用函数 **/
	function win2(title, win_id, width, height) { //title 标题 win_id 弹窗ID  width 弹窗宽度 height 弹窗高度
		var pagei = $.layer({
			type : 1, //0-4的选择,0：信息框（默认），1：页面层，2：iframe层，3：加载层，4：tips层。
			btns : 2,
			btn : [ '继续添加分享页', '关闭' ],
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
				location.href = "sharelist";
			},
			no : function(index) {
				//跳转为继续分享页
				location.href = "sharelist";
			},
			yes : function(index) {
				location.reload();
			}
		});
	}
</script>
<style>
.left_xuanzs {
	float: left;
	width: 630px;
	max-height: 65px;
	overflow-y: auto;
}

#win_div_2 table tr {
	border-bottom: 1px solid #ddd;
}

#win_div_2 table tr th {
	border-right: 1px solid #ddd;
}

#win_div_2 table tr td {
	padding: 10px 0px;
	border-right: 1px solid #ddd;
}

.mzh_fxcp {
	padding: 0px;
	border: none;
	margin: margin: 0 20px 5px 0;
}
</style>
<script type="text/javascript">
	/** 弹窗调用函数 **/
	function opentc() {
		var pagei = $.layer({
			type : 1, //0-4的选择,0：信息框（默认），1：页面层，2：iframe层，3：加载层，4：tips层。
			btns : 2,
			btn : [ '确定', '取消' ],
			title : "请选择需要分享的产品",
			border : [ 0 ],
			closeBtn : [ 0 ],
			closeBtn : [ 0, true ],
			shadeClose : true,
			area : [ 740, 650 ],
			page : {
				dom : '#win_div_2'
			},
			no : function(index) {
				$("#win_div_2").hide();
			},
			yes : function(index) {
				if (prolist.length <= 0) {
					alert("请选择要分享的产品！");
					return;
				}
				if (shareType == 2) {
					if (prolist.length != 1) {
						alert("只能选择一个商品！");
						return;
					}
				}
				var html = "";
				$.each(prolist, function(n, value) {
					html += "<div class='mzh_fxcp' sid='"+value.id+"' proid='"+value.proid+"'>";
					html += "<img src='/statics/images/delete_imgs.png' class='mzh_fxcp_close'> <img src='"+value.image+"' width='121' height='121'>";
					html += "<div class='mzh_fxcp_bt'>" + value.title + "</div>";
					html += "<span>现价：￥<label>" + value.price + "</label></span>";
					html += "<span>原价：<font>￥<label>" + value.yprice + "</label></font></span>";
					if (shareType == 3) {
						html += "<textarea class='jbzl_dl_textarea mt_10' maxlength='50' style='width: 110px;height: 120px;' placeholder='详图说明,限50字内'></textarea>";
					}
					html += "</div>";
				});
				if (shareType == 2) {
					$("#prodiv").html(html);
				} else {
					$("#prodiv").append(html);
				}
				prolist = new Array();
				layer.close(index);
			}
		});
		// 重新 加载数据
		$("[name=mzh_4_7_yes]").removeClass("yes_bgs");
		$("[name=mzh_4_7_yes]:first").addClass("yes_bgs");
		$("#txtTitle").val("");
		$("#selType").find("option[value='0']").attr("selected", true);
		page();
	}
	// 分页
	function page() {
		// 获取分页的数量
		var ids = new Array();
		$(".mzh_fxcp").each(function() {
			ids.push($(this).attr("sid"));
		});
		var data = {
			sid : $(".yes_bgs").attr("data"),
			title : $.trim($("#txtTitle").val()),
			type : $("#selType").val(),
			ids : ids.toString(),
		};
		var num_entries = 0;
		$.ajax({
			url : "/share/count",
			type : "post",
			async : false,
			data : data,
			success : function(data) {
				num_entries = data;
			}
		});
		// 创建分页
		$("#Pagination").pagination(num_entries, {
			num_edge_entries : 1, //边缘页数
			num_display_entries : 4, //主体页数
			callback : pageselectCallback,
			items_per_page : 10, //每页显示1项
			prev_text : "上一页",
			next_text : "下一页"
		});
		function pageselectCallback(page_index, jq) {
			$("#tabpro tr:not(:first)").remove();
			$("#tabpro").append("<tr><td colspan='7''><img width='50' height='50' src='/statics/images/loadimg.gif' style='margin: 93px 0px;''></td></tr>");
			data.index = page_index + 1;
			$.ajax({
				url : "/share/product",
				type : "post",
				data : data,
				success : function(data) {
					$("#tabpro tr:not(:first)").remove();
					if (data != null && data.length > 0) {
						var html = "";
						$.each(data, function(n, value) {
							html += "<tr><td><input name='checkpro' ";
							for (var i = 0; i < prolist.length; i++) {
								if (value.ID == prolist[i].id) {
									html += " checked='checked' ";
									break;
								}
							}
							html += "type='checkbox' sid='" + value.ID + "' proid='" + value.proID + "'></td>";
							html += "<td><img src='"+value.proImage+"' width='100' height='100'></td>";
							html += "<td>" + value.proTitle + "</td>";
							html += "<td><p class='line_30'>现价：￥<lable name='price'>" + value.price + "</lable></p> <p class='line_30 ft_c9'>原价：<font style='text-decoration: line-through;''>￥<lable name='yprice'>" + value.yprice + "</lable></font></p><p class='line_30'>佣金：￥" + value.commission + "</p></td>";
							html += "<td>" + value.shopClass + "</td>";
							if (value.type == 1) {
								html += "<td>自营</td>";
							} else {
								html += "<td>分销</td>";
							}
							html += "<td>" + value.supName + "</td></tr>";
						});
						$("#tabpro").append(html);
					} else {
						$("#tabpro").append("<tr><td colspan='7'><div class='mzh_100 fl' style='margin: 115px 0px;''>没有查到相应产品，</br>店铺产品不够丰富，没关系！先到<a class='ft_lan' href='http://www.okwei.com/list/list'>云端产品库</a>上架心仪的产品到店铺，再继续制作分享页！</div></td></tr>");
					}
				}
			});
			return false;
		}
	}
	var prolist = new Array();
	$(function() {
		$("[name=mzh_4_7_yes]").click(function() {
			$("[name=mzh_4_7_yes]").removeClass("yes_bgs");
			$(this).addClass("yes_bgs");
			page();
		});

		$("#checkall").click(function() {
			if ($(this).attr("checked")) {
				$("[name=checkpro]").attr("checked", true);
			} else {
				$("[name=checkpro]").attr("checked", false);
			}
			checkproduct();
		});
		$("[name=checkpro]").live("click", function() {
			checkproduct();
		});
		$(".mzh_fxcp_close").live("click", function() {
			$(this).parent(".mzh_fxcp").remove();
		});
		$("#subinput").bind("click", function() {
			addshare();
		});
		
		var upImg = $("#btnUpImg").uploadify({
			'buttonImage' : '/statics/images/xin_inselimg.png',
			'buttonText' : '选择图片',
			'buttonClass' : 'browser',
			'dataType' : 'html',
			'removeCompleted' : false,
			'swf' : '/statics/js/uploadify/uploadify.swf',//flash文件路径
			'debug' : false,
			'width' : '121',//按钮宽度
			'height' : '121',//按钮高度
			'auto' : true,
			'multi' : true,
			'queueSizeLimit' : 5,//图片最大上传数量
			'timeoutuploadLimit' : 5,//能同时上传的文件数目
			'fileTypeExts' : '*.jpg;*.gif;*.png;*.jpeg',//文件类型
			'fileTypeDesc' : '图片类型(*.jpg;*.jpeg;*.gif;*.png)',
			'formData' : {
				FileUpload : "proimage",
				cate : 1,
				type : 1
			},//参数
			'uploader' : 'http://fdfsservice.okwei.com/handle/UploadImg.ashx',
			'fileSizeLimit' : '1024',//文件最大大小
			'progressData' : 'speed',
			'removeCompleted' : true,
			'removeTimeout' : 0,
			'requestErrors' : true,
			'onFallback' : function() {
				alert("您的浏览器没有安装Flash");
			},
			'onUploadStart' : function(file) {
				return;
				alert('id: ' + file.id + ' - 索引: ' + file.index + ' - 文件名: ' + file.name + ' - 文件大小: ' + file.size + ' - 类型: ' + file.type + ' - 创建日期: ' + file.creationdate + ' - 修改日期: ' + file.modificationdate + ' - 文件状态: ' + file.filestatus);
			},
			'onProgress ' : function() {

			},
			'onUploadSuccess' : function(file, data, response) {
				if (response == true) {
					data = eval("(" + data + ")");
					if (data != null && data.Status == 1) {
						var html = "<div class='mzh_fxcp'>";
						html += "<img src='/statics/images/delete_imgs.png' class='mzh_fxcp_close'> <img src='http://img3.okimgs.com"+data.ImgUrl+"' width='121' height='121'>";
						html += "<textarea class='jbzl_dl_textarea mt_10' maxlength='50' style='width: 110px; height: 120px;' placeholder='详图说明,限50字内'></textarea></div>";
						$("#imgdiv").append(html);
					}
				}
			},
			'onDialogClose' : function(swfuploadifyQueue) {
				var len = 5 - $("#imgdiv .mzh_fxcp").length;
				if (swfuploadifyQueue.filesSelected > len && swfuploadifyQueue.filesSelected <= 5) {
					alert("最多只能选择" + len + "张图片!");
					upImg.uploadify('cancel', '*');
					return;
				}
			}
		});
	});

	function checkproduct() {
		$("[name=checkpro]").each(function() {
			var check = false;
			if ($(this).attr("checked") == "checked")
				check = true;
			var sid = $(this).attr("sid");
			var flag = false;
			for (var i = 0; i < prolist.length; i++) {
				if (sid == prolist[i].id) {
					flag = true;
					if (!check) {
						prolist.splice(i, 1);
					}
					break;
				}
			}
			if (check && !flag) {
				var pro = new Object();
				pro.id = sid;
				pro.proid = $(this).attr("proid");
				var tr = $(this).parents("tr");
				pro.image = tr.find("img").attr("src");
				pro.title = tr.find("td:eq(2)").html();
				pro.price = tr.find("[name=price]").html();
				pro.yprice = tr.find("[name=yprice]").html();
				prolist.push(pro);
			}
		});
	}

	function showview() {
		var title = $.trim($("#maintitle").val());
		if (title == "") {
			alert("分享标题不能为空！", false);
			return;
		}
		var des = $.trim($("#maindes").val());
		if (des == "") {
			alert("分享推广语不能为空！", false);
			return;
		}
		var formlist = new Array();
		$("#prodiv .mzh_fxcp").each(function() {
			var pro = new Object();
			pro.shelveId = $(this).attr("sid");
			pro.productId = $(this).attr("proid");
			pro.productPicture = $(this).find("img:eq(1)").attr("src");
			pro.productName = $(this).find(".mzh_fxcp_bt").html();
			pro.retailPrice = $(this).find("label:eq(0)").html();
			pro.displayPrice = $(this).find("label:eq(1)").html();
			pro.companyName = $(this).find("textarea").val();
			;
			formlist.push(pro);
		});

		if (formlist.length <= 0) {
			alert("请选择要分享的产品！", false);
			return;
		}
		var imglist = new Array();
		if (shareType == 2) {
			if (formlist.length != 1) {
				alert("只能选择一个商品！");
				return;
			}
			// 图文最少一个
			$("#imgdiv .mzh_fxcp").each(function() {
				var img = new Object();
				img.imageUrl = $(this).find("img:eq(1)").attr("src");
				img.description = $(this).find("textarea").val();
				imglist.push(img);
			});
			if (imglist.length <= 0) {
				alert("请上传产品详图！", false);
				return;
			}
			if (imglist.length > 5) {
				alert("产品详图最多5张", false);
				return;
			}
		} else {
			if (formlist.length<5 || formlist.length>100) {
				alert("分享的产品数量需在5到100个之间！", false);
				return;
			}
		}

		$("#formTitle").val(title);
		$("#formDes").val(des);
		$("#shareType").val(shareType);
		$("#formList").val($.toJSON(formlist));
		$("#imgList").val($.toJSON(imglist));
		document.forms[0].submit();
	}

	function addshare() {
		var title = $.trim($("#maintitle").val());
		if (title == "") {
			alert("分享标题不能为空！", false);
			return;
		}
		var des = $.trim($("#maindes").val());
		if (des == "") {
			alert("分享推广语不能为空！", false);
			return;
		}
		var prolist = new Array();
		$("#prodiv .mzh_fxcp").each(function() {
			var pro = new Object();
			pro.shelveId = $(this).attr("sid");
			pro.productId = $(this).attr("proid");
			pro.description = $(this).find("textarea").val();
			prolist.push(pro);
		});
		if (prolist.length <= 0) {
			alert("请选择要分享的产品！", false);
			return;
		}
		var imglist = new Array();
		if (shareType == 2) {
			if (prolist.length != 1) {
				alert("只能选择一个商品！");
				return;
			}
			// 图文最少一个
			$("#imgdiv .mzh_fxcp").each(function() {
				var img = new Object();
				img.imageUrl = $(this).find("img:eq(1)").attr("src");
				img.description = $(this).find("textarea").val();
				imglist.push(img);
			});
			if (imglist.length <= 0) {
				alert("请上传产品详图！", false);
				return;
			}
			if (imglist.length > 5) {
				alert("产品详图最多5张", false);
				return;
			}
		} else {
			if (prolist.length<5 || prolist.length>100) {
				alert("分享的产品数量需在5到100个之间！", false);
				return;
			}
		}
		var data = {
			shareType : shareType,
			shareid : shareid,
			title : title,
			des : des,
			prolist : $.toJSON(prolist),
			imglist : $.toJSON(imglist)
		}
		// 防止重复提交
		$("#subinput").unbind("click");
		$.ajax({
			url : "/share/addshare",
			type : "post",
			data : data,
			success : function(data) {
				if (data.Statu == 1) {
					var url = "/share/url?share=" + data.BaseModle;
					$("#imgId").attr('src', url);
					//调用弹窗函数win2()
					win2("保存成功", "win_div_3", 600, 400);
				} else {
					alert(data.StatusReson, false);
					$("#subinput").bind("click", function() {
						addshare();
					});
				}
			}
		});
	}
	var shareid = "${share.shareid}";
	var shareType = "${share.shareType}";
</script>
</head>
<body>
	<div class="zhuag_suv bor_si fl bg_white" style="min-height: 710px;">
		<div class="p10">
			<div class="fl fic_kuan">
				<div class="fic_biaot fl f14 tr ft_c3">标题：</div>
				<div class="fic_select fl">
					<input id="maintitle" value="${share.title }" maxlength="20" type="text" class="btn_h42 fl f14 w350" placeholder="限20字内，如：特产系列特卖">
				</div>
			</div>
			<a class="fl" href="http://www.<%=okweidomain%>/help/fxysm">分享页说明</a>
			<div class="fl fic_kuan">
				<div class="fic_biaot fl f14 tr ft_c3">推广语：</div>
				<div class="fic_select fl">
					<textarea id="maindes" maxlength="150" class="jbzl_dl_textarea mt_10" style="width: 350px; height: 80px;" placeholder="限150字内。可输入推广语、品牌故事、产品优势等">${share.des }</textarea>
				</div>
			</div>
			<!-- ************************************************ -->
			<div class="fl fic_kuan">
				<div class="fic_biaot fl f14 tr ft_c3">模版：</div>
				<div class="fic_select fl f14" id="radios">
					<p class="ft_c9 line_42">(选择分享产品的展示模版)</p>
					<div class="fl mr_20">
						<input type="radio" <c:if test="${share.shareType==1 }">checked="checked"</c:if> name="mzh_lx" value="1" id="mzh_jy"> <label for="mzh_jy">简易型</label>
					</div>
					<div class="fl mr_20">
						<input type="radio" <c:if test="${share.shareType==2 }">checked="checked"</c:if> name="mzh_lx" value="2" id="mzh_dange"> <label for="mzh_dange">单个精品</label>
					</div>
					<div class="fl mr_20">
						<input type="radio" <c:if test="${share.shareType==3 }">checked="checked"</c:if> name="mzh_lx" value="3" id="mzh_duoge"> <label for="mzh_duoge">多个精品</label>
					</div>
				</div>
			</div>
			<!-- ************************************************ -->
			<div class="fl fic_kuan" id="div_jy">
				<div class="fic_biaot fl f14 tr ft_c3">分享产品：</div>
				<div class="fic_select fl">
					<div id="prodiv">
						<c:forEach var="val" items="${share.list }">
							<div class="mzh_fxcp" sid="${val.ID }" proid="${val.proID }">
								<img src="/statics/images/delete_imgs.png" class="mzh_fxcp_close"> <img src="${val.proImage }" width="121" height="121">
								<div class="mzh_fxcp_bt">${val.proTitle }</div>
								<span>现价：￥<label>${val.price }</label></span> <span>原价：<font>￥<label>${val.yprice }</label></font></span>
								<c:if test="${share.shareType==3 }">
									<textarea placeholder="产品描述,限50字内" style="width: 120px; height: 120px;" maxlength="50" class="jbzl_dl_textarea mt_10 mr_20">${val.supName }</textarea>
								</c:if>
							</div>
						</c:forEach>
					</div>
					<img src="/statics/images/xin_inselimg.png" id="imgShare" class="shou mt_10" onclick="opentc()" />
				</div>
			</div>
			<div class="fl fic_kuan" id="divtuwen" <c:if test="${share.shareType!=2 }">style="display: none;"</c:if>>
				<div class="fic_biaot fl f14 tr ft_c3">
					产品详图：
					<p class="fl ft_c9" style="line-height: 0px;">(最多5张)</p>
				</div>
				<div class="fic_select fl">
					<div id="imgdiv">
						<c:if test="${share.shareType==2 }">
							<c:forEach var="val" items="${share.list[0].singlist }">
								<div class="mzh_fxcp">
									<img src="/statics/images/delete_imgs.png" class="mzh_fxcp_close"> <img src="${val.imageUrl }" width="121" height="121">
									<textarea placeholder="产品描述,限50字内" style="width: 120px; height: 120px;" maxlength="50" class="jbzl_dl_textarea mt_10 mr_20">${val.description }</textarea>
								</div>
							</c:forEach>
						</c:if>
					</div>
					<div class="mzh_fxcp">
						<div id="btnUpImg"></div>
					</div>
				</div>
			</div>
			<div class="fl fic_kuan">
				<div class="fic_biaot fl f14 tr ft_c3"></div>
				<div class="fic_select fl">
					<input onclick="showview();" id="showview" type="button" style="width: 80px; background: #fff; border: 1px solid #ccc; color: #999;" class="btn_submit_two fl" value="预览"><input id="subinput" type="button" style="width: 80px;" class="btn_submit_two fl ml_20" value="保存">
				</div>
			</div>
		</div>
	</div>
	<!-- 请选择需要分享的产品 -->
	<div class="fl mzh_100 f14" style="display: none;" id="win_div_2">
		<c:if test="${fn:length(claList) > 0}">
			<div class="mzh_100 fl">
				<div class="left_font tr fl ft_c9">商品分类：</div>
				<div class="left_xuanzs fl">
					<ul>
						<li name="mzh_4_7_yes" class="yes_bgs" data="0">全部</li>
						<c:forEach var="cla" items="${claList }">
							<li name="mzh_4_7_yes" data="${cla.sid }">${cla.sname }</li>
						</c:forEach>
					</ul>
				</div>
			</div>
		</c:if>
		<div class="mzh_100 fl">
			<span class="fl">标题：</span> <input id="txtTitle" type="text" class="btn_h24 w164 fl"> <span class="fl ml_40">类型：</span> <select id="selType" required="true" class="btn_h28" style="width: 127px;" onchange="page()">
				<option value="0">全部</option>
				<option value="1">自营</option>
				<option value="2">分销</option>
			</select> <input type="submit" value="搜索" class="btn_submit_two ml_40" style="width: 80px;" onclick="page()">
		</div>
		<div class="fl mzh_100 mt_20" style="max-height: 320px; overflow-y: scroll;">
			<table id="tabpro" class="mzh_100 fl bg_white bor_si f14 line_42 tc">
				<tr class="bg_f3">
					<th width="30"><input id="checkall" type="checkbox"></th>
					<th width="130">图片</th>
					<th>标题</th>
					<th>价格</th>
					<th>店铺分类</th>
					<th>类型</th>
					<th>供应商</th>
				</tr>
			</table>
		</div>
		<div class="pull-right">
			<div id="Pagination" class="pagination">
				<!-- 这里显示分页 -->
			</div>
		</div>
	</div>
	<!-- 保存成功 -->
	<div class="fl mzh_100 f14" style="display: none; width: 560px;" id="win_div_3">
		<div class="mzh_100 fl">
			<img src="/statics/images/d-ico1.png" class="fl" width="30" style="margin-left: 100px;">
			<h2 class="ft_c3 fl ml_10 line_30">恭喜你，成功制作一个新分享页！</h2>
		</div>
		<div class="mzh_100 fl f14">
			<ul style="margin-left: 140px;">
				<li class="fl mzh_100 mt_10">分享给其它小伙伴，获得更多客流!</li>
				<li class="fl mzh_100 mt_10"><img src="" id="imgId" width="130" height="130"></li>
				<li class="fl mzh_100 mt_10 ft_c6">扫描后分享到朋友圈</li>
			</ul>
		</div>
	</div>
	<form action="/share/view" target="_blank" method="post">
		<input type="hidden" id="formTitle" name="formTitle" /> <input type="hidden" id="formDes" name="formDes" /> <input type="hidden" id="formList" name="formList" /> <input type="hidden" id="shareType" name="shareType" /> <input type="hidden" id="imgList" name="imgList" />
	</form>
</body>
</html>