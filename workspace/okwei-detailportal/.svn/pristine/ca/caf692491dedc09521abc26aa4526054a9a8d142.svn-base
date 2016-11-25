<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
			String joindomain = ResourceBundle.getBundle("domain")
				.getString("joindomain");
			String okweidomain = ResourceBundle.getBundle("domain")
				.getString("okweidomain");
%>
<input type="hidden" id="okweidomain" value="<%=okweidomain%>"/>
<div class="w">
	<!-- 顶部 导航 -->
	<div class="line-b">
		<div class="mar1200 lin40 f12 h40 heanew ">
			<p class="fl c6 boximg">
				<a href="javascript:addfavorite();"><img src="http://base2.okimgs.com/images/Weidian2_0/xh_sc.gif" />收藏微店网</a>
			</p>
			<div class="fl pr pl5 pt10 pr5 pb5" id="Yijian">
				<a href="javascript:;" class="pr"><img src="http://base2.okimgs.com/images/xh_tg.gif"></a>
				<div class="promotion_txt none" id="Fonts">
					<textarea id="zxxTestArea">微店网打通微商城，让${headinfo.userCount }万微店主为您推销产品。网络营销不在乎平台新老，有流量、投入产出成正比，就上！<%=joindomain %>/supplier/apply?w=${user.tgWeiID }</textarea>
					<div class="promotion_button">
						<p class="fl ">
							<span id="forLoadSwf"></span>
						</p>
						<p class="fr mr10">粘贴到QQ群、QQ，或者通过邮件，均可推广！</p>
					</div>
				</div>
			</div>
			<script src="http://base2.okwei.com/js/home/copy/swfobject.js" type="text/javascript"></script>
			<script type="text/javascript">
				var copyCon = document.getElementById("zxxTestArea").value;
				var flashvars = {
					content : encodeURIComponent(copyCon),
					uri : 'http://base1.okwei.com/js/home/copy/flash_copy_btn.png'
				};
				var params = {
					wmode : "transparent",
					allowScriptAccess : "always"
				};
				swfobject.embedSWF("http://base1.okwei.com/js/home/copy/clipboard.swf", "forLoadSwf", "64", "25", "9.0.0", null, flashvars, params);
				function copySuccess() {
					alert("复制成功！");
				}
			</script>

			<div class="fl pr pl5 pr5 addtimg" id="Ewming">
				<a target="_blank" href="http://app.okwei.com/" class="c6"><img class="img-pus1" src="http://base2.okimgs.com/images/xh-addtop008.png">&nbsp;下载微店APP，微店跟着手机走&nbsp; <img class="img-pus2" src="http://base2.okimgs.com/images/xh_gbxiangxia.gif"> </a>

				<div class="div-posabues none" id="Tewms">
					<div class="eweim-imgs">
						<img src="http://base2.okimgs.com/images/xh-addtop010.png" class="fl w">
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
						<a href="http://www.${user.weiID }.okwei.com">${user.weiName }</a> <a href="javascript:SignOut();" class="c6">退出</a> <a href="<%=mydomain%>">后台管理</a><a href="<%=cartdomain%>/shopCartMgt/list">购物车（<span id="cartCount" class="tb cR">${user.cartCount }</span>） 
					</p>
				</c:when>
				<c:otherwise>
					<!-- 未登录 -->
					<p class="fr imgrs">
						<a href="javascript:goLogin();">请登录</a> <a href="<%=portdomain%>/RegistNew.aspx">免费开微店</a><a href="<%=cartdomain%>/shopCartMgt/list">购物车（<span id="cartCount" class="tb cR">${user.cartCount }</span>） 
					</p>
				</c:otherwise>
			</c:choose>

			</a>
		</div>
	</div>
	<!-- logo -->
	<div class="mar1200 logies">
		<h1 class="mt20 fl">
			<a href="http://www.<%=okweidomain%>"><img src="http://base3.okimgs.com/images/xh_logo.gif" /></a>
		</h1>
		<h2 class=" ml10 fl mt20 pr">
			<a href="http://www.<%=okweidomain%>/help/aboutus"><img src="http://base2.okimgs.com/images/xh_gaiban_cctv.png" /></a>
			<div class="pa c6 f12 tfont pl10">微店概念发起者、领导者</div>
		</h2>
		<div class="fl ml100 inputmit mt30">
			<input id="searchTxt" type="text" placeholder="搜索" /> <input type="submit" value="搜索" onclick="search()" />
		</div>
		<div class="fr wx_dabao">
			<ul>
				<li class="img_ic1 c6">微信支付</li>
				<li class="img_ic2"><a href="http://www.<%=okweidomain %>/help/us#qtbt" class="c6">七天包退</a></li>
				<li class="img_ic3"><a href="http://www.<%=okweidomain %>/help/us#dbjy" class="c6">担保交易</a></li>
			</ul>
		</div>
	</div>
	<!-- 导航条 -->
	<div class="w dht">
		<div class="mar1200 dht">
			<ul class="banone fl ">
				<li class="pr nows" id="nav_all"><a href="javascript:;" class="nows ficnews">全部分类 <i class="nav_down"></i></a>
					<div id="Z_RightSide" class="sidebar"></div>
					<div class="pa" style="top: 38px; left: 0; z-index: 99;width:1200px" id="winhide">
						<div class="Z_TypeList" id="Z_TypeList">
							<div class="fl mr15 submone Z_MenuList">
								<ul>
									<c:forEach var="menuone" items="${headinfo.leftMenu }" varStatus="index">
										<li class="list-item${index.index }">
											<h3>${menuone.name }</h3>
											<p>
												<c:forEach var="menutwo" items="${menuone.list }" varStatus="vs">
													<c:if test="${vs.index<6 }">
														<a target="_blank" href="http://www.<%=okweidomain %>/list/list?mid=${menutwo.id }&w=${user.tgWeiID}">${menutwo.name }</a>
													</c:if>
												</c:forEach>
											</p>
										</li>
									</c:forEach>
								</ul>
							</div>
							<div class="Z_SubList">
								<c:forEach var="menuone" items="${headinfo.leftMenu }">
									<div class="subView">
										<c:set value="${fn:length(menuone.list) }" var="count"/>
										<c:forEach var="menutwo" items="${menuone.list }" varStatus="vs">
											<c:if test="${vs.index%3==0 }">
												<ul>
											</c:if>
											<li class="subItem">
												<h3 class="subItem-hd">
													<a target="_blank" href="http://www.<%=okweidomain %>/list/list?mid=${menutwo.id }&w=${user.tgWeiID}">${menutwo.name }</a>
												</h3>
												<p class="subItem-cat">
													<c:forEach var="menuthree" items="${menutwo.list }">
														<a target="_blank" href="http://www.<%=okweidomain %>/list/list?sid=${menuthree.id }&w=${user.tgWeiID}">${menuthree.name }</a>
													</c:forEach>
												</p>
											</li>
											<c:if test="${(vs.index%3==2) || (vs.index==count-1) }">
			</ul>
			</c:if>
			</c:forEach>
		</div>
		</c:forEach>
	</div>
