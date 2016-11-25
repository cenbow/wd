<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>安全设置</title>
<script type="text/javascript" src="/statics/js/userinfo/settings.js"></script>
<script type="text/javascript" src="/statics/js/userinfo/password.js"></script>
</head>
<body class="bg_f3">
	<div class="fl conter_right bg_white bor_si">
		<!-- 选项卡 -->
		<div class="gygl_xxk_t f16 ndfxs_1-2_border">
			<div class="gygl_xxk_yes"  style="color: #000;">
				安全设置<span style="width: 64px;"></span>
			</div>
		</div>
		<div class="Binding f14 fl">
			<!-- 登录密码 -->
			<ul>
				<c:choose>
					<c:when test="${setting.loginPwd ==1 }">
						<!-- 已经设置登陆密码 -->
						<li><img src="../statics/images/space.gif" alt="" class="c_ico_5" /><span>登录密码</span></li>
						<li><span class="ft_c9">已设置</span></li>
						<li class="tr"><span><a href="javascript:;" onclick="win_user('other_3')">修改</a></span></li>
					</c:when>
					<c:otherwise>
						<!-- 未设置登陆密码 -->
						<li><img src="../statics/images/space.gif" alt="" class="c_ico_5a" /><span>登录密码</span></li>
						<li><span class="ft_red">未设置</span></li>
						<li class="tr"><span><a href="javascript:;" onclick="win_user('other_3')">设置</a></span></li>
					</c:otherwise>
				</c:choose>
			</ul>
			<div class="jbzl_dl f14 bor_si" id="other_3" style="display: none;">
				<jsp:include page="common/loginPassword.jsp">
					<jsp:param value="" name="" />
				</jsp:include>
			</div>

			<!-- 支付密码 -->
			<ul class="bg_F0F0F0">
				<c:choose>
					<c:when test="${setting.payPwd ==1 }">
						<!-- 已设置支付密码 -->
						<li><img src="../statics/images/space.gif" alt="" class="c_ico_7" /><span>支付密码 </span></li>
						<li><span class="ft_c9">已设置</span></li>
						<li class="tr"><span><a href="javascript:;" onclick="win_user('bor_si')">修改</a></span></li>
					</c:when>
					<c:otherwise>
						<!-- 未设置支付密码 -->
						<li><img src="../statics/images/space.gif" alt="" class="c_ico_7a" /><span>支付密码 </span></li>
						<li><span class="ft_red">未设置</span></li>
						<li class="tr"><span><a href="javascript:;" onclick="win_user('bor_si')">设置</a></span></li>
					</c:otherwise>
				</c:choose>
			</ul>
			<div class="jbzl_dl f14 bor_si" id="bor_si" style="display: none">
				<jsp:include page="common/payPassword.jsp">
					<jsp:param value="" name="" />
				</jsp:include>
			</div>

			<!-- 绑定手机 -->
			<ul id="ul_bindPhone">
				<c:choose>
					<c:when test="${setting.bindPhone == 1 }">
						<!-- 绑定 -->
						<li><img src="../statics/images/space.gif" alt="" class="c_ico_2" /> <span>手机（${setting.phoneStr }） </span></li>
						<li><span class="ft_c9">已绑定</span></li>
						<li class="tr"><span> <a href="javascript:;" onclick="win_user('jbz2')">更换</a>
						</span></li>
					</c:when>
					<c:otherwise>
						<!-- 未绑定 -->
						<li><img src="../statics/images/space.gif" alt="" class="c_ico_2a" /><span>手机 </span></li>
						<li><span class="ft_red">未绑定</span></li>
						<li class="tr"><span> <a href="javascript:;" onclick="win_user('jbz2')">绑定</a>
						</span></li>
					</c:otherwise>
				</c:choose>
			</ul>
			<div class="jbzl_dl f14 bor_si" id="jbz2" style="display: none">
				<jsp:include page="common/bindPhone.jsp">
					<jsp:param value="${setting }" name="setting" />
				</jsp:include>
			</div>


			<%-- <!-- 绑定邮箱 -->
			<ul class="bg_F0F0F0">
				<c:choose>
					<c:when test="${setting.bindMail == 1 }">
						<!-- 已经绑定邮箱 -->
						<li><img src="../statics/images/space.gif" alt="" class="c_ico_7" /><span>邮箱（${setting.mailStr }） </span></li>
						<li><span class="ft_c9">已绑定</span></li>
						<li class="tr"><span><a href="#" onclick="win_user('bor_wu')">更换</a></span></li>
					</c:when>
					<c:otherwise>
						<!-- 未绑定邮箱 -->
						<li><img src="../statics/images/space.gif" alt="" class="c_ico_7a" /><span>邮箱 </span></li>
						<li><span class="ft_red">未绑定</span></li>
						<li class="tr"><span><a href="#" onclick="win_user('bor_wu')">添加</a></span></li>
					</c:otherwise>
				</c:choose>
			</ul>
			<div class="jbzl_dl f14 bor_si" id="bor_wu" style="display: none;">
				<jsp:include page="common/bindMail.jsp">
					<jsp:param value="" name="" />
				</jsp:include>
			</div> --%>

			<!-- 实名认证 -->
			<ul>
				<c:choose>
					<c:when test="${setting.realVerify == 1 }">
						<!-- 已经验证实名 -->
						<li><img src="../statics/images/space.gif" alt="" class="c_ico_3" /><span>实名认证（${setting.realName }） </span></li>
						<li><span class="ft_c9">已认证</span></li>
						<li class="tr"><span><a href="javascript:;" onclick="win_user('car_user')">查看</a></span></li>
					</c:when>
					<c:otherwise>
						<!-- 未验证实名 -->
						<li><img src="../statics/images/space.gif" alt="" class="c_ico_3a" /><span>实名认证</span></li>
						<li><span class="ft_red">未认证</span></li>
						<li class="tr"><span><a href="javascript:;" onclick="win_user('car_user')">认证</a></span></li>
					</c:otherwise>
				</c:choose>
			</ul>
			<div class="jbzl_dl f14 bor_si" id="car_user" style="display: none">
				<jsp:include page="common/realVerify.jsp">
					<jsp:param value="" name="" />
				</jsp:include>

			</div>
		</div>
	</div>
</body>
</html>