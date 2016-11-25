/**
 * 安全设置
 */
/**
 * 倒计时
 */
var cdtime;
var seconds = 120;
function setseconds() {
	seconds = 120;
}
function countdown(id) {
	if (seconds == 0) {
		window.clearInterval(cdtime);
		$("#" + id).val("重新获取").removeClass("send");
		setseconds();
	}
	else {
		seconds--;
		$("#" + id).val(seconds + "s");
	}
}
$(function() {
	/* 是否展开 */
	var set = GetQueryString("set");
	if (set != null && set != "") {
		if (set == "login") {
			win_user('other_3');
		}
		else if (set == "paylogin") {
			win_user('bor_si')
		}
		else if (set == "phone") {
			win_user('jbz2');
		}
		else if (set == "name") {
			win_user('car_user');
		}
	}
	/**
	 * 绑定手机号码-输入验证
	 */
	$("#bind_phone").keyup(function() {
		NumVerify($(this));
	});
	/**
	 * 解除绑定-获取验证码
	 */
	$("#unbind_hqyzm").click(function() {
		var $this = $(this);
		if ($this.hasClass("send")) {
			return false;
		}
		$this.val("获取中").addClass("send");
		$.ajax({
			url : "ajaxuserinfo",
			type : "post",
			dataType : "json",
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			data : {
				"key" : "unphone"
			},
			error : function() {
				alert("异常！");
				$this.val("重新获取").removeClass("send");
				setseconds();
			},
			success : function(data) {
				if (data.state == "1") {
					cdtime = setInterval("countdown('unbind_hqyzm')", 1000);
				}
				else {
					alert(data.msg);
					$this.val("重新获取").removeClass("send");
					setseconds();
				}
			},
		});
	});
	/**
	 * 重新绑定手机-获取验证码
	 */
	$("#bind_hqyzm2").click(function() {
		var $this = $(this);
		if ($this.hasClass("send")) {
			return false;
		}
		var phone = $("#bind_phone2").val();
		if (phone.match(/^1[3|4|5|7|8][0-9]\d{4,8}$/)) {
			$this.val("获取中").addClass("send");
			$.ajax({
				url : "ajaxuserinfo",
				type : "post",
				dataType : "json",
				contentType : "application/x-www-form-urlencoded; charset=utf-8",
				data : {
					"key" : "yzphone",
					"phone" : phone
				},
				error : function() {
					alert("异常！");
					$this.val("重新获取").removeClass("send");
					setseconds();
				},
				success : function(data) {
					if (data.state == "1") {
						cdtime = setInterval("countdown('bind_hqyzm2')", 1000);
					}
					else {
						alert(data.msg);
						$this.val("重新获取").removeClass("send");
						setseconds();
					}
				},
			});
		}
		else {
			alert("输入手机格式不正确");
		}
	});
	/**
	 * 绑定手机号码-获取验证码
	 */
	$("#bind_hqyzm").click(function() {
		var $this = $(this);
		if ($this.hasClass("send")) {
			return false;
		}
		// 先验证手机正则
		var phone = $("#bind_phone").val();
		if (phone.match(/^1[3|4|5|7|8][0-9]\d{4,8}$/)) {
			$this.val("获取中").addClass("send");
			$.ajax({
				url : "ajaxuserinfo",
				type : "post",
				dataType : "json",
				contentType : "application/x-www-form-urlencoded; charset=utf-8",
				data : {
					"key" : "yzphone",
					"phone" : phone
				},
				error : function() {
					alert("异常！");
					$this.val("重新获取").removeClass("send");
					setseconds();
				},
				success : function(data) {
					if (data.state == "1") {
						cdtime = setInterval("countdown('bind_hqyzm')", 1000);
					}
					else {
						alert(data.msg);
						$this.val("重新获取").removeClass("send");
						setseconds();
					}
				},
			});
		}
		else {
			alert("输入手机格式不正确");
		}
	});
});
/**
 * 解除绑定手机
 */
function unphone() {
	var yzm = $("#unyzm").val();
	if (yzm == null || yzm == "") {
		alert("验证码不能为空");
		return false;
	}
	$.ajax({
		url : "ajaxuserinfo",
		type : "post",
		dataType : "json",
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		data : {
			"key" : "unbind",
			"yzm" : yzm
		},
		error : function() {
			alert("异常！");
			$(this).val("重新获取").removeClass("send");
			setseconds();
		},
		success : function(data) {
			if (data.state == "1") {
				// 下一步
				$("#unbind").slideUp("fast");
				$("#anewbind").slideDown("fast");
			}
			else {
				alert(data.msg);
				$(this).val("重新获取").removeClass("send");
				setseconds();
			}
		},
	});
}

/**
 * 手机绑定确认
 */
function bindphone() {
	var phone = $("#bind_phone").val();
	var yzm = $("#bind_yzm").val();
	if (!phone.match(/^1[3|4|5|7|8][0-9]\d{4,8}$/)) {
		alert("输入手机格式不正确");
		return false;
	}
	if (yzm == null || yzm == "") {
		alsert("验证码不能为空");
		return false;
	}
	$.ajax({
		url : "ajaxuserinfo",
		type : "post",
		dataType : "json",
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		data : {
			"key" : "getyzm",
			"phone" : phone,
			"yzm" : yzm
		},
		error : function() {
			alert("异常！");
			$(this).val("重新获取").removeClass("send");
			setseconds();
		},
		success : function(data) {
			if (data.state == "1") {
				location.reload();
			}
			else {
				alert(data.msg);
				$(this).val("重新获取").removeClass("send");
				setseconds();
			}
		},
	});
}
/**
 * 手机重新绑定
 */
function bind_phone2() {
	var phone = $("#bind_phone2").val();
	var yzm = $("#bind_yzm2").val();
	if (!phone.match(/^1[3|4|5|7|8][0-9]\d{4,8}$/)) {
		alert("输入手机格式不正确");
		return false;
	}
	if (yzm == null || yzm == "") {
		alsert("验证码不能为空");
		return false;
	}
	$.ajax({
		url : "ajaxuserinfo",
		type : "post",
		dataType : "json",
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		data : {
			"key" : "getyzm",
			"phone" : phone,
			"yzm" : yzm
		},
		error : function() {
			alert("异常！");
			$(this).val("重新获取").removeClass("send");
			setseconds();
		},
		success : function(data) {
			if (data.state == "1") {
				location.reload();
			}
			else {
				alert(data.msg);
				$(this).val("重新获取").removeClass("send");
				setseconds();
			}
		},
	});
}

/**
 * 数字验证
 */
function NumVerify($this) {
	var tmptxt = $this.val();
	$this.val(tmptxt.replace(/[^\d|-]/g, ''));
}
/**
 * 显示隐藏div
 */
function win_user(obj) {
	if ($("#" + obj).css('display') == 'none') {
		$("#" + obj).slideToggle("fast");
	}
	else {
		$("#" + obj).slideToggle("fast");
	}
}

function win(title, win_id) {
	var pagei = $.layer({
		type : 1, // 0-4的选择,0：信息框（默认），1：页面层，2：iframe层，3：加载层，4：tips层。
		btns : 1,
		btn : [
				'确定', '取消'
		],
		title : "www.okwei.com 上的页面显示：",
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
				'574px', '300px'
		],
		page : {
			dom : '#' + win_id
		}
	});
}

// 获取URL参数
function GetQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if (r != null)
		return unescape(r[2]);
	return null;
}