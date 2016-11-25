$(function() {
	/* 一键推广 */
	$('#Yijian').mouseover(function() {
		$('#Fonts').show();
	}).mouseout(function() {
		$('#Fonts').hide();
	});
	/* 二维码 top */
	$('#Ewming').mouseover(function() {
		$('#Tewms').show();
	}).mouseout(function() {
		$('#Tewms').hide();
	});
	/* 网站导航 */

	$('#WzDao').mouseover(function() {
		$('#Dhang').show();
	}).mouseout(function() {
		$('#Dhang').hide();
	});
	$("#searchTxt").bind("keydown", function(e) {
		var key = e.which;
		if (key == 13) {
			var txt = $.trim(this.value);
			if (txt == "")
				return;
			location.href = "http://www." + $("#okweidomain").val() + "/list/list?keyWord=" + txt;
		}
	});
});

// 搜索
function search() {
	var txt = $.trim($("#searchTxt").val());
	if (txt == "")
		return;
	location.href = "http://www." + $("#okweidomain").val() + "/list/list?keyWord=" + txt;
}

// 收藏店铺
function addfavorite() {
	var title = document.title;
	var url = window.location;
	var ua = navigator.userAgent.toLowerCase();
	if (ua.indexOf("msie 8") > -1) {
		external.AddToFavoritesBar(url, title, 'butao'); // IE8
	} else {
		try {
			window.external.addFavorite(url, title);
		} catch (e) {
			try {
				window.sidebar.addPanel(title, url, ""); // firefox
			} catch (e) {
				alert("您可以尝试通过快捷键Ctrl + D 加入到收藏夹");
			}
		}
	}
}

// 跳转登录
function goLogin() {
	var tempurl = window.location;
	location.href = "http://port.okwei.com/login.aspx?back=" + tempurl;
}

// 退出
function SignOut() {
	$.ajax({
		url : "/commons/outLogin",
		type : "get",
		async : false,
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			// alert("服务器出现异常");
			window.location.href = "http://port.okwei.com/exit.aspx";
		},
		success : function(data) {
			clearCookie();
			window.location.href = "http://port.okwei.com/exit.aspx?back=http://www." + data + ".okwei.com";
		}
	});
}
// 清除cookie
function clearCookie() {
	var keys = document.cookie.match(/[^ =;]+(?=\=)/g);
	if (keys) {
		for (var i = keys.length; i--;)
			document.cookie = 'tiket=0; path=/;expires=' + new Date(0).toUTCString();
		document.cookie = 'tiket=0; domain=.okwei.com;path=/;expires=' + new Date(0).toUTCString();
	}
}
