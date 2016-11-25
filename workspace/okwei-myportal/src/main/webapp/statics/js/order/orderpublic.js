$(function() {
	$(".mzh_xiayibu").click(function() {
		var orderNo = $("#payOrderID").val();
		// 重新支付
		$.ajax({
			url : "Repayment",
			type : "post",
			async : false,
			data : {
				orderNo : orderNo
			},
			dataType : "json",
			success : function(data) {
				if (data.status == 1) {
					location.href = $("#paydomain").val() + "/pay/cashier?orderNo=" + data.msg;
				} else {
					alert(data.msg);
				}
			}
		});
	});
	$(".btn_hui28_pre").click(function() {
		var layerObj = $('.xubox_layer');
		$.each(layerObj, function() {
			var i = $(this).attr('times');
			layer.close(i);
		});
	});
});

// 去支付
function gotoPay(orderNo) {
	$.ajax({
		url : "orderpayjump",
		type : "post",
		dataType : "json",
		data : {
			"orderNo" : orderNo
		},
		error : function() {
			alert("异常！");
		},
		success : function(data) {
			if (data.status == 0) {
				$("#payOrderID").val(data.msg);
				win4('支付', 'win_div_4', '514px', '260px');
			} else {
				location.href = $("#paydomain").val() + "/pay/cashier?orderNo=" + data.msg;
			}
		}
	});
}

function win4(title, win_id, width, height) { // title 标题 win_id 弹窗ID width
	// 弹窗宽度 height 弹窗高度
	var pagei = $.layer({
		type : 1, // 0-4的选择,0：信息框（默认），1：页面层，2：iframe层，3：加载层，4：tips层。
		btns : 0,
		btn : [ '确定', '取消' ],
		title : title,
		border : [ 0 ],
		closeBtn : [ 0 ],
		closeBtn : [ 0, true ],
		shadeClose : true,
		area : [ width, height ],
		page : {
			dom : '#' + win_id
		},
		end : function() {
			$("#AddCount").hide()
		}
	});
}

/**
 * 订单公共可用的
 */
/**
 * 跳转支付
 */
function orderpay(orderNo) {
	window.location.href = $("#paydomain").val() + "/pay/cashier?orderNo=" + orderNo;

}
// 申请退款弹窗
function refundwin(orderNo) {
	var pagei = $.layer({
		type : 1,
		btns : 2,
		btn : [ '申请退款', '暂不退款' ],
		title : "退款提示",
		border : [ 0 ],
		closeBtn : [ 0 ],
		closeBtn : [ 0, true ],
		yes : function(index) {
			refundByOrderNo(orderNo, index)
		},
		shadeClose : true,
		area : [ '500px', '280px' ],
		page : {
			dom : "#refund_ts"
		}
	});
}
// 取消退款弹窗
function cancelrefundwin(refundId) {
	var pagei = $.layer({
		type : 1,
		btns : 2,
		btn : [ '是', '否' ],
		title : "取消退款",
		border : [ 0 ],
		closeBtn : [ 0 ],
		closeBtn : [ 0, true ],
		yes : function(index) {
			cancelrefundByOrderNo(refundId, index)
		},
		shadeClose : true,
		area : [ '500px', '280px' ],
		page : {
			dom : "#cancelrefund_ts"
		}
	});
}
// 确认收货弹窗
function confirmcargowin(orderNo) {
	var pagei = $.layer({
		type : 1,
		btns : 2,
		btn : [ '我已确认收货', '点错了，取消' ],
		title : "确认收货",
		border : [ 0 ],
		closeBtn : [ 0 ],
		closeBtn : [ 0, true ],
		yes : function(index) {
			confirmByOrder(orderNo, index)
		},
		shadeClose : true,
		area : [ '500px', '280px' ],
		page : {
			dom : "#quren_ts"
		}
	});
}
// 取消的弹窗
function cancelwin(orderNo) {
	var pagei = $.layer({
		type : 1,
		btns : 2,
		btn : [ '是', '否' ],
		title : "取消订单",
		border : [ 0 ],
		closeBtn : [ 0 ],
		closeBtn : [ 0, true ],
		yes : function(index) {
			cancelByOrderNo(orderNo, index)
		},
		shadeClose : true,
		area : [ '500px', '280px' ],
		page : {
			dom : "#cancel_ts"
		}
	});
}
// 删除订单的弹窗
function deletewin(orderNo) {
	var pagei = $.layer({
		type : 1,
		btns : 2,
		btn : [ '是', '否' ],
		title : "删除订单",
		border : [ 0 ],
		closeBtn : [ 0 ],
		closeBtn : [ 0, true ],
		yes : function(index) {
			deleteByOrderNo(orderNo, index)
		},
		shadeClose : true,
		area : [ '500px', '280px' ],
		page : {
			dom : "#delete_ts"
		}
	});
}
function refundByOrderNo(orderNo, index) {
	window.location.href = "applyrefund?orderNo=" + orderNo;
	// $.ajax({
	// url : "orderajax",
	// type : "post",
	// dataType : "json",
	// data : {
	// "key" : "refund",
	// "orderNo" : orderNo
	// },
	// error : function() {
	// alert("异常！");
	// },
	// success : function(data) {
	// if (data.msg == "1") {
	// alert("申请成功");
	// }
	// else {
	// alert("申请失败");
	// }
	// location.reload();
	// }
	// });
	// layer.close(index);
}
function cancelrefundByOrderNo(refundId, index) {
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
		},
		success : function(data) {
			if (data.msg == "1") {
				alert("取消成功", true);
				location.reload();
			} else {
				alert(data.msg);
			}
		}
	});
	layer.close(index);
}
function confirmByOrder(orderNo, index) {
	$.ajax({
		url : "orderajax",
		type : "post",
		dataType : "json",
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		data : {
			"key" : "confirmcargo",
			"orderNo" : orderNo
		},
		error : function() {
			alert("异常！");
		},
		success : function(data) {
			if (data.msg == "1") {
				alert("确认成功", true);
				location.reload();
			} else {
				alert(data.msg);
			}
		}
	});
	layer.close(index);
}
function cancelByOrderNo(orderNo, index) {
	$.ajax({
		url : "orderajax",
		type : "post",
		dataType : "json",
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		data : {
			"key" : "cancel",
			"orderNo" : orderNo
		},
		error : function() {
			alert("异常！");
		},
		success : function(data) {
			if (data.msg == "1") {
				alert("取消成功", true);
				location.reload();
			} else {
				alert(data.msg);
			}
		}
	});
	layer.close(index);
}
function deleteByOrderNo(orderNo, index) {
	$.ajax({
		url : "orderajax",
		type : "post",
		dataType : "json",
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		data : {
			"key" : "delete",
			"orderNo" : orderNo
		},
		error : function() {
			alert("异常！");
			location.reload();
		},
		success : function(data) {
			if (data.msg == "1") {
				alert("删除成功", true);
				location.reload();
			} else {
				alert(data.msg);
			}
		}
	});
	layer.close(index);
}