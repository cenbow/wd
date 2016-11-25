<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.*"%>
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
<style type="text/css">
.wdqb_div_1_dw span {
    color: #666;
    float: left;
    margin: 3px 0 0 8px;
    width: 166px;
}
</style>
<title>我的钱包-结算明细</title>
	<script  type="text/javascript" src="/statics/js/My97/WdatePicker.js"></script>
 
	<script type="text/javascript">
		$(function(){
			<!--选项卡样式切换-->
			styleChange("${state}");
			<!--查询-->
			$("#btnSubmit").click(function(){
					document.forms[0].action="/walletMgt/changeSettleState/${state}" 
			 		document.forms[0].submit();
			});
			<!--详情悬浮框显示-->
			$(".wdqb_div_1").mouseenter(function(){
				var _this =$(this);
				if($(this).attr("status") =="Success"){
					$(this).children("div:gt(0)").show();
					return;
				}
				var params="";
				$(this).children("div:gt(0)").html("");
				$.ajax({
					url:"/walletMgt/getSettleAccountDetail",
					type:"post",
					data:{"detailId":$(this).children("div:first").text()},
					success:function(result){
						if(result.state =="Success"){
							$(_this).attr("status","Success");
						}
						if(result.obj.totalPrice==0){
							params+="<span>金额：未能加载该数据</span>";
						}else{
							params+="<span>金额："+result.obj.totalPrice+"</span>";
						}
						if(result.obj.tradingDetails!=null&&result.obj.tradingDetails!=""){
							 if(result.obj.tradingDetails.type==1){
								 params+="<span>成交微店："+result.obj.shop+"</span>";
								 if(result.obj.commision==0){
									 params+="<span>交易佣金：未能加载该数据</span>";
								 }else{
									 params+="<span>交易佣金："+result.obj.commision+"</span>";
								 }
							 }
							 if(result.obj.tradingDetails.type==2){
								 params+="<span>进驻商家："+result.obj.shop+"</span>";
							 }
							 params+="<span>交易时间："+dateToStr(result.obj.createTime)+"</span>";
							 
								/*  switch(result.tradingDetails.state){
									 case 0: params+="<span>交易状态：未放款</span>"; break;
									 case 1: params+="<span>交易状态：交易完成</span>"; break;
									 case 2: params+="<span>交易状态：退款中</span>"; break;
									 case 3: params+="<span>交易状态：已退款</span>"; break;
									 case 4: params+="<span>交易状态：收货冻结</span>"; break;
									 case 5: params+="<span>交易状态：等待扣税</span>"; break;
									 default: params+="<span></span>"; break;
								 } */
								 switch(result.obj.tradingDetails.state){
								 case 1: params+="<span>交易状态：已到账</span>"; break;
								 case 0:  case 2:   case 3:   case 4:   case 5: params+="<span>交易状态：未到账</span>"; break;
								 default: params+="<span></span>"; break;
							 }
						}
						 params+="<span>备注："+result.obj.comment+"</span>";
						 $(_this).children("div:gt(0)").append(params);
					}
				})
				$(this).children("div:gt(0)").show();
				
			}).mouseleave(function(){
				$(this).children("div:gt(0)").hide();
			}) 
			
		});
		<!--选项卡样式切换-->
		function styleChange(state){
			if(state==1){
				$("#notaccount").siblings("li").removeClass("now");
				$("#notaccount").addClass("now");
			} 
			if(state==2){
				$("#finish").siblings("li").removeClass("now");
				$("#finish").addClass("now");
			} 
		}
		function EnterPress(e){ //传入 event
			var e = e || window.event;
				if(e.keyCode == 13){
					document.forms[0].action="/walletMgt/changeSettleState/${state}" 
				 		document.forms[0].submit();
				}
			}
		<!--日期转换为字符串格式-->
		function dateToStr(datetime){ 
			var temp= new Date(datetime);
			 var year = temp.getFullYear();
			 var month =temp.getMonth()+1;//js从0开始取 
			 var date = temp.getDate(); 
			 var hour = temp.getHours(); 
			 var minutes = temp.getMinutes(); 
			 if(minutes<10){
				 var time  = year+"-"+month+"-"+date+"  "+hour+":0"+minutes;
				 return  time;
			 }
			 var time2  = year+"-"+month+"-"+date+"  "+hour+":"+minutes;
			 return time2;
		}
		
		 
		 
	</script>
