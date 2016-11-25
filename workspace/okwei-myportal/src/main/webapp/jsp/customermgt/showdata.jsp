<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="pg" uri="http://www.okwei.com/pagination"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>查看资料</title>
<script type="text/javascript" src="/statics/js/layer/layer.min.js"></script>
<jsp:include page="/jsp/common/scriptstyle.jsp" />
</head>

<body class="bg_f3">
	<form method="post" id="addForm">
	<div class="updata_tixian"  id="win_div_8">
    <dl class="xzgys f16 mb_10">
        <dd class="tr">姓名：</dd>
        <dt>
            <span>${data.linkMan}</span>
        </dt>
    </dl>
    <dl class="xzgys f16 mb_10">
        <dd class="tr">联系电话：</dd>
        <dt>
            <span>${data.phone }</span>
        </dt>
    </dl>
     <dl class="xzgys f16 mb_10">
        <dd class="tr">详细地址：</dd>
        <dt>
            <span>${data.address }</span> 
        </dt>
    </dl>
    
    <dl class="xzgys f16 mb_10">
        <dd class="tr">营业执照：</dd>
        <dt>
            <img id="imgLicense" src="${data.licenseImg }" width="100" height="100" />
        </dt>
    </dl>
    <dl class="xzgys f16 mb_10">
        <dd class="tr">我的优势：</dd>
        <dt>
            <input id="txtMyGoodness" type="text" value="${data.advantage }"/>
        </dt>
    </dl> 
    <c:if test="${data.follow == 1 }">
     <dl class="xzgys f16 mb_10">
        <dd class="tr">跟进记录：</dd>
        <dt>
        <c:if test="${fn:length(data.followList) < 1 }">
			<tr>
				<td>暂无相关跟进记录</td>
			</tr>
		</c:if>
        <c:forEach items="${data.followList}" var="follow" varStatus="status">
        <tr>
        <td><p class="f14 ft_c6">${follow.createTime }<span class="ml_20">${follow.remaks }</span></p></td>
        </tr>
        	
        </c:forEach>
            <textarea id="txtNewFollow" class="xzgys_input p10 f14" style=" text-indent:0; height:40px; line-height:15px;"></textarea>
        </dt>
    </dl> 
    </c:if>
</div>
</form>
</body>

