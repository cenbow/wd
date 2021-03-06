<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><c:choose>
		<c:when test="${addorupd == 0 }">新增运费模板</c:when>
		<c:otherwise>编辑运费模板</c:otherwise>
	</c:choose></title>
<link rel="stylesheet" type="text/css" href="/statics/css/regions.css" />
</head>
<body>
	<div class="bor_si fl bg_white">
		<div class="order_title fl f16">
			<c:choose>
		<c:when test="${addorupd == 0 }">新增运费模板</c:when>
		<c:otherwise>编辑运费模板</c:otherwise>
	</c:choose>
			<div class="abs_img" style="top:27px;z-index: 9999">
				<img src="/statics/pic/img_absicon.png">
			</div>
		</div>
		<div class="fl inp_suoyou">
			<div class="fl fic_kuan">
				<div class="fic_biaot fl f14 tr ft_c3">模板名称：</div>
				<div class="fic_select fl">
					<input id="postAgeName" type="text" class="btn_h42 fl f14 w350" placeholder="模板名称30字以内" maxlength="30" value="${fvo.postAgeName }" />
					<div class="fl f14 ft_red error_font">
						<span class="errorspan" style="display: none;"> <img src="/statics/images/c-ico1.png" width="20" height="20">输入内容不能为空
						</span> <span class="rightspan" style="display: none;"> <img src="/statics/images/d-ico1.png" width="20" height="20">
						</span>
					</div>
				</div>
			</div>
			<div class="fl fic_kuan">
				<div class="fic_biaot fl f14 tr ft_c3">宝贝地址：</div>
				<div class="fic_select fl">
					<select class="fl btn_h42 w104 mr_10" id="sheng">
						<option value="0">省</option>
					</select> <select class="fl btn_h42 w104 mr_10" id="shi">
						<option value="0">市</option>
					</select> <select class="fl btn_h42 w104" id="qu">
						<option value="0">区</option>
					</select>
					<div class="fl f14 ft_red error_font">
						<span class="errorspan" style="display: none;"> <img src="/statics/images/c-ico1.png" width="20" height="20">请选择地址
						</span> <span class="rightspan" style="display: none;"> <img src="/statics/images/d-ico1.png" width="20" height="20">
						</span>
					</div>
				</div>
			</div>
			<div class="fl fic_kuan">
				<div class="fic_biaot fl f14 tr ft_c3">发货时间：</div>
				<div class="fic_select fl">
					<select class="fl btn_h42 w350" id="deliverytime">
						<option value="8">8小时以内</option>
						<option value="24">24小时以内</option>
						<option value="48">两天内</option>
						<option value="72">三天内</option>
						<option value="120">五天内</option>
						<option value="168">七天内</option>
						<option value="999">其他</option>
					</select>
				</div>
			</div>
			<div class="fl fic_kuan">
				<div class="fic_biaot fl f14 tr ft_c3">计件方式：</div>
				<div class="fic_select fl">
					<span class="f14 ft_c6" style="line-height: 40px;"> <c:choose>
							<c:when test="${fvo.billingType == 1 }">按件数</c:when>
							<c:when test="${fvo.billingType == 2 }">按重量</c:when>
							<c:when test="${fvo.billingType == 3 }">按体积</c:when>
							<c:otherwise>按件数</c:otherwise>
						</c:choose>
					</span>
				</div>
			</div>
			<div class="fl fic_kuan">
				<div class="fic_biaot fl f14 tr ft_c3">运送方式：</div>
				<div class="fic_select fl">
					<div class="rdis_fic1 ft_c9">除指定区域外，其余地区的运费采用“默认运费”</div>
					<div class="rdis_fic2">
						<!-- <input type="checkbox" id="express" <c:if test="${express }">checked</c:if> /> &nbsp;  <c:if test="${!express }">style="display: none"</c:if>-->
						<label id="express">快递(必填)</label>
					</div>
					<div class="posabs" >
						<div class="mryes">
							<span>默认运费：</span> <input type="text" class="btn_h30 w80 firstCount" value="${kdmr.firstCount }" /> 件内， <input type="text" class="btn_h30 w80 firstpiece" value="${kdmr.firstpiece }" /> 元起， 每增加 <input type="text" class="btn_h30 w80 moreCount" value="${kdmr.moreCount }" /> 件， 运费增加 <input type="text" class="btn_h30 w80 morepiece" value="${kdmr.morepiece }" /> 元。
						</div>
						<div class="lu_table">
							<a href="javascript:void(0)" class="f14" id="setexpress">为指定城市设置运费</a>
							<table>
								<tbody>
									<tr>
										<td class="tc">运送到</td>
										<td class="tc">首件(件)</td>
										<td class="tc">首费(元)</td>
										<td class="tc">续件(件)</td>
										<td class="tc">续费(元)</td>
										<td class="tc">操作</td>
									</tr>
									<c:forEach var="fd" items="${fvo.detailsList }">
										<c:if test="${fd.courierType ==1 && fd.status==1 }">
											<tr data-value="${fd.destination }">
												<td><span>${fd.areaName }</span><a href="javascript:void(0)" class="ft_lan fr showarea">编辑</a></td>
												<td class="tc"><input type="text" class="btn_h30 w80 firstCount" value="${fd.firstCount }" /></td>
												<td class="tc"><input type="text" class="btn_h30 w80 firstpiece" value="${fd.firstpiece }" /></td>
												<td class="tc"><input type="text" class="btn_h30 w80 moreCount" value="${fd.moreCount }" /></td>
												<td class="tc"><input type="text" class="btn_h30 w80 morepiece" value="${fd.morepiece }" /></td>
												<td class="tc"><a href="javascript:void(0)" class="ft_c6 delarea">删除</a></td>
											</tr>
										</c:if>
									</c:forEach>
								</tbody>
							</table>
							<div class="blank1"></div>
						</div>
					</div>


					<div class="rdis_fic2">
						<input type="checkbox" id="logistics" <c:if test="${logistics }">checked</c:if>> &nbsp; <label for="logistics">物流</label>
					</div>
					<div class="posabs" <c:if test="${!logistics }">style="display: none"</c:if>>
						<div class="mryes">
							<span>默认运费：</span> 
							<input type="text" class="btn_h30 w80 firstCount" value="${wlmr.firstCount }" /> 件内， 
							<input type="text" class="btn_h30 w80 firstpiece" value="${wlmr.firstpiece }" /> 元起， 每增加 
							<input type="text" class="btn_h30 w80 moreCount" value="${wlmr.moreCount }" /> 件， 运费增加 
							<input type="text" class="btn_h30 w80 morepiece" value="${wlmr.morepiece }" /> 元。
						</div>
						<div class="lu_table">
							<a href="javascript:void(0)" class="f14" id="setlogistics">为指定城市设置运费</a>
							<table>
								<tbody>
									<tr>
										<td class="tc">运送到</td>
										<td class="tc">首件(件)</td>
										<td class="tc">首费(元)</td>
										<td class="tc">续件(件)</td>
										<td class="tc">续费(元)</td>
										<td class="tc">操作</td>
									</tr>
									<c:forEach var="fd" items="${fvo.detailsList }">
										<c:if test="${fd.courierType == 4 && fd.status==1 }">
											<tr data-value="${fd.destination }">
												<td><span>${fd.areaName }</span><a href="javascript:void(0)" class="ft_lan fr showarea">编辑</a></td>
												<td class="tc"><input type="text" class="btn_h30 w80 firstCount" value="${fd.firstCount }" /></td>
												<td class="tc"><input type="text" class="btn_h30 w80 firstpiece" value="${fd.firstpiece }" /></td>
												<td class="tc"><input type="text" class="btn_h30 w80 moreCount" value="${fd.moreCount }" /></td>
												<td class="tc"><input type="text" class="btn_h30 w80 morepiece" value="${fd.morepiece }" /></td>
												<td class="tc"><a href="javascript:void(0)" class="ft_c6 delarea">删除</a></td>
											</tr>
										</c:if>
									</c:forEach>
								</tbody>
							</table>
							<div class="blank1"></div>
						</div>
					</div>
				</div>
			</div>
			<div class="fl fic_kuan mb_20">
				<a href="javascript:void(0)" onclick="submitData()" class="botis">确定</a> <a href="/freight/freightList" class="botisto">取消</a>
			</div>
			<div class="blank1"></div>
		</div>
	</div>
	<input type="hidden" id="freightId" value="${fvo.freightId }" />
	<script type="text/javascript" src="/statics/js/district.js"></script>
	<script type="text/javascript" src="/statics/js/jquery.regions.js"></script>
	<script type="text/javascript">
		function InitCity() {
			// 初始化省市区列表
			var province = "${fvo.province}";
			var city = "${fvo.city}";
			var area = "${fvo.district}";
			var dis = new district();
			dis.init('#sheng', '#shi', '#qu');
			dis.bdbycode(province, city, area);
			var deliverytime = "${fvo.deliverytime }";
			$("#deliverytime").val(deliverytime);
		}
	</script>
	<script type="text/javascript" src="/statics/js/freight/addorupd.js"></script>
</body>
</html>