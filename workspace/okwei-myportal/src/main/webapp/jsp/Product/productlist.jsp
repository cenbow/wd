<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="pg" uri="http://www.okwei.com/pagination"%>
<%@ page import="com.okwei.bean.enums.ProductStatusEnum" %>
<%@ page import="java.util.ResourceBundle"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String mydomain = ResourceBundle.getBundle("domain").getString("mydomain");
	String productdomain = ResourceBundle.getBundle("domain").getString("productdomain");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>我的产品</title>
<style>
.gygl_xxk_table_cz_sj_fx{width: 110px;padding: 0 20px;}
.gygl_xxk_table_cz_sj{width: 80px;}
.gygl_xxk_table_cz_bt{width: 150px;padding: 0 20px;}
.gygl_xxk_table_cz_bt_span{width: 150px;}
.mzh_xl_an{margin: 5px 0 0 10px;}
.mzh_xl_an .mzh_an{line-height: 28px;}
.mzh_xl_an .mzh_xl{height: 28px;}
.mzh_xl_an .mzh_xl_yes{height: 28px;}
.xzgys dd{float:left;width:180px;line-height:26px;}
.xzgys dt{float:left;width:290px;line-height:26px;}
.two_titles_select{float: left;width: 130px;border: 1px solid #ccc;line-height: 30px;height: 30px;border-radius: 3px;margin-right: 15px;}
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
		var pageurl="<%=productdomain %>/product?sid="+productId;
		var source="${userinfo.weiName}的微店隶属于微店网总平台，普通网民可以在这里免费注册开微店，供应商可以从这里提交资料，把产品发到云端产品库，让像我一样的无数网民为他销售产品。";
		switch(type)
		{
			case 0:{
				ShareToQzone(title, pageurl, source);
				break;}
			case 1:{
				ShareToTencent(source, pageurl, source);
				break;}
			case 2:{
				ShareToSina(source, pageurl, source);
				break;}
			default:{
				alert("分享类型错误！");
				break;
			}
		}
	}
	
	/** 弹窗调用函数 **/
    function wins(title,win_id,width,height){ //title 标题 win_id 弹窗ID  width 弹窗宽度 height 弹窗高度
		
    	if(win_id=='win_warning'){
    		var $warnInput = $("#warnInput");
    		$.ajax({
    			url : "/myProduct/getStockWarning",
    			type : "get",
    			async : false,
    			error : function(XMLHttpRequest, textStatus, errorThrown) {
    				alert("服务器出现异常");
    			},
    			success : function(data) {
    				if(typeof(data.warningNum)!="undefined"){
    					$warnInput.val(data.warningNum);
    				}else{
    					$warnInput.val("");
    				}
    			}
    		});
		}else{
			var $agentsVisible = $("#agentsVisible");
			var $otherAgentsVisible = $("#otherAgentsVisible");
			var $dlFullyOpen = $("#dlFullyOpen");
			var $ldAgentsVisible = $("#ldAgentsVisible");
			var $shopVisible = $("#shopVisible");
			var $otherShopVisible = $("#otherShopVisible");
			var $ldFullyOpen = $("#ldFullyOpen");
			$.ajax({
    			url : "/myProduct/getPriceVisible",
    			type : "get",
    			async : false,
    			error : function(XMLHttpRequest, textStatus, errorThrown) {
    				alert("服务器出现异常");
    			},
    			success : function(data) {
    				$agentsVisible.val(data.agentsVisible);
    				if(data.agentsVisible==1){
    					$agentsVisible.attr("checked", true);
    				}
    				$otherAgentsVisible.val(data.otherAgentsVisible);
    				if(data.otherAgentsVisible==1){
    					$otherAgentsVisible.attr("checked", true);
    				}
    				
    				$dlFullyOpen.val(data.dlFullyOpen);
    				if(data.dlFullyOpen==1){
    					$dlFullyOpen.attr("checked", true);
    				}
    				
    				$ldAgentsVisible.val(data.ldAgentsVisible);
    				if(data.ldAgentsVisible==1){
    					$ldAgentsVisible.attr("checked", true);
    				}
    				$shopVisible.val(data.shopVisible);
    				if(data.shopVisible==1){
    					$shopVisible.attr("checked", true);
    				}
    				$otherShopVisible.val(data.otherShopVisible);
    				if(data.otherShopVisible==1){
    					$otherShopVisible.attr("checked", true);
    				}
    				$ldFullyOpen.val(data.ldFullyOpen);
    				if(data.ldFullyOpen==1){
    					$ldFullyOpen.attr("checked", true);
    				}
    			}
    		});
		}
		
        var pagei = $.layer({
            type: 1,   //0-4的选择,0：信息框（默认），1：页面层，2：iframe层，3：加载层，4：tips层。
            btns: 2,
            btn: ['确定','取消'],
            title: title,
            border: [0],
            closeBtn: [0],
            closeBtn: [0, true],
            shadeClose: true,
            area: [width,height],
            page: {dom : '#'+ win_id},
            yes : function(index) {
				//保存方法
				if(win_id=='win_warning'){
					saveWarn(index);
				}else{
					savePrice(index);
				}
			},
            end: function(){ $("#win_warning").hide()}
        });
    }
	
	function saveWarn(index){
		$.post("/myProduct/setStockWarning",{quantity:$("#warnInput").val()},function(data){
			if(!data.error){
				alert("设置成功！",true);
				layer.close(index);
			}else{
				alert("设置失败！",false);
			}
		},"json");
	}
	
	function savePrice(index){
		$.post("/myProduct/priceVisible",{
			agentsVisible:$("#agentsVisible").val(), //代理价我的代理商可见
			otherAgentsVisible:$("#otherAgentsVisible").val(),//代理价其他代理商可见
			dlFullyOpen:$("#dlFullyOpen").val(), //代理价完全公开
			ldAgentsVisible:$("#ldAgentsVisible").val(), //落地价我的代理商可见
			shopVisible:$("#shopVisible").val(), //落地价我的落地店可见
			otherShopVisible:$("#otherShopVisible").val(),//落地价其他落地店可见
			ldFullyOpen:$("#ldFullyOpen").val()//落地价完全公开
		},
		function(data){
			if(!data.error){
				alert("设置成功！",true);
				layer.close(index);
			}else{
				alert("设置失败！",false);
			}
		},"json");
	}
	
