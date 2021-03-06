/**
 * 第三方注册
 */

/**
 * 显示错误提示
 */
function ShowErrorTip(_this, msg) {
	$(_this).parent().siblings().find("img.fang").attr("src", "/statics/images/c-ico1.png").show();
	$("#liTip font").html(msg);
	$("#liTip").show();
}

/**
 * 显示正确提示
 */
function ShowRightTip(_this) {
	$(_this).parent().siblings().find("img.fang").attr("src", "/statics/images/d-ico1.png").show();
	$("#liTip").hide();
}

/**
 * 如果手机号码输入正确，显示验证码
 */
function yzmxs() {
	var myDate = new Date();
	$("#yzmimg").attr("src", "/getVImageCode?" + myDate.toLocaleString());
	$(".sjyzm").hide();
	$("#yzm").val("");
	$(".yzm").fadeIn(400);
	$("#yzm").focus();
}
/**
 * 发送短信
 */
function emailSend() {
	var phone = $("#phoneTxt").val();
	if (phone == null || phone == "") {
		ShowErrorTip($("#phoneTxt"), "请输入手机号");
		return false;
	}
	if (!phone.match(/^1[3|4|5|7|8][0-9]\d{4,8}$/)) {
		ShowErrorTip($("#phoneTxt"), "输入手机格式不正确");
		return false;
	}
	var url = "/senPhoneCode";
	var data = {
		phone : phone
	};
	$("#sendCheckCode").unbind("click");
	$.post(url, data, function(result) {
		if (result.status == 1) {
			ShowRightTip($("#phoneTxt"));
			bddjs($("#sendCheckCode"), 60);
		}
		else if (result.status == -2) {
			$(".yzm").show();
			$(".sjyzm").hide();
		}
		else {
			ShowErrorTip($("#txtCheckCode"), result.message);
		}
	});
};

/**
 * 绑定倒计时
 */
function bddjs(obj, num) {
	var tim = num;
	$(obj).unbind("click");
	var sil = setInterval(function() {
		tim--;
		if (tim < 1) {
			clearInterval(sil);
			$(obj).bind("click", function() {
				emailSend();
			});
			$(obj).html("获取手机验证码");
		}
		else {
			$(obj).html(tim + "秒可以重新获取");
		}
	}, 1000);
}
$(function() {
	// 发送短信验证码
	$("#sendCheckCode").bind("click", function() {
		emailSend();
	});

	// 密码 失去焦点
	$("#passwordTxt1").bind("blur", function() {
		var event = arguments.callee.caller.arguments[0] || window.event; // 消除浏览器差异
		var value = $(this).val();
		if (value == null || value == "") {
			ShowErrorTip(this, "请输入密码");
		}
		else if (value.length < 6) {
			ShowErrorTip(this, "密码长度不小于6");
		}
		else {
			ShowRightTip(this);
			if (value == $("#passwordTxt2").val()) {
				ShowRightTip($("#passwordTxt2"));
			}
		}
	});
	// 重复密码 失去焦点
	$("#passwordTxt2").bind("blur", function() {
		var event = arguments.callee.caller.arguments[0] || window.event; // 消除浏览器差异
		var value = $(this).val();
		if (value == null || value == "") {
			ShowErrorTip(this, "请再次输入密码");
		}
		else if (value != $("#passwordTxt1").val()) {
			ShowErrorTip(this, "两次密码不一致");
		}
		else {
			ShowRightTip(this);
		}
	});
	// 手机号只能输入数字
	$("#phoneTxt").keyup(function() {
		var tmptxt = $(this).val();
		$(this).val(tmptxt.replace(/\D|^0/g, ''));
		if (tmptxt.length == 11) {
			$(this).blur();
			// 校验显示
		}
	}).bind("paste", function() {
		var tmptxt = $(this).val();
		$(this).val(tmptxt.replace(/\D|^0/g, ''));
	}).css("ime-mode", "disabled");
	// 手机 失去焦点
	$("#phoneTxt").bind("blur", function() {
		var value = $(this).val();
		var _this = $(this);
		if (value == null || value == "") {
			ShowErrorTip(this, "请输入手机号");
			return;
		}
		else {
			if (!value.match(/^1[3|4|5|7|8][0-9]\d{4,8}$/)) {
				ShowErrorTip(this, "输入手机格式不正确");
				return false;
			}
			// 判断是否注册
			var url = "/findPhone";
			var data = {
				phone : value
			};
			$.post(url, data, function(result) {
				if (result == "0") {
					isCheckPhone = true;
					ShowRightTip(_this);
					yzmxs();
				}
				else if (result.weiid != null) {
					ShowErrorTip(_this, "手机号已被注册,若号码是您的请<a href='http://port.okwei.com' style='color:#1570a5;'>登录</a>");
				}
			});
		}
	});
	// 当图像验证码输入够4个的时候
	$("#yzm").keyup(function() {
		var len = $(this).val().length;
		if (len == 4) {
			$(this).blur();
		}
	});
	$("#yzmimg").click(function() {
		var myDate = new Date();
		$("#yzmimg").attr("src", "/getVImageCode?" + myDate.toLocaleString());
	});
	// 图像验证码失去焦点
	$("#yzm").bind("blur", function() {
		var phone = $("#phoneTxt").val();
		if (phone == null || phone == "") {
			ShowErrorTip($("#phoneTxt"), "请输入手机号");
			return false;
		}
		if (!phone.match(/^1[3|4|5|7|8][0-9]\d{4,8}$/)) {
			ShowErrorTip($("#phoneTxt"), "输入手机格式不正确");
			return false;
		}
		var value = $(this).val();
		if (value == "") {
			$(this).val("");
			ShowErrorTip(this, "请输入验证码");
		}
		else {
			var url = "/getImgVerCode";
			var data = {
				code : value,
				phone : phone
			};
			$.post(url, data, function(result) {
				if (result.status == 1) {
					$(".yzm").hide();
					$("#txtCheckCode").val("");
					$(".sjyzm").fadeIn(400);
					emailSend();
				}
				else if (result.status == -4) {
					ShowErrorTip($(this), "手机号已被注册,若号码是您的请<a href='/' style='color:#1570a5;'>登录</a>");
				}
				else {
					ShowErrorTip(this, result.message);
				}
			});
		}
	});
	// 点击注册
	$("#regist").click(function() {
		regist.Submit();
	});
});

