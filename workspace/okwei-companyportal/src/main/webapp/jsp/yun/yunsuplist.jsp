<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="pg" uri="http://www.okwei.com/pagination"%>
<%@ page import="java.util.ResourceBundle"%>
<%
	String shopdomain = ResourceBundle.getBundle("domain").getString("shopdomain");
	String domain = ResourceBundle.getBundle("domain").getString("okweidomain");
	String productdomain = ResourceBundle.getBundle("domain").getString("productdomain");
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="<%=path %>/statics/js/yunsup.js"></script>
<title>工厂,工厂货源直供微店分销平台,微店网(okwei.com)——免费开微店，认证原创微店官方平台</title>
<meta content="工厂货源直供微店分销平台。" name="description">
<meta content="微店网，免费开通微店，工厂，品牌工厂，工厂官方微店。" name="keywords">
<link href="http://base.okimgs.com/images/favicon.ico"
	rel="shortcut icon">
<style>
	.cpimgs a{ display:block; overflow:hidden;white-space: nowrap;text-overflow: ellipsis;}
	.imgps a{ display:block; overflow:hidden;}
</style>
<script>
$(function(){
	$('.leftscr a').each(function(){
		$(this).click(function(){
			$('.leftscr a').attr('class','');
			$(this).attr('class','nows')
		})
	})
	//--------选择地区--------------
    $(".address_ul a").click(function(){
	$('a[name="all_diqu"]').text($(this).text())
	})
	//分页
	var page = new Pagination({
		formId : "searcherForm",
		isAjax : false,
		targetId : "navTab",
		submitId : "searchBtn",
		validateFn : false
	});
	page.init();
	$(".rigsuces").find("b").eq(0).text($('.current').html());
	$(".rigsuces").find("b").eq(1).text($('.pageinfo em').html());
	$('.rigsuces a').click(function(){
		goPage1($(this).html());
	})
	
	//一级分类点击效果
	$('.img_left li a').each(function(){
		$(this).click(function(){
			$('.img_left li a').attr('class','fic_no');
			$(this).attr('class','fic_yes');
		})
	})
	//图片高度
	$('.imgps a').height($('.imgps a').width());
	
	$("img[name='productImg']").lazyload({
        placeholder: "<%=path%>/statics/images/206_206.png",
        effect: "fadeIn"
    });

})

function goPage1(h){
	$('.pagination a').each(function(){
		if ($(this).html()==h) {
			$(this).trigger('click');
		}
	});
}

/**
 * 改变筛选条件
 * tp-查询类型，c-分类id、品牌id
 */
function changeCondition(tp,c){
	if (tp == 0) {
		if (c == 0) {
			$('#searcherForm').find('input').each(function(){
				$(this).val(0);
			});
			$('#searcherForm').submit();
		} else {
			$('#type').val(0);
			$('#classone').val(c);
			$('#classtwo').val(0);
			$('#classthree').val(0);
			$('#brandid').val(0);
			$('#city').val(0);
			$('#searcherForm').submit();
		}
	} else if (tp == 1) {
		$('#type').val(1);
		$('#classtwo').val(c);
		$('#classthree').val(0);
		$('#brandid').val(0);
		$('#city').val(0);
		$('#searcherForm').submit();
	} else if (tp == 2) {
		$('#type').val(1);
		$('#classthree').val(c);
		$('#brandid').val(0);
		$('#city').val(0);
		$('#searcherForm').submit();
	} else if (tp == 3) {
		$('#type').val(1);
		$('#brandid').val(c);
		$('#city').val(0);
		$('#searcherForm').submit();
	} else if (tp == 4) {
		$('#type').val(1);
		$('#city').val(c);
		$('#searcherForm').submit();
	} else if (tp == 5) {
		$('#city').val(0);
		$('#searcherForm').submit();
	}
	
}

