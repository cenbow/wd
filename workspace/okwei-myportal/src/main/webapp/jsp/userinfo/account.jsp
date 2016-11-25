<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>绑定账户</title>
<script type="text/javascript" src="/statics/js/userinfo/account.js"></script>
</head>

<body class="bg_f3">
	<div class="fl conter_right bg_white bor_si">
		<!-- 选项卡 -->
		<div class="gygl_xxk_t f16 ndfxs_1-2_border">
			<div class="gygl_xxk_yes" name="mzh_xxk" style="color: #000;">
				第三方账户<span style="width: 80px;"></span>
			</div>
		</div>
		<div class="Binding f14 fl" style="min-height: 170px;">
			<div class="fl" style="width: 25%;">
				<img src="/statics/pic/renren.jpg" width="70" height="70" class="fl mr_20" />
				<div class="fl">
					<p>QQ</p>
					<c:choose>
						<c:when test="${account.bindQQ }">
							<a href="javascript:void(0)" onclick="wins('解除QQ绑定','mzh_qq')">解除绑定 </a>
						</c:when>
						<c:otherwise>
							<a href="http://port.okwei.com/ThirdPartyBind.aspx?type=qq">点击绑定 </a>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
			<div class="fl" style="width: 25%;">
				<img src="/statics/pic/weixin.jpg" width="70" height="70" class="fl mr_20" />
				<div class="fl">
					<p>微信</p>
					<c:choose>
						<c:when test="${account.bindWX }">
							<a href="javascript:void(0)" onclick="wins('解除微信绑定','mzh_weixin')">解除绑定 </a>
						</c:when>
						<c:otherwise>
							<a href="http://port.okwei.com/ThirdPartyBind.aspx?type=wx">点击绑定 </a>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>
	</div>	
	<div id="mzh_weixin" style="display: none;">
		<span class="fl f18 mt_47">解除微信绑定将不能使用该微信号登录${userinfo.weiID }微店，确认解绑？</span>
	</div>
	<div id="mzh_qq" style="display: none;">
		<span class="fl f18 mt_47">解除QQ绑定将不能使用该QQ号登录${userinfo.weiID }微店，确认解绑？</span>
	</div>
  
</body>