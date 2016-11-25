/**
 * 申请认证员
 */
$(function() {
	InitCity();
	// 姓名验证
	$("#name").blur(function() {
		isname($(this).val());
	});
	// QQ验证
	$("#qq").blur(function() {
		yzqq($(this).val());
	});
	// 手机验证
	$("#phone").blur(function() {
		yzphone($(this).val());
	});
	// 个人简介
	$("#synopsis").blur(function() {
		yzjian($(this).val());
	});
	// 正式验证
	$("#zsno").blur(function() {
		var zsno = $(this).val();
		iszs(zsno);
	});
	// 验证区域
	$("#qu").blur(function() {
		isdistrict();
	});
});
// 申请正式
function applyzsjx() {
	var text = $("#synopsis").val();
	if (!yzjian(text)) {
		return false;
	}
	$.ajax({
		url : "applyzs",
		type : "post",
		dataType : "json",
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		data : {
			"reason" : text
		},
		error : function() {
			alert("异常！");//
		},
		success : function(data) {
			if (data.state == "1") {
				window.location.href = "waitaudit";
			}
			else if (data.state == "-1") {
				alert(data.msg);
			}
		}
	})
}
// 申请见习
function applyjx() {
	var name = $("#name").val();
	var phone = $("#phone").val();
	var qq = $("#qq").val();
	var sheng = $("#sheng").val();
	var shi = $("#shi").val();
	var qu = $("#qu").val();
	var synopsis = $("#synopsis").val();
	var headimg = $("#headimg").attr("src");
	var idimg = $("#idimg").attr("src");
	var zsno = $("#zsno").val();
	if (!isname(name)) {
		return false;
	}
	if (!yzphone(phone)) {
		return false;
	}
	if (!isqq(qq)) {
		return false;
	}
	if (!isdistrict()) {
		return false;
	}
	if (!yzjian(synopsis)) {
		return false;
	}
	if (!isImg(headimg)) {
		$("#headimgtips>span:eq(1)").show().siblings().hide();
		return false;
	}
	else {
		$("#headimgtips>span:eq(0)").show().siblings().hide();
	}
	if (!isImg(idimg)) {
		$("#idimgtips>span:eq(1)").show().siblings().hide();
		return false;
	}
	else {
		$("#idimgtips>span:eq(0)").show().siblings().hide();
	}
	if (zsno == null || zsno == "" || !zsno.match(/^\d{4,12}$/)) {
		$("#zsnotips>span:eq(1)").show().siblings().hide();
		return false;
	}
	$.ajax({
		url : "applysubmit",
		type : "post",
		dataType : "json",
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		data : {
			"name" : name,
			"phone" : phone,
			"qq" : qq,
			"sheng" : sheng,
			"shi" : shi,
			"qu" : qu,
			"synopsis" : synopsis,
			"headimg" : headimg,
			"idimg" : idimg,
			"zsno" : zsno
		},
		error : function() {
			alert("异常！");//
		},
		success : function(data) {
			if (data.state == "1") {
				window.location.href = "waitaudit";
			}
			else if (data.state == "-1") {
				alert(data.msg);
			}
		}
	})
}
// 判断正式
function iszs(zsno) {
	if (zsno == null || zsno == "" || !zsno.match(/^\d{4,12}$/)) {
		$("#zsnotips>span:eq(1)").show().siblings().hide();
		return false;
	}
	$.ajax({
		url : "judgezsno",
		type : "post",
		dataType : "json",
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		data : {
			"zsno" : zsno
		},
		error : function() {
			alert("异常！");//
		},
		success : function(data) {
			if (data.msg == "1") {
				$("#zsnotips>span:eq(0)").show().siblings().hide();
			}
			else {
				$("#zsnotips>span:eq(1)").show().siblings().hide();
			}
		},
	});
}
// 验证图片是否合格
function isImg(src) {
	if (src == null || src == "" || src == "http://img3.okdocuments.com/group1/M00/11/49/wKgKPlWA0OyAQBcYAAAHU0i8aa8750.png") {
		return false;
	}
	return true;
}
// 验证区域选择
function isdistrict() {
	var sheng = $("#sheng").val();
	var shi = $("#shi").val();
	var qu = $("#qu").val();
	if (sheng == null || sheng == "" || sheng == "0") {
		$("#quyutips>span:eq(1)").show().siblings().hide();
		return false;// alert("请选择省");
	}
	if (shi == null || shi == "" || shi == "0") {
		$("#quyutips>span:eq(1)").show().siblings().hide();
		return false;// alert("请选择市");
	}
	if (qu == null || qu == "" || qu == "0") {
		$("#quyutips>span:eq(1)").show().siblings().hide();
		return false;// alert("请选择区");
	}
	$("#quyutips>span:eq(0)").show().siblings().hide();
	return true;
}
// 验证qq
function yzqq(text) {
	if (text != null && text != "" && isqq(text)) {
		$("#qqtips>span:eq(0)").show().siblings().hide();
		return true;
	}
	else {
		$("#qqtips>span:eq(1)").show().siblings().hide();
		return false;
	}
}
// 简介验证
function yzjian(text) {
	if (text != null && text != "" && text.length <= 200) {
		$("#synopsistips>span:eq(0)").show().siblings().hide();
		return true;
	}
	else {
		$("#synopsistips>span:eq(1)").show().siblings().hide();
		return false;
	}
}
// 手机验证
function yzphone(text) {
	if (text != null && text != "" && isphone(text)) {
		$("#phonetips>span:eq(0)").show().siblings().hide();
		return true;
	}
	else {
		$("#phonetips>span:eq(1)").show().siblings().hide();
		return false;
	}
}
// 姓名验证
function isname(text) {
	if (text != null && text != "" && text.length >= 2) {
		$("#nametips>span:eq(0)").show().siblings().hide();
		return true;
	}
	else {
		$("#nametips>span:eq(1)").show().siblings().hide();
		return false;
	}
}
// 手机正则验证
function isphone(phone) {
	if (phone.match(/^1[3|4|5|7|8][0-9]\d{4,8}$/)) {
		return true;
	}
	else {
		return false;
	}
}
// qq验证
function isqq(text) {
	if (text.match(/^\d{4,12}$/)) {
		$("#qqtips>span:eq(0)").show().siblings().hide();
		return true;
	}
	else {
		$("#qqtips>span:eq(1)").show().siblings().hide();
		return false;
	}
}

