<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="pg" uri="http://www.okwei.com/pagination"%>
<%@ page import="java.util.ResourceBundle"%>
<%
    String mydomain = ResourceBundle.getBundle("domain").getString("mydomain");
	String sellerdomain = ResourceBundle.getBundle("domain").getString("sellerdomain");
	String okweidomain = ResourceBundle.getBundle("domain").getString("okweidomain");
	String jsdomain = ResourceBundle.getBundle("domain").getString("jsdomain");
	String cssdomain = ResourceBundle.getBundle("domain").getString("cssdomain");
	String imgdomain = ResourceBundle.getBundle("domain").getString("imgdomain");
	String detaildomain = ResourceBundle.getBundle("domain").getString("detaildomain");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>分享页统计</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/statics/css/glbdy.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/statics/css/index.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/statics/css/mzh_dd.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/statics/css/jumei_usercenter.min.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/statics/js/share/sharedetails.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/statics/js/common/share.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/statics/js/layer/layer.min.js"></script>
 <style>
    .conter_right tr{border-bottom: 1px solid #ddd;}
    .conter_right tr td{border-right: 1px solid #ddd;}
    .xh-shurk{height: auto;}
	.shen{float: left;width: 300px;text-align: center;height: 42px;display: block;white-space: nowrap;overflow: hidden;text-overflow: ellipsis;}
</style>
</head>
<body>	 
<input type="hidden" value="<%=mydomain%>" name="mydomain" id="mydomain">
	<form id="searcherForm" name="searcherForm"  action="sharecount">
	  <div class="fr conter_right">
      <div class="zhuag_suv bor_si fl bg_white">
        <div class="xh-shurk">
          <ul>
            <li><span>标题：</span>
              <input type="text" class="btn_h24 w164" value="${dto.title}" id="title" name="title"/>
            </li>
            <li><span>统计时间段：</span> 
                  <select  class="btn_h28" id="dateTimeType" name="dateTimeType" style="width: 127px;">
                  <option value="0" <c:if test="${dto.dateTimeType==0}">selected</c:if>>今日</option>
                  <option value="2" <c:if test="${dto.dateTimeType==2}">selected</c:if>>本周</option>
                  <option value="3" <c:if test="${dto.dateTimeType==3}">selected</c:if>>本月</option>
                   <option value="4" <c:if test="${dto.dateTimeType==4}">selected</c:if>>近三个月</option>
                    <option value="5" <c:if test="${dto.dateTimeType==5}">selected</c:if>>近六个月</option>
                </select>
            </li>
            <li>
              <input type="submit" value="搜索" class="btn_submit_two" style="width: 60px;" />
            </li>
          </ul>
        </div>
      </div>
      <div class="zhuag_suv bor_si fl bg_white mt_20">
        <div class="xh-shurk">
          <ul>
              <li class="mzh_100 fw_b" style="color: #333;">分享页面数据统计： </li>
              <li class="mzh_100">
                  <span class="fl mr_20">总分享页面： ${pageResult.totalCount}</span>
                  <span class="fl mr_20">总分享产品数： ${pcount}</span>
              </li>
              <li class="mzh_100">
                  <span class="fl mr_20">总浏览量：  ${pv}</span>
                  <span class="fl mr_20">总分享次数： ${sv}</span>
                  <span class="fl mr_20">总成交笔数：  ${vol}</span>
                  <span class="fl mr_20">总成交金额： ￥  ${turnover}</span>
              <%--     <span class="fl mr_20">总佣金： ￥ ${commission}</span> --%>
              </li>
          </ul>
        </div>
      </div>
      <table class="conter_right mt_20 fl bg_white bor_si f14 line_42 tc">
          <tr>
              <th colspan="7" class="tl pl20">分享页面数据详情：</th>
          </tr>
            <tr class="bg_f3">
              <th>标题</th>
              <th>产品数量</th>
            <!--   <th>浏览量</th>
              <th>分享次数</th> -->
              <th>成交笔数</th>
              <th>成交金额</th>
           <!--    <th>佣金(￥)</th> -->
              <th>操作</th>
          </tr>
           <c:if test="${fn:length(pageResult.list)<1}">
						<tr class="">
							<td colspan="8" align="center">暂时没有符合条件的数据</td>
						</tr>
		  </c:if>
		  <c:if test="${fn:length(pageResult.list)>0}">
	        <c:forEach var="share" items="${pageResult.list}" varStatus="varStr">
	          <tr>
	              <td><div class="shen">${share.title }</div></td>
	              <td>${share.pcount }</td>
	            <%--   <td>${share.pv }</td>
	              <td>${share.sv }</td> --%>
	              <td> 
	                <c:if test="${share.vol==null}">0</c:if>
	                <c:if test="${share.vol!=null}">${share.vol}</c:if>
	              </td>
	              <td> 
	                <c:if test="${share.vol==null}">0</c:if>
	                <c:if test="${share.vol!=null}">
	                <fmt:formatNumber type="number" value=" ${share.vol*share.turnover} " maxFractionDigits="2"/>
	                </c:if>
	              </td>
	             <%--  <td>${share.commission }</td> --%>
	              <td>
	                  <a href="/share/countdetails?shareId=${share.shareId }" class="ft_lan">统计明细</a>
	              </td>
	          </tr>
	         
	         </c:forEach> 
         </c:if>
          
      </table>
    </div>

    <!-- 分页 -->
		<div class="pull-right">
			<pg:page pageResult="${pageResult}" />
		</div>
	</form> 
    	<script>
		$(function() {
			var page = new Pagination({
				formId : "searcherForm",
				isAjax : false,
				targetId : "navTab",
				submitId : "searchBtn",
				validateFn : false
			});
			page.init();
			 
		});
		</script>
</body>
</html>