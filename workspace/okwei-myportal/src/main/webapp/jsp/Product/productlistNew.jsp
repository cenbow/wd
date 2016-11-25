<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="pg" uri="http://www.okwei.com/pagination"%>
<%@ page import="com.okwei.bean.enums.ProductStatusEnum"%>
<%@ page import="java.util.ResourceBundle"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String mydomain = ResourceBundle.getBundle("domain").getString(
			"mydomain");
	String productdomain = ResourceBundle.getBundle("domain")
			.getString("productdomain");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>我的产品</title>
<style>
.gygl_xxk_table_cz_sj_fx {
	width: 110px;
	padding: 0 20px;
}

.gygl_xxk_table_cz_sj {
	width: 80px;
}

.gygl_xxk_table_cz_bt {
	width: 150px;
	padding: 0 20px;
}

.gygl_xxk_table_cz_bt_span {
	width: 150px;
}

.mzh_xl_an {
	margin: 5px 0 0 10px;
}

.mzh_xl_an .mzh_an {
	line-height: 28px;
}

.mzh_xl_an .mzh_xl {
	height: 28px;
}

.mzh_xl_an .mzh_xl_yes {
	height: 28px;
}

.xzgys dd {
	float: left;
	width: 180px;
	line-height: 26px;
}

.xzgys dt {
	float: left;
	width: 290px;
	line-height: 26px;
}

.two_titles_select {
	float: left;
	width: 130px;
	border: 1px solid #ccc;
	line-height: 30px;
	height: 30px;
	border-radius: 3px;
	margin-right: 15px;
}
.lbs_5{width:200px;}
.lbs_6{width:230px;}
</style>
<script type="text/javascript" src="<%=request.getContextPath()%>/statics/js/product/productlist.js"></script>
<script type="text/javascript" src="/statics/js/share.js?v=2"></script>
<script type="text/javascript">
	$(function(){
		$("[name=mzh_fenxiang]").mouseover(function(){
		    $(this).find("[id=aaa]").attr("class","mzh_fenxiang_yes");
		    $(this).find(".mzh_fenxiang").show();
		});
		$("[name=mzh_fenxiang]").mouseout(function(){
		    $(this).find("[id=aaa]").attr("class","mzh_fenxiang_no");
		    $(this).find(".mzh_fenxiang").hide();
		});
	});

	function shareto(type,productId){
		var title="${userinfo.weiName}";
		//var pageurl="http://"+productId+".okwei.com";
		var pageurl="<%=productdomain %>/product?pid=" + productId+"&w=${userinfo.weiID}";
		var source = "${userinfo.weiName}的微店隶属于微店网总平台，普通网民可以在这里免费注册开微店，供应商可以从这里提交资料，把产品发到云端产品库，让像我一样的无数网民为他销售产品。";
		switch (type) {
		case 0: {
			ShareToQzone(title, pageurl, source);
			break;
		}
		case 1: {
			ShareToTencent(source, pageurl, source);
			break;
		}
		case 2: {
			ShareToSina(source, pageurl, source);
			break;
		}
		default: {
			alert("分享类型错误！");
			break;
		}
		}
	}


	
</script>
</head>

<body class="bg_f3">
	<form id="searcherForm">
		<div id="div_conter" class="conter_right mt_10 fl bg_white bor_si">
				<div class="qbits fl">
					<ul>
						<li class="lbs_2">图片</li>
						<li class="lbs_3">标题</li>
						<li class="lbs_4">价格</li>
						<li class="lbs_5">品牌</li>
						<li class="lbs_6">供应商</li>
						<li class="lbs_7">操作</li>
					</ul>
				</div>
				<c:choose>
					<c:when test="${fn:length(pageResult.list) < 1 }">
						<div class="bnimgs">暂无相关数据</div>
					</c:when>
					<c:otherwise>
						<c:forEach items="${pageResult.list}" var="product" varStatus="status">
							<div class="bnimgs">
								<ul>
									<li class="lbs_2"><a href="<%=productdomain %>/product?pid=${product.productId}&w=${userinfo.weiID}" target="_blank"><img src="${product.defaultImg}" /></a></li>
									<li class="lbs_3"><a href="<%=productdomain %>/product?pid=${product.productId}&w=${userinfo.weiID}" target="_blank">${product.productTitle}</a></li>
									<!-- 价格显示区域 -->
									<li class="lbs_4">
										<div class="">
											<c:choose>
												<c:when test="${userinfo.getDuke()==1 }">
														<p class="ft_c9">城主价：￥${product.dukePrice}</p>
												</c:when>
												<c:when test="${userinfo.getDeputyduke()==1 }">
														<p class="ft_c9">副城主价：￥${product.deputyPrice}</p>
												</c:when>
												<c:when test="${userinfo.getBrandagent()==1 }">
														<p class="ft_c9">代理价：￥${product.agentPrice}</p>
												</c:when>
											</c:choose>
											<p class="ft_c9">零售价：￥${product.defaultPrice}</p>
										</div>
									</li>
									<li class="lbs_5">${product.brandName }</li>
									<li class="lbs_6">${product.supplierName }</li>
									<li class="lbs_7">
										<!-- 操作列 -->
										<style>
										.mzh_fenxiang_no {
											margin-left: -4px;
										}
										.mzh_fenxiang_yes {
											margin-left: -4px;
										}
										</style>
										<div class="fl" name="mzh_fenxiang">
											<div class="mzh_fenxiang_no" id="aaa">
												<a href="javascript:;">分享</a>
												<div class="mzh_fenxiang">
													<a href="javascript:shareto(0,${product.productId});"><img src="http://base.okimgs.com/images/TX_kj.gif"></a>
													<a href="javascript:shareto(1,${product.productId});"><img src="http://base.okimgs.com/images/TX_wb.gif"></a>
													<a href="javascript:shareto(2,${product.productId});"><img src="http://base.okimgs.com/images/XL_wb.gif"></a>
												</div>
											</div>
										</div> <br />
										<div class="clear"></div>
									</li>
								</ul>
							</div>
						</c:forEach>
					</c:otherwise>
				</c:choose>
		</div>
		<!-- 分页 -->
		<div class="pull-right">
				<pg:page pageResult="${pageResult}" />
		</div>
		
	</form>

</body>