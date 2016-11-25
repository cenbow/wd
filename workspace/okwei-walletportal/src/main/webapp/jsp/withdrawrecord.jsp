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
<title>我的钱包-提现记录</title>
	<script  type="text/javascript" src="/statics/js/My97/WdatePicker.js"></script>
 
	<script type="text/javascript">
		$(function(){
			<!--查询-->
			$("#btnSubmit").click(function(){
			 		document.forms[0].submit();
			});
		});
		function EnterPress(e){ //传入 event
			var e = e || window.event;
				if(e.keyCode == 13){
				 		document.forms[0].submit();
				}
			} 
	</script>
</head>
<body class="bg_f3">
<form id="searcherForm" name="searcherForm" action="/walletMgt/getWithdrawRecord"> 
	<div style="border: 0px;background: none;" class="fr conter_right">
      <div class="fl conter_right mt_20  bg_white bor_si">
        <div class="wdqb_xxk ndfxs_1-2_border">
            <div class="tab_pc f16">
            <ul>
                <li><a href="javascript:;" >提现记录</a><i></i></li>
                </ul>
            </div>
        </div>
          <div class="rztd_cx">
            <div class="rztd_cx_div">时间：</div>
              <input type="text" class="btn_h24 dis_b fl " id="staTime" onClick="WdatePicker()" name="staTime" value="${staTime}" onkeypress="EnterPress(event)" onkeydown="EnterPress()">
              <div style="margin: 0 5px;" class="rztd_cx_div">-</div>
              <input type="text" class="btn_h24 dis_b fl  mr_18" id="endTime" onClick="WdatePicker()" name="endTime" value="${endTime}" onkeypress="EnterPress(event)" onkeydown="EnterPress()">
              <div id="btnSubmit" style="height: 27px; margin-left:10px; line-height: 27px;width: 70px;" class="jbzl_dl_qrdz">
              		查询
              </div>
          </div>
        <!-- 提现记录-->
         <table class="conter_right_xx_cz_table f14">
         	<tbody>
         		<tr class="ndfxs_1-2_color">
                     <td style="text-indent: 12px;"><b>提现时间</b></td>
                     <td><b>交易金额</b></td>
                     <td><b>手续费</b></td>
                     <td><b>交易账户</b></td>
                     <td><b>交易状态</b></td>
                     <td><b>打款时间</b></td>
                </tr>
	          	<c:if test="${fn:length(pageResult.list)<1}">
	          		<tr>
                         <td colspan="6">
                             <div class="fl mzh_width_100 tc" style="min-height: 150px;line-height: 150px;">没有找到记录，请调整搜索条件</div>
                         </td>
                     </tr>
	          	</c:if>
          		<c:forEach items="${pageResult.list}" var="withdraw">
                 <tr class="ndfxs_1-2_border f12">
                     <td style="text-indent: 12px;"><fmt:formatDate  value="${withdraw.createTime}" pattern="yyyy/MM/dd HH:mm"/></td>
                     <td><span class="rztd_cx_4d9">${withdraw.amount}</span></td>
                     <td>${withdraw.counterFee}</td>
                     <td><c:if test="${withdraw.bankType==2 }">对公账户 </c:if> ${withdraw.bankName } 尾号${withdraw.bankNum }</td>
                     <td> 
	                     <c:if test="${withdraw.state==0}">
		            	 	未打款
		            	 </c:if>
		            	  <c:if test="${withdraw.state==1}">
		            	 	已打款
		            	 </c:if>
		            	  <c:if test="${withdraw.state==-1}">
		            	 	<p>打款失败</p>
		            	 	<p>原因：${withdraw.failReason }</p>
		            	 </c:if>
	            	 </td>
                     <td><fmt:formatDate  value="${withdraw.playTime}" pattern="yyyy/MM/dd HH:mm"/></td>
                 </tr>
                </c:forEach>
         		
         	</tbody>
         </table> 
      </div>
			<!-- 分页 -->
				<div class="pull-right">
					<pg:page pageResult="${pageResult}" />
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
