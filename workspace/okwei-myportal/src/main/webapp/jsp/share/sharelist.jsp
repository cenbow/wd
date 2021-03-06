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
<title>分享页列表</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/statics/css/mzh_dd.css" />
<script type="text/javascript" src="/statics/js/share/sharelist.js"></script>
<style>

/* 分享列表页 */
.mzh_fx{position: relative;}
.mzh_fx_0{position: absolute;left: -65px;top: 30px;border:1px solid #ddd;padding: 10px;background: #fff;display: none;z-index: 10;}
.mzh_fx_0 img{width: 140px;height: 140px;}
/* 分享列表页End */

.shen{float: left;width: 300px;text-align: center;height: 42px;display: block;white-space: nowrap;overflow: hidden;text-overflow: ellipsis;}
</style>
<script type="text/javascript">
$(function(){
	$(".mzh_fx").hover(function(){
	    $(this).children().show();
	},function(){
	    $(this).children().hide();
	})
})  
</script>

</head> 
<body>
	<form id="searcherForm" name="searcherForm" action="sharelist">
      <div class="zhuag_suv bor_si fl bg_white">
        <div class="xh-shurk">
          <ul>
            <li><span>标题：</span>
              <input type="text" class="btn_h24 w164" value="${dto.title}" id="title" name="title"/>
            </li>
        <%--     <li><span>类型：</span>
                <select  class="btn_h28" id="status" name="status" style="width: 127px;">
                  <option value="-1"<c:if test="${dto.status==-1}">selected</c:if>>全部</option>
                  <option value="0"<c:if test="${dto.status==0}">selected</c:if>>待审核</option>
                  <option value="1"<c:if test="${dto.status==1}">selected</c:if>>通过</option>
                  <option value="2"<c:if test="${dto.status==2}">selected</c:if>>不通过</option>
                </select>
            </li> --%>
             <li><span>是否上首页：</span>
                <select  class="btn_h28" id="onHomePage" name="onHomePage" style="width: 127px;">
                  <option value="-1" <c:if test="${dto.onHomePage==-1}">selected</c:if>>全部</option>
                  <option value="1" <c:if test="${dto.onHomePage==1}">selected</c:if>>是</option>
                  <option value="2" <c:if test="${dto.onHomePage==2}">selected</c:if>>否</option>
                </select>
            </li>
            <li>
              <input type="submit" value="搜索" class="btn_submit_two" style="width: 60px;" />
            </li>
          </ul>
        </div>
        <div class="blank1"></div>
      </div>

      <input type="button" value="添加分享" class="btn_submit_two mt_20" onclick="addShare()"  style="width: 80px;" />

      <table class="conter_right mt_20 fl bg_white bor_si f14 line_42 tc">
          <tr class="bg_f3">
              <th>标题</th>
              <th>推广语</th>
              <th>产品数量</th>
              <th>发布/分享时间</th>
              <th>状态</th>
              <th>操作</th>
          </tr>
          	<c:if test="${fn:length(pageResult.list) < 1 }">
				<tr>
					<td colspan="6"  style="border-bottom: 0px; margin-bottom: 0px; border: 1px solid #e7e7e7; border-top: none">没有数据记录</td>
				</tr> 
			</c:if>
			<c:forEach var="share" items="${pageResult.list}" varStatus="varStr">
			 <tr >
				 <td> <div class="shen"><c:if test="${share.isForward==1}">【转】</c:if>${share.title }</div></td>
	              <td><div class="shen">${share.describle }</div></td>
	              <td class="ft_lan">${share.pcount}</td>
	              <td>${share.createTime }</td>
	              <td>
	              <c:if test="${share.onHomePage==1}">
	              	已上首页
	              </c:if>
	               <c:if test="${share.onHomePage==2}">
	            	未上首页
	              </c:if> 
	              </td>
	              <td>
	                  <a href="<%=mydomain%>/share/sharedetails?shareId=${share.shareId}" class="ft_lan">预览</a>
	                  <c:if test="${share.isForward==0}">
	                  <a href="/share/countdetails?shareId=${share.shareId }" class="ft_lan">统计</a>
	                  
	                   <a href="#" class="mzh_fx">
                    	  分享
	                      <span class="mzh_fx_0">
	                          <img src="<%=mydomain%>/share/url?share=${share.shareId}"/>
	                          <font class="ft_c6">扫描后分享到朋友圈</font>
	                      </span>
                 		</a>
	               	  <a href="<%=mydomain%>/share/add?shareid=${share.shareId}" class="ft_lan">编辑</a>
	               	  </c:if> 
	               	  <c:if test="${share.isForward==1}">
	              &nbsp &nbsp &nbsp &nbsp &nbsp  &nbsp     &nbsp     &nbsp  
	               	  </c:if>
	                  <a href="javascript:void(0);" onclick="deleteShare(this,'${share.shareId}','${share.makeWeiID}','${share.shareWeiID}');" class="ft_lan">删除</a>
	              </td>
	           </tr>
			</c:forEach>
      </table>
		<!-- 分页 -->
		<div class="pull-right">
			<pg:page pageResult="${pageResult}" />
		</div>
	</form> 
<!-- 确认删除 -->
<div class="updata_tixian f16 layer_pageContent" style="display: none;" id="win_div_3">
    <div class="fl mzh_100">是否确认删除该分享信息？</div>
</div> 
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
		
		function addShare(){ 
			location.href ="/share/add"; 
		}

	</script>
</body>
</html>