<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>预览</title>
<Style>
.mzh_sp{height:auto;}
</Style>
</head>
<body>
<input type="hidden" id="shareType" value="${shareType}"> 

<script type="text/javascript">
    $(function(){
    	if($("#shareType").val()==1){
    		$("#div_jy").show();
    	}if($("#shareType").val()==2){	
    		$("#div_dange").show();
    	}if($("#shareType").val()==3){
    		$("#div_duoge").show();
    	}
    	
    })
</script>
	<div class="zhuag_suv bor_si fl bg_white" style="min-height:710px;display:none;"id="div_jy" >
		<div class="p10">
			<div class="fl outermost">
				<div class="fl tc mt_30 mzh_100">
					<h2 class="f24 mb_20">${title }</h2>
					<h4 class="f16">${des }</h4>
				</div>
			</div>
			<div class="fl outermost mt_30">
				<c:forEach var="pro" items="${list }">
					<div class="mzh_sp">
						<img src="${pro.productPicture }" width="180" height="180">
						<div class="mzh_sp_div">${pro.productName }</div>
						<span class="mzh_sp_span">现价：￥${pro.retailPrice } <font class="ml_10 f12">￥${pro.displayPrice }</font></span>
					</div>
				</c:forEach>
			</div>
		</div>
			<div class="blank1"></div>
		</div>
		<!-- ****************** -->
		<div class="fr conter_right" style="display:none;" id="div_dange">
        <div class="zhuag_suv bor_si fl bg_white" style="min-height: 710px;">
            <div class="p10">
                <div class="fl outermost">
                   
                    <div class="fl tc mt_30" style="width: 900px;">
                        <h2 class="f24 mb_20">${title }</h2>
                        <h4 class="f16">${des }</h4>
                    </div>
                   
                </div>
                <div class="fl outermost mt_30">
                   <c:forEach var="pro" items="${list }">
                    <div class="mzh_sp" style="width: 300px;margin-left: 320px;">
                        <img src="${pro.productPicture }" width="300" height="300">
                        <div class="mzh_sp_div">${pro.productName }</div>
                        <span class="mzh_sp_span ft_red">￥${pro.retailPrice }<font class="ml_10 f12 ft_c3">￥${pro.displayPrice }</font></span>
                    </div>
                    </c:forEach>
                </div>
                <div class="fl outermost mt_20">
                <c:forEach var="pr" items="${singList }">
                    <div class="mzh_sp">
                        <img src="${pr.imageUrl}">
                        <div class="mzh_sp_div">${pr.description}</div>
                    </div>       
                </c:forEach>
                </div>
              
            </div>
         </div>
       <div class="blank1"></div>     
    </div>
     <div class="zhuag_suv bor_si fl bg_white" style="min-height: 710px;display:none;" id="div_duoge">
            <div class="p10">
                <div class="fl outermost">           
                    <div class="fl tc mt_30" style="width: 100%;">
                        <h2 class="f24 mb_20">${title }</h2>
                        <h4 class="f16">${des }</h4>
                    </div>
                    <div class="fr">              
                    </div>
                    <div id="mzh_fenxiang" class="fr mr_10">
                        <div class="mzh_fenxiang_no" id="aaa">                      
                        </div>
                    </div>
                </div>
                <div class="fl outermost mt_30">
                    <c:forEach var="pro" items="${list }">
                    <div class="mzh_sp">
                        <img src="${pro.productPicture}" width="200" height="200">
                        <div class="mzh_sp_div">${pro.productName }</div>
                        <span class="mzh_sp_span ft_red">￥${pro.retailPrice }<font class="ml_10 f12 ft_c3">￥${pro.displayPrice }</font></span>   
                        <div class="mzh_sp_div ft_c9">${pro.companyName}</div>      
                    </div>
                    </c:forEach>
                </div>
                <div class="blank1"></div>
            </div>
        </div>
    <!-- ********************************* -->		
</body>
</html>