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
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的销售订单</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/statics/js/layer/layer.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/statics/js/seller/orderlist.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/statics/js/seller/orderpublic.js"></script>
<script language="JavaScript" type="text/javascript" src="<%=request.getContextPath()%>/statics/js/My97/WdatePicker.js"></script>
</head>
<body>
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

			<!-- 订单信息-操作 -->
			<div class="conter_right_xx_cz mt_20 " style="border-bottom: none;">
				<div class="conter_right_xx_cz_t" style="border-bottom: none;">
					<ul>
		            <li class="l_1 fw_b">商品</li>
		            <li class="l_2 fw_b">销售价</li>
		            <li class="l_3 fw_b">数量</li>
		            <li class="l_4 fw_b">售后</li>
		            <li class="l_5 fw_b" style="margin-left:247px;">销售总额</li>
		            <li class="l_6 fw_b" style="margin-left:115px;">状态</li>
					</ul>
				</div>
			</div>
			<c:if test="${fn:length(pageRes.list) < 1 }">
				<div class="conter_right_xx_cz_ddh" style="border-bottom: 0px; margin-bottom: 0px; border: 1px solid #e7e7e7; border-top: none">没有数据记录</div>
			</c:if>
			<c:forEach var="order" items="${pageRes.list}" varStatus="varStr">
				<div class="conter_right_xx_cz  ${varStr.index==0?'':'mt_20' }" style="border-bottom: 0px;">
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
							订单号：<span>${order.orderNo}</span>
						</div>
					 
						<div class="conter_right_xx_cz_ddh_1">
							买家：<font>${order.buyerName}</font>
						</div>
						<div class="conter_right_xx_cz_ddh_1">
							下单时间：<span>${order.createTimeStr}</span>
						</div>
					</div>
					<table class="conter_right_xx_cz_table">
						<tbody>
							<tr>
								<td class="conter_right_xx_cz_table_55">
								<c:forEach var="product" items="${order.proList }">
										<div class="conter_right_xx_cz_table_55_div" style="border: 0px; padding-top: 0px;">
											<div class="crxczt5d_0">
												<a href="javascript:void(0);"><img src="${product.productImg }"></a>
											</div>
											<div class="crxczt5d_1">
												<span><a href="javascript:void(0);" class="ft_c3">${product.productTitle }</a></span> <font>${product.property }</font>
											</div>
											<div class="crxczt5d_2">
												<font>${product.price }</font>
											</div>
											<div class="crxczt5d_3">${product.count }</div>
											<div class="crxczt5d_4">
											<c:choose>
													<c:when test="${product.refundState ==0 }">
														<a href="refund?refundId=${product.refundId }"><font style="color: #f00">申请中</font></a>
													</c:when>
													<c:when test="${product.refundState ==1 }">
														<a href="refund?refundId=${product.refundId }"><font style="color: #f00">供应商同意退款</font></a>
													</c:when>
													<c:when test="${product.refundState ==2 }">
														<a href="refund?refundId=${product.refundId }"><font style="color: #f00">供应商不同意退款</font></a>
													</c:when>
													<c:when test="${product.refundState ==3 }">
														<a href="refund?refundId=${product.refundId }"><font style="color: #f00">微店网介入</font></a>
													</c:when>
													<c:when test="${product.refundState ==4 }">
														<a href="refund?refundId=${product.refundId }"><font style="color: #f00">微店网介入，支持退款</font></a>
													</c:when>
													<c:when test="${product.refundState ==5 }">
														<a href="refund?refundId=${product.refundId }"><font style="color: #f00">买家已发货</font></a>
													</c:when>
													<c:when test="${product.refundState ==6 }">
														<a href="refund?refundId=${product.refundId }"><font style="color: #f00">退款完成</font></a>
													</c:when>
												</c:choose>
											</div>
										</div>
									</c:forEach></td>
								<td class="conter_right_xx_cz_table_15xh_fic1" >
									<div class="crxczt5d_sfk">
										<b>${order.totalPrice }</b> <span class="col_aaa">(含邮费：${order.postPrice })</span>
									</div>
								</td>
								<td class="conter_right_xx_cz_table_15_fic2" >
									<div class="crxczt5d_sfk">
										<span>${order.orderStateName }</span>
									</div>
								</td>
						<%-- 		<td class="conter_right_xx_cz_table_15" >
									<div class="crxczt5d_cz">
										<c:choose>
											<c:when test="${order.orderState==0}">
												<!-- 未付款 -->
												<a  target="_blank" href="buydetails?orderNo=${order.orderNo }" class="crxczt5d_qxdd">订单详情</a>
											</c:when>
											<c:when test="${order.orderState==1}">
												<!-- 已付款 -->
												<a href="delivery?orderNo=${order.orderNo }" class="crxczt5d_ljzf">发货</a>
												<a  target="_blank" href="buydetails?orderNo=${order.orderNo }" class="crxczt5d_qxdd">订单详情</a>
											</c:when>
											<c:when test="${order.orderState==2}">
												<!-- 已发货 -->

												<a  target="_blank" href="http://my.okwei.com/order/logistics?orderNo=${order.orderNo }" class="crxczt5d_qxdd">查看物流</a>
												<a  target="_blank" href="buydetails?orderNo=${order.orderNo }" class="crxczt5d_qxdd">订单详情</a>

											</c:when>
											<c:when test="${order.orderState==3}"> 
												<!-- 已收货 -->

												<a  target="_blank" href="http://my.okwei.com/order/logistics?orderNo=${order.orderNo }" class="crxczt5d_qxdd">查看物流</a>
												<a  target="_blank" href="buydetails?orderNo=${order.orderNo }" class="crxczt5d_qxdd">订单详情</a>

											</c:when>
											<c:when test="${order.orderState==4}">
												<!-- 已完成 -->
												<a  target="_blank" href="buydetails?orderNo=${order.orderNo }" class="crxczt5d_qxdd">订单详情</a>
											</c:when>
											<c:when test="${order.orderState==7}">
												<!-- 已取消 -->
												<a  target="_blank" href="buydetails?orderNo=${order.orderNo }" class="crxczt5d_qxdd">订单详情</a>
												<a href="javascript:void(0);" onclick="deletewin('${order.orderNo}')" class="crxczt5d_sqtk">删除订单</a>
											</c:when>
											<c:when test="${order.orderState==5}">
												<!-- 退款中 -->
												<a  target="_blank" href="buydetails?orderNo=${order.orderNo }" class="crxczt5d_qxdd">订单详情</a> 
											</c:when>
											<c:when test="${order.orderState==6}">
												<!-- 退款完成 -->
												<a  target="_blank" href="buydetails?orderNo=${order.orderNo }" class="crxczt5d_qxdd">订单详情</a> 
											</c:when>
											<c:when test="${order.orderState==13}">
												<!-- 已过期 -->
												<a href="buydetails?orderNo=${order.orderNo }" class="crxczt5d_qxdd">订单详情</a>
												<a href="javascript:void(0);"  onclick="deletewin('${order.orderNo}')"  class="crxczt5d_sqtk">删除订单</a>
											</c:when>
											<c:otherwise>
												<a  target="_blank" href="buydetails?orderNo=${order.orderNo }" class="crxczt5d_qxdd">订单详情</a>
											</c:otherwise>
										</c:choose>
									</div>
								</td> --%>
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
	</form> 
	<!-- 删除订单提示 -->
	<div id="delete_ts" class="mzh_tcc">
		<div class="mzh_tcc_1">
			是否确认删除该订单？<br />删除该订单将不再显示
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
			/* inits(); */
		});
		// 设置选中项
	/* 	function inits() {
			var dtype = GetQueryString("dt");// 订单类型
			var dstate = GetQueryString("ds");// 订单类型
			if (1 == dtype || dtype == 2) {
				$("[data-type=" + dtype + "]").addClass("yes_bgs");
				$("#datatype").val(dtype);
			}
			else if (dtype == 3) {
				window.location.href = "subscribelist?dt=" + datatype + "&ds=-1";
			}
			else {
				$("[data-type=1]").addClass("yes_bgs");
				$("#datatype").val(1);
			}
			if (dstate != null && dstate!="") {
				$("[data-state=" + dstate + "]").addClass("yes_bgs");
				$("#datastate").val(dstate);
			}
			else {
				$("[data-state=-1]").addClass("yes_bgs");
				$("#datastate").val(-1);
			}
		} */
	</script>
</body>
</html>