<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- 设置支付密码 -->
<div class="mzh_100 fl" id="payStep1">
<label id="payTiper" style="azimuth: center;color: red;"></label>
	<c:choose>
		<c:when test="${setting.payPwd ==1 }">
			<!-- 已设置支付密码 -->
			<div class="gygl_xxk_t f16 ndfxs_1-2_border">
				<div class="fl line_42">修改支付密码</div>
			</div>
			<dl class="fl mzh_100 f14 mt_20">
				<dd>旧支付密码：</dd>
				<dt>
					<input type="password" class="fl btn_h42 w250 mr_20" id="oldPayPwd"><a class="ft_lan" href="javascript:forgetPayPwd();">忘记密码？</a>
				</dt>
			</dl>
			<dl class="fl mzh_100 f14 mt_20">
				<dd>新支付密码：</dd>
				<dt>
					<input type="password" class="fl btn_h42 w250 mr_20" id="newPayPwd">
				</dt>
			</dl>
			<dl class="fl mzh_100 f14">
				<dd></dd>
				<dt>6-20位英文字母、数字或符号，不能是纯数字</dt>
			</dl>
			<dl class="fl mzh_100 f14 mt_20">
				<dd>确认新支付密码：</dd>
				<dt>
					<input type="password" class="fl btn_h42 w250 mr_20" id="reNewPayPwd">
				</dt>
			</dl>
			<dl class="fl mzh_100 f14 mt_20">
				<dd></dd>
				<dt>
					<input type="submit" value="确认修改" class="jbzl_dl_bc" name="button" id="modifyPayPWDBtn">
				</dt>
			</dl>
		</c:when>
		<c:otherwise>
			<!-- 未设置支付密码 -->
			<div class="gygl_xxk_t f16 ndfxs_1-2_border">
				<div class="fl line_42">设置支付密码</div>
			</div>
			<dl class="fl mzh_100 f14 mt_20">
				<dd>支付密码：</dd>
				<dt>
					<input type="password" class="fl btn_h42 w250 mr_20" id="payPWD">
				</dt>
			</dl>
			<dl class="fl mzh_100 f14">
				<dd></dd>
				<dt>6-20位英文字母、数字或符号，不能是纯数字</dt>
			</dl>
			<dl class="fl mzh_100 f14 mt_20">
				<dd>确认支付密码：</dd>
				<dt>
					<input type="password" class="fl btn_h42 w250 mr_20" id="rePayPWD">
				</dt>
			</dl>
			<dl class="fl mzh_100 f14 mt_20">
				<dd></dd>
				<dt>
					<input type="submit" value="确认" class="jbzl_dl_bc" name="button" id="setPayPWDBtn">
				</dt>
			</dl>
		</c:otherwise>
	</c:choose>
	
</div>

<!-- 设置成功，请牢记支付密码 -->
<div class="mzh_100 fl" style="display: none;" id="payStep2">
	<div class="gygl_xxk_t f16 ndfxs_1-2_border">
		<div class="fl line_42">设置成功，请牢记支付密码</div>
	</div>
	<dl class="fl mzh_100 f14 mt_20">
		<input type="submit" value="返回" class="btn_wear42_pre" name="button" onclick="returnSetting();">
	</dl>
</div>

<!-- 修改成功，请牢记支付密码 -->
<div class="mzh_100 fl" style="display: none;" id="payStep3">
	<div class="gygl_xxk_t f16 ndfxs_1-2_border">
		<div class="fl line_42">修改成功，请牢记支付密码</div>
	</div>
	<dl class="fl mzh_100 f14 mt_20">
		<input type="submit" value="返回" class="btn_wear42_pre" name="button" onclick="returnSetting();">
	</dl>
</div>

<!-- 找回支付密码 -->
<div class="mzh_100 fl" style="display: none;" id="payStep4">
<label id="payFindTiper" style="azimuth: center;color: red;"></label>
	<div class="gygl_xxk_t f16 ndfxs_1-2_border">
		<div class="fl line_42">找回支付密码</div>
	</div>
	<dl class="fl mzh_100 f14 mt_20">
		<dd>已绑定手机号：</dd>
		<dt>
			${setting.phoneStr} <input type="submit" value="点击获取验证码" class="btn_wear42_pre" name="button" onclick="getVerifyCode(9)">
		</dt>
	</dl>
	<dl class="fl mzh_100 f14 mt_20">
		<dd>验证码：</dd>
		<dt>
			<input type="text" class="fl btn_h42 w250 mr_20" id="payCode">
		</dt>
	</dl>
	<dl class="fl mzh_100 f14 mt_20">
		<dd>新支付密码：</dd>
		<dt>
			<input type="password" class="fl btn_h42 w250 mr_20" id="newFindPayPwd">
		</dt>
	</dl>
	<dl class="fl mzh_100 f14">
		<dd></dd>
		<dt>6-20位英文字母、数字或符号，不能是纯数字</dt>
	</dl>
	<dl class="fl mzh_100 f14 mt_20">
		<dd>确认新支付密码：</dd>
		<dt>
			<input type="password" class="fl btn_h42 w250 mr_20" id="reNewFindPayPwd">
		</dt>
	</dl>
	<dl class="fl mzh_100 f14 mt_20">
		<dd></dd>
		<dt>
			<input type="submit" value="确认修改" class="jbzl_dl_bc" name="button"  id="modifyNewFindPayPwdBtn">
		</dt>
	</dl>
</div>


<!-- 重置成功，请牢记支付密码 -->
<div class="mzh_100 fl" style="display: none;" id="payStep5">
	<div class="gygl_xxk_t f16 ndfxs_1-2_border">
		<div class="fl line_42">重置成功，请牢记支付密码</div>
	</div>
	<dl class="fl mzh_100 f14 mt_20">
		<input type="submit" value="返回" class="btn_wear42_pre" name="button" onclick="returnSetting();">
	</dl>
</div>

<!-- 找回支付密码 -->
<div class="mzh_100 fl" style="display: none;"  id="payStep6">
	<div class="gygl_xxk_t f16 ndfxs_1-2_border">
		<div class="fl line_42">找回支付密码</div>
	</div>
	<div class="fl f14 mzh_100 mt_20 ft_c6">您还没有绑定手机，暂不能找回密码</div>
	<div class="fl f14 mzh_100 ft_c6">请先绑定手机</div>
	<dl class="fl mzh_100 f14">
		<input type="submit" value="绑定手机" class="btn_wear42_pre fl" name="button" onclick="focusTo('ul_bindPhone');">
		<a href="javascript:void(0)" class="fl line_42 ml_10 rztd_cx_f10" onclick="focusTo('ul_bindPhone');">跳转至绑定手机页面</a>
	</dl>
</div>
