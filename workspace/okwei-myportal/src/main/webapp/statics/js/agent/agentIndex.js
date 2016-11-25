/**
 * 代理商管理
 */
// 获取URL参数
function GetQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if (r != null)
		return unescape(r[2]);
	return null;
}
$(function() {
	parameter();// 参数处理
	getdemandList();// 获取招商需求
	$("li[name=mzh_4_7_yes]").click(function() {
		$(this).addClass("yes_bgs").siblings().removeClass("yes_bgs");
		window.location.href = "agentIndex?status=" + $(this).attr("data-id");
	});
	$("#selectAll").click(function() {
		if ($(this).is(":checked")) {
			$(".checkboxs").prop("checked", true);
		}
		else {
			$(".checkboxs").prop("checked", false);
		}
	});
	$(".checkboxs").click(function() {
		if ($(".checkboxs").length == $(".checkboxs:checked").length) {
			$("#selectAll").prop("checked", true);
		}
		else {
			$("#selectAll").prop("checked", false);
		}
	});
	var page = new Pagination({
		formId : "classForm",
		isAjax : false,
		targetId : "navTab",
		submitId : "searchBtn",
		validateFn : false
	});
	page.init();
	$("#addWeiid").blur(function() {
		getweiName($(this).val());
	}).keyup(function() {
		var tmptxt = $(this).val();
		$(this).val(tmptxt.replace(/\D/g, ''));
	});
	$("#addPhone").keyup(function() {
		var tmptxt = $(this).val();
		$(this).val(tmptxt.replace(/\D/g, ''));
	});
	$("#demands").on("click", "input", function() {
		$("#cityName").parent().show();
		getcityname($(this).val());
	});
	$("#delall").on("click", function() {
		var count = $(".checkboxs:checked").length;
		if (count < 1) {
			alert("请选择要删除的代理商");
		}
		else {
			var id = "";
			$(".checkboxs:checked").each(function() {
				id += $(this).val() + ",";
			});
			id = id.substring(0, id.length - 1);
			deletes(id);
		}
	});
});
// 删除
function deletes(id) {
	if (id == null || id == "" || id == ",") {
		alert("删除的对象不能为空");
		return false;
	}
	$.ajax({
		url : "deleteAgent",
		data : {
			ids : id,
			status : $("#statusVal").val()
		},
		type : "post",
		success : function(data) {
			if (data >= 1) {
				alert("删除成功");
				window.location.href = window.location.href;
			}
			else {
				alert("删除失败");
				window.location.href = window.location.href;
			}
		}
	});
}
// 支付
function zhifu(index) {
	getorderno(index);
}
// 提交
function submit(index) {
	var weiid = $("#addWeiid").val();
	var name = $("#addName").val();
	var phone = $("#addPhone").val();
	var address = $("#addAddress").val();
	var demandID = $("#demands input:checked").val();
	var code = $("#cityName input:checked").val();
	if (weiid == null || weiid == "") {
		alert("微店号不能为空");
		return false;
	}
	if (name == null || name == "") {
		alert("姓名不能为空")
		return false;
	}
	if (phone == null || phone == "") {
		alert("手机号码不能为空")
		return false;
	}
	if (!phone.match(/^1[3|4|5|7|8][0-9]\d{4,8}$/)) {
		alert("手机号码格式不正确")
		return false;
	}
	if (demandID == null || demandID == "") {
		alert("请选择招商需求")
		return false;
	}
	if (code == null || code == "") {
		alert("请选择代理区域")
		return false;
	}
	$.ajax({
		url : "addAgent",
		dataType : "json",
		data : {
			weiId : weiid,
			linkname : name,
			phone : phone,
			demandIds : demandID,
			address : address,
			agentAreas : code
		},
		type : "post",
		success : function(data) {
			if (data.Statu == 1) {
				if (data.BaseModle == null || data.BaseModle == "") {
					alert("添加成功");
					setTimeout(function() {
						layer.close(index);
					}, 2000);
				}
				else {
					if (data.BaseModle.result == 13) {
						layer.close(index);
						win2("提示", "win_div_4", '520px', '250px');
					}
				}
			}
			else {
				alert(data.StatusReson);
			}
		}
	});
}
// 根据招商需求获取代理的城市
function getcityname(demandId) {
	$.ajax({
		url : "getCityName",
		dataType : "json",
		data : {
			demandID : demandId
		},
		success : function(data) {
			var html = "";
			if (data.Statu == 1) {
				if (data.BaseModle == null || data.BaseModle == "") {
					html = '<div class="fl ml_20 mt_5">此招商需求没有设置区域</div>';
				}
				else {
					var count = data.BaseModle.length;
					var list = data.BaseModle;
					$.each(list, function(index, city) {
						html += '<div class="fl ml_20 mt_5 city">';
						html += '<input id="city' + city.code + '" type="radio"';
						if (city.isEnable == 0) {
							html += ' disabled="disabled" ';
						}
						html += 'name="city" value="' + city.code + '" /> <label for="city' + city.code + '"';
						if (city.isEnable == 0) {
							html += ' style="color:#dedede" ';
						}
						html += '>' + city.areaName + '</label>';
						html += '</div>';
					});
				}
			}
			else {
				html = '<div class="fl ml_20 mt_5">' + data.StatusReson + '</div>';
			}
			$("#cityName").html(html);
		}
	})
}
// 获取微店名
function getweiName(weiid) {
	if (weiid == null || weiid == "") {
		$("#weiName").html('<span style=" padding-left: 20px; color: red;">微店号不能为空</span>');
		return false;
	}
	$.ajax({
		url : "getWeiName",
		dataType : "json",
		data : {
			weiID : weiid
		},
		success : function(data) {
			var html = "";
			if (data.Statu == 1) {
				html = '<span class="fl ml_10">店铺名称：</span> <span class="fl">' + data.BaseModle + '</span>';
			}
			else {
				html = '<span style=" padding-left: 20px; color: red;">' + data.StatusReson + '</span>';
			}
			$("#weiName").html(html);
		}
	});
}
// 获取招商需求
function getdemandList() {
	$.ajax({
		url : "getDemandList",
		dataType : "json",
		success : function(data) {
			var html = "";
			if (data.Statu == 1) {
				var count = data.BaseModle.length;
				if (count <= 0) {
					html = '<div class="fl ml_20 mt_5">您还没有添加过招商需求</div>';
				}
				else {
					var list = data.BaseModle;
					$.each(list, function(index, dem) {
						html += '<div class="fl ml_20 mt_5 dem">';
						html += '<input id="dem' + dem.demandId + '" type="radio" name="dem" value="' + dem.demandId + '" /> <label for="dem' + dem.demandId + '">' + dem.title + '</label>';
						html += '</div>';
					});
				}
			}
			else {
				html = '<div class="fl ml_20 mt_5">' + data.StatusReson + '</div>';
			}
			$("#demands").append(html);
		}
	})
}
// 参数设置
function parameter() {
	var status = $("#statusVal").val();
	if (status == 2) {
		$("#shbtg").addClass("yes_bgs").siblings().removeClass("yes_bgs");
	}
	else if (status == 3) {
		$("#shtg").addClass("yes_bgs").siblings().removeClass("yes_bgs");
	}
	else if (status == 4) {
		$("#yqx").addClass("yes_bgs").siblings().removeClass("yes_bgs");
	}
	else {
		status = 1;
		$("#dsh").addClass("yes_bgs").siblings().removeClass("yes_bgs");
	}
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
		},
		yes : function(index) {
			submit(index);
		}
	});
}
function cancelwin(agentID) {
	var pagei = $.layer({
		type : 1, // 0-4的选择,0：信息框（默认），1：页面层，2：iframe层，3：加载层，4：tips层。
		btns : 2,
		btn : [
				'确定', '取消'
		],
		title : "取消代理原因",
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
				"520px", "350px"
		],
		page : {
			dom : '#win_div_1'
		},
		yes : function(index) {
			var text = $("#canceltext").val();
			if (text == null || text == "") {
				alert("取消的原因不能为空");
				return false;
			}
			if (agentID == null || agentID == "") {
				alert("获取代理商ID失败");
				return false;
			}
			$.ajax({
				url : "agentManage",
				type : "post",
				dataType : "json",
				data : {
					reson : text,
					agentId : agentID,
					status : 2
				},
				success : function(data) {
					if (data.Statu == 1) {
						alert("操作成功");
						setTimeout(function() {
							window.location.href = window.location.href;
						}, 1000)
					}
					else {
						alert(data.StatusReson);
					}
				}
			});
		}
	});
}
/**
 * 恢复
 */
