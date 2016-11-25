<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="java.util.ResourceBundle"%>
<%
    String path = request.getContextPath();
			String basePath = request.getScheme() + "://"
					+ request.getServerName() + ":" + request.getServerPort()
					+ path + "/";
			String cartdomain = ResourceBundle.getBundle("domain").getString(
					"cartdomain");
			String shopdomain = ResourceBundle.getBundle("domain").getString(
					"shopdomain");
			String okweidomain = ResourceBundle.getBundle("domain").getString(
					"okweidomain");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${product.proTitle }-微店网(okwei.com)——免费开微店，认证原创微店官方平台</title>
<meta name="description" content="${product.proTitle }，${product.proMinTitle }" />
<meta name="keywords" content="微店，微店网，免费开通微店，${product.proTitle }<c:forEach var="kv" items="${product.keyword }">，${kv }</c:forEach>" />
<link rel="stylesheet" type="text/css" href="http://base1.okwei.com/css/detail/indexM.css" />
<link rel="stylesheet" type="text/css" href="http://base2.okwei.com/css/detail/tcc.css" />
<link rel="stylesheet" type="text/css" href="http://base3.okwei.com/css/detail/prodetail.css" />
<link rel="stylesheet" type="text/css" href="http://base3.okwei.com/css/detail/detail828.css" />
<script type="text/javascript" src="http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=js" charset="gb2312"></script>
<script type="text/javascript" src="http://base1.okwei.com/js/detail/common/share.js"></script>
<script type="text/javascript" src="http://base2.okwei.com/js/detail/common/jquery.pagination.js"></script>
<script type="text/javascript" src="http://base3.okwei.com/js/detail/jquery-josn.js"></script>
<script type="text/javascript" src="http://base1.okwei.com/js/detail/district.js"></script>
<script type="text/javascript" src="/statics/js/prodetail.js"></script>
<script type="text/javascript" src="http://base1.okwei.com/js/detail/zzsc.js"></script>


<script type="text/javascript">
var iCount="${product.count}";var proActId ="${product.proActId}"; var activeType="${product.activeType}"; var beginTime="${product.beginTime}"; var proID="${product.proID }"; var supWeiID="${product.supWeiID }";var sharePageProducer="${product.sharePageProducer }";var shareOne="${product.shareOne }";var sharePageId="${product.sharePageId }"; var demandid="${product.demandId }"; var actEndTime="${product.endTime}"; var proActId="${product.proActId}";var userLimitBuyCount="${userLimitBuyCount}";

</script>