//关注店铺
function attentionShop(supid,sjrz){
	var isLogin =$("#loginID").val();
	if(isLogin =="" || isLogin <1){
		alert('请先登录!');
		return;
	}
	if (sjrz == true) {
		var type = -1;
		if ($('#attentionShop').html() == '关注店铺') {
			type = 0;
		} else if ($('#attentionShop').html() == '取消关注') {
			type = 1;
		}
		if (type != -1) {
			$.ajax({
			    url: "/yun/attentionSup",
			    type: "post",
			    data: {'supID':supid,'type':type},
			    dataType : 'json',
			    success: function (result) {
					if(result.msg =="0"){
						if (type == 1) {
							alert("取消关注成功",true);
							$('#attentionShop').html("关注店铺");
						} else {
							alert("关注成功",true);
							$('#attentionShop').html("取消关注");
						}
						//location.href = location.href;
					}else if (result.msg =="-1") {
						alert("参数错误!");
					}else if (result.msg =="-2") {
						alert("关注失败!");
					}else if (result.msg =="2") {
						alert("登录已过期,请重新登录！");
					}
			    },
			    error: function () {
			    	alert("系统异常,请稍后重试");
			    }
			});	
		}
	} else {
		alert('只有供应商才能被关注!');
	}
	
}
</script>
<style>
ul.company_ul li p{height:36px;overflow: hidden;}
</style>
</head>
<body style="background:#ebebeb;">
<input type="hidden" id="loginID" value="${user.weiID}" />
<article>
  <div class="w line-b fl">
    <div class="line3 c6 mar1200"><a href="http://www.<%=domain%>">微店网首页&nbsp;&gt;&nbsp;</a>&nbsp;<a href="http://company.okwei.com/yun/list">工厂</a></div>
  </div>
   <div class="w line-b  pt10 pb10 bg-w fl">
    <div class="mar1200">
      <div class="fl tyright  f14 "> 
        <ul class="img_left">
	        <c:if test="${queryparam.classone==0 }">
				<li><a class="fic_yes" href="javascript:changeCondition(0,0)"><i></i>全部</a></li>
			</c:if>
			<c:if test="${queryparam.classone!=0 }">
				<li><a class="fic_no" href="javascript:changeCondition(0,0)"><i></i>全部</a></li>
			</c:if>
			<c:forEach var="kv" items="${classoneList }" >
				<c:if test="${kv.typeid==queryparam.classone }">
					<li><a class="fic_yes" href="javascript:changeCondition(0,'${kv.typeid}')"><span class="${kv.cssclass }"></span><i></i>${kv.typename }</a></li>
				</c:if>
				<c:if test="${kv.typeid!=queryparam.classone }">
					<li><a class="fic_no" href="javascript:changeCondition(0,'${kv.typeid}')"><span class="${kv.cssclass }"></span><i></i>${kv.typename }</a></li>
				</c:if>
			</c:forEach>
        </ul>
      </div>
    </div>
  </div> 
   <div class="w line-b  pt10 pb10 bg-w fl">
    <div class="mar1200">
      <div class="fl tyleft c6"><c:choose><c:when test="${queryparam.classtwo==0 && queryparam.classthree==0 && queryparam.classone==0}">热门类目:</c:when><c:otherwise>相关类目:</c:otherwise></c:choose></div>
      <div class="fl tygduos" ><a href="javascript:changeCondition(1,0)" <c:if test="${(queryparam.type==1 && queryparam.classthree==0) || (queryparam.classtwo==0 && queryparam.classthree==0) || queryparam.classthree==null}">class="bgyue"</c:if>>全部</a></div>
      <div class="fl tyright">
        <ul>
           <c:if test="${fn:length(classtwoList)>0}">
        	<c:forEach var="two" items="${classtwoList }" >
				<c:if test="${two.classId==queryparam.classtwo }">
					<li><div class="fl tygduos" ><a class="bgyue" href="javascript:changeCondition(1,'${two.classId}')">${two.className}</a></div></li>
				</c:if>
				<c:if test="${two.classId!=queryparam.classtwo }">
					<li><a class="" href="javascript:changeCondition(1,'${two.classId}')">${two.className}</a></li>
				</c:if>
			</c:forEach>
           </c:if>
           <c:if test="${fn:length(classthreeList)>0}">
        	<c:forEach var="three" items="${classthreeList }" >
				<c:if test="${three.classId==queryparam.classthree }">
					<li><div class="fl tygduos" ><a class="bgyue" href="javascript:changeCondition(2,'${three.classId}')">${three.className}</a></div></li>
				</c:if>
				<c:if test="${three.classId!=queryparam.classthree }">
					<li><a class="" href="javascript:changeCondition(2,'${three.classId}')">${three.className}</a></li>
				</c:if>
			</c:forEach>
           </c:if>
        </ul>
      </div>
    </div>
  </div>
