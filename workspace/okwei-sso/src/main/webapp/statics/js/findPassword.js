/**
 * 找回密碼
 */
$(function() {
	$("#phone").keyup(function() {// 手机格式限制
		var tmptxt = $(this).val();
		$(this).val(tmptxt.replace(/[^\d]/g, ''));
	}).bind("keydown", function(e) {// 手机输入绑定回车
		var key = e.which;
		if (key == 13) {
			step_one();// 第一步提交
		}
	}).blur(function() {// 手机输入框，失去焦点
		step_one();
	});
	$("#imgcodetext").bind("keydown", function(e) {
		var key = e.which;
		if (key == 13) {
			step_two2();// 第二步提交
		}
	});
	// 验证码 切换
	$("#imgcode").click(function() {
		var ver = parseInt(Math.random() * 100 + 1)
		$(this).attr("src", $(this).attr("src") + "?" + ver);
	});
	$("#phoneyzm").bind("click", function() {
		getPhoneCode();
	});
	// 密码输入校验
	$("#newpass1").blur(function() {
		var text = $(this).val();
		if (text == null || text == "") {
			$("#step3tip1img").attr("src", "/statics/images/c-ico1.png").show();
			$("#step3tip1").text("密码不能为空").show();
			return false;
		}
		if (text.length < 6) {
			$("#step3tip1img").attr("src", "/statics/images/c-ico1.png").show();
			$("#step3tip1").text("密码不得小于6位").show();
		}
		else {
			$("#step3tip1img").attr("src", "/statics/images/d-ico1.png").show();
			$("#step3tip1").hide();
		}
	});
	$("#newpass2").blur(function() {
		var pass1 = $("#newpass1").val();
		var pass2 = $(this).val();
		if (pass2 == null || pass2 == "") {
			$("#step3tip2img").attr("src", "/statics/images/c-ico1.png").show();
			$("#step3tip2").text("密码不能为空").show();
			return false;
		}
		$("#newpass1").blur();
		if (pass1 != pass2) {
			$("#step3tip2img").attr("src", "/statics/images/c-ico1.png").show();
			$("#step3tip2").text("两次密码输入不一致").show();
			return false;
		}
		else {
			$("#step3tip2img").attr("src", "/statics/images/d-ico1.png").show();
			$("#step3tip2").hide();
		}
	})
});
// 获取手机验证码
function getPhoneCode() {
	var guid = $("#guid").val();
	$.ajax({
		type : 'POST',
		url : "/getCode",
		data : {
			guid : guid
		},
		success : function(datas) {
			if (datas.status == 1) {
				bddjs($("#phoneyzm"), 60);
				$("#phone2tip").text("").hide();
			}
			else {
				$("#phone2tip").text(datas.message).show();
			}
		}
	});
}
// 绑定倒计时
function bddjs(obj, num) {
	var tim = num;
	$(obj).unbind("click");
	var sil = setInterval(function() {
		tim--;
		if (tim < 1) {
			clearInterval(sil);
			$(obj).bind("click", function() {
				getPhoneCode();
			});
			$(obj).html("获取手机验证码").css("background", "#4ec526");
		}
		else {
			$(obj).html(tim + "秒可以重新获取").css("background", "#ddd");
		}
	}, 1000);
}
function two() {
	var code = $("#imgcodehide").val();
	if (code == "1") {
		step_two2();
	}
	else {
		step_two();
	}
}
/**
 * 第一步提交
 */
function step_one() {
	var phone = $.trim($("#phone").val());
	if (phone == null || phone == "") {
		$("#phoneico").attr("src", "/statics/images/c-ico1.png").show();
		$("#phoneTxt").text("请输入手机号").show();
		$("#mszc").hide();
	}
	else {
		// 验证手机格式
		var r = /^1[345678]\d{9}$/;
		if (!r.test(phone)) {
			$("#phoneico").attr("src", "/statics/images/c-ico1.png").show();
			$("#phoneTxt").text("手机格式错误").show();
			$("#mszc").hide();
			return false;
		}
		// 验证手机是否存在
		var flag = false;// 默认不存在
		$.ajax({
			type : 'POST',
			url : "/findPhone",
			data : {
				phone : phone
			},
			success : function(datas) {
				if (datas != 0) {
					$("#guid").val(datas.guid);
					$("#phoneico").attr("src", "/statics/images/d-ico1.png").show();
					$("#phoneTxt").hide().text("该手机没有绑定微店号，");
					$("#mszc").hide();
					// 显示第二步
					$("#weiid").text(datas.weiid);
					$("#bindPhone").text(phone);
					$("#step1").hide();
					$("#step2").show();
					$(".ddxq_gg_flow_red_4_1_yes").attr("class", "ddxq_gg_flow_red_4_2_yes")
				}
				else {
					$("#phoneico").attr("src", "/statics/images/c-ico1.png").show();
					$("#phoneTxt").text("该手机没有绑定微店号，").show();
					$("#mszc").show();
				}
				$("#imgcode").click();
			}
		});
	}
}

/**
 * 第二步提交
 */
function step_two() {
	var imgcode = $("#imgcodetext").val();// 获取图片验证码
	var guid = $("#guid").val();// 获取guid;
	$.ajax({
		type : 'POST',
		url : "/getImgCode",
		data : {
			imgcode : imgcode,
			guid : guid
		},
		success : function(datas) {
			if (datas.status == 1) {
				$("#imgcodehide").val("1");
				$("#phoneli").show();
				$("#imgcodeli").hide();
				$("#step2ico").hide();
				$("#step2tip").hide();
				getPhoneCode();
			}
			else {
				$("#step2ico").show();
				$("#step2tip").text(datas.message).show();
				var ver = parseInt(Math.random() * 100 + 1)
				$(this).attr("src", $(this).attr("src") + "?" + ver);
				$("#imgcodetext").val("");// 获取图片验证码置空
			}
			$("#imgcode").click();
		}
	});
}
function step_two2() {
	var code = $("#phonecode").val();
	var guid = $("#guid").val();
	$.ajax({
		type : 'POST',
		url : "/getPhoneCode",
		data : {
			guid : guid,
			code : code
		},
		success : function(datas) {
			if (datas.status == 1) {
				$("#phone2tip").hide();
				$(".ddxq_gg_flow_red_4_2_yes").attr("class", "ddxq_gg_flow_red_4_3_yes");
				$("#step3").show();
				$("#step2").hide();
			}
			else {
				$("#phone2tip").text(datas.message).show();
			}
			$("#imgcode").click();
		}
	});
}
/**
 * 第三部提交
 */
function step_three() {
	$("#newpass1").blur();
	$("#newpass2").blur();
	var pass1 = $("#newpass1").val();
	var pass2 = $("#newpass2").val();
	if (pass1.length < 6 || pass1 != pass2) {
		$("#step3tip2img").attr("src", "/statics/images/c-ico1.png").show();
		return false;
	}
	var guid = $("#guid").val();
	$.ajax({
		type : 'POST',
		url : "/updatePassWord",
		data : {
			guid : guid,
			pass : pass2
		},
		success : function(datas) {
			if (datas.status == 1) {
				$("#phone2tip").hide();
				$(".ddxq_gg_flow_red_4_3_yes").attr("class", "ddxq_gg_flow_red_4_4_yes");
				$("#step4").show();
				$("#step3").hide();
			}
			else {
				$("#step3tip2img").attr("src", "/statics/images/c-ico1.png").show();
				$("#step3tip2").text(datas.message).show();
			}
		}
	});
}
