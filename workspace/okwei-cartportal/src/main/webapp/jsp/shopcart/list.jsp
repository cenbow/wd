<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>  
<%@ page import="java.util.ResourceBundle"%>
<%
	String productdomain = ResourceBundle.getBundle("domain").getString("productdomain");
	String okweidomain = ResourceBundle.getBundle("domain").getString("okweidomain");
	String mydomain = ResourceBundle.getBundle("domain").getString("mydomain");
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    String shopdomain = ResourceBundle.getBundle("domain").getString("shopdomain");
%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="http://base1.okwei.com/js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="http://base1.okwei.com/js/shopcart/regular.js"></script>
<script type="text/javascript" src="http://base1.okwei.com/js/shopcart/list.js"></script>
<style type="text/css">
.phd_tb{float: left;padding: 0 8px;line-height: 20px;color: #fff;font-size: 12px;font-family: "宋体";background: #45c01a;text-indent: 0px;border-radius: 3px;margin: 10px 0 0 25px;}
.jhd_tb{float: left;padding: 0 8px;line-height: 20px;color: #fff;font-size: 12px;font-family: "宋体";background: #8cbe52;text-indent: 0px;border-radius: 3px;margin: 10px 0 0 25px;}
.pfd_tb{float: left;padding: 0 8px;line-height: 20px;color: #fff;font-size: 12px;font-family: "宋体";background: #b90000;text-indent: 0px;border-radius: 3px;margin: 10px 0 0 25px;}
.lsd_tb{float: left;padding: 0 8px;line-height: 20px;color: #fff;font-size: 12px;font-family: "宋体";background: #6e80a6;text-indent: 0px;border-radius: 3px;margin: 10px 0 0 25px;}
.xinxi_fadb61 {
    background: #fffaec none repeat scroll 0 0;
    border: 1px solid #f4cea8;
    float: left;
    line-height: 30px;
    text-indent: 20px;
}
.ft_e{background: #eee;color: #ccc;}
.ft_e .ft_c3{color: #ccc;}
.ft_e .ft_red {color: #ccc;}
.fic{margin-top: 22px;}
</style>
<title>购物车</title>
</head>    
<body class="bg_f3">
<div class="outermost">
	<div class="head_two">
    	<div class="mar_au">
        	<div class="head_logo fl"><a href="http://www.<%=okweidomain%>"><img src="http://base3.okimgs.com/images/xh_logo.gif" /></a></div>
            <div class="head_gwc fl f24 ft_c6">购物车</div>
            <div class="jidong_tiao fr">
            	<div class="dut_imgs"></div>
                <div class="font_imgsone f12 ft_red">购物车</div>
                <div class="font_imgstwo f12 ft_c9">确认订单</div>
                <div class="font_imgsthr f12 ft_c9">支付</div>
                <div class="font_imgsfor f12 ft_c9">完成</div>
            </div> 
        </div>
    </div> 
    <div class="blank"></div>
<div class="tab_qie">
    	<div class="blank1"></div>
    	
       <c:choose>
       	<c:when test="${fn:length(list)>0}">
        <div class="fl quan_xuz">
        	<div class="top_bar">
            	<div class="bar_yi1 ft_c3 fl fw_b">&nbsp;</div>
                <div class="bar_yi2 ft_c3 fl fw_b">商品信息</div>
                <div class="bar_yi3 ft_c3 fl fw_b tc">单价（元）</div>
                <div class="bar_yi4 ft_c3 fl fw_b tc">数量</div>
                <div class="bar_yi5 ft_c3 fl fw_b tc">金额（元）</div>
                <div class="bar_yi6 ft_c3 fl fw_b tc">来源</div>
            	<div class="bar_yi7 ft_c3 fl fw_b">操作</div>
            </div>
            <form action="shopCartMgtVerFive/preSubmitNew" method="post">
            	<input type="hidden" id="scidJson" name="listJson" />
            </form>
            <!-- 购物车店铺循环开始 -->
            <c:forEach items="${list}" var="shoppingCar" varStatus="cartSta">
            <input type="hidden" id="cart_companyname_${cartSta.index}"   value="${shoppingCar.companyName}"/>
            <div class="shopp_count fl" demandid = "${shoppingCar.demandId}" supplierweiid = "${shoppingCar.supplierWeiId}" buyType="${shoppingCar.buyType}"  id="zysaa" data="companybuytype${cartSta.index}" name="companybuytype" source="${shoppingCar.source}" isFirstOrder="${shoppingCar.isFirstOrder}" firstOrderAmount="${shoppingCar.firstOrderAmount}">
            	<div class="shop_name f12 ft_sihui fl" id="company_${cartSta.index}">
            		<input type="checkbox" name="cart_cb" value="${cartSta.index}" />店铺：
            		<span class="ft_c3">${shoppingCar.companyName}</span>
            		<c:choose>
		   				<c:when test="${shoppingCar.buyType == 5}">
		   					<span class="phd_tb" style="float: none; display: inline-block;">铺货单</span>
		   				</c:when>
		   				<c:when test="${shoppingCar.buyType == 4}">
		   					<span class="jhd_tb" style="float: none; display: inline-block;">进货单</span>
		   				</c:when>
		   				<c:when test="${shoppingCar.buyType == 3}">
		   					<span class="pfd_tb" style="float: none; display: inline-block;">批发单</span>
		   				</c:when>
		   				<c:when test="${shoppingCar.buyType == 1}">
		   					<span class="lsd_tb" style="float: none; display: inline-block;">零售单</span>
		   				</c:when>
            		</c:choose>
            	</div>
            	<div class="fl mzh_width_100 f14 xinxi_fadb61" name="allowedlandingprompt" style="display:none;color:red;">
            		您本次的购物金额满足落地店的标准，订单支付后自动成为该商城的落地店，今后在该商城购物可享受更多优惠！
            		<span class="color_red">（加盟即赠现金券，多买多赠哦）</span>
            	</div>
				<div class="fl mzh_width_100 f14 xinxi_fadb61" name="notallowedlandingprompt" style="display:none;color:red;">
					该系列内的产品采购满<span name="allowfirstamount">${shoppingCar.firstOrderAmount}</span>元，即可成为该商城的
					<a target="_blank" style="color: #3366cc;" href="http://www.${shoppingCar.supplierWeiId}.<%=okweidomain%>?opType=5&w=">落地店</a> 
					。您需要再购买该系列价值<span class="color_red" name="notallowedlandingpromptprice">
					￥382元
	                </span>的产品！
	                <span class="color_red">（加盟即赠现金券，多买多赠哦）</span>
	                <a style="color: #3366cc;" target="_blank" class=" f14 mr_10 fr" href="http://www.${shoppingCar.supplierWeiId}.<%=okweidomain%>?opType=3&demandId=${shoppingCar.demandId}&w=">返回系列区采购&gt;&gt;</a>
                </div>
                <div class="fl mzh_width_100 f14 xinxi_fadb61" style="display:none;color:red;" name="distirbutionprompt">铺货订单不能和其他类型的订单混合结算,请单独结算!</div>
            	<!-- 购物车商品循环开始 -->
            	<c:forEach items="${shoppingCar.shoppingCarList}" var="product" varStatus="scSta">
            		<c:forEach items="${product.style}" var="style" varStatus="scSta">
            			
 							  <div class="shop_comm bor_bo fl" name="mzh_hover" styleBuyType_"${shoppingCar.buyType}"  ishover="0" id="prodDiv_${style.scid}">

		            	<input type="hidden" id="prod_style_${style.scid}" value="${style.styleId}"/>

						
		            	<input type="hidden" id="prod_nowprice_${style.scid}" value="${style.price}"/>
		            	<input type="hidden" id="sel_style_${style.scid}" value="${style.styleId}"/>

                		<div class="comm_001 fl">
                			<c:choose>
                			<c:when test="${style.status==1 && style.count>0}">
                			<c:if test="${style.isActivity==1 }"><input type="checkbox" tradeWeiId="${style.tradeWeiId}" shareweiid=${style.shareOne } count="${style.count}" styleId="${style.styleId}" scid="${style.scid}" class="companybuytype${cartSta.index}" price="${style.activityPrice}"  source="${style.source}" buyType="${shoppingCar.buyType}" name="product_cb" status="${style.status}" productId="${product.proNum}" value="${style.scid}" companyLogid="${shoppingCar.supplierWeiId}" companyIndex="${cartSta.index}"/></c:if>
                			<c:if test="${style.isActivity==0 }"><input type="checkbox" tradeWeiId="${style.tradeWeiId}" shareweiid=${style.shareOne } count="${style.count}" styleId="${style.styleId}" scid="${style.scid}" class="companybuytype${cartSta.index}" price="${style.price}"  source="${style.source}" buyType="${shoppingCar.buyType}" name="product_cb" status="${style.status}" productId="${product.proNum}" value="${style.scid}" companyLogid="${shoppingCar.supplierWeiId}" companyIndex="${cartSta.index}"/></c:if>
                			</c:when>
                			<c:otherwise>
                			<input type="checkbox" tradeWeiId="${style.tradeWeiId}" shareweiid=${style.shareOne } count="${style.count}" styleId="${style.styleId}" scid="${style.scid}" class="companybuytype${cartSta.index}" price="${style.price * style.count}"  source="${style.source}" buyType="${shoppingCar.buyType}" name="product_cb" status="${style.status}" productId="${product.proNum}" value="${style.scid}" companyLogid="${shoppingCar.supplierWeiId}" companyIndex="${cartSta.index}"  disabled="disabled"/>
                			</c:otherwise>
                			</c:choose>
                		</div>
	                    <c:choose>
	                		<c:when test = "${style.source == 1}">
		                		<div class="comm_002 fl">
			                    	<a href="http://detail.<%=okweidomain%>/product?pid=${product.proNum}&f=2" target="_blank">
			                    		<img id="prod_img_${style.scid}" src="${style.image}" />
			                    	</a>
		                    	</div>
		                   		<div class="comm_003 fl">
		                    		<a href="http://detail.<%=okweidomain%>/product?pid=${product.proNum}&f=2" target="_blank" class="ft_c3">
		                    		${style.proTitle}
		                    		</a>
		                    	</div>
	                		</c:when>
	                		<c:when test = "${style.source == 2}">
	                			<div class="comm_002 fl">
			                    	<a href="http://detail.<%=okweidomain%>/product?pid=${product.proNum}&f=1" target="_blank">
			                    		<img id="prod_img_${style.scid}" src="${style.image}" />
			                    	</a>
		                    	</div>
		                   		<div class="comm_003 fl">
		                    		<a href="http://detail.<%=okweidomain%>/product?pid=${product.proNum}&f=1" target="_blank" class="ft_c3">
		                    		${style.proTitle}
		                    		</a>
		                    	</div>
	                		</c:when>
	                		<c:otherwise>
	                			<div class="comm_002 fl">
			                    	<a href="http://detail.<%=okweidomain%>/product?pid=${product.proNum}" target="_blank">
			                    		<img id="prod_img_${style.scid}" src="${style.image}" />
			                    	</a>
		                    	</div>
		                   		<div class="comm_003 fl">
		                    		<a href="http://detail.<%=okweidomain%>/product?pid=${product.proNum}" target="_blank" class="ft_c3">
		                    		${style.proTitle}
		                    		</a>
		                    	</div>
	                		</c:otherwise>
                		</c:choose>
	                    <div class="comm_004 fl">
	                    	<div class="comm_bianjno_mr fl" name="mzh_fangshang">
	                        	<div class="img_relcomm" name="mzh_click">
	                        	<img src="http://base1.okwei.com/images/bianji_yangsimgs.png"/></div>
	                            <div class="img_bianji" source="${style.source}" buyType = "${shoppingCar.buyType}" image="${style.image}" price="${style.price}" scid="${style.scid}" styleId="${style.styleId}" productId="${product.proNum}" isactivity="${style.isActivity }" activityprice="${style.activityPrice }" >编辑</div>
	                            <div class="count fl">
	                            	<div class="ys_color fl f12 ft_c3" id="property_${style.scid}">
	                            		<c:forEach items="${style.pProductStyleKVVOList}" var="pAV" varStatus="pSSta">
	                            			<c:choose>
                								<c:when test = "${pAV.attributeName == null || pAV.attributeName == '-1' || pAV.attributeName == ''}">
                									<span>默认</span>
                									 <c:choose>
                									 	<c:when test = "${pAV.keyIdName == null || pAV.keyIdName == '-1' || pAV.keyIdName == ''}">
                									 		<span>:默认</span>
                									 	</c:when>
                									 	<c:otherwise> 
                									 		<span>:${pAV.keyIdName}</span>
                									 	</c:otherwise>
                									 </c:choose>
                								</c:when>
                								<c:when test = "${pAV.keyIdName == null || pAV.keyIdName == '-1' || pAV.keyIdName == ''}">
                									<span>${pAV.attributeName}</span>
                									<span>:默认</span>
                									<br>
                								</c:when>
                								<c:otherwise> 
                									<span>${pAV.attributeName}</span>
                									<span>:${pAV.keyIdName}</span>
                									<br>
                								</c:otherwise>
                							</c:choose>
	                            		</c:forEach>
	                            	</div>
	                            	<c:if test="${style.pProductStyleKVVOList == null || fn:length(style.pProductStyleKVVOList) < 1}">
										<span>默认</span>
                						<span>:默认</span>
									</c:if>
	                            </div>
<!-- 	                            产品样式弹出层 -->
	                            <div class="elect_color" >
	                            	<div class="icon_lefsan"><img src="http://base2.okwei.com/images/leftimg_icom.png" /></div>
	                            	<div class="elect_left fl">
	                                	<div class="zuo_ones fl">
	                                		<c:forEach items="${sc.styles}" var="productStyleParam" varStatus="stylesSta">
	                                    	<div class="ones_color">
	                                        	<div class="color_font fl">
	                                        	<c:choose>
	                                        		<c:when test="${productStyleParam.proSellKey.attributeName == '-1'}">默认：</c:when>
	                                        		<c:otherwise>${productStyleParam.proSellKey.attributeName}：</c:otherwise>
	                                        	</c:choose>
	                                        	</div>
	                                            <div class="color_sel fl">
	                                            	<ul sxCount="${fn:length(sc.styles)}" scid="${sc.sCID}">
	                                                	<c:forEach items="${productStyleParam.proSellValue}" var="productSellValue">
	                                                		<c:choose>
	                                                			<c:when test="${fn:length(productStyleParam.selectStyle)>0}">
			                                                		<c:forEach items="${productStyleParam.selectStyle}" var="selectStyleMap">
			                                                			<c:choose>
			                                                				<c:when test="${selectStyleMap.key==productSellValue.attributeId && selectStyleMap.value==productSellValue.keyId}">
			                                                					<li class="bor_redsel" scid="sc_${sc.sCID}" defaultSel="1" name="mzh_${sc.proNum}-${productSellValue.attributeId}" productId="${sc.proNum}" attrId="${productSellValue.attributeId}" keyId="${productSellValue.keyId}">
			                                                					<c:choose><c:when test="${productSellValue.value == '-1'}">默认</c:when><c:otherwise>${productSellValue.value}</c:otherwise></c:choose></li>
			                                                				</c:when>
			                                                				<c:otherwise>
			                                                					<li class="bor_heisel" scid="sc_${sc.sCID}" defaultSel="0" name="mzh_${sc.proNum}-${productSellValue.attributeId}" productId="${sc.proNum}" attrId="${productSellValue.attributeId}" keyId="${productSellValue.keyId}">
			                                                					<c:choose><c:when test="${productSellValue.value == '-1'}">默认</c:when><c:otherwise>${productSellValue.value}</c:otherwise></c:choose></li>
			                                                				</c:otherwise>
			                                                			</c:choose>
		                                                			</c:forEach>
	                                                			</c:when>
	                                                			<c:otherwise>
	                                                				<li class="bor_heisel" scid="${sc.sCID}" defaultSel="0" name="mzh_${sc.proNum}-${productSellValue.attributeId}" productId="${sc.proNum}" attrId="${productSellValue.attributeId}" keyId="${productSellValue.keyId}" >
	                                                				<c:choose><c:when test="${productSellValue.value == '-1'}">默认</c:when><c:otherwise>${productSellValue.value}</c:otherwise></c:choose>
	                                                				</li>
	                                                			</c:otherwise>
	                                                		</c:choose>
	                                                	</c:forEach>
	                                                </ul>
	                                            </div> 
	                                        </div> 
	                                        </c:forEach>
	                                        <div class="ones_cprice">
	                                        	<div class="color_font fl">价格：</div>
	                                            <div class="color_sel fl">
	                                            	<ul>
	                                                	<li class="f12 ft_c6" id="styleprice_${style.scid}">${sc.nowprice}</li> 
	                                                </ul>
	                                            </div> 
	                                        </div>
	                                        <div class="bot_redyes fl">
	                                        	<a href="javascript:void(0)" class="ft_white tc btn_yes" name="mzh_ok" scid="${style.scid}">确定</a>
	                                            <a href="javascript:void(0)" class="btn_qxia ft_lan tc" name="mzh_close">取消</a>
	                                        </div>
	                                    </div>
	                                    <div class="blank1"></div> 
	                                </div>
	                                <div class="elect_right fl"><img id="sel_img_sc_${style.scid}" src="${sc.image}" /></div>
	                            </div>
								<!-- 	     弹出层窗结束 -->
	                        </div>
	                    </div>
	                    <div class="comm_005 fl">
	                    	<div class="tc f12 fic fw_b ft_c3">
	                    		<span id="nowPrice_${style.scid}">
	                    			<!--<c:choose>
                						<c:when test = "${shoppingCar.buyType == 1}">
                						<c:if test="${style.isActivity==1}"><span>现价:</span><lable style="text-decoration: line-through;">${style.price}</lable><br><span>活动价:</span></c:if>
			                    			<c:if test="${style.isActivity==0}"><span>零售价:</span></c:if>
	                    				</c:when>
	                    				<c:when test = "${shoppingCar.buyType == 3}">
	                    					<span>批发价：</span>
	                    				</c:when>
	                    				<c:when test = "${shoppingCar.buyType == 4 || shoppingCar.buyType == 5}">
	                    					<c:choose>
	                    						<c:when test = "${style.source == 1}">
	                    							<span>代理价:</span>
	                    						</c:when>
	                    						<c:when test = "${style.source == 2}">
	                    							<span>落地价:</span>
	                    						</c:when>
	                    						<c:otherwise>   
	                    							<c:if test="${style.isActivity==1}"><span>活动价:</span></c:if>
			                    					<c:if test="${style.isActivity==0}"><span>零售价:</span></c:if>
	                    						</c:otherwise>
	                    					</c:choose>
	                    				</c:when>
	                    			</c:choose>-->
			                    		<c:if test="${style.isActivity==1}"><font>${style.activityPrice}</font></c:if>			                    		
		                   				<c:if test="${style.isActivity==0}"><font>${style.price}</font></c:if>
	                    		</span>
	                    	</div>
	                        <!-- <div class="sel_xiala ft_red">折扣活动</div> -->
	                    </div>
	                    <div class="comm_006 fl">
	                    	<div class="jia_count">
	                        	<ul>
	                            	<li class="jia_img tc f16 ft_sihui" name="mzh_jian" value="${sc.sCID}" state="${style.status}">-</li>
	                                <li class="fot_inp">
<%-- 	                                	    <input type="text" id="prod_count_${sc.sCID}" productId="${sc.proNum}" value="${style.count}" name="prodCountText" scid="${sc.sCID}"/> --%>
	                               			<input type="text" count="${style.count}" price="${style.price}" wholesalePriceListJson='${product.wholesalePriceListJson}' supplierWeiId="${shoppingCar.supplierWeiId}" sellerWeiId="${shoppingCar.sellerWeiId}" id="prod_count_${style.scid}" buyType="${shoppingCar.buyType}" productId="${product.proNum}" value="${style.count}" name="prodCountText" scid="${style.scid}" state="${style.status}" maxlength="5"/>
	                                </li>
	                                <li class="jian_img tc f16 ft_c6" name="mzh_jia" value="${style.scid}" state="${style.status}">+</li>
	                            </ul>
	                        </div>
	                    </div>
	                    <div class="comm_007 fl tc f14 ft_red mt13">
	                    	<span id="totalPrice_${style.scid}">
<%-- 	                    		<fmt:formatNumber type="number" value="${sc.totalPrice}" maxFractionDigits="2"/> --%>
								<c:if test="${style.isActivity==1}"><fmt:formatNumber value='${style.activityPrice * style.count}' pattern='#0.00'/></c:if>			                    		
		                   		<c:if test="${style.isActivity==0}"><fmt:formatNumber value='${style.price * style.count}' pattern='#0.00'/></c:if>	                    		
	                    	</span>
	                    	<br/>
	                    	<c:if test="${style.status==0}">
		                    	<span class="ft_c6 f12">
		                    		已下架
		                    	</span>
	                    	</c:if>
	                    </div>
	                    <div class="comm_008 fl tc f12 ft_c3">
	                    	<c:choose>
                   				<c:when test="${shoppingCar.shopName != null && shoppingCar.shopName != ''}">
                   					${shoppingCar.shopName}
                   				</c:when>
                   				<c:otherwise>
                   					无
                   				</c:otherwise>
                   			</c:choose>
	                    	                    
	                    </div>
	                    <div class="comm_009 fl"><a href="javascript:signDel(${style.scid},${cartSta.index})" class="ft_c3">删除</a></div> 
	                </div>
 							
            			
            		</c:forEach>
            	</c:forEach>
            	<!-- 购物车商品循环结束 -->
            </div>
            </c:forEach>
            <!-- 购物车店铺循环结束 -->
            <div class="blank1"></div>
            <div class="fl jsus bor_si bg_white">
            	<div class="update_shop ft_c3 fl">
<!--                 	<span><input type="checkbox" name="quanChe"/>全选</span> -->
<!--                     <a href="javascript:batchDel();" class="ft_c3">删除</a> -->
                </div>
                <div class="fr wind_up">
                	<div class="wind_one f12 ft_c3 fl">已选商品 <span class="f18 ft_red" id="selProd">0</span>件</div>
                    <div class="wind_one f12 ft_c3 fl">合计(不含运费)： <span class="f18 ft_red" id="selProdPrice"><fmt:formatNumber value='' pattern='#0.00'/></span></div>
                    <div class="wind_two_no fl" id="jiesuan_div"><a href="javascript:void(0)" id="jiesuan">结算</a></div>
                </div>
            </div>
        </div>
      	</c:when>
      	<c:otherwise>
      <!-- 购物车为空 -->
	  <div class="fl quan_xuz">
	    <div class="shopp_count fl">
	      <div class="null_date">
	        <div class="date_kone fl"><img src="<%=path%>/statics/images/gwc_datenull.png" /></div>
	        <div class="date_ktwo ml_10 fl">
	          <p class="f16" style="font-weight:700;">您的购物车还是空的，赶紧行动吧，您可以</p>
	          <p class="f16">看看<a target="_blank" href="http://www.<%=okweidomain %>/list/list?w=${user.tgWeiID}" class="cB">云端产品库</a>或<a target="_blank" href="<%=mydomain %>/order/buylist" class="cB">我的购买订单</a></p>
	        </div>
	      </div>
	    </div>
	  </div>
      	</c:otherwise>
      </c:choose>
    </div>
</div>    
<div class="blank"></div>
<div class="updata_tixian f16" style="display:none;" id="win_div_1">
    您的进货订单不满足落地店的采购标准，请补货后再提交订单！
</div>

<div class="updata_tixian f16" style="display:none;" id="win_div_2">
    铺货订单不能和其他类型的订单合并结算，请单独结算
</div>
</body>
</html>