</head>
<body class="bg_f3">
<form id="searcherForm" name="searcherForm" 
	<c:choose>
		<c:when test="${fn:contains(action,'.ajax')}">
			action="${fn:substring(action,0,action.length()-5)}"
		</c:when>
		<c:otherwise>
			action=${action}
		</c:otherwise>
	</c:choose>
> 
	<div style="border: 0px;background: none;" class="fr conter_right">
      <div class="fl conter_right mt_20  bg_white bor_si">
        <div class="wdqb_xxk ndfxs_1-2_border">
            <div class="tab_pc f16">
            <ul>
                <li id="notaccount"><a href="/walletMgt/changeSettleState/1" >未到账</a><i></i></li>
                <li id="finish"><a href="/walletMgt/changeSettleState/2">已完成</a><i></i></li>
                </ul>
            </div>
        </div>
          <div class="rztd_cx">
            <div class="rztd_cx_div">时间：</div>
              <input type="text" class="btn_h24 dis_b fl " id="staTime" onClick="WdatePicker()" name="staTime" value="${staTime}" onkeypress="EnterPress(event)" onkeydown="EnterPress()" >
              <div style="margin: 0 5px;" class="rztd_cx_div">-</div>
              <input type="text" class="btn_h24 dis_b fl  mr_18" id="endTime" onClick="WdatePicker()" name="endTime" value="${endTime}" onkeypress="EnterPress(event)" onkeydown="EnterPress()" >
              <button type="submit" id="searchBtn" style="height: 27px; margin-left:10px; line-height: 27px;width: 70px;" class="jbzl_dl_qrdz">
              		查询
              </button>
          </div>
        <!-- 未到账和已完成-->
        <table id="mzh_qb_0"  
        class="mt_20 wdqb_table">
          <tbody>
	          <tr class="wdqb_tr">
	            <td><b class="ml_20">交易时间</b></td>
	            <td><b>金额</b></td>
	            <td><b>交易状态</b></td>
	            <td><b>类型</b></td>
	            <td><b>操作</b></td>
	          </tr>
          <c:if test="${fn:length(pageResult.list)<1}">
          		<tr style="height:200px;">
					<td colspan="5" style="text-align:center">暂无相关数据</td>
				<tr>
          </c:if>
          <c:forEach items="${pageResult.list}" var="tradeDetail" varStatus="status">
          		
	          <tr>
	            <td>
	            	<div class="wdqb_div ml_20"> 
	                <p><fmt:formatDate  value="${tradeDetail.createTime}" pattern="yyyy/MM/dd"/></p>
	                <p><fmt:formatDate  value="${tradeDetail.createTime}" pattern="HH:mm"/></p>
	              </div>
	            </td>
	            <td>
	            	<span class="ft_red">
	            		<c:if test="${tradeDetail.type==1 || tradeDetail.type==2 || tradeDetail.type==3 ||tradeDetail.type==4 ||tradeDetail.type==12|| tradeDetail.type==5 || tradeDetail.type==6}">
	            			<c:set var="temp" value="${tradeDetail.amount}" scope="request"/>
	            			<%
				            	Double tempAmountOld = (Double)request.getAttribute("temp");
				            	Double tempAmountNew = Math.abs(tempAmountOld);
				            	out.print("+"+tempAmountNew);
		            		%>
	            			<%-- +${tradeDetail.amount} --%>
	            		</c:if>
	            		<c:if test="${tradeDetail.type==7 || tradeDetail.type==8 || tradeDetail.type==9 ||tradeDetail.type==10 }">
	            			<c:set var="temp" value="${tradeDetail.amount}" scope="request"/>
	            			<%
				            	Double tempAmountOld = (Double)request.getAttribute("temp");
				            	Double tempAmountNew = Math.abs(tempAmountOld);
				            	out.print("-"+tempAmountNew);
		            		%>
	            			<%-- ${tradeDetail.amount} --%>
	            		</c:if>
	            	</span>
	            </td>
	            <td>
	            	<%-- <c:choose>
	            		<c:when test="${tradeDetail.state==0}">
	            			未放款
	            		</c:when>
	            		<c:when test="${tradeDetail.state==2}">
	            			退款中
	            		</c:when>
	            		<c:when test="${tradeDetail.state==4}">
	            			收货冻结
	            		</c:when>
	            		<c:when test="${tradeDetail.state==5}">
	            			等待扣税
	            		</c:when>
	            		<c:when test="${tradeDetail.state==1}">
	            			交易完成
	            		</c:when>
	            		<c:when test="${tradeDetail.state==3}">
	            			已退款
	            		</c:when>
	            		
	            	</c:choose> --%>
	            
	            	<c:choose>
	            		<c:when test="${tradeDetail.state==1}">
	            			已到账
	            		</c:when>
	            		<c:otherwise>
	            			未到账<br>
	            			<c:if test="${tradeDetail.state==0}">
		            			 <c:set var="temp" value="${tradeDetail.createTime}" scope="request"/>
		            			 <%
		            			 Calendar cal = Calendar.getInstance();
		            			 cal.setTime((Date)request.getAttribute("temp"));
		            			 cal.add(Calendar.DATE, 14);
		            			 out.print("<span style='color:gray'>预计"+new SimpleDateFormat("yyyy年-MM月-dd日").format(cal.getTime())+"到账</span>");
		            			 %>
	            			</c:if>
	            			<c:if test="${tradeDetail.state==4}">
		            			 <c:set var="temp" value="${tradeDetail.createTime}" scope="request"/>
		            			 <%
		            			 Calendar cal = Calendar.getInstance();
		            			 cal.setTime((Date)request.getAttribute("temp"));
		            			 cal.add(Calendar.DATE, 7);
		            			 out.print("<span style='color:gray'>预计"+new SimpleDateFormat("yyyy年-MM月-dd日").format(cal.getTime())+"到账</span>");
		            			 %>
	            			</c:if>
	            			<c:if test="${tradeDetail.state==5}">
		            			 <c:set var="temp" value="${tradeDetail.inTime}" scope="request"/>
		            			 <%
		            			 Calendar cal = Calendar.getInstance();
		            			 cal.setTime((Date)request.getAttribute("temp"));
		            			 Calendar current = Calendar.getInstance();
		            			 current.setTime(new Date());
		            			 if(current.getTime().getMonth()==cal.getTime().getMonth()){
		            				 cal.set(cal.get(Calendar.YEAR),cal.getTime().getMonth()+1,1);
		            				 out.print("<span style='color:gray'>预计"+new SimpleDateFormat("yyyy年-MM月-dd日").format(cal.getTime())+"到账</span>");
		            			 }else{
		            				 cal.add(Calendar.DATE, 5);
			            			 out.print("<span style='color:gray'>预计"+new SimpleDateFormat("yyyy年-MM月-dd日").format(cal.getTime())+"到账</span>");
		            			 }
		            			 %>
	            			</c:if>
	            		</c:otherwise>
	            	</c:choose>
	            </td>
	            <td>
	            	<c:choose>
	            		<c:when test="${tradeDetail.type==1}">
	            			订单的佣金
	            		</c:when>
	            		<c:when test="${tradeDetail.type==2}">
	            			认证的佣金
	            		</c:when>
	            		<c:when test="${tradeDetail.type==3}">
	            			供应商货款
	            		</c:when>
	            		<c:when test="${tradeDetail.type==4}">
	            			买家收到退款
	            		</c:when>
	            		<c:when test="${tradeDetail.type==5}">
	            			分类带来的佣金
	            		</c:when>
	            		<c:when test="${tradeDetail.type==6}">
	            			充值
	            		</c:when>
	            		<c:when test="${tradeDetail.type==7}">
	            			提现
	            		</c:when>
	            		<c:when test="${tradeDetail.type==8}">
	            			购物
	            		</c:when>
	            		<c:when test="${tradeDetail.type==9}">
	            			退款扣除
	            		</c:when>
	            		<c:when test="${tradeDetail.type==10}">
	            			月结扣税
	            		</c:when>
	            		<c:when test="${tradeDetail.type==12}">
	            			悬赏
	            		</c:when>
	            	</c:choose>
	            </td>
	            <td>
	            	<div class="wdqb_div_1">
							<div style="display:none" class="detailId">${tradeDetail.detailId}</div>
							<span class="wdqb_div_1_span">详情</span>
							<div class="wdqb_div_1_dw" style="display: none;">
								 
						</div>
					</div>
	            </td>
	          </tr>
	          
	        

          </c:forEach>
        </tbody>
        </table>
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
