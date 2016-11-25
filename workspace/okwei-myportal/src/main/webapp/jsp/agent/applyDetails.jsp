<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="java.util.ResourceBundle"%>
<%
    String okweidomain = ResourceBundle.getBundle("domain").getString("okweidomain"); 
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>申请结果</title>
<link rel="stylesheet" type="text/css" href="/statics/js/uploadify/uploadify.css" />
<script type="text/javascript" src="/statics/js/uploadify/jquery.uploadify-3.1.min.js"></script>

<script type="text/javascript" src="/statics/js/agent/applyDetails.js"></script>
</head>
<body>
	<div class="fl fw_1024 bg_white bor_si">
		<div class="p20">
			<div class="blank"></div>
			<div class="gxn_yester ">
				<div class="table-datenews">
					<c:choose>
						<c:when test="${vo.status == 0 }">
							<!-- 待审核 -->
							<table width="400" border="0" cellpadding="5" cellspacing="0" class="mT10" style="margin-left: 150px;">
								<tbody>
									<tr>
										<td width="60"><i class="msg-right"></i></td>
										<td valign="middle" class="f14"><p class="cO tb">您的代理申请提交成功</p> 请等待平台商城审核</td>
									</tr>
									<tr>
										<td colspan="2"><a href="javascript:void(0)" class="btn_sel28_pre w150 dis_b" onclick="wins()" style="margin-left: 90px;">查看申请资料</a></td>
									</tr>
								</tbody>
							</table>
						</c:when>
						<c:when test="${vo.status == 1 }">
							<!-- 审核通过 -->
							<table width="400" border="0" cellpadding="5" cellspacing="0" class="mT10" style="margin-left: 150px;">
								<tbody>
									<tr>
										<td width="60"><i class="msg-right"></i></td>
										<td valign="middle" class="f14"><p class="cO tb">恭喜，您的代理商申请已审核通过，<br>已成为${code }的代理商</p></td>
									</tr>
									<tr>
										<td colspan="2"><a href="http://www.${vo.merchantWeiId }.<%=okweidomain %>?opType=2&demandId=${demandId }&w=${userinfo.weiID }"  target="_blank" class="btn_sel28_pre w150 dis_b" style="margin-left: 90px;">去商城进货</a></td>
									</tr>
									<tr>
										<td colspan="2">&nbsp;</td>
									</tr>
									<tr>
										<td colspan="2"><a href="javascript:void(0)" class="btn_sel28_pre w150 dis_b" onclick="wins()" style="margin-left: 90px;">查看申请资料</a></td>
									</tr>
								</tbody>
							</table>
						</c:when>
						<c:otherwise>
							<!-- 审核不通过 -->
							<table width="600" border="0" cellpadding="5" cellspacing="0" class="mT10" style="margin-left: 150px;">
								<tbody>
									<tr>
										<td width="60"><i class="msg-wrong"></i></td>
										<td valign="middle" class="f14"><p class="cO tb">sorry，您提交的代理商申请被${vo.merchantShopName }平台商城拒绝了！</p> 拒绝原因： ${vo.reason }</td>
									</tr>
									<tr>
										<td colspan="2"><a href="#" class="btn_sel28_pre w150 dis_b" onclick="again()" style="margin-left: 90px;">重新申请</a></td>
									</tr>
								</tbody>
							</table>
						</c:otherwise>
					</c:choose>
					<div class="blank"></div>
				</div>
			</div>
			<div class="blank"></div>


			<div class="ficnews fl" style="width: 720px; margin-left: 138px; margin-top: 30px">
				<div class="fl ficnewsleftone">
					<h3 class="f14 fm_wei pt5 pb5 ft_c6">商城负责人联系方式</h3>
					<img src="${mer.shopImg }" width="98" height="105" class="dis_b fl">
					<div class="fl ml_10">
						<p class="f14 pt5">商城负责人：${mer.linkName }</p>
						<p class="f14 pt5">手机：${mer.phone }</p>
					</div>
				</div>
				<div class="fl ficnewsleftone bor_le pl20">
					<h3 class="f14 fm_wei pt5 pb5 ft_c6">如有疑问，可咨询微店网认证员</h3>
					<img src="${ver.image }" width="98" height="105" class="dis_b fl">
					<div class="fl ml_10">
						<p class="f14 pt5">认证员：${ver.weiName }</p>
						<p class="f14 pt5">手机：${ver.phone }</p>
					</div>
				</div>
			</div>
			<div class="blank"></div>
		</div>
	</div>
	<div class="updata_tixian" style="display: none;" id="win_div_10">
		<dl class="xzgys f16 mb_10">
			<dd class="tr">姓名：</dd>
			<dt>
				<input type="text" class="xzgys_input" disabled="disabled" value="${vo.linkname }" style="width: 260px;" />
			</dt>
		</dl>
		<dl class="xzgys f16 mb_10">
			<dd class="tr">联系电话：</dd>
			<dt>
				<input type="text" class="xzgys_input" style="width: 260px;" disabled="disabled" value="${vo.phone }" />
			</dt>
		</dl>
		<dl class="xzgys f16 mb_10">
			<dd class="tr">代理地区：</dd>
			<dt>
				<input type="text" class="xzgys_input" style="width: 260px;" disabled="disabled" value="${code }" />
			</dt>
		</dl>
		<dl class="xzgys f16 mb_10">
			<dd class="tr">详细地址：</dd>
			<dt>
				<input type="text" class="xzgys_input" style="width: 260px;" disabled="disabled" value="${vo.address }" />
			</dt>
		</dl>
		<dl class="xzgys f16 mb_10">
			<dd class="tr">营业执照：</dd>
			<dt>
				<img src="${vo.imgs[0] }" width="100" height="100" />
			</dt>
		</dl>
		<dl class="xzgys f16 mb_10">
			<dd class="tr">推荐人微店号：</dd>
			<dt>
				<input type="text" class="xzgys_input" style="width: 260px;" disabled="disabled" value="${vo.developmentWeiId }" />
			</dt>
		</dl>

		<dl class="xzgys f16 mb_10">
			<dd class="tr">我的优势：</dd>
			<dt>${vo.intro }</dt>
		</dl>
	</div>




	<div class="updata_tixian" style="display: none;" id="win_div_9">
		<dl class="xzgys f16 mb_10">
			<dd class="tr">姓名：</dd>
			<dt>
				<input type="text" class="xzgys_input" style="width: 260px;" id="addname" value="${vo.linkname }" />
			</dt>
		</dl>
		<dl class="xzgys f16 mb_10">
			<dd class="tr">联系电话：</dd>
			<dt>
				<input type="text" class="xzgys_input" style="width: 260px;" id="addphone" maxlength="11"  value="${vo.phone }" />
			</dt>
		</dl> 
		<dl class="xzgys f16 mb_10">
			<dd class="tr">代理地区：</dd>
			<dt>
				<select class="xzgys_input" style="width: 125px;" id="sheng"> 
				<c:forEach var="reg" items="${reglist }">
					<option value="${reg.code }">${reg.name }</option>
				</c:forEach>
				</select> 
				<select class="xzgys_input" style="width: 125px; margin-left: 10px;" id="city" > 
				</select>
			</dt>
		</dl>
		<dl class="xzgys f16 mb_10">
			<dd class="tr">详细地址：</dd>
			<dt>
				<input type="text" class="xzgys_input" style="width: 260px;" id="address" value="${vo.address }" />
			</dt>
		</dl>
		<dl class="xzgys f16 mb_10">
			<dd class="tr">营业执照：</dd>
			<dt>
				<img src="http://img3.okdocuments.com/group1/M00/11/49/wKgKPlWA0OyAQBcYAAAHU0i8aa8750.png" width="100" height="100" id="imgs" />
				<input id="imgInput" type="button"  />
			</dt>
		</dl>
		<dl class="xzgys f16 mb_10">
			<dd class="tr">推荐人微店号：</dd>
			<dt>
				<input type="text" class="xzgys_input" maxlength="13" style="width: 260px;" id="recommend" value="${codeNum }" />
			</dt>
		</dl>
		<dl class="xzgys f16 mb_10">
			<dd class="tr">我的优势：</dd>
			<dt>
				<textarea class="xzgys_input" style="height: 50px; line-height: 20px; padding: 10px;" id="intro">${vo.intro }</textarea>
			</dt>
		</dl>
	</div>
	<input id="demands" type="hidden" value="${vo.demands[0].investmentDemandId }" />
	<input id="selectcode" type="hidden" value="${codeNum }" />
	<input id="laiyuanid" type="hidden" value="${vo.developmentWeiId }" />
	<input id="applyID" type="hidden" value="${applyID }" />
</body>
</html>