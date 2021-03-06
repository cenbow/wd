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
	String setLftedomain  = ResourceBundle.getBundle("domain").getString("setdomain");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>发现招商需求</title>

<script type="text/javascript" src="/statics/js/district.js"></script>
<script type="text/javascript" src="/statics/js/My97/WdatePicker.js"></script>
<script type="text/javascript" src="/statics/js/demand/founddemand.js"></script>
<style type="text/css">
.ndfxs_1-2_fenye div{
	float:right;
}
.conter_right_xx_cz_table td{padding:15px 10px;}
.ndfxs_1-2_xh{text-indent:0px;}
</style>
</head>
<body>
<form id="searcherForm" name="searcherForm" action="/demand/fondDemand" onsubmit="return false;">
<div class="fr conter_right">
      <div class="zhuag_suv bor_si fl bg_white">
        <div class="oneci_ztai fl">
          <div class="left_font tr fl f12 ft_c9" style="line-height:26px;">招商地区：</div>
          <div class="mzh_xlk" style="margin: 0px;width: 100px;" name="mzh_xlk">
<!--              <div class="mzh_xlk_text" isclick="0" style="width: 100px;">选择省</div>
             <div class="mzh_xlk_dw" style="width: 100px;"> 
             	<span name="mzh_span_4_9">选择省</span> 
             	<span name="mzh_span_4_9">选择省</span> 
            </div> -->
          <select id="selProvince" name="province" style="margin: 0px;width: 98px;">
		    <!-- <option>选择省</option> -->
		  </select>
		  <input type="hidden" id="txtProvince" value="${province}">
          </div>
          
          <div class="mzh_xlk" style="margin: 0 0 0 10px;width: 100px;" name="mzh_xlk">
           <select id="selCity" name="city" style="margin: 0px;width: 98px;">
		    <option value="-1">选择市</option>
		  </select>
		  <input type="hidden" id="txtCity" value="${city}">
          </div> 
          <div class="left_font tr fl f12 ft_c9" style="line-height:26px;">申请时间：</div>
          <span class="fl">
          <input type="text" name="stateTimeStr" value="${stateTimeStr }" class="xzgys_input" onclick="WdatePicker()" readonly="readonly" style="width:160px;">
          </span> <span class="fl ml_5 mr_5" style="line-height:25px; color:#666;">—</span> <span class="fl">
          <input type="text" name="endTimeStr" value="${endTimeStr }" class="xzgys_input" onclick="WdatePicker()" readonly="readonly" style="width:160px;">
          </span>
          <input type="submit" id="submitBtn" value="查询" class="dis_b ml_20 fl btn_sel28_pre bor_cls shou">
        </div>
        <div class="blank1"></div>
      </div>
      <!-- 销售中 -->
      <div class="gygl_xxk mt_20 bor_le bor_ri" id="id_cpjl_0">
        <table class="conter_right_xx_cz_table mt_10">
          <tr class="ndfxs_1-2_color ndfxs_1-2_border">
            <td class="ndfxs_1-2_xh">需求名称</td>
            <td class="ndfxs_1-2_fxs">招商地区</td>
            <td class="ndfxs_1-2_ss">发布时间</td>
            <td class="ndfxs_1-2_ss">招商产品数量</td>
            <td class="ndfxs_1-2_qq">招募代理悬赏金额</td>
            <td class="ndfxs_1-2_xh">已招募代理数量</td>
            <td class="ndfxs_1-2_qq">已招募落地店数量</td>
            <td class="ndfxs_1-2_ss">操作</td>
          </tr>
         <c:if test="${page !=null && page.list !=null && page.list.size()>0 }">
         <c:forEach items="${page.list}" var="item">
         <tr class="ndfxs_1-2_border">
            <td class="ndfxs_1-2_xh">${item.title }</td>
            <td class="ndfxs_1-2_fxs">${item.areaString }</td>
            <td class="ndfxs_1-2_ss">${item.createTimeString }</td>
            <td class="ndfxs_1-2_ss">${item.pCount }款</td>
            <td class="ndfxs_1-2_qq">${item.maxAgentReward}</td>
            <td class="ndfxs_1-2_xh">${item.agentCount !=null ? item.agentCount:0 }</td>
            <td class="ndfxs_1-2_qq">${item.shopCount!=null ? item.shopCount :0 }</td>
            <td class="ndfxs_1-2_ss"> 
            	<a target="_blank" href="http://${item.weiId}.<%=okweidomain%>/demand/demandInfo?demandID=${item.demandId}" class="mr_10">查看</a>
            </td>
          </tr>
         </c:forEach>
         </c:if>
                
        </table>
        <c:if test="${page ==null || page.list==null || page.list.size() <1 }">
	       	<div style="width:100%;height:400px;text-align:center;font-size:18px;line-height:400px;">
	       		暂无相关数据
	       	</div>
        </c:if>
      </div>
      <div class="ndfxs_1-2_fenye">
		<pg:page pageResult="${page}" />
      </div>
      </div>
    </div>
    </form>
</body>
</html>