var regist = {
	CheckParam : function() {
		regist.SJweiNo = $("#tjrTxt").val();
		regist.Mobile = $("#phoneTxt").val();
		regist.Pwd = $("#passwordTxt1").val();
		regist.Pwd2 = $("#passwordTxt2").val();
		regist.CheckCode = $("#txtCheckCode").val();
		// 上级
		if (regist.SJweiNo == null || regist.SJweiNo == "") {
			regist.SJweiNo = 1;
		}
		else {
			ShowRightTip($("#tjrTxt"));
		}
		if (regist.Pwd == null || regist.Pwd == "") {
			ShowErrorTip($("#passwordTxt1"), "请输入密码");
			return false;
		}
		else if (regist.Pwd.length < 6) {
			ShowErrorTip($("#passwordTxt1"), "密码长度不小于6");
			return false;
		}
		else {
			ShowRightTip($("#passwordTxt1"));
		}

		if (regist.Pwd2 == "重复密码" || regist.Pwd2 == "") {
			ShowErrorTip($("#passwordTxt2"), "请再次输入密码");
			return false;
		}
		else if (regist.Pwd2 != regist.Pwd) {
			ShowErrorTip($("#passwordTxt2"), "两次密码不一致");
			return false;
		}
		else {
			ShowRightTip($("#passwordTxt2"));
		}

		if (regist.Mobile == "手机号码" || regist.Mobile == "") {
			ShowErrorTip($("#phoneTxt"), "请输入手机号码");
			return false;
			;
		}
		else {
			if (!regist.Mobile.match(/^1[3|4|5|7|8][0-9]\d{4,8}$/)) {
				ShowErrorTip($("#phoneTxt"), "输入手机格式不正确");
				return false;
				;
			}
			else {
				if (isCheckPhone) {
					ShowRightTip($("#phoneTxt"));
				}
				else {
					$("#phoneTxt").blur();
				}
			}
		}

		if (regist.CheckCode == "" || regist.CheckCode == null || regist.CheckCode == "验证码") {
			ShowErrorTip($("#txtCheckCode"), "请输入手机验证码");
			return false;
		}
		return true;

	},
	Submit : function() {
		if (regist.CheckParam()) {
			$.ajax({
				type : 'POST',
				url : "/other/otherRegist",
				data : {
					sjWeiNo : regist.SJweiNo,
					mobile : regist.Mobile,
					code : regist.CheckCode,
					pwd : regist.Pwd
				},
				success : function(result) {
					if (result != null) {
						if (result.Statu == 1) {
							var tiket = result.BaseModle;
							setCookie("tiket", tiket, 30);
							var url = getCookie("backUrl");
							if (url == null || url == "") {
								url = "http://www.okwei.com";
							}
							else {
								url = unescape(url);
							}
							if (url.indexOf("?") > 0) {
								location.href = url + "&tiket=" + tiket;
							}
							else {
								location.href = url + "?tiket=" + tiket;
							}
						}
						else {
							alert(result.StatusReson);
						}
					}
					else {
						alert("系统异常！请联系微店网客服！");
					}
				},
				error : function() {
					alert("系统异常！请联系微店网客服！");
				}
			});
		}
	}
};