<%--   <div class="w line-b  pt10 pb10 bg-w fl">
    <div class="mar1200">
      <div class="fl tyleft c6">
      <c:choose><c:when test="${queryparam.classtwo==0 && queryparam.classthree==0 && queryparam.classone==0}">
      <!-- 	热门品牌: -->
      </c:when><c:otherwise>相关品牌:</c:otherwise></c:choose></div>
      <div class="fl tygduos" ><a href="javascript:changeCondition(3,0)" <c:if test="${queryparam.brandid==0 || queryparam.brandid==null}">class="bgyue"</c:if>>全部</a></div>
      <div class="fl tyright">
        <ul>
          <c:forEach var="hotBrand" items="${brandList }" >
			<c:if test="${hotBrand.brandId==queryparam.brandid }">
				<li><div class="fl tygduos" ><a class="bgyue" href="javascript:changeCondition(3,'${hotBrand.brandId}')">${hotBrand.brandName}</a></div></li>
			</c:if>
			<c:if test="${hotBrand.brandId!=queryparam.brandid }">
				<li><a class="" href="javascript:changeCondition(3,'${hotBrand.brandId}')">${hotBrand.brandName}</a></li>
			</c:if>
		  </c:forEach>
        </ul>
      </div>
    </div>
  </div> --%>
<form id="searcherForm" action="/yun/list" method="get">
<input type="hidden" id="type" name="type" value="${queryparam.type}" /> 
<input type="hidden" id="classone" name="classone" value="${queryparam.classone}" /> 
<input type="hidden" id="classtwo" name="classtwo" value="${queryparam.classtwo}" /> 
<input type="hidden" id="classthree" name="classthree" value="${queryparam.classthree}" /> 
<input type="hidden" id="city" name="city" value="${queryparam.city}" /> 
<input type="hidden" id="brandid" name="brandid" value="${queryparam.brandid}" /> 
  <div class="mar1200  "> 
    <!-- 选择地区 -->
