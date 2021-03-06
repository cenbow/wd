/**
 * 修改订单价格
 */
$(function() {
	// 折扣输入
	$(".zhekou").keyup(function() {
		zhengdecimal($(this));// 验证
		var price = $(this).parents(".xiugaiprc").siblings(".fm_var:eq(0)").text();// 单价
		price = parseFloat(price);
		var count = $(this).parents(".xiugaiprc").siblings(".fm_var:eq(1)").text();// 数量		 	
		count = parseFloat(count);
		var zhe = parseFloat($(this).val());// 当前的浮点数(折扣)
		var jg;
		if (zhe <= 10) {		
			jg = accAdd(accMul(price, count), accMul(accMul(zhe, 0.1), accMul(price, count)) * -1) * -1;
		}
		else {
			jg = accMul(accMul(zhe, 0.1), accMul(price, count));
		}
		if (jg == null || jg == NaN || isNaN(jg)) {
			jg = "";
		}		
		$(this).parent().next().find(".youhui").val(jg);
		calculate();
	}).blur(function() {
		validationdian($(this));// 验证
		calculate();
	});
	// 优惠输入
	$(".youhui").keyup(function() {
		alldecimal($(this));// 验证
		$(this).parent().siblings().find(".zhekou").val("");
		calculate();
	}).blur(function() {
		validationdian($(this));// 验证
		calculate();
	});
	// 快递输入
	$(".kuaidi").keyup(function() {
		zhengdecimal($(this));// 验证
		if ($(this).val() == "") {
			$("#freight").text(0);
		}
		else {
			$("#freight").text($(this).val());
		}
		calculate();
	}).blur(function() {
		validationdian($(this));// 验证
		calculate();
	});
});
// 点击确认
function submitajax(orderNo) {
	var commission=0;//佣金
	var count;//数量
	$(".jg").each(function(){
		var tempcommission= parseFloat($(this).val());
		if(tempcommission==null||tempcommission==""){
			tempcommission = 0;
		}	
		var tempcount=$(this).attr("name");
		commission+=(tempcommission*tempcount);	
	})
	var kuaidi = $("#kuaidi").val();
	var totalprice = $("#totalprice").text();
	var jg =Subtr(totalprice,kuaidi);
	jg = parseFloat(jg);
	if (kuaidi == null || kuaidi == "" || totalprice == null || totalprice == "") {
		alert("输入不能为空");
		return;
	}
	if(commission>=jg){
		alert("总价不能小于佣金");
		return;
	}
	else {
		$.ajax({
			url : "orderajax",
			type : "post",
			dataType : "json",
			data : {
				"key" : "updateorder",
				"postage" : kuaidi,				
				"totalPrice" : totalprice,
				"orderNo" : orderNo,
				"commission":commission,
				"jg":jg
			},
			error : function() {
				alert("异常！");
			},
			success : function(data) {
				if (data.msg == "1") {
					alert("修改成功");
					location.href = "buylist";
				}
				if(data.msg!="1"){
					alert("修改失败");
					location.reload();
				}
			}

		})
	}
}