</div>
</div>
</li>
<li name="nav_li"><a href="<%=companydomain%>/wholesale/index?w=${user.tgWeiID}" <c:if test="${mtype=='0'}">class="now_2 ficnews"</c:if>>批发市场</a></li>
<li name="nav_li"><a href="<%=companydomain%>/yun/list?c=0&type=0&w=${user.tgWeiID}" <c:if test="${mtype=='1'}">class="now_2 ficnews"</c:if>>工厂</a></li>
<li name="nav_li"><a href="<%=companydomain %>/agent/list" <c:if test="${mtype==3}">class="now_2 "</c:if>>代理区</a></li>
<!-- <li name="nav_li"><a href="#">特步旗舰店</a></li> -->
</ul>
<ul class="bantwo fr">
	<li><a href="http://www.<%=okweidomain %>/list/list?w=${user.tgWeiID}">云端产品库</a></li>
	<li><a href="<%=joindomain%>/supplier/apply?w=${user.tgWeiID}">供应商进驻</a></li>
</ul>
</div>
</div>
</div>
<!-- 菜单效果 -->
<script src="http://base2.okwei.com/js/jquery.tmailsider.js"></script>
<script type="text/javascript">
	(function($) {
		$.fn.Z_TMAIL_SIDER.defaults = {
			target : $('#Z_RightSide'),
			fTop : 60,
			cTop : 0,
			unitHeight : 80,
			autoExpan : true
		};
	})(jQuery);
	$('#Z_TypeList').Z_TMAIL_SIDER();
</script>

