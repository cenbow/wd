var cookieWeiID = "";
var cookieUserPwd = "";
/**
 * 获取返回路径，设置到cookie
 */
function backurlByCookie() {
	var url = window.location.href;
	var index = url.indexOf("back=");
	var back = "";
	if (index >= 0) {
		back = url.substr(index + 5);
	}
	else {
		back = "http://www.okwei.com";
	}
	back = decodeURIComponent(back);
	setCookie("backUrl", back, 2);
	return back;
}
$(function() {
	// 返回路径存入cookie
	backurlByCookie();// 设置返回路径
	// 获取焦点 失去焦点
	$("#txtWeiID").blur(function() {
		IsShowCode();// 判断是否要出现验证码
	});
	/**
	 * 验证密码是否改变
	 */
	$("#txtPwd").focus(function() {
		var value = $(this).val();
		if (value == cookieUserPwd) {
			$(this).val("");
		}
	});

	// 回车
	$("#txtCode,#txtPwd ,#txtTruePwd").live("keydown", function() {
		var event = arguments.callee.caller.arguments[0] || window.event; // 消除浏览器差异
		var value = $(this).val();
		if (event.keyCode == 13) {
			$("#btnLogin").click();
		}
	});

	// 登录
	$("#btnLogin").bind("click", function() {
		login();
	});

	// 注册按钮
	$("btnreg").click(function() {
		location.href = "regist";
	});

	// 验证码 切换
	$("#imgcode").click(function() {
		var ver = parseInt(Math.random() * 100 + 1)
		$(this).attr("src", $(this).attr("src") + "?" + ver);
	});

	IsShowCode();

});
// 获取登录次数判断是否需要出现验证码
function IsShowCode() {
	var value = $("#txtWeiID").val();
	var reg = new RegExp("^[0-9]{4,12}$");
	if (reg.test(value)) {
		$.ajax({
			type : "POST",
			url : "checkThing",
			data : {
				weino : value
			},
			success : function(result) {
				if (result == 1) {
					$("#liCode").show();
				}
				else {
					$("#liCode").hide();
				}
				$("#imgcode").click();
			},
			error : function() {

			}
		});
	}
}

// 提示
function alert(msg) {
	$("#litip span").html(msg);
	$("#litip").show();
}

function login() {
	$("#litip").hide();
	var weiID = $("#txtWeiID").val();
	var pwd = $("#txtPwd").val();
	var code = $("#txtCode").val();
	var loginType = 1;
	if (weiID == "" || weiID == null) {
		alert("请输入微店号/手机");
		return;
	}
	else {
		var reg = new RegExp("^[0-9]{1,10}_{0,1}[0-9]{1,5}$");
		if (!reg.test(weiID)) {
			alert("请输入正确的微店号或手机号");
			return;
		}
	}

	if (pwd == "" || pwd == null) {
		alert("请输入密码");
		return;
	}

	if (!$("#txtCode").is(":hidden")) {
		if (code == "" || code == null || code == "验证码") {
			alert("请输入验证码")
			return;
		}
	}

	// 是否记住密码
	var isRemember = 1;
	// 值是否发生过改变
	loginType = 1;
	$("#btnLogin").html("登录中...");
	$("#btnLogin").unbind();
	var url = window.location.href;
	var index = url.indexOf("back=");
	var back = encodeURIComponent(getCookie("backUrl"));
	$.ajax({
		type : 'POST',
		url : "getlogin",
		data : {
			username : weiID,
			password : pwd,
			vcode : code,
			isRemember : isRemember,
			loginType : loginType,
			back : back
		},
		success : function(result) {
			if (result != null) {
				// result = eval("(" + result + ")");
				if (result.status == 1) {
					var domain = result.okweidomain;
					var href = domain + "?tiket=" + result.message;
					if (domain.indexOf("?") >= 0) {
						href = domain + "&tiket=" + result.message;
					}
					bindlogin(href, result.message);// 登陆绑定
				}
				else {
					if (result.status == 3) {
						$("#liCode").show();
					}
					$("#imgcode").attr("src", "/getVImageCode?id=" + Math.random());
					alert(result.message);
				}
			}

			$("#btnLogin").html("登录");
			$("#txtCode").css({
				color : "#999"
			});
			$("#btnLogin").bind("click", function() {
				login();
			});

		},
		error : function() {
			$("#imgcode").attr("src", "/getVImageCode?id=" + Math.random());
			$("#btnLogin").html("登录");
			$("#txtCode").val("");
			$("#btnLogin").bind("click", function() {
				login();
			});
			alert("系统异常，请重试/联系客服！");
		}
	});
}

function bindlogin(href, tiket) {
	$.ajax({
		type : 'POST',
		url : "/other/LoginBindByTiket",
		data : {
			tiket : tiket,
		},
		success : function(data) {
			if (data.statu == 1) {
				window.location.href = href;
			}
			else {
				alert(data.message);
			}
		}
	});
}