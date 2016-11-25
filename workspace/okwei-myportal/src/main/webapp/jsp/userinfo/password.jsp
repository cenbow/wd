<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="pg" uri="http://www.okwei.com/pagination"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>我的分销商</title>
</head>

<body class="bg_f3">
	<div class="fl conter_right bg_white bor_si">
		<!-- 选项卡 -->
		<div class="gygl_xxk_t f16 ndfxs_1-2_border">
			<div style="color: #000;" name="mzh_xxk" class="gygl_xxk_yes">
				更改密码<span style="width: 64px;"></span>
			</div>
		</div>
		<div class="jbzl">
			<label class="warning" id="loginTiper"></label>
			<dl class="jbzl_dl f14">
				<dd>旧密码：</dd>
				<dt>
					<input id="oldPwd" type="password" class="fl btn_h42 w250"><a
						class="wjmm_3_23 lh_40 ml_10 ft_lan" href="javascript:;">忘记密码?</a>
				</dt>
			</dl>
			<dl class="jbzl_dl f14">
				<dd>新密码：</dd>
				<dt>
					<input id="newPwd" type="password" class="fl btn_h42 w250">
				</dt>
			</dl>
			<dl class="jbzl_dl f14">
				<dd>确认新密码：</dd>
				<dt>
					<input id="reNewPwd" type="password" class="fl btn_h42 w250">
				</dt>
			</dl>
			<dl class="jbzl_dl f14">
				<dd></dd>
				<dt>
					<div id="submitBtn" class="jbzl_dl_bc">立即修改</div>
				</dt>
			</dl>
		</div>
	</div>

	<script type="text/javascript">
		$(document).ready(function() {
			$("#submitBtn").click(function() {
				changePwd();
			});
			$("#resetBtn").click(function() {
				clearVal();
			});
			
			function clearVal() {
				$("#oldPwd").val("");
				$("#newPwd").val("");
				$("#reNewPwd").val("");
			}
			
			function changePwd() {
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
				if(newPwd.val().length<6){
					loginTip("密码必须大于等于6位");
					return false;
				}
				if (!reNewPwd.val().length) {
					loginTip("请再次输入新密码！");
					return false;
				}
				if (newPwd.val() != reNewPwd.val()) {
					loginTip("2次输入的密码不一样，请再次输入！");
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
				$.post(window.basePath+"userInfo/changePwd/",{ "oldPwd":  oldPwd.val(), "reNewPwd": reNewPwd.val() }, function(data){
					alert(data);
					if (!data.error) {
						alert("密码已更换,请重新登录！");
						/* location.href = "login.html"; */
					}else{
						alert("输入的旧密码错误!");
					}
				},"json");
				
			}
			
			var loginTiper = $("#loginTiper");
			/* 登录提示方法 */
			function loginTip(msg, cssClass) {
				cssClass = cssClass ? "warning" : cssClass;
				if (loginTiper.length) {
					loginTiper.attr("class", "").addClass(cssClass).text(msg);
				} else {
					loginTiper = $("<label id='loginTiper' class='" + cssClass + "'>" + msg + "</label>");
				}
			}
			
		});

		
		
		
		
	</script>

</body>