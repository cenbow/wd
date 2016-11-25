<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="pg" uri="http://www.okwei.com/pagination"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%> 
<%@ page import="java.util.ResourceBundle"%>
<%
    String paydomain = ResourceBundle.getBundle("domain").getString(
					"paydomain");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>悬赏列表</title> 
<style> 
#mzh_qb_0 .wdqb_tr td{padding: 0px 0;}
#mzh_qb_0  td{padding: 20px 0;}
#mzh_qb_1 .wdqb_tr td{padding: 0px 0;}
#mzh_qb_1  td{padding: 20px 0;}</style>
<script type="text/javascript" src="<%=request.getContextPath()%>/statics/js/reward/reward.js"></script>
<script language="JavaScript" type="text/javascript" src="<%=request.getContextPath()%>/statics/js/My97/WdatePicker.js"></script>
<script language="JavaScript" type="text/javascript" src="<%=request.getContextPath()%>/statics/js/jquery-1.7.1.min.js"></script>
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
			dtype=0;
		}
		$("[data-type=" + dtype + "]").addClass("yes_bgs");
		$("#datatype").val(dtype);
		var yearTimeStr = GetQueryString("yearTimeStr");
			$("#yearTimeStr").val(yearTimeStr);
		if($("#yearTimeStr").val().length>0){
			$("#monthTimeStr").removeAttr("disabled");
		}
	}
	
})
 
</script>
</head>
 
<body class="bg_f3">    
 <input type="hidden" id="paydomain" value="<%=paydomain%>"/>
<form id="searcherForm" name="searcherForm" action="/reward/rewardLists"> 
    <div class="fr conter_right" style="border: 0px;background: none;">
        <div class="zhuag_suv bor_si fl bg_white">
            <div class="oneci_ztai fl">
                <div class="left_font tr fl f12 ft_c9">状态：</div>
                <div class="left_xuanzs fl f12">
                    <ul id="type">
                        <li name="mzh_4_7_yes" data-type="0">未支付（<b class="left_xuanzs_b">${weiZhiFu}</b>）</li>
                        <li name="mzh_4_7_yes" data-type="1">已支付（<b class="left_xuanzs_b">${yiZhiFu}</b>）</li>
                    </ul>
                </div>
                  <input type="hidden" id="datatype" name="dt" value="0" />
            </div>
        </div>
      <!-- 未支付 -->
      <div class="fl conter_right mt_20  bg_white bor_si" id="id_zhifu_0">
          <div class="rztd_cx f14">
          
          <!-- 未完成 -->
          <c:if test="${dt==0}">
              <span class="fl ml_10">未支付悬赏金额：</span>
              <b class="fl ft_red mr_20 f16">￥${TotalMoney}</b>
             <%--  <span class="fl ml_10">未支付悬赏数：</span>
              <b class="fl ft_red mr_20 f16">${pageResult.totalCount}</b> --%>
          </c:if>
          <c:if test="${dt==1}">
              <span class="fl ml_10">已支付悬赏金额：</span>
              <b class="fl ft_red mr_20 f16">￥${TotalMoney}</b>
             <%--  <span class="fl ml_10">支付悬赏数：</span>
              <b class="fl ft_red mr_20 f16">${pageResult.totalCount}</b> --%>
          </c:if>
          </div>
          <div class="rztd_cx f14">
            <div class="rztd_cx_div">筛选：</div>
            <div class="rztd_cx_div  fl"> <input value="${yearTimeStr }" readonly="readonly" type="text" class="btn_h24" name="yearTimeStr" id="yearTimeStr" onfocus="WdatePicker({dateFmt:'yyyy'})" /> 年</div>
              <div class="rztd_cx_div"><input value="${monthTimeStr }" readonly="readonly" disabled="disabled" type="text" class="btn_h24" name="monthTimeStr" id="monthTimeStr" onfocus="WdatePicker({dateFmt:'MM'})" />月
             	 <input type="submit" value="查询" class="btn_submit_two" style="width: 80px;"/> 
             	   <input type="button" value="清空" class="btn_submit_two" onclick="onClear()" style="width: 80px;"/>  
             	
             	 </div>
               
             <c:if test="${monthTimeStr!=''}">
              <span class="fl rztd_cx_div f12" style="color: #999;">（该月未支付悬赏金额：<b class="ft_c3">
      		  ￥${monthTotalMoney}
              </b>）</span>
             </c:if>
          </div>
        <table class="mt_20 wdqb_table"  id="mzh_qb_0" style="margin: 0px;padding: 0px;">
          <tr class="wdqb_tr">
            <td><input type="checkbox" class="ml_20 fl mt_14" name="id_allYes"/><label for="id_allYes" class="fl line_42 ml_5">全选</label></td>
            <td><b>招募认证员</b></td>
            <td><b>代理商/落地店</b></td>
            <td><b>审核通过时间</b></td>
            <td><b>悬赏金额</b></td>
             <c:if test="${dt==0}">
            <td><b>操作</b></td>
            </c:if>
          </tr>
          <c:if test="${fn:length(pageResult.list)<1}">
          		<tr style="height:200px;">
					<td colspan="6" style="text-align:center">暂无相关数据</td>
				</tr>
          </c:if>
          <c:forEach items="${pageResult.list}" var="ss">
          <tr>
            <td>
                <input type="checkbox" name="cls" value="${ss.channelId}" class="ml_20 fl"/>
            </td>
            <td>
                <div class="fl w130" style="width:210px;" >
                    <img src="${ss.verifierImg}" width="80" height="80" class="fl"/>
                    <span class="fl ml_10" >${ss.verifierName}</span>
                </div>
            </td>
            <td>  <c:if test="${ss.channelType==1}">
                <div class="fl w80">
                    <p class="dls_tb" style="margin: 0px;">
                         	    代理商
                    </p>
                    <p class="fl" style="width:80px;">${ss.companyName}</p>
                </div>
                 </c:if>
                 <c:if test="${ss.channelType==2}">
                 <div class="fl w80">
                    <p class="ldd_tb" style="margin: 0px;">落地店</p>
                    <p class="fl" style="width:80px;">${ss.companyName}</p>
               	 </div>
                 </c:if>   
            </td>
            <td><span class="ft_c9">${ss.createTime}</span></td>
            <td>￥<span class="ft_red " >${ss.reward}</span></td>
             <c:if test="${dt==0}">
            <td>  
                <a href="javascript:void(0);getOrderNo1(${ss.channelId})" >支付</a>
            </td>
            </c:if>
          </tr>
          
          </c:forEach>
           
            <tr>
                <td colspan="7" style="padding: 0px;">
                    <div class="fl mzh_width_100 bg_white">
                        <div class="fl">
                        <input type="checkbox"  class="ml_20 fl mt_14" name="id_allYes" id="id_allYes"/><label for="id_allYes" class="fl line_42 ml_5">全选</label>
                        </div>
                        <div class="fr wind_up">
                            <div class="wind_one f12 ft_c3 fl">已选 <span class="f18 ft_red"><label id="valuse">0</label></span>笔悬赏记录</div>
                            <div class="wind_one f12 ft_c3 fl">合计： <span class="f18 ft_red">￥<label id="total">0</label></span></div>
                            <div class="wind_two fl" ><a href="javascript:void(0);" onclick="getOrderNo();">去支付</a></div>
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
