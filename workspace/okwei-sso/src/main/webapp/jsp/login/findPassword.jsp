<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>微店网-找回密码</title>
<link rel="stylesheet" type="text/css" href="/statics/css/ysdd-reg.css?v=1" />
<link rel="stylesheet" type="text/css" href="/statics/css/glbdy.css?v=1" />
<link rel="stylesheet" type="text/css" href="/statics/css/index.css?v=1" />
<link rel="stylesheet" type="text/css" href="/statics/css/mzh_ibs.css?v=1" />
<script type="text/javascript" src="http://base1.okwei.com/js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="/statics/js/findPassword.js?v=1"></script>

</head>
<body style="background: #f2f2f2;">
	<div class="top">
		<div class="w-990">
			<div class="topLogo">
				<em><img src="http://base.okimgs.com/images/xh_logo.gif" /></em><span>密码找回</span>
			</div>
		</div>
	</div>
	<div class="w-990">
		<div class="mzh_dljm" style="height: 350px;">
			<!-- 步骤 -->
			<div class="ddxq_gg_flow" style="width: 850px;">
				<div class="ddxq_gg_flow_red_4_1_yes">
					<div class="ddxq_gg_flow_red_4_1">
						<b>1</b> <span>请输入手机号</span>
					</div>
					<div class="ddxq_gg_flow_red_4_2">
						<b>2</b> <span>验证身份</span>
					</div>
					<div class="ddxq_gg_flow_red_4_3">
						<b>3</b> <span>重设密码</span>
					</div>
					<div class="ddxq_gg_flow_red_4_4">
						<b>4</b> <span>完成</span>
					</div>
				</div>
			</div>
			<!-- 步骤1 -->
			<div class="fl" id="step1" style="margin-left: 200px;">
				<ul id="mzh_srsjh">
					<li><span class="fl f14 fw_b line_36 mr_20 w104 tr">手机号：</span> <input id="phone" maxlength="11" type="text" placeholder="请输入注册手机号码" class="input_05_28 fl"> <img src="/statics/images/c-ico1.png" id="phoneico" width="28" class="fl ml_10 mt_5" style="display: none;" /> <span id="phoneTxt" class="fl color_red f14 line_36 ml_5" style="display: none;">该手机没有绑定微店号，</span> <a href="regist.aspx" style="float: left; color: #3c5fd1; line-height: 38px; display: none;" id="mszc">马上注册</a></li>
					<li class="mt_10"><span class="fl f14 fw_b line_36 mr_20 w104 tr" style="height: 30px;"></span> <span href="#" class="fl ">未绑定手机号？</span> <span class="color_36c fl ml_5">请联系客服</span> <span class="fl color_999 ml_20">电话：4008-933-980</span></li>
					<li><span class="fl f14 fw_b line_36 mr_20 w104 tr" style="height: 30px;"></span> <a href="javascript:void(0)" onclick="step_one()" class="mzh_xiayibu">下一步</a></li>
				</ul>
			</div>
			<!-- 步骤2 -->
			<div class="fl" id="step2" style="margin-left: 200px; display: none;">
				<ul id="mzh_srsjh">
					<li class="mb_30"><span class="fl f14 fw_b ml_30">您要找回的微店号为：</span> <span class="fl f14 fw_b" id="weiid"></span> <span class="fl f14 fw_b ml_48">绑定手机号为：</span> <span class="fl f14 fw_b" id="bindPhone"></span></li>
					<li id="imgcodeli"><span class="fl f14 fw_b line_36 mr_20 w104 tr">验证码：</span> 
					<input type="text" placeholder="输入验证码" maxlength="4" class="input_05_28 fl" style="width: 150px;" id="imgcodetext" /> <img id="imgcode" width="100px" height="36px" src="/getVImageCode" alt="验证码" class="fl ml_20 cup" title="看不清楚?点我换一张!"> 
					<img src="/statics/images/c-ico1.png" id="step2ico" style="display: none" width="28" class="fl ml_10 mt_5"> 
					<span class="fl color_red f14 line_36 ml_5" id="step2tip" style="display: none"></span></li>
					<li id="phoneli" style="display: none;">
					<span class="fl f14 fw_b line_36 mr_20 w104 tr">手机验证码：</span> 
					<input type="text" placeholder="输入验证码" id="phonecode" class="input_05_28 fl" style="width: 150px;" /> 
					<span id="phoneyzm"  class="fl bor_si tc color_fff fs_08_25 ml_20" style="width: 110px;">获取验证码</span>
					<span class="fl color_red f14 line_36 ml_5" id="phone2tip" style="display: none"></span>
					</li>
					<li><span class="fl f14 fw_b line_36 mr_20 w104 tr" style="height: 30px;"></span> <a href="javascript:void(0)" onclick="two()" class="mzh_xiayibu mt_20 mb_30">下一步</a></li>
				</ul>
			</div>
			<!-- 步骤3 -->
			<div class="fl" id="step3"  style="margin-left: 200px;display: none;">
            <ul id="mzh_srsjh">
                <li>
                    <span class="fl f14 fw_b line_36 mr_20 w130 tr">新密码：</span>
                    <input type="password" maxlength="25" placeholder="输入密码" class="input_05_28 fl" id="newpass1" />
                    <img src="/statics/images/c-ico1.png" id="step3tip1img"  width="28" class="fl ml_10 mt_5" style="display: none" />
                    <span class="fl color_red f14 line_36 ml_5"  id="step3tip1" style="display: none"></span>
                </li>
                <li class="mt_20">
                    <span class="fl f14 fw_b line_36 mr_20 w130 tr">再次输入新密码：</span>
                    <input type="password" maxlength="25" placeholder="确认密码" class="input_05_28 fl"  id="newpass2" />
                    <img src="/statics/images/c-ico1.png" id="step3tip2img" width="28" class="fl ml_10 mt_5" style="display: none" />
                    <span class="fl color_red f14 line_36 ml_5"  id="step3tip2" style="display: none"></span>
                </li>
                <li>
                    <span class="fl f14 fw_b line_36 mr_20 w130 tr" style="height: 30px;"></span>
                    <a href="javascript:void(0)" onclick="step_three()" class="mzh_xiayibu mt_20 mb_30">下一步</a>
                </li>
            </ul>
        </div>
			<!-- 步骤4 -->
        <div class="fl mzh_width_100" id="step4"  style="display: none;">
            <ul id="mzh_srsjh">
                <li class="tc">
                    <h1>恭喜您！密码修改成功！</h1>
                </li>
                <li>
                    <a href="/" class="mzh_xiayibu" style="margin:30px 0 50px 450px;">完成</a>
                </li>
            </ul>
        </div>
		</div>
	</div>
	<input id="guid" value="" type="hidden" />
	<input id="imgcodehide" value="0" type="hidden" />

	<!-- 底部 -->

	<div class="mzh-dingwei">
		<div class="mzh-dingwei_1">
			<span style="">深圳市云商微店网络技术有限公司版权所有 经营许可证：粤ICP备10203293号-3 - 增值电信业务经营许可证：粤B2-20130803</span>
		</div>
	</div>
</body>

</html>