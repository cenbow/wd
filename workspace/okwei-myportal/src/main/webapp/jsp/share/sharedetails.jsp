<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="pg" uri="http://www.okwei.com/pagination"%>
<%@ page import="java.util.ResourceBundle"%>
<%
	String productdomain = ResourceBundle.getBundle("domain").getString("productdomain");
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
<title>分享页详情</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/statics/css/jumei_usercenter.min.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/statics/js/share/sharedetails.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/statics/js/common/share.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/statics/js/layer/layer.min.js"></script>
<script>
	function shareto1(ftype,shareId,title1,source2){
		var title=title1;  
		var pageurl="<%=mydomain %>/share/sharedetails?shareId="+shareId;
		var source=source2;
		 $.ajax({ 
			    url: "/share/addShareCount",
			    type: "post",
			    data:{//分享Id
			    	  shareId:shareId, 
			    	},
			    dataType : 'json',
			    success: function (data) { 
		        	if(data.state == -1){ 
		        	 	alert(data.msg);
				    } 
			    } ,
			    error: function () {
			        alert("数据提交失败！请稍后重试！");
			    }
			});  
		switch(ftype)
		{
			case 0:{
				ShareToQzone(title, pageurl, source);
				break;}
			case 1:{
				ShareToTencent(title, pageurl, source);
				break;}
			case 2:{
				ShareToSina(title, pageurl, source);
				break;}
			default:{
				alert("分享类型错误！");
				break;
			}
		}
		/* switch(ftype)
		{
			case 0:{
			
				ShareToQzone(title, pageurl, source);
				break;}
			case 1:{
				ShareToTencent(title, pageurl, source);
				break;}
			case 2:{
				ShareToSina(title, pageurl, source);
				break;}
			default:{
				alert("分享类型错误！");
				break;
			}
		} */
	}
</script>
<style>
.mzh_sp{height: 350px;}
.mzh_sp_span font:first-child{text-decoration: none;}
</style>
</head>
<body style="float: left;width: 100%;overflow-x: hidden;">
<input type="hidden" value="<%=mydomain%>" name="mydomain" id="mydomain">
	<form id="searcherForm" name="searcherForm"  action="sharedetails">
	<input type="hidden" value="${shareId}" name="shareId" id="shareId" /> 
     <div class="fr conter_right">
        <div class="zhuag_suv bor_si fl bg_white" style="min-height: 710px;">
            <div class="p10">
                <div class="fl outermost" style="position: relative;">
                    <a href="<%=mydomain%>/share/sharelist" class="ft_lan f14" style="position: absolute;left: 0px;top: 0px;">&lt;返回列表</a>
                    <div class="fl tc mt_30" style="width: 1009px;">
                        <h2 class="f24 mb_20"> ${sMainDataDetails.title }</h2>
                        <h4 class="f16" style="width:700px;margin:0px auto;font-weight: normal;">${sMainDataDetails.describle}</h4>
                    </div>
                    <div style="position: absolute;right: 0px;top: 10px;">
                        <img src="<%=mydomain%>/share/url?share=${shareId}" width="100">
                        <p>扫描后分享到朋友圈</p>
                    </div>
                   <%--  <div id="mzh_fenxiang" class="fr mr_10">
                        <div class="mzh_fenxiang_no" id="aaa">
                            <img src="<%=request.getContextPath()%>/statics/images/fenxiang_icon.png" style="margin: 0px;">
                            <div class="mzh_fenxiang" style="display: none;left: -90px;">
                                <a href="javascript:shareto1(0,${sMainDataDetails.shareId},'${sMainDataDetails.title}','${sMainDataDetails.describle}');"><img src="http://base.okimgs.com/images/TX_kj.gif"></a>
                                <a href="javascript:shareto1(1,${sMainDataDetails.shareId},'${sMainDataDetails.title}','${sMainDataDetails.describle}');"><img src="http://base.okimgs.com/images/TX_wb.gif"></a>
                                <a href="javascript:shareto1(2,${sMainDataDetails.shareId},'${sMainDataDetails.title}','${sMainDataDetails.describle}');"><img src="http://base.okimgs.com/images/XL_wb.gif"></a>
                            </div>
                        </div>
                    </div>   --%>
                </div>
                <div class="fl outermost mt_10">
                
                
             <%--       <c:forEach var="share" items="${sMainDataDetails.productsList.list}" varStatus="varStr">
		                   <div class="mzh_sp">
			                   <a href="<%=productdomain %>/product?shareid=${shareId}&pid=${share.productId}">
			                        <img height="180px" width="180px" src="${share.defaultImg}"/>
			                        <div style="color:#333;float:left;height:40px;overflow: hidden;">${share.productTitle}</div>  
			                    </a>
			                    <span class="mzh_sp_span"><font class="ml_10 f12  "style="color: #f10;">￥ ${share.presentPrice}</font> <font class="ml_10 f12 fr" style="color: #666;">￥ ${share.originalPrice}</font></span>
		                    </div>
                   </c:forEach>　 --%>
                 <c:forEach var="share" items="${sMainDataDetails.productsList.list}" varStatus="varStr">
		         <c:if test="${sMainDataDetails.shareType!=2}">
				 <div class="mzh_sp">
                       <a href="<%=productdomain %>/product?shareid=${shareId}&pid=${share.productId}">
                       <img height="180px" width="180px" src="${share.defaultImg}"/>
                       <div style="color:#333;float:left;height:40px;overflow: hidden;">${share.productTitle}</div>  
			           </a>
	                   <span class="mzh_sp_span"><font class="ml_10 f12  "style="color: #f10;">￥ ${share.presentPrice}</font> <font class="ml_10 f12 fr" style="color: #666;">￥ ${share.originalPrice}</font></span>
                     <c:if test="${sMainDataDetails.shareType==3}">
                  		<div class="mzh_sp_div c9">${share.shareDescription}</div>
                  	 </c:if>
                  </div>
                  </c:if>
                <c:if test="${sMainDataDetails.shareType==2}">
		         <div class="fl outermost mt_30">
		            <div class="mzh_sp" style="width: 300px;height: auto;margin: 0px auto;float: none;">
		                <img src="${share.defaultImg}" width="300" height="300">
                        <div class="mzh_sp_div" style="height:32px;">${share.productTitle}</div>
                        <span class="mzh_sp_span ft_red">￥${share.presentPrice} <font class="ml_10 f12 ft_c3">￥${share.originalPrice}</font></span>
                    </div>
		        </div>
		         <div class="fl outermost mt_20">
		            <div style="margin: 0px auto;width: 1130px;">
		             <c:forEach var="productDetails" items="${share.productPictureList}" varStatus="varStr">
		                <div class="mzh_sp">
		                    <img src="${productDetails.productPicture}" height="180" width="180"/>
		                    <div class="mzh_sp_div">${productDetails.shareDescription}</div>
		                </div>
		              </c:forEach>
		            </div>
		            <div class="blank"></div>
		        </div>
                </c:if>
            </c:forEach>
                   
                   
                   
           		 </div>
        </div>
    </div>
    </div>
    <!-- 分页 -->
		<div class="pull-right">
			<pg:page pageResult="${sMainDataDetails.productsList}" />
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