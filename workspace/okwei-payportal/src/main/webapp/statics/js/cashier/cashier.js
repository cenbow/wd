/** 弹窗调用函数 * */
function wins(title, win_id, width, height) { // title 标题 win_id 弹窗ID width
	// 弹窗宽度 height 弹窗高度
	var pagei = $.layer({
		type : 1, // 0-4的选择,0：信息框（默认），1：页面层，2：iframe层，3：加载层，4：tips层。
		// btns : 2,
		// btn : [
		// '确定', '取消'
		// ],
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
// 获取URL参数
function GetQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if (r != null)
		return unescape(r[2]);
	return null;
}
/**
 * 收银台
 */
$(function() {
	// 使用微钱包支付
	$("#walletck").click(function() {
		walletClick();
	});
	// 使用微钱币支付
	$("#weicoinck").click(function() {
		weicoinClick();
	});
	$("#butzfwc").click(function() {
		var orderNo = $("#orderNo").val();
		// 检测是否支付完成
		$.ajax({
			url : "yzPayState",
			type : "post",
			async : false,
			data : {
				orderNo : orderNo
			},
			dataType : "json",
			success : function(data) {
				if (data.status == 1) {
					window.location.href = "success";
				}
				else {
					window.location.href = "error?orderNo=" + orderNo;
				}
			}
		});

	});
	$(".btn_hui28_pre").click(function() {
		window.location.href = "error?orderNo=" + $("#orderNo").val();
	});

	$("#kjzf,[for=kjzf]").click(function() {
		$("#kuaijie").slideDown(300);
		$("#car_user").slideUp(300);
	});
	$("#wxzf,#bbzf,[for=wxzf],[for=bbzf]").click(function() {
		$("#kuaijie").slideUp(300);
	})
	$("#ylzf,[for=ylzf]").click(function() {
		$("#car_user").slideDown(300);
		$("#kuaijie").slideUp(300);
	});
	$("[name=mzh_zf_zf]").click(function() {
		if ($(this).is(":checked")) {
			$("#kjzf").prop("checked", true);
		}
	});
	$("#kuaijie [type=radio]").click(function() {
		$(this).siblings("div").show(300).parent().siblings().find("div.dis_n").hide();
		var total = $("#totalPrice").val();// 总价
		var balance = $("#balance").val();// 微钱包余额
		if ($("#walletck").is(":checked")) {
			// 选中，并且微钱包余额大于支付金额的时候
			if (parseFloat(total) <= parseFloat(balance)) {
				$(this).siblings("div").find("div>span").text("0.0");
			}
			else {
				$(this).siblings("div").find("div>span").text(fomatFloat(parseFloat(total) - parseFloat(balance),2));
			}

		}
		else {
			$(this).siblings("div").find("div>span").text(total);
		}
	});

	if ($("#wallets").length > 0) {
		walletClick();
//		$("#bor_wu").slideDown(300);
	}
	if ($("#weicoinck").is(":checked")) {
		weicoinClick();
	}
	
	 document.onkeydown=function(event){
		 var paypass = $("#paypass").val();// 获取支付密码
         var e = event || window.event || arguments.callee.caller.arguments[0];
         /*if(e && e.keyCode==27){ // 按 Esc 
             //要做的事情
           }
         if(e && e.keyCode==113){ // 按 F2 
              //要做的事情
            }  */          
          if(e && e.keyCode==13 && paypass!=null && paypass!=""){ // enter 键
              //要做的事情
        	  verifySMS();
         }
     }; 
});
function walletClick() {
	// 是否选中微钱包
	if ($("#walletck").is(":checked")) {
		// 选中
		var balance = $("#wallets").val();// 微钱包余额
		var zhifujine = $("#zhifujine").val();//需要支付的金额
		$("#paytype").val($("#paytype").val()+"1");
		// 选中，并且微钱包余额大于支付金额的时候
		if (parseFloat(zhifujine) <= parseFloat(balance)) {
			// 所有的单选框不可选，并置灰
			
			$("[type=radio]").prop("checked", false).attr("disabled", "disabled");
			$("#walletzhifu").val(parseFloat(zhifujine));
			$("#zhifujine").val("0");
			$(".walletmoney").html(parseFloat(zhifujine));
			$("#bor_walletg").slideDown(300);
			$("#bor_walletl").slideUp(300);
			$("bor_walletg").show();
			$("bor_walletl").hide();
		}
		else {
			// 微钱包余额不足，可以用其他支付部分
			$("[type=radio]").removeAttr("disabled");
			$("#zhifujine").val(fomatFloat(parseFloat(zhifujine)-parseFloat(balance),2));
			$("#walletzhifu").val(parseFloat(balance));
			$(".walletmoney").html(parseFloat(balance));
			$(".walletmoney1").html(fomatFloat(parseFloat(zhifujine)-parseFloat(balance),2));
			$("#bor_walletl").slideDown(300);
			$("#bor_walletg").slideUp(300);
			$("bor_walletl").show();
			$("bor_walletg").hide();
			
		}
		
		$("dl.bor_si").slideUp(300);
	}
	else {
		// 未选中
		$("#zhifujine").val(fomatFloat(parseFloat($("#zhifujine").val())+parseFloat($("#walletzhifu").val()),2));		
		$("[type=radio]").removeAttr("disabled");
		$("#bor_walletg").slideUp(300);
		$("#bor_walletl").slideUp(300);
		$("#paytype").val($("#paytype").val().replace("1",""));
		if ($("#weicoinck").is(":checked")) {
			if($("#lastone").is(":checked"))
			{
				$("#zhifujine").val(fomatFloat(parseFloat($("#totalPrice").val())-parseFloat($("#cashAmount").val()),2));
			} else
			{
				$("#zhifujine").val($("#totalPrice").val());
			}
			
			weicoinClick();
		}
	}
}


//点击微店币支付
function weicoinClick(){
	// 是否选中微钱包
	if ($("#weicoinck").is(":checked")) {
		// 选中
		var balance = $("#weicoins").val();// 微店币余额
		var zhifujine = $("#zhifujine").val();//需要支付的金额
		$("#paytype").val($("#paytype").val()+"2");
		// 选中，并且微钱包余额大于支付金额的时候
		if (parseFloat(zhifujine) <= parseFloat(balance)) {
			// 所有的单选框不可选，并置灰
			$("[type=radio]").prop("checked", false).attr("disabled", "disabled");
			$("#weicoinzhifu").val(parseFloat(zhifujine));
			$("#zhifujine").val("0");
			$(".weicoinmoney").html(parseFloat(zhifujine));
			$("#bor_weicoing").slideDown(300);
			$("#bor_weicoinl").slideUp(300);
			$("bor_weicoing").show();
			$("bor_weicoinl").hide();
			
		}
		else {
			// 微钱包余额不足，可以用其他支付部分
			$("[type=radio]").removeAttr("disabled");
			$("#zhifujine").val(fomatFloat(parseFloat(zhifujine)-parseFloat(balance),2));
			$("#weicoinzhifu").val(parseFloat(balance));
			$(".weicoinmoney").html(parseFloat(balance));
			$(".weicoinmoney1").html(fomatFloat(parseFloat(zhifujine)-parseFloat(balance),2));
			$("#bor_weicoinl").slideDown(300);
			$("#bor_weicoing").slideUp(300);
			$("bor_weicoinl").show();
			$("bor_weicoing").hide();
		}
		
		$("dl.bor_si").slideUp(300);
	}
	else {
		// 未选中
		$("#zhifujine").val(fomatFloat(parseFloat($("#zhifujine").val())+parseFloat($("#weicoinzhifu").val()),2));		
		$("[type=radio]").removeAttr("disabled");
		$("#bor_weicoing").slideUp(300);
		$("#bor_weicoinl").slideUp(300);
		$("#paytype").val($("#paytype").val().replace("2",""));
		if ($("#walletck").is(":checked")) {
			if($("#lastone").is(":checked"))
			{
				$("#zhifujine").val(fomatFloat(parseFloat($("#totalPrice").val())-parseFloat($("#cashAmount").val()),2));
			} else
			{
				$("#zhifujine").val($("#totalPrice").val());
			}
			walletClick();
		}
	}
}

function clicke(){   
	 var wallet=document.getElementById("wallet"); 
	 //总金额
	 var totalPrice= $("#totalPrice").val();
	 //其他支付金额
	 var surplus= $("#surplus").val();
	 //现金券抵减金额
	 var cashAmount= $("#cashAmount").val();
	 //余额
	 var balance= $("#balance").val();
	 var xz=document.getElementById("lastone"); 
	 //是否使用现金劵
	 var flag=false;
	 if (xz==null) {
		 flag=false;
	}else{
		flag=xz.checked;
	}
	 $("#lastone1").val(flag+"");  
	 var ss=0;
		//使用钱包
		if (wallet.checked) { 
			ss=surplus-balance;
			//使用现金劵
			if (flag) {  
				ss=surplus-cashAmount;
			}else{
				ss=totalPrice-balance;
			} 
		}else{ 
			//不使用钱包
			ss=totalPrice;
			//使用现金劵
			if (flag){
				ss=totalPrice-cashAmount;
			}else{  
				ss=totalPrice;
			} 
		}
	
	$(".money2").html(ss); 
		
	 
	//将金额减去现金劵
	if (flag) {   
		 $(".money").html(totalPrice-cashAmount); 
		 $(".money1").html(surplus-cashAmount); 
	}else{
		 $(".money").html(totalPrice); 
		 $(".money1").html(surplus);
		
	}
 
}


/**
 * 点击下一步
 */
function next() { 
	if ($("#walletck:checked").length < 1 && $("input.ml_10:checked").length < 1 && $("#weicoinck:checked").length < 1) {
		alert("请选择支付方式！");
		return false;
	}
	
	if ($("#kjzf").is(":checked")) {
		if ($("#kuaijie input:checked").length < 1) {
			alert("请选择快捷支付的方式！");
			return false;
		}
	}
	// 是否需要选择其他支付方式
	var bankAmout =parseFloat($("#zhifujine").val());
	if(bankAmout !=null && bankAmout>0.01 &&  $("input.ml_10:checked").length < 1){
		alert("请选择剩余金额的支付方式！");
		return false;
	}


	var orderNo = $("#orderNo").val();
	 var xz=$("#lastone1"); 
	// 获取最新的状态
	var flag = false;
	$.ajax({
		url : "getPayState",
		type : "post",
		async : false,
		data : {
			orderNo : orderNo,
			coinPay : xz.val()
		},
		dataType : "json",
		success : function(data) {
			if (data.status == 1) {
				alert("该订单信息已发生改变，请刷新页面！");
				flag = true;
			}
		}
	});
	if (flag) {
		return;
	}

	if ($("#walletck").is(":checked")||$("#weicoinck").is(":checked")) {
		var total = $("#totalPrice").val();// 总价
		if (parseFloat(total) >= 200) {
			wins('微店钱包支付验证', 'win_div_2', '514px', '420px');
		}
		else {
			wins('微店钱包支付验证', 'win_div_2', '514px', '320px');
		}
	}
	else {
		var id = $("input[name=mzh_zf]:checked").attr("id");// 获取选中的ID
		var url = "";

		if (id == "kjzf") {// 快捷
			var cardid = $("#kuaijie input[name=mzh_zf_zf]").val();// 选中的银行卡
			// window.location.href = "/llpay/payrequest?orderNo=" + orderNo +
			// "&cardID=" + cardid;
			$("[name=cardID]").val(cardid);
			document.getElementById("llpay").submit();
		}
		else if (id == "wxzf") {// 微信
			// window.location.href = "/wxpay/payrequest?orderNo=" + orderNo;
			document.getElementById("wxpay").submit();
		}
		else if (id == "bbzf") {// 百宝支付
			// window.location.href = "/bfbpay/payrequest?orderNo=" + orderNo;
			document.getElementById("bfbpay").submit();
		}
		else if (id == "cftzf") {// 财付通
			// window.location.href = "/tenpay/payrequest?orderNo=" + orderNo;
			document.getElementById("tenpay").submit();
		}
		// 弹出支付成功支付失败页面
		win3('支付', 'win_div_3', '514px', '260px');
	}
}

function win3(title, win_id, width, height) { // title 标题 win_id 弹窗ID width
	// 弹窗宽度 height 弹窗高度
	var pagei = $.layer({
		type : 1, // 0-4的选择,0：信息框（默认），1：页面层，2：iframe层，3：加载层，4：tips层。
		btns : 0,
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
				0, false
		],
		shadeClose : false,
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

/**
 * 发送短信验证码
 */
var time;
var count;
function sendSMS() {
	$("#tips").text("");
	var paypass = $("#paypass").val();// 获取支付密码
	if (paypass == null || paypass == "") {
		alert("请输入支付密码！");
		return false;
	}
	$.ajax({
		url : "sendSMS",
		type : "post",
		data : {
			paypassword : paypass
		},
		dataType : "json",
		success : function(data) {
			if (data.status == 1) {
				count = 60;
				$("#sendsms").val("60秒后重新发送");
				$("#sendsms").attr("disabled", "disabled").css("background", "#ccc");
				time = window.setInterval("run();", 1000);
			}
			else {
				$("#tips").text(data.msg);
			}
		}
	});
}
function run() {
	count--;
	$("#sendsms").val(count + "秒后重新发送");
	if (count <= 0) {
		window.clearInterval(time);
		$("#sendsms").removeAttr("disabled", "disabled").css("background", "#71b601");
		$("#sendsms").val("重新发送");
		// $("#sendsms")
	}
}
/**
 * 小数精度
 * @param src
 * @param pos
 * @returns {Number}
 */
function fomatFloat(src,pos){   
    return Math.round(src*Math.pow(10, pos))/Math.pow(10, pos);   
 } 
/**
 * 验证手机验证码
 */
function verifySMS() {
	var paypass = $("#paypass").val();// 获取支付密码
	if (paypass == null || paypass == "") {
		alert("请输入支付密码！");
		return false;
	}
	var code = $("#phonecode").val();// 手机验证码
	code = (code == null ? "" : code);
	var orderNo = $("#orderNo").val();// 订单号
	if (orderNo == null || orderNo == "") {
		alert("订单号不能为空！");
		return false;
	}
	// 需要发送短信验证码
	if ($("#sendsms").length > 0) {
		if (code == null || code == "") {
			alert("请填写手机验证码！");
			return false;
		}
	}
	var paytype=$("#paytype").val();
	$("#tips").text("");
	$("#yzzf").attr("disabled", "disabled").css("background", "#ccc");
	$.ajax({
		url : "verifySMS",
		type : "post",
		data : {
			paypassword : paypass,
			code : code,
			orderNo : orderNo,
			payType : paytype
		},
		dataType : "json",
		success : function(data) {
			if (data.status == "1") {
				var orderNo = $("#orderNo").val();
				if ($("input[name=mzh_zf]:checked").length > 0) {
					var id = $("input[name=mzh_zf]:checked").attr("id");// 获取选中的ID
					if (id == "kjzf") {// 快捷
						var cardid = $("#kuaijie input[name=mzh_zf_zf]").val();// 选中的银行卡
						window.location.href = "/llpay/payrequest?orderNo=" + orderNo + "&cardID=" + cardid;
					}
					else if (id == "wxzf") {// 微信
						window.location.href = "/wxpay/payrequest?orderNo=" + orderNo;
					}
					else if (id == "bbzf") {// 百宝支付
						window.location.href = "/bfbpay/payrequest?orderNo=" + orderNo;
					}
					else if (id == "cftzf") {// 财付通
						window.location.href = "/tenpay/payrequest?orderNo=" + orderNo;
					}
				}
				else {
					window.location.href = "/pay/success?orderNo=" + orderNo;
				}
			}
			else {
				$("#yzzf").removeAttr("disabled", "disabled").css("background", "#71b601");
				$("#tips").text(data.msg);
			}

			
		}
	});
}
function countdown(dateStr) {
//	var time = new Date();     
//    var b = 10; //分钟数
//    time.setMinutes(time.getMinutes() + b, time.getSeconds(), 0);
//    
//	setInterval("timer('" + time.toString() + "')", 1000);
	var t=new Date()
	var nt= Number(t.getTime())+Number(dateStr);
	setInterval("timer(" +nt + ")", 1000);
}
function timer(dateStr) {
	var now= new Date();
	var ts = dateStr - now.getTime();// 计算剩余的毫秒数
//	var ts=dateStr;
	if(ts<=0) //已经过期
	{
		$(".mzh_xiayibu.ml_40.mb_30").attr("disabled", "disabled").css("background", "#ccc");
	}
	var dd = parseInt(ts / 1000 / 60 / 60 / 24, 10);// 计算剩余的天数
	var hh = parseInt(ts / 1000 / 60 / 60 % 24, 10) + dd * 24;// 计算剩余的小时数
	var mm = parseInt(ts / 1000 / 60 % 60, 10);// 计算剩余的分钟数
	var ss = parseInt(ts / 1000 % 60, 10);// 计算剩余的秒数
	if (ss <= 0) {
		ss = 00;
	}
	if (mm <= 0) {
		mm = 00;
	}
	if (hh <= 0) {
		hh = 00;
	}
	hh = checkTime(hh);
	mm = checkTime(mm);
	if (isNaN(ss) || ss == null || ss == "") {
		ss = "00";
	}
	if (isNaN(mm) || mm == null || mm == "") {
		mm = "00";
	}
	if (isNaN(hh) || hh == null || hh == "") {
		hh = "00";
	}
	$("#countdown").html("<font>" + hh + "小时<font>" + mm + "</font>分<font>" + ss + "</font>秒");
	
}
function checkTime(i) {
	if (i < 10) {
		i = "0" + i;
	}
	return i;
}