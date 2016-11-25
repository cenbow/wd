<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="pg" uri="http://www.okwei.com/pagination"%>

<%@page import="com.okwei.util.DateUtils"%>
<%@ page import="java.util.ResourceBundle"%>
<%
	String paydomain = ResourceBundle.getBundle("domain").getString(
			"paydomain");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>限时抢购</title>
<link rel="stylesheet" href="../statics/css/glbdy.css" />
<link rel="stylesheet" href="../statics/css/mzh_dd.css" />
<link rel="stylesheet" href="../statics/css/js.pagination.css" />
<link rel="stylesheet" href="../statics/css/jumei_usercenter.min.css" />
<script type="text/javascript" src="/statics/js/common/JsRender.js"></script>
<script type="text/javascript" src="/statics/js/common/jquery.pagination.js"></script>
<style>
.conter_right tr {
	border-bottom: 1px solid #ddd;
}

.conter_right tr td {
	border-right: 1px solid #ddd;
}

.xh-shurk {
	height: auto;
}

.left_xuanzs {
	width: 630px;
}

#win_div_22 tr {
	border-bottom: 1px solid #ddd;
}

#win_div_22 tr th {
	border-right: 1px solid #ddd;
	padding: 10px 0px;
}

#win_div_22 tr td {
	padding: 10px 0px;
	border-right: 1px solid #ddd;
}



.mzh_biaoti {
	float: left;
	width: 150px;
	height: 36px;
	overflow: hidden;
	margin-left: 50px;
	text-align: left;
	line-height: 18px;
	font-size: 14px;
	margin-bottom: 5px;
}

.mzh_jiage {
	float: left;
	width: 150px;
	margin-left: 50px;
	text-align: left;
	line-height: 18px;
	font-size: 14px;
	margin-bottom: 5px;
}

.mzh_jg_text {
	float: left;
	width: 50px;
	border-radius: 3px;
	height: 30px;
	border: 1px solid #ccc;
}

.xh-shurk {
	height: auto;
}

