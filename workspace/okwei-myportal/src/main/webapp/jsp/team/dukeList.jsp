<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="pg" uri="http://www.okwei.com/pagination"%>
<%@ page import="java.util.ResourceBundle"%>
<%
    String mydomain = ResourceBundle.getBundle("domain").getString("mydomain");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>城主管理</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/statics/js/team/duke.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/statics/js/district.js"></script>
</head>
<body>
<form id="searcherForm" name="searcherForm" action="/teamMgt/dukeList" onsubmit="return true;"> 
  <div class="fr conter_right">
      <div class="zhuag_suv bor_si fl bg_white">
          <div class="gygl_xxk_t f16 ndfxs_1-2_border">
              <div class="gygl_xxk_yes" style="color:#333;">城主管理<span style="width: 60px;"></span></div>
          </div>
        <div class="xh-shurk">
          <ul>
            <li><span>微店号：</span>
              <input type="text"  name="weiid"   value="${dto.weiid}" class="btn_h24 w164" />
            </li>
            <li><span>所在省市：</span>
                <select  id="province" name="province" class="btn_h28" style="width: 100px;">
                  <option>省</option>
                </select>
                <select  id="city" name="city"  class="btn_h28" style="width: 100px;">
                  <option>市</option>
                </select>
                <select id="district" name="district" class="btn_h28" style="width: 100px;">
                  <option>区</option>
                </select>
            </li>
            <li><span>代理类型：</span>
                <select id="agenttype" name="agenttype" class="btn_h28" style="width: 127px;">
                  <option value="-1" <c:if test="${dto.agenttype == -1}">selected</c:if> >全部</option>
                  <option value="1" <c:if test="${dto.agenttype == 1}">selected</c:if> >编内</option>
                  <option value="2" <c:if test="${dto.agenttype == 2}">selected</c:if> >编外</option>
                </select>
            </li>
            <li><input type="submit" value="查询" class="btn_submit_two" style="width: 80px;"></li>
          </ul>
        </div>
        <div class="blank1"></div>
      </div>
		
		
		
      <table class="conter_right mt_20 fl bg_white bor_si f14 line_42 tc">
          <tr class="bg_f3">
              <th>店铺名</th>
              <th>代理地区</th>
              <th>联系电话</th>
              <th>QQ</th>
              <th>代理类型</th>
              <th>申请时间</th>
              <th>缴费金额</th>
          </tr>
          <c:if test="${page !=null && page.list !=null && page.list.size()>0 }"> 
          <c:forEach items="${page.list }" var="item">
          <tr>
              <td>${item.shopname }(${item.weiid })</td>
              <td>${item.showarea }</td>
              <td>${item.mobile }</td>
              <td>${item.qq }</td>
              <td><c:if test="${item.type==1 }">编内</c:if><c:if test="${item.type==2 }">编外</c:if></td>
              <td>${item.showapplytime }</td>
              <td>${item.bond }</td>
          </tr>
         
          </c:forEach>
          </c:if>
      </table>
      <!-- 分页 -->
		<div class="pull-right">
			<pg:page pageResult="${page}" />
		</div>
      <c:if test="${page ==null || page.list==null || page.list.size() <1 }">
        	<div style="width:100%;height:400px;text-align:center;font-size:18px;line-height:400px;">
        		暂无相关数据
        	</div>
        </c:if>
    </div>
    <input type="hidden" id="hidprovince" value="${dto.province }">
    <input type="hidden" id="hidcity" value="${dto.city }">
    <input type="hidden" id="hiddistrict" value="${dto.district }">
    </form>
</body>
</html>