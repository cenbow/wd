<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="pg" uri="http://www.okwei.com/pagination"%>
<%@ page import="java.util.ResourceBundle"%>
<%
    String mydomain = ResourceBundle.getBundle("domain").getString("mydomain");
	String sellerdomain = ResourceBundle.getBundle("domain").getString("sellerdomain");

	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>我的分销商</title>
<script src="<%=request.getContextPath()%>/statics/js/mzh_dd.js"></script>


</head>

<body style="background: #f3f3f3;">
	<form id="searcherForm" name="searcherForm"
		action="<%=basePath%>manage/list" onsubmit="return false;">
		<input type="hidden" name="ptype" value="${manavo.ptype}" /> <input
			type="hidden" name="c" value="${manavo.content}" />
		<div class="fl conter_right">
			<!-- 供应管理 -->
			<div class="gygl_top">
				<div class="gygl_top_l">
					<div class="gygl_top_l_tp">
						<img src="${userinfo.weiImg}">
					</div>
					<div class="gygl_top_l_xx mt_20">
						<div class="gygl_top_l_xx_name f14">${userinfo.weiName}的微店</div>
						<b class="gygl_top_l_xx_name f14">${userinfo.weiID}</b>
						<div class="gygl_top_l_xx_ypr mt_10">
						<c:if test="${manavo.yun==1 }">
							<span class="gygl_top_l_xx_hong mr_5">云</span> 
						</c:if>
						<c:if test="${manavo.batch==1 }">
							<span class="gygl_top_l_xx_lan mr_5">批</span> 
						</c:if>
						<c:if test="${manavo.verifer==1 }">
								<span class="gygl_top_l_xx_huang mr_5">认</span>
						</c:if>
						</div>
					</div>
				</div>
				<div class="gygl_top_r f14">
					我销售的订单：&#12288;&#12288;待付款( <span class="color_red">${manavo.ordercount.needpay}</span>
					)&#12288;&#12288;&#12288;需发货( <span class="color_red">${manavo.ordercount.needsend}</span>
					)&#12288;&#12288;&#12288;待收货( <span class="color_red">${manavo.ordercount.needreseive}</span>
					)
				</div>
			</div>

			<!-- 选项卡 -->
			<div class="gygl_xxk mt_20">
				<div class="tab_pc f16">
				<ul>
					  <li>
						<a href="<%=sellerdomain%>/manage/list?ptype=1">销售中（${manavo.productcount.saleing}）</a>
							<i></i>
				      </li>
				
					<li>
						<a href="<%=sellerdomain%>/manage/list?ptype=4">仓库中（${manavo.productcount.saveing}）</a>
						<i></i>
					</li>
					<li>
						<a href="<%=sellerdomain%>/manage/list?ptype=3">草稿箱（${manavo.productcount.drafting}）</a>
						<i></i>
					</li>
				</ul>
				</div>

				<!-- 信息标题-查询 -->
				<div class="gygl_xxk_b">
					<span class="gygl_xxk_b_bt">信息标题：</span> <input id="searchcontent"
						type="text" class=" btn_h28 dis_b fl w250 mt_20 mr_20"
						value="${manavo.content}">
					<div class="gygl_xxk_b_cx" onclick="javascript:search()">查询</div>
				</div>

				<c:choose>
					<c:when test="${manavo.productcollect.type == 1}">
						<table id="mzh_click_0" class="gygl_xxk_table">
							<tbody>
								<tr class="ndfxs_1-2_color ndfxs_1-2_border lh_40 fw_b td_no">
									<td>图片</td>
									<td>标题</td>
									<td>价格</td>
									<td>发布时间</td>
									<td>操作</td>
									<td>上架</td>
								</tr>
								<tr class="ndfxs_1-2_border td_no">
									<td colspan="6"><div class="gygl_xxk_table_cz">
											<div class="gygl_xxk_table_cz_qx">
												<input type="checkbox" id="mzh_quanxuan"> <label
													for="mzh_quanxuan">全选</label>
											</div>
											<div class="gygl_xxk_table_cz_an"
												onclick="javascript:{location.replace(location.href);}">刷新</div>
											<div class="gygl_xxk_table_cz_an" id="batchshowTJ">推荐</div>
											<div class="gygl_xxk_table_cz_an" id="batchshowOff">下架</div>
											<div class="gygl_xxk_table_cz_wz">推荐的产品可在分销商微店优先展示</div></td>
								</tr>
								<c:if
									test="${fn:length(manavo.productcollect.productlist) < 1 }">
									<tr>
										<td colspan="7">没有数据记录</td>
										<tr>
								</c:if>
								<c:forEach var="product"
									items="${manavo.productcollect.productlist}" varStatus="status">
									<c:choose>
										<c:when test="${product.isshow == 0}">
											<tr style="display: none;" class="ndfxs_1-2_border"
												id="producttr_${product.productid}"
												position="${product.position}" value="${product.productid}"
												istop="${product.istop}" isshow="${product.isshow}">
										</c:when>
										<c:otherwise>
											<tr class="ndfxs_1-2_border"
												id="producttr_${product.productid}"
												position="${product.position}" value="${product.productid}"
												istop="${product.istop}" isshow="${product.isshow}">
										</c:otherwise>
									</c:choose>
									<td class="gygl_xxk_table_cz_td">
										<div class="gygl_xxk_table_cz_qx w130">
											<input name="productcheck" type="checkbox"
												class="gygl_xxk_table_cz_qx_text"
												value="${product.productid}" istop="${product.istop}">
											<img src="${product.productimage}">
										</div>
									</td>
									<td class="gygl_xxk_table_cz_td">
										<div class="gygl_xxk_table_cz_bt">
											<span class="gygl_xxk_table_cz_bt_span mb_10">${product.producttitle}</span>
											<c:if test="${product.istop==1}">
												<div class="gygl_xxk_table_cz_bt_ytj mr_20">已推荐</div>
												<div class="gygl_xxk_table_cz_bt_qxtj mr_20"
													onclick="javascript:offsort(${product.productid})">取消推荐</div>
												<c:choose>
													<c:when test="${product.position == 0}"></c:when>
													<c:when
														test="${product.position == 1 && manavo.issearch==0}">
														<div id="up_${product.productid}" style="display: none;"
															class="gygl_xxk_table_cz_bt_qxtj mr_20"
															onclick="javascript:moveposition(${product.productid},1)">上移</div>
														<div id="down_${product.productid}"
															class="gygl_xxk_table_cz_bt_qxtj"
															onclick="javascript:moveposition(${product.productid},0)">下移</div>
													</c:when>
													<c:when
														test="${product.position == 2 && manavo.issearch==0}">
														<div id="up_${product.productid}"
															class="gygl_xxk_table_cz_bt_qxtj mr_20"
															onclick="javascript:moveposition(${product.productid},1)">上移</div>
														<div id="down_${product.productid}"
															class="gygl_xxk_table_cz_bt_qxtj"
															onclick="javascript:moveposition(${product.productid},0)">下移</div>
													</c:when>
													<c:when
														test="${product.position == 3 && manavo.issearch==0}">
														<div id="up_${product.productid}"
															class="gygl_xxk_table_cz_bt_qxtj mr_20"
															onclick="javascript:moveposition(${product.productid},1)">上移</div>
														<div id="down_${product.productid}" style="display: none;"
															class="gygl_xxk_table_cz_bt_qxtj"
															onclick="javascript:moveposition(${product.productid},0)">下移</div>
													</c:when>
												</c:choose>
											</c:if>
											<c:if test="${product.istop==0 }">
												<div class="gygl_xxk_table_cz_an"
													onclick="javascript:onsort(${product.productid})">推荐</div>
											</c:if>
										</div>
									</td>
									<td class="gygl_xxk_table_cz_td"><div
											class="gygl_xxk_table_cz_jg">￥${product.price}</div></td>
									<td class="gygl_xxk_table_cz_td"><div
											class="gygl_xxk_table_cz_sj">${product.realsetime}</div></td>
									<td class="gygl_xxk_table_cz_td">
										<div class="mzh_xl_an">
											<div class="mzh_an"
												onclick="location.href='/Product/editProductInfo?operation=4&productId=${product.productid}'">编辑</div>
											<div class="mzh_xl">
												<img isclick="0" name="mzh_xl"
													src="<%=request.getContextPath()%>/statics/pic/mzh_4_8_xl_no.jpg">
												<div class="mzh_xl_dw">
													<span name="mzh_span"
														onclick="location.href='/Product/editProductInfo?operation=4&productId=${product.productid}'">编辑</span>
													<span name="mzh_span"
														onclick="javascript:offshow(${product.productid})">下架</span>
												</div>
											</div>
										</div>
									</td>
									<td class="gygl_xxk_table_cz_td"><div
											class="gygl_xxk_table_cz_sj_fx">
											被上架：<font class="ft_f10">${product.shevlecount}</font>
										</div>
									</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</c:when>
					<c:when test="${manavo.productcollect.type == 4}">
						<table id="mzh_click_1" class="gygl_xxk_table">
							<tbody>
								<tr class="ndfxs_1-2_color ndfxs_1-2_border lh_40 fw_b td_no">
									<td>图片</td>
									<td>标题</td>
									<td>价格</td>
									<td>发布时间</td>
									<td>操作</td>
									<td>上架</td>
								</tr>
								<tr class="ndfxs_1-2_border td_no">
									<td colspan="6"><div class="gygl_xxk_table_cz">
											<div class="gygl_xxk_table_cz_qx">
												<input type="checkbox" id="mzh_quanxuan"> <label
													for="mzh_quanxuan">全选</label>
											</div>
											<div class="gygl_xxk_table_cz_an"
												onclick="javascript:{location.replace(location.href);}">刷新</div>
											<div class="gygl_xxk_table_cz_an" id="batchonshow"
												onclick="javascript:batchoperateproduct(1)">上架</div>
											<div class="gygl_xxk_table_cz_an" id="batchdelete"
												onclick="javascript:batchoperateproduct(-1)">删除</div>
											<div class="gygl_xxk_table_cz_wz">推荐的产品可在分销商微店优先展示</div>
										</div></td>
								</tr>
								<c:if
									test="${fn:length(manavo.productcollect.productlist) < 1 }">
									<tr>
										<td colspan="7">没有数据记录</td>
										<tr>
								</c:if>
								<c:forEach var="product"
									items="${manavo.productcollect.productlist}" varStatus="status">
									<tr class="ndfxs_1-2_border">
										<td class="gygl_xxk_table_cz_td"><div
												class="gygl_xxk_table_cz_qx w130">
												<input name="productcheck" type="checkbox"
													class="gygl_xxk_table_cz_qx_text"
													value="${product.productid}"> <img
													src="${product.productimage}">
											</div></td>
										<td class="gygl_xxk_table_cz_td"><div
												class="gygl_xxk_table_cz_bt">
												<span class="gygl_xxk_table_cz_bt_span mb_10">${product.producttitle}</span>

											</div></td>
										<td class="gygl_xxk_table_cz_td"><div
												class="gygl_xxk_table_cz_jg">￥${product.price}</div></td>
										<td class="gygl_xxk_table_cz_td"><div
												class="gygl_xxk_table_cz_sj">${product.realsetime}</div></td>
										<td class="gygl_xxk_table_cz_td">
											<div class="mzh_xl_an">
												<div class="mzh_an"
													onclick="location.href='/Product/editProductInfo?operation=4&productId=${product.productid}'">编辑</div>
												<div class="mzh_xl">
													<img isclick="0" name="mzh_xl"
														src="<%=request.getContextPath()%>/statics/pic/mzh_4_8_xl_no.jpg">
													<div class="mzh_xl_dw">
														<span name="mzh_span"
															onclick="location.href='/Product/editProductInfo?operation=4&productId=${product.productid}'">编辑</span>
														<span name="mzh_span"
															onclick="javascript:operateproduct(${product.productid},1)">上架</span>
														<span name="mzh_span"
															onclick="javascript:operateproduct(${product.productid},-1)">删除</span>
													</div>
												</div>
											</div>
										</td>
										<td class="gygl_xxk_table_cz_td"><div
												class="gygl_xxk_table_cz_sj_fx">
												被上架：<font class="ft_f10">${product.shevlecount}</font>
											</div>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</c:when>
					<c:when test="${manavo.productcollect.type == 3}">
						<table id="mzh_click_4" class="gygl_xxk_table">
							<tbody>
								<tr class="ndfxs_1-2_color ndfxs_1-2_border lh_40 fw_b td_no">
									<td>图片</td>
									<td>标题</td>
									<td>价格</td>
									<td>发布时间</td>
									<td>操作</td>
									<td>上架</td>
								</tr>
								<tr class="ndfxs_1-2_border td_no">
									<td colspan="6"><div class="gygl_xxk_table_cz">
											<div class="gygl_xxk_table_cz_qx">
												<input type="checkbox" id="mzh_quanxuan"> <label
													for="mzh_quanxuan">全选</label>
											</div>
											<div class="gygl_xxk_table_cz_an" onclick="javascript:{location.replace(location.href);}">刷新</div>
											<div class="gygl_xxk_table_cz_an" id="batchdelete"
												onclick="javascript:batchoperateproduct(-1)">删除</div>
											<div class="gygl_xxk_table_cz_wz">推荐的产品可在分销商微店优先展示</div>
										</div></td>
								</tr>
								<c:if
									test="${fn:length(manavo.productcollect.productlist) < 1 }">
									<tr>
										<td colspan="7">没有数据记录</td>
										<tr>
								</c:if>
								<c:forEach var="product"
									items="${manavo.productcollect.productlist}" varStatus="status">
									<tr class="ndfxs_1-2_border">
										<td class="gygl_xxk_table_cz_td"><div
												class="gygl_xxk_table_cz_qx w130">
												<input name="productcheck" type="checkbox"
													class="gygl_xxk_table_cz_qx_text"
													value="${product.productid}"> <img
													src="${product.productimage}">
											</div></td>
										<td class="gygl_xxk_table_cz_td"><div
												class="gygl_xxk_table_cz_bt">
												<span class="gygl_xxk_table_cz_bt_span mb_10">${product.producttitle}</span>

											</div></td>
										<td class="gygl_xxk_table_cz_td"><div
												class="gygl_xxk_table_cz_jg">￥${product.price}</div></td>
										<td class="gygl_xxk_table_cz_td"><div
												class="gygl_xxk_table_cz_sj">${product.realsetime}</div></td>
										<td class="gygl_xxk_table_cz_td">
											<div class="mzh_xl_an">
												<div class="mzh_an"
													onclick="location.href='/Product/editProductInfo?operation=4&productId=${product.productid}'">编辑</div>
												<div class="mzh_xl">
													<img isclick="0" name="mzh_xl"
														src="<%=request.getContextPath()%>/statics/pic/mzh_4_8_xl.jpg">
													<div class="mzh_xl_dw">
														<span name="mzh_span"
															onclick="location.href='/Product/editProductInfo?operation=4&productId=${product.productid}'">编辑</span>
														<span name="mzh_span"
															onclick="javascript:operateproduct(${product.productid},-1)">删除</span>
													</div>
												</div>
											</div>
										</td>
										<td class="gygl_xxk_table_cz_td"><div
												class="gygl_xxk_table_cz_sj_fx">
												被上架：<font class="ft_f10">${product.shevlecount}</font>
											</div>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</c:when>
					<c:when test="${manavo.productcollect.type == 2}"></c:when>
					<c:when test="${manavo.productcollect.type == -1}"></c:when>
					<c:otherwise></c:otherwise>
				</c:choose>
			</div>

			<!-- 分页 -->
			<div class="pull-right">
				<pg:page pageResult="${pageRes}" />
			</div>
		</div>
	</form>
	<script type="text/javascript">
    //单个产品取消置顶
    function offsort(productid){
    	 $.post(window.basePath+"manage/offsort/",{ "productid":  productid }, function(data){
				if (!data.error) {
					location.replace(location.href);
				}else{
					alert("取消推荐出错！");
				}
			},"json");   
    }
    
    //单个产品置顶
    function onsort(productid){
    	 $.post(window.basePath+"manage/onsort/",{ "productid":  productid }, function(data){
				if (!data.error) {
					location.replace(location.href);
				}else{
					alert("推荐出错！");
				}
			},"json");  
    }
    //移动置顶产品的位置
    function moveposition(productid,updown){
    	$.post(window.basePath+"manage/updown/",{ "productid":  productid,"updown":updown}, function(data){
			if (!data.error) {
				if(updown==0){
					//目标元素
					var target = $("#producttr_"+productid);
					//下一位兄弟
					var nextbrathor = $("#producttr_"+productid).next();
					//兄弟位置
					var nextposition = nextbrathor.attr("position");
					//目标元素位置
					var targetposition = target.attr("position");
					//兄弟是否展示
					var nextshow = nextbrathor.attr("isshow");
					//目标元素是否展示
					var targetshow = target.attr("isshow");
					//将目标元素插入下一位兄弟元素后面
					nextbrathor.insertBefore(target);
					
					//就换两个元素的位置
					nextbrathor.attr("position",targetposition);
					target.attr("position",nextposition);
					//本页最后一个
					if(nextshow==0){
						//如果下一位兄弟是隐藏的，切换两个元素的显示隐藏
						nextbrathor.show();
						target.hide();
						nextbrathor.attr("isshow",targetshow);
						target.attr("isshow",nextshow);
					}
					//第一页第一个
					if(targetposition==1){
						//如果目标元素为第一页的第一个元素，要切换下移的隐藏显示
						$("#up_"+productid).show();
						$("#up_"+nextbrathor.attr("value")).hide();
					}
					
				    //倒数第二个按了下移
					if(nextposition==3){
						//如果兄弟元素是最后一个置顶的，要切换上移的隐藏显示
						$("#down_"+productid).hide();
						$("#down_"+nextbrathor.attr("value")).show();
					}
				}else if(updown===1){
					//上移的逻辑同理下移
					var target = $("#producttr_"+productid);
					var prebrathor = $("#producttr_"+productid).prev();
					var targetposition=target.attr("position");
					var preposition=prebrathor.attr("position");
					var preshow = prebrathor.attr("isshow");
					var targetshow = target.attr("isshow");
					
					prebrathor.insertAfter(target);
					prebrathor.attr("position",targetposition);
					target.attr("position",preposition);
					
					if(preshow==0){
						prebrathor.show();
						target.hide();
						prebrathor.attr("isshow",targetshow);
						target.attr("isshow",preshow);
					}
					
					//第一页第二个
					if(preposition==1){
						$("#up_"+productid).hide();
						$("#up_"+prebrathor.attr("value")).show();
					}
					
				    //倒数第二个按了下移
					if(targetposition==3){
						$("#down_"+productid).show();
						$("#down_"+prebrathor.attr("value")).hide();
					}
				}
			}else{
				alert("移动位置出错！");
			}
		},"json"); 
    }
    
    //批量操作产品
    function batchoperateproduct(optype){
    	var result = new Array();
	    	$("[name = productcheck]:checked").each(function () {
               result.push($(this).attr("value"));
        });

    	if(result.length==0){
    	    alert("请选择您要操作的产品！");
    		return;
    	}
    	
    	$.post(window.basePath+"manage/batchoperate/",{ "products":  result.join(","),"updown":optype}, function(data){
			if (!data.error) {
				location.replace(location.href);
			}else{
				alert("批量操作出错！");
			}
		},"json"); 
    }
    
    //下架产品
    function offshow(productid){
    	var result = new Array();
    	result.push(productid);
        
    	$.post(window.basePath+"manage/batchoffshow/",{ "products":  result.join(",")}, function(data){
			if (!data.error) {
				location.replace(location.href);
			}else{
				alert("下架出错！");
			}
		},"json"); 
    }
    
    function search(){
    	var content = $("#searchcontent").val();
    	if(content!=""){
    		var hre
    		location.replace(changeURLPar(location.href,"c",content));
    	}else{
    		alert("请填写你要搜索的产品信息！");
    	}
    }
    
    function changeURLPar(url, ref, value) {
        var str = "";
        if (url.indexOf('?') != -1)
            str = url.substr(url.indexOf('?') + 1);
        else
            return url + "?" + ref + "=" + value;
        var returnurl = "";
        var setparam = "";
        var arr;
        var modify = "0";
        if (str.indexOf('&') != -1) {
            arr = str.split('&');
            for (i in arr) {
                if (arr[i].split('=')[0] == ref) {
                    setparam = value;
                    modify = "1";
                }
                else {
                    setparam = arr[i].split('=')[1];
                }
                returnurl = returnurl + arr[i].split('=')[0] + "=" + setparam + "&";
            }
            returnurl = returnurl.substr(0, returnurl.length - 1);
            if (modify == "0")
                if (returnurl == str)
                    returnurl = returnurl + "&" + ref + "=" + value;
        }
        else {
            if (str.indexOf('=') != -1) {
                arr = str.split('=');
                if (arr[0] == ref) {
                    setparam = value;
                    modify = "1";
                }
                else {
                    setparam = arr[1];
                }
                returnurl = arr[0] + "=" + setparam;
                if (modify == "0")
                    if (returnurl == str)
                        returnurl = returnurl + "&" + ref + "=" + value;
            }
            else
                returnurl = ref + "=" + value;
        }
        return url.substr(0, url.indexOf('?')) + "?" + returnurl;
    }
    
    //上架产品
    function operateproduct(productid,optype){
    	var result = new Array();
    	result.push(productid);

		$.post(window.basePath+"manage/batchoperate/",{ "products":  result.join(","),"updown":optype}, function(data){
			if (!data.error) {
				location.replace(location.href);
			}else{
				alert("批量操作出错！");
			}
		},"json"); 
    }
    
    $(document).ready(function(){
    	//选项卡（不同状态的产品选择）
    	var host_url=location.href;
   	    $(".tab_pc li a[href='"+host_url+"']").parent().addClass("now")
    	
    	var page = new Pagination( {
			formId: "searcherForm",
			isAjax : true,
			targetId : "navTab",
			submitId:"searchBtn",
			/* validateFn:checkInfo,
			ajaxCallback:callback */
		});
		page.init();
		
    	//全选，全不选
   	    $('#mzh_quanxuan').click(function(){
   		 if(this.checked)
   		     $('[name=productcheck]:checkbox').prop("checked", true);
   		 else
   			$('[name=productcheck]:checkbox').removeAttr("checked");
   	    })
   	    //批量推荐
   	    $("#batchshowTJ").click(function(){
   	    	var result = new Array();
   	    	var tag = false;
   	    	$("[name = productcheck]:checked").each(function () {
   	    		tag=true;
   	    		var istop = $(this).attr("istop");
   	    		if(istop==0)
                   result.push($(this).attr("value"));
            });
   	    	
   	    	if(result.length==0){
   	    		if(tag)
   	    		    alert("您选择的产品都已经置顶过！");
   	    		else
   	    			alert("请选择您要推荐的产品！");
   	    		return;
   	    	}
   	    		
   	    	$.post(window.basePath+"manage/batchontop/",{ "products":  result.join(",")}, function(data){
   				if (!data.error) {
   					location.replace(location.href);
   				}else{
   					alert("推荐出错！");
   				}
   			},"json"); 
   	    })
   	    //批量下架
   	    $("#batchshowOff").click(function(){
   	    	var result = new Array();
   	    	$("[name = productcheck]:checked").each(function () {
                   result.push($(this).attr("value"));
            });

   	    	if(result.length==0){
   	    	    alert("请选择您要下架的产品！");
   	    		return;
   	    	}
   	    		
   	    	$.post(window.basePath+"manage/batchoffshow/",{ "products":  result.join(",")}, function(data){
   				if (!data.error) {
   					location.replace(location.href);
   				}else{
   					alert("下架出错！");
   				}
   			},"json"); 
   	    })
   	    
   	    
   	   	$("[name=mzh_xl]").mouseover(function(){
			if($(this).attr("isclick") == 0){
				$(this).parent().attr("class","mzh_xl_yes");
				$(this).attr("isclick","1");
				$(this).attr("src","<%=request.getContextPath()%>/statics/pic/mzh_4_8_xl.jpg")
			} else{
				$(this).parent().attr("class","mzh_xl");
				$(this).attr("isclick","0");
				$(this).attr("src","<%=request.getContextPath()%>/statics/pic/mzh_4_8_xl_no.jpg")
			}
		})
		

	    $(".mzh_xl_an").mouseleave(function(){
	        $(this).find(".mzh_xl_yes").attr("class","mzh_xl");
	        $(this).find("[name=mzh_xl]").attr("isclick","0");
	        $(this).find("[name=mzh_xl]").attr("src","<%=request.getContextPath()%>/statics/pic/mzh_4_8_xl_no.jpg");
	    })
        
    })
</script>
</body>