function recovery(agentID) {
	var pagei = $.layer({
		type : 1, // 0-4的选择,0：信息框（默认），1：页面层，2：iframe层，3：加载层，4：tips层。
		btns : 2,
		btn : [
				'确定', '取消'
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
				"520px", "250px"
		],
		page : {
			dom : '#win_div_2'
		},
		yes : function(index) {
			if (agentID == null || agentID == "") {
				alert("获取代理商ID失败");
				return false;
			}
			$.ajax({
				url : "agentManage",
				type : "post",
				dataType : "json",
				data : {
					agentId : agentID,
					status : 1
				},
				success : function(data) {
					if (data.Statu == 1 && data.BaseModle != null && data.BaseModle.result == 13) {
						layer.close(index);
						win1('提示', 'win_div_11', '520px', '250px');
					}
					else if (data.Statu == 1 && data.BaseModle != null && data.BaseModle.result == 12) {
						layer.close(index);
						win1('提示', 'win_div_3', '520px', '250px');
					}
					else if (data.Statu == 1) {
						alert("操作成功");
						setTimeout(function() {
							window.location.href = window.location.href;
						}, 1000)
					}
					else {
						alert(data.StatusReson);
					}
				}
			});
		}
	});

}

function win1(title, win_id, width, height) { // title 标题 win_id 弹窗ID width 弹窗宽度 height 弹窗高度
	var pagei = $.layer({
		type : 1, // 0-4的选择,0：信息框（默认），1：页面层，2：iframe层，3：加载层，4：tips层。
		btns : 1,
		btn : [
			'确定'
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
function win2(title, win_id, width, height) { // title 标题 win_id 弹窗ID width 弹窗宽度 height 弹窗高度
	var pagei = $.layer({
		type : 1, // 0-4的选择,0：信息框（默认），1：页面层，2：iframe层，3：加载层，4：tips层。
		btns : 1,
		btn : [
			'去支付'
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
		},
		yes : function(index) {
			window.location.href = "agentIndex?status=3";
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