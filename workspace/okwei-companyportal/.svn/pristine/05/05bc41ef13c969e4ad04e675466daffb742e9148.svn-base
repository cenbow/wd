<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="java.util.ResourceBundle"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
String path = request.getContextPath();
String productdomain = ResourceBundle.getBundle("domain").getString("productdomain");
String okweidomain = ResourceBundle.getBundle("domain").getString("okweidomain");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="http://base1.okwei.com/css/company/mzh_dd.css" />
<title>代理区</title>
<script>
$(function(){
	$(".mzh_dlq_l_xx_r ul li:nth-child(4n)").css("margin-right", "0px");
})
</script>
<style>
div.mzh_dlq_l {overflow: inherit;}
</style>
</head>
<body>
<div class="fl w">
    <div class="line3 mar1200">当前位置： <a href="#" class="c3">首页</a> &gt; <span class="tb">代理区</span></div>
  </div>
  <div class="fl w">
    <div class="mar1200">
      <div class="fl w"><img src="http://base1.okwei.com/images/img_cz_30.jpg" class="fl w"/></div>

      <!-- 代理区 -->
      <div class="mzh_dlq mt20">
      <div class="mzh_dlq_l">
      <c:if test="${fn:length(union) < 1 && fn:length(indep)<1}">
						<div class="null_coues fl pb30">
					      	<p class="f18 tc">没有符合条件的数据！</p> 
					     </div>
	 </c:if>
	   <c:forEach var="union" items="${union }">
        <div class="mzh_dlq_l_xx">
        
          <div class="mzh_dlq_l_xx_l">
            <ul>
              <li class="mb20"><a href="${union.agenturl}" class="slh fl f16 fb cB">${union.user.brandname}</a></li>
              <li class="mb10"><a href="${union.agenturl}"><img src="${union.user.imgurl }" width="78" height="78"/></a></li>
              <li>分销产品数：${union.user.productcount }</li>
              <li>代理人数：${union.user.agentcount }</li>
              <li class="mt20">
                <a href="#" class="ico_map_3 fl mt5 mr10"></a>
                <span class="erweima_8_18">
	                <div class="div-posabues">
                      <div class="eweim-imgs"> <img src="http://api.okwei.com/api/v3/Client/VerifierQrcode?size=10&amp;weino=${union.user.weiid }&amp;itype=5"> </div>
                    </div>
                </span>
                <a href="${union.user.agenturl }" class="mzh_jxcz">代理申请</a>
              </li>
            </ul>
          </div>
          <div class="mzh_dlq_l_xx_r">
         	 <div class="fr w">
  				<a class="fr cB" href="${union.agenturl}">更多&gt;&gt;</a>
			</div>
            <ul>
             <c:forEach var="product" items="${union.product }">
              <li>
                 <a href="<%=productdomain %>/product?pid=${product.productid}" target="_black"><img src="${product.imgurl }" width="170" height="170" class="fl"/></a>
                <b class="cR f18 fl w mb5 mt5">￥${product.price }</b>
                <a href="<%=productdomain %>/product?pid=${product.productid}" target="_black" class="mzh_dlq_l_xx_r_a">${product.title }</a>
              </li>
              </c:forEach>             
            </ul>
          </div>
          
        </div>
        </c:forEach>
      <c:forEach var="indep" items="${indep }">
        <div class="mzh_dlq_l_xx">
        
          <div class="mzh_dlq_l_xx_l">
            <ul>
              <li class="mb20"><a href="${indep.agenturl}" class="slh fl f16 fb cB">${indep.user.brandname}</a></li>
              <li class="mb10"><a href="${indep.agenturl}"><img src="${indep.user.imgurl }" width="78" height="78"/></a></li>
              <li>分销产品数：${indep.user.productcount }</li>
              <li>代理人数：${indep.user.agentcount }</li>
              <li class="mt20">
                <a href="#" class="ico_map_3 fl mt5 mr10"></a>
                <span class="erweima_8_18">
	                <div class="div-posabues">
                      <div class="eweim-imgs"> <img src="http://api.okwei.com/api/v3/Client/VerifierQrcode?size=10&amp;weino=${indep.user.weiid }&amp;itype=5"> </div>
                    </div>
                </span>
                <c:if test="${indep.daili==0 }">
                <a href="${indep.user.agenturl }" class="mzh_jxcz">代理申请</a>
                </c:if>
                <c:if test="${indep.daili==1 }">
                <a href="http://www.<%=okweidomain %>/castellan/rdl?" class="mzh_jxcz">推荐他人</a>
                </c:if>
              </li>
            </ul>
          </div>
          <div class="mzh_dlq_l_xx_r">
           	<div class="fr w">
  				<a class="fr cB" href="${indep.agenturl}">更多&gt;&gt;</a>
			</div>
            <ul>
             <c:forEach var="inproduct" items="${indep.product }">
              <li>
                <a href="<%=productdomain %>/product?pid=${product.productid}" target="_black"><img src="${inproduct.imgurl }" width="170" height="170" class="fl"/></a>
                <b class="cR f18 fl w mb5 mt5">￥${inproduct.price }</b>
                <a href="<%=productdomain %>/product?pid=${inproduct.productid}" target="_black" class="mzh_dlq_l_xx_r_a">${inproduct.title }</a>
              </li>
              </c:forEach>             
            </ul>
          </div>
          
        </div>
        </c:forEach>
       
	 </div>
      <div class="mzh_dlq_r">
        <div class="mzh_dlq_r_1">
          <div class="mzh_dlq_r_1_xx">
            <ul>
              <li><img src="http://base2.okwei.com/images/img_cz_31.png" class="mt10 mb5"/><p>稳定货源</p></li>
              <li><img src="http://base3.okwei.com/images/img_cz_32.png" class="mt10 mb5"/><p>价格专享</p></li>
              <li><img src="http://base1.okwei.com/images/img_cz_33.png" class="mt10 mb5"/><p>服务保障</p></li>
            </ul>
          </div>
        </div>
        <div class="mzh_dlq_r_1">
          <div class="fl w pt10 pb10 line-b"><img src="http://base2.okwei.com/images/img_cz_34.jpg" class="fl ml10 mr5" style="margin-top: 4px;"/><span class="f16">代理申请流程</span></div>
          <div class="mzh_dlq_r_1_dlsqlc f14">
            <ul>
              <li class="mb5"><b class="cR">1.</b>筛选中意品牌商</li>
              <li class="mb5"><b class="cR">2.</b>选择代理城市竞选城主</li>
              <li class="mb5"><b class="cR">3.</b>邀请团队支持你 ，仅需有30人支持可成为城主候选人 ，100人支持可成为城主</li>
              <li class="mb5"><b class="cR">4.</b>竞选成功，代理销货</li>
            </ul>
          </div>
        </div>
        <div class="mzh_dlq_r_1">
          <div class="fl w pt10 pb10 line-b"><img src="http://base1.okwei.com/images/img_cz_35.jpg" class="fl ml10 mr5" style="margin-top: 4px;"/><span class="f16">明星商品</span></div>
          <div class="pl5 pr5 fl">
          <c:forEach var="top" items="${top }">
            <div class="mzh_dlq_r_1_mxsp">
              <ul>
                <li><a href="<%=productdomain %>/product?pid=${top.productid}"><img src="${top.imgurl }" width="205" height="205"/></a></li>
                <li class="cR f18">￥${top.price }</li>
              </ul>
            </div>
          </c:forEach>
          
          </div>
        </div>

      </div>
    </div>
      </div>
  </div>
</body>
</html>