// 上传图片
$('#upheadimg').uploadify({
	"buttonClass" : "sc_botms",
	'buttonText' : '上传',
	'dataType' : 'html',
	'removeCompleted' : false,
	'swf' : '/statics/js/uploadify/uploadify.swf',
	'debug' : false,
	'width' : '110',
	'height' : '34',
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
		$("#upheadimg").addClass("");
	},
	'onUploadSuccess' : function(file, data, response) {
		var json = JSON.parse(data);
		if (json.Status != 1) {
			alert(json.StatusReason);
		}
		else {
			$.ajax({
				url : "/verify/fullImgUrl",
				type : "get",
				async : false,
				contentType : "application/x-www-form-urlencoded; charset=utf-8",
				data : 'img=' + json.ImgUrl,
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert("服务器出现异常");
				},
				success : function(data) {
					$("#headimg").attr('src', data.msg);
				}
			});
		}
	}
})

// 上传图片
$('#upidimg').uploadify({
	"buttonClass" : "sc_botms",
	'buttonText' : '上传',
	'dataType' : 'html',
	'removeCompleted' : false,
	'swf' : '/statics/js/uploadify/uploadify.swf',
	'debug' : false,
	'width' : '110',
	'height' : '34',
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
		$("#upheadimg").addClass("");
	},
	'onUploadSuccess' : function(file, data, response) {
		var json = JSON.parse(data);
		if (json.Status != 1) {
			alert(json.StatusReason);
		}
		else {
			$.ajax({
				url : "/verify/fullImgUrl",
				type : "get",
				async : false,
				contentType : "application/x-www-form-urlencoded; charset=utf-8",
				data : 'img=' + json.ImgUrl,
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					alert("服务器出现异常");
				},
				success : function(data) {
					$("#idimg").attr('src', data.msg);
				}
			});
		}
	}
})