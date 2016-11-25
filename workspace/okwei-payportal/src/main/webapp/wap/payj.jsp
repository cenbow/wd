<%@page import="com.okwei.util.StringHelp"%>
<%@page import="java.text.MessageFormat"%>
<%@page import="java.util.ResourceBundle"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>微信支付</title>
    <meta http-equiv="Content-Type" content="text/html; charset=GBK" />
    <meta id="viewport" name="viewport" content="width=device-width; initial-scale=1.0; maximum-scale=1; user-scalable=no;" />
    <script src="<%= request.getContextPath() %>/statics/js/jquery-1.7.1.min.js" type="text/javascript"></script>
    <link href="<%= request.getContextPath() %>/statics/css/wap.css" rel="stylesheet" type="text/css" />
    <style type="text/css">
     .vc{margin:0 auto;display:-webkit-box;-webkit-box-orient:horizontal;-webkit-box-pack:center;-webkit-box-align:center;display:-moz-box;-moz-box-orient:horizontal;-moz-box-pack:center;-moz-box-align:center;display:-o-box;-o-box-orient:horizontal;-o-box-pack:center;-o-box-align:center;display:-ms-box;-ms-box-orient:horizontal;-ms-box-pack:center;-ms-box-align:center;display:box;box-orient:horizontal;box-pack:center;box-align:center}.g_bg13{background-color:#666;display:none;width:100%;height:100%;left:0;top:0;filter:alpha(opacity=50);opacity:.8;position:fixed!important;position:absolute}
    </style>
   <%-- 	源js，需从这里修改再压缩 --%>
   <script type="text/javascript">
            var validOrder = true;  //string.IsNullOrEmpty(errMsg)?"true":"false"
            var ua = navigator.userAgent.toLowerCase();
            var isWeixin5 = false; //环境是否支持支付
            var agentKey =  "micromessenger";
            if (ua.match(/micromessenger/i) == agentKey ) {
                    isWeixin5 = true;   
            }
            // 微信内置浏览器完成内部初始化
            document.addEventListener('WeixinJSBridgeReady', function onBridgeReady() {
                  if(!isWeixin5 || !validOrder ) return;
                  jQuery("#loadingwait").show();
                  jQuery('#getBrandWCPayRequest').click(function(e){
                     $(this).attr("disabled","disabled");//防止二次点击
                     WeixinJSBridge.invoke('getBrandWCPayRequest',
                        {
                           "appId" : "<%= request.getParameter("appid") %>", 
                           "timeStamp" : "<%=  request.getParameter("timestamp")  %>",
                          "nonceStr" : "<%=  request.getParameter("nonceStr")  %>", 
                           "package" : "<%= request.getParameter("packageValue") %>",
                          "signType" : "<%=  request.getParameter("signType")  %>", 
						"paySign" : "<%=  request.getParameter("paySign")  %>"
                           }						   
                           ,function(res){
                               afterPay(res);
                           })
                     
                     });
                  jQuery('#getBrandWCPayRequest').click();
                  WeixinJSBridge.log('yo~ ready.');
                  }, false);
            function afterPay(res){
				//alert(res.err_msg);
               if(res.err_msg == "get_brand_wcpay_request:ok" ) {            	
                 delayAfterPay();
               }
			   else{
				   	alert(res.err_msg);
					if(res.err_msg=="get_brand_wcpay_request:fail_invalid appid"){
					//alert("不支持在此微信号或公众号内支付微店网订单，您可以点击右上角弹出菜单'收藏'，并在微信首页底部工具栏点'我'->'收藏'进行支付");
					}
					if(res.err_msg=="get_brand_wcpay_request:cancel"){
					}
					else{
						//alert(res.err_msg);
						alert("无法支付，可能当前公众号权限不足或您未使用微信登录，请点击按钮下载或进入APP");
					}	         
					showFavor();
                    $("#payfailed").show();
                   jQuery("#loadingwait").hide();
               } 
            }
            var delayCount =0,maxCount =3;
            function delayAfterPay(){             
                  jQuery("#loadingwait").hide();
                   $("#paysuccess").show();
				var returnurlStr= $("#returnurl").val();
				if(returnurlStr.length>0)
					location.href= returnurlStr;
                
            }
            String.prototype.stringFormat=function()  
            {  
              if(arguments.length==0) return this;  
              for(var s=this, i=0; i<arguments.length; i++)  
                s=s.replace(new RegExp("\\{"+i+"\\}","g"), arguments[i]);  
              return s;  
            };  
            function showFavor(){
				$("#divWeiXin,#favorbg").show();
			}			  
			function hideFavor(){
				$("#divWeiXin,#favorbg").hide();
			}	
			$(function(){
				$("#divWeiXin,#favorbg").click(function(){
					hideFavor();
				});
			});						  
    </script> 
 	<script  type="text/javascript">

 	</script> 
</head>
<body style="line-height:2.5em;">
	<input type="hidden" id="returnurl" name="returnurl" value="<%=  request.getParameter("returnurl")  %>" />
	<%--收藏提示
	 <img id="divWeiXin" style="position: absolute; right: 0px; z-index: 9999; display: none;width:100%;" src="/image/pay-favor.png">
	<div class="g_bg13" id='favorbg'  style="height: 3693px; display: none;"></div> -->
	 --%>
    <div id='loadingwait' class="mzh_bg vc" style="text-align: center; display: none;">
        <img src='http://base.okimgs.com/images/loading.gif' style='width: 42px; height: 42px;' /></div>
    <div class="top_s">
        <div class="xh_wap_sijilao">
            <!-- <nav><a href="??" id="head_back">&nbsp;<img src="Http://base.okimgs.com/images/xh_waping_fanhui.gif" width="8" height="14"  style="margin-top:0.6em;"/> 返回</a></nav> -->
        </div>
        <div class="top_L_xh" style="text-align: center; font-size: 2em; color: #FFF; padding: 0.2em 0 0.3em 0.3em;
            line-height: 1.6em;">
            微信支付
        </div>
    </div>
    <div style="width: 60%; margin: 0px auto; text-align: center; padding: 10px; font-size: 16px;">
       <%--  <% if (!string.IsNullOrEmpty(errMsg))
           { %>
        <div id="ordermsg">
            <%= errMsg%></div>
        
        <% } %> --%>
	   <div  >
            您的订单号：<%=  request.getParameter("order_no") %>!</a>
        </div>
		
        <div id="paysuccess" style="display: none;">
            支付成功！
        </div><!---->
        <div id="payfailed" style="display:none;"  >
            <ul>
                <li>支付遇到问题？</li>
                <li>可联系客服  4008-933-980 或
                    </li>
                <li>
                <a   style="display: block;width: 100%;font-size: 1.2em;text-align: center;background: #45c01a;color: #fff !important;padding-top: 0.6em;padding-bottom: 0.6em;border-radius: 0.4em;line-height:0.8em;height:0.8em;"
 href="http://a.app.qq.com/o/simple.jsp?pkgname=com.okwei.mobile" class=" block w fl f16 pt10 pb10 cW tc" style="background:#45c01a; border-radius:4px;">打开APP</a>
                		 查看、支付订单
                    </li>
				<!-- <li style="display:none;">若提示‘没有权限支付’，请尝试点击右上角弹出菜单'收藏'，并在微信首页底部工具栏点'我'->'收藏'进行支付！</li>  --> 
            </ul>
        </div>
    </div>
    <div class="m_dd">
        <ul>
            <li class="m_dd_1">
                <%-- <% if (string.IsNullOrEmpty(errMsg))
                   { %> --%>
                   	总价：<span>￥<%=  request.getParameter("totalfee") %></span>
               <%--  <% } %> --%>
                </li>
            <li class="m_dd_2"><a id="getBrandWCPayRequest" style="display: none" href="uppay://uppayservice/?style=token&paydata=">
                去支付</a></li>
        </ul>
    </div>
	<script type="text/javascript">$(function(){
		$("header,footer").remove();
	});</script>
</body>
</html>