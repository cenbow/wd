<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.ResourceBundle"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	String paydomain = ResourceBundle.getBundle("domain").getString("paydomain");
	String setTempdomain  = ResourceBundle.getBundle("domain").getString("setdomain");
	String walletdomain  = ResourceBundle.getBundle("domain").getString("walletdomain");
%>

<div class="conter_right fl  bg_white bor_si">
	<div class="wdqb_tu">
		<img src="/statics/images/1_10.jpg">
	</div>
	<div class="wdqb_cz">
		<span class="wdqb_cz_span mt_20">账户余额 
			<a href="javascript:;" ahref="<%=basePath%>walletMgt/index" act="loadPage" to="navTab" class="skip">查看</a>
		</span> 
		<a href="javascript:;" ahref="<%=basePath%>walletMgt/index" act="loadPage" to="navTab" class="ft_lan skip">
			<b class="wdqb_cz_b f18"><span id="balanceAmount"></span><font class="f12 ft_c6">元</font></b>
		</a>
		<a href="<%=paydomain %>/pay/recharge" target="_blank"><div class="wdqb_cz_cz mt_20">充值</div></a>
	</div>
	<div class="wdqb_cz">
		<span class="wdqb_cz_span_1 mt_30">未到账 <a href="javascript:;" ahref="<%=basePath%>walletMgt/changeSettleState/1" act="loadPage" to="navTab" class="skip">查看</a></span> 
		<a href="javascript:;" ahref="<%=basePath%>walletMgt/changeSettleState/1" act="loadPage" to="navTab" class="ft_lan skip">
			<span class="wdqb_cz_b_1 f14"><span id="accountNotAmount"></span><font class="f12 ft_c6">元</font></span>
		</a>
		<div id="txBtn" name="txBtn" class="wdqb_cz_tx mt_20">提现</div>
	</div>
	<div class="wdqb_cz">
		<span class="wdqb_cz_span_1 mt_30">提现中 <a href="javascript:;" ahref="<%=basePath%>walletMgt/getWithdrawRecord" act="loadPage" to="navTab" class="skip">查看</a></span> 
		<a href="javascript:;" ahref="<%=basePath%>walletMgt/getWithdrawRecord" act="loadPage" to="navTab" class="ft_lan skip">
			<span class="wdqb_cz_b_1 f14"><span id="accountIngAmount"></span><font class="f12 ft_c6">元</font></span> 
		</a>
	</div>
	<div class="wdqb_cz" id="divbond" style="display:none;">
		<span class="wdqb_cz_span_1 mt_30">保证金 <a href="javascript:;" ahref="<%=walletdomain%>/bondMgt/bondList" act="loadPage" to="navTab" class="skip">查看</a></span> 
		<a href="javascript:;" ahref="<%=walletdomain%>/bondMgt/bondList" act="loadPage" to="navTab" class="ft_lan skip">
			<span class="wdqb_cz_b_1 f14"><span id="bondAmout"></span><font class="f12 ft_c6">元</font></span> 
		</a>
	</div>
</div>
<!-- hidden区域 -->
<input type="hidden" id="txtMaxTxAmout"/>
<input type="hidden" id="txtIsFirstTx"/>
<input type="hidden" id="txtSetUrl" value="<%=setTempdomain %>" />
<!-- 提现  width:514px; -->
<div class="updata_tixian" style="display:none;" id="win_div_1">
  <div class="updata_tixian">
    <div class="update_tixian fl">
      <div class="moile_ones fl tr fw_b">提现金额：</div>
      <div class="moile_two fl">
        <input id="txtTxAmout" type="text" class="btn_h28 w82" />
        &nbsp;元<span class="ml_10 span_yans" id="tipTxAmout" style="display:none;">（需要手续费：10元）</span>
        <div class="tisfont_ju">每月首笔提现免手续费，手续费为转出金额的1%，每笔最低2元</div>
      </div>
    </div>
    <div class="update_tixian mt_20 fl">
      <div class="moile_ones fl tr fw_b">选择银行卡：</div>
      <div class="moile_two fl">
        <div class="radio_bank">
          <input type="radio" id="radis1" />
          &nbsp;
          <label for="radis1"><img src="/statics/pic/bank_icon_001.png" width="14" height="14" />中国农业银行  尾号：<span>4073</span></label>
          <span class="ml_30 ft_c9">限额49999次</span>
        </div>
        <div class="ahre_nes"><a href="/bankCardMgt/bankCard" >绑定/更换 银行卡</a></div>
      </div>
    </div>
    <div class="update_tixian mt_20 fl">
      <div class="moile_ones fl tr fw_b">已绑定的手机号：</div>
      <div class="moile_two fl">
        <span class="fl lh_30" id="txtMobile" >137*****150</span>
        <div class="btn_hui28_pre fl ml_20 shou" id="btnGetCode">点击获取验证码</div>
        <!-- <span class="fl lh_30 ft_red ml_20">增加已绑定的手机号提示</span> -->
      </div>
    </div>
    <div class="update_tixian mt_20 fl">
      <div class="moile_ones fl tr fw_b">验证码：</div>
      <div class="moile_two fl">
        <input type="password" id="txtCheckCode" class="btn_h28 w82 dis_b fl" /> 
      </div>
    </div>
  </div>
</div>

<script type="text/javascript" src="/statics/js/Wallet/walletTx.js"></script>


