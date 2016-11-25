<%@page import="com.okwei.supplyportal.bean.vo.ProductParamModelVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>微店网-商品发布</title>

<link rel="stylesheet" type="text/css" href="/statics/js/lib/uploadify/uploadify.css" />
<script type="text/javascript">
	window.UEDITOR_HOME_URL = "/statics/ueditor/";
</script>
<link rel="stylesheet" type="text/css" href="/statics/css/glbdy.css" />
<link rel="stylesheet" type="text/css" href="/statics/css/index.css" />
<link rel="stylesheet" type="text/css" href="/statics/css/mzh_dd.css" />
<link rel="stylesheet" type="text/css" href="/statics/css/style.default.css" />
<link rel="stylesheet" type="text/css" href="/statics/js/lib/uploadify/uploadify.css" />
<script type="text/javascript" src="/statics/js/lib/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="/statics/js/layer/layer.min.js"></script>
<script type="text/javascript" charset="utf-8" src="/statics/js/lib/uploadify/jquery.uploadify-3.1.js"></script>
<!-- 配置文件 -->
<script type="text/javascript" src="/statics/ueditor/ueditor.config.js"></script>
<!-- 编辑器源码文件 -->
<script type="text/javascript" src="/statics/ueditor/ueditor.all.js"></script>
<!-- 语言包文件(建议手动加载语言包，避免在ie下，因为加载语言失败导致编辑器加载失败) -->
<script type="text/javascript" src="/statics/ueditor/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript" src="/statics/js/Supply/editProductInfo.js?v=201506161729"></script>
</head>
<body>
	<div class="outermost">
		<div class="content mar_au">
			<div class="weiz_iam f12 fm_song">
				当前位置：<a href="/publishProduct/index">产品管理</a>><span>发布产品</span>
			</div>
			<div class="readc fl">
				<div class="fl type_msg">
					<div class="title_type fl">
						<div class="leimu_msg f12 fw_b ft_c3">类目信息</div>						
						<div class="leimu_msg f12 ft_c3">类目 ：
							<c:if test="${pClass.clssName !=null && pClass.clssName !='' }">
							${pClass.oneClassName }>>${pClass.twoClassName }>>${pClass.clssName}
							</c:if>
							<c:if test="${pClass.clssName==null || pClass.clssName =='' }">
							未选择分类
							</c:if>
						</div>					
						<div id="btnSelectClass" class="btn_hui24_pre shou mt_10">编辑类目</div>
						<div class="muban_s f12 ft_c3 fl">
							模板：<span id="classModelName">${product.mID>0 && paramModel!=null?paramModel.mname:"未选择模板" }</span>
						</div>
						<div id="btnSelectPModel" class="btn_hui24_pre shou fl mt13">编辑模板</div>
						<div class="blank2"></div>
						<div class="leimu_msg f14 ft_c3">
							商品参数<span class="ft_sihui"> (您选择的模板内容)</span>：
						</div>
						<div class="blank2"></div>
						<div class="leimu_msg fl">
							<ul>
								<c:if test="${paramModel!=null &&  paramModel.keyList !=null && paramModel.keyList.size()>0}">
									<c:forEach items="${paramModel.keyList}" var="item">
										<li>${item.attributeName }：<span>${item.valueName }</span></li>
									</c:forEach>
								</c:if>
							</ul>
						</div>
					</div>
				</div>
				<div class="fl type_input">
					<div class="fl inp_suoyou">
						<div class="fl fic_kuan">
							<div class="fic_biaot fl f14 tr ft_c3">商品标题：</div>
							<div class="fic_select fl">
								<input type="text" id="txtProductTitle" value="${product.productTitle}" maxlength="30" class="btn_h42 fl f14 w350" placeholder="商品标题30字以内" />
								<!-- <div class="fl f14 ft_red error_font" ><img src="/statics/images/c-ico1.png" width="20" height="20" />输入错误</div> -->
							</div>
						</div>
						<div class="fl fic_kuan">
							<div class="fic_biaot fl f14 tr ft_c3">副标题：</div>
							<div class="fic_select fl">
								<input type="text" id="txtProductMinTitle" value='${product.productMinTitle}' maxlength="50" class="btn_h42 fl f14 w350" placeholder="可填写产品特性、促销等信息" />
								<!-- <div class="fl selyes_font" ><img src="/statics/images/d-ico1.png" width="20" height="20" /></div> -->
							</div>
						</div>
						<div class="fl fic_kuan">
							<div class="fic_biaot fl f14 tr ft_c3">关键词：</div>
							<div class="fic_select fl">
								<input type="text" id="txtkeyWords" maxlength="50" value="${product.keyWords}" class="btn_h42 f14 w350" placeholder="商品搜索关键词，以空格隔开" />
							</div>
						</div>
						<div class="fl fic_kuan">
							<div class="fic_biaot fl f14 tr ft_c3">商品参数：</div>
							<div class="fic_select fl">
								<div class="fl value_shez f14 ft_c3">
									<input type="checkbox" andio="0" id="cboxIsShowParam" checked="fase" class=" mt13 mr_5 dis_b fl" /> <label for="cboxIsShowParam">商品属性设置 <span onmouseover="tips(this)" name="准确的商品属性，才能带来更多的销量"><img src="/statics/pic/icon_wensi.png" /></span></label>
								</div>
								<div id="divSetParam" style="width: 100%; float: left; background: #f3f3f3; display: none;">
									<c:if test="${product.mID >0}">
										<div class="fl value_shez f14 ft_c3 fw_b t_ind20">商品参数设置</div>
										<div class="fl type_selec">
										<c:if test="${paramModel!=null &&  paramModel.keyList !=null && paramModel.keyList.size()>0}">
											<c:forEach items="${paramModel.keyList}" var="item">
												<c:if test="${item.sysAttributeID>0}">
													<div class="selec_once fl">
														<div class="clao_le fl f14 tr ft_c3">
															<span showType="${item.showtype }" attributeID="${item.attributeId}" isMust="${ item.isMust}">${item.attributeName}</span>：
														</div>
														<div class="clao_rg fl">
															<c:choose>
																<c:when test="${item.showtype ==1 }">
																	<input type="text" avid="${item.valueList[0].avid }" value="${item.valueList[0].value }" class="btn_h42 f14 w350" />
																</c:when>
																<c:when test="${item.showtype ==2 }">
																	<select class="btn_h42 f14 w350">
																		<c:forEach items="${item.valueList}" var="valItem">
																			<option ${valItem.isDefault==1?"selected='selected'":"" } avid="${valItem.avid }" value="${valItem.value }">${valItem.value }</option>
																		</c:forEach>
																	</select>
																</c:when>
																<c:when test="${item.showtype ==3 }">
																	<div class="fl check_boxfont">
																		<c:forEach items="${item.valueList}" var="valItem">
																			<input type="checkbox" avid="${valItem.avid }" value="${valItem.value }" id="ck${valItem.avid }" ${valItem.isDefault==1?"checked='checked'":"" } />
																			<label for="ck${valItem.avid }">${valItem.value }</label>
																		</c:forEach>
																	</div>
																</c:when>
															</c:choose>
														</div>
													</div>
												</c:if>
											</c:forEach>
											</c:if>
											<div class="blank1"></div>
										</div>
									</c:if>
									<div class="fl value_shez f14 ft_c3 fw_b t_ind20">
										自定义参数设置<span onmouseover="tips(this)" name="商品属性不够？您可以添加自定义商品属性"><img src="/statics/pic/icon_wensi.png" /></span>
									</div>
									<div class="self fl">
										<div id="Fice_inp">
											<c:if test="${paramModel!=null &&  paramModel.keyList !=null && paramModel.keyList.size()>0}">
												<c:forEach items="${paramModel.keyList}" var="item">
													<c:if test="${item.sysAttributeID==0 || item.sysAttributeID==null}">
														<div class="self_one fl">
															<div class="once_inps fl">
																<input maxlength="16" type="text" attributeID="${item.attributeId}" name="attributeName" value="${item.attributeName }" class="btn_h42 tr pr_10 w104 f14" />：
															</div>
															<div class="once_inps fl">
																<input  maxlength="16" type="text" name="valueName" avid="${item.valueList[0].avid }" value="${item.valueList[0].value }" class="btn_h42 f14 w222" />
															</div>
															<div class="fl xiao_icon">
																<a href="javascript:;" name="Del_img"><img src="/statics/pic/delete_icon.png" /></a>
															</div>
														</div>
													</c:if>
												</c:forEach>
											</c:if>
											<div class="self_one fl">
												<div class="once_inps fl">
													<input type="text" maxlength="16" name="attributeName" class="btn_h42 tr pr_10 w104 f14">：
												</div>
												<div class="once_inps fl">
													<input type="text" maxlength="16" value="" name="valueName" class="btn_h42 f14 w222">
												</div>
												<div class="fl xiao_icon">
													<a name="Add_imgs" href="javascript:;"><img src="/statics/pic/tianjia_icon.png"></a>
												</div>
											</div>
										</div>
										<div class="self_one fl">
											<a href="javascript:;" class="dis_b fl btn_wear42_pre" id="btnSaveParamTemp">保存模板</a>
											<div class="fr shou" id="imgHideParamDiv">
												<img src="/statics/pic/hide_topimg.png" />
											</div>
										</div>
										<div class="blank1"></div>
									</div>
								</div>
							</div>
						</div>
						<div class="fl fic_kuan" id="divProductImg">
							<div class="fic_biaot fl f14 tr ft_c3">商品图片：</div>
							<div class="fic_select fl">
								<div style="width: 100%; float: left; background: #f3f3f3;">
									<div class="xua_imgs fl">
										<a id="" href="javascript:void(0);" class="dis_b btn_wear24_pre fl"> <span id="btnUpImg">选择图片</span>
										</a> <span class="ml_10 ft_c9 fl lh_40">注：最多允许6张产品图片，产品图片规格为1:1 图片像素800*800 文件大小500K以内</span>
									</div>
									<div class="she_img fl">
										<ul>
											<c:if test="${product!=null && product.productImg!='' && product.productImg!=null }">
												<li>
													<div class="own_img">
														<div class="img_dele">
															<img src="/statics/pic/delete_imgs.png" />
														</div>
														<div class="own_yis">
															<img src="${product.productImg}" />
														</div>
														<div class="she_ci">
															<input type="radio" checked="checked" name="productImg" value="${product.productImg }" id="productImg0" /> <label for="productImg0">设为主图</label>
														</div>
													</div>
												</li>
											</c:if>
											<c:if test="${product==null || product.productImg =='' || product.productImg==null }">
												<li>
													<div class="own_img_one">
														<img src="/statics/pic/xin_inselimg.png" />
														<div class='transparentDiv'>
															<span id="upbtnID0"></span>
														</div>
													</div>
												</li>
											</c:if>
											<c:forEach var="item" begin="0" end="4" varStatus="state">
												<c:choose>
													<c:when test="${state.index <product.imgList.size()}">
														<li>
															<div class="own_img">
																<div class="img_dele">
																	<img src="/statics/pic/delete_imgs.png" />
																</div>
																<div class="own_yis">
																	<img src="${product.imgList[state.index].imgPath}" />
																</div>
																<div class="she_ci">
																	<input type="radio" name="productImg" value="${product.imgList[state.index].imgPath}" id="productImg${product.imgList[state.index].productImgId }" /> <label for="productImg${product.imgList[state.index].productImgId }">设为主图</label>
																</div>
															</div>
														</li>
													</c:when>
													<c:otherwise>
														<li>
															<div class="own_img_one">
																<img src="/statics/pic/xin_inselimg.png" />
																<div class='transparentDiv'>
																	<span id="upbtnID${state.index+1 }"></span>
																</div>
															</div>
														</li>
													</c:otherwise>
												</c:choose>
											</c:forEach>
										</ul>
									</div>
								</div>
							</div>
						</div>
						<div class="fl fic_kuan">
							<div class="fic_biaot fl f14 tr ft_c3">商品价格：</div>
							<div class="fic_select fl ">
								<div class="fl ft_c6">
									<input type="text" id="txtProductPrice" maxlength="10" value="${(product.price-product.commission)>0?(product.price-product.commission):'' }" class="btn_h42 f14 w130" placeholder="商品价格" /> 元
								</div>
								<div class="fl f14 fw_b fm_aria jia_zai">+</div>
								<div class="fl ft_c6">
									<input type="text" id="txtProductComminss" maxlength="10" value="${product.commission }" class="btn_h42 f14 w130" placeholder="佣金" /> 元 <img src="/statics/pic/icon_wensi.png" class="img_weiz" onmouseover="tips(this)" name="佣金用来鼓励分销商上架销售" />
								</div>
								<div class="fl f14 fw_b fm_aria jia_zai">=</div>
								<div class="fl ft_c6">
									<input type="text" id="txtTotal" maxlength="10" value="${product.price }" disabled="disabled" class="btn_nosur42 f14 w130" placeholder="微店价" /> 元
								</div>
							</div>
						</div>
						<div class="fl fic_kuan">
							<div class="fic_biaot fl f14 tr ft_c3">商品数量：</div>
							<div class="fic_select fl">
								<input id="txtProductNum" maxlength="10" value="${product.count<1?'': product.count}" type="text" class="btn_h42 f14 w350" /> <span class="ml_10 ft_c9">注：未展开商品规格设置情况下，该数量为每个规格的数量</span>
							</div>
						</div>
						<div class="blank2"></div>
						<div class="fl fic_kuan mar_tono">
							<div class="fic_biaot fl f14 tr ft_c3">商品规格：</div>
							<div class="fic_select fl">
								<div class="pinpaixze_pf" style="background: #f3f3f3;">
									<c:if test="${product.sellKeyList !=null &&  product.sellKeyList.size()>0}">
										<c:forEach items="${product.sellKeyList }" var="keyItem">
											<div class="pinpaixze_is">
												<div class="fl pincmas">
													<select>
														<option value="--请选择--">--请选择--</option>
														<c:forEach items="${sellParams}" var="sysName">
															<c:if test="${sysName == keyItem.attributeName && sysName!='-1'}">
																<option value="${sysName}" selected="selected">${sysName}</option>
															</c:if>
															<c:if test="${sysName != keyItem.attributeName  && sysName!='-1'}">
																<option value="${sysName}">${sysName}</option>
															</c:if>
														</c:forEach>
														<option value="自定义规格">自定义规格</option>
													</select>
												</div>
												<div class="fl pincrig">
													<c:if test="${keyItem.sellValueList !=null &&  keyItem.sellValueList.size()>0}">
														<c:forEach items="${keyItem.sellValueList}" var="valueItem" varStatus="status">
															<c:if test="${valueItem.value !='-1' }">
																<div class="input_cols fl">
																	<input type="text" valueID="${valueItem.keyId}" value="${valueItem.value}" placeholder="自定义值" maxlength='10' />
																	<div class="fl del_inputimg">
																		<img src="/statics/images/delimg_hua.png" style="top: 8px;" />
																	</div>
																</div>
															</c:if>
														</c:forEach>
													</c:if>
													<div class="input_cols fl">
														<input type="text" placeholder="自定义值" value="" valueid="" maxlength='20'>
														<div class="fl add_heistimg">
															<img style="top: 8px;" src="/statics/pic/tianjia_icon.png">
														</div>
													</div>
												</div>
											</div>
										</c:forEach>
									</c:if>
									<c:if test="${product.sellKeyList ==null ||  product.sellKeyList.size()<2}">
										<c:forEach begin="1" end="${2-product.sellKeyList.size()}">
											<div class="pinpaixze_is">
												<div class="fl pincmas">
													<select>
														<option value="--请选择--">--请选择--</option>
														<c:forEach items="${sellParams}" var="sysName">
															<option>${sysName}</option>
														</c:forEach>
														<option value="自定义规格">自定义规格</option>
													</select>
												</div>
												<div class="fl pincrig">
													<div class="input_cols fl">
														<input type="text" valueid="" value="" placeholder="自定义值" maxlength='10'>
														<div class="fl add_heistimg">
															<img src="/statics/pic/tianjia_icon.png" style="top: 8px;">
														</div>
													</div>
												</div>
											</div>
										</c:forEach>
									</c:if>
									<div class="blank1"></div>
								</div>
								<div class="add_mos fl">
									<div class="wulai_fle fl">
										<div class="num_col fl mt_10">
											<div class="title_lefts fl">
												<ul>
													<li class="ift_lefcolor">${product.sellKeyList !=null &&  product.sellKeyList.size()>0 && product.sellKeyList[0].attributeName !='-1' ? product.sellKeyList[0].attributeName:""}</li>
													<li class="img_rigcolor">图片（无图片可不填）</li>
												</ul>
											</div>
											<div id="Cofic">
												<c:if test="${product.sellKeyList !=null &&  product.sellKeyList.size()>0 && product.sellKeyList[0].attributeName !='-1'}">
													<c:forEach items="${product.sellKeyList }" var="keyItem" varStatus="status">
														<c:if test="${status.index==0 && keyItem.sellValueList !=null &&  keyItem.sellValueList.size()>0}">
															<c:forEach items="${keyItem.sellValueList}" var="valueItem">
																<div class="col_scimgs fl">
																	<div class="scimg_col fl">
																		<!-- <span class="dis_b ml_10 dis_bgnews fl"></span> --><span class="fl ml_10 lh_40 ft_c3 dis_b">${valueItem.value }</span>
																	</div>
																	<div class="scimg_del fl">
																		<div class="btn_wear30_pre shou ml_10 mt_5 fl w150">
																			<span id="valueImg${valueItem.keyId }">上传图片</span>
																		</div>
																		<c:if test='${valueItem.defaultImg !=null && valueItem.defaultImg!="" }'>
																			<div class="fr imgsew">
																				<img src="${valueItem.defaultImg }" width="30" height="30" /><a class="ml_10 mr_20" href="javascript:void(0);">删除</a>
																			</div>
																		</c:if>
																	</div>
																</div>
															</c:forEach>
														</c:if>
													</c:forEach>
												</c:if>
											</div>
										</div>
									</div>
									<div class="blank1"></div>
									<div class="fl up_coz ft_c3">
										<input type="checkbox" id="box_1" andio="0" /> <label for="box_1">展开商品规格设置</label> <a href="#" class="ft_c9">(设置多个价格)</a>
									</div>
									<div class="fl testing_bat" id="Ute_inp" style="display: none;">
										<div class="fl bor_si size_color">
											<div class="fl size_titles">
												<ul>
													<c:if test="${product.sellKeyList !=null && product.sellKeyList.size()>0 }">
														<c:forEach items="${product.sellKeyList}" var="item" varStatus="status">
															<c:choose>
																<c:when test="${status.index==0 }">
																	<li name="liKeyName" class="coput_1">${item.attributeName !='-1'? item.attributeName:''}</li>
																</c:when>
																<c:when test="${status.index==1 }">
																	<li name="liKeyName" class="coput_2">${item.attributeName}</li>
																</c:when>
																<c:otherwise>
																	<li name="liKeyName" class="coput_7">${item.attributeName}</li>
																</c:otherwise>
															</c:choose>
														</c:forEach>
													</c:if>
													<c:if test="${product.sellKeyList ==null || product.sellKeyList.size()==0 }">
														<li class="coput_1"></li>
														<li class="coput_2"></li>
													</c:if>
													<li class="coput_3">价格<!-- <a href="javascript:void(0);" class="fw_100 handle_imgs ft_lan">批量操作</a> --></li>
													<li class="coput_4">数量<!-- <a href="javascript:void(0);" class="fw_100 handle_imgs ft_lan">批量操作</a> --></li>
													<li class="coput_5">商家编码</li>
													<li class="coput_6">操作</li>
												</ul>
											</div>
											<div class="fl size_inp" id="Foct_Shop">
												<c:if test="${product.styleList!=null && product.styleList.size()>0 && product.styleList[0].styleKVList[0].valueName !='-1'}">
													<c:forEach items="${product.styleList}" var="styleItem">
														<ul>
															<c:forEach items="${styleItem.styleKVList}" var="kvItem" varStatus="kvStatus">
																<c:choose>
																	<c:when test="${kvStatus.index==0 }">
																		<li class="inp_col1">
																			<div name="divValueName" class="color_yase">${kvItem.valueName}</div>
																		</li>
																	</c:when>
																	<c:when test="${kvStatus.index==1 }">
																		<li class="inp_col2">
																			<div name="divValueName" class="color_yase">${kvItem.valueName}</div>
																		</li>
																	</c:when>
																	<c:otherwise>
																		<li class="inp_col7">
																			<div name="divValueName" class="color_yase">${kvItem.valueName}</div>
																		</li>
																	</c:otherwise>
																</c:choose>
															</c:forEach>
															<li class="inp_col3">
																<div class="inps_san fl">
																	<input type="text" name="sellPrice" value="${styleItem.price - styleItem.conmision}" maxlength="10" class="w66 btn_h30 fl dis_b" placeholder="商品价格" /> <span class="dis_b f14 fm_aria fw_b fl ft_c6">+</span> <input type="text" name="sellComminss" value="${styleItem.conmision }" class="w66 btn_h30 fl dis_b" placeholder="佣金" /> <span class="dis_b f14 fm_aria fw_b fl ft_c6">=</span> <input type="text" name="sellTotal" value="${styleItem.price}" disabled="disabled" class="w66 btn_h30 fl dis_b" placeholder="微店价" />
																</div>
															</li>
															<li class="inp_col4">
																<div class="inps_san_ones fl">
																	<input type="text" value="${styleItem.count<1?'':styleItem.count}" maxlength="10" name="txtSellNum" class="w80 btn_h30 ml_30" placeholder="数量" />
																</div>
															</li>
															<li class="inp_col5">
																<div class="inps_san_ones fl">
																	<input type="text" value="${styleItem.bussinessNo}" name="txtBussinessNo" class="w80 btn_h30" placeholder="商家编码" />
																</div>
															</li>
															<li class="inp_col6"><a href="javascript:void(0);"><img src="/statics/pic/delete_icon.png" /></a></li>
														</ul>
													</c:forEach>
												</c:if>
												<div class="blank2"></div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="fl fic_kuan">
							<div class="fic_biaot fl f14 tr ft_c3">批发设置：</div>
							<div class="fic_select fl">
								<div class="fl up_cozfies f14 ft_c3">
									<input type="checkbox" id="box_2" andio="0" /> <label for="box_2">支持批发</label>
								</div>
								<div class="section_pric fl" id="Ute_inpFic" style="display: none;">
									<div class="sect_bor bor_si fl">
										<div class="h40 title_fons f12 fw_b ft_c6">价格区间</div>
										<div class="h40 title_fons30 f12 fw_b ft_c6">
											<div class="fons_one fl">购买数量</div>
											<div class="fons_two fl">价格</div>
										</div>
										<div id="PriceTjia">
											<c:if test="${product.batchPriceList !=null && product.batchPriceList.size()>0}">
												<c:forEach items="${product.batchPriceList }" var="item">
													<div class="h40 title_shuru">
														<div class="shuru_price fl">
															<input type="text" value="${item.count}" maxlength="10" class="btn_h30 w98">&nbsp;件及以上：
														</div>
														<div class="shuru_priceon fl">
															<input type="text" value="${item.pirce}" maxlength="10" class="btn_h30 w98"> &nbsp;元/件 <a name="DelPrice" href="javascript:;"><img src="/statics/pic/delete_icon.png"></a>
														</div>
													</div>
												</c:forEach>
											</c:if>
										</div>
										<div class="add_srus" id="AddPrice">
											<a href="javascript:;" class="f12 ft_lan"><span class="fw_b f14 fm_aria">+</span> 添加价格区间</a>
										</div>
									</div>
									<div class="yunln_bor bor_si fl">
										<div class="h40 title_fons f12 fw_b ft_c6">预览效果</div>
										<div class="h40 title_fons30 f12 fw_b ft_c6">
											<div class="fons_one fl">购买数量</div>
											<div class="fons_two fl">价格</div>
										</div>
										<c:if test="${product.batchPriceList !=null && product.batchPriceList.size()>0}">
											<c:forEach items="${product.batchPriceList }" var="item">
												<div class="h40 title_shuru">
													<div class="shuru_price fl f14">${item.count}件及其以上：${item.pirce}元/件</div>
												</div>
											</c:forEach>
										</c:if>
										<div class="blank2"></div>
									</div>
								</div>
							</div>
						</div>
						<div class="fl fic_kuan">
							<div class="fic_biaot fl f14 tr ft_c3">预定设置：</div>
							<div class="fic_select fl">
								<div class="fl up_cozfies f14 ft_c3">
									<input type="checkbox" id="box_3" andio="0" /> <label for="box_3">支持预定</label>
								</div>
								<div class="rese_pric fl" id="YuDing" style="display: none;">
									<div style="width: 100%; float: left; background: #f3f3f3;">
										<div class="blank"></div>
										<div class="rese_pric fl">
											<div class="pric_bar fl f14 tr ft_c3">预定商品价格：</div>
											<div class="pric_barnew fl f14">
												<input type="text" id="txtPreOrderPrice" maxlength="10" value="${product.preOrder !=null ? product.preOrder.preOrderPrice:''}" class="btn_h42 f14 w130" /> &nbsp;&nbsp;元
											</div>
										</div>
										<div class="rese_pric fl mt_10">
											<div class="pric_bar fl f14 tr ft_c3">预定商品备注：</div>
											<div class="pric_barnew fl f14">
												<textarea id="txtPreOrderContent" class="inp_box">${product.preOrder !=null ? product.preOrder.content : ""}</textarea>
											</div>
										</div>
										<div class="blank"></div>
									</div>
								</div>
							</div>
						</div>
						<div class="fl fic_kuan">
							<div class="fic_biaot fl f14 tr ft_c3">商品详情：</div>
							<div class="fic_select fl">
								<div class="news_tabq">
									<a href="javascript:;" name="PcWap" class="tba_twofic">PC端详情</a> <a href="javascript:;" name="PcWap" class="tba_onefic">移动端详情</a>
								</div>
								<div class="blank2"></div>
								<div id="PcNew_1" class="fl">
									<div class="datais_box bor_si fl" id="pcEditor">
										<!-- 这里写你的初始化内容 -->
										<script id="pcContent" name="pcContent" type="text/plain">${product.pcdes }</script>
										<!-- pc端详细 百度编辑器 -->
									</div>
									<div class="blank2"></div>
									<a href="javascript:;" id="btnCreateWapDes" class="btn_wear42_pre dis_b fl ">生成移动端详情</a>
								</div>
								<div id="PcNew_2" class="fl" style="display: none;">
									<!--                 <div><a href="javascript:;"class="btn_yes42_pre dis_b fl">上传图片</a></div>
                <div class="blank2"></div> -->
									<!-- 这里写你的初始化内容 -->
									<script id="wapContent" name="wapContent" type="text/plain">${product.appdes }</script>
									<div class="datais_boxwap bor_si" id="wapEditor">
										<!-- WAP端     端详细 百度编辑器 -->
									</div>
								</div>
							</div>
						</div>
						<div class="fl fic_kuan">
							<div class="fic_biaot fl f14 tr ft_c3">运费板块：</div>
							<div class="fic_select fl">
								<select id="selPostAge" class="f14 w350 btn_h42 fl dis_b">
									<c:if test="${postAgeList !=null && postAgeList.size()>0}">
										<c:forEach items="${postAgeList}" var="item">
											<option value="${item.freightId }" ${product.freightId ==item.freightId ?"selected='selected'":"" }>${item.postAgeName }</option>
										</c:forEach>
									</c:if>
								</select>
							</div>
							<div class="fl fic_kuan">
								<div class="fic_biaot fl f14 tr ft_c3">店铺分类：</div>
								<div class="fic_select fl">
									<select id="selShopClass" class="f14 w350 btn_h42 fl dis_b">
										<c:if test="${shopClassList !=null && shopClassList.size()>0}">
											<c:forEach items="${shopClassList}" var="item">
												<option value="${item.sid }" ${product.sid ==item.sid?"selected='selected'":"" }>${item.sname }</option>
											</c:forEach>
										</c:if>
									</select> <a href="javascript:void(0);" class="btn_wear42_pre dis_b fl ml_20" id="btnSaveShopClass">新增分类模板</a>
								</div>
							</div>
							<div class="blank"></div>
						</div>
						<div class="sub_btns fl">
							<div class="bc_draft fl">
								<a id="btnSaveDraft" href="javascript:void(0);" class="dis_b">保存草稿</a>
							</div>
							<div class="bc_submit fl">
								<input type="submit" id="btnSaveProduct" value="发布商品" class="btn_yes42_pre bor_cls shou" />
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 模板区 -->
	<div id="NefMban" class="popup_tc layer_pageContent" style="display: none;">
		<input id="txtName" type="text" maxlength="8" class="btn_h42 w350 ml_30" placeholder="请定义模板名称">
	</div>
	<!-- hidden区 -->
	<input type="hidden" id="txtProductID" value="${product.productId }" />
	<input type="hidden" id="txtClassID" value="${product.classId }" />
	<input type="hidden" id="txtMID" value="${product.mID}" />
	<input type="hidden" id="txtOperation" value="${operation}" />
</body>
</html>