// 点击取消
function cancel() {
	$("li input").val("");
}
/* 小数（正负都行）验证 */
function alldecimal($this) {
	var tmptxt = $this.val();
	tmptxt = tmptxt.replace(/[^\-\d.]/g, ""); // 清除“数字”和“.”以外的字符
	tmptxt = tmptxt.replace(/^\./g, ""); // 验证第一个字符是数字而不是.
	tmptxt = tmptxt.replace(/\.{2,}/g, "."); // 只保留第一个. 清除多余的.
	if (tmptxt.indexOf(".") > 0 && tmptxt.length - tmptxt.indexOf(".") > 3) {
		tmptxt = tmptxt.substr(0, tmptxt.indexOf(".") + 3);
	}
	if (tmptxt.indexOf("-") > 0) {
		tmptxt = tmptxt.replace(/\-/g, ""); // 只保留第一个. 清除多余的.
	}
	tmptxt = tmptxt.replace(".", "$#$").replace(/\./g, "").replace("$#$", ".");
	tmptxt = tmptxt.replace("-", "$#$").replace(/\-/g, "").replace("$#$", "-");
	$this.val(tmptxt);
}
// 正数，小数
function zhengdecimal($this) {
	var tmptxt = $this.val();
	tmptxt = tmptxt.replace(/[^\d.]/g, ""); // 清除“数字”和“.”以外的字符
	tmptxt = tmptxt.replace(/^\./g, ""); // 验证第一个字符是数字而不是.
	tmptxt = tmptxt.replace(/\.{2,}/g, "."); // 只保留第一个. 清除多余的.
	if (tmptxt.indexOf(".") > 0 && tmptxt.length - tmptxt.indexOf(".") > 3) {
		tmptxt = tmptxt.substr(0, tmptxt.indexOf(".") + 3);
	}
	tmptxt = tmptxt.replace(".", "$#$").replace(/\./g, "").replace("$#$", ".");
	$this.val(tmptxt);
}
// 验证最后一位
function validationdian($this) {
	var tmptxt = $this.val();
	if (tmptxt.indexOf(".") == (tmptxt.length - 1)) {
		tmptxt = tmptxt.replace(/\./g, ".0"); // 只保留第一个. 清除多余的.
	}
	$this.val(tmptxt);
}
// 计算
function calculate() {
	var original = $("#original").text();
	var freight = $("#freight").text();
	var temp = accAdd(accAdd(original, freight), -0.01);
	var allyouhui = 0;
	$(".youhui").each(function() {
		allyouhui = accAdd(allyouhui, $(this).val());
	});
	if (isNaN(allyouhui)) {
		return false;
	}
	if (parseFloat(allyouhui) < 0) {
		if (parseFloat(allyouhui * -1) > parseFloat(temp)) {
			allyouhui = temp * -1;
		}
	}

	if (allyouhui >= 0) {
		$("#discount").text("+" + allyouhui);
	}
	else {
		$("#discount").text(allyouhui);
	}
	var totalprice = accAdd(accAdd(original, freight), allyouhui);
	if (isNaN(totalprice)) {
		return false;
	}
	$("#totalprice").text(totalprice);
}
/** ***************** */
/**
 * * 加法函数，用来得到精确的加法结果 * 说明：javascript的加法结果会有误差，在两个浮点数相加的时候会比较明显。这个函数返回较为精确的加法结果。 * 调用：accAdd(arg1,arg2) * 返回值：arg1加上arg2的精确结果
 */
function accAdd(arg1, arg2) {
	var r1, r2, m, c;
	try {
		r1 = arg1.toString().split(".")[1].length;
	}
	catch (e) {
		r1 = 0;
	}
	try {
		r2 = arg2.toString().split(".")[1].length;
	}
	catch (e) {
		r2 = 0;
	}
	c = Math.abs(r1 - r2);
	m = Math.pow(10, Math.max(r1, r2));
	if (c > 0) {
		var cm = Math.pow(10, c);
		if (r1 > r2) {
			arg1 = Number(arg1.toString().replace(".", ""));
			arg2 = Number(arg2.toString().replace(".", "")) * cm;
		}
		else {
			arg1 = Number(arg1.toString().replace(".", "")) * cm;
			arg2 = Number(arg2.toString().replace(".", ""));
		}
	}
	else {
		arg1 = Number(arg1.toString().replace(".", ""));
		arg2 = Number(arg2.toString().replace(".", ""));
	}
	return (arg1 + arg2) / m;
}
//减法函数  
function Subtr(arg1, arg2) {  
    var r1, r2, m, n;  
    try {  
        r1 = arg1.toString().split(".")[1].length;  
    }  
    catch (e) {  
        r1 = 0;  
    }  
    try {  
        r2 = arg2.toString().split(".")[1].length;  
    }  
    catch (e) {  
        r2 = 0;  
    }  
    m = Math.pow(10, Math.max(r1, r2));  
     //last modify by deeka  
     //动态控制精度长度  
    n = (r1 >= r2) ? r1 : r2;  
    return ((arg1 * m - arg2 * m) / m).toFixed(n);  
}  

/**
 * * 乘法函数，用来得到精确的乘法结果 * 说明：javascript的乘法结果会有误差，在两个浮点数相乘的时候会比较明显。这个函数返回较为精确的乘法结果。 * 调用：accMul(arg1,arg2) * 返回值：arg1乘以 arg2的精确结果
 */
function accMul(arg1, arg2) {
	var m = 0, s1 = arg1.toString(), s2 = arg2.toString();
	try {
		m += s1.split(".")[1].length;
	}
	catch (e) {
	}
	try {
		m += s2.split(".")[1].length;
	}
	catch (e) {
	}
	return Number(s1.replace(".", "")) * Number(s2.replace(".", "")) / Math.pow(10, m);
}