<style>
.proLarge li.proLarge_gwqf{line-height: 20px;background: #fb4084;color: #fff;border-radius: 5px;position: absolute;right: 5px;top:5px;width:auto;height:auto;padding:3px 5px;}
.proLarge_yqg{position: absolute;left: 35%;top: 35%;color: #fff;background: #000;border-radius: 50%;width: 120px;height: 120px;font-size: 20px;text-align: center;line-height: 120px;filter:alpha(opacity=50); -moz-opacity:0.5; -khtml-opacity: 0.5; opacity: 0.5;}

/*主容器*/
.con-FangDa{
	width: 300px;
	height: auto;
	margin: 10px auto;
	background-color:#fff;
}
/*正常容器*/
.con-fangDaIMg{
	width: 360px;
	height: 360px;
	position: relative;
}
.con-fangDaIMg > img{
	width: 356px;
	height: 356px;
	overflow: hidden;
	padding: 2px;
}
/*滑块*/
.magnifyingBegin{
	width: 150px;
	height: 175px;
	left: 0;
	top: 0;
	background-color: #454545;
	opacity: 0.5;
	filter:alpha(opacity=50);
	position: absolute;
	cursor: move;
	display: none;
}
/*放大镜显示区域*/
.magnifyingShow{
	width: 360px;
	height: 360px;
	display: none;
	position: absolute;
	left: 380px;
	top: 0;
	overflow: hidden;
	background-color: #454545;
	border: 1px solid #ebebeb;
}
.magnifyingShow > img{
	width: 865px;
	height: 805px;
	margin-left:0;
	margin-top: 0;
}
/*设置选择图片容器*/
.con-FangDa-ImgList{
	margin-top:10px;
	height:54px;
	width:320px;
	list-style: none;
}
.con-FangDa-ImgList > li{
	margin-right:7px;
	width: 50px;
	height: 50px;
	float: left;
	cursor: pointer;
	border: 2px solid #666;
	background-color: #454545;
	text-align:center;
}

.con-FangDa-ImgList > li > img{
	vertical-align:top;
	display:inline;
	width:auto;
	height:50px;
}
.pro_left{margin-top: 8px;}
</style>
</head>
<body>
	<!-- 当前位置 -->
	<div class="w fl">
		<div class="line3 mar1200">
			当前位置 > <a target="_blank" href="http://${product.supWeiID}.<%=okweidomain%>">${product.supInfo.shopName }</a> > <span class="tb">${product.proTitle }</span>
		</div>
	</div>

	<!-- 内容 -->
	<div style="background: #fff;">
		<div class="content_1" style="background: #fff;">
			<div class="conMain">
				<div class="pro_left">
					<!-- 大图 -->
					<div class="proLarge pr" id="fangdajing" style="overflow: inherit;">	
						<div class="con-fangDaIMg">
					      <!-- 正常显示的图片-->
					      	<img style="margin-top: 0px; margin-left: 0px;" src="" id="productBigImg" height="355" width="355">
							<div class="proLarge_yqg" style="display:none;">已抢光</div>
					      <!-- 滑块-->
					      <div class="magnifyingBegin"></div>
					      <!-- 放大镜显示的图片 -->
					      <div class="magnifyingShow"><img style="margin-top: 0px; margin-left: 0px;" src="images/20140419022022900560_75_002.jpg" id="productBigImg" onload="javascript:ZoomXQ(this,355,355);" height="355" width="355"></div>
						</div>
						<div id="SpecialTellMe" class="mzh_dztzw" style="display: none;">
							<!-- 已关注打折 -->
							<div class="mzh_dztzw_li" style="display: none;">
								<img src="http://base1.okwei.com/images/mzh_12_10_dztzw.png">已关注打折
							</div>
							<!-- 打折通知我 -->
							<div class="mzh_dztzw_li">
								<img src="http://base2.okwei.com/images/mzh_12_10_dztzw_mr.png">打折通知我
							</div>
						</div>
					</div>
					<!-- 小图 -->
					<div class="proSmall">
						<div class="LeftBotton"></div>
						<div class="proSmall_con">
							<ul id="productImgList">
								<c:forEach var="image" items="${product.imageList }" varStatus="vs">
								<li class='<c:if test="${vs.index==0 }">current</c:if>'><img id='img_${vs.index }' onmouseover='javascript:selectedImg(${vs.index})' style="margin-top: 0px; margin-left: 0px;" src="${image }" onload="javascript:ZoomXQ(this,50,50);" height="50" width="50"></li>
								</c:forEach>
							</ul>
						</div>
						<div class="RightBotton"></div>
					</div>
					<div class="c9" style="float: left; width: 250px; line-height: 30px;">
						上架（<a href="javascript:;">${product.shelvesCount }</a>）分享（<label id="shareCount">${product.shareCount }</label>）
					</div>
					
					<div style="float: right; margin: 4px 7px 0 0px; height: 20px;">
						<div class="FX" style="cursor: pointer;">
							分享
							<div class="fx_WKX" style="display: none;">
								<div class="ZF">
									<div style="width: 22px; height: 21px; margin: 10px 0 0 12px; text-indent: 0;">
										<a href="javascript:shareTo('kj','${product.proTitle }','${product.proID }','${user.weiID }','${product.form }');"><img src="http://base1.okwei.com/images/TX_kj.gif"></a>
									</div>
									<div style="width: 22px; height: 21px; margin: 10px 0 0 12px; text-indent: 0; background: red;">
										<a href="javascript:shareTo('tx','${product.proTitle }','${product.proID }','${user.weiID }','${product.form }');"><img src="http://base2.okwei.com/images/TX_wb.gif"></a>
									</div>
									<div style="float: left; width: 22px; height: 21px; margin: 10px 0 0 12px; text-indent: 0;">
										<a href="javascript:shareTo('xl','${product.proTitle }','${product.proID }','${user.weiID }','${product.form }');"><img src="http://base3.okwei.com/images/XL_wb.gif"></a>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>

				<div class="pro_right">
					<div class="fl w mt10" id="actInfoDiv">
							<div class="fl w">
							<c:choose>
									<%-- <c:when test="${product.activeType<0}">
										<c:if test="${product.supWeiID!=supiler}">  
										<div class="mzh_ycj_no">
						                <img src="http://base2.okwei.com/images/nindex828.png" class="fl"/>
						                <span class="fl ml5">未参加8.28购物全返活动</span>	
						                </div>   
						                </c:if> 
				                    </c:when> --%>
									<c:when test="${product.activeType==0}">
									<div class="mzh_ycj">
									<img src="http://base2.okwei.com/images/img_time.jpg" class="fl"/>
			                    	<span class="fl ml5">已参加限时抢购活动</span>
			                    	</div>
			                   		</c:when>
									<%-- <c:when test="${product.activeType==1}">
									<div class="mzh_ycj">
									<img src="http://base2.okwei.com/images/index828.png" class="fl"/>
			                   		<span class="fl ml5">已参加8.28购物全返活动</span>
			                   		</div>
			                   		</c:when>	 --%>              
				            </c:choose>
				            <c:if test="${product.supWeiID==supiler}">  
				            <div class="fl w mt10">
				               <div class="mzh_ycj">
				                   <img class="fl" src="http://base1.okwei.com/images/company/img_7_22_25.png">
				                   <span class="fl ml5">购物全返兑换商品</span>
				               </div>
				             </div>
				             </c:if>
				             
				              <c:if test="${product.activeType>=0}">
			                    <div class="mzh_jjs f14 ml50">
				                    <span class="fl" id="timetext"></span>
				                    <font class="fl f24" id="timespan"></font>
				                </div> 
				                </c:if>                 
			                </div>
		            </div>  	      
					<div class="pro_right_title">
						<h3 id="productName">${product.proTitle }</h3>
						<p style="font-size: 14px; overflow: hidden; white-space: nowrap; text-overflow: ellipsis; color: #C00">${product.proMinTitle }</p>
					</div>
					<c:choose>
						<c:when test="${product.state!=1 }">
							<div class="yixjiaimgs">
								<h3>此商品已下架</h3>
								<h4>猜你喜欢</h4>
								<c:forEach var="pro" items="${product.proList }" varStatus="vs">
									<ul>
										<c:if test="${vs.index<3 }">
											<li><a href="${pro.id }" target="_blank"><img src="${pro.proImg }" title="${pro.proTitle }" onload="javascript:ZoomXQ(this,120,120);" /></a></li>
											<li class="iedtit"><a href="${pro.id }" title="${pro.proTitle }">${pro.proTitle }</a></li>
											<li class="iedtit tb cR">￥${pro.price }</li>
										</c:if>
									</ul>
								</c:forEach>
							</div>
						</c:when>
						<c:otherwise>
							<c:if test="${product.wholesale==1 || product.schedule==1 }">
								<div class="fl tabies">
									<ul>
										<li class="nesel" data="1">零售</li>
										<c:if test="${product.wholesale==1 }">
											<li data="3">批发</li>
										</c:if>
										<c:if test="${product.schedule==1 }">
											<li data="2">预定</li>
										</c:if>
									</ul>
								</div>
								<script type="text/javascript">
							$(function(){
								$(".tabies ul li").click(function(){
									$(".tabies ul li").removeClass("nesel");
									$(this).attr("class","nesel");
									$("[name=priceli]").hide();
									var data=$(this).attr("data");
									$("[name=priceli][data="+data+"]").show();
									$(".pricepropertyli").remove();
									if(data=="3"){
										//批发
										$("#pfbuynum").show();
										$("#lsbuynum").hide();
										pfPriceHtml.init();
									}else{
										$("#pfbuynum").hide();
										$("#lsbuynum").show();	
										PriceHtml.init();
									}
								})
							});
						</script>
							</c:if>
							<ul class="pro_right_txt mt20">
								<!--落地价 -->
								<c:if test="${product.form==1 }">
									<li style="border-bottom: 1px solid #f5f5f5;"><strong>落地价</strong>
										<p style="display: inline-block; float: left;">
											<c:choose>
												<c:when test="${product.showLandPrice==1 }">
													<font class="fl"><em>￥</em> <label id="ldprice">0.00</label> </font>
													<span class="ml10 cR fl">现金券可抵￥<label id="djqPrice">0.00</label></span>
												</c:when>
												<c:otherwise>
													<label id="ldprice" style="display: none;">0.00</label>
													<a href="http://${product.supWeiID}.<%=okweidomain%>?opType=3&demandId=${product.demandId}" class="cB">落地店可见</a>
													<span class="ml10 cR">下单即送等值现金券</span>
												</c:otherwise>
											</c:choose>
										</p></li>
								</c:if>
								<c:if test="${product.form==2 }"> 
									<li style="border-bottom: 1px solid #f5f5f5;"><strong>代理价</strong>
										<p style="display: inline-block; float: left;">
											<c:choose>
												<c:when test="${product.showAgentPrice==1 }">
													<font class="fl"><em>￥</em> <label id="dlprice">0.00</label> </font>
												</c:when>
												<c:otherwise>
													<label id="dlprice" style="display: none;">0.00</label>
													<a href="javascript:;" class="cB">代理商可见</a>
												</c:otherwise>
											</c:choose>
										</p></li>
									</c:if>
									<c:if test="${user.weiID!=null&&user.weiID>0&&product.publishtype==1}">
									<li id="dldiv">
									<div class="fl" style="margin-right: 60px;">
									<c:choose>
										<c:when test="${user.duke>0}">
											<strong>代理价：</strong>
											<p style=" display:inline-block;float:left;">
												<font class="fl"><em>￥</em>
													<label id="duke">0.00</label>
													</font>
											</p>
										</c:when>
										<c:when test="${user.deputyduke>0}">
											<strong>代理价：</strong>
											<p style=" display:inline-block;float:left;">
												<font class="fl"><em>￥</em>
													<label id="deputyduke">0.00</label>
													</font>
											</p>
										</c:when>
										<c:when test="${user.brandagent>0}">
											<strong>代理价：</strong>
											<p style=" display:inline-block;float:left;">
												<font class="fl"><em>￥</em>
													<label id="brandagent">0.00</label>
												</font>
											</p>
										</c:when>
									</c:choose>
									</div>
									</li>
									</c:if> 
								<li name="priceli" data="1" style="border-bottom: 1px solid #f5f5f5;">
									<div id="xianPriceDiv">
									<strong>现价</strong>
									<p style="display: inline-block; float: left;">
										<font><em>￥</em> <label id="defprice">0.0</label> </font>
									</p>
									</div>
									
									<div id="actPriceDiv" style="display:none">
									<strong>活动价</strong>
									<p style="display: inline-block; float: left;">
										<input type="hidden" name="actCount" id="actCount" value="${product.actCount }">
										<font><em>￥</em> <label id="actprice">${product.actPrice }</label> </font>
									</p>
									</div>
									
									<div class="ml50 fl">
					                    <strong style="margin-right: 0px;width: auto;">原价：</strong>
					                    <p style=" display:inline-block;float:left;">
					                        <font>
					                            <strong class="f14" style="text-decoration: line-through;">￥<label id="orignalprice" >${product.orignalPrice }</label></strong>
					                        </font>
					                        <!--特价部分 -->
					                    </p>
					                </div>
								</li>
								<c:if test="${product.wholesale==1 }"> 
									<li name="priceli" data="3" style="display: none; border-bottom: 1px solid #f5f5f5;"><strong>批发价</strong> <c:forEach var="priceValue" items="${product.priceList }" varStatus="vs">
											<c:if test="${vs.index==0 }">
												<input type="hidden" id="minCount" value="${priceValue.count }" />
											</c:if>
											<input type="hidden" name="pfprice" price="${priceValue.price}" count="${priceValue.count }" />
											<p style="display: block; float: left; padding-right: 20px;">
												<font><em>￥</em> <label>${priceValue.price }</label> </font> <span class="block c9" style="text-indent: 10px;">${priceValue.count }件起批</span>
											</p>
										</c:forEach>
								</c:if>
								<c:if test="${product.schedule==1 }">
									<li name="priceli" data="2" style="display: none; border-bottom: 1px solid #f5f5f5;"><strong>预定价</strong>
										<p style="display: inline-block; float: left;">
											<font><em>￥</em> <label id="preprice">${product.prePrice }</label> </font>
										</p>
										<div style="clear: both; text-indent: 60px;">${product.preContent }</div></li>
								</c:if>
								<li id="liaddress"><dl class="pro_right_color">
										<dt>邮寄至</dt>
										<dd>
											<span> <select id="selProvince"></select> <select id="selCity"></select> <select id="selDistrict"></select>
												<div class="baoyou"></div>
											</span>
										</dd>
									</dl></li>
								<c:if test="${product.wholesale==1 }">
									<div id="pfbuynum" class="hesiz fl mt10" style="display: none;">
										<div class="fl idesbes">
											<p class="fl counimg">
												<span class="f20 cR" id="allcount">0</span><span class="unit cR">件</span>
											</p>
											<p class="fl">
												<span class="f20  cR" id="allprice">0.00</span><span class="price-unit cR">元</span> <input id="defpfprice" type="hidden" value="" />
											</p>
										</div>
										<div class="fr ifcents pr">
											<a href="javascript:showdivpf();" class="cR">已选清单</a>
											<div id="divselpf" class="pa tabszis" style="display: none;">
												<table id="tabpfnum">

												</table>
											</div>
										</div>
									</div>
								</c:if>
								<li id="lsbuynum">
									<dl class="pro_right_shuliang">
										<dt>数量</dt>
										<dd>
											<div class="jiajian-item">
												<b>-</b> <input value="1" onkeyup="javascript:this.value = this.value.replace(/[^0-9]+/,'');" type="text" onchange="GetPostAge()"> <b>+</b>
											</div>
											
											<span>库存： <label id="stock">0</label>
											</span>
											<span id="errorTip" style="color:red;padding-left:5px"></span>
										</dd>
									</dl>
								</li>
								<li>
									<div class="pro_right_button">
										<c:choose>
											<c:when test="${product.productSupType==1 }">
												<c:choose>
													<c:when test="${product.form==1 }">
														<a href="javascript:immediately(4,2);" class="btn_1" style="background: #e5494a">立即购买</a>
														<a href="javascript:joinshopcart(4,2);" class="btn_1" style="background: #F90">加入进货单</a>
														<c:if test="${product.isMyLandShop==1 }">
															<a href="javascript:joinshopcart(5,2);" class="btn_1" style="background: #F90">加入铺货单</a>
														</c:if>
													</c:when>
													<c:when test="${product.form==2 }">
														<c:choose>
															<c:when test="${product.isMyAgent==1 }">
																<a href="javascript:immediately(4,1);" class="btn_1" style="background: #e5494a">立即购买</a>
																<a href="javascript:joinshopcart(4,1);" class="btn_1" style="background: #F90">加入进货单</a>
																<a href="javascript:joinshopcart(5,1);" class="btn_1" style="background: #F90">加入铺货单</a>
															</c:when>
															<c:otherwise>
																<a href="http://${product.supWeiID}.<%=okweidomain%>/demand/demandInfo?demandID=${product.demandId}" class="btn_1">申请加盟</a>
															</c:otherwise>
														</c:choose>
													</c:when>
													<c:otherwise>
														<c:choose>
														<c:when test="${product.supWeiID==supiler }">
														<a href="javascript:;" class="btn_1" id="mzh_ljdh" style="background: #F90">立即兑换</a>
														</c:when>
														<c:otherwise>
														<a href="javascript:immediately(1,0);" class="btn_4" id="btnbuy">立即购买</a>
														<a href="javascript:joinshopcart(1,0);" class="btn_1" style="background: #F90">加入购物车</a>
														</c:otherwise>
														</c:choose>
													</c:otherwise>
												</c:choose>
											</c:when>
											<c:otherwise>
												<a href="javascript:;" class="btn_hui">立即购买</a>
												<a href="javascript:;" class="btn_hui">加入购物车</a>
												<p>注：此商品暂未审核,为了保证消费者权益,暂不能购买</p>
											</c:otherwise>
										</c:choose>
									</div>
								</li>
								<li>
									<div class="pro_right_jybz">
										<em>交易保障</em> <span class="cft-weixin"></span><span class="cft-ico"></span><span class="yl-ico"></span><span class="qtbt-ico"> </span>
									</div>
								</li>
							</ul>
							<script type="text/javascript">
								$(function(){
									PriceHtml.init();
									InitCity();
									GetPostAge();
									$(".pro_right_color select").change(function() {
										GetPostAge();
									});
								});
							</script>
						</c:otherwise>
					</c:choose>
				</div>
				<c:if test="${product.supType>0 }">
					<div class="fr" style="width: 250px;">
						<ul class="mt20 block" style="height: 60px;">
							<li class="fl"><img style="margin-top: 0px; margin-left: 0px;" src="${product.supInfo.shopImg }" height="50" width="50" /></li>
							<li class="fl f14" style="line-height: 20px; padding-left: 20px; width: 180px;">${product.supInfo.shopName }</li>
						</ul>
						<div class="line-b cb" style="margin: 10px 0;"></div>
						<ul class="line2">
							<li class="f12 tb">微店号：${product.supWeiID }</li>
							<li class="f12 tb clearfix"><span class="block fl">资质：</span> <c:if test="${product.isRealName==1 }">
									<span class="pic_img1" title="实名认证"></span>
								</c:if> <span class="pic_img2" title="企业认证"></span> <span class="pic_img3" title="保证金${product.supInfo.bond }"></span></li>
							<li class="f12 tb">开店时间：${product.supInfo.shopTime }</li>
						</ul>
						<div class="line-b cb" style="margin: 10px 0;"></div>
						<ul class="line2">
							<%-- 							<li class="f12 tb">商品数量：${product.supInfo.proCount }</li> --%>
							<li class="f12 tb">${product.supInfo.area }</li>
							<c:if test="${product.supInfo.address!=null && product.supInfo.address!=''}">
								<li class="f12 tb">${product.supInfo.address }</li>
							</c:if>
						</ul>
						<div class="line-b cb" style="margin: 10px 0;"></div>
						<ul class="line2 ficwlyu">
							<li class="redimg3">担保交易</li>
							<li class="redimg1">七天无理由退货</li>
							<c:if test="${product.supInfo.isBrand>0 }">
								<li class="redimg2">品牌认证</li>
							</c:if>
						</ul>
						<div class="line-b cb" style="margin: 10px 0;"></div>
						<p class="tc lxkfimg">
							<a target="_blank" href="http://wpa.qq.com/msgrd?v=3&uin=${product.supInfo.qq }&site=qq&menu=yes" class="tb" style="color: #0c71b7;">联系客服 </a>
						</p>
						<c:if test="${product.supWeiID!=supiler}">  
						<p style="width: 100px; float: left; margin-top: 10px;">
							<a target="_blank" href="http://${product.supWeiID}.<%=okweidomain%>" class="btn_small_3 tb">进入店铺 </a>
						</p>
						<%-- <c:if test="${product.isAttention!=2 }"> --%>
							<p style="width: 100px; float: right; margin-top: 10px;">
								<a href="javascript:;" class="btn_small_3 tb" onclick="attentionSup(this)" data="${product.isAttention}"> <c:choose>
										<c:when test="${product.isAttention==1 }">
											取消关注
										</c:when>
										<c:otherwise>
											关注店铺	
										</c:otherwise>
									</c:choose>

								</a>
							</p>
						</c:if>
						<%-- </c:if> --%>
					</div>
				</c:if>
				<div class="clear"></div>
			</div>
			<div class="conPro" style="background: #fff;">
				<!-- 左边 -->
				<div class="conPro_left">
					<!-- 看了又看 -->
					<div class="conPro_klyk">
						<h2>——看了又看——</h2>
						<div class="conPro_klyk_con">
							<c:forEach var="pro" items="${product.proList }">
								<dl>
									<dt>
										<a href="/product?pid=${pro.proID }" target="_blank"> <img style="margin-top: 0px; margin-left: 0px;" title="${pro.proTitle }" src="${pro.proImg }" onload="javascript:ZoomXQ(this,160,160);" height="160" width="160"></a>
									</dt>
									<dd>
										<p>
											<a href="/product?pid=${pro.proID }" title="${pro.proTitle }">${pro.proTitle }</a>
										</p>
										<span>￥${pro.price }</span>
									</dd>
								</dl>
							</c:forEach>
						</div>
					</div>
				</div>
				<!-- 右边 -->
				<div class="conPro_right">
					<!-- 系列产品 -->
					<c:if test="${product.form==1||product.form==2 }">
						<div class="w fl bor_all mb20">
							<div class="fl ft_title f18 c3">
								系列产品
								<c:if test="${product.form==1 && product.isMyLandShop==0 }">
									<font class="cR f14">(购满${product.orderAmount }元即可成为落地店）</font>
								</c:if>

								<c:if test="${product.form==1 }">
									<a href="http://${product.supWeiID}.<%=okweidomain%>?opType=3&demandId=${product.demandId}" class="fr cB f14">更多同系列产品>></a>
								</c:if>
								<c:if test="${product.form==2 }">
									<a href="http://${product.supWeiID}.<%=okweidomain%>?opType=2&demandId=${product.demandId}" class="fr cB f14">更多同系列产品>></a>
								</c:if>
							</div>
							<div class="fl w sipels">
								<ul>
									<c:forEach var="pro" items="${product.seriesProduct }">
										<li>
											<p>
												<a target="_blank" href="/product?pid=${pro.proID }&f=${product.form }"><img src="${pro.proImg }" height="160" width="160"></a>
											</p>
											<p class="hidd_sel">
												<a target="_blank" href="/product?pid=${pro.proID }&f=${product.form }">${pro.proTitle }</a>
											</p>
											<p class="mt5 fl w">
												<c:choose>
													<c:when test="${product.form==1 }">
														落地价：
														<c:choose>
															<c:when test="${product.showLandPrice==1 }">
																<font class="cR f14">￥${pro.landPrice }</font>
															</c:when>
															<c:otherwise>
																<a href="http://${product.supWeiID}.<%=okweidomain%>?opType=3&demandId=${product.demandId}" class="cB">落地店可见</a>
															</c:otherwise>
														</c:choose>
													</c:when>
													<c:when test="${product.form==2 }">
														代理价：
														<c:choose>
															<c:when test="${product.showAgentPrice==1 }">
																<font class="cR f14">￥${pro.agentPrice }</font>
															</c:when>
															<c:otherwise>
																<a href="javascript:;" class="cB">代理商可见</a>
															</c:otherwise>
														</c:choose>
													</c:when>
												</c:choose>
											</p>
										</li>
									</c:forEach>
								</ul>
							</div>
						</div>
					</c:if>
					<div class="proPic_Wrap" id="wrapTop">
						<div style="position: relative; top: auto;" class="proPic_Wrap_title" id="firstTitle">
							<ul>
								<li id="tag1" class="hover"><a href="#cs">参数</a></li>
								<!-- <li class="" id="tag2"><a href="#tj">图集</a></li> -->
								<li class="" id="tag3"><a href="#spxq">商品详情</a></li>
								<%-- <li class="" id="tag4"><a href="#pjsd">微店主评价（<span>${product.evaluateCount }</span>）
								</a></li> --%>
								<li class="" id="tag5" style="border-right: 0px;"><a href="#jybz">交易保障</a></li>
							</ul>
							<c:if test="${product.state==1&&product.productSupType==1 }">
								<c:choose>
									<c:when test="${product.form==1 }">
										<c:if test="${product.isMyLandShop==1 }">
											<div class="Pro_jrgwc" style="display: none;">
												<a href="javascript:joinshopcart(5,2);">加入铺货单</a>
											</div>
										</c:if>
										<div class="Pro_jrgwc" style="display: none;">
											<a href="javascript:joinshopcart(4,2);">加入进货单</a>
										</div>
										<div class="Pro_ljgm" style="display: none;">
											<a href="javascript:immediately(4,2);">立即购买</a>
										</div>
									</c:when>
									<c:when test="${product.form==2 }">
										<c:choose>
											<c:when test="${product.isMyAgent==1 }">
												<div class="Pro_jrgwc" style="display: none;">
													<a href="javascript:joinshopcart(5,1);">加入铺货单</a>
												</div>
												<div class="Pro_jrgwc" style="display: none;">
													<a href="javascript:joinshopcart(4,1);">加入进货单</a>
												</div>
												<div class="Pro_ljgm" style="display: none;">
													<a href="javascript:immediately(4,1);">立即购买</a>
												</div>
											</c:when>
										</c:choose>
									</c:when>
									<c:otherwise>
										<div class="Pro_jrgwc" style="display: none;">
											<a href="javascript:joinshopcart(1,0);">加入购物车</a>
										</div>
										<div class="Pro_ljgm" style="display: none;">
											<a href="javascript:immediately(1,0);">立即购买</a>
										</div>
									</c:otherwise>
								</c:choose>
							</c:if>
						</div>
						<div style="height: 0px; margin-top: 0px; overflow: hidden;"></div>

						<!-- 商品详情 -->
						<h5 id="cs">参数</h5>
						<div class="proPic_Wrap_cs">
							<table border="0" cellpadding="0" cellspacing="0" width="100%">
								<tbody>
									<c:forEach var="params" items="${product.paramList }" varStatus="vs">
										<c:choose>
											<c:when test="${vs.index%2==0 }">
												<tr>
													<td class="td_title">${params.paramName }</td>
													<td>${params.paramValue }</td>
											</c:when>
											<c:otherwise>
												<td class="td_title">${params.paramName }</td>
												<td>${params.paramValue }</td>
												</tr>
											</c:otherwise>
										</c:choose>
									</c:forEach>
									<c:if test="${fn:length(product.paramList)%2==1 }">
										<td class="td_title">&nbsp;</td>
										<td>&nbsp;</td>
										</tr>
									</c:if>
								</tbody>
							</table>
						</div>
						<!-- 图集 -->
						<!-- 
						<h5 id="tj">图集</h5>
						<div class="proPic_Wrap_tj">
							<ul>
								<c:forEach var="bigImg" items="${product.bigImageList }">
									<li><img src="${bigImg }"></li>
								</c:forEach>
							</ul>
						</div>
						 -->
						<!-- 参数 -->
						<h5 id="spxq">商品详情</h5>
						<div class="proPic_Wrap_spxq">${product.proDes }</div>
						<!---->

						<<!-- h5 id="pjsd">评论</h5> -->
						<!-- 评价 -->

						<!-- 留言 -->
						<!-- <div class="liuyan">
							<div id="pagess"></div>
							分页

						</div> -->
						<!-- <div id="Pagination" class="page"></div> -->

						<!---->

						<!-- 购物保障 -->
						<h5 id="jybz">交易保障</h5>
						<div class="proPic_Wrap_bz">
							<img src="http://base1.okwei.com/images/bz-pic1.png"> <img src="http://base2.okwei.com/images/bz-pic2.png"> <img src="http://base3.okwei.com/images/bz-pic3.png">
						</div>
					</div>
				</div>
				<div class="clear"></div>
			</div>
		</div>
		<div class="clear"></div>
		<input type="hidden" id="priceproperty" value='${product.property }'> <input type="hidden" id="styleid" value="" />
		<form action="<%=cartdomain%>/shopCartMgtVerFive/preSubmitNew" method="post">
			<input type="hidden" id="listJson" name="listJson" />
		</form>

		<input type="hidden" id="hform" value="${product.form }"/> 
		<input type="hidden" id="showLandPrice" value="${product.showLandPrice }"/>
		 <input type="hidden" id="showAgentPrice" value="${product.showAgentPrice }"/>
		 <input type="hidden" id="publishtype" value="${product.publishtype }"/>
	</div>
<a href="#javascript:;" class="mzh_pc_zd"><img src="http://base1.okwei.com/images/img_pc_zd.png" width="41" height="41"/></a>
<script>
	
$(function(){
	$("#huodong").hide();
	$(".mzh_zcz_close").click(function(){
		$("#huodong").hide();
	})
	
	$("#mzh_ljdh").click(function(){
		$(".mzh_zcz_tc").show();
	})
	
    function zishiying(){
        var aHeight = 41;
        var aWidth= $(document).width();
        aWidth= (aWidth-1200)/2-60;
        var aOffsetTop = $(window).height();
        aOffsetTop= aOffsetTop*0.9;
        $(".mzh_pc_zd").css({right:aWidth+"px",top:aOffsetTop+"px"});
    }
    zishiying();
    $(window).resize(function(){
        zishiying();
    })

    $(window).scroll(function (){
        var mzh_this = $(this).scrollTop();
        if(mzh_this>100){
            $(".mzh_pc_zd").show();
        } else{
            $(".mzh_pc_zd").hide();
        }
    })

    $(".mzh_pc_zd").click(function(){
        var scroll_offset = $("body").offset();
        $("body,html").animate({
            scrollTop:scroll_offset.top  //让body的scrollTop等于pos的top，就实现了滚动
        },200);
    })
})
</script>

	<script>
		$(function(){
			var mzh_bgtm = $(document).height();
			$(".mzh_bgtm").height(mzh_bgtm).show();
			bWidth = 390;//弹出层的宽度
			bHeight= 330;//弹出层的高度
			aOffsetLeft = $(document).width()/2 - (parseInt(bWidth/2));
			aOffsetTop = (window.document.documentElement.clientHeight/2) - (bHeight=="auto"?150:parseInt(bHeight/2)) +  $(document).scrollTop();
			$(".mzh_zcz_tc").show();
			$(".mzh_zcz_tc").css({display:"none",left:aOffsetLeft+"px",top:aOffsetTop+"px",width:bWidth,height:bHeight});
			var a=$(window).height();
			})

</script>

<!-- 活动 -->
<div class="mzh_zcz_tc tc" id="huodong" style="display:none">
	<img src="http://base1.okwei.com/images/company/img_7_22_23.png" class="fl w">
	<div class="fl w">
		<img src="http://base1.okwei.com/images/company/img_7_22_22.jpg" class="fl" style="margin: 15px 25px 15px 60px"/>
		<div class="fl f18 mt20 line_30 tl">
			<p>该活动是APP用户</p>
			<p>专享活动，</p>
			<p>请前往APP体验！</p>
		</div>
	</div>
	<a href="http://app.okwei.com/" class="mzh_zcz_djxz">点击下载</a>
	<a href="javascript:;" class="mzh_zcz_close ml20">关闭</a>
</div>
<!-- 活动End -->
</body>
</html>