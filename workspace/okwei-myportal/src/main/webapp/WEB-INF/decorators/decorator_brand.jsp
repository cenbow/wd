<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title><sitemesh:write property='title'/></title>
		<jsp:include page="/jsp/common/scriptstyle.jsp" />
		<sitemesh:write property='head'/>
	</head>
	<body>
		<div id="header">
			<jsp:include page="/jsp/common/header.jsp"/>
		</div>
		<div class="mar1200">
			<div class="pprz_sel fl mt20">
		    	<p class="f24 tc tb pt20 pb15">品牌认证规则</p>
		        <p class="pl20 pr30 pb10 f16 c6"><span class="f18">1、正品原则：</span>必须为知名品牌拥有者（有商标注册）或者原厂授权的直接代理商，方可申请品牌认证</p>
		        <p class="pl20 pr30 pb10 f16 c6"><span class="f18">2、独家原则：</span>为防止红海价格战，每个品牌每个城市只认证一家</p>
		        <p class="pl20 pr30 pb10 f16 c6"><span class="f18">3、价优原则：</span>品牌认证通过，微店价不得高于京东、天猫、易迅、亚马逊、苏宁易购等网购商城、否则，微店网有权利取消认证</p>
		        <p class="pl20 pr30 pb10 f16 cR"><span class="f18">4、重罚原则：</span>所有经过品牌认证的商品，一旦出现违规操作（详细标准参照<a   target="_blank" href="http://pan.baidu.com/s/18EIZf" class="cB">《微店网服务协议》</a>），处罚标准是普通商品的4倍</p>
		        <p class="pl20 pr30 pb30 f16 c6"><span class="f18">5、可上可下：</span>品牌认证为微店网的非盈利服务，出现但不限于下列情形之一，微店网有权利取消您的认证：出现不发货、
		        延迟发货、无货、假冒伪劣、伪造代理资质、伪造商标证书、原厂来函提出网络销售争议、销售好评率过低、退货率过高等
		        </p>
		    </div>
		    <div class="for_tadan">
				<sitemesh:write property='body' />
		    </div>
		</div>
				<!-- 底部 -->
		<div id="footer">
			<jsp:include page="/jsp/common/footer.jsp" />
		</div>
	</body>
</html>
