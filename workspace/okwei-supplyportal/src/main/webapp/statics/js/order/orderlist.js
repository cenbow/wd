/**
 * 订单列表
 */
$(function() {
	// 条件选择
	$(".left_xuanzs>ul>li").on("click", function() {
		// 如果点击的条件没有是没有选中，并且不为0
		if (!$(this).hasClass("yes_bgs") && !$(this).hasClass("zero")) {
			$(this).addClass("yes_bgs").siblings().removeClass("yes_bgs");
			var datatype = $("#ordertype>li.yes_bgs").attr("data-type");
			var datastate = $("#orderstate>li.yes_bgs").attr("data-state");
			if (datatype == "1" || datatype == "2") {
				if ($(this).parent().attr("id") == "ordertype") {
					window.location.href = "buylist?dt=" + datatype + "&ds=-1";
				}
				else {
					window.location.href = "buylist?dt=" + datatype + "&ds=" + datastate;
				}
			}
			else {
				if ($(this).parent().attr("id") == "ordertype") {
					window.location.href = "reservelist?dt=" + datatype + "&ds=-1";
				}
				else {
					window.location.href = "reservelist?dt=" + datatype + "&ds=" + datastate;
				}
			}
		}
	});
	/* 下面是验证输入 */
	$("#orderNo").keyup(function() {
		var tmptxt = $(this).val();
		$(this).val(tmptxt.replace(/[^\d|-]/g, ''));
	})
	$("#buyerid").keyup(function() {
		var tmptxt = $(this).val();
		$(this).val(tmptxt.replace(/\D/g, ''));
	})
	$("#minPrice").keyup(function() {
		var tmptxt = $(this).val();
		tmptxt = tmptxt.replace(/[^\d.]/g, ""); // 清除“数字”和“.”以外的字符
		tmptxt = tmptxt.replace(/^\./g, ""); // 验证第一个字符是数字而不是.
		tmptxt = tmptxt.replace(/\.{2,}/g, "."); // 只保留第一个. 清除多余的.
		if (tmptxt.indexOf(".") > 0 && tmptxt.length - tmptxt.indexOf(".") > 3) {
			tmptxt = tmptxt.substr(0, tmptxt.indexOf(".") + 3);
		}
		tmptxt = tmptxt.replace(".", "$#$").replace(/\./g, "").replace("$#$", ".");
		$(this).val(tmptxt);
	})
	$("#maxPrice").keyup(function() {
		var tmptxt = $(this).val();
		tmptxt = tmptxt.replace(/[^\d.]/g, ""); // 清除“数字”和“.”以外的字符
		tmptxt = tmptxt.replace(/^\./g, ""); // 验证第一个字符是数字而不是.
		tmptxt = tmptxt.replace(/\.{2,}/g, "."); // 只保留第一个. 清除多余的.
		if (tmptxt.indexOf(".") > 0 && tmptxt.length - tmptxt.indexOf(".") > 3) {
			tmptxt = tmptxt.substr(0, tmptxt.indexOf(".") + 3);
		}
		tmptxt = tmptxt.replace(".", "$#$").replace(/\./g, "").replace("$#$", ".");
		$(this).val(tmptxt);
	});
	/* 输入离开 */
	$("#maxPrice").blur(function() {
		if ($("#minPrice").val() == "" || $("#maxPrice").val() == "") {
			return false;
		}
		if (parseFloat($("#maxPrice").val()) < parseFloat($("#minPrice").val())) {
			$(this).val("");
		}
	})
	$("#minPrice").blur(function() {
		if ($("#minPrice").val() == "" || $("#maxPrice").val() == "") {
			return false;
		}
		if (parseFloat($("#maxPrice").val()) < parseFloat($("#minPrice").val())) {
			$(this).val("");
		}
	})
	$("#endTimeStr").blur(function() {
		if ($("#beginTimeStr").val() == "" || $("#endTimeStr").val() == "") {
			return false;
		}
		var begin = new Date($("#beginTimeStr").val());
		var end = new Date($("#endTimeStr").val());
		if (begin > end) {
			$("#endTimeStr").val("");
		}
	})
	$("#beginTimeStr").blur(function() {
		if ($("#beginTimeStr").val() == "" || $("#endTimeStr").val() == "") {
			return false;
		}
		var begin = new Date($("#beginTimeStr").val());
		var end = new Date($("#endTimeStr").val());
		if (begin > end) {
			$("#beginTimeStr").val("");
		}
	})
});

// 获取URL参数
function GetQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if (r != null)
		return unescape(r[2]);
	return null;
}