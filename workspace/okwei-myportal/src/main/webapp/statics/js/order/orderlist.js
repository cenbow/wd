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
				} else {
					window.location.href = "buylist?dt=" + datatype + "&ds=" + datastate;
				}
			} else {
				if ($(this).parent().attr("id") == "ordertype") {
					window.location.href = "reservelist?dt=" + datatype + "&ds=-1";
				} else {
					window.location.href = "reservelist?dt=" + datatype + "&ds=" + datastate;
				}
			}
		}
	}); 
	
	
	$(".fl>select").on("change", function() {
		// 如果点击的条件没有是没有选中，并且不为0
			var datatype = $("#ordertype option:selected").val();
			var datastate = $("#orderstate option:selected").val();
			if (datatype==3){
				if ($(this).parent().attr("id") == "ordertype") {
					window.location.href = "reservelist?dt=" + datatype + "&ds=-1";
				} else {
					window.location.href = "reservelist?dt=" + datatype + "&ds=" + datastate;
				}
			}else{
				if ($(this).parent().attr("id") == "ordertype") {
					window.location.href = "buylist?dt=" + datatype + "&ds=-1";
				} else {
					window.location.href = "buylist?dt=" + datatype + "&ds=" + datastate;
				}
			}
	});
	
	
	
	
});

// 获取URL参数
function GetQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if (r != null)
		return unescape(r[2]);
	return null;
}



