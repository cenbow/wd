/**
 * 添加或删除运费模板
 */
$(function() {
	InitCity();// 初始化数据
	if ($("#setexpress").next().find("tr").length == 1) {
		$("#setexpress").next().hide();
	}
	else {
		$("#setexpress").next().show();
	}
	if ($("#setlogistics").next().find("tr").length == 1) {
		$("#setlogistics").next().hide();
	}
	else {
		$("#setlogistics").next().show();
	}
	$("#express,#logistics").click(function() {
		if ($(this).is(":checked")) {
			$(this).parent().next().slideDown();
		}
		else {
			$(this).parent().next().slideUp();
		}
	});
	$("#setexpress,#setlogistics").click(function() {
		var html = '<tr data-value="">';
		html += '<td><span></span><a href="javascript:void(0)" class="ft_lan fr showarea">编辑</a></td>';
		html += '<td class="tc"><input type="text" class="btn_h30 w80 firstCount" value="" /></td>';
		html += '<td class="tc"><input type="text" class="btn_h30 w80 firstpiece" value="" /></td>';
		html += '<td class="tc"><input type="text" class="btn_h30 w80 moreCount" value="" /></td>';
		html += '<td class="tc"><input type="text" class="btn_h30 w80 morepiece" value="" /></td>';
		html += '<td class="tc"><a href="javascript:void(0)" class="ft_c6 delarea">删除</a></td>';
		html += '</tr>';
		$(this).next().find("tbody").append(html);
		$(this).next().show();
	});
	$(".posabs").on("keyup", ".firstCount,.moreCount", function() {
		var tmptxt = $(this).val();
		$(this).val(tmptxt.replace(/\D/g, ''));
	});
	$(".posabs").on("keyup", ".firstpiece,.morepiece", function() {
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
	$(".posabs").on("click", ".delarea", function() {
		$(this).parent().parent().remove();
	});
	// 地区选则
	$(".posabs").on("click", ".showarea", function() {
		var $this = $(this);
		var setarea = $(this).parent().parent().attr("data-value");
		if (setarea == null) {
			setarea = "";
		}
		var nosetarea = getselectarea($(this));
		$(this).region({
			"setarea" : setarea,
			"notsetarea" : nosetarea,
			"ok" : function(name, data) {
				$this.siblings().text(name);
				$this.parent().parent().attr("data-value", data);
			},
		});
	});
});
// 提交数据
function submitData() {
	var data = getDataByJson();
	if (data == false || data == null || data == "") {
		return false;
	}
	var loadindex = layer.load("努力提交中...");
	$(".botis").attr("onclick", "");
	$.ajax({
		url : "submitData",
		dataType : "json",
		type : "post",
		data : {
			"data" : data,
			"fid" : $("#freightId").val(),
		},
		success : function(data) {
			layer.close(loadindex);
			if (data.state != "1") {
				alert(data.msg);
				setTimeout(function() {
					location.reload();
				}, 1000);
			}
			else {
				alert("成功", true);
				setTimeout(function() {
					window.location.href = "/freight/freightList";
				}, 1000);
			}
			$(".botis").attr("onclick", "submitData()");
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			layer.close(loadindex);
			$(".botis").attr("onclick", "submitData()");
			alert("系统异常");
			location.reload();
		}
	})
}

// 获取数据转成json格式
function getDataByJson() {
	var postAgeName = $("#postAgeName").val();// 获取模板名称
	var sheng = $("#sheng").val();// 获取省
	var shi = $("#shi").val();// 获取市
	var qu = $("#qu").val();// 获取区域/县
	var deliverytime = $("#deliverytime").val();// 获取发货时间
	if (postAgeName == null || postAgeName == "") {
		$("#postAgeName").next().find(".errorspan").show().siblings().hide();
		return false;
	}
	if (sheng == null || sheng == "" || sheng == "0" || shi == null || shi == "" || shi == "0" || qu == null || qu == "" || qu == "0") {
		$("#sheng").siblings(".error_font").find(".errorspan").show().siblings().hide();
		return false;
	}
	// if (!$("#express").is(":checked") && !$("#logistics").is(":checked")) {
	// alert("填写快递或物流");
	// return false;
	// }
	var isguo = true;
	// if ($("#express").is(":checked")) {
	// $("#express").parent().next().find(".lu_table>table>tbody>tr:gt(0)").each(function() {
	// var data = $(this).attr("data-value");
	// if (data == null || data == "") {
	// isguo = false;
	// return false;
	// }
	// });
	// }
	$("#express").parent().next().find(".lu_table>table>tbody>tr:gt(0)").each(function() {
		var data = $(this).attr("data-value");
		if (data == null || data == "") {
			isguo = false;
			return false;
		}
	});
	if (!isguo) {
		alert("请选择区域！");
		return false;
	}

	if ($("#logistics").is(":checked")) {
		$("#logistics").parent().next().find(".lu_table>table>tbody>tr:gt(0)").each(function() {
			var data = $(this).attr("data-value");
			if (data == null || data == "") {
				isguo = false;
				return false;
			}
		});
	}
	if (!isguo) {
		alert("请选择区域！");
		return false;
	}
	// 验证输入
	var tongguo = true;
	$("#express,#logistics:checked").parent().next().find(".firstCount,.moreCount").each(function() {
		var tmptxt = $(this).val();
		$(this).val(tmptxt.replace(/\D/g, ''));
		var temp = $(this).val();
		if (temp == null || temp == "") {
			tongguo = false;
			$(this).focus();
			return false;
		}
		if (parseInt(temp) <= 0) {
			tongguo = false;
			$(this).val("").focus();
			return false;
		}
	});
	if (!tongguo) {
		alert("请检查输入");
		return false;
	}
	$("#express,#logistics:checked").parent().next().find(".firstpiece,.morepiece").each(function() {
		var tmptxt = $(this).val();
		tmptxt = tmptxt.replace(/[^\d.]/g, ""); // 清除“数字”和“.”以外的字符
		tmptxt = tmptxt.replace(/^\./g, ""); // 验证第一个字符是数字而不是.
		tmptxt = tmptxt.replace(/\.{2,}/g, "."); // 只保留第一个. 清除多余的.
		if (tmptxt.indexOf(".") > 0 && tmptxt.length - tmptxt.indexOf(".") > 3) {
			tmptxt = tmptxt.substr(0, tmptxt.indexOf(".") + 3);
		}
		tmptxt = tmptxt.replace(".", "$#$").replace(/\./g, "").replace("$#$", ".");
		$(this).val(tmptxt);
		var temp = $(this).val();
		if (temp == null || temp == "") {
			tongguo = false;
			$(this).focus();
			return false;
		}
		if (parseFloat(temp) < 0) {
			tongguo = false;
			$(this).val("").focus();
			return false;
		}
	});
	if (!tongguo) {
		alert("请检查输入");
		return false;
	}
	// 组合快递物流
	var jsonlist = "[";
	var $express = $("#express").parent().next();
	var kuaidilist = "";
	if ($express != null && $express.length > 0) {
		// 先组合默认首条记录
		kuaidilist = '{';
		kuaidilist += '"firstCount":"' + $express.find(".mryes>.firstCount").val() + '",';// 默认首件
		kuaidilist += '"firstpiece":"' + $express.find(".mryes>.firstpiece").val() + '",';// 默认首费
		kuaidilist += '"moreCount":"' + $express.find(".mryes>.moreCount").val() + '",';// 默认续件
		kuaidilist += '"morepiece":"' + $express.find(".mryes>.morepiece").val() + '",';// 默认续费
		kuaidilist += '"courierType":"1",';// 快递
		kuaidilist += '"destination":"",';// 例外地区编码
		kuaidilist += '"status":"0"';// 是否默认
		kuaidilist += '},';
		var $kdtr = $express.find(".lu_table>table>tbody>tr:gt(0)");
		if ($kdtr != null && $kdtr.length > 0) {
			$kdtr.each(function() {
				kuaidilist += '{';
				kuaidilist += '"firstCount":"' + $(this).find(".firstCount").val() + '",';// 默认首件
				kuaidilist += '"firstpiece":"' + $(this).find(".firstpiece").val() + '",';// 默认首费
				kuaidilist += '"moreCount":"' + $(this).find(".moreCount").val() + '",';// 默认续件
				kuaidilist += '"morepiece":"' + $(this).find(".morepiece").val() + '",';// 默认续费
				kuaidilist += '"courierType":"1",';// 快递
				kuaidilist += '"destination":"' + $(this).attr("data-value") + '",';// 例外地区编码
				kuaidilist += '"status":"1"';// 是否默认
				kuaidilist += '},';
			});
		}
	}
	var $logistics = $("#logistics:checked").parent().next();
	var wuliulist = "";
	if ($logistics != null && $logistics.length) {
		// 先组合默认首条记录
		wuliulist = '{';
		wuliulist += '"firstCount":"' + $logistics.find(".mryes>.firstCount").val() + '",';// 默认首件
		wuliulist += '"firstpiece":"' + $logistics.find(".mryes>.firstpiece").val() + '",';// 默认首费
		wuliulist += '"moreCount":"' + $logistics.find(".mryes>.moreCount").val() + '",';// 默认续件
		wuliulist += '"morepiece":"' + $logistics.find(".mryes>.morepiece").val() + '",';// 默认续费
		wuliulist += '"courierType":"4",';// 物流
		wuliulist += '"destination":"",';// 例外地区编码
		wuliulist += '"status":"0"';// 是否默认
		wuliulist += '},';
		var $wutr = $logistics.find(".lu_table>table>tbody>tr:gt(0)");
		if ($wutr != null && $wutr.length > 0) {
			$wutr.each(function() {
				wuliulist += '{';
				wuliulist += '"firstCount":"' + $(this).find(".firstCount").val() + '",';// 默认首件
				wuliulist += '"firstpiece":"' + $(this).find(".firstpiece").val() + '",';// 默认首费
				wuliulist += '"moreCount":"' + $(this).find(".moreCount").val() + '",';// 默认续件
				wuliulist += '"morepiece":"' + $(this).find(".morepiece").val() + '",';// 默认续费
				wuliulist += '"courierType":"4",';// 物流
				wuliulist += '"destination":"' + $(this).attr("data-value") + '",';// 例外地区编码
				wuliulist += '"status":"1"';// 是否默认
				wuliulist += '},';
			});
		}
	}
	jsonlist += kuaidilist + wuliulist;
	jsonlist = jsonlist.substr(0, jsonlist.length - 1);
	jsonlist += "]";

	var json = "{";
	json += '"postAgeName":"' + postAgeName + '",';
	json += '"billingType":"1",';
	json += '"deliverytime":"' + deliverytime + '",';
	json += '"free":"0",';
	json += '"province":"' + sheng + '",';
	json += '"city":"' + shi + '",';
	json += '"district":"' + qu + '",';
	json += '"detailsList":' + jsonlist + '';
	json += "}";
	return json;
}

// 获取不可选的区域
function getselectarea($this) {
	var datavalue = "";
	$this.parent().parent().siblings().each(function() {
		var temp = $(this).attr("data-value");
		if (temp == null) {
			temp = "";
		}
		datavalue += temp;
	});
	return datavalue;

}