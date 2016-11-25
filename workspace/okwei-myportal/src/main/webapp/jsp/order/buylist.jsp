<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="pg" uri="http://www.okwei.com/pagination"%>

<%@ page import="java.util.ResourceBundle"%>
<%
	String productdomain = ResourceBundle.getBundle("domain").getString("productdomain");
    String paydomain = ResourceBundle.getBundle("domain").getString(
					"paydomain");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>我的购买订单</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/statics/js/order/orderlist.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/statics/js/layer/layer.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/statics/js/order/orderpublic.js"></script>
<script language="JavaScript" type="text/javascript" src="<%=request.getContextPath()%>/statics/js/My97/WdatePicker.js"></script>

<style>
.btn_hui28_pre {
	background: #fff none repeat scroll 0 0;
	border: 1px solid #dcdcdc;
	border-radius: 4px;
	color: #6c6c6c;
	height: 28px;
	line-height: 28px;
	padding: 0 10px;
	text-align: center;
	cursor: pointer
}

.mzh_xiayibu {
	background: #71b601 none repeat scroll 0 0;
	border-radius: 5px;
	color: #fff;
	cursor: pointer;
	float: left;
	line-height: 30px;
	padding: 0 30px;
}
div.crxczt5d_cz{margin:0px 10px;}
div.crxczt5d_3{margin-left:0px;}
div.crxczt5d_4{width:60px;}
</style>
</head>
<body>
	<input type="hidden" id="paydomain" value="<%=paydomain%>">
	<form id="searcherForm" name="searcherForm" action="buylist">
		<div class="fr conter_right">
			<!-- 订单信息 -->
			 <div class="conter_right_xx">
         <div class="oneci_ztai fl">
        	<div class="left_font tr fl f12 ft_c9">订单类型：</div>
            <div class=" fl f12">
           
            	<select class="w150 bor_si" id="ordertype">
                    <option value="-1"<c:if test="${dt==-1}">selected</c:if>>全部</option>
                    <option value="1" <c:if test="${dt==1}">selected</c:if>>零售订单</option>
                    <option value="2" <c:if test="${dt==2}">selected</c:if>>批发订单</option>
                    <option value="3" <c:if test="${dt==3}">selected</c:if>>铺货订单</option>
                    <option value="4" <c:if test="${dt==4}">selected</c:if>>进货订单</option>
            	</select> 
            </div>
        	<div class="left_font tr fl f12 ft_c9 ml_20">订单状态：</div>
            <div class=" fl f12">
            		<select class="w150 bor_si" id="orderstate">
                    <option value="-1" <c:if test="${ds==-1}">selected</c:if>>全部</option>
                    <option value="0" <c:if test="${ds==0}">selected</c:if>>未付款</option>
                    <option value="1" <c:if test="${ds==1}">selected</c:if>>已付款</option>
                    <option value="2" <c:if test="${ds==2}">selected</c:if>>已发货</option>
                    <option value="3" <c:if test="${ds==3}">selected</c:if>>已收货</option>
                    <option value="4" <c:if test="${ds==4}">selected</c:if>>已完成</option>
                    <option value="5" <c:if test="${ds==5}">selected</c:if>>退款中</option>
                    <option value="6" <c:if test="${ds==6}">selected</c:if>>已退款</option>
                    <option value="7" <c:if test="${ds==7}">selected</c:if>>已取消</option>
                    <option value="8" <c:if test="${ds==8}">selected</c:if>>等待确定</option>
                    <option value="9" <c:if test="${ds==9}">selected</c:if>>申请取消</option>
                    <option value="10" <c:if test="${ds==10}">selected</c:if>>已确定</option>
                    <option value="11" <c:if test="${ds==11}">selected</c:if>>已拒绝</option>
                    <option value="12" <c:if test="${ds==12}">selected</c:if>>已支付定金</option>
                    <option value="13" <c:if test="${ds==13}">selected</c:if>>已过期</option>
            	</select>
            </div>
      		  <input type="hidden" id="datatype" name="dt" value="-1" /> <input type="hidden" id="datastate" name="ds" value="-1" />
					<div class="xh-shurk">
					<ul>
						<li><span>&nbsp;订单号：</span> <input type="text" class="btn_h24 w164" name="orderNo" id="orderNo" value="${param.orderNo }" /></li>
						<li><span>下单时间：</span> <input value="${param.beginTimeStr }" type="text" class="btn_h24" name="beginTimeStr" id="beginTimeStr" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" /> <label>—
						</label> <input type="text" value="${param.endTimeStr }" class="btn_h24" name="endTimeStr" id="endTimeStr" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" /></li>
					</ul>
				</div>
				<div class="xh-shurk">
					<ul>
						<li><span>&nbsp;微店号：</span><input type="text" value="${param.buyerid }" class="btn_h24 w164" name="buyerid" id="buyerid" /></li>
						<li><span>付款金额：</span><input type="text" value="${param.minPrice }" class="btn_h24 w98" name="minPrice" id="minPrice" /> <label>—</label> 
						<input type="text" class="btn_h24 w98" name="maxPrice" id="maxPrice" value="${param.maxPrice }" /></li>
						<li><input type="submit" value="查询" class="btn_submit_two" style="width: 80px;"></li>
					</ul>
				</div>
				<div class="blank1"></div>
			</div>

			  </div> 		
			<%-- <div class="conter_right_xx">
				<dl class="conter_right_xx_dl" style="margin-bottom: 20px;">
					<dd style="line-height: 22px;">订单类型：</dd>
					<dt>
						<div class="left_xuanzs fl f12">
							<ul id="ordertype">
								<li data-type="1">零售订单111（<b>${orderCount.retailCount}</b>）
								</li>
								<li data-type="2">批发订单（<b>${orderCount.wholesaleCount}</b>）
								</li>
								<li data-type="3">预定订单（<b>${orderCount.reserveCount}</b>）
								</li>
							</ul>
							<input type="hidden" id="datatype" name="dt" value="1" /> <input type="hidden" id="datastate" name="ds" value="-1" />
						</div>
					</dt>
				</dl>
				<dl class="conter_right_xx_dl">
					<dd style="line-height: 22px;">订单状态：</dd>
					<dt>
						<div class="left_xuanzs fl f12">
							<ul id="orderstate">
								<li data-state="-1" style="margin-left: 0px;">所有订单</li>
								<li data-state="0" ${orderCount.waitPayCount==0?"class='zero'":""}>待付款（<b>${orderCount.waitPayCount}</b>）
								</li>
								<li data-state="1" ${orderCount.waitShipmentsCount==0?"class='zero'":""}>待发货（<b>${orderCount.waitShipmentsCount}</b>）
								</li>
								<li data-state="2" ${orderCount.waitReceivCount==0?"class='zero'":""}>待收货（<b>${orderCount.waitReceivCount}</b>）
								</li>
								<li data-state="4" ${orderCount.completeCount==0?"class='zero'":""}>交易完成（<b>${orderCount.completeCount}</b>）
								</li>
								<li data-state="5" ${orderCount.refundCount==0?"class='zero'":""}>退款中（<b>${orderCount.refundCount}</b>）
								</li>
							</ul>
						</div>
					</dt>
				</dl>
			</div>
 --%>
			<!-- 订单信息-操作 -->
				<div class="conter_right_xx_cz mt_20" style="border-bottom: 0px;">
				<div class="conter_right_xx_cz_t" style="border-bottom: none;">
					<ul>
						<li class="l_1">商品</li>
						<li class="l_2">购买价格</li>
						<li class="l_3">数量</li>
						<li class="l_4">售后</li>
						<li class="l_5" style="margin-left: 60px;">总价</li>
						<li class="l_6" style="margin-left: 100px;" >付款金额</li>
						<li class="l_7" style="margin-left: 100px;">状态</li>
						<li class="l_8">操作</li>
					</ul>
				</div>
			</div>
			<c:if test="${fn:length(pageRes.list) < 1 }">
				<div class="conter_right_xx_cz_ddh" style="border-bottom: 0px; margin-bottom: 0px; border: 1px solid #e7e7e7; border-top: none">没有数据记录</div>
			</c:if>
			<c:forEach var="order" items="${pageRes.list}" varStatus="index">
				<div class="conter_right_xx_cz 	${index.index==0?'':'mt_20' }" style="border-bottom: 0px;">
					<div class="conter_right_xx_cz_ddh">
						<div class="conter_right_xx_cz_ddh_1">
							<c:if test="${order.orderType==1||order.orderType==8||order.orderType==24 }"> 
							<span class="lsd_tb">零售单</span>
							</c:if> 
							<c:if test="${order.orderType==9}">
							<span class="lsd_tb">批发单</span>
							</c:if> 
							<c:if test="${order.orderType==19}">
							<span class="jhd_tb">进货单</span>
							</c:if> 
							<c:if test="${order.orderType==20}">
							<span class="phd_tb">铺货单</span>
							</c:if>   
							<c:if test="${order.orderType==12}">
							<span class="phd_tb">预定单</span>
							</c:if> 
						</div> 
						<div class="conter_right_xx_cz_ddh_1">
							&nbsp;订单号：<span>${order.orderNo}</span>
						</div>
						<div class="conter_right_xx_cz_ddh_1">
							供应商：<font>${order.supplyerName}</font>
						</div>
						<div class="conter_right_xx_cz_ddh_1">
							下单时间：<span>${order.createTimeStr}</span>
						</div>
					</div>
					<table class="conter_right_xx_cz_table">
						<tbody>
							<tr>
								<td class="conter_right_xx_cz_table_55"><c:forEach var="pro" items="${order.proList}">
										<div class="conter_right_xx_cz_table_55_div" style="border: 0px; padding-top: 0px;">
											<div class="crxczt5d_0">
												<a href="<%=productdomain %>/product?pid=${pro.productId}" target="_blank"><img src="${pro.productImg}"></a>
											</div>
											<div class="crxczt5d_1">

												<span><a href="<%=productdomain %>/product?pid=${pro.productId}" target="_blank" class="ft_c3">${pro.productTitle}</a></span> <font><c:out value="${pro.property}" escapeXml="false"></c:out></font>

											</div>
											<div class="crxczt5d_2">
												<font>￥${pro.price}</font>
											</div>
											<div class="crxczt5d_3">${pro.count}</div>
											<div class="crxczt5d_4">
												<!-- 售后 -->
												<c:choose>
													<c:when test="${pro.refundState ==0 }">
														<a target="_blank" href="refundetail?refundId=${pro.refundId }"><font style="color: #f00">申请中</font></a>
													</c:when>
													<c:when test="${pro.refundState ==1 }">
														<a target="_blank" href="refundetail?refundId=${pro.refundId }"><font style="color: #f00">供应商同意退款</font></a>
													</c:when>
													<c:when test="${pro.refundState ==2 }">
														<a target="_blank" href="refundetail?refundId=${pro.refundId }"><font style="color: #f00">供应商不同意退款</font></a>
													</c:when>
													<c:when test="${pro.refundState ==3 }">
														<a target="_blank" href="refundetail?refundId=${pro.refundId }"><font style="color: #f00">微店网介入</font></a>
													</c:when>
													<c:when test="${pro.refundState ==4 }">
														<a target="_blank" href="refundetail?refundId=${pro.refundId }"><font style="color: #f00">微店网介入，支持退款</font></a>
													</c:when>
													<c:when test="${pro.refundState ==5 }">
														<a target="_blank" href="refundetail?refundId=${pro.refundId }"><font style="color: #f00">买家已发货</font></a>
													</c:when>
													<c:when test="${pro.refundState ==6 }">
														<a target="_blank" href="refundetail?refundId=${pro.refundId }"><font style="color: #f00">退款完成</font></a>
													</c:when>
												</c:choose>
											</div>
										</div>
									</c:forEach></td>
								<td class="conter_right_xx_cz_table_15_fic2 tc" >
									<b> 
									<fmt:formatNumber value="${order.totalPrice-order.postPrice}" pattern="0.00"></fmt:formatNumber>
									</b>
								</td>
								<td class="conter_right_xx_cz_table_15xh_fic1" >
									<div class="crxczt5d_sfk">
										<c:choose>  
										   <c:when test="${order.orderType==20||order.orderType==12}">   
										  	<c:forEach var="picStr" items="${order.payContent }">
													<span>${picStr }</span>
												</c:forEach>   
												<%-- <div class="crxczt5d_sfk">
													<c:forEach var="amount" items="${order.amountState}">
														<span class="fw_100">${amount }</span>
													</c:forEach>
													<span class="col_aaa">(含运费：${order.postPrice})</span>
												</div> --%>
										   </c:when>  
										   <c:otherwise> 
											<b>
											<fmt:formatNumber value="${order.totalPrice}" pattern="0.00"></fmt:formatNumber>
											</b>
											 <span class="col_aaa">(含邮费：${order.postPrice })</span>
										   </c:otherwise>  
										</c:choose> 
										
									</div>
								</td>
								<td class="conter_right_xx_cz_table_15_fic2">
									<div class="crxczt5d_sfk">
										<c:choose>
											<c:when test="${order.orderState==0}">
												<span>未付款</span>
											</c:when>
											<c:when test="${order.orderState==1}">
												<span>已付款</span>
											</c:when>
											<c:when test="${order.orderState==2}">
												<span>已发货</span>
											</c:when>
											<c:when test="${order.orderState==3}">
												<span>已收货</span>
											</c:when>
											<c:when test="${order.orderState==4}">
												<span>交易完成</span>
											</c:when>
											<c:when test="${order.orderState==7}">
												<span>交易关闭</span>
											</c:when>
											<c:when test="${order.orderState==10}">
												<span>已确定</span>
											</c:when>
											<c:when test="${order.orderState==11}">
												<span>已拒绝</span>
											</c:when>
											<c:when test="${order.orderState==12}">
												<span>已支付定金</span>
											</c:when>
											<c:when test="${order.orderState==13}">
												<span>已过期</span>
											</c:when>
											<c:when test="${order.orderState==8}">
												<span>等待确定</span>
											</c:when>
											<c:when test="${order.orderState==9}">
												<span>申请取消</span>
											</c:when>
											<c:when test="${order.orderState==5}">
												<span class="col_f28300">退款中</span>
											</c:when>
											<c:when test="${order.orderState==6}">
												<span class="col_f28300">退款完成</span>
											</c:when>
											<c:otherwise></c:otherwise>
										</c:choose>
									</div>
								</td>

								<td class="conter_right_xx_cz_table_15">
									<div class="crxczt5d_cz">
									<c:choose>  
										   <c:when test="${order.orderType==20||order.orderType==12}">   
									 		<c:choose>
											<c:when test="${order.orderState==0}">
												<!-- 未付款 -->
												<a href="javascript:orderpay('${order.payOrderNo }');" class="crxczt5d_ljzf">立即支付</a>
												<a href="buydetails?orderNo=${order.orderNo }"  target="_blank" class="crxczt5d_qxdd">订单详情</a>
												<a href="javascript:void(0);" class="crxczt5d_qxdd" onclick="cancelwin('${order.orderNo }')">取消订单</a>
											</c:when>
											<c:when test="${order.orderState==1}">
												<!-- 已付款 -->
												<a href="javascript:void(0);" class="crxczt5d_ddfh">等待发货</a>
												<a href="buydetails?orderNo=${order.orderNo }"  target="_blank" class="crxczt5d_qxdd">订单详情</a>
											</c:when>
											<c:when test="${order.orderState==2}">
												<!-- 已发货 -->
												<a href="javascript:void(0);" class="crxczt5d_qrsh" onclick="confirmcargowin('${order.orderNo }')">确认收货</a>
												<a href="logistics?orderNo=${order.orderNo }" class="crxczt5d_qxdd">查看物流</a>
												<a href="buydetails?orderNo=${order.orderNo }" target="_blank"  class="crxczt5d_qxdd">订单详情</a>
											</c:when>
											<c:when test="${order.orderState==3}">
												<!-- 已收货 -->
												<c:if test="${order.tailPayType==0 }">
													<!-- 
													<a href="javascript:void(0);" class="crxczt5d_pjsd">评价晒单</a>
													 -->
												</c:if>
												<c:if test="${order.tailPayType==1}">
													<a href="javascript:orderpay('${order.payOrderNo }');" class="crxczt5d_ljzf">支付尾款</a>
												</c:if>
												<a href="buydetails?orderNo=${order.orderNo }" target="_blank" class="crxczt5d_qxdd">订单详情</a>
											</c:when>

											<c:when test="${order.orderState==4}">
												<!-- 已完成 -->
												<!-- 
												<a href="javascript:void(0);" class="crxczt5d_pjsd">评价晒单</a>
												-->
												<a href="buydetails?orderNo=${order.orderNo }"  target="_blank" class="crxczt5d_qxdd">订单详情</a>
											</c:when>
											<c:when test="${order.orderState==7}">
												<!-- 已取消 -->
												<!-- 
												<a href="javascript:void(0);" class="crxczt5d_ljzf">重新下单</a>
												 -->
												<a href="buydetails?orderNo=${order.orderNo }"  target="_blank"  class="crxczt5d_qxdd">订单详情</a>
												<a href="javascript:void(0);" class="crxczt5d_sqtk" onclick="deletewin('${order.orderNo }')">删除订单</a>
											</c:when>
											<c:when test="${order.orderState==5}">
												<!-- 退款中 -->
												<a href="javascript:void(0);" class="crxczt5d_pjsd" onclick="cancelrefundwin('${order.orderNo}')">取消退款</a>
												<a href="buydetails?orderNo=${order.orderNo }"  target="_blank"  class="crxczt5d_qxdd">订单详情</a>
											</c:when>
											<c:when test="${order.orderState==8}">
												<!-- 等待确定 -->
												<a href="javascript:void(0);" class="crxczt5d_ddfh">等待确认</a>
												<a href="buydetails?orderNo=${order.orderNo }"  target="_blank" class="crxczt5d_qxdd">订单详情</a>
												<a href="javascript:void(0);" class="crxczt5d_qxdd" onclick="cancelwin('${order.orderNo }')">取消订单</a>
											</c:when>
											<c:when test="${order.orderState==10}">
												<!-- 已确定 -->
												<a href="javascript:orderpay('${order.payOrderNo }');" class="crxczt5d_ljzf">立即支付</a>
												<a href="buydetails?orderNo=${order.orderNo }"  target="_blank" class="crxczt5d_qxdd">订单详情</a>
												<a href="javascript:void(0);" class="crxczt5d_qxdd" onclick="cancelwin('${order.orderNo }')">取消订单</a>
											</c:when>
											<c:when test="${order.orderState==11}">
												<!-- 已拒绝 -->
												<a href="buydetails?orderNo=${order.orderNo }"  target="_blank" class="crxczt5d_qxdd">订单详情</a>
												<a href="javascript:void(0);" class="crxczt5d_sqtk" onclick="deletewin('${order.orderNo }')">删除订单</a>
											</c:when>
											<c:when test="${order.orderState==12}">
												<!-- 已支付定金 -->
												<c:if test="${order.tailPayType==0 }">
													<a href="javascript:orderpay('${order.payOrderNo }');" class="crxczt5d_ljzf">支付尾款</a>
												</c:if>
												<c:if test="${order.tailPayType==1 }">
													<a href="javascript:void(0);" class="crxczt5d_ddfh">等待发货</a>
												</c:if>
												<a href="buydetails?orderNo=${order.orderNo }"  target="_blank" class="crxczt5d_qxdd">订单详情</a>
											</c:when>
											<c:otherwise>
												<a href="buydetails?orderNo=${order.orderNo }"  target="_blank" class="crxczt5d_qxdd">订单详情</a>
											</c:otherwise>
										</c:choose>
										   </c:when>  
										   <c:otherwise> 
										   <c:choose>
											<c:when test="${order.orderState==0}">
												<a href="javascript:gotoPay('${order.orderNo }');" class="crxczt5d_ljzf" style="color: #FFF;">立即支付</a>
												<a target="_blank" href="buydetails?orderNo=${order.orderNo }" class="crxczt5d_qxdd">订单详情</a>
												<a href="javascript:void(0);" class="crxczt5d_qxdd" onclick="cancelwin('${order.orderNo }')">取消订单</a>
											</c:when>
											<c:when test="${order.orderState==1}">
												<a target="_blank" href="javascript:void(0);" class="crxczt5d_ddfh">等待发货</a>
												<a target="_blank" href="buydetails?orderNo=${order.orderNo }" class="crxczt5d_qxdd">订单详情</a>
												<a target="_blank" href="/order/applyrefund?orderNo=${order.orderNo }" class="crxczt5d_sqtk">申请退款</a>
											</c:when>
											<c:when test="${order.orderState==2}">
												<a href="javascript:void(0);" class="crxczt5d_qrsh" onclick="confirmcargowin('${order.orderNo }')">确认收货</a>
												<a target="_blank" href="logistics?orderNo=${order.orderNo }" class="crxczt5d_qxdd">查看物流</a>
												<a target="_blank" href="buydetails?orderNo=${order.orderNo }" class="crxczt5d_qxdd">订单详情</a>
											</c:when>
											<c:when test="${order.orderState==3}">
												<a target="_blank" href="buydetails?orderNo=${order.orderNo }" class="crxczt5d_qxdd">订单详情</a>
												<a target="_blank" href="/order/applyrefund?orderNo=${order.orderNo }" class="crxczt5d_sqtk">申请退款</a>
											</c:when>
											<c:when test="${order.orderState==4}">
												<a target="_blank" href="buydetails?orderNo=${order.orderNo }" class="crxczt5d_qxdd">订单详情</a>
											</c:when>
											<c:when test="${order.orderState==7}">
												<!--  <a href="javascript:void(0);" class="crxczt5d_ljzf">重新下单</a> -->
												<a target="_blank" href="buydetails?orderNo=${order.orderNo }" class="crxczt5d_qxdd">订单详情</a>
												<a href="javascript:void(0);" class="crxczt5d_sqtk" onclick="deletewin('${order.orderNo }')">删除订单</a>
											</c:when>
											<c:when test="${order.orderState==5}">
												<a href="javascript:void(0);" class="crxczt5d_pjsd" onclick="cancelrefundwin('${order.refundId}')">取消退款</a>
												<a target="_blank" href="buydetails?orderNo=${order.orderNo }" class="crxczt5d_qxdd">订单详情</a>
											</c:when>
											<c:when test="${order.orderState==13}">
												<!-- 过期 -->
												<a href="javascript:void(0);" class="crxczt5d_sqtk" onclick="deletewin('${order.orderNo }')">删除订单</a>
												<a target="_blank" href="buydetails?orderNo=${order.orderNo }" class="crxczt5d_qxdd">订单详情</a>
											</c:when>

											<c:otherwise>
												<a target="_blank" href="buydetails?orderNo=${order.orderNo }" class="crxczt5d_qxdd">订单详情</a>
											</c:otherwise>
										</c:choose>
										  </c:otherwise>  
										</c:choose> 
									
										
									</div>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</c:forEach>
		</div>
		<!-- 分页 -->
		<div class="pull-right">
			<pg:page pageResult="${pageRes}" />
		</div>
		<!-- 取消订单提示 -->
		<div id="cancel_ts" class="mzh_tcc">
			<div class="mzh_tcc_1">是否确认取消该订单？</div>
		</div>
		<!-- 删除订单提示 -->
		<div id="delete_ts" class="mzh_tcc">
			<div class="mzh_tcc_1">
				是否确认删除该订单？<br />删除该订单将不再显示
			</div>
		</div>
		<!-- 确认收货提示 -->
		<div id="quren_ts" class="mzh_tcc">
			<div class="mzh_tcc_1">您将进行确认收货操作，请确定收到货物才进行此操作</div>
		</div>
		<!-- 取消退款提示 -->
		<div id="cancelrefund_ts" class="mzh_tcc">
			<div class="mzh_tcc_1">您将进行取消退款操作?</div>
		</div>
		<!-- 申请退款提示 -->
		<div id="refund_ts" class="mzh_tcc">
			<div class="mzh_tcc_1">
				您将对该订单进行退款处理，退款申请将提交给供应商<br />供应商将在48小时内回应；超时则默认同意您的请求！
			</div>
		</div>
		<!-- 支付确认 -->
		<div class="updata_tixian layer_pageContent" style="display: none;" id="win_div_4">
			<div class="updata_tixian">
				<div class="fl mzh_width_100">
					<ul class="p10">
						<li class="f14">系统检测您已发起过支付，请确认支付是否完成或支付账户是否扣款；</li>
						<li class="f14 mt_10">若支付失败，点击-继续支付，将重新发起支付。</li>
						<li class="fl mt_47"><input name="button" type="button" class="mzh_xiayibu" style="padding: 0px 20px;" value="继续支付"> <input type="hidden" id="payOrderID" value="">
							<div class="dis_b ml_20 fl  btn_hui28_pre shou">取消支付</div></li>
					</ul>
				</div>
			</div>
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
				 inits();//初始化 
			});
			
			// 设置选中项
			function inits() {
				var dtype = GetQueryString("dt");// 订单类型
				var dstate = GetQueryString("ds");// 订单类型
				$("#datatype").val(dtype);
				$("#datastate").val(dstate);
				
				/* if (1 == dtype || dtype == 2 || dtype == -1) {
					$("[data-type=" + dtype + "]").addClass("yes_bgs");
					$("#datatype").val(dtype);
				} else if (dtype == 4) {
					window.location.href = "reservelist?dt=" + datatype + "&ds=-1";
				} else {
					$("[data-type=1]").addClass("yes_bgs");
					$("#datatype").val(1);
				}

				if (dstate != null && dstate != "") {
					$("[data-state=" + dstate + "]").addClass("yes_bgs");
					$("#datastate").val(dstate);
				} else {
					$("[data-state=-1]").addClass("yes_bgs");
					$("#datastate").val(-1);
				} */
			}
		</script>
	</form>
</body>
</html>