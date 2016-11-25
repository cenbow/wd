/**
 * 店铺信息
 */

$(function() {
	// 上传图片
	$('#uploadImg').uploadify({
		//'buttonImage' : '/statics/images/sctp_3_28.jpg',
		//'buttonClass' : 'browser',
		'buttonText':'更换头像',
		'dataType' : 'html',
		'removeCompleted' : false,
		'swf' : '/statics/js/uploadify/uploadify.swf',
		'debug' : false,
		'width' : '85',
		'height' : '16',
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
					}
				});
			}
		}
	});
	
	$('.jbzl_dl_bc').click(function(){
		$.ajax({
			url : "/userInfo/saveShopInfo",
			type : "post",
			async : false,
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			data : $('#shopinfoForm').serialize(),
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert("服务器出现异常");
			},
			success : function(data) {
				if (data.msg == '0') {
					alert('店铺资料修改成功!');
					location.href = "/userInfo/index"
				} else{
					alert('店铺资料修改失败!');
				}
			},
			beforeSend:function () {
				if ($('#shopName').val() == '') {
					alert('店铺名不能为空!');
					$('#shopName').focus();
					return false;
				}
				if ($('#shopBusContent').val() == '') {
					alert('主营不能为空!');
					$('#shopBusContent').focus();
					return false;
				}
				return true;
			}
		});
	});
});



