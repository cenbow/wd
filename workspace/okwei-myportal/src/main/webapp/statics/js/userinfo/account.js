/**
 * 绑定账户
 */
$(function() {

});
var selectid;
function wins(title, win_id) {
	selectid = win_id;
	var pagei = $.layer({
		type : 1, // 0-4的选择,0：信息框（默认），1：页面层，2：iframe层，3：加载层，4：tips层。
		btns : 2,
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
		yes : function(index) {
			if (selectid == "mzh_weixin") {
				unbindwx()
			}
			else if (selectid == "mzh_qq") {
				unbindqq();
			}
		},
		area : [
				'574px', '300px'
		],
		page : {
			dom : '#' + win_id
		}
	});
}
function unbindqq() {
	unajax("unqq");
}
function unbindwx() {
	unajax("unwx");
}
function unajax(key) {
	$.ajax({
		url : "ajaxuserinfo",
		type : "post",
		dataType : "json",
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		data : {
			"key" : key
		},
		error : function() {
			alert("异常！");
			location.reload();
		},
		success : function(data) {
			if (data.state == 1) {
			}
			else {
				alert(data.msg);
			}
			location.reload();
		},
	});
}
