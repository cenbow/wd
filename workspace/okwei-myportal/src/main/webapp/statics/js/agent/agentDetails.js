/**
 * 详情页
 */
$(function() {
	$(".showimgs").click(function() {
		var src = $(this).attr("src");
		$("#tong>img").attr("src", src);
		winimg();
		var $_height = $(".xubox_main").height() - 3;
		$(".xubox_main").height($_height);
	});
});
function pass() {
	var applyID = $("#applyID").val();
	if (applyID == null || applyID == "") {
		alert("获取申请ID失败");
		return false;
	}
	$.ajax({
		url : "auditAgent",
		type : "post",
		dataType : "json",
		data : {
			applyId : applyID,
			status : 1
		},
		success : function(data) {
			if (data.Statu == 1 && data.BaseModle != null) {
				if (data.BaseModle != null && data.BaseModle.result == 11) {
					getReward(applyID, data.BaseModle.agentId);// 审核通过，去支付
				}
				else if (data.BaseModle != null && data.BaseModle.result == 12) {
					wins('提示', 'win_div_3', '520px', '330px');// 已经有代理商了
				}
				else if (data.BaseModle != null && data.BaseModle.result == 13) {
					win2('提示', 'win_div_4', '520px', '330px');// 3笔悬赏未支付
				}
				else {
					alert("审核成功");
					setTimeout(function() {
						window.location.href = "agentIndex";
					}, 1000);
				}
			}
			else {
				$("#errorinfo").text(data.StatusReson);
				error('提示', 'win_div_5', '520px', '330px');// 审核通过，去支付
				// alert(data.StatusReson);
				// setTimeout(function() {
				// window.location.href = window.location.href;
				// }, 1000);
			}
			$("#pass").attr("onclick", "pass()");
		}
	})
}
function getReward(applyID, agentId) {
	$.ajax({
		url : "agentPayVO",
		type : "post",
		dataType : "json",
		data : {
			applyID : applyID
		},
		success : function(data) {
			if (data.Statu == 1 && data.BaseModle != null) {
				// var baseModel = eval("("+data.BaseModel+")")
				$("#vname").text(data.BaseModle.verName);
				$("#payReward").text(data.BaseModle.agentReward);
				win1(agentId);// 审核通过，去支付
			}
			else {
				$("#vname").text("获取认证员信息失败");
				$("#payReward").text("获取悬赏金额失败");
				win1(agentId);// 审核通过，去支付
			}
		}
	});
}
function winimg() { // title 标题 win_id 弹窗ID width 弹窗宽度 height 弹窗高度
	var i = $.layer({
		type : 1,
		title : false,
		fix : false,
		offset : [
				'50px', ''
		],
		area : [
				'auto', 'auto'
		],
		page : {
			dom : '#tong'
		}
	});
}
function nopass(title, win_id, width, height) { // title 标题 win_id 弹窗ID width 弹窗宽度 height 弹窗高度
	var pagei = $.layer({
		type : 1, // 0-4的选择,0：信息框（默认），1：页面层，2：iframe层，3：加载层，4：tips层。
		btns : 2,
		btn : [
				'确定', '取消'
		],
		title : title,
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
				width, height
		],
		page : {
			dom : '#' + win_id
		},
		yes : function(index) {
			// 不同意审核
			var text = $("#nopass").val();
			var applyID = $("#applyID").val();
			if (text == null || text == "") {
				alert("不通过的原因不能为空");
				return false;
			}
			if (applyID == null || applyID == "") {
				alert("获取申请ID失败");
				return false;
			}
			$.ajax({
				url : "auditAgent",
				type : "post",
				dataType : "json",
				data : {
					applyId : applyID,
					status : 2,
					reson : text
				},
				success : function(data) {
					if (data.Statu == 1) {
						alert("审核成功");
						setTimeout(function() {
							window.location.href = "agentIndex";
						}, 1000);
					}
					else {
						alert(data.StatusReson);
						setTimeout(function() {
							window.location.href = window.location.href;
						}, 1000);
					}
				}
			})
		}
	});
}
function wins(title, win_id, width, height) { // title 标题 win_id 弹窗ID width 弹窗宽度 height 弹窗高度
	var pagei = $.layer({
		type : 1, // 0-4的选择,0：信息框（默认），1：页面层，2：iframe层，3：加载层，4：tips层。
		btns : 2,
		btn : [
				'确定', '取消'
		],
		title : title,
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
				width, height
		],
		page : {
			dom : '#' + win_id
		},
		end : function() {
			$("#AddCount").hide()
		}
	});
}
function win1(agentId) { // title 标题 win_id 弹窗ID width 弹窗宽度 height 弹窗高度
	var pagei = $.layer({
		type : 1, // 0-4的选择,0：信息框（默认），1：页面层，2：iframe层，3：加载层，4：tips层。
		btns : 1,
		btn : [
			'马上去支付'
		],
		title : "提示",
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
				"520px", "330px"
		],
		page : {
			dom : '#win_div_2'
		},
		end : function() {
			$("#AddCount").hide()
		},
		yes : function(index) {
			getorderno(agentId);
		}
	});
}
function win2(title, win_id, width, height) { // title 标题 win_id 弹窗ID width 弹窗宽度 height 弹窗高度
	var pagei = $.layer({
		type : 1, // 0-4的选择,0：信息框（默认），1：页面层，2：iframe层，3：加载层，4：tips层。
		btns : 2,
		btn : [
				'马上去支付', '取消'
		],
		title : title,
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
				width, height
		],
		yes : function(index) {
			window.location.href = "agentIndex?status=3";
		},
		page : {
			dom : '#' + win_id
		},
		end : function() {
			$("#AddCount").hide()
		}
	});
}

function error(title, win_id, width, height) { // title 标题 win_id 弹窗ID width 弹窗宽度 height 弹窗高度
	var pagei = $.layer({
		type : 1, // 0-4的选择,0：信息框（默认），1：页面层，2：iframe层，3：加载层，4：tips层。
		btns : 2,
		btn : [
				'确定', '取消'
		],
		title : title,
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
				width, height
		],
		page : {
			dom : '#' + win_id
		},
		end : function() {
			$("#AddCount").hide()
		}
	});
}
function getorderno(id) {
	if (id == null || id == "") {
		alert("参数不能为空");
	}
	else {
		$.ajax({
			url : "getOrederNo",
			type : "post",
			dataType : "json",
			data : {
				agentID : id,
			},
			success : function(data) {
				if (data.state == 1) {
					var href = data.msg;
					window.location.href = $("#paydomain").val() + "/pay/cashier?orderNo=" + href;
				}
				else {
					alert(data.msg);
				}
			}
		});
	}
}