<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="pg" uri="http://www.okwei.com/pagination"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>微店中心</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/statics/js/lunbo/jquery.tools.tabs.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/statics/js/lunbo/newIndex.js?v=2"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/statics/js/share.js?v=2"></script>
<script type="text/javascript">
/* 首页-分享 */
$(function(){
	$("#mzh_fenxiang").mouseover(function(){
	    $(this).find("[id=aaa]").attr("class","mzh_fenxiang_yes");
	    $(".mzh_fenxiang").show();
	})
	$("#mzh_fenxiang").mouseout(function(){
	    $(this).find("[id=aaa]").attr("class","mzh_fenxiang_no");
	    $(".mzh_fenxiang").hide();
	})
})
</script>
<style type="text/css">
	.mzh_fenxiang {
	    background: #fff none repeat scroll 0 0;
	    border: 1px solid #ccc;
	    display: block;
	    left: -1px;
	    padding-right: 10px;
	    position: absolute;
	    top: 22px;
	    width: 100px;
	}
	.conter_left{height:500px;}
</style>
</head>

<body class="bg_f3">
	<form id="searcherForm" name="searcherForm" action="<%=basePath%>distributor/list" onsubmit="return false;">
		<div class="fr conter_right">
      <div class="conter_center fl" style="height:500px;">
        <div class="txs_imgs fl">
          <div class="portrait fl">
            <div class="fl img_port"><img width="85" height="85" src="${userinfo.weiImg}"></div>
            <div class="fl wei_name">
              <div class="name_one f14 ft_hui">
                <span>${userinfo.weiName}的微店</span> 
                  <div id="mzh_fenxiang">
                      <div id="aaa" class="mzh_fenxiang_no"><img style="margin: 0px;" src="<%=request.getContextPath()%>/statics/pic/fenxiang_icon.png">
                          <div class="mzh_fenxiang" style="display: none;">
                              <a href="javascript:shareto(0);"><img src="http://base.okimgs.com/images/TX_kj.gif"></a>
                              <a href="javascript:shareto(1);"><img src="http://base.okimgs.com/images/TX_wb.gif"></a>
                              <a href="javascript:shareto(2);"><img src="http://base.okimgs.com/images/XL_wb.gif"></a>
                          </div>
                      </div>
                  </div>
                
                
                
                  
                 <!-- 
                 	<a id="mzh_fenxiang" class="mzh_fenxiang_no" href="#"><img style="margin: 0px;" src="<%=request.getContextPath()%>/statics/pic/fenxiang_icon.png">
                     <div class="mzh_fenxiang">
                     	  <a id="mzh_fenxiang" class="mzh_fenxiang_no" href="#"></a>
                     	  <a href="#"><img src="http://base.okimgs.com/images/TX_kj.gif"></a>
                          <a href="#"><img src="http://base.okimgs.com/images/TX_wb.gif"></a>
                          <a href="#"><img src="http://base.okimgs.com/images/XL_wb.gif"></a>
                      </div> 
                   </a>
                 
                  --> 
                  
              </div>
              <div class="name_two">
                <h2 class="ft_hui f14">${userinfo.weiID}</h2>
              </div>
              <div class="name_thr"> <span class="fl f12">安全等级</span> <span class="fl wai_hui"><i class="fl nei_blue" style="width:${uservo.securestep}%;"></i></span> <span class="fl f12 scale">${uservo.stepstr}</span>
                <div style="margin-top:4px; margin-left:6px;" class="gygl_top_l_xx_ypr"> 
                <c:if test="${uservo.yun==1 }">
                <span class="gygl_top_l_xx_hong mr_5 fw_b">云</span>
                </c:if>
                <c:if test="${uservo.batch==1 }"> 
                <span class="gygl_top_l_xx_lan fw_b mr_5">批</span> 
                </c:if>
                <c:if test="${uservo.verifer==1 }">
                <span class="gygl_top_l_xx_huang mr_5 fw_b">认</span> 
                </c:if>
                </div>
              </div>
            </div>
            <!--  
            <div class="shouhup_news fr"><a href="#">我的收货地址</a></div>
            -->
          </div>
        </div>
        <div class="fl purchase">
          <div class="fl img_fontme"> <img src="<%=request.getContextPath()%>/statics/pic/img_dingdan.png">
            <h6 class="ft_c6">我购买的订单</h6>
          </div>
          <div class="fl dfhs">
            <ul>
              <li>今日新订单<span>${uservo.todayordercout}</span></li>
              <li>待付款<span>${uservo.needpaycount}</span></li>
              <li>待发货<span>${uservo.needsendcount}</span></li>
              <li>待收货<span>${uservo.needacceptcount}</span></li>
              <li class="huise_color">待评价<span>${uservo.needsaycount}</span></li>
            </ul>
          </div>
        </div>
        <div class="fl purchase">
          <div class="fl img_fontme"> <img style="margin-left:10px;" src="<%=request.getContextPath()%>/statics/pic/img_sj.png">
            <h6 class="ft_c6">我上架的产品</h6>
          </div>
          <div class="fl dfhs mar_t20">
            <ul>
              <li>已上架<span>${uservo.oncount}</span></li>
              <!--  
              <li class="huise_color">即将到期<span>${uservo.needsaycount}</span></li>-->
              <li class="huise_color">已下架<span>${uservo.offcount}</span></li>
            </ul>
          </div>
        </div>
        <div style="border-bottom:none; height:108px;" class="fl purchase">
          <div class="fl img_fontme"> <img style="margin-left:16px;" src="<%=request.getContextPath()%>/statics/pic/img_fxs.png">
            <h6 class="ft_c6">我的分销商</h6>
          </div>
          <div class="fl dfhs">
            <ul>
              <li>昨天新增<span>${uservo.yaddedcount}</span></li>
              <li>分销商总量<span>${uservo.totalcount}</span></li>
            </ul>
          </div>
        </div>
      </div>
      <div class="conter_center_right fr">
        <div class="bor_si fl bg_white">
          <div class="center_title f14 ft_hui"><span>微店攻略</span>
          <!-- <a class="f12 ft_hui" href="#" target="_blank">更多</a> -->
            <div ><img width="14" height="10" src="<%=request.getContextPath()%>/statics/pic/top_jiaodu.png" class="gouzi"></div>
          </div>
          <div class="news_weid fl f12 ft_hui">
            <ul>
            <c:forEach var="msg" items="${uservo.noticemsg.glmsg}" varStatus="status">
            <li><a href="${msg.refstr}" title="${msg.title}" target="_blank">．${msg.title}</a></li>
            </c:forEach>
            </ul>
          </div>
        </div>
        <!--  
        <div class="img_qiehua fl"><a href="#"><img src="<%=request.getContextPath()%>/statics/pic/guag_gimg.png"></a></div>
        -->
        
         <div class="img_qiehua fl">
            <div class="slide">
                <div class="images">
                <c:forEach var="newimg" items="${uservo.noticemsg.imgmsg}" varStatus="status">
                    <div><a href="${newimg.refstr}" title="${newimg.title}" target="_blank"><img src="${newimg.imgstr}" /></a></div>
                </c:forEach>
                </div>
                <a class="backward png">prev</a><a class="forward png">next</a>
                <div class="tabs"> <a href="#"></a><a href="#"></a><a href="#"></a><a href="#"></a> <a href="#"></a></div>
            </div>
        </div>
      </div>
    </div>
	</form>

	<script type="text/javascript">
		$(document).ready(function() {
		    /* 首页-分享 */  
		    $("#mzh_fenxiang").mouseenter(function(){
		        $(this).find("[id=aaa]").attr("class","mzh_fenxiang_yes");
		        $(".mzh_fenxiang").show();
		    })
		    $("#mzh_fenxiang").mouseleave(function(){
		        $(this).find("[id=aaa]").attr("class","mzh_fenxiang_no");
		        $(".mzh_fenxiang").hide();
		    })
		});
		
		function shareto(type){
			var title="${userinfo.weiName}";
			var pageurl="http://${userinfo.weiID}.okwei.com";
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
	</script>

</body>