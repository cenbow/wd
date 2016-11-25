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

<head>
<title>我的钱包</title>
	<script  type="text/javascript" src="/statics/js/My97/WdatePicker.js"></script>
	<script type="text/javascript">
		$(function(){
/* 			<!--选项卡样式切换-->
			styleChange(); */
			
			<!--查询-->
			$("#btnSubmit").click(function(){
				/* if($("#mzh_qb_0").is(":hidden") && $("#mzh_qb_1").is(":hidden")){
					document.forms[0].action="/walletMgt/changeDetailType/2"  <!--2.表示是在支出的选项卡下进行的查询-->
					document.forms[0].submit();
				}else if($("#mzh_qb_0").is(":hidden") && $("#mzh_qb_2").is(":hidden")){
					document.forms[0].action="/walletMgt/changeDetailType/1"  <!--1.表示是在收入的选项卡下进行的查询-->
					document.forms[0].submit();
				}else{
					
				} */
				document.forms[0].action="/walletMgt/index";  <!--0.表示是在全部的选项卡下进行的查询-->
				document.forms[0].submit();
			});
		});
		
		/* <!--选项卡样式切换-->
		function styleChange(){
			if($("#mzh_qb_1").is(":hidden") && $("#mzh_qb_2").is(":hidden")){
				$("#all").siblings("li").removeClass("now");
				$("#all").addClass("now");
			}
			if($("#mzh_qb_0").is(":hidden") && $("#mzh_qb_1").is(":hidden")){
				$("#outgoing").siblings("li").removeClass("now");
				$("#outgoing").addClass("now");
			}
			if($("#mzh_qb_0").is(":hidden") && $("#mzh_qb_2").is(":hidden")){
				$("#income").siblings("li").removeClass("now");
				$("#income").addClass("now");
			}
		} */
		
		function EnterPress(e){ //传入 event
			var e = e || window.event;
				if(e.keyCode == 13){
					document.forms[0].action="/walletMgt/index";  <!--0.表示是在全部的选项卡下进行的查询-->
					document.forms[0].submit();
				}
			}
	</script>
