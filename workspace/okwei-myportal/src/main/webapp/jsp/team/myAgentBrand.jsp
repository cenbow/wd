<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.ResourceBundle"%>
<%
	String paydomain = ResourceBundle.getBundle("domain").getString(
			"paydomain");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我代理的品牌</title>
</head>
<body>
 	<div class="fr conter_right">
      <table class="conter_right fl bg_white bor_si f14 line_42 tc">
          <tr class="bg_f3">
              <th>代理品牌</th>
              <th>代理身份</th>
              <th>申请日期</th>
              <th>缴费金额</th>
              <th>缴费状态</th>
          </tr>
           <c:if test="${page !=null  && page.size()>0 }"> 
          <c:forEach items="${page }" var="item">
          <tr>
              <td>${item.brandname }</td>
              <td>${item.agenttype }</td>
              <td>${item.showapplytime }</td>
              <td>${item.bond }</td>
              <td>
              	<c:choose>
              		<c:when test="${item.status==1 }">
              			已缴费
              		</c:when>
              		<c:when test="${item.status==3 }">
              			未缴费 &nbsp;<a href="<%=paydomain %>/pay/cashier?orderNo=${item.payOrderId}">去缴费</a>
              		</c:when>
              		<c:when test="${item.status==-9 }">
              			已缴费（0元）
              		</c:when>
              		<c:otherwise>
              			不通过
              		</c:otherwise>
              	</c:choose>
              </td>
          </tr>
          </c:forEach>
          </c:if>
        
         
      </table>
      <c:if test="${page ==null || page.size() <1 }">
        	<div style="width:100%;height:400px;text-align:center;font-size:18px;line-height:400px;">
        		暂无相关数据
        	</div>
        </c:if>
    </div>
</body>
</html>