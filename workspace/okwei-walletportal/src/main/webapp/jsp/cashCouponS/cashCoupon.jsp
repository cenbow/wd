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
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>我的现金卷</title> 
<style>
#mzh_qb_0 .wdqb_tr td{padding: 0px 0;}
#mzh_qb_0  td{padding: 20px 0;}
#mzh_qb_1 .wdqb_tr td{padding: 0px 0;}
#mzh_qb_1  td{padding: 20px 0;}
.mzh_fan{float:left;padding:0 3px 1px;color: #fff;background: #f10;border-radius: 3px;line-height: 20px;}
.mzh_gwfxjj{float:left;background: #e8af45;color:#fff;border-radius: 3px;padding: 3px;}
.mzh_tkfxjj{float:left;background: #718ffe;color:#fff;border-radius: 3px;padding: 3px;}
</style>
<script language="JavaScript" type="text/javascript" src="<%=request.getContextPath()%>/statics/js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/statics/js/cashCoupon/cashCoupon.js"></script>
<script>
$(function(){
	$(document).ready(function(){
	   	var page = new Pagination( {
			formId: "searcherForm",
			isAjax : false,
			targetId : "navTab",
			submitId:"searchBtn",
		});
		page.init();
		inits();
	});
	function inits() {
		var dtype = GetQueryString("dt");// 类型 
		 if(dtype==null){
			 dtype=1;
		 }
			$("[data-type=" + dtype + "]").addClass("yes_bgs");
			$("#datatype").val(dtype);
	}
	

	
});


</script>
</head>
 
<body class="bg_f3">   
<form id="searcherForm" name="searcherForm" action="/cashCoupon/cashCoupon"> 
 
       <div class="fr conter_right" style="border: 0px;background: none;">
        <div class="zhuag_suv bor_si fl bg_white">
	        <div class="rztd_cx f14 mb_20">
	            <span class="fl ml_10">我的现金券：</span>
	            <b class="fl ft_red mr_20 f16"> ${weiShiYongJingE} </b>
	            <a href="/cashCoupon/youhuiquan" class="ml_48">现金卷总攻略</a>
	        </div>
        </div>
    <div class="zhuag_suv bor_si fl bg_white mt_20" style="border-bottom: none;">
        <div class="oneci_ztai fl">
            <div class="left_font tr fl f12 ft_c9">状态：</div>
            <div class="left_xuanzs fl f12">
                <ul id="type">
                      <li name="mzh_4_7_yes" data-type="1" >收入（<b class="left_xuanzs_b">${shouRu}</b>）</li>
                      <li name="mzh_4_7_yes" data-type="2" >支出（<b class="left_xuanzs_b">${zhiChu}</b>）</li>
                      <li name="mzh_4_7_yes" data-type="3" >已过期（<b class="left_xuanzs_b">${guoQi}</b>）</li>
                 </ul>
            </div>
               <input type="hidden" id="datatype" name="dt" value="1" />
        </div>
   </div>	 
 <div class="fl conter_right  bg_white bor_si" id="id_zhifu_0">
        <table class=" wdqb_table"  id="mzh_qb_0">
          <tr class="wdqb_tr">
            <td><b class="ml_20 fl">来源/用途</b></td>
            <td><b>日期</b></td>
            <td><b>类型</b></td>
            <td><b>现金券变化</b></td>
            <td><b>操作</b></td>
          </tr>
        <c:if test="${fn:length(pageResult.list)<1}">
	       	<tr style="height:200px;">
				<td colspan="5" style="text-align:center">暂无相关数据</td>
			</tr>
        </c:if>
          <c:forEach items="${pageResult.list}" var="CashCouponVO">
          <tr>
            <td class="w350">
                <div class="fl w350">
                	<input type="checkbox"  name="cls" class="ml_20 fl mr_10 mt_30"  value="${CashCouponVO.cashCouponId }"/>
                    <img src="${CashCouponVO.productImg}" width="80" height="80" class="fl"/>
                    <a href="#" class="fl ml_10" style="width: 205px;"> ${CashCouponVO.prodcutTitle} </a>
                </div>
            </td>
            <td>
                <div class="fl w80">
                    <p class="fl ft_c9">${CashCouponVO.createTime}</p>
                </div>
            </td>
            <td>
            <c:choose>
            <c:when test="${dt==1}">
             <span class="mzh_gwfxjj">购物返现金券</span>
            </c:when>
              <c:when test="${dt==2}">
              <span class="ldd_tb" style="margin: 0px;">购物抵现</span>
            </c:when>
              <c:when test="${dt==3}">
            <span class="jhd_tb" style="margin: 0px;">过期现金卷</span>
            </c:when>
            </c:choose> 
            </td>
            <td><span class="ft_red">
            <c:choose>
            <c:when test="${dt==2}">
              -${CashCouponVO.coinAmount}
            </c:when>
            <c:otherwise>
             +${CashCouponVO.coinAmount}
            </c:otherwise> 
            </c:choose> 
            </span>
            </td>
            <td>
            	<p><a href="javascript:void(0);" onclick="delteCashCoupon1('${CashCouponVO.cashCouponId }',this)" class="ft_lan">删除记录</a></p>
            	<p><a href="<%=mydomain %>/order/buydetails?orderNo=${CashCouponVO.supOrderID }"  class="ft_lan">订单详情</a></p>
            </td>
          </tr>
          </c:forEach> 
           <tr>
                <td colspan="7" style="padding: 0px;">
                    <div class="fl mzh_width_100 bg_white">
                        <div class="fl">
                             <input type="checkbox"  class="ml_20 fl mt_14" name="id_allYes" id="id_allYes"/><label for="id_allYes" class="fl line_42 ml_5">全选</label>
                            <a class="dis_b ml_20 fl mt_7 btn_hui28_pre shou" href="javascript:void(0);deleteCashCoupon();" >删除</a>
                        </div>
                    </div>
                </td>
            </tr>
        </table>
    </div> 
	<div class="pull-right">
		<pg:page pageResult="${pageResult}" />
	</div>
	 </div>
    
</form>


</body>
</html>