</head>
<body class="bg_f3">
<form id="searcherForm" name="searcherForm" action="/walletMgt/index"> 
	<div style="border: 0px;background: none;" class="fr conter_right">
      <div class="fl conter_right mt_20  bg_white bor_si">
        <div class="wdqb_xxk ndfxs_1-2_border">
            <div class="tab_pc f16">
            <ul>
                <li class="now" id="all" ><a href="/walletMgt/index">全部</a><i></i></li>
                <li id="income"><a href="/walletMgt/changeDetailType/1" >收入</a><i></i></li>
                <li id="outgoing"><a href="/walletMgt/changeDetailType/2">支出</a><i></i></li>
                </ul>
            </div>
        </div>
          <div class="rztd_cx">
            <div class="rztd_cx_div">时间：</div>
              <input type="text" class="btn_h24 dis_b fl " id="staTime" onClick="WdatePicker()" name="beginTime" value="${queryParam.beginTime}" onkeypress="EnterPress(event)" onkeydown="EnterPress()">
              <div style="margin: 0 5px;" class="rztd_cx_div">-</div>
              <input type="text" class="btn_h24 dis_b fl  mr_18" id="endTime" onClick="WdatePicker()" name="endTime" value="${queryParam.endTime}" onkeypress="EnterPress(event)" onkeydown="EnterPress()">
              <div id="btnSubmit" style="height: 27px; margin-left:10px; line-height: 27px;width: 70px;" class="jbzl_dl_qrdz">
              		查询
              </div>
          </div>
        <!-- 明细 -->
        <table id="mzh_qb_0" class="mt_20 wdqb_table">
          <tbody>
	          <tr class="wdqb_tr">
	            <td><b class="ml_20">交易时间</b></td>
	            <td><b>收入</b></td>
	            <td><b>支出</b></td>
	            <td><b>余额</b></td>
	            <td><b>备注</b></td>
	          </tr>
          <c:if test="${fn:length(pageResult.list)<1}">
          		<tr style="height:200px;">
					<td colspan="5" style="text-align:center">暂无相关数据</td>
				<tr>
          </c:if>
          <c:forEach items="${pageResult.list}" var="walletDetail">
	          <tr>
	            <td>
	            	<div class="wdqb_div ml_20">
	                <p><fmt:formatDate  value="${walletDetail.createTime}" pattern="yyyy/MM/dd"/></p>
	                <p><fmt:formatDate  value="${walletDetail.createTime}" pattern="HH:mm"/></p>
	              </div>
	            </td>
	            <td>
	            	<span class="ft_red">
	            		<c:if test="${walletDetail.type==1 || walletDetail.type==2 || walletDetail.type==3 ||walletDetail.type==4 || walletDetail.type==5 || walletDetail.type==6|| walletDetail.type==11 || walletDetail.type==12}">
	            			<c:set var="temp" value="${walletDetail.amount}" scope="request"/>
	            			<%
				            	Double tempAmountOld = (Double)request.getAttribute("temp");
				            	Double tempAmountNew = Math.abs(tempAmountOld);
				            	out.print("+"+tempAmountNew);
		            		%>
	            			<%-- +${walletDetail.amount} --%>
	            		</c:if>
	            	</span>
	            </td>
	            <td>
	           		 <b class="ft_blue">
	           		 	<c:if test="${walletDetail.type==7 || walletDetail.type==8 || walletDetail.type==9 || walletDetail.type==10|| walletDetail.type==13}">
	            			<%-- ${walletDetail.amount} --%>
	            			<c:set var="temp" value="${walletDetail.amount}" scope="request"/>
	            			<%
				            	Double tempAmountOld = (Double)request.getAttribute("temp");
				            	Double tempAmountNew = Math.abs(tempAmountOld);
				            	out.print("-"+tempAmountNew);
		            		%>
	            		</c:if>
	           		 </b>
	            </td>
	            <td>${walletDetail.restAmount}</td>
	            <td>
	            	<c:choose>
	            		<c:when test="${walletDetail.type==1}">
	            			订单的佣金
	            		</c:when>
	            		<c:when test="${walletDetail.type==2}">
	            			认证的佣金
	            		</c:when>
	            		<c:when test="${walletDetail.type==3}">
	            			供应商货款
	            		</c:when>
	            		<c:when test="${walletDetail.type==4}">
	            			买家收到退款
	            		</c:when>
	            		<c:when test="${walletDetail.type==5}">
	            			分类带来的佣金
	            		</c:when>
	            		<c:when test="${walletDetail.type==6}">
	            			充值
	            		</c:when>
	            		<c:when test="${walletDetail.type==7}">
	            			提现
	            		</c:when>
	            		<c:when test="${walletDetail.type==8}">
	            			购物
	            		</c:when>
	            		<c:when test="${walletDetail.type==9}">
	            			退款扣除
	            		</c:when>
	            		<c:when test="${walletDetail.type==10}">
	            			月结扣税
	            		</c:when>
	            		<c:when test="${walletDetail.type==11}">
	            			返现
	            		</c:when>
	            		<c:when test="${walletDetail.type==12}">
	            			悬赏
	            		</c:when>
	            		<c:when test="${walletDetail.type==13}">
	            			平台服务费
	            		</c:when>
	            	</c:choose>
	            </td>
	          </tr>
          </c:forEach>
        </tbody>
        </table>
        
       <%--  <!-- 收入 -->
        <table id="mzh_qb_1" 
        <c:if test="${type!=2}">
       		 style="display: none;"
        </c:if>
         class="mt_20 wdqb_table">
          <tbody><tr class="wdqb_tr">
            <td><b class="ml_20">交易时间</b></td>
            <td><b>类型</b></td>
            <td><b>流水号</b></td>
            <td><b>收入</b></td>
            <td><b>余额</b></td>
          </tr>
            <c:if test="${fn:length(pageResult.list)<1}">
          		<tr style="height:200px;">
					<td colspan="5" style="text-align:center">暂无相关数据</td>
				<tr>
          </c:if>
           <c:forEach items="${pageResult.list}" var="walletDetail">
	          <tr>
	            <td>
	            	<div class="wdqb_div ml_20">
	                <p><fmt:formatDate  value="${walletDetail.createTime}" pattern="yyyy/MM/dd"/></p>
	                <p><fmt:formatDate  value="${walletDetail.createTime}" pattern="HH:mm"/></p>
	              </div>
	            </td>
	            <td>
	            	<span >
	            		<c:choose>
	            		<c:when test="${walletDetail.type==1}">
	            			订单的佣金
	            		</c:when>
	            		<c:when test="${walletDetail.type==2}">
	            			认证的佣金
	            		</c:when>
	            		<c:when test="${walletDetail.type==3}">
	            			供应商货款
	            		</c:when>
	            		<c:when test="${walletDetail.type==4}">
	            			买家收到退款
	            		</c:when>
	            		<c:when test="${walletDetail.type==5}">
	            			分类带来的佣金
	            		</c:when>
	            		<c:when test="${walletDetail.type==6}">
	            			充值
	            		</c:when>
	            	</c:choose>
	            	</span>
	            </td>
	            <td>
	           		 	 ${walletDetail.wdetailsId}
	            </td>
	            <td class="ft_blue">+${walletDetail.amount}</td>
	            <td class="ft_red">${walletDetail.restAmount}</td>
	          </tr>
          </c:forEach>
        </tbody>
        
        </table>
        <!-- 支出 -->
        <table id="mzh_qb_2" 
        <c:if test="${type!=3}">
       		 style="display:none;"
        </c:if> 
        class="mt_20 wdqb_table">
        	 <tbody>
        	 <tr class="wdqb_tr">
	            <td><b class="ml_20">交易时间</b></td>
	            <td><b>类型</b></td>
	            <td><b>流水号</b></td>
	            <td><b>支出</b></td>
	            <td><b>余额</b></td>
          </tr>
            <c:if test="${fn:length(pageResult.list)<1}">
          		<tr style="height:200px;">
					<td colspan="5" style="text-align:center">暂无相关数据</td>
				<tr>
          </c:if>
           <c:forEach items="${pageResult.list}" var="walletDetail">
	          <tr>
	            <td>
	            	<div class="wdqb_div ml_20">
	                <p><fmt:formatDate  value="${walletDetail.createTime}" pattern="yyyy/MM/dd"/></p>
	                <p><fmt:formatDate  value="${walletDetail.createTime}" pattern="HH:mm"/></p>
	              </div>
	            </td>
	            <td>
	            	<span>
	            		<c:choose>
	            	 	<c:when test="${walletDetail.type==7}">
	            			提现
	            		</c:when>
	            		<c:when test="${walletDetail.type==8}">
	            			购物
	            		</c:when>
	            		<c:when test="${walletDetail.type==9}">
	            			退款扣除
	            		</c:when>
	            		<c:when test="${walletDetail.type==10}">
	            			月结扣税
	            		</c:when>
	            	</c:choose>
	            	</span>
	            </td>
	            <td>
	           		 	 ${walletDetail.wdetailsId}
	            </td>
	            <td class="ft_red">-${walletDetail.amount}</td>
	            <td class="ft_blue">${walletDetail.restAmount}</td>
	          </tr>
          </c:forEach>
        </tbody>
        </table> --%>

			<!-- 分页 -->
				<div class="pull-right">
					<pg:page pageResult="${pageResult}" />
				</div>

      </div>
    </div>
    
</form>

	<script type="text/javascript">
		$(document).ready(function(){
		   	var page = new Pagination( {
				formId: "searcherForm",
				isAjax : false,
				targetId : "navTab",
				submitId:"searchBtn",
			});
			page.init();
		});
	</script>
</body>
</html>
