<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>第三方认证</title>
<link rel="stylesheet" type="text/css" href="/statics/css/ysdd-reg.css"/>
<script type="text/javascript" src="http://base1.okwei.com/js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="/statics/js/common.js"></script>
<script type="text/javascript" src="/statics/js/loginbind.js"></script>
<script type="text/javascript" src="/statics/js/xiangqing.js"></script>
</head>
<body style="background:#f2f2f2;">
<div class="top">
	<div class="w-990">
	<div class="topLogo"><em><img src="http://base.okimgs.com/images/xh_logo.gif"></em><span>用户登录</span></div>
    </div>
</div>
<div class="w-990">
    <div class="mzh_dljm">
        <div style="padding: 60px 0 100px 330px;    overflow: hidden;    background: #fff; ">
            <div class="mzh_dljm_right_dl">
                <ul>
                    <li class="mzh_dljm_left_1" style="margin-bottom: 27px;display: inline; text-align: left;">微店账号登录</li>
                    <li id="litip" style="color:#f10;margin:-10px 0 5px; display:none;" >
                    <img src="/statics/images/m_jingzhi.png" />&nbsp;&nbsp;<span>请输入正确的微店号或手机号</span></li>
                    <li><input class="mzh_dljm_right_dl_2" id="txtWeiID" type="text" placeholder="微店号/手机"></li>
                    <li><input class="mzh_dljm_right_dl_2" id="txtPwd" type="password" placeholder="密码"></li>
                    <li id="liCode" style="display:none;">
                        <input id="txtCode" type="text" class="mzh_dljm_right_dl_2" style="width: 165px; margin-right: 20px; line-height: 38px; color: rgb(153, 153, 153);" placeholder="验证码">
                        <span style="float:left;width:100px;height:40px;"><img id="imgcode" width="100px" height="40px" src="/getVImageCode" alt="验证码" title="看不清楚?点我换一张!"></span>
                    </li> 
                    <li><span id="btnLogin" class="mzh_dljm_right_dl_3">登&nbsp;&nbsp;&nbsp;录</span></li> 
                </ul>
            </div>
        </div>
    </div>
</div>
<!-- 底部 -->

<div class="mzh-dingwei">
    <div class="mzh-dingwei_1"><span style="">深圳市云商微店网络技术有限公司版权所有   经营许可证：粤ICP备10203293号-3   -   增值电信业务经营许可证：粤B2-20130803</span></div>
</div>
</body>
</html>