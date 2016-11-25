<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="pg" uri="http://www.okwei.com/pagination"%>
<%@ page import="java.util.ResourceBundle"%>
<%
    String mydomain = ResourceBundle.getBundle("domain").getString("mydomain");
	String sellerdomain = ResourceBundle.getBundle("domain").getString("sellerdomain");
	String okweidomain = ResourceBundle.getBundle("domain").getString("okweidomain"); 
%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>我的钱包</title>  
</head>

<body class="bg_f3">  
<c:set var="cashCoupon" value="1" scope="request"></c:set>
      <div class="fl conter_right   bg_white bor_si">
        <div class="wdqb_xxk ndfxs_1-2_border">
            <div class="tab_pc f16">
            <ul>
                <li class="now"><a href="#" style="color:#333;">现金券总攻略</a><i></i></li> 
                </ul>
            </div>
        </div>
        <div class="fl outermost"> 
        	<div class="xjq_se">
            	<div class="imgris"><img src="/statics/images/img_xqis1.png" /></div>
                <div class="ml_5 fl"><img src="/statics/images/img_xqis2.jpg" /></div>
                <div class="fl outermost" style="margin-top:90px;">
                	<div class="fl ieones"><img src="/statics/images/img_xqis3.jpg" /></div>
                	<div class="fl ietwos">
                    	<p class="f16 ft_c3">什么是现金券？</p>
                        <p class="f14 ft_c3 mt_5">现金券是微店网为回馈广大落地店、准落地店而推出的优惠券，用于抵扣现金，线上下单购买即可使用。</p>
                    </div> 
                </div>
                
                <div class="fl outermost mt_20">
                	<div class="fl ieones"><img src="/statics/images/img_xqis4.jpg" /></div>
                	<div class="fl ietwos">
                    	<p class="f16 ft_c3">现金券如何获得：</p>
                        <p class="f14 ft_c3 mt_5">买家在微店网落地进货区进货购买，成为落地店即可获得与支付现金等额的现金券。</p>
                    </div> 
                </div>
                
                <div class="fl outermost mt_20">
                	<div class="fl ieones"><img src="/statics/images/img_xqis5.jpg" /></div>
                	<div class="fl ietwos">
                    	<p class="f16 ft_c3">现金券使用规则：</p>
                        <p class="f14 ft_c3 mt_5">1、每笔落地进货或铺货订单支付时，均可使用现金券抵扣现金，抵扣规则：    1现金券 抵 1元现金，可抵扣订单总金额的10%。</p>
                        <p class="f14 ft_c3 mt_5">2、当使用了现金券的订单退款时，按比例退现金以及现金券。计算方法：</p>
                        <p class="f14 ft_red mt_5 t_ind20">（1）退款现金=需退款总额 x (所付现金÷订单总价）</p>
                        <p class="f14 ft_red mt_5 t_ind20">（2）退现金券额度=需退款总额 x (所付现金券总额÷订单总价）</p>
                        <p class="f14 ft_c3 mt_5">3、现金券不可兑现、不可提现、不可转让，每年12月31日系统将自动清空。</p>
                    </div> 
                </div>
             </div>
        
        </div> 
      </div>
    </div>
</body>
</html>
