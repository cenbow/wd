<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>    
<%@ page import="java.util.ResourceBundle"%>
<%@ page import="com.okwei.util.*" %>
<%
	String productdomain = ResourceBundle.getBundle("domain").getString("productdomain");
	String okweidomain = ResourceBundle.getBundle("domain").getString(
		"okweidomain");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>兑换区首页</title>
<link rel="stylesheet" type="text/css" href="http://base2.okwei.com/css/company/glbdy.css" />
<link rel="stylesheet" type="text/css" href="http://base2.okwei.com/css/company/mzh_dd.css" />
<script src="statics/js/jquery-1.7.1.min.js"></script>
<style>
.mzh_dht ul li{padding:0 40px;}
</style>
</head>
<body style="background: #ffebb9;">

<div class="fl w">
  <a href="#" class="fl w"><img src="http://base1.okwei.com/images/company/img_7_22_25.jpg" class="fl w"/></a>
  <div class="blank"></div>
  <div class="fl w">
    <div class="mar_au">
      <div class="fl w tc">
        <div class="mzh_dht">
          <ul>
            <li><a href="http://www.<%=okweidomain %>/index828">狂欢首页买买买</a></li>
            <li><a href="http://join.okwei.com/zsjm/index">去APP任性换</a></li>
            <li><a href="http://join.okwei.com/zsjm/index">上APP抽红包</a></li>
          </ul>
        </div>
      </div>

      <div class="blank"></div>
      <div class="mzh_hdgz">
        <div class="mzh_hdgz_0">
          <p class="f24">活动规则：</p>
          <p class="f14">1.兑换、抽奖活动仅限于参加8.28购物活动的用户参加；</p>
          <p class="f14">2.兑换、抽奖活动仅限于APP上进行，此区域只作展示；</p>
          <p class="f14">3.兑换的商品仅限于支持购物券兑换；</p>
          <p class="f14">4.兑换区的商品不支持退换；</p>
        </div>
      </div>
    </div>
  </div>
</div>
<div class="blank"></div>

<div class="fl w" style="background: #ffbcb9;">
  <div class="mar_au">

    <div class="blank"></div>
    <div class="fl w tc"><img src="http://base1.okwei.com/images/company/img_7_22_28.png"/></div>
    <div class="blank"></div>
    <div class="mzh_7_22_cp">
      <ul>
      <c:forEach items="${list }" var="c">
        <li>
          <a href="<%=productdomain %>/product?pid=${c.productId }" target="_blank">
            <div class="fl w pr"><img src="${ImgDomain.GetFullImgUrl(c.defaultImg,75) }" width="216" height="216"/><span class="mzh_7_22_cp_tb">购物全返</span></div>
            <div class="fl w mt_20">
              <img src="http://base1.okwei.com/images/company/img_7_22_29.png" class="fl"/>
              <b class="mzh_7_22_cp_jg">￥${c.defaultPrice }</b>
              <del class="fl ft_c9 mt_5 ml_10">原价：${c.originalPrice }</del>
            </div>
            <div class="mzh_xsqgxx_bt">${c.productTitle }</div>
          </a>
        </li>
       </c:forEach>
      </ul>
    </div>
    <div class="blank"></div>
  </div>
</div>

<!-- 左边悬浮购物导航 -->
<div class="mzh_xfdh">
  <ul>
    <li><a href="#"><img src="http://base.okwei.com/images/828/img_7_22_2.png" width="74" height="75" /></a></li>
			<li><a href="http://www.<%=okweidomain %>/index828"><img src="http://base.okwei.com/images/828/img_7_22_3.png" class="mt10" /><span>主会场</span></a></li>
			<c:forEach var="main" items="${home }">
				<c:choose>
					<c:when test="${main.className=='衣'}">
						<li><a href="http://www.<%=okweidomain %>/second?cid=${main.classId }"><img src="http://base.okwei.com/images/828/img_7_22_4.png" class="mt10" /><span>品质衣柜</span></a></li>
					</c:when>
					<c:when test="${main.className=='食'}">
						<li><a href="http://www.<%=okweidomain %>/second?cid=${main.classId }"><img src="http://base.okwei.com/images/828/img_7_22_5.png" class="mt10" /><span>食全食美</span></a></li>
					</c:when>
					<c:when test="${main.className=='住'}">
						<li><a href="http://www.<%=okweidomain %>/second?cid=${main.classId }"><img src="http://base.okwei.com/images/828/img_7_22_6.png" class="mt10" /><span>爱巢精品</span></a></li>
					</c:when>
					<c:when test="${main.className=='行'}">
						<li><a href="http://www.<%=okweidomain %>/second?cid=${main.classId }"><img src="http://base.okwei.com/images/828/img_7_22_7.png" class="mt10" /><span>出行必备</span></a></li>
					</c:when>
					<c:when test="${main.className=='用'}">
						<li><a href="http://www.<%=okweidomain %>/second?cid=${main.classId }"><img src="http://base.okwei.com/images/828/img_7_22_8.png" class="mt10" /><span>享用不停</span></a></li>
					</c:when>
					<c:when test="${main.className=='学'}">
						<li><a href="http://www.<%=okweidomain %>/second?cid=${main.classId }"><img src="http://base.okwei.com/images/828/img_7_22_9.png" class="mt10" /><span>学无止境</span></a></li>
					</c:when>
					<c:when test="${main.className=='玩'}">
						<li><a href="http://www.<%=okweidomain %>/second?cid=${main.classId }"><img src="http://base.okwei.com/images/828/img_7_22_10.png" class="mt10" /><span>玩转生活</span></a></li>
					</c:when>
				</c:choose>
		    </c:forEach>
			<li><a href="http://join.okwei.com/zsjm/index"><img src="http://base.okwei.com/images/828/img_7_22_11.png" class="mt5" width="23" />
				<span>下载APP<br>换抽红包</span></a>
			</li>
  </ul>
</div>
<!-- 左边悬浮购物导航-End -->

</body>
</html>
