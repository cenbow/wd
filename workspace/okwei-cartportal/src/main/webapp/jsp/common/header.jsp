<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>	
	<%@ page import="java.util.ResourceBundle"%>
<%
	String cartdomain = ResourceBundle.getBundle("domain").getString(
			"cartdomain");
	String mydomain = ResourceBundle.getBundle("domain").getString(
			"mydomain");
	String portdomain = ResourceBundle.getBundle("domain").getString(
			"portdomain");
	String companydomain = ResourceBundle.getBundle("domain")
			.getString("companydomain");
	String joindomain = ResourceBundle.getBundle("domain").getString(
			"joindomain");
	String okweidomain = ResourceBundle.getBundle("domain").getString(
			"okweidomain");
%>
<div class="w bg-w">
	<!-- 顶部 导航 -->
	<div class="line-b">
		<div class="mar1200 lin40 f12 h40 heanew ">
			<p class="fl c6 boximg">
				<a href="javascript:addfavorite();"><img src="http://base2.okimgs.com/images/Weidian2_0/xh_sc.gif" />收藏微店网</a>
			</p>
			<div class="fl pr pl5 pt10 pr5 pb5" id="Yijian">
				<a href="javascript:;" class="pr"><img src="http://base2.okimgs.com/images/xh_tg.gif"></a>
				<div class="promotion_txt none" id="Fonts">
					<textarea id="zxxTestArea">微店网打通微商城，让${user.userCount }万微店主为您推销产品。网络营销不在乎平台新老，有流量、投入产出成正比，就上！<%=joindomain %>/supplier/apply?w=${user.tgWeiID }</textarea>
					<div class="promotion_button">
						<p class="fl ">
							<span id="forLoadSwf"></span> 
						</p>
						<p class="fr mr10">粘贴到QQ群、QQ，或者通过邮件，均可推广！</p>
					</div>
				</div>
			</div>
			<script src="/statics/js/copy/swfobject.js" type="text/javascript"></script>
			<script type="text/javascript">
				var copyCon = document.getElementById("zxxTestArea").value;
				var flashvars = {
					content : encodeURIComponent(copyCon),
					uri : '/statics/js/copy/flash_copy_btn.png'
				};
				var params = {
					wmode : "transparent",
					allowScriptAccess : "always"
				};
				swfobject.embedSWF("/statics/js/copy/clipboard.swf", "forLoadSwf", "64", "25", "9.0.0", null, flashvars, params);
				function copySuccess() {
					alert("复制成功！");
				}
			</script>

			<div class="fl pr pl5 pr5 addtimg" id="Ewming">
				<a target="_blank" href="http://app.okwei.com/" class="c6"><img class="img-pus1" src="http://base2.okimgs.com/images/xh-addtop008.png">&nbsp;下载微店APP，微店跟着手机走&nbsp; <img class="img-pus2" src="http://base2.okimgs.com/images/xh_gbxiangxia.gif"> </a>

				<div class="div-posabues none" id="Tewms">
					<div class="eweim-imgs">
						<img src="http://base2.okimgs.com/images/xh-addtop010.png">
					</div>
				</div>
			</div>
			<div class="fr ml10 wzdban pr" id="WzDao">
				<a href="javascript:;">网站导航</a>&nbsp;<img class="img-pus2" src="http://base2.okimgs.com/images/xh_gbxiangxia.gif">
				<div class="reabdh pa none" id="Dhang">
					<p>
						<a href="http://www.<%=okweidomain %>/list/list?w=${user.tgWeiID}">云端产品库</a>
					</p>
					<p>
						<a href="<%=companydomain%>/wholesale/index?w=${user.tgWeiID}">批发市场</a>
					</p>
					<p>
						<a href="<%=companydomain%>/yun/list?c=0&type=0&w=${user.tgWeiID}">工厂</a>
					</p>
					<p>
						<a href="http://www.<%=okweidomain %>/help/us">帮助中心</a>
					</p>
					<p>
						<a href="http://www.<%=okweidomain %>/help/trends">新闻资讯</a>
					</p>
					<p>
						<a href="<%=portdomain%>/RegistNew.aspx">免费开微店</a>
					</p>
				</div>
			</div>
			<c:choose>
				<c:when test="${user.weiID>0 }">
					<!-- 已登录 -->
					<p class="fr imgrs">
						<a href="http://www.${user.weiID}.<%=okweidomain %>">${user.weiName }</a> <a href="javascript:SignOut();" class="c6">退出</a> <a href="<%=mydomain%>">后台管理</a><a href="<%=cartdomain%>/shopCartMgt/list">购物车（<span class="tb cR" id="catCount">${user.cartCount }</span>） 
					</p>
				</c:when>
				<c:otherwise>
					<!-- 未登录 -->
					<p class="fr imgrs">
						<a href="javascript:goLogin();">请登录</a> <a href="<%=portdomain%>/RegistNew.aspx">免费开微店</a><a href="<%=cartdomain%>/shopCartMgt/list/1">购物车（<span class="tb cR">${user.cartCount }</span>） 
					</p>
				</c:otherwise>
			</c:choose>

			</a>
		</div>
	</div>
</div>	


