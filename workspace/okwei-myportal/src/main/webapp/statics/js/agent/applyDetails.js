/**
 * 申请结果
 */
function wins(title, win_id, width, height) { // title 标题 win_id 弹窗ID width 弹窗宽度 height 弹窗高度
	var pagei = $.layer({
		type : 1, // 0-4的选择,0：信息框（默认），1：页面层，2：iframe层，3：加载层，4：tips层。
		btns : 1,
		btn : [
			'关闭'
		],
		title : "查看申请资料",
		border : [
			0
		],
		closeBtn : [
			0
		],
		closeBtn : [
				0, true
		],
		shadeClose : true,
		area : [
				"520px", "530px"
		],
		page : {
			dom : '#win_div_10'
		}
	});
}
// 重新申请
function again() { // title 标题 win_id 弹窗ID width 弹窗宽度 height 弹窗高度
	var pagei = $.layer({
		type : 1, // 0-4的选择,0：信息框（默认），1：页面层，2：iframe层，3：加载层，4：tips层。
		btns : 1,
		btn : [
			'提交'
		],
		title : "申请代理",
		border : [
			0
		],
		closeBtn : [
			0
		],
		closeBtn : [
				0, true
		],
		shadeClose : true,
		area : [
				"520px", "630px"
		],
		page : {
			dom : '#win_div_9'
		},
		yes : function(index) {
			var name = $("#addname").val();// 名称
			var phone = $("#addphone").val();// 手机
			var address = $("#address").val();// 地址
			var applyID = $("#applyID").val();// 申请ID
			var code = $("#city").val();// 选中的城市
			var imgs = $("#imgs").attr("src");// 图片
			var intro = $("#intro").val();// 优势
			var recommend = $("#recommend").val();// 发展人微店号
			if (name == null || name == "") {
				alert("姓名不能为空");
				return false;
			}
			if (phone == null || phone == "") {
				alert("手机号码不能为空")
				return false;
			}
			if (!phone.match(/^1[3|4|5|7|8][0-9]\d{4,8}$/)) {
				alert("手机号码格式不正确")
				return false;
			}
			if (imgs == null || imgs == "") {
				alert("图片不能为空")
				return false;
			}
			if (code == null || code == "") {
				alert("请选择代理区域")
				return false;
			}
			$.ajax({
				url : "againApply",
				dataType : "json",
				data : {
					linkname : name,
					phone : phone,
					applyID : applyID,
					address : address,
					agentAreas : code,
					imgs : imgs,
					recommend : recommend,
					intro : intro
				},
				type : "post",
				success : function(data) {
					if (data.Statu == 1) {
						alert("申请成功");
						setTimeout(function() {
							layer.close(index);
							window.location.href = "myApply";
						}, 2000);
					}
					else {
						alert(data.StatusReson);
					}
				}
			});
		}
	});
}
$(function() {
	$("#addphone").keyup(function() {
		var tmptxt = $(this).val();
		$(this).val(tmptxt.replace(/\D/g, ''));
	});
	$("#recommend").keyup(function() {
		var tmptxt = $(this).val();
		$(this).val(tmptxt.replace(/\D/g, ''));
	});
	$("#sheng").change(function() {
		var code = $(this).val();
		$.ajax({
			url : "getRegionalListByCode",
			data : {
				code : code,
				demandID : $("#demands").val()
			},
			type : "post",
			dataType : "json",
			success : function(data) {
				if (data.Statu == 1) {
					var json = data.BaseModle;
					var html = "";
					$.each(json, function(index, city) {
						html += '<option value="' + city.code + '">' + city.name + '</option>';
					});
					$("#city").html(html);
					$("#city").val($("#selectcode").val()).change();
				}
			}
		});
	});
	// 上传图片
	$('#imgInput').uploadify({
		// 'buttonImage' : '/statics/images/sctp_3_28.jpg',
		// 'buttonClass' : 'browser',
		'buttonText' : '上传图片',
		'dataType' : 'html',
		'removeCompleted' : false,
		'swf' : '/statics/js/uploadify/uploadify.swf',
		'debug' : false,
		'width' : '85',
		'height' : '15',
		'auto' : true,
		'multi' : false,
		'fileTypeExts' : '*.jpg;*.gif;*.png;*.jpeg',
		'fileTypeDesc' : '图片类型(*.jpg;*.jpeg;*.gif;*.png)',
		'formData' : {
			'cate' : "1",
			'type' : "1"
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
			$("#imgs").attr("src", "http://img1.okimgs.com/" + json.ImgUrl);
		}
	});
	$("#sheng").change();
	$("#city").change(function() {
		var selectcode = $("#selectcode").val();
		var code = $(this).val();
		if (selectcode == code) {
			$("#recommend").attr("disabled", "disabled").val($("#laiyuanid").val());
		}
		else {
			$("#recommend").removeAttr("disabled");
		}
	});
})