<%--     <div class="screen bg-w fl mt20">
      <ul>
        <li class="fl leftscr pr seclect_address"><span class="pr"> <a href="javascript:;" name="all_diqu" style="padding:0 20px;width:auto;"><c:choose><c:when test="${queryparam.city==0 || queryparam.city==null}">所有地区</c:when><c:otherwise>${areaName}</c:otherwise></c:choose></a> <i class="ico_map_5 pa" style="top:20px; right:5px;"></i></span>
          <ul class="address_ul">
           	<c:forEach items="${areaList}" var="province">
                <li>
                <a <c:if test="${province.provinceCode==queryparam.city}">class="nows"</c:if> href="javascript:changeCondition(4,'${province.provinceCode}')" name="address_ul_a">
                    <span>${province.provinceName}</span><i class="posi_a_left"></i>
                </a>
                <div class="w600">
                    <ul>
                        <c:forEach items="${province.cityList}" var="city">
							<li><a <c:if test="${city.code==queryparam.city}">class="nows"</c:if> href="javascript:changeCondition(4,'${city.code}')">${city.name}</a></li>
                        </c:forEach>
                    </ul>
                </div>
            </li>
           	</c:forEach>
            <li>
              <input type="button" class="btn_small mt10" onclick="javascript:changeCondition(5,'0')" value="取消选择" />
            </li>
          </ul>
        </li>
        <li class="fr rigsuces"><span><b></b>/<b></b></span> <a href="javascript:;">上一页</a> <a href="javascript:;">下一页</a> </li>
      </ul>
    </div> --%>
    <!-- 商品排列  style=" visibility:visible;opacity:1"  list_li-->
    <c:if test="${fn:length(pageRes.list)<1}">
		<div class="mar1200 scpf_market ">
			<!-- 工厂 空 -->
			<div class="null_coues fl pb30">
				<p class="f18 tc">没有符合条件的工厂，请尝试其他搜索条件</p>
			</div>
		</div>
	</c:if>
	<c:if test="${fn:length(pageRes.list)>0}">
		<form id="searcherForm" action="/yun/list" >
		<input type="hidden" name="type" value="${mainvo.type}" /> 
		<input type="hidden" name="c" value="${mainvo.classid}" />
		<div class="w fl mt20 bg-w">
		<c:forEach items="${pageRes.list}" var="yun">
	      <div class="p15 line-b">
	        <div class="company_list lh200" style="overflow: visible;">
	          <dl>
	            <dt class="f16"><a href="http://${yun.weiId}.okwei.com" class="cB tb">${yun.supName}</a></dt>
	            <dd>主营：${yun.mainbus}</dd>
	            <dd>产品数量：${yun.productCount} </dd>
	            <dd>上架次数：${yun.shelvesCount}     关注人数：${yun.attentionPerson}</dd>
	            <c:if test="${yun.showArea != '  '}">
	            <dd> 所在地：${yun.showArea}</dd>
	            </c:if>	          
	            <dd class="pr"> 工厂资质：
	              <div class="pa pa_zizi">
	              <c:if test="${yun.smrz}"><span class="pic_img1" title="实名认证"></span></c:if>
	              <c:if test="${yun.sjrz}"><span class="pic_img2" title="企业认证"></span></c:if>
	              <c:if test="${yun.jzrz}"><span class="pic_img3" title="保证金${yun.bond}"></span></c:if>
	              </div>
	            </dd>
	            <dd class="mt20 pr">
	             <c:if test="${fn:length(yun.qqList)>0}">
	              <i class="ico_map_3 fl mt5 mr10">
	                  <div id="mzh_dp" class="mzh_dp" >
                      	  <c:forEach items="${yun.qqList}" var="qq" varStatus="vs">
		                      <div class="mzh_dp_1">
		                          <div class="mzh_dp_2">客服<c:if test="${vs.index==0}">一</c:if><c:if test="${vs.index==1}">二</c:if><c:if test="${vs.index==2}">三</c:if><c:if test="${vs.index==3}">四</c:if>：</div>
		                          <a href="http://wpa.qq.com/msgrd?v=3&uin=${qq}&site=qq&menu=yes" target="_blank"><img src="http://wpa.qq.com/pa?p=2:1059854962:51" class="mzh_dp_3"></a>
	                     	  </div>
             			  </c:forEach>
	                  </div>
	              </i>
	             </c:if>
	              <span class="erweima_8_18">
	                <div class="div-posabues" style="left: -93px;top: 21px;">
	                    <div class="eweim-imgs"> <img src="http://api.okwei.com/api/v3/Client/VerifierQrcode?size=10&weino=${yun.weiId}&itype=5"> </div>
	                </div>
	              </span>
	              <c:if test="${user.weiID != yun.weiId}">
	              <a href="javascript:attentionShop(${yun.weiId},${yun.sjrz})"  class="fl btn_small mr10" style="width:80px;" id="attentionShop"><c:choose><c:when test="${yun.isgz}">取消关注</c:when><c:otherwise>关注店铺</c:otherwise></c:choose></a>
	           	  </c:if>
	            </dd>
	            </dd>
	          </dl>
	        </div>
	        <ul class="company_ul">
	          <c:if test="${fn:length(yun.productList)>0}">
	          <c:forEach items="${yun.productList}" var="prod">
	          <li>
	            <div class="imgps"><a href="<%=productdomain %>/product?pid=${prod.productId}" target="_blank"><img name="productImg" original="${prod.defaultImg}" /></a></div>
	            <div class="money"> <span class="fl cR f16">¥ <b class="tb">${prod.defaultPrice}</b> </span><span class="fr c9"><del class="tb f14">原价¥${prod.originalPrice}</del>
	            <%-- <a href="<%=productdomain %>/product?pid=${prod.productId}#pjsd" target="_blank"  class="cB">评论(${prod.commentCoxunt})</a> --%>
	            </span></div>
	            <p class="smwzs"><a href="<%=productdomain %>/product?pid=${prod.productId}" target="_blank">${prod.productTitle} </a></p>
	          </li>
	          </c:forEach>
	          </c:if>
	        </ul>
	        <div class="cb"></div>
	      </div>
		</c:forEach>
	    </div>
	    
		<!-- 分页 -->
		<div class="pull-right">
			<pg:page pageResult="${pageRes}" />
		</div>
		</form> 
	</c:if>
  </div>
</form>
</article>
</body>
</html>