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
<script>
//$(document).ready(function() {
   // $("input[type='number']").stepper();
    //});
</script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>我的店铺资料</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/statics/js/uploadify/uploadify.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/statics/js/uploadify/jquery.uploadify-3.1.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/statics/js/userinfo/shopinfo.js"></script>
</head>

<body class="bg_f3">
	<form id="shopinfoForm" name="shopinfoForm" action="userinfo/saveShopInfo" onsubmit="">
	<input type="hidden" id="shopImg" name="shopImg" value="${shopinfo.shopImg}"/>
	<div class="fl conter_right bg_white bor_si">
           <!-- 选项卡 -->
           <div class="gygl_xxk_t f16 ndfxs_1-2_border">
               <div class="gygl_xxk_yes" name="mzh_xxk" style="color: #000;">店铺资料<span></span></div>
           </div>
           <div class="jbzl">
                <dl class="jbzl_dl f14">
                    <dd>店铺（公司）照片：</dd>
                    <dt>
                        <div class="jbzl_tx">
                            <img id="shopImage" src="${fullImgUrl}" width="85" height="85" />
                            <div class="jbzl_tx_dw" style="height:18px;line-height:18px; "><input type="file" id="uploadImg" name="uploadImg" class="jbzl_tx_dw" style="background-position: center;opacity: 0;"/></div>
                        </div>
                    </dt>
                </dl>
                <dl class="jbzl_dl f14">
                    <dd>微店号：</dd>
                    <dt>${shopinfo.weiId }</dt>
                </dl>
                <dl class="jbzl_dl f14">
                    <dd><span class="f14">店铺名</span>：</dd>
                    <dt><input id="shopName" name="shopName" value="${shopinfo.shopName }" type="text" class="fl btn_h42 w250" maxlength="20"/>
                    </dt>
                </dl>
                <dl class="jbzl_dl f14">
                    <dd><span class="f14">主营</span>：</dd>
                    <dt><input id="shopBusContent" name="shopBusContent" value="${shopinfo.shopBusContent }" type="text" class="fl btn_h42 w250" maxlength="20"/>
                </dl>
                <c:if test="${shopinfo.showBatchSupplyer==1 }">
                <dl class="jbzl_dl f14" style="margin-top: 10px;">
                    <dd><span class="f14">地区</span>：</dd>
                    <dt>${shopinfo.addrArea }</dt>
                </dl>
                <dl class="jbzl_dl f14" style="margin-top: 0px;">
                    <dd><span class="f14">市场名称</span>：</dd>
                    <dt>${shopinfo.marketName }</dt>
                </dl>
                <dl class="jbzl_dl f14" style="margin-top: 0px;">
                    <dd><span class="f14">店铺位置</span>：</dd>
                    <dt>${shopinfo.shopPosition }</dt>
                </dl>
                </c:if>
                <dl class="jbzl_dl f14" style="margin-top: 0px;">
                    <dd></dd>
                    <dt><div class="jbzl_dl_bc">保　存</div></dt>
                </dl>
        </div>
    </div>
	</form>

	<script type="text/javascript">
	
	</script>

</body>