</script>
</head>

<body class="bg_f3">
	<form id="searcherForm" >
		<input type="hidden" name="isClick" id="isClick" value="0"/>
		<div class="zhuag_suv bor_si fl bg_white">
			<div class="oneci_ztai fl">
				<div class="left_font tr fl f12 ft_c9">状态：</div>
				<div class="left_xuanzs fl f12">
					<ul id="status_ul">
						<li name="status_li" value="1"
						<c:if test="${dto.status==ProductStatusEnum.Showing}">class="yes_bgs"</c:if>
						url="<%=basePath%>myProduct/list/Showing/">销售中(${searchvo.count_Showing})</li>
						
						<li name="status_li" value="4" url="<%=basePath%>myProduct/list/Drop/"
						<c:if test="${dto.status==ProductStatusEnum.Drop}">class="yes_bgs"</c:if>
						>已下架(${searchvo.count_Drop})</li>
						
						<li name="status_li" value="3" url="<%=basePath%>myProduct/list/OutLine/"
						<c:if test="${dto.status==ProductStatusEnum.OutLine}">class="yes_bgs"</c:if>
						>草稿箱(${searchvo.count_OutLine})</li>
						
						<c:if test="${userinfo.getPth() == 1}"> <!-- 平台号/子供应商才可以看见 -->
						<li name="status_li" value="6" url="<%=basePath%>myProduct/list/Audit/"
						<c:if test="${dto.status==ProductStatusEnum.Audit}">class="yes_bgs"</c:if>
						>待审核(${searchvo.count_ToHandle})</li>
						</c:if>
						
						<c:if test="${userinfo.getPthSupply() == 1}"> <!-- 平台号/子供应商才可以看见 -->
						<li name="status_li" value="6" url="<%=basePath%>myProduct/list/Audit/"
						<c:if test="${dto.status==ProductStatusEnum.Audit}">class="yes_bgs"</c:if>
						>申请中(${searchvo.count_ToHandle})</li>
						</c:if>
						
					</ul>
				</div>
			</div>
			
			<script>
				$(function(){
					if($("#dp_class_div").height()<60){
						$(".idnes").hide()
						}
					else{ 
					$("#dp_class_div").css({"height":"60px"})
					}
				$(".idnes").click(function(){
					if($("#dp_class_div").height()<90)
					{
					    $("#dp_class_div").css({"height":"90px","overflow-y":"scroll"})
					}
					else
					{
						$("#dp_class_div").css({"height":"60px","overflow-y":"hidden"})
					}
						
					})
					
				}) 
				</script>
			
			<!-- 店铺分类一级目录 -->
			<div class="oneci_ztai fl">
				<div class="left_font tr fl f12 ft_c9">商品分类：</div>
				<div class="left_xuanzs fl f12" id="dp_class_div" style=" overflow:hidden;margin-right:10px;">
					<ul id="supshopclass_ul" >
						<li name="supshopclass_li" value="0"
							<c:if test="${0==dto.supShopClassId}">
							class="yes_bgs" 
							</c:if>
						>全部</li>
						<c:forEach items="${searchvo.classList}" var="shopclass">
							<li name="supshopclass_li"
								<c:if test="${shopclass.sid==dto.supShopClassId}">
								class="yes_bgs" 
								</c:if>
								value="${shopclass.sid}">${shopclass.sname}</li>
						</c:forEach>
					</ul>
				</div>
				<div class="fl idnes">
					<a href="javascript:;">更多</a>
				</div>
			</div>
			
			<!-- 店铺分类二级目录 -->
			<c:if test="${fn:length(searchvo.subClassList) > 0}">
			<div class="oneci_ztai fl">
				<div class="left_font tr fl f12 ft_c9">二级分类：</div>
				<div class="left_xuanzs fl f12" id="dp_class_div" style=" overflow:hidden;margin-right:10px;">
					<ul id="shopclass_ul" >
						<li name="shopclass_li" value="0"
							<c:if test="${0==dto.shopClassId}">
							class="yes_bgs" 
							</c:if>
						>全部</li>
						<c:forEach items="${searchvo.subClassList}" var="subshopclass">
							<li name="shopclass_li"
								<c:if test="${subshopclass.sid==dto.shopClassId}">
								class="yes_bgs" 
								</c:if>
								value="${subshopclass.sid}">${subshopclass.sname}</li>
						</c:forEach>
					</ul>
				</div>
				<div class="fl idnes">
					<a href="javascript:;">更多</a>
				</div>
			</div>
			</c:if>
			
			
			<!-- 当点击状态与店铺分类过滤后数据为空时,隐藏搜索框 -->
			<c:if test="${dto.isClick==null || dto.isClick==0 || fn:length(pageResult.list) > 0 }">
				<div class="xh-shurk">
					<ul>
						<li>
							<span>标题：</span> 
							<input type="text" class="btn_h24 w164" name="title" value="${dto.title}">
						</li>
						
						<!-- 草稿箱，隐藏类型/来源搜索,js控制 -->
						<li id="li_Type" class="li_Type">
							<span>类型：</span> 
							<select style="width: 127px;" class="btn_h28" required="true" name="type" id="select_Type">
								<option value="-1">全部</option>
								<option value="1" <c:if test="${dto.type==1}">selected</c:if>>自营</option>
								<c:if test="${userinfo.getPth() == 0 && userinfo.getPph() == 0}"> <!-- 平台号/品牌号 只有自营类型 -->
								<option value="0" <c:if test="${dto.type==0}">selected</c:if>>分销</option>
									<c:if test="${userinfo.getAgent()==1}">
										<option value="4" <c:if test="${dto.type==4}">selected</c:if>>代理</option>
									</c:if>
									<c:if test="${userinfo.getPthldd()==1}">
										<option value="5" <c:if test="${dto.type==5}">selected</c:if>>落地分销</option>
									</c:if>
								</c:if>
							</select>
						</li>
						
						<c:if test="${userinfo.getPth() == 1}"> <!-- 平台号/品牌号 才显 来源 -->
						<li id="li_Type" class="li_Type">
							<span>来源：</span> 
							<select style="width: 127px;" class="btn_h28" required="true" name="subWeiId" id="select_subWeiId">
								<option value="-1">全部</option>
								<c:forEach items="${searchvo.childrenList}" var="children">
									<option value="${children.childrenId}" <c:if test="${children.childrenId == dto.subWeiId}">selected="selected"</c:if>>${children.userName}</option>
								</c:forEach>
							</select>
						</li>
						</c:if>
						
						<li>
							<input type="submit" style="width: 80px;" class="btn_submit_two" value="搜索" id="searchBtn" />
						</li>
						
						<c:if test="${userinfo.getPth() == 1 || userinfo.getPph() == 1}"> <!-- 只有平台号、品牌号才显示 -->
						<li style="float: right;"><a onclick="wins('价格可见范围设置（所有产品）','win_div_2','520px','450px')" class="dis_b mr_20 fl btn_hui28_pre shou">价格可见设置</a></li>
						</c:if>
						<c:if test="${userinfo.getPthSupply() == 0 }"> <!-- 除了子供应商均可见 -->
						<li style="float: right;"><a onclick="wins('库存预警设置','win_warning','520px','300px')" class="dis_b fl btn_hui28_pre shou">库存预警设置</a></li>
						</c:if>
					</ul>
				</div>
			</c:if>
			<div class="blank1"></div>
		</div>

			<!-- 当点击状态与店铺分类过滤后数据为空时,显示 -->
			<c:if test="${dto.isClick==1 && fn:length(pageResult.list) < 1}">
				<c:if test="${dto.status==ProductStatusEnum.Showing}">
					<div class="null_coues fl">
						<p class="f18 tc">你还没有发布/上架过产品</p>
					</div>
				</c:if>
				<c:if test="${dto.status==ProductStatusEnum.Drop}">
					<div class="null_coues fl">
						<p class="f18 tc">你还没有下架过产品</p>
					</div>
				</c:if>
				<c:if test="${dto.status==ProductStatusEnum.OutLine}">
					<div class="null_coues fl">
						<div class="f18 mt_30 pt20 outermost tc fl">你的草稿箱空空如也</div>
						<div class="fl mt_20">
							<a class="btn_yes42_pre dis_b f14" style="margin-left:446px;" href="/publishProduct/index">发布产品</a>
						</div>
					</div>
				</c:if>
			</c:if>
			
			<c:if test="${dto.isClick!=1 || fn:length(pageResult.list) > 0}">
				<div id="div_conter" class="conter_right mt_10 fl bg_white bor_si">
					<div class="qbits fl">
						<ul>
							<li class="lbs_1"><input type="checkbox" class="checkall"></li>
							<li class="lbs_2">图片</li>
							<li class="lbs_3">标题</li>
							<li class="lbs_4">价格</li>
							<c:if test="${dto.status==ProductStatusEnum.Audit}"><!-- "待审核"显示的列 -->
								<li class="lbs_4">状态</li>
							</c:if>
							<c:if test="${dto.status!=ProductStatusEnum.Audit}"> <!-- 除"待审核"以外项显示的列 -->
								<li class="lbs_5">店铺分类</li>
								<li class="lbs_6">品牌</li>
								<li class="lbs_7">类型</li>
								<li class="lbs_8 tin30">供应商</li>
							</c:if>
							<li class="lbs_9">操作</li>
						</ul>
					</div>
					<c:choose>
						<c:when test="${fn:length(pageResult.list) < 1 }">
							<div class="bnimgs">
								<ul style="vertical-align: middle; text-align: center; height: 100px;">暂无相关数据</ul>
							</div>
						</c:when>
						<c:otherwise>
							<c:forEach items="${pageResult.list}" var="product" varStatus="status">
								<div class="bnimgs">
									<ul prodid="${product.productId}" prodimg="${product.defaultImg}" prodtitle="${product.productTitle}" prodconmision="${product.defaultConmision}" prodprice="${product.defaultPrice}" 
									<c:if test="${dto.status==ProductStatusEnum.OutLine}">
									prodsid="${product.sid}" 
									</c:if>
									<c:if test="${dto.status==ProductStatusEnum.Showing || dto.status==ProductStatusEnum.Drop}">
									prodsid="${product.classId}" 
									</c:if>
									
									prodSupplierId="${product.supplierWeiId}" sjId="${product.sjId}"
									>
										<li class="lbs_1 inchone"><input type="checkbox" name="productId" value="${product.productId}"/></li>
										<li class="lbs_2"><a href="<%=productdomain %>/product?sid=${product.sjId}" target="_blank" ><img src="${product.defaultImg}"/></a></li>
										<li class="lbs_3"><a href="<%=productdomain %>/product?sid=${product.sjId}" target="_blank" >${product.productTitle}</a></li>
										<!-- 价格显示区域 -->
										<li class="lbs_4">
											<div class="">
												<c:choose>
													<c:when test="${userInfo.getPth()==1||userInfo.getPph()==1||userInfo.getPthdls()==1||userInfo.getPthldd()==1}">
														<p class="ft_c9">佣金：￥${product.defaultConmision}</p>
														<p class="ft_c9">代理价：￥${product.agentPrice}</p>
														<p class="ft_c9">落地价：￥${product.storePrice}</p>
													</c:when>
													<c:when test="${userInfo.getPthSupply()==1||userInfo.pthSub()==1}">
														<p class="ft_c9">成本价：￥${product.factoryPrice}</p>
														<p class="ft_c9">建议价：￥${product.advicePrice}</p>
													</c:when>
													<c:when test="${userInfo.getPthSupply()==0 && userInfo.pthSub()==0}">
														<p class="ft_c9">现价：￥${product.defaultPrice}</p>
													</c:when>
													<c:otherwise>
														<p class="ft_c9">现价：￥${product.defaultPrice}</p>
													</c:otherwise>
												</c:choose>
												<p class="ft_c9">库存：${product.stockCount}</p>
												<div style="text-indent: 0; background: none; position:relative;" class="gygl_xxk_table_cz_bt_ytj mr_20 mt_10">
													<c:if test="${product.tag!=null}">
														<c:if test="${product.tag==2 || product.tag==3 || product.tag==6 || product.tag==7}">
															<span class="gygl_top_l_xx_lan fw_b" name="bookPriceSpan">预</span>
															<div name="bookPriceDiv" class="abput" style="display: none; left:0; top:23px;">
																<p class="pl20 pt5">铺货价:￥${product.bookPrice}</p>
															</div>
															
														</c:if>
														<c:if test="${product.tag==1 || product.tag==3 || product.tag==5 || product.tag==7}">
															<span class="gygl_top_l_xx_hong ml_20 mr_5 fw_b" name="batchPriceSpan" productId="${product.productId}" sjId="${product.sjId}" synchrodata="0">批</span>
														</c:if>
													</c:if>
													
												</div>
											</div>
										</li>
										
										<c:if test="${dto.status==ProductStatusEnum.Audit}"><!-- "待审核"显示的列 -->
											<li class="lbs_4">
												<c:if test="${product.status==6}">待审核</c:if>
												<c:if test="${product.status==7}">审核不通过</br> ${product.statusIntro}</c:if>
											</li>
										</c:if>
										<c:if test="${dto.status!=ProductStatusEnum.Audit}"><!-- 除"待审核"以外项显示的列 -->
											<li class="lbs_5"><div>${product.sName}</div></li>
											<li class="lbs_6"><div>${product.brandName}</div></li>
											<li class="lbs_7">
												<div>
													<c:if test="${product.type==1}">自营</c:if>
													<c:if test="${product.type==0}">分销</c:if>
													<c:if test="${product.type==4}">代理</c:if>
													<c:if test="${product.type==5}">落地分销</c:if>
												</div>
											</li>
											<li class="lbs_8"><div>${product.supplierName}</div></li>
										</c:if>
										
										<li class="lbs_9"><!-- 操作列 -->
											<c:choose>
												
												<c:when test="${dto.status==ProductStatusEnum.Showing}">
													<style>
													.mzh_fenxiang_no{ margin-left:-4px;}
													.mzh_fenxiang_yes{ margin-left:-4px;}
												</style>
													<div class="fl" name="mzh_fenxiang">
							                      		<div class="mzh_fenxiang_no" id="aaa">
							                      			<a href="javascript:;">分享</a>
							                          		<div class="mzh_fenxiang">
							                              		<a href="javascript:shareto(0,${product.sjId});"><img src="http://base.okimgs.com/images/TX_kj.gif"></a>
							                              		<a href="javascript:shareto(1,${product.sjId});"><img src="http://base.okimgs.com/images/TX_wb.gif"></a>
								                              	<a href="javascript:shareto(2,${product.sjId});"><img src="http://base.okimgs.com/images/XL_wb.gif"></a>
								                          	</div>
								                      	</div>
								                  	</div> 
								                  	<c:if test="${product.type!=4 && product.type!=5}">
								                  	<a href="javascript:;" name="drop_single" class="fl" style="padding-top:3px;padding-left:5px;display:block">下架</a>
								                  	</c:if>
								                  	<br/>
													<div class="clear"></div>
													
													<!-- 代理、落地分销的产品不显示编辑按钮 -->
													<c:if test="${product.type!=4 && product.type!=5}">
													<a href="javascript:;" name="edit_single" type="${product.type}">编辑</a>&nbsp;&nbsp; 
													<a href="javascript:;" name="delete_single">删除</a>
													</c:if>
												</c:when>
												<c:when test="${dto.status==ProductStatusEnum.Drop && product.type!=4 && product.type!=5 }">
													<a href="javascript:;" name="upput_single" type="${product.type}">上架</a>&nbsp;&nbsp;
													<a href="javascript:;" name="edit_single" type="${product.type}">编辑</a>&nbsp;&nbsp; 
													<a href="javascript:;" name="delete_single">删除</a>
												</c:when>
												<c:when test="${dto.status==ProductStatusEnum.OutLine && product.type!=4 && product.type!=5}">
													<a href="javascript:;" name="edit_single" type="${product.type}">编辑</a>&nbsp;&nbsp; 
													<a href="javascript:;" name="delete_single">删除</a>
												</c:when>
												<c:when test="${dto.status==ProductStatusEnum.Audit}">
													<c:if test="${userinfo.getPth() == 1}"> <!-- 平台号 -->
													<a href="javascript:;" name="audit_single" type="${product.type}">审核</a>&nbsp;&nbsp;
													</c:if>
													<c:if test="${userinfo.getPthSupply() == 1}"> <!-- 子供应商 -->
													<a href="javascript:;" name="edit_single" type="${product.type}">编辑</a>&nbsp;&nbsp;
													</c:if> 
													<a href="javascript:;" name="delete_single">删除</a>
												</c:when>
											</c:choose>
											<!-- <a href="javascript:;" name="delete_single">删除</a> -->
										</li>
									</ul>
								</div>
							</c:forEach>
							
					
							<div class="dibus">
								<ul>
									<li><input type="checkbox" class="checkall">&nbsp;<label>全选</label></li>
									<li>
										<a class="btn_hui28_pre fl dis_b mt_7" href="javascript:;" id="delete_selected">删除</a>
										<%-- <c:if test="${dto.status==ProductStatusEnum.Showing}">
										<a class="btn_hui28_pre fl ml_10 dis_b mt_7" href="javascript:;" id="drop_selected">下架</a>
										</c:if> --%>
									</li>
									<c:if test="${dto.status==ProductStatusEnum.Showing}">
									<li>
										<select id="move_select_1">
											<option value="0">移动到...</option>
											<c:forEach items="${searchvo.classList}" var="sclass">
												<option value="${sclass.sid}">${sclass.sname}</option>
											</c:forEach>
										</select>
										<select id="move_select" style="display: none;"></select>
									</li>
									<li>
										<select id="link_select">
											<option value="0">关联品牌</option>
											<c:forEach items="${searchvo.brandList}" var="brand">
												<option value="${brand.brandId}">${brand.brandName}</option>
											</c:forEach>
										</select>
									</li>
									</c:if>
								</ul>
							</div>
						</c:otherwise>
						
					</c:choose>
				</div>
				<!-- 分页 -->
							<div class="pull-right">
								<pg:page pageResult="${pageResult}" />
							</div>
			</c:if>
	</form>
	
	<!-- 上架产品弹出层 -->
	<div class="pos_rabls none" id="popupForm" prodId="" sjId="" prodSupplierId="">
		<div class="rabls_one">
			<div class="absdel" id="ImgClose"><img src="/statics/pic/del_ficfuncyes.png"/></div>
			<div class="one_left">
				<div class="imgfloates"><img src="/statics/pic/pfb-img001.png" id="popup_img"/></div>
				<div class="fotrightes">
					<div class="title_shop" id="popup_title">自由呼吸2015运动套装女连帽休闲套装女运动服女卫衣套装女春衣服</div>
					<div class="title_price">零售价：<span id="popup_price">￥168.00</span></div>
					<div class="title_count">佣金：<span id="popup_conmision">￥10.00</span></div>
				</div>
			</div> 
		</div>
		<div class="rabls_two">
			<div class="twoinp_sele">
				<div class="two_titles">你要把产品上架到哪个分类?</div>
				<div class="zhuang_delssic">
					<%-- <ul id="popup_shopclass">
						<c:if test="${null!=searchvo.classList && fn:length(searchvo.classList)>0 }">
							<c:forEach items="${searchvo.classList}" var="shopclass">
								<li name="popup_shopclass_li" value="${shopclass.sid}">${shopclass.sname}</li>
							</c:forEach>
						</c:if>
					</ul> --%>
					<div id="popup_shopclass">
						<select id="popup_select_1" class="two_titles_select">
							<option value="0">选择分类</option>
							<c:forEach items="${searchvo.classList}" var="sclass">
								<option value="${sclass.sid}">${sclass.sname}</option>
							</c:forEach>
						</select>
						<select id="popup_select" style="display: none;"  class="two_titles_select"></select>
					</div>
	                <div class="blank1"></div>
				</div>
				<div class="zhuang_shuru"><a href="/shopClass/classList" target="_black">新建/编辑分类</a></div>
			</div>
		</div>
		
		<!-- 没有批发价的，隐藏 -->
		<div class="rabls_two" id="newpricediv">
			<div class="twoinp_sele">
				<div class="two_titles">修改批发价出售：原批发价<span id="popup_batchprice">￥300~￥700</span></div>
				<div class="news_input">
					<ul id="popup_newprice">
						<li class="news_title">新批发价</li>
						<li><span>5件起批</span> <input type="text" class="btn_h28" /> 元</li>
						<li><span>20件以上</span> <input type="text" class="btn_h28" /> 元</li>
						<li><span>50件以上</span> <input type="text" class="btn_h28" /> 元</li> 
					</ul>
					<div class="blank"></div>
				</div>
				
			</div>
		</div>
		
		<div class="rabls_two" style="display: none;">
			<div class="twoinp_sele">
				<div class="two_titles">由谁来发货？</div>
				<div class="radio_to">
					<ul id="deliverSelect">
						<li><input type="radio" name="identity1" value="1" checked="checked"><label for="rio_2">我自己发货</label></li>
						<!-- <li><input type="radio" name="identity1" value="2"><label for="rio_2">供应商发货</label></li> -->
					</ul> 
				</div> 
			</div>
		</div>
		<div class="rabls_two">
			<a href="javascript:;" class="btn_bluese" id="confirm">确定</a>
		</div>
		<div style="display: none" id="settext">myproductpage</div>
	</div> 
	
	
	<!-- 库存预警设置 -->
	<div class="updata_tixian" style="display:none;" id="win_warning">
	    <dl class="xzgys f16 mb_20 mt_20">
	        <dd class="tr">库存预警值：</dd>
	        <dt>
	            <input type="text" class="xzgys_input" id="warnInput">
	        </dt>
	    </dl>
	    <div class="fl f14 ml_20"><span class="color_red">！</span>产品库存低于该值后进行预警提示  (值为空时，系统默认低于50时预警)</div>
	</div>
	
	<!-- 价格可见设置 -->
	<div class="updata_tixian" style="display:none;" id="win_div_2">
	    <dl class="xzgys f16 mb_20">
	        <dd class="tr" style="">设置代理价的可视范围：</dd>
	        <dt>
	            <div class="blank1"></div>
	            <div class="fl mzh_100 mt_5">
	                <input type="checkbox" id="agentsVisible" value="0" onclick="this.value=(this.value==0)?1:0"/>
	                <label for="id_wddlskj">我的代理商可见</label>
	            </div>
	            <div class="fl mzh_100 mt_5">
	                <input type="checkbox" id="otherAgentsVisible" value="0" onclick="this.value=(this.value==0)?1:0"/>
	                <label for="id_qtcjddlskj">其他厂家的代理商可见</label>
	            </div>
	            <div class="fl mzh_100 mt_5">
	                <input type="checkbox" id="dlFullyOpen" value="0" onclick="this.value=(this.value==0)?1:0"/>
	                <label for="id_wqgk">完全公开  <font class="ft_c9 f14">（谨慎选择）</font></label>
	            </div>
	        </dt>
	    </dl>
	    <div class="blank2 bor_bo"></div>
	    <div class="blank1"></div>
	    <dl class="xzgys f16 mb_20">
	        <dd class="tr" style="">设置落地价的可视范围：</dd>
	        <dt>
	            <div class="blank1"></div>
	            <div class="fl mzh_100 mt_5">
	                <input type="checkbox" id="ldAgentsVisible" value="0" onclick="this.value=(this.value==0)?1:0"/>
	                <label for="id_wddxlddkj">我的代理商下落地店可见</label>
	            </div>
	            <div class="fl mzh_100 mt_5">
	                <input type="checkbox" id="shopVisible" value="0" onclick="this.value=(this.value==0)?1:0"/>
	                <label for="id_wdlddkj">我的落地店可见</label>
	            </div>
	            <div class="fl mzh_100 mt_5">
	                <input type="checkbox" id="otherShopVisible" value="0" onclick="this.value=(this.value==0)?1:0"/>
	                <label for="id_qtcjdydlddkj">其他厂家对应的落地店可见</label>
	            </div>
	            <div class="fl mzh_100 mt_5">
	                <input type="checkbox" id="ldFullyOpen" value="0" onclick="this.value=(this.value==0)?1:0"/>
	                <label for="id_wqgk_1">完全公开  <font class="ft_c9 f14">（谨慎选择）</font></label>
	            </div>
	        </dt>
	    </dl>
	</div>
	
	
</body>