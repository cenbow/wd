/**
 * 密码
 */
$(function() {
	//
	var loginTiper = $("#loginTiper");
	$("#modifyPWDBtn").click(function() {
		changePwd();
	});
	$("#setPWDBtn").click(function() {
		setPWD();
	});
	//找回登陆密码
	$("#modifyNewFindPwdBtn").click(function() {
		resetPwd('5');
	});
	//找回支付密码
	$("#modifyNewFindPayPwdBtn").click(function() {
		resetPwd('9');
	});
	//登陆密码修改
	function changePwd() {
		loginTip('')
		var oldPwd = $("#oldPwd");
		var newPwd = $("#newPwd");
		var reNewPwd = $("#reNewPwd");
		var digestPatten = /^[0-9]+$/;
		if (!oldPwd.val().length) {
			loginTip("请输入旧密码！");
			return false;
		}
		if (!newPwd.val().length) {
			loginTip("请输入新密码！");
			return false;
		}
		if (!reNewPwd.val().length) {
			loginTip("请再次输入新密码！");
			return false;
		}
		if (newPwd.val() != reNewPwd.val()) {
			loginTip("两次输入的密码不一样，请再次输入！");
			return false;
		}
		if(newPwd.val().length<6){
			loginTip("密码必须大于等于6位");
			return false;
		}
		if (oldPwd.val() == newPwd.val()) {
			loginTip("新旧密码不能相同，请再次输入！");
			return false;
		}
		if(digestPatten.test(newPwd.val())){
			loginTip("密码不能全为数字！");
			return false;
		}
		loginTip('')
		$.ajax({
			url : "/userInfo/changePwd",
			type : "post",
			async : false,
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			data : { "oldPwd":  oldPwd.val(), "reNewPwd": reNewPwd.val() },
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert("服务器出现异常");
			},
			success : function(data) {
				if (data.msg == '0') {
					$('#step1').hide();
					$('#step3').show();
				} else if (data.msg == '1'){
					loginTip("原密码错误!");
				} else {
					loginTip("操作失败!");
				}
			}
		});
		
	}
	//设置登陆密码
	function setPWD(){
		loginTip('')
		var loginPWD = $("#loginPWD");
		var reLoginPWD = $("#reLoginPWD");
		var digestPatten = /^[0-9]+$/;
		if (!loginPWD.val().length) {
			loginTip("请输入密码！");
			return false;
		}
		if (!reLoginPWD.val().length) {
			loginTip("请再次输入密码！");
			return false;
		}
		if (loginPWD.val() != reLoginPWD.val()) {
			loginTip("两次输入的密码不一样，请再次输入！");
			return false;
		}
		if(loginPWD.val().length<6 || reLoginPWD.val().length<6 ){
			loginTip("密码必须大于等于6位");
			return false;
		}
		if(digestPatten.test(loginPWD.val())){
			loginTip("密码不能全为数字！");
			return false;
		}
		$.ajax({
			url : "/userInfo/setPWD",
			type : "post",
			async : false,
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			data : 'loginPWD='+loginPWD.val(),
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert("服务器出现异常");
			},
			success : function(data) {
				if (data.msg == '0') {
					$('#step1').hide();
					$('#step2').show();
				} else{
					loginTip('密码设置失败!');
				}
			}
		});
	}
	//支付密码修改
	var payTiper = $("#payTiper");
	$("#modifyPayPWDBtn").click(function() {
		changePayPwd();
	});
	$("#setPayPWDBtn").click(function() {
		setPayPWD();
	});
	//修改支付密码
	function changePayPwd() {
		payTip('');
		var oldPayPwd = $("#oldPayPwd");
		var newPayPwd = $("#newPayPwd");
		var reNewPayPwd = $("#reNewPayPwd");
		var digestPatten = /^[0-9]+$/;
		if (!oldPayPwd.val().length) {
			payTip("请输入旧密码！");
			return false;
		}
		if (!newPayPwd.val().length) {
			payTip("请输入新密码！");
			return false;
		}
		if (newPayPwd.val() != reNewPayPwd.val()) {
			payTip("两次输入的密码不一样，请再次输入！");
			return false;
		}
		if(newPayPwd.val().length<6){
			payTip("密码长度必须大于等于6位");
			return false;
		}
		if(newPayPwd.val().length>20){
			payTip("密码长度必须小于等于20位");
			return false;
		}
		if (!reNewPayPwd.val().length) {
			payTip("请再次输入新密码！");
			return false;
		}
		if (oldPayPwd.val() == newPayPwd.val()) {
			payTip("新旧密码不能相同，请再次输入！");
			return false;
		}
		if(digestPatten.test(newPayPwd.val())){
			payTip("密码必须是6-20位英文字母、数字或符号,不能为纯数字！");
			return false;
		}
		payTip('');
		$.ajax({
			url : "/userInfo/changePayPwd",
			type : "post",
			async : false,
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			data : "oldPayPwd="+ oldPayPwd.val() + "&newPayPwd="+ newPayPwd.val() ,
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				payTiper("服务器出现异常");
			},
			success : function(data) {
				if (data.msg == '0') {
					$('#payStep1').hide();
					$('#payStep3').show();
				} else if (data.msg == '1'){
					payTip("原密码错误!");
				} else {
					payTip("操作失败!");
				}
			}
		});
		
	}
	//设置支付密码
	function setPayPWD(){
		payTip('');
		var payPWD = $("#payPWD");
		var rePayPWD = $("#rePayPWD");
		var digestPatten = /^[0-9]+$/;
		if (!payPWD.val().length) {
			payTip("请输入密码！");
			return false;
		}
		if (!rePayPWD.val().length) {
			payTip("请再次输入密码！");
			return false;
		}
		if (payPWD.val() != rePayPWD.val()) {
			payTip("两次输入的密码不一样，请再次输入！");
			return false;
		}
		if(payPWD.val().length<6){
			payTip("密码长度必须大于等于6位");
			return false;
		}
		if(payPWD.val().length>20){
			payTip("密码长度必须小于等于20位");
			return false;
		}
		if(digestPatten.test(newPayPwd.val())){
			payTip("密码必须是6-20位英文字母、数字或符号,不能为纯数字！");
			return false;
		}
		payTip('');
		$.ajax({
			url : "/userInfo/setPayPWD",
			type : "post",
			async : false,
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			data : 'payPWD='+payPWD.val(),
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				payTiper("服务器出现异常");
			},
			success : function(data) {
				if (data.msg == '0') {
					$('#payStep1').hide();
					$('#payStep2').show();
				} else{
					payTiper('支付密码设置失败!');
				}
			}
		});
	}
	
	//找回重置登陆/支付密码
	function resetPwd(tp){
		var pwd = '';
		var yzm = '';
		if(tp=='5'){
			loginFindTip('')
			var newFindPwd = $("#newFindPwd");
			var reNewFindPwd = $("#reNewFindPwd");
			var loginCode = $("#loginCode");
			var digestPatten = /^[0-9]+$/;
			if (!loginCode.val().length) {
				loginFindTip("请输入验证码！");
				return false;
			}
			if (!newFindPwd.val().length) {
				loginFindTip("请输入新密码！");
				return false;
			}
			if (!reNewFindPwd.val().length) {
				loginFindTip("请再次输入新密码！");
				return false;
			}
			if (newFindPwd.val() != reNewFindPwd.val()) {
				loginFindTip("两次输入的密码不一样，请再次输入！");
				return false;
			}
			if(newFindPwd.val().length<6){
				loginFindTip("密码必须大于等于6位");
				return false;
			}
			if(digestPatten.test(newFindPwd.val())){
				loginFindTip("密码不能全为数字！");
				return false;
			}
			pwd = newFindPwd.val();
			yzm = loginCode.val();
		} else if (tp=='9'){
			payFindTip('');
			var payCode = $("#payCode");
			var newFindPayPwd = $("#newFindPayPwd");
			var reNewFindPayPwd = $("#reNewFindPayPwd");
			var digestPatten = /^[0-9]+$/;
			if (!payCode.val().length) {
				payFindTip("请输入验证码！");
				return false;
			}
			if (!newFindPayPwd.val().length) {
				payFindTip("请输入密码！");
				return false;
			}
			if (!reNewFindPayPwd.val().length) {
				payFindTip("请再次输入密码！");
				return false;
			}
			if (newFindPayPwd.val() != reNewFindPayPwd.val()) {
				payFindTip("两次输入的密码不一样，请再次输入！");
				return false;
			}
			if(newFindPayPwd.val().length<6){
				payFindTip("密码长度必须大于等于6位");
				return false;
			}
			if(newFindPayPwd.val().length>20){
				payFindTip("密码长度必须小于等于20位");
				return false;
			}
			if(digestPatten.test(newFindPayPwd.val())){
				payFindTip("密码必须是6-20位英文字母、数字或符号,不能为纯数字！");
				return false;
			}
			pwd = newFindPayPwd.val();
			yzm = payCode.val();
		} else {
			return;
		}
		$.ajax({
			url : "/userInfo/resetPwd",
			type : "post",
			async : false,
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			data : 'verifyCodeType='+tp+'&pwd='+pwd+'&yzm='+yzm,
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert("服务器出现异常");
			},
			success : function(data) {
				if (data.state == 1) {
					if(tp=='5'){
						$('#step5').hide();
						$('#step6').show();
					} else if(tp=='9'){
						$('#payStep4').hide();
						$('#payStep5').show();
					}
				} else {
					alert(data.msg);
				}
			}
		});
	}
	
	/* 登陆密码修改提示方法 */
	function loginTip(msg) {
		loginTiper.text(msg);
	}
	/* 支付密码修改提示方法 */
	function payTip(msg) {
		payTiper.text(msg);
	}
	var loginFindTiper = $("#loginFindTiper");
	var payFindTiper = $("#payFindTiper");
	/* 登陆密码找回提示方法 */
	function loginFindTip(msg) {
		loginFindTiper.text(msg);
	}
	/* 支付密码找回提示方法 */
	function payFindTip(msg) {
		payFindTiper.text(msg);
	}
});
//获取验证码
function getVerifyCode(tp){
	$.ajax({
		url : "/userInfo/sendVerifyCode",
		type : "post",
		async : false,
		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		data : 'verifyCodeType='+tp,
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			alert("服务器出现异常");
		},
		success : function(data) {
			alert(data.msg);
		}
	});
}
//忘记登陆密码
function forgetLoginPwd(){
	$('#step1').hide();
	if (bindPhone == 1) {//已绑定手机
		$('#step5').show();
	} else {//未绑定手机
		$('#step4').show();
	}
}

//忘记支付密码
function forgetPayPwd(){
	$('#payStep1').hide();
	if (bindPhone == 1) {//已绑定手机
		$('#payStep4').show();
	} else {//未绑定手机
		$('#payStep6').show();
	}
}

function returnSetting(){
	location.href = "/userInfo/settings";
}

function focusTo(uid){
	$("#jbz2").css('display','block');
	$("body,html").animate({
	      scrollTop: $("#"+uid).offset().top 
    }, 0);
}