.mzh_biaoti {
	float: left;
	width: 150px;
	height: 36px;
	overflow: hidden;
	margin-left: 50px;
	text-align: left;
	line-height: 18px;
	font-size: 14px;
	margin-bottom: 5px;
}
.mzh_jg_text{width:180px;}
</style>
</head>
<body style="background: #f3f3f3;">
	<div class="outermost">
		<div class="fr conter_right">
			<div class="zhuag_suv bor_si fl bg_white">
				<div class="gygl_xxk_t f16 ndfxs_1-2_border">
					<b class="gygl_xxk_yes" style="color: #333;">商家活动信息<span style="width: 90px;"></span></b>
				</div>
				<div class="xh-shurk">
					<ul>
						<li class="mzh_100"><span style="color: #333;">活动主题：</span> <span style="color: #333;">${actModel.title }</span></li>
						<li class="mzh_100"><span style="color: #333;">活动要求：</span> <span style="color: #333;">${actModel.demand }</span></li>
						<li class="mzh_100"><span style="color: #333;">报名截止日期：</span> <span style="color: #333;">${DateUtils.format(actModel.applyEndTime,"yyyy/MM/dd" )}</span></li>
						<li class="mzh_100"><span style="color: #333;">活动展示日期：</span> <span style="color: #333;">${DateUtils.format(actModel.startTime,"yyyy/MM/dd" )}-${DateUtils.format(actModel.endTime,"yyyy/MM/dd" )}</span></li>
					</ul>
				</div>
				<div class="blank2"></div>
				<input type="hidden" id="actidinput" value="${actid }">
				<div class="gygl_xxk_t f16 ndfxs_1-2_border">
					<b class="gygl_xxk_yes" style="color: #333;">选择活动报名产品<span style="width: 130px;"></span></b>
				</div>
				<div class="xh-shurk">
					<ul>
						<!-- <li class="mzh_100">
							<span class="fl ">选择产品：<font class="ft_c9">(注：参与活动的产品个数限制在20个内。选择产品后请设置产品活动价、参与活动的产品数量)</font></span>
						</li> -->
						<li class="mzh_100">
			                <span class="mzh_100">选择产品：</span>
			                <span class="mzh_100">注：1、选择产品后请设置产品结算价、活动价、参与活动的产品数量；</span>
			                <span class="mzh_100">　　2、活动价不能高于其同类同款商品在第三方电商平台售价；</span>
			                <span class="mzh_100">　　3、购物全返活动规则中，其中商户最低赞助20%，微店网最高补贴80%。</span>
          			    </li>
						<li class="mzh_100">
							<div id="productsDiv">
								<c:if test="${proModel!=null }">
									<div name="proviewdiv" class="fl w250 bor_si mr_20">
										<input type="hidden" name="productidHidden" value="${proModel.productId }">
										<div class="fl mzh_100 tc mt_20 mb_10" style="position: relative;">
											<img src="${proModel.productImg}" width="150" height="150"> <img src="/statics/images/delete_imgs.png" name="removeProductImg" style="position: absolute; right: 37px; top: -13px;"> <span class="mzh_biaoti" style="color: #000;">${proModel.productTitle }</span> <span class="mzh_jiage" style="color: #000;">现价：￥${proModel.priceBf }</span> <span class="mzh_jiage">原价：￥${proModel.oldPrice }</span>
										</div>
										<div class="mzh_100">
											<div class="mzh_100 f14" style="color: #000; text-indent: 10px; margin-bottom: 5px;">活动价：</div>
											<div class="mzh_100">
												<input type="text" name="productpriceipt" class="mzh_jg_text ml_10" value="${proModel.price }" onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')">
												<span class="fl f16 line_30 ml_5" style="color: #333;">元</span>
											</div>
											<div class="mzh_100 f14 mt_5" style="color: #000; text-indent: 10px; margin-bottom: 5px;">
												结算价：(<font class="ft_c9">平台与供应商的结算价</font>)
											</div>
											<div class="mzh_100">
												<input type="text" name="traddingpriceipt" class="mzh_jg_text ml_10" value="${proModel.traddingPrice }" placeholder="结算价不得高于活动价的80%" onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')">
												<span class="fl f16 line_30 ml_5" style="color: #333;">元</span> 
												<font name="traddingTips" class="mzh_100 ft_red ml_10 f12 line_30 none">结算价不得高于活动价的80%，让利微店网越多，回馈消费者越多</font>
											</div>
										</div>
										<div class="mzh_100 mb_10">
											<div class="mzh_100 f14" style="color: #000; text-indent: 10px; margin-bottom: 5px;">产品数量：</div>
											<div class="mzh_100">
												<input type="text" name="totalcountipt" class="mzh_jg_text ml_10" style="width: 70px;" value="${proModel.totalCount }">
											</div>
										</div>
									</div>
								</c:if>
							</div> 
							<img src="/statics/images/xin_inselimg.png" class="shou fl" name="btnAddProducts">
							<span class="mzh_100 mb_20 mt_20"><font class="fl ft_c3">申请全返促销累计金额：</font> 
								<input type="hidden" id="productsPricetotal" value="${amountTotal }">
								<input type="text" style="border:1px solid #ddd;height:20px;line-height:20px;" id="sellbackPriceipt" value="${amount}" placeholder="微店网共资助10亿用于购物全返活动" class="fl w250" onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')">
								<br/>
								<br/>
								<!-- 请选择参加活动的产品 -->
						   		<font class="ft_red ml_10 " id="sqqfcxje_ts" >请按照你的备货量申请补贴额度，我们将根据你的额度返现给消费者，微店网总共资助10亿进行活动，先申请先得，申请完即止</font><br/>
						 		<font class="mzh_100" id="sqqfcxje_ts" >(说明：你申请的额度为微店网最高补贴给消费者的额度，超出部分将不列入购物全返活动，即不会给予消费者补贴 </font><br/>
						 		<font class="mzh_100 " id="sqqfcxje_ts" > 如有因库存不足，导致消费者退单，微店网将按照活动规定进行处罚)</font>

							</span>
						</li>
						<li class="blank"></li>
						<li class="blank"></li>
						<li class="fl" style="margin-left: 450px;"><c:if test="${actModel.state==1 }">
								<input type="submit" id="applybtn" value="提交报名" class="btn_submit_two fl" style="width: 100px;">
							</c:if></li>
						<li class="blank"></li>
					</ul>
				</div>
			</div>
		</div>
	</div>

	<!-- 请选择参加活动的产品 -->
	<div class="fl mzh_100 f14" style="display: none;" id="win_div_2">
		<div class="mzh_100 fl">
			<div class="left_font fl ft_c9" style="width: 100px;">商品分类：</div>
			<div class="left_xuanzs fl" style="height: 65px; overflow-y: auto;">
				<ul>
					<li name="mzh_4_7_yes" class="yes_bgs" date-cid="-1">全部</li>
					<c:forEach var="claModel" items="${classlist }">
						<li name="mzh_4_7_yes" date-cid="${claModel.sid }">${claModel.sname }</li>
					</c:forEach>
				</ul>
			</div>
		</div>
		<div class="mzh_100 fl">
			<div class="fl mb_10">
				<span class="fl">标题：</span> <input type="text" id="proTitletxt" class="btn_h24 w120 fl">
			</div>
			<div class="fl ml_20">
				<input type="submit" value="搜索" id="searchBtn" class="btn_submit_two fl" style="width: 80px;">
			</div>
		</div>
		<div class="fl mzh_100" id="div-prolistshot" style="max-height: 320px; overflow-y: scroll;"></div>
		<script type="text/x-jsrender" id="productslist-render">
			<table id="prolistTab" class="mzh_100 fl bg_white bor_si f14 tc" id="win_div_22">
				<tr class="bg_f3">
					<th width="30"></th>
					<th width="130">产品图片</th>
					<th>产品标题</th>
					<th>价格</th>
				</tr>
				{{for list }}
					<tr>
					<td><input type="checkbox" {{if mid ===1 }} disabled="disabled" {{/if}} data-mid="{{:mid}}" data-proid="{{:productId}}" data-proimg="{{:defaultImg}}" data-title="{{:productTitle}}" data-price="{{:defaultPrice}}" data-oldprice="{{:originalPrice}}"  ></td>
					<td><img src="{{:defaultImg}}" width="100" height="100"></td>
					<td>{{:productTitle}}</td>
					<td>
						<p class="line_30">现价：￥{{:defaultPrice}}</p>
						<p class="line_30 ft_c9">
							原价：<font style="text-decoration: line-through;">￥{{:originalPrice}}</font>
						</p>
						<p class="line_30">佣金：￥{{:defaultConmision}}</p>
					</td>
				</tr>
				{{/for}}
			</table>
 		</script>
		<script type="text/x-jsrender" id="productS-render">
			<div  name="proviewdiv" class="fl w250 bor_si mr_20">
			    <input type="hidden" name="productidHidden" value="{{:productId }}">
				<div class="fl mzh_100 tc mt_20 mb_10" style="position: relative;">
					<img src="{{:productImg}}" width="150" height="150">
					<img src="/statics/images/delete_imgs.png" name="removeProductImg" style="position: absolute; right: 37px; top: -13px;">
					<span class="mzh_biaoti" style="color: #000;">{{:productTitle}}</span> 
					<span class="mzh_jiage" style="color: #000;">现价：￥{{:priceBf}}</span>
					<span class="mzh_jiage">原价：￥{{:oldPrice}}</span>
				</div>
			   <div class="mzh_100">
                    <div class="mzh_100 f14" style="color: #000;text-indent: 10px;margin-bottom: 5px;">活动价：</div>
                    <div class="mzh_100">
                            <input type="text" name="productpriceipt" class="mzh_jg_text ml_10" onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')">
							<span class="fl f16 line_30 ml_5" style="color: #333;">元</span>
                    </div>
                    <div class="mzh_100 f14 mt_5" style="color: #000;text-indent: 10px;margin-bottom: 5px;">结算价：(<font class="ft_c9">平台与供应商的结算价</font>)</div>
                   		 <div class="mzh_100">
                            <input type="text"  name="traddingpriceipt" class="mzh_jg_text ml_10" placeholder="结算价不得高于活动价的80%" onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')">
							<span class="fl f16 line_30 ml_5" style="color: #333;">元</span>
                            <font  name="traddingTips" class="mzh_100 ft_red ml_10 f12 line_30 none">结算价不得高于活动价的80%，让利微店网越多，回馈消费者越多</font>
                        </div>
                    </div>
                    <div class="mzh_100 mb_10">
                        <div class="mzh_100 f14" style="color: #000;text-indent: 10px;margin-bottom: 5px;">产品数量：</div>
                        <div class="mzh_100">
                             <input type="text" name="totalcountipt" class="mzh_jg_text ml_10" style="width: 70px;">
                        </div>
                    </div>
			</div>
 		</script>
 		
		<div class="pull-right">
			<div id="Pagination" class="pagination"></div>
		</div>
	
	</div>
	
	<!-- 请选择参加活动的产品 -->
	<div class="fl mzh_100 f14" style="display:none;" id="win_div_3">
   		 <div class="mzh_100 fl f16"> 您报名的产品库存总金额低于申请全返促销金额，您是否要添加库存或增加产品以获得更高促销补贴？</div>
	</div>

	<script language="javascript" type="text/javascript">
		$("img[name=btnAddProducts]").click(function() {
			wins("请选择需要推荐的产品", "win_div_2", "800", "630");
			prolist();
		})
		$("#searchBtn").click(function() {
			prolist();
		})

		$("#checkallipt").live("click", function() {
			var ischecked = $(this).val();
			if (ischecked == 1) {
				$(this).attr("value", "0");
				$("#prolistTab input[type=checkbox]").each(function() {
					$(this).attr("checked", false);
				});
			} else {
				$(this).attr("value", "1");
				$("#prolistTab input[type=checkbox]").each(function() {
					$(this).attr("checked", true);
				});
			}
		})
		
	$("input[name=traddingpriceipt]").live("keyup",function(){
		var price=$(this).parent("div").parent("div").find("input[name=productpriceipt]").val();
		var tradprice=$(this).val();
		var a=tradprice/price;
		if(parseFloat(a)>0.8){
			$(this).parent("div").find("font[name=traddingTips]").removeClass("none");
			//alert("结算价不得高于活动价的80%");
		}else{
			$(this).parent("div").find("font[name=traddingTips]").addClass("none");
		}
	})
		
	
		checkPrice = function() {
			var priceTotalBe = $("#productsPricetotal").val();
			$("#productsDiv").find("div[name=proviewdiv]").each(
					function() {
						var priceb = $(this).find("input[name=productpriceipt]").val();
						var totalCount = $(this).find("input[name=totalcountipt]").val();
						priceTotalBe = parseFloat(priceTotalBe)+ parseFloat(priceb * totalCount);
					})

			if (priceTotalBe < parseFloat($("#sellbackPriceipt").val())) {
				//if (confirm("您报名的产品库存总金额低于申请全返促销金额，您是否要添加库存或增加产品以获得更高促销补贴？")) {
					return false;
				//}
			}
			return true;
		}
		//删除产品
		$("img[name=removeProductImg]").live("click", function() {
			$(this).parent().parent("div[name=proviewdiv]").remove();
		})
		
	
		function winTip(title, win_id, width, height) { //title 标题 win_id 弹窗ID  width 弹窗宽度 height 弹窗高度
			var pagei = $.layer({
				type : 1, //0-4的选择,0：信息框（默认），1：页面层，2：iframe层，3：加载层，4：tips层。
				btns : 2,
				btn : [ '去修改', '继续提交' ],
				title : title,
				border : [ 0 ],
				closeBtn : [ 0 ],
				closeBtn : [ 0, true ],
				shadeClose : true,
				area : [ width, height ],
				page : {
					dom : '#' + win_id
				},
				no : function(index) {
					submitProduct();
				}
			});
		}

		function submitProduct(){
			var proArr = new Array();
			var actid = $("#actidinput").val();
			var exprice = $("#sellbackPriceipt").val();
			
			$("#productsDiv").find("div[name=proviewdiv]").each(function() {
						var obj = new Object();
						obj.productId = $(this).find("input[name=productidHidden]").val();
						var priceb = $(this).find("input[name=productpriceipt]").val();
						//var commission = $(this).find("input[name=productcommissionipt]").val();
						obj.price = priceb; //accAdd(priceb, commission);
						obj.traddingPrice = $(this).find("input[name=traddingpriceipt]").val();
						obj.commission = 0;
						obj.actId = actid;
						obj.totalCount = $(this).find("input[name=totalcountipt]").val();
						proArr.push(obj);
			})
		   //	alert(JSON.stringify(proArr));
			//return;
			$.ajax({
				url : "editpro",
				type : "post",
				data : {
					productsArr : JSON.stringify(proArr),
					amount : exprice
				},
				success : function(data) {
					if (data.Statu == "Success") {
						alert(data.StatusReson);
						location.href = "editlist?actid=" + actid;
					} else {
						alert(data.StatusReson);
					}
				},
				error : function() {
					alert("系统错误");
				}
			});
		}
		
		//提交报名
		$("#applybtn").click(function() {
			var exprice = $("#sellbackPriceipt").val();
			if(exprice==null||exprice==""){
				alert("请设置申请全返促销金额");
				return;
			}
			
			
			$("#productsDiv").find("div[name=proviewdiv]").each(function() {
				var priceb = $(this).find("input[name=productpriceipt]").val();
				if(priceb==null||priceb==""){
					alert("活动价不能为空");
					return;
				}
				var tradPrice = $(this).find("input[name=traddingpriceipt]").val();
				if(tradPrice==null||tradPrice==""){
					alert("结算价不能为空");
					return;
				}
				var totalCount = $(this).find("input[name=totalcountipt]").val();
				if(totalCount==null||totalCount==""){
					alert("产品数量不能为空");
					return;
				}
				
			})
			
			if (!checkPrice()) {
				winTip("提示", "win_div_3", '520','300');
			}else{
				submitProduct();
			}		
		})

		function accAdd(arg1, arg2) {
			var r1, r2, m;
			try {
				r1 = arg1.toString().split(".")[1].length
			} catch (e) {
				r1 = 0
			}
			try {
				r2 = arg2.toString().split(".")[1].length
			} catch (e) {
				r2 = 0
			}
			m = Math.pow(10, Math.max(r1, r2))
			return (arg1 * m + arg2 * m) / m
		}
		//选择商品分类
		$("li[name=mzh_4_7_yes]").click(function() {
			$("li[name=mzh_4_7_yes]").each(function() {
				$(this).removeClass("yes_bgs");
			})
			$(this).addClass("yes_bgs");
			prolist();
		})

		/** 弹窗调用函数 **/
		function wins(title, win_id, width, height) { //title 标题 win_id 弹窗ID  width 弹窗宽度 height 弹窗高度
			var pagei = $
					.layer({
						type : 1, //0-4的选择,0：信息框（默认），1：页面层，2：iframe层，3：加载层，4：tips层。
						btns : 2,
						btn : [ '确定', '取消' ],
						title : title,
						border : [ 0 ],
						closeBtn : [ 0 ],
						closeBtn : [ 0, true ],
						shadeClose : true,
						area : [ width, height ],
						page : {dom : '#' + win_id},
						no : function(index) {$("#" + win_id).hide();},
						yes : function(index) {
							var proArr = new Array();
							$("#prolistTab input[type=checkbox]").each(function() {
								if ($(this).is(':checked')) {
									var pro = new Object();
									var proId = parseInt($(this).attr("data-proid"));
									if (proId > 0) {
										var isHave = 0;
										$("#productsDiv").find("div[name=proviewdiv]").each(function() {
										var productId = $(this).find("input[name=productidHidden]").val();
												if (productId == proId) {
													isHave = 1;
												}
										})
										if (isHave == 0) {
											pro.productId = $(this).attr("data-proid");
											pro.productImg = $(this).attr("data-proimg");
											pro.productTitle = $(this).attr("data-title");
											pro.priceBf = $(this).attr("data-price");
											pro.oldPrice = $(this).attr("data-oldprice");
											proArr.push(pro);		
										}					
									}					
								}				
							})

							//alert(JSON.stringify(proArr));
							var htms = $("#productS-render").render(proArr);

							$("#productsDiv").append(htms);
							layer.close(index);
						}
					});
		}
		//每页条数
		var pageSize = 10;

		function prolist() {
			$.ajax({
				url : "prolist",
				type : "post",
				data : {
					actid : $("#actidinput").val(),
					index : 1,
					size : pageSize,
					count : 1,
					title : $("#proTitletxt").val(),
					classId : $("li[name=mzh_4_7_yes][class='yes_bgs']").attr(
							"date-cid")
				},
				success : function(data) {
					var totalCount = data.BaseModle.totalCount;
					$("#Pagination").pagination(totalCount, {
						num_edge_entries : 1, //边缘页数
						num_display_entries : 4, //主体页数
						callback : pageselectCallback,
						items_per_page : pageSize, //每页显示1项
						prev_text : "上一页",
						next_text : "下一页"
					});
				}
			});
		}

		function pageselectCallback(page_index, jq) {
			$.ajax({
				url : "prolist",
				type : "post",
				data : {
					actid : $("#actidinput").val(),
					index : page_index + 1,
					size : pageSize,
					title : $("#proTitletxt").val(),
					classId : $("li[name=mzh_4_7_yes][class='yes_bgs']").attr(
							"date-cid")
				},
				success : function(data) {
					$("#div-prolistshot").html("");
					var htm = $("#productslist-render").render(data.BaseModle);
					$("#div-prolistshot").append(htm);
				}
			});

		}
	</script>
</body>
</html>