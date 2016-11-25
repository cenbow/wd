/**
 * 退款详情JS
 */
$(function() {

});
/**
 * 申请微店网介入
 */
function meddlewin(refundId) {
	var pagei = $.layer({
		type : 1,
		btns : 2,
		btn : [
				'是', '否'
		],
		title : "申请微店网介入",
		border : [
			0
		],
		closeBtn : [
			0
		],
		closeBtn : [
				0, true
		],
		yes : function(index) {
			meddle(refundId, index)
		},
		shadeClose : true,
		area : [
				'500px', '280px'
		],
		page : {
			dom : "#meddle"
		}
	});
}
function meddle(refundId, index) {
	$.ajax({
		url : "orderajax",
		type : "get",
		dataType : "json",
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		data : {
			"key" : "meddle",
			"refundId" : refundId
		},
		error : function() {
			alert("异常！");
		},
		success : function(data) {
			if (data.msg == "1") {
				window.location.href = "buylist";
			}
			else {
				alert("申请失败");
			}
			location.reload();
		}
	});
	layer.close(index);
}
/**
 * 取消申请退款
 */
function cancelrefundwin(refundId) {
	var pagei = $.layer({
		type : 1,
		btns : 2,
		btn : [
				'是', '否'
		],
		title : "取消申请退款",
		border : [
			0
		],
		closeBtn : [
			0
		],
		closeBtn : [
				0, true
		],
		yes : function(index) {
			cancelrefund(refundId, index)
		},
		shadeClose : true,
		area : [
				'500px', '280px'
		],
		page : {
			dom : "#cancelrefund"
		}
	});
}
function cancelrefund(refundId, index) {
	$.ajax({
		url : "orderajax",
		type : "get",
		dataType : "json",
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		data : {
			"key" : "cancelrefund",
			"refundId" : refundId
		},
		error : function() {
			alert("异常！");
			location.reload();
		},
		success : function(data) {
			if (data.msg == "1") {
				window.location.href = "buylist";
			}
			else {
				alert("取消失败");
			}
			location.reload();
		}
	});
	layer.close(index);
}