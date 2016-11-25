<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="pg" uri="http://www.okwei.com/pagination"%>
<%@ page import="java.util.ResourceBundle"%>
<%@ page import="com.okwei.util.DateUtils" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";

	String okweidomain = ResourceBundle.getBundle("domain").getString("okweidomain"); 
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>铁杆粉丝军团审核</title>
<script type="text/javascript" src="/statics/js/district.js"></script>
</head>
<body class="bg_f3">
	<form id="searcherForm" action="<%=basePath%>relationMgt/fans" onsubmit="return false;">
		<div class="conter_right_xx">
			<div style="margin-top: 0; height: auto;" class="xh-shurk">
		</div>

		 <div class="fr conter_right">
        <div class="zhuag_suv bor_si fl bg_white">
            <div class="gygl_xxk_t f16 ndfxs_1-2_border">
                <div class="gygl_xxk_yes" style="color:#333;"> 铁杆朋友圈管理<span style="width: 110px;"></span></div>
            </div>
            <div class="xh-shurk">
                <ul>
                    <li><span>微店号：</span>
                        <input type="text" name="weiId" class="btn_h24 w164" value="${dto.weiId }">
                    </li>
                    <li>
                        <span>审核状态：</span>
                        <select required="true" class="btn_h28" style="width: 127px;" id="statu" name="statu">
                            <option value="0" <c:if test="${dto.statu==0}">selected</c:if>>未审核</option>
                            <option value="1" <c:if test="${dto.statu==1}">selected</c:if>>已通过</option>
                            <option value="2" <c:if test="${dto.statu==2}">selected</c:if>>未通过</option>
                        </select>
                    </li>
                    <li><input type="submit" style="width: 80px;" class="btn_submit_two" value="查询" id="searchBtn"></li>
                </ul>
            </div>
            <div class="blank1"></div>
        </div>
      <table class="conter_right mt_20 fl bg_white bor_si f14 line_42 tc">
          <tr class="bg_f3">
              <th>姓名</th>
              <th>联系电话</th>
              <th>年龄</th>
              <th>学历</th>
              <th>QQ</th>
              <th>微信</th>
              <th>所在城市</th>
              <th>申请时间</th>
              <th>状态</th>
          </tr>
          <c:forEach items="${pageResult.list}" var="li" varStatus="status">
          <tr>
              <td><a1 href="#">${li.name }(</a1><a2>${li.fansId }</a2>)</td>
              <input type="hidden" name="fansId" value="${li.fansId }"/>
              <td>${li.phone }</td>
              <td>${li.age }</td>
              <td>${li.degree }</td>
              <td>${li.qq }</td>
              <td>${li.weiXin }</td>
              <td>${li.city }</td>
              <td>${DateUtils.dateToString(li.createTime,"yyyy-MM-dd HH:mm:ss") }</td>
              <td>
                  <c:if test="${li.status==0 }">
                  <a href="#" id="tg">通过</a>
                  <a href="#" id="btg">不通过</a>
                  </c:if>
                  <c:if test="${li.status==1 }">
                  <a href="#">通过</a>
                  </c:if>
                  <c:if test="${li.status==2 }">
                  <a href="#">未通过</a>
                  </c:if> 
              </td>
          </tr>
          </c:forEach>
         
         
      </table>
    </div>
  </div>
			<div class="pull-right">
				<pg:page pageResult="${pageResult}" />
			</div>
</div>
		</div>
	</form>
	<script>
		$(function(){
			$("#tg").click(function(){
				var weiId=$(this).parent().parent().find("a2").html();
				//alert(weiId)
				$.ajax({
					url:"/relationMgt/change?state=1&weiId="+weiId,
					type:"post",
					success:function(data){
						//alert(JSON.stringify(data));
						if(data.Statu=="Success"){
							window.location.href ="/relationMgt/fans";
						}
							window.location.href ="/relationMgt/fans";
					}
				 });
			});
			$("#btg").click(function(){
				var weiId=$(this).parent().parent().find("a2").html();
				//alert(weiId)
				//alert("####")
				$.ajax({
					url:"/relationMgt/change?state=2&weiId="+weiId,
					type:"post",
					success:function(data){
						//alert(JSON.stringify(res));
						if(data.Statu=="Success"){
							window.location.href ="/relationMgt/fans";
						}
						window.location.href ="/relationMgt/fans";
					}
				 });
			});
			
			$("#statu").bind("change",function(){ 
				window.location.href ="/relationMgt/fans?statu="+$(this).val()
			});
			
			
			
			
			
			var page = new Pagination( {
				formId: "searcherForm",
				isAjax : true,
				targetId : "navTab",
				submitId:"searchBtn",
				validateFn:checkInfo
			});
			page.init();
		})
		function checkInfo(){
			var code = $("#weiId").val();
			var re = /^[1-9]+[0-9]*]*$/;
			if (code && !re.test(code)) {
				alert("请输入数字格式的微店号");
				return false;
			}
			return true;
		}
</script>
</body>